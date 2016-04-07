package com.lives.platform.common.util;

public class RoleUtils {
	private Integer userId;
	private Integer refId;
	private Integer roleId;
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRefId() {
		return refId;
	}

	public void setRefId(Integer refId) {
		this.refId = refId;
	}

	public RoleUtils() {
		// TODO Auto-generated constructor stub
	}

	public RoleUtils(Integer userId, Integer refId) {
		super();
		this.userId = userId;
		this.refId = refId;
	}

	@Override
	public String toString() {
		return "RoleUtils [userId=" + userId + ", roleId=" + roleId + "]";
	}
	
}
