package org.liufeng.weixin.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * 群发消息,根据id来发送,而非模板 "text": { "content": "hello from boxer."}
 * 
 * @author Lzh
 *
 */
public class SendMessages {
	private List<String> touser;
	private String msgtype;
	private Media_id mpnews;
	private Map<String, String> text;
	
	/**
	 * @return the mpnews
	 */
	public Media_id getMpnews() {
		return mpnews;
	}

	/**
	 * @param mpnews the mpnews to set
	 */
	public void setMpnews(Media_id mpnews) {
		this.mpnews = mpnews;
	}

	public List<String> getTouser() {
		return touser;
	}

	public void setTouser(List<String> touser) {
		this.touser = touser;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public Map<String, String> getText() {
		return text;
	}

	public void setText(Map<String, String> text) {
		this.text = text;
	}

	public SendMessages() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 群发文本
	 * @param touser
	 * @param msgtype
	 * @param text
	 */
	public SendMessages(List<String> touser, String msgtype,
			Map<String, String> text) {
		super();
		this.touser = touser;
		this.msgtype = msgtype;
		this.text = text;
	}

	/**
	 * 群发图文
	 * @param touser
	 * @param msgtype
	 * @param mpnews
	 */
	public SendMessages(List<String> touser, String msgtype, Media_id mpnews) {
		super();
		this.touser = touser;
		this.msgtype = msgtype;
		this.mpnews = mpnews;
	}

	public static void main(String[] args) {
		List<String> touser = new ArrayList<String>();
		touser.add("oHZz8tpK3YYo9r0X4kGi7wzW3DIs");
		touser.add("oHZz8tgNdctySiRXqP8TF7x_s9Ws");
		Map<String, String> text = new HashMap<String, String>();
		text.put("content", "老婆,宝贝喝水.");
		SendMessages messages = new SendMessages(touser, "text", text);
		System.out.println(JSONObject.fromObject(messages).toString());
	}
}
