package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * 充值记录表
* @author 高国藩
* @date 2016年5月23日 下午8:08:54
 */
public class RechargeRecord {
    
    /***/
    private Integer rId;
    /***/
    private Integer enterpriseAccountId;
    /***/
    private BigDecimal rechargeAmount;
    /***/
    private String openId;
    /***/
    private String outTradeNo;
    /***/
    private Integer status;
    /***/
    private String createTime;
    
    /**
     * df
    * @author 高国藩
    * @date 2016年5月23日 下午8:09:28
    * @return df
     */
    public Integer getrId() {
        return rId;
    }

    /**
     * 地方
    * @author 高国藩
    * @date 2016年5月23日 下午8:09:48
    * @param rId 地方
     */
    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public Integer getEnterpriseAccountId() {
        return enterpriseAccountId;
    }

    public void setEnterpriseAccountId(Integer enterpriseAccountId) {
        this.enterpriseAccountId = enterpriseAccountId;
    }

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}