package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 营业实际收入
* @author 乐建建
* @date 2016年2月18日 下午8:57:19 
*/
public class BusinessRealIncome {
    /**营业日期*/
    private String date;
    /**营业实际收入*/
    private BigDecimal realIncome;
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public BigDecimal getRealIncome() {
        return realIncome;
    }
    public void setRealIncome(BigDecimal realIncome) {
        this.realIncome = realIncome;
    }
}
