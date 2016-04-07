package com.zefun.wechat.dto;

import java.math.BigDecimal;

/**
 * 员工业绩详情dto
 * 
 * @author DavidLiang
 * @date 2016年1月27日 下午3:56:08
 */
public class EmployeeCommissionDetailOfBossDto {
	/**
	 * 业绩分布
	 */
	/** 现金业绩总和 */
	private double cashCommissionSum;
	/** 项目现金业绩(order_detail.order_type = 1) */
	private double cashCommissionOfProject;
	/** 商品现金业绩(order_detail.order_type = 2) */
	private double cashCommissionOfGoods;
	/** 套餐现金业绩(order_detail.order_type = 3) */
	private double cashCommissionOfPackage;
	/** 卡项现金业绩(order_detail.order_type = 4,5,6) */
	private double cashCommissionOfCard;

	/** 卡金业绩总和 */
	private double cardCommissionSum;
	/** 项目卡金业绩(order_detail.order_type = 1) */
	private double cardCommissionOfProject;
	/** 商品卡金业绩(order_detail.order_type = 2) */
	private double cardCommissionOfGoods;
	/** 套餐卡金业绩(order_detail.order_type = 3) */
	private double cardCommissionOfPackage;
	/** 卡项卡金业绩(order_detail.order_type = 4,5,6) */
	private double cardCommissionOfCard;

	/**
	 * 技能分析
	 */
	/** 大项客户量 */
	// private long customerCountOfBigProject;
	private int customerCountOfBigProject;
	/** 小项客户量 */
	// private long customerCountOfSmallProject;
	private int customerCountOfSmallProject;
	/** 预约客户量 */
	// private long customerCountOfIsAppoint;
	private int customerCountOfIsAppoint;
	/** 非预约客户量 */
	// private long customerCountOfNotAppoint;
	private int customerCountOfNotAppoint;
	/** 指定客户量 */
	// private long customerCountOfIsAssign;
	private int customerCountOfIsAssign;
	/** 非指定客户量 */
	// private long customerCountOfNotAssign;
	private int customerCountOfNotAssign;

	/**
	 * 提成来源
	 */
	/** 项目提成 */
	private BigDecimal employeeCommissionOfProject;
	/** 商品提成 */
	private BigDecimal employeeCommissionOfGoods;
	/** 套餐提成 */
	private BigDecimal employeeCommissionOfPackage;
	/** 开卡充值提成 */
	private BigDecimal employeeCommissionOfCard;

	/**
	 * 员工评价
	 */
	/** 5分次数 */
	private long employeeEvaluateFive;
	/** 4分次数 */
	private long employeeEvaluateFour;
	/** 3分次数 */
	private long employeeEvaluateThree;
	/** 2分次数 */
	private long employeeEvaluateTwo;
	/** 1分次数 */
	private long employeeEvaluateOne;
	/** 没评分次数 */
	private long employeeEvaluateNull;

	/**
	 * 工作态度
	 */
	/** 迟到次数 */
	private long rewardCountOfLate;
	/** 请假次数 */
	private long rewardCountOfHoliday;
	/** 旷工次数 */
	private long rewardCountOfAbsenteeism;
	/** 大过次数 */
	private long rewardCountOfSeriousMistake;
	/** 小过次数 */
	private long rewardCountOfSmallMistake;
	/** 投诉次数 */
	private long rewardCountOfComplaint;

	public double getCashCommissionSum() {
		return cashCommissionSum;
	}

	public void setCashCommissionSum(double cashCommissionSum) {
		this.cashCommissionSum = cashCommissionSum;
	}

	public double getCashCommissionOfProject() {
		return cashCommissionOfProject;
	}

	public void setCashCommissionOfProject(double cashCommissionOfProject) {
		this.cashCommissionOfProject = cashCommissionOfProject;
	}

	public double getCashCommissionOfGoods() {
		return cashCommissionOfGoods;
	}

	public void setCashCommissionOfGoods(double cashCommissionOfGoods) {
		this.cashCommissionOfGoods = cashCommissionOfGoods;
	}

	public double getCashCommissionOfPackage() {
		return cashCommissionOfPackage;
	}

	public void setCashCommissionOfPackage(double cashCommissionOfPackage) {
		this.cashCommissionOfPackage = cashCommissionOfPackage;
	}

	public double getCashCommissionOfCard() {
		return cashCommissionOfCard;
	}

	public void setCashCommissionOfCard(double cashCommissionOfCard) {
		this.cashCommissionOfCard = cashCommissionOfCard;
	}

	public double getCardCommissionSum() {
		return cardCommissionSum;
	}

	public void setCardCommissionSum(double cardCommissionSum) {
		this.cardCommissionSum = cardCommissionSum;
	}

	public double getCardCommissionOfProject() {
		return cardCommissionOfProject;
	}

	public void setCardCommissionOfProject(double cardCommissionOfProject) {
		this.cardCommissionOfProject = cardCommissionOfProject;
	}

	public double getCardCommissionOfGoods() {
		return cardCommissionOfGoods;
	}

	public void setCardCommissionOfGoods(double cardCommissionOfGoods) {
		this.cardCommissionOfGoods = cardCommissionOfGoods;
	}

	public double getCardCommissionOfPackage() {
		return cardCommissionOfPackage;
	}

	public void setCardCommissionOfPackage(double cardCommissionOfPackage) {
		this.cardCommissionOfPackage = cardCommissionOfPackage;
	}

	public double getCardCommissionOfCard() {
		return cardCommissionOfCard;
	}

	public void setCardCommissionOfCard(double cardCommissionOfCard) {
		this.cardCommissionOfCard = cardCommissionOfCard;
	}

