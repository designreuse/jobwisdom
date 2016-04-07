package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class ScrossStoreSale {
    private Integer scrossStoreSaleId;

    private Integer orderId;

    private Integer vipSubordinateStore;

    private Integer vipSaleStore;

    private String spendingTime;

    private BigDecimal buckleCardAmount;

    private String createTime;

    public Integer getScrossStoreSaleId() {
        return scrossStoreSaleId;
    }

    public void setScrossStoreSaleId(Integer scrossStoreSaleId) {
        this.scrossStoreSaleId = scrossStoreSaleId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getVipSubordinateStore() {
        return vipSubordinateStore;
    }

    public void setVipSubordinateStore(Integer vipSubordinateStore) {
        this.vipSubordinateStore = vipSubordinateStore;
    }

    public Integer getVipSaleStore() {
        return vipSaleStore;
    }

    public void setVipSaleStore(Integer vipSaleStore) {
        this.vipSaleStore = vipSaleStore;
    }

    public String getSpendingTime() {
        return spendingTime;
    }

    public void setSpendingTime(String spendingTime) {
        this.spendingTime = spendingTime == null ? null : spendingTime.trim();
    }

    public BigDecimal getBuckleCardAmount() {
        return buckleCardAmount;
    }

    public void setBuckleCardAmount(BigDecimal buckleCardAmount) {
        this.buckleCardAmount = buckleCardAmount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}