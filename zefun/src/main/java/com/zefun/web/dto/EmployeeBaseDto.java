package com.zefun.web.dto;

import java.io.Serializable;


/**
* @author 张进军
* @date Oct 14, 2015 9:03:50 PM 
*/
public class EmployeeBaseDto implements Serializable{
    /** @Fields serialVersionUID : */
    private static final long serialVersionUID = 1L;

    /** 员工标识 */
    private Integer employeeId;
    
    /** 门店标识 */
    private String storeId;
    
    /** 门店类型(1:单店，2:连锁总店，3:连锁分店) */
    private Integer storeType;
    
    /** 岗位标示*/
    private Integer positionId;

    /** 门店名称 */
    private String storeName;
    
    /** 级别标识 */
    private Integer levelId;

    /** 级别名称 */
    private String levelName;

    /** 员工编码 */
    private Integer employeeCode;

    /** 姓名 */
    private String name;
    
    /** 手机号码 */
    private String phone;

    /** 头像 */
    private String headImage;

    /** 性别 */
    private String sex;
    
    /** 服务人数 */
    private Integer servicePeoples;

    /** 服务次数 */
    private Integer serviceCount;

    /** 综合评分 */
    private Float serviceScore;
    
    /** 员工简介 */
    private String employeeDesc;
    
    /** 基本工资 */
    private  double baseSalaries;
    /** 奖金（正负就看你自己咯） */
    private  double number;

    
    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public double getBaseSalaries() {
        return baseSalaries;
    }

    public void setBaseSalaries(double baseSalaries) {
        this.baseSalaries = baseSalaries;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
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
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getServicePeoples() {
        return servicePeoples;
    }

    public void setServicePeoples(Integer servicePeoples) {
        this.servicePeoples = servicePeoples;
    }

    public Integer getServiceCount() {
        return serviceCount;
    }

    public void setServiceCount(Integer serviceCount) {
        this.serviceCount = serviceCount;
    }

    public Float getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(Float serviceScore) {
        this.serviceScore = serviceScore;
    }

    public String getEmployeeDesc() {
        return employeeDesc;
    }

    public void setEmployeeDesc(String employeeDesc) {
        this.employeeDesc = employeeDesc;
    }

}
