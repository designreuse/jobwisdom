package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.ProjectTreatment;

public interface ProjectTreatmentMapper {
    int deleteByPrimaryKey(Integer treatmentCourseId);

    int insert(ProjectTreatment record);

    int insertSelective(ProjectTreatment record);

    ProjectTreatment selectByPrimaryKey(Integer treatmentCourseId);

    int updateByPrimaryKeySelective(ProjectTreatment record);

    int updateByPrimaryKey(ProjectTreatment record);
}