package com.lives.platform.web.dto;

import java.math.BigDecimal;

import com.lives.platform.web.entity.Card;

public class VipUserDto {

	//会员姓名	
	private String vipName;
	//手机号码
	private String vipMobile;
	//性别
	private Integer sex;
	//生日
	private String birthday;
	//是否农历
	private Integer isLunarCal;
	//会员等级id
	private Integer vipLevelId;
	//系统卡号
	private String sysCardNum;
	//会员ID
	private Integer vipId;
	//卡号id
	private Integer vipCardId;
	//会员信息id
	private Integer vipInfoId;
	
	private Card card;
	
	
	public VipUserDto() {
		super();
	}
	
	public VipUserDto(String vipName, String vipMobile, Integer sex,
			String birthday, Integer isLunarCal, Integer vipLevelId,
			String sysCardNum, String cardNo, BigDecimal cardAmount,
			String cardPassword, Integer vipId, Integer vipCardId,
			Integer vipInfoId) {
		super();
		this.vipName = vipName;
		this.vipMobile = vipMobile;
		this.sex = sex;
		this.birthday = birthday;
		this.isLunarCal = isLunarCal;
		this.vipLevelId = vipLevelId;
		this.sysCardNum = sysCardNum;
		this.vipId = vipId;
		this.vipCardId = vipCardId;
		this.vipInfoId = vipInfoId;
	}
	
	
	
	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public String getVipName() {
		return vipName;
	}
	public void setVipName(String vipName) {
		this.vipName = vipName;
	}
	public String getVipMobile() {
		return vipMobile;
	}
	public void setVipMobile(String vipMobile) {
		this.vipMobile = vipMobile;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Integer getIsLunarCal() {
		return isLunarCal;
	}
	public void setIsLunarCal(Integer isLunarCal) {
		this.isLunarCal = isLunarCal;
	}
	public Integer getVipLevelId() {
		return vipLevelId;
	}
	public void setVipLevelId(Integer vipLevelId) {
		this.vipLevelId = vipLevelId;
	}
	public String getSysCardNum() {
		return sysCardNum;
	}
	public void setSysCardNum(String sysCardNum) {
		this.sysCardNum = sysCardNum;
	}
	public Integer getVipId() {
		return vipId;
	}
	public void setVipId(Integer vipId) {
		this.vipId = vipId;
	}
	public Integer getVipCardId() {
		return vipCardId;
	}
	public void setVipCardId(Integer vipCardId) {
		this.vipCardId = vipCardId;
	}
	public Integer getVipInfoId() {
		return vipInfoId;
	}
	public void setVipInfoId(Integer vipInfoId) {
		this.vipInfoId = vipInfoId;
	}
	
	
	
	
}
