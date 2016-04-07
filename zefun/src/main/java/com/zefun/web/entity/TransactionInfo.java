package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2016年03月04日 AM 11:59:44
 */
public class TransactionInfo {
	/** 交易标识 */
	private String transactionId;

	/** 交易金额(单位：分) */
	private Integer transactionAmount;

	/** 商品类型(1、门店开通，2、门店续费，3、短息购买，4、商品购买，5、参加会议) */
	private Integer goodsType;

	/** 商品标识 */
	private Integer goodsId;

	/** 商品名称 */
	private String goodsName;

	/** 门店标识 */
	private Integer storeId;

	/** 用户标识 */
	private String openId;

	/** 支付渠道(1、微信，2、支付宝) */
	private Integer payChannel;

	/** 支付交易标识 */
	private String outTradeNo;

	/** 支付状态(1、支付中，2、已支付，3、已取消) */
	private Integer payStatus;

	/** 订单创建时间 */
	private String createTime;

	/** 订单修改时间 */
	private String updateTime;

	/** @param transactionId	交易标识 */
	public void setTransactionId(String transactionId){
		this.transactionId = transactionId;
	}

	/** @return	交易标识 */
	public String getTransactionId(){
		return transactionId;
	}

	/** @param transactionAmount	交易金额(单位：分) */
	public void setTransactionAmount(Integer transactionAmount){
		this.transactionAmount = transactionAmount;
	}

	/** @return	交易金额(单位：分) */
	public Integer getTransactionAmount(){
		return transactionAmount;
	}

	/** @param goodsType	商品类型(1、门店开通，2、门店续费，3、短息购买，4、商品购买，5、参加会议) */
	public void setGoodsType(Integer goodsType){
		this.goodsType = goodsType;
	}

	/** @return	商品类型(1、门店开通，2、门店续费，3、短息购买，4、商品购买，5、参加会议) */
	public Integer getGoodsType(){
		return goodsType;
	}

	/** @param goodsId	商品标识 */
	public void setGoodsId(Integer goodsId){
		this.goodsId = goodsId;
	}

	/** @return	商品标识 */
	public Integer getGoodsId(){
		return goodsId;
	}

	/** @param goodsName	商品名称 */
	public void setGoodsName(String goodsName){
		this.goodsName = goodsName;
	}

	/** @return	商品名称 */
	public String getGoodsName(){
		return goodsName;
	}

	/** @param storeId	门店标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店标识 */
	public Integer getStoreId(){
		return storeId;
	}

	/** @param openId	用户标识 */
	public void setOpenId(String openId){
		this.openId = openId;
	}

	/** @return	用户标识 */
	public String getOpenId(){
		return openId;
	}

	/** @param payChannel	支付渠道(1、微信，2、支付宝) */
	public void setPayChannel(Integer payChannel){
		this.payChannel = payChannel;
	}

	/** @return	支付渠道(1、微信，2、支付宝) */
	public Integer getPayChannel(){
		return payChannel;
	}

	/** @param outTradeNo	支付交易标识 */
	public void setOutTradeNo(String outTradeNo){
		this.outTradeNo = outTradeNo;
	}

	/** @return	支付交易标识 */
	public String getOutTradeNo(){
		return outTradeNo;
	}

	/** @param payStatus	支付状态(1、支付中，2、已支付，3、已取消) */
	public void setPayStatus(Integer payStatus){
		this.payStatus = payStatus;
	}

	/** @return	支付状态(1、支付中，2、已支付，3、已取消) */
	public Integer getPayStatus(){
		return payStatus;
	}

	/** @param createTime	订单创建时间 */
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	/** @return	订单创建时间 */
	public String getCreateTime(){
		return createTime;
	}

	/** @param updateTime	订单修改时间 */
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	/** @return	订单修改时间 */
	public String getUpdateTime(){
		return updateTime;
	}

}