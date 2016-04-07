package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.MembersCashgiftStages;

public interface MembersCashgiftStagesMapper {
    int deleteByPrimaryKey(Integer cashgiftStagesId);

    int insert(MembersCashgiftStages record);

    int insertSelective(MembersCashgiftStages record);

    MembersCashgiftStages selectByPrimaryKey(Integer cashgiftStagesId);

    int updateByPrimaryKeySelective(MembersCashgiftStages record);

    int updateByPrimaryKey(MembersCashgiftStages record);
}