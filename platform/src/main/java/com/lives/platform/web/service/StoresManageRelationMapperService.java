package com.lives.platform.web.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.StoresManageRelation;
import com.lives.platform.web.mapper.StoresManageRelationMapper;


/**
 * 区域门店管理关系表
* @author 王潇
* @date 2015年6月8日 上午10:17:17 
*
 */
@Service
public class StoresManageRelationMapperService {
	@Autowired private StoresManageRelationMapper storesManageRelationMapper;
	
	public int deleteStoresManageRelation(Integer storesManageRelationId){
		return storesManageRelationMapper.deleteByPrimaryKey(storesManageRelationId);
	}
	
	public int insertStoresManageRelation(StoresManageRelation storesManageRelation){
		return storesManageRelationMapper.insertSelective(storesManageRelation);
	}
	
	public StoresManageRelation queryStoresManageRelation(Integer storesManageRelationId){
		return storesManageRelationMapper.selectByPrimaryKey(storesManageRelationId);
	}
	
	public int updateStoresManageRelation(StoresManageRelation storesManageRelation){
		return storesManageRelationMapper.updateByPrimaryKeySelective(storesManageRelation);
	}
	
	public int insertStoresManageRelationList(List<StoresManageRelation> voList){
		return storesManageRelationMapper.insertStoresManageRelationList(voList);
	}
	
	public int insertStoresManageRelationCopy(StoresManageRelation storesManageRelation){
		return storesManageRelationMapper.insertStoresManageRelationCopy(storesManageRelation);
	}
	
	/**
	 * 根据登录账户id查找门店列表
	* @author 洪秋霞
	* @date 2015年6月10日 下午4:49:13
	* @param itemid1 门店账户id
	* @return
	 */
	public List<StoresManageRelation> queryStoresManageRelationIdList(Integer itemid1){
		return storesManageRelationMapper.selectByStoresAccountId(itemid1);
	}
	
}
