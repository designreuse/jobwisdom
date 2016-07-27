package com.zefun.web.mapper;

import com.zefun.web.entity.SettingRule;

/**
 * 方案规则
* @author 老王
* @date 2016年7月27日 下午3:14:43
 */
public interface SettingRuleMapper {
	/**
	 * 删除
	* @author 老王
	* @date 2016年7月27日 下午3:12:55 
	* @param settingRuleId 方案规则标识
	* @return int
	 */
    int deleteByPrimaryKey(Integer settingRuleId);

    /**
     * 
    * @author 老王
    * @date 2016年7月27日 下午3:13:16 
    * @param record record
    * @return  int
     */
    int insertSelective(SettingRule record);

    /**
     * 
    * @author 老王
    * @date 2016年7月27日 下午3:14:20 
    * @param settingRuleId settingRuleId
    * @return SettingRule
     */
    SettingRule selectByPrimaryKey(Integer settingRuleId);

    /**
     * 
    * @author 老王
    * @date 2016年7月27日 下午3:14:23 
    * @param record record
    * @return int
     */
    int updateByPrimaryKeySelective(SettingRule record);

}