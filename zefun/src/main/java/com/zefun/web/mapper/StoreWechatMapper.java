package com.zefun.web.mapper;

import com.zefun.web.entity.StoreWechat;

/**
 * 门店微信设置
* @author 高国藩
* @date 2016年5月25日 上午10:15:56
 */
public interface StoreWechatMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月25日 上午10:16:06
    * @param storeAccount storeAccount
    * @return             int
     */
    int deleteByPrimaryKey(String storeAccount);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月25日 上午10:16:06
    * @param record storeAccount
    * @return             int
     */
    int insert(StoreWechat record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月25日 上午10:16:06
    * @param record storeAccount
    * @return             int
     */
    int insertSelective(StoreWechat record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月25日 上午10:16:06
    * @param storeAccount storeAccount
    * @return             int
     */
    StoreWechat selectByPrimaryKey(String storeAccount);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月25日 上午10:16:06
    * @param record storeAccount
    * @return             int
     */
    int updateByPrimaryKeySelective(StoreWechat record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月25日 上午10:16:06
    * @param record storeAccount
    * @return             int
     */
    int updateByPrimaryKey(StoreWechat record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年5月25日 上午10:16:06
    * @param wechatId storeAccount
    * @return             int
     */
    StoreWechat selectByWechatId(String wechatId);
}