package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2016年03月21日 PM 22:17:15
 */
public class MemberErrorData {
	/** 会员标识 */
	private Integer id;

	/** 门店标识 */
	private Integer storeId;

	/** 级别标识 */
	private Integer levelId;

	/** 姓名 */
	private String name;

	/** 性别 */
	private String sex;

	/** 生日 */
	private String birthday;

	/** 手机号码 */
	private String phone;

	/** 支付密码 */
	private String payPassword;

	/** 密码盐值 */
	private String passwordSalt;

	/** 储值总额 */
	private BigDecimal totalAmount;

	/** 储值余额 */
	private BigDecimal balanceAmount;

	/** 积分总额 */
	private Integer totalIntegral;

	/** 积分余额 */
	private Integer balanceIntegral;

	/** 礼金总额 */
	private BigDecimal totalGiftmoneyAmount;

	/** 礼金余额 */
	private BigDecimal balanceGiftmoneyAmount;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 是否迁移(0:未迁移,1:已迁移) */
	private Integer isMigrate;

	/** 是否删除(0:未删除,1:已删除) */
	private Integer isDeleted;

	/** 最后操作人标识 */
	private Integer lastOperatorId;

	/** @param id	会员标识 */
	public void setId(Integer id){
		this.id = id;
	}

	/** @return	会员标识 */
	public Integer getId(){
		return id;
	}

	/** @param storeId	门店标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店标识 */
	public Integer getStoreId(){
		return storeId;
	}

	/** @param levelId	级别标识 */
	public void setLevelId(Integer levelId){
		this.levelId = levelId;
	}

	/** @return	级别标识 */
	public Integer getLevelId(){
		return levelId;
	}

	/** @param name	姓名 */
	public void setName(String name){
		this.name = name;
	}

	/** @return	姓名 */
	public String getName(){
		return name;
	}

	/** @param sex	性别 */
	public void setSex(String sex){
		this.sex = sex;
	}

	/** @return	性别 */
	public String getSex(){
		return sex;
	}

	/** @param birthday	生日 */
	public void setBirthday(String birthday){
		this.birthday = birthday;
	}

	/** @return	生日 */
	public String getBirthday(){
		return birthday;
	}

	/** @param phone	手机号码 */
	public void setPhone(String phone){
		this.phone = phone;
	}

	/** @return	手机号码 */
	public String getPhone(){
		return phone;
	}

	/** @param payPassword	支付密码 */
	public void setPayPassword(String payPassword){
		this.payPassword = payPassword;
	}

	/** @return	支付密码 */
	public String getPayPassword(){
		return payPassword;
	}

	/** @param passwordSalt	密码盐值 */
	public void setPasswordSalt(String passwordSalt){
		this.passwordSalt = passwordSalt;
	}

	/** @return	密码盐值 */
	public String getPasswordSalt(){
		return passwordSalt;
	}

	/** @param totalAmount	储值总额 */
	public void setTotalAmount(BigDecimal totalAmount){
		this.totalAmount = totalAmount;
	}

	/** @return	储值总额 */
	public BigDecimal getTotalAmount(){
		return totalAmount;
	}

	/** @param balanceAmount	储值余额 */
	public void setBalanceAmount(BigDecimal balanceAmount){
		this.balanceAmount = balanceAmount;
	}

	/** @return	储值余额 */
	public BigDecimal getBalanceAmount(){
		return balanceAmount;
	}

	/** @param totalIntegral	积分总额 */
	public void setTotalIntegral(Integer totalIntegral){
		this.totalIntegral = totalIntegral;
	}

	/** @return	积分总额 */
	public Integer getTotalIntegral(){
		return totalIntegral;
	}

	/** @param balanceIntegral	积分余额 */
	public void setBalanceIntegral(Integer balanceIntegral){
		this.balanceIntegral = balanceIntegral;
	}

	/** @return	积分余额 */
	public Integer getBalanceIntegral(){
		return balanceIntegral;
	}

	/** @param totalGiftmoneyAmount	礼金总额 */
	public void setTotalGiftmoneyAmount(BigDecimal totalGiftmoneyAmount){
		this.totalGiftmoneyAmount = totalGiftmoneyAmount;
	}

	/** @return	礼金总额 */
	public BigDecimal getTotalGiftmoneyAmount(){
		return totalGiftmoneyAmount;
	}

	/** @param balanceGiftmoneyAmount	礼金余额 */
	public void setBalanceGiftmoneyAmount(BigDecimal balanceGiftmoneyAmount){
		this.balanceGiftmoneyAmount = balanceGiftmoneyAmount;
	}

	/** @return	礼金余额 */
	public BigDecimal getBalanceGiftmoneyAmount(){
		return balanceGiftmoneyAmount;
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

	/** @param isMigrate	是否迁移(0:未迁移,1:已迁移) */
	public void setIsMigrate(Integer isMigrate){
		this.isMigrate = isMigrate;
	}

	/** @return	是否迁移(0:未迁移,1:已迁移) */
	public Integer getIsMigrate(){
		return isMigrate;
	}

	/** @param isDeleted	是否删除(0:未删除,1:已删除) */
	public void setIsDeleted(Integer isDeleted){
		this.isDeleted = isDeleted;
	}

	/** @return	是否删除(0:未删除,1:已删除) */
	public Integer getIsDeleted(){
		return isDeleted;
	}

	/** @param lastOperatorId	最后操作人标识 */
	public void setLastOperatorId(Integer lastOperatorId){
		this.lastOperatorId = lastOperatorId;
	}

	/** @return	最后操作人标识 */
	public Integer getLastOperatorId(){
		return lastOperatorId;
	}

}