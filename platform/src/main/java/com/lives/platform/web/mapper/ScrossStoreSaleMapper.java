package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.ScrossStoreSale;

public interface ScrossStoreSaleMapper {
    int deleteByPrimaryKey(Integer scrossStoreSaleId);

    int insert(ScrossStoreSale record);

    int insertSelective(ScrossStoreSale record);

    ScrossStoreSale selectByPrimaryKey(Integer scrossStoreSaleId);

    int updateByPrimaryKeySelective(ScrossStoreSale record);

    int updateByPrimaryKey(ScrossStoreSale record);
}