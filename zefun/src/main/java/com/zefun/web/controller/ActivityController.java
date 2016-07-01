package com.zefun.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.web.service.ActivityService;

/**
 * 活动
* @author 骆峰
* @date 2016年6月29日 下午2:28:25
 */
@Controller
public class ActivityController extends BaseController {
    
    
    /** 活动 service*/
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
    * @date 2016年6月29日 下午5:02:04
    * @param request
    * @return
     */
//    @RequestMapping(value = Url.HairstyleDesign.TO_HAIRSTYLEDESIGN)
//    public ModelAndView showViweActivity(HttpServletRequest request) {
//        return activityService.showViweActivity(getStoreAccount(request));
//    }
    
    
}
