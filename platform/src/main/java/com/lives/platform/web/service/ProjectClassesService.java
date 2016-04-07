package com.lives.platform.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lives.platform.common.util.DateUtils;
import com.lives.platform.web.entity.ProjectClasses;
import com.lives.platform.web.entity.StoresManageRelation;
import com.lives.platform.web.mapper.ProjectClassesMapper;
import com.lives.platform.web.mapper.StoresManageRelationMapper;

/**
 * 项目类别表
* @author 洪秋霞
* @date 2015年6月8日 上午10:17:38 
*
 */
@Service
public class ProjectClassesService {
	@Autowired private ProjectClassesMapper projectClassesMapper;
	@Autowired private StoresManageRelationMapper storesManageRelationMapper;
	
	public void deleteProjectClasses (Integer classesId){
		//删除类别 默认删除分店下的所有类别
		ProjectClasses projectClasses = new ProjectClasses();
		projectClasses.setParentClassesId(classesId);
		//分店下的类别列表
		List<ProjectClasses> projectClassesList = projectClassesMapper.selectByProperty(projectClasses);
		List<Integer> classesIds = new ArrayList<Integer>();
		classesIds.add(classesId);
		for (ProjectClasses classes : projectClassesList) {
			classesIds.add(classes.getClassesId());
		}
		if(classesIds.size() > 0){
			projectClassesMapper.deleteClassesIdBatch(classesIds);
		}
//		projectClassesMapper.deleteByPrimaryKey(classesId);
	}
	
	public int insertProjectClasses(ProjectClasses projectClasses){
		return projectClassesMapper.insertSelective(projectClasses);
	}
	
	public ProjectClasses queryProjectClasses(Integer classesId){
		return projectClassesMapper.selectByPrimaryKey(classesId);
	}
	
	public int updateProjectClasses(ProjectClasses projectClasses){
		return projectClassesMapper.updateByPrimaryKeySelective(projectClasses);
	}
	
	public List<ProjectClasses> queryProjectClassesList(Integer storesId){
		return projectClassesMapper.selectByStoresIdList(storesId);
	}
	
	/**
	 * 根据多个门店id查询项目类别列表
	* @author 洪秋霞
	* @date 2015年7月10日 下午5:59:16
	* @param classesIdList
	* @return
	 */
	public List<ProjectClasses> selectByProjectstoresIdList(Map<String, Object> paramMap){
		return projectClassesMapper.selectByProjectstoresIdList(paramMap);
	}
	
	/**
	 * 动态查询
	* @author 洪秋霞
	* @date 2015年7月7日 下午2:19:51
	* @param projectClasses
	* @return
	 */
	public List<ProjectClasses> selectByProperty(ProjectClasses projectClasses){
		return projectClassesMapper.selectByProperty(projectClasses);
	}
	
	@Transactional
	public void saveProjectClasses(Integer plateId,Integer classesId,String classesName){
		List<ProjectClasses> projectClassesList = new ArrayList<ProjectClasses>();
		
		ProjectClasses classes = new ProjectClasses();
		classes.setPlateId(plateId);
		classes.setClassesName(classesName);
		classes.setStoresId(1);//从session获取门店id
		classes.setType(1);
		classes.setCreateTime(DateUtils.getCurTime());
//		projectClassesList.add(classes);
		projectClassesMapper.insertSelective(classes);
		
		List<StoresManageRelation> storesManageRelationList = storesManageRelationMapper.selectByStoresAccountId(1);//登录的门店id
		if(storesManageRelationList.size()>0){
			for (int j = 0; j < storesManageRelationList.size(); j++) {
				ProjectClasses projectClasses = new ProjectClasses();
				projectClasses.setPlateId(plateId);
				projectClasses.setClassesName(classesName);
				projectClasses.setStoresId(storesManageRelationList.get(j).getItemid2());
				projectClasses.setParentClassesId(classes.getClassesId());
				projectClasses.setType(1);
				projectClasses.setCreateTime(DateUtils.getCurTime());
				projectClassesList.add(projectClasses);
//				projectClassesMapper.insertSelective(projectClasses);
			}
		}
		if(projectClassesList.size() > 0){
			projectClassesMapper.insertClassesBatch(projectClassesList);
		}
	}
	
	
	
}
