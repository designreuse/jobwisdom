package com.zefun.web.dto;

import java.math.BigDecimal;

/**
 * 通过筛选条件欲查询会员数据信息
* @author 高国藩
* @date 2015年9月12日 上午9:36:42
 */
public class ScreeningDto {
    
    /**主键*/
    private Integer screeningId;
    /**筛选器名称*/
    private String screeningName;
    /**筛选器创建时间*/
    private String createTime;
    /**性别*/
    private String sex;
    /**等级*/
    private Integer levelId;
    /**储值余额*/
    private Integer unusedBalanceStart;
    /**储值余额*/
    private Integer unusedBalanceEnd;
    /**积分余额*/
    private Integer integralBalanceStart;
    /**积分余额*/
    private Integer integralBalanceEnd;
    /**生日*/
    private String birthdayStart;
    /**生日*/
    private String birthdayEnd;
    /**注册日期*/
    private String registrationDateStart;
    /**注册日期*/
    private String registrationDateEnd;
    /**消费均额*/
    private Integer consumptionAverageStart;
    /**消费均额*/
    private Integer consumptionAverageEnd;
    /**累计消费*/
    private Integer cumulativeConsumptionStart;
    /**累计消费*/
    private Integer cumulativeConsumptionEnd;
    /**挂账*/
    private BigDecimal debtAmountStart;
    /**挂账*/
    private BigDecimal debtAmountEnd;
    /**礼金余额*/
    private BigDecimal giftMoneyAmountStart;
    /**礼金余额*/
    private BigDecimal giftMoneyAmountEnd;
    /**距离上次消费*/
    private Integer lastConsumeTimeStart;
    /**距离上次消费*/
    private Integer lastConsumeTimeEnd;
    /**门店信息*/
    private Integer storeId;
    /**分店门店id*/
    private String branchStoreIds;
    /**查询冻结账户*/
    private Integer status;

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public BigDecimal getGiftMoneyAmountStart() {
        return giftMoneyAmountStart;
    }
    public void setGiftMoneyAmountStart(BigDecimal giftMoneyAmountStart) {
        this.giftMoneyAmountStart = giftMoneyAmountStart;
    }
    public BigDecimal getGiftMoneyAmountEnd() {
        return giftMoneyAmountEnd;
    }
    public void setGiftMoneyAmountEnd(BigDecimal giftMoneyAmountEnd) {
        this.giftMoneyAmountEnd = giftMoneyAmountEnd;
    }
    public BigDecimal getDebtAmountStart() {
        return debtAmountStart;
    }
    public void setDebtAmountStart(BigDecimal debtAmountStart) {
        this.debtAmountStart = debtAmountStart;
    }
    public BigDecimal getDebtAmountEnd() {
        return debtAmountEnd;
    }
    public void setDebtAmountEnd(BigDecimal debtAmountEnd) {
        this.debtAmountEnd = debtAmountEnd;
    }
    public String getBranchStoreIds() {
        return branchStoreIds;
    }
    public void setBranchStoreIds(String branchStoreIds) {
        this.branchStoreIds = branchStoreIds;
    }
    
    public Integer getLastConsumeTimeStart() {
        return lastConsumeTimeStart;
    }

    public void setLastConsumeTimeStart(Integer lastConsumeTimeStart) {
        this.lastConsumeTimeStart = lastConsumeTimeStart;
    }

    public Integer getLastConsumeTimeEnd() {
        return lastConsumeTimeEnd;
    }

    public void setLastConsumeTimeEnd(Integer lastConsumeTimeEnd) {
        this.lastConsumeTimeEnd = lastConsumeTimeEnd;
    }

    public Integer getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(Integer screeningId) {
        this.screeningId = screeningId;
    }

    public String getScreeningName() {
        return screeningName;
    }

