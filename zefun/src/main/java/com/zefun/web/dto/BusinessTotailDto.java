package com.zefun.web.dto;

/**
* @author 老王
* @date 2016年2月17日 上午11:45:44 
*/
public class BusinessTotailDto {
	/** 创建时间*/
    private String createTime;
    /** 查询对应值*/
    private Double valueMoney;
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
