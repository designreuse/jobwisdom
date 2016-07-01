package com.zefun.web.mapper;

import com.zefun.web.entity.DeptObjective;

public interface DeptObjectiveMapper {
    int deleteByPrimaryKey(Integer objectiveId);

    int insert(DeptObjective record);

    int insertSelective(DeptObjective record);

    DeptObjective selectByPrimaryKey(Integer objectiveId);

    int updateByPrimaryKeySelective(DeptObjective record);

    int updateByPrimaryKey(DeptObjective record);
}