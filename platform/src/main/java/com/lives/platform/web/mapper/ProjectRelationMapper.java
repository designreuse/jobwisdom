package com.lives.platform.web.mapper;

import java.util.List;
import java.util.Map;

import com.lives.platform.web.entity.ProjectRelation;

public interface ProjectRelationMapper {
    int deleteByPrimaryKey(Integer projectRelationId);

    int insert(ProjectRelation record);

    int insertSelective(ProjectRelation record);

    ProjectRelation selectByPrimaryKey(Integer projectRelationId);

    int updateByPrimaryKeySelective(ProjectRelation record);

    int updateByPrimaryKey(ProjectRelation record);
    
    List<ProjectRelation> selectByProperty(ProjectRelation record);
    
    int insertBatch(List<ProjectRelation> records);
    
    int deleteByProjectIdSet(String projectIdSet);
    
    int deleteProjectRelationBatch(List<Integer> projectRelationIds);
    
    List<ProjectRelation> selectByProjectIdSet(Map<String, Object> paramMap);
    
}