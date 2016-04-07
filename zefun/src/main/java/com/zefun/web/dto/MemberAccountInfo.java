package com.zefun.web.dto;

import java.math.BigDecimal;

/**
* 会员账户汇总封装类
* @author 乐建建
* @date 2016年2月23日 上午10:53:21 
*/
public class MemberAccountInfo {
    
    /**累计储值总额*/
    private BigDecimal accumulativeAmt;
    /**余额汇总金额*/
    private BigDecimal balanceAmt;
    /**会员数量*/
    private BigDecimal memberCnt;
    
    /**
    * @author 乐建建
    * @date 2016年2月23日 下午5:08:27 
    */
    public MemberAccountInfo() {
        super();
    }
    /**
    * @author 乐建建
    * @date 2016年2月23日 下午5:08:39
    * @param accumulativeAmt 累计储值总额
    * @param balanceAmt 汇总余额
    * @param memberCnt 会员数量
    */
    public MemberAccountInfo(BigDecimal accumulativeAmt, BigDecimal balanceAmt,
            BigDecimal memberCnt) {
        super();
        this.accumulativeAmt = accumulativeAmt;
        this.balanceAmt = balanceAmt;
        this.memberCnt = memberCnt;
    }
    public BigDecimal getMemberCnt() {
        return memberCnt;
    }
    public void setMemberCnt(BigDecimal memberCnt) {
        this.memberCnt = memberCnt;
    }
    public BigDecimal getAccumulativeAmt() {
        return accumulativeAmt;
    }
    public void setAccumulativeAmt(BigDecimal accumulativeAmt) {
        this.accumulativeAmt = accumulativeAmt;
    }
    public BigDecimal getBalanceAmt() {
        return balanceAmt;
    }
    public void setBalanceAmt(BigDecimal balanceAmt) {
        this.balanceAmt = balanceAmt;
    }    
    
}
