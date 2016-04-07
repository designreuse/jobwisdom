package com.lives.platform.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.ProjectComboTreatment;
import com.lives.platform.web.mapper.ProjectComboTreatmentMapper;
/**
 * 项目疗程明细表
* @author 洪秋霞
* @date 2015年6月8日 上午10:16:41 
*
 */
@Service
public class ProjectTreatmentDetailService {

	@Autowired private ProjectComboTreatmentMapper projectComboTreatmentMapper;
	
	public void deleteProjectComboTreatment(Integer treatmentCourseDetailId){
		projectComboTreatmentMapper.deleteByPrimaryKey(treatmentCourseDetailId);
	}
	
	public int insertProjectComboTreatment(ProjectComboTreatment projectComboTreatment){
		return projectComboTreatmentMapper.insertSelective(projectComboTreatment);
	}
	
	public ProjectComboTreatment queryProjectComboTreatment(Integer treatmentCourseDetailId){
		return projectComboTreatmentMapper.selectByPrimaryKey(treatmentCourseDetailId);
	}
	
	public int update(ProjectComboTreatment projectComboTreatment){
		return projectComboTreatmentMapper.updateByPrimaryKeySelective(projectComboTreatment);
	}
}
