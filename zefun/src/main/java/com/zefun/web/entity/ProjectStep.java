package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 
* @author 骆峰
* @date 2016年6月25日 下午12:39:07
 */
public class ProjectStep {
    /** 项目步骤标识 */
    private Integer projectStepId;

    /** 项目步骤名称 */
    private String projectStepName;

    /** 步骤业绩计算方式(1:固定，2:比例) */
    private Integer stepPerformanceType;

    /** 步骤业绩计算 */
    private BigDecimal stepPerformance;

    /** 项目标识 */
    private Integer projectId;

    /** 是否删除(0:未删除,1:已删除) */
    private Integer isDeleted;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 最后操作人标识 */
    private Integer lastOperatorId;

    /** 岗位标识 */
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