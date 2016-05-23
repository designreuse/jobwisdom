package com.zefun.web.mapper;

import com.zefun.web.entity.StoreShop;

/**
 * 商城设置项
* @author 高国藩
* @date 2016年5月21日 下午6:49:56
 */
public interface StoreShopMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月21日 下午6:50:03
    * @param sId sId
    * @return    sId
     */
    int deleteByPrimaryKey(Integer sId);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月21日 下午6:50:03
    * @param record sId
    * @return    sId
     */
    int insert(StoreShop record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月21日 下午6:50:03
    * @param record sId
    * @return    sId
     */
    int insertSelective(StoreShop record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月21日 下午6:50:03
    * @param sId sId
    * @return    sId
     */
    StoreShop selectByPrimaryKey(Integer sId);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月21日 下午6:50:03
    * @param record sId
    * @return    sId
     */
    int updateByPrimaryKeySelective(StoreShop record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月21日 下午6:50:03
    * @param record sId
    * @return    sId
     */
    int updateByPrimaryKey(StoreShop record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月21日 下午6:50:03
    * @param storeShop sId
    * @return    sId
     */
    StoreShop selectByProties(StoreShop storeShop);
}