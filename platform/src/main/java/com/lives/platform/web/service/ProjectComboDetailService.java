package com.lives.platform.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.ProjectComboDetail;
import com.lives.platform.web.mapper.ProjectComboDetailMapper;
/**
 * 项目套餐明细表
* @author 洪秋霞
* @date 2015年6月8日 上午10:17:32 
*
 */
@Service
public class ProjectComboDetailService {

	@Autowired private ProjectComboDetailMapper projectComboDetailMapper;
	
	public void deleteProjectComboDetail(Integer comboDetailId){
		projectComboDetailMapper.deleteByPrimaryKey(comboDetailId);
	}
	
	public int insertProjectComboDetail(ProjectComboDetail projectComboDetail){
		return projectComboDetailMapper.insertSelective(projectComboDetail);
	}
	
	public ProjectComboDetail queryProjectComboDetail(Integer comboDetailId){
		return projectComboDetailMapper.selectByPrimaryKey(comboDetailId);
	}
	
	public int updateProjectComboDetail(ProjectComboDetail projectComboDetail){
		return projectComboDetailMapper.updateByPrimaryKeySelective(projectComboDetail);
	}
	
	public List<ProjectComboDetail> queryProjectComboDetailList(Integer storesId){
		return projectComboDetailMapper.selectByStoresIdList(storesId);
	}
}
