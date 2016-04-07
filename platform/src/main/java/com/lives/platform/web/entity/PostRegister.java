package com.lives.platform.web.entity;

public class PostRegister {
    private Integer postRegisterId;

    private Integer postId;

    private Integer registerOrder;

    private String registerName;

    private String creationTime;

    private Integer creator;

    private Integer valid;

    public Integer getPostRegisterId() {
        return postRegisterId;
    }

    public void setPostRegisterId(Integer postRegisterId) {
        this.postRegisterId = postRegisterId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getRegisterOrder() {
        return registerOrder;
    }

    public void setRegisterOrder(Integer registerOrder) {
        this.registerOrder = registerOrder;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName == null ? null : registerName.trim();
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

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}