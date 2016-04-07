package com.lives.manage.web.mapper;

import java.util.List;
import java.util.Map;

import com.lives.manage.web.dto.MenuDto;
import com.lives.manage.web.dto.RoleUtils;
import com.lives.manage.web.entity.Privilege;
import com.lives.manage.web.entity.UserPrivilege;

public interface UserPrivilegeMapper {
    int insert(UserPrivilege record);

    int insertSelective(UserPrivilege record);

	void deleteByUserId(Integer userId);

	/**根据userId查询pId,用于显示已经选择的权限*/
	List<Integer> selectPidByUserId(Integer uId);

	/**根据userId查询权限,用于显示一级菜单*/
	List<Privilege> getFirstMenu(Integer userId);
	
	/**根据ref_privilege_id查询权限,用于显示二级菜单*/
	List<Privilege> getSecondMenu(RoleUtils roleUtils);

	/**查询页面元素权限*/
	List<Privilege> getPageClass(Integer userId);

	/**查询接口权限*/
	List<String> getGn(Integer userId);
	
	/**批量新增*/
	int insertList(List<UserPrivilege> userPrivilege);
	
	/**根据userId和roleId查询pId,用于显示已经选择的权限*/
	List<Integer> selectPidByUserIdAndRoleId(RoleUtils roleUtils);

	/**根据userId和roleId删除权限*/
	int deleteByRoleIdAndUserId(RoleUtils roleUtils);

	int deleteByPid(Integer pId);
	
	/** 查询用户得所有菜单权限 */
	List<MenuDto> selectMenuListByUserId(Integer userId);
	
	/** 根据用户id跟权限类型查询功能与元素权限*/
	List<String> selectPrivilegeByUserIdAndType(Map<String, Object> map);

	/**超级管理员给人分配权限,自身将所有的权限开发出来*/
	List<Privilege> selectMenuListByRoleId(Integer roleId);

	List<Privilege> selectSecondMenuListByRoleId(Map<String, Integer> map);

	void deleteByRoleId(Integer roleId);

}