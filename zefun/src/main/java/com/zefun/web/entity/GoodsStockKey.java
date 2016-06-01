package com.zefun.web.entity;

/**
 * 库存ID
* @author 高国藩
* @date 2016年6月1日 下午3:07:25
 */
public class GoodsStockKey {
    /** 主键 */
    private Integer aId;

    /** 主键 */
    private Integer storeId;

    /**
     * 地方
    * @author 高国藩
    * @date 2016年6月1日 下午3:07:32
    * @return d
     */
    public Integer getaId() {
        return aId;
    }

    /**
     * 水电费
    * @author 高国藩
    * @date 2016年6月1日 下午3:08:01
    * @param aId 第三方
     */
    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}