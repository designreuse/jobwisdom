package com.zefun.web.vo;

import java.math.BigInteger;

/**
  *@author Administrator
  *@date 2016年1月14日
  *@description 门店劳动业绩分类汇总数据
  */
public class DeptLaborPerformanceVo {
	/**项目消费数量*/
	private Integer consumedNum;
	
	/**部门名称*/
	private String deptName;
	
	/**项目消费总收入*/
	private BigInteger projectConsumedAmt;
	
	/**项目名称*/
	private String projectName;

	/**系列名称*/
	private String seriesName;

	public Integer getConsumedNum() {
		return consumedNum;
	}

	public String getDeptName() {
		return deptName;
	}

	public BigInteger getProjectConsumedAmt() {
		return projectConsumedAmt;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setConsumedNum(Integer consumedNum) {
		this.consumedNum = consumedNum;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void setProjectConsumedAmt(BigInteger projectConsumedAmt) {
		this.projectConsumedAmt = projectConsumedAmt;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
}
