package com.lives.platform.web.entity;

import java.math.BigDecimal;

public class Dommission {
    private Integer dommissionId;

    private Integer projectInfoId;

    private Integer employeeId;

    private BigDecimal amount;

    private Integer discount;
    
    private BigDecimal amountAssign;//fixedCommission

    private Integer discountAssign;//percentageCommission

    private String createTime;

    public Integer getDommissionId() {
        return dommissionId;
    }

    public void setDommissionId(Integer dommissionId) {
        this.dommissionId = dommissionId;
    }

    public Integer getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(Integer projectInfoId) {
        this.projectInfoId = projectInfoId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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

	public BigDecimal getAmountAssign() {
		return amountAssign;
	}

	public void setAmountAssign(BigDecimal amountAssign) {
		this.amountAssign = amountAssign;
	}

	public Integer getDiscountAssign() {
		return discountAssign;
	}

	public void setDiscountAssign(Integer discountAssign) {
		this.discountAssign = discountAssign;
	}

	public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}