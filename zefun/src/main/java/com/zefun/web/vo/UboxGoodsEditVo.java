package com.zefun.web.vo;

/**
 * 友宝商品编辑对象
* @author 张进军
* @date Jan 31, 2016 2:03:23 PM
 */
public class UboxGoodsEditVo {
    /** 商品名称 */
    private String goodsName;
    /** 附属图片数组 */
    private String[] imgs;
    /** 图片主图 */
    private String goodsPicture;
    /** 商品描述数组 */
    private String[] contents;
    /** 商品标识 */
    private String goodsId;
    public String getGoodsName() {
        return goodsName;
    }
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    public String[] getImgs() {
        return imgs;
    }
    public void setImgs(String[] imgs) {
        this.imgs = imgs;
    }
    public String getGoodsPicture() {
        return goodsPicture;
    }
    public void setGoodsPicture(String goodsPicture) {
        this.goodsPicture = goodsPicture;
    }
    public String[] getContents() {
        return contents;
    }
    public void setContents(String[] contents) {
        this.contents = contents;
    }
    public String getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}
