package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class OrderInfo {
    private Integer orderInfoId;

    private String orderNo;

    private Integer userId;

    private BigDecimal orderAmount;

    private BigDecimal realIncomeAmount;

    private BigDecimal payableAmount;

    private BigDecimal saleAmount;

    private Integer orderType;

    private Integer commodityId;

    private Integer payType;

    private Integer storesId;

    private Integer signBillId;

    private String createTime;

    private BigDecimal gzBill;

    private Integer gzAuthorizationCode;

    private Integer gzEmployeeId;

    private BigDecimal qdBill;

    private Integer qdAuthorizationCode;

    private Integer qdEmployeeId;

    private BigDecimal cardBill;

    private BigDecimal cashBill;

    private BigDecimal memberBill;

    public Integer getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(Integer orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
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

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
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

    public BigDecimal getGzBill() {
        return gzBill;
    }

    public void setGzBill(BigDecimal gzBill) {
        this.gzBill = gzBill;
    }

    public Integer getGzAuthorizationCode() {
        return gzAuthorizationCode;
    }

    public void setGzAuthorizationCode(Integer gzAuthorizationCode) {
        this.gzAuthorizationCode = gzAuthorizationCode;
    }

    public Integer getGzEmployeeId() {
        return gzEmployeeId;
    }

    public void setGzEmployeeId(Integer gzEmployeeId) {
        this.gzEmployeeId = gzEmployeeId;
    }

    public BigDecimal getQdBill() {
        return qdBill;
    }

    public void setQdBill(BigDecimal qdBill) {
        this.qdBill = qdBill;
    }

    public Integer getQdAuthorizationCode() {
        return qdAuthorizationCode;
    }

    public void setQdAuthorizationCode(Integer qdAuthorizationCode) {
        this.qdAuthorizationCode = qdAuthorizationCode;
    }

    public Integer getQdEmployeeId() {
        return qdEmployeeId;
    }

    public void setQdEmployeeId(Integer qdEmployeeId) {
        this.qdEmployeeId = qdEmployeeId;
    }

    public BigDecimal getCardBill() {
        return cardBill;
    }

    public void setCardBill(BigDecimal cardBill) {
        this.cardBill = cardBill;
    }

    public BigDecimal getCashBill() {
        return cashBill;
    }

    public void setCashBill(BigDecimal cashBill) {
        this.cashBill = cashBill;
    }

    public BigDecimal getMemberBill() {
        return memberBill;
    }

    public void setMemberBill(BigDecimal memberBill) {
        this.memberBill = memberBill;
    }
}