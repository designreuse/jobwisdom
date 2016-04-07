/**  
 * Project Name:zefun  
 * File Name:UserInfoDTO.java  
 * Package Name:com.zefun.app.common.dto  
 * Date:2016年3月18日下午12:06:00  
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
/**  
 * Project Name:zefun  
 * File Name:UserInfoDTO.java  
 * Package Name:com.zefun.app.common.dto  
 * Date:2016年3月18日下午12:06:00  
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.  
 *  
 */  
  
package com.zefun.app.common.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**  
 * ClassName:UserInfoDTO  
 * Function: TODO ADD FUNCTION.  
 * Reason:   TODO ADD REASON.  
 * Date:     2016年3月18日 下午12:06:00  
 * @author   maywant  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
/**  
 * ClassName: UserInfoDTO  
 * Function: TODO ADD FUNCTION.  
 * Reason: TODO ADD REASON(可选).  
 * date: 2016年3月18日 下午12:06:00  
 *  
 * @author maywant  
 * @version   
 * @since JDK 1.8  
 */
public class UserInfoDTO {
	/** 员工标识 */
	@ApiModelProperty(value="员工标识", required=true)
    private Integer userId;

    /** 角色标识 */
	@ApiModelProperty(value="角色标识", required=true)
    private Integer roleId;

    /** 用户账号 */
	@ApiModelProperty(value="用户账号", required=true)
    private String userName;

    
    /** 用户所在商店*/
	@ApiModelProperty(value="用户所在商店", required=true)
    private int storeId;

	/**  
	 * userId.  
	 *  
	 * @return  the userId  
	 * @since   JDK 1.8 
	 */
	public Integer getUserId() {
		return userId;
	}

	/**  
	 * userId.  
	 *  
	 * @param   userId    the userId to set  
	 * @since   JDK 1.8  
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**  
	 * roleId.  
	 *  
	 * @return  the roleId  
	 * @since   JDK 1.8 
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**  
	 * roleId.  
	 *  
	 * @param   roleId    the roleId to set  
	 * @since   JDK 1.8  
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**  
	 * userName.  
	 *  
	 * @return  the userName  
	 * @since   JDK 1.8 
	 */
	public String getUserName() {
		return userName;
	}

	/**  
	 * userName.  
	 *  
	 * @param   userName    the userName to set  
	 * @since   JDK 1.8  
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**  
	 * storeId.  
	 *  
	 * @return  the storeId  
	 * @since   JDK 1.8 
	 */
	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
}
  
