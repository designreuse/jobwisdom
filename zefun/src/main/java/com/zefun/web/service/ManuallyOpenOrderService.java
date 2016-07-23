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
import com.zefun.web.dto.SelfCashierOrderDto;
import com.zefun.web.entity.ComboInfo;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.MemberAppointment;
import com.zefun.web.entity.MemberInfo;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.OrderInfo;
import com.zefun.web.entity.PositionInfo;
import com.zefun.web.entity.ProjectCategory;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.entity.ShiftMahjongEmployee;
import com.zefun.web.entity.ShiftMahjongProjectStep;
import com.zefun.web.entity.StoreSetting;
import com.zefun.web.mapper.ComboInfoMapper;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.GoodsInfoMapper;
import com.zefun.web.mapper.MemberAppointmentMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.OrderInfoMapper;
import com.zefun.web.mapper.PositioninfoMapper;
import com.zefun.web.mapper.ProjectCategoryMapper;
import com.zefun.web.mapper.ProjectInfoMapper;
import com.zefun.web.mapper.ShiftMahjongEmployeeMapper;
import com.zefun.web.mapper.ShiftMahjongProjectStepMapper;
import com.zefun.web.mapper.StoreSettingMapper;
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
	/** 疗程*/
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
	/** 轮牌员工*/
	@Autowired private ShiftMahjongEmployeeMapper shiftMahjongEmployeeMapper;
	/** */
	@Autowired private StoreSettingMapper storeSettingMapper;
	/** */
	@Autowired private SelfCashierService selfCashierService;
	/** */
	@Autowired private MemberInfoMapper memberInfoMapper;
	/** */
	@Autowired private MemberAppointmentMapper memberAppointmentMapper;
	/** */
	@Autowired private ProjectCategoryMapper projectCategoryMapper;
	/**
	 * 初始化轮职排班界面
	* @author laowang
	* @date 2015年8月8日 上午11:17:40
	* @param storeId 门店标识
	* @param orderId 订单标识
	* @return ModelAndView
	 */
	public ModelAndView initializeManuallyOpenOrder(Integer storeId, Integer orderId){
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
		
		if (orderId != null) {
			SelfCashierOrderDto selfCashierOrderDto = selfCashierService.selectSelfCashierOrderDetail(orderId, false, storeId);
			mav.addObject("selfCashierOrderDto", selfCashierOrderDto);
			if (selfCashierOrderDto.getMemberId() != null) {
				MemberBaseDto memberBaseDto = memberInfoMapper.selectMemberBaseInfo(selfCashierOrderDto.getMemberId());
				mav.addObject("memberBaseDto", JSONObject.fromObject(memberBaseDto).toString());
			}
		}
		
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
		
		Map<String, Integer> map = new HashMap<>();
		map.put("storeId", storeId);
		map.put("type", 1);
		// 查询未结账订单
		List<SelfCashierOrderDto> cashierDtoList = orderInfoMapper.selectUnfinishedOrderInfo(map);
		mav.addObject("cashierDtoList", cashierDtoList);
		
		StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(storeId);
		
		mav.addObject("startHandNumber", storeSetting.getStartHandNumber());
		
		List<DeptInfo> deptList = deptInfoMapper.getDeptIdAndNameByStoreId(storeId);
        
        List<Map<String, Object>> dtoList = new ArrayList<Map<String, Object>>();
        
        for (DeptInfo dept : deptList) {
            Integer deptId = dept.getDeptId();
            
            Map<String, Object> deptMap = new HashMap<String, Object>();
            deptMap.put("deptId", deptId);
            DeptProjectBaseDto deptProjectBaseDto = projectService.getDeptProjectByDeptId(deptId);
            deptMap.put("project", deptProjectBaseDto.getProjectCategoryList());
            
            dtoList.add(deptMap);
        }

        mav.addObject("dtoList", dtoList);
        mav.addObject("deptList", deptList);
		
		return mav;
	}
	
	/**
	 * 初始化弹出框
	* @author 老王
	* @date 2016年7月6日 下午5:13:10 
	* @param storeId 门店标识
	* @return BaseDto
	 */
	public BaseDto initializeNoPaperModel (Integer storeId) {
		Map<String, Object> map = new HashMap<>();
		List<PositionInfoShiftMahjongDto> positionInfoShiftMahjongDtoList = positioninfoMapper.selectByPositionShiftMahjong(storeId);
		map.put("positionInfoShiftMahjongDtoList", positionInfoShiftMahjongDtoList);
		
		List<Integer> handOrderCodeList = orderInfoMapper.selectIsUserHandOrderCode(storeId);
		map.put("handOrderCodeList", handOrderCodeList);
		
		List<MemberAppointment> memberAppointmentList = memberAppointmentMapper.selectByStoreIdServer(storeId);
		List<Map<String, Object>> appointObjList = new ArrayList<>();
		for (MemberAppointment memberAppointment : memberAppointmentList) {
			Map<String, Object> appointObj = new HashMap<>();
			appointObj.put("appointmentId", memberAppointment.getAppointmentId());
			appointObj.put("appointmentWay", memberAppointment.getAppointmentWay());
			appointObj.put("appointmentDate", memberAppointment.getAppointmentDate() + " " + memberAppointment.getAppointmentTime());
			MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(memberAppointment.getMemberId());
			//设置会员信息
			appointObj.put("memberId", memberAppointment.getMemberId());
			appointObj.put("memberName", memberInfo.getName());
			appointObj.put("sex", memberInfo.getSex());
			appointObj.put("phone", memberInfo.getPhone());
			appointObj.put("headUrl", memberInfo.getHeadUrl());
			EmployeeDto employeeDto = employeeInfoMapper.selectEmployeeBaseInfo(memberAppointment.getEmployeeId());
			//设置员工信息
			appointObj.put("employeeId", employeeDto.getEmployeeId());
			appointObj.put("employeeName", employeeDto.getName());
			appointObj.put("levelName", employeeDto.getLevelName());
			//预约大项目
			ProjectCategory projectCategory = projectCategoryMapper.selectByPrimaryKey(memberAppointment.getProjectId());
			appointObj.put("categoryName", projectCategory.getCategoryName());
			appointObjList.add(appointObj);
		}
		map.put("appointObjList", appointObjList);
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
	}
	
	/**
     * 开单
    * @author 王大爷
    * @date 2015年10月27日 上午11:06:17
    * @param employeeObj 指定人员
    * @param handOrderCode 手工号
    * @param memberId 会员标识
    * @param sex    消费者性别
    * @param storeId 门店标识
    * @param appointmentId 预约标识
    * @param lastOperatorId 操作者
    * @return ModelAndView
     */
    @Transactional
    public BaseDto addOrder(String sex, String handOrderCode, String employeeObj, Integer memberId, 
    		  Integer storeId, Integer appointmentId, Integer lastOperatorId) {
    	Integer orderId = addOrderInfo(handOrderCode, memberId, storeId, sex, DateUtil.getCurTime(), lastOperatorId);
        //暂时未做
        addDetail(orderId, employeeObj, storeId, appointmentId, lastOperatorId);
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("storeId", storeId);
        map.put("orderId", orderId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
    }
	
    /**
     * 无纸单结算
    * @author 老王
    * @date 2016年7月6日 下午8:24:02 
    * @param orderId 订单标识
    * @return BaseDto
     */
    public BaseDto settlementOrder (Integer orderId) {
    	return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 添加明细，步骤，及修改轮牌信息
    * @author 王大爷
    * @date 2015年11月5日 上午10:41:08
    * @param orderId 订单标识
    * @param employeeObj 指定员工
    * @param storeId 门店标识
    * @param appointmentId 是否预约
    * @param lastOperatorId 操作人
     */
    public void addDetail(Integer orderId, String employeeObj, Integer storeId, 
            Integer appointmentId, Integer lastOperatorId) {
        MemberAppointment memberAppointment = null;
    	if (appointmentId != null) {
    		memberAppointment = memberAppointmentMapper.selectByPrimaryKey(appointmentId);
    		MemberAppointment record = new MemberAppointment();
    		record.setAppointmentId(memberAppointment.getAppointmentId());
    		record.setAppointmentStatus(6);
    		memberAppointmentMapper.updateByPrimaryKey(record);
    	}
        Integer detailId = addOrderDetail(orderId, appointmentId, DateUtil.getCurTime(), storeId, lastOperatorId);
        
        //获取项目指定轮牌员工
        JSONArray empjsonArray =JSONArray.fromObject(employeeObj);
        for (int j = 0; j < empjsonArray.size(); j++) {
            JSONObject jsonEmployee = empjsonArray.getJSONObject(j);
            //岗位标识
            Integer positionId = jsonEmployee.getInt("positionId");
            //是否指定
            Integer isAssign = jsonEmployee.getInt("isAssign");
            Integer isAppoint = 0;
            
            Integer shiftMahjongId = null;
            if (!jsonEmployee.get("shiftMahjongId").toString().isEmpty()) {
            	//指定员工对应轮牌员工标识
            	shiftMahjongId = jsonEmployee.getInt("shiftMahjongId");
            }
            
            Integer employeeId = null;
            if (!jsonEmployee.get("employeeId").toString().isEmpty()) {
            	//指定员工对应轮牌员工标识
            	employeeId = jsonEmployee.getInt("employeeId");
            	if (memberAppointment != null && employeeId.intValue() == memberAppointment.getEmployeeId().intValue()) {
            		isAppoint = 1;
            	}
            }
            
            addShiftMahjongProjectStep(detailId, shiftMahjongId, employeeId, positionId, isAssign, isAppoint, lastOperatorId);
        }
        
        
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(orderId);
        //状态进行中
        orderInfo.setOrderStatus(1);
        orderInfoMapper.updateByPrimaryKey(orderInfo);
    }
    
    /**
     * 保存订单信息
    * @author 王大爷
    * @date 2015年9月19日 下午2:01:33
    * @param handOrderCode 手牌号
    * @param memberId 会员信息标识
    * @param storeId 门店标识
    * @param openOrderDate 补单时间
    * @param sex 性别
    * @param employeeId 操作员工
    * @return 订单标识
     */
    @Transactional
    public Integer addOrderInfo(String handOrderCode, Integer memberId, Integer storeId, String sex, 
    		  String openOrderDate, Integer employeeId){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setHandOrderCode(handOrderCode);
        if (memberId != null) {
            orderInfo.setMemberId(memberId);
            orderInfo.setSex(sex);
        }
        else {
        	orderInfo.setSex(sex);
        }
        orderInfo.setOrderType(1);
        orderInfo.setStoreId(storeId);
        orderInfo.setCreateTime(openOrderDate);
        orderInfo.setLastOperatorId(employeeId);
        orderInfo.setOrderStatus(1);
        orderInfoMapper.insert(orderInfo);
        return orderInfo.getOrderId();
    }
    
    /**
     * 保存订单明细
    * @author 王大爷
    * @date 2015年9月19日 下午3:50:07
    * @param orderId 订单标识
    * @param isAppointment 是否预约
    * @param openOrderDate 补单时间
    * @param storeId 门店标识
    * @param lastOperatorId 操作员工
    * @return 订单明细标识
     */
    public Integer addOrderDetail(Integer orderId, Integer isAppointment, String openOrderDate,
            Integer storeId, Integer lastOperatorId) {
    	
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderId);
        orderDetail.setOrderType(1);
        orderDetail.setIsAssign(0);
        orderDetail.setIsAppoint(isAppointment);
        orderDetail.setProjectCount(1);
        orderDetail.setStoreId(storeId);
        orderDetail.setOrderStatus(2);
        orderDetail.setCreateTime(openOrderDate);
        orderDetail.setLastOperatorId(lastOperatorId);
        
        orderDetailMapper.insert(orderDetail);
        
        return orderDetail.getDetailId();
    }
    
    /**
     * 保存轮牌步骤
    * @author 王大爷
    * @date 2015年9月19日 下午4:40:41
    * @param detailId 订单明细
    * @param shiftMahjongId 轮牌员工
    * @param employeeId 员工标识
    * @param positionId 岗位标识
    * @param isAssign 是否指定
    * @param isAppoint 是否预约
    * @param lastOperatorId 操作人员
     */
    public void addShiftMahjongProjectStep(Integer detailId, Integer shiftMahjongId, Integer employeeId, Integer positionId, Integer isAssign, 
    		  Integer isAppoint, Integer lastOperatorId){
    	
    	ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
    	shiftMahjongProjectStep.setDetailId(detailId);
    	shiftMahjongProjectStep.setPositionId(positionId);
    	if (employeeId != null) {
    		
    		Map<String, Integer> map = new HashMap<>();
            map.put("employeeId", employeeId);
            map.put("shiftMahjongId", shiftMahjongId);
            //查询步骤对应轮牌员工标识
            ShiftMahjongEmployee shiftMahjongEmployee = shiftMahjongEmployeeMapper.selectEmployeeByStepId(map);
    		
    		shiftMahjongProjectStep.setShiftMahjongId(shiftMahjongEmployee.getShiftMahjongId());
            shiftMahjongProjectStep.setIsAssign(isAssign);
            shiftMahjongProjectStep.setIsOver(1);
            shiftMahjongProjectStep.setBeginTime(DateUtil.getCurTime());
            shiftMahjongProjectStep.setEmployeeId(shiftMahjongEmployee.getEmployeesId());
            
            boolean type = false;
            if (isAssign == 1) {
            	type = true;
            }
            //调整员工在轮牌的位置及修改状态
            staffService.moveEmployee(shiftMahjongEmployee.getShiftMahjongEmployeeId(), type);
    	}
    	else {
    		shiftMahjongProjectStep.setIsOver(0);
    	}
        shiftMahjongProjectStep.setCreateTime(DateUtil.getCurTime());
        shiftMahjongProjectStep.setLastOperatorId(lastOperatorId);
        
        shiftMahjongProjectStepMapper.insert(shiftMahjongProjectStep);
        
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
	* @param orderId 订单标识
	* @return BaseDto
	 */
	@Transactional
    public BaseDto manuallyOpenOrderSave(Integer memberId, String sex, String arrayObjStr, String openOrderDate, Integer storeId, 
    		Integer lastOperatorId, String handOrderCode, Integer orderId){
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
        
        if (orderId == null) {
        	String orderCode = staffService.getOrderCode("order_info", storeId);
        	//保存订单信息
        	orderId = staffService.addOrderInfo(orderCode, memberId, storeId, sex, openOrderDate, lastOperatorId, handOrderCode);
        }
        else {
        	OrderInfo orderInfo = new OrderInfo();
            if (memberId != null) {
                orderInfo.setMemberId(memberId);
                orderInfo.setSex(sex);
            }
            else {
            	orderInfo.setSex(sex);
            }
            OrderInfo orderObj = orderInfoMapper.selectByPrimaryKey(orderId);
            if (orderObj.getOrderCode().isEmpty()) {
            	String orderCode = staffService.getOrderCode("order_info", storeId);
            	orderInfo.setOrderCode(orderCode);
            	orderInfo.setOrderId(orderId);
            }
            orderInfoMapper.updateByPrimaryKey(orderInfo);
        }
        
        JSONArray arrayObj =JSONArray.fromObject(arrayObjStr);
        
        for (int j = 0; j < arrayObj.size(); j++) {
            JSONObject jsonObj = arrayObj.getJSONObject(j);
            
            Integer orderType = jsonObj.getInt("type");
            Integer projectId = jsonObj.getInt("projectId");
            if (orderType == 1) {
            	Integer detailId = null;
            	if (!jsonObj.get("detailId").toString().isEmpty()) {
            		detailId = jsonObj.getInt("detailId");
                }
            	
                Integer appoint = jsonObj.getInt("appoint");
                
                if (detailId == null) {
                	ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(projectId);
                    String detailCode = "";
            		if (typeDate) {
            			detailCode = staffService.getOrderCode("order_detail", storeId);
            		}
            		else {
            			detailCode = "999";
            		}
            		
                    detailId = staffService.addOrderDetail(detailCode, orderId, memberId, memberBaseDto.getLevelId(), orderType, projectId, 
                            projectInfo.getProjectName(), projectInfo.getProjectPrice(), 1, projectInfo.getProjectImage(),
                            appoint, openOrderDate, storeId, lastOperatorId);
                    
                }
                
                String projectStepArrayObjStr = jsonObj.getString("projectStepArrayObjStr");
                
                JSONArray stepObj =JSONArray.fromObject(projectStepArrayObjStr);
                for (int i = 0; i < stepObj.size(); i++) {
                    JSONObject jsonStep = stepObj.getJSONObject(i);
                    Integer shiftMahjongStepId = null;
                    if (!jsonStep.get("shiftMahjongStepId").toString().isEmpty()) {
                    	shiftMahjongStepId = jsonStep.getInt("shiftMahjongStepId");
                    }
                    Integer positionId = jsonStep.getInt("positionId");
                    String employeeIds = jsonStep.getString("employeeId");
                    Integer employeeId = null;
                    if (!StringUtil.isEmpty(employeeIds)) {
                        employeeId = Integer.valueOf(employeeIds);
                    }
                    Integer isAssign = jsonStep.getInt("isAssign");
                    Integer isAppoint = jsonStep.getInt("isAppoint");
                    
                    if (shiftMahjongStepId == null) {
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
                    else {
                    	ShiftMahjongProjectStep shiftMahjongProjectStep = new ShiftMahjongProjectStep();
                        shiftMahjongProjectStep.setShiftMahjongStepId(shiftMahjongStepId);
                        shiftMahjongProjectStep.setEmployeeId(employeeId);
                        shiftMahjongProjectStep.setIsAssign(isAssign);
                        shiftMahjongProjectStep.setIsAppoint(isAppoint);
                        
                        shiftMahjongProjectStepMapper.updateByPrimaryKey(shiftMahjongProjectStep);
                    }
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
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, orderId);
    }
}
