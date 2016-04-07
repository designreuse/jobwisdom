package com.lives.platform.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.Card;
import com.lives.platform.web.mapper.CardMapper;

@Service
public class CardMapperService {
	
	@Autowired
	private CardMapper cardMapper;
	
	/**添加卡信息*/
	public Integer addCard(Card card){
		Integer i = cardMapper.insert(card);
		return i;
	}
	
	/**查询卡信息*/
	public Card selectVipUser(Integer vipCardId){
		Card card = cardMapper.selectByPrimaryKey(vipCardId);
		return card;
	}
	
	/**修改卡信息*/
	public Integer UpdateVipUser(Card card){
		Integer i = cardMapper.updateByPrimaryKeySelective(card);
		return i;
	}
	
	/**删除卡信息*/
	public Integer deleteVipUser(Integer vipCardId){
		Integer i = cardMapper.deleteByPrimaryKey(vipCardId);
		return i;
	}

}
