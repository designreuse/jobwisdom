package com.zefun.web.entity;

import java.math.BigDecimal;

/**
 * @author 张进军
 * @date 2015年08月10日 PM 22:38:58
 */
public class MemberAccount {
	/** 账户标识 */
	private Integer accountId;

	/** 会员标识 */
	private Integer memberId;

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

	/** 储值余额 */
	private BigDecimal balanceAmount;

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

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;
	
	/** 挂账金额*/
	private BigDecimal debtAmount;

	/** 最后操作人标识 */
	private Integer lastOperatorId;

	
	public BigDecimal getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(BigDecimal debtAmount) {
        this.debtAmount = debtAmount;
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

	/** @param memberId	会员标识 */
	public void setMemberId(Integer memberId){
		this.memberId = memberId;
	}

	/** @return	会员标识 */
	public Integer getMemberId(){
		return memberId;
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

	/** @param balanceAmount	储值余额 */
	public void setBalanceAmount(BigDecimal balanceAmount){
		this.balanceAmount = balanceAmount;
	}

	/** @return	储值余额 */
	public BigDecimal getBalanceAmount(){
		return balanceAmount;
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

}