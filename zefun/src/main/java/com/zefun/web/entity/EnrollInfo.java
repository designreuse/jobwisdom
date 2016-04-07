package com.zefun.web.entity;

/**
 * 渠道会议报名表
* @author 高国藩
* @date 2016年1月9日 下午3:09:15
 */
public class EnrollInfo {
    /**人员ID*/
    private Integer personnelId;
    /**电话*/
    private String phone;
    /**名字*/
    private String name;
    /**门店名称*/
    private String storeName;
    /**门店类型(1：单店，2：连锁总店，3：连锁分店)*/
    private Integer storeType;
    /**会议ID*/
    private Integer conferenceId;
    /**微信标识*/
    private String openId;
    /**是否支付(0:未支付,1:已支付)*/
    private Integer isPay;
    /**上级推荐人*/
    private Integer refereeId;
    /**创建时间*/
    private String createTime;
    /**是否到场(0:未到场,1:已到场)*/
    private Integer isTurn;

    public Integer getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(Integer personnelId) {
        this.personnelId = personnelId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public Integer getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public Integer getRefereeId() {
        return refereeId;
    }

    public void setRefereeId(Integer refereeId) {
        this.refereeId = refereeId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Integer getIsTurn() {
        return isTurn;
    }

    public void setIsTurn(Integer isTurn) {
        this.isTurn = isTurn;
    }
    
    
}