package com.lives.manage.web.dto;
/**
 * 
 * 用户详情信息
 * */
public class UserInfoDto {
	
	/**用户id*/
	private Integer userId;
	/**客户id*/
	private Integer customerId;
	/**角色id*/
	private Integer roleId;
	/**用户名称*/
	private String userName;
	/**用户密码*/
	private String password;
	/**创建时间*/
	private String createTime;
	/**创建人*/
	private String creater;
	/**修改时间*/
	private String updateTime;
	/**修改人*/
	private String updater;
	/**状态*/
	private Integer isDelete;

	/**用户昵称*/
	private String nickName;
	/**用户头像*/
	private String headImg;
	/**用户生日*/
	private String birthday;
	/**用户真实姓名*/
	private String realName;
	/**用户性别*/
	private Integer sex;
	/**用户邮箱*/
	private String email;
	/**用户电话*/
    private String mobile;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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

	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public UserInfoDto(Integer userId, String userName,Integer roleId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.roleId=roleId;
	}

	

}
