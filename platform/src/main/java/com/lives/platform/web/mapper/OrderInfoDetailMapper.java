package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.OrderInfoDetail;

public interface OrderInfoDetailMapper {
    int deleteByPrimaryKey(Integer orderInfoDetailId);

    int insert(OrderInfoDetail record);

    int insertSelective(OrderInfoDetail record);

    OrderInfoDetail selectByPrimaryKey(Integer orderInfoDetailId);

    int updateByPrimaryKeySelective(OrderInfoDetail record);

    int updateByPrimaryKey(OrderInfoDetail record);
}