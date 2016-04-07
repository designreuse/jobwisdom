package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2016年01月26日 PM 17:59:11
 */
public class WechatGroupInfo {
	/** 分组标识 */
	private Integer id;

	/** 门店标识 */
	private Integer storeId;

	/** 分组类型(1:会员，2:员工，3:老板，4:无身份) */
	private Integer groupType;

	/** 微信分组ID */
	private Integer groupId;

	/** 微信分组名称 */
	private String groupName;
	
	/**
	 * 默认构造函数
	* @author 张进军
	* @date Jan 26, 2016 8:40:46 PM
	 */
	public WechatGroupInfo() {
        super();
    }

    /**
	 * 初始化微信分组信息
	* @author 张进军
	* @date Jan 26, 2016 8:39:40 PM
	* @param storeId   门店标识
	* @param groupType 分组类型(1:会员，2:员工，3:老板，4:无身份)
	* @param groupId   微信分组ID
	* @param groupName 微信分组名称
	 */
	public WechatGroupInfo(Integer storeId, Integer groupType, Integer groupId, String groupName) {
        super();
        this.storeId = storeId;
        this.groupType = groupType;
        this.groupId = groupId;
        this.groupName = groupName;
    }

    /** @param id	分组标识 */
	public void setId(Integer id){
		this.id = id;
	}

	/** @return	分组标识 */
	public Integer getId(){
		return id;
	}

	/** @param storeId	门店标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店标识 */
	public Integer getStoreId(){
		return storeId;
	}

	/** @param groupType	分组类型(1:会员，2:员工，3:老板，4:无身份) */
	public void setGroupType(Integer groupType){
		this.groupType = groupType;
	}

	/** @return	分组类型(1:会员，2:员工，3:老板，4:无身份) */
	public Integer getGroupType(){
		return groupType;
	}

	/** @param groupId	微信分组ID */
	public void setGroupId(Integer groupId){
		this.groupId = groupId;
	}

	/** @return	微信分组ID */
	public Integer getGroupId(){
		return groupId;
	}

	/** @param groupName	微信分组名称 */
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}

	/** @return	微信分组名称 */
	public String getGroupName(){
		return groupName;
	}

}