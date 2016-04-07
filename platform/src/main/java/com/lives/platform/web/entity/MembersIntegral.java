package com.lives.platform.web.entity;

public class MembersIntegral {
    private Integer membersIntegralId;

    private Integer membersId;
    
    private Integer membersOpenCardId;

    private Integer referrer;

    private Integer openCardIntegral;

    private Integer topupIntegral;

    private Integer consumptionIntegral;
    
    private Integer storesId;
    
    private String createTime;

    public Integer getMembersIntegralId() {
        return membersIntegralId;
    }

    public void setMembersIntegralId(Integer membersIntegralId) {
        this.membersIntegralId = membersIntegralId;
    }

    public Integer getMembersId() {
        return membersId;
    }

    public void setMembersId(Integer membersId) {
        this.membersId = membersId;
    }

    public Integer getMembersOpenCardId() {
		return membersOpenCardId;
	}

	public void setMembersOpenCardId(Integer membersOpenCardId) {
		this.membersOpenCardId = membersOpenCardId;
	}

	public Integer getReferrer() {
        return referrer;
    }

    public void setReferrer(Integer referrer) {
        this.referrer = referrer;
    }

    public Integer getOpenCardIntegral() {
        return openCardIntegral;
    }

    public void setOpenCardIntegral(Integer openCardIntegral) {
        this.openCardIntegral = openCardIntegral;
    }

    public Integer getTopupIntegral() {
        return topupIntegral;
    }

    public void setTopupIntegral(Integer topupIntegral) {
        this.topupIntegral = topupIntegral;
    }

    public Integer getConsumptionIntegral() {
        return consumptionIntegral;
    }

    public void setConsumptionIntegral(Integer consumptionIntegral) {
        this.consumptionIntegral = consumptionIntegral;
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