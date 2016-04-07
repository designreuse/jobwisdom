package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.BigOrder;

public interface BigOrderMapper {
    int deleteByPrimaryKey(Integer bOrderId);

    int insert(BigOrder record);

    int insertSelective(BigOrder record);

    BigOrder selectByPrimaryKey(Integer bOrderId);

    int updateByPrimaryKeySelective(BigOrder record);

    int updateByPrimaryKey(BigOrder record);
}