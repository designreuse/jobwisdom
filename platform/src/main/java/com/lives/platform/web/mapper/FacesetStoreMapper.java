package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.FacesetStoreKey;

public interface FacesetStoreMapper {
    int deleteByPrimaryKey(FacesetStoreKey key);

    int insert(FacesetStoreKey record);
    
    String selectSetIdByStoreId(int storeId);
}