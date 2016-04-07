package com.zefun.web.dto;

import java.math.BigDecimal;

/**
* @author 乐建建
* @date 2016年3月2日 下午7:51:59 
*/
public class ReconciliationDataDto {
    
    /**已审核的明细记录数*/
    private Integer isChecked;
    /**所有的明细记录数*/
    private Integer allDetailsCnt;
    /**审核汇总金额*/
    private BigDecimal reconciliationAmt;    
    /**初始状态*/
    private Integer initialiseState;
    
    public Integer getInitialiseState() {
        return initialiseState;
    }
    public void setInitialiseState(Integer initialiseState) {
        this.initialiseState = initialiseState;
    }
    public Integer getIsChecked() {
        return isChecked;
    }
    public void setIsChecked(Integer isChecked) {
        this.isChecked = isChecked;
    }
    public Integer getAllDetailsCnt() {
        return allDetailsCnt;
    }
    public void setAllDetailsCnt(Integer allDetailsCnt) {
        this.allDetailsCnt = allDetailsCnt;
    }
    public BigDecimal getReconciliationAmt() {
        return reconciliationAmt;
    }
    public void setReconciliationAmt(BigDecimal reconciliationAmt) {
        this.reconciliationAmt = reconciliationAmt;
    }
    
    
    
}
