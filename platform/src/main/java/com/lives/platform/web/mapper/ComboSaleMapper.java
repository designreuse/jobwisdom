package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.ComboSale;

public interface ComboSaleMapper {
    int deleteByPrimaryKey(Integer comboSaleId);

    int insert(ComboSale record);

    int insertSelective(ComboSale record);

    ComboSale selectByPrimaryKey(Integer comboSaleId);

    int updateByPrimaryKeySelective(ComboSale record);

    int updateByPrimaryKey(ComboSale record);
}