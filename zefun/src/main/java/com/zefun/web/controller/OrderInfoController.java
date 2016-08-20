package com.zefun.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    * @param response  response
    * @return ModelAndView
     * @throws IOException  IOException
     * @throws ServletException  ServletException
     */
    @RequestMapping(value = Url.Statistical.STORE_ORDER_HOME)
    public ModelAndView showOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Object storeId = request.getSession().getAttribute(Session.STORE_ID);
        String storeAccount = getStoreAccount(request);
        return orderInfoService.showOrderDetail(storeAccount, storeId, request, response);
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
    
    
    
    /**
     *  销量汇总
    * @author 骆峰
    * @date 2016年8月11日 下午3:20:36
    * @param storeId storeId
    * @param time1 time1
    * @param time2 time2
    * @return BaseDto
     */
    
    @RequestMapping(value = Url.Statistical.STORE_GOODS_CHECK)
    @ResponseBody
    public BaseDto selectByGoods(Integer storeId, String time1, String time2){
        return orderInfoService.selectByGoods(storeId, time1, time2);
    }
    
    
    
    /**
     *  项目销售页面展示
    * @author 骆峰
    * @date 2016年8月17日 上午10:36:05
    * @param request request
    * @param response response
    * @return ModelAndView
     * @throws IOException 
     * @throws ServletException 
     */
    @RequestMapping(value = Url.Statistical.PROJECT_GOODS_CHECK)
    public ModelAndView showProjectDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Object storeId = request.getSession().getAttribute(Session.STORE_ID);
        String storeAccount = getStoreAccount(request);
        return orderInfoService.showProjectDetail(storeAccount, storeId, request, response);
    }
    
    
    
    /**
     *  劳动业绩
    * @author 骆峰
    * @date 2016年8月17日 下午2:20:02
    * @param time time
    * @param storeId storeId
    * @param timeType timeType
    * @return BaseDto
     */
    @RequestMapping(value = Url.Statistical.STORE_PROJECT_SELECT)
    @ResponseBody
    public BaseDto selectProjectCheck(String time, Integer storeId, String timeType){
        return orderInfoService.selectProjectCheck(time, storeId, timeType);
    }
    
    /**
     *   项目PK
    * @author 骆峰
    * @date 2016年8月17日 下午3:12:24
    * @param storeId  门店
    * @param projectId1 项目1
    * @param projectId2 项目2
    * @param categoryId1 大项1
    * @param categoryId2 大项2
    * @param timeType  年月
    * @param time   年
    * @return  BaseDto
     */
    @RequestMapping(value = Url.Statistical.STORE_CATEGORY_SELECT)
    @ResponseBody
    public BaseDto selectProjectCategory(Integer storeId, Integer projectId1, Integer projectId2, Integer categoryId1,
            Integer categoryId2, String timeType, String time){
        return orderInfoService.selectProjectCategory(storeId, projectId1, projectId2, categoryId1, categoryId2, timeType, time);
    }
    


    /**
     *  项目销量汇总
    * @author 骆峰
    * @date 2016年8月17日 下午5:09:51
    * @param storeId 门店
    * @param time1  时间
    * @param time2  时间
    * @return  BaseDto
     */
    @RequestMapping(value = Url.Statistical.STORE_PROJECT_CATEGORY_CHECK)
    @ResponseBody  
    public BaseDto selectByProjectCategory(Integer storeId, String time1, String time2){
        return orderInfoService.selectByProjectCategory(storeId, time1, time2);
    }
    
    
    /**
     *  疗程报表
    * @author 骆峰
    * @date 2016年8月19日 下午6:47:07
    * @param request  request
    * @param response  response
    * @return ModelAndView
     * @throws IOException 
     * @throws ServletException 
 
     */
    @RequestMapping(value = Url.Statistical.STORE_COMBOINFO_CHECK)
    public ModelAndView showComboInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Object storeId = request.getSession().getAttribute(Session.STORE_ID);
        String storeAccount = getStoreAccount(request); 
        return orderInfoService.showComboInfo(storeAccount, storeId, request, response);
    }
    
}
