package com.lives.platform.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.MembersCardName;
import com.lives.platform.web.mapper.MembersCardNameMapper;

@Service
public class MembersCardNameService {

	@Autowired MembersCardNameMapper membersCardNameMapper;
	
	public int deleteVipLevel(Integer cardNameId){
		return membersCardNameMapper.deleteByPrimaryKey(cardNameId);
	}
	
	public int insertVipLevel(MembersCardName membersCardName){
		return membersCardNameMapper.insertSelective(membersCardName);
	}
	
	public MembersCardName queryVipLevel(Integer cardNameId){
		return membersCardNameMapper.selectByPrimaryKey(cardNameId);
	}
	
	public int updateVipLevel(MembersCardName membersCardName){
		return membersCardNameMapper.updateByPrimaryKeySelective(membersCardName);
	}
	
	/**
	 * 动态查询
	* @author 洪秋霞
	* @date 2015年7月7日 下午2:19:51
	* @param projectClasses
	* @return
	 */
	public List<MembersCardName> selectByProperty(MembersCardName membersCardName){
		return membersCardNameMapper.selectByProperty(membersCardName);
	}
}
