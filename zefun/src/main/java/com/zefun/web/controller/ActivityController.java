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
import com.zefun.web.entity.ActivityStore;
import com.zefun.web.entity.InitializeInFo;
import com.zefun.web.entity.Page;
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
     * 活动类别显示（企业）
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
     * 查询该名店下的活动类别
    * @author 骆峰
    * @date 2016年7月6日 下午3:54:27
    * @param storeId storeId
    * @param pageNo pageNo
    * @return BaseDto
     */
    @RequestMapping(value = Url.Activity.TO_ACTIVITYSIGN, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto showViweActivityByStore(Integer storeId, Integer pageNo) {
        
        return activityService.showViweActivityByStore(storeId, pageNo);
    }
    
    
    /**
     * 活动类别保存
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
     *  活动类别删除
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
     * 查询一个活动类别的信息
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
     * 单个活动类别修改
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
    
    
    /**
     * 门店活动
    * @author 骆峰
    * @date 2016年7月7日 下午6:45:09
    * @param request request
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Activity.SHOW_ACTIVITY, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showStoreActivity(HttpServletRequest request) {
        
        return activityService.showStoreActivity(getStoreAccount(request));
    }
    
    
    
    /**
     * 保存门店活动
    * @author 骆峰
    * @date 2016年7月8日 上午10:46:56
    * @param activityStore activityStore
    * @param request request
    * @return BaseDto
     */
    @RequestMapping(value = Url.Activity.SHOW_ACTIVITYSAVE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto saveStoreActivity(@RequestBody ActivityStore activityStore, HttpServletRequest request){
        return activityService.saveStoreActivity(activityStore, getStoreAccount(request));
        
    }
    
    
    /**
     * 删除
    * @author 骆峰
    * @date 2016年7月8日 上午11:30:41
    * @param activityStoreId activityStoreId
    * @return BaseDto
     */
    @RequestMapping(value = Url.Activity.SHOW_ACTIVITYDELETE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto deleteStoreActivity(Integer activityStoreId){
        return activityService.deleteStoreActivity(activityStoreId);
    }
    
    /**
     * 门店活动
    * @author 骆峰
    * @date 2016年7月7日 下午6:45:09
    * @param request request
    * @param pageNo pageNo
    * @return ModelAndView
     */
    @RequestMapping(value = Url.Activity.SHOW_ACTIVITY, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto showStoreActivityPage(HttpServletRequest request, Integer pageNo) {
        
        return activityService.showStoreActivityPage(getStoreAccount(request), pageNo);
    }
   
}