    public void setScreeningName(String screeningName) {
        this.screeningName = screeningName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getUnusedBalanceStart() {
        return unusedBalanceStart;
    }

    public void setUnusedBalanceStart(Integer unusedBalanceStart) {
        this.unusedBalanceStart = unusedBalanceStart;
    }

    public Integer getUnusedBalanceEnd() {
        return unusedBalanceEnd;
    }

    public void setUnusedBalanceEnd(Integer unusedBalanceEnd) {
        this.unusedBalanceEnd = unusedBalanceEnd;
    }

    public Integer getIntegralBalanceStart() {
        return integralBalanceStart;
    }

    public void setIntegralBalanceStart(Integer integralBalanceStart) {
        this.integralBalanceStart = integralBalanceStart;
    }

    public Integer getIntegralBalanceEnd() {
        return integralBalanceEnd;
    }

    public void setIntegralBalanceEnd(Integer integralBalanceEnd) {
        this.integralBalanceEnd = integralBalanceEnd;
    }

    public String getBirthdayStart() {
        return birthdayStart;
    }

    public void setBirthdayStart(String birthdayStart) {
        this.birthdayStart = birthdayStart;
    }

    public String getBirthdayEnd() {
        return birthdayEnd;
    }

    public void setBirthdayEnd(String birthdayEnd) {
        this.birthdayEnd = birthdayEnd;
    }

    public String getRegistrationDateStart() {
        return registrationDateStart;
    }

    public void setRegistrationDateStart(String registrationDateStart) {
        this.registrationDateStart = registrationDateStart;
    }

    public String getRegistrationDateEnd() {
        return registrationDateEnd;
    }

    public void setRegistrationDateEnd(String registrationDateEnd) {
        this.registrationDateEnd = registrationDateEnd;
    }

    public Integer getConsumptionAverageStart() {
        return consumptionAverageStart;
    }

    public void setConsumptionAverageStart(Integer consumptionAverageStart) {
        this.consumptionAverageStart = consumptionAverageStart;
    }

    public Integer getConsumptionAverageEnd() {
        return consumptionAverageEnd;
    }

    public void setConsumptionAverageEnd(Integer consumptionAverageEnd) {
        this.consumptionAverageEnd = consumptionAverageEnd;
    }

    public Integer getCumulativeConsumptionStart() {
        return cumulativeConsumptionStart;
    }

    public void setCumulativeConsumptionStart(Integer cumulativeConsumptionStart) {
        this.cumulativeConsumptionStart = cumulativeConsumptionStart;
    }

    public Integer getCumulativeConsumptionEnd() {
        return cumulativeConsumptionEnd;
    }

    public void setCumulativeConsumptionEnd(Integer cumulativeConsumptionEnd) {
        this.cumulativeConsumptionEnd = cumulativeConsumptionEnd;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer sql = new StringBuffer();
        if (unusedBalanceStart != null) {
            sql.append("储值余额:" + unusedBalanceStart + "-");
            if (unusedBalanceEnd != null) {
                sql.append(unusedBalanceEnd);
            }
            sql.append(",");
        }
        if (integralBalanceStart != null) {
            sql.append("积分余额:" + integralBalanceStart + "-");
            if (integralBalanceEnd != null) {
                sql.append(integralBalanceEnd);
            }
            sql.append(",");
        }
        if (birthdayStart != null) {
            sql.append("生日:" + birthdayStart + "-");
            if (birthdayEnd != null) {
                sql.append(birthdayEnd);
            }
            sql.append(",");
        }
        if (registrationDateStart != null) {
            sql.append("注册日期:" + registrationDateStart + "-");
            if (registrationDateEnd != null) {
                sql.append(registrationDateEnd);
            }
            sql.append(",");
        }
        if (consumptionAverageStart != null) {
            sql.append("消费均额:" + consumptionAverageStart + "-");
            if (consumptionAverageEnd != null) {
                sql.append(consumptionAverageEnd);
            }
            sql.append(",");
        }
        if (cumulativeConsumptionStart != null) {
            sql.append("累计消费:" + cumulativeConsumptionStart + "-");
            if (cumulativeConsumptionEnd != null) {
                sql.append(cumulativeConsumptionEnd);
            }
            sql.append(",");
        }
        if (lastConsumeTimeStart != null) {
            sql.append("上次消费时间:" + lastConsumeTimeStart + "-");
            if (lastConsumeTimeEnd != null) {
                sql.append(lastConsumeTimeEnd);
            }
            sql.append(",");
        }
        if (debtAmountStart != null) {
            sql.append("欠款挂账:" + debtAmountStart + "-");
            if (debtAmountEnd != null) {
                sql.append(debtAmountEnd);
            }
            sql.append(",");
        }
        if (giftMoneyAmountStart != null) {
            sql.append("礼金余额 :" + giftMoneyAmountStart + "-");
            if (giftMoneyAmountEnd != null) {
                sql.append(giftMoneyAmountEnd);
            }
            sql.append(",");
        }
        return sql.toString();
    }
}
