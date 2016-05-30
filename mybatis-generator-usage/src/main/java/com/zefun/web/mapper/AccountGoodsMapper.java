package com.zefun.web.mapper;

import com.zefun.web.entity.AccountGoods;

public interface AccountGoodsMapper {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(AccountGoods record);

    int insertSelective(AccountGoods record);

    AccountGoods selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(AccountGoods record);

    int updateByPrimaryKey(AccountGoods record);
}