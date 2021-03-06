package com.zefun.wechat.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.HttpClientUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.AppointDateDto;
import com.zefun.web.dto.AppointmentBaseDto;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.CouponBaseDto;
import com.zefun.web.dto.DetailPaymentDto;
import com.zefun.web.dto.EmployeeBaseDto;
import com.zefun.web.dto.EnterpriseInfoDto;
import com.zefun.web.dto.GoodsInfoCatagoryDto;
import com.zefun.web.dto.GoodsInfoDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.MemberComboDto;
import com.zefun.web.dto.MemberOrderDto;
import com.zefun.web.dto.MemberSubAccountDto;
import com.zefun.web.dto.MoneyFlowDto;
import com.zefun.web.dto.OrderDetailDto;
import com.zefun.web.dto.OrderEvaluateDto;
import com.zefun.web.dto.OrderInfoBaseDto;
import com.zefun.web.dto.OrderInfoSubmitDto;
import com.zefun.web.dto.OrderPaymentDto;
import com.zefun.web.dto.ProjectAppointDto;
import com.zefun.web.dto.ProjectBaseDto;
import com.zefun.web.dto.ProjectEvaluateDto;
import com.zefun.web.dto.SelfCashierDetailDto;
import com.zefun.web.dto.SelfCashierOrderDto;
import com.zefun.web.entity.ComboInfo;
import com.zefun.web.entity.CouponInfo;
import com.zefun.web.entity.EmployeeEvaluate;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.EnterpriseInfo;
import com.zefun.web.entity.GiftmoneyFlow;
import com.zefun.web.entity.IntegralFlow;
import com.zefun.web.entity.MemberAccount;
import com.zefun.web.entity.MemberAppointment;
import com.zefun.web.entity.MemberCoupon;
import com.zefun.web.entity.MemberInfo;
import com.zefun.web.entity.MemberRecommend;
import com.zefun.web.entity.MemberSubAccount;
import com.zefun.web.entity.OrderInfo;
import com.zefun.web.entity.PositionInfo;
import com.zefun.web.entity.ProjectCategory;
import com.zefun.web.entity.ProjectEvaluate;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.entity.ProjectShare;
import com.zefun.web.entity.SpecialService;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.entity.StoreSetting;
import com.zefun.web.entity.StoreShop;
import com.zefun.web.entity.StoreWechat;
import com.zefun.web.entity.UserAccount;
import com.zefun.web.entity.WechatMember;
import com.zefun.web.mapper.ComboInfoMapper;
import com.zefun.web.mapper.CouponInfoMapper;
import com.zefun.web.mapper.EmployeeEvaluateMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.EnterpriseInfoMapper;
import com.zefun.web.mapper.GiftmoneyFlowMapper;
import com.zefun.web.mapper.GoodsInfoMapper;
import com.zefun.web.mapper.IntegralFlowMapper;
import com.zefun.web.mapper.MemberAccountMapper;
import com.zefun.web.mapper.MemberAppointmentMapper;
import com.zefun.web.mapper.MemberComboRecordMapper;
import com.zefun.web.mapper.MemberCouponMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.MemberRecommendMapper;
import com.zefun.web.mapper.MemberSubAccountMapper;
import com.zefun.web.mapper.MoneyFlowMapper;
import com.zefun.web.mapper.OrderInfoMapper;
import com.zefun.web.mapper.PositioninfoMapper;
import com.zefun.web.mapper.ProjectCategoryMapper;
import com.zefun.web.mapper.ProjectEvaluateMapper;
import com.zefun.web.mapper.ProjectInfoMapper;
import com.zefun.web.mapper.ProjectShareMapper;
import com.zefun.web.mapper.ShiftMapper;
import com.zefun.web.mapper.SpecialServiceMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.mapper.StoreSettingMapper;
import com.zefun.web.mapper.StoreShopMapper;
import com.zefun.web.mapper.StoreWechatMapper;
import com.zefun.web.mapper.UserAccountMapper;
import com.zefun.web.mapper.WechatMemberMapper;
import com.zefun.web.service.GoodsInfoService;
import com.zefun.web.service.MemberInfoService;
import com.zefun.web.service.ProjectService;
import com.zefun.web.service.QiniuService;
import com.zefun.web.service.RabbitService;
import com.zefun.web.service.RedisService;
import com.zefun.web.service.SelfCashierService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 会员中心业务逻辑类
* @author 张进军
* @date Aug 19, 2015 11:01:22 AM 
*/
@Service
public class MemberCenterService {
    /** 会员信息业务逻辑对象 */
    @Autowired
    private MemberInfoService memberInfoService;
    
    /** 会员信息数据操作对象 */
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    
    /** redis操作类 */
    @Autowired
    private RedisService redisService;
    
    /** 队列操作类 */
    @Autowired
    private RabbitService rabbitService;
    
    /** 微信与会员关联数据操作对象 */
    @Autowired
    private WechatMemberMapper wechatMemberMapper;
    
    /** 会员等级数据操作对象 */
    @Autowired
    private MemberLevelMapper memberLevelMapper;
    
    /** 会员账户数据操作对象 */
    @Autowired
    private MemberAccountMapper memberAccountMapper;
    
    /** 会员预约操作对象 */
    @Autowired
    private MemberAppointmentMapper memberAppointmentMapper;
    
    /**员工班次操作对象*/
    @Autowired
    private ShiftMapper shiftMapper;
    
    /**积分流水操作对象*/
    @Autowired
    private IntegralFlowMapper integralFlowMapper;
    
    /**优惠券操作对象*/
    @Autowired
    private CouponInfoMapper couponInfoMapper;
    
    /**会员优惠券操作对象*/
    @Autowired
    private MemberCouponMapper memberCouponMapper;
    
    /**会员卡金流水操作对象*/
    @Autowired
    private MoneyFlowMapper moneyFlowMapper;
    
    /**会员礼金流水操作对象*/
    @Autowired
    private GiftmoneyFlowMapper giftmoneyFlowMapper;
    
    /**会员疗程记录操作对象*/
    @Autowired
    private MemberComboRecordMapper memberComboRecordMapper;
    
    /**订单信息操作对象*/
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    
    /**店铺信息操作对象*/
    @Autowired
    private StoreInfoMapper storeInfoMapper;
    
    /**项目评价信息操作对象*/
    @Autowired 
    private ProjectEvaluateMapper projectEvaluateMapper;
    
    /** 七牛api操作服务类 */
    @Autowired
    private QiniuService qiniuService;
    
    /** 项目服务操作对象 */
    @Autowired
    private ProjectService projectService;
    
    /** 自助收银操作对象 */
    @Autowired
    private SelfCashierService selfCashierService;
	
	/**员工评价操作对象*/
	@Autowired
	private EmployeeEvaluateMapper employeeEvaluateMapper;
	
	/**员工信息操作对象*/
	@Autowired
	private EmployeeInfoMapper employeeInfoMapper;
	
	/**项目分享信息操作对象*/
	@Autowired
	private ProjectShareMapper projectShareMapper;
	
	/**项目信息操作对象*/
	@Autowired
	private ProjectInfoMapper projectInfoMapper;
	
	/**门店设置操作对象*/
	@Autowired
	private StoreSettingMapper storeSettingMapper;
	
	/**会员推荐关系操作对象*/
	@Autowired
	private MemberRecommendMapper memberRecommendMapper;
	
	/**门店微信关联操作对象*/
	@Autowired
	private StoreWechatMapper storeWechatMapper;
	
	/**员工账户操作对象*/
	@Autowired
	private UserAccountMapper userAccountMapper;
	
	/** 微信api服务对象 */
	@Autowired
	private WeixinMessageService weixinMessageService;
	
	/** 微信员工模块服务对象 */
	@Autowired
	private StaffService staffService;
	
	/** 老板模块中心服务对象 */
    @Autowired
    private BossCenterService bossCenterService;
    
    /** 会员子账户信息操作对象 */
    @Autowired
    private MemberSubAccountMapper memberSubAccountMapper;
	
	/**日志记录对象*/
	private final Logger logger = Logger.getLogger(MemberCenterService.class);
	
	/** 商品列表 */
	@Autowired
	private GoodsInfoService goodsInfoService;
	