	public int getCustomerCountOfBigProject() {
		return customerCountOfBigProject;
	}

	public void setCustomerCountOfBigProject(int customerCountOfBigProject) {
		this.customerCountOfBigProject = customerCountOfBigProject;
	}

	public int getCustomerCountOfSmallProject() {
		return customerCountOfSmallProject;
	}

	public void setCustomerCountOfSmallProject(int customerCountOfSmallProject) {
		this.customerCountOfSmallProject = customerCountOfSmallProject;
	}

	public int getCustomerCountOfIsAppoint() {
		return customerCountOfIsAppoint;
	}

	public void setCustomerCountOfIsAppoint(int customerCountOfIsAppoint) {
		this.customerCountOfIsAppoint = customerCountOfIsAppoint;
	}

	public int getCustomerCountOfNotAppoint() {
		return customerCountOfNotAppoint;
	}

	public void setCustomerCountOfNotAppoint(int customerCountOfNotAppoint) {
		this.customerCountOfNotAppoint = customerCountOfNotAppoint;
	}

	public int getCustomerCountOfIsAssign() {
		return customerCountOfIsAssign;
	}

	public void setCustomerCountOfIsAssign(int customerCountOfIsAssign) {
		this.customerCountOfIsAssign = customerCountOfIsAssign;
	}

	public int getCustomerCountOfNotAssign() {
		return customerCountOfNotAssign;
	}

	public void setCustomerCountOfNotAssign(int customerCountOfNotAssign) {
		this.customerCountOfNotAssign = customerCountOfNotAssign;
	}

	public BigDecimal getEmployeeCommissionOfProject() {
		return employeeCommissionOfProject;
	}

	public void setEmployeeCommissionOfProject(BigDecimal employeeCommissionOfProject) {
		this.employeeCommissionOfProject = employeeCommissionOfProject;
	}

	public BigDecimal getEmployeeCommissionOfGoods() {
		return employeeCommissionOfGoods;
	}

	public void setEmployeeCommissionOfGoods(BigDecimal employeeCommissionOfGoods) {
		this.employeeCommissionOfGoods = employeeCommissionOfGoods;
	}

	public BigDecimal getEmployeeCommissionOfPackage() {
		return employeeCommissionOfPackage;
	}

	public void setEmployeeCommissionOfPackage(BigDecimal employeeCommissionOfPackage) {
		this.employeeCommissionOfPackage = employeeCommissionOfPackage;
	}

	public BigDecimal getEmployeeCommissionOfCard() {
		return employeeCommissionOfCard;
	}

	public void setEmployeeCommissionOfCard(BigDecimal employeeCommissionOfCard) {
		this.employeeCommissionOfCard = employeeCommissionOfCard;
	}

	public long getEmployeeEvaluateFive() {
		return employeeEvaluateFive;
	}

	public void setEmployeeEvaluateFive(long employeeEvaluateFive) {
		this.employeeEvaluateFive = employeeEvaluateFive;
	}

	public long getEmployeeEvaluateFour() {
		return employeeEvaluateFour;
	}

	public void setEmployeeEvaluateFour(long employeeEvaluateFour) {
		this.employeeEvaluateFour = employeeEvaluateFour;
	}

	public long getEmployeeEvaluateThree() {
		return employeeEvaluateThree;
	}

	public void setEmployeeEvaluateThree(long employeeEvaluateThree) {
		this.employeeEvaluateThree = employeeEvaluateThree;
	}

	public long getEmployeeEvaluateTwo() {
		return employeeEvaluateTwo;
	}

	public void setEmployeeEvaluateTwo(long employeeEvaluateTwo) {
		this.employeeEvaluateTwo = employeeEvaluateTwo;
	}

	public long getEmployeeEvaluateOne() {
		return employeeEvaluateOne;
	}

	public void setEmployeeEvaluateOne(long employeeEvaluateOne) {
		this.employeeEvaluateOne = employeeEvaluateOne;
	}

	public long getEmployeeEvaluateNull() {
		return employeeEvaluateNull;
	}

	public void setEmployeeEvaluateNull(long employeeEvaluateNull) {
		this.employeeEvaluateNull = employeeEvaluateNull;
	}

	public long getRewardCountOfLate() {
		return rewardCountOfLate;
	}

	public void setRewardCountOfLate(long rewardCountOfLate) {
		this.rewardCountOfLate = rewardCountOfLate;
	}

	public long getRewardCountOfHoliday() {
		return rewardCountOfHoliday;
	}

	public void setRewardCountOfHoliday(long rewardCountOfHoliday) {
		this.rewardCountOfHoliday = rewardCountOfHoliday;
	}

	public long getRewardCountOfAbsenteeism() {
		return rewardCountOfAbsenteeism;
	}

	public void setRewardCountOfAbsenteeism(long rewardCountOfAbsenteeism) {
		this.rewardCountOfAbsenteeism = rewardCountOfAbsenteeism;
	}

	public long getRewardCountOfSeriousMistake() {
		return rewardCountOfSeriousMistake;
	}

	public void setRewardCountOfSeriousMistake(long rewardCountOfSeriousMistake) {
		this.rewardCountOfSeriousMistake = rewardCountOfSeriousMistake;
	}

	public long getRewardCountOfSmallMistake() {
		return rewardCountOfSmallMistake;
	}

	public void setRewardCountOfSmallMistake(long rewardCountOfSmallMistake) {
		this.rewardCountOfSmallMistake = rewardCountOfSmallMistake;
	}

	public long getRewardCountOfComplaint() {
		return rewardCountOfComplaint;
	}

	public void setRewardCountOfComplaint(long rewardCountOfComplaint) {
		this.rewardCountOfComplaint = rewardCountOfComplaint;
	}

}
