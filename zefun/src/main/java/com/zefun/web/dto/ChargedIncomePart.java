package com.zefun.web.dto;

import java.math.BigDecimal;

/**
* @author 乐建建
* @date 2016年2月17日 上午11:38:46 
*/
public class ChargedIncomePart extends SummaryResultDto {
    /**充值金额*/
    private BigDecimal chargedAmt=new BigDecimal(0);
    /**套餐金额*/
    private BigDecimal comboAmt=new BigDecimal(0);
    /**开卡金额*/
    private BigDecimal openCardAmt=new BigDecimal(0);
    /**汇总金额*/
    private BigDecimal totalAmt;
    /**升级金额*/
    private BigDecimal upgradeAmt=new BigDecimal(0);
    public BigDecimal getChargedAmt() {
        return chargedAmt;
    }
    
    public BigDecimal getComboAmt() {
        return comboAmt;
    }
    public BigDecimal getOpenCardAmt() {
        return openCardAmt;
    }
    
    public BigDecimal getTotalAmt() {
        return totalAmt;
    }
    public BigDecimal getUpgradeAmt() {
        return upgradeAmt;
    }
    public void setChargedAmt(BigDecimal chargedAmt) {
        this.chargedAmt = chargedAmt;
    }
    public void setComboAmt(BigDecimal comboAmt) {
        this.comboAmt = comboAmt;
    }
    public void setOpenCardAmt(BigDecimal openCardAmt) {
        this.openCardAmt = openCardAmt;
    }
    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }
    public void setUpgradeAmt(BigDecimal upgradeAmt) {
        this.upgradeAmt = upgradeAmt;
    }
    @Override
    public String toString() {
        return "ChargedIncomePart [openCardAmt=" + openCardAmt + ", chargedAmt="
                + chargedAmt + ", upgradeAmt=" + upgradeAmt + ", totalAmt="
                + totalAmt + "]";
    }
}
