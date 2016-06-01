package com.zefun.web.entity;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;

/**
 * @author 张进军
 * @date 2016年03月12日 PM 19:45:30
 */
public class MemberLevel {
	/** 等级标识 */
	private Integer levelId;

	/** 企业代号 */
	private String storeAccount;

	/** 等级名称 */
	private String levelName;
	/** 等级类型*/
	private String levelType;

	/** 背景logo*/
	private String levelLogo;
	
	/** 图片模板*/
	private Integer levelTemplate;
	
	/** 等级说明 */
	private String levelNotice;

	/** 是否默认等级(0:否,1:是) */
	private Integer isDefault;

	/** 是否删除(0:未删除,1:已删除) */
	private Integer isDeleted;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;
	
	/** 等级说明数组 */
    private String[] levelNoticeArr;

	public String getLevelType() {
		return levelType;
	}

	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}

	public String getLevelLogo() {
		return levelLogo;
	}

	public void setLevelLogo(String levelLogo) {
		this.levelLogo = levelLogo;
	}

	public Integer getLevelTemplate() {
		return levelTemplate;
	}

	public void setLevelTemplate(Integer levelTemplate) {
		this.levelTemplate = levelTemplate;
	}

	/** @param levelId	等级标识 */
	public void setLevelId(Integer levelId){
		this.levelId = levelId;
	}

	/** @return	等级标识 */
	public Integer getLevelId(){
		return levelId;
	}

	public String getStoreAccount() {
		return storeAccount;
	}

	public void setStoreAccount(String storeAccount) {
		this.storeAccount = storeAccount;
	}

	/** @param levelName	等级名称 */
	public void setLevelName(String levelName){
		this.levelName = levelName;
	}

	/** @return	等级名称 */
	public String getLevelName(){
		return levelName;
	}

	/** @param levelNotice	等级说明 */
	public void setLevelNotice(String levelNotice){
		this.levelNotice = levelNotice;
	}

	/** @return	等级说明 */
	public String getLevelNotice(){
		return levelNotice;
	}

	/** @param isDefault	是否默认等级(0:否,1:是) */
	public void setIsDefault(Integer isDefault){
		this.isDefault = isDefault;
	}

	/** @return	是否默认等级(0:否,1:是) */
	public Integer getIsDefault(){
		return isDefault;
	}

	/** @param isDeleted	是否删除(0:未删除,1:已删除) */
	public void setIsDeleted(Integer isDeleted){
		this.isDeleted = isDeleted;
	}

	/** @return	是否删除(0:未删除,1:已删除) */
	public Integer getIsDeleted(){
		return isDeleted;
	}

	/** @param createTime	创建时间 */
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	/** @return	创建时间 */
	public String getCreateTime(){
		return createTime;
	}

	/** @param updateTime	修改时间 */
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	/** @return	修改时间 */
	public String getUpdateTime(){
		return updateTime;
	}

	/** @param lastOperatorId	最后操作人标识 */
	public void setLastOperatorId(Integer lastOperatorId){
		this.lastOperatorId = lastOperatorId;
	}

	/** @return	最后操作人标识 */
	public Integer getLastOperatorId(){
		return lastOperatorId;
	}

    public String[] getLevelNoticeArr() {
        return levelNoticeArr;
    }

    public void setLevelNoticeArr(String[] levelNoticeArr) {
        this.levelNoticeArr = levelNoticeArr;
    }
    
    /**
     * 以JSON数组的方式获取等级描述信息
    * @author 张进军
    * @date Jan 28, 2016 9:00:49 PM
    * @return   等级描述信息
     */
    public JSONArray getLevelNoticeList(){
        if (StringUtils.isNotBlank(getLevelNotice())) {
            return JSONArray.fromObject(getLevelNotice());
        }
        return null;
    }

}