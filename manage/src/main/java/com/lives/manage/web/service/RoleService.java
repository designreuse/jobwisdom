package com.lives.manage.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lives.manage.web.dto.RoleInfoDto;
import com.lives.manage.web.entity.RoleInfo;
import com.lives.manage.web.entity.RoleMenuRelation;
import com.lives.manage.web.mapper.RoleInfoMapper;
import com.lives.manage.web.mapper.RoleMapper;
import com.lives.manage.web.mapper.RoleMenuRelationMapper;

@Service
public class RoleService {
	@Autowired
	private RoleInfoMapper roleInfoMapper;
	@Autowired
	private RoleMenuRelationMapper roleMenuRelationMapper;
	@Autowired
	private RoleMapper roleMapper;

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 上午11:31:25 新增角色
	 */
	public String addRole(RoleInfo roleInfo) {
		roleInfoMapper.insert(roleInfo);
		return null;
	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 上午11:31:25 修改角色
	 */
	public String updateRole(RoleInfo roleInfo) {
		roleInfoMapper.updateByPrimaryKeySelective(roleInfo);
		return null;

	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 上午11:31:25 角色查询
	 */
	public List<RoleInfoDto> queryRole(RoleInfo roleInfo) {

		List<RoleInfoDto> list = roleInfoMapper.queryRole(roleInfo);
		return list;
	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 上午11:31:25 删除角色拥有的菜单
	 */
	public String deleteRoleMenu(RoleMenuRelation roleMenuRelation) {

		roleMenuRelationMapper.deleteRoleMenu(roleMenuRelation);

		return null;

	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 上午11:31:25 角色配置菜单
	 */
	@Transactional
	public String addRoleMenu(Integer roleId, String menuIdList) {

		RoleMenuRelation roleMenuRelation = new RoleMenuRelation();
		roleMenuRelation.setRoleId(roleId);
		roleMenuRelationMapper.deleteRoleMenu(roleMenuRelation);

		// 先清空之前角色所绑定的所有菜单
		String[] menuIdsz = menuIdList.split(",");
		// 循环获取出菜单
		for (int i = 0; i < menuIdsz.length; i++) {
			// 菜单跟角色进行绑定操作
			roleMenuRelation = new RoleMenuRelation();
			String number = menuIdsz[i];
			// 获取的id进行转换
			int menuId = Integer.parseInt(number);
			roleMenuRelation.setMenuId(menuId);
			roleMenuRelation.setRoleId(roleId);
			// 录入到
			roleMenuRelationMapper.addRoleMenu(roleMenuRelation);
		}

		return null;

	}
	
	public int selectRoleId(String roleName){
		int id=roleMapper.selectRoleId(roleName);
		return id;
	}

}
