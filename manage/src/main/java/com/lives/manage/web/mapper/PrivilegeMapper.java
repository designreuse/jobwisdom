package com.lives.manage.web.mapper;

import java.util.List;
import java.util.Map;

import com.lives.manage.web.dto.MenuDto;
import com.lives.manage.web.dto.PrivilegeDto;
import com.lives.manage.web.dto.PrivilegeInfoDto;
import com.lives.manage.web.entity.Privilege;

public interface PrivilegeMapper {
    int deleteByPrimaryKey(Integer privilegeId);

    int insert(Privilege record);

    int insertSelective(Privilege record);

    Privilege selectByPrimaryKey(Integer privilegeId);

    int updateByPrimaryKeySelective(Privilege record);

    int updateByPrimaryKey(Privilege record);

    /**查询一级菜单*/
	List<Privilege> selectFirstMenu();
	/**根据一级查询二级菜单*/
	List<Privilege> selectSecondPrivileges(Integer id);
	
	List<Privilege> getAllPrivilegesCd();
	/**查询接口权限*/
	List<Privilege> getAllPrivilegesGn();
	/**查询元素权限*/
	List<Privilege> getAllPrivilegesYs();

	/**bootstrap 获取树形*/
	List<PrivilegeDto> selectBootstrapPrivilegeMenu();
	/**bootstrap 获取树形 type 0 代表菜单 1代表功能2代表元素*/
	List<PrivilegeDto> selectPrivilege(Integer type);
	/**bootstrap 获取树形 用于人员授权*/
	List<PrivilegeDto> selectFirstBootstrapPrivilegeMenu(
			Map<String, Integer> map);
	/**bootstrap 获取树形 用于人员授权*/
	List<PrivilegeDto> selectBootstrapPriivlegeFunction(Map<String, Integer> map);
	/**bootstrap 获取树形 用于人员授权*/
	List<PrivilegeDto> selectBootstrapPriivlegeElemt(Map<String, Integer> map);

	List<PrivilegeInfoDto> selectBootstrapTablePrivilegeMenu(Map<String, Object> map);

	List<PrivilegeDto> selectPrivilegForGn(Map<String, Object> mp);

	List<PrivilegeInfoDto> selectPrivilegForYs(Map<String, Object> mp);

	int selectPrivilegeMenuCount(Map<String, Object> mp);

	/**超级管理员获得所有的菜单*/
	List<MenuDto> selectMenuListForSuperMan();

}