package com.lives.manage.web.dto;

public class MenuInfoDto {
	
	private Integer menuId;

    private Integer parentId;
    
    private Integer parentName;

    private String menuName;

    private String linkUrl;

    private Integer ordreNo;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getParentName() {
		return parentName;
	}

	public void setParentName(Integer parentName) {
		this.parentName = parentName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Integer getOrdreNo() {
		return ordreNo;
	}

	public void setOrdreNo(Integer ordreNo) {
		this.ordreNo = ordreNo;
	}
    
    

}
