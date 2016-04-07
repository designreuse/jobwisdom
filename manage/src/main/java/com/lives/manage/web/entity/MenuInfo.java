package com.lives.manage.web.entity;

import net.sf.json.JSONObject;

public class MenuInfo {
    private Integer menuId;

    private Integer parentId;

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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public Integer getOrdreNo() {
        return ordreNo;
    }

    public void setOrdreNo(Integer ordreNo) {
        this.ordreNo = ordreNo;
    }
}