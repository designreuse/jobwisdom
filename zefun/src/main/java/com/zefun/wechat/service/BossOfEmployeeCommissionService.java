package com.zefun.wechat.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.utils.DataTypeUtil;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.entity.EmployeeCommission;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.mapper.EmployeeCommissionMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.EmployeeRewardMapper;
import com.zefun.web.mapper.PositioninfoMapper;
import com.zefun.wechat.dto.CommissionValueAndTypeDto;
import com.zefun.wechat.dto.EmployeeCommissionDetailOfBossDto;
import com.zefun.wechat.dto.EmployeeCommissionOfBossDto;

/**
 * Boss端员工业绩服务
* @author DavidLiang
* @date 2016年1月22日 下午7:26:05
 */
@Service
public class BossOfEmployeeCommissionService {
	
	/** 员工提成映射 */
	@Autowired
	private EmployeeCommissionMapper employeeCommissionMapper;
	
	/** 员工奖惩映射 */
	@Autowired
	private EmployeeRewardMapper employeeRewardMapper;
	
	/** 员工信息映射 */
	@Autowired
	private EmployeeInfoMapper employeeInfoMapper;
	
	/** 岗位信息映射 */
	@Autowired
	private PositioninfoMapper positioninfoMapper;

	/**
	 * 首页查询员工业绩
	* @author DavidLiang
	* @date 2016年1月23日 上午10:54:54
	* @param mav  员工业绩模型和视图
	* @param storeId  店铺id
	* @return  员工业绩模型和视图
	 */
	public ModelAndView findEmployeePerformanceHome(ModelAndView mav, int storeId) {
		//默认按当天现金查询
		List<EmployeeCommissionOfBossDto> employeeCommissionOfBossList = findEmployeeCommission(
				  storeId, null, "day", "cash");
		/*List<EmployeeCommissionOfBossDto> employeeCommissionOfBossList = employeeCommissionMapper.selectEmployeeCommissionByBoss(
				  storeId, null, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "cash");*/
		/*List<EmployeeCommissionOfBossDto> employeeCommissionOfBossList = employeeCommissionMapper.selectEmployeeCommissionByBoss(
				  storeId, null, "2015-12-28", "cash");*/
		mav.addObject("employeeCommissionOfBossList", employeeCommissionOfBossList);
//		mav.addObject("deptInfoList", deptInfoMapper.selectDeptByStoreId(storeId));
		mav.addObject("positionInfoList", positioninfoMapper.queryAllByStoreId(storeId));
		return mav;
	}
	
