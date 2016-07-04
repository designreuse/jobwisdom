package com.zefun.web.entity;
/**
 * 提成分配方案管理
* @author 老王
* @date 2016年7月2日 下午2:05:45
 */
public class CommissionScheme {
    /** 方案分配标识 */
    private Integer commissionId;

    /** 门店标识*/
    private Integer storeId;

    /** 方案类型（1：商品，2：项目）*/
    private Integer commissionType;

    /** 现金二人提成（以50:50,50:50）*/
    private String cashTwoCommission;

    /** 现金三人提成（以50:50,50:50）*/
    private String cashThreeCommission;

    /** 卡金二人提成（以50:50,50:50） */
    private String cardTwoCommission;

    /** 卡金三人提成（以50:50,50:50）*/
    private String cardThreeCommission;

    /** 创建时间 */
    private String createTime;

    /** 最后操作人标识 */
    private Integer lastOperatorId;

    /** 是否删除(0:否，1:是) */
    private Integer isDeleted;

    public Integer getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(Integer commissionId) {
        this.commissionId = commissionId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(Integer commissionType) {
        this.commissionType = commissionType;
    }

    public String getCashTwoCommission() {
        return cashTwoCommission;
    }

    public void setCashTwoCommission(String cashTwoCommission) {
        this.cashTwoCommission = cashTwoCommission == null ? null : cashTwoCommission.trim();
    }

    public String getCashThreeCommission() {
        return cashThreeCommission;
    }

    public void setCashThreeCommission(String cashThreeCommission) {
        this.cashThreeCommission = cashThreeCommission == null ? null : cashThreeCommission.trim();
    }

    public String getCardTwoCommission() {
        return cardTwoCommission;
    }

    public void setCardTwoCommission(String cardTwoCommission) {
        this.cardTwoCommission = cardTwoCommission == null ? null : cardTwoCommission.trim();
    }

    public String getCardThreeCommission() {
        return cardThreeCommission;
    }

    public void setCardThreeCommission(String cardThreeCommission) {
        this.cardThreeCommission = cardThreeCommission == null ? null : cardThreeCommission.trim();
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}