package com.zefun.web.entity;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;

/**
 * @author 张进军
 * @date 2016年03月12日 PM 19:45:30
 */
public class MemberLevel {
	/** 等级标识 */
	private Integer levelId;

	/** 门店标识 */
	private Integer storeId;

	/** 等级名称 */
	private String levelName;

	/** 项目折扣 */
	private Integer projectDiscount;

	/** 商品折扣 */
	private Integer goodsDiscount;

	/** 售卡开卡金额 */
	private Integer sellAmount;

	/** 最低充值金额 */
	private Integer chargeMinMoney;

	/** 现金是否打折(0:不打折，1:打折) */
	private Integer cashDiscountType;

	/** 消费积分单位 */
	private Integer integralUnit;

	/** 单位积分数量 */
	private Integer integralNumber;

	/** 业绩折扣比例(0-100) */
	private Integer performanceDiscountPercent;

	/** 等级说明 */
	private String levelNotice;

	/** 是否默认等级(0:否,1:是) */
	private Integer isDefault;

	/** 是否删除(0:未删除,1:已删除) */
	private Integer isDeleted;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;
	
	/** 等级说明数组 */
    private String[] levelNoticeArr;

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

	/** @param sellAmount	售卡开卡金额 */
	public void setSellAmount(Integer sellAmount){
		this.sellAmount = sellAmount;
	}

	/** @return	售卡开卡金额 */
	public Integer getSellAmount(){
		return sellAmount;
	}

	/** @param chargeMinMoney	最低充值金额 */
	public void setChargeMinMoney(Integer chargeMinMoney){
		this.chargeMinMoney = chargeMinMoney;
	}

	/** @return	最低充值金额 */
	public Integer getChargeMinMoney(){
		return chargeMinMoney;
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

    public String[] getLevelNoticeArr() {
        return levelNoticeArr;
    }

    public void setLevelNoticeArr(String[] levelNoticeArr) {
        this.levelNoticeArr = levelNoticeArr;
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