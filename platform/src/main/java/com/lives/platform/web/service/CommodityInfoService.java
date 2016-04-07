package com.lives.platform.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.CommodityInfo;
import com.lives.platform.web.mapper.CommodityInfoMapper;

/**
 * 商品信息表
* @author 洪秋霞
* @date 2015年6月8日 上午10:21:45 
*
 */
@Service
public class CommodityInfoService {
	
	@Autowired private CommodityInfoMapper commodityInfoMapper;
	
	public void deleteCommodityInfo (Integer commodityId){
		commodityInfoMapper.deleteByPrimaryKey(commodityId);
	}
	
	public int insertCommodityInfo(CommodityInfo commodityInfo){
		return commodityInfoMapper.insertSelective(commodityInfo);
	}
	
	public CommodityInfo queryCommodityInfo(Integer commodityId){
		return commodityInfoMapper.selectByPrimaryKey(commodityId);
	}
	
	public int updateCommodityInfo(CommodityInfo commodityInfo){
		return commodityInfoMapper.updateByPrimaryKeySelective(commodityInfo);
	}
}
