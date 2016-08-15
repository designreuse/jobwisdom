package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 卡项汇总数据传输
* @author 高国藩
* @date 2016年8月13日 下午3:06:20
 */
public class LevelTotalRemittanceDto {

    /** 会员卡等级ID*/
    private Integer levelId;
    /** 折扣标示*/
    private Integer discountId;
    /** 会员卡名称*/
    private String levelName;
    /** 开卡费用*/
    private BigDecimal sellAmount;
    /** 卡总开卡数 */
    private Integer activateLevelCount;
    /** 卡总续卡人次*/
    private Integer activateLevelGoOnCount;
    /** 卡总储值余额*/
    private BigDecimal balanceAmount;
    /** 卡总储值总额*/
    private BigDecimal totalAmount;
    /** 门店卡总储值总额 */
    private BigDecimal levelTotalAmount;
    /** 卡项总实充*/
    private BigDecimal totalRechargeAmount;
    /** 卡项总赠送*/
    private BigDecimal totalPresentAmount;
    
    /** 开卡人次 */
    private Integer activePersionCount;
    /** 开卡实充 */
    private BigDecimal activeFaceAmount;
    /** 开卡赠送总额 */
    private BigDecimal activePersentAmount;
    /** 充值人次 */
    private Integer rechargePersionCount;
    /** 充值总额 */
    private BigDecimal rechargeFaceAmount;
    /** 充值赠送总额 */
    private BigDecimal rechargePersentAmount;
    /** 升级人次 */
    private Integer upgradePersionCount;
    /** 升级总额 */
    private BigDecimal upgradeFaceAmount;
    /** 升级赠送总额 */
    private BigDecimal upgradePersentAmount;
    /** 划卡消费总额*/
    private BigDecimal pushLevel;
    
