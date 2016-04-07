package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.ubox.UboxStoreGoodsDto;
import com.zefun.web.entity.UboxStoreGoods;

/**
 * 友宝门店商品关联操作类
* @author 张进军
* @date Jan 28, 2016 5:53:52 PM
 */
public interface UboxStoreGoodsMapper {
    
    /**
     * 根据门店商品标识查询门店商品信息
    * @author 张进军
    * @date Jan 30, 2016 10:06:44 PM
    * @param storeGoodsId   门店商品标识
    * @return   门店商品信息
     */
    UboxStoreGoodsDto selectGoodsInfoByStoreGoodsId(Integer storeGoodsId);
    
    /**
     * 根据门店标识查询友宝商品列表
    * @author 张进军
    * @date Jan 30, 2016 5:33:10 PM
    * @param storeId    门店标识
    * @return   友宝商品列表
     */
    List<UboxStoreGoodsDto> selectGoodsListByStoreId(int storeId);
    
    /**
     * 根据主键标识删除关联记录
    * @author 张进军
    * @date Jan 28, 2016 5:54:02 PM
    * @param id 关联标识
    * @return   0:失败，1:成功
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增友宝门店商品关联记录
    * @author 张进军
    * @date Jan 28, 2016 5:54:17 PM
    * @param record 商品关联记录
    * @return   0:失败，1:成功
     */
    int insert(UboxStoreGoods record);

    /**
     * 根据主键标识查询关联记录
    * @author 张进军
    * @date Jan 28, 2016 5:54:45 PM
    * @param id 主键标识
    * @return   商品关联记录
     */
    UboxStoreGoods selectByPrimaryKey(Integer id);

    /**
     * 修改商品关联记录
    * @author 张进军
    * @date Jan 28, 2016 5:54:28 PM
    * @param record 商品关联记录
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(UboxStoreGoods record);
    
    
    /**
     * 根据门店标识、友宝商品标识查询商品信息
    * @author 张进军
    * @date Mar 2, 2016 6:23:01 PM
    * @param map    门店标识、友宝商品标识
    * @return       商品信息
     */
    UboxStoreGoodsDto selectGoodsInfoByStoreIdAndUboxId(Map<String, String> map);
}