package com.zefun.web.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.DayBookDto;
import com.zefun.web.dto.DayBookQueryDto;
import com.zefun.web.dto.GoodsInfoDto;
import com.zefun.web.dto.MemberComboDto;
import com.zefun.web.dto.MemberDto;
import com.zefun.web.dto.MemberLevelDto;
import com.zefun.web.dto.OrderDetailDto;
import com.zefun.web.dto.OrderDetailStepDto;
import com.zefun.web.dto.OrderInfoBaseDto;
import com.zefun.web.dto.ShiftMahjongProjectStepDto;
import com.zefun.web.entity.DebtFlow;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.EmployeeCommission;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.EmployeeObjective;
import com.zefun.web.entity.GiftmoneyDetail;
import com.zefun.web.entity.GiftmoneyFlow;
import com.zefun.web.entity.IntegralFlow;
import com.zefun.web.entity.MemberAccount;
import com.zefun.web.entity.MemberComboProject;
import com.zefun.web.entity.MemberComboRecord;
import com.zefun.web.entity.MemberInfo;
import com.zefun.web.entity.MemberSubAccount;
import com.zefun.web.entity.MoneyFlow;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.OrderInfo;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.ShiftMahjongProjectStep;
import com.zefun.web.entity.StockFlow;
import com.zefun.web.mapper.ComboGoodsMapper;
import com.zefun.web.mapper.DebtFlowMapper;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.EmployeeCommissionMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.EmployeeObjectiveMapper;
import com.zefun.web.mapper.GiftmoneyDetailMapper;
import com.zefun.web.mapper.GiftmoneyFlowMapper;
import com.zefun.web.mapper.GoodsInfoMapper;
import com.zefun.web.mapper.IntegralFlowMapper;
import com.zefun.web.mapper.MemberAccountMapper;
import com.zefun.web.mapper.MemberComboProjectMapper;
import com.zefun.web.mapper.MemberComboRecordMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.MemberSubAccountMapper;
import com.zefun.web.mapper.MoneyFlowMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.OrderInfoMapper;
import com.zefun.web.mapper.ShiftMahjongProjectStepMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 流水查询
 * @author luhw
 * @since 2015-11-05 15:39:11
 * 
 */
@Service
public class DayBookService {
	/** orderInfoMapper */
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	
	/** orderDetailMapper*/
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	
	/** shiftMahjongEmployeeMapper*/
	@Autowired
	private ShiftMahjongProjectStepMapper shiftMahjongProjectStepMapper;
	/** employeeCommissionMapper*/
	@Autowired
	private EmployeeCommissionMapper employeeCommissionMapper;
	/** employeeObjectiveMapper*/
	@Autowired
	private EmployeeObjectiveMapper employeeObjectiveMapper;
	
	/** 商品*/
	@Autowired
	private GoodsInfoMapper goodsInfoMapper;
	
	/** 员工*/
	@Autowired
	private EmployeeInfoMapper employeeInfoMapper;
	/** 会员信息*/
	@Autowired
	private MemberInfoService memberInfoService;
	
	/** 会员Mapper*/
	@Autowired
	private MemberInfoMapper memberInfoMapper;
	
	/** 流水mapper*/
	@Autowired 
	private MoneyFlowMapper moneyFlowMapper;
	
	/** 会员账户*/
	@Autowired 
	private MemberAccountMapper memberAccountMapper;
	
	/** 礼金明细*/
	@Autowired 
	private GiftmoneyDetailMapper giftmoneyDetailMapper;
	
	/** 礼金流水*/
	@Autowired
	private GiftmoneyFlowMapper giftmoneyFlowMapper;
	
	/** 会员等级*/
	@Autowired
	private MemberLevelMapper memberLevelMapper;
	
	/** 会员疗程项目明细*/
	@Autowired
	private MemberComboProjectMapper memberComboProjectMapper;
	
	/** 会员疗程记录*/
	@Autowired
	private MemberComboRecordMapper memberComboRecordMapper;
	
	
	/** 积分*/
	@Autowired
	private IntegralFlowMapper integralFlowMapper;
	
	/** 挂账记录*/
	@Autowired
	private DebtFlowMapper debtFlowMapper;
	
	/** 会员子账户操作对象 */
	@Autowired
	private MemberSubAccountMapper memberSubAccountMapper;
	
	/** 部门*/
	@Autowired
	private DeptInfoMapper deptInfoMapper;
	
