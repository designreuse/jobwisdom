package com.zefun.web.dto;

import java.math.BigDecimal;

/**
* 营业报表下面卡项销售的会员数据统计封装类
* @author 乐建建
* @date 2016年2月22日 下午6:02:43 
*/
public class MemberDataPart {
    
    /**会员等级表 member_level表中的等级标志*/
    private int levelId;
    
    /**总人数*/
    private int totalMemberCnt;
    
    /**累计储值*/
    private BigDecimal accumulativeAmt;
    
    /**余额*/
    private BigDecimal balanceAmt;

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public int getTotalMemberCnt() {
        return totalMemberCnt;
    }

    public void setTotalMemberCnt(int totalMemberCnt) {
        this.totalMemberCnt = totalMemberCnt;
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
