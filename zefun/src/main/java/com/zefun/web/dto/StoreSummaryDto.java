package com.zefun.web.dto;

/**
 * 门店付款订单统计
 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
 * @date 2016年1月4日
 */
public class StoreSummaryDto {

    /**
     * 时间
     */
    protected String date;

    /**
     * 收入
     */
    protected double income = 0;

    /**
     * 支出
     */
    protected double expenses = 0;

    /**
     * 实收
     */
    protected double amount = 0;
    /**
     * 部门id
     */
    protected Integer deptId;

    /**
     * 现金金额
     */
    protected double cashAmount = 0;

    /**
     * 会员卡支付
     */
    protected double cardAmount = 0;

    /**
     * 礼金支付
     */
    protected double giftAmount = 0;

    /**
     * 微信支付
     */
    protected double wechatAmount = 0;

    /**
     * 支付宝支付
     */
    protected double alipayAmount = 0;

    /**
     * 团购支付
     */
    protected double groupAmount = 0;

    /**
     * 套餐抵扣
     */
    protected double comboAmount = 0;

    /**
     * 优惠券抵扣
     */
    protected double couponAmount = 0;

    /**
     * 预约优惠
     */
    protected double appointOff = 0;

    /**
     * 现金实收(微信支付, 支付宝支付, 银联, 现金等之和)
     */
    protected double cashRealAmount = 0;

    /**
     * 改价金额
     */
    protected double freeAmount = 0;

    /**
     * 挂账金额
     */
    protected double debtAmount = 0;

    /**
     * 应收金额
     */
    protected double receivableAmount = 0;

    /**
     * 银联支付
     */
    protected double unionpayAmount = 0;

    /**
     * 订单类型(1:项目,2:商品,3:套餐,4、开卡,5:充值,6:升级)
     */
    protected Integer orderType = 0;

    /**
     * 会员id
     */
    protected Integer memberId;

    /**
     * 会员等级id
     */
    protected Integer memLevelId = 0;

    /**
     * 会员数量
     */
    protected Integer memberCnt = 0;

    /**
     * 
     * @author gebing
     * @date 2016年1月4日
     * @return 获取时间
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @author gebing
     * @date 2016年1月4日
     * @param date 设置时间
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 
     * @author gebing
     * @date 2016年1月4日
     * @return 获取和
     */
    public double getAmount() {
        return amount;
    }

    /**
     * 设置和
     * @author gebing
     * @date 2016年1月4日
     * @param amount  实收
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * 
     * @author gebing
     * @date 2016年1月4日
     * @return  获取部门id
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * @author gebing
     * @date 2016年1月4日
     * @param deptId 设置部门id
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * 
     * @author gebing
     * @date 2016年1月4日
     * @return 获取收入
     */
    public double getIncome() {
        return income;
    }

    /**
     * 
     * @author gebing
     * @date 2016年1月4日
     * @param income 设置收入
     */
    public void setIncome(double income) {
        this.income = income;
    }

    /**
     * 
     * @author gebing
     * @date 2016年1月4日
     * @return 获取扣减
     */
    public double getExpenses() {
        return expenses;
    }

    /**
     * 
     * @author gebing
     * @date 2016年1月4日
     * @param expenses 设置扣减
     */
    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public double getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(double cardAmount) {
        this.cardAmount = cardAmount;
    }

    public double getGiftAmount() {
        return giftAmount;
    }

    public void setGiftAmount(double giftAmount) {
        this.giftAmount = giftAmount;
    }

    public double getWechatAmount() {
        return wechatAmount;
    }

    public void setWechatAmount(double wechatAmount) {
        this.wechatAmount = wechatAmount;
    }

    public double getAlipayAmount() {
        return alipayAmount;
    }

    public void setAlipayAmount(double alipayAmount) {
        this.alipayAmount = alipayAmount;
    }

    public double getGroupAmount() {
        return groupAmount;
    }

    public void setGroupAmount(double groupAmount) {
        this.groupAmount = groupAmount;
    }

    public double getComboAmount() {
        return comboAmount;
    }

    public void setComboAmount(double comboAmount) {
        this.comboAmount = comboAmount;
    }

    public double getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(double couponAmount) {
        this.couponAmount = couponAmount;
    }

    public double getAppointOff() {
        return appointOff;
    }

    public void setAppointOff(double appointOff) {
        this.appointOff = appointOff;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public double getCashRealAmount() {
        return cashRealAmount;
    }

    public void setCashRealAmount(double cashRealAmount) {
        this.cashRealAmount = cashRealAmount;
    }

    public double getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(double freeAmount) {
        this.freeAmount = freeAmount;
    }

    public double getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(double debtAmount) {
        this.debtAmount = debtAmount;
    }

    public double getReceivableAmount() {
        return receivableAmount;
    }

    public void setReceivableAmount(double receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    public double getUnionpayAmount() {
        return unionpayAmount;
    }

    public void setUnionpayAmount(double unionpayAmount) {
        this.unionpayAmount = unionpayAmount;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getMemLevelId() {
        return memLevelId;
    }

    public void setMemLevelId(Integer memLevelId) {
        this.memLevelId = memLevelId;
    }

    public Integer getMemberCnt() {
        return memberCnt;
    }

    public void setMemberCnt(Integer memberCnt) {
        this.memberCnt = memberCnt;
    }

}
