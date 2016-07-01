package com.zefun.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.web.dto.StoreInfoDto;
import com.zefun.web.entity.ActivityAccount;
import com.zefun.web.entity.CouponInfo;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.mapper.ActivityAccountMapper;
import com.zefun.web.mapper.FavourableAccountMapper;
import com.zefun.web.mapper.StoreInfoMapper;

/**
 * 活动
* @author 骆峰
* @date 2016年6月29日 下午2:39:50
 */
@Service
@Transactional
public class ActivityService {

    /** 活动操作*/
    @Autowired
    private ActivityAccountMapper activityAccountMapper;
    
    /** 门店操作类*/
    @Autowired
    private StoreInfoMapper storeInfoMapper;
    
    /** 活动折扣具体操作*/
    @Autowired
    private FavourableAccountMapper favourableAccountMapper;
    /**
     * 活动页面折扣显示
    * @author 骆峰
    * @date 2016年6月29日 下午2:37:46
    * @param storeAccount storeAccount 
    * @return  ModelAndView
     */
    public ModelAndView showViweFavourable(String storeAccount) {
        ModelAndView view = new ModelAndView();

        
        Page<StoreInfoDto> page = new Page<>();
        page.setPageNo(1);
        page.setPageSize(10);
        Map<String, Object> params = new HashMap<>();
        params.put("storeAccount", storeAccount);
        page.setParams(params);
        
        List<StoreInfoDto> favourable = storeInfoMapper.selectByfavourable(page);
        page.setResults(favourable);
        view.addObject("page", page);
        return view;
    }
    
    /**
     * 活动显示页面（企业）
    * @author 骆峰
    * @date 2016年6月29日 下午5:03:00
    * @param storeAccount storeAccount
    * @return ModelAndView
     */
    public ModelAndView showViweActivity(String storeAccount) {
        ModelAndView view = new ModelAndView();
        Page<ActivityAccount> page = new Page<>();
        page.setPageNo(1);
        page.setPageSize(10);
        Map<String, Object> params = new HashMap<>();
        params.put("storeAccount", storeAccount);
        List<ActivityAccount> selectByActivity = activityAccountMapper.selectByActivity(page);
        page.setResults(selectByActivity);
        view.addObject("page", page);
        return view;
    }

}
