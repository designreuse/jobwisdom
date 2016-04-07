/**
 * 
 */
package com.lives.manage.web.dto;

/** 
 * @author 陈端斌
 * @date 2015年6月8日 下午5:56:18 
 *  
 */
public class CustInfoDto {

    private Integer customerId;

    private Integer parentId;

    private Integer customerType;
    
    private String customerType1;

    private String customerName;

    private String companyName;

    private String customerCode;

    private String brandName;

    private String province;

    private String city;

    private String area;

    private String addr;

    private String phone;

    private String principalDuty;
    
    private String principalDuty1;

    private String principalName;

    private String sysName;
    
    private String sysName1;

    private String expireTime;

    private String userName;

    private String paasWord;

    private String createTime;

    private Integer creatorId;

    private String updateTime;

    private Integer updatorId;

    private Integer isPay;
    private String isPay1;

    private Integer isDelete;

    public String getIsPay1() {
		return isPay1;
	}

	public void setIsPay1(String isPay1) {
		this.isPay1 = isPay1;
	}

	public String getPrincipalDuty1() {
		return principalDuty1;
	}

	public void setPrincipalDuty1(String principalDuty1) {
		this.principalDuty1 = principalDuty1;
	}

	public String getCustomerType1() {
		return customerType1;
	}

	public void setCustomerType1(String customerType1) {
		this.customerType1 = customerType1;
	}

	public String getSysName1() {
		return sysName1;
	}

	public void setSysName1(String sysName1) {
		this.sysName1 = sysName1;
	}

	public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPrincipalDuty() {
        return principalDuty;
    }

    public void setPrincipalDuty(String principalDuty) {
        this.principalDuty = principalDuty == null ? null : principalDuty.trim();
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName == null ? null : principalName.trim();
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName == null ? null : sysName.trim();
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime == null ? null : expireTime.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPaasWord() {
        return paasWord;
    }

    public void setPaasWord(String paasWord) {
        this.paasWord = paasWord == null ? null : paasWord.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public Integer getUpdatorId() {
        return updatorId;
    }

    public void setUpdatorId(Integer updatorId) {
        this.updatorId = updatorId;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
