package com.zefun.web.dto;

import java.math.BigDecimal;

/**
* @author 乐建建
* @date 2016年1月18日 上午11:20:25 
*/
public class ProjectSalesDto {
    /**项目id*/
	private Integer projectId;
	/**项目销量*/
	private Integer salesCount;
	/**项目收入*/
	private BigDecimal salesAmount;
	/**项目销量增长比率*/
	private BigDecimal countRate;
	/**项目收入增长率*/
	private BigDecimal amountRate;
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getSalesCount() {
		return salesCount;
	}
	public void setSalesCount(Integer salesCount) {
		this.salesCount = salesCount;
	}
	public BigDecimal getSalesAmount() {
		return salesAmount;
	}
	public void setSalesAmount(BigDecimal salesAmount) {
		this.salesAmount = salesAmount;
	}
	public BigDecimal getCountRate() {
		return countRate;
	}
	public void setCountRate(BigDecimal countRate) {
		this.countRate = countRate;
	}
	public BigDecimal getAmountRate() {
		return amountRate;
	}
	public void setAmountRate(BigDecimal amountRate) {
		this.amountRate = amountRate;
	}
}
