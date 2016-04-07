package com.lives.manage.web.mapper;

import java.util.List;

import com.lives.manage.web.dto.RoleInfoDto;
import com.lives.manage.web.entity.RoleInfo;

public interface RoleInfoMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);
    
    List<RoleInfoDto> queryRole(RoleInfo record);
}