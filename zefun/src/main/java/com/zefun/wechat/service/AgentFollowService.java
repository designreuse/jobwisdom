package com.zefun.wechat.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zefun.web.entity.AgentAccount;
import com.zefun.web.entity.AgentFollow;
import com.zefun.web.entity.SalesmanInfo;
import com.zefun.web.mapper.AgentAccountMapper;
import com.zefun.web.mapper.AgentFollowMapper;
import com.zefun.web.mapper.SalesmanInfoMapper;

/**
 * 渠道跟踪服务
* @author DavidLiang
* @date 2016年1月18日 下午6:00:02
 */
@Service
public class AgentFollowService {
	
	/** 渠道跟踪映射 */
	@Autowired
	private AgentFollowMapper agentFollowMapper;
	
	/** 业务员信息映射 */
	@Autowired
	private SalesmanInfoMapper salesmanInfoMapper;
	
	/** 渠道账户映射 */
	@Autowired
	private AgentAccountMapper agentAccountMapper;
	
	/**
	 * 新增渠道跟踪记录
	* @author DavidLiang
	* @date 2016年1月18日 下午6:29:39
	* @param agentFollow  渠道跟踪记录实体
	* @return  是否成功，true成功
	 */
	public boolean addAgentFollow(AgentFollow agentFollow) {
		//根据openId查看是渠道商还是业务员
		AgentAccount agentAccount = agentAccountMapper.selectAgentAccountByOpenId(agentFollow.getOpenId());
		SalesmanInfo salesmanInfo = salesmanInfoMapper.selectSalesmanByOpenIdNotDeleteNotDisable(agentFollow.getOpenId());
		String type = null;
		if (agentAccount != null) {
			type = "1";
		} 
		else if (salesmanInfo != null) {
			type = "2";
		}
		agentFollow.setOperatortype(type);
		agentFollow.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return agentFollowMapper.insertSelective(agentFollow) == 1 ? true : false;
	}
	
	
	/**
	 * 删除渠道跟踪记录
	* @author DavidLiang
	* @date 2016年1月18日 下午8:04:47
	* @param agentFollowId  渠道跟踪记录id
	* @param openId  微信标识
	* @return  删除map包装结果
	 */
	public boolean deleteAgentFollow(Integer agentFollowId, String openId) {
		//数据库该原始记录
		AgentFollow originAgentFollow = agentFollowMapper.selectByPrimaryKey(agentFollowId);
		//该操作人openId和数据库原始数据中的openId作比较
		if (openId.equals(originAgentFollow.getOpenId())) {
			originAgentFollow.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			originAgentFollow.setIsDelete("1");
			return agentFollowMapper.updateByPrimaryKeySelective(originAgentFollow) == 1 ? true : false;
		}
		/*
		 * 如果比较失败,符合以下情况也能删除该记录
		 * 1.该操作人是渠道商
		 * 2.原始记录中的openId身份是该操作人(渠道商)门下业务员
		 */
		SalesmanInfo salesman = salesmanInfoMapper.selectSalesmanByAgentOpenIdAndSalesmanOpenId(openId, originAgentFollow.getOpenId());
		if (salesman != null) {
			originAgentFollow.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			originAgentFollow.setIsDelete("1");
			return agentFollowMapper.updateByPrimaryKeySelective(originAgentFollow) == 1 ? true : false;
		}
		return false;
	}

}
