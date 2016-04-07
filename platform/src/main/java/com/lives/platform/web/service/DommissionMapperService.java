package com.lives.platform.web.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.platform.web.dto.DommissionEmployeeDto;
import com.lives.platform.web.entity.Dommission;
import com.lives.platform.web.mapper.DommissionMapper;

/**
 * 提成表
* @author 洪秋霞
* @date 2015年6月8日 上午10:17:17 
*
 */
@Service
public class DommissionMapperService {
	@Autowired private DommissionMapper dommissionMapper;
	
	public int deleteDommission(Integer projectDommissionId){
		return dommissionMapper.deleteByPrimaryKey(projectDommissionId);
	}
	
	
	public int insertDommission(Dommission dommission){
		return dommissionMapper.insertSelective(dommission);
	}
	
	public Dommission queryDommission(Integer projectDommissionId){
		return dommissionMapper.selectByPrimaryKey(projectDommissionId);
	}
	
	public int updateDommission(Dommission dommission){
		return dommissionMapper.updateByPrimaryKeySelective(dommission);
	}
	
	public List<Dommission> selectByProperty(Dommission dommission){
		return dommissionMapper.selectByProperty(dommission);
	}
	
	public int insertPositionList(List<Dommission> dommissionList){
		return dommissionMapper.insertPositionList(dommissionList);
	}
	
	public List<DommissionEmployeeDto> selectByProjectInfoId(Integer projectInfoId){
		return dommissionMapper.selectByProjectInfoId(projectInfoId);
	}
}
