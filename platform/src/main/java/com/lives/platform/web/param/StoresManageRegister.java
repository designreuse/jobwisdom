package com.lives.platform.web.param;


public class StoresManageRegister {
    /**
     * 区域名称
     */
	private String storesManageName;
    /**
     * 总店id
     */
    private Integer hqStoresId;
    /**
     * 门店信息
     */
    private String checkboxname;
    /**
     * 店长帐号id
     */
    private Integer StoresId;
    /**区域ID */
    private Integer storesManageId;
    
    
    
	public Integer getStoresManageId() {
		return storesManageId;
	}
	public void setStoresManageId(Integer storesManageId) {
		this.storesManageId = storesManageId;
	}
	public Integer getStoresId() {
		return StoresId;
	}
	public void setStoresId(Integer storesId) {
		StoresId = storesId;
	}
	public String getStoresManageName() {
		return storesManageName;
	}
	public void setStoresManageName(String storesManageName) {
		this.storesManageName = storesManageName;
	}
	public Integer getHqStoresId() {
		return hqStoresId;
	}
	public void setHqStoresId(Integer hqStoresId) {
		this.hqStoresId = hqStoresId;
	}
	public String getCheckboxname() {
		return checkboxname;
	}
	public void setCheckboxname(String checkboxname) {
		this.checkboxname = checkboxname;
	}
    
    
	
}
