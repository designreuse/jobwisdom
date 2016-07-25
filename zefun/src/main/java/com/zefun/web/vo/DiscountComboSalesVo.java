package com.zefun.web.vo;

import java.math.BigDecimal;

/**
* @author 张洋
* @date 2016年3月19日 下午13:19:32
*/
public class DiscountComboSalesVo {
	
	/**折扣疗程数量*/
    private Integer discountComboCnt;
    /**折扣疗程业绩*/
    private BigDecimal discountComboAmt;
    
	public Integer getDiscountComboCnt() {
		return discountComboCnt;
	}
	public void setDiscountComboCnt(Integer discountComboCnt) {
		this.discountComboCnt = discountComboCnt;
	}
	public BigDecimal getDiscountComboAmt() {
		return discountComboAmt;
	}
	public void setDiscountComboAmt(BigDecimal discountComboAmt) {
		this.discountComboAmt = discountComboAmt;
	}
	
	/**默认构造函数*/
	public DiscountComboSalesVo(){}
	
	/**
	 * 
	* @author 张进军
	* @date Mar 19, 2016 10:25:29 PM
	* @param discountComboCnt  抵扣数量
	* @param discountComboAmt  抵扣金额
	 */
    public DiscountComboSalesVo(Integer discountComboCnt, BigDecimal discountComboAmt){
    	this.discountComboAmt = discountComboAmt;
    	this.discountComboCnt = discountComboCnt;
    }
}
