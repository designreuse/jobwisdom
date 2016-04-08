package org.liufeng.weixin.main;

import net.sf.json.JSONObject;

import org.junit.Test;
import org.liufeng.weixin.pojo.AccessToken;
import org.liufeng.weixin.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TmSendMessage {
	private static Logger log = LoggerFactory.getLogger(TmSendMessage.class);
	// 第三方用户唯一凭证
	private static String appId = "wx57423593c068308c";
	// 第三方用户唯一凭证密钥
	private static String appSecret = "e18bd8ce8c3996374e697d3771c51493";
	
	private static String TmSendUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	private static String json = " {\"touser\":\"oHZz8tpK3YYo9r0X4kGi7wzW3DIs\",\"template_id\":\"rZq95mUBsKf6xDhT9Q1Fpk_PCkb1Dzjpfjz-HDEkYAg\",\"url\":\"http://weixin.qq.com/download\",\"topcolor\":\"#FF0000\",\"data\":{\"first\": {\"value\":\"恭喜你购买成功！\",\"color\":\"#173177\"},\"keynote1\":{\"value\":\"巧克力\",\"color\":\"#173177\"},\"keynote2\": {\"value\":\"39.8元\",\"color\":\"#173177\"},\"keynote3\": {\"value\":\"2014年9月16日\",\"color\":\"#173177\"},\"remark\":{\"value\":\"欢迎再次购买！\",\"color\":\"#173177\"}}}";
	
	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wx57423593c068308c";
		// 第三方用户唯一凭证密钥
		String appSecret = "e18bd8ce8c3996374e697d3771c51493";

		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		TmSendUrl = TmSendUrl.replace("ACCESS_TOKEN", at.getToken());
		JSONObject jsonObject = WeixinUtil.httpRequest(TmSendUrl, "POST",
				JSONObject.fromObject(json).toString());
		
	}
	/**
	 * 获得模板ID
	 */
	@Test
	public void getTM_id(){
		String url = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";
		String json = "{\"template_id_short\":\"TM00015\"}";
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		url = url.replace("ACCESS_TOKEN", at.getToken());
		JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST",
				JSONObject.fromObject(json).toString());
	}
	
}