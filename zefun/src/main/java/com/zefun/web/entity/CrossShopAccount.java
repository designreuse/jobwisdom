package com.zefun.web.entity;

import java.math.BigDecimal;

/**
* 跨店对账明细类
* @author 乐建建
* @date 2016年3月1日 下午1:45:57 
*/
public class CrossShopAccount {
    
    /**所属门店id*/
    private Integer belongStoreId;
    
    /**审核状态*/
    private Integer checkState;
    
    /**消费门店id*/
    private Integer consumedStoreId;
    
    /**跨店对账明细标识*/
    private Integer crossShopId;
    
    /**对账金额*/
    private BigDecimal reconciliationAmount;
    
    /**参考order_detail表中detail_id*/
    private String referenceDetailId;

    /**备注*/
    private String remark;

    /**流水号*/
    private String seriesId;
    
    public Integer getBelongStoreId() {
        return belongStoreId;
    }
    
    public Integer getCheckState() {
        return checkState;
    }

    public Integer getConsumedStoreId() {
        return consumedStoreId;
    }

    public Integer getCrossShopId() {
        return crossShopId;
    }


    public BigDecimal getReconciliationAmount() {
        return reconciliationAmount;
    }

    public String getReferenceDetailId() {
        return referenceDetailId;
    }

    public String getRemark() {
        return remark;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setBelongStoreId(Integer belongStoreId) {
        this.belongStoreId = belongStoreId;
    }

    public void setCheckState(Integer checkState) {
        this.checkState = checkState;
    }

    public void setConsumedStoreId(Integer consumedStoreId) {
        this.consumedStoreId = consumedStoreId;
    }

    public void setCrossShopId(Integer crossShopId) {
        this.crossShopId = crossShopId;
    }


    public void setReconciliationAmount(BigDecimal reconciliationAmount) {
        this.reconciliationAmount = reconciliationAmount;
    }

    public void setReferenceDetailId(String referenceDetailId) {
        this.referenceDetailId = referenceDetailId;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }
    
}
