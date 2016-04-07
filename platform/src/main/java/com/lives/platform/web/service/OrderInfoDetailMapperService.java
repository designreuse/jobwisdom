package com.lives.platform.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.OrderInfoDetail;
import com.lives.platform.web.mapper.OrderInfoDetailMapper;

@Service
public class OrderInfoDetailMapperService {
	
	@Autowired
	private OrderInfoDetailMapper orderInfoDetailMapper;
	
	public Integer insert(OrderInfoDetail orderInfoDetail){
		Integer i = orderInfoDetailMapper.insert(orderInfoDetail);
		return i;
	}

}
