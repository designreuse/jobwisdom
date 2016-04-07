package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.CommodityType;

public interface CommodityTypeMapper {
    int deleteByPrimaryKey(Integer commodityTypeId);

    int insert(CommodityType record);

    int insertSelective(CommodityType record);

    CommodityType selectByPrimaryKey(Integer commodityTypeId);

    int updateByPrimaryKeySelective(CommodityType record);

    int updateByPrimaryKey(CommodityType record);
}