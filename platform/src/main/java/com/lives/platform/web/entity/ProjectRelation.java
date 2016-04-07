package com.lives.platform.web.entity;

import java.math.BigDecimal;
import java.util.List;

import com.lives.platform.web.dto.PositionDto;

public class ProjectRelation {
    private Integer projectRelationId;

    private Integer categoryNo;

    private String categoryName;

    private Integer type;

    private Integer projectInfoId;
    
    private String projectIdSet;

    private String stroesIdSet;

    private BigDecimal projectPrice;

    private String positionInfo;

    private String createTime;
    
    private List<PositionDto> positionDtos;

    public Integer getProjectRelationId() {
        return projectRelationId;
    }

    public void setProjectRelationId(Integer projectRelationId) {
        this.projectRelationId = projectRelationId;
    }

    public Integer getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(Integer categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(Integer projectInfoId) {
        this.projectInfoId = projectInfoId;
    }
    
    public String getProjectIdSet() {
		return projectIdSet;
	}

	public void setProjectIdSet(String projectIdSet) {
		this.projectIdSet = projectIdSet;
	}

	public String getStroesIdSet() {
        return stroesIdSet;
    }

    public void setStroesIdSet(String stroesIdSet) {
        this.stroesIdSet = stroesIdSet == null ? null : stroesIdSet.trim();
    }

	public BigDecimal getProjectPrice() {
		return projectPrice;
	}

	public void setProjectPrice(BigDecimal projectPrice) {
		this.projectPrice = projectPrice;
	}

	public String getPositionInfo() {
        return positionInfo;
    }

    public void setPositionInfo(String positionInfo) {
        this.positionInfo = positionInfo == null ? null : positionInfo.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

	public List<PositionDto> getPositionDtos() {
		return positionDtos;
	}

	public void setPositionDtos(List<PositionDto> positionDtos) {
		this.positionDtos = positionDtos;
	}
    
    
}