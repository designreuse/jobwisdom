package com.zefun.web.service;

import java.util.Date;
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
import com.zefun.web.dto.StoreInfoDto;
import com.zefun.web.entity.ActivityInfo;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.EmployeeLevel;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.PositionInfo;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.mapper.ActivityAccountMapper;
import com.zefun.web.mapper.ActivityInfoMapper;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.EmployeeLevelMapper;
import com.zefun.web.mapper.FavourableAccountMapper;
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

    /**
     * 部门
     */
    @Autowired
    private DeptInfoMapper deptInfoMapper;
    
    /**
     * 职位
     */
    @Autowired
    private EmployeeLevelMapper employeeLevelMapper;
    
    /** 门店*/
    @Autowired
    private StoreInfoMapper storeInfoMapper;
    
    /**
     * 岗位信息
     */
    @Autowired
    private PositioninfoMapper positioninfoMapper;
    
    /**
     * 活动
     */
    @Autowired
    private ActivityInfoMapper activityInfoMapper;
    /**
     * 活动页面折扣显示
    * @author 骆峰
    * @date 2016年6月29日 下午2:37:46
    * @param storeAccount storeAccount 
    * @return  ModelAndView
     */
//    public ModelAndView showViweFavourable(String storeAccount) {
//        ModelAndView view = new ModelAndView();
//
//        
//        Page<StoreInfoDto> page = new Page<>();
//        page.setPageNo(1);
//        page.setPageSize(10);
//        Map<String, Object> params = new HashMap<>();
//        params.put("storeAccount", storeAccount);
//        page.setParams(params);
//        
//        List<StoreInfoDto> favourable = storeInfoMapper.selectByfavourable(page);
//        page.setResults(favourable);
//        view.addObject("page", page);
//        return view;
//    }
    
    /**
     * 活动显示页面（企业）
    * @author 骆峰
    * @date 2016年6月29日 下午5:03:00
    * @param storeAccount storeAccount
    * @return ModelAndView
     */
    public ModelAndView showViweActivity(String storeAccount) {
        ModelAndView view = new ModelAndView(View.Activity.TO_ACTIVITYSIGN);
        
        List<StoreInfo> storeInfos = storeInfoMapper.selectByStoreAccount(storeAccount);
        List<PositionInfo> positionInfos = positioninfoMapper.queryAllByStoreId(storeInfos.get(0).getStoreId());
        List<ActivityInfoDto> activityInfo = activityInfoMapper.selectByStore(storeInfos.get(0).getStoreId());
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
        view.addObject("storeInfos", storeInfos);
        view.addObject("storeId", storeInfos.get(0).getStoreId());
        view.addObject("storeInfos", storeInfos);
        view.addObject("positionInfos", positionInfos);
        view.addObject("activityInfo", activityInfo);
        view.addObject("positionInfo", JSONArray.fromObject(positionInfos));
       
        return view;
    }

    /**
     *  企业活动保存
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
     * 查询门店活动
    * @author 骆峰
    * @date 2016年7月6日 下午3:55:33
    * @param storeId storeId
    * @return BaseDto
     */
    
    public BaseDto showViweActivityByStore(Integer storeId) {
        List<ActivityInfoDto> activityInfo = activityInfoMapper.selectByStore(storeId);
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
        Map<String, JSONArray> map = new HashMap<>();
        map.put("activityInfo", JSONArray.fromObject(activityInfo));
        map.put("positionInfos", JSONArray.fromObject(positionInfos));
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
    }

    
    /**
     * 活动删除
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
     *  查询单个活动
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
     * 修改
    * @author 骆峰
    * @date 2016年7月7日 下午12:40:24
    * @param activityInfo activityInfo
    * @return BaseDto
     */
    public BaseDto updateActivity(ActivityInfo activityInfo) {
        activityInfo.setIsDeleted(0);
//        activityInfo.setStoreAccount(storeAccount);
        activityInfo.setUpdateTime(DateUtil.getCurTime());
        activityInfoMapper.updateByPrimaryKeySelective(activityInfo);
        return  new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
//    public ModelAndView accountViewStorePosition(String storeAccount) {
//        ModelAndView view = new ModelAndView(View.Position.VIEW_ACCOUNT);
//        List<StoreInfo> storeInfos = storeInfoMapper.selectByStoreAccount(storeAccount);
//        List<DeptInfo> deptInfos = deptInfoMapper.selectAllDetpByStoreId(storeInfos.get(0).getStoreId());
//        List<PositionInfo> positionInfos = positioninfoMapper.queryAllByStoreId(storeInfos.get(0).getStoreId());
//        
//        EmployeeLevel query = new EmployeeLevel();
//        query.setStoreId(storeInfos.get(0).getStoreId());
//        
//        view.addObject("empLevels", JSONArray.fromObject(employeeLevelMapper.queryEmployeeLevel(query)));
//        view.addObject("storeInfos", storeInfos);
//        view.addObject("deptInfos", deptInfos);
//        view.addObject("positionInfos", positionInfos);
//        return view;
//    }

//    public BaseDto accountViewStorePosition(Integer storeId) {
//        List<DeptInfo> deptInfos = deptInfoMapper.selectAllDetpByStoreId(storeId);
//        List<PositionInfo> positionInfos = positioninfoMapper.queryAllByStoreId(storeId);
//        EmployeeLevel query = new EmployeeLevel();
//        query.setStoreId(storeId);
//        List<EmployeeLevel> employeeLevels = employeeLevelMapper.queryEmployeeLevel(query);
//        Map<String, Object> results = new HashMap<>();
//        results.put("deptInfos", deptInfos);
//        results.put("positionInfos", positionInfos);
//        results.put("empLevels", employeeLevels);
//        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, results);
//    }

}
