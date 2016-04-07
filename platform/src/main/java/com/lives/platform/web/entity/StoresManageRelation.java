package com.lives.platform.web.entity;

public class StoresManageRelation {
    private Integer storesManageRelationId;

    private Integer itemid1;

    private Integer itemid2;

    private Integer isValid;

    private String createTime;

    public Integer getStoresManageRelationId() {
        return storesManageRelationId;
    }

    public void setStoresManageRelationId(Integer storesManageRelationId) {
        this.storesManageRelationId = storesManageRelationId;
    }

    public Integer getItemid1() {
        return itemid1;
    }

    public void setItemid1(Integer itemid1) {
        this.itemid1 = itemid1;
    }

    public Integer getItemid2() {
        return itemid2;
    }

    public void setItemid2(Integer itemid2) {
        this.itemid2 = itemid2;
    }

    public Integer getTemplate() {
        return isValid;
    }

    public void setTemplate(Integer template) {
        this.isValid = template;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}