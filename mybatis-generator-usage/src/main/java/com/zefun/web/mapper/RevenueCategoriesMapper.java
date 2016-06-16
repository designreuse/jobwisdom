package com.zefun.web.mapper;

import com.zefun.web.entity.RevenueCategories;

public interface RevenueCategoriesMapper {
    int deleteByPrimaryKey(Integer rId);

    int insert(RevenueCategories record);

    int insertSelective(RevenueCategories record);

    RevenueCategories selectByPrimaryKey(Integer rId);

    int updateByPrimaryKeySelective(RevenueCategories record);

    int updateByPrimaryKey(RevenueCategories record);
}