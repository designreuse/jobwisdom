package com.lives.manage.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lives.manage.web.entity.Role;
import com.lives.manage.web.entity.SystemAffiche;
import com.lives.manage.web.mapper.RoleMapper;
import com.lives.manage.web.mapper.SystemAfficheMapper;

@Service
public class SystemAfficheService {
	@Autowired
	private SystemAfficheMapper systemAfficheMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Transactional
	public int addSystemAfficheService(Map<String,Object> map){
		//新增公告
		systemAfficheMapper.insert(map);
		//把角色和公告入关联表
		String afficheRole=map.get("afficheRole").toString();
		if(afficheRole.length()>0){
			String[]afficheRole1= afficheRole.split(",");
			for (int i = 0; i < afficheRole1.length; i++) {
				map.put("afficheRole", afficheRole1[i]);
				systemAfficheMapper.addAfficheRole(map);
			}
		}
		
		return 0;
	}
	
	public int deleteSystemAfficheService(Integer afficheId){
		systemAfficheMapper.deleteByPrimaryKey(afficheId);
		return afficheId;
	}
	@Transactional
	public int updateSystemAfficheService(Map<String,Object> map){
		systemAfficheMapper.updateByPrimaryKeySelective(map);
		//先删除公告和角色的关联
		systemAfficheMapper.deleteAfficheRole(map);
		//把角色和公告入关联表
		String afficheRole=map.get("afficheRole").toString();
		if(!afficheRole.endsWith("null") &&afficheRole.length()>0){
			
			String[]afficheRole1= afficheRole.split(",");
			for (int i = 0; i < afficheRole1.length; i++) {
				map.put("afficheRole", afficheRole1[i]);
				systemAfficheMapper.addAfficheRole(map);
			}
		}
		return 0;
	}
	public int updatestate(Map<String ,Object> map){
		systemAfficheMapper.updatestate(map);
		return 0;
	}
	
	public SystemAffiche selectSystemAfficheService(Integer afficheId){
		return systemAfficheMapper.selectByPrimaryKey(afficheId);
	}
	
	public List<SystemAffiche> selectTable(Map<String, Object> map){
		List<SystemAffiche> list = systemAfficheMapper.selectTable(map);
		return list;
	}
	
	public int selectdetalis(Map<String, Object> map) {
		int count = systemAfficheMapper.selectDetalis(map);
		return count;
	}
	
	public Map<String, Object> selectAfficheInfo(Map<String, Object> map) {
		Map<String, Object> info = systemAfficheMapper.selectAfficheInfo(map);
		return info;
	}
	
	public List<Role> selectAllRole() {
		List<Role> rolelist=roleMapper.selectAllRole();
		return rolelist;
	}
	

}
