package com.zefun.web.dto.ubox;

import com.zefun.web.entity.CouponInfo;

/**
 * 友宝门店商品信息传输对象
* @author 张进军
* @date Jan 30, 2016 5:59:58 PM
 */
public class UboxStoreGoodsDto {
    /** 主键标识 */
    private Integer storeGoodsId;
    
    /** 门店标识 */
    private Integer storeId;

    /** 友宝商品标识 */
    private String uboxGoodsId;

    /** 门店商品销售价格 */
    private Integer storeGoodsPrice;

    /** 门店商品销售所需积分 */
    private Integer storeGoodsIntegral;

    /** 门店商品库存 */
    private Integer storeGoodsStock;

    /** 门店商品销量 */
    private Integer storeGoodsSales;

    /** 购买奖励优惠券标识 */
    private Integer rewardsCouponId;
    
    /** 商品信息 */
    private UboxGoodsInfoDto goodsInfo;
    
    /** 奖励的优惠券信息 */ 
    private CouponInfo rewardsCoupon;
    
    /** 购买奖励礼金金额 */
    private Integer rewardsGiftAmount;

    public Integer getStoreGoodsId() {
        return storeGoodsId;
    }

    public void setStoreGoodsId(Integer storeGoodsId) {
        this.storeGoodsId = storeGoodsId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getUboxGoodsId() {
        return uboxGoodsId;
    }

    public void setUboxGoodsId(String uboxGoodsId) {
        this.uboxGoodsId = uboxGoodsId;
    }

    public Integer getStoreGoodsPrice() {
        return storeGoodsPrice;
    }

    public void setStoreGoodsPrice(Integer storeGoodsPrice) {
        this.storeGoodsPrice = storeGoodsPrice;
    }

    public Integer getStoreGoodsIntegral() {
        return storeGoodsIntegral;
    }

    public void setStoreGoodsIntegral(Integer storeGoodsIntegral) {
        this.storeGoodsIntegral = storeGoodsIntegral;
    }

    public Integer getStoreGoodsStock() {
        return storeGoodsStock;
    }

    public void setStoreGoodsStock(Integer storeGoodsStock) {
        this.storeGoodsStock = storeGoodsStock;
    }

    public Integer getStoreGoodsSales() {
        return storeGoodsSales;
    }

    public void setStoreGoodsSales(Integer storeGoodsSales) {
        this.storeGoodsSales = storeGoodsSales;
    }

    public Integer getRewardsCouponId() {
        return rewardsCouponId;
    }

    public void setRewardsCouponId(Integer rewardsCouponId) {
        this.rewardsCouponId = rewardsCouponId;
    }

    public UboxGoodsInfoDto getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(UboxGoodsInfoDto goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public CouponInfo getRewardsCoupon() {
        return rewardsCoupon;
    }

    public void setRewardsCoupon(CouponInfo rewardsCoupon) {
        this.rewardsCoupon = rewardsCoupon;
    }

    public Integer getRewardsGiftAmount() {
        return rewardsGiftAmount;
    }

    public void setRewardsGiftAmount(Integer rewardsGiftAmount) {
        this.rewardsGiftAmount = rewardsGiftAmount;
    }
    
}
