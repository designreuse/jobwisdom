package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 盛传疗程卡
* @author 高国藩
* @date 2015年12月30日 下午4:30:41
 */
public class RumorsCourse {
    
    /**主键*/
    private Integer id;
    /**姓名*/
    private String name;
    /**电话号码*/
    private String phone;
    /**卡号*/
    private String levelNum;
    /**卡类型*/
    private String levelName;
    /**主键*/
    private String levelType;
    /**项目描述键*/
    private String courseDesc;
    /**剩余次数*/
    private Integer residueDegree;
    /**剩余金额*/
    private BigDecimal residueAmount;
    /**剩余金额统计*/
    private BigDecimal residueAmounts;
    /**门店*/
    private Integer storeId;
    /**左后*/
    private Integer lastOperatorId;
    /**是否*/
    private Integer isDeleted;
    /**创建时间*/
    private String createTime;
    /**主键*/
    private String updateTime;

    
    public BigDecimal getResidueAmounts() {
        return residueAmounts;
    }

    public void setResidueAmounts(BigDecimal residueAmounts) {
        this.residueAmounts = residueAmounts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(String levelNum) {
        this.levelNum = levelNum == null ? null : levelNum.trim();
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType == null ? null : levelType.trim();
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc == null ? null : courseDesc.trim();
    }

    public Integer getResidueDegree() {
        return residueDegree;
    }

    public void setResidueDegree(Integer residueDegree) {
        this.residueDegree = residueDegree;
    }

    public BigDecimal getResidueAmount() {
        return residueAmount;
    }

    public void setResidueAmount(BigDecimal residueAmount) {
        this.residueAmount = residueAmount;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}