package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class MemberCardCommission {
    private Integer cardId;

    private String commissionType;

    private BigDecimal commissionSingleMoney;

    private String commissionMultiMoney;

    private String rechargeCommissionType;

    private BigDecimal prepaidSingleCommissionMoney;

    private String rechargeRateMoney;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(String commissionType) {
        this.commissionType = commissionType == null ? null : commissionType.trim();
    }

    public BigDecimal getCommissionSingleMoney() {
        return commissionSingleMoney;
    }

    public void setCommissionSingleMoney(BigDecimal commissionSingleMoney) {
        this.commissionSingleMoney = commissionSingleMoney;
    }

    public String getCommissionMultiMoney() {
        return commissionMultiMoney;
    }

    public void setCommissionMultiMoney(String commissionMultiMoney) {
        this.commissionMultiMoney = commissionMultiMoney == null ? null : commissionMultiMoney.trim();
    }

    public String getRechargeCommissionType() {
        return rechargeCommissionType;
    }

    public void setRechargeCommissionType(String rechargeCommissionType) {
        this.rechargeCommissionType = rechargeCommissionType == null ? null : rechargeCommissionType.trim();
    }

    public BigDecimal getPrepaidSingleCommissionMoney() {
        return prepaidSingleCommissionMoney;
    }

    public void setPrepaidSingleCommissionMoney(BigDecimal prepaidSingleCommissionMoney) {
        this.prepaidSingleCommissionMoney = prepaidSingleCommissionMoney;
    }

    public String getRechargeRateMoney() {
        return rechargeRateMoney;
    }

    public void setRechargeRateMoney(String rechargeRateMoney) {
        this.rechargeRateMoney = rechargeRateMoney == null ? null : rechargeRateMoney.trim();
    }
}