package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class MembersWater {
    private Integer membersWaterId;

    private Integer membersId;

    private Integer type;

    private Integer waterType;

    private BigDecimal amount;

    private Integer givingIntegral;
    
    private Integer storesId;
    
    private String createTime;

    public Integer getMembersWaterId() {
        return membersWaterId;
    }

    public void setMembersWaterId(Integer membersWaterId) {
        this.membersWaterId = membersWaterId;
    }

    public Integer getMembersId() {
        return membersId;
    }

    public void setMembersId(Integer membersId) {
        this.membersId = membersId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getWaterType() {
        return waterType;
    }

    public void setWaterType(Integer waterType) {
        this.waterType = waterType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getGivingIntegral() {
        return givingIntegral;
    }

    public void setGivingIntegral(Integer givingIntegral) {
        this.givingIntegral = givingIntegral;
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
		this.createTime = createTime;
	}
    
}