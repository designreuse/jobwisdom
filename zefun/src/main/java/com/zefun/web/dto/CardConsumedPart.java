package com.zefun.web.dto;

import java.math.BigDecimal;

/**
* 营业报表下面卡项销售的划卡消费封装类
* @author 乐建建
* @date 2016年2月22日 下午5:32:51 
*/
public class CardConsumedPart {
    /**某个会员等级下划卡消费的人数*/
    private int cardConsumedCnt;
    
    /**会员等级id*/
    private int levelId;
    
    /**某个会员等级下划卡消费的总金额**/
    private BigDecimal cardConsumedAmt;

    public int getCardConsumedCnt() {
        return cardConsumedCnt;
    }

    public void setCardConsumedCnt(int cardConsumedCnt) {
        this.cardConsumedCnt = cardConsumedCnt;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public BigDecimal getCardConsumedAmt() {
        return cardConsumedAmt;
    }

    public void setCardConsumedAmt(BigDecimal cardConsumedAmt) {
        this.cardConsumedAmt = cardConsumedAmt;
    }
    
    
}
