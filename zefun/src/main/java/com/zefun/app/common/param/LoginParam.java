package com.zefun.app.common.param;
import com.wordnik.swagger.annotations.ApiModelProperty;
   /**
    * 
    * ClassName: LoginParam  
    * Function: TODO ADD FUNCTION.  
    * Reason: TODO ADD REASON(可选).  
    * date: 2016年3月22日 下午3:36:44  
    *  
    * @author michael 
    * @version   
    * @since JDK 1.8
    */
public class LoginParam {
    /**
     * userName 用户名字
     */
	@ApiModelProperty(value="用户名字", required=true)
    private String userName;
	/**
	 * passWord 用户密码
	 */
	@ApiModelProperty(value="用户密码", required=true)
    private String passWord;
	/**
	 * deviceType 设备类型
	 */
	@ApiModelProperty(value="设备类型(android、ios、others)", required=true)
    private String deviceType;
	/**
	 * deviceToken 推送标识
	 */
	@ApiModelProperty(value="推送标识", required=true)
    private String deviceToken;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
}
