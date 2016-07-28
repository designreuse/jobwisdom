package com.zefun.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.ActivityInfoDto;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.ActivityInfo;
import com.zefun.web.entity.ActivityStore;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.PositionInfo;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.mapper.ActivityInfoMapper;
import com.zefun.web.mapper.ActivityStoreMapper;
import com.zefun.web.mapper.PositioninfoMapper;
import com.zefun.web.mapper.StoreInfoMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 活动
* @author 骆峰
* @date 2016年6月29日 下午2:39:50
 */
@Service
@Transactional
public class ActivityService {

    
    
    /** 门店*/
    @Autowired
    private StoreInfoMapper storeInfoMapper;
    
    /**
     * 岗位信息
     */
    @Autowired
    private PositioninfoMapper positioninfoMapper;
    
    /**
     * 活动类别
     */
    @Autowired
    private ActivityInfoMapper activityInfoMapper;
    
    /**
     * 门店活动信息
     */
    @Autowired
    private ActivityStoreMapper activityStoreMapper;
    
    /**
     * 活动页面折扣显示
    * @author 骆峰
    * @date 2016年6月29日 下午2:37:46
    * @param storeAccount storeAccount 
    * @return  ModelAndView
     */
    
    /**
     * 活动类别显示页面（企业）
    * @author 骆峰
    * @date 2016年6月29日 下午5:03:00
    * @param storeAccount storeAccount
    * @return ModelAndView
     */
    public ModelAndView showViweActivity(String storeAccount) {
        ModelAndView view = new ModelAndView(View.Activity.TO_ACTIVITYSIGN);
        List<StoreInfo> storeInfos = storeInfoMapper.selectByStoreAccount(storeAccount);
        List<PositionInfo> positionInfos = positioninfoMapper.queryAllByStoreId(storeInfos.get(0).getStoreId());
        Page<ActivityInfoDto> page = new Page<>();
        page.setPageNo(1);
        page.setPageSize(5);
        Map<String, Object> params = new HashMap<>();
        params.put("storeId", storeInfos.get(0).getStoreId());
        page.setParams(params);
        List<ActivityInfoDto> activityInfo = activityInfoMapper.selectByStore(page);
        
        for (int i = 0; i < activityInfo.size(); i++) {
            if (activityInfo.get(i).getActivityPositionOne().length()>3) {
                activityInfo.get(i).setActivityPositionOne(activityInfo.get(i).getActivityPositionOne().substring(0, 3));
            }
            if (activityInfo.get(i).getActivityPositionTwo().length()>3) {
                activityInfo.get(i).setActivityPositionOne(activityInfo.get(i).getActivityPositionOne().substring(0, 3));
            }
            if (activityInfo.get(i).getActivityPositionThree().length()>3) {
                activityInfo.get(i).setActivityPositionOne(activityInfo.get(i).getActivityPositionOne().substring(0, 3));
            }
        }
        page.setResults(activityInfo);
        view.addObject("page", page);
        view.addObject("storeId", storeInfos.get(0).getStoreId());
        view.addObject("storeInfos", storeInfos);
        view.addObject("positionInfos", positionInfos);
        view.addObject("positionInfo", JSONArray.fromObject(positionInfos));
       
        return view;
    }

