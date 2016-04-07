package com.lives.platform.web.mapper;

import java.util.List;

import com.lives.platform.web.entity.MembersIntegral;

public interface MembersIntegralMapper {
    int deleteByPrimaryKey(Integer membersIntegralId);

    int insert(MembersIntegral record);

    int insertSelective(MembersIntegral record);

    MembersIntegral selectByPrimaryKey(Integer membersIntegralId);

    int updateByPrimaryKeySelective(MembersIntegral record);

    int updateByPrimaryKey(MembersIntegral record);
    
    List<MembersIntegral> selectByMembersIntegralProperty(MembersIntegral membersIntegral);
}