package com.zefun.web.entity;

import java.math.BigDecimal;

public class AccountGoods {
    /** ���� */
    private Integer goodsId;

    /** ��Ӧ�� */
    private Integer supplierId;

    /** Ʒ�� */
    private Integer brandId;

    /** ��Ʒ���� */
    private String goodsName;

    /** ��� */
    private String goodsCodeSuffix;

    /** �Ƿ���Ʒ(0:��,1:��) */
    private Integer isSellProduct;

    /** �ɱ��� */
    private BigDecimal costPrice;

    /** ��ע��Ϣ */
    private String goodsDesc;

    /** ��ҵ���� */
    private String storeAccount;

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
}