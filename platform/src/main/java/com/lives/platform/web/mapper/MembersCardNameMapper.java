package com.lives.platform.web.mapper;

import java.util.List;

import com.lives.platform.web.entity.MembersCardName;

public interface MembersCardNameMapper {
    int deleteByPrimaryKey(Integer cardNameId);

    int insert(MembersCardName record);

    int insertSelective(MembersCardName record);

    MembersCardName selectByPrimaryKey(Integer cardNameId);

    int updateByPrimaryKeySelective(MembersCardName record);

    int updateByPrimaryKey(MembersCardName record);
    
    /**
	 * 动态查询
	* @author 洪秋霞
	* @date 2015年7月7日 下午2:19:51
	* @param projectClasses
	* @return
	 */
	public List<MembersCardName> selectByProperty(MembersCardName membersCardName);
}