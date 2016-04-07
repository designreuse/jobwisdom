package com.lives.platform.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.MembersCashgiftStages;
import com.lives.platform.web.mapper.MembersCashgiftStagesMapper;

/**
 * 会员礼金分期记录表
* @author 洪秋霞
* @date 2015年7月20日 上午10:18:34 
*
 */
@Service
public class MembersCashgiftStagesService {

	@Autowired private MembersCashgiftStagesMapper membersCashgiftStagesMapper;
	
	public int deleteMembersCashgiftStages(Integer cashgiftStagesId){
		return membersCashgiftStagesMapper.deleteByPrimaryKey(cashgiftStagesId);
	}
	
	public int insertMembersCashgiftStages(MembersCashgiftStages membersCashgiftStages){
		return membersCashgiftStagesMapper.insertSelective(membersCashgiftStages);
	}
	
	public MembersCashgiftStages queryMembersCashgiftStagesById(Integer cashgiftStagesId){
		return membersCashgiftStagesMapper.selectByPrimaryKey(cashgiftStagesId);
	}
	
	public int updateMembersCashgiftStages(MembersCashgiftStages membersCashgiftStages){
		return membersCashgiftStagesMapper.updateByPrimaryKeySelective(membersCashgiftStages);
	}
	
}
