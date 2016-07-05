package com.zefun.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.DeptGoodsBaseDto;
import com.zefun.web.dto.DeptProjectBaseDto;
import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.dto.GoodsInfoDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.PositionInfoShiftMahjongDto;
import com.zefun.web.dto.ProjectMahjongProjectStepDto;
import com.zefun.web.entity.ComboInfo;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.OrderInfo;
import com.zefun.web.entity.PositionInfo;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.entity.ShiftMahjongProjectStep;
import com.zefun.web.mapper.ComboInfoMapper;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.GoodsInfoMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.OrderInfoMapper;
import com.zefun.web.mapper.PositioninfoMapper;
import com.zefun.web.mapper.ProjectInfoMapper;
import com.zefun.web.mapper.ShiftMahjongProjectStepMapper;
import com.zefun.wechat.service.StaffService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 轮牌信息Service
* @author 王大爷
* @date 2015年8月11日 上午11:50:57
 */
@Service
public class ManuallyOpenOrderService {
	/** 员工信息*/
	@Autowired private EmployeeInfoMapper employeeInfoMapper;
	/** 部门*/
	@Autowired private DeptInfoMapper deptInfoMapper;
	/** */
	@Autowired private StaffService staffService;
    /** 项目*/
	@Autowired private ProjectService projectService;
	/** 商品*/
	@Autowired private GoodsInfoService goodsInfoService;
	/** 套餐*/
	@Autowired private ComboInfoMapper comboInfoMapper;
	/** 项目*/
	@Autowired private ProjectInfoMapper projectInfoMapper;
	/** 商品*/
	@Autowired private GoodsInfoMapper goodsInfoMapper;
	/** 会员*/
	@Autowired private MemberInfoService memberInfoService;
	/** 步骤*/
	@Autowired private ShiftMahjongProjectStepMapper shiftMahjongProjectStepMapper;
	/** 订单*/
	@Autowired private OrderInfoMapper orderInfoMapper;
	/** 订单明细*/
	@Autowired private OrderDetailMapper orderDetailMapper;
	/** 岗位*/
	@Autowired private PositioninfoMapper positioninfoMapper;
	
	/**
	 * 初始化轮职排班界面
	* @author laowang
	* @date 2015年8月8日 上午11:17:40
	* @param storeId 门店标识
	* @return ModelAndView
	 */
	public ModelAndView initializeManuallyOpenOrder(Integer storeId){
		ModelAndView mav = new ModelAndView();
        
		List<Integer> deptIdList = deptInfoMapper.selectDeptIdByStoreIdIsResults(storeId);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < deptIdList.size(); i++) {
		    
		    DeptProjectBaseDto deptProjectBaseDto = projectService.getDeptProjectByDeptId(deptIdList.get(i));
		    
		    DeptGoodsBaseDto deptGoodsBaseDto = goodsInfoService.getDeptGoodsByDeptId(deptIdList.get(i));
		    
	        List<ComboInfo> comboInfoList = comboInfoMapper.getComboInfo(deptIdList.get(i));
	        
	        //按部门组装数据
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("deptId", deptIdList.get(i));
	        map.put("deptName", deptProjectBaseDto.getDeptName());
	        map.put("projectCategoryList", deptProjectBaseDto.getProjectCategoryList());
	        map.put("goodsCategoryList", deptGoodsBaseDto.getGoodsCategoryBaseDto());
	        map.put("comboInfoList", comboInfoList);
		    list.add(map);
        }
		mav.addObject("list", list);
		//查询门店下所有的岗位
		List<PositionInfo> positionInfos = positioninfoMapper.queryAllByStoreId(storeId);
		
		mav.addObject("positionInfosStr", JSONArray.fromObject(positionInfos).toString());
		
