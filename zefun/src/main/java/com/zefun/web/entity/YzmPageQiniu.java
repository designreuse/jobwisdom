package com.zefun.web.entity;
/**
 * 验证码entity
* @author 老王
* @date 2016年4月28日 上午11:59:43
 */
public class YzmPageQiniu {
    /** 验证码id*/
	private Integer yzmPageId;
    /** 验证码对应url*/
	private String pageUrl;
	/** 验证码对应值*/
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