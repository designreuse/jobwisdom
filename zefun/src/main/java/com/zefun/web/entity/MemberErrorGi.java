package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 共赢会员数据
* @author 高国藩
* @date 2016年3月2日 下午2:17:46
 */
public class MemberErrorGi {
    
    /**id*/
    private Integer id;
    /**id*/
    private String name;
    /**id*/
    private String levelName;
    /**id*/
    private String levelNum;
    /**id*/
    private String phone;
    /**id*/
    private String sex;
    /**id*/
    private BigDecimal balanceAmount;
    /**id*/
    private BigDecimal balanceGiftmoneyAmount;
    /**id*/
    private BigDecimal totalConsumeAmount;
    /**id*/
    private String lastConsumeTime;
    /**id*/
    private Integer storeId;
    /**id*/
    private Integer isDeleted;
    /**id*/
    private Integer lastOperatorId;
    /**id*/
    private String updateTime;
    /**id*/
    private BigDecimal balanceAmounts;
    /**id*/
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

    public BigDecimal getTotalConsumeAmount() {
        return totalConsumeAmount;
    }

    public void setTotalConsumeAmount(BigDecimal totalConsumeAmount) {
        this.totalConsumeAmount = totalConsumeAmount;
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