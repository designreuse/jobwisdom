package com.zefun.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.CouponInfo;
import com.zefun.web.entity.CouponStoreKey;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.mapper.CouponInfoMapper;
import com.zefun.web.mapper.CouponStoreMapper;
import com.zefun.web.mapper.StoreInfoMapper;


/**
 * 优惠卷
* @author 骆峰
* @date 2016年6月21日 下午4:57:52
 */
@Service
@Transactional
public class CouponService {

    /** 门店表 */
    @Autowired
    private StoreInfoMapper storeInfoMapper;

    /** 企业优惠卷 */
    @Autowired
    private CouponInfoMapper couponInfoMapper;
    
    
    /** 门店关联表优惠卷 */
    @Autowired
    private CouponStoreMapper couponStoreMapper;

    /**
     * 优惠卷展示页面
    * @author 骆峰
    * @date 2016年6月21日 下午4:59:21
    * @param request request
    * @return ModelAndView
     */

    public ModelAndView viewCouponsByPage(HttpServletRequest request) {
        Object storeId = request.getSession().getAttribute(App.Session.STORE_ID);
        Object storeAccount = request.getSession().getAttribute(App.Session.STORE_ACCOUNT);
        CouponInfo couponInfo =new CouponInfo();
        CouponStoreKey couponStore= new CouponStoreKey();
        ModelAndView view = null ;
        
        Page<CouponInfo> page = new Page<>();
        page.setPageNo(1);
        page.setPageSize(10);
        Map<String, Object> params = new HashMap<>();
        List<CouponInfo> coupon =null;
      
        
        if (storeId == null) {
            couponInfo.setStoreAccount(String.valueOf(storeAccount));
            view = new ModelAndView(View.Coupon.VIEW_COUPON_LIST);
            params.put("storeAccount", String.valueOf(storeAccount));
            page.setParams(params);
            coupon = couponInfoMapper.selectByCoupon(page);
        } 
        else {
            couponStore.setCouponId(Integer.parseInt(storeId.toString()));
            view = new ModelAndView(View.Coupon.VIEW_COUPON_STORE);
            params.put("StoreId", Integer.parseInt(storeId.toString()));
            page.setParams(params);
            coupon = couponInfoMapper.selectByStore(page);
        }
        page.setResults(coupon);
        List<StoreInfo> storeInfo = storeInfoMapper.selectByStoreAccount(couponInfo.getStoreAccount());
        view.addObject("StoreInfo", storeInfo);
        view.addObject("page", page);

        return view;
    }

    /**
     * 企业优惠卷新增
    * @author 骆峰
    * @date 2016年6月22日 下午2:45:53
    * @param couponInfo couponInfo
    * @return BaseDto
     */
    public BaseDto viweAddCoupon(CouponInfo couponInfo) {
        couponInfo.setIsDelete(0);
        couponInfo.setCouponNum(0);
        couponInfo.setCouponIsUse(0);
        couponInfo.setIsType(1);
        couponInfoMapper.insert(couponInfo);
        
        CouponStoreKey couponStore = new CouponStoreKey();
        couponStore.setCouponId(couponInfo.getCouponId());
        String type = couponInfo.getStoreType();
        String[] store = type.split(",");
        for (int i = 0; i < store.length; i++) {
            couponStore.setStoreId(Integer.parseInt(store[i]));
            couponStoreMapper.insert(couponStore);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, couponInfo);
    }
    /**
     * 企业分页查询
    * @author 骆峰
    * @date 2016年6月22日 下午8:43:10
    * @param request request
    * @param pageNo pageNo
    * @return BaseDto
     */
    public BaseDto viweByPage(HttpServletRequest request, Integer pageNo) {
        Object storeId = request.getSession().getAttribute(App.Session.STORE_ID);
        Object storeAccount = request.getSession().getAttribute(App.Session.STORE_ACCOUNT);
        CouponInfo couponInfo =new CouponInfo();
        
        // 为空进企业号
        Page<CouponInfo> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(10);
        Map<String, Object> params = new HashMap<>();
        
        if (storeId == null) {
            couponInfo.setStoreAccount(String.valueOf(storeAccount));
            params.put("storeAccount", couponInfo.getStoreAccount());
            page.setParams(params);
            List<CouponInfo> coupon = couponInfoMapper.selectByCoupon(page);
            page.setResults(coupon);
        } 
        else {
            couponInfo.setStoreId(Integer.parseInt(storeId.toString()));
            params.put("StoreId", couponInfo.getStoreId());
            page.setParams(params);
            List<CouponInfo> coupon = couponInfoMapper.selectByStore(page);
            page.setResults(coupon);
        }
        return new BaseDto(0, page);
    }

    /**
     * 优惠卷上架
    * @author 骆峰
    * @date 2016年6月23日 下午3:13:29
    * @param couponId couponId
    * @param isType isType
    * @return BaseDto
     */
    public BaseDto sendCoupons(Integer couponId, Integer isType) {
        CouponInfo couponInfo = new CouponInfo();
        couponInfo.setCouponId(couponId);
        couponInfo.setIsType(isType);
        couponInfoMapper.updateBytype(couponInfo);
        return new BaseDto(0, couponId);
    }

    /**
     * 删除
    * @author 骆峰
    * @date 2016年6月23日 下午5:26:08
    * @param couponId couponId
    * @param couponStopTime couponStopTime
    * @param couponStartTime couponStartTime
    * @return BaseDto
     */
    public BaseDto deleted(Integer couponId, String couponStopTime, String couponStartTime) {
        CouponInfo couponInfo = new CouponInfo();
        couponInfo.setCouponId(couponId);
        couponInfo.setCouponStopTime(couponStopTime);
        couponInfo.setCouponStartTime(couponStartTime);
        int t = couponInfoMapper.updateByDelete(couponInfo);
        return new BaseDto(t, couponId);
    }
    /**
     * 修改优惠卷
    * @author 骆峰
    * @date 2016年6月23日 下午8:33:08
    * @param couponInfo couponInfo
    * @return BaseDto
     */
    public BaseDto saveUpdate(CouponInfo couponInfo) {
        couponInfo.setIsDelete(0);
        couponInfo.setCouponNum(0);
        couponInfo.setCouponIsUse(0);
        couponInfo.setIsType(1);
        couponInfoMapper.updateByPrimaryKey(couponInfo);
        
       
        CouponStoreKey couponStore = new CouponStoreKey();
        couponStore.setCouponId(couponInfo.getCouponId());
        couponStoreMapper.deleteByPrimaryKey(couponStore);
        
        String type = couponInfo.getStoreType();
        String[] store = type.split(",");
        for (int i = 0; i < store.length; i++) {
            couponStore.setStoreId(Integer.parseInt(store[i]));
            couponStoreMapper.insert(couponStore);
        }
        
        return new BaseDto(0, couponInfo);
    }

}
