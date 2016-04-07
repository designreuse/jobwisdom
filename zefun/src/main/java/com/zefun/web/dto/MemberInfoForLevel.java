package com.zefun.web.dto;

/**
 * 这是一个组合类
* @author 乐建建
* @date 2016年2月23日 下午2:52:00 
*/
public class MemberInfoForLevel {
    
    /**会员等级id和该等级对应的会员列表*/
    private MemberLevelInfo levelInfo;
    /**该会员等级在给定条件下的开卡\充值\升级汇总数据*/
    private Member2Info level;
    /**该会员等级在给定条件下的消费数据*/
    private MemberAccountInfo memberAccounts;
    /**该会员等级在给定条件下的会员账户汇总数据*/
    private MemberCardConsumedInfo memberCards;
    public Member2Info getLevel() {
        return level;
    }

    public void setLevel(Member2Info level) {
        this.level = level;
    }

    public MemberAccountInfo getMemberAccounts() {
        return memberAccounts;
    }

    public void setMemberAccounts(MemberAccountInfo memberAccounts) {
        this.memberAccounts = memberAccounts;
    }

    public MemberCardConsumedInfo getMemberCards() {
        return memberCards;
    }

    public void setMemberCards(MemberCardConsumedInfo memberCards) {
        this.memberCards = memberCards;
    }

    public MemberLevelInfo getLevelInfo() {
        return levelInfo;
    }
 
    public void setLevelInfo(MemberLevelInfo levelInfo) {
        this.levelInfo = levelInfo;
    }
    
}
