package com.zefun.web.entity;

public class InitializeInFo {
    /** id */
    private Integer id;

    /** �������� */
    private String deptName;

    /** ��� */
    private Integer goodsPrice;

    /** ��֧������� */
    private String type;

    /** ��֧��ʽ */
    private String typeName;

    /** ��ע */
    private String note;

    /** ʱ�� */
    private String date;

    /** 1����2֧�� */
    private Integer incometypeType;

    /** 0����1ɾ�� */
    private Integer isdeleted;

    /** �ŵ�ʶ�� */
    private Integer storeId;

    /** �������� */
    private String priceName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public Integer getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Integer goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public Integer getIncometypeType() {
        return incometypeType;
    }

    public void setIncometypeType(Integer incometypeType) {
        this.incometypeType = incometypeType;
    }

    public Integer getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Integer isdeleted) {
        this.isdeleted = isdeleted;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName == null ? null : priceName.trim();
    }
}