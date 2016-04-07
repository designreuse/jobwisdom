package com.lives.manage.web.dto;

import java.util.List;

import net.sf.json.JSONObject;

public class PrivilegeInfoDto {

	private Integer id;
	private String type;
	private String text;
	private String url;
	private String classname;
	private Integer refId;
	private String fatherName;
	private String value;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private List<PrivilegeInfoDto> nodes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		JSONObject jsonObject = JSONObject.fromObject(url);
		if(jsonObject.get("url") == null){
			return jsonObject.get("jsp").toString();
		}else{
			return jsonObject.get("url").toString();
		}
		
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClassname() {
		JSONObject jsonObject = JSONObject.fromObject(url);
		return  jsonObject.get("classname").toString();
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public Integer getRefId() {
		return refId;
	}

	public void setRefId(Integer refId) {
		this.refId = refId;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public List<PrivilegeInfoDto> getNodes() {
		return nodes;
	}

	public void setNodes(List<PrivilegeInfoDto> nodes) {
		this.nodes = nodes;
	}

	public PrivilegeInfoDto() {
		// TODO Auto-generated constructor stub
	}

}
