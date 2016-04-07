package com.lives.platform.web.mapper;

import java.util.List;

import com.lives.platform.web.entity.StoresManageRelation;

public interface StoresManageRelationMapper {
    int deleteByPrimaryKey(Integer storesManageRelationId);

    int insert(StoresManageRelation record);

    int insertSelective(StoresManageRelation record);

    StoresManageRelation selectByPrimaryKey(Integer storesManageRelationId);

    int updateByPrimaryKeySelective(StoresManageRelation record);

    int updateByPrimaryKey(StoresManageRelation record);
    
    int insertStoresManageRelationList(List<StoresManageRelation> voList);
    
    int insertStoresManageRelationCopy(StoresManageRelation storesManageRelation);
    
    List<StoresManageRelation> selectByStoresAccountId(Integer itemid1);
}