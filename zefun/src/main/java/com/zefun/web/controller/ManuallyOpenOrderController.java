package com.zefun.web.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.service.ManuallyOpenOrderService;

/**
 * 手工开单
 * @author 王大爷
 * @date Jun 30, 2015 4:42:19 PM 
 */
@Controller
public class ManuallyOpenOrderController extends BaseController{
	
	/**
	 * 轮牌信息Service
	 */
	@Autowired
	private ManuallyOpenOrderService manuallyOpenOrderService;
	
	/**
	 * 手工开单页面
	* @author 王大爷
	* @date 2015年8月11日 上午11:10:16
	* @param request 返回
	* @param response 请求
	* @return ModelAndView
	 */
	@RequestMapping(value = Url.KeepAccounts.INITIALIZE_MANUALLY_OPEN_ORDER, method = RequestMethod.GET)
	public ModelAndView initializeManuallyOpenOrder(HttpServletRequest request, HttpServletResponse response){
	    Integer storeId = getStoreId(request);
		return manuallyOpenOrderService.initializeManuallyOpenOrder(storeId);
	}
	
	/**
	 * 无纸开单页面
	* @author 王大爷
	* @date 2015年8月11日 上午11:10:16
	* @param request 返回
	* @param response 请求
	* @return ModelAndView
	 */
	@RequestMapping(value = Url.KeepAccounts.INITIALIZE_NO_PAPER_OPEN_ORDER, method = RequestMethod.GET)
	public ModelAndView initializeNoPaperOpenOrder(HttpServletRequest request, HttpServletResponse response){
	    Integer storeId = getStoreId(request);
		return manuallyOpenOrderService.initializeNoPaperOpenOrder(storeId);
	}
	
	/**
	 *  无纸开单
	* @author 老王
	* @date 2016年7月5日 下午3:49:55 
	* @param request 请求
	* @param response 返回
	* @param sex 性别
	* @param handOrderCode 手牌号
	* @param employeeObj 选择的轮牌员工
	* @param memberId 会员标识
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.ACTION_ADD_ORDER)
    @ResponseBody
    public BaseDto addOrder(HttpServletRequest request, HttpServletResponse response, String sex, String handOrderCode, String employeeObj,
             Integer memberId) {
        Integer lastOperatorId = getUserId(request);
        Integer storeId = getStoreId(request);
        return manuallyOpenOrderService.addOrder(sex, handOrderCode, employeeObj, memberId, storeId, lastOperatorId);
    }
	
	/**
	 * 根据项目标识查询想轮牌信息及步骤对应员工
	* @author 王大爷
	* @date 2015年11月24日 下午12:08:34
	* @param request 返回
    * @param response 请求
	* @param projectId 项目标识
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.SELECT_PROJECT_SHIFT_STEP)
	@ResponseBody
	public BaseDto selectProjectShiftStep(HttpServletRequest request, HttpServletResponse response, Integer projectId){
	    return manuallyOpenOrderService.selectProjectShiftStep(projectId);
	}
	
	/**
	 * 手动开单
	* @author 王大爷
	* @date 2015年11月25日 上午10:25:53
	* @param request 返回
    * @param response 请求
	* @param memberId 会员标识
	* @param sex 散客性别
	* @param arrayObjStr 开单项目
	* @param openOrderDate 补单时间
	* @param handOrderCode 手工单号
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.MANUALLY_OPEN_ORDER_SAVE)
    @ResponseBody
	public BaseDto manuallyOpenOrderSave(HttpServletRequest request, HttpServletResponse response, Integer memberId, String sex,
	        String arrayObjStr, String openOrderDate, String handOrderCode) {
		
	    Integer storeId = getStoreId(request);
	    Integer lastOperatorId = getUserId(request);
	    return manuallyOpenOrderService.manuallyOpenOrderSave(memberId, sex, arrayObjStr, openOrderDate, storeId, lastOperatorId, handOrderCode);
	}
	
	
}
