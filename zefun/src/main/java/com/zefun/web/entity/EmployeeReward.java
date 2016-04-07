package com.zefun.web.entity;

/**
 * 员工奖惩记录entity
 * 
 * @author lzc
 *
 */
public class EmployeeReward {
	/** id */
	private Integer rewardId;

	/** 员工id */
	private Integer employeeId;

	/** 类型：1.迟到,2.早退,3.请假,4.旷工,5.全勤,6.小过,7.大过,8.警告,9.好评,10.差评,11.投诉 */
	private String type;

	/** 是否奖励：0，否；1，是； */
	private String isReward;

	/** 金额 */
	private Double number;

	/** 开始时间 */
	private String starttime;

	/** 结束时间 */
	private String endtime;

	/** 修改人 */
	private Integer modifyer;

	/** 修改时间 */
	private String modifytime;

	/** 原因 */
	private String reasons;

	/** 总数 */
	private Integer count;

	/**
	 * 无参构造
	 * 
	 * @author DavidLiang
	 * @date 2016年1月21日 上午10:49:04
	 */
	public EmployeeReward() {
		super();
	}

	/**
	 * 员工奖惩有参构造函数
	 * 
	 * @author DavidLiang
	 * @date 2016年1月20日 下午7:28:55
	 * @param employeeId
	 *            员工id
	 * @param type
	 *            奖惩类型
	 * @param isReward
	 *            是否奖励：0，否；1，是
	 * @param number
	 *            金额
	 * @param modifyer
	 *            修改人
	 * @param modifytime
	 *            修改时间
	 * @param reasons
	 *            原因
	 * @param count
	 */
	public EmployeeReward(Integer employeeId, String type, String isReward, Double number, Integer modifyer,
			  String modifytime, String reasons) {
		super();
		this.employeeId = employeeId;
		this.type = type;
		this.isReward = isReward;
		this.number = number;
		this.modifyer = modifyer;
		this.modifytime = modifytime;
		this.reasons = reasons;
	}

	public Integer getRewardId() {
		return rewardId;
	}

	public void setRewardId(Integer rewardId) {
		this.rewardId = rewardId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public Integer getModifyer() {
		return modifyer;
	}

	public void setModifyer(Integer modifyer) {
		this.modifyer = modifyer;
	}

	public String getIsReward() {
		return isReward;
	}

	public void setIsReward(String isReward) {
		this.isReward = isReward == null ? null : isReward.trim();
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public String getModifytime() {
		return modifytime;
	}

	public void setModifytime(String modifytime) {
		this.modifytime = modifytime == null ? null : modifytime.trim();
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

}