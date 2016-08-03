package com.zefun.web.dto;

import java.math.BigDecimal;



/**
 * 
* @author 骆峰
* @date 2016年8月3日 下午4:02:36
 */
public class EmployeeInfoDto  {
	
    /** 提成员工名称 */
    private String employeeName;
      
    
    /** 提成员工名称 */
    private Integer employeeCode;
      
      /** 提成员工标识 */
    private Integer employeeId;
    
          /** 提成金额 */
    private BigDecimal commissionAmount;
    
          
      /** 订单类型(1:项目,2:商品,3:疗程,4、开卡充值) */
    private BigDecimal orderType;
    
          /** 提成金额 */
    private BigDecimal baseSalaries;
      
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Integer employeeCode) {
        this.employeeCode = employeeCode;
    }

    public BigDecimal getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(BigDecimal commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public BigDecimal getOrderType() {
        return orderType;
    }

    public void setOrderType(BigDecimal orderType) {
        this.orderType = orderType;
    }

    public BigDecimal getBaseSalaries() {
        return baseSalaries;
    }

    public void setBaseSalaries(BigDecimal baseSalaries) {
        this.baseSalaries = baseSalaries;
    }

}