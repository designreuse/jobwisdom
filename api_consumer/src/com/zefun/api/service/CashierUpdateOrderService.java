package com.zefun.api.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zefun.api.dto.CashierCommissionDto;
import com.zefun.api.dto.CashierOrderDetailDto;
import com.zefun.api.entity.EmployeeCommission;
import com.zefun.api.entity.EmployeeObjective;
import com.zefun.api.entity.OrderDetail;
import com.zefun.api.mapper.EmployeeObjectiveMapper;
import com.zefun.api.mapper.OrderDetailMapper;
import com.zefun.api.utils.DateUtil;

/**
 * 
 *
 */
@Service
public class CashierUpdateOrderService {
    /** */
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	
	/** */
	@Autowired EmployeeObjectiveMapper employeeObjectiveMapper;
	
	/**
	 * 计算并添加员工提成 
	 * @param detailId 订单标识
	 * 
	 */
	@Transactional
	public void insertCashierCommission(Integer detailId) {
		List<CashierOrderDetailDto> orderDetailList = orderDetailMapper.selectUpdateOrderDetailByDetailId(detailId);
		if (orderDetailList == null || orderDetailList.isEmpty()) {
			return;
		}
		OrderDetail obj = orderDetailMapper.selectByDetailId(detailId);
		
		for (CashierOrderDetailDto orderDetail : orderDetailList) {
		    Map<String, Object> mapOrder = orderDetailMapper.selectMemberIdByOrderId(orderDetail.getOrderId());
	        Integer memberIdConsumption = Integer.valueOf(mapOrder.get("memberId").toString());
	        
	        BigDecimal discountAmount = obj.getProjectPrice();
	                
	        Integer performanceDiscountPercent = 100;
	        //还有问题
	        //计算会员账户均值
	        if (memberIdConsumption != 0) {
	            performanceDiscountPercent = employeeObjectiveMapper.getPerformanceDiscountPercent(memberIdConsumption);
	            
	            BigDecimal rate = new BigDecimal(100);
	            
	            Map<String, Integer> map = new HashMap<String, Integer>();
	            map.put("memberId", memberIdConsumption);
	            map.put("projectId", orderDetail.getProjectId());
	            Map<String, Object> objMap = orderDetailMapper.selectByDiscount(map);
	            
	            //该项目对应该会员的会员等级不存在特定价格
	            if (objMap == null) {
	                Integer levelId = orderDetailMapper.selectLevelIdByMemberId(memberIdConsumption);
	                //计算会员折扣价
	                BigDecimal projectDiscount = orderDetailMapper.selectMemberLevel(levelId);
	                discountAmount = discountAmount.multiply(projectDiscount).divide(rate);
	            }
	            //该项目对应该会员的会员等级存在特定价格
	            else {
	                discountAmount = new BigDecimal(objMap.get("discountAmount").toString());
	            }
	        }

	        
	        Integer projectId = null;
	        Integer employeeId = null;
	        
	        Map<String, Integer> commissionParams = new HashMap<String, Integer>();
	        projectId = orderDetail.getProjectId();
	        commissionParams.put("projectId", projectId);
	        //查询轮牌标识
	        Integer shiftMahjongId = orderDetailMapper.selectByShiftMahjongStepId(orderDetail.getShiftMahjongStepId());
	        
	        commissionParams.put("levelId", orderDetail.getLevelId());
	        commissionParams.put("isAssign", orderDetail.getIsAssign());
	        commissionParams.put("shiftMahjongId", shiftMahjongId);
	        
	        CashierCommissionDto commissionDto = orderDetailMapper.selectProjectCommissionInfo(commissionParams);
	        
	        if (commissionDto != null) {
	            employeeId = orderDetail.getEmployeeId();
	            // 计算项目提成
	            calculateCommonCommission(obj, orderDetail.getShiftMahjongStepId(), orderDetail.getProjectCount(), employeeId, 
	                    commissionDto, performanceDiscountPercent, discountAmount, orderDetail.getIsAppoint());
	        }
        }
		
		Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("orderType", obj.getOrderType());
        map.put("projectId", obj.getProjectId());
        Integer deptId = orderDetailMapper.selectDeptIdByProjectId(map);
        
        Map<String, Integer> mapDeptId = new HashMap<String, Integer>();
        mapDeptId.put("deptId", deptId);
        mapDeptId.put("detailId", detailId);
        orderDetailMapper.updateOrderDetailDeptId(mapDeptId);
		
	}
	