	/** 商品列表 */
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    /**  特色服务 */
    @Autowired
    private SpecialServiceMapper specialServiceMapper;
    /** 在线商城 */
    @Autowired
    private StoreShopMapper storeShopMapper;
    /** 企业信息信息*/
    @Autowired
    private EnterpriseInfoMapper enterpriseInfoMapper;
    /** 微信推送信息*/
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /** 门店的项目系列*/
    @Autowired
    private ProjectCategoryMapper projectCategoryMapper;
    /** 岗位信息*/
    @Autowired
    private PositioninfoMapper positioninfoMapper;
    /** 套餐信息*/
    @Autowired
    private ComboInfoMapper comboInfoMapper;
    
    
	/**
	 * wifi主页
	* @author 张进军
	* @date Jan 27, 2016 5:00:51 PM
	* @param storeId   门店标识
	* @param ownerStoreId  用户所属门店标识
	* @param businessType  业务类型(1:会员，2:员工)
	* @param userId    用户标识
	* @return  会员/员工/老板/门店信息页
	 */
	public ModelAndView wifiHome(String storeId, Integer ownerStoreId, String businessType, Integer userId){
	    if ("1".equals(businessType)) {
	        return homeView(userId, ownerStoreId);
	    }
	    else if ("2".equals(businessType)) {
	        //区分普通员工、老板
            UserAccount userAccount = userAccountMapper.selectByPrimaryKey(userId);
            //老板
            if (userAccount.getRoleId() == App.System.SYSTEM_ROLE_STORE_BOSS) {
                return bossCenterService.homeView(ownerStoreId, userId);
            }
            //员工
            else {
                return staffService.receptionView();
            }
	    }
        return storeInfoView(storeId, ownerStoreId);
	}
	
	
    /**
     * 查看会员主页
    * @author 张进军
    * @date Aug 19, 2015 4:21:25 PM
    * @param memberId       会员标识
    * @param storeId        门店标识
    * @return           会员主页面
     */
    public ModelAndView homeView(Integer memberId, int storeId){
        MemberBaseDto memberBaseInfo = memberInfoService.getMemberBaseInfo(memberId, false);
        List<CouponBaseDto> couponList = couponInfoMapper.selectBaseByMemberId(memberId);
        memberBaseInfo.setCouponCount(couponList.size());
        ModelAndView mav = new ModelAndView(View.MemberCenter.HOME);
        mav.addObject("memberBaseInfo", memberBaseInfo);
        mav.addObject("couponList", couponList);
        return mav;
    }
    
    
    /**
     * 会员等级信息页面
    * @author 张进军
    * @date Mar 8, 2016 4:04:03 PM
    * @param memberId        会员标识
    * @param storeId 门店标识
    * @return   会员等级信息页面
     */
    public ModelAndView levelInfoView(int memberId, Integer storeId) {
        ModelAndView mav = new ModelAndView(View.MemberCenter.LEVEL_INFO);
        MemberBaseDto memberBaseInfo = memberInfoService.getMemberBaseInfo(memberId, false);
        Map<String, Integer> selectMap = new HashMap<>();
        selectMap.put("accountId", memberId);
        selectMap.put("storeId", storeId);
        List<MemberSubAccountDto> subAccountList = memberSubAccountMapper.selectSubAccountListByAccountId(selectMap);
        mav.addObject("memberInfo", memberBaseInfo);
        mav.addObject("subAccountList", subAccountList);
        return mav;
    }
    
    
    /**
     * 访问会员注册页面
    * @author 张进军
    * @date Aug 19, 2015 7:08:55 PM
    * @param storeId        注册门店标识
    * @return   会员注册页面
     */
    public ModelAndView registerView(Integer storeId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.REGISTER);
        StoreInfo storeInfo = storeInfoMapper.selectBaseInfoByStoreId(storeId);
        mav.addObject("storeInfo", storeInfo);
        return mav;
    }
    
    
    /**
     * 查看个人资料
    * @author 张进军
    * @date Oct 22, 2015 8:37:07 PM
    * @param memberId   会员标识
    * @return   个人资料页面
     */
    public ModelAndView infoView(Integer memberId) {
        MemberBaseDto memberBaseInfo = memberInfoService.getMemberBaseInfo(memberId, false);
        ModelAndView mav = new ModelAndView(View.MemberCenter.INFO);
        mav.addObject("memberBaseInfo", memberBaseInfo);
        
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        //检查是否有设置支付密码
        if (memberAccount != null && StringUtils.isBlank(memberAccount.getPayPassword())) {
            mav.addObject("isPayCode", 0);
        }
        else {
            mav.addObject("isPayCode", 1);
        }
        
        return mav;
    }
    
    
    /**
     * 修改个人资料
    * @author 张进军
    * @date Oct 23, 2015 10:53:17 AM
    * @param memberId   会员标识    
    * @param name       昵称
    * @param headUrl    头像 
    * @param sex        性别
    * @param birthday   生日
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    public BaseDto updateInfoAction(Integer memberId, String name, String headUrl, String sex, String birthday) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(memberId);
        memberInfo.setName(name);
        memberInfo.setSex(sex);
        memberInfo.setHeadUrl(headUrl);
        memberInfo.setBirthday(birthday);
        memberInfo.setUpdateTime(DateUtil.getCurTime());
        memberInfoMapper.updateByPrimaryKey(memberInfo);
        
        memberInfoService.wipeCache(memberId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, memberInfo);
    }
    
    
    /**
     * 设置支付密码
    * @author 张进军
    * @date Nov 28, 2015 4:09:08 PM
    * @param memberId   会员标准
    * @param pwd        支付密码
    * @return           baseDto
     */
    public BaseDto setPwdAction(Integer memberId, String pwd) { 
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        
        //检查用户是否有设置密码
        if (StringUtils.isNotBlank(memberAccount.getPayPassword())) {
            return new BaseDto(9003, "您已设置过密码");
        }
        
        setPwd(pwd, memberAccount);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 修改支付密码
    * @author 张进军
    * @date Oct 23, 2015 10:56:04 AM
    * @param memberId   会员标识
    * @param oldPwd     旧密码
    * @param newPwd     新密码
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    public BaseDto updatePaycodeAction(Integer memberId, String oldPwd, String newPwd) {
        MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
        
        //检查用户密码
        if (!StringUtil.md5(oldPwd + memberAccount.getPasswordSalt()).equals(memberAccount.getPayPassword())) {
            return new BaseDto(9002, "密码不对，努力回忆下");
        }
        
        setPwd(newPwd, memberAccount);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 修改支付密码
    * @author 张进军
    * @date Nov 28, 2015 4:12:25 PM
    * @param newPwd         新密码
    * @param memberAccount  需修改的账户
    * @return   0:失败，1:成功
     */
    private int setPwd(String newPwd, MemberAccount memberAccount){
        String hash = StringUtil.encryptPwd(newPwd);
        newPwd = hash.split(":")[0];
        String passwordSalt = hash.split(":")[1];
        memberAccount.setPayPassword(newPwd);
        memberAccount.setPasswordSalt(passwordSalt);
        memberAccount.setUpdateTime(DateUtil.getCurTime());
        return memberAccountMapper.updateByPrimaryKey(memberAccount);
    }
    
    
    /**
     * 查看会员预约列表
    * @author 张进军
    * @date Aug 19, 2015 4:21:25 PM
    * @param memberId       会员标识
    * @return           会员预约列表页面
     */
    public ModelAndView appointmentListView(Integer memberId){
        List<AppointmentBaseDto> appointmentList = memberAppointmentMapper.selectAppointmentByMemberId(memberId);
        ModelAndView mav = new ModelAndView(View.MemberCenter.APPOINTMENT_LIST);
        mav.addObject("appointmentList", appointmentList);
        return mav;
    }
    
    
    /**
     * 查看会员订单列表
    * @author 张进军
    * @date Aug 19, 2015 4:21:25 PM
    * @param memberId       会员标识
    * @param orderType      orderType
    * @return           会员预约列表页面
     */
    public ModelAndView orderListView(int memberId, Integer orderType){
        ModelAndView mav = new ModelAndView(View.MemberCenter.ORDER_LIST);
        List<MemberOrderDto> orderList = orderInfoMapper.selectOrderListByMemberId(memberId);
        
        //检查订单的评价状态，商品/疗程订单暂不能评价
        for (MemberOrderDto order : orderList) {
            boolean isProject = false;
            for (OrderDetailDto detail : order.getDetailList()) {
                if (detail.getOrderType() == 1) {
                    isProject = true;
                    break;
                }
            }
            //可评价
            if (isProject && order.getOrderStatus() == 3) {
                order.setOrderStatus(100);
            }
            //已评价，待分享
            else if (isProject && order.getOrderStatus() == 4) {
            	order.setOrderStatus(101);
            }
        }
        if (orderType != null){
            orderList.stream().forEach(c -> {
                    List<OrderDetailDto> detailDtos = c.getDetailList()
                        .stream().filter(p -> p.getOrderType()
                                .equals(orderType)).collect(Collectors.toList());
                    c.setDetailList(detailDtos);
                });
            orderList = orderList
                    .stream().filter(p -> p.getDetailList()!=null && p.getDetailList().size()>0)
                    .collect(Collectors.toList());
        }
        mav.addObject("orderList", orderList);
        return mav;
    }
    
    
    /**
     * 查看会员订单确认页面
    * @author 张进军
    * @date Aug 19, 2015 4:21:25 PM
    * @param storeId        门店标识
    * @param orderId        订单编号
    * @return           会员订单确认页面
     */
    public ModelAndView orderPayView(String storeId, Integer orderId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.ORDER_PAY);
        
        SelfCashierOrderDto orderDto = selfCashierService.queryOrderDetailAction(orderId, 
                Integer.valueOf(orderInfoMapper.selectOrderBaseByOrderId(orderId).getStoreId()));
        //检查订单付款状态，如果已付款，直接跳转到小票页面
        if (orderDto.getOrderStatus() == 3 || orderDto.getOrderStatus() == 4) {
            return new ModelAndView("redirect:" + Url.MemberCenter.VIEW_PAYMENT_DETAIL
                    .replace("{storeId}", storeId + "").replace("{businessType}", "1") + "?orderId=" + orderId);
        }
        
        List<SelfCashierDetailDto> detialList = orderDto.getOrderDetails();
        BigDecimal appointOff = new BigDecimal(0);
        for (SelfCashierDetailDto detail : detialList) {
            appointOff = appointOff.add(detail.getAppointOff());
        }
        
        //检查是否需要评价，存在服务类才需评价
        for (SelfCashierDetailDto detail : detialList) {
            if (detail.getOrderType() == 1) {
            	mav.addObject("isEvaluate", "1");
                break;
            }
        }
        
        mav.addObject("appointOff", appointOff);
        mav.addObject("orderDto", orderDto);
        mav.addObject("detailStr", JSONArray.fromObject(orderDto.getOrderDetails()).toString());
        mav.addObject("allOffStr", JSONObject.fromObject(orderDto.getAllOffMap()).toString());
        mav.addObject("orderDtoStr", JSONObject.fromObject(orderDto).toString());
        return mav;
    }
    
    
    /**
     * 支付订单
    * @author 张进军
    * @date Nov 11, 2015 8:23:17 PM
    * @param orderSubmit    订单支付信息
    * @param memberId		会员标识
    * @param storeId 门店标识
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    public BaseDto orderPayAction(OrderInfoSubmitDto orderSubmit, Integer memberId, Integer storeId) {
        if (storeId == null){
            storeId = orderInfoMapper.selectByPrimaryKey(orderSubmit.getOrderId()).getStoreId();
        }
        return selfCashierService.cashierSubmit(0, orderSubmit, memberId, storeId);
    }
    
    
    /**
     * 查看订单支付明细信息
    * @author 张进军
    * @date Nov 9, 2015 11:23:08 PM
    * @param orderId        订单编号
    * @return   订单支付明细页面
     */
    public ModelAndView paymentDetailView(int orderId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.PAYMENT_DETAIL);
        OrderPaymentDto orderPayment = orderInfoMapper.selectOrderPaymentByOrderId(orderId);
        boolean isProject = false;
        for (DetailPaymentDto detail : orderPayment.getDetailList()) {
            if (detail.getOrderType() == 1) {
                isProject = true;
                break;
            }
        }
        
        if (isProject) {
        	mav.addObject("isProject", "1");
        }
        mav.addObject("orderPayment", orderPayment);
        
        int integralAmount = integralFlowMapper.selectIntegralAmountByOrderId(orderId);
        mav.addObject("integralAmount", integralAmount);
        
        StoreInfo storeInfo = storeInfoMapper.selectBaseInfoByStoreId(orderPayment.getStoreId());
        mav.addObject("storeInfo", storeInfo);
        return mav;
    }
    
    
    /**
     * 根据订单标识查询订单详情
    * @author 张进军
    * @date Nov 7, 2015 4:01:57 PM
    * @param orderId    订单标识
    * @return   订单评价页面
     */
    public ModelAndView orderEvaluateView(int orderId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.ORDER_EVALUATE);
        OrderInfoBaseDto orderInfo = orderInfoMapper.selectOrderBaseByOrderId(orderId);
        mav.addObject("orderInfo", orderInfo);
        return mav;
    }
    
    
    /**
     * 根据订单标识查询订单详情
    * @author 张进军
    * @date Nov 7, 2015 4:01:57 PM
    * @param orderEvaluate  评价对象
    * @param memberId       会员标识
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    @Transactional
    public BaseDto orderEvaluateAction(OrderEvaluateDto orderEvaluate, int memberId){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(orderEvaluate.getOrderId());
        orderInfo.setOrderStatus(4);
        orderInfo.setUpdateTime(DateUtil.getCurTime());
        int result = orderInfoMapper.updateEvaluateByOrderId(orderInfo);
        
        if (result == 1) {
            List<EmployeeEvaluate> employeeList = orderEvaluate.getEmloyeeList();
            List<ProjectEvaluate> projectList = orderEvaluate.getProjectList();
            
            String curTime = DateUtil.getCurTime();
            for (ProjectEvaluate projectEvaluate : projectList) {
                projectEvaluate.setTime(curTime);
                projectEvaluate.setMemberId(memberId);
            }
            for (EmployeeEvaluate employeeEvaluate : employeeList) {
                employeeEvaluate.setCreateTime(curTime);
                employeeEvaluate.setMemberId(memberId);
            }
            if (employeeList != null && employeeList.size() > 0) {
            	employeeEvaluateMapper.insertBatch(employeeList);
            }
            projectEvaluateMapper.insertBatch(projectList);
        }
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 查看会员疗程列表
    * @author 张进军
    * @date Aug 19, 2015 4:21:25 PM
    * @param memberId       会员标识
    * @return           会员疗程列表页面
     */
    public ModelAndView comboListView(int memberId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.COMBO_LIST);
        List<MemberComboDto> comboList = memberComboRecordMapper.selectComboListByMemberId(memberId);
        mav.addObject("comboList", comboList);
        return mav;
    }
    
    
    /**
     * 会员注册
    * @author 张进军
    * @date Aug 19, 2015 7:43:51 PM
    * @param storeId        注册门店
    * @param storeAccount   总店代号 
    * @param phone          手机号
    * @param verifyCode     验证码
    * @param openId         微信id
    * @param accessToken    微信接口口令
    * @param request        请求对象
    * @param checkExists	检查是否存在
    * @return               成功返回码0，返回值为跳转地址；失败返回其他错误码，返回值为提示语
     */
    @Transactional
    public BaseDto registerAction(int storeId, String storeAccount, String phone, String verifyCode, String openId,
            String accessToken, HttpServletRequest request, boolean checkExists){
        //校验验证码是否正确
        String code = redisService.get(App.Redis.PHONE_VERIFY_CODE_KEY_PRE + phone);
        if (!verifyCode.equals(code)) {
            return new BaseDto(10001, "验证码错误");
        }
        
        Integer memberId = memberInfoService.selectMemberIdByPhone(phone, storeAccount);
        if (checkExists && memberId != null) {
        	return new BaseDto(10002, "该号码已是本店会员");
        }
        
        //先完善资料
        BaseDto baseDto = new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, 
                App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_REGISTER_INFO);
        
        int subscribe = 0;
        //如果不存在，则需要新增会员数据、会员账户，跳转到完善会员信息页面
        if (memberId == null) {
            //查看店铺的默认会员等级
            int memberLevel = memberLevelMapper.selectDefaultLevelIdByStoreId(storeId);
            
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setStoreId(storeId);
            memberInfo.setPhone(phone);
            memberInfo.setLevelId(memberLevel);
            memberInfo.setLastOperatorId(0);
            memberInfo.setCreateTime(DateUtil.getCurTime());
            memberInfoMapper.insert(memberInfo);
            memberId = memberInfo.getMemberId();
            
            MemberAccount memberAccount = new MemberAccount();
            memberAccount.setAccountId(memberId);
            memberAccount.setBalanceIntegral(0);
            memberAccount.setTotalIntegral(0);
            memberAccount.setBalanceAmount(new BigDecimal(0));
            memberAccount.setTotalAmount(new BigDecimal(0));
            memberAccount.setLastOperatorId(0);
            memberAccount.setCreateTime(DateUtil.getCurTime());
            memberAccountMapper.insert(memberAccount);
            
            MemberSubAccount memberSubAccount = new MemberSubAccount();
            memberSubAccount.setAccountId(memberId);
            memberSubAccount.setLevelId(memberLevel);
            memberSubAccount.setCreateTime(DateUtil.getCurTime());
            memberSubAccount.setLastOperatorId(0);
            memberSubAccountMapper.insert(memberSubAccount);
        }
        else {
            String ownerOpenId = redisService.hget(App.Redis.WECHAT_MEMBERID_TO_OPENID_KEY_HASH, memberId);
            if (StringUtils.isNotBlank(ownerOpenId)) {
                return new BaseDto(10002, "对不起，该会员已经被其他微信绑定");
            }
            MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId, false);
            if (StringUtils.isNotBlank(memberInfo.getName())) {
                baseDto.setMsg(App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_HOME.replace("{storeId}", 
                        String.valueOf(storeAccount)).replace("{businessType}", "1"));
            }
        }
        
        wechatMemberMapper.deleteByPrimaryKey(openId);
        //建立微信的关联关系
        WechatMember wechatMember = new WechatMember();
        wechatMember.setOpenId(openId);
        wechatMember.setMemberId(memberId);
        wechatMember.setIsSubscribe(subscribe);
        String curTime = DateUtil.getCurTime();
        wechatMember.setCreateTime(curTime);
        wechatMember.setUpdateTime(curTime);
        wechatMemberMapper.insert(wechatMember);
        
        //检查该会员是否有未赠送的奖励
        if (redisService.sismember(App.Redis.WECHAT_OPENID_TO_SUBSCRIBE_AWARD_SET, openId)) {
            StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(storeId);
            if (storeSetting!=null){
                //检查是否有赠送的优惠券
                String coupon = storeSetting.getFirstFollowCoupon();
                if (StringUtils.isNotBlank(coupon)) {
                    String[] couponList = coupon.split(",");
                    for (String c : couponList) {
                        int couponId = Integer.parseInt(c);
                        memberInfoService.presentCouponToMember(memberId, couponId);
                    }
                }
                
                //检查是否有赠送的礼金
                Integer gift = storeSetting.getFirstFollowGift();
                if (gift > 0) {
                    memberInfoService.presentGiftmoneyToMember(memberId, new BigDecimal(gift), "用户注册奖励");
                    
                }
            }
            redisService.srem(App.Redis.WECHAT_OPENID_TO_SUBSCRIBE_AWARD_SET, memberId);
        }
        
        //将该用户移动到微信会员组中，刷新个性菜单
        weixinMessageService.moveGroupByGroupType(storeAccount, 1, openId);
        
        redisService.hset(App.Redis.WECHAT_SUBSCRIBE_KEY_HASH, openId, subscribe);
        redisService.hset(App.Redis.WECHAT_OPENID_TO_USERID_KEY_HASH, openId, memberId);
        redisService.hset(App.Redis.WECHAT_MEMBERID_TO_OPENID_KEY_HASH, memberId, openId);
        redisService.hset(App.Redis.WECHAT_OPENID_TO_BUSINESS_TYPE_KEY_HASH, openId, 1);
        request.getSession().setAttribute(App.Session.USER_ID, memberId);
        
        return baseDto;
    }
    
    
    /**
     * 解除微信关联
    * @author 张进军
    * @date Dec 12, 2015 12:10:13 AM
    * @param storeId    微信对应门店标识
    * @param memberId   会员标识
    * @param openId     微信标识
    * @param request    请求对象
    * @return   成功返回码为0，失败为其他错误码
     */
    public BaseDto logout(String storeId, int memberId, String openId, HttpServletRequest request) {
        //将用户移动到未绑定分组，刷新个性菜单
        weixinMessageService.moveGroupByGroupType(storeId, 4, openId);
        
        redisService.hdel(App.Redis.WECHAT_OPENID_TO_USERID_KEY_HASH, openId);
        redisService.hdel(App.Redis.WECHAT_OPENID_TO_BUSINESS_TYPE_KEY_HASH, openId);
        redisService.hdel(App.Redis.WECHAT_OPENID_TO_STORE_KEY_HASH, openId);
        redisService.hdel(App.Redis.WECHAT_MEMBERID_TO_OPENID_KEY_HASH, memberId);
        wechatMemberMapper.deleteByPrimaryKey(openId);
        request.getSession().invalidate();
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 获取短信验证码
    * @author 张进军
    * @date Sep 17, 2015 11:57:08 AM
    * @param storeId    店铺标识
    * @param phone      手机号
    * @return           验证码
     */
    public BaseDto getVerifyCodeAction(int storeId, String phone){
        rabbitService.sendVerifyCode(storeId, phone, "中邦我道");
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 访问完善会员信息页面
    * @author 张进军
    * @date Aug 19, 2015 8:37:05 PM
    * @param accessToken    微信api口令
    * @param openId         微信id
    * @return               完善会员信息页面             
     */
    public ModelAndView registerInfoView(String accessToken, String openId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.REGISTER_INFO);
        
        //如果是关注的会员，提取微信资料填充的页面
        String userInfoRes = HttpClientUtil.sendGetReq(String.format(App.Wechat.GET_USER_INFO_URL, 
                new Object[] { accessToken, openId }), "utf-8");
        JSONObject userInfoJson = JSONObject.fromObject(userInfoRes);
        if (!userInfoJson.containsKey("errcode") && userInfoJson.getInt("subscribe") == 1) {
            mav.addObject("nickname", userInfoJson.getString("nickname"));
            mav.addObject("sex", userInfoJson.getInt("sex"));
            mav.addObject("headimgurl", userInfoJson.getString("headimgurl"));
        }
        
        return mav;
    }
    
    
    /**
     * 访问完善会员信息页面
     * @author 张进军
     * @date Aug 19, 2015 7:08:55 PM
     * @param userId        用户标识
     * @param nickname      昵称
     * @param headUrl       头像
     * @param sex           性别
     * @param paycode       支付密码
     * @param birthday      生日（月－日）
     * @return              成功返回码0；失败返回其他错误码，返回值为提示语                 
     */
    @Transactional
    public BaseDto registerInfoAction(Integer userId, String nickname, 
    		    String headUrl, String sex, String paycode, String birthday){
    	//如果为其他域名的路径，需上传到七牛统一存储
    	if (headUrl.startsWith("http://") || headUrl.startsWith("https://")) {
    		String key = "userhead/" + userId + "/" + new Date().getTime();
    		qiniuService.fetch(headUrl, key);
    		headUrl = key;
    	}
    	
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(userId);
        memberInfo.setName(nickname);
        memberInfo.setHeadUrl(headUrl);
        memberInfo.setSex(sex);
        memberInfo.setBirthday(birthday);
        memberInfo.setUpdateTime(DateUtil.getCurTime());
        
        memberInfoMapper.updateByPrimaryKey(memberInfo);
        memberInfoService.wipeCache(userId);
        
        //更新用户支付密码
        if (StringUtils.isNotBlank(paycode)) {
            MemberAccount memberAccount = new MemberAccount();
            String hash = StringUtil.encryptPwd(paycode);
            paycode = hash.split(":")[0];
            String passwordSalt = hash.split(":")[1];
            memberAccount.setPayPassword(paycode);
            memberAccount.setPasswordSalt(passwordSalt);
            memberAccount.setAccountId(userId);
            memberAccountMapper.updateByPrimaryKey(memberAccount);
        }
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 访问预约页面
    * @author 张进军
    * @date Sep 2, 2015 11:00:28 AM
    * @param memberId       门店会员
    * @param storeAccount   门店代号
    * @param selectStoreId  所选门店
    * @return               预约页面
     */
    public ModelAndView orderAppointmentView(Integer memberId, String storeAccount, Integer selectStoreId){
        
        ModelAndView mav = new ModelAndView(View.MemberCenter.ORDER_APPOINTMENT);
        EnterpriseInfoDto enterpriseInfoDto = enterpriseInfoMapper.selectByProperties(new EnterpriseInfo(storeAccount));
        /** 企业下所有门店信息,部门信息*/
        List<StoreInfo> storeList = storeInfoMapper.selectByStoreAccount(storeAccount);
        if (selectStoreId == null && memberId == null){
            selectStoreId = storeList.get(0).getStoreId();
        }
        else if (selectStoreId == null && memberId != null){
            MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId, false);
            selectStoreId = memberInfo.getStoreId();
        }
        List<PositionInfo> positionInfos = positioninfoMapper.queryAllByStoreId(selectStoreId);
        List<EmployeeBaseDto> employeeInfos = employeeInfoMapper.selectEmployeeListByStoreId(selectStoreId);
        employeeInfos = employeeInfos.stream().filter(e -> e.getPositionId().intValue()==positionInfos.get(0)
                .getPositionId().intValue()).collect(Collectors.toList());
        StoreInfo storeInfo = storeInfoMapper.selectBaseInfoByStoreId(selectStoreId);
        mav.addObject("memberId", memberId);                      /**会员标示,如果没有的话,点击预约进行注册*/
        mav.addObject("storeList", storeList);                    /**企业门店列表*/
        mav.addObject("storeInfo", storeInfo);                    /**选择的门店信息*/
        mav.addObject("employeeInfos", employeeInfos);            /**门店下的员工信息*/
        mav.addObject("selectStoreId", selectStoreId);            /**默认选择的门店*/
        mav.addObject("enterpriseInfoDto", enterpriseInfoDto);    /**企业信息*/
        return mav;
    
    }
    
    
    /**
     * 访问项目详情页面
    * @author 张进军
    * @date Oct 18, 2015 7:21:19 PM
    * @param projectId  项目编号
    * @param memberId   会员标识
    * @param type   项目查询类型(1、查询所有可服务员工，2、不查询员工)
    * @param storeId 门店标识
    * @return   项目详情页面
     */
    public ModelAndView projectDetailView(int projectId, Integer memberId, int type, Integer storeId) {
        ModelAndView mav = new ModelAndView(View.MemberCenter.PROJECT_DETAIL);
        
        BigDecimal projectPrice = new BigDecimal(0);
        if (type == 1) {
            ProjectAppointDto projectDetail = projectService.selectProjectAppointByProjectId(projectId);
            mav.addObject("project", projectDetail);
            
            projectPrice = projectDetail.getProjectPrice();
        }
        else {
            ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(projectId);
            mav.addObject("project", projectInfo);
            projectPrice = projectInfo.getProjectPrice();
        }
        

        MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId, false);
        if (memberInfo != null) {
            mav.addObject("levelName", memberInfo.getLevelName());
            mav.addObject("discountAmount", projectService.getProjectPriceByMember(memberInfo.getLevelId(), 
                    projectId, projectPrice, Integer.valueOf(storeId)));
        }
        else {
            mav.addObject("discountAmount", projectPrice);
        }
        
        List<ProjectEvaluateDto> evaluateList = projectEvaluateMapper.selectListByProjectId(projectId);
        for (ProjectEvaluateDto projectEvaluateDto : evaluateList) {
            projectEvaluateDto.setMemberInfo(memberInfoService.getMemberBaseInfo(projectEvaluateDto.getMemberId(), false));
        }
        mav.addObject("evaluateList", evaluateList);
        return mav;
    }
    
    
    /**
     * 访问时间预约页面
    * @author 张进军
    * @date Oct 19, 2015 3:43:41 PM
    * @param employeeId     员工标识
    * @param request        请求对象
    * @param response       响应对象
    * @throws IOException 页面跳转异常
    * @throws ServletException 页面跳转异常
    * @return   时间预约页面
     */
    public ModelAndView dateAppointmentView(Integer employeeId, HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException{
        ModelAndView mav = new ModelAndView(View.MemberCenter.DATE_APPOINTMENT);
        
        EmployeeBaseDto employeeInfo = employeeInfoMapper.selectBaseInfoByEmployeeId(employeeId);
        mav.addObject("employeeId", employeeId);
        mav.addObject("employeeName", employeeInfo.getName());
        mav.addObject("levelName", employeeInfo.getLevelName());
        
        List<ProjectCategory> projectCategories = projectCategoryMapper.
                selectAllProjectByStoreId(Integer.parseInt(employeeInfo.getStoreId()));
        mav.addObject("projectCategories", projectCategories);
        
        //查询员工的班次
        Map<Integer, String> map = shiftMapper.selectShiftByEmployeeId(employeeId);
        //检查员工是否有设置班次
        if (map == null || map.isEmpty()) {
            request.setAttribute("tip", "对不起，系统找不到TA的可服务时间！");
            request.getRequestDispatcher("/404.jsp").forward(request, response);
            return null;
        }
        //TODO 查询员工当前的考勤情况
        
        //查询员工的预约情况
        List<String> appointList = memberAppointmentMapper.selectAppointDateByEmployee(employeeId);
        
        List<AppointDateDto> dateList = new ArrayList<AppointDateDto>();
        Map<String, List<Map<String, Integer>>> timeListMap = new HashMap<String, List<Map<String, Integer>>>();
        
        Calendar calendar = Calendar.getInstance();
        Integer week = 0;
        String workTime = "";
        String appointDate = "";
        String appointTime = "";
        String[] workTimeArr = null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFmt = new SimpleDateFormat("MM-dd");
        
        for (int i = 0; i < 30; i++) {
            week = calendar.get(Calendar.DAY_OF_WEEK);
            appointDate = dateFmt.format(calendar.getTime());
            AppointDateDto appointDateDto = new AppointDateDto();
            appointDateDto.setDate(appointDate);
            appointDateDto.setWeekNumber(week);
            appointDateDto.setWeekName(DateUtil.getWeekName(week));
            
            //获取当天的班次
            workTime = map.get(week + ""); 
            if (StringUtils.isBlank(workTime)) {
                continue;
            }
            workTimeArr = workTime.split(",");
            
            int bh = Integer.parseInt(workTimeArr[0].split(":")[0]);
            int bm = Integer.parseInt(workTimeArr[0].split(":")[1]);
            int eh = Integer.parseInt(workTimeArr[1].split(":")[0]);
            int em = Integer.parseInt(workTimeArr[1].split(":")[1]);
            
            Calendar workStartCalendar = Calendar.getInstance();
            workStartCalendar.setTime(calendar.getTime());
            
            Calendar workEndCalendar = Calendar.getInstance();
            workEndCalendar.setTime(calendar.getTime());
            
            workStartCalendar.set(Calendar.HOUR_OF_DAY, bh);
            workStartCalendar.set(Calendar.MINUTE, bm);
            
            workEndCalendar.set(Calendar.HOUR_OF_DAY, eh);
            workEndCalendar.set(Calendar.MINUTE, em);
            
            workStartCalendar.add(Calendar.MINUTE, 0);
            workEndCalendar.add(Calendar.MINUTE, 0);
            
            if (workStartCalendar.after(workEndCalendar)) {
                workEndCalendar.add(Calendar.DAY_OF_YEAR, 1);
            }
            
            workStartCalendar.add(Calendar.MINUTE, 30);
            workEndCalendar.add(Calendar.MINUTE, -29);
            
            List<Map<String, Integer>> timeList = timeListMap.get(appointDate);
            if (timeList == null) {
                timeList = new ArrayList<Map<String, Integer>>();
                timeListMap.put(appointDate, timeList);
            }
            boolean isJump = false;
            while (workStartCalendar.before(workEndCalendar)) {
                //检查是否超过一天
                if (workStartCalendar.get(Calendar.DAY_OF_MONTH) != calendar.get(Calendar.DAY_OF_MONTH)) {
                    calendar.add(Calendar.DAY_OF_YEAR, 1);
                    isJump = true;
                    appointDate = dateFmt.format(calendar.getTime());
                    timeList = new ArrayList<Map<String, Integer>>();
                    timeListMap.put(appointDate, timeList);
                }
                
                appointTime = sdf.format(workStartCalendar.getTime());
                
                Map<String, Integer> timeMap = new HashMap<String, Integer>();
                timeMap.put(appointTime, 1);
                
                //如果是当天的话，检查是否超过当前时间
                String tempDateTime = calendar.get(Calendar.YEAR) + "-" + appointDate + " " + appointTime;
                if (i == 0 && workStartCalendar.before(calendar)) {
                    timeMap.put(appointTime, 2);
                }
                //检查该时间是否已被预约
                else if (appointList.contains(tempDateTime)) {
                    timeMap.put(appointTime, 2);
                }
                
                timeList.add(timeMap);
                workStartCalendar.add(Calendar.MINUTE, 30);
            }
            dateList.add(appointDateDto);
            
            if (!isJump) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }
        }
        for (AppointDateDto appoint : dateList) {
            List<Map<String, Integer>> timeList = timeListMap.get(appoint.getDate());
            appoint.setTimeList(timeList);
        }
        
        mav.addObject("dateList", dateList);
        
        //查询员工请假情况leave_info
        return mav;
    }
    
    
    /**
     * 预约确认操作
    * @author 张进军
    * @date Oct 19, 2015 3:43:41 PM
    * @param memberId       预约的会员标识
    * @param mainStoreId    总店标识
    * @param appointDate    预约日期
    * @param appointTime    预约时间
    * @param projectId      项目标识
    * @param employeeId     员工标识
    * @param request        request
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    public BaseDto orderAppointmentAction(int memberId, String mainStoreId, String appointDate, 
            String appointTime, Integer projectId, Integer employeeId, HttpServletRequest request){
        //获取预约员工的门店标识
        int storeId = employeeInfoMapper.selectByPrimaryKey(employeeId).getStoreId();
        MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId, false);
        
        MemberAppointment memberAppointment = new MemberAppointment();
        memberAppointment.setMemberId(memberId);
        memberAppointment.setName(memberInfo.getName());
        memberAppointment.setPhone(memberInfo.getPhone());
        memberAppointment.setStoreId(storeId);
        memberAppointment.setEmployeeId(employeeId);
        memberAppointment.setProjectId(projectId);
        memberAppointment.setAppointmentDate(appointDate);
        memberAppointment.setAppointmentTime(appointTime);
        String curTime = DateUtil.getCurTime();
        memberAppointment.setCreateTime(curTime);
        memberAppointment.setUpdateTime(curTime);
        memberAppointment.setAppointmentStatus(1);
        memberAppointment.setIsDeleted(0);
        memberAppointment.setLastOperatorId(0);
        memberAppointment.setAppointmentWay(2);
        
        memberAppointmentMapper.insert(memberAppointment);
        
        //发送预约通知给员工
        String openId = redisService.hget(App.Redis.WECHAT_EMPLOYEEID_TO_OPENID_KEY_HASH, employeeId);
        if (StringUtils.isNotBlank(openId)) {
            rabbitService.sendAppointmentApplyNotice(storeId, mainStoreId, App.System.SERVER_BASE_URL 
                    + Url.Staff.VIEW_STAFF_APPOINT.replace("{storeId}", mainStoreId + "").replace("{businessType}", "2").replace("{type}", "1"), 
                    employeeId, openId, memberInfo.getName(), memberInfo.getLevelName(), 
                    projectCategoryMapper.selectByPrimaryKey(projectId).getCategoryName(), 
                    appointDate + " " + appointTime);
        }
        //推送声音到门店
        rabbitService.storeAppointVoice(storeId, employeeId, request);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 取消预约
    * @author 张进军
    * @date Nov 4, 2015 10:49:11 AM
    * @param memberId       会员标识
    * @param storeId        门店标识
    * @param appointmentId  预约标识
    * @param employeeId     员工标识
    * @param projectName    预约项目    
    * @param appointmentTime    预约时间
    * @param reason         取消原因
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    public BaseDto cancelAppoinmentAction(int memberId, int storeId, int appointmentId, int employeeId, 
            String projectName, String appointmentTime, String reason){
        String curTime = DateUtil.getCurTime();
        MemberAppointment memberAppointment = new MemberAppointment();
        memberAppointment.setAppointmentId(appointmentId);
        memberAppointment.setCancelReason(reason);
        memberAppointment.setCancelTime(curTime);
        memberAppointment.setUpdateTime(curTime);
        memberAppointment.setAppointmentStatus(4);
        memberAppointmentMapper.updateByPrimaryKey(memberAppointment);
        projectName = projectCategoryMapper.selectByPrimaryKey(memberAppointmentMapper.
                selectAppointmentByAppointmentId(appointmentId).getProjectId()).getCategoryName();
        
        
        //发送预约取消通知给员工
        String openId = redisService.hget(App.Redis.WECHAT_EMPLOYEEID_TO_OPENID_KEY_HASH, employeeId);
        if (StringUtils.isNotBlank(openId)) {
            String storeAccount = storeInfoMapper.selectByPrimaryKey(storeId).getStoreAccount();
            MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId, false);
            String url = App.System.SERVER_BASE_URL 
                    + Url.Staff.VIEW_STAFF_APPOINT.replace("{storeId}", storeAccount + "").replace("{businessType}", "2").replace("{type}", "3");
            //如果是会员
            if (memberInfo != null) {
            	rabbitService.sendAppointmentResultNotice(3, storeId, url, openId, 
            	        memberInfo.getName(), memberInfo.getLevelName(), projectName, appointmentTime, reason);
            }
            //如果是散客
            else {
            	rabbitService.sendAppointmentResultNotice(3, storeId, url, openId, "散客", "无等级", projectName, appointmentTime, reason);
            }
        }
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 查看会员卡金流水
    * @author 张进军
    * @date Oct 20, 2015 8:48:15 PM
    * @param memberId   会员标识
    * @return   卡金流水页面
     */
    public ModelAndView cardmoneyFlowView(int memberId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.CARD_MONEY_FLOW);
        List<MoneyFlowDto> flowList = moneyFlowMapper.selectFlowListByMemberId(memberId);
        mav.addObject("flowList", flowList);
        return mav;
    }
    
    
    /**
     * 查看会员礼金流水
    * @author 张进军
    * @date Oct 20, 2015 8:48:15 PM
    * @param memberId   会员标识
    * @return   礼金流水页面
     */
    public ModelAndView giftmoneyFlowView(int memberId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.GIFT_MONEY_FLOW);
        List<GiftmoneyFlow> flowList = giftmoneyFlowMapper.selectFlowlistByAccountId(memberId);
        mav.addObject("flowList", flowList);
        return mav;
    }
    
    
    /**
     * 查看会员积分流水
    * @author 张进军
    * @date Oct 20, 2015 8:48:15 PM
    * @param memberId   会员标识
    * @return   积分流水页面
     */
    public ModelAndView integralFlowView(int memberId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.INTEGRAL_FLOW);
        List<IntegralFlow> integralFlowList = integralFlowMapper.selectFlowListByAccountId(memberId);
        mav.addObject("memberInfo", memberInfoService.getMemberBaseInfo(memberId, false));
        mav.addObject("integralFlowList", integralFlowList);
        return mav;
    }
    
    
    /**
     * 查看会员优惠券
    * @author 张进军
    * @date Oct 20, 2015 8:48:15 PM
    * @param memberId   会员标识
    * @return   会员优惠券页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_INTEGRAL_FLOW_LIST)
    public ModelAndView memberCouponView(int memberId){
        List<CouponBaseDto> couponList = couponInfoMapper.selectBaseByMemberId(memberId);
        ModelAndView mav = new ModelAndView(View.MemberCenter.MEMBER_COUPON);
        mav.addObject("couponList", couponList);
        return mav;
    }
    
    /**
     * 查看门店优惠券
    * @author 张进军
    * @date Oct 20, 2015 8:48:15 PM
    * @param memberId   会员标识
    * @param storeAccount storeAccount
    * @param storeId storeId
    * @return   会员优惠券页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_INTEGRAL_FLOW_LIST)
    public ModelAndView storeCouponView(String storeAccount, Integer storeId, int memberId){
        MemberBaseDto memberBaseInfo = memberInfoService.getMemberBaseInfo(memberId, false);
        CouponInfo couponInfo = new CouponInfo();
        couponInfo.setStoreAccount(storeAccount);
        couponInfo.setStartType("1");
        List<CouponInfo> couponInfos = couponInfoMapper.selectByProperties(couponInfo);
        
        for (int i = 0; i < couponInfos.size(); i++) {
            String storeNames = JSONArray.fromObject(couponInfos.get(i)
                    .getStoreInfos().stream()
                    .map(s -> s.getStoreName())
                    .collect(Collectors.toList()))
                    .toString();
            couponInfos.get(i).setStoreNames(storeNames);
        }
        ModelAndView mav = new ModelAndView(View.MemberCenter.STORE_COUPON);
        mav.addObject("couponInfos", couponInfos);
        mav.addObject("memberBaseInfo", memberBaseInfo);
        return mav;
    }
    
    
    /**
     * 查询门店下商城信息
    * @author 张进军
    * @date Oct 21, 2015 10:00:34 AM
    * @param storeAccount    门店标识（总店）
    * @param ownerStoreId    会员所属门店
    * @param selectedStoreId 选中门店
    * @return   积分商城页面
     */
    public ModelAndView shopCenterView(String storeAccount, Integer ownerStoreId, Integer selectedStoreId){
        
        List<StoreInfo> storeList = storeInfoMapper.selectByStoreAccount(storeAccount);
        if (ownerStoreId == null && selectedStoreId == null) {
            ownerStoreId = storeList.get(0).getStoreId();
        }
        if (selectedStoreId != null){
            ownerStoreId = selectedStoreId;
        }
        List<CouponInfo> couponList = couponInfoMapper.selectCouponListByStoreId(ownerStoreId);
        ModelAndView mav = new ModelAndView(View.MemberCenter.SHOP_CENTER);
        mav.addObject("couponList", couponList);
        
        StoreShop storeShop = new StoreShop();
        storeShop.setStoreId(ownerStoreId);
        StoreShop shop = storeShopMapper.selectByProties(storeShop);
        List<Integer> paramsAestSellers = null;
        List<Integer> paramsBestSellers = null;
        List<GoodsInfoDto> aestSellers = null;
        List<GoodsInfoDto> bestSellers = null;
        if (shop != null && !"".equals(shop.getNewArrival())){
            paramsAestSellers = Stream.of(shop.getNewArrival().split(",")).map(str -> Integer.parseInt(str)).collect(Collectors.toList());
            paramsBestSellers = Stream.of(shop.getBestSellers().split(",")).map(str -> Integer.parseInt(str)).collect(Collectors.toList());
            aestSellers = goodsInfoService.queryByGoodsIds(paramsAestSellers);
            bestSellers = goodsInfoService.queryByGoodsIds(paramsBestSellers);
        }
        
        List<ProjectInfo> projectInfos = projectInfoMapper.selectByStoreId(ownerStoreId);
        List<ComboInfo> comboInfos = comboInfoMapper.selectByProperty(new ComboInfo(ownerStoreId));
        
        mav.addObject("aestSellers", aestSellers);
        mav.addObject("bestSellers", bestSellers);
        mav.addObject("storeShop", shop);
        
        StoreInfo storeInfo = storeInfoMapper.selectBaseInfoByStoreId(ownerStoreId);
        mav.addObject("storeInfo", storeInfo);
        mav.addObject("storeList", storeList);
        
        mav.addObject("storeId", ownerStoreId);
        
        mav.addObject("projectInfos", projectInfos);
        mav.addObject("comboInfos", comboInfos);
        
        return mav;
    }
    
    
    /**
     * 兑换优惠券
     * @author 张进军
     * @date Oct 22, 2015 2:58:22 PM
     * @param storeAccount storeAccount
     * @param storeId      storeId
     * @param openId       openId
     * @param memberId  会员标识
     * @param couponId  优惠券标识
     * @param num       兑换的数量
     * @return  成功返回码0；失败返回其他错误码，返回值为提示语
     */
    @Transactional
    public BaseDto exchangeCouponAction(String openId, String storeAccount, Integer storeId, int memberId, Integer couponId, Integer num) {
        BaseDto dto = new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
//        MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId, false);
        CouponInfo couponInfo = couponInfoMapper.selectByPrimaryKey(couponId);
        
        //判断该人员之前对该优惠券的领取数量
        List<CouponBaseDto> couponList = couponInfoMapper.selectBaseByMemberId(memberId);
        Long memerHasThisCouponNum = couponList.stream().filter(c -> c.getCouponId() == couponId).count();
        if ((memerHasThisCouponNum.intValue()+num)>couponInfo.getCouponMan()){
            dto.setCode(-1);
            return dto;
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(couponInfo.getCouponStartTime()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<MemberCoupon> memberCoupons = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            MemberCoupon memberCoupon = new MemberCoupon(couponId, memberId, sdf.format(calendar.getTime()), 0);
            memberCoupons.add(memberCoupon);
        }
        if (memberCoupons.size()>0){
            memberCouponMapper.insertList(memberCoupons);
            MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
            memberAccount.setBalanceIntegral(memberAccount.getBalanceIntegral()-num*couponInfo.getCouponVantages());
            memberAccountMapper.updateByPrimaryKey(memberAccount);
            redisService.hdel(App.Redis.MEMBER_BASE_INFO_KEY_HASH, memberId);
            //推送消息
            List<String> touser = new ArrayList<>();
            touser.add(openId);
            JSONObject object = new JSONObject();
            object.put("storeAccount", storeAccount);
            object.put("storeName", storeInfoMapper.selectByPrimaryKey(storeId).getStoreName());
            object.put("couponName", couponInfo.getCouponName());
            object.put("num", memberCoupons.size());
            object.put("couponStopTime", sdf.format(calendar.getTime()));
            object.put("tempId", storeWechatMapper.selectByPrimaryKey(storeAccount).getTmCouponOverdue());
            object.put("touser", touser);
            rabbitTemplate.convertAndSend(App.Queue.SEND_COUPONS, object);
        }
        
        return dto;
    }
    
    
    /**
     * 查询门店信息
    * @author 张进军
    * @date Oct 21, 2015 10:00:34 AM
    * @param storeAccount    总店标识
    * @param selectStoreId    所选门店标识
    * @return   积分商城页面
     */
    public ModelAndView storeInfoView(String storeAccount, Integer selectStoreId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.STORE_INFO);
        
        List<StoreInfo> storeList = storeInfoMapper.selectByStoreAccount(storeAccount);
        
        StoreInfo storeInfo = null;
        if (selectStoreId == null) {
            storeInfo = storeList.get(0);
            selectStoreId = storeList.get(0).getStoreId();
        }
        else {
            storeInfo = storeInfoMapper.selectBaseInfoByStoreId(selectStoreId);
        }
        mav.addObject("storeInfo", storeInfo);
        mav.addObject("storeSize", storeList.size());
        
        String tel = storeInfo.getStoreTel();
        if (StringUtils.isNotBlank(tel)) {
            String[] telArray = tel.split(",");
            mav.addObject("telArray", telArray);
        }
        
        String employee = storeInfo.getTeacherIntroduction();
        if (StringUtils.isNotBlank(employee)) {
            List<String> list = Arrays.asList(employee.split(","));
            List<EmployeeBaseDto> employeeList = employeeInfoMapper.selectEmployeeListByList(list);
            mav.addObject("employeeList", employeeList);
        }
        
        List<SpecialService> specialServices = specialServiceMapper.selectByStoreId(selectStoreId);
        mav.addObject("specialServices", specialServices);
        
        mav.addObject("session_key_store_id", selectStoreId);
        
        return mav;
    }
    
    
    /**
     * 店铺展示页面
    * @author 张进军
    * @date Oct 21, 2015 10:00:34 AM
    * @param storeId    门店标识
    * @param type           展示类型(1：门店介绍，2：技术展示，3：特色服务)
    * @return   店铺展示页面
     */
    public ModelAndView storeShowView(int storeId, int type){
        ModelAndView mav = new ModelAndView(View.MemberCenter.STORE_SHOW);
        String title = "";
        String content = "";
        switch (type) {
            case 1:
                content = storeInfoMapper.selectDescByStoreId(storeId);
                title = "门店介绍";
                break;
            case 2:
                content = storeInfoMapper.selectTechnicalByStoreId(storeId);
                title = "技术展示";
                break;
            default:
                content = storeInfoMapper.selectCharacteristicByStoreId(storeId);
                title = "特色服务";
                break;
        }
        mav.addObject("title", title);
        mav.addObject("content", content);
        return mav;
    }
    
    
    /**
     * 进入分享操作页面，如果已分享，直接进入分享的页面
    * @author 张进军
    * @date Jan 7, 2016 11:43:59 AM
    * @param orderId	订单标识
    * @param storeId	门店标识
    * @param memberId	会员标识
    * @return	订单未分享跳转到分享操作页面，订单已分享跳转到分享给好友的页面
     * @throws Exception 加密抛出的异常
     */
    public ModelAndView shareView(int orderId, String storeId, int memberId) throws Exception{
    	ModelAndView mav = new ModelAndView(View.MemberCenter.SHARE_SHOW);
    	OrderInfoBaseDto orderInfo = orderInfoMapper.selectOrderBaseByOrderId(orderId);
    	String code = String.valueOf(memberId);
        if (orderInfo.getIsShare() == 1) {
            return shareInfoView(code, memberId, null, orderId, true, storeId);
        }
    	mav.addObject("orderId", orderId);
    	mav.addObject("code", code);
    	return mav;
    }
    
    
    /**
     * 分享发型操作
    * @author 张进军
    * @date Nov 14, 2015 12:45:46 PM
    * @param projectShare    分享信息
    * @return   成功返回获赠积分数量
     */
    @Transactional
    public BaseDto shareAction(ProjectShare projectShare){
        int orderId = projectShare.getOrderId();
        //检查是否有分享过
        OrderInfoBaseDto orderInfo = orderInfoMapper.selectOrderBaseByOrderId(orderId);
        int storeId = orderInfo.getStoreId();
        
        if (orderInfo.getIsShare() == 1) {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "感谢您的再次分享，只有首次分享有奖励哦");
        }
        orderInfoMapper.updateOrderShare(orderId);
        
        String time = DateUtil.getCurTime();
        int memberId = orderInfo.getMemberId();
        
        //获取最贵的项目
        Integer projectId = orderInfo.getOrderDetailList().stream().filter(detail -> detail.getOrderType() == 1)
        		    .max(Comparator.comparing(detail -> detail.getProjectPrice())).get().getProjectId();
        
        projectShare.setProjectId(projectId);
        projectShare.setMemberId(memberId);
        projectShare.setTime(time);
        projectShareMapper.insert(projectShare);
        
        //检查门店是否有设置分享机制
        StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(storeId);
        if (StringUtils.isBlank(storeSetting.getMemberShareReward())) {
        	return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, null);
        }
        
        //赠送门店的分享奖励
        Map<String, Object> sharerMap = presentShareReward(storeId, memberId, 1, BigDecimal.ZERO);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, sharerMap);
    }

    
    /**
     * 查看分享信息页面
    * @author 张进军
    * @date Jan 3, 2016 11:13:47 PM
    * @param code	分享者标识
    * @param ownerId	分享者标识
    * @param memberId	会员标识
    * @param orderId	分享订单标识
    * @param isOwner	是否为分享者
    * @param storeAccount	公众号对应门店标识
    * @return	分享信息页面
     * @throws UnsupportedEncodingException 
     */
    public ModelAndView shareInfoView(String code, Integer ownerId, Integer memberId, Integer orderId, 
    		    Boolean isOwner, String storeAccount) throws UnsupportedEncodingException{
        String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeAccount);
    	ModelAndView mav = new ModelAndView(View.MemberCenter.SHARE_INFO);
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	map.put("memberId", ownerId);
    	map.put("orderId", orderId);
    	ProjectShare projectShare = projectShareMapper.selectByMemberIdAndOrderId(map);
    	
    	MemberBaseDto ownerInfo = memberInfoService.getMemberBaseInfo(ownerId, false);
    	
    	OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderId);
    	int storeId = orderInfo.getStoreId();
    	StoreInfo storeInfo = storeInfoMapper.selectBaseInfoByStoreId(storeId);
    	StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(storeId);
    	String res = storeSetting.getMemberShareReward();
    	if (StringUtils.isNotBlank(res)) {
    		JSONObject json = JSONObject.fromObject(res);
    		
    		Integer watcherRewardType = json.getInt("watcherRewardType");
    		Integer watcherRewardContent = json.getInt("watcherRewardContent");
    		Map<String, Object> watcherMap = getShareReward(watcherRewardType, watcherRewardContent);
//    		mav.addObject("rewardName", watcherMap.get("rewardName"));
    		mav.addObject("rewardName", Stream.of(watcherMap.get("rewardName").toString().split("")).collect(Collectors.toList()));
    		mav.addObject("rewardAmount", watcherMap.get("rewardAmount"));
    		if (watcherRewardType == 1) {
    			mav.addObject("rewardUnit", "分");
    		}
    		else {
    			mav.addObject("rewardUnit", "元");
    		}
    		
    		//拼装分享奖励说明
    		Integer sharerRewardType = json.getInt("sharerRewardType");
    		Integer sharerRewardContent = json.getInt("sharerRewardContent");
    		Map<String, Object> sharerMap = getShareReward(sharerRewardType, sharerRewardContent);
    		mav.addObject("shareDesc", sharerMap.get("rewardDesc"));
    		
    		//拼装消费回馈奖励
    		Integer firstConsumeRewardType = json.getInt("firstConsumeRewardType");
    		Integer firstConsumeRewardUnit = json.getInt("firstConsumeRewardUnit");
    		Integer firstConsumeRewardContent = json.getInt("firstConsumeRewardContent");
    		String firstConsumeDesc = "";
    		if (firstConsumeRewardType == 3) {
    			Map<String, Object> firstConsumeMap = getShareReward(sharerRewardType, sharerRewardContent);
    			firstConsumeDesc = "您推荐的好友首次消费时，您可以获得一张"
    			        + firstConsumeMap.get("rewardAmount").toString() + firstConsumeMap.get("rewardName").toString();
    		}
    		else {
    			firstConsumeDesc = "您推荐的好友首次消费时，每消费"
    			        + firstConsumeRewardUnit + "元，您可以获得" + firstConsumeRewardContent;
    			if (firstConsumeRewardType == 1) {
    				firstConsumeDesc += "元账户礼金";
        		}
    			else {
    				firstConsumeDesc += "分商城积分";
    			}
    			firstConsumeDesc += "";
    		}
    		mav.addObject("firstConsumeDesc", firstConsumeDesc);
    	}
    	
    	//如果查看分享页面的为分享者, 统计推荐数据
    	if (isOwner) {
    		List<Integer> recommendList = memberRecommendMapper.selectRecommendIdByMemberId(ownerId);
    		mav.addObject("isOwner", isOwner);
    		mav.addObject("recommendCount", recommendList.size());
    	}
    	
    	StoreWechat storeWechat = storeWechatMapper.selectByPrimaryKey(storeAccount);
    	mav.addObject("qrcode", storeWechat.getWechatId());
    	
    	MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId, false);
    	mav.addObject("memberInfo", memberInfo);
    	if (memberInfo==null){
    	    Map<String, Object> ticket = new HashMap<>();
    	    ticket.put("expire_seconds", 604800);
    	    ticket.put("action_name", "QR_SCENE");
    	    Map<String, Object> scene = new HashMap<>();
    	    Map<String, Object> sceneId = new HashMap<>();
    	    sceneId.put("scene_id", 101);
    	    scene.put("scene", sceneId);
    	    ticket.put("action_info", scene);
    	    JSONObject result = WeixinUploadService.httpRequest(Url.Wechat.TICKET.replace("ACCESS_TOKEN", accessToken), 
    	            "POST", JSONObject.fromObject(ticket).toString());
    	    String tick = result.getString("ticket");
    	    mav.addObject("ticket", java.net.URLEncoder.encode(tick, "utf-8"));
    	}
    	
    	ProjectInfo projectInfo = projectService.queryProjectInfoById(projectShare.getProjectId());
    	mav.addObject("projectInfo", projectInfo);
    	
    	mav.addObject("projectShare", projectShare);
    	mav.addObject("ownerInfo", ownerInfo);
    	mav.addObject("storeInfo", storeInfo);
    	mav.addObject("mainStoreId", storeAccount);
    	mav.addObject("code", code);
    	mav.addObject("orderId", orderId);
    	return mav;
    }
    
    /**
     * 获取解析后的奖励方式、奖励内容
    * @author 张进军
    * @date Jan 6, 2016 4:44:30 PM
    * @param rewardType		奖励方式
    * @param rewardAmount	奖励内容
    * @return	解析后的奖励方式、奖励内容
     */
    private Map<String, Object> getShareReward(Integer rewardType, Integer rewardAmount) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	String rewardName = "";
    	String rewardDesc = "";
    	//如果为优惠券，查询出优惠券信息
		if (rewardType == 3) {
			CouponInfo couponInfo = couponInfoMapper.selectNormalByCouponId(rewardAmount);
			rewardName = couponInfo.getCouponName();
//			switch (couponInfo.getCouponType()) {
//    			case 0:
//    				rewardName = "通用优惠券";
//    				break;
//    			case 1:
//    				rewardName = "项目优惠券";
//    				break;
//    			case 2:
//    				rewardName = "商品优惠券";
//    				break;
//    			default:
//    				break;
//			}
			rewardAmount = couponInfo.getCouponPrice();
			rewardDesc = "一张" + rewardAmount + "元" + rewardName;
		}
		else if (rewardType == 1) {
			rewardName = "商城积分";
			rewardDesc = rewardAmount + "分" + rewardName;
		}
		else if (rewardType == 2) {
			rewardName = "账户礼金";
			rewardDesc = rewardAmount + "元" + rewardName;
		}
		
		map.put("rewardType", rewardType);
		map.put("rewardName", rewardName);
		map.put("rewardDesc", rewardDesc);
		map.put("rewardAmount", rewardAmount);
    	return map;
    }
    

    /**
     * 查看门店列表
    * @author 张进军
    * @date Nov 19, 2015 5:38:33 PM
    * @param storeAccount    总店标识
    * @param url        跳转地址
    * @return   门店列表页面
    */
    public ModelAndView storeListView(String storeAccount, String url) {
        logger.debug("storeId : " + storeAccount + ", url : " + url);
        if (url.startsWith("//")) {
            url = url.replaceFirst("/", "");
        }
        
        List<StoreInfo> storeList = storeInfoMapper.selectByStoreAccount(storeAccount);
        
        //如果只有一个分店，那么直接跳转到对应的地址，即分店
        if (storeList.size() == 1) {
            return new ModelAndView("redirect:" + url.replace("_storeId_", String.valueOf(storeList.get(0).getStoreId())));
        }
        //保证页面拼接时的地址正确
        if (url.startsWith("/")) {
            url = url.replaceFirst("/", "");
        }
        
        ModelAndView mav = new ModelAndView(View.MemberCenter.STORE_LIST);
        mav.addObject("url", url);
        mav.addObject("storeList", storeList);
        
//        StoreInfo mainStoreInfo = storeInfoMapper.selectBaseInfoByStoreId(storeId);
//        mav.addObject("mainStoreInfo", mainStoreInfo);
        return mav;
    }
    
    
    /**
     * 新用户领取分享奖励
    * @author 张进军
    * @date Aug 19, 2015 7:43:51 PM
    * @param ownerMemberId	分享者用户标识
    * @param mainStoreId	微信对应门店标识
    * @param storeId        注册门店
    * @param phone          手机号
    * @param verifyCode     验证码
    * @param openId         微信id
    * @param accessToken    微信接口口令
    * @param request        请求对象
    * @return               成功返回码0，返回值为关注结果；失败返回其他错误码，返回值为提示语
     */
    public BaseDto getRewardAction(int ownerMemberId, String mainStoreId, int storeId, String phone, String verifyCode, String openId,
            String accessToken, HttpServletRequest request){
    	BaseDto dto = registerAction(storeId, mainStoreId, phone, verifyCode, openId, accessToken, request, true);
    	//如果会员录入成功
    	if (dto.getCode() == 0) {
    		int memberId = Integer.parseInt(redisService.hget(App.Redis.WECHAT_OPENID_TO_USERID_KEY_HASH, openId));
    		
    		//赠予奖励
    		presentShareReward(storeId, memberId, 2, BigDecimal.ZERO);
        	
        	//建立推荐关系
        	MemberRecommend memberRecommend = new MemberRecommend();
        	memberRecommend.setMemberId(ownerMemberId);
        	memberRecommend.setRecommendId(memberId);
        	memberRecommend.setRecommendTime(DateUtil.getCurTime());
        	memberRecommendMapper.insert(memberRecommend);
        	
    		String subscribe = redisService.hget(App.Redis.WECHAT_SUBSCRIBE_KEY_HASH, openId);
    		dto.setMsg(subscribe);
    	}
    	return dto;
    }
    
    
    /**
     * 员工信息页面
    * @author 张进军
    * @date Jan 29, 2016 3:09:31 PM
    * @param employeeId 员工标识
    * @return   员工信息页面
     */
    public ModelAndView employeeInfoView(int employeeId){
        ModelAndView mav = new ModelAndView(View.MemberCenter.EMPLOYEE_INFO);
        EmployeeBaseDto employeeInfo = employeeInfoMapper.selectBaseInfoByEmployeeId(employeeId);
        List<ProjectBaseDto> projectList = projectInfoMapper.selectAppointProjectByEmployeeLevelId(employeeInfo.getLevelId());
        mav.addObject("employeeInfo", employeeInfo);
        mav.addObject("projectList", projectList);
        return mav;
    }
    
    
    /**
     * 员工项目详情页面
    * @author 张进军
    * @date Jan 29, 2016 4:14:24 PM
    * @param employeeId     员工标识
    * @param projectId      项目标识
    * @param memberId       访问会员标识
    * @param storeId 门店标识
    * @return   员工项目详情页面
     */
    public ModelAndView employeeProjectView(int employeeId, int projectId, Integer memberId, Integer storeId) {
        ModelAndView mav = projectDetailView(projectId, memberId, 2, storeId);
        mav.setViewName(View.MemberCenter.EMPLOYEE_PROJECT);
        
        /*ProjectStep projectStep = projectStepMapper.selectAppointStepByProjectId(projectId);
        mav.addObject("projectStep", projectStep);*/
        
        EmployeeBaseDto employeeInfo = employeeInfoMapper.selectBaseInfoByEmployeeId(employeeId);
        mav.addObject("employeeInfo", employeeInfo);
        
        return mav;
    }
    
    
    
    
    
    /**
     *赠送分享奖励
    * @author 张进军
    * @date Jan 7, 2016 11:01:11 AM
    * @param storeId	门店标识
    * @param memberId	会员标识
    * @param type		业务类型(1:赠送分享者，2:赠送领取者，3:领取奖励消费回馈予分享者)
    * @param firstConsumeMoney	被推荐者首次消费金额
    * @return	返回分享奖励内容
     */
    private Map<String, Object> presentShareReward(int storeId, int memberId, int type, BigDecimal firstConsumeMoney){
    	Map<String, Object> map = null;
    	StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(storeId);
    	String res = storeSetting.getMemberShareReward();
    	if (StringUtils.isNotBlank(res)) {
    		
    		JSONObject json = JSONObject.fromObject(res);
    		Integer shareType = null;
    		Integer shareReward = null;
    		
    		switch (type) {
				case 1:
					shareType = json.getInt("sharerRewardType");
					shareReward = json.getInt("sharerRewardContent");
					break;
				case 2:
					shareType = json.getInt("watcherRewardType");
					shareReward = json.getInt("watcherRewardContent");
					break;
				//如果为消费回扣，单独处理	
				case 3:
					return null;
				default:
					break;
			}
    		
    		map = getShareReward(shareType, shareReward);
    		
    		switch (shareType) {
    				//积分
				case 1:
					memberInfoService.changeIntegralToMember(memberId, shareReward, 2, "好友分享奖励", null);
					break;
					//礼金
				case 2:
					memberInfoService.presentGiftmoneyToMember(memberId, new BigDecimal(shareReward), "好友分享奖励");
					break;
					//优惠券
				case 3:
					memberInfoService.presentCouponToMember(memberId, shareReward);
					break;
				default:
					break;
			}
    	}
    	return map;
    }


    /**
     * 查询门店下商品分类大全
    * @author 小高
    * @date Oct 21, 2015 10:00:34 AM
    * @param storeAccount    门店标识（总店）
    * @param selectStoreId   所选门店
    * @param ownerStoreId   会员所属门店
    * @return               商城分类页面
     */
    public ModelAndView shopCenterViewList(String storeAccount, Integer ownerStoreId, Integer selectStoreId) {
        List<StoreInfo> storeList = storeInfoMapper.selectByStoreAccount(storeAccount);
        Integer storeId = null;
        if (ownerStoreId==null&&selectStoreId==null){
            storeId = storeList.get(0).getStoreId();
        }
        if (ownerStoreId != null) {
            storeId = ownerStoreId;
        }
        if (selectStoreId != null) {
            storeId = selectStoreId;
        }
        List<GoodsInfoCatagoryDto> goodsInfoCatagoryDtos =  goodsInfoMapper.selectCatagoryGoodsInfos(storeId);
        
        ModelAndView view = new ModelAndView(View.MemberCenter.SHOP_CENTER_LIST);
        view.addObject("goodsInfoCatagoryDtos", goodsInfoCatagoryDtos);
        view.addObject("storeId", storeId);
        return view;
    }


    /**
     * 展示特色服务
    * @author 高国藩
    * @date 2016年5月19日 下午7:23:09
    * @param sId sId
    * @return    sId
     */
    public ModelAndView storeInfoViewSpecail(Integer sId) {
        SpecialService specialService = specialServiceMapper.selectByPrimaryKey(sId);
        Integer storeId = specialService.getStoreId();
        Integer employeeCode = specialService.getEmployeeCode();
        List<EmployeeInfo> employeeInfos = employeeInfoMapper.selectEmployeeList(storeId);
        employeeInfos = employeeInfos.stream().filter(p -> p.getEmployeeCode().equals(employeeCode)).collect(Collectors.toList());
        ModelAndView view = new ModelAndView(View.MemberCenter.STORE_SPE);
        view.addObject("specialService", specialService);
        view.addObject("employeeId", employeeInfos.get(0).getEmployeeId());
        return view;
    }


    /**
     * 在线商城设置
    * @author 高国藩
    * @date 2016年5月21日 下午3:53:30
    * @param storeId storeId
    * @return        页面
     */
    public ModelAndView onlionShopView(Integer storeId) {
        GoodsInfoDto goodsInfo = new GoodsInfoDto();
        goodsInfo.setStoreId(storeId);
        goodsInfo.setIsSellProduct(1);
        List<GoodsInfoDto> goodsInfos = goodsInfoMapper.selectByProperty(goodsInfo);
        
        StoreShop storeShop = new StoreShop();
        storeShop.setStoreId(storeId);
        StoreShop hasStoreShop = storeShopMapper.selectByProties(storeShop);
        ModelAndView view = new ModelAndView(View.MemberCenter.ONLIONE_SHOP);
        view.addObject("goodsInfos", goodsInfos);
        view.addObject("hasStoreShop", JSONObject.fromObject(hasStoreShop));
        return view;
    }


    /**
     * 保存在线生成设置
    * @author 高国藩
    * @date 2016年5月21日 下午3:54:59
    * @param storeShop 信息
    * @return          状态吗
     */
    public BaseDto onlionShopAction(StoreShop storeShop) {
        storeShop.setIsDeleted(0);
        StoreShop hasStoreShop = storeShopMapper.selectByProties(storeShop);
        if (hasStoreShop!=null){
            storeShop.setsId(hasStoreShop.getsId());
            storeShopMapper.updateByPrimaryKeySelective(storeShop);
        }
        else {
            storeShopMapper.insertSelective(storeShop); 
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
}