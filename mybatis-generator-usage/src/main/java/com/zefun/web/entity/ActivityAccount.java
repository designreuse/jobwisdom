package com.zefun.web.entity;

public class ActivityAccount {
    /** ���ʶ */
    private Integer activityId;

    /** ����� */
    private String activityAccount;

    /** ���� */
    private String activityPaper;

    /** ��ʼʱ�� */
    private String startsTime;

    /** ����ʱ�� */
    private String endTime;

    /** ���� */
    private String activityNote;

    /** �ŵ�ID */
    private Integer storeId;

    /** ��ҵid */
    private String storeAccount;

    /** ����ʱ�� */
    private String createTime;

    /** �޸�ʱ�� */
    private String updateTime;

    /** ʱ��ɾ����0������1ɾ���� */
    private Integer isDeleted;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityAccount() {
        return activityAccount;
    }

    public void setActivityAccount(String activityAccount) {
        this.activityAccount = activityAccount == null ? null : activityAccount.trim();
    }

    public String getActivityPaper() {
        return activityPaper;
    }

    public void setActivityPaper(String activityPaper) {
        this.activityPaper = activityPaper == null ? null : activityPaper.trim();
    }

    public String getStartsTime() {
        return startsTime;
    }

    public void setStartsTime(String startsTime) {
        this.startsTime = startsTime == null ? null : startsTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getActivityNote() {
        return activityNote;
    }

    public void setActivityNote(String activityNote) {
        this.activityNote = activityNote == null ? null : activityNote.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreAccount() {
        return storeAccount;
    }

    public void setStoreAccount(String storeAccount) {
        this.storeAccount = storeAccount == null ? null : storeAccount.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}