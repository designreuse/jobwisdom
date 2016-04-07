package com.lives.platform.web.mapper;

import java.util.List;

import com.lives.platform.web.entity.Position;

public interface PositionMapper {
    int deleteByPrimaryKey(Integer positionId);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer positionId);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);
    
    List<Position> selectByStoresIdList(Integer storesId);
    
    List<Position> selectPositionByPosition(Position record);
}