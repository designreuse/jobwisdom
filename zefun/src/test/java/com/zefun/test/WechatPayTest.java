package com.zefun.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.common.consts.Url;
import com.zefun.common.utils.StringUtil;
import com.zefun.wechat.service.WechatCallService;

/**
 * 微信支付测试类
* @author 张进军
* @date Mar 11, 2016 5:01:22 PM
 */
public class WechatPayTest extends BaseTest {

   /* *//** 微信支付服务对象 *//*
    @Autowired
    private WechatCallService wechatCallService;*/
    
    
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
