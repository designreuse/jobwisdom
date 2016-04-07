package com.lives.platform.web.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lives.platform.common.util.DateUtils;
import com.lives.platform.web.dto.PositionDto;
import com.lives.platform.web.dto.ProjectClassDto;
import com.lives.platform.web.entity.Dommission;
import com.lives.platform.web.entity.Employee;
import com.lives.platform.web.entity.ProjectClasses;
import com.lives.platform.web.entity.ProjectInfo;
import com.lives.platform.web.entity.ProjectRelation;
import com.lives.platform.web.entity.Stores;
import com.lives.platform.web.mapper.DommissionMapper;
import com.lives.platform.web.mapper.EmployeeMapper;
import com.lives.platform.web.mapper.ProjectClassesMapper;
import com.lives.platform.web.mapper.ProjectInfoMapper;
import com.lives.platform.web.mapper.ProjectRelationMapper;

/**
 * 项目信息
* @author 洪秋霞
* @date 2015年7月3日 下午5:44:04 
*
 */
@Service
public class ProjectInfoService {

	@Autowired ProjectInfoMapper projectInfoMapper;
	@Autowired DommissionMapper dommissionMapper;
	@Autowired EmployeeMapper employeeMapper;
	@Autowired ProjectRelationMapper projectRelationMapper;
	@Autowired ProjectClassesMapper projectClassesMapper;
	
	public List<ProjectInfo> selectByProperty(ProjectInfo projectInfo){
		return projectInfoMapper.selectByProperty(projectInfo);
	}
	
	public ProjectInfo selectByPrimaryKey(Integer projectInfoId){
		return projectInfoMapper.selectByPrimaryKey(projectInfoId);
	}
	
	public List<ProjectClassDto> selectProjectInfoDto(Integer storesId){
		return projectInfoMapper.selectProjectInfoDto(storesId);
	}
	public int insertSelective(ProjectInfo projectInfo){
		return projectInfoMapper.insertSelective(projectInfo);
	}
	public int updateProject(ProjectInfo projectInfo){
		return projectInfoMapper.updateByPrimaryKeySelective(projectInfo);
	}
	
	public List<ProjectInfo> selectByProjectIdList(List<Integer> projectInfoIdList){
		return projectInfoMapper.selectByProjectIdList(projectInfoIdList);
	}
	
	public List<ProjectInfo> selectByStroesIdAndPno(Map<String, Object> paramMap){
		return projectInfoMapper.selectByStroesIdAndPno(paramMap);
	}
	
	public int deleteProjectIdBatch(List<Integer> projectInfoIds){
		return projectInfoMapper.deleteProjectIdBatch(projectInfoIds);
	}
	
	public int batchUpdateProject(List<ProjectInfo> projectInfoList){
		return projectInfoMapper.batchUpdateProject(projectInfoList);
	}
	
	/**
	 * 批量新增项目
	* @author 洪秋霞
	* @date 2015年7月10日 下午5:04:11
	* @param projectInfoIdList
	* @return
	 */
	@Transactional
	public void insertProjectInfoList(ProjectInfo projectInfoStore,List<Stores> storesList,List<ProjectClasses> classeList,ProjectInfo projectInfo){
		//总店项目
		projectInfoMapper.insertSelective(projectInfoStore);
		
		List<ProjectInfo> projectInfoList = new ArrayList<ProjectInfo>();
		//循环给总店分店下的门店加项目
		for (int j = 0; j < storesList.size(); j++) {
			for (int i = 0; i < classeList.size(); i++) {
				if(storesList.get(j).getStoresId().intValue() == classeList.get(i).getStoresId().intValue()){
					ProjectInfo project = new ProjectInfo();
					project.setProjectNo(projectInfo.getProjectNo());
					project.setProjectName(projectInfo.getProjectName());
					project.setProjectPrice(projectInfo.getProjectPrice());
					project.setStatusEmp(1);
					project.setStatusMember(1);//1:未指定，2:指定
					project.setCreateTime(DateUtils.getCurTime());
					project.setClassesId(classeList.get(i).getClassesId());
					project.setStroesId(classeList.get(i).getStoresId());
					project.setParentId(projectInfoStore.getProjectInfoId());//上级项目id
					projectInfoList.add(project);
				}
			}
		}
		projectInfoMapper.insertProjectInfoList(projectInfoList);
	}
	
