package com.zefun.wechat.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.common.consts.App.Session;
import com.zefun.common.utils.XmlUtil;
import com.zefun.web.controller.BaseController;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.GoodsInfoDto;
import com.zefun.web.mapper.GoodsInfoMapper;
import com.zefun.wechat.service.UboxMallService;
import com.zefun.wechat.service.WechatCallService;


/**
 * 友宝商城控制器
* @author 张进军
* @date Mar 4, 2016 11:14:51 PM
 */
@Controller
public class UboxMallController extends BaseController {
    
    /** 友宝商城服务对象 */
    @Autowired
    private UboxMallService uboxMallService;
    
    /** 微信支付服务 */
    @Autowired
    private WechatCallService wechatCallService;
    
    /** 商品 */
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    
    /**日志*/
    private Logger logger = Logger.getLogger(UboxMallController.class);
    
    
    /**
     * 商品详情页面
    * @author 张进军
    * @date Jan 30, 2016 9:58:53 PM
    * @param storeId        门店标识
    * @param storeGoodsId   门店商品标识
    * @param request        请求对象
    * @param response       响应对象
    * @return   商品详情页面
     */
    @RequestMapping(value = Url.UboxMall.VIEW_GOODS_INFO)
    public ModelAndView goodsInfoView(int storeId, Integer storeGoodsId,
            HttpServletRequest request, HttpServletResponse response){
        String storeAccount = "";
        try {
            storeAccount = request.getSession().getAttribute(Session.STORE_ACCOUNT).toString();
        } 
        catch (Exception e) {
            storeAccount = getStoreAccountByStoreId(storeId);
        }
        String openId = getOpenId(storeAccount, 1, request, response);
        if (openId == null) {
            return null;
        }
        setJsapiSignData(storeAccount, request);
        /*String wechatPayOpenId = getOpenIdForYoumei(1, request, response);
        if (wechatPayOpenId == null) {
            return null;
        }*/
        Integer memberId = getUserIdByOpenId(openId);
        return uboxMallService.goodsInfoView(storeGoodsId, memberId);
    }
    

