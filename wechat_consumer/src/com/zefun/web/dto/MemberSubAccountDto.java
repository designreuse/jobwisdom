package com.zefun.web.dto;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;

/**
 * 会员子账户传输对象
* @author 张进军
* @date Mar 14, 2016 7:25:28 PM
 */
public class MemberSubAccountDto {
    /** 子账户标识 */
    private Integer subAccountId;

    /** 储值总额 */
    private BigDecimal totalAmount;

    /** 赠送总额 */
    private BigDecimal totalPresentAmount;

    /** 储值余额 */
    private BigDecimal balanceAmount;

    /** 使用金额 */
    private BigDecimal useAmount;
    
    /** 级别标识 */
    private Integer levelId;
    
    /** 等级名称 */
    private String levelName;
    
    /** 卡logo*/
    private String levelLogo;
    
    /** 卡模板*/
    private Integer levelTemplate;

    /** 项目折扣 */
    private Integer projectDiscount;

    /** 商品折扣 */
    private Integer goodsDiscount;

    /** 现金是否打折(0:不打折，1:打折) */
    private Integer cashDiscountType;

    /** 消费积分单位 */
    private Integer integralUnit;

    /** 单位积分数量 */
    private Integer integralNumber;

    /** 业绩折扣比例(0-100) */
    private Integer performanceDiscountPercent;
    
    /** 等级说明 */
    private String levelNotice;

    
    public Integer getLevelTemplate() {
        return levelTemplate;
    }

    public void setLevelTemplate(Integer levelTemplate) {
        this.levelTemplate = levelTemplate;
    }

    public String getLevelLogo() {
        return levelLogo;
    }

    public void setLevelLogo(String levelLogo) {
        this.levelLogo = levelLogo;
    }

    public Integer getSubAccountId() {
        return subAccountId;
    }

    public void setSubAccountId(Integer subAccountId) {
        this.subAccountId = subAccountId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalPresentAmount() {
        return totalPresentAmount;
    }

    public void setTotalPresentAmount(BigDecimal totalPresentAmount) {
        this.totalPresentAmount = totalPresentAmount;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public BigDecimal getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(BigDecimal useAmount) {
        this.useAmount = useAmount;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getProjectDiscount() {
        return projectDiscount;
    }

    public void setProjectDiscount(Integer projectDiscount) {
        this.projectDiscount = projectDiscount;
    }

    public Integer getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(Integer goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    public Integer getCashDiscountType() {
        return cashDiscountType;
    }

    public void setCashDiscountType(Integer cashDiscountType) {
        this.cashDiscountType = cashDiscountType;
    }

    public Integer getIntegralUnit() {
        return integralUnit;
    }

    public void setIntegralUnit(Integer integralUnit) {
        this.integralUnit = integralUnit;
    }

    public Integer getIntegralNumber() {
        return integralNumber;
    }

    public void setIntegralNumber(Integer integralNumber) {
        this.integralNumber = integralNumber;
    }

    public Integer getPerformanceDiscountPercent() {
        return performanceDiscountPercent;
    }

    public void setPerformanceDiscountPercent(Integer performanceDiscountPercent) {
        this.performanceDiscountPercent = performanceDiscountPercent;
    }
    
    
    public String getLevelNotice() {
        return levelNotice;
    }

    public void setLevelNotice(String levelNotice) {
        this.levelNotice = levelNotice;
    }

    /**
     * 以JSON数组的方式获取等级描述信息
    * @author 张进军
    * @date Jan 28, 2016 9:00:49 PM
    * @return   等级描述信息
     */
    public JSONArray getLevelNoticeList(){
        if (StringUtils.isNotBlank(getLevelNotice())) {
            return JSONArray.fromObject(getLevelNotice());
        }
        return null;
    }
    
}
