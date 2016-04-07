package com.lives.platform.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.Stores;
import com.lives.platform.web.mapper.StoresMapper;

/**
 * 员工信息表
* @author 老王
* @date 2015年6月8日 上午10:17:17 
*
 */
@Service
public class StoresMapperService {
	@Autowired 
	private StoresMapper storesMapper;
	
	public int deleteStores(Integer storesId){
		return storesMapper.deleteByPrimaryKey(storesId);
	}
	
	public int insertStores(Stores stores){
		return storesMapper.insertSelective(stores);
	}
	
	public Stores queryStores(Integer storesId){
		return storesMapper.selectByPrimaryKey(storesId);
	}
	
	public int updateStores(Stores stores){
		return storesMapper.updateByPrimaryKeySelective(stores);
	}
	
	public List<Stores> selectByStoresId(Integer storesId){
		return storesMapper.selectByStoresId(storesId);
	}
	
	/**
	 * 动态查询
	* @author 洪秋霞
	* @date 2015年7月14日 上午10:11:29
	* @param record
	* @return
	 */
	public List<Stores> selectByProperty(Stores record){
		return storesMapper.selectByProperty(record);
	}
}
