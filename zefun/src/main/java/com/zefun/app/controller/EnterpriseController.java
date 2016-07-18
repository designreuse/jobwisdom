
package com.zefun.app.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.app.service.EnterpriseService;
import com.zefun.common.consts.Url;
import com.zefun.web.controller.BaseController;
import com.zefun.web.dto.BaseDto;

/**
 * 
* @author 老王
* @date 2016年5月21日 下午7:07:12
 */
@Controller
public class EnterpriseController extends BaseController{
	
    /**
     * 配置员工出勤
     */
	@Autowired
	private EnterpriseService enterpriseService;
	
	/**
	 * 查询所有的门店记录
	* @author 老王
	* @date 2016年5月21日 下午7:18:18 
	* @param request 返回
	* @param response 请求
	* @return ModelAndView
	 */
	@RequestMapping(value =  Url.Enterprise.VIEW_SHOW_ENTERPRISE, method = RequestMethod.GET)
    public ModelAndView showEnterprise(HttpServletRequest request, HttpServletResponse response){
        return enterpriseService.showEnterprise();
    }
	
	/**
	 * 新增企业
	* @author 老王
	* @date 2016年5月21日 下午7:40:30 
	* @param request 返回
	* @param enterpriseName 企业名称
	* @param enterpriseLinkphone  企业联系电话
	* @param enterpriseLinkname 企业联系人
	* @param storeAccount 企业代号
	* @param enterpriseEdition 企业版本
	* @param useTime 使用时间
	* @param enterpriseInfoId 标识
	* @return BaseDto
	 */
	@RequestMapping(value =  Url.Enterprise.ADD_ENTERPRISE, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto addEnterprise (HttpServletRequest request, String enterpriseName, String enterpriseLinkphone, 
			  String enterpriseLinkname, String storeAccount, 
			  Integer enterpriseEdition, Integer useTime , Integer enterpriseInfoId) {
	    
	    Integer userId = getUserId(request) ;
		return enterpriseService.addEnterprise(enterpriseName, enterpriseLinkphone, enterpriseLinkname, storeAccount, null, 
		        null, null, enterpriseEdition, useTime , enterpriseInfoId, userId);
	}
	
	/**
	 * 企业状态修改
	* @author 骆峰
	* @date 2016年6月28日 下午6:42:47
	* @param start 0正常 1 禁用
	* @param enterpriseInfoId 企业标识
	* @param request request
	* @param storeAccount storeAccount
	* @return BaseDto
	 */
    @RequestMapping(value =  Url.Enterprise.SHOW_START, method = RequestMethod.POST)
    @ResponseBody
	public BaseDto disableAndStart(HttpServletRequest request, Integer start, Integer enterpriseInfoId, String storeAccount){
        return enterpriseService.disableAndStart(start, enterpriseInfoId, storeAccount);
	}

}
  
