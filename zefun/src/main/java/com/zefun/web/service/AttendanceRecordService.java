package com.zefun.web.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.enums.EmployeeRewardTypeEnum;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.EmployeeAttendanceDateUtil;
import com.zefun.web.dto.AttendanceRecordDto;
import com.zefun.web.entity.EmployeeAttendance;
import com.zefun.web.entity.EmployeeReward;
import com.zefun.web.entity.EmployeeShift;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.ShiftInfo;
import com.zefun.web.entity.StoreManageRule;
import com.zefun.web.mapper.EmployeeAttendanceMapper;
import com.zefun.web.mapper.EmployeeRewardMapper;
import com.zefun.web.mapper.EmployeeShiftMapper;
import com.zefun.web.mapper.ShiftMapper;
import com.zefun.web.mapper.StoreManageRuleMapper;
import com.zefun.web.vo.AttendanceRecordVo;

/**
 * 员工设置：考勤记录服务
 * @author lzc
 *
 */
@Service
public class AttendanceRecordService {
	
	/** 缓存服务对象 */
    @Autowired
    private RedisService redisService;
	
	/** 员工考勤映射 */
	@Autowired
	private EmployeeAttendanceMapper employeeAttendanceMapper;
	
	/** 员工(一周)班次关联映射 */
	@Autowired
	private EmployeeShiftMapper employeeShiftMapper;
	
	/** 员工具体班次信息映射 */
	@Autowired
	private ShiftMapper shiftMapper;
	
	/** 门店管理制度DAO */
	@Autowired
	private StoreManageRuleMapper storeManageRuleMapper;
	
	/** 员工奖惩映射 */
	@Autowired
	private EmployeeRewardMapper employeeRewardMapper;
	
	/**
	 * 查询员工考勤记录
	 * @param vo  考勤查询条件
	 * @return  ModelAndView
	 */
	public ModelAndView findAttendanceRecordSelective(AttendanceRecordVo vo) {
		Page<AttendanceRecordDto> page = findByPage(1, App.System.API_DEFAULT_PAGE_SIZE, vo);
//		Page<AttendanceRecordDto> page = findByPage(1, 25, vo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("page", page);
		mav.setViewName(View.AttendanceRecord.HOME);
		return mav;
	}
	
	/**
	 * 员工考勤记录分页查询
	 * @param pageNo    页码
	 * @param pageSize  页容量
	 * @param vo  考勤查询条件
	 * @return Page<AttendanceRecordDto>
	 */
	public Page<AttendanceRecordDto> findByPage(int pageNo, int pageSize, AttendanceRecordVo vo) {
		//js清空不了值
		if (vo.getEmployeeName() != null && vo.getEmployeeName().length() == 0) {
			vo.setEmployeeName(null);
		}
		if (vo.getAttendanceDate() != null && vo.getAttendanceDate().length() == 0) {
			vo.setAttendanceDate(null);
		}
		Page<AttendanceRecordDto> page = new Page<AttendanceRecordDto>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storeId", vo.getStoreId());
		params.put("employeeName", vo.getEmployeeName());
		params.put("attendanceDate", vo.getAttendanceDate());
		page.setParams(params);
		List<AttendanceRecordDto> list = employeeAttendanceMapper.selectAttendanceRecordSelective(page);
		for (int i=0; i<list.size(); i++) {
			AttendanceRecordDto ard = list.get(i);
			if (ard.getSignInOffset() != null) {
				if (ard.getSignInOffset() >= 0) {
					ard.setSignInOffsetToTimeDetail("0");
				}
				else if (ard.getSignInOffset() < 0) {
					ard.setSignInOffsetToTimeDetail(EmployeeAttendanceDateUtil.minuteToTimeDetail(- ard.getSignInOffset()));
				}
			}
			if (ard.getSignOutOffset() != null) {
				if (ard.getSignOutOffset() > 0) {
					ard.setSignOutOffsetTimeDetail("0");
				}
				else if (ard.getSignOutOffset() < 0) {
					ard.setSignOutOffsetTimeDetail(EmployeeAttendanceDateUtil.minuteToTimeDetail(ard.getSignOutOffset()));
				}
				else {
					if (! new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(ard.getAttendanceDate())) {
						ard.setSignOutOffsetTimeDetail("0");
					}
				}
			}
		}
		page.setResults(list);
		return page;
	}
	
