package com.zefun.wechat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.utils.GenerateQrCodeUtil;
import com.zefun.common.utils.StringUtil;
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
    
    /**
     * 微信NATIVE扫码支付
    * @author 高国藩
    * @date 2016年5月10日 下午4:52:22
    * @param request    request
    * @param response   response
    * @return           展示支付二维码
     */
    @RequestMapping(value = Url.AppPay.REQUEST_APP_PAY, method=RequestMethod.GET)
    public ModelAndView getQRCodeUrl(HttpServletRequest request, HttpServletResponse response){
        String googsName = "门店充值";
        Integer totalFee = 1;
        String outTradeNo = StringUtil.getKey(); 
        String callback = "/" + Url.AppPay.REQUEST_APP_PAY_CALLBACK.replace("{outTradeNo}", outTradeNo);
        String codeUrl = wechatCallService.payByQrCode(googsName, totalFee, outTradeNo, callback, request);
        ModelAndView view = new ModelAndView("wechat/NATIVE");
        view.addObject("codeUrl", codeUrl);
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
     */
    @RequestMapping(value = Url.AppPay.REQUEST_APP_PAY, method=RequestMethod.POST)
    @ResponseBody
    public BaseDto getNativeCode(HttpServletRequest request, HttpServletResponse response, Integer amount){
        String googsName = "门店充值";
        String outTradeNo = StringUtil.getKey(); 
        String callback = "/" + Url.AppPay.REQUEST_APP_PAY_CALLBACK.replace("{outTradeNo}", outTradeNo);
        String code = wechatCallService.payByQrCode(googsName, amount*100, outTradeNo, callback, request);
        String storeAccount = getStoreAccount(request);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, code);
    }
    
    /**
     * 处理支付的回调函数
    * @author 高国藩
    * @date 2016年5月10日 下午5:08:47
    * @param request request
    * @param response response
    * @param outTradeNo 订单标示
    * @return SUCCESS
     */
    @RequestMapping(value = Url.AppPay.REQUEST_APP_PAY_CALLBACK)
    @ResponseBody
    public String appCallBack(HttpServletRequest request, HttpServletResponse response, @PathVariable String outTradeNo){
        log.info("...........................微信进行回调了...........................outTradeNo............."+outTradeNo);
        return "SUCCESS";
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
        GenerateQrCodeUtil.encodeQrcode(codeUrl, response);
    }
    
}
