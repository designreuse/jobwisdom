package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.entity.StoreManageRule;

/**
 *  
* @author 骆峰
* @date 2016年8月4日 下午1:49:34
 */
public interface StoreManageRuleMapper {
    
    /**
     * 
    * @author 骆峰
    * @date 2016年8月4日 下午1:49:52
    * @param ruleId ruleId
    * @return int
     */
    int deleteByPrimaryKey(Integer ruleId);
    
    /**
     * 
    * @author 骆峰
    * @date 2016年8月4日 下午1:57:21
    * @param ruleId ruleId
    * @return int
     */
    int initStoreRuleByStoreId(Integer ruleId);
    
    /**
     * 
    * @author 骆峰
    * @date 2016年8月4日 下午1:53:14
    * @param storeId storeId
    * @return List
     */
    List<StoreManageRule> selectRuleListByStoreId(Integer  storeId);
    
    /**
     *  名称
    * @author 骆峰
    * @date 2016年8月13日 下午2:29:34
    * @param map map
    * @return  List<StoreManageRule>
     */
    List<StoreManageRule> selectRuleListByRuleName(Map<String, Object>  map);
    
    /**
     * 
    * @author 骆峰
    * @date 2016年8月4日 下午8:25:09
    * @param map map
    * @return List
     */
    List<StoreManageRule> selectRuleListByAccountStoreId(Map<String, Object>  map);
    /**
     * 
    * @author 骆峰
    * @date 2016年8月4日 下午1:49:55
    * @param record record
    * @return int
     */
    int insert(StoreManageRule record);

    /**
     * 
    * @author 骆峰
    * @date 2016年8月4日 下午1:50:00
    * @param record record
    * @return int
     */
    int insertSelective(StoreManageRule record);

    /**
     * 
    * @author 骆峰
    * @date 2016年8月4日 下午1:50:04
    * @param ruleId ruleId
    * @return StoreManageRule
     */
    StoreManageRule selectByPrimaryKey(Integer ruleId);

    /**
     * 
    * @author 骆峰
    * @date 2016年8月4日 下午1:50:09
    * @param record record
    * @return int
     */
    
    int updateByPrimaryKeySelective(StoreManageRule record);

    /**
     * 
    * @author 骆峰
    * @date 2016年8月4日 下午1:50:13
    * @param record record
    * @return int
     */
    int updateByPrimaryKey(StoreManageRule record);
}