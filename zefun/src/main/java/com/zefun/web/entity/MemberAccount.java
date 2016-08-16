package com.zefun.web.entity;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class MemberAccount {
	/** 账户标识 */
	private Integer accountId;

	/** 支付密码 */
	private String payPassword;

	/** 密码盐值 */
	private String passwordSalt;
	
	/** 安全问题*/
	private String problem;
	
	/** 安全答案*/
	private String answer;

	/** 储值总额 */
	private BigDecimal totalAmount;
	
	/** 赠送总额 */
	private BigDecimal totalPresentAmount;

	/** 储值余额 */
	private BigDecimal balanceAmount;
	
	/** 礼金储值总额 */
    private BigDecimal totalGiftmoneyAmount;
    
    /** 礼金储值余额 */
    private BigDecimal balanceGiftmoneyAmount;
    
    /** 礼金过期金额*/
    private BigDecimal pastdataMoney;
    
    /** 礼金使用金额 */
    private BigDecimal useMoney;
	
	/** 累计消费次数 */
	private Integer consumeCount;

	/** 积分总额 */
	private Integer totalIntegral;

	/** 积分余额 */
	private Integer balanceIntegral;

	/** 累计消费总额 */
	private BigDecimal totalConsumeAmount;

	/** 单次消费均价 */
	private BigDecimal avgConsumeAmount;

	/** 最后消费时间 */
	private String lastConsumeTime;

	/** 平均消费频率(天) */
	private Integer avgConsumeDays;
	
	/** 欠款金额*/
	private BigDecimal debtAmount;
	
	/** 微信剩余次数*/
	private Integer wechatCount;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;

	/** 最后操作人标识 */
	private Integer lastOperatorId;
	
	/** 会员等级 */
	private Integer levelId;

	/** 默认构造函数 */
	public MemberAccount() {
        super();
    }
	
	/**
	 * 新增账户的构造函数
	* @author 张进军
	* @date Mar 21, 2016 8:53:19 PM
	* @param accountId                 会员标识
	* @param payPassword               密码
	* @param passwordSalt              密码盐值
	* @param totalAmount               储值总额
	* @param balanceAmount             储值余额
	* @param totalGiftmoneyAmount      礼金总额
	* @param balanceGiftmoneyAmount    礼金余额
	* @param totalIntegral             积分总额
	* @param balanceIntegral           积分余额
	* @param debtAmount                挂账金额
	* @param createTime                创建时间
	* @param lastOperatorId            操作人标识
	 */
    public MemberAccount(Integer accountId, String payPassword, String passwordSalt, 
            BigDecimal totalAmount, BigDecimal balanceAmount,  
            BigDecimal totalGiftmoneyAmount, BigDecimal balanceGiftmoneyAmount, 
            Integer totalIntegral, Integer balanceIntegral,
             BigDecimal debtAmount, String createTime, Integer lastOperatorId) {
        super();
        this.accountId = accountId;
        this.payPassword = payPassword;
        this.passwordSalt = passwordSalt;
        this.totalAmount = totalAmount;
        this.balanceAmount = balanceAmount;
        this.totalGiftmoneyAmount = totalGiftmoneyAmount;
        this.balanceGiftmoneyAmount = balanceGiftmoneyAmount;
        this.totalIntegral = totalIntegral;
        this.balanceIntegral = balanceIntegral;
        this.debtAmount = debtAmount;
        this.createTime = createTime;
        this.lastOperatorId = lastOperatorId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getWechatCount() {
        return wechatCount;
    }

    public void setWechatCount(Integer wechatCount) {
        this.wechatCount = wechatCount;
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

    public BigDecimal getPastdataMoney() {
        return pastdataMoney;
    }

    public void setPastdataMoney(BigDecimal pastdataMoney) {
        this.pastdataMoney = pastdataMoney;
    }

    public BigDecimal getUseMoney() {
        return useMoney;
    }

    public void setUseMoney(BigDecimal useMoney) {
        this.useMoney = useMoney;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /** @param accountId	账户标识 */
	public void setAccountId(Integer accountId){
		this.accountId = accountId;
	}

	/** @return	账户标识 */
	public Integer getAccountId(){
		return accountId;
	}

	/** @param payPassword	支付密码 */
	public void setPayPassword(String payPassword){
		this.payPassword = payPassword;
	}

	/** @return	支付密码 */
	public String getPayPassword(){
		return payPassword;
	}

	/** @param passwordSalt	密码盐值 */
	public void setPasswordSalt(String passwordSalt){
		this.passwordSalt = passwordSalt;
	}

	/** @return	密码盐值 */
	public String getPasswordSalt(){
		return passwordSalt;
	}

	/** @param totalAmount	储值总额 */
	public void setTotalAmount(BigDecimal totalAmount){
		this.totalAmount = totalAmount;
	}

	/** @return	储值总额 */
	public BigDecimal getTotalAmount(){
		return totalAmount;
	}

	public BigDecimal getTotalPresentAmount() {
		return totalPresentAmount;
	}

	public void setTotalPresentAmount(BigDecimal totalPresentAmount) {
		this.totalPresentAmount = totalPresentAmount;
	}

	/** @param balanceAmount	储值余额 */
	public void setBalanceAmount(BigDecimal balanceAmount){
		this.balanceAmount = balanceAmount;
	}

	/** @return	储值余额 */
	public BigDecimal getBalanceAmount(){
		return balanceAmount;
	}

	public Integer getConsumeCount() {
		return consumeCount;
	}

	public void setConsumeCount(Integer consumeCount) {
		this.consumeCount = consumeCount;
	}

	/** @param totalIntegral	积分总额 */
	public void setTotalIntegral(Integer totalIntegral){
		this.totalIntegral = totalIntegral;
	}

	/** @return	积分总额 */
	public Integer getTotalIntegral(){
		return totalIntegral;
	}

	/** @param balanceIntegral	积分余额 */
	public void setBalanceIntegral(Integer balanceIntegral){
		this.balanceIntegral = balanceIntegral;
	}

	/** @return	积分余额 */
	public Integer getBalanceIntegral(){
		return balanceIntegral;
	}

	/** @param totalConsumeAmount	累计消费总额 */
	public void setTotalConsumeAmount(BigDecimal totalConsumeAmount){
		this.totalConsumeAmount = totalConsumeAmount;
	}

	/** @return	累计消费总额 */
	public BigDecimal getTotalConsumeAmount(){
		return totalConsumeAmount;
	}

	/** @param avgConsumeAmount	单次消费均价 */
	public void setAvgConsumeAmount(BigDecimal avgConsumeAmount){
		this.avgConsumeAmount = avgConsumeAmount;
	}

	/** @return	单次消费均价 */
	public BigDecimal getAvgConsumeAmount(){
		return avgConsumeAmount;
	}

	/** @param lastConsumeTime	最后消费时间 */
	public void setLastConsumeTime(String lastConsumeTime){
		this.lastConsumeTime = lastConsumeTime;
	}

	/** @return	最后消费时间 */
	public String getLastConsumeTime(){
		return lastConsumeTime;
	}

	/** @param avgConsumeDays	平均消费频率(天) */
	public void setAvgConsumeDays(Integer avgConsumeDays){
		this.avgConsumeDays = avgConsumeDays;
	}

	/** @return	平均消费频率(天) */
	public Integer getAvgConsumeDays(){
		return avgConsumeDays;
	}
	
	public BigDecimal getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(BigDecimal debtAmount) {
        this.debtAmount = debtAmount;
    }

    /** @param createTime	创建时间 */
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	/** @return	创建时间 */
	public String getCreateTime(){
		return createTime;
	}

	/** @param updateTime	修改时间 */
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	/** @return	修改时间 */
	public String getUpdateTime(){
		return updateTime;
	}

	/** @param lastOperatorId	最后操作人标识 */
	public void setLastOperatorId(Integer lastOperatorId){
		this.lastOperatorId = lastOperatorId;
	}

	/** @return	最后操作人标识 */
	public Integer getLastOperatorId(){
		return lastOperatorId;
	}
	
	@Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return "MemberAccount [totalAmount=" + totalAmount + ", balanceAmount="
                + balanceAmount + ", balanceIntegral=" + balanceIntegral
                + ", totalConsumeAmount=" + totalConsumeAmount
                + ", lastConsumeTime=" + lastConsumeTime + ", createTime="
                + createTime + ", lastOperatorId=" + lastOperatorId + "]";
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
    
    

}