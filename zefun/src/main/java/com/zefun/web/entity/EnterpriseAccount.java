package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 企业账户表
* @author 老王
* @date 2016年5月21日 下午6:40:00
 */
public class EnterpriseAccount {
	
	/**
	 * 账户标识
	 */
    private Integer enterpriseAccountId;

    /**
     * 上线时间
     */
    private String beginTime;

    /**
     * 到期时间
     */
    private String finishTime;

    /**
     * 累计充值
     */
    private BigDecimal totalAmount;

    /**
     * 当前余额
     */
    private BigDecimal balanceAmount;

    /**
     * 累计充值短信
     */
    private Integer totalMsnNum;

    /**
     * 短信余额
     */
    private Integer balanceMsnNum;

    /**
     * 当前使用版本
     */
    private String enterpriseEdition;

    /**
     * 总创建门店数
     */
    private Integer totalStoreNum;

    /**
     * 已创建门店数
     */
    private Integer alreadyStoreNum;

    /**
     * 剩余创建门店数
     */
    private Integer balanceStoreNum;

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

    public Integer getEnterpriseAccountId() {
        return enterpriseAccountId;
    }

    public void setEnterpriseAccountId(Integer enterpriseAccountId) {
        this.enterpriseAccountId = enterpriseAccountId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime == null ? null : beginTime.trim();
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime == null ? null : finishTime.trim();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Integer getTotalMsnNum() {
        return totalMsnNum;
    }

    public void setTotalMsnNum(Integer totalMsnNum) {
        this.totalMsnNum = totalMsnNum;
    }

    public Integer getBalanceMsnNum() {
        return balanceMsnNum;
    }

    public void setBalanceMsnNum(Integer balanceMsnNum) {
        this.balanceMsnNum = balanceMsnNum;
    }

    public String getEnterpriseEdition() {
        return enterpriseEdition;
    }

    public void setEnterpriseEdition(String enterpriseEdition) {
        this.enterpriseEdition = enterpriseEdition == null ? null : enterpriseEdition.trim();
    }

    public Integer getTotalStoreNum() {
        return totalStoreNum;
    }

    public void setTotalStoreNum(Integer totalStoreNum) {
        this.totalStoreNum = totalStoreNum;
    }

    public Integer getAlreadyStoreNum() {
        return alreadyStoreNum;
    }

    public void setAlreadyStoreNum(Integer alreadyStoreNum) {
        this.alreadyStoreNum = alreadyStoreNum;
    }

    public Integer getBalanceStoreNum() {
        return balanceStoreNum;
    }

    public void setBalanceStoreNum(Integer balanceStoreNum) {
        this.balanceStoreNum = balanceStoreNum;
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