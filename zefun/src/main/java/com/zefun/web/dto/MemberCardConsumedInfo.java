package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 卡项销售下面储值卡销售汇总划卡消费封装类
* @author 乐建建
* @date 2016年2月23日 上午10:31:36 
*/
public class MemberCardConsumedInfo {
        
    /**会员卡消费金额*/
    private BigDecimal cardAmt;
    
    /**会员数量*/
    private BigDecimal memberCnt;

    /**
    * @author 乐建建
    * @date 2016年2月23日 下午5:11:20 
    */
    public MemberCardConsumedInfo() {
        super();
    }
       
    /**
    * @author 乐建建
    * @date 2016年2月23日 下午5:11:40
    * @param cardAmt 会员消费金额
    * @param memberCnt 会员数量
    */
    public MemberCardConsumedInfo(BigDecimal cardAmt, BigDecimal memberCnt) {
        super();
        this.cardAmt = cardAmt;
        this.memberCnt = memberCnt;
    }

    public BigDecimal getMemberCnt() {
        return memberCnt;
    }

    public void setMemberCnt(BigDecimal memberCnt) {
        this.memberCnt = memberCnt;
    }

    public BigDecimal getCardAmt() {
        return cardAmt;
    }

    public void setCardAmt(BigDecimal cardAmt) {
        this.cardAmt = cardAmt;
    }
    
    
}
