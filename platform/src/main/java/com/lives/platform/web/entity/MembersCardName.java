package com.lives.platform.web.entity;

public class MembersCardName {
    private Integer cardNameId;

    private String cardName;

    private Integer cost;

    private Integer lowestLimit;

    private Integer storesId;

    private Integer cardDiscount;

    private String createTime;

    public Integer getCardNameId() {
        return cardNameId;
    }

    public void setCardNameId(Integer cardNameId) {
        this.cardNameId = cardNameId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName == null ? null : cardName.trim();
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getLowestLimit() {
        return lowestLimit;
    }

    public void setLowestLimit(Integer lowestLimit) {
        this.lowestLimit = lowestLimit;
    }

    public Integer getStoresId() {
        return storesId;
    }

    public void setStoresId(Integer storesId) {
        this.storesId = storesId;
    }

    public Integer getCardDiscount() {
        return cardDiscount;
    }

    public void setCardDiscount(Integer cardDiscount) {
        this.cardDiscount = cardDiscount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}