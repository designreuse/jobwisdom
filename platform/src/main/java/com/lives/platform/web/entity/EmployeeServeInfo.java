package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class EmployeeServeInfo {
    private Integer employeeServeInfoId;

    private Integer saleRecord;

    private Integer employeeId;
    
    private Integer allot;

    private BigDecimal accountsCommission;

    private BigDecimal realCommission;

    private Integer isAppiont;

    private Integer storesId;
    
    private BigDecimal paidInPerformance;

    private String createTime;

    
    
    public BigDecimal getPaidInPerformance() {
		return paidInPerformance;
	}

	public void setPaidInPerformance(BigDecimal paidInPerformance) {
		this.paidInPerformance = paidInPerformance;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getEmployeeServeInfoId() {
        return employeeServeInfoId;
    }

    public void setEmployeeServeInfoId(Integer employeeServeInfoId) {
        this.employeeServeInfoId = employeeServeInfoId;
    }

    public Integer getSaleRecord() {
        return saleRecord;
    }

    public void setSaleRecord(Integer saleRecord) {
        this.saleRecord = saleRecord;
    }

    public Integer getAllot() {
        return allot;
    }

    public void setAllot(Integer allot) {
        this.allot = allot;
    }

    public BigDecimal getAccountsCommission() {
        return accountsCommission;
    }

    public void setAccountsCommission(BigDecimal accountsCommission) {
        this.accountsCommission = accountsCommission;
    }

    public BigDecimal getRealCommission() {
        return realCommission;
    }

    public void setRealCommission(BigDecimal realCommission) {
        this.realCommission = realCommission;
    }

    public Integer getIsAppiont() {
        return isAppiont;
    }

    public void setIsAppiont(Integer isAppiont) {
        this.isAppiont = isAppiont;
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
        this.createTime = createTime == null ? null : createTime.trim();
    }
}