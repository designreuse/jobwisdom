package com.zefun.web.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.MemberLevelDto;
import com.zefun.web.dto.MemberSubAccountDto;
import com.zefun.web.dto.MoneyFlowDto;
import com.zefun.web.entity.CouponInfo;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.DeptObjective;
import com.zefun.web.entity.EmployeeCommission;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.EmployeeObjective;
import com.zefun.web.entity.GiftmoneyDetail;
import com.zefun.web.entity.GiftmoneyFlow;
import com.zefun.web.entity.IntegralFlow;
import com.zefun.web.entity.MemberAccount;
import com.zefun.web.entity.MemberCoupon;
import com.zefun.web.entity.MemberInfo;
import com.zefun.web.entity.MemberPresentRecord;
import com.zefun.web.entity.MemberSubAccount;
import com.zefun.web.entity.MoneyFlow;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.OrderInfo;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.mapper.CouponInfoMapper;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.DeptObjectiveMapper;
import com.zefun.web.mapper.EmployeeCommissionMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.EmployeeObjectiveMapper;
import com.zefun.web.mapper.GiftmoneyDetailMapper;
import com.zefun.web.mapper.GiftmoneyFlowMapper;
import com.zefun.web.mapper.IntegralFlowMapper;
import com.zefun.web.mapper.MemberAccountMapper;
import com.zefun.web.mapper.MemberCouponMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.MemberPresentRecordMapper;
import com.zefun.web.mapper.MemberSubAccountMapper;
import com.zefun.web.mapper.MoneyFlowMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.OrderInfoMapper;
import com.zefun.web.mapper.ProjectInfoMapper;
import com.zefun.wechat.service.StaffService;

import net.sf.json.JSONArray;

/**
 * 开支记账Service
 * 
 * @author 王大爷
 * @date 2015年8月11日 上午11:24:41
 */
@Service
public class OpenCardService {

	/** 会员信息服务对象 */
	@Autowired
	private MemberInfoService memberInfoService;

	/** 会员信息 */
	@Autowired
	private MemberInfoMapper memberInfoMapper;

	/** 会员等级 */
	@Autowired
	private MemberLevelMapper memberLevelMapper;

	/** 员工信息 */
	@Autowired
	private EmployeeInfoMapper employeeInfoMapper;

	/** 会员账户表 */
	@Autowired
	private MemberAccountMapper memberAccountMapper;

	/** 部门 */
	@Autowired
	private DeptInfoMapper deptInfoMapper;

	/** 员工开卡充值提成 */
	@Autowired
	private EmployeeCommissionMapper employeeCommissionMapper;

	/** 礼金明细 */
	@Autowired
	private GiftmoneyDetailMapper giftmoneyDetailMapper;

	/** 礼金流水 */
	@Autowired
	private GiftmoneyFlowMapper giftmoneyFlowMapper;

	/** 资金流水 */
	@Autowired
	private MoneyFlowMapper moneyFlowMapper;

	/** 订单信息 */
	@Autowired
	private OrderInfoMapper orderInfoMapper;

	/** 订单明细 */
	@Autowired
	private OrderDetailMapper orderDetailMapper;

	/** redis缓存操作对象 */
	@Autowired
	private RedisService redisService;
	/** 项目信息 */
	@Autowired
	private ProjectInfoMapper projectInfoMapper;
	/** 项目信息服务 */
	@Autowired
	private ProjectService projectService;
	/** 商品信息服务对象 */
	@Autowired
	private GoodsInfoService goodsInfoService;
	/** 员工业绩目标表 */
	@Autowired
	private EmployeeObjectiveMapper employeeObjectiveMapper;

	/** 优惠券信息操作对象 */
	@Autowired
	private CouponInfoMapper couponInfoMapper;

	/** 会员优惠券操作对象 */
	@Autowired
	private MemberCouponMapper memberCouponMapper;

	/** 积分流水操作对象 */
	@Autowired
	private IntegralFlowMapper integralFlowMapper;

	/** 会员赠送记录操作对象 */
	@Autowired
	private MemberPresentRecordMapper memberPresentRecordMapper;

	/** staffService */
	@Autowired
	private StaffService staffService;

	/** 会员子账户操作类 */
	@Autowired
	private MemberSubAccountMapper memberSubAccountMapper;

	/** 部门业绩分布 */
	@Autowired
	private DeptObjectiveMapper deptObjectiveMapper;
	
	/** 队列信息服务对象 */
	@Autowired
	private RabbitService rabbitService;

	/**
	 * 初始化开卡充值页面
	 * @author 王大爷
	 * @date 2015年9月8日 下午8:49:03
	 * @param storeId 门店标识
	 * @param phoneNum 会员手机号
	 * @param clickType 开卡充值类型
	 * @return     开卡充值页面
	 */
	public ModelAndView initializeOpenCard(Integer storeId, String phoneNum, Integer clickType) {
		ModelAndView mav = new ModelAndView(View.KeepAccounts.OPEN_CARD);
		List<MemberLevelDto> memberLevelList = memberLevelMapper.selectByStoreId(storeId);
		List<EmployeeInfo> employeeInfoList = employeeInfoMapper.selectEmployeeList(storeId);
		List<DeptInfo> deptInfoList = deptInfoMapper.getResultsDeptInfo(storeId);
		mav.addObject("memberLevelList", memberLevelList);
		if (memberLevelList.size() > 0) {
			MemberLevelDto memberLevel = memberLevelList.get(0);
			mav.addObject("memberLevels", memberLevel);
		}
		mav.addObject("memberLevelListStr", JSONArray.fromObject(memberLevelList).toString());
		mav.addObject("employeeInfoList", employeeInfoList);
		mav.addObject("deptInfoList", deptInfoList);

		List<CouponInfo> couponList = couponInfoMapper.selectCouponListByStoreId(storeId);
		mav.addObject("couponList", couponList);
		mav.addObject("phoneNum", phoneNum);
		mav.addObject("clickType", clickType);
		return mav;
	}

