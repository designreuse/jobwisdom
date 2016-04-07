package com.lives.platform.web.mapper;

import java.util.List;

import com.lives.platform.web.entity.ProjectPlate;

public interface ProjectPlateMapper {
    int deleteByPrimaryKey(Integer plateId);

    int insert(ProjectPlate record);

    int insertSelective(ProjectPlate record);

    ProjectPlate selectByPrimaryKey(Integer plateId);

    int updateByPrimaryKeySelective(ProjectPlate record);

    int updateByPrimaryKey(ProjectPlate record);
    
    List<ProjectPlate> selectByStoresIdList(Integer storesId);
}