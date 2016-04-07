package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class Members {
    private Integer membersId;

    private Integer storesId;

    private Integer membersInfoId;

    private Integer membersCardId;

    private Integer cardNameId;// = membersTypeName

    private String membersName;

    private String membersMobile;

    private Integer sex;

    private String birthday;

    private Integer isLunarCal;

    private Integer membersType;
    
    private Integer membersTypeName;

    private String registerTime;

    private BigDecimal consumeTotalAmount;

    private BigDecimal consumeNum;

    private BigDecimal cashGiftAmount;

    private BigDecimal cashGiftBalance;

    private BigDecimal topupTotalAmount;

    private BigDecimal accountBalance;

    private BigDecimal storedTotalAmount;

    private BigDecimal storedBalance;

    private String lastConsumptionDate;

    private String createTime;

    private Integer systemCardNo;

    private Integer entityCardNo;

    private Integer openCardWay;

    private Integer directReferrer;
    
    private Integer indirectReferrer;

    private Integer openCardSendIntegral;
    
    private String openCardDate;
    
    private String payPwd;

    public Integer getMembersId() {
        return membersId;
    }

    public void setMembersId(Integer membersId) {
        this.membersId = membersId;
    }

    public Integer getStoresId() {
        return storesId;
    }

    public void setStoresId(Integer storesId) {
        this.storesId = storesId;
    }

    public Integer getMembersInfoId() {
        return membersInfoId;
    }

    public void setMembersInfoId(Integer membersInfoId) {
        this.membersInfoId = membersInfoId;
    }

    public Integer getMembersCardId() {
        return membersCardId;
    }

    public void setMembersCardId(Integer membersCardId) {
        this.membersCardId = membersCardId;
    }

    public Integer getCardNameId() {
        return cardNameId;
    }

    public void setCardNameId(Integer cardNameId) {
        this.cardNameId = cardNameId;
    }

    public String getMembersName() {
        return membersName;
    }

    public void setMembersName(String membersName) {
        this.membersName = membersName == null ? null : membersName.trim();
    }

    public String getMembersMobile() {
        return membersMobile;
    }

    public void setMembersMobile(String membersMobile) {
        this.membersMobile = membersMobile == null ? null : membersMobile.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public Integer getIsLunarCal() {
        return isLunarCal;
    }

    public void setIsLunarCal(Integer isLunarCal) {
        this.isLunarCal = isLunarCal;
    }

    public Integer getMembersType() {
        return membersType;
    }

    public void setMembersType(Integer membersType) {
        this.membersType = membersType;
    }

    public Integer getMembersTypeName() {
		return membersTypeName;
	}

	public void setMembersTypeName(Integer membersTypeName) {
		this.membersTypeName = membersTypeName;
	}

	public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime == null ? null : registerTime.trim();
    }

    public BigDecimal getConsumeTotalAmount() {
        return consumeTotalAmount;
    }

    public void setConsumeTotalAmount(BigDecimal consumeTotalAmount) {
        this.consumeTotalAmount = consumeTotalAmount;
    }

    public BigDecimal getConsumeNum() {
        return consumeNum;
    }

    public void setConsumeNum(BigDecimal consumeNum) {
        this.consumeNum = consumeNum;
    }

    public BigDecimal getCashGiftAmount() {
        return cashGiftAmount;
    }

    public void setCashGiftAmount(BigDecimal cashGiftAmount) {
        this.cashGiftAmount = cashGiftAmount;
    }

    public BigDecimal getCashGiftBalance() {
        return cashGiftBalance;
    }

    public void setCashGiftBalance(BigDecimal cashGiftBalance) {
        this.cashGiftBalance = cashGiftBalance;
    }

    public BigDecimal getTopupTotalAmount() {
        return topupTotalAmount;
    }

    public void setTopupTotalAmount(BigDecimal topupTotalAmount) {
        this.topupTotalAmount = topupTotalAmount;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public BigDecimal getStoredTotalAmount() {
        return storedTotalAmount;
    }

    public void setStoredTotalAmount(BigDecimal storedTotalAmount) {
        this.storedTotalAmount = storedTotalAmount;
    }

    public BigDecimal getStoredBalance() {
        return storedBalance;
    }

    public void setStoredBalance(BigDecimal storedBalance) {
        this.storedBalance = storedBalance;
    }

    public String getLastConsumptionDate() {
        return lastConsumptionDate;
    }

    public void setLastConsumptionDate(String lastConsumptionDate) {
        this.lastConsumptionDate = lastConsumptionDate == null ? null : lastConsumptionDate.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Integer getSystemCardNo() {
        return systemCardNo;
    }

    public void setSystemCardNo(Integer systemCardNo) {
        this.systemCardNo = systemCardNo;
    }

    public Integer getEntityCardNo() {
        return entityCardNo;
    }

    public void setEntityCardNo(Integer entityCardNo) {
        this.entityCardNo = entityCardNo;
    }

    public Integer getOpenCardWay() {
        return openCardWay;
    }

    public void setOpenCardWay(Integer openCardWay) {
        this.openCardWay = openCardWay;
    }

    public Integer getDirectReferrer() {
        return directReferrer;
    }

    public void setDirectReferrer(Integer directReferrer) {
        this.directReferrer = directReferrer;
    }

    public Integer getIndirectReferrer() {
		return indirectReferrer;
	}

	public void setIndirectReferrer(Integer indirectReferrer) {
		this.indirectReferrer = indirectReferrer;
	}

	public Integer getOpenCardSendIntegral() {
        return openCardSendIntegral;
    }

    public void setOpenCardSendIntegral(Integer openCardSendIntegral) {
        this.openCardSendIntegral = openCardSendIntegral;
    }

	public String getOpenCardDate() {
		return openCardDate;
	}

	public void setOpenCardDate(String openCardDate) {
		this.openCardDate = openCardDate;
	}

	public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}
    
}