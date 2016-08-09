package com.zefun.web.entity;

import java.math.BigDecimal;

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
    
    /** 库存总金额*/
    private BigDecimal amount;
    
    /** 库存总数量*/
    private Integer amcount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getAmcount() {
        return amcount;
    }

    public void setAmcount(Integer amcount) {
        this.amcount = amcount;
    }

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