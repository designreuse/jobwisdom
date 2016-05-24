package com.zefun.web.mapper;

import com.zefun.web.entity.StoreWechat;

public interface StoreWechatMapper {
    
    int deleteByPrimaryKey(String storeAccount);

    int insert(StoreWechat record);

    int insertSelective(StoreWechat record);

    StoreWechat selectByPrimaryKey(String storeAccount);

    int updateByPrimaryKeySelective(StoreWechat record);

    int updateByPrimaryKey(StoreWechat record);
}