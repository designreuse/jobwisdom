package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.List;

/**
* @author 乐建建
* @date 2016年1月22日 下午7:49:33 
* 部门下商品汇总
*/
public class DeptGoodSalesSummaryDto {
    
    /**部门下商品明细列表*/
    private List<GoodSalesSummaryDto> deptGoodsInfo;
    
    /**部门id*/
    private Integer deptId;
    
    /**部门名称*/
    private String deptName;
    
    /**部门商品销售汇总*/
    private BigDecimal deptTotalAmt;
    
    /**部门商品销售量*/
    private Integer deptTotalCnt;

    public List<GoodSalesSummaryDto> getDeptGoodsInfo() {
        return deptGoodsInfo;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public BigDecimal getDeptTotalAmt() {
        return deptTotalAmt;
    }

    public Integer getDeptTotalCnt() {
        return deptTotalCnt;
    }

    public void setDeptGoodsInfo(List<GoodSalesSummaryDto> deptGoodsInfo) {
        this.deptGoodsInfo = deptGoodsInfo;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setDeptTotalAmt(BigDecimal deptTotalAmt) {
        this.deptTotalAmt = deptTotalAmt;
    }

    public void setDeptTotalCnt(Integer deptTotalCnt) {
        this.deptTotalCnt = deptTotalCnt;
    }
}
