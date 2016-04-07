package com.lives.platform.web.mapper;

import java.util.List;

import com.lives.platform.web.dto.MemberCardRecommendDto;
import com.lives.platform.web.entity.Page;
import com.lives.platform.web.entity.MemberCardRecommend;

public interface MemberCardRecommendMapper {
    int deleteByPrimaryKey(Integer cardId);

    int insert(MemberCardRecommendDto record);

    int insertSelective(MemberCardRecommendDto record);

    MemberCardRecommend selectByPrimaryKey(Integer cardId);

    int updateByPrimaryKeySelective(MemberCardRecommendDto record);

    int updateByPrimaryKey(MemberCardRecommendDto record);
    
    List<MemberCardRecommendDto> selectRecommendInfosByPage(Page<MemberCardRecommendDto> page);
}