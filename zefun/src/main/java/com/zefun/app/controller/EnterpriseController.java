
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
import com.zefun.common.consts.View;
import com.zefun.web.dto.BaseDto;

/**
 * 
* @author 老王
* @date 2016年5月21日 下午7:07:12
 */
@Controller
public class EnterpriseController {
	
    /**
     * 配置员工出勤
     */
	@Autowired
	private EnterpriseService enterpriseService;
	
	/**
	 * 
	* @author 老王
	* @date 2016年5月21日 下午7:18:18 
	* @param request 返回
	* @param response 请求
	* @return ModelAndView
	 */
	@RequestMapping(value =  Url.Enterprise.VIEW_SHOW_ENTERPRISE, method = RequestMethod.GET)
    public ModelAndView showEnterprise(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView(View.Enterprise.ADD_ENTERPRISE);
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
	* @return BaseDto
	 */
	@RequestMapping(value =  Url.Enterprise.ADD_ENTERPRISE, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto addEnterprise (HttpServletRequest request, String enterpriseName, String enterpriseLinkphone, 
			  String enterpriseLinkname, String storeAccount) {
		return enterpriseService.addEnterprise(enterpriseName, enterpriseLinkphone, enterpriseLinkname, storeAccount);
	}
}
  
