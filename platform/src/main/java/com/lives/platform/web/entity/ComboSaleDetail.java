package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class ComboSaleDetail {
    private Integer comboSaleDetailId;

    private Integer comboSaleId;

    private Integer employeeId;

    private Integer projectId;

    private Integer state;

    private BigDecimal orderAmount;

    private BigDecimal realIncomeAmount;

    private BigDecimal payableAmount;

    private BigDecimal saleAmount;

    private Integer type;

    private BigDecimal paidInPerformance;

    private Integer performanceDistribution;

    private String createTime;

    public Integer getComboSaleDetailId() {
        return comboSaleDetailId;
    }

    public void setComboSaleDetailId(Integer comboSaleDetailId) {
        this.comboSaleDetailId = comboSaleDetailId;
    }

    public Integer getComboSaleId() {
        return comboSaleId;
    }

    public void setComboSaleId(Integer comboSaleId) {
        this.comboSaleId = comboSaleId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getRealIncomeAmount() {
        return realIncomeAmount;
    }

    public void setRealIncomeAmount(BigDecimal realIncomeAmount) {
        this.realIncomeAmount = realIncomeAmount;
    }

    public BigDecimal getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(BigDecimal payableAmount) {
        this.payableAmount = payableAmount;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getPaidInPerformance() {
        return paidInPerformance;
    }

    public void setPaidInPerformance(BigDecimal paidInPerformance) {
        this.paidInPerformance = paidInPerformance;
    }

    public Integer getPerformanceDistribution() {
        return performanceDistribution;
    }

    public void setPerformanceDistribution(Integer performanceDistribution) {
        this.performanceDistribution = performanceDistribution;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}