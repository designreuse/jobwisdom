package com.lives.manage.web.mapper;

import java.util.List;
import java.util.Map;

import com.lives.manage.web.dto.PrivilegeDto;
import com.lives.manage.web.dto.RoleUtils;
import com.lives.manage.web.dto.TreeDto;
import com.lives.manage.web.entity.Privilege;
import com.lives.manage.web.entity.RolePrivilege;

public interface RolePrivilegeMapper {
    int insert(RolePrivilege record);

    int insertSelective(RolePrivilege record);

	List<Integer> selectPidByRoleId(Integer roleId);
	
	void deleteByRoleId(Integer roleId);

	List<Privilege> selectHasBuild(Integer roleId);

	/**人员授权----查询一级菜单*/
	//List<Privilege> selectHasBuildForMenu(Integer roleId);

	/**人员授权----查询二级菜单*/
	List<Privilege> selectHasBuildForSecondMenu(TreeDto treeUtils);

	/**人员授权----查询元素权限*/
	List<Privilege> selectHasBuildForYs(RoleUtils roleUtils);

	/**人员授权----查询接口权限*/
	List<Privilege> selectHasBuildForGn(RoleUtils roleUtils);

	/**到了人员授权---根据登录用户的信息来显示表*/
	List<Privilege> selectHasBuildForMenu(RoleUtils roleUtils);

	//新加的
	int deleteBecasePid(Integer pId);

	int deleteByRoleIdAndUserId(Map<String, Integer> map);

	List<Privilege> selectElemetByRoleId(Integer roleId);

	List<Privilege> selectGnByRoleId(Integer roleId);

	/*超级管理员获得对应角色下的所有权限*/
	List<PrivilegeDto> selectPrivilegeMenuForAdmin(Integer roleId);
	/*超级管理员获得对应角色下的所有权限*/
	List<PrivilegeDto> selectPriivlegeFunctionForAdmin(Integer roleId);
	/*超级管理员获得对应角色下的所有权限*/
	List<PrivilegeDto> selectPriivlegeElemtForAdmin(Integer roleId);

	/**批量新增*/
	void insertByList(List<RolePrivilege> rolePrivileges);
	
}