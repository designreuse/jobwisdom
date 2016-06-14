package com.zefun.api.entity;

/**
 * @author 小高
 * @date 2015年12月06日 PM 12:02:16
 */
public class EmployeeAttendance {
	/** 记录标识 */
	private Integer recordId;

	/** 员工标识 */
	private Integer employeeId;

	/** 考勤日期 */
	private String attendanceDate;

	/** 签到时间 */
	private String signInTime;

	/** 签到时间差(分钟) */
	private Integer signInOffset;

	/** 签退时间 */
	private String signOutTime;

	/** 签退时间差(分钟) */
	private Integer signOutOffset;

	/** 修改人(id) */
	private Integer modifyer;

	/** 注释 */
	private String comment;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public String getSignInTime() {
		return signInTime;
	}

	public void setSignInTime(String signInTime) {
		this.signInTime = signInTime;
	}

	public Integer getSignInOffset() {
		return signInOffset;
	}

	public void setSignInOffset(Integer signInOffset) {
		this.signInOffset = signInOffset;
	}

	public String getSignOutTime() {
		return signOutTime;
	}

	public void setSignOutTime(String signOutTime) {
		this.signOutTime = signOutTime;
	}

	public Integer getSignOutOffset() {
		return signOutOffset;
	}

	public void setSignOutOffset(Integer signOutOffset) {
		this.signOutOffset = signOutOffset;
	}

	public Integer getModifyer() {
		return modifyer;
	}

	public void setModifyer(Integer modifyer) {
		this.modifyer = modifyer;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}