	/**
	 * 查询员工业绩
	* @author DavidLiang
	* @date 2016年1月23日 下午3:00:30
	* @param storeId  店铺id
	* @param positionId  岗位id
	* @param dateType  查询时间类型
	* @param sortType  排序类型
	* @return  员工业绩集合
	 */
	public List<EmployeeCommissionOfBossDto> findEmployeeCommission(int storeId, Integer positionId, String dateType, String sortType) {
		String time = DateUtil.getCurDateByType(dateType);
		if (positionId != null && positionId == -1) {
			positionId = null;
		}
//		return employeeCommissionMapper.selectEmployeeCommissionByBoss(storeId, deptId, "2015-12-28", sortType);
//		return employeeCommissionMapper.selectEmployeeCommissionByBoss(storeId, deptId, time, sortType);
		List<EmployeeCommissionOfBossDto> cashCommissionList = employeeCommissionMapper.selectEmployeeCashCommissionByBoss(storeId, positionId, time);
		List<EmployeeCommissionOfBossDto> cardCommissionList = employeeCommissionMapper.selectEmployeeCardCommissionByBoss(storeId, positionId, time);
		//合并现金业绩集和卡金业绩集(卡金加到现金中去)
		cashCommissionList = mergeTwoEmployeeCommissionOfBossDtoList(cashCommissionList, cardCommissionList);
		//可能会出现会员用疗程抵扣项目情况，需额外查order_detail中off_type = 1 && combo_id != null的值
		List<EmployeeCommissionOfBossDto> cardCommissionOfComboPeductionProjectList 
				  = employeeCommissionMapper.selectEmployeeCardCommissionOfComboDeductionProjectByBoss(storeId, positionId, time);
		cashCommissionList = mergeTwoEmployeeCommissionOfBossDtoList(cashCommissionList, cardCommissionOfComboPeductionProjectList);
		//拼接出该店铺没有出现的员工
		List<EmployeeInfo> allEmployeeList = employeeInfoMapper.selectEmployeeByStoreOrDeptOrPositionOrLevel(storeId, null, positionId, null);
		cashCommissionList = fillNoRecordEmployeeForSum(cashCommissionList, allEmployeeList);
		//排序
		Collections.sort(cashCommissionList, new Comparator<EmployeeCommissionOfBossDto>(){
		    @Override
			public int compare(EmployeeCommissionOfBossDto o1, EmployeeCommissionOfBossDto o2) {
				if (sortType != null && sortType.equals("cash")) {
					return new Double(o2.getCash()).compareTo(new Double(o1.getCash()));
				} 
				else if (sortType != null && sortType.equals("card")) {
					return new Double(o2.getCard()).compareTo(new Double(o1.getCard()));
				} 
				else if (sortType != null && sortType.equals("employee_commission")) {
					return new Double(o2.getEmployeeCommission()).compareTo(new Double(o1.getEmployeeCommission()));
				} 
				else if (sortType != null && sortType.equals("order_num")) {
					return new Integer(o2.getOrderNum()).compareTo(new Integer(o1.getOrderNum()));
				}
				return new Double(o2.getCash()).compareTo(new Double(o1.getCash()));
			}
        });
		//给double类型保留两位小数
		for (int i=0; i<cashCommissionList.size(); i++) {
			EmployeeCommissionOfBossDto ec = cashCommissionList.get(i);
			if (ec != null && ec.getCard() != 0) {
				ec.setCard(Double.parseDouble(String.format("%.2f", ec.getCard())));
			}
			if (ec != null && ec.getCash() != 0) {
				ec.setCash(Double.parseDouble(String.format("%.2f", ec.getCash())));
			}
			if (ec != null && ec.getEmployeeCommission() != 0) {
				ec.setEmployeeCommission(Double.parseDouble(String.format("%.2f", ec.getEmployeeCommission())));
			}
		}
		return cashCommissionList;
	}
	
	/**
	 * 员工业绩详情查询员工信息
	* @author DavidLiang
	* @date 2016年3月5日 下午1:55:47
	* @param employeeId  员工id
	* @return  员工信息
	* @throws ParseException  计算工龄抛出解析异常
	 */
	public EmployeeDto findEmployeeInfoOfCommissionDetail(Integer employeeId) throws ParseException {
		EmployeeDto employeeInfo = employeeCommissionMapper.selectEmployeeInfoOfCommissionDetail(employeeId);
		employeeInfo.setSeniority(DateUtil.caculateSeniority(employeeInfo.getCreateTime()));
		return employeeInfo;
	}
	
