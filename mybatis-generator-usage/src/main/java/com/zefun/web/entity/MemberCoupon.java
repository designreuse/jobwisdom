package com.zefun.web.entity;

public class MemberCoupon {
    /** ���� */
    private Integer mId;

    /** �Ż݄� */
    private Integer couponId;

    /** ��Ա */
    private Integer memberInfoId;

    /** ����ʱ�� */
    private String stopTime;

    /** �Ƿ�ʹ���� */
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