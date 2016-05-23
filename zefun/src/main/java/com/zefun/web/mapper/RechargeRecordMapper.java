package com.zefun.web.mapper;

import com.zefun.web.entity.RechargeRecord;

public interface RechargeRecordMapper {
    int deleteByPrimaryKey(Integer rId);

    int insert(RechargeRecord record);

    int insertSelective(RechargeRecord record);

    RechargeRecord selectByPrimaryKey(Integer rId);

    int updateByPrimaryKeySelective(RechargeRecord record);

    int updateByPrimaryKey(RechargeRecord record);

    RechargeRecord selectByTradeNo(RechargeRecord record);
}