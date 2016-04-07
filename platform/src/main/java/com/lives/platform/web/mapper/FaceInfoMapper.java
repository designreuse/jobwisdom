package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.FaceInfo;

public interface FaceInfoMapper {
    int deleteByPrimaryKey(String faceId);

    int insert(FaceInfo record);

    FaceInfo selectByPrimaryKey(String faceId);

    int updateByPrimaryKey(FaceInfo record);
    
    int selectCountByUserId(Integer userId);
    
    int selectCountBySetId(String setId);
}