package com.zefun.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App.Session;
import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;
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
    @RequestMapping(value = Url.Statistical.STORE_ORDER_HOME)
    public ModelAndView showOrderDetail(HttpServletRequest request){
        Object storeId = request.getSession().getAttribute(Session.STORE_ID);
        String storeAccount = getStoreAccount(request);
        return orderInfoService.showOrderDetail(storeAccount, storeId);
    }
    /**
     *  门店出售
    * @author 骆峰
    * @date 2016年8月10日 下午2:40:19
    * @param time time
    * @param storeId storeId
    * @param timeType timeType
    * @return BaseDto
     */
    @RequestMapping(value = Url.Statistical.STORE_ORDER_CHECK)
    @ResponseBody
    public BaseDto selectCheck(String time, Integer storeId, String timeType){
        return orderInfoService.selectCheck(time, storeId, timeType);
    }
    
 
    /**
     *  销售PK
    * @author 骆峰
    * @date 2016年8月10日 下午5:26:54
    * @param storeId storeId
    * @param goodsId1  商品
    * @param goodsId2  商品
    * @param categoryId1  大项
    * @param categoryId2  大项
    * @param timeType  年月
    * @param time  年
    * @return BaseDto
     */
    @RequestMapping(value = Url.Statistical.STORE_CATEGORY_CHECK)
    @ResponseBody
    public BaseDto selectCategory(Integer storeId, Integer goodsId1, Integer goodsId2, Integer categoryId1,
            Integer categoryId2, String timeType, String time){
        return orderInfoService.selectCategory(storeId, goodsId1, goodsId2, categoryId1, categoryId2, timeType, time);
    }
    
    
    
}
