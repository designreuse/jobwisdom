package com.zefun.web.dto;

/**
 * 工资单中的奖惩数据
* @author 张进军
* @date Jan 13, 2016 10:48:58 PM
 */
public class PayrollRewardDto {
	/** 员工标识 */
    private Integer employeeId;
    
	/** 考勤奖惩金额 */
    private Double attendanceReward;
    
    /** 行为奖惩金额 */
    private Double behaviourReward;
    
    /** 服务奖惩金额 */
    private Double serviceReward;

	public Double getAttendanceReward() {
		return attendanceReward;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public void setAttendanceReward(Double attendanceReward) {
		this.attendanceReward = attendanceReward;
	}

	public Double getBehaviourReward() {
		return behaviourReward;
	}

	public void setBehaviourReward(Double behaviourReward) {
		this.behaviourReward = behaviourReward;
	}

	public Double getServiceReward() {
		return serviceReward;
	}

	public void setServiceReward(Double serviceReward) {
		this.serviceReward = serviceReward;
	}
    
}