	/**
	 * 查看员工业绩详情
	* @author DavidLiang
	* @date 2016年1月26日 下午2:03:27
	* @param storeId  店铺id
	* @param employeeId  员工id
	* @param time  日期
	* @return  员工业绩详情
	 * @throws ParseException 
	 */
	public EmployeeCommissionDetailOfBossDto findEmployeeCommissionDetailHome(int storeId, Integer employeeId, String time) throws ParseException {
		/*EmployeeDto employeeInfo = employeeCommissionMapper.selectEmployeeInfoOfCommissionDetail(employeeId);
		employeeInfo.setSeniority(DateUtil.caculateSeniority(employeeInfo.getCreateTime()));
		mav.addObject("employeeInfo", employeeInfo);*/
		/**
		 * 业绩分布
		 */
		//员工现金业绩详情
		List<CommissionValueAndTypeDto> cashCommissionDetailList = employeeCommissionMapper
				  .selectCashCommissionDetailByEmployeeIdAndTime(employeeId, time);
		//员工卡金业绩详情
		List<CommissionValueAndTypeDto> cardCommissionDetailList = employeeCommissionMapper
				  .selectCardCommissionDetailByEmployeeIdAndTime(employeeId, time);
		EmployeeCommissionDetailOfBossDto employeeCommissionDetailOfBossDto = new EmployeeCommissionDetailOfBossDto();
		//再加上会员用疗程抵扣项目中的业绩到  卡金业绩中的项目业绩  中去
		Double comboDeductionProjectValue = employeeCommissionMapper.selectComboDeductionProjectEmployeeCommissionDetail(employeeId, time);
		if (employeeCommissionDetailOfBossDto != null && comboDeductionProjectValue != null) {
			employeeCommissionDetailOfBossDto.setCardCommissionOfProject(
					  employeeCommissionDetailOfBossDto.getCardCommissionOfProject() + comboDeductionProjectValue.doubleValue());
		}
		employeeCommissionDetailOfBossDto = fillCommissionDistribute(
				  employeeCommissionDetailOfBossDto, cashCommissionDetailList, cardCommissionDetailList);
		/**
		 * 技能分析(只包含项目，不包含疗程和商品)
		 */
		/*//大小项
		List<Map<String, Object>> customerCountOfProjectTypeList = employeeCommissionMapper.selectCustomerCountGroupByProjectType(employeeId, time);
		employeeCommissionDetailOfBossDto = fillCustomerCountOfProjectType(employeeCommissionDetailOfBossDto, customerCountOfProjectTypeList);
		//是否预约
		List<Map<String, Object>> customerCountOfAppointList = employeeCommissionMapper.selectCustomerCountGroupByAppoint(employeeId, time);
		employeeCommissionDetailOfBossDto = fillCustomerCountOfAppoint(employeeCommissionDetailOfBossDto, customerCountOfAppointList);
		//是否指定
		List<Map<String, Object>> customerCountOfAssignList = employeeCommissionMapper.selectCustomerCountGroupByAssign(employeeId, time);
		employeeCommissionDetailOfBossDto = fillCustomerCountOfAssign(employeeCommissionDetailOfBossDto, customerCountOfAssignList);*/
		List<EmployeeCommission> skillList = employeeCommissionMapper.selectCommissionAndStepByEmployeeIdAndTime(employeeId, time);
		employeeCommissionDetailOfBossDto = fillSkillAnalytics(employeeCommissionDetailOfBossDto, skillList);
		
		/**
		 * 提成来源
		 */
		List<Map<String, Object>> employeeCommissionList = employeeCommissionMapper.selectCommissionAmountGroupByOrderType(employeeId, time);
		employeeCommissionDetailOfBossDto = fillEmployeeCommission(employeeCommissionDetailOfBossDto, employeeCommissionList);
		/**
		 * 员工评分
		 */
		List<Map<String, Object>> employeeEvaluateList = employeeCommissionMapper.selectEvaluateGroupByRate(employeeId, time);
		employeeCommissionDetailOfBossDto = fillEmployeeEvaluate(employeeCommissionDetailOfBossDto, employeeEvaluateList);
		/**
		 * 工作态度
		 */
		List<Map<String, Object>> rewardList = employeeRewardMapper.selectRewardCountGroupByType(employeeId, time);
		employeeCommissionDetailOfBossDto = fillRewardCount(employeeCommissionDetailOfBossDto, rewardList);
		
//		mav.addObject("employeeCommissionDetailOfBossDto", employeeCommissionDetailOfBossDto);
		return employeeCommissionDetailOfBossDto;
	}
	
	/**
	 * 合并两个List<EmployeeCommissionOfBossDto>
	* @author DavidLiang
	* @date 2016年2月27日 下午5:31:59
	* @param primaryList  主集合
	* @param deputyList  副集合
	* @return  主集合
	 */
	private List<EmployeeCommissionOfBossDto> mergeTwoEmployeeCommissionOfBossDtoList(
			  List<EmployeeCommissionOfBossDto> primaryList, List<EmployeeCommissionOfBossDto> deputyList) {
		for (int i=0; i<deputyList.size(); i++) {
			EmployeeCommissionOfBossDto cardCommission = deputyList.get(i);
			boolean exist = false;
			for (int j=0; j<primaryList.size(); j++) {
				EmployeeCommissionOfBossDto cashCommission = primaryList.get(j);
				if (cashCommission.getEmployeeId() == cardCommission.getEmployeeId()) {
					exist = true;
					cashCommission.setCard(cardCommission.getCard());
					cashCommission.setEmployeeCommission(cashCommission.getEmployeeCommission() + cardCommission.getEmployeeCommission());
					cashCommission.setOrderNum(cashCommission.getOrderNum() + cardCommission.getOrderNum());
				}
			}
			if (!exist) {
				primaryList.add(cardCommission);
            }
		}
		return primaryList;
	}
	
