package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class ProjectComboDetail {
    private Integer comboDetailId;

    private Integer comboId;

    private Integer projectId;

    private Integer storesId;

    private String createTime;
    
    private BigDecimal originalCost;
    
    private BigDecimal cost;

    public Integer getComboDetailId() {
        return comboDetailId;
    }

    public void setComboDetailId(Integer comboDetailId) {
        this.comboDetailId = comboDetailId;
    }

    public Integer getComboId() {
        return comboId;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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