package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class Coupons {
    private Integer couponsId;

    private String couponsType;

    private String projectId;

    private BigDecimal amount;

    private Integer isUse;

    private Integer isRecord;

    private Integer stoteId;

    private String createUser;

    private String startEnd;

    private String endDate;

    private String createTime;

    public Integer getCouponsId() {
        return couponsId;
    }

    public void setCouponsId(Integer couponsId) {
        this.couponsId = couponsId;
    }

    public String getCouponsType() {
        return couponsType;
    }

    public void setCouponsType(String couponsType) {
        this.couponsType = couponsType == null ? null : couponsType.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public Integer getIsRecord() {
        return isRecord;
    }

    public void setIsRecord(Integer isRecord) {
        this.isRecord = isRecord;
    }

    public Integer getStoteId() {
        return stoteId;
    }

    public void setStoteId(Integer stoteId) {
        this.stoteId = stoteId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getStartEnd() {
        return startEnd;
    }

    public void setStartEnd(String startEnd) {
        this.startEnd = startEnd == null ? null : startEnd.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}