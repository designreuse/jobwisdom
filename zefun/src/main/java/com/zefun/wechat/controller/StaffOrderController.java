package com.zefun.wechat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.web.controller.BaseController;
import com.zefun.web.dto.BaseDto;
import com.zefun.wechat.service.StaffOrderService;

/**
 * 移动端员工的订单服务控制类
* @author 张进军
* @date Oct 13, 2015 9:26:33 PM 
*/
@Controller
public class StaffOrderController extends BaseController{
    
    /** 移动端员工的订单服务操作对象 */
    @Autowired
    private StaffOrderService staffOrderService;
    
//    /**测试门店*/
//    private int storeId = 121;
//    /**测试员工*/
//    private int employeeId = 235;
    
    /**
     * 查询看手牌对应订单
    * @author 老王
    * @date Oct 14, 2015 9:19:41 AM
    * @param request    请求对象
    * @param response   响应对象
    * @param orderId 订单标识
    * @param pageType 是否存在操作订单标识
    * @return   全部订单页面
     */
    @RequestMapping(value = Url.Staff.VIEW_ALL_ORDER)
    public ModelAndView allOrderView(HttpServletRequest request, HttpServletResponse response, Integer orderId, Integer pageType){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer employeeId = getUserIdByOpenId(openId);
        return staffOrderService.allOrderView(orderId, employeeId, pageType);
    }
    
    /**
     * 查询看手牌对应订单
    * @author 老王
    * @date Oct 14, 2015 9:19:41 AM
    * @param request    请求对象
    * @param response   响应对象
    * @param orderId 订单标识
    * @return   全部订单页面
     */
    @RequestMapping(value = Url.Staff.ACTION_SELECT_NO_PAGE_ORDERID)
    @ResponseBody
    public BaseDto selectNoPageOrderId (HttpServletRequest request, HttpServletResponse response, Integer orderId) {
    	String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        return staffOrderService.selectNoPageOrderId(orderId);
    }
    
