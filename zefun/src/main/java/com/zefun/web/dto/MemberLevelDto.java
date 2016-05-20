package com.zefun.web.dto;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;

/**
 * @author 张进军
 * @date 2016年03月12日 PM 19:45:30
 */
public class MemberLevelDto {
	/** 等级标识 */
	private Integer levelId;

	/** 等级名称 */
	private String levelName;

	/** 等级说明 */
	private String levelNotice;

	/** 是否默认等级(0:否,1:是) */
	private Integer isDefault;
	
	/** 折扣标识*/
	private Integer discountId;

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


	
	
	public Integer getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}

	public Integer getProjectDiscount() {
		return projectDiscount;
	}

	public void setProjectDiscount(Integer projectDiscount) {
		this.projectDiscount = projectDiscount;
	}

	public Integer getGoodsDiscount() {
		return goodsDiscount;
	}

	public void setGoodsDiscount(Integer goodsDiscount) {
		this.goodsDiscount = goodsDiscount;
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

	public Integer getCashDiscountType() {
		return cashDiscountType;
	}

	public void setCashDiscountType(Integer cashDiscountType) {
		this.cashDiscountType = cashDiscountType;
	}

	public Integer getIntegralUnit() {
		return integralUnit;
	}

	public void setIntegralUnit(Integer integralUnit) {
		this.integralUnit = integralUnit;
	}

	public Integer getIntegralNumber() {
		return integralNumber;
	}

	public void setIntegralNumber(Integer integralNumber) {
		this.integralNumber = integralNumber;
	}

	public Integer getPerformanceDiscountPercent() {
		return performanceDiscountPercent;
	}

	public void setPerformanceDiscountPercent(Integer performanceDiscountPercent) {
		this.performanceDiscountPercent = performanceDiscountPercent;
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

	/** @param levelName	等级名称 */
	public void setLevelName(String levelName){
		this.levelName = levelName;
	}

	/** @return	等级名称 */
	public String getLevelName(){
		return levelName;
	}

	/** @param levelNotice	等级说明 */
	public void setLevelNotice(String levelNotice){
		this.levelNotice = levelNotice;
	}

	/** @return	等级说明 */
	public String getLevelNotice(){
		return levelNotice;
	}

	/** @param isDefault	是否默认等级(0:否,1:是) */
	public void setIsDefault(Integer isDefault){
		this.isDefault = isDefault;
	}

	/** @return	是否默认等级(0:否,1:是) */
	public Integer getIsDefault(){
		return isDefault;
	}
    
    /**
     * 以JSON数组的方式获取等级描述信息
    * @author 张进军
    * @date Jan 28, 2016 9:00:49 PM
    * @return   等级描述信息
     */
    public JSONArray getLevelNoticeList(){
        if (StringUtils.isNotBlank(getLevelNotice())) {
            return JSONArray.fromObject(getLevelNotice());
        }
        return null;
    }

}