		List<Map<String, Object>> employeeInfoList = new ArrayList<>();
		for (PositionInfo positionInfo : positionInfos) {
			Map<String, Object> employeeMap = new HashMap<>();
			Map<String, Integer> params =  new HashMap<>();
			params.put("positionId", positionInfo.getPositionId());
			List<EmployeeDto> employeeDtoList = employeeInfoMapper.selectAllEmployeeByCondition(params);
			employeeMap.put("positionId", positionInfo.getPositionId());
			employeeMap.put("positionName", positionInfo.getPositionName());
			employeeMap.put("employeeDtoList", employeeDtoList);
			employeeInfoList.add(employeeMap);
		}
		
		//查询该门店所有员工
		mav.addObject("employeeInfoList", employeeInfoList);
		mav.setViewName(View.KeepAccounts.MANUALLY_OPEN_ORDER);
		return mav;
	}
	
	/**
	 * 初始化无纸开单
	* @author 老王
	* @date 2016年7月5日 上午10:06:46 
	* @param storeId 门店标识
	* @return ModelAndView
	 */
	public ModelAndView initializeNoPaperOpenOrder (Integer storeId) {
		ModelAndView mav =  new ModelAndView(View.KeepAccounts.NO_PAPER_OPEN_ORDER);
		List<PositionInfoShiftMahjongDto> positionInfoShiftMahjongDtoList = positioninfoMapper.selectByPositionShiftMahjong(storeId);
		mav.addObject("positionInfoShiftMahjongDtoList", positionInfoShiftMahjongDtoList);
		return mav;
		
	}
	
	/**
     * 根据项目标识查询想轮牌信息及步骤对应员工
    * @author 王大爷
    * @date 2015年11月24日 下午12:08:34
    * @param projectId 项目标识
    * @return BaseDto
     */
	public BaseDto selectProjectShiftStep(Integer projectId) {
	    ProjectMahjongProjectStepDto projectMahjongProjectStepDto = projectInfoMapper.selectByProjectId(projectId);
	    return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, projectMahjongProjectStepDto);
	}
	
	/**
	 * 手动开单
	* @author 王大爷
	* @date 2015年11月25日 上午10:23:45
	* @param memberId 会员标识
	* @param sex 散客性别
	* @param arrayObjStr 开单项目
	* @param openOrderDate 补单时间
	* @param storeId 门店标识
	* @param lastOperatorId 操作人
	* @param handOrderCode 手工单号
	* @return BaseDto
	 */
	@Transactional
    public BaseDto manuallyOpenOrderSave(Integer memberId, String sex, String arrayObjStr, String openOrderDate, Integer storeId, 
    		Integer lastOperatorId, String handOrderCode){
        //查询会员基本信息
        MemberBaseDto memberBaseDto = new MemberBaseDto();
        if (memberId != null) {
            memberBaseDto = memberInfoService.getMemberBaseInfo(memberId, true);
        }
        else {
            memberBaseDto.setSex(sex);
        }
        
        boolean typeDate = false;
        
        if (StringUtil.isEmpty(openOrderDate)) {
			openOrderDate = DateUtil.getCurTime();
			typeDate = true;
		}
        else {
        	openOrderDate = openOrderDate.replace("T", " ");
        }
        
        String orderCode = staffService.getOrderCode("order_info", storeId);
        
        //保存订单信息
        Integer orderId = staffService.addOrderInfo(orderCode, memberId, storeId, sex, openOrderDate, lastOperatorId, handOrderCode);
        
        JSONArray arrayObj =JSONArray.fromObject(arrayObjStr);
        
        for (int j = 0; j < arrayObj.size(); j++) {
            JSONObject jsonObj = arrayObj.getJSONObject(j);
            
            Integer orderType = jsonObj.getInt("type");
            Integer projectId = jsonObj.getInt("projectId");
            if (orderType == 1) {
                Integer appoint = jsonObj.getInt("appoint");
                ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(projectId);
                String detailCode = "";
        		if (typeDate) {
        			detailCode = staffService.getOrderCode("order_detail", storeId);
        		}
        		else {
        			detailCode = "999";
        		}
        		
                Integer detailId = staffService.addOrderDetail(detailCode, orderId, memberId, memberBaseDto.getLevelId(), orderType, projectId, 
                        projectInfo.getProjectName(), projectInfo.getProjectPrice(), 1, projectInfo.getProjectImage(),
                        appoint, openOrderDate, storeId, lastOperatorId);
                
                String projectStepArrayObjStr = jsonObj.getString("projectStepArrayObjStr");
                
                JSONArray stepObj =JSONArray.fromObject(projectStepArrayObjStr);
                for (int i = 0; i < stepObj.size(); i++) {
                    JSONObject jsonStep = stepObj.getJSONObject(i);
                    Integer positionId = jsonStep.getInt("positionId");
                    String employeeIds = jsonStep.getString("employeeId");
                    Integer employeeId = null;
                    if (!StringUtil.isEmpty(employeeIds)) {
                        employeeId = Integer.valueOf(employeeIds);
                    }
                    Integer isAssign = jsonStep.getInt("isAssign");
                    Integer isAppoint = jsonStep.getInt("isAppoint");
                    
                    
                    ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
                    shiftMahjongProjectStep.setPositionId(positionId);
                    shiftMahjongProjectStep.setEmployeeId(employeeId);
                    shiftMahjongProjectStep.setDetailId(detailId);
                    shiftMahjongProjectStep.setIsAssign(isAssign);
                    shiftMahjongProjectStep.setIsAppoint(isAppoint);
                    shiftMahjongProjectStep.setIsOver(2);
                    shiftMahjongProjectStep.setCreateTime(openOrderDate);
                    shiftMahjongProjectStep.setLastOperatorId(lastOperatorId);
                    
                    shiftMahjongProjectStepMapper.insert(shiftMahjongProjectStep);
                }
                OrderDetail orderDetail = new OrderDetail(); 
                orderDetail.setDetailId(detailId);
                orderDetail.setOrderStatus(3);
                orderDetailMapper.updateByPrimaryKey(orderDetail);
            }
            else if (orderType == 2) {
                
                GoodsInfoDto goodsInfo = goodsInfoMapper.selectByPrimaryKey(projectId);
                String employeeIds = jsonObj.getString("projectStepArrayObjStr");
                
                JSONObject employeeIdObj = JSONObject.fromObject(employeeIds);
                
                Integer detailId = staffService.addOrderDetail(null, orderId, memberId, memberBaseDto.getLevelId(), orderType, projectId, 
                		  goodsInfo.getGoodsName(), goodsInfo.getGoodsPrice(), 1, goodsInfo.getGoodsImage(), 0, openOrderDate, 
                		  storeId, lastOperatorId);
                
                Integer employeeId1 = employeeIdObj.getInt("employeeId1");
                Integer employeeId2 = employeeIdObj.getInt("employeeId2");
                Integer employeeId3 = employeeIdObj.getInt("employeeId3");
                
                if (employeeId1 != null && employeeId1 != 0) {
                	ShiftMahjongProjectStep shiftMahjongProjectStep1 = new ShiftMahjongProjectStep();
                    shiftMahjongProjectStep1.setPositionId(1);
                    shiftMahjongProjectStep1.setEmployeeId(employeeId1);
                    shiftMahjongProjectStep1.setDetailId(detailId);
                    shiftMahjongProjectStep1.setIsOver(2);
                    shiftMahjongProjectStep1.setCreateTime(openOrderDate);
                    shiftMahjongProjectStep1.setLastOperatorId(lastOperatorId);
                    
                    shiftMahjongProjectStepMapper.insert(shiftMahjongProjectStep1);
                }
                
                if (employeeId2 != null && employeeId2 != 0) {
                	ShiftMahjongProjectStep shiftMahjongProjectStep2 = new ShiftMahjongProjectStep();
                    shiftMahjongProjectStep2.setPositionId(2);
                    shiftMahjongProjectStep2.setEmployeeId(employeeId2);
                    shiftMahjongProjectStep2.setDetailId(detailId);
                    shiftMahjongProjectStep2.setIsOver(2);
                    shiftMahjongProjectStep2.setCreateTime(openOrderDate);
                    shiftMahjongProjectStep2.setLastOperatorId(lastOperatorId);
                    
                    shiftMahjongProjectStepMapper.insert(shiftMahjongProjectStep2);
                }
                
                if (employeeId3 != null && employeeId3 != 0) {
                	ShiftMahjongProjectStep shiftMahjongProjectStep3 = new ShiftMahjongProjectStep();
                    shiftMahjongProjectStep3.setPositionId(1);
                    shiftMahjongProjectStep3.setEmployeeId(employeeId3);
                    shiftMahjongProjectStep3.setDetailId(detailId);
                    shiftMahjongProjectStep3.setIsOver(2);
                    shiftMahjongProjectStep3.setCreateTime(openOrderDate);
                    shiftMahjongProjectStep3.setLastOperatorId(lastOperatorId);
                    
                    shiftMahjongProjectStepMapper.insert(shiftMahjongProjectStep3);
                }
            }
            else {
                ComboInfo comboInfo = comboInfoMapper.selectByPrimaryKey(projectId);
                String employeeIds = jsonObj.getString("projectStepArrayObjStr");

                Integer detailId = staffService.addOrderDetail(null, orderId, memberId, memberBaseDto.getLevelId(), orderType, projectId, 
                		  comboInfo.getComboName(), comboInfo.getComboSalePrice(), 1, comboInfo.getComboImage(), 0, openOrderDate, 
                		  storeId, lastOperatorId);
                
                JSONObject employeeIdObj = JSONObject.fromObject(employeeIds);
                
                Integer employeeId1 = employeeIdObj.getInt("employeeId1");
                Integer employeeId2 = employeeIdObj.getInt("employeeId2");
                Integer employeeId3 = employeeIdObj.getInt("employeeId3");
                
                if (employeeId1 != null && employeeId1 != 0) {
                	ShiftMahjongProjectStep shiftMahjongProjectStep1 = new ShiftMahjongProjectStep();
                    shiftMahjongProjectStep1.setPositionId(1);
                    shiftMahjongProjectStep1.setEmployeeId(employeeId1);
                    shiftMahjongProjectStep1.setDetailId(detailId);
                    shiftMahjongProjectStep1.setIsOver(2);
                    shiftMahjongProjectStep1.setCreateTime(openOrderDate);
                    shiftMahjongProjectStep1.setLastOperatorId(lastOperatorId);
                    
                    shiftMahjongProjectStepMapper.insert(shiftMahjongProjectStep1);
                }
                
                if (employeeId2 != null && employeeId2 != 0) {
                	ShiftMahjongProjectStep shiftMahjongProjectStep2 = new ShiftMahjongProjectStep();
                    shiftMahjongProjectStep2.setPositionId(2);
                    shiftMahjongProjectStep2.setEmployeeId(employeeId2);
                    shiftMahjongProjectStep2.setDetailId(detailId);
                    shiftMahjongProjectStep2.setIsOver(2);
                    shiftMahjongProjectStep2.setCreateTime(openOrderDate);
                    shiftMahjongProjectStep2.setLastOperatorId(lastOperatorId);
                    
                    shiftMahjongProjectStepMapper.insert(shiftMahjongProjectStep2);
                }
                
                if (employeeId3 != null && employeeId3 != 0) {
                	ShiftMahjongProjectStep shiftMahjongProjectStep3 = new ShiftMahjongProjectStep();
                    shiftMahjongProjectStep3.setPositionId(1);
                    shiftMahjongProjectStep3.setEmployeeId(employeeId3);
                    shiftMahjongProjectStep3.setDetailId(detailId);
                    shiftMahjongProjectStep3.setIsOver(2);
                    shiftMahjongProjectStep3.setCreateTime(openOrderDate);
                    shiftMahjongProjectStep3.setLastOperatorId(lastOperatorId);
                    
                    shiftMahjongProjectStepMapper.insert(shiftMahjongProjectStep3);
                }
            }
        }
        
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(orderId);
        //状态进行中
        orderInfo.setOrderStatus(2);
        orderInfoMapper.updateByPrimaryKey(orderInfo);
        //修改订单价
        orderInfoMapper.updateTotalPrice(orderId);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
}