    public Integer getLevelId() {
        return levelId;
    }
    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }
    public Integer getDiscountId() {
        return discountId;
    }
    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }
    public String getLevelName() {
        return levelName;
    }
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
    /**
     * 获取充值人次
    * @author 高国藩
    * @date 2016年8月15日 上午9:55:47
    * @return activateLevelCount
     */
    public Integer getActivateLevelCount() {
        if (activateLevelCount == null){
            return 0;
        }
        return activateLevelCount;
    }
    public void setActivateLevelCount(Integer activateLevelCount) {
        this.activateLevelCount = activateLevelCount;
    }
    /**
     * 余额总汇
    * @author 高国藩
    * @date 2016年8月15日 上午9:56:29
    * @return balanceAmount
     */
    public BigDecimal getBalanceAmount() {
        if (balanceAmount == null){
            return new BigDecimal(0);
        }
        return balanceAmount;
    }
    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }
    /**
     * 余额总汇
    * @author 高国藩
    * @date 2016年8月15日 上午9:56:29
    * @return balanceAmount
     */
    public BigDecimal getTotalAmount() {
        if (totalAmount == null){
            return new BigDecimal(0);
        }
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    /**
     * 余额总汇
    * @author 高国藩
    * @date 2016年8月15日 上午9:56:29
    * @return balanceAmount
     */
    public BigDecimal getLevelTotalAmount() {
        if (levelTotalAmount == null){
            return new BigDecimal(0);
        }
        return levelTotalAmount;
    }
    public void setLevelTotalAmount(BigDecimal levelTotalAmount) {
        this.levelTotalAmount = levelTotalAmount;
    }
    /**
     * 余额总汇
    * @author 高国藩
    * @date 2016年8月15日 上午9:56:29
    * @return balanceAmount
     */
    public Integer getActivateLevelGoOnCount() {
        if (activateLevelGoOnCount == null){
            return 0;
        }
        return activateLevelGoOnCount;
    }
    public void setActivateLevelGoOnCount(Integer activateLevelGoOnCount) {
        this.activateLevelGoOnCount = activateLevelGoOnCount;
    }
    /**
     * 余额总汇
    * @author 高国藩
    * @date 2016年8月15日 上午9:56:29
    * @return balanceAmount
     */
    public BigDecimal getTotalRechargeAmount() {
        if (totalRechargeAmount == null){
            return new BigDecimal(0);
        }
        return totalRechargeAmount;
    }
    public void setTotalRechargeAmount(BigDecimal totalRechargeAmount) {
        this.totalRechargeAmount = totalRechargeAmount;
    }
    /**
     * 余额总汇
    * @author 高国藩
    * @date 2016年8月15日 上午9:56:29
    * @return balanceAmount
     */
    public BigDecimal getTotalPresentAmount() {
        if (totalPresentAmount == null){
            return new BigDecimal(0);
        }
        return totalPresentAmount;
    }
    public void setTotalPresentAmount(BigDecimal totalPresentAmount) {
        this.totalPresentAmount = totalPresentAmount;
    }
    /**
     * 余额总汇
    * @author 高国藩
    * @date 2016年8月15日 上午9:56:29
    * @return balanceAmount
     */
    public BigDecimal getSellAmount() {
        if (sellAmount == null){
            return new BigDecimal(0);
        }
        return sellAmount;
    }
    public void setSellAmount(BigDecimal sellAmount) {
        this.sellAmount = sellAmount;
    }
    /**
     * 余额总汇
    * @author 高国藩
    * @date 2016年8月15日 上午9:56:29
    * @return balanceAmount
     */
    public Integer getActivePersionCount() {
        if (activePersionCount == null){
            return 0;
        }
        return activePersionCount;
    }
    public void setActivePersionCount(Integer activePersionCount) {
        this.activePersionCount = activePersionCount;
    }
    /**
     * 余额总汇
    * @author 高国藩
    * @date 2016年8月15日 上午9:56:29
    * @return balanceAmount
     */
    public BigDecimal getActiveFaceAmount() {
        if (activeFaceAmount == null){
            return new BigDecimal(0);
        }
        return activeFaceAmount;
    }
    public void setActiveFaceAmount(BigDecimal activeFaceAmount) {
        this.activeFaceAmount = activeFaceAmount;
    }
    /**
     * 余额总汇
    * @author 高国藩
    * @date 2016年8月15日 上午9:56:29
    * @return balanceAmount
     */
    public BigDecimal getActivePersentAmount() {
        if (activePersentAmount == null){
            return new BigDecimal(0);
        }
        return activePersentAmount;
    }
    public void setActivePersentAmount(BigDecimal activePersentAmount) {
        this.activePersentAmount = activePersentAmount;
    }
    /**
     * 余额总汇
    * @author 高国藩
    * @date 2016年8月15日 上午9:56:29
    * @return balanceAmount
     */
    public Integer getRechargePersionCount() {
        if (rechargePersionCount == null){
            return 0;
        }
        return rechargePersionCount;
    }
    public void setRechargePersionCount(Integer rechargePersionCount) {
        this.rechargePersionCount = rechargePersionCount;
    }
    /**
     * 余额总汇
    * @author 高国藩
    * @date 2016年8月15日 上午9:56:29
    * @return balanceAmount
     */
    public BigDecimal getRechargeFaceAmount() {
        if (rechargeFaceAmount == null){
            return new BigDecimal(0);
        }
        return rechargeFaceAmount;
    }
    public void setRechargeFaceAmount(BigDecimal rechargeFaceAmount) {
        this.rechargeFaceAmount = rechargeFaceAmount;
    }
    /**
     * 余额总汇
    * @author 高国藩
    * @date 2016年8月15日 上午9:56:29
    * @return balanceAmount
     */
    public BigDecimal getRechargePersentAmount() {
        if (rechargePersentAmount == null){
            return new BigDecimal(0);
        }
        return rechargePersentAmount;
    }
    public void setRechargePersentAmount(BigDecimal rechargePersentAmount) {
        this.rechargePersentAmount = rechargePersentAmount;
    }
    /**
     * 余额总汇
    * @author 高国藩
    * @date 2016年8月15日 上午9:56:29
    * @return balanceAmount
     */
    public Integer getUpgradePersionCount() {
        if (upgradePersionCount == null){
            return 0;
        }
        return upgradePersionCount;
    }
    public void setUpgradePersionCount(Integer upgradePersionCount) {
        this.upgradePersionCount = upgradePersionCount;
    }
    /**
     * 余额总汇
    * @author 高国藩
    * @date 2016年8月15日 上午9:56:29
    * @return balanceAmount
     */
    public BigDecimal getUpgradeFaceAmount() {
        if (upgradeFaceAmount == null){
            return new BigDecimal(0);
        }
        return upgradeFaceAmount;
    }
    public void setUpgradeFaceAmount(BigDecimal upgradeFaceAmount) {
        this.upgradeFaceAmount = upgradeFaceAmount;
    }
    /**
     * 余额总汇
    * @author 高国藩
    * @date 2016年8月15日 上午9:56:29
    * @return balanceAmount
     */
    public BigDecimal getUpgradePersentAmount() {
        if (upgradePersentAmount == null){
            return new BigDecimal(0);
        }
        return upgradePersentAmount;
    }
    public void setUpgradePersentAmount(BigDecimal upgradePersentAmount) {
        this.upgradePersentAmount = upgradePersentAmount;
    }
    /**
     * 余额总汇
    * @author 高国藩
    * @date 2016年8月15日 上午9:56:29
    * @return balanceAmount
     */
    public BigDecimal getPushLevel() {
        if (pushLevel == null){
            return new BigDecimal(0);
        }
        return pushLevel;
    }
    public void setPushLevel(BigDecimal pushLevel) {
        this.pushLevel = pushLevel;
    }
    
}
