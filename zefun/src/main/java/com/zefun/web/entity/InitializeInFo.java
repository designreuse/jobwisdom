package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 
* @author 骆峰
* @date 2016年6月18日 上午11:54:29
 */
public class InitializeInFo {
    
    /** id */
    private Integer id;

    /** 部门名称 */
    private String deptName;

    /** 金额 */
    private  BigDecimal goodsPrice;

    /** 收支类别名称 */
    private String type;

    /** 收支方式 */
    private String typeName;

    /** 备注 */
    private String note;

    /** 时间 */
    private String date;

    /** 1收入2支出 */
    private Integer incometypeType;

    /** 0正常1删除 */
    private Integer isdeleted;

    /** 门店识别 */
    private Integer storeId;
    
    /** 消费名称 */
    private String priceName;
    
    /**  图片地址*/
    private String initializeImage;

    public String getInitializeImage() {
        return initializeImage;
    }

    public void setInitializeImage(String initializeImage) {
        this.initializeImage = initializeImage;
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName == null ? null : priceName.trim();
    }

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

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
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
}