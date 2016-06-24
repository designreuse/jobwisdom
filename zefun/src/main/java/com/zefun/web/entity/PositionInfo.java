package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年11月20日 PM 15:24:14
 */
public class PositionInfo {
    
    /** 岗位标识 */
    private Integer positionId;

    /** 门店标识 */
    private Integer storeId;

    /** 岗位名称 */
    private String positionName;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 最后操作人标识 */
    private Integer lastOperatorId;

    /** @param positionId   岗位标识 */
    public void setPositionId(Integer positionId){
        this.positionId = positionId;
    }

    /** @return 岗位标识 */
    public Integer getPositionId(){
        return positionId;
    }

    /** @param storeId  门店标识 */
    public void setStoreId(Integer storeId){
        this.storeId = storeId;
    }

    /** @return 门店标识 */
    public Integer getStoreId(){
        return storeId;
    }

    /** @param positionName 岗位名称 */
    public void setPositionName(String positionName){
        this.positionName = positionName;
    }

    /** @return 岗位名称 */
    public String getPositionName(){
        return positionName;
    }

    /** @param createTime   创建时间 */
    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }

    /** @return 创建时间 */
    public String getCreateTime(){
        return createTime;
    }

    /** @param updateTime   修改时间 */
    public void setUpdateTime(String updateTime){
        this.updateTime = updateTime;
    }

    /** @return 修改时间 */
    public String getUpdateTime(){
        return updateTime;
    }

    /** @param lastOperatorId   最后操作人标识 */
    public void setLastOperatorId(Integer lastOperatorId){
        this.lastOperatorId = lastOperatorId;
    }

    /** @return 最后操作人标识 */
    public Integer getLastOperatorId(){
        return lastOperatorId;
    }
    
    /**
     * 常态方法
    * @author 高国藩
    * @date 2016年6月23日 下午3:26:07
     */
    public PositionInfo() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 参数构造
    * @author 高国藩
    * @date 2016年6月23日 下午3:25:40
    * @param positionId       positionId
    * @param storeId          storeId
    * @param positionName     positionName
    * @param createTime       createTime
    * @param updateTime       updateTime
    * @param lastOperatorId   lastOperatorId
     */
    public PositionInfo(Integer positionId, Integer storeId,
            String positionName, String createTime, String updateTime,
            Integer lastOperatorId) {
        this.positionId = positionId;
        this.storeId = storeId;
        this.positionName = positionName;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.lastOperatorId = lastOperatorId;
    }
    
}