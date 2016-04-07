package com.zefun.web.entity;

import java.math.BigDecimal;

/**
* @author 乐建建
* @date 2016年3月7日 上午11:29:17 
*/
public class ShopAccountState {
    
    /**状态id*/
    private Integer stateId;
    /**发起对账门店id*/
    private Integer launchStoreId;
    /**被对账门店*/
    private Integer reconciliationStoreId;
    /**对账金额*/
    private BigDecimal amount;
    /**对账日期*/
    private String reconciliationDate;
    
    /**
    * @author 乐建建
    * @date 2016年3月7日 下午1:55:06
    * @param stateId 状态id
    * @param launchStoreId 发起对账门店id
    * @param reconciliationStoreId 被对账门店id
    * @param amount 对账金额
    * @param reconciliationDate 对账日期 
    */
    public ShopAccountState(Integer stateId, Integer launchStoreId,
            Integer reconciliationStoreId, BigDecimal amount,
            String reconciliationDate) {
        super();
        this.stateId = stateId;
        this.launchStoreId = launchStoreId;
        this.reconciliationStoreId = reconciliationStoreId;
        this.amount = amount;
        this.reconciliationDate = reconciliationDate;
    }
    public Integer getStateId() {
        return stateId;
    }
    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }
    public Integer getLaunchStoreId() {
        return launchStoreId;
    }
    public void setLaunchStoreId(Integer launchStoreId) {
        this.launchStoreId = launchStoreId;
    }
    public Integer getReconciliationStoreId() {
        return reconciliationStoreId;
    }
    public void setReconciliationStoreId(Integer reconciliationStoreId) {
        this.reconciliationStoreId = reconciliationStoreId;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    /**
    * @author 乐建建
    * @date 2016年3月7日 下午1:54:59 
    */
    public ShopAccountState() {
        super();
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getReconciliationDate() {
        return reconciliationDate;
    }
    public void setReconciliationDate(String reconciliationDate) {
        this.reconciliationDate = reconciliationDate;
    }
}
