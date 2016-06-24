package com.zefun.web.entity;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class EmployeeLevel {
    /** 级别标识 */
    private Integer levelId;

    /** 门店标识 */
    private Integer storeId;

    /** 岗位标识 */
    private Integer positionId;

    /** 级别名称 */
    private String levelName;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 最后操作人标识 */
    private Integer lastOperatorId;

    /** 是否删除*/ 
    private Integer isDeleted;
    
    
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /** @param levelId	级别标识 */
    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    /** @return	级别标识 */
    public Integer getLevelId() {
        return levelId;
    }

    /** @param storeId	门店标识 */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /** @return	门店标识 */
    public Integer getStoreId() {
        return storeId;
    }

    /** @param positionId	岗位标识 */
    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    /** @return	岗位标识 */
    public Integer getPositionId() {
        return positionId;
    }

    /** @param levelName	级别名称 */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /** @return	级别名称 */
    public String getLevelName() {
        return levelName;
    }

    /** @param createTime	创建时间 */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /** @return	创建时间 */
    public String getCreateTime() {
        return createTime;
    }

    /** @param updateTime	修改时间 */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /** @return	修改时间 */
    public String getUpdateTime() {
        return updateTime;
    }

    /** @param lastOperatorId	最后操作人标识 */
    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    /** @return	最后操作人标识 */
    public Integer getLastOperatorId() {
        return lastOperatorId;
    }
    
    

}