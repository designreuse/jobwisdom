package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 疗程项目提成Dto
* @author 王大爷
* @date 2015年9月25日 下午2:04:14
 */
public class ComboStatementDto {

    /**疗程Id */
    private Integer comboId;
    
    /**疗程名字*/
    private String comboName;
    
    /** 部门标识*/
    private Integer deptId;
    
    /** 疗程销售总价*/
    private BigDecimal comboSaleTatailPrice;
    
    /** 销售次数*/
    private Integer salesCount;
    
    /** 销售人数*/
    private Integer salesPeople;
    
    /** 是否有时间限制(0:否,1:是)*/
    private Integer standard;
    
    /** 新增购买人次*/
    private Integer newAddTime;
    
    /** 有效个数*/
    private Integer validNumber;
    
    /** 新增增使用次数*/
    private Integer newUseTime;
    
    /** 项目消费情况*/
    private List<ComboStatementProjectDto> statementProjectDtoList;
    
    
    
    
    public Integer getNewUseTime() {
		return newUseTime;
	}

	public void setNewUseTime(Integer newUseTime) {
		this.newUseTime = newUseTime;
	}

	public Integer getValidNumber() {
        return validNumber;
    }

    public void setValidNumber(Integer validNumber) {
        this.validNumber = validNumber;
    }

    public Integer getNewAddTime() {
        return newAddTime;
    }

    public void setNewAddTime(Integer newAddTime) {
        this.newAddTime = newAddTime;
    }

    public List<ComboStatementProjectDto> getStatementProjectDtoList() {
        return statementProjectDtoList;
    }

    public void setStatementProjectDtoList(
            List<ComboStatementProjectDto> statementProjectDtoList) {
        this.statementProjectDtoList = statementProjectDtoList;
    }

    public Integer getComboId() {
        return comboId;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public BigDecimal getComboSaleTatailPrice() {
        return comboSaleTatailPrice;
    }

    public void setComboSaleTatailPrice(BigDecimal comboSaleTatailPrice) {
        this.comboSaleTatailPrice = comboSaleTatailPrice;
    }

    public Integer getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    public Integer getSalesPeople() {
        return salesPeople;
    }

    public void setSalesPeople(Integer salesPeople) {
        this.salesPeople = salesPeople;
    }

    public Integer getStandard() {
        return standard;
    }

    public void setStandard(Integer standard) {
        this.standard = standard;
    }
    
}
