package com.zefun.web.entity;

public class GoodsStock extends GoodsStockKey {
    /** 库存余量 */
    private Integer count;

    /** 更新时间 */
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