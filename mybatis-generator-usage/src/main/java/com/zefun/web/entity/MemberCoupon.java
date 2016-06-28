package com.zefun.web.entity;

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

    public Integer getmId() {
        return mId;
    }

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
}