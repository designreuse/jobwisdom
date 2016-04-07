package com.lives.platform.web.contorller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lives.platform.common.consts.Url;
import com.lives.platform.common.consts.View;
import com.lives.platform.common.exception.ServiceException;
import com.lives.platform.common.util.DateUtils;
import com.lives.platform.web.dto.BaseDto;
import com.lives.platform.web.dto.PositionDto;
import com.lives.platform.web.entity.MembersCardName;
import com.lives.platform.web.entity.Position;
import com.lives.platform.web.entity.ProjectClasses;
import com.lives.platform.web.entity.ProjectInfo;
import com.lives.platform.web.entity.ProjectPlate;
import com.lives.platform.web.entity.ProjectRelation;
import com.lives.platform.web.entity.Stores;
import com.lives.platform.web.entity.StoresManageRelation;
import com.lives.platform.web.service.EmployeeMapperService;
import com.lives.platform.web.service.MemberDiscountService;
import com.lives.platform.web.service.MembersCardNameService;
import com.lives.platform.web.service.PositionMapperService;
import com.lives.platform.web.service.ProjectClassesService;
import com.lives.platform.web.service.ProjectInfoService;
import com.lives.platform.web.service.ProjectPlateService;
import com.lives.platform.web.service.ProjectRelationService;
import com.lives.platform.web.service.StoresManageRelationMapperService;
import com.lives.platform.web.service.StoresMapperService;

/**
 * 项目信息
* @author 洪秋霞
* @date 2015年7月3日 下午5:44:44 
*
 */
@Controller
public class ProjectInfoController {
	
	@Autowired private ProjectPlateService projectPlateService;
	@Autowired private ProjectClassesService projectClassesService;
	@Autowired private ProjectInfoService projectInfoService;
	private Integer storesId = 1;
	/***
	 * 区域门店管理关系表
	 */
	@Autowired private StoresManageRelationMapperService storesManageRelationService;
	/**
	 * 门店表
	 */
	@Autowired private StoresMapperService storesMapperService;
	
	@Autowired private PositionMapperService positionMapperService;
	@Autowired private EmployeeMapperService employeeService;
	@Autowired private ProjectRelationService projectRelationService;
	
	@Autowired private MemberDiscountService memberDiscountService;
	@Autowired private MembersCardNameService membersCardNameService;
	
