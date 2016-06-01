package com.zefun.web.entity;

/**
 * 商品库存
* @author 高国藩
* @date 2016年6月1日 下午3:07:07
 */
public class GoodsStock extends GoodsStockKey {
    /** 数量 */
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