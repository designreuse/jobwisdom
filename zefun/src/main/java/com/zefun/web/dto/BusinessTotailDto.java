package com.zefun.web.dto;

/**
* @author 老王
* @date 2016年2月17日 上午11:45:44 
*/
public class BusinessTotailDto {
	/** 创建时间/明细类型*/
    private String createTime;
    /** 查询对应值*/
    private Double valueMoney;
    /** 是否指定(0：否，1：是)*/
    private Integer isAssign;
    /** 订单类型*/
    private Integer orderType;
    /** 会员标识*/
    private Integer memberId;
    /** 性别*/
    private String sex;
    
    
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Integer getIsAssign() {
		return isAssign;
	}
	public void setIsAssign(Integer isAssign) {
		this.isAssign = isAssign;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Double getValueMoney() {
		return valueMoney;
	}
	public void setValueMoney(Double valueMoney) {
		this.valueMoney = valueMoney;
	}
    
    
}
