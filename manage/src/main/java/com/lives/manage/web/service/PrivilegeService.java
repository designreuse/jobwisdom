package com.lives.manage.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.lives.manage.common.consts.App;
import com.lives.manage.common.consts.Url;
import com.lives.manage.common.util.JsonPropertyFilter;
import com.lives.manage.web.dto.BaseDto;
import com.lives.manage.web.dto.PrivilegeDto;
import com.lives.manage.web.dto.PrivilegeInfoDto;
import com.lives.manage.web.dto.RoleDto;
import com.lives.manage.web.dto.RoleUtils;
import com.lives.manage.web.dto.TreeDto;
import com.lives.manage.web.dto.UserInfoDto;
import com.lives.manage.web.entity.Privilege;
import com.lives.manage.web.entity.Role;
import com.lives.manage.web.entity.RolePrivilege;
import com.lives.manage.web.entity.User;
import com.lives.manage.web.entity.UserPrivilege;
import com.lives.manage.web.entity.UserRole;
import com.lives.manage.web.entity.UserRoleUtils;
import com.lives.manage.web.mapper.PrivilegeMapper;
import com.lives.manage.web.mapper.RoleMapper;
import com.lives.manage.web.mapper.RolePrivilegeMapper;
import com.lives.manage.web.mapper.UserMapper;
import com.lives.manage.web.mapper.UserPrivilegeMapper;
import com.lives.manage.web.mapper.UserRoleMapper;

