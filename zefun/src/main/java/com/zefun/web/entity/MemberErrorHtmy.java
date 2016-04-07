package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 华拓美业
* @author 高国藩
* @date 2016年3月15日 下午4:37:07
 */
public class MemberErrorHtmy {
    /***/
    private Integer id;
    /***/
    private String name;
    /***/
    private String levelName;
    /***/
    private String levelNum;
    /***/
    private String phone;
    /***/
    private String sex;
    /***/
    private BigDecimal balanceAmount;
    /***/
    private BigDecimal balanceGiftmoneyAmount;
    /***/
    private BigDecimal balanceIntegral;
    /***/
    private Integer storeId;
    /***/
    private Integer isDeleted;
    /***/
    private Integer lastOperatorId;
    /***/
    private String updateTime;
    /***/
    private BigDecimal balanceAmounts;
    /***/
    private BigDecimal debtAmounts;
    
    
    public BigDecimal getDebtAmounts() {
        return debtAmounts;
    }

    public void setDebtAmounts(BigDecimal debtAmounts) {
        this.debtAmounts = debtAmounts;
    }

    public BigDecimal getBalanceAmounts() {
        return balanceAmounts;
    }

    public void setBalanceAmounts(BigDecimal balanceAmounts) {
        this.balanceAmounts = balanceAmounts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    public String getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(String levelNum) {
        this.levelNum = levelNum == null ? null : levelNum.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public BigDecimal getBalanceGiftmoneyAmount() {
        return balanceGiftmoneyAmount;
    }

    public void setBalanceGiftmoneyAmount(BigDecimal balanceGiftmoneyAmount) {
        this.balanceGiftmoneyAmount = balanceGiftmoneyAmount;
    }

    public BigDecimal getBalanceIntegral() {
        return balanceIntegral;
    }

    public void setBalanceIntegral(BigDecimal balanceIntegral) {
        this.balanceIntegral = balanceIntegral;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}