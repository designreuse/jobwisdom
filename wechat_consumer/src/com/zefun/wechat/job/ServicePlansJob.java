package com.zefun.wechat.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.zefun.wechat.service.RedisService;

/**
 * 
* @author 高国藩
* @date 2016年6月30日 上午10:56:21
 */
public class ServicePlansJob {
    /** 日志对象 */
    private Logger logger = Logger.getLogger(ServicePlansJob.class);
    /** 阿里请求接口 */
    private static String tbClientUrl = "http://gw.api.taobao.com/router/rest";
    /** 阿里请求接口 */
    private static String appKey = "23341881";
    /** 阿里请求接口 */
    private static String appSecret = "ebfd3e013a89513d4e64f7b56b801ae9";
    /** 阿里基础api对象 */
    private static TaobaoClient tbClient = new DefaultTaobaoClient(tbClientUrl, appKey, appSecret);
    /**redisService*/
    @Autowired
    private RedisService redisService;
    /** 会员标识对应微信openid的hash key */
    public static final String WECHAT_MEMBERID_TO_OPENID_KEY_HASH = "wechat_memberid_to_openid_key_hash";
    
    /**
     * 针对门店设置的服务计划进行会员实施的推送
    * @author 高国藩
    * @date Aug 23, 2015 9:04:47 PM
     */
	public void execute() {
	    logger.info("ServicePlansJob execute start... ");
	    try {
	        logger.info("服务计划已启动");
	        // 第一步 取得当前时间下的服务计划列表,并把所有的会员id,电话取出来,进行下列的推送,短信推送校验,是否需要短信推送
	        String openId = redisService.hget(WECHAT_MEMBERID_TO_OPENID_KEY_HASH, 56021);
	        
	        // 第二步 发送后,将该服务计划的内容进行伪删除, 防止下次执行进行筛选
	        
	        // 第三部 ,发送成功后 如果是短信进行的推送, 将门店下的短信数量进行一次减法操作
	        
	        // 发送成功后,进行门店页面的提醒功能, {中帮我道门店, 您的服务计划已经生效, 该门店下的会员分组:金卡会员将收取到推送消息, 推送形式为 短信}
	        
	        
	        logger.info(openId);
//	        this.sendVerifyCode("18734911338", "1234", "呵呵哒");
        } catch (Exception e) {
            logger.info("ServicePlansJob execute Exception! ");
        }
		logger.info("ServicePlansJob execute finish! ");
	}
	
	   /**
     * 发送短信验证码
    * @author 高国藩
    * @date Feb 26, 2016 9:00:49 PM
    * @param storeId    门店标识 
    * @param phone  手机号码
    * @param code   验证码
    * @param desc   描述信息
    * @return   成功返回true，失败返回false
     */
    public boolean sendVerifyCode(String phone, String code, String desc) {
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setSmsType("normal");
        req.setSmsFreeSignName("注册验证");
        req.setSmsParam("{\"code\":\"" + code + "\",\"product\":\"" + desc + "\"}");
        req.setRecNum(phone);
        req.setSmsTemplateCode("SMS_7465203");
        try {
            AlibabaAliqinFcSmsNumSendResponse response = tbClient.execute(req);
            logger.info(response.getBody());
            return true;
        }
        catch (ApiException e) {
            e.printStackTrace();
        }
        return false;
    }
}

