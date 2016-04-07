package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2016年03月05日 PM 14:26:21
 */
public class UboxTransaction {
	/** 交易标识 */
	private String transactionId;

	/** 交易金额(单位：分) */
	private Integer transactionAmount;

	/** 交易积分 */
	private Integer transactionIntegral;

	/** 商品标识 */
	private Integer goodsId;

	/** 友宝商品标识 */
	private String uboxGoodsId;

	/** 会员标识 */
	private Integer memberId;

	/** 支付渠道(1、微信，2、支付宝) */
	private Integer payChannel;

	/** 服务员工标识 */
	private Integer serviceEmployeeId;

	/** 奖励优惠券标识 */
	private Integer rewardsCouponId;

	/** 奖励礼金金额 */
	private Integer rewardsGiftAmount;

	/** 支付交易标识 */
	private String outTradeNo;

	/** 友宝交易标识 */
	private String tranId;

	/** 售货机标识 */
	private String vmid;

	/** 盒子编号 */
	private String boxNumber;

	/** 取货码 */
	private String pickupCode;

	/** 取货过期时间 */
	private Integer expireTime;

	/** 取货状态(1、待取货，2、已取货) */
	private Integer boxStatus;

	/** 支付状态(1、支付中，2、已支付，3、已取消) */
	private Integer payStatus;

	/** 是否修改过服务人员 */
	private Integer updatedServerStatus;

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

	/** @param transactionIntegral	交易积分 */
	public void setTransactionIntegral(Integer transactionIntegral){
		this.transactionIntegral = transactionIntegral;
	}

	/** @return	交易积分 */
	public Integer getTransactionIntegral(){
		return transactionIntegral;
	}

	/** @param goodsId	商品标识 */
	public void setGoodsId(Integer goodsId){
		this.goodsId = goodsId;
	}

	/** @return	商品标识 */
	public Integer getGoodsId(){
		return goodsId;
	}

	/** @param uboxGoodsId	友宝商品标识 */
	public void setUboxGoodsId(String uboxGoodsId){
		this.uboxGoodsId = uboxGoodsId;
	}

	/** @return	友宝商品标识 */
	public String getUboxGoodsId(){
		return uboxGoodsId;
	}

	/** @param memberId	会员标识 */
	public void setMemberId(Integer memberId){
		this.memberId = memberId;
	}

	/** @return	会员标识 */
	public Integer getMemberId(){
		return memberId;
	}

	/** @param payChannel	支付渠道(1、微信，2、支付宝) */
	public void setPayChannel(Integer payChannel){
		this.payChannel = payChannel;
	}

	/** @return	支付渠道(1、微信，2、支付宝) */
	public Integer getPayChannel(){
		return payChannel;
	}

	/** @param serviceEmployeeId	服务员工标识 */
	public void setServiceEmployeeId(Integer serviceEmployeeId){
		this.serviceEmployeeId = serviceEmployeeId;
	}

	/** @return	服务员工标识 */
	public Integer getServiceEmployeeId(){
		return serviceEmployeeId;
	}

	/** @param rewardsCouponId	奖励优惠券标识 */
	public void setRewardsCouponId(Integer rewardsCouponId){
		this.rewardsCouponId = rewardsCouponId;
	}

	/** @return	奖励优惠券标识 */
	public Integer getRewardsCouponId(){
		return rewardsCouponId;
	}

	/** @param rewardsGiftAmount	奖励礼金金额 */
	public void setRewardsGiftAmount(Integer rewardsGiftAmount){
		this.rewardsGiftAmount = rewardsGiftAmount;
	}

	/** @return	奖励礼金金额 */
	public Integer getRewardsGiftAmount(){
		return rewardsGiftAmount;
	}

	/** @param outTradeNo	支付交易标识 */
	public void setOutTradeNo(String outTradeNo){
		this.outTradeNo = outTradeNo;
	}

	/** @return	支付交易标识 */
	public String getOutTradeNo(){
		return outTradeNo;
	}

	/** @param tranId	友宝交易标识 */
	public void setTranId(String tranId){
		this.tranId = tranId;
	}

	/** @return	友宝交易标识 */
	public String getTranId(){
		return tranId;
	}

	/** @param vmid	售货机标识 */
	public void setVmid(String vmid){
		this.vmid = vmid;
	}

	/** @return	售货机标识 */
	public String getVmid(){
		return vmid;
	}

	/** @param boxNumber	盒子编号 */
	public void setBoxNumber(String boxNumber){
		this.boxNumber = boxNumber;
	}

	/** @return	盒子编号 */
	public String getBoxNumber(){
		return boxNumber;
	}

	/** @param pickupCode	取货码 */
	public void setPickupCode(String pickupCode){
		this.pickupCode = pickupCode;
	}

	/** @return	取货码 */
	public String getPickupCode(){
		return pickupCode;
	}

	/** @param expireTime	取货过期时间 */
	public void setExpireTime(Integer expireTime){
		this.expireTime = expireTime;
	}

	/** @return	取货过期时间 */
	public Integer getExpireTime(){
		return expireTime;
	}

	/** @param boxStatus	取货状态(1、待取货，2、已取货) */
	public void setBoxStatus(Integer boxStatus){
		this.boxStatus = boxStatus;
	}

	/** @return	取货状态(1、待取货，2、已取货) */
	public Integer getBoxStatus(){
		return boxStatus;
	}

	/** @param payStatus	支付状态(1、支付中，2、已支付，3、已取消) */
	public void setPayStatus(Integer payStatus){
		this.payStatus = payStatus;
	}

	/** @return	支付状态(1、支付中，2、已支付，3、已取消) */
	public Integer getPayStatus(){
		return payStatus;
	}

	/** @param updatedServerStatus	是否修改过服务人员 */
	public void setUpdatedServerStatus(Integer updatedServerStatus){
		this.updatedServerStatus = updatedServerStatus;
	}

	/** @return	是否修改过服务人员 */
	public Integer getUpdatedServerStatus(){
		return updatedServerStatus;
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