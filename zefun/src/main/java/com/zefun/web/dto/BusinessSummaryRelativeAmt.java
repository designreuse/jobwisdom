package com.zefun.web.dto;

import java.math.BigDecimal;

/**
*营业汇总设计的相关部分 包括营业收入 营业扣减 营业纯收入
* @author 乐建建
* @date 2016年2月19日 下午1:47:17 
*/
public class BusinessSummaryRelativeAmt {
    
    /**日期*/
    private String date;
    /**汇总金额*/
    private BigDecimal totalAmt;
    public String getDate() {
        return date;
    }
    /**
    * @author 乐建建
    * @date 2016年2月19日 下午2:04:04
    *  默认构造函数 
    */
    public BusinessSummaryRelativeAmt() {
        super();
    }
    /**
    * @author 乐建建
    * @date 2016年2月19日 下午2:04:17
    * @param date 日期
    * @param totalAmt 金额
    */
    public BusinessSummaryRelativeAmt(String date, BigDecimal totalAmt) {
        this.date = date;
        this.totalAmt = totalAmt;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public BigDecimal getTotalAmt() {
        return totalAmt;
    }
    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }    
    
}
