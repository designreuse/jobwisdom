package com.zefun.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.service.ProgrammeService;

/**
 * 方案模块
* @author 老王
* @date Jan 13, 2016 8:20:36 PM
 */
@Controller
public class ProgrammeController extends BaseController{
	
	/** 营业分析服务对象 */
	@Autowired
	private ProgrammeService programmeService;
	
	
	/**
     * 大客户分析
    * @author 张进军
    * @date Jan 13, 2016 8:19:34 PM
    * @param request	请求对象
    * @param response	响应对象
    * @return	客情分析页面
     */
    @RequestMapping(value = Url.Programme.VIEW_BIG_CUSTOMER_ANALYSIS)
    public ModelAndView bigCustomerAnalysis(HttpServletRequest request, HttpServletResponse response){
    	String storeAccount = getStoreAccount(request);
    	return programmeService.bigCustomerAnalysis(storeAccount);
    }
    
    /**
     * 初始化该企业的规则数据
    * @author 老王
    * @date 2016年7月27日 下午3:23:55 
    * @param request	请求对象
    * @param response	响应对象
    * @param ruleType   规则类型
    * @return BaseDto
     */
    @RequestMapping(value = Url.Programme.ACTION_INITIALIZATION_SETTING_RULE)
    @ResponseBody
    public BaseDto initializationSettingRule (HttpServletRequest request, HttpServletResponse response, Integer ruleType) {
    	String storeAccount = getStoreAccount(request);
    	return programmeService.initializationSettingRule(storeAccount);
    }
    
    /**
     * 保存方案规则
    * @author 老王
    * @date 2016年7月27日 下午3:19:41 
    * @param request	请求对象
    * @param response	响应对象
    * @param storeIdOrAccount 方案规则归宿（门店或者企业）
    * @param ruleType 方案规则类型（1：大客户分析）
    * @param ruleInfo 累计账号数量
    * @return BaseDto
     */
    public BaseDto updateSettingRule (HttpServletRequest request, HttpServletResponse response, 
    		  String storeIdOrAccount, Integer ruleType, String ruleInfo) {
    	return programmeService.updateSettingRule(storeIdOrAccount, ruleType, ruleInfo);
    }

}
