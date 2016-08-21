package com.zefun.web.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.exception.ServiceException;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.OrderInfoSubmitDto;
import com.zefun.web.dto.SelfCashierOrderDto;
import com.zefun.web.entity.StoreSetting;
import com.zefun.web.mapper.StoreSettingMapper;
import com.zefun.web.service.MemberInfoService;
import com.zefun.web.service.SelfCashierService;
import com.zefun.wechat.service.StaffOrderService;

/**
 * 自助收银相关信息
 * 
 * @author luhw
 * @date 2015年10月21日 下午3:11:56
 */
@Controller
public class SelfCashierController extends BaseController {

	/** 自助收银 */
	@Autowired
	private SelfCashierService selfCashierService;

	/** 会员信息服务对象 */
	@Autowired
	private MemberInfoService memberInfoService;

	/** */
	@Autowired
	private StaffOrderService staffOrderService;
	
	/** */
	@Autowired
	private StoreSettingMapper storeSettingMapper;
	/**
	 * 订单信息页面
	 * 
	 * @author 老王
	 * @date 2015年10月21日 下午3:12:13
	 * @param request
	 *            request
	 * @param response
	 *            response
	 * @return ModelAndView
	 * @throws ParseException
	 *             ParseException
	 */
	@RequestMapping(value = Url.SelfCashier.VIEW_HOME, method = RequestMethod.GET)
	public ModelAndView selfCashierView(HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		Integer storeId = getStoreId(request);
		Integer loginType = (Integer) request.getSession().getAttribute(App.Session.ONE_LOGIN_TIME);
		request.getSession().setAttribute(App.Session.ONE_LOGIN_TIME, 1);
		return selfCashierService.queryOrderInfoList(storeId, loginType);
	}

	/**
	 * 修改订单对应的会员信息
	 * 
	 * @param orderId
	 *            订单标识
	 * @param memberId
	 *            会员标识
	 * @param passwd
	 *            会员密码
	 * @param request
	 *            request
	 * @param response
	 *            response
	 * @return BaseDto
	 */
	@RequestMapping(value = Url.SelfCashier.ACTION_CHANGE_MEMBER, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto changeOrderMember(Integer orderId, Integer memberId, String passwd, HttpServletRequest request,
			    HttpServletResponse response) {
		Integer storeId = getStoreId(request);
		if (storeId == null || orderId == null || memberId == null || StringUtils.isBlank(passwd)) {
			return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "指定的会员不存在");
		}
		BaseDto baseDto = selfCashierService.changeOrderMemberId(storeId, orderId, memberId, passwd);
		return baseDto;
	}

	/**
	 * 查询用户信息
	 * 
	 * @param phone
	 *            会员手机号
	 * @param request
	 *            request
	 * @param response
	 *            response
	 * @return BaseDto
	 */
	@RequestMapping(value = Url.SelfCashier.ACTION_MEMBER_INFO, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto selectSelfCashierMemberByStore(String phone, HttpServletRequest request,
			    HttpServletResponse response) {
		BaseDto baseDto = new BaseDto();
		String storeAccount = getStoreAccount(request);

		Integer memberId = memberInfoService.selectMemberIdByPhone(phone, storeAccount);
		MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId, false);
		if (memberInfo == null) {
			baseDto.setCode(App.System.API_RESULT_CODE_FOR_FAIL);
		} 
		else {
			baseDto.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
			baseDto.setMsg(memberInfo);
		}
		return baseDto;
	}

	/**
	 * 订单详情
	 * 
	 * @author luhw
	 * @date 2015年10月21日 下午3:12:13
	 * @param orderId
	 *            订单标识
	 * @param request
	 *            request
	 * @param response
	 *            response
	 * @return ModelAndView
	 */
	@RequestMapping(value = Url.SelfCashier.ACTION_ORDER_INFO, method = RequestMethod.GET)
	public ModelAndView selfCashierOrderInfoAction(Integer orderId, HttpServletRequest request,
			    HttpServletResponse response) {
		Integer storeId = getStoreId(request);

		SelfCashierOrderDto selfCashierOrderDto = selfCashierService.queryOrderDetailAction(orderId, storeId);
		ModelAndView mav = new ModelAndView(View.SelfCashier.VIEW_CHECKOUT_ORDER);
		mav.addObject("selfCashierOrderDto", selfCashierOrderDto);
		Integer memberType = 0;
		if (selfCashierOrderDto.getMemberId() != null) {
			mav.addObject("allOffMapStr", JSONObject.toJSON(selfCashierOrderDto.getAllOffMap()).toString());
			if (selfCashierOrderDto.getDiscountMap() != null) {
				mav.addObject("discountMapStr", JSONObject.toJSON(selfCashierOrderDto.getDiscountMap()).toString());
			}
			memberType = 1;
		}
		StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(storeId);
		mav.addObject("updateMoneyAuthorize", storeSetting.getUpdateMoneyAuthorize());
		mav.addObject("isDecimalPoint", storeSetting.getIsDecimalPoint());
		mav.addObject("memberType", memberType);
		return mav;
	}

	/**
	 * 支付订单
	 * @author laowang
	 * @date Nov 11, 2015 8:23:17 PM
	 * @param orderSubmit 订单支付信息
	 * @param request 请求对象
	 * @param response 响应对象
	 * @return 成功返回码0；失败返回其他错误码，返回值为提示语
	 * @throws ServiceException
	 *             业务异常
	 */
	@RequestMapping(value = Url.SelfCashier.ACTION_SUBMIT_ORDER, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto cashierSubmit(@RequestBody OrderInfoSubmitDto orderSubmit, HttpServletRequest request,
			    HttpServletResponse response) throws ServiceException {
		Integer employeeId = getUserId(request);
		Integer storeId = getStoreId(request);
		return selfCashierService.cashierSubmit(employeeId, orderSubmit, null, storeId);
	}

	/**
	 * 取消订单
	 * 
	 * @author 王大爷
	 * @date 2016年1月8日 下午3:52:58
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 * @param orderId
	 *            订单标识
	 * @return BaseDto
	 */
	@RequestMapping(value = Url.SelfCashier.ACTION_DELETE_ORDERINFO)
	@ResponseBody
	public BaseDto deleteOrderInfo(HttpServletRequest request, HttpServletResponse response, Integer orderId) {
		Integer storeId = getStoreId(request);
		Integer lastOperatorId = getUserId(request);
		return staffOrderService.deleteOrderInfo(orderId, storeId, lastOperatorId);
	}
	
	/**
	 * 
	* @author 老王
	* @date 2016年5月16日 下午12:09:23 
	* @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	* @param mainOrderId 主id
	* @param removeOrderId 副id
	* @return BaseDto
	 */
	@RequestMapping(value = Url.SelfCashier.ACTION_MERGE_ORDER)
	@ResponseBody
	public BaseDto mergeOrder (HttpServletRequest request, HttpServletResponse response, Integer mainOrderId, Integer removeOrderId) {
		return selfCashierService.mergeOrder(mainOrderId, removeOrderId);
	}
	
	/**
	 * 修改员工提成
	* @author 老王
	* @date 2016年5月16日 下午12:09:23 
	* @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	* @param commissionSaveListStr 员工提成信息
	* @return BaseDto
	 */
	@RequestMapping(value = Url.SelfCashier.ACTION_SAVE_UPDATE_COMMISSION)
	@ResponseBody
	public BaseDto saveUpdateCommission (HttpServletRequest request, HttpServletResponse response, String commissionSaveListStr) {
		return selfCashierService.saveUpdateCommission(commissionSaveListStr);
	}
}
