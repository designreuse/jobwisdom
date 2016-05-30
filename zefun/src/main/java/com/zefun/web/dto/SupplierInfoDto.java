package com.zefun.web.dto;

import java.util.List;

import com.zefun.web.entity.GoodsBrand;

public class SupplierInfoDto {

    /** 供应商标识 */
    private Integer supplierId;
    
    /** 供应商名称 */
    private String supplierName;

    /** 门店标识 */
    private Integer storeId;

    /** 经营品牌 */
    private String supplyBrand;
    
    /** 经营品牌描述 */
    private String supplyBrandStr;

    /** 供货类别 */
    private String supplyCategory;
    
    /** 供货类别描述 */
    private String supplyCategoryStr;

    /** 联系人 */
    private String linkName;

    /** 手机号 */
    private String linkPhone;

    /** 30天销售量 */
    private Integer thirtySales;

    /** 创建时间 */
    private String createTime;

    /** 修改时间 */
    private String updateTime;

    /** 是否删除(0:未删除,1:已删除) */
    private Integer isDeleted;

    /** 最后操作人标识 */
    private Integer lastOperatorId;
    
    /** 企业代号 */
    private String storeAccount;
    
    /** 品牌列表 */
    private List<GoodsBrand> brands;

    public List<GoodsBrand> getBrands() {
        return brands;
    }

    public void setBrands(List<GoodsBrand> brands) {
        this.brands = brands;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getSupplyBrand() {
        return supplyBrand;
    }

    public void setSupplyBrand(String supplyBrand) {
        this.supplyBrand = supplyBrand;
    }

    public String getSupplyBrandStr() {
        return supplyBrandStr;
    }

    public void setSupplyBrandStr(String supplyBrandStr) {
        this.supplyBrandStr = supplyBrandStr;
    }

    public String getSupplyCategory() {
        return supplyCategory;
    }

    public void setSupplyCategory(String supplyCategory) {
        this.supplyCategory = supplyCategory;
    }

    public String getSupplyCategoryStr() {
        return supplyCategoryStr;
    }

    public void setSupplyCategoryStr(String supplyCategoryStr) {
        this.supplyCategoryStr = supplyCategoryStr;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public Integer getThirtySales() {
        return thirtySales;
    }

    public void setThirtySales(Integer thirtySales) {
        this.thirtySales = thirtySales;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    public String getStoreAccount() {
        return storeAccount;
    }

    public void setStoreAccount(String storeAccount) {
        this.storeAccount = storeAccount;
    }
    
    
}
