package com.ucs.security.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.ucs.security.face.SecurityTestInterface;
import com.ucs.security.pojo.URLResource;
import com.ucs.security.pojo.Users;

@Repository("SecurityTestDao")
public class SecurityTestDao implements SecurityTestInterface{
	Logger log=Logger.getLogger(SecurityTestDao.class);
	
	@Resource
	private JdbcTemplate jdbcTamplate;
	public boolean getinput() {
		log.info("getinput");
		return true;
	}
	
	public boolean geoutput() {
		log.info("geoutput");
		return true;
	}

	public boolean addreport_admin() {
		log.info("addreport_admin");
		return true;
	}

	public boolean deletereport_admin() {
		log.info("deletereport_admin");
		return true;
	}


	public Users findbyUsername(String name) {
		final Users users = new Users();  
		jdbcTamplate.query("SELECT * FROM USER WHERE logname = ?",  
		                    new Object[] {name},  
		                    new RowCallbackHandler() {  
								@Override
								public void processRow(java.sql.ResultSet rs)
										throws SQLException {
								 users.setName(rs.getString("logname"));
								 users.setPassword(rs.getString("password"));
								 users.setRole(rs.getString("role_ids"));
								}  
		                    });  
		log.info(users.getName()+"    "+users.getPassword()+"    "+users.getRole());
		return users;
	}

	
		
	@Override
	public void user_login() {
		log.info("拥有ROLE_USER权限的方法访问：user_login");
		
	}

	@Override
	//获取所有资源链接
	public List<URLResource> findResource() {
		
		List<URLResource> uRLResources =Lists.newArrayList();
		Map<String,Integer[]> role_types=new HashMap<String, Integer[]>();
		List<String> role_Names=Lists.newArrayList();
		List list_role=jdbcTamplate.queryForList("select role_type,power_ids from role");
		Iterator it_role = list_role.iterator();
		while(it_role.hasNext()){
			Map role_map=(Map)it_role.next();
			String object = (String)role_map.get("power_ids");
			String type = (String)role_map.get("role_type");
			role_Names.add(type);
			String[] power_ids = object.split(",");
			Integer[] int_pow_ids=new Integer[power_ids.length];
			for(int i=0;i<power_ids.length;i++){
				int_pow_ids[i]=Integer.parseInt(power_ids[i]);
			}
			role_types.put(type, int_pow_ids);
		}
		for(String name:role_Names){
			URLResource resource=new URLResource();
			Integer[] ids=role_types.get(name);//更具角色获取权限id
			List<Integer> all_res_ids=Lists.newArrayList();
			List<String> urls=Lists.newArrayList();
			for(Integer id:ids){//更具权限id获取资源id
				List resource_ids=jdbcTamplate.queryForList("select resource_ids from power where id =?",new Object[]{id});
				Iterator it_resource_ids = resource_ids.iterator();
				while(it_resource_ids.hasNext()){
					Map resource_ids_map=(Map)it_resource_ids.next();
					String[] ids_str=((String)resource_ids_map.get("resource_ids")).split(",");
					for(int i=0;i<ids_str.length;i++){
						all_res_ids.add(Integer.parseInt(ids_str[i]));
					}
				}
			}
			for(Integer id:all_res_ids){
				List resource_urls=jdbcTamplate.queryForList("select resource_url from resource where id=?",new Object[]{id});
				Iterator it_res_urls = resource_urls.iterator();
				while(it_res_urls.hasNext()){
					Map res_url_map=(Map)it_res_urls.next();
					urls.add(((String)res_url_map.get("resource_url")));
				}
			}
			//将对应的权限关系添加到URLRsource
			resource.setRole_Name(name);
			resource.setRole_url(urls);
			uRLResources.add(resource);
		}
		
		Gson gson =new Gson();
		log.info("权限资源对应关系:"+gson.toJson(uRLResources));
		return uRLResources;
	}

}
