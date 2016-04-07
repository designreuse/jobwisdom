package com.lives.manage.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lives.manage.common.consts.App;
import com.lives.manage.common.consts.View;
import com.lives.manage.web.dto.MenuDto;
import com.lives.manage.web.dto.RoleUtils;
import com.lives.manage.web.entity.Privilege;
import com.lives.manage.web.entity.User;
import com.lives.manage.web.mapper.UserPrivilegeMapper;
import com.lives.manage.web.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	private UserPrivilegeMapper userPrivilegeMapper;
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(User user, HttpSession session,HttpServletRequest request) {
		return loginService.login(user,session,request);
	}
	@RequestMapping(value="/loginOut")
	public String loginOut(HttpSession session) {
		session.removeAttribute("userInfoDto");
		return "redirect:login.jsp";
	}

	/**
	 * 菜单html
	 * 
	 * @author 高国藩
	 * @param basePath1
	 * @date 2015年7月1日 下午11:08:02
	 * @return
	 */
	public String getHtml(Integer userId, String basePath1) {
		StringBuffer html = new StringBuffer();
		List<Privilege> ls = userPrivilegeMapper.getFirstMenu(userId);

		for (int i = 0; i < ls.size(); i++) {
			// 对首页看板和开户注册特殊处理
			if (ls.get(i).getPrivilegeName().equals("首页看板")
					|| ls.get(i).getPrivilegeName().equals("开户注册")) {
				JSONObject json = JSONObject.fromObject(ls.get(i)
						.getPrivilegeValue());
				String classname = (String) json.get("classname");
				String url = (String) json.get("url");
				html.append("<li ><a href=\""+basePath1+url+"\"><i class=\"" + classname + "\"></i> <span>"+ ls.get(i).getPrivilegeName() + "</span></a> </li>");
			} else {
				JSONObject json = JSONObject.fromObject(ls.get(i)
						.getPrivilegeValue());
				String classname = (String) json.get("classname");
				String url = (String) json.get("url");
				String name = ls.get(i).getPrivilegeName();
				// 对暂时有的没有连接的拼接 #
				if (url.equals("#")) {
					html.append("<li class=\"submenu\"> <a href=\"#\"><i class=\""
							+ classname
							+ "\"></i> <span>"
							+ name
							+ "</span> <span class=\"icon-sort-down icon-right\"></span></a>");
				} else {
					html.append("<li class=\"submenu\"> <a href=\""
							+ url
							+ "\"><i class=\""
							+ classname
							+ "\"></i> <span>"
							+ name
							+ "</span> <span class=\"icon-sort-down icon-right\"></span></a>");
				}
				Integer privilegeId = ls.get(i).getPrivilegeId();
				// 查看是否有二级菜单权限
				List<Privilege> second = userPrivilegeMapper
						.getSecondMenu(new RoleUtils(userId, privilegeId));
				// 有二级菜单权限
				if (second.size() > 0 && second != null) {
					html.append("<ul>");
					for (int j = 0; j < second.size(); j++) {
						JSONObject json1 = JSONObject.fromObject(second.get(j)
								.getPrivilegeValue());
						if (json1.getString("url").equals("#")) {
							html.append("<li><a href=\"#\">"
									+ second.get(j).getPrivilegeName()
									+ "</a></li>");
						} else {
							html.append("<li><a href=\""+basePath1+json1.getString("url")+"\">"+ second.get(j).getPrivilegeName()+ "</a></li>");
						}
					}
					html.append("</ul>");
					html.append("</li>");
				} else {
					html.append("</li>");
				}
			}
		}
		return html.toString();
	}

	/**
	 * 获取页面元素权限
	 * 
	 * @author 高国藩
	 * @date 2015年7月1日 下午11:15:53
	 * @param userId
	 * @return
	 */
	public List<JSONObject> getPageClass(Integer userId) {
		List<Privilege> ls = userPrivilegeMapper.getPageClass(userId);
		List<JSONObject> al = new ArrayList<JSONObject>();
		if (ls != null && ls.size() > 0) {
			for (int i = 0; i < ls.size(); i++) {
				JSONObject json = JSONObject.fromObject(ls.get(i).getPrivilegeValue());
				al.add(json);
			}
			return al;
		} else {
			return null;
		}
	}
    
	/**
	 * 获取接口权限
	 * 
	 * @author 高国藩
	 * @date 2015年7月1日 下午11:15:53
	 * @param userId
	 * @return
	 */
	public List<String> getGn(Integer userId) {
		List<String> ls = userPrivilegeMapper.getGn(userId);
		return ls;
	}

	/**
	 * 页面元素转换  [{"userInfo.jsp":["delete","delete"]}, {"userInfo.jsp":["delete","delete"]}, {"cust/query.jsp":["remove"]}]
	* @author 高国藩
	* @date 2015年7月2日 下午4:46:03
	* @param al
	* @return
	 */
	public Set<JSONObject> changeList(List<JSONObject> al){
		List<JSONObject> bxl = new ArrayList<JSONObject>();
		List<String> ls = new ArrayList<String>();
		for (int i = 0; i < al.size(); i++) {
			String jsp = al.get(i).getString("jsp");//第一步  拿出了index.jsp
			ls.add(jsp);
		}
		Set<JSONObject> set = new HashSet<JSONObject>();
		for (int i = 0; i < ls.size(); i++) {
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			List<String> xl = new ArrayList<String>();//存放classname
			String jsp = ls.get(i);//分布拿出jsp,进行匹配
			for (int j = 0; j < al.size(); j++) {
				String jsp2 = al.get(j).getString("jsp");
				if(jsp.equals(jsp2)) xl.add(al.get(j).getString("classname"));
			}
			map.put(jsp, xl);
			JSONObject json = JSONObject.fromObject(map);
			set.add(json);
		}
		
		return set;
	}
	/**
	 * 接口权限测试页面
	 * 
	 * @author 高国藩
	 * @date 2015年7月2日 上午10:12:45
	 */
	@RequestMapping("/testtt")
	@ResponseBody
	public void testGn() {
		System.out.println("test.......");
	}
	@RequestMapping("/deleteUser")
	public void deleteUser() {
		System.out.println("deleteUser.......");
	}
}
