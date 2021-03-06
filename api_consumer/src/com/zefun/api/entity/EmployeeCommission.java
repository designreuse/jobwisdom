package com.zefun.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author 小高
 * @date 2015年08月10日 PM 22:38:58
 */
public class EmployeeCommission implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -524882484005731116L;
	
	/**
	 * 无参构造器
	 */
	public EmployeeCommission() {
		
	}
	
	/**
	 * 设置员工提成构造器的构造器
	 */
	public EmployeeCommission(Integer detailId, Integer shiftMahjongStepId, Integer orderType, Integer employeeId, BigDecimal commissionCalculate, BigDecimal commissionAmount,
			String chargeTime) {
		this.detailId = detailId;
		this.shiftMahjongStepId = shiftMahjongStepId;
		this.orderType = orderType;
		this.employeeId = employeeId;
		this.commissionCalculate = commissionCalculate;
		this.commissionAmount = commissionAmount;
		this.chargeTime = chargeTime;
	}

	/** 提成标识 */
	private Integer commissionId;

	/** 订单明细标识 */
	private Integer detailId;
	
	/** 轮牌步骤*/
	private Integer shiftMahjongStepId;
	
	/** 订单类型(1:项目,2:商品,3:套餐,4、开卡充值) */
	private Integer orderType;

	/** 提成员工标识 */
	private Integer employeeId;
	
	/** 业绩计算 */
	private BigDecimal commissionCalculate;

	/** 提成金额 */
	private BigDecimal commissionAmount;

	/** 发生时间 */
	private String chargeTime;

	
	public Integer getShiftMahjongStepId() {
        return shiftMahjongStepId;
    }

    public void setShiftMahjongStepId(Integer shiftMahjongStepId) {
        this.shiftMahjongStepId = shiftMahjongStepId;
    }

    public Integer getCommissionId() {
		return commissionId;
	}

	public void setCommissionId(Integer commissionId) {
		this.commissionId = commissionId;
	}

	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public BigDecimal getCommissionCalculate() {
		return commissionCalculate;
	}

	public void setCommissionCalculate(BigDecimal commissionCalculate) {
		this.commissionCalculate = commissionCalculate;
	}

	public BigDecimal getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(BigDecimal commissionAmount) {
		this.commissionAmount = commissionAmount;
	}

	public String getChargeTime() {
		return chargeTime;
	}

	public void setChargeTime(String chargeTime) {
		this.chargeTime = chargeTime;
	}
	
	@Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
            ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}