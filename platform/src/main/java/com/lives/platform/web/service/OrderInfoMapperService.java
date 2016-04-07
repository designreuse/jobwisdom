package com.lives.platform.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.OrderInfo;
import com.lives.platform.web.mapper.OrderInfoMapper;

@Service
public class OrderInfoMapperService {
	
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	
	public Integer insert(OrderInfo orderInfo){
		Integer i = orderInfoMapper.insert(orderInfo);
		return i;
	}

}
