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
import com.zefun.common.consts.App.Session;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.EmployeeReward;
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
     * 添加奖惩
     * @param request  请求
     * @param vo  添加参数详情
     * @return  添加结果
     */
    @RequestMapping(value = Url.EmployeeReward.ACTION_ADD_REWARD, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto addEmployeeReward(HttpServletRequest request, EmployeeReward vo) {
        return employeeRewardService.addEmployeeReward(vo);
    }
    
    /**
     * 修改奖惩
     * @param request  请求
     * @param vo  添加参数详情
     * @return  添加结果
     */
    @RequestMapping(value = Url.EmployeeReward.ACTION_UPDATE_REWARD, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto updateEmployeeReward(HttpServletRequest request, EmployeeReward vo) {
        return employeeRewardService.updateEmployeeReward(vo);
    }
    
	/**
	 * 员工奖惩汇总主页查询
	 * @param request  请求
	 * @param vo  EmployeeRewardVo
	 * @return  ModelAndView
	 */
	@RequestMapping(value = Url.EmployeeReward.VIEW_EMPLOYEE_REWARD_HOME)
	public ModelAndView searchCountEmployeeRewardHome (HttpServletRequest request, EmployeeRewardVo vo) {
	    String storeAccount = getStoreAccount(request);
	    Object storeId = request.getSession().getAttribute(Session.STORE_ID);
		return employeeRewardService.findCountEmployeeRewardHome(vo, storeAccount, storeId);
	}
	/**
	 *  该门店下的员工
	* @author 骆峰
	* @date 2016年8月5日 下午6:41:12
	* @param storeId  storeId
	* @param type  type
	* @return BaseDto
	 */
    @RequestMapping(value = Url.EmployeeReward.ACTION_EMOLOYEE_REWARD, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto selectEmployeeByAccount(Integer storeId, Integer type) {
        return employeeRewardService.selectEmployeeByAccount(storeId, type);
    }
	
    

    /**
     *  奖罚明细分页查询
    * @author 骆峰
    * @date 2016年8月6日 下午1:54:32
    * @param pageNo 页数  
    * @param staTime 开始时间
    * @param endTime 结束时间
    * @param storeId 门店标识
    * @param ruleName 奖罚名称
    * @param ruleType 类型
    * @param employee 员工
    * @param pageSize 一页多少条数据
    * @param request request
    * @return BaseDto
    * 
     */
    @RequestMapping(value = Url.EmployeeReward.VIEW_EMPLOYEE_REWARD_HOME_PAGE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto selectEmployeeByAccount(HttpServletRequest request, Integer pageNo, String staTime, String endTime, 
            Integer storeId, String ruleName, Integer ruleType, String employee, Integer pageSize) {          
        Object storeid = request.getSession().getAttribute(Session.STORE_ID);
        return employeeRewardService.selectRuleByPage(pageNo, staTime, endTime, storeId, ruleName, ruleType, employee, pageSize, storeid);
    } 
	
    /**
     *  奖罚管理条件查询
    * @author 骆峰
    * @date 2016年8月6日 下午3:19:28
    * @param storeId storeId
    * @param time time
    * @return BaseDto
     */
    @RequestMapping(value = Url.EmployeeReward.VIEW_EMPLOYEE_REWARD_HOME_RULE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto changeRule(Integer storeId, String time){
        return employeeRewardService.changeRule(storeId, time);
        
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
