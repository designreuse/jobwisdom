package com.lives.platform.web.entity;

public class StoresManage {
    private Integer storesManageId;

    private String storesManageName;

    private Integer hqStoresId;

    private String createTime;

    public Integer getStoresManageId() {
        return storesManageId;
    }

    public void setStoresManageId(Integer storesManageId) {
        this.storesManageId = storesManageId;
    }

    public String getStoresManageName() {
        return storesManageName;
    }

    public void setStoresManageName(String storesManageName) {
        this.storesManageName = storesManageName;
    }

    public Integer getHqStoresId() {
        return hqStoresId;
    }

    public void setHqStoresId(Integer hqStoresId) {
        this.hqStoresId = hqStoresId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}