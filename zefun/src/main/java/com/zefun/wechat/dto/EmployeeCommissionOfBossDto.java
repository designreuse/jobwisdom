package com.zefun.wechat.dto;

/**
 * 员工绩效dto
 * 
 * @author DavidLiang
 * @date 2016年1月22日 下午7:55:59
 */
public class EmployeeCommissionOfBossDto {

	/** 员工id */
	private int employeeId;

	/** 员工姓名 */
	private String employeeName;

	/** 现金业绩 */
	private double cash;

	/** 卡金业绩 */
	private double card;

	/** 员工提成 */
	private double employeeCommission;

	/** 客单数(客量) */
	private int orderNum;

	/** 无参构造器 */
	public EmployeeCommissionOfBossDto() {
		super();
	}

	/** 有参构造器 */
	public EmployeeCommissionOfBossDto(int employeeId, String employeeName, double cash, double card,
			  double employeeCommission, int orderNum) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.cash = cash;
		this.card = card;
		this.employeeCommission = employeeCommission;
		this.orderNum = orderNum;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getCard() {
		return card;
	}

	public void setCard(double card) {
		this.card = card;
	}

	public double getEmployeeCommission() {
		return employeeCommission;
	}

	public void setEmployeeCommission(double employeeCommission) {
		this.employeeCommission = employeeCommission;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

}