	/** 商品库存*/
    @Autowired
    private ComboGoodsMapper comboGoodsMapper;
    /** 商品库存*/
    @Autowired
    private GoodsStockService goodsStockService;
	
	
    /**
     * 
    * @author 老王
    * @date 2016年6月13日 上午12:55:56 
    * @param storeId 门店标识
    * @return ModelAndView
     */
	public ModelAndView dayBookIndex (Integer storeId) {
		ModelAndView mav = new ModelAndView();
    	if (storeId != null) {
			// 开始时间不能大于结束时
    	    String beginTime = DateUtil.getCurDate() + " 00:00";
    	    String endTime = DateUtil.getCurDate() + " 23:59";
    	    DayBookQueryDto params = new DayBookQueryDto(storeId, beginTime, endTime, 0);
    	    params.setTimeOrder(2);
    		Map<String, Object> map = querydaybookInfo(storeId, params);
    		if (map != null) {
    			mav.setViewName(Url.DayBook.VIEW_HOME);
    			mav.addAllObjects(map);
    		}
    		mav.addObject("beginTime", beginTime);
    		mav.addObject("endTime", endTime);
    	}
    	List<DeptInfo> deptInfoList = deptInfoMapper.getDeptIdAndNameByStoreId(storeId);
    	mav.addObject("deptInfoList", deptInfoList);
        return mav;
	}
	
	/**
	 * 根据查询条件查询流水
	 * @param storeId 门店标识
	 * @param queryParams 查询条件
	 * @return 流水信息
	 */
	public Map<String, Object> querydaybookInfo(Integer storeId, DayBookQueryDto queryParams) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<DayBookDto> page = new Page<DayBookDto>();
		page.setPageNo(queryParams.getPageNo());
		page.setPageSize(queryParams.getPageSize());
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("daybook", queryParams);
		page.setParams(params);
		
		//统计各类型收入
		/*if (queryParams.getIsDeleted() == 0) {
		    DayBookDto countBook = orderInfoMapper.selectDayBookInfoCount(queryParams);
            map.put("countBook", countBook);
            
		    Map<String, Object> detailCount = new HashMap<String, Object>();
			detailCount = orderInfoMapper.selectDetailCountForType(queryParams);
			map.put("detailCount", detailCount);
		}*/
		
		DayBookDto countBook = orderInfoMapper.selectDayBookInfoCount(queryParams);
        map.put("countBook", countBook);
        
	    /*Map<String, Object> detailCount = new HashMap<String, Object>();
		detailCount = orderInfoMapper.selectDetailCountForType(queryParams);
		map.put("detailCount", detailCount);*/
		
		List<DayBookDto> dayBookInfos = orderInfoMapper.selectByPage(page);
		for (DayBookDto dayBookDto : dayBookInfos) {
			dayBookDto.setCreateTime(dayBookDto.getCreateTime().substring(5));
			/*dayBookDto.setOrderCode(dayBookDto.getOrderCode().substring(4));*/
		}
	    

