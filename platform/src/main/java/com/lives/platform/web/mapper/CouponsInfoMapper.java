package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.CouponsInfo;

public interface CouponsInfoMapper {
    int deleteByPrimaryKey(Integer couponsId);

    int insert(CouponsInfo record);

    int insertSelective(CouponsInfo record);

    CouponsInfo selectByPrimaryKey(Integer couponsId);

    int updateByPrimaryKeySelective(CouponsInfo record);

    int updateByPrimaryKey(CouponsInfo record);
}