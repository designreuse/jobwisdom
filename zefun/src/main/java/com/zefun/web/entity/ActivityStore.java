package com.zefun.web.entity;

/**
 * 
* @author 骆峰
* @date 2016年7月8日 上午10:31:09
 */
public class ActivityStore {
    /** 门店活动标识 */
    private Integer activityStoreId;

    /** 门店活动名称 */
    private String activityStoreName;

    /** 活动开始时间 */
    private String statusTime;

    /** 活动结束时间 */
    private String sotpTime;

    /** 简述 */
    private String note;

    /** 门店 */
    private String storeId;

    /** 描述 */
    private String notes;

    /** 企业标识 */
    private String storeAccount;

    /** （0整除，1删除） */
    private Integer isDeleted;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;
    
    /** 状态 */
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getActivityStoreId() {
        return activityStoreId;
    }

    public void setActivityStoreId(Integer activityStoreId) {
        this.activityStoreId = activityStoreId;
    }

    public String getActivityStoreName() {
        return activityStoreName;
    }

    public void setActivityStoreName(String activityStoreName) {
        this.activityStoreName = activityStoreName == null ? null : activityStoreName.trim();
    }

    public String getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(String statusTime) {
        this.statusTime = statusTime == null ? null : statusTime.trim();
    }

    public String getSotpTime() {
        return sotpTime;
    }

    public void setSotpTime(String sotpTime) {
        this.sotpTime = sotpTime == null ? null : sotpTime.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId == null ? null : storeId.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public String getStoreAccount() {
        return storeAccount;
    }

    public void setStoreAccount(String storeAccount) {
        this.storeAccount = storeAccount == null ? null : storeAccount.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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
}