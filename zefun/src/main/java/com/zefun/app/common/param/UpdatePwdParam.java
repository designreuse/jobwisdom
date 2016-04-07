package com.zefun.app.common.param;
import com.wordnik.swagger.annotations.ApiModelProperty;
   /**
    * 
    * ClassName: LoginParam   
    * date: 2016年3月22日 下午3:36:44  
    *  
    * @author michael 
    * @version   
    * @since JDK 1.8
    */
public class UpdatePwdParam extends BaseParam {
    /**
     * 定义员工旧密码
     */
	@ApiModelProperty(value="员工旧密码", required=true)
    private String oldPwd;
	/**
	 * 定义员工新密码
	 */
	@ApiModelProperty(value="员工新密码", required=true)
    private String newPwd;
	/**  
	 * oldPwd.  
	 * @return  the oldPwd  
	 */
	public String getOldPwd() {
		return oldPwd;
	}
	/**  
	 * oldPwd.  
	 * @param   oldPwd    the oldPwd to set   
	 */
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	/**  
	 * newPwd.  
	 * @return  the newPwd  
	 */
	public String getNewPwd() {
		return newPwd;
	}
	/**  
	 * newPwd.  
	 * @param   newPwd    the newPwd to set   
	 */
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
}
