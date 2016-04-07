package com.zefun.wechat.job;

import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.wechat.service.RedisService;
import com.zefun.wechat.utils.App;
import com.zefun.wechat.utils.HttpClientUtil;

/**
* @author 张进军
* @date Aug 23, 2015 9:03:36 PM 
*/
public class WechatTokenJob {
    /** 日志对象 */
    private Logger logger = Logger.getLogger(WechatTokenJob.class);
    
    @Autowired
    private RedisService redisService;
    
    /**
     * 定时器执行内容
    * @author 张进军
    * @date Aug 23, 2015 9:04:47 PM
     */
	public void execute() {
	    logger.info("WechatTokenJob execute start... ");
	    try {
	        Set<String> storeSet = redisService.hkeys(App.Redis.STORE_WECHAT_APP_ID_KEY_HASH);
	        for (String storeId : storeSet) {
	            String appId = redisService.hget(App.Redis.STORE_WECHAT_APP_ID_KEY_HASH, storeId);
	            String appSecret = redisService.hget(App.Redis.STORE_WECHAT_APP_SECRET_KEY_HASH, storeId);
	            String getAccessTokenUrl = String.format(App.Wechat.GET_ACCESS_TOKEN_URL, new Object[] {appId, appSecret});
	            String accessTokenRes = HttpClientUtil.sendGetReq(getAccessTokenUrl, "utf-8");
	            JSONObject accessTokenJSON = JSONObject.fromObject(accessTokenRes);
	            if (!accessTokenJSON.containsKey("access_token")) {
	                continue;
	            }
	            String accessToken = accessTokenJSON.getString("access_token");
	            redisService.hset(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeId, accessToken);
	            
	            String getJsapiTicketUrl = String.format(App.Wechat.GET_JSAPI_TICKET_URL, new Object[] {accessToken});
	            String jsapiTicketRes = HttpClientUtil.sendGetReq(getJsapiTicketUrl, "utf-8");
	            JSONObject jsapiTicketJSON = JSONObject.fromObject(jsapiTicketRes);
	            String jsapiTicket = jsapiTicketJSON.getString("ticket");
	            redisService.hset(App.Redis.STORE_WECHAT_JSAPI_TICKET_KEY_HASH, storeId, jsapiTicket);
	        }
        }
        catch (Exception e) {
            logger.error("WechatTokenJob execute error : ", e);
        }
	    
		logger.info("WechatTokenJob execute finish! ");
	}
}

