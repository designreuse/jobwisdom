package com.lives.manage.web.mapper;

import java.util.List;
import java.util.Map;

import com.lives.manage.web.entity.SystemAffiche;

public interface SystemAfficheMapper {
    int deleteByPrimaryKey(Integer afficheId);

    int insert(Map<String,Object> map);
    
    int addAfficheRole(Map<String,Object> map);
    
    int insertSelective(SystemAffiche record);

    SystemAffiche selectByPrimaryKey(Integer afficheId);

    int updateByPrimaryKeySelective(Map<String,Object> map);
    
    int deleteAfficheRole(Map<String,Object> map);
    

    int updateByPrimaryKey(SystemAffiche record);
    
    List<SystemAffiche> selectTable(Map<String, Object> map);
    
    int selectDetalis(Map<String, Object> map);
    
    int updatestate(Map<String ,Object> map);
    
    Map<String, Object> selectAfficheInfo(Map<String, Object> map);
    
    
}