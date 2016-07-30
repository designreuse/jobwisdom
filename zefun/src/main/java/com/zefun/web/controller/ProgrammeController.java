package com.zefun.web.controller;

import java.util.ArrayList;
import java.util.List;

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

import net.sf.json.JSONArray;

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
    * @param pageType 页面类型
    * @return	客情分析页面
     */
    @RequestMapping(value = Url.Programme.VIEW_BIG_CUSTOMER_ANALYSIS)
    public ModelAndView bigCustomerAnalysis(HttpServletRequest request, HttpServletResponse response, Integer pageType){
    	String storeAccount = getStoreAccount(request);
    	Integer roleId = getRoleId(request);
    	return programmeService.bigCustomerAnalysis(storeAccount, pageType, roleId);
    }

    
    /**
     * 保存方案规则
    * @author 老王
    * @date 2016年7月27日 下午3:19:41 
    * @param request	请求对象
    * @param response	响应对象
    * @param settingRuleId 方案规则标识
    * @param ruleType 方案规则类型（1：大客户分析）
    * @param ruleInfo 累计账号数量
    * @return BaseDto
     */
    @RequestMapping(value = Url.Programme.ACTION_INITIALIZATION_SETTING_RULE)
    @ResponseBody
    public BaseDto updateSettingRule (HttpServletRequest request, HttpServletResponse response, Integer settingRuleId,
    		  Integer ruleType, String ruleInfo) {
    	return programmeService.updateSettingRule(settingRuleId, ruleType, ruleInfo);
    }

    /**
     * 大客户分析数据
    * @author 老王
    * @date 2016年7月29日 下午7:41:04 
    * @param request	请求对象
    * @param response	响应对象
    * @param storeIdOrAccount 方案规则归宿（门店或者企业）
    * @param ruleType 1、门店 2企业号
    * @param pageType 拉取数据类型
    * @return BaseDto
     */
    @RequestMapping(value = Url.Programme.ACTION_BIG_MEMBER_DATA)
    @ResponseBody
    public BaseDto bigMemberData (HttpServletRequest request, HttpServletResponse response, String storeIdOrAccount, 
    		  Integer ruleType, Integer pageType) {
    	return programmeService.bigMemberData(storeIdOrAccount, ruleType, pageType);
    }
    
    /**
     *  规则内所有会员信息
    * @author 老王
    * @date 2016年7月30日 下午2:17:24 
    * @param request	请求对象
    * @param response	响应对象
    * @param memberIdListStr 会员标识集合
    * @param pageNo pageNo
    * @param pageSize pageSize
    * @return BaseDto
     */
    @RequestMapping(value = Url.Programme.ACTION_RULE_MEMBERID_LIST)
    @ResponseBody
    public BaseDto ruleMemberIdList (HttpServletRequest request, HttpServletResponse response, String memberIdListStr, 
    		  Integer pageNo, Integer pageSize) {
    	List<Integer> memberIdList = new ArrayList<>();
    	JSONArray jsonArray = JSONArray.fromObject(memberIdListStr);
    	for (Object object : jsonArray) {
    		memberIdList.add(Integer.valueOf(object.toString()));
		}
    	return programmeService.ruleMemberIdList(pageNo, pageSize, memberIdList);
    }
}
