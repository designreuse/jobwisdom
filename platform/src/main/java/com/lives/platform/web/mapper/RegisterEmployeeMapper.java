package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.RegisterEmployee;

public interface RegisterEmployeeMapper {
    int deleteByPrimaryKey(Integer registerEmployeeId);

    int insert(RegisterEmployee record);

    int insertSelective(RegisterEmployee record);

    RegisterEmployee selectByPrimaryKey(Integer registerEmployeeId);

    int updateByPrimaryKeySelective(RegisterEmployee record);

    int updateByPrimaryKey(RegisterEmployee record);
}