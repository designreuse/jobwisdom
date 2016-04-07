package com.lives.platform.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.UserInfo;
import com.lives.platform.web.mapper.UserInfoMapper;

/**
 * 提成表
* @author 洪秋霞
* @date 2015年6月8日 上午10:17:17 
*
 */
@Service
public class UserInfoService {
	
	@Autowired 
	private UserInfoMapper userInfoMapper;
	
	public int deleteUserInfo(Integer userInfoId){
		return userInfoMapper.deleteByPrimaryKey(userInfoId);
	}
	
	
	public int insertUserInfo(UserInfo userInfo){
		return userInfoMapper.insertSelective(userInfo);
	}
	
	public UserInfo queryUserInfo(Integer userInfoId){
		return userInfoMapper.selectByPrimaryKey(userInfoId);
	}
	
	public int updateUserInfo(UserInfo userInfo){
		return userInfoMapper.updateByPrimaryKey(userInfo);
	}
	
	public int deleteEmployeeId(Integer employeeId){
		return userInfoMapper.deleteEmployeeId(employeeId);
	}
}
