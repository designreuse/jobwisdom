package com.zefun.web.dto;

/**
 * 部门现金收入
* @author 乐建建
* @date 2016年2月21日 下午1:50:13 
*/
public class DeptCashIncome {
    /**部门id*/
    private Integer deptId;
    /**部门名称*/
    private String deptName;
    /**部门封装类*/
    private DeptCashIncomeDto deptDto;
    
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
    public DeptCashIncomeDto getDeptDto() {
        return deptDto;
    }
    public void setDeptDto(DeptCashIncomeDto deptDto) {
        this.deptDto = deptDto;
    }
}
