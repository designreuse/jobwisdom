package com.zefun.web.controller;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.DayBookQueryDto;
import com.zefun.web.entity.OrderInfo;
import com.zefun.web.service.DayBookService;

/**
 * 流水查詢
* @author luhw
 */
@Controller
public class DayBookController extends BaseController {
    
	/** dayBookService */
	@Autowired
	private DayBookService dayBookService;
	
	/**
	 * 流水查詢
	 * @param request request
	 * @param response response
	 * @return ModelAndView
	 */
    @RequestMapping(value = View.DayBook.VIEW_DAYBOOK_INDEX, method = RequestMethod.GET)
    public ModelAndView dayBookIndex(HttpServletRequest request, HttpServletResponse response){
    	Integer storeId = getStoreId(request);
        return dayBookService.dayBookIndex(storeId);
    }
    
    /**
	 * 流水查詢
	 * @param params 查询条件
	 * @param request request
	 * @param response response
	 * @return List<DayBookDto>
     * @throws ParseException 
	 */
    @ResponseBody
    @RequestMapping(value = View.DayBook.ACTION_DAYBOOK_LIST, method = RequestMethod.POST)
    public BaseDto dayBookView(DayBookQueryDto params, HttpServletRequest request, HttpServletResponse response) throws ParseException{
    	Integer storeId = getStoreId(request);
    	params.setStoreId(storeId);
		
		Map<String, Object> map = dayBookService.querydaybookInfo(storeId, params);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
    }

    /**
     * 通过订单标识查询订单及明细步骤
    * @author 王大爷
    * @date 2015年12月2日 上午10:21:28
    * @param request request
    * @param response response
    * @param orderId 订单标识
    * @return BaseDto
     */
    @ResponseBody
    @RequestMapping(value = Url.DayBook.SELECT_ORDER_BY_UPDATE, method = RequestMethod.POST)
    public BaseDto selectOrderByUpdate(HttpServletRequest request, HttpServletResponse response, Integer orderId) {
    	Integer storeId = getStoreId(request);
        return dayBookService.selectOrderByUpdate(orderId, storeId);
    }
    
    /**
     * 确定修改订单
    * @author 王大爷
    * @date 2015年12月3日 下午7:40:07
    * @param request request
    * @param response response
    * @param orderInfo 订单参数
    * @param commissionArrayStr 修改的提成业绩
    * @return BaseDto
     */
    @ResponseBody
    @RequestMapping(value = Url.DayBook.ORDER_BY_UPDATE, method = RequestMethod.POST)
    public BaseDto orderByUpdate(HttpServletRequest request, HttpServletResponse response, OrderInfo orderInfo, String commissionArrayStr) {
        return dayBookService.orderByUpdate(orderInfo, commissionArrayStr);
    }
    
    /**
     * 作废订单
    * @author 王大爷
    * @date 2015年12月14日 下午3:04:10
    * @param request request
    * @param response response
    * @param orderId 订单标识
    * @return BaseDto
     */
    @ResponseBody
    @RequestMapping(value = Url.DayBook.ELEMENT_DELETE_ORDERID, method = RequestMethod.POST)
    public BaseDto elementDeleteOrderId(HttpServletRequest request, HttpServletResponse response, Integer orderId) {
        Integer storeId = getStoreId(request);
        String storeAccount = getStoreAccount(request);
        return dayBookService.elementDeleteOrderId(orderId, storeId, storeAccount);
    }
    
    /**
     * 修改会员等级
    * @author 王大爷
    * @date 2015年12月14日 下午10:01:11
    * @param request request
    * @param response response
    * @param memberId 会员标识
    * @param levelId 级别标识
    * @return BaseDto
     */
    @ResponseBody
    @RequestMapping(value = Url.DayBook.CHANGE_LEVELID, method = RequestMethod.POST)
    public BaseDto changeLevelId(HttpServletRequest request, HttpServletResponse response, Integer memberId, Integer levelId) {
        return dayBookService.changeLevelId(memberId, levelId, getUserId(request));
    }
}
