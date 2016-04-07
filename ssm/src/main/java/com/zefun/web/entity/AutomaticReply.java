package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class AutomaticReply {
	/** 消息ID */
	private Integer replyId;

	/** 图文消息MediaId */
	private String mediaId;

	/** 图文消息的标题 */
	private String title;

	/** 图文页的URL */
	private String url;

	/** 图文消息的描述 */
	private String description;

	/** 图片链接 */
	private String picUrl;

	/** 创建时间 */
	private String createTime;

	/** 门店ID */
	private Integer storeId;

	public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /**
	 * 
	* @author 洪秋霞
	* @date 2015年8月11日 上午11:57:20
	* @param mediaId mediaId
	* @param title 标题
	* @param url 路径
	* @param description 描述
	* @param picUrl 图片路径
	* @param createTime 创建时间
	* @param storeId 门店id
	 */
    public AutomaticReply(String mediaId, String title,
            String url, String description, String picUrl, String createTime,
            Integer storeId) {
        super();
        this.mediaId = mediaId;
        this.title = title;
        this.url = url;
        this.description = description;
        this.picUrl = picUrl;
        this.createTime = createTime;
        this.storeId = storeId;
    }
	
    /**
     * 无参构造
    * @author 高国藩
    * @date 2015年8月11日 下午6:00:08
     */
    public AutomaticReply() {
        // TODO Auto-generated constructor stub
    }
	

}