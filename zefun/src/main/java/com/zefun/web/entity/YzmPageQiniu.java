package com.zefun.web.entity;

public class YzmPageQiniu {
    
	private Integer yzmPageId;

	private String pageUrl;

	private String pageValue;

	public Integer getYzmPageId() {
		return yzmPageId;
	}

	public void setYzmPageId(Integer yzmPageId) {
		this.yzmPageId = yzmPageId;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl == null ? null : pageUrl.trim();
	}

	public String getPageValue() {
		return pageValue;
	}

	public void setPageValue(String pageValue) {
		this.pageValue = pageValue == null ? null : pageValue.trim();
	}
}