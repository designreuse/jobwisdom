package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 *  营业汇总页面趋势数据封装类
* @author 乐建建
* @date 2016年2月19日 下午7:49:24 
*/
public class BusinessSummaryPage {
    
    /**日期*/
    private String date;
    
    /**营业毛收入*/
    private BigDecimal incomes;
    
    /**营业扣减*/
    private BigDecimal expenses;
    
    /**营业纯收入*/
    private BigDecimal realIncomes;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getIncomes() {
        return incomes;
    }

    public void setIncomes(BigDecimal incomes) {
        this.incomes = incomes;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public void setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
    }

    public BigDecimal getRealIncomes() {
        return realIncomes;
    }

    public void setRealIncomes(BigDecimal realIncomes) {
        this.realIncomes = realIncomes;
    }
    
    
}
