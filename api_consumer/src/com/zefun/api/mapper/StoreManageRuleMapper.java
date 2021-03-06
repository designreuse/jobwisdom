package com.zefun.api.mapper;

import java.util.List;

import com.zefun.api.entity.StoreManageRule;


/**
 * 门店管理制度操作类
* @author 小高
* @date Dec 5, 2015 6:19:03 PM
 */
public interface StoreManageRuleMapper {
    
    /**
     * 根据规则标识查询规则信息
    * @author 小高
    * @date Dec 5, 2015 6:19:23 PM
    * @param ruleId 规则标识
    * @return   规则信息
     */
    StoreManageRule selectByPrimaryKey(Integer ruleId);

    
    /**
     * 修改规则信息
    * @author 小高
    * @date Dec 5, 2015 6:20:09 PM
    * @param record 规则信息
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(StoreManageRule record);
    
    
    /**
     * 根据门店查询管理制度列表
    * @author 小高
    * @date Dec 5, 2015 7:03:07 PM
    * @param storeId    门店标识
    * @return   门店查询管理制度列表
     */
    List<StoreManageRule> selectRuleListByStoreId(int storeId);
    
    /**
     * 初始化门店管理制度规则
    * @author 小高
    * @date Dec 9, 2015 12:01:07 PM
    * @param storeId    需要初始化的门店标识
    * @return   0:失败，1:成功
     */
    int initStoreRuleByStoreId(int storeId);
    
    /**
     * 根据店铺id查询迟到规则
     * @param storeId  店铺id
     * @return 该店铺迟到规则
     */
    StoreManageRule selectStoreRuleLateByStoreId(int storeId);
    
    /**
     * 根据店铺id查询旷工规则
     * @param storeId 店铺id
     * @return 该店铺旷工规则
     */
    StoreManageRule selectStoreRuleAbsenteeismByStoreId(int storeId);
}