package com.lives.platform.web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.StoresManage;
import com.lives.platform.web.mapper.StoresManageMapper;


/**
 * 区域门店管理表
* @author 王潇
* @date 2015年6月8日 上午10:17:17 
*
 */
@Service
public class StoresManageMapperService {
	@Autowired private StoresManageMapper storesManageMapper;
	
	public int deleteStoresManage(Integer storesManageID){
		return storesManageMapper.deleteByPrimaryKey(storesManageID);
	}
	
	public int insertStoresManage(StoresManage storesManage){
		return storesManageMapper.insertSelective(storesManage);
	}
	
	public StoresManage queryStoresManage(Integer storesManageId){
		return storesManageMapper.selectByPrimaryKey(storesManageId);
	}
	
	public int updateStoresManage(StoresManage storesManage){
		return storesManageMapper.updateByPrimaryKeySelective(storesManage);
	}
	
}
