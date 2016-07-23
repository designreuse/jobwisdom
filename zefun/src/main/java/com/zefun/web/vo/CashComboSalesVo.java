package com.zefun.web.vo;

import java.math.BigDecimal;

/**
* @author 乐建建
* @date 2016年3月15日 上午10:23:24 
*/
public class CashComboSalesVo {
    
    /**现金购买疗程数量*/
    private Integer cashComboCnt;
    /**现金购买疗程业绩*/
    private BigDecimal cashComboAmt;
    
    public Integer getCashComboCnt() {
        return cashComboCnt;
    }
    public void setCashComboCnt(Integer cashComboCnt) {
        this.cashComboCnt = cashComboCnt;
    }
    public BigDecimal getCashComboAmt() {
        return cashComboAmt;
    }
    public void setCashComboAmt(BigDecimal cashComboAmt) {
        this.cashComboAmt = cashComboAmt;
    }
    
    /**默认构造方法*/
    public CashComboSalesVo(){}
    
    /**
     * 
    * @author 张进军
    * @date Mar 19, 2016 10:19:22 PM
    * @param cashComboCnt   现金数量
    * @param cashComboAmt   现金金额
     */
    public CashComboSalesVo(Integer cashComboCnt, BigDecimal cashComboAmt){
    	this.cashComboAmt = cashComboAmt;
    	this.cashComboCnt = cashComboCnt;
    }
}
