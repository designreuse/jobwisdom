package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 
* @author 骆峰
* @date 2016年8月4日 下午1:51:41
 */
public class StoreManageRule {
    /** 规则标识 */
    private Integer ruleId;

    /** 门店标识 */
    private Integer storeId;

    /** 规则名称 */
    private String ruleName;

    /** 规则描述 */
    private String ruleDesc;

    /** 处理方式(1:奖励，2:惩罚) */
    private Integer processType;

    /** 奖惩金额 */
    private BigDecimal processMoney;

    /** 最后操作人标识 */
    private Integer lastOperatorId;

    /** 最后操作时间 */
    private String lastOperatorTime;
    
    /** 门店名称 */
    private String storeName;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc == null ? null : ruleDesc.trim();
    }

    public Integer getProcessType() {
        return processType;
    }

    public void setProcessType(Integer processType) {
        this.processType = processType;
    }

    public BigDecimal getProcessMoney() {
        return processMoney;
    }

    public void setProcessMoney(BigDecimal processMoney) {
        this.processMoney = processMoney;
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    public String getLastOperatorTime() {
        return lastOperatorTime;
    }

    public void setLastOperatorTime(String lastOperatorTime) {
        this.lastOperatorTime = lastOperatorTime == null ? null : lastOperatorTime.trim();
    }
}