package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.StockFlow;

public interface StockFlowMapper {
    int deleteByPrimaryKey(Integer stockFlowId);

    int insert(StockFlow record);

    int insertSelective(StockFlow record);

    StockFlow selectByPrimaryKey(Integer stockFlowId);

    int updateByPrimaryKeySelective(StockFlow record);

    int updateByPrimaryKey(StockFlow record);

    List<StockFlow> selectByProperties(StockFlow query);
}