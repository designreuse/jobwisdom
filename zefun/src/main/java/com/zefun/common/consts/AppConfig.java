package com.zefun.common.consts;  
/**  
 * ClassName: AppConfig    
 * date: 2016年3月23日 下午3:44:58  
 * @author michael
 * @version   
 * @since JDK 1.8  
 */
public interface AppConfig {
	/**
	 * 预约类型(1:预约中,2:已确认,3:已取消)
	 */
	int APP_APPOINT_TYPE_APPOINTING = 1;
	/**2:已确认*/
	int APP_APPOINT_TYPE_APPOINTED = 2;
	/**3:已取消*/
	int APP_APPOINT_TYPE_CANCEL = 3;
	
	/**
	 * 预约状态(1:预约中,2:已确认,3:已服务,4:已取消,5:已拒绝)
	 */
	int APP_APPOINT_STATUS_APPOINTING=1;
	/**已确认*/
	int APP_APPOINT_STATUS_CONFIRED = 2;
	/**已服务*/
	int APP_APPOINT_STATUS_SERVICED = 3;
	/**已取消*/
	int APP_APPOINT_STATUS_CANCELED = 4;
	/**已拒绝*/
	int APP_APPOINT_STATUS_REJECTED = 5;
}
  
