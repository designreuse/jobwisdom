package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class ProjectInfo {
    private Integer projectInfoId;

    private Integer projectNo;

    private String projectName;

    private BigDecimal projectPrice;

    private Integer stroesId;

    private Integer statusEmp;
    
    private Integer statusMember;

    private Integer classesId;

    private String createTime;
    
    private Integer parentId;

    public Integer getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(Integer projectInfoId) {
        this.projectInfoId = projectInfoId;
    }

    public Integer getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(Integer projectNo) {
        this.projectNo = projectNo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public BigDecimal getProjectPrice() {
        return projectPrice;
    }

    public void setProjectPrice(BigDecimal projectPrice) {
        this.projectPrice = projectPrice;
    }

    public Integer getStroesId() {
        return stroesId;
    }

    public void setStroesId(Integer stroesId) {
        this.stroesId = stroesId;
    }

    public Integer getStatusEmp() {
		return statusEmp;
	}

	public void setStatusEmp(Integer statusEmp) {
		this.statusEmp = statusEmp;
	}

	public Integer getStatusMember() {
		return statusMember;
	}

	public void setStatusMember(Integer statusMember) {
		this.statusMember = statusMember;
	}

	public Integer getClassesId() {
        return classesId;
    }

    public void setClassesId(Integer classesId) {
        this.classesId = classesId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
    
}