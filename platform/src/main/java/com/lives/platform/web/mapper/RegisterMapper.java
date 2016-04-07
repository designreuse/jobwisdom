package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.Register;

public interface RegisterMapper {
    int deleteByPrimaryKey(Integer registerId);

    int insert(Register record);

    int insertSelective(Register record);

    Register selectByPrimaryKey(Integer registerId);

    int updateByPrimaryKeySelective(Register record);

    int updateByPrimaryKey(Register record);
}