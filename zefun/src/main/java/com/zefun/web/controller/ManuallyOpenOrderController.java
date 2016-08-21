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
import com.zefun.wechat.service.StaffOrderService;
import com.zefun.wechat.service.StaffService;

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
	 * 
	 */
	@Autowired
	private StaffOrderService staffOrderService;
	/**
	 * 
	 */
	@Autowired
	private StaffService staffService;
	
	/**
	 * 手工开单页面
	* @author 王大爷
	* @date 2015年8月11日 上午11:10:16
	* @param orderId 订单标识
	* @param request 返回
	* @param response 请求
	* @return ModelAndView
	 */
	@RequestMapping(value = Url.KeepAccounts.INITIALIZE_MANUALLY_OPEN_ORDER, method = RequestMethod.GET)
	public ModelAndView initializeManuallyOpenOrder(Integer orderId, HttpServletRequest request, HttpServletResponse response){
	    Integer storeId = getStoreId(request);
		return manuallyOpenOrderService.initializeManuallyOpenOrder(storeId, orderId);
	}
	
	/**
	 * 快速开单预约信息
	* @author 老王
	* @date 2016年8月10日 下午8:08:39 
	* @param request 返回
	* @param response 请求
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.MANUALLY_OPEN_ORDER_APPOINT)
    @ResponseBody
	public BaseDto manuallyOpenOrderAppoint (HttpServletRequest request, HttpServletResponse response) {
		Integer storeId = getStoreId(request);
		return manuallyOpenOrderService.manuallyOpenOrderAppoint(storeId);
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
	 * 初始化弹出框
	* @author 老王
	* @date 2016年7月6日 下午5:21:14 
	* @param request 返回
	* @param response 请求
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.INITIALIZE_NOPAPER_MODEL)
    @ResponseBody
	public BaseDto initializeNoPaperModel (HttpServletRequest request, HttpServletResponse response) {
		Integer storeId = getStoreId(request);
		return manuallyOpenOrderService.initializeNoPaperModel(storeId);
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
	* @param appointmentId 预约单标识
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.ACTION_ADD_ORDER)
    @ResponseBody
    public BaseDto addOrder(HttpServletRequest request, HttpServletResponse response, String sex, String handOrderCode, String employeeObj,
             Integer memberId, Integer appointmentId) {
        Integer lastOperatorId = getUserId(request);
        Integer storeId = getStoreId(request);
        return manuallyOpenOrderService.addOrder(sex, handOrderCode, employeeObj, memberId, storeId, appointmentId, lastOperatorId);
    }
	
	/**
     * 添加或修改服务人员
    * @author 王大爷
    * @date 2015年10月21日 下午3:14:20
    * @param request 返回
    * @param response 请求
    * @param shiftMahjongStepId 轮牌步骤标识
    * @param type 交接类型
    * @param shiftMahjongId 轮牌标识
    * @param employeeId 员工标识
    * @param isAssign 是否指定
    * @return BaseDto
     */
    @RequestMapping(value = Url.KeepAccounts.ACTION_ADD_OR_UPDATE_SERVER_EMPLOYEE)
    @ResponseBody
    public BaseDto addOrUpdateServerEmployee(HttpServletRequest request, HttpServletResponse response, Integer shiftMahjongStepId, 
            Integer type, Integer employeeId, Integer shiftMahjongId, Integer isAssign) {
        int storeId = getStoreId(request);
        return staffOrderService.serverAssociateShiftMahjong(shiftMahjongStepId, type, shiftMahjongId, employeeId, 
                storeId, isAssign, getUserId(request));
    }
	
    /**
     * 结束服务
    * @author 老王
    * @date 2016年7月6日 下午2:35:01 
    * @param request 返回
    * @param response 请求
    * @param shiftMahjongStepId 轮牌步骤标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.KeepAccounts.ACTION_OVER_SERVER_EMPLOYEE)
    @ResponseBody
    public BaseDto overServerEmployee(HttpServletRequest request, HttpServletResponse response, Integer shiftMahjongStepId) {
        return staffOrderService.overServerEmployee(shiftMahjongStepId);
    }
    
    /**
     * 无纸单结算
    * @author 老王
    * @date 2016年7月6日 下午8:21:41 
    * @param request 返回
    * @param response 请求
    * @param orderId 订单标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.KeepAccounts.ACTION_SETTLEMENT_ORDER)
    @ResponseBody
    public BaseDto settlementOrder (HttpServletRequest request, HttpServletResponse response, Integer orderId) {
    	return manuallyOpenOrderService.settlementOrder(orderId);
    }
    
    /**
     * 删除明细
    * @author 老王
    * @date 2016年7月7日 下午3:30:25 
    * @param request 返回
    * @param response 请求
    * @param detailId 明细标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.KeepAccounts.ACTION_DELETE_ORDER_DETAIL)
    @ResponseBody
    public BaseDto deleteOrderDetail(HttpServletRequest request, HttpServletResponse response, Integer detailId) {
    	Integer storeId = getStoreId(request);
    	Integer lastOperatorId = getUserId(request);
    	return staffOrderService.deleteOrderDetail(detailId, storeId, lastOperatorId);
    }
    
    /**
     * 删除订单
    * @author 老王
    * @date 2016年7月7日 下午3:44:32 
    * @param request 返回
    * @param response 请求
    * @param orderId 订单标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.KeepAccounts.ACTION_DELETE_ORDER_INFO)
    @ResponseBody
    public BaseDto deleteOrderInfo(HttpServletRequest request, HttpServletResponse response, Integer orderId) {
    	Integer storeId = getStoreId(request);
    	Integer lastOperatorId = getUserId(request);
    	return staffOrderService.deleteOrderInfo(orderId, storeId, lastOperatorId);
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
	* @param orderId 订单标识
	* @param deleteDetailIdStr 删除的明细标识
	* @return BaseDto
	 */
	@RequestMapping(value = Url.KeepAccounts.MANUALLY_OPEN_ORDER_SAVE)
    @ResponseBody
	public BaseDto manuallyOpenOrderSave(HttpServletRequest request, HttpServletResponse response, Integer memberId, String sex,
	        String arrayObjStr, String openOrderDate, String handOrderCode, Integer orderId, String deleteDetailIdStr) {
		
	    Integer storeId = getStoreId(request);
	    Integer lastOperatorId = getUserId(request);
	    return manuallyOpenOrderService.manuallyOpenOrderSave(memberId, sex, arrayObjStr, openOrderDate, 
	    		storeId, lastOperatorId, handOrderCode, orderId, deleteDetailIdStr);
	}
	
	/**
     * 设置项目
    * @author 张进军
    * @date Jan 17, 2016 1:12:54 PM
    * @param detailId   会员标识
    * @param projectId 项目标识
    * @param request    请求对象
    * @param response   响应对象
    * @return   会员信息
     */
    @RequestMapping(value = Url.KeepAccounts.ACTION_SETTING_PROJECT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto settingProject(Integer detailId, Integer projectId, HttpServletRequest request, 
            HttpServletResponse response) {
        
        return staffService.settingProject(detailId, projectId);
    }
	
    /**
     * 添加服务
    * @author 老王
    * @date 2016年7月11日 下午3:53:31 
    * @param orderId 订单标识
    * @param request        请求对象
    * @param response       返回对象
    * @return BaseDto
     */
    @RequestMapping(value = Url.KeepAccounts.ACTION_ADD_DETAIL_SERVER, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto addDetailServer (Integer orderId, HttpServletRequest request, HttpServletResponse response) {
    	Integer storeId = getStoreId(request);
	    Integer lastOperatorId = getUserId(request);
        return staffService.addDetailServer(orderId, storeId, lastOperatorId);
    }
}
