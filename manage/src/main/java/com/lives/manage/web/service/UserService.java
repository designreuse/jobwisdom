package com.lives.manage.web.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lives.manage.common.util.DateUtils;
import com.lives.manage.common.util.StringUtils;
import com.lives.manage.web.entity.Role;
import com.lives.manage.web.entity.User;
import com.lives.manage.web.entity.UserPrivilege;
import com.lives.manage.web.mapper.RolePrivilegeMapper;
import com.lives.manage.web.mapper.UserMapper;
import com.lives.manage.web.mapper.UserPrivilegeMapper;
import com.lives.manage.web.mapper.UserRoleMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RolePrivilegeMapper rolePrivilegeMapper;
	@Autowired
	private UserPrivilegeMapper userPrivilegeMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 上午11:31:25 用户查询
	 */
	public List<User> queryUser(Map<String ,Object> map) {
		List<User> list = new ArrayList<User>();
			list = userMapper.queryUserList(map);
		return list;
	}
	public int queryUserCount(Map<String ,Object> map) {
			int count = userMapper.queryUserCount(map);
		return count;
	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 上午11:31:25 用户查询
	 */
	/** 用户新增 */
	@Transactional
	public int addUser(User user) {
		int count=userMapper.getUserName(user);
		  if(count>0){
			  //说明用户名已经存在
			  return 1; 
		  }
		userMapper.insert(user);
		userMapper.insertUser_role(user);
		initUserPrivilege(user.getUserId(),user.getRoleId());
		/*//获取权限
		List<Integer> pIds = rolePrivilegeMapper.selectPidByRoleId(user.getRoleId());
		List<UserPrivilege> sbl = new ArrayList<UserPrivilege>();
        for (int i = 0; i < pIds.size(); i++) {
            UserPrivilege privilege = new UserPrivilege();
            privilege.setRoleId(pIds.get(i));
            privilege.setUserId(user.getUserId());
            sbl.add(privilege);
        }
        userRoleMapper.insertIntoUserRole(sbl);*/
		return 0;
	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 上午11:31:25 修改用户
	 */
	@Transactional
	public int updateUser(User user) {
	  int count=userMapper.getUserName(user);
	  if(count>0){
		  int id=userMapper.getUserId(user);
		  if(id!=user.getUserId()){
			//说明用户名已经存在
			  return 1;  
		  }
	  }
	  userMapper.updateByPrimaryKeySelective(user);
	  //先删除
	  userMapper.deleteUserRole(user);
	  //再录入角色与用户关系
	  userMapper.insertUser_role(user);
	  
	  userPrivilegeMapper.deleteByUserId(user.getUserId());
	  initUserPrivilege(user.getUserId(),user.getRoleId());
	  
	/*//先删除之前权限
	  userPrivilegeMapper.deleteByUserId(user.getUserId());
	//获取权限
      List<Integer> pIds = rolePrivilegeMapper.selectPidByRoleId(user.getRoleId());
      List<UserPrivilege> sbl = new ArrayList<UserPrivilege>();
      for (int i = 0; i < pIds.size(); i++) {
          UserPrivilege privilege = new UserPrivilege();
          privilege.setRoleId(pIds.get(i));
          privilege.setUserId(user.getUserId());
          sbl.add(privilege);
      }
      userRoleMapper.insertIntoUserRole(sbl);*/
		return 0;
	}
	
	public int deleteUser(User user) {
		  userMapper.updateByPrimaryKeySelective(user);
			return 0;
		}
	
	/**
	 * 初始化人员选择权限直接授权
	* @author 
	* @date 2015年7月13日 上午9:10:33
	* @param userId
	* @param roleId
	 */
	public int initUserPrivilege(Integer userId,Integer roleId){
		List<Integer> pId = rolePrivilegeMapper.selectPidByRoleId(roleId);
		List<UserPrivilege> ls = new ArrayList<UserPrivilege>();
		for (int i = 0; i < pId.size(); i++) {
			UserPrivilege privilege = new UserPrivilege();
			privilege.setPrivilegeId(pId.get(i));
			privilege.setRoleId(roleId);
			privilege.setUserId(userId);
			ls.add(privilege);
		}
		if(ls.size()>0){
			return userPrivilegeMapper.insertList(ls);
		}
		return 0;
	}
	
	public List<Role> getRoleInfosByUserId(Integer userId) {
		List<Role> list=userRoleMapper.getRoleInfosByUserId(userId);
			return list;
		}
	/**
	 * 查询所有角色
	* @author chendb
	* @date 2015年10月31日 下午2:04:07
	* @return
	 */
	public List<Role> getAllRoleInfos() {
        List<Role> list=userRoleMapper.getAllRoleInfos();
            return list;
        }
	
	public Map<String,Object>getUserInfo(Integer userId) {
		Map<String,Object> map=userMapper.getUserInfo(userId);
			return map;
		}
	//修改密码
	public int updatepassword(String userName,String oldpassword,String newpassword){
		User user=new User();
		user.setUserName(userName);
		Map<String,Object> usermap = userMapper.loginUser(user);
		String passWord=usermap.get("passWord").toString();
		String salt=usermap.get("salt").toString();
		//判断当密码不对时候返回
		if (!StringUtils.MD5(oldpassword + salt).equals(passWord)) {
			return 1;
		}
		newpassword=StringUtils.MD5(newpassword + salt);
		user.setPassWord(newpassword);
		userMapper.updatepassword(user);
		
		return 0;
	}
	//密码初始化
		public int initpassword(Integer userId){
			User user=new User();
			String password = "123456";
			String hash = StringUtils.encryptPwd(password);
			password = hash.split(":")[0];
			String salt = hash.split(":")[1];
			
			user.setPassWord(password);
			user.setSalt(salt);
			user.setUserId(userId);
			userMapper.initpassword(user);
			
			return 0;
		}
		
		//账户充值
		@Transactional
		public int recharge(Map<String,Object> map){
			//获取账户余额
			Map<String,Object> moneymap=userMapper.getUserMoney(map);
			String money=moneymap.get("money").toString();
			BigDecimal m1=new BigDecimal(money); 
			//修改帐号金额（加上现在的新增金额）
			String money1=map.get("money").toString();
			BigDecimal m2=new BigDecimal(money1);
			BigDecimal m=m1.add(m2);
			map.put("money", m);
			userMapper.updateMoney(map);
			//记录入流水表
			map.put("waterType", 1);
			map.put("dsc", "帐号充值");
			map.put("money", m2);
			map.put("createTime", DateUtils.getCurTime());
			userMapper.insertWater(map);
			return 0;
		}
	
}
