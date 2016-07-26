package com.zefun.web.entity;

import java.util.List;

/**
 * 菜单
* @author 骆峰
* @date 2016年7月20日 下午3:55:16
 */
public class MenuIdQuote {
    /** 菜单类型 */
    private Integer menuType;

    /** 第几个菜单 */
    private Integer menuId;

    /** 二级菜单引用的一级菜单id */
    private Integer quoteId;

    /** 菜单html */
    private String menuHtml;
    
    /** 二级菜单集合*/
    private List<MenuIdQuote> menuIdQuotes;

    
    public List<MenuIdQuote> getMenuIdQuotes() {
        return menuIdQuotes;
    }

    public void setMenuIdQuotes(List<MenuIdQuote> menuIdQuotes) {
        this.menuIdQuotes = menuIdQuotes;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }

    public String getMenuHtml() {
        return menuHtml;
    }

    public void setMenuHtml(String menuHtml) {
        this.menuHtml = menuHtml == null ? null : menuHtml.trim();
    }
}