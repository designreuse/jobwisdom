package com.zefun.wechat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.common.utils.GenerateQrCodeUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.controller.BaseController;
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
    @RequestMapping(value = Url.AppPay.REQUEST_APP_PAY)
    public ModelAndView getQRCodeUrl(HttpServletRequest request, HttpServletResponse response){
        String callback = "/" + Url.AppPay.REQUEST_APP_PAY_CALLBACK;
        String googsName = "iphone 7s";
        Integer totalFee = 1;
        String outTradeNo = StringUtil.getKey(); 
        String codeUrl = wechatCallService.payByQrCode(googsName, totalFee, outTradeNo, callback, request);
        ModelAndView view = new ModelAndView("wechat/NATIVE");
        view.addObject("codeUrl", codeUrl);
        return view;
    }
    
    /**
     * 处理支付的回调函数
    * @author 高国藩
    * @date 2016年5月10日 下午5:08:47
    * @param request request
    * @param response response
    * @return SUCCESS
     */
    @RequestMapping(value = Url.AppPay.REQUEST_APP_PAY_CALLBACK)
    @ResponseBody
    public String appCallBack(HttpServletRequest request, HttpServletResponse response){
        log.info("...........................微信进行回调了...........................");
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
