package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 部门现金收入分布封装类
* @author 乐建建
* @date 2016年2月21日 下午1:07:07 
*/
public class DeptCashIncomeDto {
    
    /**部门id*/
    private Integer deptId;
    /**项目收入*/
    private BigDecimal projectAmt;
    /**商品收入*/
    private BigDecimal goodsAmt;
    /**套餐收入*/
    private BigDecimal comboAmt;
    /**开卡收入*/
    private BigDecimal openCardAmt;
    /**充值收入*/
    private BigDecimal chargedAmt;
    /**升级收入*/
    private BigDecimal upgradeAmt;
    /**部门现金总收入*/
    private BigDecimal totalAmt;
    
    /**
    * @author 乐建建
    * @date 2016年2月21日 下午4:31:55
    * @param deptId 部门id
    * @param projectAmt 项目现金
    * @param goodsAmt 商品现金
    * @param comboAmt 套餐现金
    * @param openCardAmt 开卡现金
    * @param chargedAmt 充值现金
    * @param upgradeAmt 升级现金
    * @param totalAmt  总收入
    */
    public DeptCashIncomeDto(Integer deptId, BigDecimal projectAmt,
            BigDecimal goodsAmt, BigDecimal comboAmt, BigDecimal openCardAmt,
            BigDecimal chargedAmt, BigDecimal upgradeAmt, BigDecimal totalAmt) {
        this.deptId = deptId;
        this.projectAmt = projectAmt;
        this.goodsAmt = goodsAmt;
        this.comboAmt = comboAmt;
        this.openCardAmt = openCardAmt;
        this.chargedAmt = chargedAmt;
        this.upgradeAmt = upgradeAmt;
        this.totalAmt = totalAmt;
    }
    /**
    * @author 乐建建
    * @date 2016年2月21日 下午4:32:44 
    */
    public DeptCashIncomeDto() {
        super();
    }
    public BigDecimal getTotalAmt() {
        return totalAmt;
    }
    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }
    public Integer getDeptId() {
        return deptId;
    }
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
    public BigDecimal getProjectAmt() {
        return projectAmt;
    }
    public void setProjectAmt(BigDecimal projectAmt) {
        this.projectAmt = projectAmt;
    }
    public BigDecimal getGoodsAmt() {
        return goodsAmt;
    }
    public void setGoodsAmt(BigDecimal goodsAmt) {
        this.goodsAmt = goodsAmt;
    }
    public BigDecimal getComboAmt() {
        return comboAmt;
    }
    public void setComboAmt(BigDecimal comboAmt) {
        this.comboAmt = comboAmt;
    }
    public BigDecimal getOpenCardAmt() {
        return openCardAmt;
    }
    public void setOpenCardAmt(BigDecimal openCardAmt) {
        this.openCardAmt = openCardAmt;
    }
    public BigDecimal getChargedAmt() {
        return chargedAmt;
    }
    public void setChargedAmt(BigDecimal chargedAmt) {
        this.chargedAmt = chargedAmt;
    }
    public BigDecimal getUpgradeAmt() {
        return upgradeAmt;
    }
    public void setUpgradeAmt(BigDecimal upgradeAmt) {
        this.upgradeAmt = upgradeAmt;
    }
}
