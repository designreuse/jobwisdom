package com.zefun.web.entity;

/**
 * 渠道会议
* @author 高国藩
* @date 2016年1月8日 上午10:30:53
 */
public class ConferenceInfo {
    
    /**标示*/
    private Integer conferenceId;
    /**会议名称*/
    private String conferenceName;
    /**召开时间*/
    private String holdTime;
    /**会议结束时间*/
    private String endTime;
    /**报名开始*/
    private String registrationStartTime;
    /**报名结束*/
    private String registrationEndTime;
    /**地址*/
    private String address;
    /**人数上限*/
    private Integer peopleCount;
    /**报名费用*/
    private Integer registrationAmount;
    /**一级奖励*/
    private Integer mainAward;
    /**二级奖励*/
    private Integer branchAward;
    /**联系人名*/
    private String linkName;
    /**联系电话*/
    private String linkPhone;
    /**会议摘要*/
    private String conferenceDesc;
    /**渠道ID*/
    private Integer agentId;
    /**创建时间*/
    private String createTime;
    /**是否删除(0:未删除,1:已删除)*/
    private Integer isDeleted;

    public Integer getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getHoldTime() {
        return holdTime;
    }

    public void setHoldTime(String holdTime) {
        this.holdTime = holdTime == null ? null : holdTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getRegistrationStartTime() {
        return registrationStartTime;
    }

    public void setRegistrationStartTime(String registrationStartTime) {
        this.registrationStartTime = registrationStartTime == null ? null : registrationStartTime.trim();
    }

    public String getRegistrationEndTime() {
        return registrationEndTime;
    }

    public void setRegistrationEndTime(String registrationEndTime) {
        this.registrationEndTime = registrationEndTime == null ? null : registrationEndTime.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Integer getRegistrationAmount() {
        return registrationAmount;
    }

    public void setRegistrationAmount(Integer registrationAmount) {
        this.registrationAmount = registrationAmount;
    }

    public Integer getMainAward() {
        return mainAward;
    }

    public void setMainAward(Integer mainAward) {
        this.mainAward = mainAward;
    }

    public Integer getBranchAward() {
        return branchAward;
    }

    public void setBranchAward(Integer branchAward) {
        this.branchAward = branchAward;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName == null ? null : linkName.trim();
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone == null ? null : linkPhone.trim();
    }

    public String getConferenceDesc() {
        return conferenceDesc;
    }

    public void setConferenceDesc(String conferenceDesc) {
        this.conferenceDesc = conferenceDesc == null ? null : conferenceDesc.trim();
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }
    
    
}