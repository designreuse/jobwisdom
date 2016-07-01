package com.zefun.web.mapper;

import com.zefun.web.entity.ActivityAccount;

public interface ActivityAccountMapper {
    int deleteByPrimaryKey(Integer activityId);

    int insert(ActivityAccount record);

    int insertSelective(ActivityAccount record);

    ActivityAccount selectByPrimaryKey(Integer activityId);

    int updateByPrimaryKeySelective(ActivityAccount record);

    int updateByPrimaryKey(ActivityAccount record);
}