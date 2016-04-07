package com.zefun.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeRewardDto;
import com.zefun.web.entity.EmployeeReward;
import com.zefun.web.entity.Page;
import com.zefun.web.service.EmployeeRewardService;
import com.zefun.web.vo.EmployeeRewardVo;

/**
 * 员工奖惩记录控制器
 * @author lzc
 *
 */
@Controller
public class EmployeeRewardController extends BaseController {
	
	/** 员工奖惩service */
	@Autowired
	private EmployeeRewardService employeeRewardService;
	
	/**
	 * 员工奖惩汇总主页查询
	 * @param request  请求
	 * @param vo  EmployeeRewardVo
	 * @return  ModelAndView
	 */
	@RequestMapping(value = Url.EmployeeReward.VIEW_EMPLOYEE_REWARD_HOME)
	public ModelAndView searchCountEmployeeRewardHome (HttpServletRequest request, EmployeeRewardVo vo) {
		vo.setStoreId(getStoreId(request));
		return employeeRewardService.findCountEmployeeRewardHome(vo);
	}
	
	/**
	 * 全查询员工奖惩汇总
	* @author DavidLiang
	* @date 2016年1月16日 下午8:43:15
	* @param request  请求
	* @param vo  查询条件
	* @return  奖惩汇总BaseDto
	 */
	@RequestMapping(value = Url.EmployeeReward.ACTION_LISTALL_ATTENDANCE, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto findAllAttendanceReward(HttpServletRequest request, EmployeeRewardVo vo) {
		vo.setStoreId(getStoreId(request));
		if (vo.getEmployeeName().trim().length() == 0) {
			vo.setEmployeeName(null);
		}
		return employeeRewardService.findALlListAction(vo);
	}
	
	/**
	 * 分页查询员工奖惩汇总
	 * @param request  请求
	 * @param vo  查询条件
	 * @param page  分页
	 * @return  奖惩汇总BaseDto
	 */
	@RequestMapping(value = Url.EmployeeReward.ACTION_LIST_ATTENDANCE, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto findAttendanceReward(HttpServletRequest request, EmployeeRewardVo vo, Page<EmployeeRewardDto> page) {
		vo.setStoreId(getStoreId(request));
		if (vo.getEmployeeName().trim().length() == 0) {
			vo.setEmployeeName(null);
		}
		return employeeRewardService.findListAction(page, vo);
	}
	
	/**
	 * 分页查询奖惩详细action
	 * @param page  页码页距查询条件
     * @param vo  其他查询条件
     * @return  带状态奖惩分页查询结果
	 */
	@RequestMapping(value = Url.EmployeeReward.ACTION_REWARD_DETAIL, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto findEmployeeRewardByPageAction(Page<EmployeeReward> page, EmployeeRewardVo vo) {
		return employeeRewardService.findEmployeeRewardByPageAction(page, vo);
	}
	
	/**
     * 添加奖惩
     * @param request  请求
     * @param vo  添加参数详情
     * @return  添加结果
     */
	@RequestMapping(value = Url.EmployeeReward.ACTION_ADD_REWARD, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto addEmployeeReward(HttpServletRequest request, EmployeeRewardVo vo) {
		int userId = getUserId(request);
		int storeId = getStoreId(request);
		return employeeRewardService.addEmployeeReward(userId, storeId, vo);
	}
	
	/**
	 * 删除奖惩记录
	* @author DavidLiang
	* @date 2016年1月14日 下午8:46:15
	* @param rewardId  奖惩id
	* @return  BaseDto
	 */
	@RequestMapping(value = Url.EmployeeReward.ACTION_DELETE_REWARD, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto deleteEmployeeReward(int rewardId) {
		if (employeeRewardService.deleteEmployeeReward(rewardId)) {
			return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "删除成功");
		}
		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "删除失败");
	}

}
