package com.zefun.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.StoreManageRule;
import com.zefun.web.mapper.StoreManageRuleMapper;

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
    
    /**
     * 
    * @author 骆峰
    * @date 2016年8月4日 下午1:54:30
    * @param storeId storeId
    * @return ModelAndView
     */
    public ModelAndView homeView(int storeId){
        ModelAndView mav = new ModelAndView(View.StoreManageRule.RULE);
        List<StoreManageRule> ruleListByStoreId = storeManageRuleMapper.selectRuleListByStoreId(storeId);
        mav.addObject("ruleListByStoreId", ruleListByStoreId);
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
    * @param storeId storeId
    * @return BaseDto
     */
    public BaseDto saveOrUpdate(StoreManageRule storeManageRule, Integer storeId) {
        storeManageRule.setLastOperatorTime(DateUtil.getCurDate());
        storeManageRule.setStoreId(storeId);
        if (storeManageRule.getRuleId() != null) {
            storeManageRuleMapper.updateByPrimaryKey(storeManageRule);
        }
        else {
            
            storeManageRuleMapper.insertSelective(storeManageRule);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, storeManageRule);
    }
}
