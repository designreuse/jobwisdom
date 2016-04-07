package com.zefun.web.mapper;

import com.zefun.web.entity.UserInfo;

public interface UserInfoMapper {
    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectUserByParam(UserInfo userInfo);
}