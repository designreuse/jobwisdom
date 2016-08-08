package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 企业-商品信息
* @author 高国藩
* @date 2016年5月28日 下午6:34:07
 */
public class AccountGoods {
    /** 主键 */
    private Integer goodsId;

    /** 供应商 */
    private Integer supplierId;

    /** 品牌 */
    private Integer brandId;

    /** 商品名称 */
    private String goodsName;

    /** 编号 */
    private String goodsCodeSuffix;

    /** 是否卖品(0:否,1:是) */
    private Integer isSellProduct;

    /** 成本价 */
    private BigDecimal costPrice;

    /** 备注信息 */
    private String goodsDesc;

    /** 企业代号 */
    private String storeAccount;
    
    /** 是否停止 */
    private Integer isDeleted;
    
    /** 供应商 */
    private String supplierName;

    /** 品牌 */
    private String brandName;
    
    /** 单位*/
    private String unit;

    
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsCodeSuffix() {
        return goodsCodeSuffix;
    }

    public void setGoodsCodeSuffix(String goodsCodeSuffix) {
        this.goodsCodeSuffix = goodsCodeSuffix == null ? null : goodsCodeSuffix.trim();
    }

    public Integer getIsSellProduct() {
        return isSellProduct;
    }

    public void setIsSellProduct(Integer isSellProduct) {
        this.isSellProduct = isSellProduct;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc == null ? null : goodsDesc.trim();
    }

    public String getStoreAccount() {
        return storeAccount;
    }

    public void setStoreAccount(String storeAccount) {
        this.storeAccount = storeAccount == null ? null : storeAccount.trim();
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    
}