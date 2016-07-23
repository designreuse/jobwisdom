package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 员工工资提成数据传输对象
* @author 张进军
* @date Jan 16, 2016 8:57:53 PM
 */
public class PayrollCommissionDto {
	/** 员工标识 */
    private Integer employeeId;
    
	/** 卡项提成 */
    private BigDecimal cardCommission;
    
    /** 卡项业绩 */
    private BigDecimal cardPerformance;
    
    /** 项目提成 */
    private BigDecimal projectCommission;
    
    /** 项目业绩 */
    private BigDecimal projectPerformance;
    
    /** 疗程提成 */
    private BigDecimal comboCommission;
    
    /** 疗程业绩 */
    private BigDecimal comboPerformance;
    
    /** 商品提成 */
    private BigDecimal goodsCommission;
    
    /** 商品业绩 */
    private BigDecimal goodsPerformance;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public BigDecimal getCardCommission() {
		return cardCommission;
	}

	public void setCardCommission(BigDecimal cardCommission) {
		this.cardCommission = cardCommission;
	}

	public BigDecimal getCardPerformance() {
		return cardPerformance;
	}

	public void setCardPerformance(BigDecimal cardPerformance) {
		this.cardPerformance = cardPerformance;
	}

	public BigDecimal getProjectCommission() {
		return projectCommission;
	}

	public void setProjectCommission(BigDecimal projectCommission) {
		this.projectCommission = projectCommission;
	}

	public BigDecimal getProjectPerformance() {
		return projectPerformance;
	}

	public void setProjectPerformance(BigDecimal projectPerformance) {
		this.projectPerformance = projectPerformance;
	}

	public BigDecimal getComboCommission() {
		return comboCommission;
	}

	public void setComboCommission(BigDecimal comboCommission) {
		this.comboCommission = comboCommission;
	}

	public BigDecimal getComboPerformance() {
		return comboPerformance;
	}

	public void setComboPerformance(BigDecimal comboPerformance) {
		this.comboPerformance = comboPerformance;
	}

	public BigDecimal getGoodsCommission() {
		return goodsCommission;
	}

	public void setGoodsCommission(BigDecimal goodsCommission) {
		this.goodsCommission = goodsCommission;
	}

	public BigDecimal getGoodsPerformance() {
		return goodsPerformance;
	}

	public void setGoodsPerformance(BigDecimal goodsPerformance) {
		this.goodsPerformance = goodsPerformance;
	}
    
}