	/**
	 * 开卡
	 * @author 王大爷
	 * @date 2015年9月9日 下午3:53:08
	 * @param memberId 会员标识
	 * @param phone 手机号
	 * @param name 姓名
	 * @param sex 性别
	 * @param levelId 会员级别
	 * @param amountvalue 开卡金额
	 * @param recommendId 开卡员工
	 * @param commissionAmount 员工提成金额
	 * @param calculateAmount 员工业绩金额
	 * @param giftmoneyAmount 礼金金额
	 * @param pastDate 过期天数
	 * @param partType 分期方式
	 * @param balanceAmount 储值金额
	 * @param rewardAmount 卡金
	 * @param messageType 是否接收短信（0：否、1、是）
	 * @param cashAmount 现金支付
	 * @param unionpayAmount 银联支付
	 * @param wechatAmount 微信支付
	 * @param alipayAmount 支付宝支付
	 * @param debtAmount 挂账金额
	 * @param payPassword 支付密码
	 * @param deptIds 业绩部门标识集合
	 * @param deptCalculates   部门业绩值集合
	 * @param storeId 门店标识
	 * @param lastOperatorId 操作人
	 * @param openRecommendId 推荐人
	 * @return BaseDto
	 * @throws ParseException  解析异常
	 */
	@Transactional
	public BaseDto addMemberInfo(Integer memberId, String phone, String name, String sex, Integer levelId,
    			BigDecimal amountvalue, List<Integer> recommendId, List<BigDecimal> commissionAmount,
    			List<BigDecimal> calculateAmount, BigDecimal giftmoneyAmount, Integer pastDate, Integer partType,
    			BigDecimal balanceAmount, BigDecimal rewardAmount, Integer messageType, BigDecimal cashAmount,
    			BigDecimal unionpayAmount, BigDecimal wechatAmount, BigDecimal alipayAmount, BigDecimal debtAmount,
    			String payPassword, List<Integer> deptIds, List<BigDecimal> deptCalculates, Integer openRecommendId,
    			Integer storeId, Integer lastOperatorId) throws ParseException {

	    if (memberId != null) {
	        //校验是否存在同样的卡，存在则返回错误代码
	    	Map<String, Integer> map = new HashMap<>();
        	map.put("accountId", memberId);
        	map.put("storeId", storeId);
	        List<MemberSubAccountDto> subAccountList = memberSubAccountMapper.selectSubAccountListByAccountId(map);
	        for (MemberSubAccountDto subAccount : subAccountList) {
	            if (subAccount.getLevelId().intValue() == levelId.intValue()) {
	                return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "你已经存在一张(" + subAccount.getLevelName() + ")卡");
	            }
	        }
	    }
	    
		// 储值总金额
		BigDecimal receivableAmount = balanceAmount.add(rewardAmount);

		if (balanceAmount.compareTo(amountvalue) == 1) {
			receivableAmount = receivableAmount.subtract(amountvalue);
			balanceAmount = balanceAmount.subtract(amountvalue);
		} 
		else {
			receivableAmount = rewardAmount;
			balanceAmount = new BigDecimal(0);
		}

		Map<String, Integer> accountMap = insertMemberBastInfo(memberId, storeId, levelId, name, sex, phone, messageType, lastOperatorId,
				    payPassword, receivableAmount, rewardAmount, openRecommendId);
		
		memberId = accountMap.get("memberId");
		int subAccountId = accountMap.get("subAccountId");

		commissionAndGift(memberId, subAccountId, recommendId, commissionAmount, calculateAmount, giftmoneyAmount, balanceAmount,
    				cashAmount, unionpayAmount, wechatAmount, alipayAmount, debtAmount, pastDate, partType, 4, storeId,
    				rewardAmount, deptIds, deptCalculates, lastOperatorId);
		
