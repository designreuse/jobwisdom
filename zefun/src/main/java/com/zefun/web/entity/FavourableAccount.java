package com.zefun.web.entity;

/**
 * 优惠活动
* @author 骆峰
* @date 2016年6月29日 下午12:12:53
 */
public class FavourableAccount {
    /** 优惠类型标识 */
    private Integer favourableId;

    /** 编号 */
    private String favourableNamber;

    /** 优惠名称 */
    private Integer favourableName;

    /** 优惠类型 （0折扣，1现金，2体验） */
    private Integer favourableType;

    /** 优惠策略 */
    private Integer favourableStrategy;

    /** 是否允许会员折扣（0允许，1不允许） */
    private Integer favourableAllow;

    /** 业绩折扣（0优惠部分，1总价） */
    private String favourablePart;

    /** 美发/手艺师  */
    private String favourableHair;

    /** 技师 */
    private String favourableTech;

    /** 助理 */
    private String favourableAssistant;

    /** 企业id */
    private String storeAccount;

    /** 门店id */
    private Integer storeId;

    /** 是否停用（0整除，1停用） */
    private Integer fullstop;

    /** （0整除，1删除） */
    private Integer isDeleted;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    public Integer getFavourableId() {
        return favourableId;
    }

    public void setFavourableId(Integer favourableId) {
        this.favourableId = favourableId;
    }

    public String getFavourableNamber() {
        return favourableNamber;
    }

    public void setFavourableNamber(String favourableNamber) {
        this.favourableNamber = favourableNamber == null ? null : favourableNamber.trim();
    }

    public Integer getFavourableName() {
        return favourableName;
    }

    public void setFavourableName(Integer favourableName) {
        this.favourableName = favourableName;
    }

    public Integer getFavourableType() {
        return favourableType;
    }

    public void setFavourableType(Integer favourableType) {
        this.favourableType = favourableType;
    }

    public Integer getFavourableStrategy() {
        return favourableStrategy;
    }

    public void setFavourableStrategy(Integer favourableStrategy) {
        this.favourableStrategy = favourableStrategy;
    }

    public Integer getFavourableAllow() {
        return favourableAllow;
    }

    public void setFavourableAllow(Integer favourableAllow) {
        this.favourableAllow = favourableAllow;
    }

    public String getFavourablePart() {
        return favourablePart;
    }

    public void setFavourablePart(String favourablePart) {
        this.favourablePart = favourablePart == null ? null : favourablePart.trim();
    }

    public String getFavourableHair() {
        return favourableHair;
    }

    public void setFavourableHair(String favourableHair) {
        this.favourableHair = favourableHair == null ? null : favourableHair.trim();
    }

    public String getFavourableTech() {
        return favourableTech;
    }

    public void setFavourableTech(String favourableTech) {
        this.favourableTech = favourableTech == null ? null : favourableTech.trim();
    }

    public String getFavourableAssistant() {
        return favourableAssistant;
    }

    public void setFavourableAssistant(String favourableAssistant) {
        this.favourableAssistant = favourableAssistant == null ? null : favourableAssistant.trim();
    }

    public String getStoreAccount() {
        return storeAccount;
    }

    public void setStoreAccount(String storeAccount) {
        this.storeAccount = storeAccount == null ? null : storeAccount.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getFullstop() {
        return fullstop;
    }

    public void setFullstop(Integer fullstop) {
        this.fullstop = fullstop;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}