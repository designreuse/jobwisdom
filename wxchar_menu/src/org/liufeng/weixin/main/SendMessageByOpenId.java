package org.liufeng.weixin.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Test;
import org.liufeng.weixin.pojo.AccessToken;
import org.liufeng.weixin.pojo.Media_id;
import org.liufeng.weixin.pojo.SendMessages;
import org.liufeng.weixin.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 群发助手 先要知道发送人touser 也就是openId？具体怎么获取我还没有研究通
 * 
 * @author Lzh
 *
 */
public class SendMessageByOpenId {
	private static Logger log = LoggerFactory
			.getLogger(SendMessageByOpenId.class);
	private static String sendUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
	// 第三方用户唯一凭证
	private static String appId = "wx57423593c068308c";
	// 第三方用户唯一凭证密钥
	private static String appSecret = "e18bd8ce8c3996374e697d3771c51493";

	/**
	 * 群发一个文本消息
	 */
	@Test
	public void sendByMessageByOpenId() {
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		sendUrl = sendUrl.replace("ACCESS_TOKEN", at.getToken());
		System.out.println(at.getToken());
		List<String> touser = new ArrayList<String>();
		touser.add("oHZz8tpK3YYo9r0X4kGi7wzW3DIs");
		touser.add("oHZz8tgNdctySiRXqP8TF7x_s9Ws");
		Map<String, String> text = new HashMap<String, String>();
		text.put("content", "群发助手消息测试.");
		SendMessages messages = new SendMessages(touser, "text", text);

		JSONObject jsonObject = WeixinUtil.httpRequest(sendUrl, "POST",
				JSONObject.fromObject(messages).toString());
		System.out.println(jsonObject.get("errmsg") + "  "
				+ jsonObject.get("msg_id"));
	}

	/**
	 * 发送图文消息
	 */
	@Test
	public void sendByMessageHasPic() {
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		url = url.replace("ACCESS_TOKEN", at.getToken());

		//发送人员openid
		List<String> touser = new ArrayList<String>();
		touser.add("oHZz8tpK3YYo9r0X4kGi7wzW3DIs");
		touser.add("oHZz8tgNdctySiRXqP8TF7x_s9Ws");
		//通过上传得到media_id
		String media_id = UploadNews.UploadNews();
		Media_id media = new Media_id(media_id);
		SendMessages messages = new SendMessages(touser, "mpnews", media);
		System.out.println(JSONObject.fromObject(messages).toString());

		JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST",
				JSONObject.fromObject(messages).toString());
		
		System.out.println(jsonObject.get("errmsg") + "  "
				+ jsonObject.get("msg_id"));
	}

}
