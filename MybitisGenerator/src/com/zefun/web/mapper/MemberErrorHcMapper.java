package com.zefun.web.mapper;

import com.zefun.web.entity.MemberErrorHc;

public interface MemberErrorHcMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberErrorHc record);

    int insertSelective(MemberErrorHc record);

    MemberErrorHc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberErrorHc record);

    int updateByPrimaryKey(MemberErrorHc record);
}