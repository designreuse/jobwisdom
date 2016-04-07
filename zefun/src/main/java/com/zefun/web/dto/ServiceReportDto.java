package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.List;

/**
* @author 乐建建
* @date 2016年1月18日 上午11:06:35 
*/
public class ServiceReportDto {
    /**部门下系列列表*/
	private List<CategoryReportDto> categoryList;
	/**部门下系列数*/
	private Integer categoryNumInDept;
	/**部门id*/
	private Integer deptId;
	
	/**部门收入*/
	private BigDecimal deptIncome;
	
	/**部门名称*/
	private String deptName;
    /**部门项目销量*/
	private Integer deptSales;
    public List<CategoryReportDto> getCategoryList() {
		return categoryList;
	}
	public Integer getCategoryNumInDept() {
        return categoryNumInDept;
    }
	public Integer getDeptId() {
		return deptId;
	}
	public BigDecimal getDeptIncome() {
		return deptIncome;
	}
	public String getDeptName() {
		return deptName;
	}
	public Integer getDeptSales() {
        return deptSales;
    }

	public void setCategoryList(List<CategoryReportDto> categoryList) {
		this.categoryList = categoryList;
	}
    public void setCategoryNumInDept(Integer categoryNumInDept) {
        this.categoryNumInDept = categoryNumInDept;
    }
    public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public void setDeptIncome(BigDecimal deptIncome) {
		this.deptIncome = deptIncome;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public void setDeptSales(Integer deptSales) {
        this.deptSales = deptSales;
    }

}
