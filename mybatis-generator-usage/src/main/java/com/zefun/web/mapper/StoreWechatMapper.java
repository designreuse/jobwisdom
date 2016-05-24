package com.zefun.web.mapper;

import com.zefun.web.entity.StoreWechat;

public interface StoreWechatMapper {
    int deleteByPrimaryKey(Integer storeId);

    int insert(StoreWechat record);

    int insertSelective(StoreWechat record);

    StoreWechat selectByPrimaryKey(Integer storeId);

    int updateByPrimaryKeySelective(StoreWechat record);

    int updateByPrimaryKey(StoreWechat record);
}