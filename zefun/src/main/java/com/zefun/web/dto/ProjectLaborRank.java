package com.zefun.web.dto;

import java.math.BigDecimal;

/**
  *@author Administrator
  *@date 2016年1月15日
  *@description 项目劳动业绩排行榜
  */
public class ProjectLaborRank {
	
	/**平均服务时长*/
	private BigDecimal avgServiceTime;
	
	/**所属部门id*/
	private Integer deptId;
	
	/**部门名称*/
	private String deptName;

    /**上期项目在店内的排名*/
	private String lastProjectRank;

    /**项目名称*/
	private String projectName;
	
	/**项目消费数量*/
	private Integer projectNum;

	/**项目在店内的排名*/
	private Integer projectRank;

	/**项目总业绩*/
	private BigDecimal projectSummary;
	
	public BigDecimal getAvgServiceTime() {
		return avgServiceTime;
	}
	
	public Integer getDeptId() {
		return deptId;
	}
	
	public String getDeptName() {
        return deptName;
    }

    public String getLastProjectRank() {
        return lastProjectRank;
    }

    public String getProjectName() {
		return projectName;
	}

	public Integer getProjectNum() {
		return projectNum;
	}

	public Integer getProjectRank() {
		return projectRank;
	}
	
	public BigDecimal getProjectSummary() {
		return projectSummary;
	}

	public void setAvgServiceTime(BigDecimal avgServiceTime) {
		this.avgServiceTime = avgServiceTime;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

	public void setLastProjectRank(String lastProjectRank) {
        this.lastProjectRank = lastProjectRank;
    }

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setProjectNum(Integer projectNum) {
		this.projectNum = projectNum;
	}

	public void setProjectRank(Integer projectRank) {
		this.projectRank = projectRank;
	}

	public void setProjectSummary(BigDecimal projectSummary) {
		this.projectSummary = projectSummary;
	}
	
}
