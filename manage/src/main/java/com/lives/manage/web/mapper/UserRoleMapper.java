package com.lives.manage.web.mapper;

import java.util.List;

import com.lives.manage.web.entity.Role;
import com.lives.manage.web.entity.UserPrivilege;
import com.lives.manage.web.entity.UserRole;
import com.lives.manage.web.entity.UserRoleUtils;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);

	//List<UserRoleUtils> getAllUserRole();
	
	/**删除角色时，将人员绑定信息删除*/
	int deleteByRoleId(Integer roleId);

	int deleteByUserId(Integer userId);

	List<Integer> getRoleByUserId(Integer userId);

	void insertIntoUserRole(List<UserPrivilege> sbl);
	
	/** 兵哥用的 */
	List<Role> getRoleInfosByUserId(Integer userId);
	/**
	 * 获取所有的角色
	* @author chendb
	* @date 2015年10月31日 下午2:00:54
	* @return List<Role>
	 */
	List<Role> getAllRoleInfos();

}