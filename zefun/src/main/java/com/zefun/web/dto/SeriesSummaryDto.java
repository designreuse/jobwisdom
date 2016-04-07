package com.zefun.web.dto;

import java.math.BigDecimal;

/**
  *@author Administrator
  *@date 2016年1月14日
  *@description 部门下系列相关数据汇总 
  */
public class SeriesSummaryDto {
	/**系列所属的部门id*/
	private Integer deptId;
	
	/**系列名称*/
	private String seriesName;
	
	/**系列id*/
	private Integer seriesId;
	
	/**系列收入*/
	private BigDecimal seriesIncome;

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public Integer getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(Integer seriesId) {
		this.seriesId = seriesId;
	}

	public BigDecimal getSeriesIncome() {
		return seriesIncome;
	}

	public void setSeriesIncome(BigDecimal seriesIncome) {
		this.seriesIncome = seriesIncome;
	}
}
