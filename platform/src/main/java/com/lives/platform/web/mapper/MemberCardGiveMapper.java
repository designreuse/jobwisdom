package com.lives.platform.web.mapper;

import java.util.List;

import com.lives.platform.web.dto.MemberCardGiveDto;
import com.lives.platform.web.entity.Page;
import com.lives.platform.web.entity.MemberCardGive;

public interface MemberCardGiveMapper {
    int deleteByPrimaryKey(Integer cardId);

    int insert(MemberCardGiveDto record);

    int insertSelective(MemberCardGiveDto record);

    MemberCardGive selectByPrimaryKey(Integer cardId);

    int updateByPrimaryKeySelective(MemberCardGiveDto record);

    int updateByPrimaryKey(MemberCardGiveDto record);

	List<MemberCardGiveDto> selectPresentInfosByPage(Page<MemberCardGiveDto> page);
}