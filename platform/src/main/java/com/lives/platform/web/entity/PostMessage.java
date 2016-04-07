package com.lives.platform.web.entity;

public class PostMessage {
    private Integer postMessageId;

    private String postMessageName;
    
    private Integer postMessageNo;

    private String creationTime;

    private Integer creator;

    private Integer state;

    private Integer storesId;

    
    
    
    public Integer getPostMessageNo() {
		return postMessageNo;
	}

	public void setPostMessageNo(Integer postMessageNo) {
		this.postMessageNo = postMessageNo;
	}

	public Integer getPostMessageId() {
        return postMessageId;
    }

    public void setPostMessageId(Integer postMessageId) {
        this.postMessageId = postMessageId;
    }

    public String getPostMessageName() {
        return postMessageName;
    }

    public void setPostMessageName(String postMessageName) {
        this.postMessageName = postMessageName == null ? null : postMessageName.trim();
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime == null ? null : creationTime.trim();
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getStoresId() {
        return storesId;
    }

    public void setStoresId(Integer storesId) {
        this.storesId = storesId;
    }
}