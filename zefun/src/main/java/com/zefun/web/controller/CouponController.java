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
import com.zefun.web.entity.CouponInfo;
import com.zefun.web.service.CouponService;

/**
 * 优惠券请求类
* @author 高国藩
* @date 2015年9月14日 下午2:10:07
 */
@Controller
public class CouponController extends BaseController{
    
    /**
     * 
     */
    @Autowired
    private CouponService couponService;
    
    
    
 
   
   /**
    * 优惠卷展示页面
   * @author 骆峰
   * @date 2016年6月21日 下午6:18:54
   * @param request request
   * @return ModelAndView
    */
    @RequestMapping(value = Url.Coupon.VIEW_COUPONS, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showMinBargain(HttpServletRequest request) {
       
        return couponService.viewCouponsByPage(request);
    }

    /**
     *  优惠卷新增
    * @author 骆峰
    * @date 2016年6月22日 下午2:59:11
    * @param request request
    * @param couponInfo couponInfo
    * @return BaseDto
     */
    @ResponseBody
    @RequestMapping(value = Url.Coupon.ADD_COUPONS, method = RequestMethod.POST)
    public BaseDto viweAddCoupon(HttpServletRequest request, @RequestBody CouponInfo couponInfo) {
        couponInfo.setStoreAccount(getStoreAccount(request));
        return couponService.viweAddCoupon(couponInfo);
    }
    /**
     *分页查询
    * @author 骆峰
    * @date 2016年6月22日 下午8:42:32
    * @param request request
    * @param pageNo pageNo
    * @return BaseDto
     */
    @ResponseBody
    @RequestMapping(value = Url.Coupon.VIEW_COUPONS_BY_PAGE, method = RequestMethod.POST)
    public BaseDto viweAddCoupon(HttpServletRequest request, Integer pageNo) {
        return couponService.viweByPage(request, pageNo);
    }
    
    /**
     * 优惠卷上架
    * @author 骆峰
    * @date 2016年6月23日 下午3:13:07
    * @param couponId couponId
    * @param isType isType
    * @return BaseDto
     */
    @ResponseBody
    @RequestMapping(value = Url.Coupon.SEND_COUPONS, method = RequestMethod.POST)
    public BaseDto sendCoupons(Integer couponId, Integer isType){
        return couponService.sendCoupons(couponId, isType);
        
    }
    
    /**
     * 删除
    * @author 骆峰
    * @date 2016年6月23日 下午5:25:00
    * @param couponId couponId
    * @param couponStopTime couponStopTime
    * @param couponStartTime couponStartTime
    * @return  BaseDto
     */
    @ResponseBody
    @RequestMapping(value = Url.Coupon.DELETE_COUPONS, method = RequestMethod.POST)
    public BaseDto deleted(Integer couponId, String couponStopTime, String couponStartTime){
        return couponService.deleted(couponId, couponStopTime, couponStartTime);
        
    }
    /**
     * 修改优惠卷
    * @author 骆峰
    * @date 2016年6月23日 下午8:32:43
    * @param request request
    * @param couponInfo couponInfo
    * @return BaseDto
     */
    @ResponseBody
    @RequestMapping(value = Url.Coupon.COUPONS_UPDATE, method = RequestMethod.POST)
    public BaseDto saveUpdate(HttpServletRequest request, @RequestBody CouponInfo couponInfo){
        couponInfo.setStoreAccount(getStoreAccount(request));
        return couponService.saveUpdate(couponInfo);
    }
}
