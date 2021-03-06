package com.zefun.web.dto;

import java.util.List;

import com.zefun.web.entity.EmployeeLevel;
/**
 * 岗位相关信息
* @author chendb
* @date 2015年8月24日 下午3:31:05
 */
public class PositionInfoDto {
    /** 岗位标识*/
    private Integer positionId;
    /** 门店标识*/
    private Integer storeId;
    /** 岗位名称*/
    private String positionName;
    /** 是否为店长\\收银员(0,不是 1是)*/
    private  String isShow;
   /**职位相关信息*/
    private List<EmployeeLevel> employeeLevel;
    
    
    public Integer getStoreId() {
		return storeId;
	}
	
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getIsShow() {
		return isShow;
	}
    
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public Integer getPositionId() {
        return positionId;
    }
	
    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
    public String getPositionName() {
        return positionName;
    }
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    public List<EmployeeLevel> getEmployeeLevel() {
        return employeeLevel;
    }
    public void setEmployeeLevel(List<EmployeeLevel> employeeLevel) {
        this.employeeLevel = employeeLevel;
    }
    
}
