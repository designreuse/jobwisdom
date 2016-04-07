package com.lives.platform.web.entity;

public class Position {
    private Integer positionId;

    private String positionName;

    private Integer storesId;

    private String createTime;
    
    private Integer postMessageId;
    
    private Integer appointScale;
    
    private Integer appointMoney;
    
    private Integer nonAppointScale;
    
    private Integer nonAppointMoney;

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Integer getStoresId() {
		return storesId;
	}

	public void setStoresId(Integer storesId) {
		this.storesId = storesId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getPostMessageId() {
		return postMessageId;
	}

	public void setPostMessageId(Integer postMessageId) {
		this.postMessageId = postMessageId;
	}

	public Integer getAppointScale() {
		return appointScale;
	}

	public void setAppointScale(Integer appointScale) {
		this.appointScale = appointScale;
	}

	public Integer getAppointMoney() {
		return appointMoney;
	}

	public void setAppointMoney(Integer appointMoney) {
		this.appointMoney = appointMoney;
	}

	public Integer getNonAppointScale() {
		return nonAppointScale;
	}

	public void setNonAppointScale(Integer nonAppointScale) {
		this.nonAppointScale = nonAppointScale;
	}

	public Integer getNonAppointMoney() {
		return nonAppointMoney;
	}

	public void setNonAppointMoney(Integer nonAppointMoney) {
		this.nonAppointMoney = nonAppointMoney;
	}
    
    
}