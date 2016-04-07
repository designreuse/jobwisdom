package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class MemberCardGive {
    private Integer cardId;

    private String giveCashType;

    private BigDecimal giveCashMoney;

    private Integer giveCashDate;

    private String giveIntegralType;

    private BigDecimal giveIntegralMoney;

    private String giveRechargeType;

    private BigDecimal giveRechargeMoney;

    private Integer giveRechargeDate;

    private String giveRechargeIntegralType;

    private BigDecimal giveRechargeIntegralMoney;

    private String giveConsumptionIntegralType;

    private BigDecimal giveConsumptionIntegralMoney;
    
    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getGiveCashType() {
        return giveCashType;
    }

    public void setGiveCashType(String giveCashType) {
        this.giveCashType = giveCashType == null ? null : giveCashType.trim();
    }

    public BigDecimal getGiveCashMoney() {
        return giveCashMoney;
    }

    public void setGiveCashMoney(BigDecimal giveCashMoney) {
        this.giveCashMoney = giveCashMoney;
    }

    public Integer getGiveCashDate() {
        return giveCashDate;
    }

    public void setGiveCashDate(Integer giveCashDate) {
        this.giveCashDate = giveCashDate;
    }

    public String getGiveIntegralType() {
        return giveIntegralType;
    }

    public void setGiveIntegralType(String giveIntegralType) {
        this.giveIntegralType = giveIntegralType == null ? null : giveIntegralType.trim();
    }

    public BigDecimal getGiveIntegralMoney() {
        return giveIntegralMoney;
    }

    public void setGiveIntegralMoney(BigDecimal giveIntegralMoney) {
        this.giveIntegralMoney = giveIntegralMoney;
    }

    public String getGiveRechargeType() {
        return giveRechargeType;
    }

    public void setGiveRechargeType(String giveRechargeType) {
        this.giveRechargeType = giveRechargeType == null ? null : giveRechargeType.trim();
    }

    public BigDecimal getGiveRechargeMoney() {
        return giveRechargeMoney;
    }

    public void setGiveRechargeMoney(BigDecimal giveRechargeMoney) {
        this.giveRechargeMoney = giveRechargeMoney;
    }

    public Integer getGiveRechargeDate() {
        return giveRechargeDate;
    }

    public void setGiveRechargeDate(Integer giveRechargeDate) {
        this.giveRechargeDate = giveRechargeDate;
    }

    public String getGiveRechargeIntegralType() {
        return giveRechargeIntegralType;
    }

    public void setGiveRechargeIntegralType(String giveRechargeIntegralType) {
        this.giveRechargeIntegralType = giveRechargeIntegralType == null ? null : giveRechargeIntegralType.trim();
    }

    public BigDecimal getGiveRechargeIntegralMoney() {
        return giveRechargeIntegralMoney;
    }

    public void setGiveRechargeIntegralMoney(BigDecimal giveRechargeIntegralMoney) {
        this.giveRechargeIntegralMoney = giveRechargeIntegralMoney;
    }

    public String getGiveConsumptionIntegralType() {
        return giveConsumptionIntegralType;
    }

    public void setGiveConsumptionIntegralType(String giveConsumptionIntegralType) {
        this.giveConsumptionIntegralType = giveConsumptionIntegralType == null ? null : giveConsumptionIntegralType.trim();
    }

    public BigDecimal getGiveConsumptionIntegralMoney() {
        return giveConsumptionIntegralMoney;
    }

    public void setGiveConsumptionIntegralMoney(BigDecimal giveConsumptionIntegralMoney) {
        this.giveConsumptionIntegralMoney = giveConsumptionIntegralMoney;
    }
}