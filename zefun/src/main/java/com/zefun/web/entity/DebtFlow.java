package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 挂账流水表
* @author 高国藩
* @date 2015年12月28日 下午8:02:18
 */
public class DebtFlow {
    
    /**主键*/
    private Integer debtId;
    /**单号*/
    private Integer orderId;
    /**流水类型(1:挂账,2:还款)*/
    private Integer flowType;
    /**流水描述(1:开卡充值,2:会员消费,3:会员导入)*/
    private String flowDesc;
    /**会员标识*/
    private Integer accountId;
    /**流水金额*/
    private BigDecimal inAmount;
    /**挂账/欠款日期*/
    private String flowDebtTime;
    /**操作人标示*/
    private Integer lastOperatorId;
    /**是否删除(0:未删除,1:已删除)*/
    private Integer isDeleted;

    public Integer getDebtId() {
        return debtId;
    }

    public void setDebtId(Integer debtId) {
        this.debtId = debtId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getFlowType() {
        return flowType;
    }

    public void setFlowType(Integer flowType) {
        this.flowType = flowType;
    }

    public String getFlowDesc() {
        return flowDesc;
    }

    public void setFlowDesc(String flowDesc) {
        this.flowDesc = flowDesc == null ? null : flowDesc.trim();
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getInAmount() {
        return inAmount;
    }

    public void setInAmount(BigDecimal inAmount) {
        this.inAmount = inAmount;
    }

    public String getFlowDebtTime() {
        return flowDebtTime;
    }

    public void setFlowDebtTime(String flowDebtTime) {
        this.flowDebtTime = flowDebtTime == null ? null : flowDebtTime.trim();
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
    
}