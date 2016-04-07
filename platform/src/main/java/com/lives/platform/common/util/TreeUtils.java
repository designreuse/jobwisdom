package com.lives.platform.common.util;


public class TreeUtils {
/**父级Id*/
private Integer  id ;
/**角色Id*/
private Integer  roleId ;
private Integer userId;

public Integer getUserId() {
	return userId;
}
public void setUserId(Integer userId) {
	this.userId = userId;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getRoleId() {
	return roleId;
}
public void setRoleId(Integer roleId) {
	this.roleId = roleId;
}
public TreeUtils() {
	// TODO Auto-generated constructor stub
}
public TreeUtils(Integer id, Integer roleId, Integer userId) {
	super();
	this.id = id;
	this.roleId = roleId;
	this.userId = userId;
}

}
