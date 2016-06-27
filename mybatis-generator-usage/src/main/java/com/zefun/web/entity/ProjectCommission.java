package com.zefun.web.entity;

import java.math.BigDecimal;

public class ProjectCommission {
    /** 提成标识 */
    private Integer commissionId;

    /** 项目标识 */
    private Integer projectId;

    /** 职位标识 */
    private Integer levelId;

    /** 指定客现金方式(1:按业绩比例,2:按固定金额) */
    private Integer assignCashType;

    /** 指定客现金 */
    private BigDecimal assignCash;

    /** 指定客刷卡 */
    private BigDecimal assignCard;

    /** 是否删除(0:未删除,1:已删除) */
    private Integer isDeleted;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 最后操作人标识 */
    private Integer lastOperatorId;

    /** 指定提成 */
    private Integer commissionPrice;

    /** 非指定提成 */
    private Integer commissionNoPrice;

    /** 预约奖励 */
    private Integer commissionCard;

    /** 岗位标识 */
    private Integer positionId;

    public Integer getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(Integer commissionId) {
        this.commissionId = commissionId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getAssignCashType() {
        return assignCashType;
    }

    public void setAssignCashType(Integer assignCashType) {
        this.assignCashType = assignCashType;
    }

    public BigDecimal getAssignCash() {
        return assignCash;
    }

    public void setAssignCash(BigDecimal assignCash) {
        this.assignCash = assignCash;
    }

    public BigDecimal getAssignCard() {
        return assignCard;
    }

    public void setAssignCard(BigDecimal assignCard) {
        this.assignCard = assignCard;
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

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    public Integer getCommissionPrice() {
        return commissionPrice;
    }

    public void setCommissionPrice(Integer commissionPrice) {
        this.commissionPrice = commissionPrice;
    }

    public Integer getCommissionNoPrice() {
        return commissionNoPrice;
    }

    public void setCommissionNoPrice(Integer commissionNoPrice) {
        this.commissionNoPrice = commissionNoPrice;
    }

    public Integer getCommissionCard() {
        return commissionCard;
    }

    public void setCommissionCard(Integer commissionCard) {
        this.commissionCard = commissionCard;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
}