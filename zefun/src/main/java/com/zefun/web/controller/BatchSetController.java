package com.zefun.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.service.BatchSetService;

/**
 * 批量设置
 * 
 * @author laowang
 * @date 2015年10月21日 下午3:11:56
 */
@Controller
public class BatchSetController extends BaseController {

	/** 自助收银 */
	@Autowired
	private BatchSetService batchSetService;
	
	/**
	 * 初始化项目批量设置页面
	 * @author 老王
	 * @date 2015年10月21日 下午3:12:13
	 * @param request
	 *            request
	 * @param response
	 *            response
	 * @return ModelAndView
	 */
	@RequestMapping(value = Url.BatchSet.VIEW_INITIALIZATION_PROJECT_BATCH_SET, method = RequestMethod.GET)
	public ModelAndView initializationProjectBatchSet(HttpServletRequest request, HttpServletResponse response) {
		Integer storeId = getStoreId(request);
		return batchSetService.initializationProjectBatchSet(storeId);
	}
	
	/**
	 * 保存批量设置业绩
	* @author 老王
	* @date 2016年8月12日 下午6:09:30 
	* @param request request
	* @param response response
	* @param projectIdListStr projectIdListStr
	* @param calculateListStr calculateListStr
	* @return BaseDto
	 */
	@RequestMapping(value = Url.BatchSet.ACTION_BATCH_SET_CALCULATE)
	@ResponseBody
	public BaseDto batchSetCalculate (HttpServletRequest request, HttpServletResponse response, String projectIdListStr,
			  String calculateListStr) {
		Integer lastOperatorId = getUserId(request);
		return batchSetService.batchSetCalculate(projectIdListStr, calculateListStr, lastOperatorId);
	}
	
	/**
	 * 保存批量设置提成
	* @author 老王
	* @date 2016年8月12日 下午6:09:30 
	* @param request request
	* @param response response
	* @param projectIdListStr projectIdListStr
	* @param commissionListStr commissionListStr
	* @return BaseDto
	 */
	@RequestMapping(value = Url.BatchSet.ACTION_BATCH_SET_COMMISSION)
	@ResponseBody
	public BaseDto batchSetCommission (HttpServletRequest request, HttpServletResponse response, String projectIdListStr,
			  String commissionListStr) {
		Integer lastOperatorId = getUserId(request);
		Integer storeId = getStoreId(request);
		return batchSetService.batchSetCommission(projectIdListStr, commissionListStr, lastOperatorId, storeId);
	}
}
