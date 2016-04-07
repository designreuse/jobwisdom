package com.lives.platform.web.mapper;

import java.util.List;

import com.lives.platform.web.entity.ProjectCombo;

public interface ProjectComboMapper {
    int deleteByPrimaryKey(Integer comboId);

    int insert(ProjectCombo record);

    int insertSelective(ProjectCombo record);

    ProjectCombo selectByPrimaryKey(Integer comboId);

    int updateByPrimaryKeySelective(ProjectCombo record);

    int updateByPrimaryKey(ProjectCombo record);
    
    List<ProjectCombo> selectByStoresIdList(Integer storesId);
}