package com.zefun.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App.Session;
import com.zefun.web.service.OrderInfoService;


/**
 *  统计商品销售报表
* @author 骆峰
* @date 2016年8月9日 下午2:02:24
 */
@Controller
public class OrderInfoController extends BaseController {
    
    
    /** */
    @Autowired
    private OrderInfoService orderInfoService;

    
    
    
    
    /**
     *  商品销售报表
    * @author 骆峰
    * @date 2016年8月9日 下午2:43:21
    * @param request request
    * @return ModelAndView
     */
    public ModelAndView showOrderDetail(HttpServletRequest request){
        Object storeId = request.getSession().getAttribute(Session.STORE_ID);
        String storeAccount = getStoreAccount(request);
        return orderInfoService.showOrderDetail(storeAccount, storeId);
        
    }
    
    
}
