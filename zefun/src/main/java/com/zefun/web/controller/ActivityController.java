package com.zefun.web.controller;


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
import com.zefun.web.entity.ActivityInfo;
import com.zefun.web.service.ActivityService;

/**
 * 活动
* @author 骆峰
* @date 2016年6月29日 下午2:28:25
 */
@Controller
public class ActivityController extends BaseController {
    
    /** 活动*/
    @Autowired
    private ActivityService activityService;
    /**
     *  显示活动折扣页面
    * @author 骆峰
    * @date 2016年6月29日 下午2:30:28
    * @param request request
    * @return ModelAndView
     */
//    @RequestMapping(value = Url.HairstyleDesign.TO_HAIRSTYLEDESIGN)
//    public ModelAndView showViweFavourable(HttpServletRequest request){
//        return activityService.showViweFavourable(getStoreAccount(request));
//    }
//    
    
    /**
     * 活动显示（企业）
    * @author 骆峰
    * @date 2016年7月5日18:17:12
    * @param request request
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Activity.TO_ACTIVITYSIGN, method = RequestMethod.GET)
    public ModelAndView showViweActivity(HttpServletRequest request) {
        
        return activityService.showViweActivity(getStoreAccount(request));
    }
    
    
    /**
     * 查询该名店下的活动
    * @author 骆峰
    * @date 2016年7月6日 下午3:54:27
    * @param storeId storeId
    * @return BaseDto
     */
    @RequestMapping(value = Url.Activity.TO_ACTIVITYSIGN, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto showViweActivityByStore(Integer storeId) {
        
        return activityService.showViweActivityByStore(storeId);
    }
    
    
    /**
     * 活动保存
    * @author 骆峰
    * @date 2016年7月6日 下午1:44:59
    * @param request request
    * @param activityInfo activityInfo
    * @return BaseDto
     */
    @RequestMapping(value = Url.Activity.TO_ACTIVITYSAVE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto saveActivity(HttpServletRequest request, @RequestBody ActivityInfo activityInfo) {
        
        return activityService.saveActivity(getStoreAccount(request), activityInfo);
    }
    
    /**
     *  活动删除
    * @author 骆峰
    * @date 2016年7月6日 下午4:34:45
    * @param activityId activityId
    * @return BaseDto
     */
    @RequestMapping(value = Url.Activity.TO_ACTIVITYDELETE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto deletedActivity(Integer activityId){
        return activityService.deletedActivity(activityId);
        
    }
    
    /**
     * 查询一个活动的信息
    * @author 骆峰
    * @date 2016年7月6日 下午5:44:11
    * @param activityId activityId
    * @return BaseDto
     */
    @RequestMapping(value = Url.Activity.TO_ACTIVITYSELECT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto selectActivity(Integer activityId) {
        
        return activityService.selectActivity(activityId);
    }
    
    /**
     * 单个活动修改
    * @author 骆峰
    * @date 2016年7月7日 上午11:51:21
    * @param request request
    * @param activityInfo activityInfo
    * @return BaseDto
     */
    @RequestMapping(value = Url.Activity.TO_ACTIVITYUPDATE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto updateActivity(HttpServletRequest request, @RequestBody ActivityInfo activityInfo) {
        
        return activityService.updateActivity(activityInfo);
    }
    
}