	/**
	 * 员工业绩汇总页面填充没有记录的员工
	* @author DavidLiang
	* @date 2016年3月3日 下午5:20:21
	* @param employeeCommissionList  具有记录的员工业绩记录
	* @param allEmployeeList  全部员工信息记录
	* @return  填充后的
	 */
	private List<EmployeeCommissionOfBossDto> fillNoRecordEmployeeForSum(
			  List<EmployeeCommissionOfBossDto> employeeCommissionList, List<EmployeeInfo> allEmployeeList) {
		for (int i=0; i<allEmployeeList.size(); i++) {
			boolean exist = false;
			EmployeeInfo employeeInfo = allEmployeeList.get(i);
			for (int j=0; j<employeeCommissionList.size(); j++) {
				if (employeeInfo.getEmployeeId().intValue() == employeeCommissionList.get(j).getEmployeeId()) {
					exist = true;
				}
			}
			if (! exist) {
				employeeCommissionList.add(new EmployeeCommissionOfBossDto(employeeInfo.getEmployeeId(), 
						  employeeInfo.getName(), 0.00, 0.00, 0.00, 0));
			}
		}
		return employeeCommissionList;
	}
	
	/**
	 * 业绩分布赋值
	* @author DavidLiang
	* @date 2016年1月27日 下午4:23:19
	* @param employeeCommissionDetailOfBossDto  员工业绩详情dto
	* @param cashCommissionDetailList  员工现金业绩详情
	* @param cardCommissionDetailList  员工卡金业绩详情
	* @return  员工业绩详情dto
	 */
	private EmployeeCommissionDetailOfBossDto fillCommissionDistribute(EmployeeCommissionDetailOfBossDto employeeCommissionDetailOfBossDto, 
			  List<CommissionValueAndTypeDto> cashCommissionDetailList, List<CommissionValueAndTypeDto> cardCommissionDetailList) {
		for (int i=0; i<cashCommissionDetailList.size(); i++) {
			CommissionValueAndTypeDto cvt = cashCommissionDetailList.get(i);
			double cashCommissionOfCard = employeeCommissionDetailOfBossDto.getCashCommissionOfCard();
			switch(cvt.getOrderType()) {
				case 1:
					employeeCommissionDetailOfBossDto.setCashCommissionOfProject(cvt.getCommissionCalculate().doubleValue());
					break;
				case 2:
					employeeCommissionDetailOfBossDto.setCashCommissionOfGoods(cvt.getCommissionCalculate().doubleValue());
					break;
				case 3:
					employeeCommissionDetailOfBossDto.setCashCommissionOfPackage(cvt.getCommissionCalculate().doubleValue());
					break;
				case 4:
					employeeCommissionDetailOfBossDto.setCashCommissionOfCard(cashCommissionOfCard + cvt.getCommissionCalculate().doubleValue());
					break;
				case 5:
					employeeCommissionDetailOfBossDto.setCashCommissionOfCard(cashCommissionOfCard + cvt.getCommissionCalculate().doubleValue());
					break;
				case 6:
					employeeCommissionDetailOfBossDto.setCashCommissionOfCard(cashCommissionOfCard + cvt.getCommissionCalculate().doubleValue());
					break;
				default:
					break;
			}
		}
		employeeCommissionDetailOfBossDto.setCashCommissionSum(employeeCommissionDetailOfBossDto.getCashCommissionOfProject() 
				  + employeeCommissionDetailOfBossDto.getCashCommissionOfGoods() + employeeCommissionDetailOfBossDto.getCashCommissionOfPackage() 
				  + employeeCommissionDetailOfBossDto.getCashCommissionOfCard());
		DataTypeUtil.formatDataReserveTwoDecimal(employeeCommissionDetailOfBossDto.getCashCommissionSum());
		for (int i=0; i<cardCommissionDetailList.size(); i++) {
			CommissionValueAndTypeDto cvt = cardCommissionDetailList.get(i);
			double cardCommissionOfCard = employeeCommissionDetailOfBossDto.getCardCommissionOfCard();
			switch(cvt.getOrderType()) {
				case 1:
					employeeCommissionDetailOfBossDto.setCardCommissionOfProject(cvt.getCommissionCalculate().doubleValue());
					break;
				case 2:
					employeeCommissionDetailOfBossDto.setCardCommissionOfGoods(cvt.getCommissionCalculate().doubleValue());
					break;
				case 3:
					employeeCommissionDetailOfBossDto.setCardCommissionOfPackage(cvt.getCommissionCalculate().doubleValue());
					break;
				case 4:
					employeeCommissionDetailOfBossDto.setCardCommissionOfCard(cardCommissionOfCard + cvt.getCommissionCalculate().doubleValue());
					break;
				case 5:
					employeeCommissionDetailOfBossDto.setCardCommissionOfCard(cardCommissionOfCard + cvt.getCommissionCalculate().doubleValue());
					break;
				case 6:
					employeeCommissionDetailOfBossDto.setCardCommissionOfCard(cardCommissionOfCard + cvt.getCommissionCalculate().doubleValue());
					break;
				default:
					break;
			}
		}
		employeeCommissionDetailOfBossDto.setCardCommissionSum(employeeCommissionDetailOfBossDto.getCardCommissionOfProject() 
				  + employeeCommissionDetailOfBossDto.getCardCommissionOfGoods() + employeeCommissionDetailOfBossDto.getCardCommissionOfPackage() 
				  + employeeCommissionDetailOfBossDto.getCardCommissionOfCard());
		DataTypeUtil.formatDataReserveTwoDecimal(employeeCommissionDetailOfBossDto.getCardCommissionSum());
		return employeeCommissionDetailOfBossDto;
	}
	
