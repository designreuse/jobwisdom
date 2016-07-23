package com.zefun.web.mapper;

import com.zefun.web.entity.ComboMemberLevel;
/**
 * 疗程会员等级关联表
* @author 洪秋霞
* @date 2015年9月15日 上午10:39:43
 */
public interface ComboMemberLevelMapper {
    /**
     * 删除疗程会员等级关联
    * @author 洪秋霞
    * @date 2015年9月15日 上午10:39:57
    * @param comboMemberLevelId 疗程会员等级关联id
    * @return int
     */
    int deleteByPrimaryKey(Integer comboMemberLevelId);

    /**
     * 添加疗程会员等级关联
    * @author 洪秋霞
    * @date 2015年9月15日 上午10:40:12
    * @param comboMemberLevel 疗程会员等级关联
    * @return int
     */
    int insertSelective(ComboMemberLevel comboMemberLevel);

    /**
     * 根据主键id查询疗程会员等级关联
    * @author 洪秋霞
    * @date 2015年9月15日 上午10:40:20
    * @param comboMemberLevel 疗程会员等级关联
    * @return ComboMemberLevel
     */
    ComboMemberLevel selectByPrimaryKey(ComboMemberLevel comboMemberLevel);

    /**
     * 更新疗程会员等级关联
    * @author 洪秋霞
    * @date 2015年9月15日 上午10:40:29
    * @param comboMemberLevel 疗程会员等级关联
    * @return int
     */
    int updateByPrimaryKeySelective(ComboMemberLevel comboMemberLevel);
    
    /**
     * 根据疗程id查询
    * @author 洪秋霞
    * @date 2015年9月15日 上午11:44:02
    * @param comboId 疗程id
    * @return ComboMemberLevel
     */
    ComboMemberLevel selectByComboId(Integer comboId);
    

}