package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 部门业绩结算
* @author 骆峰
* @date 2016年7月1日 下午6:08:11
 */
public class DeptObjective {
    /** 部门业绩分布标识 */
    private Integer objectiveId;

    /** 订单标识 */
    private Integer orderId;

    /** 部门标识 */
    private Integer deptId;

    /** 部门业绩值 */
    private BigDecimal deptCalculate;

    /** 业绩类型（1:开卡，2：充值，3：升级） */
    private Integer calculateType;

    /** 0正常1删除 */
    private Integer isDeleted;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    public Integer getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(Integer objectiveId) {
        this.objectiveId = objectiveId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public BigDecimal getDeptCalculate() {
        return deptCalculate;
    }

    public void setDeptCalculate(BigDecimal deptCalculate) {
        this.deptCalculate = deptCalculate;
    }

    public Integer getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(Integer calculateType) {
        this.calculateType = calculateType;
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