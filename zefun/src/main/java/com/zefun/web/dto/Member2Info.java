package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 卡项销售下面储值卡销售汇总新开\充值\划卡封装类
* @author 乐建建
* @date 2016年2月23日 上午10:23:38 
*/
public class Member2Info {
    
    /**会员新开卡金额*/
    private BigDecimal newOpenAmt;
    /**会员充值金额*/
    private BigDecimal chargeCardAmt;
    /**会员升级金额*/
    private BigDecimal upgradeAmt;
    
    public BigDecimal getOpenCnt() {
        return openCnt;
    }
    public void setOpenCnt(BigDecimal openCnt) {
        this.openCnt = openCnt;
    }
    public BigDecimal getChargeCnt() {
        return chargeCnt;
    }
    public void setChargeCnt(BigDecimal chargeCnt) {
        this.chargeCnt = chargeCnt;
    }
    /**
    * @author 乐建建
    * @date 2016年2月24日 上午11:20:52
    * @param newOpenAmt 新开卡金额
    * @param chargeCardAmt 充值金额
    * @param upgradeAmt 升级金额
    * @param openCnt 开卡人数
    * @param chargeCnt 充值人数
    * @param upgradeCnt  升级人数
    */
    public Member2Info(BigDecimal newOpenAmt, BigDecimal chargeCardAmt,
            BigDecimal upgradeAmt, BigDecimal openCnt, BigDecimal chargeCnt,
            BigDecimal upgradeCnt) {
        super();
        this.newOpenAmt = newOpenAmt;
        this.chargeCardAmt = chargeCardAmt;
        this.upgradeAmt = upgradeAmt;
        this.openCnt = openCnt;
        this.chargeCnt = chargeCnt;
        this.upgradeCnt = upgradeCnt;
    }
    public BigDecimal getUpgradeCnt() {
        return upgradeCnt;
    }
    public void setUpgradeCnt(BigDecimal upgradeCnt) {
        this.upgradeCnt = upgradeCnt;
    }
    /**
    * @author 乐建建
    * @date 2016年2月23日 下午5:05:01 
    */
    public Member2Info() {
        super();
    }
    /**新开卡人数*/
    private BigDecimal openCnt;
    /**充值人数*/
    private BigDecimal chargeCnt;
    /**升级人数*/
    private BigDecimal upgradeCnt;
    
    public BigDecimal getNewOpenAmt() {
        return newOpenAmt;
    }
    public void setNewOpenAmt(BigDecimal newOpenAmt) {
        this.newOpenAmt = newOpenAmt;
    }
    public BigDecimal getChargeCardAmt() {
        return chargeCardAmt;
    }
    public void setChargeCardAmt(BigDecimal chargeCardAmt) {
        this.chargeCardAmt = chargeCardAmt;
    }
    public BigDecimal getUpgradeAmt() {
        return upgradeAmt;
    }
    public void setUpgradeAmt(BigDecimal upgradeAmt) {
        this.upgradeAmt = upgradeAmt;
    }
    
}
