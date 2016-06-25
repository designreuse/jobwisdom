package com.zefun.web.entity;

import java.math.BigDecimal;

public class ProjectCommission {
    /** ��ɱ�ʶ */
    private Integer commissionId;

    /** ��Ŀ��ʶ */
    private Integer projectId;

    /** ְλ��ʶ */
    private Integer levelId;

    /** ָ�����ֽ�ʽ(1:��ҵ������,2:���̶����) */
    private Integer assignCashType;

    /** ָ�����ֽ� */
    private BigDecimal assignCash;

    /** ָ����ˢ�� */
    private BigDecimal assignCard;

    /** �Ƿ�ɾ��(0:δɾ��,1:��ɾ��) */
    private Integer isDeleted;

    /** ����ʱ�� */
    private String createTime;

    /** �޸�ʱ�� */
    private String updateTime;

    /** �������˱�ʶ */
    private Integer lastOperatorId;

    /** ָ����� */
    private Integer commissionPrice;

    /** ��ָ����� */
    private Integer commissionNoPrice;

    /** ԤԼ���� */
    private Integer commissionCard;

    /** ��λ��ʶ */
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