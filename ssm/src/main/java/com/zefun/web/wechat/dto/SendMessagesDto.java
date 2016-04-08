package com.zefun.web.wechat.dto;

import java.util.List;
import java.util.Map;

/**
 * 群发消息内容
* @author 高国藩
* @date 2015年8月11日 下午2:27:17
 */
public class SendMessagesDto {
    
    /** 发送者集合*/
    private List<String> touser;
     /** 发送消息类型*/
    private String msgtype;
     /** 发送图文ID*/
    private MediaIdDto mpnews;
     /** 发送文本内容*/
    private Map<String, String> text;
    /** 分组群发消息 */
    private GroupDto filter;

	public GroupDto getFilter() {
		return filter;
	}

	public void setFilter(GroupDto filter) {
		this.filter = filter;
	}

	public MediaIdDto getMpnews() {
		return mpnews;
	}

	public void setMpnews(MediaIdDto mpnews) {
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

	public SendMessagesDto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 群发文本
	 * 
	 * @param touser
	 * @param msgtype
	 * @param text
	 */
	public SendMessagesDto(List<String> touser, String msgtype,
			Map<String, String> text) {
		super();
		this.touser = touser;
		this.msgtype = msgtype;
		this.text = text;
	}

	/**
	 * 群发图文
	 * 
	 * @param touser
	 * @param msgtype
	 * @param mpnews
	 */
	public SendMessagesDto(List<String> touser, String msgtype,
			MediaIdDto mpnews) {
		super();
		this.touser = touser;
		this.msgtype = msgtype;
		this.mpnews = mpnews;
	}
}