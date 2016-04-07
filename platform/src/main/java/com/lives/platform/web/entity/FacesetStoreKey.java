package com.lives.platform.web.entity;

public class FacesetStoreKey {
    private String setId;

    private Integer storeId;

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId == null ? null : setId.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}