	/**
	 * 填充客户汇总大小项
	* @author DavidLiang
	* @date 2016年1月27日 下午9:03:40
	* @param employeeCommissionDetailOfBossDto  员工业绩详情dto
	* @param customerCountOfProjectTypeList  大小项客户量
	* @return  员工业绩详情dto
	 */
	/*private EmployeeCommissionDetailOfBossDto fillCustomerCountOfProjectType(EmployeeCommissionDetailOfBossDto employeeCommissionDetailOfBossDto, 
			  List<Map<String, Object>> customerCountOfProjectTypeList) {
		for (int i=0; i<customerCountOfProjectTypeList.size(); i++) {
			Map<String, Object> map = customerCountOfProjectTypeList.get(i);
//			if (map.get("project_type").equals(1)) {
			if ("1".equals(map.get("project_type"))) {
				employeeCommissionDetailOfBossDto.setCustomerCountOfBigProject((long) map.get("order_num"));
			}
//			else if (map.get("project_type").equals(2)) {
			else if ("2".equals(map.get("project_type"))) {
				employeeCommissionDetailOfBossDto.setCustomerCountOfSmallProject((long) map.get("order_num"));
			}
		}
		return employeeCommissionDetailOfBossDto;
	}*/
	
	/**
	 * 填充客户汇总预约项
	* @author DavidLiang
	* @date 2016年1月28日 下午12:04:18
	* @param employeeCommissionDetailOfBossDto  员工业绩详情dto
	* @param customerCountOfAppointList  预约项客户量
	* @return  员工业绩详情dto
	 */
	/*private EmployeeCommissionDetailOfBossDto fillCustomerCountOfAppoint(EmployeeCommissionDetailOfBossDto employeeCommissionDetailOfBossDto, 
			    List<Map<String, Object>> customerCountOfAppointList) {
		for (int i=0; i<customerCountOfAppointList.size(); i++) {
			Map<String, Object> map = customerCountOfAppointList.get(i);
			if (map.get("is_appoint").equals(0)) {
				employeeCommissionDetailOfBossDto.setCustomerCountOfNotAppoint((long) map.get("order_num"));
			}
			else if (map.get("is_appoint").equals(1)) {
				employeeCommissionDetailOfBossDto.setCustomerCountOfIsAppoint((long) map.get("order_num"));
			}
		}
		return employeeCommissionDetailOfBossDto;
	}*/
	
