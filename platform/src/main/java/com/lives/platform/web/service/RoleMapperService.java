package com.lives.platform.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.Employee;
import com.lives.platform.web.entity.Role;
import com.lives.platform.web.mapper.EmployeeMapper;
import com.lives.platform.web.mapper.RoleMapper;

/**
 * 员工信息表
* @author 老王
* @date 2015年6月8日 上午10:17:17 
*
 */
@Service
public class RoleMapperService {
	@Autowired private RoleMapper roleMapper;
	
	public int deleteRole(Integer roleId){
		return roleMapper.deleteByPrimaryKey(roleId);
	}
	
	
	public int insertRole(Role role){
		return roleMapper.insertSelective(role);
	}
	
	public Role queryRole(Integer roleId){
		return roleMapper.selectByPrimaryKey(roleId);
	}
	
	public int updateRole(Role role){
		return roleMapper.updateByPrimaryKeySelective(role);
	}
	
	public List<Role> selectAll(){
		return roleMapper.selectAll();
	}
}
