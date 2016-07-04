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
import com.zefun.web.entity.CommissionScheme;
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
    
    
    
    /**
     *  业绩提成分配保存
    * @author 骆峰
    * @date 2016年7月4日 上午11:40:12
    * @param request request
    * @param commissionScheme commissionScheme
    * @return BaseDto
     */
    @RequestMapping(value = Url.CommissionScheme.VIEW_SAVE_COMMISSION_SCHEME, method = RequestMethod.POST)
    @ResponseBody
    public  BaseDto saveCommissionScheme(HttpServletRequest request, @RequestBody CommissionScheme commissionScheme){
        int storeId = getStoreId(request);
        return commissionSchemeService.saveCommissionScheme(storeId, commissionScheme);
    }
    
}
