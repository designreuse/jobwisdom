package com.zefun.wechat.dto;

import java.math.BigDecimal;

/**
 * 员工业绩值和类型dto
 * 
 * @author DavidLiang
 * @date 2016年1月27日 下午3:42:38
 */
public class CommissionValueAndTypeDto {

	/** 订单类型 */
	private Integer orderType;

	/** 业绩值 */
	private BigDecimal commissionCalculate;

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public BigDecimal getCommissionCalculate() {
		return commissionCalculate;
	}

	public void setCommissionCalculate(BigDecimal commissionCalculate) {
		this.commissionCalculate = commissionCalculate;
	}

}
