package com.zefun.web.dto;

import java.math.BigDecimal;

/**
* @author 乐建建
* @date 2016年2月17日 上午11:14:32 
*/
public class BusinessIncomePart  extends SummaryResultDto{
    /**项目消费*/
    private BigDecimal projectConsumedAmt=new BigDecimal(0);
    /**疗程消费*/
    private BigDecimal comboConsumedAmt=new BigDecimal(0);
    /**商品消费*/
    private BigDecimal goodsConsumedAmt=new BigDecimal(0);
    /**营业收入汇总*/
    private BigDecimal totalAmt=new BigDecimal(0);
    public BigDecimal getProjectConsumedAmt() {
        return projectConsumedAmt;
    }
    public void setProjectConsumedAmt(BigDecimal projectConsumedAmt) {
        this.projectConsumedAmt = projectConsumedAmt;
    }
    public BigDecimal getComboConsumedAmt() {
        return comboConsumedAmt;
    }
    public void setComboConsumedAmt(BigDecimal comboConsumedAmt) {
        this.comboConsumedAmt = comboConsumedAmt;
    }
    public BigDecimal getGoodsConsumedAmt() {
        return goodsConsumedAmt;
    }
    public void setGoodsConsumedAmt(BigDecimal goodsConsumedAmt) {
        this.goodsConsumedAmt = goodsConsumedAmt;
    }
    public BigDecimal getTotalAmt() {
        return totalAmt;
    }
    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }
    @Override
    public String toString() {
        return "BusinessIncomePart [projectConsumedAmt=" + projectConsumedAmt
                + ", comboConsumedAmt=" + comboConsumedAmt
                + ", goodsConsumedAmt=" + goodsConsumedAmt + ", totalAmt="
                + totalAmt + "]";
    }
    
    
}