	/**
	 * 填充客户汇总指定项
	* @author DavidLiang
	* @date 2016年1月28日 下午12:31:22
	* @param employeeCommissionDetailOfBossDto  员工业绩详情dto
	* @param customerCountOfAssignList  指定项客户量
	* @return  员工业绩详情dto
	 */
	/*private EmployeeCommissionDetailOfBossDto fillCustomerCountOfAssign(EmployeeCommissionDetailOfBossDto employeeCommissionDetailOfBossDto, 
		      List<Map<String, Object>> customerCountOfAssignList) {
		for (int i=0; i<customerCountOfAssignList.size(); i++) {
			Map<String, Object> map = customerCountOfAssignList.get(i);
			if (map.get("is_assign").equals(0)) {
				employeeCommissionDetailOfBossDto.setCustomerCountOfNotAssign((long) map.get("order_num"));
			}
			else if (map.get("is_appoint").equals(1)) {
				employeeCommissionDetailOfBossDto.setCustomerCountOfIsAssign((long) map.get("order_num"));
			}
		}
		return employeeCommissionDetailOfBossDto;
	}*/
	
	/**
	 * 填充技能分析
	* @author DavidLiang
	* @date 2016年2月26日 下午4:32:18
	* @param dto  员工业绩详情dto
	* @param skillList  技能分析员工提成list
	* @return  员工业绩详情dto
	 */
	private EmployeeCommissionDetailOfBossDto fillSkillAnalytics(EmployeeCommissionDetailOfBossDto dto, 
			  List<EmployeeCommission> skillList) {
		for (int i=0; i<skillList.size(); i++) {
			EmployeeCommission employeeCommission = skillList.get(i);
			//判断大小项
			if (employeeCommission.getProjectType() == 1) {
				dto.setCustomerCountOfBigProject(dto.getCustomerCountOfBigProject() + 1);
			} 
			else if (employeeCommission.getProjectType() == 2) {
				dto.setCustomerCountOfSmallProject(dto.getCustomerCountOfSmallProject() + 1);
			}
			//判断是否指定
			if (employeeCommission.getIsAssign() == 0) {
				dto.setCustomerCountOfNotAssign(dto.getCustomerCountOfNotAssign() + 1);
			}
			else if (employeeCommission.getIsAssign() == 1) {
				dto.setCustomerCountOfIsAssign(dto.getCustomerCountOfIsAssign() + 1);
			}
			//判断是否预约
			if (employeeCommission.getIsAppoint() == 0) {
				dto.setCustomerCountOfNotAppoint(dto.getCustomerCountOfNotAppoint() + 1);
			}
			else if (employeeCommission.getIsAppoint() == 1) {
				dto.setCustomerCountOfIsAppoint(dto.getCustomerCountOfIsAppoint() + 1);
			}
		}
		return dto;
	}
	
	/**
	 * 填充员工业绩详情dto
	* @author DavidLiang
	* @date 2016年1月28日 下午3:08:46
	* @param employeeCommissionDetailOfBossDto  员工业绩详情dto
	* @param employeeCommissionList  员工提成集
	* @return  员工业绩详情dto
	 */
	private EmployeeCommissionDetailOfBossDto fillEmployeeCommission(EmployeeCommissionDetailOfBossDto employeeCommissionDetailOfBossDto, 
			  List<Map<String, Object>> employeeCommissionList) {
		for (int i=0; i<employeeCommissionList.size(); i++) {
			Map<String, Object> map = employeeCommissionList.get(i);
			if (map.get("order_type").toString().equals("1.00")) {
				employeeCommissionDetailOfBossDto.setEmployeeCommissionOfProject((BigDecimal) map.get("commission_amount"));
			}
			else if (map.get("order_type").toString().equals("2.00")) {
				employeeCommissionDetailOfBossDto.setEmployeeCommissionOfGoods((BigDecimal) map.get("commission_amount"));
			}
			else if (map.get("order_type").toString().equals("3.00")) {
				employeeCommissionDetailOfBossDto.setEmployeeCommissionOfPackage((BigDecimal) map.get("commission_amount"));
			}
			else if (map.get("order_type").toString().equals("4.00")) {
				employeeCommissionDetailOfBossDto.setEmployeeCommissionOfCard((BigDecimal) map.get("commission_amount"));
			}
		}
		return employeeCommissionDetailOfBossDto;
	}
	
