package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class SignBill {
    private Integer signBillId;

    private Integer orderInfoId;

    private BigDecimal amount;

    private Integer employeeId;

    private String signBillTime;

    private Integer storesId;

    private String createTime;

    public Integer getSignBillId() {
        return signBillId;
    }

    public void setSignBillId(Integer signBillId) {
        this.signBillId = signBillId;
    }

    public Integer getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(Integer orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getSignBillTime() {
        return signBillTime;
    }

    public void setSignBillTime(String signBillTime) {
        this.signBillTime = signBillTime == null ? null : signBillTime.trim();
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