package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.StoresManage;

public interface StoresManageMapper {
    int deleteByPrimaryKey(Integer storesManageId);

    int insert(StoresManage record);

    int insertSelective(StoresManage record);

    StoresManage selectByPrimaryKey(Integer storesManageId);

    int updateByPrimaryKeySelective(StoresManage record);

    int updateByPrimaryKey(StoresManage record);
}