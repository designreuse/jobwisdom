package com.zefun.web.entity;

import java.math.BigDecimal;

/**
  *@author Administrator
  *@date 2016年1月13日
  *@description 门店当日汇总 包括当日收入 当日客单数 当日平均客单价
  */
public class DeptCurrentSummary {
	
	/**单日服务顾客数量*/
	private Integer customerNum;
	
	/**当日总收入*/
	private BigDecimal totalIncome;

	public Integer getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(Integer customerNum) {
		this.customerNum = customerNum;
	}

	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}
	
}
