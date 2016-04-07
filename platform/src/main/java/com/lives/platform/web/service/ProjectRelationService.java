package com.lives.platform.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.ProjectRelation;
import com.lives.platform.web.mapper.ProjectRelationMapper;

@Service
public class ProjectRelationService {

	@Autowired private ProjectRelationMapper projectRelationMapper;
	
	/**
	 * 动态查询
	* @author 洪秋霞
	* @date 2015年7月4日 下午12:42:48
	* @param projectRelation
	* @return
	 */
	public List<ProjectRelation> selectByProperty(ProjectRelation projectRelation){
		return projectRelationMapper.selectByProperty(projectRelation);
	}
	
	public List<ProjectRelation> selectByProjectIdSet(Map<String, Object> paramMap){
		return projectRelationMapper.selectByProjectIdSet(paramMap);
	}
}
