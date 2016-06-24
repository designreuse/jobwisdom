package com.zefun.web.entity;

/**
 * 
* @author 骆峰
* @date 2016年6月23日 下午3:03:58
 */
public class CouponStoreKey {
    /** 优惠卷标识 */
    private Integer couponId;

    /** 门店标识 */
    private Integer storeId;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}