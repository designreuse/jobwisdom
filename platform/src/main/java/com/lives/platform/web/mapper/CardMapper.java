package com.lives.platform.web.mapper;

import com.lives.platform.web.entity.Card;

public interface CardMapper {
	Integer deleteByPrimaryKey(Integer vipCardId);

	Integer insert(Card record);

	Integer insertSelective(Card record);

    Card selectByPrimaryKey(Integer vipCardId);

    Integer updateByPrimaryKeySelective(Card record);

    Integer updateByPrimaryKey(Card record);
}