	/**
	 * 进入项目页面
	* @author 洪秋霞
	* @date 2015年6月8日 上午10:13:48
	* @return
	 */
	@RequestMapping(value = Url.Project.TOPROJECTPAGE)
	public ModelAndView toProjectPage(HttpServletRequest request,HttpServletResponse response,ModelAndView model,Integer classesId,Integer plateId){
		try {
			//项目模版
			List<ProjectPlate> plateList =projectPlateService.queryProjectPlateList(storesId);
			model.addObject("plateList", plateList);
			//项目类别
			List<ProjectClasses> classList = projectClassesService.queryProjectClassesList(storesId);//storesId
			model.addObject("classList", classList);
			//项目
			if(classesId != null){
				ProjectInfo project = new ProjectInfo();
				project.setClassesId(classesId);
				project.setStroesId(storesId);
				List<ProjectInfo> projects = projectInfoService.selectByProperty(project);
				model.addObject("projectList", projects);
				model.addObject("classesId",classesId);
			}else{
				if(classList.size()>0){
					ProjectInfo project = new ProjectInfo();
					project.setClassesId(classList.get(0).getClassesId());
					project.setStroesId(storesId);
					List<ProjectInfo> projects = projectInfoService.selectByProperty(project);
					model.addObject("projectList", projects);
					model.addObject("classesId",classList.get(0).getClassesId());
				}
			}
			
			//门店列表
			//Integer storesId = 1;//从登录人员的session获取
			Stores stores = storesMapperService.queryStores(storesId);
			Integer parentStoresId = stores.getStoresId();
			if(0 != parentStoresId.intValue()){
				Stores stores2 = new Stores();
				stores2.setParentId(parentStoresId);
				List<Stores> storesList = storesMapperService.selectByProperty(stores2);
				model.addObject("storesList", JSONArray.fromObject(storesList).toString());
			}else{
				model.addObject("stores", stores);
			}
//			List<StoresManageRelation> storesManageRelationList = storesManageRelationService.queryStoresManageRelationIdList(1);
//			model.addObject("storesManageRelationList", JSONArray.fromObject(storesManageRelationList).toString());
			
			//职位、岗位列表
			List<Position> positionList = positionMapperService.selectPositionByStoresIdList(storesId);//storesId
			model.addObject("positionList", positionList);
			
			model.setViewName(View.Project.PROJECT);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 *  进入[员工提成]设置页面
	* @author 洪秋霞
	* @date 2015年7月4日 下午12:47:01
	* @param projectInfoId
	* @return
	 */
	@RequestMapping(value = Url.Project.TOPROJECTRELATION)
	public ModelAndView queryProjectRelation(HttpServletRequest request,HttpServletResponse response,ModelAndView model){
		try {
			String projectInfoId = request.getParameter("projectInfoId");
			
			if(!"".equals(projectInfoId) && projectInfoId != null){
				
				String[] projectInfoIds = projectInfoId.split(",");
				List<Integer> projectInfoIdList = new ArrayList<Integer>();
				for (int i = 0; i < projectInfoIds.length; i++) {
					projectInfoIdList.add(Integer.parseInt(projectInfoIds[i].trim()));
				}
				//项目列表
				List<ProjectInfo> projectInfoList = projectInfoService.selectByProjectIdList(projectInfoIdList);
				model.addObject("projectInfoList",projectInfoList);
				
				if(projectInfoIdList.size() == 1){
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("type", 1);
					paramMap.put("projectInfoId", projectInfoIdList);
					List<ProjectRelation> projectRelationList = projectRelationService.selectByProjectIdSet(paramMap);
					if(projectRelationList.size() > 0){
						for (int i = 0; i < projectRelationList.size(); i++) {
							List<PositionDto> positionDtoList = new ArrayList<PositionDto>();
							ProjectRelation projectRel = projectRelationList.get(i);
							String positionInfo = projectRel.getPositionInfo();
							JSONArray json = JSONArray.fromObject(positionInfo);
							for(int j=0;j<json.size();j++){
								JSONObject jsonObject = (JSONObject)json.get(j);
								PositionDto positionDto = new PositionDto();
								positionDto.setPositionId(Integer.valueOf(jsonObject.get("positionId").toString()));
								if(!jsonObject.get("discountAssign").equals("/")){
									positionDto.setDiscountAssign(Integer.valueOf(jsonObject.get("discountAssign").toString()));
								}
								if(!jsonObject.get("amountAssign").equals("/")){
									positionDto.setAmountAssign(new BigDecimal(Double.valueOf(jsonObject.get("amountAssign").toString())));
								}
								if(!jsonObject.get("discount").equals("/")){
									positionDto.setDiscount(Integer.valueOf(jsonObject.get("discount").toString()));
								}
								if(!jsonObject.get("amount").equals("/")){
									positionDto.setAmount(new BigDecimal(Double.valueOf(jsonObject.get("amount").toString())));
								}
								positionDtoList.add(positionDto);
							}
							projectRel.setPositionDtos(positionDtoList);
						}
					}
					model.addObject("projectRelationList", projectRelationList);
				}
				
				model.addObject("projectInfoId",projectInfoId);
			}
			
			//门店列表
//			Integer storesId = 1;//从登录人员的session获取
			Stores stores = storesMapperService.queryStores(storesId);
			Integer parentId = stores.getParentId();
			if(0 == parentId.intValue()){
				Stores stores2 = new Stores();
				stores2.setParentId(stores.getStoresId());
				List<Stores> storesList = storesMapperService.selectByProperty(stores2);
				model.addObject("storesList", JSONArray.fromObject(storesList).toString());
			}else{
				model.addObject("stores", stores);
			}
//			List<StoresManageRelation> storesManageRelationList = storesManageRelationService.queryStoresManageRelationIdList(1);
//			model.addObject("storesManageRelationList", JSONArray.fromObject(storesManageRelationList).toString());
			
			//职位、岗位列表
			List<Position> positionList = positionMapperService.selectPositionByStoresIdList(storesId);//storesId
			model.addObject("positionList", JSONArray.fromObject(positionList).toString());
			
			model.setViewName(View.Project.PROJECTSTROE);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 进入[会员折扣]设置页面
	* @author 洪秋霞
	* @date 2015年7月7日 下午6:33:03
	* @param request
	* @param response
	* @param model
	* @return
	 */
	@RequestMapping(value = Url.Project.TOPROJECTMEMBERS)
	public ModelAndView toProjectMembers(HttpServletRequest request,HttpServletResponse response,ModelAndView model){
		try {
			String projectInfoId = request.getParameter("projectInfoId");
			if(!"".equals(projectInfoId)){
				String[] projectInfoIds = projectInfoId.split(",");
				List<Integer> projectInfoIdList = new ArrayList<Integer>();
				for (int i = 0; i < projectInfoIds.length; i++) {
					projectInfoIdList.add(Integer.parseInt(projectInfoIds[i].trim()));
				}
				//项目列表
				List<ProjectInfo> projectInfoList = projectInfoService.selectByProjectIdList(projectInfoIdList);
				model.addObject("projectInfoList",projectInfoList);
				
				if(projectInfoList.size() == 1){
					ProjectRelation projectRelation = new ProjectRelation();
					projectRelation.setProjectInfoId(Integer.parseInt(projectInfoId));
					projectRelation.setType(2);//1:员工，2:会员
					List<ProjectRelation> projectRelationList = projectRelationService.selectByProperty(projectRelation);
					if(projectRelationList.size() > 0){
						for (int i = 0; i < projectRelationList.size(); i++) {
							List<PositionDto> positionDtoList = new ArrayList<PositionDto>();
							ProjectRelation projectRel = projectRelationList.get(i);
							String positionInfo = projectRel.getPositionInfo();
							JSONArray json = JSONArray.fromObject(positionInfo);
							for(int j=0;j<json.size();j++){
								JSONObject jsonObject = (JSONObject)json.get(j);
								PositionDto positionDto = new PositionDto();
								positionDto.setPositionId(Integer.valueOf(jsonObject.get("positionId").toString()));
								if(!jsonObject.get("discount").equals("/")){
									positionDto.setDiscount(Integer.valueOf(jsonObject.get("discount").toString()));
								}
								if(!jsonObject.get("amount").equals("/")){
									positionDto.setAmount(new BigDecimal(Double.valueOf(jsonObject.get("amount").toString())));
								}
								positionDtoList.add(positionDto);
							}
							projectRel.setPositionDtos(positionDtoList);
						}
					}
					model.addObject("projectRelationList", projectRelationList);
				}
			}
			
			//门店列表
//			Integer storesId = 2;//从登录人员的session获取
			Stores stores = storesMapperService.queryStores(storesId);
			Integer parentId = stores.getParentId();
			if(0 == parentId.intValue()){
				Stores stores2 = new Stores();
				stores2.setParentId(stores.getStoresId());
				List<Stores> storesList = storesMapperService.selectByProperty(stores2);
				model.addObject("storesList", JSONArray.fromObject(storesList).toString());
			}else{
				model.addObject("stores", stores);
			}
//			List<StoresManageRelation> storesManageRelationList = storesManageRelationService.queryStoresManageRelationIdList(1);
//			model.addObject("storesManageRelationList", storesManageRelationList);
			
			//会员等级列表
			MembersCardName membersCardName = new MembersCardName();
			membersCardName.setStoresId(storesId);//从session获取
			List<MembersCardName> membersCardNameList = membersCardNameService.selectByProperty(membersCardName);
			model.addObject("membersCardNameList",membersCardNameList);
			
			model.setViewName(View.Project.PROJECTMEMBERS);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	/**
	 * 总店 - 项目批量保存 ok
	 * 总店添加项目 默认个它所在的分店添加项目
	* @author 洪秋霞
	* @date 2015年7月10日 下午5:08:45
	* @param request
	* @param response
	* @return
	 */
	@RequestMapping(value = Url.Project.ADDPROJECTBATCH, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto saveProjectBatch(HttpServletRequest request,HttpServletResponse response,ProjectInfo projectInfo){
		try {
			Integer classesId = projectInfo.getClassesId();
			
//			List<StoresManageRelation> storesManageRelationList = storesManageRelationService.queryStoresManageRelationIdList(1);
			//根据登录的门店查找分店列表
			Integer parentStoresId = 1;//从session获取上级id
			// --------------------总店--------------------
			if(0 == parentStoresId.intValue()){
				Stores stores2 = new Stores();
				stores2.setParentId(parentStoresId);
				List<Stores> storesList = storesMapperService.selectByProperty(stores2);
				
				if(storesList.size()>0){
					List<Integer> storesIdList = new ArrayList<Integer>();
					for (int j = 0; j < storesList.size(); j++) {
						storesIdList.add(storesList.get(j).getStoresId());
					}
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("parentClassesId", classesId);
					paramMap.put("storesId", storesIdList);
					List<ProjectClasses> classeList = projectClassesService.selectByProjectstoresIdList(paramMap);
					
					//总店添加一条项目
					ProjectInfo projectInfoStore = new ProjectInfo();
					projectInfoStore.setProjectNo(projectInfo.getProjectNo());
					projectInfoStore.setProjectName(projectInfo.getProjectName());
					projectInfoStore.setProjectPrice(projectInfo.getProjectPrice());
					projectInfoStore.setStatusEmp(1);
					projectInfoStore.setStatusMember(1);//1:未指定，2:指定
					projectInfoStore.setCreateTime(DateUtils.getCurTime());
					projectInfoStore.setClassesId(classesId);
					projectInfoStore.setParentId(0);
					projectInfoStore.setStroesId(storesId);//登录的门店id
					
					projectInfoService.insertProjectInfoList(projectInfoStore, storesList, classeList, projectInfo);
				}
			}else{
				// --------------------分店--------------------
				//添加自己门店的项目
				ProjectInfo projectInfoStore = new ProjectInfo();
				projectInfoStore.setProjectNo(projectInfo.getProjectNo());
				projectInfoStore.setProjectName(projectInfo.getProjectName());
				projectInfoStore.setProjectPrice(projectInfo.getProjectPrice());
				projectInfoStore.setStatusEmp(1);
				projectInfoStore.setStatusMember(1);//1:未指定，2:指定
				projectInfoStore.setCreateTime(DateUtils.getCurTime());
				projectInfoStore.setClassesId(classesId);
				projectInfoStore.setStroesId(storesId);//登录的门店id
				projectInfoStore.setParentId(0);
				projectInfoService.insertSelective(projectInfoStore);
			}
			return new BaseDto(0, "ok");
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseDto(-1, "error");
		}
	}
	
	
	/**
	 * 总店 - 批量设置项目 - [员工提成] 保存
	* @author 洪秋霞
	* @date 2015年7月4日 下午12:27:10
	* @param request
	* @param response
	* @return
	* @throws ServiceException
	 */
	@RequestMapping(value = Url.Project.ADDPROJECTCOMMISSION, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto addProjectCommissionBatch(HttpServletRequest request,HttpServletResponse response) throws ServiceException{
		try{
			String categoryName = request.getParameter("categoryName");//类别
			
			String selectStoresIds = request.getParameter("selectStoresId");//选择的门店id
			
			String[] projectInfoIdCheck = request.getParameterValues("projectInfoIdCheck");//选择的项目id
			String[] projectPrices = request.getParameterValues("projectPrice");//项目价格
			String[] projectNos = request.getParameterValues("projectNo");
			String[] positionIds = request.getParameterValues("positionId");//职位id
			//指定
			String[] discountAssign = request.getParameterValues("discountAssign");//折扣
			String[] amountAssign = request.getParameterValues("amountAssign");//金额
			//非指定
			String[] discount = request.getParameterValues("discount");//比例提成 = 折扣
			String[] amount = request.getParameterValues("amount");//固定提成 = 金额
			
			JSONArray array = new JSONArray();
			for (int i = 0; i < positionIds.length; i++) {
				 JSONObject projectRelation = new JSONObject();
				 projectRelation.put("positionId", positionIds[i]);
				 projectRelation.put("amountAssign", amountAssign[i]);
				 projectRelation.put("discountAssign", discountAssign[i]);
				 projectRelation.put("amount", amount[i]);
				 projectRelation.put("discount", discount[i]);
				 array.add(projectRelation);
			}
			String positionInfo = array.toString();
			if(!"".equals(selectStoresIds) && !"".equals(projectInfoIdCheck)){
				projectInfoService.batchProjectPriceAndCommission(selectStoresIds, projectInfoIdCheck, projectNos, projectPrices, positionIds, categoryName, positionInfo, amount, discount, amountAssign, discountAssign);
				return new BaseDto(0, "ok");
			}else {
				return new BaseDto(-1, "error");
			}
			
			
			/**
			String classesId = request.getParameter("classesId");//项目类别id 如果项目是新增，获取该id
			
			String categoryName = request.getParameter("categoryName");//类别
			String projectIdSet = request.getParameter("projectIdSet");//项目id集合
			
			String selectStoresId = request.getParameter("selectStoresId");//选择的门店id
			String[] storeIdArr = selectStoresId.split(",");
			
			//总店项目信息
			String storeProjectNo = request.getParameter("storeProjectNo");
			String storeProjectName = request.getParameter("storeProjectName");
			String storeProjectPrice = request.getParameter("storeProjectPrice");
			Integer stroesId = 1;//从登录session获取
			String storeClassesId = request.getParameter("storeClassesId");//如果项目为修改的，获取该id
			
			//项目信息
			String projectInfoIdStr = request.getParameter("projectInfoId");//项目信息id
			String projectNo = request.getParameter("projectNo");
			String projectName = request.getParameter("projectName");//项目名称
//			String discount = request.getParameter("discount");//折扣
			String projectPrice = request.getParameter("projectPrice");//项目价格=门店价格
			
			String[] positionIds = request.getParameterValues("positionId");
			String[] discount = request.getParameterValues("discount");//比例提成 = 折扣
			String[] amount = request.getParameterValues("amount");//固定提成 = 金额
			
			JSONArray array = new JSONArray();
			for (int i = 0; i < positionIds.length; i++) {
				 JSONObject projectRelation = new JSONObject();
				 projectRelation.put("positionId", positionIds[i]);
				 projectRelation.put("commission", discount[i]);
				 projectRelation.put("amount", amount[i]);
				 array.add(projectRelation);
			}
			String positionInfo = array.toString();
			
			ProjectInfo storeProjectInfo = new ProjectInfo();
			if("".equals(projectInfoIdStr)){
				storeProjectInfo.setProjectNo(Integer.parseInt(storeProjectNo));
				storeProjectInfo.setProjectName(storeProjectName);
				storeProjectInfo.setProjectPrice(new BigDecimal(storeProjectPrice));
				storeProjectInfo.setStroesId(stroesId);
				storeProjectInfo.setStatus(1);
				storeProjectInfo.setClassesId(Integer.parseInt(storeClassesId));
				storeProjectInfo.setCreateTime(DateUtils.getCurTime());
			}
			if("".equals(projectNo)){
				projectNo = storeProjectNo;
			}
//			Integer projectInfoId = projectInfoService.saveProject(projectInfoIdStr, storeProjectInfo, storeIdArr, positionIds, discount, amount, categoryName, selectStoresId, storeClassesId, projectPrice, positionInfo, discount, projectNo, projectName, projectIdSet, storeClassesId)
			*/
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseDto(-1, "error");
		}
	}
	
	/**
	 * 项目会员折扣保存
	* @author 洪秋霞
	* @date 2015年7月7日 下午8:08:59
	* @return
	 */
	@RequestMapping(value = Url.Project.SAVEMEMBERS)
	@ResponseBody
	public BaseDto saveMembers(HttpServletRequest request,HttpServletResponse response){
		try {
			String vipLevelIdSet = request.getParameter("vipLevelIdSet");//会员等级id
			
			String categoryName = request.getParameter("categoryName");//类别
			String selectStoresId = request.getParameter("selectStoresId");//选择的门店id
			String[] storeIdArr = selectStoresId.split(",");
			String[] projectInfoIds = request.getParameterValues("projectInfoIdCheck");//项目信息id
			String[] projectNo = request.getParameterValues("projectNo");
			String[] vipLevelIds = request.getParameterValues("vipLevelId");//会员等级id
			String[] discount = request.getParameterValues("discount");//比例提成 = 提成
			String[] amount = request.getParameterValues("amount");//固定提成 = 金额
			
			JSONArray array = new JSONArray();
			for (int i = 0; i < vipLevelIds.length; i++) {
				JSONObject projectRelation = new JSONObject();
				projectRelation.put("positionId", vipLevelIds[i]);
				projectRelation.put("discount", discount[i]);
				projectRelation.put("amount", amount[i]);
				array.add(projectRelation);
			}
			String vipLevelInfo = array.toString();
			memberDiscountService.saveMembers(categoryName, storeIdArr, selectStoresId, projectInfoIds, projectNo, vipLevelIds, amount, discount, vipLevelInfo, vipLevelIdSet);
			return new BaseDto(0, "ok");
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseDto(-1, "error");
		}
	}
	
	/**
	 * 删除员工提成类别
	* @author 洪秋霞
	* @date 2015年7月8日 下午2:35:46
	* @return
	 */
	@RequestMapping(value = Url.Project.DELETEEMPCOMMISSION)
	@ResponseBody
	public BaseDto deleteEmpCommissionClasses(HttpServletRequest request,HttpServletResponse response){
		try {
			String positionIds = request.getParameter("positionId");
			String selectStoresId = request.getParameter("selectStoresId");
			String projectIdSet = request.getParameter("projectInfoIdCheck");
			String projectNo = request.getParameter("projectNo");
			projectInfoService.deleteEmpCommission(positionIds, selectStoresId, projectIdSet, projectNo);
			return new BaseDto(0,"ok");
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseDto(-1,"error");
		}
	}
	
	/**
	 * 删除会员等级类别
	* @author 洪秋霞
	* @date 2015年7月8日 下午2:33:42
	* @param request
	* @param response
	* @return
	 */
	@RequestMapping(value = Url.Project.DELETEMEMBERSLEVEL)
	@ResponseBody
	public BaseDto deleteMembersLevelClasses(HttpServletRequest request,HttpServletResponse response){
		try {
			String selectStoresId = request.getParameter("selectStoresId");
			
//			String vipLevelIdSet = request.getParameter("vipLevelIdSet");
//			String[] vipLevelIds = request.getParameterValues("vipLevelId");
			
			String vipLevelIds = request.getParameter("vipLevelIds");
			String projectInfoIds = request.getParameter("projectInfoIds");
			
			memberDiscountService.deleteMembersLevel(selectStoresId , vipLevelIds, projectInfoIds);
			return new BaseDto(0,"ok");
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseDto(-1,"error");
		}
	}
	
	/**
	 * 项目修改
	* @author 洪秋霞
	* @date 2015年7月7日 上午9:18:47
	* @param request
	* @param response
	* @return
	 */
	@RequestMapping(value = Url.Project.EDITPROJECT)
	@ResponseBody
	public BaseDto editProject(HttpServletRequest request,HttpServletResponse response){
		try {
			//1.修改总店项目
			String projectInfoId = request.getParameter("projectInfoId");
			String projectNo = request.getParameter("projectNo");
			String projectName = request.getParameter("projectName");
			String projectPrice = request.getParameter("projectPrice");
			ProjectInfo projectInfo = new ProjectInfo();
			projectInfo.setProjectInfoId(Integer.parseInt(projectInfoId));
			projectInfo.setProjectNo(Integer.parseInt(projectNo));
			projectInfo.setProjectName(projectName);
			projectInfo.setProjectPrice(new BigDecimal(projectPrice));
			projectInfoService.updateProject(projectInfo);
			
			//2.修改总店下的分店项目
			List<Integer> storesIdList = new ArrayList<Integer>();
			List<Integer> projectNoList = new ArrayList<Integer>();
			//根据登录的人员查找分店列表
			List<StoresManageRelation> storesManageRelationList = storesManageRelationService.queryStoresManageRelationIdList(1);
			for (StoresManageRelation storesManageRelation : storesManageRelationList) {
				storesIdList.add(storesManageRelation.getItemid2());
			}
			projectNoList.add(Integer.parseInt(projectNo));
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("stroesId", storesIdList);
			paramMap.put("projectNo", projectNoList);
			//根据分店的项目列表 门店id 和 项目编号 查找项目表
			List<ProjectInfo> projectInfoList = projectInfoService.selectByStroesIdAndPno(paramMap);
			List<ProjectInfo> projectInfoUpdateList = new ArrayList<ProjectInfo>();
			for (ProjectInfo pInfo : projectInfoList) {
				ProjectInfo project = new ProjectInfo();
				project.setProjectInfoId(pInfo.getProjectInfoId());
				project.setProjectName(projectName);
				project.setProjectNo(Integer.parseInt(projectNo));
				project.setProjectPrice(new BigDecimal(projectPrice.trim()));//价格
				project.setStatusEmp(pInfo.getStatusEmp());//1：未指定 2：指定
				projectInfoUpdateList.add(project);
			}
			projectInfoService.batchUpdateProject(projectInfoUpdateList);
			
			return new BaseDto(0, "ok");
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseDto(-1, "error");
		}
	}
	
	/**
	 * 根据项目id查询项目是否有设置分店
	* @author 洪秋霞
	* @date 2015年7月7日 上午10:18:37
	* @return
	 */
	@RequestMapping(value = Url.Project.QUERYPROJECTIDISSET)
	@ResponseBody
	public BaseDto queryProjectIdIsSet(HttpServletRequest request,HttpServletResponse response){
		try {
			String projectInfoId = request.getParameter("projectInfoId");
			ProjectRelation projectRelation = new ProjectRelation();
			projectRelation.setProjectInfoId(Integer.parseInt(projectInfoId));
			List<ProjectRelation> projectRelationList = projectRelationService.selectByProperty(projectRelation);
			String isConnect = "false";
			if(projectRelationList.size() > 0 ){
				isConnect = "true";
			}
			return new BaseDto(0, isConnect);
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseDto(-1, "error");
		}
	}
	
	
	/**
	 * 项目删除
	* @author 洪秋霞
	* @date 2015年7月7日 上午10:18:37
	* @return
	 */
	@RequestMapping(value = Url.Project.DELETEPROJECT)
	@ResponseBody
	public BaseDto deleteProject(HttpServletRequest request,HttpServletResponse response){
		try {
			String projectInfoId = request.getParameter("projectInfoId");
			projectInfoService.deleteProject(Integer.parseInt(projectInfoId));
			return new BaseDto(0, "ok");
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseDto(-1, "error");
		}
	}
	
	/**
	 * 项目类别保存
	 * @param plateId 项目板块id
	 * @param classesName 类别名称
	 * @param storesId 门店id
	 * @param type 项目类型（0：项目，1：套餐，2：疗程）
	 * @author 洪秋霞
	 * @date 2015年6月8日 上午10:14:00
	 * @return
	 */
	@RequestMapping(value = Url.Project.ADDPROJECTCLASSES)
	@ResponseBody
	public BaseDto addProjectClasses(Integer plateId,Integer classesId,String classesName){
		try {
			if(classesId != null){
				ProjectClasses classes = new ProjectClasses();
				classes.setClassesId(classesId);
				classes.setClassesName(classesName);
				projectClassesService.updateProjectClasses(classes);
			}else{
				projectClassesService.saveProjectClasses(plateId, classesId, classesName);
			}
			return new BaseDto(0, "ok");
		} catch (Exception e) {
			return new BaseDto(-1, "error");
		}
	}
	
	/**
	 * 项目类别删除
	* @author 洪秋霞
	* @date 2015年6月8日 下午5:10:51
	* @param plateId
	* @return
	 */
	@RequestMapping(value = Url.Project.DELETEPROJECTCLASSES)
	@ResponseBody
	public BaseDto deleteProjectClasses(Integer classesId){
		projectClassesService.deleteProjectClasses(classesId);
		return new BaseDto(0, "ok");
	}
	
	/**
	 * 获取职位列表
	* @author 洪秋霞
	* @date 2015年6月25日 上午11:36:42
	* @return
	 */
	@RequestMapping(value = Url.Employee.GETPOSITIONLIST)
	@ResponseBody
	public List<Position> getEmployeeMsgName(){
		List<Position> positionList = positionMapperService.selectPositionByStoresIdList(storesId);
		return positionList;
	}
	
	/**
	 * 获取门店列表
	* @author 洪秋霞
	* @date 2015年7月4日 下午4:24:03
	* @return
	 */
	@RequestMapping(value = Url.Project.STORESLIST)
	@ResponseBody
	public List<Stores> storesList(){
		//门店列表
		List<Stores> storesList = new ArrayList<Stores>();
		Stores stores = storesMapperService.queryStores(storesId);
		Integer parentStoresId = stores.getStoresId();
		if(0 != parentStoresId.intValue()){
			Stores stores2 = new Stores();
			stores2.setParentId(parentStoresId);
			storesList = storesMapperService.selectByProperty(stores2);
		}else {
			storesList.add(stores);
		}
		return storesList;
//		List<StoresManageRelation> storesManageRelationList = storesManageRelationService.queryStoresManageRelationIdList(1);
//		return storesManageRelationList;
	}
	
	/**
	 * 获取会员等级列表
	* @author 洪秋霞
	* @date 2015年7月7日 下午8:51:19
	* @return
	 */
	@RequestMapping(value = Url.Project.VIPLEVELLIST)
	@ResponseBody
	public List<MembersCardName> getVipLevelList(){
		//会员等级列表
		MembersCardName membersCardName = new MembersCardName();
		membersCardName.setStoresId(storesId);//从session获取
		List<MembersCardName> membersCardNameList = membersCardNameService.selectByProperty(membersCardName);
		return membersCardNameList;
	}
	
	/**
	 * 校验项目编号唯一性，每个门店下的项目编号唯一
	* @author 洪秋霞
	* @date 2015年7月13日 下午4:59:28
	* @param request
	* @param response
	* @return
	 */
	@RequestMapping(value = Url.Project.VALIDPROJECTNO)
	@ResponseBody
	public BaseDto validProjectNo(HttpServletRequest request,HttpServletResponse response){
		String projectNo = request.getParameter("projectNo");
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setProjectNo(Integer.parseInt(projectNo));
		projectInfo.setStroesId(storesId);//登录的门店id
		List<ProjectInfo> projectInfoList = projectInfoService.selectByProperty(projectInfo);
		if(projectInfoList.size() > 0){
			return new BaseDto(0, "error");
		}else{
			return new BaseDto(0, "ok");
		}
	}
}
