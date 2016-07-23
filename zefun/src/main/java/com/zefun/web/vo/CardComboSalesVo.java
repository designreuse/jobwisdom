package com.zefun.web.vo;

import java.math.BigDecimal;

/**
* @author 乐建建
* @date 2016年3月15日 上午10:27:12 
*/
public class CardComboSalesVo {
    
    /**卡金销售疗程数量*/
    private Integer cardComboCnt;
    
    /**卡金销售疗程业绩*/
    private BigDecimal cardComboAmt;

    public Integer getCardComboCnt() {
        return cardComboCnt;
    }

    public void setCardComboCnt(Integer cardComboCnt) {
        this.cardComboCnt = cardComboCnt;
    }

    public BigDecimal getCardComboAmt() {
        return cardComboAmt;
    }

    public void setCardComboAmt(BigDecimal cardComboAmt) {
        this.cardComboAmt = cardComboAmt;
    }
    
    /**默认构造参数*/
    public CardComboSalesVo(){}
    
    /**
     * 
    * @author 张进军
    * @date Mar 19, 2016 10:17:33 PM
    * @param cardComboCnt   卡项数量
    * @param cardComboAmt   卡项金额
     */
    public CardComboSalesVo(Integer cardComboCnt, BigDecimal cardComboAmt){
    	this.cardComboAmt = cardComboAmt;
    	this.cardComboCnt = cardComboCnt;
    }
}
