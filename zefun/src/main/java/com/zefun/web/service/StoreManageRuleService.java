package com.zefun.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.entity.StoreManageRule;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.mapper.StoreManageRuleMapper;

import net.sf.json.JSONArray;

/**
 * 
* @author 骆峰
* @date 2016年8月4日 下午1:54:40
 */
@Service
public class StoreManageRuleService {
	
	
    
    /**门店管理制度操作对象*/
    @Autowired
    private StoreManageRuleMapper storeManageRuleMapper;
    
    /**门店*/
    @Autowired
    private StoreInfoMapper storeInfoMapper;
    
    /**
     * 
    * @author 骆峰
    * @date 2016年8月4日 下午1:54:30
    * @param storeId storeId
    * @param storeAccount storeAccount
    * @return ModelAndView
     */
    public ModelAndView homeView(Object storeId, String storeAccount){
        ModelAndView mav = new ModelAndView(View.StoreManageRule.RULE);
        List<StoreInfo> selectByStoreAccount = storeInfoMapper.selectByStoreAccount(storeAccount);
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        for (StoreInfo storeInfo : selectByStoreAccount) {
//            list.add(storeInfo.getStoreId());
//        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (storeId !=null){
            map.put("storeId", storeId);
            mav.addObject("StoreId", storeId);
        }
        else {
            map.put("storeId", selectByStoreAccount.get(0).getStoreId());

            
            mav.addObject("StoreId", 0);
        }
        mav.addObject("selectByStoreAccount", selectByStoreAccount);
        List<StoreManageRule> ruleListByStoreId = storeManageRuleMapper.selectRuleListByAccountStoreId(map);
        mav.addObject("ruleListByStoreId", ruleListByStoreId);
        mav.addObject("ruleListByStoreIds", JSONArray.fromObject(ruleListByStoreId));
       
        return mav;
    }
    
    
    /**
     * 删除
    * @author 骆峰
    * @date 2016年8月4日 下午2:05:34
    * @param ruleId ruleId
    * @return BaseDto
     */
    public BaseDto deleteAction(Integer ruleId){
        storeManageRuleMapper.deleteByPrimaryKey(ruleId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     *  保存或者修改
    * @author 骆峰
    * @date 2016年8月4日 下午12:07:35
    * @param storeManageRule storeManageRule
    * @return BaseDto
     */
    public BaseDto saveOrUpdate(StoreManageRule storeManageRule) {
        storeManageRule.setLastOperatorTime(DateUtil.getCurDate());
        Map<String, Object>  map = new HashMap<String, Object>();
        map.put("ruleName", storeManageRule.getRuleName());
        map.put("storeId", storeManageRule.getStoreId());
        List<StoreManageRule> selectRuleListByRuleName = storeManageRuleMapper.selectRuleListByRuleName(map);
      
        if (storeManageRule.getRuleId() != null) {
            
            storeManageRuleMapper.updateByPrimaryKey(storeManageRule);
        }
        else {
            if (selectRuleListByRuleName.size()==0){
                storeManageRuleMapper.insertSelective(storeManageRule);
            }
            else{
                return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "已有该名称");
            }
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, storeManageRule);
    }

    /**
     *  门店查询
    * @author 骆峰
    * @date 2016年8月8日 下午4:13:46
    * @param storeId storeId
    * @return BaseDto
     */
    public BaseDto storeView(Integer storeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeId", storeId);
        List<StoreManageRule> ruleListByStoreId = storeManageRuleMapper.selectRuleListByAccountStoreId(map);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, JSONArray.fromObject(ruleListByStoreId));
    }
}
