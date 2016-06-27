package com.zefun.web.mapper;

import com.zefun.web.entity.StoreWechat;

/**
 * 微信门店数据关联
* @author 高国藩
* @date 2015年10月10日 上午10:09:23
 */
public interface StoreWechatMapper {
    /**
     * 查询该门店是否注册公众号
    * @author 高国藩
    * @date 2015年10月13日 下午3:34:23
    * @param storeId 门店
    * @return 返回实体
     */
    StoreWechat selectByStoreId(String storeAccount);
}