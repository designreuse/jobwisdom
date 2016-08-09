package com.zefun.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.common.consts.App.Session;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.StoreManageRule;
import com.zefun.web.service.StoreManageRuleService;

/**
 * 
* @author 骆峰
* @date 2016年8月4日 下午2:06:14
 */
@Controller
public class StoreManageRuleController extends BaseController {

    /**门店制度管理对象*/
    @Autowired
    private StoreManageRuleService storeManageRuleService;
    
    
    /**
     * 查看管理制度主页
    * @author 骆峰
    * @date 2016年8月4日 下午2:06:05
    * @param request request
    * @return ModelAndView
     */
    @RequestMapping(value = Url.StoreManageRule.VIEW_HOME)
    public ModelAndView homeView(HttpServletRequest request){
        Object storeId = request.getSession().getAttribute(Session.STORE_ID);
        String storeAccount = getStoreAccount(request);
        return storeManageRuleService.homeView(storeId, storeAccount);
    }
    
    
    /**
     * 查看管理制度门店
    * @author 骆峰
    * @date 2016年8月4日 下午2:06:05
    * @param request request
    * @param storeId storeId
    * @return BaseDto
     */
    @RequestMapping(value = Url.StoreManageRule.VIEW_STORE)
    @ResponseBody
    public BaseDto storeView(HttpServletRequest request, Integer storeId){
        return storeManageRuleService.storeView(storeId);
    }
    
    /**
     *保存或者修改规则信息
    * @author 骆峰
    * @date 2016年8月4日 下午1:38:37
    * @param storeManageRule storeManageRule
    * @param request request
    * @return BaseDto
     */
    @RequestMapping(value = Url.StoreManageRule.SAVE_HOME, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto saveOrUpdate(StoreManageRule storeManageRule, HttpServletRequest request){
        return storeManageRuleService.saveOrUpdate(storeManageRule);
        
    }
    
    /**
     *    删除
    * @author 骆峰
    * @date 2016年8月4日 下午1:38:56
    * @param ruleId ruleId
    * @return BaseDto
     */
    @RequestMapping(value = Url.StoreManageRule.ACTION_DELETE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto deleteAction(Integer ruleId){
        return storeManageRuleService.deleteAction(ruleId);
    }
}
