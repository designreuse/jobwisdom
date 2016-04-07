package com.zefun.web.dto;

import java.math.BigDecimal;

/**
  *@author Administrator
  *@date 2016年1月14日
  *@description 部门汇总相关数据
  */
public class DeptSummaryDto {
	
    /**部门id*/
    private Integer deptId;
    /**部门名称*/
    private String deptName;
    /**营业收入部分*/
    private BusinessIncomePart businessIncome;
    /**充值收入部分*/
    private ChargedIncomePart chargedIncome;
    /**营业折扣部分*/
    private BusinessDiscountPart businessDiscount;
    /**会员刷卡部分*/
    private BigDecimal cardConsumedAmt=new BigDecimal(0);
    /**添加套餐抵扣部分*/
    private BigDecimal comboDiscount=new BigDecimal(0);
    
    public BigDecimal getComboDiscount() {
        return comboDiscount;
    }
    public void setComboDiscount(BigDecimal comboDiscount) {
        this.comboDiscount = comboDiscount;
    }
    /**部门营业收入*/
    private BigDecimal incomes;
    /**部门营业扣减*/
    private BigDecimal expenses;
    /**部门实际收入*/
    private BigDecimal realIncomes;
    public BigDecimal getIncomes() {
        return incomes;
    }
    public void setIncomes(BigDecimal incomes) {
        this.incomes = incomes;
    }
    public BigDecimal getExpenses() {
        return expenses;
    }
    public void setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
    }
    public BigDecimal getRealIncomes() {
        return realIncomes;
    }
    public void setRealIncomes(BigDecimal realIncomes) {
        this.realIncomes = realIncomes;
    }
    public Integer getDeptId() {
        return deptId;
    }
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public BusinessIncomePart getBusinessIncome() {
        return businessIncome;
    }
    public void setBusinessIncome(BusinessIncomePart businessIncome) {
        this.businessIncome = businessIncome;
    }
    public ChargedIncomePart getChargedIncome() {
        return chargedIncome;
    }
    public void setChargedIncome(ChargedIncomePart chargedIncome) {
        this.chargedIncome = chargedIncome;
    }
    public BusinessDiscountPart getBusinessDiscount() {
        return businessDiscount;
    }
    public void setBusinessDiscount(BusinessDiscountPart businessDiscount) {
        this.businessDiscount = businessDiscount;
    }
    public BigDecimal getCardConsumedAmt() {
        return cardConsumedAmt;
    }
    public void setCardConsumedAmt(BigDecimal cardConsumedAmt) {
        this.cardConsumedAmt = cardConsumedAmt;
    }
}
