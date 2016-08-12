package com.zefun.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.View;
import com.zefun.web.dto.PositionInfoDto;
import com.zefun.web.dto.ProjectCategoryDto;
import com.zefun.web.mapper.PositioninfoMapper;
import com.zefun.web.mapper.ProjectCategoryMapper;

import net.sf.json.JSONArray;

/**
 * 
 * @author laowang
 * @date Oct 22, 2015 10:27:15 AM
 */
@Service
public class BatchSetService {

	/** 项目大项 */
	@Autowired
	private ProjectCategoryMapper projectCategoryMapper;
	/** 岗位信息 */
	@Autowired
	private PositioninfoMapper  positioninfoMapper;

	/**
	 * 初始化项目批量设置页面
	* @author 老王
	* @date 2016年8月11日 下午8:08:43 
	* @param storeId 门店标识
	* @return  ModelAndView
	 */
	public ModelAndView initializationProjectBatchSet (Integer storeId) {
		ModelAndView mav = new ModelAndView(View.BatchSet.VIEW_PROJECT_BATCH_SET);
		List<ProjectCategoryDto> projectCategoryDtoList = projectCategoryMapper.selectProjectCategoryByStoreId(storeId);
		mav.addObject("projectCategoryDtoList", projectCategoryDtoList);
		mav.addObject("projectCategoryDtoListStr", JSONArray.fromObject(projectCategoryDtoList).toString());
		
		//查询门店下所有的岗位
		List<PositionInfoDto> positionInfos = positioninfoMapper.selectPositionEpmployees(storeId);
		mav.addObject("positionInfos", positionInfos);
		mav.addObject("positionInfosStr", JSONArray.fromObject(positionInfos).toString());
		return mav;
	}
}
