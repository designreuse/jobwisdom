package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class MoneyFlow {
	/** 流水标识 */
	private Integer flowId;

	/** 账户标识 */
	private Integer accountId;

	/** 流水类型(1:支出,2:收入) */
	private Integer flowType;

	/** 流水金额 */
	private BigDecimal flowAmount;

	/** 当前余额 */
	private BigDecimal balanceAmount;
	
	/** 单号标识(资金变化的订单标识) */
	private Integer orderId;

	/** 业务类型(1：消费、2：充值、3：转账、4：开卡) */
	private Integer businessType;
	
	/** 流水会员等级标识*/
	private Integer levelId;

	/** 业务描述 */
	private String businessDesc;

	/** 流水时间 */
	private String flowTime;

	/** 是否删除(0:未删除,1:已删除) */
	private Integer isDeleted;

	/** 流水发生门店标识*/
	private Integer storeId;
	
	/** 最后操作人标识*/
	private Integer lastOperatorId;
	
	/** 查询条件, 1:year 2:month*/
	private Integer queryType;

	/** 会员等级标识 */
	private Integer levelId;
	
	
	
    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public Integer getQueryType() {
        return queryType;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    /** @param flowId	流水标识 */
	public void setFlowId(Integer flowId){
		this.flowId = flowId;
	}

	/** @return	流水标识 */
	public Integer getFlowId(){
		return flowId;
	}

	/** @param accountId	账户标识 */
	public void setAccountId(Integer accountId){
		this.accountId = accountId;
	}

	/** @return	账户标识 */
	public Integer getAccountId(){
		return accountId;
	}

	/** @param flowType	流水类型(1:支出,2:收入) */
	public void setFlowType(Integer flowType){
		this.flowType = flowType;
	}

	/** @return	流水类型(1:支出,2:收入) */
	public Integer getFlowType(){
		return flowType;
	}

	/** @param flowAmount	流水金额 */
	public void setFlowAmount(BigDecimal flowAmount){
		this.flowAmount = flowAmount;
	}

	/** @return	流水金额 */
	public BigDecimal getFlowAmount(){
		return flowAmount;
	}

	/** @param balanceAmount	当前余额 */
	public void setBalanceAmount(BigDecimal balanceAmount){
		this.balanceAmount = balanceAmount;
	}

	/** @return	当前余额 */
	public BigDecimal getBalanceAmount(){
		return balanceAmount;
	}

	/** @param orderId	单号标识(资金变化的订单标识) */
	public void setOrderId(Integer orderId){
		this.orderId = orderId;
	}

	/** @return	单号标识(资金变化的订单标识) */
	public Integer getOrderId(){
		return orderId;
	}

	/** @param businessType	业务类型 */
	public void setBusinessType(Integer businessType){
		this.businessType = businessType;
	}

	/** @return	业务类型 */
	public Integer getBusinessType(){
		return businessType;
	}

	/** @param businessDesc	业务描述 */
	public void setBusinessDesc(String businessDesc){
		this.businessDesc = businessDesc;
	}

	/** @return	业务描述 */
	public String getBusinessDesc(){
		return businessDesc;
	}

	/** @param flowTime	流水时间 */
	public void setFlowTime(String flowTime){
		this.flowTime = flowTime;
	}

	/** @return	流水时间 */
	public String getFlowTime(){
		return flowTime;
	}

	/** @param isDeleted	是否删除(0:未删除,1:已删除) */
	public void setIsDeleted(Integer isDeleted){
		this.isDeleted = isDeleted;
	}

	/** @return	是否删除(0:未删除,1:已删除) */
	public Integer getIsDeleted(){
		return isDeleted;
	}

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

	
}