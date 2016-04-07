package com.lives.manage.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.manage.common.consts.App;
import com.lives.manage.common.util.StringUtils;
import com.lives.manage.web.dto.MenuDto;
import com.lives.manage.web.dto.UserInfoDto;
import com.lives.manage.web.entity.Privilege;
import com.lives.manage.web.entity.User;
import com.lives.manage.web.mapper.PrivilegeMapper;
import com.lives.manage.web.mapper.UserMapper;
import com.lives.manage.web.mapper.UserPrivilegeMapper;
@Service
public class LoginService {
	@Autowired
	private UserPrivilegeMapper userPrivilegeMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PrivilegeMapper privilegeMapper;
	/**登录接口*/
	public String login(User user, HttpSession session,HttpServletRequest request) {
		Map<String,Object> usermap = userMapper.loginUser(user);
		//判断当登录帐号是否有信息
		if(usermap!=null&&usermap.size()>0){
			
			String salt=usermap.get("salt").toString();
			//判断当密码不对时候返回
			if (!StringUtils.MD5(user.getPassWord() + salt).equals(usermap.get("passWord"))) {
				return "redirect:login.jsp?param=1";
			}
			int userId=Integer.parseInt(usermap.get("userId").toString());
			int roleId=Integer.parseInt(usermap.get("roleId").toString());
			user.setRoleId(roleId);
			user.setUserId(userId);
			UserInfoDto userInfoDto = new UserInfoDto(userId, user.getUserName(), roleId);
			session.setAttribute("userInfoDto", userInfoDto);
		
			/**
			 * 处理菜单权限
			 */
			List<MenuDto> menuList = null;
			if(user.getUserName().equals(App.SUPERNAME)){
				menuList = privilegeMapper.selectMenuListForSuperMan();
			}else{
			menuList = userPrivilegeMapper.selectMenuListByUserId(userId);
			}
			for (MenuDto menuDto : menuList) {
				JSONObject json = JSONObject.fromObject(menuDto.getUrl());
				menuDto.setUrl(json.getString("url"));
				menuDto.setClassname(json.getString("classname"));
				for (MenuDto m : menuDto.getSubMenus()) {
					json = JSONObject.fromObject(m.getUrl());
					m.setUrl(json.getString("url"));
					m.setClassname(json.getString("classname"));
				}
			}
			session.setAttribute(App.SESSION_MENU_LIST_KEY, menuList);
			
			//获取该用户得所有功能权限
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("type", 1);
			List<String> functionList = userPrivilegeMapper.selectPrivilegeByUserIdAndType(map);
			session.setAttribute(App.SESSION_FCUNTION_LIST_KEY, functionList);
			
			//获取该用户得所有元素权限
			map.put("type", 2);
			List<String> elementList = userPrivilegeMapper.selectPrivilegeByUserIdAndType(map);
			
			
			List<Privilege> ls = userPrivilegeMapper.getPageClass(userId);
			List<JSONObject> al = new ArrayList<JSONObject>();
			if (ls != null && ls.size() > 0) {
				for (int i = 0; i < ls.size(); i++) {
					JSONObject json = JSONObject.fromObject(ls.get(i).getPrivilegeValue());
					al.add(json);
				}
			} 
			System.out.println(JSONArray.fromObject(al).toString());
			
			/**
			 * 封装处理页面元素的权限
			 * 一个jsp页面对应一组权限列表
			 */
			session.setAttribute("ys", al);
			Map<String, List<String>> elementMap = new HashMap<String, List<String>>();
			for (String s : elementList) {
				JSONObject json = JSONObject.fromObject(s);
				String key = json.getString("jsp");
				String classname = json.getString("classname");
				List<String> list = new ArrayList<String>();
				if(elementMap.containsKey(key)){
					list = elementMap.get(key);
				}
				list.add(classname);
				elementMap.put(key, list);
			}
			for (String k : elementMap.keySet()) {
				session.setAttribute(App.SESSION_ELEMENT_LIST_KEY_PREFIX + k, elementMap.get(k));
			}
			session.setAttribute("user", user);
			if(roleId==1){
			    return "redirect:homepage/view/query"; 
			}else if(roleId==3){
			    return "redirect:custclue/view/query"; 
			}else if(roleId==2){
			    return "redirect:cust/view/query"; 
			}
			return "redirect:homepage/view/query"; 
		}else{
			return "redirect:login.jsp?param=1"; 
		}
	}
}
