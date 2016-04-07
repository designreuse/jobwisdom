package com.lives.platform.web.dto;

import java.math.BigDecimal;

public class MembersDto {
	
	private Integer storesId;
	/**
	 * 会员名称
	 */
	private String membersName;

	/**
	 * 手机号
	 */
    private String membersMobile;
    
    /**
     * 会员卡类型
     */
    private Integer membersType;
    
    /**
     * 会员卡名称
     */
    private Integer cardNameId;

    /**
     * 性别
     */
    private Integer sex;
    
    /**
     * 礼金金额
     */
    private BigDecimal cashGiftAmountStart;
    private BigDecimal cashGiftAmountEnd;
    
    /**
     * 礼金余额
     */
    private BigDecimal cashGiftBalanceStart;
    private BigDecimal cashGiftBalanceEnd;

    /**
     * 充值总额
     */
    private BigDecimal topupTotalAmountStart;
    private BigDecimal topupTotalAmountEnd;
    
    /**
     * 注册时间
     */
    private String registerTimeStart;
    private String registerTimeEnd;
    
    /**
     * 消费金额
     */
    private BigDecimal consumeTotalAmountStart;
    private BigDecimal consumeTotalAmountEnd;
    
    /**
     * 消费次数
     */
    private BigDecimal consumeNumStart;
    private BigDecimal consumeNumEnd;
    
    /**
     * 账户余额
     */
    private BigDecimal accountBalanceStart;
    private BigDecimal accountBalanceEnd;
    
    
	public Integer getStoresId() {
		return storesId;
	}
	public void setStoresId(Integer storesId) {
		this.storesId = storesId;
	}
	public String getMembersName() {
		return membersName;
	}
	public void setMembersName(String membersName) {
		this.membersName = membersName;
	}
	public String getMembersMobile() {
		return membersMobile;
	}
	public void setMembersMobile(String membersMobile) {
		this.membersMobile = membersMobile;
	}
	
	public Integer getMembersType() {
		return membersType;
	}
	public void setMembersType(Integer membersType) {
		this.membersType = membersType;
	}
	public Integer getCardNameId() {
		return cardNameId;
	}
	public void setCardNameId(Integer cardNameId) {
		this.cardNameId = cardNameId;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public BigDecimal getCashGiftAmountStart() {
		return cashGiftAmountStart;
	}
	public void setCashGiftAmountStart(BigDecimal cashGiftAmountStart) {
		this.cashGiftAmountStart = cashGiftAmountStart;
	}
	public BigDecimal getCashGiftAmountEnd() {
		return cashGiftAmountEnd;
	}
	public void setCashGiftAmountEnd(BigDecimal cashGiftAmountEnd) {
		this.cashGiftAmountEnd = cashGiftAmountEnd;
	}
	public BigDecimal getCashGiftBalanceStart() {
		return cashGiftBalanceStart;
	}
	public void setCashGiftBalanceStart(BigDecimal cashGiftBalanceStart) {
		this.cashGiftBalanceStart = cashGiftBalanceStart;
	}
	public BigDecimal getCashGiftBalanceEnd() {
		return cashGiftBalanceEnd;
	}
	public void setCashGiftBalanceEnd(BigDecimal cashGiftBalanceEnd) {
		this.cashGiftBalanceEnd = cashGiftBalanceEnd;
	}
	public BigDecimal getTopupTotalAmountStart() {
		return topupTotalAmountStart;
	}
	public void setTopupTotalAmountStart(BigDecimal topupTotalAmountStart) {
		this.topupTotalAmountStart = topupTotalAmountStart;
	}
	public BigDecimal getTopupTotalAmountEnd() {
		return topupTotalAmountEnd;
	}
	public void setTopupTotalAmountEnd(BigDecimal topupTotalAmountEnd) {
		this.topupTotalAmountEnd = topupTotalAmountEnd;
	}
	public String getRegisterTimeStart() {
		return registerTimeStart;
	}
	public void setRegisterTimeStart(String registerTimeStart) {
		this.registerTimeStart = registerTimeStart;
	}
	public String getRegisterTimeEnd() {
		return registerTimeEnd;
	}
	public void setRegisterTimeEnd(String registerTimeEnd) {
		this.registerTimeEnd = registerTimeEnd;
	}
	public BigDecimal getConsumeTotalAmountStart() {
		return consumeTotalAmountStart;
	}
	public void setConsumeTotalAmountStart(BigDecimal consumeTotalAmountStart) {
		this.consumeTotalAmountStart = consumeTotalAmountStart;
	}
	public BigDecimal getConsumeTotalAmountEnd() {
		return consumeTotalAmountEnd;
	}
	public void setConsumeTotalAmountEnd(BigDecimal consumeTotalAmountEnd) {
		this.consumeTotalAmountEnd = consumeTotalAmountEnd;
	}
	public BigDecimal getConsumeNumStart() {
		return consumeNumStart;
	}
	public void setConsumeNumStart(BigDecimal consumeNumStart) {
		this.consumeNumStart = consumeNumStart;
	}
	public BigDecimal getConsumeNumEnd() {
		return consumeNumEnd;
	}
	public void setConsumeNumEnd(BigDecimal consumeNumEnd) {
		this.consumeNumEnd = consumeNumEnd;
	}
	public BigDecimal getAccountBalanceStart() {
		return accountBalanceStart;
	}
	public void setAccountBalanceStart(BigDecimal accountBalanceStart) {
		this.accountBalanceStart = accountBalanceStart;
	}
	public BigDecimal getAccountBalanceEnd() {
		return accountBalanceEnd;
	}
	public void setAccountBalanceEnd(BigDecimal accountBalanceEnd) {
		this.accountBalanceEnd = accountBalanceEnd;
	}
    
}
