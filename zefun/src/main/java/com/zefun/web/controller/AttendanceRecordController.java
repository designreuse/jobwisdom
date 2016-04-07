package com.zefun.web.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.web.dto.AttendanceRecordDto;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.EmployeeAttendance;
import com.zefun.web.entity.Page;
import com.zefun.web.mapper.EmployeeAttendanceMapper;
import com.zefun.web.service.AttendanceRecordService;
import com.zefun.web.vo.AttendanceRecordVo;

/**
 * 员工设置：考勤记录控制器
 * 
 * @author lzc
 *
 */
@Controller
public class AttendanceRecordController extends BaseController {
	
	/** 员工考勤DAO */
	@Autowired
	private EmployeeAttendanceMapper employeeAttendanceMapper;
	
	/** 员工设置：考勤记录服务 */
	@Autowired
	private AttendanceRecordService attendanceRecordService;

	/**
	 * 查询员工考勤记录
	 * 
	 * @param request 请求
	 * @param response 返回
	 * @param vo 考勤记录vo
	 * @return ModelAndView
	 */
	@RequestMapping(value = Url.AttendanceRecord.VIEW_ATTENDANCE_RECORD_HOME)
	public ModelAndView searchAttendanceRecordHome(HttpServletRequest request, HttpServletResponse response,
					AttendanceRecordVo vo) {
		vo.setStoreId(getStoreId(request));
		return attendanceRecordService.findAttendanceRecordSelective(vo);
	}
	
	/**
	 * 根据主键查询考勤信息
	* @author DavidLiang
	* @date 2016年1月14日 下午4:32:17
	* @param recordId  考勤主键
	* @return  考勤实体
	 */
	@RequestMapping(value = Url.AttendanceRecord.ACTION_FIND_EMPLOYEE_ATTENDANCE_BYPRIMARY)
	@ResponseBody
	public EmployeeAttendance findEmployeeAttendanceByPrimary(Integer recordId) {
		if (recordId == null || recordId <= 0) {
			return null;
		}
		return employeeAttendanceMapper.selectByPrimaryKey(recordId);
	}
	
	/**
	 * 分页查询员工考勤记录
	 * @param request  请求
	 * @param vo  查询条件
	 * @param page  查询页码页容量
	 * @return  分页考勤记录BaseDto
	 */
	@RequestMapping(value = Url.AttendanceRecord.ACTION_LIST_EMPLOYEE_ATTENDANCE, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto findAttendanceRecord(HttpServletRequest request, AttendanceRecordVo vo, Page<AttendanceRecordDto> page) {
		vo.setStoreId(getStoreId(request));
		page = attendanceRecordService.findByPage(page.getPageNo(), page.getPageSize(), vo);
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
	}
	
	/**
	 * 修改考勤记录
	* @author DavidLiang
	* @date 2016年1月14日 下午3:37:08
	* @param request  请求
	* @param response  响应
	* @param employeeAttendance  考勤记录实体
	* @return  BaseDto
	 */
	@RequestMapping(value = Url.AttendanceRecord.ACTION_EDIT_EMPLOYEE_ATTENDANCE, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto editEmployeeAttendance(HttpServletRequest request, HttpServletResponse response, EmployeeAttendance employeeAttendance){
		boolean result;
		//时间可能是"2015-12-09 12:00:00",也可能是"2015/12/09 12:00"
		/*if (employeeAttendance.getSignInTime().contains("/")) {
			String signInTime = employeeAttendance.getSignInTime();
			signInTime = signInTime.replaceAll("/", "-");
			signInTime = signInTime.concat(":00");
			employeeAttendance.setSignInTime(signInTime);
		}
		if (employeeAttendance.getSignOutTime().contains("/")) {
			String signOutTime = employeeAttendance.getSignOutTime();
			signOutTime = signOutTime.replaceAll("/", "-");
			signOutTime = signOutTime.concat(":00");
			employeeAttendance.setSignOutTime(signOutTime);
		}*/
		if (employeeAttendance.getSignInTime().contains("%20")) {
			employeeAttendance.setSignInTime(employeeAttendance.getSignInTime().replaceAll("%20", " "));
		}
		if (employeeAttendance.getSignOutTime().contains("%20")) {
			employeeAttendance.setSignOutTime(employeeAttendance.getSignOutTime().replaceAll("%20", " "));
		}
		try {
			result = attendanceRecordService.editEmployeeAttendance(employeeAttendance, getUserId(request), getStoreId(request));
		} 
		catch (ParseException e) {
			result = false;
			e.printStackTrace();
		}
		if (result) {
			return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "修改成功");
		}
		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "修改失败");
	}
	
	/**
	 * 删除考勤记录
	* @author DavidLiang
	* @date 2016年1月14日 下午8:15:22
	* @param request  请求
	* @param response  响应
	* @param recordId  考勤id
	* @return  BaseDto
	 */
	@RequestMapping(value = Url.AttendanceRecord.ACTION_DELETE_EMPLOYEE_ATTENDANCE, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto deleteEmployeeAttendance(HttpServletRequest request, HttpServletResponse response, int recordId){
		if (attendanceRecordService.deleteEmployeeAttendance(recordId)) {
			return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "删除成功");
		}
		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "删除失败");
	}

}
