package com.zefun.web.entity;

/**
 * 渠道跟踪实体
 * 
 * @author DavidLiang
 * @date 2016年1月18日 下午5:46:19
 */
public class AgentFollow {
	/** 渠道跟踪id(主键) */
	private Integer agentFollowId;

	/** 店铺/客户id(关联store_info/store_account表主键) */
	private Integer storeId;

	/** 操作员类型：1.渠道商，2.业务员 */
	private String operatortype;

	/** 操作人微信标识 */
	private String openId;

	/** 跟踪内容 */
	private String content;

	/** 是否删除：0.正常，1删除 */
	private String isDelete;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	public Integer getAgentFollowId() {
		return agentFollowId;
	}

	public void setAgentFollowId(Integer agentFollowId) {
		this.agentFollowId = agentFollowId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getOperatortype() {
		return operatortype;
	}

	public void setOperatortype(String operatortype) {
		this.operatortype = operatortype == null ? null : operatortype.trim();
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId == null ? null : openId.trim();
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete == null ? null : isDelete.trim();
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
		this.updateTime = updateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}