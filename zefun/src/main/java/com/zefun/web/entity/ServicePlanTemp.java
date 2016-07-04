package com.zefun.web.entity;

/**
 * 服务计划模板
* @author 高国藩
* @date 2016年7月1日 下午3:42:39
 */
public class ServicePlanTemp {
    
    /** 主键 */
    private Integer tId;

    /** 模板名称 */
    private String tempName;

    /** 主题 */
    private String theme;

    /** 推送在计划启动的天数 */
    private Integer topicDay;

    /** 推送在计划启动的小时 */
    private Integer topicHoure;

    /** 服务的项目名称 */
    private String serviceProjectName;

    /** 服务的启动的天数 */
    private Integer serviceDay;

    /** 服务的启动的小时 */
    private Integer serviceHoure;

    /** 门店信息 */
    private Integer storeId;

    /** 是否短信推送 */
    private Integer isSms;

    /** 是否删除 */
    private Integer isDeleted;

    /**
     * gettId
    * @author 高国藩
    * @date 2016年7月1日 下午3:42:56
    * @return gettId
     */
    public Integer gettId() {
        return tId;
    }

    /**
     * gettId
    * @author 高国藩
    * @date 2016年7月1日 下午3:43:02
    * @param tId tId
     */
    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName == null ? null : tempName.trim();
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    public Integer getTopicDay() {
        return topicDay;
    }

    public void setTopicDay(Integer topicDay) {
        this.topicDay = topicDay;
    }

    public Integer getTopicHoure() {
        return topicHoure;
    }

    public void setTopicHoure(Integer topicHoure) {
        this.topicHoure = topicHoure;
    }

    public String getServiceProjectName() {
        return serviceProjectName;
    }

    public void setServiceProjectName(String serviceProjectName) {
        this.serviceProjectName = serviceProjectName == null ? null
                : serviceProjectName.trim();
    }

    public Integer getServiceDay() {
        return serviceDay;
    }

    public void setServiceDay(Integer serviceDay) {
        this.serviceDay = serviceDay;
    }

    public Integer getServiceHoure() {
        return serviceHoure;
    }

    public void setServiceHoure(Integer serviceHoure) {
        this.serviceHoure = serviceHoure;
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