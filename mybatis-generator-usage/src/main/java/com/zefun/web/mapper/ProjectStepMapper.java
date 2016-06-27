package com.zefun.web.mapper;

import com.zefun.web.entity.ProjectStep;

public interface ProjectStepMapper {
    int deleteByPrimaryKey(Integer projectStepId);

    int insert(ProjectStep record);

    int insertSelective(ProjectStep record);

    ProjectStep selectByPrimaryKey(Integer projectStepId);

    int updateByPrimaryKeySelective(ProjectStep record);

    int updateByPrimaryKey(ProjectStep record);
}