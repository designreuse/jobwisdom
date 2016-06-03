package com.zefun.web.mapper;

import com.zefun.web.entity.GoodsStock;
import com.zefun.web.entity.GoodsStockKey;

/**
 * 商品库存
* @author 高国藩
* @date 2016年6月3日 上午9:55:19
 */
public interface GoodsStockMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2016年6月3日 上午9:55:26
    * @param key  key
    * @return     key
     */
    int deleteByPrimaryKey(GoodsStockKey key);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年6月3日 上午9:55:26
    * @param record  key
    * @return     key
     */
    int insert(GoodsStock record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年6月3日 上午9:55:26
    * @param record  key
    * @return     key
     */
    int insertSelective(GoodsStock record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年6月3日 上午9:55:26
    * @param key  key
    * @return     key
     */
    GoodsStock selectByPrimaryKey(GoodsStockKey key);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年6月3日 上午9:55:26
    * @param record  key
    * @return     key
     */
    int updateByPrimaryKeySelective(GoodsStock record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年6月3日 上午9:55:26
    * @param record  key
    * @return     key
     */
    int updateByPrimaryKey(GoodsStock record);
}