package com.lives.platform.web.mapper;

import java.util.List;

import com.lives.platform.web.entity.Coupons;

public interface CouponsMapper {
    int deleteByPrimaryKey(Integer couponsId);

    int insert(Coupons record);

    int insertSelective(Coupons record);

    Coupons selectByPrimaryKey(Integer couponsId);

    int updateByPrimaryKeySelective(Coupons record);

    int updateByPrimaryKey(Coupons record);

	List<Coupons> selectAll();
}