		page.setResults(dayBookInfos);
		map.put("page", page);
		map.put("queryParams", queryParams);
		map.put("queryParamsStr", JSONObject.fromObject(queryParams).toString());
		return map;
	}
	
	/**
	 * 查询订单及明细
	* @author 王大爷
	* @date 2015年12月1日 下午7:48:17
	* @param orderId 订单标识
	* @param storeId 门店标识
	* @return BaseDto
	 */
    public BaseDto selectOrderByUpdate(Integer orderId, Integer storeId) {
        OrderInfoBaseDto orderInfoBaseDto = orderInfoMapper.selectOrderBaseByOrderId(orderId);
        orderInfoBaseDto = processOrderInfoBaseDto(orderInfoBaseDto);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderInfoBaseDto", orderInfoBaseDto);
        
        List<EmployeeInfo> employeeInfoList = employeeInfoMapper.selectEmployeeByStoreId(storeId);
        map.put("employeeInfoList", employeeInfoList);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
    }

    /**
     * 组装OrderProjectPageDto
    * @author 王大爷
    * @date 2015年11月30日 下午4:32:39
    * @param orderInfoBaseDto orderInfoBaseDto
    * @return List<OrderProjectPageDto>
     */
    private OrderInfoBaseDto processOrderInfoBaseDto(OrderInfoBaseDto orderInfoBaseDto) {
        List<OrderDetailDto> orderDetailList = orderInfoBaseDto.getOrderDetailList();
        for (OrderDetailDto orderDetailDto : orderDetailList) {
            if (orderDetailDto.getOffType() == 1) {
//                String comboName = orderDetailMapper.selectComboNameByDetailId(orderDetailDto.getDetailId());
                orderDetailDto.setPrivilegeInfo("疗程：");
            }
            else if (orderDetailDto.getOffType() == 2) {
//                Map<String, Object> map = orderDetailMapper.selectCouponNameByDetailId(orderDetailDto.getDetailId());
                orderDetailDto.setPrivilegeInfo("优惠券：");
            }
            else if (orderDetailDto.getOffType() == 3) {
                orderDetailDto.setPrivilegeInfo("礼金：");
            }
            else {
                orderDetailDto.setPrivilegeInfo("未使用优惠");
            }
            orderDetailDto.setPrivilegeMoney(orderDetailDto.getGiftAmount());
            
            if (orderDetailDto.getOrderType() == 1) {
                List<OrderDetailStepDto> stepList = orderDetailDto.getStepList();
                for (OrderDetailStepDto orderDetailStepDto : stepList) {
                    EmployeeCommission employeeCommission 
                                            = employeeCommissionMapper.selectByEmployeeIdShift(orderDetailStepDto.getShiftMahjongStepId());
                    if (employeeCommission == null) {
                        orderDetailStepDto.setCommissionCalculate(new BigDecimal(0));
                        orderDetailStepDto.setCommissionAmount(new BigDecimal(0));
                        orderDetailStepDto.setCommissionId(null);
                    }
                    else {
                        orderDetailStepDto.setCommissionCalculate(employeeCommission.getCommissionCalculate());
                        orderDetailStepDto.setCommissionAmount(employeeCommission.getCommissionAmount());
                        orderDetailStepDto.setCommissionId(employeeCommission.getCommissionId());
                    }
                }
            }
            else {
                
                List<EmployeeCommission> list = employeeCommissionMapper.selectByDetailId(orderDetailDto.getDetailId());
                
                List<OrderDetailStepDto> stepList = new ArrayList<OrderDetailStepDto>();
                
                for (EmployeeCommission employeeCommission : list) {
                    
                	EmployeeInfo employeeInfo = employeeInfoMapper.selectByPrimaryKey(employeeCommission.getEmployeeId());
                    
                    OrderDetailStepDto orderDetailStepDto = new OrderDetailStepDto();
                    orderDetailStepDto.setEmployeeInfo(employeeInfo);
                    orderDetailStepDto.setCommissionCalculate(employeeCommission.getCommissionCalculate());
                    orderDetailStepDto.setCommissionAmount(employeeCommission.getCommissionAmount());
                    orderDetailStepDto.setCommissionId(employeeCommission.getCommissionId());
                    orderDetailStepDto.setIsAppoint(2);
                    orderDetailStepDto.setIsAssign(2);
                    orderDetailStepDto.setPositionName("");
                    stepList.add(orderDetailStepDto);
                } 
                
                orderDetailDto.setStepList(stepList);
            }
            
            //解决签单备注为null
            if (StringUtil.isEmpty(orderDetailDto.getOrderRemark())) {
                orderDetailDto.setOrderRemark("");
            }
        }
        return orderInfoBaseDto;
    }
    
    /**
     * 保存修改订单
    * @author 王大爷
    * @date 2015年12月3日 下午7:43:58
    * @param orderInfo 参数
    * @param commissionArrayStr 修改的提成业绩
    * @return BaseDto
     */
    @Transactional
    public BaseDto orderByUpdate(OrderInfo orderInfo, String commissionArrayStr) {
        orderInfoMapper.updateByPrimaryKey(orderInfo);
        
        JSONArray commissionArray = JSONArray.fromObject(commissionArrayStr);
        for (int i = 0; i < commissionArray.size(); i++) {
            JSONObject commissionObject = commissionArray.getJSONObject(i);
            Integer commissionId = commissionObject.getInt("commissionId");
            String commissionAmountStr = commissionObject.getString("commissionAmount");
            String commissionCalculateStr = commissionObject.getString("commissionCalculate");
            
            EmployeeCommission employeeCommission = employeeCommissionMapper.selectByPrimaryKey(commissionId);
            
            String month = getMonthDate(employeeCommission.getChargeTime());
            
            //修改提成
            EmployeeCommission record = new EmployeeCommission();
            record.setCommissionId(commissionId);
            record.setCommissionAmount(new BigDecimal(commissionAmountStr));
            record.setCommissionCalculate(new BigDecimal(commissionCalculateStr));
            employeeCommissionMapper.updateByPrimaryKey(record);
            
            //修改业绩
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("employeeId", employeeCommission.getEmployeeId());
            map.put("month", month);
            EmployeeObjective objectiveDto = employeeObjectiveMapper.selectObjectiveByMonth(map);
            
            if (objectiveDto != null) {
            	Integer type = employeeCommission.getOrderType();
                
                EmployeeObjective employeeObjectiveRecord = new EmployeeObjective();
                
                employeeObjectiveRecord.setObjectiveId(objectiveDto.getObjectiveId());
                
                //计算修改后的值差距
                BigDecimal commissionCalculate = employeeCommission.getCommissionCalculate().subtract(new BigDecimal(commissionCalculateStr));
                
                if (type == 1) {
                    BigDecimal actualTotalProjectSales =objectiveDto.getActualTotalProjectSales().add(commissionCalculate);
                    
                    employeeObjectiveRecord.setActualTotalProjectSales(actualTotalProjectSales);
                    
                    ShiftMahjongProjectStepDto isAssignObj 
                            = shiftMahjongProjectStepMapper.selectByPrimaryKey(employeeCommission.getShiftMahjongStepId());
                    if (isAssignObj.getIsAssign() == 1) {
                        BigDecimal actualAssignProjectSales = objectiveDto.getActualAssignProjectSales().add(commissionCalculate);
                        employeeObjectiveRecord.setActualAssignProjectSales(actualAssignProjectSales);
                    }
                }
                else if (type == 2) {
                    BigDecimal actualGoodsSales = objectiveDto.getActualGoodsSales().add(commissionCalculate);
                    
                    employeeObjectiveRecord.setActualGoodsSales(actualGoodsSales);
                }
                else if (type == 3) {
                    BigDecimal actualComboSales = objectiveDto.getActualComboSales().add(commissionCalculate);
                    employeeObjectiveRecord.setActualComboSales(actualComboSales);
                }
                else {
                    BigDecimal actualChargeSales = objectiveDto.getActualChargeSales().add(commissionCalculate);
                    
                    employeeObjectiveRecord.setActualChargeSales(actualChargeSales);
                }
                
                employeeObjectiveMapper.updateByPrimaryKeySelective(employeeObjectiveRecord);
            }
            
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 整笔订单作废
    * @author 王大爷
    * @date 2015年12月2日 上午10:42:27
    * @param orderId 订单标识
    * @param storeId 门店标识
    * @param storeAccount 企业代号
    * @return BaseDto
     */
    @Transactional
    public BaseDto elementDeleteOrderId(Integer orderId, Integer storeId, String storeAccount) {
        OrderInfoBaseDto orderInfoBaseDto = orderInfoMapper.selectOrderBaseByOrderId(orderId);
        
        List<OrderDetailDto> orderDetailList = orderInfoBaseDto.getOrderDetailList();
        Integer memberId = orderInfoBaseDto.getMemberId();
        Integer type = 2;
        
        DebtFlow debtFlow = debtFlowMapper.selectByOrderId(orderId);
        
        if (debtFlow != null) {
            MemberAccount orderSubmit = memberAccountMapper.selectByPrimaryKey(memberId);
            
            BigDecimal inAmount = new BigDecimal(0);
            
            if (debtFlow.getFlowType() == 1) {
                if (orderSubmit.getDebtAmount().compareTo(debtFlow.getInAmount()) == 1) {
                    inAmount = orderSubmit.getDebtAmount().subtract(debtFlow.getInAmount());
                }
                else if ((orderSubmit.getDebtAmount().compareTo(debtFlow.getInAmount()) == -1)){
                    return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "请先删除还款记录！");
                }
            }
            else {
                inAmount = orderSubmit.getDebtAmount().add(debtFlow.getInAmount());
            }
            
            DebtFlow record = new DebtFlow();
            record.setDebtId(debtFlow.getDebtId());
            record.setIsDeleted(1);
            debtFlowMapper.updateByPrimaryKeySelective(record);
            
            MemberAccount memberAccount = new MemberAccount();
            memberAccount.setAccountId(memberId);
            memberAccount.setDebtAmount(inAmount);
            memberAccount.setUpdateTime(DateUtil.getCurTime());
            memberAccountMapper.updateByPrimaryKey(memberAccount);
        }
        
        String aIdStr = null;
        String goodsNumStr = null;
        for (int i = 0; i < orderDetailList.size(); i++) {
            Integer detailId = orderDetailList.get(i).getDetailId();
            Map<String, Object> dataMap = elementDeleteDetailId(orderInfoBaseDto, detailId, memberId);
            
            Integer deleteType = Integer.valueOf(dataMap.get("type").toString());
            
            if (orderDetailList.get(i).getOrderType() != 1) {
    			if (dataMap.get("aId") != null) {
    				if (aIdStr == null) {
    					aIdStr = dataMap.get("aId").toString();
    					goodsNumStr = dataMap.get("goodsNum").toString();
    				}
    				else {
    					aIdStr = aIdStr + "," + dataMap.get("aId").toString();
    					goodsNumStr = goodsNumStr + "," + dataMap.get("goodsNum").toString();
    				}
    			}
    		}
            
            if (deleteType != 2) {
                type = deleteType;
            }
        }
        
        if (aIdStr != null) {
			StockFlow stockFlow = new StockFlow();
			stockFlow.setaIds(aIdStr);
			stockFlow.setStockCount(goodsNumStr);
			stockFlow.setToStore(storeId);
			stockFlow.setFlowType("客户退货");
			stockFlow.setStockType(1);
			stockFlow.setStoreAccount(storeAccount);
			//更新商品库存并生成流水
			goodsStockService.inStock(stockFlow);
		}
        
        if (type == 1) {
            
            OrderInfo record = new  OrderInfo();
            record.setOrderId(orderId);
            record.setIsDeleted(2);
            orderInfoMapper.updateByPrimaryKey(record);
            
            MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(memberId);
            List<MemberLevelDto> memberLevelList = memberLevelMapper.selectByStoreId(storeId);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("memberId", memberId);
            map.put("levelId", memberInfo.getLevelId());
            map.put("memberLevelList", memberLevelList);
            return new BaseDto(888, map);
        }
        else if (type == 0){
            if (memberId != null) {
                MemberDto memberDto = memberInfoMapper.selectByMemberResultMap(memberId);
                
                int subAccountId = 0;
                List<MoneyFlow> moneyFlowList = moneyFlowMapper.selectByOrderId(orderId);
                for (MoneyFlow moneyFlow : moneyFlowList) {
                    moneyFlow.setIsDeleted(1);
                    moneyFlowMapper.updateByPrimaryKey(moneyFlow);
                    
                    subAccountId = moneyFlow.getAccountId();
                }
                
                MemberAccount memberAccount = new MemberAccount();
                memberAccount.setAccountId(memberId);
                memberAccount.setBalanceAmount(memberDto.getMemberAccount().getBalanceAmount().add(orderInfoBaseDto.getCardAmount()));

                IntegralFlow integralFlow = integralFlowMapper.selectByOrderId(orderId);
                if (integralFlow != null) {
                    memberAccount.setTotalIntegral(memberDto.getMemberAccount().getTotalIntegral() - integralFlow.getFlowAmount());
                    Integer valueInteger = memberDto.getMemberAccount().getBalanceIntegral() - integralFlow.getFlowAmount();
                    if (valueInteger >= 0) {
                        memberAccount.setBalanceIntegral(valueInteger); 
                    }
                    else {
                        memberAccount.setBalanceIntegral(0); 
                    }
                    IntegralFlow record = new IntegralFlow();
                    record.setFlowId(integralFlow.getFlowId());
                    record.setIsDeleted(1);
                    integralFlowMapper.updateByPrimaryKey(record);
                }
                memberAccountMapper.updateByPrimaryKey(memberAccount);
                
                //更新子账户
                MemberSubAccount memberSubAccount = memberSubAccountMapper.selectByPrimaryKey(subAccountId);
                if (memberSubAccount != null) {
                    memberSubAccount.setBalanceAmount(memberSubAccount.getBalanceAmount().add(orderInfoBaseDto.getCardAmount()));
                    memberSubAccount.setUpdateTime(DateUtil.getCurTime());
                    memberSubAccountMapper.updateByPrimaryKey(memberSubAccount);
                }
            }
        }
        
        if (memberId  != null) {
           //更新缓存中的会员数据
            memberInfoService.wipeCache(memberId);
            memberInfoService.syncLevelId(memberId);
        }
            
        OrderInfo record = new  OrderInfo();
        record.setOrderId(orderId);
        record.setIsDeleted(2);
        orderInfoMapper.updateByPrimaryKey(record);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    
    /**
     * 删除明细
    * @author 王大爷
    * @date 2015年11月11日 上午11:37:54
    * @param orderInfoBaseDto 订单基础
    * @param detailId 明细信息
    * @param memberId 会员标识
    * @return Integer
     */
    @Transactional
    public Map<String, Object> elementDeleteDetailId(OrderInfoBaseDto orderInfoBaseDto, Integer detailId, Integer memberId) {
    	
    	Map<String, Object> dataMap = new HashMap<>();
    	
        Integer type = 0;
        
        OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(detailId);
        
        Integer orderId = orderInfoBaseDto.getOrderId();
        
        MemberDto memberDto = new MemberDto();
        
        if (memberId != null){
            memberDto = memberInfoMapper.selectByMemberResultMap(memberId);
        }
        
        /*Integer storeId = orderInfoBaseDto.getStoreId();*/
        //删除员工业绩，提成
        deleteObjective(detailId, orderDetail.getOrderType());
        
        //项目
        if (orderDetail.getOrderType() == 1) {
            List<OrderDetailStepDto> orderDetailStepDtos = shiftMahjongProjectStepMapper.selectOrderStepListByDetailId(orderDetail.getDetailId());
            for (int i = 0; i < orderDetailStepDtos.size(); i++) {
                Integer shiftMahjongStepId = orderDetailStepDtos.get(i).getShiftMahjongStepId();
                deleteShiftStep(shiftMahjongStepId);
            }
        }
        //商品
        else if (orderDetail.getOrderType() == 2){
            GoodsInfoDto goodsInfo = goodsInfoMapper.selectByPrimaryKey(orderDetail.getProjectId());
            /*GoodsStockKey key = new GoodsStockKey();
            key.setaId(goodsInfo.getaId());
            key.setStoreId(orderInfoBaseDto.getStoreId());
            GoodsStock goodsStock = goodsStockMapper.selectByPrimaryKey(key);
            goodsStock.setCount(goodsStock.getCount()+orderDetail.getProjectCount());
            goodsStockMapper.updateByPrimaryKeySelective(goodsStock);*/
            
            dataMap.put("aId", goodsInfo.getaId());
            dataMap.put("goodsNum", 1);
        }
        else if (orderDetail.getOrderType() == 3) {
            //清除员工疗程
            MemberComboDto dto = memberComboRecordMapper.selectComboListByDetailId(detailId);
            if (dto != null) {
            	
            	Map<String, String> map = comboGoodsMapper.selectGoodsNumByComboId(dto.getComboId());
    	        
    	        if (map == null) {
    	        	dataMap.put("aId", null);
    				
    	        	dataMap.put("goodsNum", null);
    	        }
    	        else {
    	        	dataMap.put("aId", map.get("aId"));
    				
    	        	dataMap.put("goodsNum", map.get("goodsNum"));
    	        }
            	
                MemberComboRecord record = new MemberComboRecord();
                record.setRecordId(dto.getRecordId());
                record.setIsDeleted(1);
                memberComboRecordMapper.updateByPrimaryKey(record);
                
                List<MemberComboProject> plist = dto.getProjectList();
                
                for (MemberComboProject memberComboProject : plist) {
                    MemberComboProject precord = new MemberComboProject();
                    precord.setDetailId(memberComboProject.getDetailId());
                    precord.setIsDeleted(1);
                    memberComboProjectMapper.updateByKey(precord);
                }
            }
        }
        //开卡
        else if (orderDetail.getOrderType() == 4) {
            changeAccount(memberDto, orderId, detailId, 1);
            type = 1;
        }
        //充值
        else if (orderDetail.getOrderType() == 5) {
            changeAccount(memberDto, orderId, detailId, 2);
            type = 2;
        }
        //升级
        else if (orderDetail.getOrderType() == 6) {
            changeAccount(memberDto, orderId, detailId, 3);
            type = 1;
        }
        else if (orderDetail.getOrderType() == 7) {
            type = 2;
        }
        else if (orderDetail.getOrderType() == 8) {
            type = 2;
        }
        //回滚抵扣
        if (orderDetail.getOffType() == 1) {
            //回滚会员对应疗程
            Integer detailComboId = orderDetail.getComboId();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("detailId", detailComboId);
            map.put("comboCount", 1);
            map.put("updateTime", DateUtil.getCurTime());
            memberComboProjectMapper.updateAddComboNum(map);
        }
        else if (orderDetail.getOffType() == 2){
            //回滚优惠卷
            /*MemberCoupon record = new MemberCoupon();
            record.setRelevanceId(orderDetail.getCouponId());
            record.setIsUsed(0);
            memberCouponMapper.updateByPrimaryKey(record);*/
        }
        else if (orderDetail.getOffType() == 3){
            //回滚礼金(出现两个记录的话 ，我会骂街)
            //删除流水
            GiftmoneyFlow giftmoneyFlow = giftmoneyFlowMapper.selectByDetailId(detailId);
            GiftmoneyFlow record = new GiftmoneyFlow();
            record.setFlowId(giftmoneyFlow.getFlowId());
            record.setIsDeleted(1);
            giftmoneyFlowMapper.updateByPrimaryKeySelective(record);
            
            //回滚礼金明细
            String residueMoneyInfo = giftmoneyFlow.getResidueMoneyInfo();
            
            //礼金储值余额
            BigDecimal balanceGiftmoneyAmount = new BigDecimal(0);
            
            //礼金过期金额
            BigDecimal pastdataMoney = new BigDecimal(0);
            
            //礼金使用金额
            BigDecimal useMoney = new BigDecimal(0);
            
            useMoney = giftmoneyFlow.getFlowAmount();
            
            if (residueMoneyInfo != null && !residueMoneyInfo.equals("")) {
                String[] residueMoneys = residueMoneyInfo.split(",");
                List<Integer> detailList = new ArrayList<Integer>(residueMoneys.length);
                List<BigDecimal> operationValueList = new ArrayList<BigDecimal>(residueMoneys.length);
                for (int i = 0; i < residueMoneys.length; i++) {
                    String residueMoneyStr = residueMoneys[i];
                    detailList.add(Integer.parseInt(residueMoneyStr.split(":")[0]));
                    operationValueList.add(new BigDecimal(residueMoneyStr.split(":")[1]));
                }
                
                Integer index = 0;
                for (Integer detail : detailList) {
                    GiftmoneyDetail giftmoneyDetail = giftmoneyDetailMapper.selectByPrimaryKey(detail);
                    
                    BigDecimal operationValue = operationValueList.get(index);
                    if (!"永久".equals(giftmoneyDetail.getEndDate())) {
                        Date endDate = DateUtil.tranStrToDateDD(giftmoneyDetail.getEndDate());
                        if (giftmoneyDetail.getIsDeleted() != 1) {
                            if (endDate.before(new Date())) {
                                pastdataMoney = pastdataMoney.add(operationValue);
                            }
                            else {
                                balanceGiftmoneyAmount = balanceGiftmoneyAmount.add(operationValue);
                            }
                        }
                    }
                    else {
                        balanceGiftmoneyAmount = balanceGiftmoneyAmount.add(operationValue);
                    }
                    GiftmoneyDetail grecord = new GiftmoneyDetail();
                    grecord.setDetail(detail);
                    grecord.setResidueNowMoney(giftmoneyDetail.getResidueNowMoney().add(operationValue));
                    giftmoneyDetailMapper.updateByPrimaryKeySelective(grecord);
                    
                    index++ ;
                }
                MemberAccount objAccount = memberDto.getMemberAccount();
                MemberAccount memberAccount = new MemberAccount();
                memberAccount.setAccountId(memberDto.getMemberId());
                memberAccount.setBalanceGiftmoneyAmount(objAccount.getBalanceGiftmoneyAmount().add(balanceGiftmoneyAmount));
                memberAccount.setPastdataMoney(objAccount.getPastdataMoney().add(pastdataMoney));
                memberAccount.setUseMoney(objAccount.getUseMoney().subtract(useMoney));
                memberAccountMapper.updateByPrimaryKey(memberAccount);
            }
            
        }
        if (memberId != null) {
        	memberInfoService.wipeCache(memberId);
        }
        
        OrderDetail record = new OrderDetail();
        record.setDetailId(detailId);
        record.setIsDeleted(2);
        orderDetailMapper.updateByPrimaryKey(record);
        
        dataMap.put("type", type);
        return dataMap;
    }
    
    /**
     * 修改账户信息(开卡充值升级)
    * @author 王大爷
    * @date 2015年12月14日 下午7:47:58
    * @param memberDto 会员Dto
    * @param orderId 订单标识
    * @param detailId 明细标识
    * @param type   1、开卡，2、充值，3、升级
     */
    @Transactional
    public void changeAccount(MemberDto memberDto, Integer orderId, Integer detailId, int type) {
        MemberAccount memberAccount = new MemberAccount();
        memberAccount.setAccountId(memberDto.getMemberId());
        //充值金额
        BigDecimal balanceAmount = new BigDecimal(0);
        
        int subAccountId = 0;
        List<MoneyFlow> moneyFlowList = moneyFlowMapper.selectByOrderId(orderId);
        for (MoneyFlow moneyFlow : moneyFlowList) {
            balanceAmount = balanceAmount.add(moneyFlow.getFlowAmount());
            moneyFlow.setIsDeleted(1);
            moneyFlowMapper.updateByPrimaryKey(moneyFlow);
            
            subAccountId = moneyFlow.getAccountId();
        }
        
        List<GiftmoneyDetail> giftmoneyDetailList = giftmoneyDetailMapper.selectByDetailId(detailId);
        BigDecimal isGiftmoney = new BigDecimal(0);
        BigDecimal nowGiftmoney = new BigDecimal(0);
        //删除礼金明细表
        for (GiftmoneyDetail giftmoneyDetail : giftmoneyDetailList) {
            if (giftmoneyDetail.getIsPresent() == 1) {
                isGiftmoney = isGiftmoney.add(giftmoneyDetail.getResidueNowMoney());
                nowGiftmoney = nowGiftmoney.add(giftmoneyDetail.getNowMoney());
            }
            //删除礼金明细
            GiftmoneyDetail record = new GiftmoneyDetail();
            record.setDetail(giftmoneyDetail.getDetail());
            record.setIsDeleted(1);
            giftmoneyDetailMapper.updateByPrimaryKeySelective(record);
        }
        
        //回滚礼金(出现两个记录的话 ，我会骂街)
        //删除流水
        GiftmoneyFlow giftmoneyFlow = giftmoneyFlowMapper.selectByDetailId(detailId);
        if (giftmoneyFlow != null) {
            giftmoneyFlow.setIsDeleted(1);
            giftmoneyFlowMapper.updateByPrimaryKeySelective(giftmoneyFlow);
        }
        
        //修改储值总额
        memberAccount.setTotalAmount(memberDto.getMemberAccount().getTotalAmount().subtract(balanceAmount));
        //储值余额
        if (memberDto.getMemberAccount().getBalanceAmount().compareTo(balanceAmount) == -1) {
            memberAccount.setBalanceAmount(new BigDecimal(0));
        }
        else {
            memberAccount.setBalanceAmount(memberDto.getMemberAccount().getBalanceAmount().subtract(balanceAmount));
        }
        memberAccount.setTotalGiftmoneyAmount(memberDto.getMemberAccount().getTotalGiftmoneyAmount().subtract(nowGiftmoney));
        memberAccount.setBalanceGiftmoneyAmount(memberDto.getMemberAccount().getBalanceGiftmoneyAmount().subtract(isGiftmoney));
        memberAccountMapper.updateByPrimaryKey(memberAccount);
        
        //更新子账号信息
        if (type == 1) {
            memberSubAccountMapper.deleteByPrimaryKey(subAccountId);
        }
        else {
            MemberSubAccount memberSubAccount = memberSubAccountMapper.selectByPrimaryKey(subAccountId);
            if (memberSubAccount != null) {
                memberSubAccount.setTotalAmount(memberSubAccount.getTotalAmount().subtract(balanceAmount));
                memberSubAccount.setBalanceAmount(memberSubAccount.getBalanceAmount().subtract(balanceAmount));
                memberSubAccount.setUpdateTime(DateUtil.getCurTime());
                memberSubAccountMapper.updateByPrimaryKey(memberSubAccount);
            }
        }
        
        //更新缓存中的会员数据
        memberInfoService.wipeCache(memberDto.getMemberId());
    }
    
    /**
     * 修改业绩提成
    * @author 王大爷
    * @date 2015年12月2日 下午2:43:48
    * @param detailId 明细标识
    * @param type 类型
     */
    public void deleteObjective(Integer detailId, Integer type) {
      //根据明细标识查询员工提成
        List<EmployeeCommission> employeeCommissionList = employeeCommissionMapper.selectByDetailId(detailId);
        
        for (EmployeeCommission employeeCommission : employeeCommissionList) {
            //打删除标识
            EmployeeCommission record = new EmployeeCommission();
            record.setCommissionId(employeeCommission.getCommissionId());
            record.setIsDeleted(2);
            employeeCommissionMapper.updateByPrimaryKey(record);
            
            String objectiveMonth = getMonthDate(employeeCommission.getChargeTime());
            
            BigDecimal commissionCalculate = employeeCommission.getCommissionCalculate();
            
            if (commissionCalculate != null) {
                //减实际业绩
                EmployeeObjective employeeObjective = new EmployeeObjective();
                employeeObjective.setEmployeeId(employeeCommission.getEmployeeId());
                employeeObjective.setObjectiveMonth(objectiveMonth);
                if (type == 1) {
                    employeeObjective.setActualTotalProjectSales(commissionCalculate);
                    if (employeeCommission.getShiftMahjongStepId() != null) {
                        ShiftMahjongProjectStepDto shiftMahjongProjectStepDto 
                            = shiftMahjongProjectStepMapper.selectByPrimaryKey(employeeCommission.getShiftMahjongStepId());
                        if (shiftMahjongProjectStepDto.getIsAssign() == 1) {
                            employeeObjective.setActualAssignProjectSales(commissionCalculate);
                        }
                    }
                    
                }
                else if (type == 2) {
                    employeeObjective.setActualGoodsSales(commissionCalculate);
                }
                else if (type == 3){
                    employeeObjective.setActualComboSales(commissionCalculate);
                }
                else {
                    employeeObjective.setActualChargeSales(commissionCalculate);
                }

                employeeObjectiveMapper.updateDecreaseActual(employeeObjective);
            }
        }
    }
    
    
    /**
     * 删除轮牌项目步骤
    * @author 王大爷
    * @date 2015年12月2日 上午11:04:10
    * @param shiftMahjongStepId 轮牌项目步骤标识
     */
    @Transactional
    public void deleteShiftStep(Integer shiftMahjongStepId) {
        ShiftMahjongProjectStep record = new ShiftMahjongProjectStep();
        record.setShiftMahjongStepId(shiftMahjongStepId);
        record.setIsDeleted(1);
        shiftMahjongProjectStepMapper.updateByPrimaryKey(record);
    }
    
    /**
     * 根据传人参数时间返回对应年月  yyyy-mm
    * @author 王大爷
    * @date 2015年12月12日 下午4:43:00
    * @param parameterDate 参数时间
    * @return String
     */
    public String getMonthDate(String parameterDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        try {
            date = sdf.parse(parameterDate);
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }
        
        String month = dateFormat.format(date);
        
        return month;
    }
    
    /**
     * 修改会员等级
    * @author 王大爷
    * @date 2015年12月14日 下午9:54:57
    * @param memberId 会员标识
    * @param levelId 级别标识
    * @param lastOperatorId 操作人
    * @return BaseDto
     */
    public BaseDto changeLevelId(Integer memberId, Integer levelId, Integer lastOperatorId) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setLevelId(levelId);
        memberInfo.setUpdateTime(DateUtil.getCurTime());
        memberInfo.setMemberId(memberId);
        memberInfo.setLastOperatorId(lastOperatorId);
        
        memberInfoMapper.updateByPrimaryKey(memberInfo);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
}
