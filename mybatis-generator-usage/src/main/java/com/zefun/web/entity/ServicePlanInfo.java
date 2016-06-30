package com.zefun.web.entity;

public class ServicePlanInfo {
    /** ���� */
    private Integer sId;

    /** ���Ͷ���ʽ(1:�ȼ� 2:��Ա����) */
    private Integer sendMemberType;

    /** �ȼ�������ID */
    private Integer memberId;

    /** Ա��ID */
    private Integer employeeId;

    /** �������� */
    private String theme;

    /** ����ʱ�� */
    private String serviceTime;

    /** ������Ŀ���� */
    private String serviceProjectName;

    /** ����ʱ�� */
    private String topicTime;

    /** �ŵ�ID */
    private Integer storeId;

    /** �Ƿ����(0������, 1����) */
    private Integer isSms;

    /** �Ƿ��Ѿ�����(0:δ���� 1:������) */
    private Integer isDeleted;

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public Integer getSendMemberType() {
        return sendMemberType;
    }

    public void setSendMemberType(Integer sendMemberType) {
        this.sendMemberType = sendMemberType;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime == null ? null : serviceTime.trim();
    }

    public String getServiceProjectName() {
        return serviceProjectName;
    }

    public void setServiceProjectName(String serviceProjectName) {
        this.serviceProjectName = serviceProjectName == null ? null : serviceProjectName.trim();
    }

    public String getTopicTime() {
        return topicTime;
    }

    public void setTopicTime(String topicTime) {
        this.topicTime = topicTime == null ? null : topicTime.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getIsSms() {
        return isSms;
    }

    public void setIsSms(Integer isSms) {
        this.isSms = isSms;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}