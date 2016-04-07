package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 员工工资数据传输对象
* @author 张进军
* @date Jan 13, 2016 7:35:26 PM
 */
public class EmployeePayrollDto {
	/** 员工标识 */
    private Integer employeeId;

    /** 员工编码 */
    private Integer employeeCode;

    /** 姓名 */
    private String name;
    
    /** 职位名称 */
    private String levelName;

    /** 头像 */
    private String headImage;

    /** 手机号码 */
    private String phone;

    /** 基本工资 */
    private Integer baseSalaries;

    /** 岗位工资 */
    private Integer positionSalaries;
    
    /** 奖惩金额 */
    private PayrollRewardDto payrollReward;
    
    /** 业绩提成 */
    private PayrollCommissionDto payrollCommission;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(Integer employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getBaseSalaries() {
		return baseSalaries;
	}

	public void setBaseSalaries(Integer baseSalaries) {
		this.baseSalaries = baseSalaries;
	}

	public Integer getPositionSalaries() {
		return positionSalaries;
	}

	public void setPositionSalaries(Integer positionSalaries) {
		this.positionSalaries = positionSalaries;
	}

	public BigDecimal getReceivableAmount() {
		return payrollCommission.getCardCommission().add(payrollCommission.getComboCommission())
				.add(payrollCommission.getGoodsCommission())
				.add(payrollCommission.getProjectCommission())
				.add(new BigDecimal(getBaseSalaries()))
				.add(new BigDecimal(getPositionSalaries()));
	}

	public BigDecimal getRealAmount() {
		return getReceivableAmount().add(new BigDecimal(payrollReward.getAttendanceReward()))
				.add(new BigDecimal(payrollReward.getBehaviourReward()))
				.add(new BigDecimal(payrollReward.getServiceReward()));
	}

	public PayrollRewardDto getPayrollReward() {
		return payrollReward;
	}

	public void setPayrollReward(PayrollRewardDto payrollReward) {
		this.payrollReward = payrollReward;
	}

	public PayrollCommissionDto getPayrollCommission() {
		return payrollCommission;
	}

	public void setPayrollCommission(PayrollCommissionDto payrollCommission) {
		this.payrollCommission = payrollCommission;
	}

}
