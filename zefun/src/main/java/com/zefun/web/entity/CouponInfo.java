package com.zefun.web.entity;

/**
 * 优惠卷
* @author 骆峰
* @date 2016年6月21日 下午5:50:20
 */
public class CouponInfo {
    /** 优惠劵标识 */
    private Integer couponId;

    /** 优惠劵名称 */
    private String couponName;

    /** 门店标识 */
    private Integer storeId;

    /** 企业所属门店标识 */
    private String storeType;

    /** 优惠卷使用条件*/
    private String couponNames;

    /** 优惠劵价格 */
    private Integer couponPrice;

    /** 兑换所需积分 */
    private Integer couponVantages;

    /** 优惠劵使用条件(0:通用 1:单笔订单) */
    private Integer couponType;

    /** 有效时间 */
    private String couponStartTime;

    /** 优惠劵结束时间 */
    private String couponStopTime;

    /** 优惠卷使用量 */
    private Integer couponNum;
    
    /** 优惠券发送量*/
    private Integer hasSendNum;

    /** 发布日期 */
    private String releaseTime;

    /** 优惠劵已经使用*/
    private Integer couponIsUse;

    /** 企业信息 */
    private String storeAccount;

    /** 是否删除(0:未删除，1:已删除) */
    private Integer isDelete;

    /** 是否上架（0：上架，1下架） */
    private Integer isType;

    /** 单笔订单满足多少钱使用 */
    private Integer priceSigle;

    /** 优惠卷发行量 */
    private Integer couponNumber;

    /** 每人限领 */
    private Integer couponMan;

    /** 模板颜色 */
    private String couponColour;

    /** 发布方式（1积分，2门店） */
    private String startType;
    
  
    public Integer getHasSendNum() {
        return hasSendNum;
    }

    public void setHasSendNum(Integer hasSendNum) {
        this.hasSendNum = hasSendNum;
    }

    public Integer getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(Integer couponNum) {
        this.couponNum = couponNum;
    }

    public String getCouponNames() {
        return couponNames;
    }

    public void setCouponNames(String couponNames) {
        this.couponNames = couponNames;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(Integer couponPrice) {
        this.couponPrice = couponPrice;
    }

    public Integer getCouponVantages() {
        return couponVantages;
    }

    public void setCouponVantages(Integer couponVantages) {
        this.couponVantages = couponVantages;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public String getCouponStartTime() {
        return couponStartTime;
    }

    public void setCouponStartTime(String couponStartTime) {
        this.couponStartTime = couponStartTime == null ? null
                : couponStartTime.trim();
    }

    public String getCouponStopTime() {
        return couponStopTime;
    }

    public void setCouponStopTime(String couponStopTime) {
        this.couponStopTime = couponStopTime == null ? null
                : couponStopTime.trim();
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime == null ? null : releaseTime.trim();
    }

    public Integer getCouponIsUse() {
        return couponIsUse;
    }

    public void setCouponIsUse(Integer couponIsUse) {
        this.couponIsUse = couponIsUse;
    }

    public String getStoreAccount() {
        return storeAccount;
    }

    public void setStoreAccount(String storeAccount) {
        this.storeAccount = storeAccount == null ? null : storeAccount.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsType() {
        return isType;
    }

    public void setIsType(Integer isType) {
        this.isType = isType;
    }

    public Integer getPriceSigle() {
        return priceSigle;
    }

    public void setPriceSigle(Integer priceSigle) {
        this.priceSigle = priceSigle;
    }

    public Integer getCouponNumber() {
        return couponNumber;
    }

    public void setCouponNumber(Integer couponNumber) {
        this.couponNumber = couponNumber;
    }

    public Integer getCouponMan() {
        return couponMan;
    }

    public void setCouponMan(Integer couponMan) {
        this.couponMan = couponMan;
    }

    public String getCouponColour() {
        return couponColour;
    }

    public void setCouponColour(String couponColour) {
        this.couponColour = couponColour == null ? null : couponColour.trim();
    }

    public String getStartType() {
        return startType;
    }

    public void setStartType(String startType) {
        this.startType = startType == null ? null : startType.trim();
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

}