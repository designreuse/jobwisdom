package com.lives.platform.web.dto;

import java.math.BigDecimal;

public class MemberCardRecommendDto {
	private Integer cardId;

    private String recommendMainCardCommissionType;

    private BigDecimal recommendMainCardCommissionMoney;

    private String recommendMainRechargeCommissionType;

    private BigDecimal recommendMainRechargeCommissionMoney;

    private String recommendMainConsumptionCommissionType;

    private BigDecimal recommendMainConsumptionCommissionMoney;

    private String recommendSecondaryCardCommissionType;

    private BigDecimal recommendSecondaryCardCommissionMoney;

    private String recommendSecondaryRechargeCommissionType;

    private BigDecimal recommendSecondaryRechargeCommissionMoney;

    private String recommendSecondaryConsumptionCommissionType;

    private BigDecimal recommendSecondaryConsumptionCommissionMoney;
    
    private String cardName;
    
    

    public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getRecommendMainCardCommissionType() {
        return recommendMainCardCommissionType;
    }

    public void setRecommendMainCardCommissionType(String recommendMainCardCommissionType) {
        this.recommendMainCardCommissionType = recommendMainCardCommissionType == null ? null : recommendMainCardCommissionType.trim();
    }

    public BigDecimal getRecommendMainCardCommissionMoney() {
        return recommendMainCardCommissionMoney;
    }

    public void setRecommendMainCardCommissionMoney(BigDecimal recommendMainCardCommissionMoney) {
        this.recommendMainCardCommissionMoney = recommendMainCardCommissionMoney;
    }

    public String getRecommendMainRechargeCommissionType() {
        return recommendMainRechargeCommissionType;
    }

    public void setRecommendMainRechargeCommissionType(String recommendMainRechargeCommissionType) {
        this.recommendMainRechargeCommissionType = recommendMainRechargeCommissionType == null ? null : recommendMainRechargeCommissionType.trim();
    }

    public BigDecimal getRecommendMainRechargeCommissionMoney() {
        return recommendMainRechargeCommissionMoney;
    }

    public void setRecommendMainRechargeCommissionMoney(BigDecimal recommendMainRechargeCommissionMoney) {
        this.recommendMainRechargeCommissionMoney = recommendMainRechargeCommissionMoney;
    }

    public String getRecommendMainConsumptionCommissionType() {
        return recommendMainConsumptionCommissionType;
    }

    public void setRecommendMainConsumptionCommissionType(String recommendMainConsumptionCommissionType) {
        this.recommendMainConsumptionCommissionType = recommendMainConsumptionCommissionType == null ? null : recommendMainConsumptionCommissionType.trim();
    }

    public BigDecimal getRecommendMainConsumptionCommissionMoney() {
        return recommendMainConsumptionCommissionMoney;
    }

    public void setRecommendMainConsumptionCommissionMoney(BigDecimal recommendMainConsumptionCommissionMoney) {
        this.recommendMainConsumptionCommissionMoney = recommendMainConsumptionCommissionMoney;
    }

    public String getRecommendSecondaryCardCommissionType() {
        return recommendSecondaryCardCommissionType;
    }

    public void setRecommendSecondaryCardCommissionType(String recommendSecondaryCardCommissionType) {
        this.recommendSecondaryCardCommissionType = recommendSecondaryCardCommissionType == null ? null : recommendSecondaryCardCommissionType.trim();
    }

    public BigDecimal getRecommendSecondaryCardCommissionMoney() {
        return recommendSecondaryCardCommissionMoney;
    }

    public void setRecommendSecondaryCardCommissionMoney(BigDecimal recommendSecondaryCardCommissionMoney) {
        this.recommendSecondaryCardCommissionMoney = recommendSecondaryCardCommissionMoney;
    }

    public String getRecommendSecondaryRechargeCommissionType() {
        return recommendSecondaryRechargeCommissionType;
    }

    public void setRecommendSecondaryRechargeCommissionType(String recommendSecondaryRechargeCommissionType) {
        this.recommendSecondaryRechargeCommissionType = recommendSecondaryRechargeCommissionType == null ? null : recommendSecondaryRechargeCommissionType.trim();
    }

    public BigDecimal getRecommendSecondaryRechargeCommissionMoney() {
        return recommendSecondaryRechargeCommissionMoney;
    }

    public void setRecommendSecondaryRechargeCommissionMoney(BigDecimal recommendSecondaryRechargeCommissionMoney) {
        this.recommendSecondaryRechargeCommissionMoney = recommendSecondaryRechargeCommissionMoney;
    }

    public String getRecommendSecondaryConsumptionCommissionType() {
        return recommendSecondaryConsumptionCommissionType;
    }

    public void setRecommendSecondaryConsumptionCommissionType(String recommendSecondaryConsumptionCommissionType) {
        this.recommendSecondaryConsumptionCommissionType = recommendSecondaryConsumptionCommissionType == null ? null : recommendSecondaryConsumptionCommissionType.trim();
    }

    public BigDecimal getRecommendSecondaryConsumptionCommissionMoney() {
        return recommendSecondaryConsumptionCommissionMoney;
    }

    public void setRecommendSecondaryConsumptionCommissionMoney(BigDecimal recommendSecondaryConsumptionCommissionMoney) {
        this.recommendSecondaryConsumptionCommissionMoney = recommendSecondaryConsumptionCommissionMoney;
    }

	@Override
	public String toString() {
		return "MemberCardRecommendDto [cardId=" + cardId
				+ ", recommendMainCardCommissionType="
				+ recommendMainCardCommissionType
				+ ", recommendMainCardCommissionMoney="
				+ recommendMainCardCommissionMoney
				+ ", recommendMainRechargeCommissionType="
				+ recommendMainRechargeCommissionType
				+ ", recommendMainRechargeCommissionMoney="
				+ recommendMainRechargeCommissionMoney
				+ ", recommendMainConsumptionCommissionType="
				+ recommendMainConsumptionCommissionType
				+ ", recommendMainConsumptionCommissionMoney="
				+ recommendMainConsumptionCommissionMoney
				+ ", recommendSecondaryCardCommissionType="
				+ recommendSecondaryCardCommissionType
				+ ", recommendSecondaryCardCommissionMoney="
				+ recommendSecondaryCardCommissionMoney
				+ ", recommendSecondaryRechargeCommissionType="
				+ recommendSecondaryRechargeCommissionType
				+ ", recommendSecondaryRechargeCommissionMoney="
				+ recommendSecondaryRechargeCommissionMoney
				+ ", recommendSecondaryConsumptionCommissionType="
				+ recommendSecondaryConsumptionCommissionType
				+ ", recommendSecondaryConsumptionCommissionMoney="
				+ recommendSecondaryConsumptionCommissionMoney + ", cardName="
				+ cardName + "]";
	}
    
}
