package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2016年01月05日 PM 20:08:21
 */
public class MemberRecommend {
	/** 会员标识 */
	private Integer memberId;

	/** 被推荐会员标识 */
	private Integer recommendId;

	/** 推荐时间 */
	private String recommendTime;

	/** @param memberId	会员标识 */
	public void setMemberId(Integer memberId){
		this.memberId = memberId;
	}

	/** @return	会员标识 */
	public Integer getMemberId(){
		return memberId;
	}

	/** @param recommendId	被推荐会员标识 */
	public void setRecommendId(Integer recommendId){
		this.recommendId = recommendId;
	}

	/** @return	被推荐会员标识 */
	public Integer getRecommendId(){
		return recommendId;
	}

	/** @param recommendTime	推荐时间 */
	public void setRecommendTime(String recommendTime){
		this.recommendTime = recommendTime;
	}

	/** @return	推荐时间 */
	public String getRecommendTime(){
		return recommendTime;
	}

}