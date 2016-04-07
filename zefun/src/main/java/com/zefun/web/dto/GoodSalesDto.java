package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 统计商品销售封装类
* @author 乐建建
* @date 2016年2月20日 下午5:47:40 
*/
public class GoodSalesDto {
    
    /**商品id*/
    private Integer goodId;
    
    /**销量统计*/
    private Integer salesCount;
    
    /**销售额统计*/
    private BigDecimal salesAmt;

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Integer getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    public BigDecimal getSalesAmt() {
        return salesAmt;
    }

    public void setSalesAmt(BigDecimal salesAmt) {
        this.salesAmt = salesAmt;
    }
    
    
}
