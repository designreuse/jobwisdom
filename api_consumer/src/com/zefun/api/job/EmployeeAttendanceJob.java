package com.zefun.api.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.api.dto.EmployeeShiftAndShiftInfoDto;
import com.zefun.api.entity.EmployeeAttendance;
import com.zefun.api.entity.EmployeeReward;
import com.zefun.api.entity.StoreManageRule;
import com.zefun.api.enums.EmployeeRewardTypeEnum;
import com.zefun.api.mapper.EmployeeAttendanceMapper;
import com.zefun.api.mapper.EmployeeRewardMapper;
import com.zefun.api.mapper.EmployeeShiftMapper;
import com.zefun.api.mapper.StoreManageRuleMapper;
import com.zefun.api.service.RedisService;
import com.zefun.api.service.ShiftMahjongService;
import com.zefun.api.utils.App;
import com.zefun.api.utils.DateUtil;

/**
 * 员工考勤定时任务
* @author 张进军
* @date Dec 6, 2015 2:45:29 PM 
*/
public class EmployeeAttendanceJob {
    
    /** 日志对象 */
    private Logger logger = Logger.getLogger(EmployeeAttendanceJob.class);
    
    /** 缓存服务对象 */
    @Autowired
    private RedisService redisService;
    
    /** 员工班次关联映射 */
    @Autowired
    private EmployeeShiftMapper employeeShiftMapper;
    
    /** 员工考勤记录映射 */
    @Autowired
    private EmployeeAttendanceMapper employeeAttendanceMapper;
    
    /** 门店管理制度映射 */
    @Autowired
    private StoreManageRuleMapper storeManageRuleMapper;
    
    /** 员工奖惩映射 */
    @Autowired
    private EmployeeRewardMapper employeeRewardMapper;
    
    /**员工轮牌操作对象映射*/
    @Autowired
    private ShiftMahjongService shiftMahjongService;

    /**
     * 考勤处理函数(检查旷工和自动签退)
    * @author 张进军
    * @date Dec 6, 2015 2:50:29 PM
     */
    public void execute() {
        logger.info("EmployeeAttendanceJob execute start...");
        //定时器逻辑
        String curTimeDay = DateUtil.getCurDate();
        String curTimeSecond = DateUtil.getCurTimeStr();
        //先看今天星期几
        int weekDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);  //周日是1，周一是2
        //从employee_shift查出今天(星期几)需要上班所有员工(关联)
        List<EmployeeShiftAndShiftInfoDto> employeeShiftList = employeeShiftMapper.selectEmployeeDtoByWeekday(weekDay);
        /**
         * 根据以上员工id集查询出上班员工班次
         * shifInfoList长度和employeeShiftList是一样的,
         * 因为shifInfoList就是从employeeShiftList抽出的主键查询的
         */
//        List<ShiftInfo> shiftInfoList = shiftMapper.selectShiftsByIds(employeeShiftIds);
        for(int i=0; i<employeeShiftList.size(); i++) {
        	/*
        	 * eg: 该员工该天工作开始时间：shiftInfoList.get(i).getStartTime() -> 08:45,  
        	 * 	      该员工该天工作结束时间：shiftInfoList.get(i).getEndTime() -> 12:00
        	 */
        	String beginTime = employeeShiftList.get(i).getStartTime();
        	String endTime = employeeShiftList.get(i).getEndTime();
        	String[] beginTimeArray = beginTime.split(":");
        	String[] endTimeArray = endTime.split(":");
        	//if跨日
            if (Integer.parseInt(beginTimeArray[0]) > Integer.parseInt(endTimeArray[0])) {
            	endTime = DateUtil.addOneDay() + " " + endTime + ":00";
            } else {
            	endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " " + endTime + ":00";
            }
            beginTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) +  " " + beginTime + ":00";
            int employeeId = employeeShiftList.get(i).getEmployeeId();
        	//该员工当天打卡(考勤)记录
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("employeeId", employeeId);
            params.put("time", curTimeDay);
        	EmployeeAttendance ea = employeeAttendanceMapper.selectEmployeeAttendanceByloyeeIdAndTime(params);
        	//如果没有打卡记录
        	if(ea == null) {
        		//查询该员工店铺旷工条件
        		StoreManageRule rule = storeManageRuleMapper.selectStoreRuleAbsenteeismByStoreId(employeeShiftList.get(i).getStoreId());
        		if(rule != null) {  //该判断实际上没必要
        			int storeRuleViolationMinute = storeRuleViolationMinute(rule.getTargetType(), rule.getTargetValue());
        			//根据当前时间和该员工当天班次开始时间作比较，看是否满足旷工条件(if 满足)
            		if(storeRuleViolationMinute + DateUtil.getTwoTimeBetween(curTimeSecond, beginTime) < 0) {
            			//再检查该员工当天有没有旷工记录
            		    Map<String, Object> map = new HashMap<String, Object>();
            		    map.put("employeeId", employeeId);
            		    map.put("type", EmployeeRewardTypeEnum.ABSENTEEISM.getEmployeeRewardType());
            		    map.put("time", curTimeDay);
            			EmployeeReward employeeRewardOfAbsenteeism = employeeRewardMapper.
                				selectRewardByEmployeeIdAndTimeAndType(map);
                		// 如果该员工当天没有旷工记录，需要记录旷工奖惩一条
                		if (employeeRewardOfAbsenteeism == null) {
                			EmployeeReward er = new EmployeeReward();
                    		er.setEmployeeId(employeeId);
                    		er.setType(String.valueOf(EmployeeRewardTypeEnum.ABSENTEEISM.getEmployeeRewardType()));
                    		er.setIsReward("0");
                    		er.setNumber(rule.getProcessMoney().doubleValue());
                    		er.setModifyer(0);
                    		er.setModifytime(curTimeSecond);
                    		er.setReasons("旷工:系统自动扫描");
                    		employeeRewardMapper.insertSelective(er);
                		}
            		}
        		}
        	}
        	//如果有打卡记录检查是否需要自动签退
        	else if( ea != null && ea.getSignOutTime() == null) {
        		//判断正常下班时间和当前时间比较,如果下班时间 < 当前时间 ,这时就需要自动签退 
        		int signOutOffSet = DateUtil.getTwoTimeBetween(endTime, curTimeSecond);
        		if(signOutOffSet > 0) {
        			try {
						//下牌
						shiftMahjongService.downShiftMahjong(employeeId);
						ea.setSignOutTime(curTimeSecond);
	        			ea.setSignOutOffset(signOutOffSet);
	        			ea.setModifyer(0);  //0代表系统
	        			ea.setComment("签退:系统自动签退");
	        			employeeAttendanceMapper.updateByPrimaryKeySelective(ea);
	        			//更新redis状态
	        			redisService.hset(App.Redis.EMPLOYEE_ATTENDANCE_STATUS_HASH, employeeId, "2");
					} catch (Exception e) {
						
					}
        		}
        	}
        }
        logger.info("EmployeeAttendanceJob execute finish...");
    }
    
    /**
     * 计算该店铺违规时间(分钟)
     * @param targetType  违规时间类型(1:分钟，2:小时)
     * @param targetValue  违规时间数值
     * @return 分钟
     */
    private int storeRuleViolationMinute(int targetType, int targetValue) {
    	if (targetType == 2) {
    		return targetValue * 60;
    	}
    	return targetValue;
    }
    
}
