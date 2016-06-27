package com.zefun.web.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.MessageUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.ScreeningDto;
import com.zefun.web.entity.CouponInfo;
import com.zefun.web.entity.CouponStoreKey;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.mapper.CouponInfoMapper;
import com.zefun.web.mapper.CouponStoreMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberScreeningMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.mapper.StoreWechatMapper;
import com.zefun.web.mapper.WechatMemberMapper;
import com.zefun.web.mapper.WechatStoreMapper;

import net.sf.json.JSONObject;


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
    
    /** 门店关联表优惠卷 */
    @Autowired
    private MemberScreeningMapper memberScreeningMapper;
    /** 门店关联表优惠卷 */
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    /** 门店关联表优惠卷 */
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /** 门店关联表优惠卷 */
    @Autowired
    private StoreWechatMapper storeWechatMapper;
    
    

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
        return new BaseDto(1, couponInfo);
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
    * @param releaseTime releaseTime
    * @return BaseDto
     */
    public BaseDto deleted(Integer couponId, String couponStopTime, String couponStartTime, String releaseTime) {
        CouponInfo couponInfo = new CouponInfo();
        couponInfo.setCouponId(couponId);
        couponInfo.setCouponStopTime(couponStopTime);
        couponInfo.setCouponStartTime(couponStartTime);
        couponInfo.setReleaseTime(releaseTime);
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
        int updateByPrimaryKey = couponInfoMapper.updateByPrimaryKey(couponInfo);
        
       
        CouponStoreKey couponStore = new CouponStoreKey();
        couponStore.setCouponId(couponInfo.getCouponId());
        couponStoreMapper.deleteByPrimaryKey(couponStore);
        
        String type = couponInfo.getStoreType();
        String[] store = type.split(",");
        for (int i = 0; i < store.length; i++) {
            couponStore.setStoreId(Integer.parseInt(store[i]));
            couponStoreMapper.insert(couponStore);
        }
        
        return new BaseDto(updateByPrimaryKey, couponInfo);
    }

    
    /**
     * 优惠券推送功能
    * @author 高国藩
    * @date 2016年6月27日 下午8:03:40
    * @param level     level 会员等级
    * @param sceening   sceening 会员分组
    * @param couponId   优惠券Id
    * @param storeId    门店标示
    * @param storeAccount  微门店设置性能
    * @return              baseDto
     */
    public BaseDto sendCounponsToMembers(String level, String sceening,
            Integer couponId, Integer storeId, String storeAccount) {
        Set<Integer> sendsIds = new HashSet<>();
        List<ScreeningDto> dtos = memberScreeningMapper.selectByDtos(Arrays.asList(sceening));
        for (int i = 0; i < dtos.size(); i++) {
            List<Integer> memberIds = memberInfoMapper.selectMemberIdsByDtos2(MessageUtil.transBean2Map(dtos.get(i)));
            sendsIds.addAll(memberIds);
        }
        List<String> levels = Arrays.asList(level);
        List<Integer> memberIds = memberInfoMapper.selectMemberIdsByLevelIds(levels);
        sendsIds.addAll(memberIds);
        List<Integer> ls = new ArrayList<>();
        ls.addAll(sendsIds);
        List<String> touser = new ArrayList<>(); //wechatMemberMapper.selectOpenIdsByMemberIdList(ls);
        touser.add("opqSZwHLjhGDjR6wo2fAIVmqlqAM");
        touser.add("opqSZwJIvK-CT0PVZRVwxpLmy6Y8");
        touser.add("opqSZwP0DCpckbvGx1gB-mEVWi7s");
        JSONObject object = new JSONObject();
        object.put("storeAccount", storeAccount);
        object.put("storeName", "中帮我道-小高分店1");
        object.put("couponName", "欢天喜地优惠券");
        object.put("couponStopTime", "2016-09-21");
        object.put("tempId", storeWechatMapper.selectByPrimaryKey(storeAccount).getTmCouponOverdue());
        object.put("touser", touser);
        rabbitTemplate.convertAndSend(App.Queue.SEND_COUPONS, object);
        return null;
    }

}
