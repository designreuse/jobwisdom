package com.lives.platform.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.dto.MembersDto;
import com.lives.platform.web.entity.Members;
import com.lives.platform.web.mapper.MembersMapper;
/**
 * 会员信息
* @author 洪秋霞
* @date 2015年7月16日 下午5:21:44 
*
 */
@Service
public class MembersService {

	@Autowired MembersMapper membersMapper;
	
	public int deleteMembers(Integer membersId){
		return membersMapper.deleteByPrimaryKey(membersId);
	}
	
	public int insertMembers(Members members){
		return membersMapper.insertSelective(members);
	}
	
	public Members queryMembersById(Integer membersId){
		return membersMapper.selectByPrimaryKey(membersId);
	}
	
	public int updateMembers(Members members){
		return membersMapper.updateByPrimaryKeySelective(members);
	}
	
	public List<Members> selectByMembersProperty(Members members){
		return membersMapper.selectByMembersProperty(members);
	}
	
	/**
	 * 会员列表搜索条件查询
	* @author 洪秋霞
	* @date 2015年7月17日 下午3:47:48
	* @param membersDto
	* @return
	 */
	public List<Members> selectByMembersSearchCriteria(MembersDto membersDto){
		return membersMapper.selectByMembersSearchCriteria(membersDto);
	}
}
