package com.lives.manage.web.dto;

import java.util.List;
import java.util.Map;

public class SystemStatisticsDto {

	private String systemType;
	private Integer count;
	private Double moneycount;
	
	private List<Map<String,Object>> statisticsList;
	
	public Double getMoneycount() {
		return moneycount;
	}
	public void setMoneycount(Double moneycount) {
		this.moneycount = moneycount;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getSystemType() {
		return systemType;
	}
	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
	public List<Map<String,Object>> getStatisticsList() {
		return statisticsList;
	}
	public void setStatisticsList(List<Map<String,Object>> statisticsList) {
		this.statisticsList = statisticsList;
	}

}
