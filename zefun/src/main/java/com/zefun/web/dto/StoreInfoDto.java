package com.zefun.web.dto;

import java.util.List;

import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.FavourableAccount;


/**
 * 
* @author 骆峰
* @date 2016年6月29日 下午4:00:23
 */
public class StoreInfoDto {
	/** 门店标识 */
	private Integer storeId;

	/** 门店名称 */
	private String storeName;

	/** 门店代号 */
	private String storeAccount;
	
	/** 员工人员*/
	private List<EmployeeInfo> employeeInfos;
	
	/** 活动具体类 */
	private List<FavourableAccount> favourableAccount;

    public List<FavourableAccount> getFavourableAccount() {
        return favourableAccount;
    }

    public void setFavourableAccount(List<FavourableAccount> favourableAccount) {
        this.favourableAccount = favourableAccount;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAccount() {
        return storeAccount;
    }

    public void setStoreAccount(String storeAccount) {
        this.storeAccount = storeAccount;
    }

    public List<EmployeeInfo> getEmployeeInfos() {
        return employeeInfos;
    }

    public void setEmployeeInfos(List<EmployeeInfo> employeeInfos) {
        this.employeeInfos = employeeInfos;
    }
    
}