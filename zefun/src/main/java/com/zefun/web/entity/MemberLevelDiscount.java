package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2016年03月12日 PM 19:45:30
 */
public class MemberLevelDiscount {
	
	/** 折扣标识*/
	private Integer discountId;
	/** 等级标识 */
	private Integer levelId;

	/** 门店标识 */
	private Integer storeId;

	/** 项目折扣 */
	private Integer projectDiscount;

	/** 商品折扣 */
	private Integer goodsDiscount;

	/** 售卡开卡金额 */
	private BigDecimal sellAmount;

	/** 最低充值金额 */
	private BigDecimal chargeMinMoney;

	/** 现金是否打折(0:不打折，1:打折) */
	private Integer cashDiscountType;

	/** 消费积分单位 */
	private Integer integralUnit;

	/** 单位积分数量 */
	private Integer integralNumber;

	/** 业绩折扣比例(0-100) */
	private Integer performanceDiscountPercent;

	/** 是否删除(0:未删除,1:已删除) */
	private Integer isDeleted;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;
	

	
	
	public Integer getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}

	/** @param levelId	等级标识 */
	public void setLevelId(Integer levelId){
		this.levelId = levelId;
	}

	/** @return	等级标识 */
	public Integer getLevelId(){
		return levelId;
	}

	/** @param storeId	门店标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店标识 */
	public Integer getStoreId(){
		return storeId;
	}

	/** @param projectDiscount	项目折扣 */
	public void setProjectDiscount(Integer projectDiscount){
		this.projectDiscount = projectDiscount;
	}

	/** @return	项目折扣 */
	public Integer getProjectDiscount(){
		return projectDiscount;
	}

	/** @param goodsDiscount	商品折扣 */
	public void setGoodsDiscount(Integer goodsDiscount){
		this.goodsDiscount = goodsDiscount;
	}

	/** @return	商品折扣 */
	public Integer getGoodsDiscount(){
		return goodsDiscount;
	}

	public BigDecimal getSellAmount() {
		return sellAmount;
	}

	public void setSellAmount(BigDecimal sellAmount) {
		this.sellAmount = sellAmount;
	}

	public BigDecimal getChargeMinMoney() {
		return chargeMinMoney;
	}

	public void setChargeMinMoney(BigDecimal chargeMinMoney) {
		this.chargeMinMoney = chargeMinMoney;
	}

	/** @param cashDiscountType	现金是否打折(0:不打折，1:打折) */
	public void setCashDiscountType(Integer cashDiscountType){
		this.cashDiscountType = cashDiscountType;
	}

	/** @return	现金是否打折(0:不打折，1:打折) */
	public Integer getCashDiscountType(){
		return cashDiscountType;
	}

	/** @param integralUnit	消费积分单位 */
	public void setIntegralUnit(Integer integralUnit){
		this.integralUnit = integralUnit;
	}

	/** @return	消费积分单位 */
	public Integer getIntegralUnit(){
		return integralUnit;
	}

	/** @param integralNumber	单位积分数量 */
	public void setIntegralNumber(Integer integralNumber){
		this.integralNumber = integralNumber;
	}

	/** @return	单位积分数量 */
	public Integer getIntegralNumber(){
		return integralNumber;
	}

	/** @param performanceDiscountPercent	业绩折扣比例(0-100) */
	public void setPerformanceDiscountPercent(Integer performanceDiscountPercent){
		this.performanceDiscountPercent = performanceDiscountPercent;
	}

	/** @return	业绩折扣比例(0-100) */
	public Integer getPerformanceDiscountPercent(){
		return performanceDiscountPercent;
	}

	/** @param isDeleted	是否删除(0:未删除,1:已删除) */
	public void setIsDeleted(Integer isDeleted){
		this.isDeleted = isDeleted;
	}

	/** @return	是否删除(0:未删除,1:已删除) */
	public Integer getIsDeleted(){
		return isDeleted;
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