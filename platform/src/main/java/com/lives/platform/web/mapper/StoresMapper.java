package com.lives.platform.web.mapper;

import java.util.List;

import com.lives.platform.web.entity.Stores;

public interface StoresMapper {
    int deleteByPrimaryKey(Integer storesId);

    int insert(Stores record);

    int insertSelective(Stores record);

    Stores selectByPrimaryKey(Integer storesId);

    int updateByPrimaryKeySelective(Stores record);

    int updateByPrimaryKey(Stores record);
    
    List<Stores> selectByStoresId(Integer storesId);
    
    List<Stores> selectByProperty(Stores record);
}