	/**
	 * 填充员工评价
	* @author DavidLiang
	* @date 2016年1月28日 下午6:08:55
	* @param employeeCommissionDetailOfBossDto  员工业绩详情dto
	* @param employeeEvaluateList  员工评价集
	* @return  员工业绩详情dto
	 */
	private EmployeeCommissionDetailOfBossDto fillEmployeeEvaluate(EmployeeCommissionDetailOfBossDto employeeCommissionDetailOfBossDto, 
			  List<Map<String, Object>> employeeEvaluateList) {
		for (int i=0; i<employeeEvaluateList.size(); i++) {
			Map<String, Object> map = employeeEvaluateList.get(i);
			if (map.get("evaluate_rate") == null) {
				employeeCommissionDetailOfBossDto.setEmployeeEvaluateNull((long) map.get("evaluate_count"));
			}
			else if (map.get("evaluate_rate").equals(5)) {
				employeeCommissionDetailOfBossDto.setEmployeeEvaluateFive((long) map.get("evaluate_count"));
			}
			else if (map.get("evaluate_rate").equals(4)) {
				employeeCommissionDetailOfBossDto.setEmployeeEvaluateFour((long) map.get("evaluate_count"));
			}
			else if (map.get("evaluate_rate").equals(3)) {
				employeeCommissionDetailOfBossDto.setEmployeeEvaluateThree((long) map.get("evaluate_count"));
			}
			else if (map.get("evaluate_rate").equals(2)) {
				employeeCommissionDetailOfBossDto.setEmployeeEvaluateTwo((long) map.get("evaluate_count"));
			}
			else if (map.get("evaluate_rate").equals(1)) {
				employeeCommissionDetailOfBossDto.setEmployeeEvaluateOne((long) map.get("evaluate_count"));
			}
		}
		return employeeCommissionDetailOfBossDto;
	}
	
	/**
	 * 填充奖惩
	* @author DavidLiang
	* @date 2016年1月28日 下午8:22:06
	* @param employeeCommissionDetailOfBossDto  员工业绩详情dto
	* @param rewardList  奖惩集
	* @return  员工业绩详情dto
	 */
	private EmployeeCommissionDetailOfBossDto fillRewardCount(EmployeeCommissionDetailOfBossDto employeeCommissionDetailOfBossDto, 
			  List<Map<String, Object>> rewardList) {
		for (int i=0; i<rewardList.size(); i++) {
			Map<String, Object> map = rewardList.get(i);
			switch (map.get("type").toString()) {
				case "1":
					employeeCommissionDetailOfBossDto.setRewardCountOfLate((long) map.get("reward_count"));
					break;
				case "3":
					employeeCommissionDetailOfBossDto.setRewardCountOfHoliday((long) map.get("reward_count"));
					break;
				case "4":
					employeeCommissionDetailOfBossDto.setRewardCountOfAbsenteeism((long) map.get("reward_count"));
					break;
				case "6":
					employeeCommissionDetailOfBossDto.setRewardCountOfSmallMistake((long) map.get("reward_count"));
					break;
				case "7":
					employeeCommissionDetailOfBossDto.setRewardCountOfSeriousMistake((long) map.get("reward_count"));
					break;
				case "11":
					employeeCommissionDetailOfBossDto.setRewardCountOfComplaint((long) map.get("reward_count"));
					break;
				default:
					break;
			}
		}
		return employeeCommissionDetailOfBossDto;
	}
	
}
