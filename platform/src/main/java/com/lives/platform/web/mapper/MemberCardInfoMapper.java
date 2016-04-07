package com.lives.platform.web.mapper;

import java.util.List;
import java.util.Map;

import com.lives.platform.web.entity.MemberCardInfo;
import com.lives.platform.web.entity.Page;

public interface MemberCardInfoMapper {
    int deleteByPrimaryKey(Integer cardId);

    int insert(MemberCardInfo record);

    int insertSelective(MemberCardInfo record);

    MemberCardInfo selectByPrimaryKey(Integer cardId);

    int updateByPrimaryKeySelective(MemberCardInfo record);

    int updateByPrimaryKey(MemberCardInfo record);
    
    /**列表显示*/
	List<MemberCardInfo> selectVipCardInfos(Map<String, Integer> map);

	/**分页查询*/
	List<MemberCardInfo> selectVipCardsByPage(Page<MemberCardInfo> page);
}