package com.lives.platform.web.mapper;

import java.util.List;

import com.lives.platform.web.entity.ProjectComboDetail;

public interface ProjectComboDetailMapper {
    int deleteByPrimaryKey(Integer comboDetailId);

    int insert(ProjectComboDetail record);

    int insertSelective(ProjectComboDetail record);

    ProjectComboDetail selectByPrimaryKey(Integer comboDetailId);

    int updateByPrimaryKeySelective(ProjectComboDetail record);

    int updateByPrimaryKey(ProjectComboDetail record);
    
    List<ProjectComboDetail> selectByStoresIdList(Integer storesId);
}