	/**
	 * 修改考勤记录
	 * @param employeeAttendance  考勤实体
	 * @param userId  修改人id
	 * @param storeId  店铺id
	 * @return  true：成功，false：失败
	 * @throws ParseException 
	 */
	public boolean editEmployeeAttendance(EmployeeAttendance employeeAttendance, Integer userId, Integer storeId) throws ParseException {
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    	//根据参数考勤id查询数据库已经存在的对应考勤记录(原始考勤记录)
    	EmployeeAttendance originalEmployeeAttendance = employeeAttendanceMapper.selectByPrimaryKey(employeeAttendance.getRecordId());
    	originalEmployeeAttendance.setModifyer(userId.toString());
    	//根据原始考勤记录中的打卡日期得到他的当天班次
    	ShiftInfo shiftInfo = findShiftByAttendance(originalEmployeeAttendance);
    	//查询该门店的迟到规则(store_manage_rule)
		StoreManageRule storeManageRuleOfLate = storeManageRuleMapper.selectRuleByRuleNameAndStoreId(storeId, "迟到");
		//该门店旷工规则
		StoreManageRule storeManageRuleOfAbsenteeism = storeManageRuleMapper.selectRuleByRuleNameAndStoreId(storeId, "旷工");
		//该门店早退规则
		StoreManageRule storeManageRuleOfEarlyLeave = storeManageRuleMapper.selectRuleByRuleNameAndStoreId(storeId, "早退");
		//该员工该天旷工奖惩记录
		EmployeeReward rewardOfAbsenteeism = employeeRewardMapper.selectRewardByEmployeeIdAndTypeAndDate(
				  originalEmployeeAttendance.getEmployeeId(), String.valueOf(EmployeeRewardTypeEnum.ABSENTEEISM.getEmployeeRewardType()), 
				  originalEmployeeAttendance.getAttendanceDate());
		//该员工该天迟到奖惩记录
		EmployeeReward rewardOfLate = employeeRewardMapper.selectRewardByEmployeeIdAndTypeAndDate(originalEmployeeAttendance.getEmployeeId(), 
				  String.valueOf(EmployeeRewardTypeEnum.LATE.getEmployeeRewardType()), originalEmployeeAttendance.getAttendanceDate());
		//该员工该天早退奖惩记录
		EmployeeReward rewardOfEarlyLeave = employeeRewardMapper.selectRewardByEmployeeIdAndTypeAndDate(originalEmployeeAttendance.getEmployeeId(), 
				  String.valueOf(EmployeeRewardTypeEnum.EARLY_LEAVE.getEmployeeRewardType()), originalEmployeeAttendance.getAttendanceDate());
    	/**
    	 * 如果修改了签到时间
    	 */
    	/*
		 * 如果修改的考勤记录中员工签到时间为空
		 * 		则检查奖惩表中该员工该天是否存在迟到记录，存在就删除
		 * 		再检查奖惩表中该员工该天是否存在旷工记录，不存在就添加一条
		 */
		if (employeeAttendance.getSignInTime() == null || employeeAttendance.getSignInTime().trim().length() == 0 
				  && originalEmployeeAttendance.getSignInTime() != null) {
			originalEmployeeAttendance.setSignInTime(null);
			originalEmployeeAttendance.setSignInOffset(null);
			if (rewardOfLate != null) {
				employeeRewardMapper.deleteByPrimaryKey(rewardOfLate.getRewardId());
			} 
			if (rewardOfAbsenteeism == null) {
				employeeRewardMapper.insertSelective(new EmployeeReward(originalEmployeeAttendance.getEmployeeId(), "4", "0", 
						  storeManageRuleOfAbsenteeism.getProcessMoney().doubleValue(), userId, originalEmployeeAttendance.getAttendanceDate(), 
						  "旷工：用户'" + userId + "'在'" + nowTime + "'清空了员工id'" + originalEmployeeAttendance.getEmployeeId() +"'的签到时间"));
			}
		}
		/*
		 * 否则如果修改的考勤记录中员工签到时间不为空
		 * 检查旷工迟到奖惩
		 * if  signInDifference > 0  
		 * 		则检查奖惩表中该员工该天是否存在旷工记录，存在就删除
		 * 		再检查奖惩表中该员工该天是否存在迟到记录，存在就删除
		 * if  signInDifference < 0 
		 * 		if signInDifference绝对值 > 该店铺旷工时间  
		 * 			则检查奖惩该员工该天是否存在旷工记录，不存在就添加旷工；检查是否有迟到记录，有则删除
		 * 		else if  signInDifference绝对值 > 该店铺迟到时间
		 * 			则检查奖惩该员工该天是否存在迟到记录，不存在就添加迟到；检查是否有旷工记录，有则删除
		 */
		else if (employeeAttendance.getSignInTime() != null 
				  && ! employeeAttendance.getSignInTime().equals(originalEmployeeAttendance.getSignInTime())) {
    		originalEmployeeAttendance.setSignInTime(employeeAttendance.getSignInTime());
    		//操作人修改的签到时间和班次该天该员工上班开始时间的时间差(分钟)，正数表示前者早于后者(也就是前者 < 后者，这时候表示没迟到)
    		int signInDifference = DateUtil.getTwoTimeBetween(employeeAttendance.getSignInTime(), 
    				     originalEmployeeAttendance.getAttendanceDate() + " " + shiftInfo.getStartTime() + ":00");
			originalEmployeeAttendance.setSignInOffset(signInDifference);
			if (signInDifference > 0) {
				if (rewardOfAbsenteeism != null) {
					employeeRewardMapper.deleteByPrimaryKey(rewardOfAbsenteeism.getRewardId());
				}
				if (rewardOfLate != null) {
					employeeRewardMapper.deleteByPrimaryKey(rewardOfLate.getRewardId());
				}
			}
			else if (signInDifference <= 0) {
				//如果符合旷工(并且该员工该日期没有旷工记录)
				if (signInDifference + storeRuleViolationMinute(storeManageRuleOfAbsenteeism.getTargetType(), 
						  storeManageRuleOfAbsenteeism.getTargetValue()) <= 0 && rewardOfAbsenteeism == null) {
					employeeRewardMapper.insertSelective(new EmployeeReward(originalEmployeeAttendance.getEmployeeId(), "4", "0", 
							  storeManageRuleOfAbsenteeism.getProcessMoney().doubleValue(), userId, originalEmployeeAttendance.getAttendanceDate(), 
							  "旷工：用户'" + userId + "'在'" + nowTime + "'修改了员工id'" + originalEmployeeAttendance.getEmployeeId() +"'的签到时间"));
					if (rewardOfLate != null) {
						employeeRewardMapper.deleteByPrimaryKey(rewardOfLate.getRewardId());
					}
				}
				//否则如果符合迟到(并且该员工该日期没有迟到记录)
				else if (signInDifference + storeRuleViolationMinute(storeManageRuleOfLate.getTargetType(), 
						  storeManageRuleOfLate.getTargetValue()) <= 0 && rewardOfLate == null) {
					employeeRewardMapper.insertSelective(new EmployeeReward(originalEmployeeAttendance.getEmployeeId(), "1", "0", 
							  storeManageRuleOfLate.getProcessMoney().doubleValue(), userId, originalEmployeeAttendance.getAttendanceDate(), 
							  "迟到：用户'" + userId + "'在'" + nowTime + "'修改了员工id'" + originalEmployeeAttendance.getEmployeeId() +"'的签到时间"));
					if (rewardOfAbsenteeism != null) {
						employeeRewardMapper.deleteByPrimaryKey(rewardOfAbsenteeism.getRewardId());
					}
				}
			}
    	}
    	/**
    	 * 如果修改了签退时间
    	 */
		/*
		 * 如果修改的考勤记录中员工签退时间为空
		 * 这时候比较需要修改的考勤记录(employeeAttendance)中的考勤时间(这个时间用originalEmployeeAttendance.getAttendanceDate()比较安全)和系统当前天
		 * if  是同一天
		 * 		再比较系统小时分钟(暂且定义为HourA)和该员工该天的班次结束时间(暂且定义为HourB)
		 * 		if HourA < HourB，表示HourA早于HourB，即当前时间该员工理论上属于上班时间段
		 * 			把redis该员工的打卡状态清空即可(redisService.hdel(App.Redis.EMPLOYEE_ATTENDANCE_STATUS_HASH, employeeId)),
		 * 			再检查该员工该天是否有早退，有则删除
		 */
		if (employeeAttendance.getSignOutTime() == null || employeeAttendance.getSignOutTime().trim().length() == 0 
				  && originalEmployeeAttendance.getSignOutTime() != null) {
			if (new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(originalEmployeeAttendance.getAttendanceDate())) {
				originalEmployeeAttendance.setSignOutTime(null);
				redisService.hdel(App.Redis.EMPLOYEE_ATTENDANCE_STATUS_HASH, originalEmployeeAttendance.getEmployeeId());
				if (rewardOfEarlyLeave != null) {
    				employeeRewardMapper.deleteByPrimaryKey(rewardOfEarlyLeave.getRewardId());
    			}
			}
		}
		/*
		 * 否则如果修改的考勤记录中员工签退时间不为空
		 * 计算签退时间差：signOutDifference
		 * 		if signOutDifference > 0，表示没早退
		 * 			查看该员工该天是否有早退记录，有则删除
		 * 		if signOutDifference < 0，表示早退了
		 * 			查看该员工该天是否有早退记录，没有则添加
		 */
		else if (employeeAttendance.getSignOutTime() != null 
				  && ! employeeAttendance.getSignOutTime().equals(originalEmployeeAttendance.getSignOutTime())) {
			originalEmployeeAttendance.setSignOutTime(employeeAttendance.getSignOutTime());
    		//操作人修改的签退时间和该天该员工下班时间的时间差(分钟)，正数表示前者晚于后者(也就是前者 > 后者，这时候表示没早退)
    		int signOutDifference = DateUtil.getTwoTimeBetween(
    				     originalEmployeeAttendance.getAttendanceDate() + " " + shiftInfo.getEndTime() + ":00", employeeAttendance.getSignOutTime());
    		originalEmployeeAttendance.setSignOutOffset(signOutDifference);
    		if (signOutDifference > 0 && rewardOfEarlyLeave != null) {
    			employeeRewardMapper.deleteByPrimaryKey(rewardOfEarlyLeave.getRewardId());
    		}
    		else if (signOutDifference <= 0 && rewardOfEarlyLeave == null) {
    			employeeRewardMapper.insertSelective(new EmployeeReward(originalEmployeeAttendance.getEmployeeId(), "2", "0", 
    					   storeManageRuleOfEarlyLeave.getProcessMoney().doubleValue(), userId, originalEmployeeAttendance.getAttendanceDate(), 
    					   "早退：用户'" + userId + "'在" + nowTime + "'修改了员工id'" + originalEmployeeAttendance.getEmployeeId() +"'的签退时间"));
    		}
    		originalEmployeeAttendance.setSignOutOffset(signOutDifference);
		}
    	return employeeAttendanceMapper.updateByPrimaryKey(originalEmployeeAttendance) == 1 ? true : false;
    }
	
