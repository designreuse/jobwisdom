package com.zefun.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.EmployeePayrollDto;
import com.zefun.web.mapper.BusinessAnalysissMapper;


/**
 * 营业分析服务类
* @author 张进军
* @date Jan 13, 2016 8:20:21 PM
 */
@Service
public class BusinessAnalysissService {

	/** 营业分析数据操作对象 */
	@Autowired
	private BusinessAnalysissMapper businessAnalysissMapper;
	
	
	/**
     * 客情分析页面
    * @author 张进军
    * @date Jan 13, 2016 8:19:34 PM
    * @param storeId    门店标识
    * @return	客情分析页面
     */
	public ModelAndView customerView(int storeId) {
		ModelAndView mav = new ModelAndView(View.BusinessAnalysis.CUSTOMER);
		return mav;
	}
	
	
	/**
     * 预约分析页面
    * @author 张进军
    * @date Jan 13, 2016 8:19:34 PM
    * @param storeId    门店标识
    * @return	预约分析页面
     */
	public ModelAndView appointmentView(int storeId) {
		ModelAndView mav = new ModelAndView(View.BusinessAnalysis.APPOINTMENT);
		return mav;
	}
	
	
	/**
     * 员工分析页面
    * @author 张进军
    * @date Jan 13, 2016 8:19:34 PM
    * @param storeId    门店标识
    * @return	员工分析页面
     */
	public ModelAndView employeeView(int storeId) {
		ModelAndView mav = new ModelAndView(View.BusinessAnalysis.EMPLOYEE);
		return mav;
	}
	
	
	/**
     * 营业分析页面
    * @author 张进军
    * @date Jan 13, 2016 8:19:34 PM
    * @param storeId    门店标识
    * @return	营业分析页面
     */
	public ModelAndView businessView(int storeId) {
		ModelAndView mav = new ModelAndView(View.BusinessAnalysis.BUSINESS);
		return mav;
	}
	
	
	/**
     * 员工工资单页面
    * @author 张进军
    * @date Jan 13, 2016 8:19:34 PM
    * @param storeId	门店标识
    * @param month		工资月份
    * @return	员工工资单页面
     */
	public ModelAndView payrollView(int storeId, String month) {
		ModelAndView mav = new ModelAndView(View.BusinessAnalysis.PAYROLL);
		mav.addAllObjects(payrollAction(storeId, month == null ? DateUtil.getCurMonth() : month));
		return mav;
	}
	
	
	/**
	 * 查看门店下指定月份的工资信息
	* @author 张进军
	* @date Jan 14, 2016 12:06:01 AM
	* @param storeId	门店标识
	* @param month	月份
	* @return	工资信息
	 */
	private Map<String, Object> payrollAction(int storeId, String month){
		Map<String, Object> map = new HashMap<>();
		map.put("storeId", storeId);
		map.put("month", month);
		List<EmployeePayrollDto> employeePayrollList = businessAnalysissMapper.selectPayrollByStoreId(map);
		map.put("employeePayrollList", employeePayrollList);
		
		//根据当前时间获取近半年内的日期
		List<String> monthList = new ArrayList<>();
		String curMonth = DateUtil.getCurMonth();
		monthList.add(curMonth);
		for (int i = 1; i < 6; i++) {
			monthList.add(DateUtil.addMonth(curMonth, -i));
		}
		map.put("monthList", monthList);
		return map;
	}

}
