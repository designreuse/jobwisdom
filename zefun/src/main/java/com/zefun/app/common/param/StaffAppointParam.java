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
public class StaffAppointParam extends BaseParam {
	/**
	 * 预约类型(1:预约中，2:已确认，3:已取消)
	 */
	@ApiModelProperty(value="预约类型(1:预约中，2:已确认，3:已取消)", required=true)
    private int appointType;
	/**
	 * businessType   业务类型(1:会员,2:员工)
	 */
	@ApiModelProperty(value="业务类型(1:会员,2:员工)", required=true)
	private int businessType;
	/**  
	 * appointType.  
	 * @return  the appointType  
	 */
	public int getAppointType() {
		return appointType;
	}
	/**  
	 * appointType.  
	 * @param   appointType    the appointType to set   
	 */
	public void setAppointType(int appointType) {
		this.appointType = appointType;
	}
	/**  
	 * businessType.  
	 * @return  the businessType  
	 */
	public int getBusinessType() {
		return businessType;
	}
	/**  
	 * businessType.  
	 * @param   businessType    the businessType to set   
	 */
	public void setBusinessType(int businessType) {
		this.businessType = businessType;
	}	
}
