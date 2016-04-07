package com.lives.platform.web.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.Coupons;
import com.lives.platform.web.entity.CouponsInfo;
import com.lives.platform.web.entity.SankerCoupons;
import com.lives.platform.web.mapper.CouponsInfoMapper;
import com.lives.platform.web.mapper.CouponsMapper;
import com.lives.platform.web.mapper.SankerCouponsMapper;


/**
 * 优惠券service
* @author 高国藩
* @date 2015年6月8日 下午2:53:13 
*
 */
@Service
public class CouponsService {
	@Autowired
	private CouponsMapper couponsMapper;
	@Autowired
	private CouponsInfoMapper couponsInfoMapper;
	@Autowired
	private SankerCouponsMapper sankerCouponsMapper;
	/**
	 * 优惠券类型 table insert
	* @author 中文姓名
	* @date 2015年6月8日 下午2:59:05
	 */
	public int initCouponsType(Coupons coupons){
		return couponsMapper.insert(coupons);
	}
	/**
	 * 散客优惠券 table insert
	* @author 中文姓名
	* @date 2015年6月8日 下午2:59:05
	 */
	public int initSankerCoupons(SankerCoupons coupons){
		return sankerCouponsMapper.insert(coupons);
	}
	public List<Coupons> serch(){
		return couponsMapper.selectAll();
	}
	public int sendtoVip(CouponsInfo couponsInfo) {
		return couponsInfoMapper.insert(couponsInfo);
	}
	public List<SankerCoupons> getSankerCoupons(){
		return sankerCouponsMapper.selectAll();
	}
	public Coupons getCoupons(Integer couponsId){
		return couponsMapper.selectByPrimaryKey(couponsId);
	}
}
