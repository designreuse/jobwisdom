package com.lives.platform.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.MembersIntegral;
import com.lives.platform.web.mapper.MembersIntegralMapper;

/**
 * 会员积分赠送记录表
* @author 洪秋霞
* @date 2015年7月20日 上午10:17:32 
*
 */
@Service
public class MembersIntegralService {

	@Autowired private MembersIntegralMapper membersIntegralMapper;
	
	public int deleteMembersIntegral(Integer membersIntegralId){
		return membersIntegralMapper.deleteByPrimaryKey(membersIntegralId);
	}
	
	public int insertMembersIntegral(MembersIntegral membersIntegral){
		return membersIntegralMapper.insertSelective(membersIntegral);
	}
	
	public MembersIntegral queryMembersIntegralById(Integer membersIntegralId){
		return membersIntegralMapper.selectByPrimaryKey(membersIntegralId);
	}
	
	public int updateMembersIntegral(MembersIntegral membersIntegral){
		return membersIntegralMapper.updateByPrimaryKeySelective(membersIntegral);
	}
	
	public List<MembersIntegral> selectByMembersIntegralProperty(MembersIntegral membersIntegral){
		return membersIntegralMapper.selectByMembersIntegralProperty(membersIntegral);
	}
}