@Service
public class PrivilegeService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private PrivilegeMapper privilegeMapper;
	@Autowired
	private RolePrivilegeMapper rolePrivilegeMapper;
	@Autowired
	private UserPrivilegeMapper userPrivilegeMapper;
	
	/**
	 * 角色新增
	 * 
	 * @author 高国藩
	 * @date 2015年6月26日 下午8:05:44
	 * @param role
	 * @return
	 */
	public BaseDto addRole(Role role) {
		int count = roleMapper.selectCount();
		role.setSort(count + 1);
		int i = roleMapper.insert(role);
		//当新增一个角色的时候,直接就赋予给admin超级管理员
		UserRole record = new UserRole();
		record.setRoleId(role.getRoleId());
		User user = userMapper.selectSuperMan(App.SUPERNAME);
		record.setUserId(user.getUserId());
		int k = userRoleMapper.insert(record);
		if (i > 0) {
			return new BaseDto(200, "新增角色成功");
		} else {
			return new BaseDto(200, "新增角色失败");
		}
	}

	/**
	 * 获取所有角色
	 * 如果用户要对其他人进行授权的话,那么他一定要登录,登录以后会找寻他所绑定的角色,然后显示角色之后,点击了角色,
	 * 在setPrivateToUser接口中,session中的roleId和userId都会存在值,存在值了,那么久将其已经绑定的权限显示出来
	 * @author 高国藩
	 * @date 2015年6月29日 上午11:29:39
	 * @return
	 */
	public JSONArray getRole(HttpSession session,String status) {
		User user = (User) session.getAttribute("user");
		Integer userId = (Integer) session.getAttribute("userId");
//		Integer userId = (Integer) session.getAttribute("userId");
		/**通过status来判断是否进入了人员授权的页面*/
		if(status!=null){
			if(user==null)return null;
			List<Role> ls = roleMapper.selectRoleByUserId(userId);
			return JSONArray.fromObject(ls);
		}else{
		List<Role> ls = roleMapper.selectAllRole();
		return JSONArray.fromObject(ls);
		}
	}

	/**
	 * 删除角色
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 上午11:59:07
	 * @return
	 */
	public BaseDto deleteRole(Integer roleId) {
		int i = roleMapper.deleteByPrimaryKey(roleId);
		if (i > 0) {
			rolePrivilegeMapper.deleteByRoleId(roleId);
			//删除角色将绑定的用户信息都删除
			userRoleMapper.deleteByRoleId(roleId);
			userPrivilegeMapper.deleteByRoleId(roleId);
			return new BaseDto(200, "删除角色成功");
		} else {
			return new BaseDto(200, "删除角色失败");
		}
	}

	/**
	 * 修改角色
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 上午11:59:07
	 * @return
	 */
	public BaseDto updateRole(Role role) {
		Role newRole = roleMapper.selectByPrimaryKey(role.getRoleId());
		newRole.setRoleName(role.getRoleName());
		int i = roleMapper.updateByPrimaryKey(newRole);
		if (i > 0) {
			return new BaseDto(200, "修改角色成功");
		} else {
			return new BaseDto(200, "修改角色失败");
		}
	}

	/**
	 * 新增权限 菜单的形式 权限的形式 -- 对应字段 1.菜单 1-菜单-首页看板-{"classname":'icon
	 * icon-home',"url":'homepage/view/query'}-0 2.元素
	 * 2-元素-查询页面删除按钮-{"classname":'delete',"jsp":'userInfo.jsp'}-0 3.功能
	 * 3-功能-删除用户接口-/deleteUser-0
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 上午11:59:07
	 * @return
	 */
	public BaseDto addPrivilege(String type,
			Privilege privilege) {
		if (privilege.getRefPrivilegeId() == null) {
			privilege.setRefPrivilegeId(0);
		}
		int i = privilegeMapper.insert(privilege);
		if (i > 0) {
			return new BaseDto(200, "新增权限成功");
		} else {
			return new BaseDto(200, "新增权限失败");
		}
	}

	/** 跳转至权限修改页面 */
	public ModelAndView sendUpdatePrivilege() {
//		ModelAndView model = new ModelAndView("role/updatePrivilege");
		ModelAndView model = new ModelAndView("roleInfo/updatePrivilege");
		return model;
	}

	/** 修改菜单权限 */
	public BaseDto updatePrivilege(Privilege privilege) {
		int k = privilegeMapper.updateByPrimaryKeySelective(privilege);
		if (k > 0)
			return new BaseDto(200, "修改成功");
		return new BaseDto(200, "修改失败");
	}

	/**
	 * 删除权限
	 * 
	 * @author 高国藩
	 * @date 2015年7月3日 下午3:25:24
	 * @return
	 */
	public BaseDto deletePrivilege(String pId) {
		if (pId == null || pId.equals(""))
			return new BaseDto(200, "请选择删除项目");
		int k = 0;
		String id[] = pId.split("_");
		for (int i = 0; i < id.length; i++) {
			k = privilegeMapper.deleteByPrimaryKey(Integer.valueOf(id[i]));
			//删除角色将绑定的用户信息都删除
			userPrivilegeMapper.deleteByPid(Integer.valueOf(id[i]));
			rolePrivilegeMapper.deleteBecasePid(Integer.valueOf(id[i]));
		}
		if (k == 1)
			return new BaseDto(200, "删除成功");
		return new BaseDto(204, "删除失败");
	}

	/**
	 * 获取菜单中的一级菜单
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 上午11:29:39
	 * @return
	 */
	public JSONArray getPrivilege() {
		List<Privilege> ls = privilegeMapper.selectFirstMenu();
		return JSONArray.fromObject(ls);
	}

	/**
	 * 获取所有的权限信息,进行角色绑定
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 下午9:07:37
	 * @return
	 */
	public JSONArray getAllPrivileges() {
		List<Privilege> ls = privilegeMapper.selectFirstMenu();
		return JSONArray.fromObject(ls);
	}

	/**
	 * 获取二级权限信息,进行角色绑定
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 下午9:07:37
	 * @return
	 */
	public JSONArray getAllPrivilegesSecond(Integer id) {
		List<Privilege> ls = privilegeMapper.selectSecondPrivileges(id);
		return JSONArray.fromObject(ls);
	}

	/**
	 * 获取接口权限信息,进行角色绑定
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 下午9:07:37
	 * @return
	 */
	public JSONArray getAllPrivilegesGn() {
		List<Privilege> ls = privilegeMapper.getAllPrivilegesGn();
		return JSONArray.fromObject(ls);
	}

	/**
	 * 获取元素权限信息,进行角色绑定
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 下午9:07:37
	 * @return
	 */
	public JSONArray getAllPrivilegesYs() {
		List<Privilege> ls = privilegeMapper.getAllPrivilegesYs();
		List<Privilege> al = new ArrayList<Privilege>();
		for (int i = 0; i < ls.size(); i++) {
			JSONObject json = JSONObject.fromObject(ls.get(i)
					.getPrivilegeValue());
			Privilege privilege = new Privilege();
			privilege.setPrivilegeId(ls.get(i).getPrivilegeId());
			privilege.setPrivilegeValue(json.get("jsp").toString() + "页面下的"
					+ json.get("classname").toString());
			al.add(privilege);
		}
		return JSONArray.fromObject(al);
	}

	/**
	 * 进行角色绑定
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 下午9:07:37
	 * @return
	 */
	public BaseDto buildRole(Integer pId, HttpSession session) {
		Integer roleId = (Integer) session.getAttribute("roleId");
		if (roleId == null) {
			return new BaseDto(200, "请选择角色");
		} else {
			RolePrivilege record = new RolePrivilege();
			record.setPrivilegeId(pId);
			record.setRoleId(roleId);
			rolePrivilegeMapper.deleteByRoleId(roleId);
			rolePrivilegeMapper.insert(record);
		}
		return null;
	}

	/**
	 * 回显已勾选
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 下午9:07:37
	 * @return
	 */
	public JSONArray getHashPrivileges(HttpSession session) {
		List<Integer> ls = new ArrayList<Integer>();
		Integer roleId = (Integer) session.getAttribute("roleId");
		if (roleId == null) {
			return null;
		} else {
			ls = rolePrivilegeMapper.selectPidByRoleId(roleId);
		}
		for (int i = 0; i < ls.size(); i++) {
		}
		return JSONArray.fromObject(ls);
	}

	/**
	 * 人员授权
	 * 
	 * @author 高国藩
	 * @date 2015年6月26日 下午8:35:02
	 * @param userRole
	 * @return
	 */
	public BaseDto setRoleToUser(UserRole userRole) {
		int i = userRoleMapper.insert(userRole);
		if (i > 0) {
			return new BaseDto(200, "授权成功");
		} else {
			return new BaseDto(200, "授权失败");
		}
	}

	public JSONObject getAllUserRole() {
		//List<UserRoleUtils> ls = userRoleMapper.getAllUserRole();
		return null;
	}

	/**
	 * 跳转至角色新增页面
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 上午10:36:16
	 */
	public String sendRole(Integer roleId, HttpSession session) {
		session.setAttribute("roleId", roleId);
		//return "role/roleInfo";
		return "roleInfo/roleInfo";
	}

	/**
	 * 跳转至权限新增页面
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 上午10:36:16
	 */
	public String sendPrivilege() {
		System.out.println("跳转至角色新增页面");
		return "role/privilegeInfo";
	}

	/**
	 * 跳转至人员权限页面
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 上午10:36:16
	 */
	public String sendPeoplePrivilege() {
		
		System.out.println("跳转至角色新增页面");
		return "role/tree";
	}

	public JSONArray getTree(HttpSession session, String status) {
		/*获取user,如果是登录的话将查询该用户所有的权限*/
		User user = (User) session.getAttribute("user");
		List<Privilege> ls; // 一级菜单
		List<Privilege> al; // 二级菜单
		/**status是否进入了人员授权页面*/
		if (status != null) {
			Integer roleId = (Integer) session.getAttribute("roleId");
			if (roleId == null) return null;
			if (user == null) return null;
			/** 如果status!=null那么说明用户是在授权的页面,那么要根据user所绑定的权限来展示,如果user为空,不显示了*/
			/** 点击的角色的权限树形 */
			RoleUtils roleUtils=new RoleUtils();
			roleUtils.setRoleId(roleId);
			roleUtils.setUserId(user.getUserId());
			//加一个判断,如果是admin的话,获得所拥有的角色下的所有权限
			if(user.getUserName().equals(App.SUPERNAME)){
				ls = userPrivilegeMapper.selectMenuListByRoleId(roleId);
				System.out.println("超级管理员获取一级菜单:"+ls.toString());
			}else{
			ls = rolePrivilegeMapper.selectHasBuildForMenu(roleUtils);
			}
			//ls = rolePrivilegeMapper.selectHasBuildForMenu(roleId);
		} else {
			ls = privilegeMapper.selectFirstMenu();
		}
		StringBuffer sql = new StringBuffer(
				"[{\"id\":0,\"text\":\"菜单权限\",\"children\":");
		sql.append("[");
		// 菜单权限开始
		for (int i = 0; i < ls.size(); i++) {
			if (i == ls.size() - 1) {
				Integer id = ls.get(i).getPrivilegeId();
				String text = ls.get(i).getPrivilegeName();
				sql.append("{");
				sql.append("\"id\" :");
				sql.append(id);
				sql.append(",");
				sql.append("\"text\" :");
				sql.append("\"" + text + "\"");
				// 这里要用roleId的条件,否则的话,二级页面就圈出来了,加一个判断
				if (status != null) {
					Integer roleId = (Integer) session.getAttribute("roleId");
					/**如果在授权页面用户没有登录,直接返回null*/
					if(user == null) return null;
					//加一个判断,如果是admin的话,获得所拥有的角色下的所有二级菜单
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("roleId", roleId);
					map.put("id", id);
					if(user.getUserName().equals(App.SUPERNAME)){
						al = userPrivilegeMapper.selectSecondMenuListByRoleId(map);
					}else{
						//不是超级管理员
						al = rolePrivilegeMapper.selectHasBuildForSecondMenu(new TreeDto(id,roleId,user.getUserId()));
					}
				} else {
					al = privilegeMapper.selectSecondPrivileges(id);
				}
				if (al.size() > 0 && al != null) {
					sql.append(",");
					sql.append("\"children\" : [");
					for (int j = 0; j < al.size(); j++) {
						if (j == al.size() - 1) {
							Integer cid = al.get(j).getPrivilegeId();
							String ctext = al.get(j).getPrivilegeName();
							sql.append("{");
							sql.append("\"id\" :");
							sql.append(cid);
							sql.append(",");
							sql.append("\"text\" :");
							sql.append("\"" + ctext + "\"");
							sql.append("}");
						} else {
							Integer cid = al.get(j).getPrivilegeId();
							String ctext = al.get(j).getPrivilegeName();
							sql.append("{");
							sql.append("\"id\" :");
							sql.append(cid);
							sql.append(",");
							sql.append("\"text\" :");
							sql.append("\"" + ctext + "\"");
							sql.append("}");
							sql.append(",");
						}
					}
					sql.append("]");
				}
				sql.append("}");
			} else {
				Integer id = ls.get(i).getPrivilegeId();
				String text = ls.get(i).getPrivilegeName();
				sql.append("{");
				sql.append("\"id\" :");
				sql.append(id);
				sql.append(",");
				sql.append("\"text\" :");
				sql.append("\"" + text + "\"");
				if (status != null) {
					Integer roleId = (Integer) session.getAttribute("roleId");
					/**如果在授权页面用户没有登录,直接返回null*/
					if(user == null) return null;
					//加一个判断,如果是admin的话,获得所拥有的角色下的所有二级菜单
					Map<String, Integer> map = new HashMap<String, Integer>();map.put("roleId", roleId);map.put("id", id);
					if(user.getUserName().equals(App.SUPERNAME)){
						al = userPrivilegeMapper.selectSecondMenuListByRoleId(map);
					}else{
					//不是超级管理员
					al = rolePrivilegeMapper.selectHasBuildForSecondMenu(new TreeDto(id,roleId,user.getUserId()));
					}
				} else {
					al = privilegeMapper.selectSecondPrivileges(id);
				}
				if (al.size() > 0 && al != null) {
					sql.append(",");
					sql.append("\"children\" : [");
					for (int j = 0; j < al.size(); j++) {
						if (j == al.size() - 1) {
							Integer cid = al.get(j).getPrivilegeId();
							String ctext = al.get(j).getPrivilegeName();
							sql.append("{");
							sql.append("\"id\" :");
							sql.append(cid);
							sql.append(",");
							sql.append("\"text\" :");
							sql.append("\"" + ctext + "\"");
							sql.append("}");
						} else {
							Integer cid = al.get(j).getPrivilegeId();
							String ctext = al.get(j).getPrivilegeName();
							sql.append("{");
							sql.append("\"id\" :");
							sql.append(cid);
							sql.append(",");
							sql.append("\"text\" :");
							sql.append("\"" + ctext + "\"");
							sql.append("}");
							sql.append(",");
						}
					}
					sql.append("]");
				}
				sql.append("}");
				sql.append(",");
			}
		}
		sql.append("]");
		sql.append("}");
		sql.append("]");
		return JSONArray.fromObject(sql.toString());
	}

	/**
	 * 页面元素权限生成树
	 * @author 高国藩
	 * @date 2015年6月30日 下午8:41:26
	 * @return
	 */
	public JSONArray getTree2(HttpSession session, String status) {
		/*获取user,如果是登录的话将查询该用户所有的权限*/
		User user = (User) session.getAttribute("user");
		List<Privilege> al;
		/**status判断是否进入了授权模块,如果是超级管理员,在下面的判断中加入超级的判断,跳过即可获取所有的权限*/
		if (status != null) {
			Integer roleId = (Integer) session.getAttribute("roleId");
			if (roleId == null) return null;
			if(user == null) return null;
			RoleUtils roleUtils=new RoleUtils();
			roleUtils.setRoleId(roleId);
			roleUtils.setUserId(user.getUserId());
			if(user.getUserName().equals(App.SUPERNAME)){
				al = rolePrivilegeMapper.selectElemetByRoleId(roleId);
			}else{
			/** 点击的角色的权限树形 */
			al = rolePrivilegeMapper.selectHasBuildForYs(roleUtils);
			}
		} else {
			al = privilegeMapper.getAllPrivilegesYs();
		}
		StringBuffer sql = new StringBuffer(
				"[{\"id\":0,\"text\":\"页面元素权限\",\"children\":");
		sql.append("[");
		if (al.size() > 0 && al != null) {
			for (int j = 0; j < al.size(); j++) {
				Integer id = al.get(j).getPrivilegeId();
				String text = al.get(j).getPrivilegeName();
				if (j == al.size() - 1) {
					sql.append("{");
					sql.append("\"id\" :");
					sql.append(id);
					sql.append(",");
					sql.append("\"text\" :");
					sql.append("\"" + text + "\"");
					sql.append("}");
				} else {
					sql.append("{");
					sql.append("\"id\" :");
					sql.append(id);
					sql.append(",");
					sql.append("\"text\" :");
					sql.append("\"" + text + "\"");
					sql.append("}");
					sql.append(",");
				}
			}
			sql.append("]");
			sql.append("}");
		} else {
			sql.append("]");
			sql.append("}");
		}
		sql.append("]");
		return JSONArray.fromObject(sql.toString());
	}

	/**
	 * 接口权限
	 * @author 高国藩
	 * @date 2015年6月30日 下午8:41:26
	 * @return
	 */
	public JSONArray getTree3(HttpSession session, String status) {
		User user = (User) session.getAttribute("user");
		List<Privilege> al;
		if (status != null) {
			Integer roleId = (Integer) session.getAttribute("roleId");
			if (roleId == null) return null;
			if(user == null) return null;
			RoleUtils roleUtils=new RoleUtils();
			roleUtils.setRoleId(roleId);
			roleUtils.setUserId(user.getUserId());
			if(user.getUserName().equals(App.SUPERNAME)){
				al = rolePrivilegeMapper.selectGnByRoleId(roleId);
			}else{
			/** 点击的角色的权限树形 */
			al = rolePrivilegeMapper.selectHasBuildForGn(roleUtils);
			}
		} else {
			al = privilegeMapper.getAllPrivilegesGn();
		}
		StringBuffer sql = new StringBuffer(
				"[{\"id\":0,\"text\":\"接口权限\",\"children\":");
		sql.append("[");
		if (al.size() > 0 && al != null) {
			for (int j = 0; j < al.size(); j++) {
				Integer id = al.get(j).getPrivilegeId();
				String text = al.get(j).getPrivilegeName();
				if (j == al.size() - 1) {
					sql.append("{");
					sql.append("\"id\" :");
					sql.append(id);
					sql.append(",");
					sql.append("\"text\" :");
					sql.append("\"" + text + "\"");
					sql.append("}");
				} else {
					sql.append("{");
					sql.append("\"id\" :");
					sql.append(id);
					sql.append(",");
					sql.append("\"text\" :");
					sql.append("\"" + text + "\"");
					sql.append("}");
					sql.append(",");
				}
			}
			sql.append("]");
			sql.append("}");
		} else {
			sql.append("]");
			sql.append("}");
		}
		sql.append("]");
		return JSONArray.fromObject(sql.toString());
	}

	/**
	 * 跳转至角色权限绑定页面，在session中保存角色的id,页面上显示该角色已经勾选的权限
	 * @author 高国藩
	 * @date 2015年7月1日 上午10:36:22
	 * @param session
	 */
	public ModelAndView sendRoleBuildPrivlege(HttpSession session,
			Integer roleId) {
		session.removeAttribute("pId");
		session.removeAttribute("roleId");
		if (roleId != null) {
			session.setAttribute("roleId", roleId);
		}
		List<Integer> ls = rolePrivilegeMapper.selectPidByRoleId(roleId);
		if (ls.size() > 0) {
			StringBuffer sql = new StringBuffer();
			for (int i = 0; i < ls.size(); i++) {
				if (i == ls.size() - 1) {
					sql.append(ls.get(i).toString());
				} else {
					sql.append(ls.get(i).toString());
					sql.append("_");
				}
				session.setAttribute("pId", sql.toString());
			}
		}
		return new ModelAndView("roleInfo/roleBuildPrivilege");
//		return new ModelAndView("role/roleInfo");
	}

	/**
	 * 角色权限绑定
	 * 
	 * @author 高国藩
	 * @date 2015年7月1日 上午10:36:22
	 * @param session
	 */
	public BaseDto buildRolePrivlege(HttpSession session, String pId) {
		Integer roleId = (Integer) session.getAttribute("roleId");
		if (roleId == null) {
			return new BaseDto(200, "请选择角色");
		} else {
			if (pId.equals("") || pId == null) {
				rolePrivilegeMapper.deleteByRoleId(roleId);
				return new BaseDto(200, "绑定成功");
			} else {
				RolePrivilege record = new RolePrivilege();
				String pid[] = pId.split("_");
				rolePrivilegeMapper.deleteByRoleId(roleId);
				for (int i = 0; i < pid.length; i++) {
					record.setPrivilegeId(Integer.valueOf(pid[i]));
					record.setRoleId(roleId);
					rolePrivilegeMapper.insert(record);
				}
				session.setAttribute("pId", pId);
				return new BaseDto(200, "绑定成功");
			}
		}
	}

	/**
	 * @author 高国藩
	 * @date 2015年7月1日 上午10:36:22
	 * @param session
	 */
	public ModelAndView setPrivilegeToUser(Integer userId, HttpSession session,
			Integer roleId, Map<String, String> map) {
		//新家的
		session.removeAttribute("roleId");
		/*取得已经登录的用户,查询其相关的角色和权限*/
		User user = (User) session.getAttribute("user");
		/*取得要授权的用户*/
		if (userId != null) {
			session.removeAttribute("userId");
			session.setAttribute("userId", userId);
		}
		if (roleId != null) {
			session.removeAttribute("roleId");
			session.setAttribute("roleId", roleId);
		}
		roleId = (Integer) session.getAttribute("roleId");
		userId = (Integer) session.getAttribute("userId");
		RoleUtils roleUtils = new RoleUtils();
		roleUtils.setRoleId(roleId);
		roleUtils.setUserId(userId);
		Integer uId = (Integer) session.getAttribute("userId");
		List<Integer> ls = userPrivilegeMapper
				.selectPidByUserIdAndRoleId(roleUtils);
		System.out.println("ls已经拥有的权限有:"+ls.toString());
		session.removeAttribute("pId");
		if (ls.size() > 0) {
			StringBuffer sql = new StringBuffer();
			for (int i = 0; i < ls.size(); i++) {
				if (i == ls.size() - 1) {
					sql.append(ls.get(i).toString());
				} else {
					sql.append(ls.get(i).toString());
					sql.append("_");
				}
				session.setAttribute("pId", sql.toString());
				map.put("pId", sql.toString());
			}
		}
		ModelAndView model = new ModelAndView("roleInfo/setPrivilegeToUser");
		return model;
	}

	/**
	 * 人员权限绑定
	 * @author 高国藩
	 * @date 2015年7月1日 上午10:36:22
	 * @param session
	 */
	public BaseDto buildUserRolePrivlege(HttpSession session, String pId) {
		/* 1 */
		Integer roleId = (Integer) session.getAttribute("roleId");
		Integer userId = (Integer) session.getAttribute("userId");
		RoleUtils roleUtils = new RoleUtils();
		roleUtils.setRoleId(roleId);
		roleUtils.setUserId(userId);
		if (userId == null || roleId == null) {
			return new BaseDto(200, "请选择人员和角色");
		} else {
			/* 如果是空,说明是全部勾选掉了,全部删除,根据userId和roleId进行删除 */
			if (pId.equals("") || pId == null) {
				userPrivilegeMapper.deleteByRoleIdAndUserId(roleUtils);
				return new BaseDto(200, "绑定成功");
			} else {
				/* 如果传过来了,,全部删除,根据userId和roleId进行删除,再次将其加入数据中 */
				userPrivilegeMapper.deleteByRoleIdAndUserId(roleUtils);
				
				/*将选定的角色也赋予给授权的对象*/
				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("userId", userId);map.put("roleId", roleId);
				rolePrivilegeMapper.deleteByRoleIdAndUserId(map);
				//再次插入
				UserRole userRole = new UserRole();
				userRole.setRoleId(roleId);userRole.setUserId(userId);
				userRoleMapper.insert(userRole);
				/* 全部删除后,开始批量新增操作 */
				String pid[] = pId.split("_");
				List<UserPrivilege> userPrivileges = new ArrayList<UserPrivilege>();
				for (int i = 0; i < pid.length; i++) {
					UserPrivilege userPrivilege = new UserPrivilege();
					userPrivilege.setPrivilegeId(Integer.valueOf(pid[i]));
					userPrivilege.setRoleId(roleId);
					userPrivilege.setUserId(userId);
					userPrivileges.add(userPrivilege);
				}
				int k = userPrivilegeMapper.insertList(userPrivileges);
				// 重新将所有的pId查询,用户返回用户已选择的权限
				List<Integer> ls = userPrivilegeMapper
						.selectPidByUserIdAndRoleId(roleUtils);
				StringBuffer sql = new StringBuffer();
				for (int i = 0; i < ls.size(); i++) {
					if (i == ls.size() - 1) {
						sql.append(ls.get(i).toString());
						sql.append("_");
					} else {
						sql.append(ls.get(i).toString());
					}
				}
				session.setAttribute("pId", sql.toString());
				return new BaseDto(200, "绑定成功");
			}
		}
	}
	
	/**下三为人员绑定角色操作*/
	public List<User> queryUser() {
		List<User> list = new ArrayList<User>();
			list = userMapper.selectUsers();
		return list;
	}

	public BaseDto realUserBuildRole(String roleIds, String userId) {
		if(roleIds!=null&&roleIds.equals("")){
			userRoleMapper.deleteByUserId(Integer.valueOf(userId));
			return new BaseDto(0, "绑定成功");
		}else{
			userRoleMapper.deleteByUserId(Integer.valueOf(userId));
	    String roleId[] = roleIds.split("_");
	    int k =0 ;
	    for (int i = 0; i < roleId.length; i++) {
	    	UserRole record = new UserRole();
	    	record.setRoleId(Integer.valueOf(roleId[i]));
	    	record.setUserId(Integer.valueOf(userId));
			k = userRoleMapper.insert(record);
		}
	    if(k>0){return new BaseDto(0, "绑定成功");} else{
		 return new BaseDto(0, "绑定失败");
		 }
	    }
	}
	
	public BaseDto getUserRole(String userId){
		List<Integer> role = userRoleMapper.getRoleByUserId(Integer.valueOf(userId));
		if(role==null||role.size()<=0)return new BaseDto(0, role);
		return new BaseDto(0, role);
	}
	
