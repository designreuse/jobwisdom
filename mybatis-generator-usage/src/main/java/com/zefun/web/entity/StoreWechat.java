package com.zefun.web.entity;

public class StoreWechat {
    /** �ŵ�ID*/
    private Integer storeId;

    /** ���ں�ԭʼid*/
    private String wechatId;

    /** ���ں�Ӧ��ID*/
    private String wechatAppid;

    /** ���ں�Ӧ����Կ*/
    private String wechatAppsecret;

    /** �ͻ�ԤԼ����֪ͨ*/
    private String tmAppointApply;

    /** �ͻ�ԤԼ���֪ͨ*/
    private String tmAppointResult;

    /** �ͻ�ԤԼ��ʱ����*/
    private String tmAppointRemind;

    /** �ͻ���ֵ�������*/
    private String tmChargeResult;

    /** �ͻ�������Ϣ֪ͨ*/
    private String tmPaymentInfo;

    /** Ա�������ƽ�֪ͨ*/
    private String tmServiceTurn;

    /** �Ż�ȯ��������*/
    private String tmCouponOverdue;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId == null ? null : wechatId.trim();
    }

    public String getWechatAppid() {
        return wechatAppid;
    }

    public void setWechatAppid(String wechatAppid) {
        this.wechatAppid = wechatAppid == null ? null : wechatAppid.trim();
    }

    public String getWechatAppsecret() {
        return wechatAppsecret;
    }

    public void setWechatAppsecret(String wechatAppsecret) {
        this.wechatAppsecret = wechatAppsecret == null ? null : wechatAppsecret.trim();
    }

    public String getTmAppointApply() {
        return tmAppointApply;
    }

    public void setTmAppointApply(String tmAppointApply) {
        this.tmAppointApply = tmAppointApply == null ? null : tmAppointApply.trim();
    }

    public String getTmAppointResult() {
        return tmAppointResult;
    }

    public void setTmAppointResult(String tmAppointResult) {
        this.tmAppointResult = tmAppointResult == null ? null : tmAppointResult.trim();
    }

    public String getTmAppointRemind() {
        return tmAppointRemind;
    }

    public void setTmAppointRemind(String tmAppointRemind) {
        this.tmAppointRemind = tmAppointRemind == null ? null : tmAppointRemind.trim();
    }

    public String getTmChargeResult() {
        return tmChargeResult;
    }

    public void setTmChargeResult(String tmChargeResult) {
        this.tmChargeResult = tmChargeResult == null ? null : tmChargeResult.trim();
    }

    public String getTmPaymentInfo() {
        return tmPaymentInfo;
    }

    public void setTmPaymentInfo(String tmPaymentInfo) {
        this.tmPaymentInfo = tmPaymentInfo == null ? null : tmPaymentInfo.trim();
    }

    public String getTmServiceTurn() {
        return tmServiceTurn;
    }

    public void setTmServiceTurn(String tmServiceTurn) {
        this.tmServiceTurn = tmServiceTurn == null ? null : tmServiceTurn.trim();
    }

    public String getTmCouponOverdue() {
        return tmCouponOverdue;
    }

    public void setTmCouponOverdue(String tmCouponOverdue) {
        this.tmCouponOverdue = tmCouponOverdue == null ? null : tmCouponOverdue.trim();
    }
}