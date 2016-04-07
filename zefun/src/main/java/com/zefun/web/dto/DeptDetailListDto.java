package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 部门明细清单dto
 * 统计特定部门下 营业收入 卡项收入 会员消费  营业扣减等明细情况 
* @author 乐建建
* @date 2016年1月30日 下午4:51:54 
*/
public class DeptDetailListDto {
    
    /**项目业绩*/
    private BigDecimal projectAmount;
    
    /**商品业绩*/
    private BigDecimal goodsAmount;
    
    /**套餐业绩*/
    private BigDecimal comboAmount;
    
    /**开卡业绩*/
    private BigDecimal cardAmount;
    
    /**充值业绩*/
    private BigDecimal chargedAmount;
    
    /**升级业绩*/
    private BigDecimal upgradeAmount;
    
    /**刷卡消费*/
    private BigDecimal cardCosumeAmount;
    
    /**套餐折扣*/
    private BigDecimal comboDisCountAmount;
    
    /**礼金折扣*/
    private BigDecimal giftAmount;
    
    /**挂账*/
    private BigDecimal debtAmount;
    
    /**优惠券折扣*/
    private BigDecimal couponAmount;
    
    /**预约金额*/
    private BigDecimal appointAmount;

    public BigDecimal getProjectAmount() {
        return projectAmount;
    }

    public void setProjectAmount(BigDecimal projectAmount) {
        this.projectAmount = projectAmount;
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public BigDecimal getComboAmount() {
        return comboAmount;
    }

    public void setComboAmount(BigDecimal comboAmount) {
        this.comboAmount = comboAmount;
    }

    public BigDecimal getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(BigDecimal cardAmount) {
        this.cardAmount = cardAmount;
    }

    public BigDecimal getChargedAmount() {
        return chargedAmount;
    }

    public void setChargedAmount(BigDecimal chargedAmount) {
        this.chargedAmount = chargedAmount;
    }

    public BigDecimal getUpgradeAmount() {
        return upgradeAmount;
    }

    public void setUpgradeAmount(BigDecimal upgradeAmount) {
        this.upgradeAmount = upgradeAmount;
    }

    public BigDecimal getCardCosumeAmount() {
        return cardCosumeAmount;
    }

    public void setCardCosumeAmount(BigDecimal cardCosumeAmount) {
        this.cardCosumeAmount = cardCosumeAmount;
    }

    public BigDecimal getComboDisCountAmount() {
        return comboDisCountAmount;
    }

    public void setComboDisCountAmount(BigDecimal comboDisCountAmount) {
        this.comboDisCountAmount = comboDisCountAmount;
    }

    public BigDecimal getGiftAmount() {
        return giftAmount;
    }

    public void setGiftAmount(BigDecimal giftAmount) {
        this.giftAmount = giftAmount;
    }

    public BigDecimal getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(BigDecimal debtAmount) {
        this.debtAmount = debtAmount;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getAppointAmount() {
        return appointAmount;
    }

    public void setAppointAmount(BigDecimal appointAmount) {
        this.appointAmount = appointAmount;
    }
}
