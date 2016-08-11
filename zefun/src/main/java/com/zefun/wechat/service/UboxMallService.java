package com.zefun.wechat.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.UboxApiUtil;
import com.zefun.common.utils.XmlUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeBaseDto;
import com.zefun.web.dto.GoodsInfoDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.MemberSubAccountDto;
import com.zefun.web.dto.ubox.UboxTransactionDto;
import com.zefun.web.entity.AccountGoods;
import com.zefun.web.entity.CouponInfo;
import com.zefun.web.entity.GoodsInfo;
import com.zefun.web.entity.GoodsStock;
import com.zefun.web.entity.GoodsStockKey;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.OrderInfo;
import com.zefun.web.entity.StockFlow;
import com.zefun.web.entity.StockFlowDetail;
import com.zefun.web.entity.TransactionInfo;
import com.zefun.web.entity.UboxMachineInfo;
import com.zefun.web.entity.UboxTransaction;
import com.zefun.web.mapper.AccountGoodsMapper;
import com.zefun.web.mapper.CouponInfoMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.GoodsInfoMapper;
import com.zefun.web.mapper.GoodsStockMapper;
import com.zefun.web.mapper.MemberSubAccountMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.OrderInfoMapper;
import com.zefun.web.mapper.StockFlowDetailMapper;
import com.zefun.web.mapper.StockFlowMapper;
import com.zefun.web.mapper.TransactionInfoMapper;
import com.zefun.web.mapper.UboxMachineInfoMapper;
import com.zefun.web.mapper.UboxTransactionMapper;
import com.zefun.web.service.MemberInfoService;
import com.zefun.web.service.RedisService;

import net.sf.json.JSONObject;

/**
 * 友宝商城服务类
* @author 张进军
* @date Mar 4, 2016 11:15:29 PM
 */
@Service
public class UboxMallService {
    /** 会员信息业务逻辑对象 */
    @Autowired
    private MemberInfoService memberInfoService;
    
    /** 微信API服务对象 */
    @Autowired
    private WechatCallService wechatCallService;
    
    /** 友宝交易信息操作对象 */
    @Autowired
    private UboxTransactionMapper uboxTransactionMapper;
    
    /** 友宝机器信息操作对象 */
    @Autowired
    private UboxMachineInfoMapper uboxMachineInfoMapper;
    
    /** 员工信息操作对象 */
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;
    
    /** 优惠券信息操作对象 */
    @Autowired
    private CouponInfoMapper couponInfoMapper;
    
    /** 优惠券信息操作对象 */
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    
    /** 订单处理 */
    @Autowired
    private TransactionInfoMapper transactionInfoMapper;
    
    /**redis*/
    @Autowired
    private RedisService redisService;
    
    /**订单*/
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    /**订单明细*/
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    /**商品库存信息*/
    @Autowired
    private GoodsStockMapper goodsStockMapper;
    
    /**会员关注列表*/
    @Autowired
    private MemberSubAccountMapper memberSubAccountMapper;
    
    /** 企业商品*/
    @Autowired
    private AccountGoodsMapper accountGoodsMapper;
    
    /** 库存管理*/
    @Autowired
    private StockFlowDetailMapper stockFlowDetailMapper;
    
    /** 库存管理*/
    @Autowired
    private StockFlowMapper stockFlowMapper;
    
    
    /**
     * 商品详情页面
    * @author 张进军
    * @date Jan 30, 2016 9:58:53 PM
    * @param storeGoodsId   门店商品标识
    * @param memberId       会员标识
    * @return   商品详情页面
     */
    public ModelAndView goodsInfoView(Integer storeGoodsId, Integer memberId){
        ModelAndView mav = new ModelAndView(View.UboxMall.GOODS_INFO);
        
        MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId, false);
        mav.addObject("memberInfo", memberInfo);
        
        GoodsInfoDto info = goodsInfoMapper.selectByPrimaryKey(storeGoodsId);
        mav.addObject("goodsInfo", info);
        
        mav.addObject("isBuy", true);
        Integer storeId = info.getStoreId();
        Integer aId = info.getaId();
        GoodsStockKey key = new GoodsStockKey();
        key.setaId(aId);
        key.setStoreId(storeId);
        GoodsStock goodsStock = goodsStockMapper.selectByPrimaryKey(key);
        if (goodsStock == null || goodsStock.getCount() <= 0){
            mav.addObject("isBuy", false);
        }
        
