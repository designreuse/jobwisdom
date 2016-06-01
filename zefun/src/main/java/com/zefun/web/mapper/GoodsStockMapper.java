package com.zefun.web.mapper;

import com.zefun.web.entity.GoodsStock;
import com.zefun.web.entity.GoodsStockKey;

public interface GoodsStockMapper {
    int deleteByPrimaryKey(GoodsStockKey key);

    int insert(GoodsStock record);

    int insertSelective(GoodsStock record);

    GoodsStock selectByPrimaryKey(GoodsStockKey key);

    int updateByPrimaryKeySelective(GoodsStock record);

    int updateByPrimaryKey(GoodsStock record);
}