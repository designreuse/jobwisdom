package com.zefun.web.entity;

public class GoodsStock extends GoodsStockKey {
    /** ������� */
    private Integer count;

    /** ����ʱ�� */
    private String updateTime;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}