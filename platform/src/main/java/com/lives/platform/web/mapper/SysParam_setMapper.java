package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.SysParam_set;

public interface SysParam_setMapper {
    int deleteByPrimaryKey(Integer paramId);

    int insert(SysParam_set record);

    int insertSelective(SysParam_set record);

    SysParam_set selectByPrimaryKey(Integer paramId);

    int updateByPrimaryKeySelective(SysParam_set record);

    int updateByPrimaryKey(SysParam_set record);
}