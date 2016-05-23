package com.zefun.web.entity;

/**
 * 商城设置选项
* @author 高国藩
* @date 2016年5月21日 下午6:48:58
 */
public class StoreShop {
    
    /***/
    private Integer sId;
    /***/
    private Integer storeId;
    /***/
    private String adsense;
    /***/
    private String newArrival;
    /***/
    private String bestSellers;
    /***/
    private Integer isDeleted;
    
    /**
     * sd
    * @author 高国藩
    * @date 2016年5月21日 下午6:49:26
    * @return d
     */
    public Integer getsId() {
        return sId;
    }
    /**
     * d
    * @author 高国藩
    * @date 2016年5月21日 下午6:49:39
    * @param sId d
     */
    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getAdsense() {
        return adsense;
    }

    public void setAdsense(String adsense) {
        this.adsense = adsense == null ? null : adsense.trim();
    }

    public String getNewArrival() {
        return newArrival;
    }

    public void setNewArrival(String newArrival) {
        this.newArrival = newArrival == null ? null : newArrival.trim();
    }

    public String getBestSellers() {
        return bestSellers;
    }

    public void setBestSellers(String bestSellers) {
        this.bestSellers = bestSellers == null ? null : bestSellers.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}