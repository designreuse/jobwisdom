package com.zefun.web.vo;

import java.math.BigDecimal;

/**
* @author 张洋
* @date 2016年3月19日 下午19:54:38
*/
public class CashStoreSalesVo {
	/**卡金销售商品数量*/
    private Integer cashStoreCnt;
    
    /**卡金销售商品业绩*/
    private BigDecimal cashStoreAmt;
    
    

	public Integer getCashStoreCnt() {
		return cashStoreCnt;
	}

	public void setCashStoreCnt(Integer cashStoreCnt) {
		this.cashStoreCnt = cashStoreCnt;
	}

	public BigDecimal getCashStoreAmt() {
		return cashStoreAmt;
	}

	public void setCashStoreAmt(BigDecimal cashStoreAmt) {
		this.cashStoreAmt = cashStoreAmt;
	}

	/**默认构造方法*/
	public CashStoreSalesVo(){
    	
    }
    
	/**
	 * 
	* @author 张进军
	* @date Mar 19, 2016 10:20:09 PM
	* @param cashStoreCnt  现金数量
	* @param cashStoreAmt  现金金额
	 */
    public CashStoreSalesVo(Integer cashStoreCnt, BigDecimal cashStoreAmt){
    	this.cashStoreCnt = cashStoreCnt;
    	this.cashStoreAmt = cashStoreAmt;
    	
    }
}
