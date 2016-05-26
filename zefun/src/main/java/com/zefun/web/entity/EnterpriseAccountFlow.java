package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 企业账户流水表
* @author 老王
* @date 2016年5月21日 下午6:41:58
 */
public class EnterpriseAccountFlow {
	/**
	 * 账户标识
	 */
    private Integer accountFlowId;

    /**
     * 账户标识
     */
    private Integer enterpriseAccountId;

    /**
     * 流水类型(1:支出,2:收入)
     */
    private Integer flowType;

    /**
     * 流水金额
     */
    private BigDecimal flowAmount;

    /**
     * 当前余额
     */
    private BigDecimal balanceAmount;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 最后操作人标识
     */
    private Integer lastOperatorId;
    
    /**
     * 是否删除(0:未删除,1:已删除)
     */
    private Integer isDeleted;

    
    
    public BigDecimal getFlowAmount() {
		return flowAmount;
	}

	public void setFlowAmount(BigDecimal flowAmount) {
		this.flowAmount = flowAmount;
	}

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getAccountFlowId() {
        return accountFlowId;
    }

    public void setAccountFlowId(Integer accountFlowId) {
        this.accountFlowId = accountFlowId;
    }

    public Integer getEnterpriseAccountId() {
        return enterpriseAccountId;
    }

    public void setEnterpriseAccountId(Integer enterpriseAccountId) {
        this.enterpriseAccountId = enterpriseAccountId;
    }

    public Integer getFlowType() {
        return flowType;
    }

    public void setFlowType(Integer flowType) {
        this.flowType = flowType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }
}