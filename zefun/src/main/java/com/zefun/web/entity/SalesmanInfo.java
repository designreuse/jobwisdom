package com.zefun.web.entity;

/**
 * 业务员信息实体
 * 
 * @author lzc
 *
 */
public class SalesmanInfo {
	/** 业务员id */
	private Integer salesmanId;

	/** 业务员微信微信 */
	private String openId;

	/** 所属代理id */
	private Integer agentId;

	/** 状态(0：正常，1：停用) */
	private Integer status;

	/** 是否删除(0:未删除,1:已删除) */
	private Integer isDelete;

	/** 姓名 */
	private String name;

	/** 电话 */
	private String phone;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 登录密码 */
	private String password;

	/** 年龄 */
	private Integer age;

	/** 性别 */
	private String gender;

	/** 备注 */
	private String comment;

	/** 业务员已完成客户总数 */
	private int countStoreCompleted;

	/** 业务员正在洽谈中(进行中)客户总数 */
	private int countStoreProcessing;

	/** 渠道代理信息实体 */
	private AgentInfo agentInfo;

	public Integer getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(Integer salesmanId) {
		this.salesmanId = salesmanId;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime == null ? null : createTime.trim();
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime == null ? null : updateTime.trim();
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public int getCountStoreCompleted() {
		return countStoreCompleted;
	}

	public void setCountStoreCompleted(int countStoreCompleted) {
		this.countStoreCompleted = countStoreCompleted;
	}

	public int getCountStoreProcessing() {
		return countStoreProcessing;
	}

	public void setCountStoreProcessing(int countStoreProcessing) {
		this.countStoreProcessing = countStoreProcessing;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public AgentInfo getAgentInfo() {
		return agentInfo;
	}

	public void setAgentInfo(AgentInfo agentInfo) {
		this.agentInfo = agentInfo;
	}

}