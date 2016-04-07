package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 部门业绩分布表
* @author chendb
* @date 2015年9月8日 上午10:08:53
 */
public class DeptObjective {
	
    /**部门标识*/
    private Integer objectiveId;
    /**门店标识*/
    private Integer orderId;
    /**部门编码*/
    private Integer deptId;
    /**部门名称*/
    private BigDecimal  deptCalculate;
    /**是否产生业绩*/
    private Integer calculateType;
    /**操作时间*/
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
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

    
	
}