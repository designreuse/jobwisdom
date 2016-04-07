package com.zefun.api.job;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.api.entity.ShiftInfo;
import com.zefun.api.mapper.ShiftMapper;
import com.zefun.api.service.RedisService;
import com.zefun.api.utils.App;
import com.zefun.api.utils.DateUtil;

/**
 * 员工考勤状态重置(2 -> null)定时器类
 * @author lzc
 *
 */
public class EmployeeAttendanceStatusJob {
	
	/** 日志对象 */
    private Logger logger = Logger.getLogger(EmployeeAttendanceJob.class);
    
    /** 缓存服务对象 */
    @Autowired
    private RedisService redisService;
    
    /** 员工班次信息映射 */
    @Autowired
    private ShiftMapper shiftMapper;
    
	/**
	 * 员工考勤状态重置执行函数
	 * 已知条件：员工id集，考勤状态为2
	 * 目的：把考勤状态为2的员工在合适的时间重置("2" -> "")
	 * 1.检查该员工昨天班次
	 * 	if 没跨天(说明他在今天该时刻已经上完今天班次) -> 今天23重置即可
	 *  if 有跨天
	 *  	2.查询该员工今天是否有班次
	 *  		if 无  -> 今天23重置
	 *  		if 有  -> 根据昨天下班时间和今天上班时间折中重置
	 */
	public void execute() {
		logger.info("EmployeeAttendanceStatusJob execute start...");
		//当前时间是否是凌晨
		boolean isSmallHours = DateUtil.isSmallHours();
//		boolean isSmallHours = true;
		//今天星期几(周日是1，周一是2)
		int weekToday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		//昨天星期几
		int weekYesterday = weekToday - 1 == 0 ? 7 : weekToday - 1;
		/** 所有员工考勤状态 Map<employeeId, status(null,1,2)> */
		Map<String, String> allEmployeeStatusMap = redisService.hgetAll(App.Redis.EMPLOYEE_ATTENDANCE_STATUS_HASH);
		if (allEmployeeStatusMap == null || allEmployeeStatusMap.size() == 0) {
		    return;
		}
		//考勤状态为2的所有员工id集
		List<String> allEmployeeIdList = new ArrayList<String>();
		for(String key : allEmployeeStatusMap.keySet()) {
			if(null != key && allEmployeeStatusMap.get(key).equals("2")) {
				allEmployeeIdList.add(key);
			}
		}
		/*
		 * 这些考勤状态为2的员工今天的班次集
		 * (如果有员工在上班时间是"昨天22:00到今天04:00"，今天查询他今天班次，如果他今天不上班就会出问题)
		 */
//		List<ShiftInfo> shiftInfoTomorrowList = shiftMapper.selectShiftByEmployeeId(weekDay, allEmployeeIdList);
		for(int i=0; i<allEmployeeIdList.size(); i++) {
			//该员工昨天班次(昨天班次一定有,因为他的状态已经是2了)
			ShiftInfo yesterdayShiftInfo = shiftMapper.selectShiftByEmployeeIdAndWeekday(weekToday, Integer.parseInt(allEmployeeIdList.get(i)));
			//该员工今天班次
			ShiftInfo todayShiftInfo = shiftMapper.selectShiftByEmployeeIdAndWeekday(weekYesterday, Integer.parseInt(allEmployeeIdList.get(i)));
			//if昨天班次跨天(两种情况)
			if(isBetweenTwoDay(yesterdayShiftInfo.getStartTime(), yesterdayShiftInfo.getEndTime())) {
				//if今天班次也有(采用折中方式重置状态)
				if(todayShiftInfo != null && Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == getModdleTime(
						yesterdayShiftInfo.getEndTime(), todayShiftInfo.getStartTime())) {
					redisService.hdel(App.Redis.EMPLOYEE_ATTENDANCE_STATUS_HASH, allEmployeeIdList.get(i));
				}
				//if今天班次没有
				else {
					//今天23:30重置状态就OK
					if(isSmallHours) {
						redisService.hdel(App.Redis.EMPLOYEE_ATTENDANCE_STATUS_HASH, allEmployeeIdList.get(i));
					}
				}
			}
			//if昨天班次没跨天(说明今天上班也完成)
			else {
				//今天23:30重置状态就OK
				if(isSmallHours) {
					redisService.hdel(App.Redis.EMPLOYEE_ATTENDANCE_STATUS_HASH, allEmployeeIdList.get(i));
				}
			}
		}
		logger.info("EmployeeAttendanceStatusJob execute finish...");
	}
	
	/**
	 * 判断班次(工作开始时间startTime,结束时间endTime)是否跨日
	 * @param startTime 班次开始时间(22:45)
	 * @param endTime 班次结束时间(04:15)
	 * @return true跨日，false不跨日
	 */
	private boolean isBetweenTwoDay(String startTime, String endTime) {
		String[] startTimeArray = startTime.split(":");
		String[] endTimeArray = endTime.split(":");
		if(Integer.parseInt(startTimeArray[0]) > Integer.parseInt(endTimeArray[0]))
			return true;
		return false;
	}
	
	/**
	 * 得到两次时间的折中时间
	 * @param startTime 昨天下班时间(04:15)
	 * @param endTime 今天上班时间(14:00)
	 * @return 折中时间小时段
	 */
	private int getModdleTime(String startTime, String endTime) {
		String[] startTimeArray = startTime.split(":");
		String[] endTimeArray = endTime.split(":");
		return (Integer.parseInt(endTimeArray[0]) - Integer.parseInt(startTimeArray[0])) / 2 + Integer.parseInt(startTimeArray[0]);
	}

}
