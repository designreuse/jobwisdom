package com.zefun.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.web.service.BusinessAnalysissService;

/**
 * 营业分析控制器
* @author 张进军
* @date Jan 13, 2016 8:20:36 PM
 */
@Controller
public class BusinessAnalysisController extends BaseController{
	
	/** 营业分析服务对象 */
	@Autowired
	private BusinessAnalysissService businessAnalysissService;
	
	
	/**
     * 客情分析页面
    * @author 张进军
    * @date Jan 13, 2016 8:19:34 PM
    * @param request	请求对象
    * @param response	响应对象
    * @return	客情分析页面
     */
    @RequestMapping(value = Url.BusinessAnalysis.VIEW_ANALYSIS_CUSTOMER)
    public ModelAndView customerView(HttpServletRequest request, HttpServletResponse response){
    	int storeId = getStoreId(request);
    	return businessAnalysissService.customerView(storeId);
    }
    
    
    /**
     * 预约分析页面
    * @author 张进军
    * @date Jan 13, 2016 8:19:34 PM
    * @param request	请求对象
    * @param response	响应对象
    * @return	预约分析页面
     */
    @RequestMapping(value = Url.BusinessAnalysis.VIEW_ANALYSIS_APPOINTMENT)
    public ModelAndView appointmentView(HttpServletRequest request, HttpServletResponse response){
    	int storeId = getStoreId(request);
    	return businessAnalysissService.appointmentView(storeId);
    }
    
    
    /**
     * 员工分析页面
    * @author 张进军
    * @date Jan 13, 2016 8:19:34 PM
    * @param request	请求对象
    * @param response	响应对象
    * @return	员工分析页面
     */
    @RequestMapping(value = Url.BusinessAnalysis.VIEW_ANALYSIS_EMPLOYEE)
    public ModelAndView employeeView(HttpServletRequest request, HttpServletResponse response){
    	int storeId = getStoreId(request);
    	return businessAnalysissService.employeeView(storeId);
    }
    
    
    /**
     * 营业分析页面
    * @author 张进军
    * @date Jan 13, 2016 8:19:34 PM
    * @param request	请求对象
    * @param response	响应对象
    * @return	营业分析页面
     */
    @RequestMapping(value = Url.BusinessAnalysis.VIEW_ANALYSIS_BUSINESS)
    public ModelAndView businessView(HttpServletRequest request, HttpServletResponse response){
    	int storeId = getStoreId(request);
    	return businessAnalysissService.businessView(storeId);
    }
	
	
    /**
     * 员工工资单页面
    * @author 张进军
    * @date Jan 13, 2016 8:19:34 PM
    * @param month		工资月份
    * @param request	请求对象
    * @param response	响应对象
    * @return	员工工资单页面
     */
    @RequestMapping(value = Url.BusinessAnalysis.VIEW_ANALYSIS_PAYROLL)
    public ModelAndView payrollView(@RequestParam(value = "month", required = false) String month,
    		    HttpServletRequest request, HttpServletResponse response){
    	int storeId = getStoreId(request);
    	return businessAnalysissService.payrollView(storeId, month);
    }

}