	/**
	 * 批量设置 指定员工：项目价格 和 员工提成 ok
	* @author 洪秋霞
	* @date 2015年7月10日 下午7:08:11
	 */
	@Transactional
	public void batchProjectPriceAndCommission(String selectStoresIds, String[] projectInfoIds, String[] projectNos, String[] projectPrices, String[] positionIds,String categoryName,String positionInfo,String[] amount,String[] discount,String[] amountAssign,String[] discountAssign){
		if(!"".equals(selectStoresIds) && !"".equals(projectInfoIds)){
			
			for (int i = 0; i < projectInfoIds.length; i++) {
				ProjectInfo pInfo = new ProjectInfo();
				pInfo.setProjectInfoId(Integer.parseInt(projectInfoIds[i].trim()));
				pInfo.setStatusEmp(2);
				projectInfoMapper.updateByPrimaryKeySelective(pInfo);
			}
			
			//修改项目价格 根据选择的门店 和 项目编号 查找项目表 然后修改
			String[] storesIdArr = selectStoresIds.split(",");
			List<Integer> storesIdList = new ArrayList<Integer>();
			List<Integer> projectNoList = new ArrayList<Integer>();
			for (int i = 0; i < storesIdArr.length; i++) {
				storesIdList.add(Integer.parseInt(storesIdArr[i].trim()));
			}
			for (int i = 0; i < projectNos.length; i++) {
				projectNoList.add(Integer.parseInt(projectNos[i].trim()));
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("stroesId", storesIdList);
			paramMap.put("projectNo", projectNoList);
			//分店的项目列表
			List<ProjectInfo> projectInfoList = projectInfoMapper.selectByStroesIdAndPno(paramMap);//根据选择的门店id 和 项目编号 查找项目表
			if(projectInfoList.size() > 0){
				List<ProjectInfo> projectInfoUpdateList = new ArrayList<ProjectInfo>();
				for (int i = 0; i < projectInfoList.size(); i++) {//查询到的分店的项目列表
					Integer projectInfoId = projectInfoList.get(i).getProjectInfoId();
					Integer projectNo = projectInfoList.get(i).getProjectNo();
					Integer stroesId = projectInfoList.get(i).getStroesId();
					for (int s = 0; s < storesIdArr.length; s++) {//页面选择的门店
						Integer sId = Integer.parseInt(storesIdArr[s].trim());
						if(stroesId.intValue() == sId.intValue()){
							for (int j = 0; j < projectNos.length; j++) {
								Integer pNo = Integer.parseInt(projectNos[j].trim());
								if(projectNo.intValue() == pNo.intValue()){
									ProjectInfo projectInfo = new ProjectInfo();
									projectInfo.setProjectInfoId(projectInfoId);
									projectInfo.setProjectPrice(new BigDecimal(projectPrices[j].trim()));//价格
									projectInfo.setStatusEmp(2);//1：未指定 2：指定
									
									projectInfo.setStroesId(stroesId);
									projectInfoUpdateList.add(projectInfo);
								}
							}
						}
					}
				}
				if(projectInfoUpdateList.size() > 0){
					projectInfoMapper.batchUpdateProject(projectInfoUpdateList);
				}
				//保存员工提成 根据选择的职位id 和 门店id 查找员工列表 然后新增
				saveProjectRelationAndCommission(categoryName, projectInfoIds, projectPrices,projectInfoUpdateList, storesIdArr, selectStoresIds, positionInfo, positionIds , amount, discount, amountAssign, discountAssign);
			}
		}
	}
	
	/**
	 * 批量设置 保存项目关系和提成表 ok
	* @author 洪秋霞
	* @date 2015年7月11日 上午10:55:54
	 */
	private void saveProjectRelationAndCommission(String categoryName,String[] projectInfoIds,String[] projectPrices,List<ProjectInfo> projectInfoList,String[] storeIdArr,String selectStoresIds,
			String positionInfo,String[] positionIds,String[] amount,String[] discount,String[] amountAssign,String[] discountAssign){
		Map<Integer, PositionDto> positionMap = new HashMap<Integer, PositionDto>();
		for (int i = 0; i < positionIds.length; i++) {
			PositionDto positionDto = new PositionDto();
			int positionId = Integer.parseInt(positionIds[i].trim());
			positionDto.setPositionId(positionId);
			if(!"/".equals(amountAssign[i])){
				positionDto.setAmountAssign(new BigDecimal(amountAssign[i].trim()));
			}
			if(!"/".equals(discountAssign[i])){
				positionDto.setDiscountAssign(Integer.parseInt(discountAssign[i]));
			}
			if(!"/".equals(amount[i])){
				positionDto.setAmount(new BigDecimal(amount[i].trim()));
			}
			if(!"/".equals(discount[i])){
				positionDto.setDiscount(Integer.parseInt(discount[i]));
			}
			positionMap.put(positionId, positionDto);
		}
		
		// 提成
		List<Dommission> dommissionList = new ArrayList<Dommission>();
		
		//根据【职位id查】找员工，根据找到的员工保存到【提成表】
		for (int s = 0; s < storeIdArr.length; s++) {//得到每一个门店编号
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			int storesId = Integer.parseInt(storeIdArr[s].trim());
			paramMap.put("storesId", storesId);
			paramMap.put("positionId", positionIds);
			List<Employee> employeeList = employeeMapper.selectEmployeesByPositionList(paramMap);//1个门店下的员工列表
			//遍历员工列表，分别对项目进行提成设置
			for (Employee employee : employeeList) {
				PositionDto positionDto = positionMap.get(employee.getPositionId());//获取该员工所属职位的提成信息
				BigDecimal amountAssignBD = positionDto.getAmountAssign();
				Integer discountAssignInt = positionDto.getDiscountAssign();
				BigDecimal amountBD = positionDto.getAmount();
				Integer discountInt = positionDto.getDiscount();
				//分店的项目列表
				for (ProjectInfo projectInfo : projectInfoList) {
					if(projectInfo.getStroesId().intValue() != storesId){
						continue;
					}
					Dommission dommission = new Dommission();//提成表
					dommission.setProjectInfoId(projectInfo.getProjectInfoId());//分店项目id
					dommission.setEmployeeId(employee.getEmployeeId());
					//指定
					if(amountAssignBD != null){
						dommission.setAmountAssign(amountAssignBD);//金额
					}
					if(discountAssignInt != null){
						dommission.setDiscountAssign(discountAssignInt);//折扣
					}
					//非指定
					if(amountBD != null){
						dommission.setAmount(amountBD);//金额
					}
					if(discountInt != null){
						dommission.setDiscount(discountInt);//折扣
					}
					dommission.setCreateTime(DateUtils.getCurTime());
					dommissionList.add(dommission);
				}
			}
		}
		if(dommissionList.size() > 0){
			//添加提成前先把原来的提成删除
			//根据项目id和员工id查找提成列表
			List<Integer> projectInfoIdList = new ArrayList<Integer>();
			List<Integer> employeeIdList = new ArrayList<Integer>();
			for (Dommission dommission : dommissionList) {
				projectInfoIdList.add(dommission.getProjectInfoId());
				employeeIdList.add(dommission.getEmployeeId());
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("projectInfoId", projectInfoIdList);
			paramMap.put("employeeId", employeeIdList);
			List<Dommission> dommissions = dommissionMapper.selectByProjectIdAndEmpIdList(paramMap);
			if(dommissions.size() > 0){
				List<Integer> dommissionsIdList = new ArrayList<Integer>();
				for (Dommission dom : dommissions) {
					dommissionsIdList.add(dom.getDommissionId());
				}
				dommissionMapper.deleteDommissionBatch(dommissionsIdList);
			}
			//批量增加提成
			dommissionMapper.insertPositionList(dommissionList);
		}
		
		//添加项目关系
		List<ProjectRelation> projectRelationList = new ArrayList<ProjectRelation>();
		for (int i = 0; i < projectInfoIds.length; i++) {//总店的项目id
			Integer projectInfoId = Integer.parseInt(projectInfoIds[i].trim());
			ProjectRelation projectRelation = new ProjectRelation();
			projectRelation.setCategoryName(categoryName);
			projectRelation.setType(1);
			projectRelation.setProjectInfoId(projectInfoId);
			projectRelation.setStroesIdSet(selectStoresIds);
			projectRelation.setPositionInfo(positionInfo);
			projectRelation.setCreateTime(DateUtils.getCurTime());
			projectRelation.setProjectPrice(new BigDecimal(projectPrices[i].trim()));
			projectRelationList.add(projectRelation);
			
			//根据项目id和门店选择id查找 把查询到的数据删除 再新增
			ProjectRelation relation = new ProjectRelation();
			relation.setProjectInfoId(projectInfoId);
			relation.setStroesIdSet(selectStoresIds);
			relation.setType(1);
			List<ProjectRelation> relationList = projectRelationMapper.selectByProperty(relation);
			if(relationList.size() > 0){
				List<Integer> projectRelationIdList = new ArrayList<Integer>();
				for (ProjectRelation relation2 : relationList) {
					projectRelationIdList.add(relation2.getProjectRelationId());
				}
				projectRelationMapper.deleteProjectRelationBatch(projectRelationIdList);
			}
		}
		Integer parentStoresId = 1;//从session获取上级id 0 为总店
		//分店的项目列表
		if(0 == parentStoresId.intValue()){
			for (ProjectInfo projectInfo : projectInfoList) {
				ProjectRelation projectRelation = new ProjectRelation();
				projectRelation.setCategoryName(categoryName);
				projectRelation.setType(1);
				projectRelation.setProjectInfoId(projectInfo.getProjectInfoId());
				projectRelation.setStroesIdSet(projectInfo.getStroesId().toString());
				projectRelation.setPositionInfo(positionInfo);
				projectRelation.setCreateTime(DateUtils.getCurTime());
				projectRelation.setProjectPrice(projectInfo.getProjectPrice());
				projectRelationList.add(projectRelation);
				
				//根据项目id和门店选择id查找 把查询到的数据删除 再新增
				ProjectRelation relation = new ProjectRelation();
				relation.setProjectInfoId(projectInfo.getProjectInfoId());
				relation.setStroesIdSet(projectInfo.getStroesId().toString());
				relation.setType(1);
				List<ProjectRelation> relationList = projectRelationMapper.selectByProperty(relation);
				if(relationList.size() > 0){
					List<Integer> projectRelationIdList = new ArrayList<Integer>();
					for (ProjectRelation relation2 : relationList) {
						projectRelationIdList.add(relation2.getProjectRelationId());
					}
					projectRelationMapper.deleteProjectRelationBatch(projectRelationIdList);
				}
			}
		}
		
		//批量增加项目关系
		projectRelationMapper.insertBatch(projectRelationList);
	}
	
	/**
	 * 项目删除
	* @author 洪秋霞
	* @date 2015年7月13日 下午2:13:29
	* @param projectInfoId
	 */
	public void deleteProject(Integer projectInfoId){
		if(projectInfoId != null){
			List<Integer> projectInfoIdList = new ArrayList<Integer>();
			projectInfoIdList.add(projectInfoId);
			
//			StringBuffer projectIdSet = new StringBuffer();
			
			/**
			 * 1.删除项目关系表
			 */
			//根据项目id查找项目关系表 删除数据
			ProjectRelation projectRelation = new ProjectRelation();
			projectRelation.setProjectInfoId(projectInfoId);
			List<ProjectRelation> projectRelationList = projectRelationMapper.selectByProperty(projectRelation);
			List<Integer> projectRelationIds = new ArrayList<Integer>();
			if(projectRelationList.size() > 0){
				for (int i = 0; i < projectRelationList.size(); i++) {
					projectRelationIds.add(projectRelationList.get(i).getProjectRelationId());
//					projectIdSet.append(projectRelationList.get(i).getProjectIdSet() + ",");
				}
			}
			if(projectRelationIds.size() > 0){
				projectRelationMapper.deleteProjectRelationBatch(projectRelationIds);
			}
			
//			if(projectIdSet.length() > 0){
//				String[] projectIdSetArr = projectIdSet.toString().split(",");
//				for (int i = 0; i < projectIdSetArr.length; i++) {
//					projectInfoIdList.add(Integer.parseInt(projectIdSetArr[i].trim()));
//				}
//			}
			/**
			 * 2.删除项目指定员工提成表
			 */
			//根据项目id查找提成表 删除项目关联的提成
			List<Integer> dommissionIdList = new ArrayList<Integer>();
			List<Dommission> dommissionList = dommissionMapper.selectByProjectIdList(projectInfoIdList);
			if(dommissionList.size() > 0){
				for (int i = 0; i < dommissionList.size(); i++) {
					dommissionIdList.add(dommissionList.get(i).getDommissionId());
				}
			}
			if(dommissionIdList.size() > 0){
				dommissionMapper.deleteDommissionBatch(dommissionIdList);
			}
			
			/**
			 * 3.删除总店下的所有分店的项目
			 */
			//查询该门店下的所有分店的项目
			ProjectInfo projectInfo1 = new ProjectInfo();
			projectInfo1.setParentId(projectInfoId);
			List<ProjectInfo> projectInfoList = projectInfoMapper.selectByProperty(projectInfo1);
			for (ProjectInfo projectInfo : projectInfoList) {
				projectInfoIdList.add(projectInfo.getProjectInfoId());
			}
			
			projectInfoMapper.deleteProjectIdBatch(projectInfoIdList);
		}
	}
	
	/**
	 * 删除指定员工类别 -- 总店
	* @author 洪秋霞
	* @date 2015年7月8日 下午2:50:51
	* @param positionIds
	* @param selectStoresId
	* @param projectIdSet
	 */
	public void deleteEmpCommission(String positionIds,String selectStoresId,String projectIds,String projectNos){
		String[] storesIdArr = selectStoresId.split(",");
		String[] positionIdArr = positionIds.split(",");
		String[] projectIdArr = projectIds.split(",");
//		String[] projectNoArr = projectNos.split(",");
		
		List<Integer> storesIdList = new ArrayList<Integer>();
		List<Integer> positionIdList = new ArrayList<Integer>();
		for (int i = 0; i < positionIdArr.length; i++) {
			positionIdList.add(Integer.parseInt(positionIdArr[i].trim()));
		}
		
		/**
		 * --------------------- 1.删除提成表数据 ---------------------
		 */
		//根据选中的：positionId 和 门店id 查找所在职位下的员工 ，根据找到的员工id 去删除员工提成表
		List<Integer> employeeIds = new ArrayList<Integer>();
		for (int i = 0; i < storesIdArr.length; i++) {
			Integer storesId = Integer.parseInt(storesIdArr[i].trim());
			storesIdList.add(storesId);
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("storesId", storesId);
			paramMap.put("positionId",positionIdList);
			List<Employee> employeeList = employeeMapper.selectEmployeesByPositionList(paramMap);
			if (employeeList.size() > 0) {
				for (int j = 0; j < employeeList.size(); j++) {
					employeeIds.add(employeeList.get(j).getEmployeeId());
				}
			}
		}
		if(employeeIds.size() > 0 ){
			//根据员工id批量删除提成表
			dommissionMapper.deleteDommissionByEmpIdBatch(employeeIds);
		}
		
		/**
		 * --------------------- 2.删除项目关系表 ---------------------
		 */
		for (int i = 0; i < projectIdArr.length; i++) {
			Integer projectInfoId = Integer.parseInt(projectIdArr[i].trim());
			
			ProjectRelation projectRelation = new ProjectRelation();
			projectRelation.setProjectInfoId(projectInfoId);
			projectRelation.setStroesIdSet(selectStoresId);
			List<ProjectRelation> projectRelationList = projectRelationMapper.selectByProperty(projectRelation);
			if(projectRelationList.size()>0){
				projectRelationMapper.deleteByPrimaryKey(projectRelationList.get(0).getProjectRelationId());
			}
			//更改项目指定员工状态
			ProjectInfo pInfo = new ProjectInfo();
			pInfo.setProjectInfoId(projectInfoId);
			pInfo.setStatusEmp(1);
			projectInfoMapper.updateByPrimaryKeySelective(pInfo);
			
			//查询该门店下的所有分店的项目
			ProjectInfo projectInfo1 = new ProjectInfo();
			projectInfo1.setParentId(projectInfoId);
			List<ProjectInfo> projectInfoList = projectInfoMapper.selectByProperty(projectInfo1);
			for (ProjectInfo projectInfo : projectInfoList) {
				Integer pInfoId = projectInfo.getProjectInfoId();
				ProjectRelation projectRelation2 = new ProjectRelation();
				projectRelation2.setProjectInfoId(pInfoId);
				projectRelation2.setStroesIdSet(projectInfo.getStroesId().toString());
				projectRelation2.setType(1);
				List<ProjectRelation> projectRelationList2 = projectRelationMapper.selectByProperty(projectRelation2);
				if(projectRelationList2.size()>0){
					projectRelationMapper.deleteByPrimaryKey(projectRelationList2.get(0).getProjectRelationId());
				}
				ProjectInfo poInfo = new ProjectInfo();
				poInfo.setProjectInfoId(pInfoId);
				poInfo.setStatusEmp(1);
				projectInfoMapper.updateByPrimaryKeySelective(poInfo);
			}
		}
		
//		List<Integer> projectNoList = new ArrayList<Integer>();
//		for (int i = 0; i < projectNoArr.length; i++) {
//			projectNoList.add(Integer.parseInt(projectNoArr[i].trim()));
//		}
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("stroesId", storesIdList);
//		paramMap.put("projectNo", projectNoList);
//		//分店的项目列表
//		//根据选择的门店id 和 项目编号 查找项目表
//		List<ProjectInfo> projectInfoList = projectInfoMapper.selectByStroesIdAndPno(paramMap);
		
	}
	
	
}
