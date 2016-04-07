package com.lives.platform.web.mapper;

import java.util.List;

import com.lives.platform.web.entity.SankerCoupons;

public interface SankerCouponsMapper {
    int deleteByPrimaryKey(Integer couponsId);

    int insert(SankerCoupons record);

    int insertSelective(SankerCoupons record);

    SankerCoupons selectByPrimaryKey(Integer couponsId);

    int updateByPrimaryKeySelective(SankerCoupons record);

    int updateByPrimaryKey(SankerCoupons record);

	List<SankerCoupons> selectAll();
}