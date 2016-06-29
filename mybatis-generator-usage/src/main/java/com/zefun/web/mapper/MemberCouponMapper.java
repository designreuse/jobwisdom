package com.zefun.web.mapper;

import com.zefun.web.entity.MemberCoupon;

public interface MemberCouponMapper {
    int deleteByPrimaryKey(Integer mId);

    int insert(MemberCoupon record);

    int insertSelective(MemberCoupon record);

    MemberCoupon selectByPrimaryKey(Integer mId);

    int updateByPrimaryKeySelective(MemberCoupon record);

    int updateByPrimaryKey(MemberCoupon record);
}