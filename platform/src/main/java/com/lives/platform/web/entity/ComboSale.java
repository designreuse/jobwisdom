package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class ComboSale {
    private Integer comboSaleId;

    private Integer comboId;

    private Integer userId;

    private BigDecimal orderAmount;

    private BigDecimal realIncomeAmount;

    private BigDecimal payableAmount;

    private BigDecimal saleAmount;

    private Integer orderType;

    private Integer storesId;

    private Integer signBillId;

    private String createTime;

    public Integer getComboSaleId() {
        return comboSaleId;
    }

    public void setComboSaleId(Integer comboSaleId) {
        this.comboSaleId = comboSaleId;
    }

    public Integer getComboId() {
        return comboId;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getStoresId() {
        return storesId;
    }

    public void setStoresId(Integer storesId) {
        this.storesId = storesId;
    }

    public Integer getSignBillId() {
        return signBillId;
    }

    public void setSignBillId(Integer signBillId) {
        this.signBillId = signBillId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}