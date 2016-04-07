package com.lives.platform.web.mapper;

import java.util.List;
import java.util.Map;

import com.lives.platform.web.entity.ProjectClasses;

public interface ProjectClassesMapper {
    int deleteByPrimaryKey(Integer classesId);

    int insert(ProjectClasses projectClasses);

    int insertSelective(ProjectClasses projectClasses);

    ProjectClasses selectByPrimaryKey(Integer classesId);

    int updateByPrimaryKeySelective(ProjectClasses projectClasses);

    int updateByPrimaryKey(ProjectClasses projectClasses);
    
    List<ProjectClasses> selectByStoresIdList(Integer storesId);
    
    List<ProjectClasses> selectByProperty(ProjectClasses projectClasses);
    
    List<ProjectClasses> selectByProjectstoresIdList(Map<String, Object> paramMap);
    
    int insertClassesBatch(List<ProjectClasses> projectClassesList);
    
    int deleteClassesIdBatch(List<Integer> classesIds);
}