package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.entity.ComboGoods;

/**
 * 疗程商品关联mapper
* @author 高国藩
* @date 2015年11月10日 下午3:54:51
 */
public interface ComboGoodsMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2015年11月10日 下午3:53:06
    * @param key 实体
    * @return 影响行数
     */
    int deleteByPrimaryKey(ComboGoods key);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年11月10日 下午3:53:06
    * @param record 实体
    * @return 影响行数
     */
    int insert(ComboGoods record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年11月10日 下午3:53:06
    * @param record 实体
    * @return 影响行数
     */
    int insertSelective(ComboGoods record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年11月10日 下午3:53:06
    * @param key 实体
    * @return 影响行数
     */
    List<ComboGoods> selectByPrimaryKey(ComboGoods key);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年11月10日 下午3:53:06
    * @param record 实体
    * @return 影响行数
     */
    int updateByPrimaryKeySelective(ComboGoods record);
    /**
     * 删除
    * @author 高国藩
    * @date 2015年11月10日 下午3:53:06
    * @param record 实体
    * @return 影响行数
     */
    int updateByPrimaryKey(ComboGoods record);
    
    /**
     * 
    * @author 老王
    * @date 2016年8月22日 下午2:56:39 
    * @param comboId 套餐标识
    * @return Map<String, String>
     */
    Map<String, String> selectGoodsNumByComboId(Integer comboId);
}