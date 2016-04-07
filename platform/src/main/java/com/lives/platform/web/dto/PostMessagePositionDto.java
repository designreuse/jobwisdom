package com.lives.platform.web.dto;

import java.util.List;

import com.lives.platform.web.entity.Position;

public class PostMessagePositionDto {
	
	private Integer postMessageId;

    private String postMessageName;
    
    private Integer postMessageNo;

    private String creationTime;

    private Integer creator;

    private Integer state;

    private Integer storesId;
    
    private List<Position> position;

    
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
		this.postMessageName = postMessageName;
	}

	public Integer getPostMessageNo() {
		return postMessageNo;
	}

	public void setPostMessageNo(Integer postMessageNo) {
		this.postMessageNo = postMessageNo;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
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

	public List<Position> getPosition() {
		return position;
	}

	public void setPosition(List<Position> position) {
		this.position = position;
	}

	
    
    
	
}
