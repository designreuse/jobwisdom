package com.lives.platform.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.Address;
import com.lives.platform.web.mapper.AddressMapper;

@Service
public class AddressMapperService {
	
	@Autowired
	private AddressMapper addressMapper;
	
	/**添加地址信息*/
	public Integer addAddress(Address address){
		Integer i = addressMapper.insert(address);
		return i;
	}
	
	/**查询地址信息*/
	public Address selectAddress(Integer addressId){
		Address address = addressMapper.selectByPrimaryKey(addressId);
		return address;
	}
	
	/**修改地址信息*/
	public Integer UpdateAddress(Address address){
		Integer i = addressMapper.updateByPrimaryKeySelective(address);
		return i;
	}
	
	/**删除地址信息*/
	public Integer deleteAddress(Integer addressId){
		Integer i = addressMapper.deleteByPrimaryKey(addressId);
		return i;
	}

}
