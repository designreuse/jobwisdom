package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.PostRegister;

public interface PostRegisterMapper {
    int deleteByPrimaryKey(Integer postRegisterId);

    int insert(PostRegister record);

    int insertSelective(PostRegister record);

    PostRegister selectByPrimaryKey(Integer postRegisterId);

    int updateByPrimaryKeySelective(PostRegister record);

    int updateByPrimaryKey(PostRegister record);
}