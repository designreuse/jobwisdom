package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class MemberCardInfo {
    private Integer cardId;

    private String cardName;

    private Integer storeId;

    private Integer cardType;

    private Integer cardProjectDiscount;

    private Integer cardGoodsDiscount;

    private BigDecimal cardMoneyRecharge;

    private BigDecimal cardMoneyCash;

    private Integer cardIsUseDate;

    private Integer cardIsMessage;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName == null ? null : cardName.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public Integer getCardProjectDiscount() {
        return cardProjectDiscount;
    }

    public void setCardProjectDiscount(Integer cardProjectDiscount) {
        this.cardProjectDiscount = cardProjectDiscount;
    }

    public Integer getCardGoodsDiscount() {
        return cardGoodsDiscount;
    }

    public void setCardGoodsDiscount(Integer cardGoodsDiscount) {
        this.cardGoodsDiscount = cardGoodsDiscount;
    }

    public BigDecimal getCardMoneyRecharge() {
        return cardMoneyRecharge;
    }

    public void setCardMoneyRecharge(BigDecimal cardMoneyRecharge) {
        this.cardMoneyRecharge = cardMoneyRecharge;
    }

    public BigDecimal getCardMoneyCash() {
        return cardMoneyCash;
    }

    public void setCardMoneyCash(BigDecimal cardMoneyCash) {
        this.cardMoneyCash = cardMoneyCash;
    }

    public Integer getCardIsUseDate() {
        return cardIsUseDate;
    }

    public void setCardIsUseDate(Integer cardIsUseDate) {
        this.cardIsUseDate = cardIsUseDate;
    }

    public Integer getCardIsMessage() {
        return cardIsMessage;
    }

    public void setCardIsMessage(Integer cardIsMessage) {
        this.cardIsMessage = cardIsMessage;
    }
}