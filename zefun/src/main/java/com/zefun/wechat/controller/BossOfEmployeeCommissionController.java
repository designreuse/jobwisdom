package com.zefun.wechat.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.web.controller.BaseController;
import com.zefun.wechat.dto.EmployeeCommissionOfBossDto;
import com.zefun.wechat.service.BossOfEmployeeCommissionService;

/**
 * Boss端员工业绩控制器
* @author DavidLiang
* @date 2016年1月22日 下午6:05:10
 */
@Controller
public class BossOfEmployeeCommissionController extends BaseController {
	
	/** Boss端员工业绩服务 */
	@Autowired
	private BossOfEmployeeCommissionService bossOfEmployeeCommissionService;
	
	/**测试门店*/
//	private int ownerStoreId = 1005;
	
	/**
	 * boss查看员工业绩首页
	* @author DavidLiang
	* @date 2016年1月22日 下午7:22:15
	* @param request  请求
	* @param response  响应
	* @param storeId  门店id
	* @param businessType  商户类型
	* @return  员工业绩页面详情
	 */
	@RequestMapping(value = Url.Boss.VIEW_HOME_EMPLOYEE_COMMISSION, method = RequestMethod.GET)
	public ModelAndView employeePerformanceHome(HttpServletRequest request, HttpServletResponse response, 
			  @PathVariable(value = "storeId") String storeId, @PathVariable(value = "businessType") Integer businessType) {
		String openId = getOpenId(storeId, businessType, request, response);
		if (openId == null) {
			return null;
		}
		int ownerStoreId = getStoreIdByOpenId(openId);
		return bossOfEmployeeCommissionService.findEmployeePerformanceHome(
				new ModelAndView(View.BossPage.VIEW_EMPLOYEE_COMMISSION), ownerStoreId);
	}
	
	/**
	 * 查询员工业绩
	* @author DavidLiang
	* @date 2016年1月23日 下午2:57:14
	* @param request  请求
	* @param response  响应
	* @param deptId  岗位id
	* @param dateType  时间类型(year, month, day)
	* @param sortType  排序类型
	* @return  员工业绩集合
	 */
	@RequestMapping(value = Url.Boss.ACTION_FIND_EMPLOYEE_COMMISSION_BY_CONDITION, method = RequestMethod.POST)
	@ResponseBody
	public List<EmployeeCommissionOfBossDto> findEmployeeCommission(HttpServletRequest request, HttpServletResponse response, 
			  Integer deptId, String dateType, String sortType) {
		String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int ownerStoreId = getStoreIdByOpenId(openId);
		return bossOfEmployeeCommissionService.findEmployeeCommission(ownerStoreId, deptId, dateType, sortType);
	}
	
	/**
	 * boss查看员工业绩详情首页
	* @author DavidLiang
	* @date 2016年1月26日 下午1:58:24
	* @param request  请求
	* @param response  响应
	* @param employeeId  员工id
	* @param time  日期
	* @return  员工业绩详情模型和视图
	 * @throws ParseException 
	 */
	@RequestMapping(value = Url.Boss.VIEW_HOME_EMPLOYEE_COMMISSION_DETAIL, method = RequestMethod.GET)
	public ModelAndView findEmployeeCommissionDetailHome(HttpServletRequest request, HttpServletResponse response, 
			  Integer employeeId, String time) throws ParseException {
		String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
		int ownerStoreId = getStoreIdByOpenId(openId);
		/**
		 * 处理time
		 * js传过来的参数time是全局日期参数，具体有以下三种情况
		 * year(年), month(月), day(日)
		 */
		switch (time) {
			case "year":
				time = new SimpleDateFormat("yyyy").format(new Date());
				break;
			case "month":
				time = new SimpleDateFormat("yyyy-MM").format(new Date());
				break;
			case "day":
				time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				break;
			default:
				break;
		}
		
		ModelAndView mav = new ModelAndView(View.BossPage.VIEW_EMPLOYEE_COMMISSION_DETAIL);
		mav.addObject("employeeInfo", bossOfEmployeeCommissionService.findEmployeeInfoOfCommissionDetail(employeeId));
		mav.addObject("employeeCommissionDetailOfBossDto", bossOfEmployeeCommissionService.
				  findEmployeeCommissionDetailHome(ownerStoreId, employeeId, time));
		return mav;
	}

}
