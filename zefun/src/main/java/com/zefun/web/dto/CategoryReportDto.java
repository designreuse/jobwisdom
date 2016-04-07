package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.List;

/**
* @author 乐建建
* @date 2016年1月18日 上午11:16:08 
*/
public class CategoryReportDto {
    /**系列id*/
	private Integer categoryId;
	/**系列名称*/
	private String categoryName;
	/**系列收入汇总*/
	private BigDecimal categoryIncomeSummary;
	/**系列下项目列表*/
	private List<ItemReportDto> projectList;
	/**系列下项目数量*/
	private Integer projectNumInCategory;
	/**卡金收入汇总*/
	private BigDecimal categoryCard;
	/**现金收入汇总*/
	private BigDecimal categoryCash;
	public Integer getProjectNumInCategory() {
		return projectNumInCategory;
	}
	public void setProjectNumInCategory(Integer projectNumInCategory) {
		this.projectNumInCategory = projectNumInCategory;
	}
	public BigDecimal getCategoryIncomeSummary() {
		return categoryIncomeSummary;
	}
	public void setCategoryIncomeSummary(BigDecimal categoryIncomeSummary) {
		this.categoryIncomeSummary = categoryIncomeSummary;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<ItemReportDto> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<ItemReportDto> projectList) {
		this.projectList = projectList;
	}
	
	public BigDecimal getCategoryCard() {
		return categoryCard;
	}
	public void setCategoryCard(BigDecimal categoryCard) {
		this.categoryCard = categoryCard;
	}
	public BigDecimal getCategoryCash() {
		return categoryCash;
	}
	public void setCategoryCash(BigDecimal categoryCash) {
		this.categoryCash = categoryCash;
	}
	
}
