package com.zefun.web.entity;

public class RevenueCategories {
    /** ���� */
    private Integer rId;

    /** 1:���� 2:֧�� */
    private Integer rType;

    /** ������� */
    private String rName;

    /** �޸�ʱ�� */
    private String createTime;

    /** �����˱�ʾ */
    private Integer personnelId;

    /** �ŵ����� */
    private Integer storeId;

    /** �Ƿ�ɾ�� */
    private Integer isDeleted;

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public Integer getrType() {
        return rType;
    }

    public void setrType(Integer rType) {
        this.rType = rType;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName == null ? null : rName.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Integer getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(Integer personnelId) {
        this.personnelId = personnelId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}