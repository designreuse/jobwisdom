package com.lives.platform.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.CommodityType;
import com.lives.platform.web.mapper.CommodityTypeMapper;
/**
 * 商品类型表
* @author 洪秋霞
* @date 2015年6月8日 上午10:21:50 
*
 */
@Service
public class CommodityTypeService {

	@Autowired private CommodityTypeMapper commodityTypeMapper;
	
	public void deleteCommodityType (Integer commodityTypeId){
		commodityTypeMapper.deleteByPrimaryKey(commodityTypeId);
	}
	
	public int insertCommodityType(CommodityType commodityType){
		return commodityTypeMapper.insertSelective(commodityType);
	}
	
	public CommodityType queryCommodityType(Integer commodityTypeId){
		return commodityTypeMapper.selectByPrimaryKey(commodityTypeId);
	}
	
	public int updateCommodityType(CommodityType commodityType){
		return commodityTypeMapper.updateByPrimaryKeySelective(commodityType);
	}
}
