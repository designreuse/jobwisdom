package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.FaceSearchRecord;

public interface FaceSearchRecordMapper {
    int deleteByPrimaryKey(String faceId);

    int insert(FaceSearchRecord record);

    FaceSearchRecord selectByPrimaryKey(String faceId);

    int updateByPrimaryKey(FaceSearchRecord record);
}