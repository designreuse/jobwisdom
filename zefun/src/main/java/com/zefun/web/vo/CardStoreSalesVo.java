package com.zefun.web.vo;

import java.math.BigDecimal;
/**
* @author 张洋
* @date 2016年3月19日 下午19:54:45
*/
public class CardStoreSalesVo {
	/**卡金销售商品数量*/
    private Integer cardStoreCnt;
    
    /**卡金销售商品业绩*/
    private BigDecimal cardStoreAmt;

	public Integer getCardStoreCnt() {
		return cardStoreCnt;
	}

	public void setCardStoreCnt(Integer cardStoreCnt) {
		this.cardStoreCnt = cardStoreCnt;
	}

	public BigDecimal getCardStoreAmt() {
		return cardStoreAmt;
	}

	public void setCardStoreAmt(BigDecimal cardStoreAmt) {
		this.cardStoreAmt = cardStoreAmt;
	}
    
	/**默认构造参数*/
    public CardStoreSalesVo(){
    	
    }
    
    /**
     * 
    * @author 张进军
    * @date Mar 19, 2016 10:18:14 PM
    * @param cardStoreCnt   卡项数量
    * @param cardStoreAmt   卡项金额
     */
    public CardStoreSalesVo(Integer cardStoreCnt, BigDecimal cardStoreAmt){
    	this.cardStoreAmt = cardStoreAmt;
    	this.cardStoreCnt = cardStoreCnt;
    	
    }
}
