package com.zefun.sms.job;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.sms.service.RedisService;
import com.zefun.sms.utils.App;
import com.zefun.sms.utils.HttpClientUtil;

/**
* @author 张进军
* @date Aug 23, 2015 9:03:36 PM 
*/
public class SmsTokenJob {
    /** 日志对象 */
    private Logger logger = Logger.getLogger(SmsTokenJob.class);
    
    @Autowired
    private RedisService redisService;
    
    /**
     * 定时器执行内容
    * @author 张进军
    * @date Aug 23, 2015 9:04:47 PM
     */
	public void execute() {
	    logger.info("SmsTokenJob execute start... ");
	    Map<String, Object> params = new HashMap<String, Object>();
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", getRefreshToken());
        params.put("app_id", App.Sms.APP_ID);
        params.put("app_secret", App.Sms.APP_SECRET);
        params.put("state", "re");

        String res = HttpClientUtil.sendPostReq(App.Sms.ACCESS_TOKEN_URL, params, null);
        JSONObject resJson = JSONObject.fromObject(res);
        if (!resJson.get("res_code").toString().equals("0")) {
             logger.error("refresh 189 access_token by refresh_token: " + getRefreshToken() + 
                     " failed, res_code: " + resJson.get("res_code").toString() + ", res_message: " + resJson.getString("res_message"));
             logger.info("replay...");
             execute();
        }
        String accessToken = resJson.getString("access_token");
        String refreshToken = resJson.getString("refresh_token");
        refreshToken(accessToken, refreshToken);
        
		logger.info("SmsTokenJob execute finish! ");
	}
	
	private String getRefreshToken() {
        return redisService.hget(App.Redis.SMS_SERVICE_KEY_HASH, App.Redis.SMS_REFRESH_TOKEN_KEY);
    }
	
	private void refreshToken(String accessToken, String refreshToken) {
	    redisService.hset(App.Redis.SMS_SERVICE_KEY_HASH, App.Redis.SMS_ACCESS_TOKEN_KEY, accessToken);
	    redisService.hset(App.Redis.SMS_SERVICE_KEY_HASH, App.Redis.SMS_REFRESH_TOKEN_KEY, refreshToken);
	}
}

