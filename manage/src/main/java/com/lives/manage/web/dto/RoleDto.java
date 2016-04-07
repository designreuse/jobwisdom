package com.lives.manage.web.dto;

import java.util.List;

public class RoleDto {
private Integer id;
private String text;
private List<PrivilegeDto> nodes;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public List<PrivilegeDto> getNodes() {
	return nodes;
}
public void setNodes(List<PrivilegeDto> nodes) {
	this.nodes = nodes;
}
public RoleDto() {
	// TODO Auto-generated constructor stub
}
}
