package com.lives.platform.web.mapper;

import java.util.List;

import com.lives.platform.web.dto.MembersDto;
import com.lives.platform.web.entity.Members;

public interface MembersMapper {
    int deleteByPrimaryKey(Integer membersId);

    int insert(Members record);

    int insertSelective(Members record);

    Members selectByPrimaryKey(Integer membersId);

    int updateByPrimaryKeySelective(Members record);

    int updateByPrimaryKey(Members record);
    
    List<Members> selectByMembersProperty(Members record);
    
    List<Members> selectByMembersSearchCriteria(MembersDto membersDto);
}