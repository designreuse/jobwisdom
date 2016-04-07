package com.lives.platform.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.ProjectPlate;
import com.lives.platform.web.mapper.ProjectPlateMapper;

/**
 * 项目板块表
* @author 洪秋霞
* @date 2015年6月8日 上午10:17:46 
*
 */
@Service
public class ProjectPlateService {

	@Autowired private ProjectPlateMapper projectPlateMapper;
	
	public void deleteProjectPlate (Integer plateId){
		projectPlateMapper.deleteByPrimaryKey(plateId);
	}
	
	public int insertProjectPlate(ProjectPlate projectPlate){
		return projectPlateMapper.insertSelective(projectPlate);
	}
	
	public ProjectPlate queryProjectPlate(Integer plateId){
		return projectPlateMapper.selectByPrimaryKey(plateId);
	}
	
	public int updateProjectPlate(ProjectPlate projectPlate){
		return projectPlateMapper.updateByPrimaryKeySelective(projectPlate);
	}
	
	public List<ProjectPlate> queryProjectPlateList(Integer storesId){
		return projectPlateMapper.selectByStoresIdList(storesId);
	}
}

