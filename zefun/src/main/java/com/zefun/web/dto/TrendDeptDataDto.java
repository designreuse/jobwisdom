package com.zefun.web.dto;

import java.util.List;

/**
* @author 乐建建
* @date 2016年1月19日 下午7:11:28 
* 部门趋势汇总数据
*/
public class TrendDeptDataDto {

    /**部门id*/
    private Integer deptId;
    
    /**为营业汇总添加*/
    private String name;
    
    /**订单类型*/
    private Integer orderType;

    /**日期对应的门店下部门汇总数据*/
    private List<DeptSummaryByDayDto> trendDeptData;

    /**
    * @author 乐建建
    * @date 2016年1月20日 下午5:57:29
    * 默认构造函数 
    */
    public TrendDeptDataDto() {
        super();
    }    
    
    /**
    * @author 乐建建
    * @date 2016年1月20日 下午5:57:45
    * @param deptId 部门id
    * @param trendDeptData 日期对应的门店下部门汇总数据
    */
    public TrendDeptDataDto(Integer deptId,
            List<DeptSummaryByDayDto> trendDeptData) {
        super();
        this.deptId = deptId;
        this.trendDeptData = trendDeptData;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public String getName() {
        return name;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public List<DeptSummaryByDayDto> getTrendDeptData() {
        return trendDeptData;
    }


    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public void setTrendDeptData(List<DeptSummaryByDayDto> trendDeptData) {
        this.trendDeptData = trendDeptData;
    }
}