    /**
     * 发起微信支付
    * @author 高国藩
    * @date 2016年5月12日 下午5:24:35
    * @param storeId  storeId
    * @param goodsId  goodsId
    * @param request  request
    * @param response response
    * @return         微信签名
     */
    @RequestMapping(value = Url.AppPay.GOODSINFO_PAY, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto goodsPayAction(Integer storeId, Integer goodsId, HttpServletRequest request, HttpServletResponse response){
        String storeAccount = getStoreAccount(request);
        String openId = getOpenId(storeAccount, 1, request, response);
        if (openId == null) {
            return null;
        }
       /* String wechatPayOpenId = getOpenIdForYoumei(1, request, response);
        if (wechatPayOpenId == null) {
            return null;
        }*/
//        Integer memberId = getUserIdByOpenId(openId);
        
        GoodsInfoDto goodsInfoDto = goodsInfoMapper.selectGoodsAllByPrimaryKey(goodsId);
        String callback = "/" + Url.AppPay.GOODSINFO_PAY_CALLBACK;
        return wechatCallService.wepayForZefun(storeId, 4, goodsId, goodsInfoDto.getGoodsName(), 
                goodsInfoDto.getGoodsPrice().intValue(), openId, callback, request);
    }
    
    /**
     * 微信回调支付接口
    * @author 高国藩
    * @date 2016年5月12日 下午6:08:14
    * @param transactionId transactionId
    * @param request request
    * @param data    微信返回的信息
    * @return SUCCESS
     */
    @RequestMapping(value = Url.AppPay.GOODSINFO_PAY_CALLBACK)
    @ResponseBody
    public String callBackPayGoodsInfo(HttpServletRequest request, @RequestBody String data, @PathVariable String transactionId){
        Map<String, String> map = XmlUtil.getMapFromXML(data);
        String resultCode = map.get("result_code");
        String returnCode = map.get("return_code");
        String openId = map.get("openid");
        String outTradeNo = map.get("out_trade_no");
        logger.info("微信回调,支付成功通知");
        logger.info(data);
        return uboxMallService.callBackPayGoodsInfo(transactionId, outTradeNo, resultCode, returnCode, openId);
    }
    
    /**
     * 商品支付操作
    * @author 张进军
    * @date Jan 31, 2016 9:47:21 AM
    * @param storeId        门店标识
    * @param storeGoodsId   门店商品标识
    * @param payType        支付类型(1、金额＋积分，2、单金额)
    * @param request        请求对象
    * @param response       响应对象
    * @return   微信支付所需参数
     */
//    @RequestMapping(value = Url.UboxMall.ACTION_GOODS_PAY, method = RequestMethod.POST)
//    @ResponseBody
//    public BaseDto goodsPayAction(int storeId, Integer storeGoodsId, int payType,
//            HttpServletRequest request, HttpServletResponse response){
//        String openId = getOpenId(storeId, 1, request, response);
//        if (openId == null) {
//            return null;
//        }
//        String wechatPayOpenId = getOpenIdForYoumei(1, request, response);
//        if (wechatPayOpenId == null) {
//            return null;
//        }
//        Integer memberId = getUserIdByOpenId(openId);
//        return uboxMallService.goodsPayAction(wechatPayOpenId, memberId, storeGoodsId, payType, request);
//    }
    
    
    /**
     * 商品支付取消操作
    * @author 张进军
    * @date Mar 4, 2016 4:47:41 PM
    * @param transactionId  交易标识
    * @return   成功返回码未0，失败为其他返回码
     */
    @RequestMapping(value = Url.UboxMall.ACTION_GOODS_PAY_CANCEL, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto goodsPayCancelAction(String transactionId) {
        return uboxMallService.goodsPayCancelAction(transactionId);
    }
    
    
    /**
     * 商品支付成功微信异步回调接口
    * @author 张进军
    * @date Jan 31, 2016 9:47:21 AM
    * @param data   微信回调参数
    * @param transactionId  订单标识
    * @return   处理成功返回SUCCESS
     */
    @RequestMapping(value = Url.UboxMall.ACTION_GOODS_PAY_CALLBACK, method = RequestMethod.POST)
    @ResponseBody
    public String wechatCallBackGoodsPayAction(@RequestBody String data, @PathVariable String transactionId){
        return uboxMallService.wechatCallBackGoodsPayAction(data, transactionId);
    }
    
    
    /**
     * 商品支付成功微信同步回调接口
    * @author 张进军
    * @date Mar 4, 2016 9:44:26 PM
    * @param transactionId  交易标识
    * @return   支付成功页面
     */
    @RequestMapping(value = Url.UboxMall.VIEW_GOODS_PAY_CALLBACK)
    public ModelAndView wechatCallBackGoodsPayView(@PathVariable String transactionId) {
        return uboxMallService.wechatCallBackGoodsPayView(transactionId);
    }
    
    
    /**
     * 查看会员的订单列表
    * @author 张进军
    * @date Mar 4, 2016 11:29:10 PM
    * @param storeId    门店标识
    * @param request    请求对象
    * @param response   响应对象
    * @return   订单列表
     */
//    @RequestMapping(value = Url.UboxMall.VIEW_ORDER_LIST)
//    public ModelAndView orderListView(@PathVariable int storeId, 
//            HttpServletRequest request, HttpServletResponse response) {
//        String openId = getOpenId(storeId, 3, request, response);
//        if (openId == null) {
//            return null;
//        }
//        Integer memberId = getUserIdByOpenId(openId);
//        return uboxMallService.orderListView(memberId);
//    }
    
    
    /**
     * 交易详情
    * @author 张进军
    * @date Mar 4, 2016 11:25:42 PM
    * @param transactionId  交易标识
    * @return   详情页面
     */
    @RequestMapping(value = Url.UboxMall.VIEW_PAYMENT_INFO)
    public ModelAndView paymentInfoView(String transactionId) {
        return uboxMallService.paymentInfoView(transactionId);
    }
    
    
    /**
     * 设置售后服务人员
    * @author 张进军
    * @date Mar 5, 2016 7:40:41 PM
    * @param transactionId  交易标识
    * @param employeeId     服务员工标识
    * @return   成功返回码为0，失败为其他返回码
     */
    @RequestMapping(value = Url.UboxMall.ACTION_SET_SERVER, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto setServer(String transactionId, int employeeId) {
        return uboxMallService.setServer(transactionId, employeeId);
    }
    
    
    /**
     * 友宝交易回调接口
    * @author 张进军
    * @date Mar 5, 2016 9:01:34 PM
    * @param tran_id    交易id
    * @param code       状态码
    * @param msg        这是第几次发送通知
    * @param timestamp  尝试出货的时间，unix时间戳。
    * @param sign       签名字符串
    * @param try_number 这是第几次发送通知。如果友宝发送通知未能得到返回，则会间隔一段时间后重试，间隔算法是：interval = (try_number-1)*10，单位是分钟。try_number从1开始，最大等于5，之后将放弃重试。
    * @param app_trace_code 第三方应用传递的跟踪标识
    * @return   1为成功接收，0为失败
     */
    @RequestMapping(value = Url.UboxMall.ACTION_CALLBACK)
    @ResponseBody
    public int uboxCallback(String tran_id, String code, String msg, int timestamp, 
            String sign, int try_number, String app_trace_code) {
        return uboxMallService.uboxCallback(tran_id, code, msg, timestamp, sign, try_number, app_trace_code);
    }
    
}