    /**
     *  活动类别保存
    * @author 骆峰
    * @date 2016年7月6日 下午1:51:09
    * @param storeAccount storeAccount
    * @param activityInfo activityInfo
    * @return  BaseDto
     */
    public BaseDto saveActivity(String storeAccount,
            ActivityInfo activityInfo) {
        activityInfo.setStoreAccount(storeAccount);
        activityInfo.setIsDeleted(0);
        activityInfo.setCreateTime(DateUtil.getCurTime());
        activityInfoMapper.insert(activityInfo);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     * 查询活动类别
    * @author 骆峰
    * @date 2016年7月6日 下午3:55:33
    * @param storeId storeId
    * @param pageNo pageNo
    * @return BaseDto
     */
    
    public BaseDto showViweActivityByStore(Integer storeId, Integer pageNo) {
        
        Page<ActivityInfoDto> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(5);
        Map<String, Object> params = new HashMap<>();
        params.put("storeId", storeId);
        page.setParams(params);
        List<ActivityInfoDto> activityInfo = activityInfoMapper.selectByStore(page);
        List<PositionInfo> positionInfos = positioninfoMapper.queryAllByStoreId(storeId);
   
        for (int i = 0; i < activityInfo.size(); i++) {
            if (activityInfo.get(i).getActivityPositionOne().length()>3) {
                activityInfo.get(i).setActivityPositionOne(activityInfo.get(i).getActivityPositionOne().substring(0, 3));
            }
            if (activityInfo.get(i).getActivityPositionTwo().length()>3) {
                activityInfo.get(i).setActivityPositionOne(activityInfo.get(i).getActivityPositionOne().substring(0, 3));
            }
            if (activityInfo.get(i).getActivityPositionThree().length()>3) {
                activityInfo.get(i).setActivityPositionOne(activityInfo.get(i).getActivityPositionOne().substring(0, 3));
            }
        }
        page.setResults(activityInfo);
        Map<String, JSONArray> map = new HashMap<>();
        map.put("activityInfo", JSONArray.fromObject(activityInfo));
        map.put("positionInfos", JSONArray.fromObject(positionInfos));
        map.put("page", JSONArray.fromObject(page));
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
    }

    
    /**
     * 活动类别删除
    * @author 骆峰
    * @date 2016年7月6日 下午4:35:26
    * @param activityId activityId
    * @return BaseDto
     */
    public BaseDto deletedActivity(Integer activityId) {
        activityInfoMapper.updateIsDeleted(activityId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     *  查询单个活动类别
    * @author 骆峰
    * @date 2016年7月6日 下午4:59:06
    * @param activityId activityId
    * @return BaseDto
     */
    public BaseDto selectActivity(Integer activityId) {
        ActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(activityId);
        return  new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, JSONObject.fromObject(activityInfo));
    }

    /**
     * 修改活动类别
    * @author 骆峰
    * @date 2016年7月7日 下午12:40:24
    * @param activityInfo activityInfo
    * @return BaseDto
     */
    public BaseDto updateActivity(ActivityInfo activityInfo) {
        activityInfo.setIsDeleted(0);
        activityInfo.setUpdateTime(DateUtil.getCurTime());
        activityInfoMapper.updateByPrimaryKeySelective(activityInfo);
        return  new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     * 门店活动显示
    * @author 骆峰
    * @date 2016年7月7日 下午6:48:39
    * @param storeAccount storeAccount
    * @return ModelAndView
     */
    public ModelAndView showStoreActivity(String storeAccount) {
        ModelAndView view = new ModelAndView(View.Activity.TO_ACTIVITYSTORE);
        
        Page<ActivityStore> page = new Page<>();
        page.setPageNo(1);
        page.setPageSize(13);
        Map<String, Object> params = new HashMap<>();
        params.put("storeAccount", storeAccount);
        
        page.setParams(params);
        
        List<ActivityStore> ativityStore = activityStoreMapper.selectByStoreAcount(page);
//        view.addObject("ativityStore", ativityStore);
  
        List<StoreInfo> storeInfos = storeInfoMapper.selectByStoreAccount(storeAccount);
        view.addObject("storeInfos", storeInfos);
        page.setResults(ativityStore);
        view.addObject("page", page);
        return view;
    }

    /**
     * 新增保存
    * @author 骆峰
    * @date 2016年7月8日 上午10:53:50
    * @param activityStore activityStore
    * @param storeAccount storeAccount
    * @return BaseDto
     */
    public BaseDto saveStoreActivity(ActivityStore activityStore, String storeAccount) {
        activityStore.setIsDeleted(0);
        activityStore.setStoreAccount(storeAccount);
        activityStore.setCreateTime(DateUtil.getCurTime());
        activityStoreMapper.insertSelective(activityStore);
        return  new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, activityStore);
    }

    /**
     * 删除
    * @author 骆峰
    * @date 2016年7月8日 上午11:33:02
    * @param activityStoreId activityStoreId
    * @return BaseDto
     */
    public BaseDto deleteStoreActivity(Integer activityStoreId) {
        ActivityStore record = new ActivityStore();
        record.setIsDeleted(1);
        record.setActivityStoreId(activityStoreId);
        activityStoreMapper.updateByPrimaryKeySelective(record);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     *  门店活动分页
    * @author 骆峰
    * @date 2016年7月8日 下午3:00:40
    * @param storeAccount storeAccount
    * @param pageNo pageNo
    * @return BaseDto
     */
    public BaseDto showStoreActivityPage(String storeAccount, Integer pageNo) {
      
        Page<ActivityStore> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(13);
        Map<String, Object> params = new HashMap<>();
        params.put("storeAccount", storeAccount);
        
        page.setParams(params);
        
        List<ActivityStore> ativityStore = activityStoreMapper.selectByStoreAcount(page);
  
        page.setResults(ativityStore);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }
    
    

}
