package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class MembersCashgiftStages {
    private Integer cashgiftStagesId;

    private Integer membersId;

    private BigDecimal stagesAmount;

    private Integer stagesNumber;

    private Integer storesId;
    
    private String createTime;
    
    public Integer getCashgiftStagesId() {
        return cashgiftStagesId;
    }

    public void setCashgiftStagesId(Integer cashgiftStagesId) {
        this.cashgiftStagesId = cashgiftStagesId;
    }

    public Integer getMembersId() {
        return membersId;
    }

    public void setMembersId(Integer membersId) {
        this.membersId = membersId;
    }

    public BigDecimal getStagesAmount() {
        return stagesAmount;
    }

    public void setStagesAmount(BigDecimal stagesAmount) {
        this.stagesAmount = stagesAmount;
    }

    public Integer getStagesNumber() {
        return stagesNumber;
    }

    public void setStagesNumber(Integer stagesNumber) {
        this.stagesNumber = stagesNumber;
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