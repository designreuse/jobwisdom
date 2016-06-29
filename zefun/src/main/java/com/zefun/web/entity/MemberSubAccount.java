package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2016年03月12日 PM 14:03:53
 */
public class MemberSubAccount {
    /** 子账户标识 */
    private Integer subAccountId;

    /** 账户标识 */
    private Integer accountId;

    /** 级别标识 */
    private Integer levelId;

    /** 储值总额 */
    private BigDecimal totalAmount;

    /** 赠送总额 */
    private BigDecimal totalPresentAmount;

    /** 储值余额 */
    private BigDecimal balanceAmount;

    /** 使用金额 */
    private BigDecimal useAmount;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 最后操作人标识 */
    private Integer lastOperatorId;

    /** 是否删除(1删除，0正常)*/
    private Integer isDeleted;

    /** @param subAccountId	子账户标识 */
    public void setSubAccountId(Integer subAccountId) {
        this.subAccountId = subAccountId;
    }

    /** @return	子账户标识 */
    public Integer getSubAccountId() {
        return subAccountId;
    }

    /** @param accountId	账户标识 */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /** @return	账户标识 */
    public Integer getAccountId() {
        return accountId;
    }

    /** @param levelId	级别标识 */
    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    /** @return	级别标识 */
    public Integer getLevelId() {
        return levelId;
    }

    /** @param totalAmount	储值总额 */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /** @return	储值总额 */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /** @param totalPresentAmount	赠送总额 */
    public void setTotalPresentAmount(BigDecimal totalPresentAmount) {
        this.totalPresentAmount = totalPresentAmount;
    }

    /** @return	赠送总额 */
    public BigDecimal getTotalPresentAmount() {
        return totalPresentAmount;
    }

    /** @param balanceAmount	储值余额 */
    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    /** @return	储值余额 */
    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    /** @param useAmount	使用金额 */
    public void setUseAmount(BigDecimal useAmount) {
        this.useAmount = useAmount;
    }

    /** @return	使用金额 */
    public BigDecimal getUseAmount() {
        return useAmount;
    }

    /** @param createTime	创建时间 */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /** @return	创建时间 */
    public String getCreateTime() {
        return createTime;
    }

    /** @param updateTime	修改时间 */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /** @return	修改时间 */
    public String getUpdateTime() {
        return updateTime;
    }

    /** @param lastOperatorId	最后操作人标识 */
    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    /** @return	最后操作人标识 */
    public Integer getLastOperatorId() {
        return lastOperatorId;
    }
     
    /**
     * 获取是否删除
    * @author 高国藩
    * @date 2016年6月29日 下午6:39:48
    * @return  isDeleted
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

}