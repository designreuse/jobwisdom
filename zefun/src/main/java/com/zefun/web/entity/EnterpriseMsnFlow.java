package com.zefun.web.entity;

/**
 * 企业短信流水表
* @author 老王
* @date 2016年5月21日 下午6:45:27
 */
public class EnterpriseMsnFlow {
	/**
	 * 账户标识
	 */
    private Integer msnFlowId;

    /**
	 * 账户标识
	 */
    private Integer enterpriseAccountId;

    /**
	 * 流水类型(1:支出,2:收入)
	 */
    private Integer flowType;

    /**
	 * 流水数量
	 */
    private Integer flowAmount;

    /**
	 * 当前余量
	 */
    private Integer balanceAmount;

    /**
	 * 分配门店标识
	 */
    private Integer storeId;

    /**
	 * 分配门店名称
	 */
    private String storeName;

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
    
    /**
     * 是否删除(0:未删除,1:已删除)
     */
    private Integer isDeleted;

    
    
    public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getMsnFlowId() {
        return msnFlowId;
    }

    public void setMsnFlowId(Integer msnFlowId) {
        this.msnFlowId = msnFlowId;
    }

    public Integer getEnterpriseAccountId() {
        return enterpriseAccountId;
    }

    public void setEnterpriseAccountId(Integer enterpriseAccountId) {
        this.enterpriseAccountId = enterpriseAccountId;
    }

    public Integer getFlowType() {
        return flowType;
    }

    public void setFlowType(Integer flowType) {
        this.flowType = flowType;
    }

    public Integer getFlowAmount() {
        return flowAmount;
    }

    public void setFlowAmount(Integer flowAmount) {
        this.flowAmount = flowAmount;
    }

    public Integer getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Integer balanceAmount) {
        this.balanceAmount = balanceAmount;
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