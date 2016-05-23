package com.zefun.web.entity;

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
    private Integer flowAmount;

    /**
     * 当前余额
     */
    private Integer balanceAmount;

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

    public Integer getFlowAmount() {
        return flowAmount;
    }

    public void setFlowAmount(Integer flowAmount) {
        this.flowAmount = flowAmount;
    }

    public Integer getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Integer balanceAmount) {
        this.balanceAmount = balanceAmount;
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