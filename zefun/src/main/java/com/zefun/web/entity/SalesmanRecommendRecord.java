package com.zefun.web.entity;

/**
 * 业务员门店关联
 * @author lzc
 *
 */
public class SalesmanRecommendRecord {
	
	/** 被推荐者(门店)id */
    private Integer recommendedId;

    /** 推荐者(业务员)id */
    private Integer recommendSalesmanId;

    /** 保留字段，1为门店 */
    private Integer recommendType;

    public Integer getRecommendedId() {
        return recommendedId;
    }

    public void setRecommendedId(Integer recommendedId) {
        this.recommendedId = recommendedId;
    }

    public Integer getRecommendSalesmanId() {
        return recommendSalesmanId;
    }

    public void setRecommendSalesmanId(Integer recommendSalesmanId) {
        this.recommendSalesmanId = recommendSalesmanId;
    }

    public Integer getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(Integer recommendType) {
        this.recommendType = recommendType;
    }
}