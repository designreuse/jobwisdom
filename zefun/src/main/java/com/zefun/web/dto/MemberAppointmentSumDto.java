package com.zefun.web.dto;

/**
 * 会员预约汇总dto
 * 
 * @author DavidLiang
 * @date 2016年2月22日 下午4:58:44
 */
public class MemberAppointmentSumDto {

	/** 所有预约员工数量(本日被预约的员工) */
	private int allAppointEmployeeNum;

	/** 所有预约会员数目( 预约客户) */
	private int allAppointMemberNum;

	/** 所有预约项目金额( 预约项目总价) */
	private double allAppointPriceNum;

	/** 成功服务会员数目(已到店人数) */
	private int successServiceMemberNum;

	/** 成功服务挣取金额(到店已消费) */
	private double successServicePriceNum;

	/** 已取消客户人数 */
	private int cancelNum;

	public int getCancelNum() {
		return cancelNum;
	}

	public void setCancelNum(int cancelNum) {
		this.cancelNum = cancelNum;
	}

	public int getAllAppointEmployeeNum() {
		return allAppointEmployeeNum;
	}

	public void setAllAppointEmployeeNum(int allAppointEmployeeNum) {
		this.allAppointEmployeeNum = allAppointEmployeeNum;
	}

	public int getAllAppointMemberNum() {
		return allAppointMemberNum;
	}

	public void setAllAppointMemberNum(int allAppointMemberNum) {
		this.allAppointMemberNum = allAppointMemberNum;
	}

	public double getAllAppointPriceNum() {
		return allAppointPriceNum;
	}

	public void setAllAppointPriceNum(double allAppointPriceNum) {
		this.allAppointPriceNum = allAppointPriceNum;
	}

	public int getSuccessServiceMemberNum() {
		return successServiceMemberNum;
	}

	public void setSuccessServiceMemberNum(int successServiceMemberNum) {
		this.successServiceMemberNum = successServiceMemberNum;
	}

	public double getSuccessServicePriceNum() {
		return successServicePriceNum;
	}

	public void setSuccessServicePriceNum(double successServicePriceNum) {
		this.successServicePriceNum = successServicePriceNum;
	}

}
