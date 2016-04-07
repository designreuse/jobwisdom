package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.MembersWater;

public interface MembersWaterMapper {
    int deleteByPrimaryKey(Integer membersWaterId);

    int insert(MembersWater record);

    MembersWater selectByPrimaryKey(Integer membersWaterId);

    int updateByPrimaryKey(MembersWater record);
}