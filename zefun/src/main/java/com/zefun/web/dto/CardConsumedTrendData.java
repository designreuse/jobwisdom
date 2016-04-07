package com.zefun.web.dto;

import java.util.List;

/**
 * 卡消费趋势数据
* @author 乐建建
* @date 2016年2月23日 上午11:22:49 
*/
public class CardConsumedTrendData {
    
    /**订单类型*/
    private int orderType;
    /**趋势数据*/
    private List<DeptSummaryByDayDto> trends;

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public List<DeptSummaryByDayDto> getTrends() {
        return trends;
    }

    public void setTrends(List<DeptSummaryByDayDto> trends) {
        this.trends = trends;
    }   
    
}
