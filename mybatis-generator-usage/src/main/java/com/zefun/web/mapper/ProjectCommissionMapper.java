package com.zefun.web.mapper;

import com.zefun.web.entity.ProjectCommission;

public interface ProjectCommissionMapper {
    int deleteByPrimaryKey(Integer commissionId);

    int insert(ProjectCommission record);

    int insertSelective(ProjectCommission record);

    ProjectCommission selectByPrimaryKey(Integer commissionId);

    int updateByPrimaryKeySelective(ProjectCommission record);

    int updateByPrimaryKey(ProjectCommission record);
}