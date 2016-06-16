package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 商品信息表
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class GoodsInfo {
	/** 商品标识 */
	private Integer goodsId;

	/** 门店标识 */
	private Integer storeId;

	/** 类别标识 */
	private Integer categoryId;

//	/** 品牌标识 */
//	private String brandId;
//	
//	/** 供应商标识 */
//	private Integer supplierId;

	/** 部门标识*/
    private Integer deptId;
	
//	/** 商品名称 */
//	private String goodsName;

	/** 商品价格 */
	private BigDecimal goodsPrice;

//	/** 成本价格 */
//	private BigDecimal costPrice;
	
	/** 员工业绩提成方式1:比例2:固定*/
	private Integer calculationType;
	
	/** 员工销售业绩值 */
	private BigDecimal onlineShoppingPrice;

//	/** 提成是否包含成本(0:否,1:是) */
//	private Integer isIncludeCost;

	/** 商品图片 */
	private String goodsImage;

	/** 商品描述 */
	private String goodsDesc;

//	/** 商品库存 */
//	private Integer goodsStock;

	/** 告警库存 */
	private Integer warnStock;

	/** 提成方式(1:按业绩比例,2:按固定金额) */
	private Integer commissionType;

	/** 提成金额 */
	private Integer commissionAmount;
	
	/** 刷卡提成方式(1:按业绩比例,2:按固定金额) */
    private Integer commissionCardType;

    /** 刷卡提成金额 */
    private Integer cardAmount;
    
    /** 积分兑换 */
    private Integer integralExchange;

	/** 销售次数 */
	private Integer salesCount;

//	/** 销售人数 */
//	private Integer salesPeople;

//	/** 是否微信销售(0:否,1:是) */
//	private Integer isWechatSell;
//
//	/** 是否禁用(0:未禁用,1:已禁用) */
//	private Integer isDisable;

	/** 是否删除(0:未删除,1:已删除) */
	private Integer isDeleted;
	
//	/** 是否卖品(0:否,1:是) */
//	private Integer isSellProduct;
	
	/** 附属图片 */
	private String affiliatedImage;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;
	
	/**是否礼金抵扣*/
	private Integer isCashDeduction;
	
	/**最大礼金抵扣*/
	private BigDecimal highestDiscount; 
	
	/**商品步骤*/
	private Integer projectStep;
//	/**商品编号*/
//	private String goodsCodeSuffix;
	
	/**企业商品ID*/
	private Integer aId;

	/**
	 * 获得ID
	* @author 高国藩
	* @date 2016年5月31日 上午9:42:41
	* @return aId
	 */
    public Integer getaId() {
        return aId;
    }

    /**
     * 设置ID
    * @author 高国藩
    * @date 2016年5月31日 上午9:43:34
    * @param aId aId
     */
    public void setaId(Integer aId) {
        this.aId = aId;
    }
    
    public BigDecimal getOnlineShoppingPrice() {
        return onlineShoppingPrice;
    }

    public void setOnlineShoppingPrice(BigDecimal onlineShoppingPrice) {
        this.onlineShoppingPrice = onlineShoppingPrice;
    }

    public Integer getProjectStep() {
        return projectStep;
    }

    public void setProjectStep(Integer projectStep) {
        this.projectStep = projectStep;
    }

    public Integer getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(Integer calculationType) {
        this.calculationType = calculationType;
    }

    public BigDecimal getHighestDiscount() {
        return highestDiscount;
    }

    public void setHighestDiscount(BigDecimal highestDiscount) {
        this.highestDiscount = highestDiscount;
    }

    public Integer getIsCashDeduction() {
        return isCashDeduction;
    }

    public void setIsCashDeduction(Integer isCashDeduction) {
        this.isCashDeduction = isCashDeduction;
    }

    /** @param goodsId	商品标识 */
	public void setGoodsId(Integer goodsId){
		this.goodsId = goodsId;
	}

	/** @return	商品标识 */
	public Integer getGoodsId(){
		return goodsId;
	}

	/** @param storeId	门店标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店标识 */
	public Integer getStoreId(){
		return storeId;
	}

	/** @param categoryId	类别标识 */
	public void setCategoryId(Integer categoryId){
		this.categoryId = categoryId;
	}

	/** @return	类别标识 */
	public Integer getCategoryId(){
		return categoryId;
	}
	
    /**@return 部门标识*/
    public Integer getDeptId() {
        return deptId;
    }

    /**@param deptId 部门标识*/
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

	/** @param goodsPrice	商品价格 */
	public void setGoodsPrice(BigDecimal goodsPrice){
		this.goodsPrice = goodsPrice;
	}

	/** @return	商品价格 */
	public BigDecimal getGoodsPrice(){
		return goodsPrice;
	}

	/** @param goodsImage	商品图片 */
	public void setGoodsImage(String goodsImage){
		this.goodsImage = goodsImage;
	}

	/** @return	商品图片 */
	public String getGoodsImage(){
		return goodsImage;
	}

	/** @param goodsDesc	商品描述 */
	public void setGoodsDesc(String goodsDesc){
		this.goodsDesc = goodsDesc;
	}

	/** @return	商品描述 */
	public String getGoodsDesc(){
		return goodsDesc;
	}

	/** @param warnStock	告警库存 */
	public void setWarnStock(Integer warnStock){
		this.warnStock = warnStock;
	}

	/** @return	告警库存 */
	public Integer getWarnStock(){
		return warnStock;
	}

	/** @param commissionType	提成方式(1:按业绩比例,2:按固定金额) */
	public void setCommissionType(Integer commissionType){
		this.commissionType = commissionType;
	}

	/** @return	提成方式(1:按业绩比例,2:按固定金额) */
	public Integer getCommissionType(){
		return commissionType;
	}
	
	/** @return 刷卡提成方式(1:按业绩比例,2:按固定金额) */
	public Integer getCommissionCardType() {
        return commissionCardType;
    }

	/** @param commissionCardType 刷卡提成方式(1:按业绩比例,2:按固定金额) */
    public void setCommissionCardType(Integer commissionCardType) {
        this.commissionCardType = commissionCardType;
    }

    /** @return 积分兑换 */
    public Integer getIntegralExchange() {
        return integralExchange;
    }

    /** @param integralExchange 积分兑换 */
    public void setIntegralExchange(Integer integralExchange) {
        this.integralExchange = integralExchange;
    }

    /** @param salesCount	销售次数 */
	public void setSalesCount(Integer salesCount){
		this.salesCount = salesCount;
	}

	/** @return	销售次数 */
	public Integer getSalesCount(){
		return salesCount;
	}

	/** @param isDeleted 是否删除(0:未删除,1:已删除) */
	public void setIsDeleted(Integer isDeleted){
		this.isDeleted = isDeleted;
	}

	/** @return	是否删除(0:未删除,1:已删除) */
	public Integer getIsDeleted(){
		return isDeleted;
	}
	
    /** @return 附属图片 */
    public String getAffiliatedImage() {
        return affiliatedImage;
    }

    /** @param affiliatedImage 附属图片 */
    public void setAffiliatedImage(String affiliatedImage) {
        this.affiliatedImage = affiliatedImage;
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

    public Integer getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(Integer commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public Integer getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(Integer cardAmount) {
        this.cardAmount = cardAmount;
    }
	
}