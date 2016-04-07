package com.lives.manage.web.mapper;

import java.util.List;
import java.util.Map;

import com.lives.manage.web.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	List<User> selectUsers();
	
	List<User> queryUserList(Map<String ,Object> map);
	int queryUserCount(Map<String ,Object> map);
	//获取用户名是否存在
	int getUserName(User record);
	int getUserId(User record);
	
	//角色 用户关联表录入
	int insertUser_role(User record);
	
	int deleteUserRole(User record);

	Map<String,Object> loginUser(User user);

	/**获取超级管理员的Id*/
	User selectSuperMan(String userName);

	Map<String,Object> getUserInfo(Integer userId);
	
	int updatepassword(User record);
	
	int initpassword(User record);
	/**获取用户金额*/
	Map<String,Object> getUserMoney(Map<String ,Object> map);
	/**新增金额*/
	int updateMoney(Map<String ,Object> map);
	/**新增流水*/
	int insertWater(Map<String ,Object> map);
	
	List<User>querychannelUser(Map<String ,Object> map);
	
	int countchannelUser(Map<String ,Object> map);
	
	
	
}