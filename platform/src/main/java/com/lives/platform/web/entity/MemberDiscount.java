package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class MemberDiscount {
    private Integer memberDiscountId;

    private Integer projectInfoId;

    private Integer cardNameId;//memberCardNo

    private BigDecimal amount;//fixedPrice

    private Integer discount;

    private String createTime;
    
    private Integer storesId;

    public Integer getMemberDiscountId() {
        return memberDiscountId;
    }

    public void setMemberDiscountId(Integer memberDiscountId) {
        this.memberDiscountId = memberDiscountId;
    }

    public Integer getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(Integer projectInfoId) {
        this.projectInfoId = projectInfoId;
    }

    public Integer getCardNameId() {
		return cardNameId;
	}

	public void setCardNameId(Integer cardNameId) {
		this.cardNameId = cardNameId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

	public Integer getStoresId() {
		return storesId;
	}

	public void setStoresId(Integer storesId) {
		this.storesId = storesId;
	}
    
}