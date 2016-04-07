package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 耕宇异常会员数据
* @author 高国藩
* @date 2016年1月13日 下午1:58:35
 */
public class MemberErrorGy {
    
    /***/
    private Integer id;
    /***/
    private String phone;
    /***/
    private String name;
    /***/
    private String sex;
    /***/
    private String createTime;
    /***/
    private String levelNum;
    /***/
    private String levelName;
    /***/
    private BigDecimal discount;
    /***/
    private BigDecimal totalAmount;
    /***/
    private BigDecimal totalConsumeAmount;
    /***/
    private BigDecimal balanceAmount;
    /***/
    private Integer consumeCount;
    /***/
    private Integer balanceIntegral;
    /***/
    private BigDecimal debtAmount;
    /***/
    private BigDecimal avgConsumeAmount;
    /***/
    private Integer storeId;
    /***/
    private Integer lastOperatorId;
    /***/
    private Integer isDeleted;
    /***/
    private String updateTime;
    /***/
    private BigDecimal balanceAmounts;
    /***/
    private BigDecimal debtAmounts;
    

    public BigDecimal getBalanceAmounts() {
        return balanceAmounts;
    }

    public void setBalanceAmounts(BigDecimal balanceAmounts) {
        this.balanceAmounts = balanceAmounts;
    }

    public BigDecimal getDebtAmounts() {
        return debtAmounts;
    }

    public void setDebtAmounts(BigDecimal debtAmounts) {
        this.debtAmounts = debtAmounts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(String levelNum) {
        this.levelNum = levelNum == null ? null : levelNum.trim();
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalConsumeAmount() {
        return totalConsumeAmount;
    }

    public void setTotalConsumeAmount(BigDecimal totalConsumeAmount) {
        this.totalConsumeAmount = totalConsumeAmount;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Integer getConsumeCount() {
        return consumeCount;
    }

    public void setConsumeCount(Integer consumeCount) {
        this.consumeCount = consumeCount;
    }

    public Integer getBalanceIntegral() {
        return balanceIntegral;
    }

    public void setBalanceIntegral(Integer balanceIntegral) {
        this.balanceIntegral = balanceIntegral;
    }

    public BigDecimal getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(BigDecimal debtAmount) {
        this.debtAmount = debtAmount;
    }

    public BigDecimal getAvgConsumeAmount() {
        return avgConsumeAmount;
    }

    public void setAvgConsumeAmount(BigDecimal avgConsumeAmount) {
        this.avgConsumeAmount = avgConsumeAmount;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}