package com.zefun.web.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.exception.ServiceException;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.GoodsInfoDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.MemberLevelDto;
import com.zefun.web.dto.MemberSubAccountDto;
import com.zefun.web.dto.OrderDetaiSubmitDto;
import com.zefun.web.dto.OrderDetailStepDto;
import com.zefun.web.dto.OrderInfoSubmitDto;
import com.zefun.web.dto.PaymentOffDto;
import com.zefun.web.dto.SelfCashierDetailDto;
import com.zefun.web.dto.SelfCashierOrderDto;
import com.zefun.web.dto.SelfCashierStatDto;
import com.zefun.web.entity.ComboInfo;
import com.zefun.web.entity.CommissionScheme;
import com.zefun.web.entity.CouponInfo;
import com.zefun.web.entity.EmployeeCommission;
import com.zefun.web.entity.GiftmoneyDetail;
import com.zefun.web.entity.GiftmoneyFlow;
import com.zefun.web.entity.GoodsDiscount;
import com.zefun.web.entity.IntegralFlow;
import com.zefun.web.entity.MemberAccount;
import com.zefun.web.entity.MemberLevelDiscount;
import com.zefun.web.entity.MemberSubAccount;
import com.zefun.web.entity.MoneyFlow;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.OrderInfo;
import com.zefun.web.entity.ProjectCommission;
import com.zefun.web.entity.ProjectDiscount;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.entity.ProjectStep;
import com.zefun.web.entity.StockFlow;
import com.zefun.web.entity.StoreSetting;
import com.zefun.web.mapper.ComboGoodsMapper;
import com.zefun.web.mapper.ComboInfoMapper;
import com.zefun.web.mapper.CommissionSchemeMapper;
import com.zefun.web.mapper.CouponInfoMapper;
import com.zefun.web.mapper.EmployeeCommissionMapper;
import com.zefun.web.mapper.EmployeeLevelMapper;
import com.zefun.web.mapper.EmployeeObjectiveMapper;
import com.zefun.web.mapper.GiftmoneyDetailMapper;
import com.zefun.web.mapper.GiftmoneyFlowMapper;
import com.zefun.web.mapper.GoodsDiscountMapper;
import com.zefun.web.mapper.GoodsInfoMapper;
import com.zefun.web.mapper.IntegralFlowMapper;
import com.zefun.web.mapper.MemberAccountMapper;
import com.zefun.web.mapper.MemberComboProjectMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberLevelDiscountMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.MemberRecommendMapper;
import com.zefun.web.mapper.MemberSubAccountMapper;
import com.zefun.web.mapper.MoneyFlowMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.OrderInfoMapper;
import com.zefun.web.mapper.ProjectCommissionMapper;
import com.zefun.web.mapper.ProjectDiscountMapper;
import com.zefun.web.mapper.ProjectStepMapper;
import com.zefun.web.mapper.ShiftMahjongProjectStepMapper;
import com.zefun.web.mapper.StoreSettingMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author laowang
 * @date Oct 22, 2015 10:27:15 AM
 */
@Service
public class SelfCashierService {

	/** 订单信息操作对象 */
	@Autowired
	private OrderInfoMapper orderInfoMapper;

	/** 订单详情操作对象 */
	@Autowired
	private OrderDetailMapper orderDetailMapper;

	/** 会员疗程项目明细操作对象 */
	@Autowired
	private MemberComboProjectMapper memberComboProjectMapper;

	/** 会员信息操作对象 */
	@Autowired
	private MemberInfoMapper memberInfoMapper;

	/** 会员账户信息操作对象 */
	@Autowired
	private MemberAccountMapper memberAccountMapper;

	/** 会员等级操作对象 */
	@Autowired
	private MemberLevelMapper memberLevelMapper;

	/** 积分流水操作对象 */
	@Autowired
	private IntegralFlowMapper integralFlowMapper;

	/** 资金流水操作对象 */
	@Autowired
	private MoneyFlowMapper moneyFlowMapper;

	/** 礼金账户流水操作对象 */
	@Autowired
	private GiftmoneyFlowMapper giftmoneyFlowMapper;

	/** 优惠券信息操作对象 */
	@Autowired
	private CouponInfoMapper couponInfoMapper;

	/** redis操作对象 */
	@Autowired
	private RedisService redisService;

	/** 队列操作对象 */
	@Autowired
	private RabbitService rabbitService;

	/** 会员基础信息服务对象 */
	@Autowired
	private MemberInfoService memberInfoService;

	/** 开卡充值服务类 */
	@Autowired
	private OpenCardService openCardService;

	/** 礼金明细 */
	@Autowired
	private GiftmoneyDetailMapper giftmoneyDetailMapper;

	/** 会员推荐操作对象 */
	@Autowired
	private MemberRecommendMapper memberRecommendMapper;

	/** 门店设置信息操作对象 */
	@Autowired
	private StoreSettingMapper storeSettingMapper;

	/** 疗程信息 */
	@Autowired
	private ComboInfoMapper comboInfoMapper;

	/** 会员子账户操作对象 */
	@Autowired
	private MemberSubAccountMapper memberSubAccountMapper;

	/** 项目信息服务对象 */
	@Autowired
	private ProjectService projectService;

	/** 商品信息服务对象 */
	@Autowired
	private GoodsInfoService goodsInfoService;
	
	/** 商品*/
	@Autowired
	private GoodsInfoMapper goodsInfoMapper;
	
	/** 项目折扣*/
	@Autowired
	private ProjectDiscountMapper projectDiscountMapper;
	/** 商品折扣*/
	@Autowired
	private GoodsDiscountMapper goodsDiscountMapper;
	/** 员工目标*/
	@Autowired
	private EmployeeObjectiveMapper employeeObjectiveMapper;
	/** 轮牌步骤*/
	@Autowired
	private ShiftMahjongProjectStepMapper shiftMahjongProjectStepMapper;
	/** 项目步骤*/
	@Autowired 
	private ProjectStepMapper projectStepMapper;
	/** 会员卡账户*/
	@Autowired
	private MemberLevelDiscountMapper memberLevelDiscountMapper;
	/** 项目提成表*/
	@Autowired
	private ProjectCommissionMapper projectCommissionMapper;
	/** 职位*/
	@Autowired
	private EmployeeLevelMapper employeeLevelMapper;
	/** 业绩提成分配方案*/
	@Autowired 
	private CommissionSchemeMapper commissionSchemeMapper;
	/** 员工提成表*/
	@Autowired
	private EmployeeCommissionMapper employeeCommissionMapper;
	/** 商品库存Service*/
	@Autowired
	private GoodsStockService goodsStockService;
	/** 商品库存Service*/
	@Autowired
	private ComboGoodsMapper comboGoodsMapper;

	/** 日志操作对象 */
	// private Logger logger = Logger.getLogger(SelfCashierService.class);

	/**
	 * 自助收银页面
	 * 
	 * @author luhw
	 * @date 2015年10月21日 下午15:25:03
	 * @param storeId
	 *            门店标识
	 * @param loginType 登录类型
	 * @return cashierDto
	 * @throws ParseException
	 *             ParseException
	 */
	public ModelAndView queryOrderInfoList(Integer storeId, Integer loginType) throws ParseException {
		// 查询当日订单金额
		Map<String, Object> params = new HashMap<String, Object>();
		String currDate = DateUtil.getCurDate();
		params.put("storeId", storeId);
		params.put("starTime", currDate);
		params.put("endTime", DateUtil.getDateAfterDaysStr(currDate, 1));
		SelfCashierStatDto statDto = orderInfoMapper.selectCashierStatInfo(params);

		Map<String, Integer> map = new HashMap<>();
		map.put("storeId", storeId);
		map.put("type", 2);
		
		// 查询未结账订单
		List<SelfCashierOrderDto> cashierDtoList = orderInfoMapper.selectUnfinishedOrderInfo(map);
		for (SelfCashierOrderDto dayBookDto : cashierDtoList) {
			dayBookDto.setCreateTime(dayBookDto.getCreateTime().substring(5));
			dayBookDto.setOrderCode(dayBookDto.getOrderCode().substring(4));
		}

		ModelAndView mav = new ModelAndView(View.SelfCashier.VIEW_SELF_CASHIER);
		mav.addObject("statDto", statDto);
		mav.addObject("cashierDtoList", cashierDtoList);
		mav.addObject("loginType", loginType);
		return mav;
	}
	
	/**
	 * 查询订单详情
	 * 
	 * @param orderId
	 *            订单标识
	 * @param storeId 门店标识
	 * @return SelfCashierResultDto
	 */
	public SelfCashierOrderDto queryOrderDetailAction(Integer orderId, Integer storeId) {
		SelfCashierOrderDto cashierDto = selectSelfCashierOrderDetail(orderId, true, storeId);
		if (cashierDto == null) {
			return cashierDto;
		}
		return cashierDto;
	}

	/**
	 * 
	 * @author 老王
	 * @date Nov 11, 2015 8:27:23 PM
	 * @param employeeId
	 *            操作人员标识
	 * @param orderSubmit
	 *            订单支付信息
	 * @param memberId
	 *            结账会员标识(会员自助结账时)
	 * @param storeId 门店标识
	 * @param storeAccount 企业代号
	 * @return 成功返回码0；失败返回其他错误码，返回值为提示语
	 * @throws ServiceException ServiceException
	 */
	@Transactional
	public BaseDto cashierSubmit(int employeeId, OrderInfoSubmitDto orderSubmit, Integer memberId, Integer storeId, String storeAccount) {
		int orderId = orderSubmit.getOrderId();
		SelfCashierOrderDto cashierDto = selectSelfCashierOrderDetail(orderId, true, storeId);
		List<SelfCashierDetailDto> ownerDetailList = cashierDto.getOrderDetails();
		List<OrderDetaiSubmitDto> submitDetailList = orderSubmit.getDetailList();

		// 检查是否为待结账状态
		if (!(cashierDto.getOrderStatus() == 2 || cashierDto.getOrderStatus() == 5)) {
			return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "此订单已经结过帐啦");
		}