        if (memberId!=null){
        	
        	Map<String, Integer> selectMap = new HashMap<>();
            selectMap.put("accountId", memberId);
            selectMap.put("storeId", storeId);
            
            List<MemberSubAccountDto> subAccountList = memberSubAccountMapper.selectSubAccountListByAccountId(selectMap);
            mav.addObject("subAccountList", subAccountList);
            
            List<MemberSubAccountDto> buySubAccountList = subAccountList.stream()
                .filter(dto -> dto.getBalanceAmount().intValue()>=info.getGoodsPrice().intValue())
                .collect(Collectors.toList());
            mav.addObject("buySubAccountList", buySubAccountList);
        }
        
        return mav;
    }
    
    
//    /**
//     * 商品支付操作
//    * @author 张进军
//    * @date Jan 31, 2016 9:47:21 AM
//    * @param openId         微信用户标识
//    * @param memberId       会员标识
//    * @param storeGoodsId   门店商品标识
//    * @param payType        支付类型(1、金额＋积分，2、单金额)
//    * @param request        请求对象
//    * @return   微信支付所需参数
//     */
//    @Transactional
//    public BaseDto goodsPayAction(String openId, Integer memberId, Integer storeGoodsId, int payType, HttpServletRequest request){
//        UboxStoreGoodsDto storeGoods = uboxStoreGoodsMapper.selectGoodsInfoByStoreGoodsId(storeGoodsId);
//        MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(memberId, false);
//        Integer totalFee = 1;
//        boolean flag = payType == 1 && memberInfo != null && memberInfo.getBalanceIntegral() >= storeGoods.getStoreGoodsIntegral();
//        if (flag) {
//            totalFee = storeGoods.getStoreGoodsPrice();
//        }
//        else {
//            totalFee = storeGoods.getGoodsInfo().getUboxOriginalPrice();
//        }
//        
//        //查询门店售货机编码
//        String vmid = uboxMachineInfoMapper.selectVmidByStoreId(storeGoods.getStoreId());
//        int stock = UboxApiUtil.getGoodsStockByVmidAndGoodsId(vmid, storeGoods.getUboxGoodsId());
//        if (stock <= 0) {
//            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "该商品已售罄，正在火速补货中...");
//        }
//        
//        String transactionId = StringUtil.getKey();
//        String callback = Url.UboxMall.ACTION_GOODS_PAY_CALLBACK.replace("{transactionId}", transactionId);
//        BaseDto res = wechatCallService.pay(App.System.WECHAT_YOUMEI_STORE_ID, storeGoods.getGoodsInfo().getGoodsName(), 
//                totalFee, openId, transactionId, callback, request);
//        if (res.getCode() == App.System.API_RESULT_CODE_FOR_SUCCEES) {
//            UboxTransaction uboxTransaction = new UboxTransaction();
//            uboxTransaction.setTransactionId(transactionId);
//            uboxTransaction.setTransactionAmount(totalFee);
//            uboxTransaction.setGoodsId(storeGoodsId);
//            uboxTransaction.setUboxGoodsId(storeGoods.getUboxGoodsId());
//            uboxTransaction.setVmid(vmid);
//            uboxTransaction.setMemberId(memberId);
//            uboxTransaction.setPayChannel(1);
//            uboxTransaction.setPayStatus(1);
//            uboxTransaction.setRewardsCouponId(storeGoods.getRewardsCouponId());
//            uboxTransaction.setRewardsGiftAmount(storeGoods.getRewardsGiftAmount());
//            uboxTransaction.setCreateTime(DateUtil.getCurTime());
//            uboxTransactionMapper.insert(uboxTransaction);
//            
//            if (flag) {
//                memberInfoService.changeIntegralToMember(memberId, storeGoods.getStoreGoodsIntegral(), 1, "购买商品时抵扣", null, transactionId);
//            }
//        }
//        
//        return res;
//    }
    
    
    /**
     * 商品支付成功微信异步回调接口
    * @author 张进军
    * @date Mar 4, 2016 4:28:14 PM
    * @param data   微信回调参数
    * @param transactionId  订单标识
    * @return   处理成功返回SUCCESS
     */
    @Transactional
    public String wechatCallBackGoodsPayAction(String data, String transactionId){
        UboxTransaction uboxTransaction = uboxTransactionMapper.selectByPrimaryKey(transactionId);
        if (uboxTransaction.getPayStatus() == 1) {
            Integer memberId = uboxTransaction.getMemberId();
            //检查是否有赠送礼金
            if (uboxTransaction.getRewardsGiftAmount() > 0) {
                memberInfoService.presentGiftmoneyToMember(memberId, new BigDecimal(uboxTransaction.getRewardsGiftAmount()), "商城购物赠送");
            }
            //检查是否有赠送优惠券
            Integer couponId = uboxTransaction.getRewardsCouponId();
            if (couponId != null && couponId != 0) {
                memberInfoService.presentCouponToMember(memberId, couponId);
            }
            
            //调用友宝买单接口
            JSONObject res = UboxApiUtil.payment(uboxTransaction.getVmid(), uboxTransaction.getUboxGoodsId(), memberId, transactionId);
            if (res == null) {
                //友宝下单失败，作退款处理
                wechatCallService.refund(transactionId);
                uboxTransaction.setPayStatus(4);
            } 
            else {
                Map<String, String> map = XmlUtil.getMapFromXML(data);
                String wxTransactionId = map.get("transaction_id");
                
                uboxTransaction.setBoxNumber(res.getString("box_number"));
                uboxTransaction.setExpireTime(res.getInt("expire_time"));
                uboxTransaction.setPickupCode(res.getString("delivery_code"));
                uboxTransaction.setTranId(res.getString("tran_id"));
                uboxTransaction.setOutTradeNo(wxTransactionId);
                uboxTransaction.setPayStatus(2);
            }
            uboxTransactionMapper.updateByPrimaryKey(uboxTransaction);
        }
        return "SUCCESS";
    } 
    
    
    /**
     * 商品支付成功微信同步回调接口
    * @author 张进军
    * @date Mar 4, 2016 9:44:26 PM
    * @param transactionId  交易标识
    * @return   支付成功页面
     */
    public ModelAndView wechatCallBackGoodsPayView(String transactionId) {
        UboxTransaction uboxTransaction = uboxTransactionMapper.selectByPrimaryKey(transactionId);
        if (uboxTransaction.getPayStatus() == 2) {
            return paymentInfoView(transactionId);
        }
        return orderListView(uboxTransaction.getMemberId());
    }
    
    
    /**
     * 查看会员的订单列表
    * @author 张进军
    * @date Mar 4, 2016 11:29:10 PM
    * @param memberId   会员标识
    * @return   订单列表
     */
    public ModelAndView orderListView(int memberId) {
        ModelAndView mav = new ModelAndView(View.UboxMall.ORDER_LIST);
        List<UboxTransactionDto> transactionList = uboxTransactionMapper.selectTransactionListByMemberId(memberId);
        mav.addObject("transactionList", transactionList);
        return mav;
    }
    
    
    /**
     * 交易详情
    * @author 张进军
    * @date Mar 4, 2016 11:25:42 PM
    * @param transactionId  交易标识
    * @return   详情页面
     */
    public ModelAndView paymentInfoView(String transactionId) {
        ModelAndView mav = new ModelAndView(View.UboxMall.PAYMENT_INFO);
        UboxTransactionDto transactionInfo = uboxTransactionMapper.selectTransactionInfoByTransactionId(transactionId);
        UboxMachineInfo machineInfo = uboxMachineInfoMapper.selectByPrimaryKey(transactionInfo.getVmid());
        mav.addObject("transaction", transactionInfo);
        mav.addObject("machineInfo", machineInfo);
        
        if (transactionInfo.getServiceEmployeeId() != null) {
            EmployeeBaseDto employeeInfo = employeeInfoMapper.selectBaseInfoByEmployeeId(transactionInfo.getServiceEmployeeId());
            mav.addObject("employeeInfo", employeeInfo);
        }
        
        Integer couponId = transactionInfo.getRewardsCouponId();
        if (couponId != null && couponId != 0) {
            CouponInfo couponInfo = couponInfoMapper.selectNormalByCouponId(couponId);
            mav.addObject("couponInfo", couponInfo);
        }
        
        List<EmployeeBaseDto> employeeList = employeeInfoMapper.selectEmployeeListByStoreId(machineInfo.getStoreId());
        mav.addObject("employeeList", employeeList);
        
        return mav;
    }
    
    
    /**
     * 商品支付取消操作
    * @author 张进军
    * @date Mar 4, 2016 4:47:41 PM
    * @param transactionId  交易标识
    * @return   成功返回码未0，失败为其他返回码
     */
    @Transactional
    public BaseDto goodsPayCancelAction(String transactionId) {
        UboxTransaction uboxTransaction = uboxTransactionMapper.selectByPrimaryKey(transactionId);
        if (uboxTransaction.getPayStatus() == 1) {
            Integer memberId = uboxTransaction.getMemberId();
            //检查是否有使用积分抵扣，如果有，需要将积分返还
            int integralAmount = uboxTransaction.getTransactionIntegral();
            if (uboxTransaction.getTransactionIntegral() > 0) {
                memberInfoService.changeIntegralToMember(memberId, integralAmount, 2, "取消购买商品时返还", null, transactionId);
            }
            uboxTransaction.setPayStatus(3);
            uboxTransactionMapper.updateByPrimaryKey(uboxTransaction);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 设置售后服务人员
    * @author 张进军
    * @date Mar 5, 2016 7:40:41 PM
    * @param transactionId  交易标识
    * @param employeeId     服务员工标识
    * @return   成功返回码为0，失败为其他返回码
     */
    public BaseDto setServer(String transactionId, int employeeId) {
        BaseDto res = new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        UboxTransaction uboxTransaction = uboxTransactionMapper.selectByPrimaryKey(transactionId);
        
        if (uboxTransaction.getServiceEmployeeId() != null) {
            res.setCode(App.System.API_RESULT_CODE_FOR_FAIL);
            res.setMsg("您已经选择过售货服务人员啦");
        }
        else {
            uboxTransaction.setServiceEmployeeId(employeeId);
            uboxTransactionMapper.updateByPrimaryKey(uboxTransaction);
        }
        
        return res;
    }
    
    
    /**
     * 友宝交易回调接口
    * @author 张进军
    * @date Mar 5, 2016 9:01:34 PM
    * @param tran_id    交易id
    * @param code       状态码
    * @param msg        状态说明
    * @param timestamp  尝试出货的时间，unix时间戳。
    * @param sign       签名字符串
    * @param try_number 这是第几次发送通知。如果友宝发送通知未能得到返回，则会间隔一段时间后重试，
    *                   间隔算法是：interval = (try_number-1)*10，单位是分钟。try_number从1开始，最大等于5，之后将放弃重试。
    * @param app_trace_code 第三方应用传递的跟踪标识
    * @return   1为成功接收，0为失败
     */
    public int uboxCallback(String tran_id, String code, String msg, int timestamp, 
            String sign, int try_number, String app_trace_code) {
        UboxTransaction uboxTransaction = uboxTransactionMapper.selectTransactionInfoByUboxTranId(tran_id);
        uboxTransaction.setBoxStatus(2);
        uboxTransactionMapper.updateByPrimaryKey(uboxTransaction);
        return 1;
    }


    /**
     * 回调,将订单信息更新,将会员购买的商品进行入单
    * @author 高国藩
    * @date 2016年5月12日 下午8:57:29
    * @param transactionId  transactionId
    * @param outTradeNo     outTradeNo
    * @param resultCode     resultCode
    * @param returnCode     returnCode
    * @param openId         openId
    * @return               SUCCESS
     */
    @Transactional
    public String callBackPayGoodsInfo(String transactionId, String outTradeNo, String resultCode, String returnCode, String openId) {
        if (resultCode.equals("SUCCESS")&&returnCode.equals("SUCCESS")){
            String memberId = redisService.hget(App.Redis.WECHAT_OPENID_TO_USERID_KEY_HASH, openId);
            TransactionInfo transactionInfo = transactionInfoMapper.selectByPrimaryKey(transactionId);
            transactionInfo.setTransactionId(transactionId);
            transactionInfo.setPayChannel(1);
            transactionInfo.setPayStatus(2);
            transactionInfoMapper.updateByPrimaryKey(transactionInfo);
            //订单
            Integer storeId = transactionInfo.getStoreId();
            Integer goodsId = transactionInfo.getGoodsId();
            Integer amount = transactionInfo.getTransactionAmount();
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setStoreId(storeId);
            orderInfo.setOrderType(3);
            orderInfo.setMemberId(Integer.parseInt(memberId));
            orderInfo.setReceivableAmount(new BigDecimal(amount).divide(new BigDecimal(100)));
            orderInfo.setRealAmount(new BigDecimal(amount).divide(new BigDecimal(100)));
            orderInfo.setWechatAmount(new BigDecimal(amount).divide(new BigDecimal(100)));
            orderInfo.setOrderStatus(3);
            orderInfo.setCashCardType(1);
            orderInfo.setCreateTime(DateUtil.getCurDate());
            orderInfoMapper.insert(orderInfo);
            Integer orderId = orderInfo.getOrderId();
            //订单明细
            GoodsInfoDto goodsInfo = goodsInfoMapper.selectGoodsAllByPrimaryKey(goodsId);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setOrderType(2);
            orderDetail.setProjectId(goodsId);
            orderDetail.setProjectName(goodsInfo.getGoodsName());
            orderDetail.setProjectImage(goodsInfo.getGoodsImage());
            orderDetail.setOrderStatus(3);
            orderDetail.setCreateTime(DateUtil.getCurDate());
            orderDetail.setProjectPrice(new BigDecimal(amount).divide(new BigDecimal(100)));
            orderDetailMapper.insert(orderDetail);
            
            //商品销售
            GoodsInfo info = new GoodsInfo();
            info.setGoodsId(goodsInfo.getGoodsId());
            info.setSalesCount(goodsInfo.getSalesCount()+1);
            goodsInfoMapper.updateByPrimaryKeySelective(info);
            
            AccountGoods accountGoods = accountGoodsMapper.selectByPrimaryKey(goodsInfo.getaId());
            
            List<StockFlowDetail> stockFlowDetails = new ArrayList<>();
            
            StockFlow stockFlow = new StockFlow();
            stockFlow.setStockFlowId(2);
            stockFlow.setFlowType("商城销售");
            SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmm");
            stockFlow.setFlowNumber("ck" + sdf.format(new Date()));
            stockFlow.setFromStore(storeId);
            stockFlow.setCreateTime(DateUtil.getCurDate());
            stockFlow.setaIds(accountGoods.getGoodsId().toString());
            stockFlow.setStoreAccount(accountGoods.getStoreAccount());
            stockFlowMapper.insertSelective(stockFlow);
            
            
            StockFlowDetail stockFlowDetail = new StockFlowDetail();
            stockFlowDetail.setaId(goodsInfo.getaId());
            stockFlowDetail.setFlowCount(1);
            stockFlowDetail.setCostPrice(accountGoods.getCostPrice());
            stockFlowDetail.setFlowAmount(accountGoods.getCostPrice());
            stockFlowDetail.setStockType(2);
            stockFlowDetail.setFlowType("商城销售");
            stockFlowDetail.setFromStore(goodsInfo.getStoreId());
            stockFlowDetail.setStoreAccount(accountGoods.getStoreAccount());
            
            //商品库存
            GoodsStockKey key = new GoodsStockKey();
            key.setaId(goodsInfo.getaId());
            key.setStoreId(goodsInfo.getStoreId());
            GoodsStock goodsStock = goodsStockMapper.selectByPrimaryKey(key);
            
            // 记录发生事件商品库存
            stockFlowDetail.setGoodsStockCount(goodsStock.getCount());
            stockFlowDetails.add(stockFlowDetail);
            stockFlowDetails.stream().forEach(s -> {
                    s.setFlowNumber(stockFlow.getFlowNumber());
                    s.setCreateTime(DateUtil.getCurDate());
                    s.setIsDeleted(0);
                });
            stockFlowDetailMapper.insertStockFlowDetails(stockFlowDetails);
            
            goodsStock.setCount(goodsStock.getCount()-1);
            goodsStockMapper.updateByPrimaryKeySelective(goodsStock);
            
            return "SUCCESS";
        }
        return "FAILE";
    }

}