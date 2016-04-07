package com.lives.platform.web.dto;

import java.math.BigDecimal;

public class PositionDto {
	
	private Integer positionId;
	private Integer discount;
	private BigDecimal amount;
	private Integer discountAssign;
	private BigDecimal amountAssign;
	
	public Integer getPositionId() {
		return positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getDiscountAssign() {
		return discountAssign;
	}
	public void setDiscountAssign(Integer discountAssign) {
		this.discountAssign = discountAssign;
	}
	public BigDecimal getAmountAssign() {
		return amountAssign;
	}
	public void setAmountAssign(BigDecimal amountAssign) {
		this.amountAssign = amountAssign;
	}
	
}
