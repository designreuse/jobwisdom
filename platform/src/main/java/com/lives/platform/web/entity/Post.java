package com.lives.platform.web.entity;

public class Post {
    private Integer postId;

    private Integer postMessageId;

    private Integer valid;

    
    
    
    public Integer getPostMessageId() {
		return postMessageId;
	}

	public void setPostMessageId(Integer postMessageId) {
		this.postMessageId = postMessageId;
	}

	public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}