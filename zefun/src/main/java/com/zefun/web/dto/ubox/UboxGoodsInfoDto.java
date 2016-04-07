package com.zefun.web.dto.ubox;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;

/**
 * 友宝商品基本信息传输对象
* @author 张进军
* @date Jan 28, 2016 8:06:17 PM
 */
public class UboxGoodsInfoDto {
    /** 友宝商品标识 */
    private String uboxId;

    /** 友宝商品名称 */
    private String uboxName;

    /** 友宝商品原价 */
    private Integer uboxOriginalPrice;

    /** 友宝商品售价 */
    private Integer uboxSalePrice;

    /** 友宝商品库存 */
    private Integer uboxStock;

    /** 友宝商品图片 */
    private String uboxPicture;

    /** 友宝商品描述 */
    private String uboxDesc;

    /** 友宝售卖截止时间，unix时间戳，商品不可售卖、不再可取 */
    private Integer uboxExpireTime;

    /** 商品名称 */
    private String goodsName;

    /** 商品图片集合，逗号分割 */
    private String goodsPictureArray;

    /** 商品描述 */
    private String goodsDesc;

    /** 商品销量 */
    private Integer goodsSales;
    
    /** 修改时间 */
    private String updateTime;
    
    public String getUboxId() {
        return uboxId;
    }

    public void setUboxId(String uboxId) {
        this.uboxId = uboxId;
    }

    public String getUboxName() {
        return uboxName;
    }

    public void setUboxName(String uboxName) {
        this.uboxName = uboxName;
    }

    public Integer getUboxOriginalPrice() {
        return uboxOriginalPrice;
    }

    public void setUboxOriginalPrice(Integer uboxOriginalPrice) {
        this.uboxOriginalPrice = uboxOriginalPrice;
    }

    public Integer getUboxSalePrice() {
        return uboxSalePrice;
    }

    public void setUboxSalePrice(Integer uboxSalePrice) {
        this.uboxSalePrice = uboxSalePrice;
    }

    public Integer getUboxStock() {
        return uboxStock;
    }

    public void setUboxStock(Integer uboxStock) {
        this.uboxStock = uboxStock;
    }

    public String getUboxPicture() {
        return uboxPicture;
    }

    public void setUboxPicture(String uboxPicture) {
        this.uboxPicture = uboxPicture;
    }

    public String getUboxDesc() {
        return uboxDesc;
    }

    public void setUboxDesc(String uboxDesc) {
        this.uboxDesc = uboxDesc;
    }

    public Integer getUboxExpireTime() {
        return uboxExpireTime;
    }

    public void setUboxExpireTime(Integer uboxExpireTime) {
        this.uboxExpireTime = uboxExpireTime;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPictureArray() {
        return goodsPictureArray;
    }

    public void setGoodsPictureArray(String goodsPictureArray) {
        this.goodsPictureArray = goodsPictureArray;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public Integer getGoodsSales() {
        return goodsSales;
    }

    public void setGoodsSales(Integer goodsSales) {
        this.goodsSales = goodsSales;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取商品图片数组
    * @author 张进军
    * @date Jan 28, 2016 8:26:27 PM
    * @return   商品图片数组
     */ 
    public String[] getPictureArray(){
        if (StringUtils.isNotBlank(getGoodsPictureArray())) {
            return getGoodsPictureArray().split(",");
        }
        return null;
    }
    
    /**
     * 以JSON数组的方式获取商品描述
    * @author 张进军
    * @date Jan 28, 2016 9:00:49 PM
    * @return   商品描述
     */
    public JSONArray getGoodsContentList(){
        if (StringUtils.isNotBlank(getGoodsDesc())) {
            return JSONArray.fromObject(getGoodsDesc());
        }
        return null;
    }
    
}
