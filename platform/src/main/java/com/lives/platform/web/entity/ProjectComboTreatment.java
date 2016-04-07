package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class ProjectComboTreatment {
    private Integer treatmentCourseDetailId;

    private Integer treatmentCourseId;

    private Integer projectId;

    private Integer num;

    private Integer storesId;

    private String createTime;
    
    private BigDecimal originalCost;
    
    private BigDecimal cost;

    public Integer getTreatmentCourseDetailId() {
        return treatmentCourseDetailId;
    }

    public void setTreatmentCourseDetailId(Integer treatmentCourseDetailId) {
        this.treatmentCourseDetailId = treatmentCourseDetailId;
    }

    public Integer getTreatmentCourseId() {
        return treatmentCourseId;
    }

    public void setTreatmentCourseId(Integer treatmentCourseId) {
        this.treatmentCourseId = treatmentCourseId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getStoresId() {
        return storesId;
    }

    public void setStoresId(Integer storesId) {
        this.storesId = storesId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

	public BigDecimal getOriginalCost() {
		return originalCost;
	}

	public void setOriginalCost(BigDecimal originalCost) {
		this.originalCost = originalCost;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
    
}