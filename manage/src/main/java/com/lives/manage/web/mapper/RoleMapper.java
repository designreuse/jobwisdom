package com.lives.manage.web.mapper;

import java.util.List;

import com.lives.manage.web.dto.RoleDto;
import com.lives.manage.web.entity.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	int selectCount();

	List<Role> selectAllRole();

	/**人员授权页面的权限查看,只查看该登录用户已经有的*/
	List<Role> selectRoleByUserId(Integer userId);

	/** bootstrap 的树形*/
	RoleDto selectRoleBookstrap(Integer roleId);

	/**超级管理员获得了所有的角色*/
	List<Integer> selectAllRoleId();
	
	
	int selectRoleId(String roleName);
}