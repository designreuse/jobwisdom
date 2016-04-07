package com.lives.platform.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.ProjectTreatment;
import com.lives.platform.web.mapper.ProjectTreatmentMapper;
/**
 * 项目疗程表
* @author 洪秋霞
* @date 2015年6月8日 上午10:16:05 
*
 */
@Service
public class ProjectTreatmentService {

	@Autowired private ProjectTreatmentMapper projectTreatmentMapper;
	
	public void deleteProjectTreatment (Integer treatmentCourseId){
		projectTreatmentMapper.deleteByPrimaryKey(treatmentCourseId);
	}
	
	public int insertProjectTreatment(ProjectTreatment projectTreatment){
		return projectTreatmentMapper.insertSelective(projectTreatment);
	}
	
	public ProjectTreatment queryProjectTreatment(Integer treatmentCourseId){
		return projectTreatmentMapper.selectByPrimaryKey(treatmentCourseId);
	}
	
	public int updateProjectTreatment(ProjectTreatment projectTreatment){
		return projectTreatmentMapper.updateByPrimaryKeySelective(projectTreatment);
	}
}
