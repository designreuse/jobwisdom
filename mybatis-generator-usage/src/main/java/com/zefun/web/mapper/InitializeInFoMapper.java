package com.zefun.web.mapper;

import com.zefun.web.entity.InitializeInFo;

public interface InitializeInFoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InitializeInFo record);

    int insertSelective(InitializeInFo record);

    InitializeInFo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InitializeInFo record);

    int updateByPrimaryKey(InitializeInFo record);
}