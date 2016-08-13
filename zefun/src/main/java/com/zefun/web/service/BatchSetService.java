package com.zefun.web.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.PositionInfoDto;
import com.zefun.web.dto.ProjectCategoryDto;
import com.zefun.web.entity.EmployeeLevel;
import com.zefun.web.entity.ProjectCommission;
import com.zefun.web.entity.ProjectStep;
import com.zefun.web.mapper.EmployeeLevelMapper;
import com.zefun.web.mapper.PositioninfoMapper;
import com.zefun.web.mapper.ProjectCategoryMapper;
import com.zefun.web.mapper.ProjectCommissionMapper;
import com.zefun.web.mapper.ProjectStepMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
	/** 项目岗位业绩 */
	@Autowired
	private ProjectStepMapper projectStepMapper;
	/** 项目岗位提成 */
	@Autowired
	private ProjectCommissionMapper projectCommissionMapper;
	/** 项目职位 */
	@Autowired
	private EmployeeLevelMapper employeeLevelMapper;

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
	
	/**
	 * 保存批量设置业绩
	* @author 老王
	* @date 2016年8月12日 下午6:11:29 
	* @param projectIdListStr projectIdListStr
	* @param calculateListStr calculateListStr
	* @param lastOperatorId 最后操作人
	* @return BaseDto
	 */
	public BaseDto batchSetCalculate (String projectIdListStr, String calculateListStr, Integer lastOperatorId) {
		JSONArray projectIdList = JSONArray.fromObject(projectIdListStr);
		
		String[] projectList = (String[])JSONArray.toCollection(projectIdList).toArray(new String[]{});  
		
		
		
		//根据项目查询项目对应所有的岗位提成
		List<ProjectStep> projectStepList = projectStepMapper.selectAppointStepByProjectId(projectList);
		for (int i = 0; i < projectIdList.size(); i++) {
			Integer projectId = projectIdList.getInt(i);
			
			JSONArray calculateList = JSONArray.fromObject(calculateListStr);
			
			for (int j = 0; j < calculateList.size(); j++) {
				JSONObject calculate = calculateList.getJSONObject(j);
				Integer positionId = calculate.getInt("positionId");
				Integer stepPerformanceType = calculate.getInt("stepPerformanceType");
				BigDecimal stepPerformance = new BigDecimal(calculate.getString("stepPerformance"));
				
				List<ProjectStep> list = projectStepList.parallelStream().filter(p -> p.getProjectId().intValue() == projectId.intValue() 
						  && p.getPositionId().intValue() == positionId.intValue())
						  .collect(Collectors.toList());
				
				if (list.isEmpty() || list.size() == 0) {
					ProjectStep projectStep = new ProjectStep();
					projectStep.setProjectId(projectId);
					projectStep.setStepPerformanceType(stepPerformanceType);
					projectStep.setStepPerformance(stepPerformance);
					projectStep.setPositionId(positionId);
					projectStep.setCreateTime(DateUtil.getCurTime());
					projectStep.setLastOperatorId(lastOperatorId);
					projectStepMapper.insert(projectStep);
				}
				else {
					ProjectStep obj = list.get(0);
					ProjectStep projectStep = new ProjectStep();
					projectStep.setProjectStepId(obj.getProjectStepId());
					projectStep.setProjectId(projectId);
					projectStep.setStepPerformanceType(stepPerformanceType);
					projectStep.setStepPerformance(stepPerformance);
					projectStep.setPositionId(positionId);
					projectStep.setCreateTime(DateUtil.getCurTime());
					projectStep.setLastOperatorId(lastOperatorId);
					projectStepMapper.updateByPrimaryKey(projectStep);
				}
			}
		}
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
	}
	
	/**
	 * 保存批量设置提成
	* @author 老王
	* @date 2016年8月13日 下午2:10:03 
	* @param commissionListStr commissionListStr
	* @param projectIdListStr projectIdListStr
	* @param lastOperatorId lastOperatorId
	* @param storeId 门店标识
	* @return BaseDto
	 */
	public BaseDto batchSetCommission (String projectIdListStr, String commissionListStr, Integer lastOperatorId, Integer storeId) {
		
		JSONArray projectIdList = JSONArray.fromObject(projectIdListStr);
		
		String[] projectList = (String[])JSONArray.toCollection(projectIdList).toArray(new String[]{});  
		
		List<EmployeeLevel> levelStores = employeeLevelMapper.selectAllByStoreId(storeId);
		
		//根据项目查询项目对应所有的岗位提成
		List<ProjectCommission> projectCommissionList = projectCommissionMapper.selectByProjectIdList(projectList);
		for (int i = 0; i < projectIdList.size(); i++) {
			Integer projectId = projectIdList.getInt(i);
			
			JSONArray commissionList = JSONArray.fromObject(commissionListStr);
			
			for (int j = 0; j < commissionList.size(); j++) {
				JSONObject commission = commissionList.getJSONObject(j);
				Integer assignCashType = commission.getInt("assignCashType");
				
				Integer commissionCash = commission.getInt("commissionCash");
				Integer commissionNoCash = commission.getInt("commissionNoCash");
				
				Integer commissionCourse = commission.getInt("commissionCourse");
				Integer commissionNoCourse = commission.getInt("commissionNoCourse");
				
				Integer commissionGold = commission.getInt("commissionGold");
				Integer commissionNoGold = commission.getInt("commissionNoGold");
				
				Integer commissionCard = commission.getInt("commissionCard");
				
				
				String levelIdListStr = commission.getString("levelIdList");
				
				String[] levelIdList = (String[])JSONArray.toCollection(JSONArray.fromObject(levelIdListStr)).toArray(new String[]{}); 
				
				for (int k = 0; k < levelIdList.length; k++) {
					Integer levelId = Integer.valueOf(levelIdList[k]);
					
					List<ProjectCommission> list 
					          = projectCommissionList.parallelStream().filter(p -> p.getProjectId().intValue() == projectId.intValue() 
							  && p.getLevelId().intValue() == levelId.intValue())
							  .collect(Collectors.toList());
					
					if (list.isEmpty() || list.size() == 0) {
						ProjectCommission projectCommission = new ProjectCommission();
						projectCommission.setProjectId(projectId);
						projectCommission.setLevelId(levelId);
						List<EmployeeLevel> employeeLevel 
						          = levelStores.parallelStream().filter(p -> p.getLevelId().intValue() == levelId.intValue())
								  .collect(Collectors.toList());
						projectCommission.setPositionId(employeeLevel.get(0).getPositionId());
						
						projectCommission.setAssignCashType(assignCashType);
						projectCommission.setCommissionCard(commissionCard);
						projectCommission.setCommissionCash(commissionCash);
						projectCommission.setCommissionNoCash(commissionNoCash);
						projectCommission.setCommissionCourse(commissionCourse);
						projectCommission.setCommissionNoCourse(commissionNoCourse);
						projectCommission.setCommissionGold(commissionGold);
						projectCommission.setCommissionNoGold(commissionNoGold);
						projectCommission.setUpdateTime(DateUtil.getCurTime());
						projectCommission.setLastOperatorId(lastOperatorId);
						projectCommissionMapper.insertSelective(projectCommission);
					}
					else {
						ProjectCommission obj = list.get(0);
						ProjectCommission projectCommission = new ProjectCommission();
						projectCommission.setCommissionId(obj.getCommissionId());
						projectCommission.setAssignCashType(assignCashType);
						projectCommission.setCommissionCard(commissionCard);
						projectCommission.setCommissionCash(commissionCash);
						projectCommission.setCommissionNoCash(commissionNoCash);
						projectCommission.setCommissionCourse(commissionCourse);
						projectCommission.setCommissionNoCourse(commissionNoCourse);
						projectCommission.setCommissionGold(commissionGold);
						projectCommission.setCommissionNoGold(commissionNoGold);
						projectCommission.setUpdateTime(DateUtil.getCurTime());
						projectCommission.setLastOperatorId(lastOperatorId);
						projectCommissionMapper.updateByPrimaryKeySelective(projectCommission);
					}
				}
			}
		}
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
	}
}
