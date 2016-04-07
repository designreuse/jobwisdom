package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.ProjectComboTreatment;

public interface ProjectComboTreatmentMapper {
    int deleteByPrimaryKey(Integer treatmentCourseDetailId);

    int insert(ProjectComboTreatment record);

    int insertSelective(ProjectComboTreatment record);

    ProjectComboTreatment selectByPrimaryKey(Integer treatmentCourseDetailId);

    int updateByPrimaryKeySelective(ProjectComboTreatment record);

    int updateByPrimaryKey(ProjectComboTreatment record);
}