	/**
	 * 计算订单的提成
	 * @param orderDetail 详情标识
	 * @param shiftMahjongStepId 轮牌步骤标识
	 * @param count 数量
	 * @param employeeId 员工标识
	 * @param commissionDto 提成对象
	 * @param performanceDiscountPercent performanceDiscountPercent
	 * @param discountAmount 折扣金额
	 * @param isAppoint 是否指定
	 */
	protected void calculateCommonCommission(OrderDetail orderDetail, Integer shiftMahjongStepId, 
	        Integer count, Integer employeeId, 
	        CashierCommissionDto commissionDto, Integer performanceDiscountPercent, BigDecimal discountAmount, Integer isAppoint) {
	    
	    BigDecimal hundred = new BigDecimal(100);
	    
	    Map<String, Object> storeSettingMap = employeeObjectiveMapper.selectStoreSetting(orderDetail.getStoreId());
	    //提成是否扣除成本(0:不扣除，1:扣除)
	    Integer costCommissionType = Integer.valueOf(storeSettingMap.get("costCommissionType").toString());
	    
	    Integer commissionFixedType = Integer.valueOf(storeSettingMap.get("commissionFixedType").toString());
	    
	    // 员工提成
        BigDecimal empCommission = null;
        // 提成方式
        Integer commissionType = null;
        // 提成金额
        BigDecimal commissionAmount =  null;
        // 业绩计算
        BigDecimal commonCalculate = discountAmount;
                
        if (costCommissionType == 1) {
            commonCalculate = commonCalculate.subtract(commissionDto.getCostPrice());
        }
        
	    commissionType = commissionDto.getCommissionType();
	    commissionAmount = commissionDto.getCommissionAmount();
		switch(commissionType) {
			case 1 : // 按比例
			    empCommission = commonCalculate.multiply(commissionAmount).divide(hundred);
				break;
			case 2 : // 按固定金额
                empCommission = commissionAmount.multiply(new BigDecimal(count));
				break;
			default :
				break;
		}
		
		//当为预约时
		if (isAppoint == 1) {
		    empCommission = empCommission.add(commissionDto.getAppointmentReward());
		}
		
		if (empCommission != null) {
		    
		    empCommission = zeroChoose(empCommission);
		    
		    empCommission = empCommission.multiply(new BigDecimal(performanceDiscountPercent)).divide(new BigDecimal(100));
		    
            BigDecimal saveCommonCalculate = null;
            
            EmployeeObjective employeeObjective = new EmployeeObjective();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
            //修改本月实际完成项目销售目标
            Map<String, Object> isAssignStepPerformanceMap = orderDetailMapper.selectIsAssignStepPerformance(shiftMahjongStepId);
            //是否指定
            Integer isAssign = Integer.valueOf(isAssignStepPerformanceMap.get("isAssign").toString());
            //步骤业绩计算
            BigDecimal stepPerformance = new BigDecimal(isAssignStepPerformanceMap.get("stepPerformance").toString());
            //步骤业绩计算方式(2:固定,1:比例)
            Integer stepPerformanceType = Integer.valueOf(isAssignStepPerformanceMap.get("stepPerformanceType").toString());
            //该项目中固定步骤值
            BigDecimal fixedValue = new BigDecimal(isAssignStepPerformanceMap.get("fixedValue").toString());
            
            //比例
            if (stepPerformanceType == 1) {
                if (commissionFixedType == 0) {
                    saveCommonCalculate = commonCalculate.multiply(stepPerformance).divide(new BigDecimal(100));
                }
                else {
                    saveCommonCalculate = (commonCalculate.subtract(fixedValue)).multiply(stepPerformance).divide(new BigDecimal(100));
                }
            }
            //固定值
            else {
                saveCommonCalculate = stepPerformance;
            }
            
            saveCommonCalculate = zeroChoose(saveCommonCalculate);
            
            saveCommonCalculate = saveCommonCalculate.multiply(new BigDecimal(performanceDiscountPercent)).divide(new BigDecimal(100));
            
            employeeObjective.setActualTotalProjectSales(saveCommonCalculate);
            if (isAssign  == 1) {
                employeeObjective.setActualAssignProjectSales(saveCommonCalculate);
            }
            
            employeeObjective.setEmployeeId(employeeId);
            try {
                employeeObjective.setObjectiveMonth(dateFormat.format(dateFormat.parse(orderDetail.getCreateTime())));
            } 
            catch (ParseException e) {
                e.printStackTrace();
            }
            employeeObjective.setUpdateTime(DateUtil.getCurTimeStr());
            employeeObjectiveMapper.updateActual(employeeObjective);

			EmployeeCommission commission = new EmployeeCommission(orderDetail.getDetailId(), shiftMahjongStepId, 1, employeeId, 
			        saveCommonCalculate, empCommission, orderDetail.getCreateTime());
			orderDetailMapper.insertEmployeeCommission(commission);

		}
		
	}
	
	/**
	 * 赋值为o
	* @author 王大爷
	* @date 2016年1月6日 下午3:31:01
	* @param values 值
	* @return BigDecimal
	 */
	public BigDecimal zeroChoose (BigDecimal values) {
	    if (values.compareTo(new BigDecimal(0)) != 1) {
	        values = new BigDecimal(0);
        }
	    return values;
	}
}
