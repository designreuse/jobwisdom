package com.zefun.api.dto;

public class EmployeeShiftAndShiftInfoDto {

	/** 员工标识 */
	private Integer employeeId;

	/** 门店标识 */
	private Integer storeId;

	/** 开始时间 */
	private String startTime;

	/** 结束时间 */
	private String endTime;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
