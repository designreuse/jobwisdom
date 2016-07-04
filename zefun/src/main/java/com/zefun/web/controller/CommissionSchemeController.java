package com.zefun.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.web.service.CommissionSchemeService;

/**
 * 提成分配方案管理
* @author 老王
* @date Nov 23, 2015 9:25:48 PM 
*/
@Controller
public class CommissionSchemeController extends BaseController {

    /** 提成分配方案管理 */
    @Autowired
    private CommissionSchemeService commissionSchemeService;
    
    
    /**
     * 查看提成分配方案管理
    * @author 老王
    * @date Nov 23, 2015 10:17:35 PM
    * @param request    请求对象
    * @return   预约列表
     */
    @RequestMapping(value = Url.CommissionScheme.VIEW_SHOW_COMMISSION_SCHEME, method = RequestMethod.GET)
    public ModelAndView showCommissionScheme(HttpServletRequest request){
        int storeId = getStoreId(request);
        return commissionSchemeService.showCommissionScheme(storeId);
    }
    
}
