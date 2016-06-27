package com.zefun.web.entity;

import java.math.BigDecimal;

public class ProjectStep {
    /** ��Ŀ�����ʶ */
    private Integer projectStepId;

    /** ��Ŀ�������� */
    private String projectStepName;

    /** ����ҵ�����㷽ʽ(1:�̶���2:����) */
    private Integer stepPerformanceType;

    /** ����ҵ������ */
    private BigDecimal stepPerformance;

    /** ��Ŀ��ʶ */
    private Integer projectId;

    /** �Ƿ�ɾ��(0:δɾ��,1:��ɾ��) */
    private Integer isDeleted;

    /** ����ʱ�� */
    private String createTime;

    /** �޸�ʱ�� */
    private String updateTime;

    /** �������˱�ʶ */
    private Integer lastOperatorId;

    /** ��λ��ʶ */
    private Integer positionId;

    public Integer getProjectStepId() {
        return projectStepId;
    }

    public void setProjectStepId(Integer projectStepId) {
        this.projectStepId = projectStepId;
    }

    public String getProjectStepName() {
        return projectStepName;
    }

    public void setProjectStepName(String projectStepName) {
        this.projectStepName = projectStepName == null ? null : projectStepName.trim();
    }

    public Integer getStepPerformanceType() {
        return stepPerformanceType;
    }

    public void setStepPerformanceType(Integer stepPerformanceType) {
        this.stepPerformanceType = stepPerformanceType;
    }

    public BigDecimal getStepPerformance() {
        return stepPerformance;
    }

    public void setStepPerformance(BigDecimal stepPerformance) {
        this.stepPerformance = stepPerformance;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
}