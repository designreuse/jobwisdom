package com.lives.manage.web.mapper;

import com.lives.manage.web.entity.RoleMenuRelation;

public interface RoleMenuRelationMapper {
    int deleteByPrimaryKey(Integer relationId);

    int insert(RoleMenuRelation record);

    int insertSelective(RoleMenuRelation record);

    RoleMenuRelation selectByPrimaryKey(Integer relationId);

    int updateByPrimaryKeySelective(RoleMenuRelation record);

    int updateByPrimaryKey(RoleMenuRelation record);
    
    int deleteRoleMenu(RoleMenuRelation record);
    
    int addRoleMenu(RoleMenuRelation record);
}