package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 活动
* @author 骆峰
* @date 2016年7月6日 上午9:56:07
 */
public class ActivityInfoDto {
    /** 活动标识 */
    private Integer activityId;

    /** 活动名称 */
    private String activityName;

    /** 活动类型（1折扣，2现金，3体验） */
    private Integer activityType;

    /** 活动策略 */
    private BigDecimal activityCl;

    /** 是否允许会员打折(1是，2否) */
    private Integer activityDiscount;

    /** 岗位标识 */
    private String activityPositionOne;

    /** 岗位标识 */
    private String activityPositionTwo;

    /** 岗位标识 */
    private String activityPositionThree;

    /** 提成方式（1，固定，2比利） */
    private Integer positionOneType;

    /** 提成 */
    private BigDecimal positionOnePush;

    /** 业绩 */
    private BigDecimal positionOneResult;

    /** 提成方式（1，固定，2比利） */
    private Integer positionTwoType;

    /** 提成 */
    private BigDecimal positionTwoPush;

    /** 业绩 */
    private BigDecimal positionTwoResult;

    /** 提成方式（1，固定，2比利） */
    private Integer positionThreeType;

    /** 提成 */
    private BigDecimal positionThreePush;

    /** 业绩 */
    private BigDecimal positionThreeResult;

    /** 门店标识 */
    private Integer storeId;

    /** 企业标识 */
    private String storeAccount;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 是否删除（0正常，1删除） */
    private Integer isDeleted;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public BigDecimal getActivityCl() {
        return activityCl;
    }

    public void setActivityCl(BigDecimal activityCl) {
        this.activityCl = activityCl;
    }

    public Integer getActivityDiscount() {
        return activityDiscount;
    }

    public void setActivityDiscount(Integer activityDiscount) {
        this.activityDiscount = activityDiscount;
    }

   
    public String getActivityPositionOne() {
        return activityPositionOne;
    }

    public void setActivityPositionOne(String activityPositionOne) {
        this.activityPositionOne = activityPositionOne;
    }

    public String getActivityPositionTwo() {
        return activityPositionTwo;
    }

    public void setActivityPositionTwo(String activityPositionTwo) {
        this.activityPositionTwo = activityPositionTwo;
    }

    public String getActivityPositionThree() {
        return activityPositionThree;
    }

    public void setActivityPositionThree(String activityPositionThree) {
        this.activityPositionThree = activityPositionThree;
    }

    public Integer getPositionOneType() {
        return positionOneType;
    }

    public void setPositionOneType(Integer positionOneType) {
        this.positionOneType = positionOneType;
    }

    public BigDecimal getPositionOnePush() {
        return positionOnePush;
    }

    public void setPositionOnePush(BigDecimal positionOnePush) {
        this.positionOnePush = positionOnePush;
    }

    public BigDecimal getPositionOneResult() {
        return positionOneResult;
    }

    public void setPositionOneResult(BigDecimal positionOneResult) {
        this.positionOneResult = positionOneResult;
    }

    public Integer getPositionTwoType() {
        return positionTwoType;
    }

    public void setPositionTwoType(Integer positionTwoType) {
        this.positionTwoType = positionTwoType;
    }

    public BigDecimal getPositionTwoPush() {
        return positionTwoPush;
    }

    public void setPositionTwoPush(BigDecimal positionTwoPush) {
        this.positionTwoPush = positionTwoPush;
    }

    public BigDecimal getPositionTwoResult() {
        return positionTwoResult;
    }

    public void setPositionTwoResult(BigDecimal positionTwoResult) {
        this.positionTwoResult = positionTwoResult;
    }

    public Integer getPositionThreeType() {
        return positionThreeType;
    }

    public void setPositionThreeType(Integer positionThreeType) {
        this.positionThreeType = positionThreeType;
    }

    public BigDecimal getPositionThreePush() {
        return positionThreePush;
    }

    public void setPositionThreePush(BigDecimal positionThreePush) {
        this.positionThreePush = positionThreePush;
    }

    public BigDecimal getPositionThreeResult() {
        return positionThreeResult;
    }

    public void setPositionThreeResult(BigDecimal positionThreeResult) {
        this.positionThreeResult = positionThreeResult;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }


    public String getStoreAccount() {
        return storeAccount;
    }

    public void setStoreAccount(String storeAccount) {
        this.storeAccount = storeAccount;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}