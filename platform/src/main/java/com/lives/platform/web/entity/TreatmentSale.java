package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class TreatmentSale {
    private Integer treatmentSaleId;

    private Integer treatmentCourseId;

    private Integer userId;

    private BigDecimal orderAmount;

    private BigDecimal realIncomeAmount;

    private BigDecimal payableAmount;

    private BigDecimal saleAmount;

    private Integer orderType;

    private Integer howMany;

    private Integer storesId;

    private Integer signBillId;

    private String createTime;

    public Integer getTreatmentSaleId() {
        return treatmentSaleId;
    }

    public void setTreatmentSaleId(Integer treatmentSaleId) {
        this.treatmentSaleId = treatmentSaleId;
    }

    public Integer getTreatmentCourseId() {
        return treatmentCourseId;
    }

    public void setTreatmentCourseId(Integer treatmentCourseId) {
        this.treatmentCourseId = treatmentCourseId;
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

    public Integer getHowMany() {
        return howMany;
    }

    public void setHowMany(Integer howMany) {
        this.howMany = howMany;
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