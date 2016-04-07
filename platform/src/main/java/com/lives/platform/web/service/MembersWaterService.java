package com.lives.platform.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.MembersWater;
import com.lives.platform.web.mapper.MembersWaterMapper;
/**
 * 	会员流水表
* @author 洪秋霞
* @date 2015年7月20日 上午10:12:24 
*
 */
@Service
public class MembersWaterService {

	@Autowired MembersWaterMapper membersWaterMapper;
	
	public int deleteMembersWater(Integer membersWaterId){
		return membersWaterMapper.deleteByPrimaryKey(membersWaterId);
	}
	
	public int insertMembersWater(MembersWater membersWater){
		return membersWaterMapper.insert(membersWater);
	}
	
	public MembersWater queryMembersWaterById(Integer membersWaterId){
		return membersWaterMapper.selectByPrimaryKey(membersWaterId);
	}
	
	public int updateMembersWater(MembersWater membersWater){
		return membersWaterMapper.updateByPrimaryKey(membersWater);
	}
}
