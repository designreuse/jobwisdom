package com.lives.platform.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.entity.Position;
import com.lives.platform.web.mapper.PositionMapper;

@Service
public class PositionMapperService {
	
	@Autowired
	private PositionMapper positionMapper;
	
	/**添加职位信息信息*/
	public Integer addPosition(Position position){
		Integer i = positionMapper.insert(position);
		return i;
	}
	
	/**查询职位信息信息*/
	public Position selectPosition(Integer positionId){
		Position position = positionMapper.selectByPrimaryKey(positionId);
		return position;
	}
	
	/**修改职位信息信息*/
	public Integer UpdatePosition(Position position){
		Integer i = positionMapper.updateByPrimaryKeySelective(position);
		return i;
	}
	
	/**删除职位信息信息*/
	public Integer deletePosition(Integer positionId){
		Integer i = positionMapper.deleteByPrimaryKey(positionId);
		return i;
	}
	
	/**根据门店查所有职位信息*/
	public List<Position> selectPositionByStoresIdList(Integer storesId){
		return positionMapper.selectByStoresIdList(storesId);
	}
	
	/**动态查找职位*/
    public List<Position> selectPositionByPosition(Position position){
    	return positionMapper.selectPositionByPosition(position);
    }
	
}
