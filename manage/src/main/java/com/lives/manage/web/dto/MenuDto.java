package com.lives.manage.web.dto;

import java.util.List;

/**
 * @author 张进军
 * @date Jul 6, 2015 10:08:36 PM 
 */
public class MenuDto {
	private String name;
	private String url;
	private String classname;
	private List<MenuDto> subMenus;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public List<MenuDto> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<MenuDto> subMenus) {
		this.subMenus = subMenus;
	}
}
