package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 会员数据dto
* @author 高国藩
* @date 2015年9月11日 下午2:41:39
 */
public class MemberInfoDto {
    /** 会员标识 */
    private Integer memberId;
    /** 门店标识 */
    private Integer storeId;
    /** 姓名 */
    private String name;
    /** 性别 */
    private String sex;
    /** 生日 */
    private String birthday;
    /** 手机号码 */
    private String phone;
    /** 创建时间 */
    private String createTime;
    /** 会员等级名称*/
    private String levelName;
    /** 赠送总额 */
    private BigDecimal totalPresentAmount;
    /** 储值总额 */
    private BigDecimal totalAmount;
    /** 储值余额 */
    private BigDecimal balanceAmount;
    /** 礼金储值总额 */
    private BigDecimal totalGiftmoneyAmount;
    /** 礼金储值余额 */
    private BigDecimal balanceGiftmoneyAmount;
    /** 积分总额 */
    private Integer totalIntegral;
    /** 积分余额 */
    private Integer balanceIntegral;
    /** 累计消费总额 */
    private BigDecimal totalConsumeAmount;
    /** 累计消费次数 */
    private Integer consumeCount;
    /** 单次消费均价 */
    private BigDecimal avgConsumeAmount;
    /** 最后消费时间 */
    private String lastConsumeTime;
    /** 挂账金额*/
    private BigDecimal debtAmount;
    /** 平均消费频率(天) */
    private Integer avgConsumeDays;
    /**是否删除*/
    private Integer isDeleted;
    /**最后操作人标示*/
    private Integer lastOperatorId;
    /**门店名称*/
    private String storeName;
    /**会员备注信息*/
    private String community;
    /** 会员子账户*/
    private List<MemberSubAccountDto> memberSubAccounts;
    

    
    
    public Integer getConsumeCount() {
		return consumeCount;
	}

	public void setConsumeCount(Integer consumeCount) {
		this.consumeCount = consumeCount;
	}

	public List<MemberSubAccountDto> getMemberSubAccounts() {
        return memberSubAccounts;
    }

    public void setMemberSubAccounts(List<MemberSubAccountDto> memberSubAccounts) {
        this.memberSubAccounts = memberSubAccounts;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public BigDecimal getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(BigDecimal debtAmount) {
        this.debtAmount = debtAmount;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    public BigDecimal getTotalPresentAmount() {
        return totalPresentAmount;
    }

    public void setTotalPresentAmount(BigDecimal totalPresentAmount) {
        this.totalPresentAmount = totalPresentAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public BigDecimal getTotalGiftmoneyAmount() {
        return totalGiftmoneyAmount;
    }

    public void setTotalGiftmoneyAmount(BigDecimal totalGiftmoneyAmount) {
        this.totalGiftmoneyAmount = totalGiftmoneyAmount;
    }

    public BigDecimal getBalanceGiftmoneyAmount() {
        return balanceGiftmoneyAmount;
    }

    public void setBalanceGiftmoneyAmount(BigDecimal balanceGiftmoneyAmount) {
        this.balanceGiftmoneyAmount = balanceGiftmoneyAmount;
    }

    public Integer getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(Integer totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public Integer getBalanceIntegral() {
        return balanceIntegral;
    }

    public void setBalanceIntegral(Integer balanceIntegral) {
        this.balanceIntegral = balanceIntegral;
    }

    public BigDecimal getTotalConsumeAmount() {
        return totalConsumeAmount;
    }

    public void setTotalConsumeAmount(BigDecimal totalConsumeAmount) {
        this.totalConsumeAmount = totalConsumeAmount;
    }

    public BigDecimal getAvgConsumeAmount() {
        return avgConsumeAmount;
    }

    public void setAvgConsumeAmount(BigDecimal avgConsumeAmount) {
        this.avgConsumeAmount = avgConsumeAmount;
    }

    public String getLastConsumeTime() {
        return lastConsumeTime;
    }

    public void setLastConsumeTime(String lastConsumeTime) {
        this.lastConsumeTime = lastConsumeTime;
    }

    public Integer getAvgConsumeDays() {
        return avgConsumeDays;
    }

    public void setAvgConsumeDays(Integer avgConsumeDays) {
        this.avgConsumeDays = avgConsumeDays;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /** @param memberId 会员标识 */
    public void setMemberId(Integer memberId){
        this.memberId = memberId;
    }

    /** @return 会员标识 */
    public Integer getMemberId(){
        return memberId;
    }

    /** @param storeId  门店标识 */
    public void setStoreId(Integer storeId){
        this.storeId = storeId;
    }

    /** @return 门店标识 */
    public Integer getStoreId(){
        return storeId;
    }

    /** @param name 姓名 */
    public void setName(String name){
        this.name = name;
    }

    /** @return 姓名 */
    public String getName(){
        return name;
    }

    /** @param sex  性别 */
    public void setSex(String sex){
        this.sex = sex;
    }

    /** @return 性别 */
    public String getSex(){
        return sex;
    }

    /** @param birthday 生日 */
    public void setBirthday(String birthday){
        this.birthday = birthday;
    }

    /** @return 生日 */
    public String getBirthday(){
        return birthday;
    }

    /** @param phone    手机号码 */
    public void setPhone(String phone){
        this.phone = phone;
    }

    /** @return 手机号码 */
    public String getPhone(){
        return phone;
    }

    /** @param createTime   创建时间 */
    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }

    /** @return 创建时间 */
    public String getCreateTime(){
        return createTime;
    }

}
