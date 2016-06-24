package com.zefun.web.mapper;

import com.zefun.web.entity.CouponStoreKey;

public interface CouponStoreMapper {
    int deleteByPrimaryKey(CouponStoreKey key);

    int insert(CouponStoreKey record);

    int insertSelective(CouponStoreKey record);
}