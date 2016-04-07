package com.zefun.app.common.param;
import com.wordnik.swagger.annotations.ApiModelProperty;
/**
 * 
 * ClassName: BaseParam  
 * date: 2016年3月22日 下午4:08:34  
 * @author michael  
 * @version   
 * @since JDK 1.8
 */
public class BaseParam {
	/**
	 * 定义商店的id属性
	 */
	@ApiModelProperty(value="商店id", required=true)
    private int storeId;
	/**
	 * 定义员工的id属性
	 */
	@ApiModelProperty(value="员工id", required=true)
    private int employeeId;

	/**  
	 * storeId.  
	 *  
	 * @return  the storeId  
	 * @since   JDK 1.8 
	 */
	public int getStoreId() {
		return storeId;
	}

	/**  
	 * storeId.  
	 *  
	 * @param   storeId    the storeId to set  
	 * @since   JDK 1.8  
	 */
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	/**  
	 * employeeId.  
	 * @return  the employeeId  
	 */
	public int getEmployeeId() {
		return employeeId;
	}
	/**  
	 * employeeId.  
	 * @param   employeeId    the employeeId to set   
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
}
