package com.zefun.web.dto;

import java.util.List;

/**
 * @author 张进军
 * @date 2015年11月20日 PM 15:24:14
 */
public class PositionInfoShiftMahjongDto {
    
    /** 岗位标识 */
    private Integer positionId;

    /** 门店标识 */
    private Integer storeId;

    /** 岗位名称 */
    private String positionName;

    /** 轮牌信息*/
    private List<ShiftMahjongDto> shiftMahjongDtoList;

    
    
	public List<ShiftMahjongDto> getShiftMahjongDtoList() {
		return shiftMahjongDtoList;
	}

	public void setShiftMahjongDtoList(List<ShiftMahjongDto> shiftMahjongDtoList) {
		this.shiftMahjongDtoList = shiftMahjongDtoList;
	}

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
}