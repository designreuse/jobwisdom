package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2016年01月06日 PM 15:40:05
 */
public class StoreWechat {
	/** 门店ID */
	private Integer storeId;

	/** 公众号原始id */
	private String wechatId;

	/** 公众号应用ID */
	private String wechatAppid;

	/** 公众号应用密钥 */
	private String wechatAppsecret;

	/** 客户预约申请通知 */
	private String tmAppointApply;

	/** 客户预约结果通知 */
	private String tmAppointResult;

	/** 客户预约到时提醒 */
	private String tmAppointRemind;

	/** 客户充值结果提醒 */
	private String tmChargeResult;

	/** 客户结账信息通知 */
	private String tmPaymentInfo;

	/** 员工服务移交通知 */
	private String tmServiceTurn;

	/** 优惠券到期提醒 */
	private String tmCouponOverdue;

	/** @param storeId	门店ID */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店ID */
	public Integer getStoreId(){
		return storeId;
	}

	/** @param wechatId	公众号原始id */
	public void setWechatId(String wechatId){
		this.wechatId = wechatId;
	}

	/** @return	公众号原始id */
	public String getWechatId(){
		return wechatId;
	}

	/** @param wechatAppid	公众号应用ID */
	public void setWechatAppid(String wechatAppid){
		this.wechatAppid = wechatAppid;
	}

	/** @return	公众号应用ID */
	public String getWechatAppid(){
		return wechatAppid;
	}

	/** @param wechatAppsecret	公众号应用密钥 */
	public void setWechatAppsecret(String wechatAppsecret){
		this.wechatAppsecret = wechatAppsecret;
	}

	/** @return	公众号应用密钥 */
	public String getWechatAppsecret(){
		return wechatAppsecret;
	}

	/** @param tmAppointApply	客户预约申请通知 */
	public void setTmAppointApply(String tmAppointApply){
		this.tmAppointApply = tmAppointApply;
	}

	/** @return	客户预约申请通知 */
	public String getTmAppointApply(){
		return tmAppointApply;
	}

	/** @param tmAppointResult	客户预约结果通知 */
	public void setTmAppointResult(String tmAppointResult){
		this.tmAppointResult = tmAppointResult;
	}

	/** @return	客户预约结果通知 */
	public String getTmAppointResult(){
		return tmAppointResult;
	}

	/** @param tmAppointRemind	客户预约到时提醒 */
	public void setTmAppointRemind(String tmAppointRemind){
		this.tmAppointRemind = tmAppointRemind;
	}

	/** @return	客户预约到时提醒 */
	public String getTmAppointRemind(){
		return tmAppointRemind;
	}

	/** @param tmChargeResult	客户充值结果提醒 */
	public void setTmChargeResult(String tmChargeResult){
		this.tmChargeResult = tmChargeResult;
	}

	/** @return	客户充值结果提醒 */
	public String getTmChargeResult(){
		return tmChargeResult;
	}

	/** @param tmPaymentInfo	客户结账信息通知 */
	public void setTmPaymentInfo(String tmPaymentInfo){
		this.tmPaymentInfo = tmPaymentInfo;
	}

	/** @return	客户结账信息通知 */
	public String getTmPaymentInfo(){
		return tmPaymentInfo;
	}

	/** @param tmServiceTurn	员工服务移交通知 */
	public void setTmServiceTurn(String tmServiceTurn){
		this.tmServiceTurn = tmServiceTurn;
	}

	/** @return	员工服务移交通知 */
	public String getTmServiceTurn(){
		return tmServiceTurn;
	}

	/** @param tmCouponOverdue	优惠券到期提醒 */
	public void setTmCouponOverdue(String tmCouponOverdue){
		this.tmCouponOverdue = tmCouponOverdue;
	}

	/** @return	优惠券到期提醒 */
	public String getTmCouponOverdue(){
		return tmCouponOverdue;
	}

}