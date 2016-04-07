package com.lives.platform.web.entity;

public class BigOrder {
    private Integer bOrderId;

    private Integer bOrderType;

    private Integer comboSaleDetailId;

    private Integer userOrderDetailId;

    private Integer storesId;

    private String createTime;

    public Integer getbOrderId() {
        return bOrderId;
    }

    public void setbOrderId(Integer bOrderId) {
        this.bOrderId = bOrderId;
    }

    public Integer getbOrderType() {
        return bOrderType;
    }

    public void setbOrderType(Integer bOrderType) {
        this.bOrderType = bOrderType;
    }

    public Integer getComboSaleDetailId() {
        return comboSaleDetailId;
    }

    public void setComboSaleDetailId(Integer comboSaleDetailId) {
        this.comboSaleDetailId = comboSaleDetailId;
    }

    public Integer getUserOrderDetailId() {
        return userOrderDetailId;
    }

    public void setUserOrderDetailId(Integer userOrderDetailId) {
        this.userOrderDetailId = userOrderDetailId;
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