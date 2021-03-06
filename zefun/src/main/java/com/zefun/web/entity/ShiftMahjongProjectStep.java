package com.zefun.web.entity;

/**
 * @author laowang
 * @date 2015年08月10日 PM 22:38:58
 */
public class ShiftMahjongProjectStep {
	/** 轮牌信息标识 */
	private Integer shiftMahjongStepId;
    /** 步骤对应的轮牌标识*/
	private Integer shiftMahjongId;
	
	/** 岗位标识 */
	private Integer positionId;

	/** 订单明细 */
	private Integer detailId;

	/** 服务开始时间 */
	private String beginTime;

	/** 服务结束时间 */
	private String finishTime;

	/** 员工标识 */
	private Integer employeeId;

	/** 是否指定 */
	private Integer isAssign;

	/** 是否指派(0：否 1：是) */
	private Integer isDesignate;

	/** 是否预约 */
	private Integer isAppoint;

	/** 步骤状态(1：服务中、2：已结束) */
	private Integer isOver;

	/** 是否删除 */
	private Integer isDeleted;

	/** 是否当前服务步骤(0：否 1：是) */
	private Integer isCurrent;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;

	/***/
	private OrderDetail orderDetail;

	
	
	public Integer getShiftMahjongId() {
		return shiftMahjongId;
	}

	public void setShiftMahjongId(Integer shiftMahjongId) {
		this.shiftMahjongId = shiftMahjongId;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Integer getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(Integer isCurrent) {
		this.isCurrent = isCurrent;
	}

	public Integer getIsDesignate() {
		return isDesignate;
	}

	public void setIsDesignate(Integer isDesignate) {
		this.isDesignate = isDesignate;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getIsAssign() {
		return isAssign;
	}

	public void setIsAssign(Integer isAssign) {
		this.isAssign = isAssign;
	}

	public Integer getIsAppoint() {
		return isAppoint;
	}

	public void setIsAppoint(Integer isAppoint) {
		this.isAppoint = isAppoint;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public Integer getShiftMahjongStepId() {
		return shiftMahjongStepId;
	}

	public void setShiftMahjongStepId(Integer shiftMahjongStepId) {
		this.shiftMahjongStepId = shiftMahjongStepId;
	}

	/**
	 * @param beginTime
	 *            服务开始时间
	 */
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	/** @return 服务开始时间 */
	public String getBeginTime() {
		return beginTime;
	}

	/**
	 * @param finishTime
	 *            服务结束时间
	 */
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	/** @return 服务结束时间 */
	public String getFinishTime() {
		return finishTime;
	}

	/**
	 * @param isOver
	 *            是否结束(0：否 1：是)
	 */
	public void setIsOver(Integer isOver) {
		this.isOver = isOver;
	}

	/** @return 是否结束(0：否 1：是) */
	public Integer getIsOver() {
		return isOver;
	}

	/**
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/** @return 创建时间 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param updateTime
	 *            修改时间
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/** @return 修改时间 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param lastOperatorId
	 *            最后操作人标识
	 */
	public void setLastOperatorId(Integer lastOperatorId) {
		this.lastOperatorId = lastOperatorId;
	}

	/** @return 最后操作人标识 */
	public Integer getLastOperatorId() {
		return lastOperatorId;
	}

}