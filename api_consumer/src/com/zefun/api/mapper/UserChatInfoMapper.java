package com.zefun.api.mapper;

import com.zefun.api.entity.UserChatInfo;

public interface UserChatInfoMapper {
    int deleteByPrimaryKey(Integer chatId);

    int insert(UserChatInfo record);

    int insertSelective(UserChatInfo record);

    UserChatInfo selectByPrimaryKey(Integer chatId);

    int updateByPrimaryKeySelective(UserChatInfo record);

    int updateByPrimaryKeyWithBLOBs(UserChatInfo record);

    int updateByPrimaryKey(UserChatInfo record);
}