	/**
	 * 删除考勤记录
	* @author DavidLiang
	* @date 2016年1月14日 下午8:12:54
	* @param recordId  考勤id
	* @return  是否成功
	 */
	public boolean deleteEmployeeAttendance(int recordId) {
		return employeeAttendanceMapper.deleteByPrimaryKey(recordId) == 1 ? true : false;
	}
	
    /**
     * 根据考勤记录查询他当天班次
    * @author DavidLiang
    * @date 2016年1月14日 上午11:03:40
    * @param employeeAttendance  考勤实体
    * @return  班次
     */
    private ShiftInfo findShiftByAttendance(EmployeeAttendance employeeAttendance) {
    	//该员工一周班次(employeeShift)
    	EmployeeShift employeeShift = employeeShiftMapper.selectEmployeeShiftByEmployeeId(employeeAttendance.getEmployeeId());
    	int shiftId ;
    	//根据employeeAttendance中的考勤日期得到是周几
    	String week = null;
		try {
			week = DateUtil.getWeekOfDate(new SimpleDateFormat("yyyy-MM-dd").parse(employeeAttendance.getAttendanceDate()));
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		switch (week) {
			case "星期一":
				shiftId = employeeShift.getShifIda();
				break;
			case "星期二":
				shiftId = employeeShift.getShifIdb();
				break;
			case "星期三":
				shiftId = employeeShift.getShifIdc();
				break;
			case "星期四":
				shiftId = employeeShift.getShifIdd();
				break;
			case "星期五":
				shiftId = employeeShift.getShifIde();
				break;
			case "星期六":
				shiftId = employeeShift.getShifIdf();
				break;
			case "星期日":
				shiftId = employeeShift.getShifIdg();
				break;
			default:
				shiftId = 0;
				break;
		}
		if (shiftId > 0) {
			return shiftMapper.selectByPrimaryKey(shiftId);
		}
		return null;
    }
    
    /**
     * 计算该店铺违规时间(分钟)
    * @author DavidLiang
    * @date 2016年1月20日 下午8:08:03
    * @param targetType  违规时间类型(1:分钟，2:小时)
    * @param targetValue  违规时间数值
    * @return  分钟
     */
    private int storeRuleViolationMinute(int targetType, int targetValue) {
    	if (targetType == 2) {
    		return targetValue * 60;
    	}
    	return targetValue;
    }

}
