package com.zefun.wechat.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.socket.TextMessage;
import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.swagger.SystemWebSocketHandler;
import com.zefun.common.utils.GenerateQrCodeUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.common.utils.XmlUtil;
import com.zefun.web.controller.BaseController;
import com.zefun.web.dto.BaseDto;
import com.zefun.wechat.service.WechatCallService;

/**
 * pay
* @author 高国藩
* @date 2016年5月10日 下午5:10:15
 */
@Controller
public class WechatPayController extends BaseController{

    /**微信支付*/
    @Autowired
    private WechatCallService wechatCallService;
    /**log*/
    private final Logger log = Logger.getLogger(WechatPayController.class);
    /** 事件通知操作*/
    @Autowired
    private SystemWebSocketHandler systemWebSocketHandler;
    /** NATIVE 支付名称*/
    private static String goodsName = "我道系统-门店充值";
    
    /**
     * 微信NATIVE支付,门店充值
    * @author 高国藩
    * @date 2016年5月10日 下午4:52:22
    * @param request    request
    * @param response   response
    * @return           展示支付二维码
     */
    @RequestMapping(value = Url.AppPay.REQUEST_APP_PAY, method=RequestMethod.GET)
    public ModelAndView getQRCodeUrl(HttpServletRequest request, HttpServletResponse response){
        String outTradeNo = StringUtil.getKey();
        String callback = "/" + Url.AppPay.REQUEST_APP_PAY_CALLBACK.replace("{outTradeNo}", outTradeNo);
        String code = wechatCallService.payByQrCode(goodsName, 300*100, outTradeNo, callback, request);
        String storeAccount = getStoreAccount(request);
        wechatCallService.updateRechargeRecord(storeAccount, 300, outTradeNo, 0, null);
        ModelAndView view = new ModelAndView("wechat/NATIVE");
        view.addObject("codeUrl", code);
        return view;
    }
    
    /**
     * 发起微信支付
    * @author 高国藩
    * @date 2016年5月10日 下午5:08:47
    * @param request request
    * @param response response
    * @param amount   钱
    * @return SUCCESS
     * @throws UnsupportedEncodingException e
     */
    @RequestMapping(value = Url.AppPay.REQUEST_APP_PAY, method=RequestMethod.POST)
    @ResponseBody
    public BaseDto getNativeCode(HttpServletRequest request, HttpServletResponse response, Integer amount) throws UnsupportedEncodingException {
        String storeAccount = getStoreAccount(request);
        String outTradeNo = StringUtil.getKey(); 
        String callback = "/" + Url.AppPay.REQUEST_APP_PAY_CALLBACK.replace("{outTradeNo}", outTradeNo).replace("{storeAccount}", storeAccount);
        String code = wechatCallService.payByQrCode(goodsName, amount*100, outTradeNo, callback, request);
        wechatCallService.updateRechargeRecord(storeAccount, amount, outTradeNo, 0, null);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, code);
    }
    
    /**
     * 处理支付的回调函数
    * @author 高国藩
    * @date 2016年5月10日 下午5:08:47
    * @param request request
    * @param response response
    * @param outTradeNo 订单标示
    * @param storeAccount 处理
    * @param data       微信处理回调数据
    * @return SUCCESS
     * @throws UnsupportedEncodingException 异常处理
     */
    @RequestMapping(value = Url.AppPay.REQUEST_APP_PAY_CALLBACK)
    @ResponseBody
    public String appCallBack(HttpServletRequest request, HttpServletResponse response, 
            @PathVariable String outTradeNo, @PathVariable String storeAccount, @RequestBody String data) throws UnsupportedEncodingException{
        log.info("微信支付回调了,32位随机字码为:"+outTradeNo+",store信息:"+storeAccount);
        wechatCallService.updateRechargeRecord(null, null, outTradeNo, 1, null);
        systemWebSocketHandler.sendMessageToUser(storeAccount, new TextMessage("充值成功".getBytes("UTF-8")));
        return "SUCCESS";
    }
    
    /**
     * 微信MICRO支付,展示页面
    * @author 高国藩
    * @date 2016年5月10日 下午4:52:22
    * @param request    request
    * @param response   response
    * @return           展示支付二维码
     */
    @RequestMapping(value = Url.AppPay.REQUEST_APP_MICRO, method=RequestMethod.GET)
    public ModelAndView payByMicro(HttpServletRequest request, HttpServletResponse response){
        ModelAndView view = new ModelAndView("wechat/MICRO");
        return view;
    }
    
    /**
     * 微信MICRO支付, 扫码授权
    * @author 高国藩
    * @date 2016年8月5日 下午3:54:56
    * @param request     request
    * @param authCode    authCode
    * @param fel         fel
    * @return            BaseDto
     */
    @RequestMapping(value = Url.AppPay.REQUEST_APP_MICRO, method=RequestMethod.POST)
    @ResponseBody
    public BaseDto payByMicro(HttpServletRequest request, String authCode, String fel){
        try {
            String result = wechatCallService.payByMicro(authCode, Integer.parseInt(fel), 
                    authCode, UUID.randomUUID().toString().replace("-", ""), request);
            Map<String, String> map = XmlUtil.getMapFromXML(result);
            String resultCode = map.get("result_code");
            String returnCode = map.get("return_code");
            String openId = map.get("openid");
            String outTradeNo = map.get("out_trade_no");
            log.info(resultCode+returnCode+openId+outTradeNo);
        } 
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new BaseDto(0, "支付成功");
    }
    
    /**
     * qr 生成扫描
    * @author 高国藩
    * @date 2016年5月10日 下午4:59:28
    * @param codeUrl   codeUrl
    * @param response  response
     */
    @RequestMapping(value = "qr_code.img")
    @ResponseBody
    public void getQRCode(String codeUrl, HttpServletResponse response){
        GenerateQrCodeUtil.encodeQrcode(codeUrl, response, 151, 151);
    }
    
}
