package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.ComboSaleDetail;

public interface ComboSaleDetailMapper {
    int deleteByPrimaryKey(Integer comboSaleDetailId);

    int insert(ComboSaleDetail record);

    int insertSelective(ComboSaleDetail record);

    ComboSaleDetail selectByPrimaryKey(Integer comboSaleDetailId);

    int updateByPrimaryKeySelective(ComboSaleDetail record);

    int updateByPrimaryKey(ComboSaleDetail record);
}