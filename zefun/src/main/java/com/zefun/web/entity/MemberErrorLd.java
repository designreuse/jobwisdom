package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 蓝蝶会员数据
* @author 高国藩
* @date 2016年2月22日 下午3:49:53
 */
public class MemberErrorLd {
    
    /** */
    private Integer id;
    /** */
    private String name;
    /** */
    private String levelNum;
    /** */
    private String phone;
    /** */
    private String sex;
    /** */
    private BigDecimal balanceAmount;
    /** */
    private BigDecimal balanceIntegral;
    /** */
    private String lastConsumeTime;
    /** */
    private Integer storeId;
    /** */
    private Integer isDeleted;
    /** */
    private Integer lastOperatorId;
    /** */
    private String updateTime;
    /** */
    private BigDecimal balanceAmounts;
    /** */
    private BigDecimal debtAmounts;
    /** */
    private BigDecimal debtAmount;
    
    
    public BigDecimal getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(BigDecimal debtAmount) {
        this.debtAmount = debtAmount;
    }

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

    public BigDecimal getBalanceIntegral() {
        return balanceIntegral;
    }

    public void setBalanceIntegral(BigDecimal balanceIntegral) {
        this.balanceIntegral = balanceIntegral;
    }

    public String getLastConsumeTime() {
        return lastConsumeTime;
    }

    public void setLastConsumeTime(String lastConsumeTime) {
        this.lastConsumeTime = lastConsumeTime == null ? null : lastConsumeTime.trim();
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