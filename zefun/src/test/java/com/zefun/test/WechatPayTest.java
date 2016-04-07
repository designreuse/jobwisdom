package com.zefun.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.wechat.service.WechatCallService;

/**
 * 微信支付测试类
* @author 张进军
* @date Mar 11, 2016 5:01:22 PM
 */
public class WechatPayTest extends BaseTest {

    /** 微信支付服务对象 */
    @Autowired
    private WechatCallService wechatCallService;
    
    
    /**
     * 退款操作测试
    * @author 张进军
    * @date Mar 11, 2016 5:02:23 PM
     */
    @Test
    public void refund() {
        wechatCallService.refund("1457958175525945499");
    }
}
