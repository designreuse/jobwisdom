package com.zefun.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.zefun.common.consts.Url;
import com.zefun.common.utils.StringUtil;
import com.zefun.wechat.controller.WechatPayController;
import com.zefun.wechat.service.WechatCallService;

/**
 * 微信支付测试类
* @author 张进军
* @date Mar 11, 2016 5:01:22 PM
 */
public class WechatPayTest extends BaseTest {

    @Autowired
    private WechatPayController wechatPayController;
   /* *//** 微信支付服务对象 *//*
    @Autowired
    private WechatCallService wechatCallService;*/
    
    @Test  
    public void testView() throws Exception {  
        wechatPayController.appCallBack(request, response, "1464003619611956695");
    }  
    /**
     * 退款操作测试
    * @author 张进军
    * @date Mar 11, 2016 5:02:23 PM
     */
    @Test
    public void refund() {
        /*String callback = "/" + Url.Conference.WECHAT_CALLBACK_CONFERENCE_PAY.replace("{personnelId}", String.valueOf(1))
            .replace("{conferenceId}", String.valueOf(1));
        wechatCallService.payByQrCode("iphone 7s", 1, StringUtil.getKey(), callback, request);*/
//        wechatCallService.refund("1457958175525945499");
    }
}
