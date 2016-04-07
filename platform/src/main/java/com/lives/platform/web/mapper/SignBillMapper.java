package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.SignBill;

public interface SignBillMapper {
    int deleteByPrimaryKey(Integer signBillId);

    int insert(SignBill record);

    int insertSelective(SignBill record);

    SignBill selectByPrimaryKey(Integer signBillId);

    int updateByPrimaryKeySelective(SignBill record);

    int updateByPrimaryKey(SignBill record);
}