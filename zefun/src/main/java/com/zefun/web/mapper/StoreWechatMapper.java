package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.StoreWechat;

/**
 * 微信门店数据关联
* @author 高国藩
* @date 2015年10月10日 上午10:09:23
 */
public interface StoreWechatMapper {

    /**
     * 新增
    * @author 高国藩
    * @date 2015年10月10日 上午10:13:37
    * @param record 实体
    * @return 影响行数
     */
    int insert(StoreWechat record);

    /**
     * 查询
    * @author 高国藩
    * @date 2015年10月10日 上午10:14:20
    * @param storeId 主键
    * @return 返回实体
     */
    StoreWechat selectByPrimaryKey(Integer storeId);

    /**
     * 更新
    * @author 高国藩
    * @date 2015年10月10日 上午10:15:36
    * @param record 实体
    * @return 影响行数
     */
    int updateByPrimaryKey(StoreWechat record);

    /**
     * 根据微信的唯一id进行查询
    * @author 高国藩
    * @date 2015年10月10日 上午10:26:41
    * @param toUserName wechatID
    * @return 返回实体
     */
    StoreWechat selectByWechatId(String toUserName);
    
    /**
     * 查询所有门店的微信设置信息
    * @author 张进军
    * @date Mar 10, 2016 2:34:18 PM
    * @return   所有门店的微信设置信息
     */
    List<StoreWechat> selectAll();
}