package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class Card {
    private Integer vipCardId;

    private String cardNo;

    private Integer cardType;

    private BigDecimal cardAmount;

    private String cardPassword;

    private String cardEmployeeId;

    private Integer storesId;

    private String createTime;

    public Integer getVipCardId() {
        return vipCardId;
    }

    public void setVipCardId(Integer vipCardId) {
        this.vipCardId = vipCardId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public BigDecimal getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(BigDecimal cardAmount) {
        this.cardAmount = cardAmount;
    }

    public String getCardPassword() {
        return cardPassword;
    }

    public void setCardPassword(String cardPassword) {
        this.cardPassword = cardPassword == null ? null : cardPassword.trim();
    }

    public String getCardEmployeeId() {
        return cardEmployeeId;
    }

    public void setCardEmployeeId(String cardEmployeeId) {
        this.cardEmployeeId = cardEmployeeId == null ? null : cardEmployeeId.trim();
    }

    public Integer getStoresId() {
        return storesId;
    }

    public void setStoresId(Integer storesId) {
        this.storesId = storesId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
    
    public Card() {
		// TODO Auto-generated constructor stub
	}
    
}