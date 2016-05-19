package com.zefun.web.entity;

/**
 * 消息封装类
* @author 老王
* @date 2016年5月17日 下午3:59:15
 */
public class SimpleMessage {
	/**
	 */
    private String body;
    /**
     * 
     */
    private String msgId;
    /**
     * 
     */
    private String bornTime;
    /**
     * 
     */
    private String msgHandle;
    /**
     * 
     */
    private int reconsumeTimes;
    /**
     * 
     */
    private String tag;
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getTag() {
        return tag;
    }
    public int getReconsumeTimes() {
        return reconsumeTimes;
    }
    public void setReconsumeTimes(int reconsumeTimes) {
        this.reconsumeTimes = reconsumeTimes;
    }
    public void setMsgHandle(String msgHandle) {
        this.msgHandle = msgHandle;
    }
    public String getMsgHandle() {
        return msgHandle;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getMsgId() {
        return msgId;
    }
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    public String getBornTime() {
        return bornTime;
    }
    public void setBornTime(String bornTime) {
        this.bornTime = bornTime;
    }
}