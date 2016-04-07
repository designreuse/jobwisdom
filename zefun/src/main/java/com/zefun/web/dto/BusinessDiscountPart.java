package com.zefun.web.dto;

import java.math.BigDecimal;

/**
* @author 乐建建
* @date 2016年2月17日 上午11:45:44 
*/
public class BusinessDiscountPart extends SummaryResultDto {
    /**预约优惠金额*/
    private BigDecimal appointAmt=new BigDecimal(0);
    /**套餐优惠*/
    private BigDecimal comboAmt=new BigDecimal(0);
    /**优惠券金额*/
    private BigDecimal couponAmt=new BigDecimal(0);
    /**挂账金额*/
    private BigDecimal debtAmt=new BigDecimal(0);
    @Override
    public String toString() {
        return "BusinessDiscountPart [appointAmt=" + appointAmt + ", comboAmt="
                + comboAmt + ", couponAmt=" + couponAmt + ", debtAmt=" + debtAmt
                + ", giftAmt=" + giftAmt + ", totalAmt=" + totalAmt + "]";
    }
    /**礼金金额*/
    private BigDecimal giftAmt;
    /**汇总金额*/
    private BigDecimal totalAmt;
    public BigDecimal getAppointAmt() {
        return appointAmt;
    }    
    public BigDecimal getComboAmt() {
        return comboAmt;
    }    
    public BigDecimal getCouponAmt() {
        return couponAmt;
    }
    public BigDecimal getDebtAmt() {
        return debtAmt;
    }
    public BigDecimal getGiftAmt() {
        return giftAmt;
    }
    public BigDecimal getTotalAmt() {
        return totalAmt;
    }
    public void setAppointAmt(BigDecimal appointAmt) {
        this.appointAmt = appointAmt;
    }
    public void setComboAmt(BigDecimal comboAmt) {
        this.comboAmt = comboAmt;
    }
    public void setCouponAmt(BigDecimal couponAmt) {
        this.couponAmt = couponAmt;
    }
    public void setDebtAmt(BigDecimal debtAmt) {
        this.debtAmt = debtAmt;
    }
    public void setGiftAmt(BigDecimal giftAmt) {
        this.giftAmt = giftAmt;
    }
    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }
}
