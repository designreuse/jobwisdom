package com.zefun.web.dto;

import java.util.List;
import com.zefun.web.entity.EmployeeInfo;

/**
 * 职位信息
* @author 陈端斌
* @date 2015年8月6日 下午2:26:49 
*
 */
public class EmployeeLevelDto {
    
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

    /** 岗位名称*/
    private String positionName;
    
    /**
     * 员工信息
     */
    private List<EmployeeInfo>  employeeInfoList;

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
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

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public List<EmployeeInfo> getEmployeeInfoList() {
        return employeeInfoList;
    }

    public void setEmployeeInfoList(List<EmployeeInfo> employeeInfoList) {
        this.employeeInfoList = employeeInfoList;
    }
	
}
