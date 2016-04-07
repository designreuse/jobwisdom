package com.lives.platform.web.dto;

import java.math.BigDecimal;

import com.lives.platform.web.entity.ProjectClasses;

public class ProjectInfoDto {

	private Integer projectId;

    private Integer classesId;

    private String projectName;

    private BigDecimal projectPrice;

    private Integer storesId;

    private String createTime;
    
    private String consume;
    
    private Integer projectNo;
    
    private ProjectClasses projectClasses;

    
    
	public String getConsume() {
		return consume;
	}

	public void setConsume(String consume) {
		this.consume = consume;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getClassesId() {
		return classesId;
	}

	public void setClassesId(Integer classesId) {
		this.classesId = classesId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public BigDecimal getProjectPrice() {
		return projectPrice;
	}

	public void setProjectPrice(BigDecimal projectPrice) {
		this.projectPrice = projectPrice;
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
		this.createTime = createTime;
	}

	public Integer getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(Integer projectNo) {
		this.projectNo = projectNo;
	}

	public ProjectClasses getProjectClasses() {
		return projectClasses;
	}

	public void setProjectClasses(ProjectClasses projectClasses) {
		this.projectClasses = projectClasses;
	}
    
    
	
}
