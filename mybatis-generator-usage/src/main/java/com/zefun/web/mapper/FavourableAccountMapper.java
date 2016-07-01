package com.zefun.web.mapper;

import com.zefun.web.entity.FavourableAccount;

public interface FavourableAccountMapper {
    int deleteByPrimaryKey(Integer favourableId);

    int insert(FavourableAccount record);

    int insertSelective(FavourableAccount record);

    FavourableAccount selectByPrimaryKey(Integer favourableId);

    int updateByPrimaryKeySelective(FavourableAccount record);

    int updateByPrimaryKey(FavourableAccount record);
}