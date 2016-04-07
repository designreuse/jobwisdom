package com.zefun.web.dto;

import java.util.List;

/**
 * 轮牌信息，包含职位列表
* @author 张进军
* @date Oct 15, 2015 1:31:09 AM 
*/
public class MahjongLevelDto {
    /** 轮牌信息标识 */
    private Integer shiftMahjongId;

    /** 轮牌名称 */
    private String shiftMahjongName;
    
    /** 轮牌信息下的岗位信息*/
    private String positionId;
    
    /** 职位列表 */
    private List<EmployeeLevelBaseDto> employeeLevelList;

    
    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public Integer getShiftMahjongId() {
        return shiftMahjongId;
    }

    public void setShiftMahjongId(Integer shiftMahjongId) {
        this.shiftMahjongId = shiftMahjongId;
    }

    public String getShiftMahjongName() {
        return shiftMahjongName;
    }

    public void setShiftMahjongName(String shiftMahjongName) {
        this.shiftMahjongName = shiftMahjongName;
    }

    public List<EmployeeLevelBaseDto> getEmployeeLevelList() {
        return employeeLevelList;
    }

    public void setEmployeeLevelList(List<EmployeeLevelBaseDto> employeeLevelList) {
        this.employeeLevelList = employeeLevelList;
    }
}
