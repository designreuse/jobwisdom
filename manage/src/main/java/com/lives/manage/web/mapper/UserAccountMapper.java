package com.lives.manage.web.mapper;


import java.util.List;

import com.lives.manage.common.consts.Url.User;
import com.lives.manage.web.dto.UserInfoDto;
import com.lives.manage.web.entity.UserAccount;

public interface UserAccountMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    UserAccount selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    //查询用户列表
    List<UserInfoDto> getlistUser(UserInfoDto UserInfoDto);
}