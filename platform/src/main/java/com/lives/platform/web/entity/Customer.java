package com.lives.platform.web.entity;

public class Customer {
    private Integer customerId;

    private String customerName;

    private String customerMobile;

    private String sex;

    private String birthday;

    private Integer isLunarCal;

    private String sysCardNum;

    private Integer storesId;

    private String createTime;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile == null ? null : customerMobile.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public Integer getIsLunarCal() {
        return isLunarCal;
    }

    public void setIsLunarCal(Integer isLunarCal) {
        this.isLunarCal = isLunarCal;
    }

    public String getSysCardNum() {
        return sysCardNum;
    }

    public void setSysCardNum(String sysCardNum) {
        this.sysCardNum = sysCardNum == null ? null : sysCardNum.trim();
    }

    public Integer getStoresId() {
        return storesId;
    }

    public void setStoresId(Integer storesId) {
        this.storesId = storesId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}