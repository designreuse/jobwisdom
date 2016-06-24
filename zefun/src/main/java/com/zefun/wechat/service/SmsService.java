package com.zefun.wechat.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.zefun.common.consts.App;
import com.zefun.web.service.RedisService;


/**
 * 短信服务类
* @author 张进军
* @date Feb 26, 2016 8:48:40 PM
 */
@Service
public class SmsService {
    
    /** 阿里请求接口 */
    private static String tbClientUrl = "http://gw.api.taobao.com/router/rest";
    /** 阿里请求接口 */
    private static String appKey = "23341881";
    /** 阿里请求接口 */
    private static String appSecret = "ebfd3e013a89513d4e64f7b56b801ae9";
    
    /** 阿里基础api对象 */
    private static TaobaoClient tbClient = new DefaultTaobaoClient(tbClientUrl, appKey, appSecret);
    
    /** 日志记录对象 */
    private static Logger logger = Logger.getLogger(SmsService.class);
    
    /** redis服务对象 */
    @Autowired
    private RedisService redisService;
    
    
    /**
     * 发送短信验证码
    * @author 张进军
    * @date Feb 26, 2016 9:00:49 PM
    * @param storeId    门店标识 
    * @param phone  手机号码
    * @param code   验证码
    * @param desc   描述信息
    * @return   成功返回true，失败返回false
     */
    public boolean sendVerifyCode(int storeId, String phone, String code, String desc) {
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setSmsType("normal");
        req.setSmsFreeSignName("注册验证");
        req.setSmsParam("{\"code\":\"" + code + "\",\"product\":\"" + desc + "\"}");
        req.setRecNum(phone);
        req.setSmsTemplateCode("SMS_7465203");
        try {
            AlibabaAliqinFcSmsNumSendResponse response = tbClient.execute(req);
            redisService.setex(App.Redis.PHONE_VERIFY_CODE_KEY_PRE + phone, code, 180);
            logger.info(response.getBody());
            return true;
        }
        catch (ApiException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
