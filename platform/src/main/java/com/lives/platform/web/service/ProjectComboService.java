package com.lives.platform.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.ProjectCombo;
import com.lives.platform.web.mapper.ProjectComboMapper;
/**
 * 项目套餐表
* @author 洪秋霞
* @date 2015年6月8日 上午10:17:26 
*
 */
@Service
public class ProjectComboService {

	@Autowired private ProjectComboMapper projectComboMapper;
	
	public void deleteCommodityInfo (Integer comboId){
		projectComboMapper.deleteByPrimaryKey(comboId);
	}
	
	public int insertProjectCombo(ProjectCombo projectCombo){
		return projectComboMapper.insertSelective(projectCombo);
	}
	
	public ProjectCombo queryProjectCombo(Integer comboId){
		return projectComboMapper.selectByPrimaryKey(comboId);
	}
	
	public int updateProjectCombo(ProjectCombo projectCombo){
		return projectComboMapper.updateByPrimaryKeySelective(projectCombo);
	}
	
	public List<ProjectCombo> queryProjectComboList(Integer storesId){
		return projectComboMapper.selectByStoresIdList(storesId);
	}
}
