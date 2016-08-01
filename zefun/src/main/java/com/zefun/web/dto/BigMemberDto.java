package com.zefun.web.dto;

/**
 * 大客户分析
* @author 老王
* @date Aug 19, 2015 4:44:42 PM 
*/
public class BigMemberDto {
    
    /** 年平均值 */
    private double yearAverageVlaue;
    
    /** 消费总额 */
    private double totalAverageVlaue;
    
    /** 挂帐金额 */
    private double avgConsumeAmount;
    
    /** 积分余额 */
    private Integer memberId;
    
    /** 性别*/
    private String sex;

    
    
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public double getYearAverageVlaue() {
		return yearAverageVlaue;
	}

	public void setYearAverageVlaue(double yearAverageVlaue) {
		this.yearAverageVlaue = yearAverageVlaue;
	}

	public double getTotalAverageVlaue() {
		return totalAverageVlaue;
	}

	public void setTotalAverageVlaue(double totalAverageVlaue) {
		this.totalAverageVlaue = totalAverageVlaue;
	}

	public double getAvgConsumeAmount() {
		return avgConsumeAmount;
	}

	public void setAvgConsumeAmount(double avgConsumeAmount) {
		this.avgConsumeAmount = avgConsumeAmount;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

}
