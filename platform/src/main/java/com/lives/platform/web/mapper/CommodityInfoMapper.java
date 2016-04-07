package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.CommodityInfo;

public interface CommodityInfoMapper {
    int deleteByPrimaryKey(Integer commodityId);

    int insert(CommodityInfo record);

    int insertSelective(CommodityInfo record);

    CommodityInfo selectByPrimaryKey(Integer commodityId);

    int updateByPrimaryKeySelective(CommodityInfo record);

    int updateByPrimaryKey(CommodityInfo record);
}