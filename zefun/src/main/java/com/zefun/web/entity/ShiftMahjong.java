package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class ShiftMahjong {
	/** 轮牌信息标识 */
	private Integer shiftMahjongId;

	/** 轮牌名称 */
	private String shiftMahjongName;

	/** 适用门店*/
	private Integer storeId;
	
	/** 上牌规则（1：考勤轮牌、2：持续轮牌） */
	private Integer shiftMahjongUp;

	/** 轮牌规则（1：指定不轮牌、2：指定某只后轮牌） */
	private Integer shiftMahjongRule;
	
	/** 轮牌性质(1：助理轮牌、2：技师轮牌)*/
	private Integer nature;
	
	/** 选择岗位*/
	private String positionId;

	/** 创建时间 */
	private String createTime;

	/** 操作人标识 */
	private Integer operatorId;

	

    public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    /** @param shiftMahjongId	轮牌信息标识 */
	public void setShiftMahjongId(Integer shiftMahjongId){
		this.shiftMahjongId = shiftMahjongId;
	}

	/** @return	轮牌信息标识 */
	public Integer getShiftMahjongId(){
		return shiftMahjongId;
	}

	/** @param shiftMahjongName	轮牌名称 */
	public void setShiftMahjongName(String shiftMahjongName){
		this.shiftMahjongName = shiftMahjongName;
	}

	/** @return	轮牌名称 */
	public String getShiftMahjongName(){
		return shiftMahjongName;
	}

	/** @param shiftMahjongUp	上牌规则（1：考勤轮牌、2：持续轮牌） */
	public void setShiftMahjongUp(Integer shiftMahjongUp){
		this.shiftMahjongUp = shiftMahjongUp;
	}

	/** @return	上牌规则（1：考勤轮牌、2：持续轮牌） */
	public Integer getShiftMahjongUp(){
		return shiftMahjongUp;
	}

	/** @param shiftMahjongRule	轮牌规则（1：指定不轮牌、2：指定某只后轮牌） */
	public void setShiftMahjongRule(Integer shiftMahjongRule){
		this.shiftMahjongRule = shiftMahjongRule;
	}

	/** @return	轮牌规则（1：指定不轮牌、2：指定某只后轮牌） */
	public Integer getShiftMahjongRule(){
		return shiftMahjongRule;
	}

	public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /** @param operatorId	操作人标识 */
	public void setOperatorId(Integer operatorId){
		this.operatorId = operatorId;
	}

	/** @return	操作人标识 */
	public Integer getOperatorId(){
		return operatorId;
	}

}