		/*memberInfoService.syncLevelId(memberId);*/

		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
	}

	/**
	 * 保存会员基本信息
	 * @param memberId 会员标识
	 * @param storeId 门店标识
	 * @param levelId 会员级别
	 * @param name 会员名称
	 * @param sex 会员性别
	 * @param phone 手机号码
	 * @param messageType 是否接收短信（0：否、1、是）
	 * @param lastOperatorId 操作人
	 * @param payPassword 密码
	 * @param receivableAmount 实际储值
	 * @param rewardAmount 卡金
	 * @param openRecommendId 推荐人
	 * @return 会员标识
	 */
	public Map<String, Integer> insertMemberBastInfo(Integer memberId, Integer storeId, Integer levelId, String name, String sex,
			  String phone, Integer messageType, Integer lastOperatorId, String payPassword, BigDecimal receivableAmount,
			  BigDecimal rewardAmount, Integer openRecommendId) {
		if (memberId == null) {
			// 添加会员信息表
			MemberInfo memberInfo = new MemberInfo();
			memberInfo.setStoreId(storeId);
			memberInfo.setLevelId(levelId);
			memberInfo.setName(name);
			memberInfo.setSex(sex);
			if (sex == "男") {
				memberInfo.setHeadUrl("system/profile/common_img_man.png");
			}
			else {
				memberInfo.setHeadUrl("system/profile/common_img_gril.png");
			}
			memberInfo.setPhone(phone);
			memberInfo.setMessageType(messageType);
			memberInfo.setCreateTime(DateUtil.getCurTime());
			memberInfo.setIsDeleted(0);
			memberInfo.setLastOperatorId(lastOperatorId);
			memberInfo.setRecommendId(openRecommendId);
			memberInfoMapper.insert(memberInfo);

			// 添加会员账户表
			MemberAccount memberAccount = new MemberAccount();
			memberAccount.setAccountId(memberInfo.getMemberId());
			String hash = StringUtil.encryptPwd(payPassword);
			payPassword = hash.split(":")[0];
			String passwordSalt = hash.split(":")[1];
			memberAccount.setPayPassword(payPassword);
			memberAccount.setPasswordSalt(passwordSalt);
			memberAccount.setTotalAmount(receivableAmount);
			memberAccount.setBalanceAmount(receivableAmount);
			memberAccount.setTotalPresentAmount(rewardAmount);
			memberAccount.setCreateTime(DateUtil.getCurTime());
			memberAccount.setLastOperatorId(lastOperatorId);

			memberAccountMapper.insert(memberAccount);

			memberId = memberInfo.getMemberId();
		} 
		else {
			MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
			// 储值总额
			BigDecimal totalAmount = memberAccount.getTotalAmount().add(receivableAmount);
			// 赠送总额
			BigDecimal totalPresentAmount = memberAccount.getTotalPresentAmount().add(rewardAmount);
			// 储值余额
			BigDecimal balanceAmount = memberAccount.getBalanceAmount().add(receivableAmount);

			// 修改会员总账户
			MemberAccount newNemberAccount = new MemberAccount();
			newNemberAccount.setAccountId(memberId);
			newNemberAccount.setTotalAmount(totalAmount);
			newNemberAccount.setTotalPresentAmount(totalPresentAmount);
			newNemberAccount.setBalanceAmount(balanceAmount);

			memberAccountMapper.updateByPrimaryKey(newNemberAccount);
		}
		
		//检查是否存在一张默认等级的会员卡且无余额，有则直接删除
		List<Integer> noMoneySubAccountList = memberSubAccountMapper.selectNoMoneyAccountId(memberId);
		if (noMoneySubAccountList != null && noMoneySubAccountList.size() > 0) {
		    for (Integer subAccountId : noMoneySubAccountList) {
		        memberSubAccountMapper.deleteByPrimaryKey(subAccountId);
            }
		}

		MemberSubAccount memberSubAccount = new MemberSubAccount();
		memberSubAccount.setAccountId(memberId);
		memberSubAccount.setLevelId(levelId);
		memberSubAccount.setTotalAmount(receivableAmount);
		memberSubAccount.setTotalPresentAmount(rewardAmount);
		memberSubAccount.setBalanceAmount(receivableAmount);
		memberSubAccount.setCreateTime(DateUtil.getCurTime());
		memberSubAccount.setLastOperatorId(lastOperatorId);
		memberSubAccountMapper.insert(memberSubAccount);
		
		Map<String, Integer> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("subAccountId", memberSubAccount.getSubAccountId());

		return map;
	}

	
	/**
	 * 充值
	 * @author 王大爷
	 * @date 2015年9月10日 下午7:12:25
	 * @param subAccountId 会员子账户标识
	 * @param chargeAmount 充值金额
	 * @param cashAmount 现金支付
	 * @param unionpayAmount 银联支付
	 * @param wechatAmount 微信支付
	 * @param alipayAmount 支付宝支付
	 * @param debtAmount 挂账金额
	 * @param recommendId 提成员工标识
	 * @param commissionAmount 提成金额
	 * @param calculateAmount 业绩金额
	 * @param giftmoneyAmount 礼金金额
	 * @param pastDate 过期时间
	 * @param partType 分期方式
	 * @param rewardAmount 卡金
	 * @param deptIds 业绩部门标识集合
	 * @param deptCalculates   部门业绩值集合
	 * @param type 确认情况
	 * @param storeId 门店标识
	 * @param lastOperatorId 操作人
	 * @return BaseDto
	 * @throws ParseException  解析异常
	 */
	@Transactional
	public BaseDto rechargeMemberInfo(Integer subAccountId, BigDecimal chargeAmount, BigDecimal cashAmount,
    			BigDecimal unionpayAmount, BigDecimal wechatAmount, BigDecimal alipayAmount, BigDecimal debtAmount,
    			List<Integer> recommendId, List<BigDecimal> commissionAmount, List<BigDecimal> calculateAmount,
    			BigDecimal giftmoneyAmount, Integer pastDate, Integer partType, BigDecimal rewardAmount,
    			List<Integer> deptIds, List<BigDecimal> deptCalculates, Integer type, Integer storeId,
    			Integer lastOperatorId) throws ParseException {

		MemberSubAccount subAccount = memberSubAccountMapper.selectByPrimaryKey(subAccountId);

		Integer memberId = subAccount.getAccountId();

		Map<String, Integer> map = new HashMap<>();
		map.put("levelId", subAccount.getLevelId());
		map.put("storeId", storeId);
		MemberLevelDto memberLevel = memberLevelMapper.selectByEnterprise(map);

		if (type == 1) {
			if (Float.valueOf(memberLevel.getChargeMinMoney().toString()) > Float.valueOf(chargeAmount.toString())) {
				return new BaseDto(41007, "充值金额小于最低充值金额！");
			}
		}

		// 修改账户信息
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("chargeAmount", chargeAmount.add(rewardAmount));
		hashMap.put("rewardAmount", rewardAmount);
		hashMap.put("accountId", memberId);
		memberAccountMapper.updateCharge(hashMap);

		// 修改子账户信息
		Map<String, Object> subHashMap = new HashMap<String, Object>();
		subHashMap.put("subAccountId", subAccount.getSubAccountId());
		subHashMap.put("chargeAmount", chargeAmount.add(rewardAmount));
		subHashMap.put("rewardAmount", rewardAmount);
		memberSubAccountMapper.updateCharge(subHashMap);

		commissionAndGift(memberId, subAccount.getSubAccountId(), recommendId, commissionAmount, calculateAmount, giftmoneyAmount, chargeAmount,
    				cashAmount, unionpayAmount, wechatAmount, alipayAmount, debtAmount, pastDate, partType, 5, storeId,
    				rewardAmount, deptIds, deptCalculates, lastOperatorId);
		
		/*memberInfoService.syncLevelId(memberId);*/

		// 更新缓存中的会员数据
		memberInfoService.wipeCache(memberId);
		String openId = redisService.hget(App.Redis.WECHAT_MEMBERID_TO_OPENID_KEY_HASH, memberId);
		if (StringUtils.isNotBlank(openId)) {
		    MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId, false);
			String url = App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_HOME.replace("{businessType}", "1");
			rabbitService.sendMemberCharge(storeId, url, openId, memberInfo.getStoreName(), 
			        memberLevel.getLevelName(), chargeAmount.toString(), 
			        memberInfo.getBalanceAmount().add(chargeAmount).toString(), DateUtil.getCurTime());
		}

		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
	}

	/**
	 * 会员还款
	 * @author 王大爷
	 * @date 2016年1月22日 下午5:26:04
	 * @param memberId 会员标识
	 * @param realPrice 还款金额
	 * @param cashAmount 现金
	 * @param unionpayAmount 银联
	 * @param wechatAmount 微信
	 * @param alipayAmount 支付宝
	 * @param storeId 门店标识
	 * @param lastOperatorId 操作人
	 * @return BaseDto
	 * @throws ParseException  解析异常
	 */
	@Transactional
	public BaseDto refundMemberInfo(Integer memberId, BigDecimal realPrice, BigDecimal cashAmount,
    			BigDecimal unionpayAmount, BigDecimal wechatAmount, BigDecimal alipayAmount, Integer storeId,
    			Integer lastOperatorId) throws ParseException {
		MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);

		MemberAccount record = new MemberAccount();
		record.setAccountId(memberId);
		record.setDebtAmount(memberAccount.getDebtAmount().subtract(realPrice));
		memberAccountMapper.updateByPrimaryKey(record);

		List<Integer> recommendId = new ArrayList<Integer>();
		List<BigDecimal> commissionAmount = new ArrayList<BigDecimal>();
		List<BigDecimal> calculateAmount = new ArrayList<BigDecimal>();
		BigDecimal giftmoneyAmount = new BigDecimal(0);

		commissionAndGift(memberId, null, recommendId, commissionAmount, calculateAmount, giftmoneyAmount, realPrice,
    				cashAmount, unionpayAmount, wechatAmount, alipayAmount, new BigDecimal(0), 0, 0, 8, storeId,
    				new BigDecimal(0), null, null, lastOperatorId);

		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
	}
	

	/**
	 * 会员升级
	 * @author 王大爷
	 * @date 2015年10月31日 下午9:28:23
	 * @param memberId 会员标识
	 * @param levelId 升级等级
	 * @param amountvalue 开卡金额
	 * @param recommendId 提成员工
	 * @param commissionAmount 提成金额
	 * @param calculateAmount 业绩金额
	 * @param giftmoneyAmount 礼金金额
	 * @param pastDate 过期天数
	 * @param partType 分期方式
	 * @param cashAmount 现金支付金额
	 * @param unionpayAmount 网银支付金额
	 * @param wechatAmount 微信支付
	 * @param alipayAmount 支付宝支付
	 * @param debtAmount 挂账金额
	 * @param rewardAmount 卡金
	 * @param storeId 门店标识
	 * @param deptIds 业绩部门标识集合
	 * @param deptCalculates   部门业绩值集合           
	 * @param lastOperatorId 操作人员
	 * @return BaseDto
	 * @throws ParseException  解析异常
	 */
	@Transactional
	public BaseDto upgradeMemberInfo(Integer memberId, Integer levelId, BigDecimal amountvalue,
    			List<Integer> recommendId, List<BigDecimal> commissionAmount, List<BigDecimal> calculateAmount,
    			BigDecimal giftmoneyAmount, Integer pastDate, Integer partType, BigDecimal cashAmount,
    			BigDecimal unionpayAmount, BigDecimal wechatAmount, BigDecimal alipayAmount, BigDecimal debtAmount,
    			BigDecimal rewardAmount, Integer storeId, List<Integer> deptIds, List<BigDecimal> deptCalculates,
    			Integer lastOperatorId) throws ParseException {
		// 添加会员信息表
		MemberInfo memberInfo = new MemberInfo();
		memberInfo.setLevelId(levelId);
		memberInfo.setUpdateTime(DateUtil.getCurTime());
		memberInfo.setMemberId(memberId);
		memberInfo.setLastOperatorId(lastOperatorId);

		memberInfoMapper.updateByPrimaryKey(memberInfo);

		BigDecimal balanceAmount = unionpayAmount.add(cashAmount).add(debtAmount).add(wechatAmount).add(alipayAmount);

		BigDecimal receivableAmount = balanceAmount.add(rewardAmount);
		// 如果是充值升级
		Map<String, Object> hashMap = new HashMap<String, Object>();

		if (balanceAmount.compareTo(amountvalue) == 1) {
			receivableAmount = receivableAmount.subtract(amountvalue);
			balanceAmount = balanceAmount.subtract(amountvalue);
		} 
		else {
			receivableAmount = rewardAmount;
			balanceAmount = new BigDecimal(0);
		}

		hashMap.put("chargeAmount", receivableAmount);

		hashMap.put("rewardAmount", rewardAmount);
		hashMap.put("accountId", memberInfo.getMemberId());
		memberAccountMapper.updateCharge(hashMap);

		commissionAndGift(memberId, null, recommendId, commissionAmount, calculateAmount, giftmoneyAmount, balanceAmount,
    				cashAmount, unionpayAmount, wechatAmount, alipayAmount, debtAmount, pastDate, partType, 6, storeId,
    				rewardAmount, deptIds, deptCalculates, lastOperatorId);
		changeMemberOrder(memberId, storeId);
		// 更新缓存中的会员数据
		memberInfoService.wipeCache(memberId);
		
		/*memberInfoService.syncLevelId(memberId);*/
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
	}

	/**
	 * 会员升级或散客转会员时调用
	 * @author 王大爷
	 * @date 2015年11月27日 下午4:52:18
	 * @param memberId 会员标识
	 * @param storeId 门店标识
	 */
	public void changeMemberOrder(Integer memberId, Integer storeId) {
		// 如果有未结账的订单，将其金额修改
		List<OrderInfo> orderInfoList = orderInfoMapper.selectIsNotOver(memberId);
		MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(memberId);
		for (int i = 0; i < orderInfoList.size(); i++) {
			List<OrderDetail> orderDetailList = orderDetailMapper.selectOrderId(orderInfoList.get(i).getOrderId());
			for (int j = 0; j < orderDetailList.size(); j++) {
				OrderDetail orderDetail = orderDetailList.get(j);
				OrderDetail updayeDetail = new OrderDetail();
				updayeDetail.setDetailId(orderDetail.getDetailId());
				BigDecimal discountAmount = orderDetail.getDiscountAmount();

				if (orderDetail.getOrderType() == 1) {
					discountAmount = projectService.getProjectPriceByMember(memberInfo.getLevelId(),
							orderDetail.getProjectId(), orderDetail.getProjectPrice());

					// 扣除预约优惠
					if (orderDetail.getIsAppoint() == 1) {
						ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(orderDetail.getProjectId());
						discountAmount.subtract(projectInfo.getAppointmentPrice());
					}
				} 
				else if (orderDetail.getOrderType() == 2) {
					discountAmount = goodsInfoService.getGoodsPriceByMember(memberInfo.getLevelId(),
							orderDetail.getProjectId(), orderDetail.getProjectPrice(), storeId);
				}

				BigDecimal zore = new BigDecimal(0);
				if (discountAmount.compareTo(zore) == -1) {
					discountAmount = zore;
				}
				updayeDetail.setDiscountAmount(new BigDecimal(discountAmount.floatValue()));
				orderDetailMapper.updateByPrimaryKey(updayeDetail);
			}
			// 汇总订单金额
			orderInfoMapper.updateTotalPrice(orderInfoList.get(i).getOrderId());
		}
	}

	/**
	 * 新增员工提成及礼金
	 * @author 王大爷
	 * @date 2015年9月10日 下午7:32:42
	 * @param memberId 会员标识
	 * @param subAccountId 子账户标识
	 * @param recommendId 提成员工标识
	 * @param commissionAmount 提成金额
	 * @param calculateAmount 员工业绩金额
	 * @param giftmoneyAmount 礼金金额
	 * @param receivableAmount 充值金额
	 * @param cashAmount 现金支付
	 * @param unionpayAmount 银联支付
	 * @param wechatAmount 微信支付
	 * @param alipayAmount 支付宝支付
	 * @param debtAmount 挂账金额
	 * @param pastDate 过期天数
	 * @param partType 分期方式
	 * @param type 类型（4:开卡提成,5:充值提成,6:升级提成,7:还款）
	 * @param storeId 门店标识
	 * @param rewardAmount 流水标识
	 * @param deptIds 业绩部门标识集合
	 * @param deptCalculates   部门业绩值集合
	 * @param lastOperatorId 操作人
	 * @throws ParseException  解析异常
	 */
	@Transactional
	private void commissionAndGift(Integer memberId, Integer subAccountId, List<Integer> recommendId, List<BigDecimal> commissionAmount,
    			List<BigDecimal> calculateAmount, BigDecimal giftmoneyAmount, BigDecimal receivableAmount,
    			BigDecimal cashAmount, BigDecimal unionpayAmount, BigDecimal wechatAmount, BigDecimal alipayAmount,
    			BigDecimal debtAmount, Integer pastDate, Integer partType, Integer type, Integer storeId,
    			BigDecimal rewardAmount, List<Integer> deptIds, List<BigDecimal> deptCalculates, Integer lastOperatorId)
			throws ParseException {

		// 添加订单信息
		OrderInfo orderInfo = new OrderInfo();
		String businessDesc = "";

		Integer calculateType = 0;
		if (type == 4) {
			businessDesc = "开卡";
			orderInfo.setOrderType(2);
			calculateType = 1;
		} 
		else if (type == 5) {
			businessDesc = "充值";
			orderInfo.setOrderType(2);
			calculateType = 2;
		} 
		else if (type == 6) {
			businessDesc = "升级";
			orderInfo.setOrderType(2);
			calculateType = 3;
		}
		else {
			businessDesc = "还款";
			orderInfo.setOrderType(3);
		}
		String orderCode = staffService.getOrderCode("order_info", storeId);
		orderInfo.setOrderCode(orderCode);
		orderInfo.setMemberId(memberId);
		orderInfo.setReceivableAmount(receivableAmount);
		orderInfo.setDiscountAmount(receivableAmount);
		orderInfo.setCashAmount(cashAmount);
		orderInfo.setUnionpayAmount(unionpayAmount);
		orderInfo.setWechatAmount(wechatAmount);
		orderInfo.setAlipayAmount(alipayAmount);
		orderInfo.setRealAmount(cashAmount.add(unionpayAmount).add(wechatAmount).add(alipayAmount).add(debtAmount));
		orderInfo.setDebtAmount(debtAmount);
		orderInfo.setOrderStatus(4);
		orderInfo.setStoreId(storeId);
		orderInfo.setIsDeleted(0);
		orderInfo.setCreateTime(DateUtil.getCurTime());
		orderInfo.setLastOperatorId(lastOperatorId);
		orderInfoMapper.insert(orderInfo);

		if (calculateType > 0) {
			addDeptObjective(orderInfo.getOrderId(), calculateType, deptIds, deptCalculates);
		}
		
		//查询操作员工的部门标识
		EmployeeInfo employeeInfo = employeeInfoMapper.selectByPrimaryKey(lastOperatorId);
		int calculateDeptId = 0;
		if (employeeInfo != null) {
		    calculateDeptId = employeeInfo.getDeptId();
		}

		// 添加订单明细
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderId(orderInfo.getOrderId());
		orderDetail.setDeptId(calculateDeptId);
		orderDetail.setProjectName(businessDesc);
		orderDetail.setOrderType(type);
		orderDetail.setProjectPrice(cashAmount.add(unionpayAmount).add(wechatAmount).add(alipayAmount).add(debtAmount));
		orderDetail.setDiscountAmount(cashAmount.add(unionpayAmount).add(wechatAmount).add(alipayAmount).add(debtAmount));
		orderDetail.setRealPrice(cashAmount.add(unionpayAmount).add(wechatAmount).add(alipayAmount).add(debtAmount));
		orderDetail.setProjectCount(1);
		orderDetail.setStoreId(storeId);
		orderDetail.setCreateTime(DateUtil.getCurTime());
		orderDetail.setLastOperatorId(lastOperatorId);
		orderDetailMapper.insert(orderDetail);

		// 如果赠送卡金，需要额外增加一笔明细
		if (rewardAmount.compareTo(BigDecimal.ZERO) == 1) {
			OrderDetail rewarDetail = new OrderDetail();
			rewarDetail.setOrderId(orderInfo.getOrderId());
			rewarDetail.setDeptId(deptIds.get(0));
			rewarDetail.setProjectName(businessDesc + "赠送");
			rewarDetail.setOrderType(7);
			rewarDetail.setProjectPrice(rewardAmount);
			rewarDetail.setDiscountAmount(rewardAmount);
			rewarDetail.setRealPrice(BigDecimal.ZERO);
			rewarDetail.setProjectCount(1);
			rewarDetail.setStoreId(storeId);
			rewarDetail.setCreateTime(DateUtil.getCurTime());
			rewarDetail.setLastOperatorId(lastOperatorId);
			orderDetailMapper.insert(rewarDetail);
		}

		Integer detailId = orderDetail.getDetailId();

		// 还款不生成流水
		if (receivableAmount.compareTo(BigDecimal.ZERO) == 1 && type != 8) {
			// 新增资金流水
			MoneyFlow moneyFlow = new MoneyFlow();
			moneyFlow.setAccountId(subAccountId);
			moneyFlow.setFlowType(2);
			if (type == 4) {
				moneyFlow.setBusinessType(4);
			} 
			else if (type == 5) {
				moneyFlow.setBusinessType(2);
			} 
			else {
				moneyFlow.setBusinessType(5);
			}
			moneyFlow.setBusinessDesc(businessDesc);
			moneyFlow.setOrderId(orderInfo.getOrderId());
			moneyFlow.setFlowAmount(receivableAmount);
			moneyFlow.setFlowTime(DateUtil.getCurTime());
			moneyFlow.setStoreId(storeId);
			moneyFlow.setIsDeleted(0);
			moneyFlow.setLastOperatorId(lastOperatorId);
			moneyFlowMapper.insert(moneyFlow);
		}

		if (rewardAmount.compareTo(BigDecimal.ZERO) == 1) {
			// 新增资金流水
			MoneyFlow moneyFlow = new MoneyFlow();
			moneyFlow.setAccountId(subAccountId);
			moneyFlow.setFlowType(2);
			moneyFlow.setBusinessType(6);
			moneyFlow.setOrderId(orderInfo.getOrderId());
			moneyFlow.setFlowAmount(rewardAmount);
			moneyFlow.setFlowTime(DateUtil.getCurTime());
			moneyFlow.setStoreId(storeId);
			moneyFlow.setIsDeleted(0);
			moneyFlow.setLastOperatorId(lastOperatorId);
			moneyFlow.setBusinessDesc(businessDesc + "赠送");
			moneyFlowMapper.insert(moneyFlow);
		}

		if (type == 8) {
			// 增加还款记录
			memberInfoService.insertDebtFlow(memberId, orderInfo.getOrderId(), receivableAmount, businessDesc, 2,
					    lastOperatorId, DateUtil.getCurTime());
		}

		if (debtAmount.compareTo(BigDecimal.ZERO) == 1) {
			// 增加挂帐记录
			memberInfoService.insertDebtFlow(memberId, orderInfo.getOrderId(), debtAmount, businessDesc + "欠款", 1,
					    lastOperatorId, DateUtil.getCurTime());

			MemberAccount orderSubmit = memberAccountMapper.selectByPrimaryKey(memberId);

			MemberAccount memberAccount = new MemberAccount();
			memberAccount.setAccountId(memberId);
			memberAccount.setDebtAmount(debtAmount.add(orderSubmit.getDebtAmount()));
			memberAccount.setUpdateTime(DateUtil.getCurTime());
			memberAccountMapper.updateByPrimaryKey(memberAccount);
		}

		// 添加员工开卡充值提成表
		if (recommendId.size() > 0) {
			for (int i = 0; i < recommendId.size(); i++) {
				EmployeeCommission employeeCommission = new EmployeeCommission();
				employeeCommission.setDetailId(detailId);
				employeeCommission.setOrderType(type);
				employeeCommission.setEmployeeId(recommendId.get(i));
				employeeCommission.setCommissionAmount(commissionAmount.get(i));
				employeeCommission.setCommissionCalculate(calculateAmount.get(i));
				employeeCommission.setChargeTime(DateUtil.getCurTime());
				employeeCommissionMapper.insert(employeeCommission);

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");

				EmployeeObjective employeeObjective = new EmployeeObjective();
				employeeObjective.setActualChargeSales(calculateAmount.get(i));
				employeeObjective.setEmployeeId(recommendId.get(i));
				employeeObjective.setObjectiveMonth(dateFormat.format(new Date()));
				employeeObjectiveMapper.updateActual(employeeObjective);
			}
		}

		// 添加礼金
		if (giftmoneyAmount.compareTo(BigDecimal.ZERO) == 1) {
			addGiftmoney(memberId, partType, giftmoneyAmount, pastDate, businessDesc + "赠送", businessDesc + "赠送",
					    lastOperatorId, detailId);
		}
	}

	
	/**
	 * 批量新增部门业绩记录
	* @author 张进军
	* @date Mar 19, 2016 5:21:54 PM
	* @param orderId           订单标识
	* @param calculateType     业绩类型
	* @param deptIds           部门集合标识
	* @param deptCalculates    部门业绩集合
	 */
	public void addDeptObjective(Integer orderId, Integer calculateType, List<Integer> deptIds,
			    List<BigDecimal> deptCalculates) {
		for (int i = 0; i < deptIds.size(); i++) {
			DeptObjective deptObjective = new DeptObjective();
			deptObjective.setOrderId(orderId);
			deptObjective.setCalculateType(calculateType);
			deptObjective.setDeptId(deptIds.get(i));
			deptObjective.setDeptCalculate(deptCalculates.get(i));
			deptObjective.setCreateTime(DateUtil.getCurTime());
			deptObjectiveMapper.insert(deptObjective);
		}
	}

	/**
	 * 校验账户密码及转账
	 * @author 王大爷
	 * @date 2015年9月11日 下午2:23:46
	 * @param outSubAccountId 转出会员标识
	 * @param inSubAccountId 转入会员标识
	 * @param chargeAmount 金额
	 * @param password 密码
	 * @param storeId 门店标识
	 * @param lastOperatorId 操作人员
	 * @return BaseDto
	 */
	@Transactional
	public BaseDto checkoutAccount(Integer outSubAccountId, Integer inSubAccountId, BigDecimal chargeAmount,
			    String password, Integer storeId, Integer lastOperatorId) {

		MemberSubAccount outMemberSubAccount = memberSubAccountMapper.selectByPrimaryKey(outSubAccountId);

		Integer outAccountId = outMemberSubAccount.getAccountId();

		MemberSubAccount inMemberSubAccount = memberSubAccountMapper.selectByPrimaryKey(inSubAccountId);

		Integer inAccountId = inMemberSubAccount.getAccountId();

		MemberAccount outMemberAccount = memberAccountMapper.selectByPrimaryKey(outAccountId);

		// 检查用户密码
		if (!StringUtil.md5(password + outMemberAccount.getPasswordSalt()).equals(outMemberAccount.getPayPassword())) {
			return new BaseDto(41007, "转出账户密码不正确！");
		}

		Map<String, Object> hashAdd = new HashMap<String, Object>();
		hashAdd.put("chargeAmount", chargeAmount);
		hashAdd.put("accountId", inAccountId);

		memberAccountMapper.updateAdd(hashAdd);

		Map<String, Object> hashDecrease = new HashMap<String, Object>();
		hashDecrease.put("chargeAmount", chargeAmount);
		hashDecrease.put("accountId", outAccountId);

		memberAccountMapper.updateDecrease(hashDecrease);

		Map<String, Object> hashSubAdd = new HashMap<String, Object>();
		hashSubAdd.put("chargeAmount", chargeAmount);
		hashSubAdd.put("subAccountId", inSubAccountId);

		memberSubAccountMapper.updateAdd(hashSubAdd);

		Map<String, Object> hashSubDecrease = new HashMap<String, Object>();
		hashSubDecrease.put("chargeAmount", chargeAmount);
		hashSubDecrease.put("subAccountId", outSubAccountId);

		memberSubAccountMapper.updateAdd(hashSubDecrease);

		// 添加账户记录资金流水(转出)
		MoneyFlow outMoneyFlow = new MoneyFlow();
		outMoneyFlow.setAccountId(outSubAccountId);
		outMoneyFlow.setFlowType(1);
		outMoneyFlow.setBusinessType(3);
		outMoneyFlow.setFlowAmount(chargeAmount);
		outMoneyFlow
				    .setBalanceAmount(new BigDecimal(Float.parseFloat(String.valueOf(outMemberAccount.getBalanceAmount()))
						- Float.parseFloat(String.valueOf(chargeAmount))));
		outMoneyFlow.setOrderId(inSubAccountId);
		outMoneyFlow.setFlowTime(DateUtil.getCurTime());
		outMoneyFlow.setStoreId(storeId);
		outMoneyFlow.setIsDeleted(0);
		outMoneyFlow.setLastOperatorId(lastOperatorId);
		moneyFlowMapper.insert(outMoneyFlow);

		// 添加账户记录资金流水(转入)
		MoneyFlow inMoneyFlow = new MoneyFlow();
		inMoneyFlow.setAccountId(inSubAccountId);
		inMoneyFlow.setFlowType(2);
		inMoneyFlow.setBusinessType(3);
		inMoneyFlow.setFlowAmount(chargeAmount);
		inMoneyFlow
				    .setBalanceAmount(new BigDecimal(Float.parseFloat(String.valueOf(outMemberAccount.getBalanceAmount()))
						+ Float.parseFloat(String.valueOf(chargeAmount))));
		inMoneyFlow.setOrderId(outSubAccountId);
		inMoneyFlow.setFlowTime(DateUtil.getCurTime());
		inMoneyFlow.setStoreId(storeId);
		inMoneyFlow.setIsDeleted(0);
		inMoneyFlow.setLastOperatorId(lastOperatorId);
		moneyFlowMapper.insert(inMoneyFlow);

		// 更新缓存中的会员数据
		memberInfoService.wipeCache(inAccountId);
		memberInfoService.wipeCache(outAccountId);

		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
	}

	/**
	 * 根据会员标识查询转出记录（最近4条）
	 * 
	 * @author 王大爷
	 * @date 2015年10月10日 下午2:54:04
	 * @param memberId
	 *            会员标识
	 * @return BaseDto
	 */
	public BaseDto selectZcDetail(Integer memberId) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("memberId", memberId);
		map.put("businessType", 3);
		List<MoneyFlowDto> list = moneyFlowMapper.selectDetail(map);
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, list);
	}

	/**
	 * 根据会员标识查询充值记录（最近4条）
	 * 
	 * @author 王大爷
	 * @date 2015年10月10日 下午2:54:04
	 * @param memberId
	 *            会员标识
	 * @return BaseDto
	 */
	public BaseDto selectCzDetail(Integer memberId) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("memberId", memberId);
		map.put("businessType", 2);
		List<MoneyFlowDto> list = moneyFlowMapper.selectDetail(map);
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, list);
	}

	/**
	 * @author 张进军
	 * @date Dec 10, 2015 8:39:00 PM
	 * @param memberId 会员标识
	 * @param giftmoneyAmount 礼金金额
	 * @param part 礼金分期数
	 * @param overdueMonth 礼金过期月份数
	 * @param integralAmount 积分金额
	 * @param coupon 优惠券标识集合，逗号分割
	 * @param comment 备注信息
	 * @param employeeId 操作员工标识
	 * @return 成功返回码为0，失败为其他返回码
	 */
	@Transactional
	public BaseDto presentGift(Integer memberId, Integer giftmoneyAmount, Integer part, Integer overdueMonth,
			    Integer integralAmount, String coupon, String comment, int employeeId) {
		String time = DateUtil.getCurTime();

		// 检查是否赠送礼金
		if (giftmoneyAmount > 0) {
			BigDecimal money = new BigDecimal(giftmoneyAmount);
			addGiftmoney(memberId, part, money, overdueMonth, "门店赠送", comment, employeeId, null);

			MemberPresentRecord memberPresentRecord = new MemberPresentRecord();
			memberPresentRecord.setMemberId(memberId);
			memberPresentRecord.setEmployeeId(employeeId);
			memberPresentRecord.setType(2);
			memberPresentRecord.setGift(giftmoneyAmount);
			memberPresentRecord.setComment(comment);
			memberPresentRecord.setTime(time);
			memberPresentRecordMapper.insert(memberPresentRecord);
		}

		// 检查是否赠送优惠券
		if (StringUtils.isNotBlank(coupon)) {
			String[] couponList = coupon.split(",");
			for (String c : couponList) {
				int couponId = Integer.parseInt(c);
				MemberCoupon memberCoupon = new MemberCoupon();
				memberCoupon.setMemberInfoId(memberId);
				memberCoupon.setCouponId(couponId);
				memberCoupon.setIsUsed(0);
				memberCoupon.setGrantTime(time);
				memberCouponMapper.insert(memberCoupon);

				MemberPresentRecord memberPresentRecord = new MemberPresentRecord();
				memberPresentRecord.setMemberId(memberId);
				memberPresentRecord.setEmployeeId(employeeId);
				memberPresentRecord.setType(3);
				memberPresentRecord.setGift(couponId);
				memberPresentRecord.setComment(comment);
				memberPresentRecord.setTime(time);
				memberPresentRecordMapper.insert(memberPresentRecord);
			}
		}

		MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId, false);
		// 检查是否赠送积分
		if (integralAmount > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("accountId", memberId);
			map.put("memberIntegral", integralAmount);
			map.put("currDate", time);
			memberAccountMapper.updateMemberCashier(map);

			IntegralFlow integralFlow = new IntegralFlow();
			integralFlow.setAccountId(memberId);
			integralFlow.setOrderId(null);
			integralFlow.setFlowAmount(integralAmount);
			integralFlow.setBalanceAmount(memberInfo.getBalanceIntegral());
			integralFlow.setBusinessType("门店赠送");
			integralFlow.setBusinessDesc(comment);
			integralFlow.setFlowType(2);
			integralFlow.setFlowTime(time);
			integralFlowMapper.insert(integralFlow);

			MemberPresentRecord memberPresentRecord = new MemberPresentRecord();
			memberPresentRecord.setMemberId(memberId);
			memberPresentRecord.setEmployeeId(employeeId);
			memberPresentRecord.setType(1);
			memberPresentRecord.setGift(integralAmount);
			memberPresentRecord.setComment(comment);
			memberPresentRecord.setTime(time);
			memberPresentRecordMapper.insert(memberPresentRecord);
		}

		memberInfoService.wipeCache(memberId);
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
	}

	/**
	 * 增加礼金到会员账户
	 * @author 张进军
	 * @date Dec 10, 2015 9:03:14 PM
	 * @param memberId 会员标识
	 * @param part 分期数
	 * @param totalMoney 礼金赠送总金额
	 * @param overdueMonth 过期月份数
	 * @param businessType 业务类型
	 * @param businessDesc 业务描述
	 * @param employeeId 员工标识
	 * @param detailId 订单明细标识，可为空
	 */
	@Transactional
	private void addGiftmoney(int memberId, int part, BigDecimal totalMoney, int overdueMonth, String businessType,
			    String businessDesc, int employeeId, Integer detailId) {
		BigDecimal money = totalMoney.divide(new BigDecimal(part), 2, BigDecimal.ROUND_HALF_DOWN);
		Map<String, Object> hash = new HashMap<String, Object>();
		hash.put("totalGiftmoneyAmount", money);
		hash.put("balanceGiftmoneyAmount", money);
		hash.put("accountId", memberId);
		memberAccountMapper.updateGiftmoney(hash);

		GiftmoneyFlow giftmoneyFlow = new GiftmoneyFlow();
		giftmoneyFlow.setAccountId(memberId);
		giftmoneyFlow.setDetailId(detailId);
		giftmoneyFlow.setFlowType(2);
		giftmoneyFlow.setFlowAmount(money);
		giftmoneyFlow.setFlowTime(DateUtil.getCurTime());
		giftmoneyFlow.setBusinessType(businessType);
		giftmoneyFlow.setBusinessDesc(businessDesc);
		giftmoneyFlow.setIsDeleted(0);
		giftmoneyFlowMapper.insert(giftmoneyFlow);

		String date = DateUtil.getCurDate();
		String time = DateUtil.getCurTime();
		int num = part;
		for (int i = 0; i < num; i++) {
			GiftmoneyDetail giftmoneyDetail = new GiftmoneyDetail();
			giftmoneyDetail.setAccountId(memberId);
			giftmoneyDetail.setTotalAmount(totalMoney);
			giftmoneyDetail.setNowMoney(money);
			giftmoneyDetail.setResidueNowMoney(money);
			giftmoneyDetail.setDetailId(detailId);

			String beginDate = date;
			if (i == 0) {
				giftmoneyDetail.setIsPresent(1);
			} 
			else {
				giftmoneyDetail.setIsPresent(0);
				try {
					beginDate = DateUtil.getDateAfterMonthsStr(date, i);
				} 
				catch (ParseException e1) {
				}
			}
			giftmoneyDetail.setStartDate(beginDate);

			if (overdueMonth == 0) {
				giftmoneyDetail.setEndDate("永久");
			} 
			else {
				try {
					giftmoneyDetail.setEndDate(DateUtil.getDateAfterMonthsStr(beginDate, overdueMonth));
				} 
				catch (ParseException e) {
				}
			}

			giftmoneyDetail.setPartType(part);
			giftmoneyDetail.setPartNumber(num - i - 1);
			giftmoneyDetail.setCreateTime(time);
			giftmoneyDetail.setIsDeleted(0);
			giftmoneyDetail.setLastOperatorId(employeeId);
			giftmoneyDetailMapper.insert(giftmoneyDetail);
		}
		memberInfoService.wipeCache(memberId);
	}
}