    /**
     * 查询我的所有订单
    * @author 王大爷
    * @date 2015年10月18日 下午2:18:55
    * @param storeId    门店标识
    * @param businessType   业务类型(1:会员,2:员工)
    * @param request 返回
    * @param response 请求
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Staff.VIEW_EMPLOYEE_ORDER)
    public ModelAndView employeeOrderView(@PathVariable String storeId, @PathVariable int businessType, 
            HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(storeId, businessType, request, response);
        if (openId == null) {
            return null;
        }
        Integer employeeId = getUserIdByOpenId(openId);
        return staffOrderService.employeeOrderView(employeeId);
    }
    
  /**
     * 查看订单明细
    * @author 王大爷
    * @date 2015年11月10日 上午9:58:59
    * @param storeId    门店标识
    * @param businessType   业务类型(1:会员,2:员工)
    * @param orderId 订单标识
    * @param request 返回
    * @param response 请求
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Staff.VIEW_SELECT_ORDER_DETAIL)
    public ModelAndView selectOrderDetail(@PathVariable String storeId, @PathVariable int businessType, Integer orderId, 
            HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(storeId, businessType, request, response);
        if (openId == null) {
            return null;
        }
        int ownerStoreId = getStoreIdByOpenId(openId);
        return staffOrderService.selectOrderDetail(orderId, ownerStoreId);
    }
    
    /**
     * 修改订单可操作
    * @author 老王
    * @date 2016年7月26日 下午3:10:47 
    * @param request 返回
    * @param response 请求
    * @param orderId 订单标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Staff.ACTION_UPDATE_IS_ORDER_OPTION)
    @ResponseBody
    public BaseDto updateIsOrderOption (HttpServletRequest request, HttpServletResponse response, Integer orderId) {
    	String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
    	return staffOrderService.updateIsOrderOption(orderId);
    }
    
    /**
     * 通知买单
    * @author 老王
    * @date 2016年8月5日 上午10:45:40 
    * @param request 返回
    * @param response 请求
    * @param orderId 订单标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Staff.ACTION_NOTICE_CHECKOUT)
    @ResponseBody
    public BaseDto noticeCheckout (HttpServletRequest request, HttpServletResponse response, Integer orderId) {
    	String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        return staffOrderService.noticeCheckout(orderId);
    }
    
    /**
     * 删除明细
    * @author 王大爷
    * @date 2015年11月11日 上午10:16:16
    * @param request 返回
    * @param response 请求
    * @param detailId 订单明细标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Staff.ACTION_DELETE_ORDER_DETAIL)
    @ResponseBody
    public BaseDto deleteOrderDetail(HttpServletRequest request, HttpServletResponse response, Integer detailId) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer storeId = getStoreIdByOpenId(openId);
        Integer lastOperatorId = getUserIdByOpenId(openId);
        return staffOrderService.deleteOrderDetail(detailId, storeId, lastOperatorId);
    }
    
    /**
     * 选择人员
    * @author 王大爷
    * @date 2015年10月20日 下午2:56:37
    * @param request 返回
    * @param response 请求
    * @param positionId 岗位标识
    * @param shiftMahjongStepId 轮牌步骤标识
    * @param type 设置类型
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Staff.VIEW_SERVER_ASSOCIATE)
    public ModelAndView serverAssociate(HttpServletRequest request, HttpServletResponse response, Integer positionId, 
    		  Integer shiftMahjongStepId, Integer type) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer storeId = getStoreIdByOpenId(openId);
        return staffOrderService.serverAssociate(storeId, positionId, shiftMahjongStepId, type);
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
    @RequestMapping(value = Url.Staff.ACTION_ADD_OR_UPDATE_SERVER_EMPLOYEE)
    @ResponseBody
    public BaseDto addOrUpdateServerEmployee(HttpServletRequest request, HttpServletResponse response, Integer shiftMahjongStepId, 
            Integer type, Integer employeeId, Integer shiftMahjongId, Integer isAssign) {
    	String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer storeId = getStoreIdByOpenId(openId);
        return staffOrderService.serverAssociateShiftMahjong(shiftMahjongStepId, type, shiftMahjongId, employeeId, 
                storeId, isAssign, getUserId(request));
    }
    
    /**
     * 挂起
    * @author 王大爷
    * @date 2015年12月10日 下午7:30:23
    * @param request 返回
    * @param response 请求
    * @param detailId 订单明细标识
    * @param shiftMahjongStepId 步骤标识
    * @param type 类型 （1：挂起、2：、取消挂起）
    * @return BaseDto
     */
    @RequestMapping(value = Url.Staff.ACTION_HANGUP_ORDER)
    @ResponseBody
    public BaseDto hangUpOrder(HttpServletRequest request, HttpServletResponse response, Integer detailId, Integer shiftMahjongStepId, Integer type) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer storeId = getStoreIdByOpenId(openId);
        Integer lastOperatorId = getUserIdByOpenId(openId);
        BaseDto dto = null;
        if (type == 1) {
            dto = staffOrderService.hangUpOrder(detailId, storeId, shiftMahjongStepId, lastOperatorId);
        }
        else {
            dto = staffOrderService.cancelHangUpOrder(detailId, storeId, shiftMahjongStepId, lastOperatorId);
        }
        return dto;
    }
    
    /**
     * 删除订单
    * @author 王大爷
    * @date 2015年11月11日 上午11:49:52
    * @param request 返回
    * @param response 请求
    * @param orderId 订单标识
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Staff.ACTION_DELETE_ORDERINFO)
    @ResponseBody
    public BaseDto deleteOrderInfo(HttpServletRequest request, HttpServletResponse response, Integer orderId) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer storeId = getStoreIdByOpenId(openId);
        Integer lastOperatorId = getUserIdByOpenId(openId);
        return staffOrderService.deleteOrderInfo(orderId, storeId, lastOperatorId);
    }
    
    /**
     * 确定完成订单
    * @author 王大爷
    * @date 2015年11月14日 下午4:45:37
    * @param request 返回
    * @param response 请求
    * @param shiftMahjongStepId 轮牌步骤标识
    * @param detailId 订单明细标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.Staff.ACTION_FINISH_ORDER_DETAIL)
    @ResponseBody
    public BaseDto finishOrderDetail(HttpServletRequest request, HttpServletResponse response, Integer shiftMahjongStepId, Integer detailId) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer storeId = getStoreIdByOpenId(openId);
        Integer lastOperatorId = getUserIdByOpenId(openId);
        return staffOrderService.finishOrderDetail(shiftMahjongStepId, detailId, storeId, lastOperatorId);
    }
    
    /**
     * 确认服务移交
    * @author 王大爷
    * @date 2015年10月21日 下午3:14:20
    * @param request 返回
    * @param response 请求
    * @param shiftMahjongStepId 轮牌步骤标识
    * @param type 交接类型
    * @param detailId 明细标识
    * @param shiftMahjongId 轮牌标识
    * @param shiftMahjongEmployeeId 轮牌员工标识
    * @param isType 类型
    * @return BaseDto
     */
    /*@RequestMapping(value = Url.Staff.ACTION_SERVER_ASSOCIATE_SHIFTMAHJONG)
    @ResponseBody
    public BaseDto serverAssociateShiftMahjong(HttpServletRequest request, HttpServletResponse response, Integer shiftMahjongStepId, 
            Integer type, Integer shiftMahjongEmployeeId, Integer detailId, Integer shiftMahjongId, Integer isType) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int storeId = getStoreIdByOpenId(openId);
        return staffOrderService.serverAssociateShiftMahjong(shiftMahjongStepId, type, detailId, shiftMahjongId, shiftMahjongEmployeeId, 
                storeId, isType, getUserId(request));
    }*/
    
    /**
     * 等待中心
    * @author 王大爷
    * @date 2015年10月24日 下午3:08:45
    * @param request 返回
    * @param response 请求
    * @return ModelAndView
     */
    /*@RequestMapping(value = Url.Staff.VIEW_WAITING_CENTRE)
    public ModelAndView waitingCentre(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer storeId = getStoreIdByOpenId(openId);
        return staffOrderService.waitingCentre(storeId);
    }*/
    
    /**
     * 等待中心轮牌界面
    * @author 王大爷
    * @date 2015年10月24日 下午8:28:16
    * @param request 返回
    * @param response 请求
    * @param detailId 明细标识
    * @param shiftMahjongStepId 步骤标识
    * @return ModelAndView
     */
    /*@RequestMapping(value = Url.Staff.VIEW_WAITING_CENTRE_SHIFT_MAHJONG)
    public ModelAndView waitingCentreShiftMahjong(HttpServletRequest request, HttpServletResponse response, Integer detailId, 
            Integer shiftMahjongStepId) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        return staffOrderService.waitingCentreShiftMahjong(detailId, shiftMahjongStepId);
    }*/
    
    /**
     * 确定等待轮牌更换的员工
    * @author 王大爷
    * @date 2015年10月24日 下午8:51:44
    * @param request 返回
    * @param response 请求
    * @param detailId 明细标识
    * @param shiftMahjongStepId 步骤标识
    * @param shiftMahjongEmployeeId 指定员工标识
    * @param shiftMahjongEmployeeIdOld 被更换员工轮牌标识
    * @param isType isType
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Staff.ACTION_WAITING_ASSOCIATE_SHIFTMAHJONG)
    @ResponseBody
    public BaseDto waitingAssociateShiftMahjong(HttpServletRequest request, HttpServletResponse response, Integer detailId, 
            Integer shiftMahjongStepId, Integer shiftMahjongEmployeeId, Integer shiftMahjongEmployeeIdOld, Integer isType) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int storeId = getStoreIdByOpenId(openId);
        return staffOrderService.waitingAssociateShiftMahjong(detailId, shiftMahjongStepId, shiftMahjongEmployeeId, 
                storeId, shiftMahjongEmployeeIdOld, isType, getUserIdByOpenId(openId));
    }
    
    
    /**
     * 通知会员买单
    * @author 张进军
    * @date Nov 5, 2015 9:07:05 PM
    * @param request    请求对象
    * @param response   响应对象
    * @param orderId    订单标识
    * @return   成功返回码为0，失败为其它返回码
     */
    @RequestMapping(value = Url.Staff.ACTION_ORDER_NOTITY, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto notifyPaymentAction(int orderId, HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int storeId = getStoreId(request);
        int employeeId = getUserIdByOpenId(openId);
        return staffOrderService.notifyPaymentAction(storeId, orderId, employeeId);
    }
    
    /**
     * 完成服务步骤
    * @author 老王
    * @date 2016年7月11日 下午3:05:20 
    * @param shiftMahjongStepId 服务步骤
    * @param request    请求对象
    * @param response   响应对象
    * @return BaseDto
     */
    @RequestMapping(value = Url.Staff.ACTION_OVER_SERVER_EMPLOYEE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto overServerEmployee (Integer shiftMahjongStepId, HttpServletRequest request, HttpServletResponse response) {
    	return staffOrderService.overServerEmployee(shiftMahjongStepId);
    }
    
}
