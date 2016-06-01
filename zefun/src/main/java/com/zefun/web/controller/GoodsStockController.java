package com.zefun.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.StockFlow;
import com.zefun.web.service.GoodsStockService;

import net.sf.json.JSONObject;

/**
 * 商品进销存
* @author 高国藩
* @date 2016年5月31日 下午3:58:18
 */
@Controller
public class GoodsStockController extends BaseController {
    
    /***/
    @Autowired private GoodsStockService goodsStockService;
    
    /**
     * 进入进销存页面,查看
    * @author 高国藩
    * @date 2016年5月31日 下午4:10:05
    * @param request    request
    * @param response   response
    * @return           ModelAndView
     */
    @RequestMapping(value = Url.GoodsStock.VIEW_STOCK)
    public ModelAndView viewGoodsStock(HttpServletRequest request, HttpServletResponse response) {
        String storeAccount = getStoreAccount(request);
        return goodsStockService.viewGoodsStock(storeAccount, request);
    }
    
    /**
     * 对库存进行设置
    * @author 高国藩
    * @date 2016年5月31日 下午7:53:11
    * @param request     request
    * @param response    response
    * @param jsonObject  jsonObject
    * @return            BaseDto
     */
    @RequestMapping(value = Url.GoodsStock.ACTION_STOCK)
    @ResponseBody
    public BaseDto actionGoodsStock(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jsonObject) {
        String storeAccount = getStoreAccount(request);
        StockFlow stockFlow = (StockFlow) JSONObject.toBean(jsonObject, StockFlow.class);
        return goodsStockService.actionGoodsStock(storeAccount, stockFlow);
    }
    
    /**
     * 根据不同门店进行选择该门店下的进销存流水
    * @author 高国藩
    * @date 2016年5月31日 下午10:39:00
    * @param request       request
    * @param response      response
    * @param storeId       storeId
    * @return              BaseDto
     */
    @RequestMapping(value = Url.GoodsStock.QUERY_STOCK_FLOW_BY_STORE)
    @ResponseBody
    public BaseDto queryGoodsStock(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer storeId) {
        String storeAccount = getStoreAccount(request);
        return goodsStockService.queryGoodsStock(storeAccount, storeId, request);
    }

}
