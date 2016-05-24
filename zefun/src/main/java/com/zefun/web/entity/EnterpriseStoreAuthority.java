package com.zefun.web.entity;

/**
 * 企业门店授权
* @author 老王
* @date 2016年5月21日 下午6:47:03
 */
public class EnterpriseStoreAuthority {
	
	/**
	 * 授权标识
	 */
    private Integer storeAuthorityId;

    /**
     * 企业标识
     */
    private Integer enterpriseInfoId;

    /**
     * 门店标识
     */
    private Integer storeId;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 授权码
     */
    private String authorityValue;

    /**
     * 员工标识
     */
    private Integer employeeId;

    /**
     * 员工编码
     */
    private Integer employeeCode;

    /**
     * 姓名
     */
    private String name;
    
    /**
     * 是否删除(0:未删除,1:已删除)
     */
    private Integer isDeleted;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 最后操作人标识
     */
    private Integer lastOperatorId;

    
    
    public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getStoreAuthorityId() {
        return storeAuthorityId;
    }

    public void setStoreAuthorityId(Integer storeAuthorityId) {
        this.storeAuthorityId = storeAuthorityId;
    }

    public Integer getEnterpriseInfoId() {
        return enterpriseInfoId;
    }

    public void setEnterpriseInfoId(Integer enterpriseInfoId) {
        this.enterpriseInfoId = enterpriseInfoId;
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
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getAuthorityValue() {
        return authorityValue;
    }

    public void setAuthorityValue(String authorityValue) {
        this.authorityValue = authorityValue == null ? null : authorityValue.trim();
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Integer employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }
}