package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2016年01月28日 PM 17:49:37
 */
public class UboxStoreGoods {
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
	
	/** 购买奖励礼金金额 */
	private Integer rewardsGiftAmount;

	/** @param storeGoodsId	主键标识 */
	public void setStoreGoodsId(Integer storeGoodsId){
		this.storeGoodsId = storeGoodsId;
	}

	/** @return	主键标识 */
	public Integer getStoreGoodsId(){
		return storeGoodsId;
	}

	/** @return    门店标识 */
	public Integer getStoreId() {
        return storeId;
    }

	/** @param storeId  门店标识 */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /** @param uboxGoodsId	友宝商品标识 */
	public void setUboxGoodsId(String uboxGoodsId){
		this.uboxGoodsId = uboxGoodsId;
	}

	/** @return	友宝商品标识 */
	public String getUboxGoodsId(){
		return uboxGoodsId;
	}

	/** @param storeGoodsPrice	门店商品销售价格 */
	public void setStoreGoodsPrice(Integer storeGoodsPrice){
		this.storeGoodsPrice = storeGoodsPrice;
	}

	/** @return	门店商品销售价格 */
	public Integer getStoreGoodsPrice(){
		return storeGoodsPrice;
	}

	/** @param storeGoodsIntegral	门店商品销售所需积分 */
	public void setStoreGoodsIntegral(Integer storeGoodsIntegral){
		this.storeGoodsIntegral = storeGoodsIntegral;
	}

	/** @return	门店商品销售所需积分 */
	public Integer getStoreGoodsIntegral(){
		return storeGoodsIntegral;
	}

	/** @param storeGoodsStock	门店商品库存 */
	public void setStoreGoodsStock(Integer storeGoodsStock){
		this.storeGoodsStock = storeGoodsStock;
	}

	/** @return	门店商品库存 */
	public Integer getStoreGoodsStock(){
		return storeGoodsStock;
	}

	/** @param storeGoodsSales	门店商品销量 */
	public void setStoreGoodsSales(Integer storeGoodsSales){
		this.storeGoodsSales = storeGoodsSales;
	}

	/** @return	门店商品销量 */
	public Integer getStoreGoodsSales(){
		return storeGoodsSales;
	}

	/** @param rewardsCouponId	购买奖励优惠券标识 */
	public void setRewardsCouponId(Integer rewardsCouponId){
		this.rewardsCouponId = rewardsCouponId;
	}

	/** @return	购买奖励优惠券标识 */
	public Integer getRewardsCouponId(){
		return rewardsCouponId;
	}

	/**@return 购买奖励礼金金额  */
    public Integer getRewardsGiftAmount() {
        return rewardsGiftAmount;
    }

    /** @param rewardsGiftAmount   购买奖励礼金金额 */
    public void setRewardsGiftAmount(Integer rewardsGiftAmount) {
        this.rewardsGiftAmount = rewardsGiftAmount;
    }
	
}