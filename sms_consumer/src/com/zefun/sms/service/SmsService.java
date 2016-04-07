package com.zefun.sms.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.zefun.sms.utils.App;
import com.zefun.sms.utils.HttpClientUtil;

import net.sf.json.JSONObject;


/**
* @author 张进军
* @date Sep 16, 2015 5:59:38 PM 
*/
@Service
public class SmsService {
    private Logger logger = Logger.getLogger(SmsService.class);
    
    public static void main(String[] args) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23315394", "1a389561f39b65923914946fa64104f2");
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setSmsType("normal");
        req.setSmsFreeSignName("智放");
        req.setSmsParam("{\"code\":\"1234\",\"desc\":\"注册会员\"}");
        req.setRecNum("18718921692");
        req.setSmsTemplateCode("SMS_5170058");
        AlibabaAliqinFcSmsNumSendResponse response = client.execute(req);
        System.out.println(response.getBody());
    }
    
    
    
    @Autowired
    private RedisService redisService;
    
    
    public boolean sendCodeVerifyCodeSms(int storeId, String code, String desc, String phone) {
        Map<String, String> templParams = new HashMap<String, String>();
        templParams.put("code", code);
        templParams.put("desc", desc);
        boolean f = sendSms(templParams, App.Sms.TEMPLATE_VERIFY_CODE, phone);
        if (f) {
            redisService.setex(App.Redis.PHONE_VERIFY_CODE_KEY_PRE + phone, code, 180);
        }
        return f;
    }

    private boolean sendSms(Map<String, String> templParams, String templateId, String phone) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("app_id", App.Sms.APP_ID);
        params.put("access_token", getAccesToken());
        params.put("acceptor_tel", phone);
        params.put("template_id", templateId);
        
        params.put("template_param", JSONObject.fromObject(templParams).toString());
        params.put("timestamp", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        
        String res = HttpClientUtil.sendPostReq(App.Sms.SEND_SMS_URL, params, null);
        JSONObject resJson = JSONObject.fromObject(res);
        if (!resJson.get("res_code").toString().equals("0")) {
            logger.error("send deliver sms failed, res_code: " + resJson.get("res_code").toString() + 
                    ", res_message: " + resJson.getString("res_message") + ", phone : " + phone);
            return false;
        }
        return true;
    }
    
    private String getAccesToken() {
        return redisService.hget(App.Redis.SMS_SERVICE_KEY_HASH, App.Redis.SMS_ACCESS_TOKEN_KEY);
    }
}
