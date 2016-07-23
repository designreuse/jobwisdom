package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.List;

/**
* @author 乐建建
* @date 2016年1月20日 下午8:16:00 
* 部门下疗程汇总
*/
public class DeptComboSummaryDto {
    /**部门id*/
    private Integer deptId;
    /**部门名称*/
    private String deptName;
    /**部门下疗程列表*/
    private List<ComboSummaryDto> combosBelongToDept;
    /**部门疗程销售额汇总*/
    private BigDecimal deptComboSummary;
    /**部门下疗程销量*/
    private Integer deptComboSales;
    
    public Integer getDeptComboSales() {
        return deptComboSales;
    }
    public void setDeptComboSales(Integer deptComboSales) {
        this.deptComboSales = deptComboSales;
    }
    public Integer getDeptId() {
        return deptId;
    }
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public List<ComboSummaryDto> getCombosBelongToDept() {
        return combosBelongToDept;
    }
    public void setCombosBelongToDept(List<ComboSummaryDto> combosBelongToDept) {
        this.combosBelongToDept = combosBelongToDept;
    }
    public BigDecimal getDeptComboSummary() {
        return deptComboSummary;
    }
    public void setDeptComboSummary(BigDecimal deptComboSummary) {
        this.deptComboSummary = deptComboSummary;
    }
    
}
