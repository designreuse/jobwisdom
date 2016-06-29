package com.zefun.web.entity;

/**
 * 会员优惠券信息表
* @author 高国藩
* @date 2016年6月28日 上午10:50:38
 */
public class MemberCoupon {
    /** 主键 */
    private Integer mId;

    /** 优惠劵 */
    private Integer couponId;

    /** 会员 */
    private Integer memberInfoId;

    /** 结束时间 */
    private String stopTime;

    /** 是否使用了 */
    private Integer isDeleted;

    /**
     * Integer
    * @author 高国藩
    * @date 2016年6月28日 上午11:45:32
    * @return Integer
     */
    public Integer getmId() {
        return mId;
    }

    /**
     * mId
    * @author 高国藩
    * @date 2016年6月28日 上午11:45:41
    * @param mId mId
     */
    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getMemberInfoId() {
        return memberInfoId;
    }

    public void setMemberInfoId(Integer memberInfoId) {
        this.memberInfoId = memberInfoId;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime == null ? null : stopTime.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    /**
     * ssss
    * @author 高国藩
    * @date 2016年6月28日 上午11:46:49
     */
    public MemberCoupon() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 批量新增
    * @author 高国藩
    * @date 2016年6月28日 上午11:46:17
    * @param couponId       couponId
    * @param memberInfoId   memberInfoId
    * @param stopTime       stopTime
    * @param isDeleted      isDeleted(是否已经使用,0未使用,1以使用)
     */
    public MemberCoupon(Integer couponId, Integer memberInfoId, String stopTime,
            Integer isDeleted) {
        super();
        this.couponId = couponId;
        this.memberInfoId = memberInfoId;
        this.stopTime = stopTime;
        this.isDeleted = isDeleted;
    }
    
}