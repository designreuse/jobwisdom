package com.lives.platform.web.mapper;

import java.util.List;
import com.lives.platform.web.dto.MemberCardCommissionDto;
import com.lives.platform.web.entity.Page;
import com.lives.platform.web.entity.MemberCardCommission;

public interface MemberCardCommissionMapper {
    int deleteByPrimaryKey(Integer cardId);

    int insert(MemberCardCommissionDto record);

    int insertSelective(MemberCardCommissionDto record);

    MemberCardCommission selectByPrimaryKey(Integer cardId);

    int updateByPrimaryKeySelective(MemberCardCommissionDto record);

    int updateByPrimaryKey(MemberCardCommissionDto record);
    

    /**分页显示*/
   	List<MemberCardCommissionDto> selectCutInfosByPage(Page<MemberCardCommissionDto> page);
}