package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2016年01月28日 PM 17:49:37
 */
public class UboxGoodsInfo {
	/** 友宝商品标识 */
	private String uboxId;

	/** 友宝商品名称 */
	private String uboxName;

	/** 友宝商品原价 */
	private Integer uboxOriginalPrice;

	/** 友宝商品售价 */
	private Integer uboxSalePrice;

	/** 友宝商品库存 */
	private Integer uboxStock;

	/** 友宝商品图片 */
	private String uboxPicture;

	/** 友宝商品描述 */
	private String uboxDesc;

	/** 友宝售卖截止时间，unix时间戳，商品不可售卖、不再可取 */
	private Integer uboxExpireTime;

	/** 商品名称 */
	private String goodsName;

	/** 商品图片集合，逗号分割 */
	private String goodsPictureArray;

	/** 商品描述 */
	private String goodsDesc;

	/** 商品销量 */
	private Integer goodsSales;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;

	/** @param uboxId	友宝商品标识 */
	public void setUboxId(String uboxId){
		this.uboxId = uboxId;
	}

	/** @return	友宝商品标识 */
	public String getUboxId(){
		return uboxId;
	}

	/** @param uboxName	友宝商品名称 */
	public void setUboxName(String uboxName){
		this.uboxName = uboxName;
	}

	/** @return	友宝商品名称 */
	public String getUboxName(){
		return uboxName;
	}

	/** @param uboxOriginalPrice	友宝商品原价 */
	public void setUboxOriginalPrice(Integer uboxOriginalPrice){
		this.uboxOriginalPrice = uboxOriginalPrice;
	}

	/** @return	友宝商品原价 */
	public Integer getUboxOriginalPrice(){
		return uboxOriginalPrice;
	}

	/** @param uboxSalePrice	友宝商品售价 */
	public void setUboxSalePrice(Integer uboxSalePrice){
		this.uboxSalePrice = uboxSalePrice;
	}

	/** @return	友宝商品售价 */
	public Integer getUboxSalePrice(){
		return uboxSalePrice;
	}

	/** @param uboxStock	友宝商品库存 */
	public void setUboxStock(Integer uboxStock){
		this.uboxStock = uboxStock;
	}

	/** @return	友宝商品库存 */
	public Integer getUboxStock(){
		return uboxStock;
	}

	/** @param uboxPicture	友宝商品图片 */
	public void setUboxPicture(String uboxPicture){
		this.uboxPicture = uboxPicture;
	}

	/** @return	友宝商品图片 */
	public String getUboxPicture(){
		return uboxPicture;
	}

	/** @param uboxDesc	友宝商品描述 */
	public void setUboxDesc(String uboxDesc){
		this.uboxDesc = uboxDesc;
	}

	/** @return	友宝商品描述 */
	public String getUboxDesc(){
		return uboxDesc;
	}

	/** @param uboxExpireTime	友宝售卖截止时间，unix时间戳，商品不可售卖、不再可取 */
	public void setUboxExpireTime(Integer uboxExpireTime){
		this.uboxExpireTime = uboxExpireTime;
	}

	/** @return	友宝售卖截止时间，unix时间戳，商品不可售卖、不再可取 */
	public Integer getUboxExpireTime(){
		return uboxExpireTime;
	}

	/** @param goodsName	商品名称 */
	public void setGoodsName(String goodsName){
		this.goodsName = goodsName;
	}

	/** @return	商品名称 */
	public String getGoodsName(){
		return goodsName;
	}

	/** @param goodsPictureArray	商品图片集合，逗号分割 */
	public void setGoodsPictureArray(String goodsPictureArray){
		this.goodsPictureArray = goodsPictureArray;
	}

	/** @return	商品图片集合，逗号分割 */
	public String getGoodsPictureArray(){
		return goodsPictureArray;
	}

	/** @param goodsDesc	商品描述 */
	public void setGoodsDesc(String goodsDesc){
		this.goodsDesc = goodsDesc;
	}

	/** @return	商品描述 */
	public String getGoodsDesc(){
		return goodsDesc;
	}

	/** @param goodsSales	商品销量 */
	public void setGoodsSales(Integer goodsSales){
		this.goodsSales = goodsSales;
	}

	/** @return	商品销量 */
	public Integer getGoodsSales(){
		return goodsSales;
	}

	/** @param createTime	创建时间 */
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	/** @return	创建时间 */
	public String getCreateTime(){
		return createTime;
	}

	/** @param updateTime	修改时间 */
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	/** @return	修改时间 */
	public String getUpdateTime(){
		return updateTime;
	}

	/** @param lastOperatorId	最后操作人标识 */
	public void setLastOperatorId(Integer lastOperatorId){
		this.lastOperatorId = lastOperatorId;
	}

	/** @return	最后操作人标识 */
	public Integer getLastOperatorId(){
		return lastOperatorId;
	}

}