package com.zefun.web.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.ServicePlanInfo;
import com.zefun.web.service.ServicePlansService;

import net.sf.json.JSONObject;

/**
 * 服务计划任务
* @author 高国藩
* @date 2015年9月14日 下午2:10:07
 */
@Controller
public class ServicePlansController extends BaseController{
    
    /** 服务计划*/
    @Autowired
    private ServicePlansService servicePlansService;
  
    /**
     * 进入服务计划页面
    * @author 高国藩
    * @date 2016年6月30日 下午4:48:31
    * @param request request
    * @return        页面
     */
    @RequestMapping(value = Url.ServicePlans.VIEW_SERVICE_PLAN, method = RequestMethod.GET)
    public ModelAndView viewServicePlans(HttpServletRequest request){
        Integer storeId = getStoreId(request);
        return servicePlansService.viewServicePlans(storeId);
    }
    
    /**
     * 新增修改服务计划
    * @author 高国藩
    * @date 2016年6月30日 下午8:06:53
    * @param request  request
    * @param data     data
    * @return         BaseDto
     * @throws ParseException  ParseException
     */
    @RequestMapping(value = Url.ServicePlans.SAVE_SERVICE_PLAN, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto viewServicePlans(HttpServletRequest request, @RequestBody JSONObject data) throws ParseException{
        Integer storeId = getStoreId(request);
        ServicePlanInfo servicePlanInfo = (ServicePlanInfo) JSONObject.toBean(data, ServicePlanInfo.class);
        servicePlanInfo.setIsDeleted(0);
        servicePlanInfo.setStoreId(storeId);
        return servicePlansService.saveServicePlans(servicePlanInfo);
    }
    
    /**
     * 删除服务计划安排
    * @author 高国藩
    * @date 2016年6月30日 下午8:06:53
    * @param request  request
    * @param sId      sId
    * @return         BaseDto
     */
    @RequestMapping(value = Url.ServicePlans.DELETE_SERVICE_PLAN, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto deleteServicePlans(HttpServletRequest request, Integer sId) {
        ServicePlanInfo servicePlanInfo = new ServicePlanInfo();
        servicePlanInfo.setsId(sId);
        return servicePlansService.deleteServicePlans(servicePlanInfo);
    }
    
    /**
     * 服务计划模板页面
    * @author 高国藩
    * @date 2016年6月30日 下午4:48:31
    * @param request request
    * @return        页面
     */
    @RequestMapping(value = Url.ServicePlans.VIEW_SERVICE_TEMOLENT, method = RequestMethod.GET)
    public ModelAndView viewServiceTemp(HttpServletRequest request){
        Integer storeId = getStoreId(request);
        return servicePlansService.viewServiceTemp(storeId);
    }
    
}
