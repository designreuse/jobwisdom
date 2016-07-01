package com.zefun.web.entity;

/**
 * 服务计划
* @author 高国藩
* @date 2016年6月30日 下午5:43:13
 */
public class ServicePlanInfo {
    /** 主键 */
    private Integer sId;

    /** 发送对象方式(1:等级 2:会员分组) */
    private Integer sendMemberType;

    /** 等级或分组的ID */
    private Integer memberId;

    /** 员工ID */
    private Integer employeeId;

    /** 主题名称 */
    private String theme;

    /** 服务时间 */
    private String serviceTime;

    /** 服务项目名称 */
    private String serviceProjectName;

    /** 推送时间 */
    private String topicTime;

    /** 门店ID */
    private Integer storeId;

    /** 是否短信(0不推送, 1推送) */
    private Integer isSms;

    /** 是否已经推送(0:未推送 1:已推送) */
    private Integer isDeleted;
    
    /** 员工名称*/
    private String name;
    
    /** 会员分组(卡)名称*/
    private String memberName;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     * 获取ID
    * @author 高国藩
    * @date 2016年6月30日 下午5:42:46
    * @return  sId
     */
    public Integer getsId() {
        return sId;
    }

    /**
     * 设置属性
    * @author 高国藩
    * @date 2016年6月30日 下午5:42:57
    * @param sId  sId
     */
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