package com.zefun.web.dto;

/**
 * 移动端商城展示
* @author 高国藩
* @date 2016年5月14日 下午12:31:59
 */
public class GoodsInfoCatagoryDto {

    /**门店*/
    private Integer storeId;
    /**门店*/
    private Integer categoryId;
    /**门店*/
    private String categoryName;
    /**门店*/
    private java.util.List<GoodsBaseDto> goodsInfos;
    
    public Integer getStoreId() {
        return storeId;
    }
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
 
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public java.util.List<GoodsBaseDto> getGoodsInfos() {
        return goodsInfos;
    }
    public void setGoodsInfos(java.util.List<GoodsBaseDto> goodsInfos) {
        this.goodsInfos = goodsInfos;
    }
     
}