/***********************************bootstrap的树形*****************************************/
	public ModelAndView sendBprivilege(HttpSession session){
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		JSONArray json;
		//session 中的登陆用户 现在为模拟
//		User user = new User();
//		user.setUserId(74);
//		user.setUserName(App.SUPERNAME);
		//如果是超级管理员,那么就能获得角色绑定的所有权限
		if(userInfoDto.getUserName().equals(App.SUPERNAME)){
			List<RoleDto> dtos = new ArrayList<RoleDto>();
			List<Integer> ls = roleMapper.selectAllRoleId();
			for (int i = 0; i < ls.size(); i++) {
				Integer roleId = ls.get(i);
				RoleDto roleDto = roleMapper.selectRoleBookstrap(roleId);
				PrivilegeDto menu = new PrivilegeDto("菜单权限", rolePrivilegeMapper.selectPrivilegeMenuForAdmin(roleId));
				PrivilegeDto gn = new PrivilegeDto("功能权限", rolePrivilegeMapper.selectPriivlegeFunctionForAdmin(roleId));
				PrivilegeDto ys = new PrivilegeDto("元素权限", rolePrivilegeMapper.selectPriivlegeElemtForAdmin(roleId));
				List<PrivilegeDto> al = new ArrayList<PrivilegeDto>();
				al.add(menu);
				al.add(gn);
				al.add(ys);
				roleDto.setNodes(al);
				dtos.add(roleDto);
			}
			JsonConfig cfg = new JsonConfig(); 
			cfg.setJsonPropertyFilter(new JsonPropertyFilter());
			json = JSONArray.fromObject(dtos, cfg);
		}
		else//不是超级管理员的话执行下面的
		{
		List<RoleDto> dtos = new ArrayList<RoleDto>();
		Integer userId =userInfoDto.getUserId();
		List<Integer> ls = userRoleMapper.getRoleByUserId(userId);
		for (int i = 0; i < ls.size(); i++) {
			Integer roleId = ls.get(i);
			//角色树形
			RoleDto roleDto = roleMapper.selectRoleBookstrap(roleId);
			//查询用户和角色绑定的权限
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("userId", userId);
			map.put("roleId", roleId);
			PrivilegeDto menu = new PrivilegeDto("菜单权限", privilegeMapper.selectFirstBootstrapPrivilegeMenu(map));
			PrivilegeDto gn = new PrivilegeDto("功能权限", privilegeMapper.selectBootstrapPriivlegeFunction(map));
			PrivilegeDto ys = new PrivilegeDto("元素权限", privilegeMapper.selectBootstrapPriivlegeElemt(map));
			List<PrivilegeDto> al = new ArrayList<PrivilegeDto>();
			al.add(menu);
			al.add(gn);
			al.add(ys);
			roleDto.setNodes(al);
			dtos.add(roleDto);
		}
		JsonConfig cfg = new JsonConfig(); 
		cfg.setJsonPropertyFilter(new JsonPropertyFilter());
		json = JSONArray.fromObject(dtos, cfg);
		}
		/*封装人员信息*/
		List<User> ls = userMapper.selectUsers();
		ModelAndView model = new ModelAndView("roleInfo/bootstraprole");
		model.addObject("tree", json);
		model.addObject("u", JSONArray.fromObject(ls));
 		return model;
	}

	public BaseDto bootstrapUserBuildPrivilege(HttpSession session,
			String code,String userId) {
		if(code!=null&&code.equals("")){
			userPrivilegeMapper.deleteByUserId(Integer.valueOf(userId));
			userRoleMapper.deleteByUserId(Integer.valueOf(userId));
			return new BaseDto(0, "绑定成功");
		}else{
		/*权限人员角色信息*/
		List<UserPrivilege> ls = new ArrayList<UserPrivilege>();
		/*人员角色信息*/
	    List<Integer> bs = new ArrayList<Integer>();
		if(code.indexOf(",")<=0){
			String ders[] = code.split("_");
			Integer roleId = Integer.valueOf(ders[0]);
			bs.add(roleId);
			for (int i = 1; i < ders.length; i++) {
				UserPrivilege privilege = new UserPrivilege();
				privilege.setPrivilegeId(Integer.valueOf(ders[i]));
				privilege.setRoleId(roleId);
				privilege.setUserId(Integer.valueOf(userId));
				ls.add(privilege);
			}
		}else{
		String codes[] = code.split(",");
		for (String c : codes) {
			String ders[] = c.split("_");
			Integer roleId = Integer.valueOf(ders[0]);
			bs.add(roleId);
			for (int i = 1; i < ders.length; i++) {
				UserPrivilege privilege = new UserPrivilege();
				privilege.setPrivilegeId(Integer.valueOf(ders[i]));
				privilege.setRoleId(roleId);
				privilege.setUserId(Integer.valueOf(userId));
				ls.add(privilege);
			}
		}
		}
		userPrivilegeMapper.deleteByUserId(Integer.valueOf(userId));
		userRoleMapper.deleteByUserId(Integer.valueOf(userId));
		/**批量新增用户角色和用户权限*/
		List<UserPrivilege> sbl = new ArrayList<UserPrivilege>();
		for (int i = 0; i < bs.size(); i++) {
			UserPrivilege privilege = new UserPrivilege();
			privilege.setRoleId(bs.get(i));
			privilege.setUserId(Integer.valueOf(userId));
			sbl.add(privilege);
		}
		userRoleMapper.insertIntoUserRole(sbl);
		if(ls.size()<=0){
			return new BaseDto(0, "绑定成功");
		}else{
			userPrivilegeMapper.insertList(ls);
		}
		return new BaseDto(0, "绑定成功");
		}
	}

	public ModelAndView bootstraprolebuildprivilege(HttpSession session) {
		List<PrivilegeDto> dtos = new ArrayList<PrivilegeDto>();
		PrivilegeDto menu = new PrivilegeDto("菜单权限", privilegeMapper.selectBootstrapPrivilegeMenu());
		PrivilegeDto gn = new PrivilegeDto("功能权限", privilegeMapper.selectPrivilege(1));
		PrivilegeDto ys = new PrivilegeDto("元素权限", privilegeMapper.selectPrivilege(2));
		dtos.add(menu);dtos.add(gn);dtos.add(ys);
		
		
		JsonConfig cfg = new JsonConfig(); 
		cfg.setJsonPropertyFilter(new JsonPropertyFilter());
		JSONArray json = JSONArray.fromObject(dtos, cfg);
		ModelAndView model = new ModelAndView("roleInfo/bootstraprolebuildprivilege");
		model.addObject("tree", json);
		List<Role> ls = roleMapper.selectAllRole();
		model.addObject("r", JSONArray.fromObject(ls));
		return model;
	}

	public JSONArray bootstrapUserHasBuildPrivilege(Integer userId) {
		List<Integer> ls = userRoleMapper.getRoleByUserId(userId);
		List<String> bxl = new ArrayList<String>();
		for (Integer s : ls) {
			bxl.add(s.toString());
		}
		List<Map<String, List<Integer>>> bs = new ArrayList<Map<String,List<Integer>>>();
		for (int i = 0; i < ls.size(); i++) {
			Integer roleId = ls.get(i);
			RoleUtils roleUtils = new RoleUtils();
			roleUtils.setRoleId(roleId);
			roleUtils.setUserId(userId);
			List<Integer> al = userPrivilegeMapper.selectPidByUserIdAndRoleId(roleUtils);
			Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
			map.put(roleId.toString(), al);
			bs.add(map);
		}
		List<Object> xl = new ArrayList<Object>();
		xl.add(bxl);
		xl.add(bs);
		return JSONArray.fromObject(xl);
	}

	public JSONArray bootstrapRoleHasBuildPrivilege(Integer roleId) {
		List<Integer> pIds = rolePrivilegeMapper.selectPidByRoleId(roleId);
		return JSONArray.fromObject(pIds);
	}

	public BaseDto bootstrapRoleBuildPrivilege(HttpSession session,
			String codes, String roleId) {
		if(roleId==null||roleId.equals("")){
			return new BaseDto(0, "请选择角色");
		}
		if(codes!=null&&codes.equals("")){
			rolePrivilegeMapper.deleteByRoleId(Integer.valueOf(roleId));
		}else{
			rolePrivilegeMapper.deleteByRoleId(Integer.valueOf(roleId));
			String roleIds[] = codes.split("_");
			List<RolePrivilege> rolePrivileges = new ArrayList<RolePrivilege>();
			for (int i = 0; i < roleIds.length; i++) {
				RolePrivilege rolePrivilege = new RolePrivilege();
				rolePrivilege.setPrivilegeId(Integer.valueOf(roleIds[i]));
				rolePrivilege.setRoleId(Integer.valueOf(roleId));
				rolePrivileges.add(rolePrivilege);
			}
			rolePrivilegeMapper.insertByList(rolePrivileges);
		}
		return new BaseDto(0, "绑定成功");
	}

	public ModelAndView bootstrapSendToPrivilege() {
		List<PrivilegeDto> dtos = new ArrayList<PrivilegeDto>();
		PrivilegeDto menu = new PrivilegeDto("菜单权限", privilegeMapper.selectBootstrapPrivilegeMenu());
		PrivilegeDto gn = new PrivilegeDto("功能权限", privilegeMapper.selectPrivilege(1));
		PrivilegeDto ys = new PrivilegeDto("元素权限", privilegeMapper.selectPrivilege(2));
		dtos.add(menu);dtos.add(gn);dtos.add(ys);
		
		JsonConfig cfg = new JsonConfig(); 
		cfg.setJsonPropertyFilter(new JsonPropertyFilter());
		JSONArray json = JSONArray.fromObject(dtos, cfg);
		ModelAndView model = new ModelAndView("roleInfo/bootstrapUpdatePrivilege");
		model.addObject("tree", json);
		return model;
	}

	public JSONObject showPrivilegeInfo(Map<String, Object> map) {
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("limit", map.get("limit"));
		mp.put("offset", map.get("offset"));
		JSONObject jsonObject = new JSONObject();
		List<PrivilegeInfoDto> ls = privilegeMapper.selectBootstrapTablePrivilegeMenu(mp);
		mp.put("type", 0);
		int total = privilegeMapper.selectPrivilegeMenuCount(mp);
		jsonObject.put("total", total);
		jsonObject.put("rows", ls);
		return jsonObject;
	}

	public JSONObject showPrivilegeGnInfo(Map<String, Object> map) {
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("limit", map.get("limit"));
		mp.put("offset", map.get("offset"));
		mp.put("type", 1);
		List<PrivilegeDto> ls = privilegeMapper.selectPrivilegForGn(mp);
		int total = privilegeMapper.selectPrivilegeMenuCount(mp);
		jsonObject.put("total", total);
		jsonObject.put("rows", JSONArray.fromObject(ls));
		return jsonObject;
	}

	public JSONObject showPrivilegeYsInfo(Map<String, Object> map) {
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("limit", map.get("limit"));
		mp.put("offset", map.get("offset"));
		mp.put("type", 2);
		List<PrivilegeInfoDto> ls = privilegeMapper.selectPrivilegForYs(mp);
		int total = privilegeMapper.selectPrivilegeMenuCount(mp);
		jsonObject.put("total", total);
		jsonObject.put("rows", JSONArray.fromObject(ls));
		return jsonObject;
	}
	
	/**
	 * 斌哥初始化人员选择权限直接授权
	* @author 高国藩
	* @date 2015年7月13日 上午9:10:33
	* @param userId
	* @param roleId
	 */
	public int initUserPrivilege(Integer userId,Integer roleId){
		List<Integer> pId = rolePrivilegeMapper.selectPidByRoleId(roleId);
		List<UserPrivilege> ls = new ArrayList<UserPrivilege>();
		for (int i = 1; i < pId.size(); i++) {
			UserPrivilege privilege = new UserPrivilege();
			privilege.setPrivilegeId(pId.get(i));
			privilege.setRoleId(roleId);
			privilege.setUserId(userId);
			ls.add(privilege);
		}
		return userPrivilegeMapper.insertList(ls);
	}
}
