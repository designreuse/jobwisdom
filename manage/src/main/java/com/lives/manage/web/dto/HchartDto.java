/**
 * 
 */
package com.lives.manage.web.dto;

import java.util.List;

/** 
 * @author 陈端斌
 * @date 2015年6月11日 上午11:36:27 
 *  关于图形统计相关字段
 */
public class HchartDto {

	private Integer count;
	
	private Integer[] data;
	
	private String monthTime;
	
	private String years;
	
	private double moneyCount;
	
	private String sysName;
	/** 关于统计金额相关参数*/
	private Integer count0;
	private double moneyCount0;
	private Integer count1;
	private double moneyCount1;
	private Integer count2;
	private double moneyCount2;
	private Integer count3;
	private double moneyCount3;

	
	private Integer total;
	private List<HchartDto> rows;
	
	
	public Integer getCount0() {
		return count0;
	}

	public void setCount0(Integer count0) {
		this.count0 = count0;
	}

	public double getMoneyCount0() {
		return moneyCount0;
	}

	public void setMoneyCount0(double moneyCount0) {
		this.moneyCount0 = moneyCount0;
	}



	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}


	public List<HchartDto> getRows() {
		return rows;
	}

	public void setRows(List<HchartDto> rows) {
		this.rows = rows;
	}

	public Integer getCount1() {
		return count1;
	}

	public void setCount1(Integer count1) {
		this.count1 = count1;
	}

	public double getMoneyCount1() {
		return moneyCount1;
	}

	public void setMoneyCount1(double moneyCount1) {
		this.moneyCount1 = moneyCount1;
	}

	public Integer getCount2() {
		return count2;
	}

	public void setCount2(Integer count2) {
		this.count2 = count2;
	}

	public double getMoneyCount2() {
		return moneyCount2;
	}

	public void setMoneyCount2(double moneyCount2) {
		this.moneyCount2 = moneyCount2;
	}

	public Integer getCount3() {
		return count3;
	}

	public void setCount3(Integer count3) {
		this.count3 = count3;
	}

	public double getMoneyCount3() {
		return moneyCount3;
	}

	public void setMoneyCount3(double moneyCount3) {
		this.moneyCount3 = moneyCount3;
	}

	public double getMoneyCount() {
		return moneyCount;
	}

	public void setMoneyCount(double moneyCount) {
		this.moneyCount = moneyCount;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer[] getData() {
		return data;
	}

	public void setData(Integer[] data) {
		this.data = data;
	}

	public String getMonthTime() {
		return monthTime;
	}

	public void setMonthTime(String monthTime) {
		this.monthTime = monthTime;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}


	
	
}