		// 检查提交订单是否与数据库中订单一致
		if (ownerDetailList.size() != submitDetailList.size()) {
			return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "订单信息错误，请刷新重试！");
		}

		Map<String, SelfCashierDetailDto> detailMap = new HashMap<String, SelfCashierDetailDto>(ownerDetailList.size());
		List<Integer> detailIdList = new ArrayList<Integer>(ownerDetailList.size());
		for (SelfCashierDetailDto detail : ownerDetailList) {
			detailIdList.add(detail.getDetailId());
			detailMap.put(String.valueOf(detail.getDetailId()), detail);
		}
		for (OrderDetaiSubmitDto detail : orderSubmit.getDetailList()) {
			if (!detailIdList.contains(detail.getDetailId())) {
				return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "订单信息错误，请刷新重试！");
			}
		}

		String curTime = DateUtil.getCurTime();
		Integer ownerMemberId = cashierDto.getMemberId();
		
		if (memberId != null && !ownerMemberId.equals(memberId)) {
			return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "您不能支付其他会员的订单！");
		}

		BigDecimal giftAmount = new BigDecimal(0);
		BigDecimal comboAmount = new BigDecimal(0);
		BigDecimal couponAmount = new BigDecimal(0);
		BigDecimal appointOff = new BigDecimal(0);
		BigDecimal realAmount = new BigDecimal(0);
		BigDecimal discountAmount = new BigDecimal(0);
		
		//查询四舍五入或保留两位小数
		StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(storeId);
		Integer toFixedNum = 2;
		if (storeSetting.getIsDecimalPoint() == 1) {
			toFixedNum = 0;
		}
		
		MemberSubAccount memberSubAccount = memberSubAccountMapper.selectByPrimaryKey(orderSubmit.getSubAccountId());
		
		//结账方式
  		Integer payType = 0;
  		
  		//订单支付会员等级
  		Integer levelId = null;
  		
  		if (orderSubmit.getCardAmount().compareTo(new BigDecimal(0)) == 1) {
  		    payType = 1;
  		}
  		List<Map<String, Object>> stepCommissionList = new ArrayList<>();
  		
  		String updatePricArrayStr = orderSubmit.getUpdatePricArray();
  		//获取改价对象
  		JSONArray updatePricArray =JSONArray.fromObject(updatePricArrayStr);
  		
  		String aIdStr = null;
  		String goodsNumStr = null;
  		
		// 依次遍历所有支付的明细信息
		for (OrderDetaiSubmitDto detail : orderSubmit.getDetailList()) {
			SelfCashierDetailDto ownerDetail = detailMap.get(String.valueOf(detail.getDetailId()));

			Map<String, Object> stepCommissionMap = new HashMap<>();
			
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrderType(ownerDetail.getOrderType());
			orderDetail.setProjectId(ownerDetail.getProjectId());
			orderDetail.setDetailId(detail.getDetailId());
			orderDetail.setGiftAmount(BigDecimal.ZERO);
			orderDetail.setRealPrice(ownerDetail.getDiscountAmount().setScale(toFixedNum, BigDecimal.ROUND_HALF_UP));
			orderDetail.setUpdateTime(curTime);
			if (ownerMemberId != null) {
				// 一人多卡，从新计算折扣价格
				BigDecimal tempAmount = null;
				
				levelId = memberSubAccount.getLevelId();
				
				if (ownerDetail.getOrderType() == 1) {
					tempAmount = projectService.getProjectPriceByMember(memberSubAccount.getLevelId(),
							ownerDetail.getProjectId(), ownerDetail.getProjectPrice(), storeId);
				} 
				else if (ownerDetail.getOrderType() == 2) {
					tempAmount = goodsInfoService.getGoodsPriceByMember(memberSubAccount.getLevelId(),
							ownerDetail.getProjectId(), ownerDetail.getProjectPrice(), storeId);
				} 
				else {
					tempAmount = ownerDetail.getDiscountAmount();
				}
				tempAmount = tempAmount.setScale(toFixedNum, BigDecimal.ROUND_HALF_UP);
				ownerDetail.setDiscountAmount(tempAmount);
				orderDetail.setDiscountAmount(tempAmount);
				orderDetail.setRealPrice(tempAmount);
				orderDetail.setLevelId(levelId);
				discountAmount = discountAmount.add(tempAmount).setScale(toFixedNum, BigDecimal.ROUND_HALF_UP);
				// 优惠类型为疗程
				if (detail.getOffType() == 1 || detail.getOffType() == 2) {
					// 减去疗程次数
					updateMemberComboInfo(ownerMemberId, detail.getOffId(), curTime);
					Integer comboId = memberComboProjectMapper.selectComboIdByDetailId(detail.getOffId());
					if (comboId == null) {
						throw new ServiceException(-1, "对不起，您不存在此疗程！");
					}
					comboAmount = comboAmount.add(ownerDetail.getProjectPrice()).setScale(toFixedNum, BigDecimal.ROUND_HALF_UP);
					orderDetail.setGiftAmount(ownerDetail.getProjectPrice().setScale(toFixedNum, BigDecimal.ROUND_HALF_UP));
					orderDetail.setRealPrice(BigDecimal.ZERO);
					orderDetail.setComboId(detail.getOffId());
					orderDetail.setOffType(1);
				}
				// 优惠类型为礼金
				else if (detail.getOffType() == 4) {
					// 更新礼金账户
					updateMemberGiftMoney(ownerMemberId, detail.getOffAmount());
					// 增加礼金流水
					insertGiftMoneyFlow(ownerMemberId, ownerDetail.getDetailId(), detail.getOffAmount(), curTime,
							  ownerDetail.getProjectName());

					giftAmount = giftAmount.add(detail.getOffAmount());
					orderDetail.setGiftAmount(detail.getOffAmount());

					// 检查是否有礼金抵扣
					orderDetail.setRealPrice(ownerDetail.getDiscountAmount().subtract(detail.getOffAmount())
								 .setScale(toFixedNum, BigDecimal.ROUND_HALF_UP));

					orderDetail.setOffType(3);
				}
				// 优惠类型为优惠券
				else if (detail.getOffType() == 3) {
					// 将优惠券标识对应的最快过期时间的会员优惠券设为已使用
					/*Map<String, Integer> map = new HashMap<String, Integer>(2);
					map.put("memberId", ownerMemberId);
					map.put("couponId", detail.getOffId());
					int relevanceId = memberCouponMapper.selectLeftCouponByMemberIdAndCouponId(map);
					updateMemberCouponInfo(relevanceId);

					couponAmount = couponAmount.add(detail.getOffAmount());
					orderDetail.setGiftAmount(detail.getOffAmount());
					orderDetail.setRealPrice(ownerDetail.getDiscountAmount().subtract(detail.getOffAmount()));
					orderDetail.setCouponId(relevanceId);
					orderDetail.setOffType(2);
					discountAmount = discountAmount.add(ownerDetail.getDiscountAmount());*/
				} 
				appointOff = appointOff.add(ownerDetail.getAppointOff());
				BigDecimal rm = orderDetail.getRealPrice().subtract(ownerDetail.getAppointOff()).setScale(toFixedNum, BigDecimal.ROUND_HALF_UP);
				if (rm.compareTo(BigDecimal.ZERO) == -1) {
					rm = BigDecimal.ZERO;
				}
				orderDetail.setRealPrice(rm);
			} 
			else {
				discountAmount = discountAmount.add(ownerDetail.getDiscountAmount()).setScale(toFixedNum, BigDecimal.ROUND_HALF_UP);
			}

			for (int j = 0; j < updatePricArray.size(); j++) {
				JSONObject updatePricObj = updatePricArray.getJSONObject(j);
				Integer detailId = updatePricObj.getInt("detailId");
				if (detailId.intValue() == orderDetail.getDetailId().intValue()) {
					String freeAmount = updatePricObj.getString("freeAmount");
					String orderRemark = updatePricObj.getString("orderRemark");
					orderDetail.setFreeAmount(freeAmount);
					orderDetail.setOrderRemark(orderRemark);
					orderDetail.setFreeEmployeeId(orderSubmit.getAuthorityEmployeeId());
					
					orderDetail.setRealPrice(orderDetail.getRealPrice().add(new BigDecimal(freeAmount))
							   .setScale(toFixedNum, BigDecimal.ROUND_HALF_UP));
				}
				
			}
			
			realAmount = realAmount.add(orderDetail.getRealPrice());
			orderDetailMapper.updateByPrimaryKey(orderDetail);
			
			OrderDetail orderDetailObj = orderDetailMapper.selectByPrimaryKey(orderDetail.getDetailId());
			
			stepCommissionMap = calculateCommonCommission(orderDetailObj, memberSubAccount, storeId, payType, ownerMemberId, employeeId, toFixedNum);
			stepCommissionList.add(stepCommissionMap);
			
			if (orderDetailObj.getOrderType() != 1) {
				if (stepCommissionMap.get("aId") != null) {
					if (aIdStr == null) {
						aIdStr = stepCommissionMap.get("aId").toString();
						goodsNumStr = stepCommissionMap.get("goodsNum").toString();
					}
					else {
						aIdStr = aIdStr + "," + stepCommissionMap.get("aId").toString();
						goodsNumStr = goodsNumStr + "," + stepCommissionMap.get("goodsNum").toString();
					}
				}
			}
			
			//修改业绩值
			OrderDetail obj = new OrderDetail();
			obj.setDetailId(orderDetailObj.getDetailId());
			obj.setDetailCalculate(new Double(stepCommissionMap.get("tataliCommonCalculate").toString()));
			obj.setIsAssign(Integer.valueOf(stepCommissionMap.get("isAssign").toString()));
			orderDetailMapper.updateByPrimaryKey(obj);
		}

		if (aIdStr != null) {
			StockFlow stockFlow = new StockFlow();
			stockFlow.setaIds(aIdStr);
			stockFlow.setStockCount(goodsNumStr);
			stockFlow.setFromStore(storeId);
			stockFlow.setFlowType("销售出库");
			stockFlow.setStockType(2);
			stockFlow.setStoreAccount(storeAccount);
			//更新商品库存并生成流水
			goodsStockService.outStock(stockFlow);
		}
		
		if (realAmount.compareTo(BigDecimal.ZERO) == -1) {
			realAmount = BigDecimal.ZERO;
		}

		
		
		// 校验实收金额
		BigDecimal tempAmount = cashierDto.getDebtAmount().add(orderSubmit.getAlipayAmount())
				  .add(orderSubmit.getCardAmount()).add(orderSubmit.getCashAmount()).add(giftAmount)
				  .add(orderSubmit.getUnionpayAmount()).add(orderSubmit.getWechatAmount()).add(appointOff)
				  .add(comboAmount).add(couponAmount).add(orderSubmit.getGroupAmount()).add(orderSubmit.getDebtAmount());
		if (tempAmount.compareTo(realAmount) == -1) {
			throw new ServiceException(1, "您支付的金额与实收金额不一致");
		}

		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrderId(orderId);
		orderInfo.setCardAmount(orderSubmit.getCardAmount());
		if (cashierDto.getDebtAmount().compareTo(new BigDecimal(0)) > 0 || orderSubmit.getAlipayAmount().compareTo(new BigDecimal(0)) > 0
				  || orderSubmit.getCashAmount().compareTo(new BigDecimal(0)) > 0 
				  || orderSubmit.getUnionpayAmount().compareTo(new BigDecimal(0)) > 0
				  || orderSubmit.getWechatAmount().compareTo(new BigDecimal(0)) > 0
				  || orderSubmit.getGroupAmount().compareTo(new BigDecimal(0)) > 0) {
			orderInfo.setCashCardType(1);
		}
		else {
			orderInfo.setCashCardType(2);
		}
		orderInfo.setLevelId(levelId);
		orderInfo.setCashAmount(orderSubmit.getCashAmount());
		orderInfo.setUnionpayAmount(orderSubmit.getUnionpayAmount());
		orderInfo.setAlipayAmount(orderSubmit.getAlipayAmount());
		orderInfo.setWechatAmount(orderSubmit.getWechatAmount());
		orderInfo.setGroupAmount(orderSubmit.getGroupAmount());
		orderInfo.setAppointOff(appointOff);
		orderInfo.setComboAmount(comboAmount);
		orderInfo.setCouponAmount(couponAmount);
		orderInfo.setGiftAmount(giftAmount);
		orderInfo.setDiscountAmount(discountAmount);
		orderInfo.setDebtAmount(orderSubmit.getDebtAmount());
		orderInfo.setRealAmount(realAmount.subtract(orderSubmit.getDebtAmount()));
		orderInfo.setOrderStatus(3);
		orderInfo.setUpdateTime(curTime);
		orderInfo.setLastOperatorId(employeeId);
		orderInfoMapper.updateByPrimaryKey(orderInfo);

		OrderInfo orderInfoObj = orderInfoMapper.selectByPrimaryKey(orderId);
		
		List<Map<String, Object>> payTypeList = new ArrayList<>();
		
		if (orderSubmit.getCardAmount().compareTo(new BigDecimal(0)) == 1) {
			Map<String, Object> map = new HashMap<>();
			map.put("payName", "卡扣");
			map.put("payAmount", orderSubmit.getCardAmount());
			payTypeList.add(map);
		}
		if (orderSubmit.getCashAmount().compareTo(new BigDecimal(0)) == 1) {
			Map<String, Object> map = new HashMap<>();
			map.put("payName", "现金");
			map.put("payAmount", orderSubmit.getCashAmount());
			payTypeList.add(map);
		}
		if (orderSubmit.getUnionpayAmount().compareTo(new BigDecimal(0)) == 1) {
			Map<String, Object> map = new HashMap<>();
			map.put("payName", "银联");
			map.put("payAmount", orderSubmit.getUnionpayAmount());
			payTypeList.add(map);
		}
		if (orderSubmit.getAlipayAmount().compareTo(new BigDecimal(0)) == 1) {
			Map<String, Object> map = new HashMap<>();
			map.put("payName", "支付宝");
			map.put("payAmount", orderSubmit.getAlipayAmount());
			payTypeList.add(map);
		}
		if (orderSubmit.getWechatAmount().compareTo(new BigDecimal(0)) == 1) {
			Map<String, Object> map = new HashMap<>();
			map.put("payName", "微信");
			map.put("payAmount", orderSubmit.getWechatAmount());
			payTypeList.add(map);
		}
		if (orderSubmit.getGroupAmount().compareTo(new BigDecimal(0)) == 1) {
			Map<String, Object> map = new HashMap<>();
			map.put("payName", "团购");
			map.put("payAmount", orderSubmit.getGroupAmount());
			payTypeList.add(map);
		}
		if (orderSubmit.getDebtAmount().compareTo(new BigDecimal(0)) == 1) {
			Map<String, Object> map = new HashMap<>();
			map.put("payName", "挂账");
			map.put("payAmount", orderSubmit.getDebtAmount());
			payTypeList.add(map);
		}
		
		
		
		Map<String, Object> orderMap = new HashMap<>();
		orderMap.put("orderCode", orderInfoObj.getOrderCode());
		orderMap.put("payTypeList", payTypeList);
		orderMap.put("realAmount", orderInfoObj.getRealAmount());
		orderMap.put("stepCommissionList", stepCommissionList);
		// 检查是否需要减扣卡金
		MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(ownerMemberId, false);
		if (orderSubmit.getCardAmount().compareTo(BigDecimal.ZERO) == 1) {

			// 根据会员等级获取不同积分规则以计算应得积分
			Map<String, Integer> map = new HashMap<>();
			map.put("storeId", storeId);
			map.put("levelId", memberInfo.getLevelId());
			MemberLevelDto memberLevel = memberLevelMapper.selectByEnterprise(map);
			int integralAmount = 0;
			if (memberLevel.getIntegralNumber() > 0 && memberLevel.getIntegralUnit() > 0) {
				// 支付卡金金额 单位积分数量 * 单位获取积分, 每消费1元获取0.5分,消费100元 ＝ 100 / 1 * 0.5
				integralAmount = orderSubmit.getCardAmount().intValue() / memberLevel.getIntegralUnit()
						* memberLevel.getIntegralNumber();
			}

			// 更新子账户余额
			updateSubAccount(orderSubmit.getSubAccountId(), orderSubmit.getCardAmount(), curTime);

			// 更新会员账户信息
			updateMemberAccount(ownerMemberId, orderSubmit.getCardAmount(), integralAmount, curTime);

			// 新增会员账户流水
			insertMoneyFlow(cashierDto.getMemberInfo().getStoreId(), orderSubmit.getSubAccountId(), orderId,
					  orderSubmit.getCardAmount(), cashierDto.getMemberInfo().getBalanceAmount(), curTime, employeeId, levelId);
			// 新增积分流水
			if (integralAmount > 0) {
				insertIntegralFlow(ownerMemberId, orderId, cashierDto.getMemberInfo().getBalanceIntegral(),
						  integralAmount, curTime);
			}
		}

		// 刷新会员缓存
		if (ownerMemberId != null) {
			// 检查是否有挂帐
			if (orderSubmit.getDebtAmount().compareTo(BigDecimal.ZERO) == 1) {
				// 增加挂帐记录
				memberInfoService.insertDebtFlow(ownerMemberId, orderId, orderSubmit.getDebtAmount(), "消费欠款", 1,
						  employeeId, curTime);

				// 更新账户挂帐金额
				MemberAccount memberAccount = new MemberAccount();
				memberAccount.setAccountId(ownerMemberId);
				memberAccount.setDebtAmount(memberInfo.getDebtAmount().add(orderSubmit.getDebtAmount()));
				memberAccount.setUpdateTime(curTime);
				memberAccountMapper.updateByPrimaryKey(memberAccount);
			}

			//修改会员累计消费次数,累计消费总额,单次消费均价,最后消费时间
			Map<String, Object> avgMap = new HashMap<>();
			avgMap.put("memberId", ownerMemberId);
			avgMap.put("consumeAmount", orderInfo.getRealAmount());
			avgMap.put("consumeTime", DateUtil.getCurTime());
			memberAccountMapper.updateAvgConsume(avgMap);
			
			memberInfoService.wipeCache(ownerMemberId);
			memberInfoService.syncLevelId(ownerMemberId);
		}

		// 检查该会员是否为首次消费
		if (memberInfo != null && StringUtils.isBlank(memberInfo.getLastConsumeTime())) {
			// 检查该会员是否由其他会员推荐，查看是否需要赠送首次消费奖励
			Integer recommenderId = memberRecommendMapper.selectMemberIdByRecommendId(ownerMemberId);
			sendFirstConsumeRewardForSharer(recommenderId, orderSubmit.getOrderId(), memberInfo);
		}

		/*rabbitService.sendCashierOrderComission(orderId);*/
		/*for (SelfCashierDetailDto detail : ownerDetailList) {
			rabbitService.sendCashierUpdateOrderCommission(detail.getDetailId());
		}*/

		// 当操作人为0，代表会员自助结账，否则为前台结账，前台结账，向会员发送结账提醒
		if (memberInfo != null && orderSubmit.getIsNotify() == 1 && employeeId != 0 && cashierDto.getOrderStatus() != 5) {
			String openId = redisService.hget(App.Redis.WECHAT_MEMBERID_TO_OPENID_KEY_HASH, cashierDto.getMemberId());
			if (StringUtils.isNotBlank(openId)) {
				Set<String> projectSet = new HashSet<String>();
				for (SelfCashierDetailDto orderDetail : ownerDetailList) {
					projectSet.add(orderDetail.getProjectName());
				}
				String projectList = projectSet.toString();
				projectList = projectList.substring(1, projectList.length() - 1);
				String url = App.System.SERVER_BASE_URL
						  + Url.MemberCenter.VIEW_PAYMENT_DETAIL.replace("{businessType}", "1") + "?orderId=" + orderId;
				rabbitService.sendPaymentNotice(2, cashierDto.getMemberInfo().getStoreId(), url, openId,
						  memberInfo.getStoreName(), memberInfo.getPhone(), projectList,
						  cashierDto.getReceivableAmount().toString(), orderSubmit.getCardAmount().toString(),
						  memberInfo.getBalanceAmount().subtract(orderSubmit.getDebtAmount()).toString(),
						  orderSubmit.getDebtAmount().toString());
			}
		}

		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, orderMap);
	}
	
	/**
	 * 计算员工业绩提成
	 * @param orderDetail 详情标识
	 * @param memberSubAccount 会员子账户信息
	 * @param storeId 门店标识
	 * @param payType 支付方式
	 * @param memberId 会员标识
	 * @param employeeId 操作人
	 * @param toFixedNum 是否保留小数点
	 * @return Map<String, Object>
	 */
	protected Map<String, Object> calculateCommonCommission(OrderDetail orderDetail, MemberSubAccount memberSubAccount, 
			  Integer storeId, Integer payType, Integer memberId, Integer employeeId, Integer toFixedNum) {
		
		Map<String, Object> stepCommissionMap = new HashMap<>();
		stepCommissionMap.put("projectName", orderDetail.getProjectName());
		stepCommissionMap.put("detailId", orderDetail.getDetailId());
		stepCommissionMap.put("orderType", orderDetail.getOrderType());
		stepCommissionMap.put("isAssign", 0);
		
        BigDecimal hundred = new BigDecimal(100);
        
	    //查询门店设置
	    Map<String, Object> storeSettingMap = employeeObjectiveMapper.selectStoreSetting(storeId);
	    //业绩提成是否扣除成本(0:不扣除，1:扣除)
	    Integer costCommissionType = Integer.valueOf(storeSettingMap.get("costCommissionType").toString());
	    //礼金减扣比例
	    Integer giftCommissionRate = Integer.valueOf(storeSettingMap.get("giftCommissionRate").toString());
	    //优惠券减扣比例
	    Integer couponCommissionRate = Integer.valueOf(storeSettingMap.get("couponCommissionRate").toString());
	    //业绩计算是否扣减固定业绩(1:是，0:否)
	    Integer commissionFixedType = Integer.valueOf(storeSettingMap.get("commissionFixedType").toString());
		
	    Integer performanceDiscountPercent = 100;
	    
	    if (memberSubAccount != null) {
	    	
	    	Map<String, Integer> map = new HashMap<>();
	    	map.put("levelId", memberSubAccount.getLevelId());
	    	map.put("storeId", storeId);
	    	MemberLevelDiscount memberLevelDiscount = memberLevelDiscountMapper.selectByStoreLevel(map);
	    	
	    	performanceDiscountPercent = memberLevelDiscount.getPerformanceDiscountPercent();
	    }
	    
	    List<Map<String, Object>> stepList = new ArrayList<>();
	    
	    List<OrderDetailStepDto> stepDtoList =  shiftMahjongProjectStepMapper.selectOrderStepListByDetailId(orderDetail.getDetailId());
		// 业绩计算
        BigDecimal tataliCommonCalculate = null;
		if (orderDetail.getOffType() == 1) {
            //疗程计算比例
			tataliCommonCalculate = employeeObjectiveMapper.selectByComboCommonCalculate(orderDetail.getComboId());
        }
        else if (orderDetail.getCouponId() != null) {
            //项目实际金额 + (项目折扣价格 -实际金额 - 预约优惠) *  优惠券减扣比例
        	tataliCommonCalculate = orderDetail.getRealPrice().add((orderDetail.getDiscountAmount().subtract(orderDetail.getRealPrice())
                    .subtract(orderDetail.getAppointOff())).multiply(new BigDecimal(couponCommissionRate)).divide(hundred));
        }
        else if (orderDetail.getGiftAmount().compareTo(new BigDecimal(0)) == 1) {
            //项目实际金额 + (项目折扣价格 -实际金额 - 预约优惠) *  礼金减扣比例
        	tataliCommonCalculate = orderDetail.getRealPrice().add((orderDetail.getDiscountAmount().subtract(orderDetail.getRealPrice())
                    .subtract(orderDetail.getAppointOff())).multiply(new BigDecimal(giftCommissionRate)).divide(hundred));
        }
        else {
        	tataliCommonCalculate = orderDetail.getRealPrice();
        }
        
        if (costCommissionType == 1) {
        	if (orderDetail.getOrderType() == 1) {
        		ProjectInfo projectInfo = projectService.queryProjectInfoById(orderDetail.getProjectId());
            	tataliCommonCalculate = tataliCommonCalculate.subtract(projectInfo.getCostPrice());
        	}
        	else if (orderDetail.getOrderType() == 2) {
        		GoodsInfoDto goodsInfoDto = goodsInfoMapper.selectByPrimaryKey(orderDetail.getProjectId());
            	tataliCommonCalculate = tataliCommonCalculate.subtract(goodsInfoDto.getCostPrice());
        	}
        	else {
        		//疗程成本价未计算
        		
        	}
        }
        tataliCommonCalculate = tataliCommonCalculate.setScale(toFixedNum, BigDecimal.ROUND_HALF_UP);
        stepCommissionMap.put("tataliCommonCalculate", tataliCommonCalculate);
        //当为项目时
		if (orderDetail.getOrderType() == 1) {

			BigDecimal fixedValue = projectStepMapper.selectFixedValue(orderDetail.getProjectId());
	        
		    for (OrderDetailStepDto orderDetailStepDto : stepDtoList) {
		        // 业绩计算方式
		        Integer stepPerformanceType = null;
		        // 业绩金额
		        BigDecimal stepPerformance =  null;
		        //员工业绩值
		        BigDecimal saveCommonCalculate = null;
		        //员工提成金额
		        BigDecimal empCommission = null;
		        
		        if (orderDetailStepDto.getIsAssign() == 1) {
		        	stepCommissionMap.put("isAssign", 1);
		        }
		        
		        Map<String, Object> stepOrderMap = new HashMap<>();
		        stepOrderMap.put("positionName", orderDetailStepDto.getPositionName());
		        //当服务人员不为空时
		        if (orderDetailStepDto.getEmployeeInfo() != null) {
		        	stepOrderMap.put("shiftMahjongStepId", orderDetailStepDto.getShiftMahjongStepId());
		        	stepOrderMap.put("employeeId", orderDetailStepDto.getEmployeeInfo().getEmployeeId());
		        	stepOrderMap.put("employeeCodeName", 
		        			  orderDetailStepDto.getEmployeeInfo().getEmployeeCode() + " " +orderDetailStepDto.getEmployeeInfo().getName());
		        	
		        	//获取到该员工对应业绩值
		    		Map<String, Integer> stepMap = new HashMap<>();
		    		stepMap.put("projectId", orderDetail.getProjectId());
		    		stepMap.put("positionId", orderDetailStepDto.getPositionId());
		    		ProjectStep projectStep = projectStepMapper.selectByPositionId(stepMap);
		    		stepPerformanceType = projectStep.getStepPerformanceType();
		    		stepPerformance = projectStep.getStepPerformance();
		    		
			    	//比例
	                if (stepPerformanceType == 1) {
	                    if (commissionFixedType == 0) {
	                        saveCommonCalculate = tataliCommonCalculate.multiply(stepPerformance).divide(new BigDecimal(100));
	                    }
	                    else {
	                        saveCommonCalculate = (tataliCommonCalculate.subtract(fixedValue)).multiply(stepPerformance).divide(new BigDecimal(100));
	                    }
	                    saveCommonCalculate = saveCommonCalculate.multiply(new BigDecimal(performanceDiscountPercent)).divide(new BigDecimal(100));
	                }
	                //固定值
	                else {
	                    saveCommonCalculate = stepPerformance;
	                }
	                
	                saveCommonCalculate = zeroChoose(saveCommonCalculate);
	                saveCommonCalculate = saveCommonCalculate.setScale(toFixedNum, BigDecimal.ROUND_HALF_UP);
	                stepOrderMap.put("commissionCalculate", saveCommonCalculate);
	                
	                Integer stepPositionId = orderDetailStepDto.getEmployeeInfo().getPositionId();
	                Integer stepLevelId = null;
	                
	                //是否存在提成方案
		    		Integer isExists = 0;
	                
	                if (stepPositionId.intValue() == orderDetailStepDto.getPositionId()) {
	                	stepLevelId = orderDetailStepDto.getEmployeeInfo().getLevelId();
	                }
	                else {
	                	Map<String, Integer> referenceMap 
	                	          = employeeLevelMapper.selectReferencePositionId(orderDetailStepDto.getEmployeeInfo().getLevelId());
	                	if (referenceMap.get("positionId1") != null 
	                			   && referenceMap.get("positionId1").intValue() == orderDetailStepDto.getPositionId()) {
	                		stepLevelId = referenceMap.get("levelId1");
	                	}
	                	else if (referenceMap.get("positionId2") != null 
	                			  && referenceMap.get("positionId2").intValue() == orderDetailStepDto.getPositionId()){
	                		stepLevelId = referenceMap.get("levelId2");
	                	}
	                	else {
	                		isExists = 1;
	                	}
	                }
		    		
		    		//提成方式
		    		Integer commissionType = null;
		    		//员工提成方案
		    		ProjectCommission commissionObj = null;

		    		ProjectCommission projectCommission = new ProjectCommission();
		    		projectCommission.setProjectId(orderDetail.getProjectId());
		    		projectCommission.setLevelId(stepLevelId);
		    		//查询当前级别对应的提成方式
		    		List<ProjectCommission> projectCommissionList = projectCommissionMapper.selectByProperty(projectCommission);
		    		
		    		if (projectCommissionList.isEmpty()) {
		    			isExists = 1;
		    		}
		    		else {
		    			commissionObj = projectCommissionList.get(0);
		    		}
			    	
			    	if (isExists  == 1) {
			    		empCommission = new BigDecimal(0);
			    	}
			    	else {
			    		empCommission = new BigDecimal(0);
			    		if (orderDetail.getOffType() == 1) {
			    			payType = 2;
			    		}
			    		commissionType = commissionObj.getAssignCashType();
			    		switch(commissionType) {
			    			case 1 : // 按比例
			    				//如果为指定
			    				if (orderDetailStepDto.getIsAssign() == 1) {
			    					if (payType == 1) {
				    			        empCommission 
				    			            = tataliCommonCalculate.multiply(new BigDecimal(commissionObj.getCommissionGold())).divide(hundred);
				    			    }
				    			    else if (payType == 2){
				    			    	empCommission 
				    			    	    = tataliCommonCalculate.multiply(new BigDecimal(commissionObj.getCommissionCourse())).divide(hundred);
				    			    }
				    			    else {
				    			    	empCommission 
				    			    	    = tataliCommonCalculate.multiply(new BigDecimal(commissionObj.getCommissionCash())).divide(hundred);
				    			    }
			    				}
			    				else {
			    					if (payType == 1) {
				    			        empCommission 
				    			            = tataliCommonCalculate.multiply(new BigDecimal(commissionObj.getCommissionNoGold())).divide(hundred);
				    			    }
				    			    else if (payType == 2){
				    			        empCommission 
				    			            = tataliCommonCalculate.multiply(new BigDecimal(commissionObj.getCommissionNoCourse())).divide(hundred);
				    			    }
			    					else {
			    						empCommission 
			    						    = tataliCommonCalculate.multiply(new BigDecimal(commissionObj.getCommissionNoCash())).divide(hundred);
			    					}
			    				}
			    			    empCommission = empCommission.multiply(new BigDecimal(performanceDiscountPercent)).divide(new BigDecimal(100));
			    				break;
			    			case 2 : // 按固定金额
			    				if (orderDetailStepDto.getIsAssign() == 1) {
			    					if (payType == 1) {
				    			        empCommission = new BigDecimal(commissionObj.getCommissionGold());
				    			    }
				    			    else if (payType == 2){
				    			    	empCommission = new BigDecimal(commissionObj.getCommissionCourse());
				    			    }
				    			    else {
				    			    	empCommission = new BigDecimal(commissionObj.getCommissionCash());
				    			    }
			    				}
			    				else {
			    					if (payType == 1) {
				    			        empCommission = new BigDecimal(commissionObj.getCommissionNoGold());
				    			    }
				    			    else if (payType == 2){
				    			        empCommission = new BigDecimal(commissionObj.getCommissionNoCourse());
				    			    }
			    					else {
			    						empCommission = new BigDecimal(commissionObj.getCommissionNoCash());
			    					}
			    				}
			    				break;
			    			default :
			    				break;
			    		}
			    		
			    		//当为预约时
			    		if (orderDetailStepDto.getIsAppoint() == 1) {
			    		    empCommission = empCommission.add(new BigDecimal(commissionObj.getCommissionCard()));
			    		}
			    	}
			    	empCommission = empCommission.setScale(toFixedNum, BigDecimal.ROUND_HALF_UP);
			    	stepOrderMap.put("commissionAmount", empCommission);
			    	EmployeeCommission employeeCommission = new EmployeeCommission();
					employeeCommission.setDetailId(orderDetail.getDetailId());
					employeeCommission.setShiftMahjongStepId(orderDetailStepDto.getShiftMahjongStepId());
					employeeCommission.setPositionId(orderDetailStepDto.getPositionId());
					employeeCommission.setOrderType(orderDetail.getOrderType());
					employeeCommission.setEmployeeId(orderDetailStepDto.getEmployeeInfo().getEmployeeId());
					employeeCommission.setCommissionCalculate(saveCommonCalculate);
					employeeCommission.setCommissionAmount(empCommission);
					employeeCommission.setChargeTime(DateUtil.getCurTime());
					employeeCommissionMapper.insert(employeeCommission);
					stepOrderMap.put("commissionId", employeeCommission.getCommissionId());
			        stepList.add(stepOrderMap);
		        }
			}
		}
		else  if (orderDetail.getOrderType() == 2){
			//员工总业绩值
	        BigDecimal saveCommonCalculate = null;
	        //员工总提成
	        BigDecimal empCommission = null;
	        
			GoodsInfoDto goodsInfoDto = goodsInfoMapper.selectByPrimaryKey(orderDetail.getProjectId());
			
			stepCommissionMap.put("aId", goodsInfoDto.getaId());
			
			stepCommissionMap.put("goodsNum", 1);
			
			List<CommissionScheme> commissionSchemeList = commissionSchemeMapper.selectByStoreId(storeId);
			
			Map<String, Object> stepOrderMap = null;
			
			Integer calculationType = goodsInfoDto.getCalculationType();
			if (calculationType == 1) {
				saveCommonCalculate = tataliCommonCalculate.multiply(goodsInfoDto.getOnlineShoppingPrice()).divide(hundred);
			}
			else {
				saveCommonCalculate = goodsInfoDto.getOnlineShoppingPrice();
			}
			saveCommonCalculate = saveCommonCalculate.multiply(new BigDecimal(performanceDiscountPercent)).divide(new BigDecimal(100))
					   .setScale(toFixedNum, BigDecimal.ROUND_HALF_UP);
			
			if (goodsInfoDto.getCommissionType() == 1) {
				//现金
				if (payType == 0) {
					empCommission = saveCommonCalculate.multiply(new BigDecimal(goodsInfoDto.getCommissionAmount())).divide(hundred);
				}
				else {
					empCommission = saveCommonCalculate.multiply(new BigDecimal(goodsInfoDto.getCardAmount())).divide(hundred);
				}
			}
			else {
				//现金
				if (payType == 0) {
					empCommission = new BigDecimal(goodsInfoDto.getCommissionAmount());
				}
				else {
					empCommission = new BigDecimal(goodsInfoDto.getCardAmount());
				}
			}
			empCommission = empCommission.setScale(toFixedNum, BigDecimal.ROUND_HALF_UP);
			if (stepDtoList.size() > 0) {
				if (stepDtoList.size() == 1) {
					stepOrderMap = new HashMap<>();
					stepOrderMap.put("employeeId", stepDtoList.get(0).getEmployeeInfo().getEmployeeId());
		        	stepOrderMap.put("employeeCodeName", stepDtoList.get(0).getEmployeeInfo().getEmployeeCode() 
		        			  + " " + stepDtoList.get(0).getEmployeeInfo().getName());
		        	stepOrderMap.put("commissionCalculate", saveCommonCalculate);
		        	stepOrderMap.put("commissionAmount", empCommission);
		        	
		        	EmployeeCommission employeeCommission = new EmployeeCommission();
					employeeCommission.setDetailId(orderDetail.getDetailId());
					employeeCommission.setOrderType(orderDetail.getOrderType());
					employeeCommission.setEmployeeId(stepDtoList.get(0).getEmployeeInfo().getEmployeeId());
					employeeCommission.setCommissionCalculate(saveCommonCalculate);
					employeeCommission.setCommissionAmount(empCommission);
					employeeCommission.setChargeTime(DateUtil.getCurTime());
					employeeCommissionMapper.insert(employeeCommission);
					
					stepOrderMap.put("commissionId", employeeCommission.getCommissionId());
		        	stepList.add(stepOrderMap);
				}
				else {
					String commissionStr = null;
					if (stepDtoList.size() == 2){
						for (CommissionScheme commissionScheme : commissionSchemeList) {
							if (commissionScheme.getCommissionType() == 1 && payType == 0) {
								commissionStr = commissionScheme.getCashTwoCommission();
							}
							else if (commissionScheme.getCommissionType() == 1 && payType == 1){
								commissionStr = commissionScheme.getCardTwoCommission();
							}
						}
					}
					else if (stepDtoList.size() == 3) {
						for (CommissionScheme commissionScheme : commissionSchemeList) {
							if (commissionScheme.getCommissionType() == 1 && payType == 0) {
								commissionStr = commissionScheme.getCashThreeCommission();
							}
							else if (commissionScheme.getCommissionType() == 1 && payType == 1){
								commissionStr = commissionScheme.getCardThreeCommission();
							}
						}
					}
					String[] twoCommission = commissionStr.split(",");
					for (int i = 0; i < twoCommission.length; i++) {
						String commission = twoCommission[i];
						//业绩比例
						Integer calculationProportion = Integer.valueOf(commission.split(":")[0]);
						
						BigDecimal calculation = saveCommonCalculate.multiply(new BigDecimal(calculationProportion)).divide(hundred)
								   .setScale(toFixedNum, BigDecimal.ROUND_HALF_UP);
						
						//提成比例
						Integer commProportion = Integer.valueOf(commission.split(":")[1]);
						
						BigDecimal comm = empCommission.multiply(new BigDecimal(commProportion)).divide(hundred)
								   .setScale(toFixedNum, BigDecimal.ROUND_HALF_UP);
						
						stepOrderMap = new HashMap<>();
						stepOrderMap.put("employeeId", stepDtoList.get(i).getEmployeeInfo().getEmployeeId());
			        	stepOrderMap.put("employeeCodeName", stepDtoList.get(i).getEmployeeInfo().getEmployeeCode() 
			        			  + " " + stepDtoList.get(i).getEmployeeInfo().getName());
			        	stepOrderMap.put("commissionCalculate", calculation);
			        	stepOrderMap.put("commissionAmount", comm);
			        	
			        	EmployeeCommission employeeCommission = new EmployeeCommission();
						employeeCommission.setDetailId(orderDetail.getDetailId());
						employeeCommission.setOrderType(orderDetail.getOrderType());
						employeeCommission.setEmployeeId(stepDtoList.get(i).getEmployeeInfo().getEmployeeId());
						employeeCommission.setCommissionCalculate(calculation);
						employeeCommission.setCommissionAmount(comm);
						employeeCommission.setChargeTime(DateUtil.getCurTime());
						employeeCommissionMapper.insert(employeeCommission);
						stepOrderMap.put("commissionId", employeeCommission.getCommissionId());
			        	stepList.add(stepOrderMap);
					}
				}
			}
		}
		else {
			//员工总业绩值
	        BigDecimal saveCommonCalculate = null;
	        //员工总提成
	        BigDecimal empCommission = null;
	        
	        ComboInfo comboInfo = comboInfoMapper.selectByPrimaryKey(orderDetail.getProjectId());
			
	        Map<String, String> map = comboGoodsMapper.selectGoodsNumByComboId(comboInfo.getComboId());
	        
	        if (map == null) {
	        	stepCommissionMap.put("aId", null);
				
				stepCommissionMap.put("goodsNum", null);
	        }
	        else {
	        	stepCommissionMap.put("aId", map.get("aId"));
				
				stepCommissionMap.put("goodsNum", map.get("goodsNum"));
	        }
	        
			List<CommissionScheme> commissionSchemeList = commissionSchemeMapper.selectByStoreId(storeId);
			
			Map<String, Object> stepOrderMap = null;
			
			Integer commissionType = comboInfo.getCommissionType();
			if (commissionType == 1) {
				saveCommonCalculate = tataliCommonCalculate.multiply(comboInfo.getComboPerformance()).divide(hundred);
				
				//现金
				if (payType == 0) {
					empCommission = saveCommonCalculate.multiply(comboInfo.getCashCommission()).divide(hundred);
				}
				else {
					empCommission = saveCommonCalculate.multiply(comboInfo.getCardCommission()).divide(hundred);
				}
			}
			else {
				saveCommonCalculate = comboInfo.getComboPerformance();
				
				//现金
				if (payType == 0) {
					empCommission = comboInfo.getCashCommission();
				}
				else {
					empCommission = comboInfo.getCardCommission();
				}
			}
			saveCommonCalculate = saveCommonCalculate.multiply(new BigDecimal(performanceDiscountPercent)).divide(new BigDecimal(100))
					.setScale(toFixedNum, BigDecimal.ROUND_HALF_UP);
			empCommission = empCommission.setScale(toFixedNum, BigDecimal.ROUND_HALF_UP);
			if (stepDtoList.size() > 0) {
				if (stepDtoList.size() == 1) {
					stepOrderMap = new HashMap<>();
					stepOrderMap.put("employeeId", stepDtoList.get(0).getEmployeeInfo().getEmployeeId());
		        	stepOrderMap.put("employeeCodeName", stepDtoList.get(0).getEmployeeInfo().getEmployeeCode() 
		        			  + " " + stepDtoList.get(0).getEmployeeInfo().getName());
		        	stepOrderMap.put("commissionCalculate", saveCommonCalculate);
		        	stepOrderMap.put("commissionAmount", empCommission);
		        	
		        	EmployeeCommission employeeCommission = new EmployeeCommission();
					employeeCommission.setDetailId(orderDetail.getDetailId());
					employeeCommission.setOrderType(orderDetail.getOrderType());
					employeeCommission.setEmployeeId(stepDtoList.get(0).getEmployeeInfo().getEmployeeId());
					employeeCommission.setCommissionCalculate(saveCommonCalculate);
					employeeCommission.setCommissionAmount(empCommission);
					employeeCommission.setChargeTime(DateUtil.getCurTime());
					employeeCommissionMapper.insert(employeeCommission);
					stepOrderMap.put("commissionId", employeeCommission.getCommissionId());
		        	stepList.add(stepOrderMap);
				}
				else {
					String commissionStr = null;
					if (stepDtoList.size() == 2){
						for (CommissionScheme commissionScheme : commissionSchemeList) {
							if (commissionScheme.getCommissionType() == 1 && payType == 0) {
								commissionStr = commissionScheme.getCashTwoCommission();
							}
							else if (commissionScheme.getCommissionType() == 1 && payType == 1){
								commissionStr = commissionScheme.getCardTwoCommission();
							}
						}
					}
					else if (stepDtoList.size() == 3) {
						for (CommissionScheme commissionScheme : commissionSchemeList) {
							if (commissionScheme.getCommissionType() == 1 && payType == 0) {
								commissionStr = commissionScheme.getCashThreeCommission();
							}
							else if (commissionScheme.getCommissionType() == 1 && payType == 1){
								commissionStr = commissionScheme.getCardThreeCommission();
							}
						}
					}
					String[] twoCommission = commissionStr.split(",");
					for (int i = 0; i < twoCommission.length; i++) {
						
						stepOrderMap = new HashMap<>();
						
						String commission = twoCommission[i];
						//业绩比例
						Integer calculationProportion = Integer.valueOf(commission.split(":")[0]);
						
						BigDecimal calculation = saveCommonCalculate.multiply(new BigDecimal(calculationProportion)).divide(hundred)
								   .setScale(toFixedNum, BigDecimal.ROUND_HALF_UP);
						
						//提成比例
						Integer commProportion = Integer.valueOf(commission.split(":")[1]);
						
						BigDecimal comm = empCommission.multiply(new BigDecimal(commProportion)).divide(hundred)
								   .setScale(toFixedNum, BigDecimal.ROUND_HALF_UP);
						
						stepOrderMap.put("employeeId", stepDtoList.get(i).getEmployeeInfo().getEmployeeId());
			        	stepOrderMap.put("employeeCodeName", stepDtoList.get(i).getEmployeeInfo().getEmployeeCode() 
			        			  + " " + stepDtoList.get(i).getEmployeeInfo().getName());
			        	stepOrderMap.put("commissionCalculate", calculation);
			        	stepOrderMap.put("commissionAmount", comm);
			        	
			        	EmployeeCommission employeeCommission = new EmployeeCommission();
						employeeCommission.setDetailId(orderDetail.getDetailId());
						employeeCommission.setOrderType(orderDetail.getOrderType());
						employeeCommission.setEmployeeId(stepDtoList.get(i).getEmployeeInfo().getEmployeeId());
						employeeCommission.setCommissionCalculate(calculation);
						employeeCommission.setCommissionAmount(comm);
						employeeCommission.setChargeTime(DateUtil.getCurTime());
						employeeCommissionMapper.insert(employeeCommission);
						
						stepOrderMap.put("commissionId", employeeCommission.getCommissionId());
			        	stepList.add(stepOrderMap);
					}
				}
			}
			//插入会员疗程表
			insertMemberComboInfo(orderDetail.getProjectId(), employeeId, memberId, orderDetail.getDetailId());
		}
		stepCommissionMap.put("stepList", stepList);
		return stepCommissionMap;
	}

	
	/**
	 * 添加用户疗程信息
	 * @param comboId 疗程标识
	 * @param employeeId 员工标识
	 * @param memberId 会员标识
	 * @param detailId 明细标识
	 */
	protected void insertMemberComboInfo(Integer comboId, Integer employeeId, Integer memberId, Integer detailId) {
	    
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("comboId", comboId);
		map.put("employeeId", employeeId);
		map.put("memberId", memberId);
		map.put("detailId", detailId);
		// 添加用户疗程
		Integer recordId = orderDetailMapper.insertMemberComboRecord(map);
		
		Map<String, Integer> projectMap = new HashMap<String, Integer>();
		projectMap.put("recordId", recordId);
		projectMap.put("employeeId", employeeId);
		projectMap.put("comboId", comboId);
        //会员疗程项目明细表
		orderDetailMapper.insertMemberComboProject(map);
		
		
	}
	
	/**
	 * 赋值为o
	* @author 王大爷
	* @date 2016年1月6日 下午3:31:01
	* @param values 值
	* @return BigDecimal
	 */
	public BigDecimal zeroChoose (BigDecimal values) {
	    if (values.compareTo(new BigDecimal(0)) != 1) {
	        values = new BigDecimal(0);
        }
	    return values;
	}
	
	/**
	 * 针对分享者，赠送推荐好友的首次消费奖励
	 * 
	 * @author 张进军
	 * @date Jan 8, 2016 8:31:31 PM
	 * @param recommenderId
	 *            分享者
	 * @param orderId
	 *            首次消费的订单标识
	 * @param recommendedInfo
	 *            被推荐者信息
	 */
	private void sendFirstConsumeRewardForSharer(Integer recommenderId, int orderId, MemberBaseDto recommendedInfo) {
		MemberBaseDto recommenderInfo = memberInfoService.getMemberBaseInfo(recommenderId, false);
		if (recommenderInfo == null) {
			return;
		}

		int storeId = recommenderInfo.getStoreId();
		StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(storeId);
		String res = storeSetting.getMemberShareReward();
		int reward = 0;
		if (StringUtils.isNotBlank(res)) {
			JSONObject json = JSONObject.fromObject(res);
			int type = json.getInt("firstConsumeRewardType");
			int unit = json.getInt("firstConsumeRewardUnit");
			reward = json.getInt("firstConsumeRewardContent");

			// 优惠券奖励，直接赠送
			if (type == 3) {
				memberInfoService.presentCouponToMember(recommenderId, reward);
			}
			// 积分、礼金根据消费金额/单位金额进行计算
			else {
				OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderId);
				reward = orderInfo.getReceivableAmount().divide(new BigDecimal(unit)).intValue() * reward;
				// 积分奖励
				if (type == 1) {
					memberInfoService.changeIntegralToMember(recommenderId, reward, 2, "推荐好友的首次消费奖励", null);
				}
				// 礼金奖励
				else if (type == 2) {
					memberInfoService.presentGiftmoneyToMember(recommenderId, new BigDecimal(reward), "推荐好友的首次消费奖励");
				}
			}

			String openId = redisService.hget(App.Redis.WECHAT_MEMBERID_TO_OPENID_KEY_HASH, recommenderId);
			if (StringUtils.isNotBlank(openId)) {
				String phone = recommendedInfo.getPhone();
				if (StringUtils.isNotBlank(phone)) {
					phone = phone.substring(0, 3) + "****" + phone.substring(7, 11);
				}

				String url = "";
				String title = "恭喜您，您邀请的好友(" + phone + ")在本店进行了首次消费，特为您送上奖励";
				String overdue = "永久有效";
				String remark = "点击查看奖励内容";
				String rewardName = "";

				switch (type) {
					// 积分
					case 1:
						url = App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_INTEGRAL_FLOW_LIST;
						rewardName = reward + "分商城积分";
						break;
					// 礼金
					case 2:
						url = App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_GIFT_MONEY_FLOW_LIST;
						rewardName = reward + "元账户礼金";
						break;
					// 优惠券
					case 3:
						url = App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_MEMBER_COUPON;
						CouponInfo couponInfo = couponInfoMapper.selectNormalByCouponId(reward);
						rewardName = couponInfo.getCouponPrice() + "元";
						switch (couponInfo.getCouponType()) {
							case 0:
								rewardName += "通用优惠券";
								break;
							case 1:
								rewardName += "项目优惠券";
								break;
							case 2:
								rewardName += "商品优惠券";
								break;
		
							default:
								break;
						}
	
						break;
					default:
						break;
				}

				url += "?memberId=" + recommenderId;
				rabbitService.sendMemberRewardNotice(storeId, url, openId, title, rewardName, overdue, remark);
			}
		}
	}

	/**
	 * 更新订单的会员信息
	 * 
	 * @param storeId
	 *            门店标识
	 * @param orderId
	 *            订单标识
	 * @param memberId
	 *            会员标识
	 * @param passwd
	 *            会员密码
	 * @return BaseDto
	 */
	public BaseDto changeOrderMemberId(Integer storeId, Integer orderId, Integer memberId, String passwd) {
		Map<String, Object> param = memberInfoMapper.selectSelfCashierMemberPassById(memberId);
		if (param == null || param.get("memberId") == null) {
			return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "所选会员不存在");
		}
		// 检查用户密码
		if (!StringUtil.md5(passwd + param.get("passwordSalt")).equals(param.get("payPassword"))) {
			return new BaseDto(9002, "支付密码错误");
		}
		// 修改订单信息
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("memberId", memberId);
		map.put("orderId", orderId);
		int count = orderInfoMapper.updateOrderMemberInfo(map);
		if (count == 1) {
			openCardService.changeMemberOrder(memberId, storeId);
			return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "绑定会员成功");
		}
		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "绑定会员失败");

	}

	/**
	 * 根据门店标识、订单标识查询订单详情
	 * 
	 * @param orderId
	 *            订单标识
	 * @param queryOff
	 *            是否查询优惠
	 * @param storeId 门店标识
	 * @return SelfCashierOrderDto
	 */
	public SelfCashierOrderDto selectSelfCashierOrderDetail(Integer orderId, boolean queryOff, Integer storeId) {
		SelfCashierOrderDto orderInfo = orderInfoMapper.selectUnfinishedOrderDetail(orderId);
		if (orderInfo == null || orderInfo.getMemberId() == null) {
			return orderInfo;
		}
		if (queryOff) {
			orderInfo = calculatePaymentOff(orderInfo, storeId);
		}
		return orderInfo;
	}

	/**
	 * 更新会员账户信息
	 * @author 张进军
	 * @date Nov 12, 2015 12:40:24 AM
	 * @param memberId 会员标识
	 * @param useAmount 使用金额
	 * @param integralAmount 获赠积分
	 * @param time 使用时间
	 * @throws ServiceException 如果储值余额小于使用卡金金额，抛出异常
	 */
	@Transactional
	protected void updateMemberAccount(int memberId, BigDecimal useAmount, int integralAmount, String time)
			throws ServiceException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountId", memberId);
		map.put("usedBalanceAmount", useAmount);
		map.put("memberIntegral", integralAmount);
		map.put("consumeCount", 1);
		map.put("currDate", time);
		int count = memberAccountMapper.updateMemberCashier(map);
		if (count == 0) {
			throw new ServiceException(-1, "对不起，储值账户余额不足");
		}
	}

	/**
	 * 跟新子账户余额
	 * 
	 * @author 张进军
	 * @date Mar 17, 2016 7:21:52 PM
	 * @param subAccountId
	 *            子账户标识
	 * @param useAmount
	 *            交易金额
	 * @param time
	 *            交易时间
	 * @throws ServiceException
	 *             如果储值余额小于使用卡金金额，抛出异常
	 */
	protected void updateSubAccount(int subAccountId, BigDecimal useAmount, String time) throws ServiceException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subAccountId", subAccountId);
		map.put("useAmount", useAmount);
		map.put("time", time);
		int count = memberSubAccountMapper.updateBalanceAmountForConsume(map);
		if (count == 0) {
			throw new ServiceException(-1, "对不起，储值账户余额不足");
		}
	}

	/**
	 * 更新用户的优惠券信息
	 * 
	 * @author 张进军
	 * @date Nov 11, 2015 9:50:25 PM
	 * @param relevanceId
	 *            优惠券关联标识
	 * @throws ServiceException
	 *             如不存在可用优惠券，抛出异常
	 */
	/*@Transactional
	protected void updateMemberCouponInfo(int relevanceId) throws ServiceException {
		int count = memberCouponMapper.updateUseStatusByRelevanceId(relevanceId);
		if (count == 0) {
			throw new ServiceException(-1, "对不起，暂没有可用的优惠券");
		}
	}*/

	/**
	 * 更新会员的疗程信息
	 * 
	 * @author 张进军
	 * @date Nov 11, 2015 9:56:51 PM
	 * @param memberId
	 *            会员标识
	 * @param detailId
	 *            会员疗程明细标识
	 * @param time
	 *            更新时间
	 * @throws ServiceException
	 *             如不存在可用疗程，抛出异常
	 */
	@Transactional
	protected void updateMemberComboInfo(int memberId, int detailId, String time) throws ServiceException {
		// TODO 判断疗程抵扣时，没有次数限制不做扣减
		Integer countTime = comboInfoMapper.checkComboCountLimit(detailId);
		if (countTime == 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("detailId", detailId);
			map.put("comboCount", 1);
			map.put("updateTime", time);
			memberComboProjectMapper.updateSelfCashierCombo(map);
		}
	}

	/**
	 * 新增消费获取的积分
	 * 
	 * @param memberId
	 *            会员标识
	 * @param orderId
	 *            订单标识
	 * @param integralBanlance
	 *            积分余额
	 * @param integralAmount
	 *            获赠积分数量
	 * @param time
	 *            流水时间
	 */
	@Transactional
	protected void insertIntegralFlow(Integer memberId, Integer orderId, Integer integralBanlance,
			  Integer integralAmount, String time) {
		IntegralFlow integralFlow = new IntegralFlow();
		integralFlow.setAccountId(memberId);
		integralFlow.setOrderId(orderId);
		integralFlow.setFlowAmount(integralAmount);
		integralFlow.setBalanceAmount(integralBanlance);
		integralFlow.setBusinessType("消费得积分");
		integralFlow.setBusinessDesc("");
		integralFlow.setFlowType(2);
		integralFlow.setFlowTime(time);
		integralFlowMapper.insert(integralFlow);
	}

	/**
	 * 合并订单
	* @author 老王
	* @date 2016年5月16日 上午11:04:28 
	* @param mainOrderId 合并主订单标识
	* @param removeOrderId 合并副订单标识
	* @return BaseDto
	 */
	@Transactional
	public BaseDto mergeOrder(Integer mainOrderId, Integer removeOrderId) {
		orderInfoMapper.deleteByPrimaryKey(removeOrderId);
		List<OrderDetail> orderDetailList = orderDetailMapper.selectOrderDetail(removeOrderId);
		
		OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(mainOrderId);
		
		MemberBaseDto memberBaseDto = new MemberBaseDto();
		
		if (orderInfo.getMemberId() != null) {
			memberBaseDto = memberInfoService.getMemberBaseInfo(orderInfo.getMemberId(), true);
		}
		
		for (OrderDetail objDetail : orderDetailList) {
			
			OrderDetail record = new OrderDetail();
			
			//项目
	        if (objDetail.getOrderType() == 1) {
	            BigDecimal discountAmount = objDetail.getProjectPrice();
	            BigDecimal rate = new BigDecimal(100);
	            
	            //计算折扣价
	            if (orderInfo.getMemberId() != null) {
	            	
	                Map<String, Integer> map = new HashMap<String, Integer>();
	                map.put("levelId", memberBaseDto.getLevelId());
	                map.put("projectId", objDetail.getProjectId());
	                ProjectDiscount obj = projectDiscountMapper.selectDiscountPorjectIdAndLevelId(map);
	                
	                //该项目对应该会员的会员等级不存在特定价格
	                if (obj == null) {
	                	Map<String, Integer> memberMap = new HashMap<>();
	                	memberMap.put("storeId", orderInfo.getStoreId());
	                	memberMap.put("levelId", memberBaseDto.getLevelId());
	                    //计算会员折扣价
	                    MemberLevelDto memberLevel = memberLevelMapper.selectByEnterprise(memberMap);
	                    discountAmount = discountAmount.multiply(new BigDecimal(memberLevel.getProjectDiscount()).divide(rate));
	                }
	                //该项目对应该会员的会员等级存在特定价格
	                else {
	                    discountAmount = obj.getDiscountAmount();
	                }
	            }
	            record.setDiscountAmount(discountAmount);
	        }
	        
	        //商品
	        else if (objDetail.getOrderType() == 2) {
	        	BigDecimal discountAmount = objDetail.getProjectPrice();
	            BigDecimal rate = new BigDecimal(100);
	            
	            //计算折扣价
	            if (orderInfo.getMemberId() != null) {
	                Map<String, Integer> map = new HashMap<String, Integer>();
	                map.put("levelId", memberBaseDto.getLevelId());
	                map.put("projectId", objDetail.getProjectId());
	                GoodsDiscount obj = goodsDiscountMapper.selectDiscountGoodsIdAndLevelId(map);
	                
	                //该商品对应该会员的会员等级不存在特定价格
	                if (obj == null) {
	                	Map<String, Integer> memberMap = new HashMap<>();
	                	memberMap.put("storeId", orderInfo.getStoreId());
	                	memberMap.put("levelId", memberBaseDto.getLevelId());
	                    //计算会员折扣价
	                    MemberLevelDto memberLevel = memberLevelMapper.selectByEnterprise(memberMap);
	                    discountAmount = discountAmount.multiply(new BigDecimal(memberLevel.getGoodsDiscount()).divide(rate));
	                }
	                //该商品对应该会员的会员等级存在特定价格
	                else {
	                    discountAmount = obj.getDiscountAmount();
	                }
	            } 
	            
	            record.setDiscountAmount(discountAmount);
	        }
			
			record.setDetailId(objDetail.getDetailId());
			record.setOrderId(mainOrderId);
			orderDetailMapper.updateByPrimaryKey(record);
		}
		//修改订单价格
		orderInfoMapper.updateTotalPrice(mainOrderId);
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
	}
	
	/**
	 * 新增礼金流水
	 * 
	 * @param accountId
	 *            账户标识
	 * @param detailId
	 *            订单明细标识
	 * @param giftAmount
	 *            消耗的资金
	 * @param time
	 *            流水时间
	 * @param projectName
	 *            消费项目/商品
	 * @return 新增记录数
	 */
	@Transactional
	protected int insertGiftMoneyFlow(Integer accountId, Integer detailId, BigDecimal giftAmount, String time,
			  String projectName) {

		List<GiftmoneyDetail> list = giftmoneyDetailMapper.selectByAccountId(accountId);

		BigDecimal operationValue = new BigDecimal(giftAmount.toString());

		// 抵扣礼金情况
		String residueMoneyInfo = null;

		for (GiftmoneyDetail giftmoneyDetail : list) {
			if (giftmoneyDetail.getResidueNowMoney().compareTo(operationValue) == -1
					  || giftmoneyDetail.getResidueNowMoney().compareTo(operationValue) == 0) {
				// 修改礼金明细，当期剩余礼金
				GiftmoneyDetail record = new GiftmoneyDetail();
				record.setDetail(giftmoneyDetail.getDetail());
				record.setResidueNowMoney(BigDecimal.ZERO);
				giftmoneyDetailMapper.updateByPrimaryKeySelective(record);
				operationValue = operationValue.subtract(giftmoneyDetail.getResidueNowMoney());
				if (residueMoneyInfo == null) {
					residueMoneyInfo = giftmoneyDetail.getDetail() + ":" + giftmoneyDetail.getResidueNowMoney();
				} 
				else {
					residueMoneyInfo = residueMoneyInfo + "," + giftmoneyDetail.getDetail() + ":"
							+ giftmoneyDetail.getResidueNowMoney();
				}
				continue;
			} 
			else {
				if (operationValue.compareTo(new BigDecimal(0)) != 0) {
					// 修改礼金明细，当期剩余礼金
					GiftmoneyDetail record = new GiftmoneyDetail();
					record.setDetail(giftmoneyDetail.getDetail());
					record.setResidueNowMoney(giftmoneyDetail.getResidueNowMoney().subtract(operationValue));
					giftmoneyDetailMapper.updateByPrimaryKeySelective(record);

					if (residueMoneyInfo == null) {
						residueMoneyInfo = giftmoneyDetail.getDetail() + ":" + operationValue;
					} 
					else {
						residueMoneyInfo = residueMoneyInfo + "," + giftmoneyDetail.getDetail() + ":" + operationValue;
					}
					break;
				}
			}
		}

		// 新增资金流水
		GiftmoneyFlow moneyFlow = new GiftmoneyFlow();
		moneyFlow.setAccountId(accountId);
		moneyFlow.setDetailId(detailId);
		moneyFlow.setFlowType(1);
		moneyFlow.setBusinessType("消费抵扣");
		moneyFlow.setBusinessDesc(projectName + " 消费抵扣");
		moneyFlow.setResidueMoneyInfo(residueMoneyInfo);
		moneyFlow.setFlowAmount(giftAmount);
		moneyFlow.setFlowTime(time);
		moneyFlow.setIsDeleted(0);
		return giftmoneyFlowMapper.insert(moneyFlow);
	}

	/**
	 * 修改员工提成
	* @author 老王
	* @date 2016年7月12日 下午8:31:49 
	* @param commissionSaveListStr 员工提成信息
	* @return BaseDto
	 */
	public BaseDto saveUpdateCommission (String commissionSaveListStr) {
		JSONArray commissionArray = JSONArray.fromObject(commissionSaveListStr);
		for (int i = 0; i < commissionArray.size(); i++) {
			JSONObject commissionJson = (JSONObject) commissionArray.get(i);
			Integer commissionId = commissionJson.getInt("commissionId");
			BigDecimal commissionCalculate = new BigDecimal(commissionJson.get("commissionCalculate").toString());
			BigDecimal commissionAmount = new BigDecimal(commissionJson.get("commissionAmount").toString());
			EmployeeCommission employeeCommission = new EmployeeCommission();
			employeeCommission.setCommissionId(commissionId);
			employeeCommission.setCommissionCalculate(commissionCalculate);
			employeeCommission.setCommissionAmount(commissionAmount);
			
			employeeCommissionMapper.updateByPrimaryKey(employeeCommission);
		}
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
	}
	
	/**
	 * 新增资金流水
	 * 
	 * @param storeId
	 *            门店标识
	 * @param accountId
	 *            账户标识
	 * @param orderId
	 *            订单标识
	 * @param flowAmount
	 *            消费资金
	 * @param balance
	 *            资金余额
	 * @param time
	 *            流水时间
	 * @param operatorId
	 *            操作人标识
	 * @param levelId 会员等级
	 * @return 新增记录数
	 */
	@Transactional
	protected int insertMoneyFlow(Integer storeId, Integer accountId, Integer orderId, BigDecimal flowAmount,
			  BigDecimal balance, String time, int operatorId, Integer levelId) {
		// 新增资金流水
		MoneyFlow moneyFlow = new MoneyFlow();
		moneyFlow.setAccountId(accountId);
		moneyFlow.setFlowType(1);
		moneyFlow.setBusinessType(1);
		moneyFlow.setLevelId(levelId);
		moneyFlow.setFlowAmount(flowAmount);
		moneyFlow.setBalanceAmount(balance);
		moneyFlow.setFlowTime(time);
		moneyFlow.setStoreId(storeId);
		moneyFlow.setOrderId(orderId);
		moneyFlow.setIsDeleted(0);
		moneyFlow.setLastOperatorId(operatorId);
		return moneyFlowMapper.insert(moneyFlow);
	}

	/**
	 * 更新礼金账户
	 * 
	 * @author 张进军
	 * @date Nov 11, 2015 10:05:16 PM
	 * @param memberId
	 *            会员标识
	 * @param usedGiftmoney
	 *            使用礼金金额
	 * @throws ServiceException
	 *             如果礼金余额小于使用礼金金额，抛出异常
	 */
	@Transactional
	protected void updateMemberGiftMoney(Integer memberId, BigDecimal usedGiftmoney) throws ServiceException {
		Map<String, Object> giftParams = new HashMap<String, Object>(5);
		giftParams.put("accountId", memberId);
		giftParams.put("usedGiftmoney", usedGiftmoney);
		int count = memberAccountMapper.updateMemberGiftMoney(giftParams);
		if (count == 0) {
			throw new ServiceException(-1, "对不起，礼金账户余额不足");
		}
	}

	/**
	 * 计算订单支付时的可用优惠(会员)
	 * 
	 * @author 张进军
	 * @date Nov 10, 2015 3:04:29 PM
	 * @param orderInfo
	 *            订单信息
	 *            @param storeId 门店标识
	 * @return 注入优惠的订单信息
	 */
	private SelfCashierOrderDto calculatePaymentOff(SelfCashierOrderDto orderInfo, Integer storeId) {
		int memberId = orderInfo.getMemberId();
		MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId, false);
		orderInfo.setMemberInfo(memberInfo);

		BigDecimal giftmoney = memberInfo.getGiftmoneyAmount();

		// 存储所有的优惠项
		Set<PaymentOffDto> set = new HashSet<PaymentOffDto>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("memberId", memberId);
		// 遍历每个具体消费项目
		for (SelfCashierDetailDto detail : orderInfo.getOrderDetails()) {
			List<PaymentOffDto> paymentOffList = new ArrayList<PaymentOffDto>();
			// 疗程无任何优惠，直接过滤
			if (detail.getOrderType() == 3) {
				detail.setRealPrice(detail.getProjectPrice());
				continue;
			}
			// 如果是项目，先查询疗程抵扣
			else if (detail.getOrderType() == 1) {
				map.put("projectId", detail.getProjectId());
				paymentOffList = memberComboProjectMapper.selectPaymentOffListByMemberIdAndProjectId(map);
			}

			// 检查是否可以使用礼金抵扣
			if (detail.getIsGiftCash() == 1 && giftmoney.compareTo(BigDecimal.ZERO) == 1
					  && detail.getHighestDiscount().compareTo(BigDecimal.ZERO) == 1) {
				// 项目原价减掉礼金之后还需要支付超过会员折扣价格的卡金，则不优先使用礼金抵扣。
				PaymentOffDto giftPaymentOff = new PaymentOffDto();
				giftPaymentOff.setId(String.valueOf(memberId));
				giftPaymentOff.setType(4);
				giftPaymentOff.setName("礼金抵扣");
				BigDecimal amount = giftmoney;
				if (giftmoney.compareTo(detail.getHighestDiscount()) == 1) {
					amount = detail.getHighestDiscount();
				}
				giftPaymentOff.setAmount(amount);
				giftPaymentOff.setBalance(giftmoney);
				paymentOffList.add(giftPaymentOff);
			}

			// 检查是否有优惠券可以抵扣
			map.put("couponType", detail.getOrderType());
			map.put("projectId", detail.getProjectId());
			List<PaymentOffDto> couponList = new ArrayList<>(); /*memberCouponMapper.selectPaymentCouponListByMemberIdAndProjectId(map);*/
			if (!couponList.isEmpty()) {
				paymentOffList.addAll(couponList);
			}

			// 全部放入该项目优惠列表中
			detail.setPaymentOffList(paymentOffList);
			set.addAll(paymentOffList);
		}

		// 组装所有优惠项的可用次数/余额
		Map<String, Object> allOffMap = new HashMap<String, Object>();
		for (PaymentOffDto off : set) {
			allOffMap.put(off.getType() + "_" + off.getId(), off.getBalance());
		}
		orderInfo.setAllOffMap(allOffMap);

		// 查询所有会员子账户信息
		Map<String, Integer> selectMap = new HashMap<>();
        selectMap.put("accountId", memberId);
        selectMap.put("storeId", storeId);
		List<MemberSubAccountDto> subAccountList = memberSubAccountMapper.selectSubAccountListByAccountId(selectMap);
		orderInfo.setSubAccountList(subAccountList);
		if (subAccountList.size() > 0) {
			orderInfo.setSubAccountList(subAccountList);
			Map<String, BigDecimal> discountMap = new HashMap<>();
			// 遍历所有子账户，计算每个等级的折扣价格
			for (MemberSubAccountDto subAccount : subAccountList) {
				int levelId = subAccount.getLevelId();
				for (SelfCashierDetailDto detail : orderInfo.getOrderDetails()) {
					BigDecimal price = null;
					if (detail.getOrderType() == 1) {
						price = projectService.getProjectPriceByMember(levelId, detail.getProjectId(),
								detail.getProjectPrice(), storeId);
					} 
					else if (detail.getOrderType() == 2) {
						price = goodsInfoService.getGoodsPriceByMember(levelId, detail.getProjectId(),
								detail.getProjectPrice(), storeId);
					} 
					else {
						price = detail.getProjectPrice();
					}
					discountMap.put(detail.getDetailId() + "_" + subAccount.getSubAccountId(), price);
				}
			}
			orderInfo.setDiscountMap(discountMap);
		}

		return orderInfo;
	}
}
