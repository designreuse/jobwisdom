package com.zefun.web.dto;

import java.math.BigDecimal;

/**
* @author 乐建建
* @date 2016年1月19日 下午6:44:29 
* 部门分月份汇总
*/
public class DeptSummaryByDayDto {
    
    /**部门汇总*/
    private BigDecimal deptSum;
    /**月份*/
    private String currDate;
    
    /**
    * @author 乐建建
    * @date 2016年1月20日 下午5:53:46
    * @param deptSum 部门汇总
    * @param currDate 日期
    */
    public DeptSummaryByDayDto(BigDecimal deptSum, String currDate) {
        super();
        this.deptSum = deptSum;
        this.currDate = currDate;
    }
    /**
    * @author 乐建建
    * @date 2016年1月20日 下午5:54:32 
    * 默认构造函数
    */
    public DeptSummaryByDayDto() {
        super();
    }
    public String getCurrDate() {
        return currDate;
    }
    public void setCurrDate(String currDate) {
        this.currDate = currDate;
    }
    public BigDecimal getDeptSum() {
        return deptSum;
    }
    public void setDeptSum(BigDecimal deptSum) {
        this.deptSum = deptSum;
    }
    
}
