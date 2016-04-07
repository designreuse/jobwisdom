package com.lives.manage.web.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lives.manage.common.consts.Url;
import com.lives.manage.web.dto.BaseDto;
import com.lives.manage.web.entity.Privilege;
import com.lives.manage.web.entity.Role;
import com.lives.manage.web.entity.UserRole;
import com.lives.manage.web.mapper.PrivilegeMapper;
import com.lives.manage.web.mapper.RoleMapper;
import com.lives.manage.web.mapper.RolePrivilegeMapper;
import com.lives.manage.web.mapper.UserMapper;
import com.lives.manage.web.mapper.UserPrivilegeMapper;
import com.lives.manage.web.mapper.UserRoleMapper;
import com.lives.manage.web.service.PrivilegeService;

@Controller
@RequestMapping(value = "privilege")
@SuppressWarnings("unused")
public class PrivilegeController {

	@Autowired
	private PrivilegeService privilegeService;
	
	/**
	 * 角色新增
	 * 
	 * @author 高国藩
	 * @date 2015年6月26日 下午8:05:44
	 * @param role
	 * @return
	 */
	@RequestMapping(Url.Privilege.addRole)
	@ResponseBody
	public BaseDto addRole(Role role) {
		return privilegeService.addRole(role);
//		int count = roleMapper.selectCount();
//		role.setSort(count + 1);
//		int i = roleMapper.insert(role);
//		if (i > 0) {
//			return new BaseDto(200, "新增角色成功");
//		} else {
//			return new BaseDto(200, "新增角色失败");
//		}
	}

	/**
	 * 获取所有角色
	 * 如果用户要对其他人进行授权的话,那么他一定要登录,登录以后会找寻他所绑定的角色,然后显示角色之后,点击了角色,
	 * 在setPrivateToUser接口中,session中的roleId和userId都会存在值,存在值了,那么久将其已经绑定的权限显示出来
	 * @author 高国藩
	 * @date 2015年6月29日 上午11:29:39
	 * @return
	 */
	@RequestMapping(Url.Privilege.getRole)
	@ResponseBody
	public JSONArray getRole(HttpSession session,String status) {
		return privilegeService.getRole(session,status);
//		User user = (User) session.getAttribute("user");
//		/**通过status来判断是否进入了人员授权的页面*/
//		if(status!=null&&!user.getUserName().equals("admin")){
//			if(user==null)return null;
//			List<Role> ls = roleMapper.selectRoleByUserId(user.getUserId());
//			return JSONArray.fromObject(ls);
//		}else{
//		List<Role> ls = roleMapper.selectAllRole();
//		return JSONArray.fromObject(ls);
//		}
	}

	/**
	 * 删除角色
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 上午11:59:07
	 * @return
	 */
	@RequestMapping(Url.Privilege.deleteRole)
	@ResponseBody
	public BaseDto deleteRole(Integer roleId) {
		return privilegeService.deleteRole(roleId);
//		int i = roleMapper.deleteByPrimaryKey(roleId);
//		if (i > 0) {
//			rolePrivilegeMapper.deleteByRoleId(roleId);
//			//删除角色将绑定的用户信息都删除
//			userRoleMapper.deleteByRoleId(roleId);
//			return new BaseDto(200, "删除角色成功");
//		} else {
//			return new BaseDto(200, "删除角色失败");
//		}
	}

	/**
	 * 修改角色
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 上午11:59:07
	 * @return
	 */
	@RequestMapping(value = Url.Privilege.updateRole, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto updateRole(Role role) {
		return privilegeService.updateRole(role);
//		Role newRole = roleMapper.selectByPrimaryKey(role.getRoleId());
//		newRole.setRoleName(role.getRoleName());
//		int i = roleMapper.updateByPrimaryKey(newRole);
//		if (i > 0) {
//			return new BaseDto(200, "修改角色成功");
//		} else {
//			return new BaseDto(200, "修改角色失败");
//		}
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
	@RequestMapping(value = Url.Privilege.addPrivilege, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto addPrivilege(@PathVariable("type") String type,
			Privilege privilege) {
		return privilegeService.addPrivilege(type,privilege);
	}

	/** 跳转至权限修改页面 */
	@RequestMapping(Url.Privilege.sendUpdatePrivilege)
	public ModelAndView sendUpdatePrivilege() {
//		ModelAndView model = new ModelAndView("role/updatePrivilege");
		ModelAndView model = new ModelAndView("roleInfo/updatePrivilege");
		return model;
	}

	/** 修改菜单权限 */
	@RequestMapping(value = Url.Privilege.updatePrivilege, method = RequestMethod.POST)
	public BaseDto updatePrivilege(Privilege privilege) {
		return privilegeService.updatePrivilege(privilege);
//		int k = privilegeMapper.updateByPrimaryKeySelective(privilege);
//		if (k > 0)
//			return new BaseDto(200, "修改成功");
//		return new BaseDto(200, "修改失败");
	}

	/**
	 * 删除权限
	 * 
	 * @author 高国藩
	 * @date 2015年7月3日 下午3:25:24
	 * @return
	 */
	@RequestMapping(value = Url.Privilege.deletePrivilege, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto deletePrivilege(String pId) {
		return privilegeService.deletePrivilege(pId);
//		if (pId == null || pId.equals(""))
//			return new BaseDto(200, "请选择删除项目");
//		int k = 0;
//		String id[] = pId.split("_");
//		for (int i = 0; i < id.length; i++) {
//			k = privilegeMapper.deleteByPrimaryKey(Integer.valueOf(id[i]));
//			//删除角色将绑定的用户信息都删除
//			userPrivilegeMapper.deleteByPid(Integer.valueOf(id[i]));
//			rolePrivilegeMapper.deleteBecasePid(Integer.valueOf(id[i]));
//		}
//		if (k == 1)
//			return new BaseDto(200, "删除成功");
//		return new BaseDto(204, "删除失败");
	}

	/**
	 * 获取菜单中的一级菜单
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 上午11:29:39
	 * @return
	 */
	@RequestMapping(Url.Privilege.getPrivilege)
	@ResponseBody
	public JSONArray getPrivilege() {
		return privilegeService.getPrivilege();
//		List<Privilege> ls = privilegeMapper.selectFirstMenu();
//		return JSONArray.fromObject(ls);
	}

	/**
	 * 获取所有的权限信息,进行角色绑定
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 下午9:07:37
	 * @return
	 */
	@RequestMapping(Url.Privilege.getAllPrivileges)
	@ResponseBody
	public JSONArray getAllPrivileges() {
		return privilegeService.getAllPrivileges();
//		List<Privilege> ls = privilegeMapper.selectFirstMenu();
//		return JSONArray.fromObject(ls);
	}

	/**
	 * 获取二级权限信息,进行角色绑定
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 下午9:07:37
	 * @return
	 */
	@RequestMapping(Url.Privilege.getAllPrivilegesSecond)
	@ResponseBody
	public JSONArray getAllPrivilegesSecond(Integer id) {
		return privilegeService.getAllPrivilegesSecond(id);
//		List<Privilege> ls = privilegeMapper.selectSecondPrivileges(id);
//		return JSONArray.fromObject(ls);
	}

	/**
	 * 获取接口权限信息,进行角色绑定
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 下午9:07:37
	 * @return
	 */
	@RequestMapping(Url.Privilege.getAllPrivilegesGn)
	@ResponseBody
	public JSONArray getAllPrivilegesGn() {
		return privilegeService.getAllPrivilegesGn();
//		List<Privilege> ls = privilegeMapper.getAllPrivilegesGn();
//		return JSONArray.fromObject(ls);
	}

	/**
	 * 获取元素权限信息,进行角色绑定
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 下午9:07:37
	 * @return
	 */
	@RequestMapping(Url.Privilege.getAllPrivilegesYs)
	@ResponseBody
	public JSONArray getAllPrivilegesYs() {
		return privilegeService.getAllPrivilegesYs(); 
//		List<Privilege> ls = privilegeMapper.getAllPrivilegesYs();
//		List<Privilege> al = new ArrayList<Privilege>();
//		for (int i = 0; i < ls.size(); i++) {
//			JSONObject json = JSONObject.fromObject(ls.get(i)
//					.getPrivilegeValue());
//			Privilege privilege = new Privilege();
//			privilege.setPrivilegeId(ls.get(i).getPrivilegeId());
//			privilege.setPrivilegeValue(json.get("jsp").toString() + "页面下的"
//					+ json.get("classname").toString());
//			al.add(privilege);
//		}
//		return JSONArray.fromObject(al);
	}

	/**
	 * 人员角色绑定
	* @author 高国藩
	* @date 2015年7月6日 下午3:41:22
	* @param session
	* @return
	 */
	@RequestMapping(Url.Privilege.userBuileRole)
	@ResponseBody
	public ModelAndView userBuileRole(HttpSession session) {
		JSONArray json = getRole(session, null);
		BaseDto baseDto1 = new BaseDto(0, json);
		BaseDto baseDto2 = queryUser();
		ModelAndView view = new ModelAndView("roleInfo/userBuildRole");
		view.addObject("role",baseDto1);
		view.addObject("user",baseDto2);
		return view;
	
	}
	/**
	 * 人员角色绑定
	* @author 高国藩
	* @date 2015年7月6日 下午3:41:22
	* @param session
	* @return
	 */
	@RequestMapping(Url.Privilege.realUserBuildRole)
	@ResponseBody
	public BaseDto realUserBuildRole(String roleIds,String userId) {
		return privilegeService.realUserBuildRole(roleIds,userId);
	}
	
	/**
	 * 获取人员信息
	* @author 高国藩
	* @date 2015年7月6日 下午3:59:04
	* @return
	 */
	@RequestMapping(Url.Privilege.queryUser)
	@ResponseBody
	public BaseDto queryUser(){
		return new BaseDto(0, JSONArray.fromObject(privilegeService.queryUser())); 
	}
	
	/**
	 * 获取人员已经绑定的信息
	* @author 高国藩
	* @date 2015年7月6日 下午3:59:04
	* @return
	 */
	@RequestMapping(Url.Privilege.getUserRole)
	@ResponseBody
	public BaseDto getUserRole(String userId){
		return privilegeService.getUserRole(userId); 
	}
	
	/**
	 * 进行角色绑定
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 下午9:07:37
	 * @return
	 */
	@RequestMapping(Url.Privilege.buildRole)
	@ResponseBody
	public BaseDto buildRole(Integer pId, HttpSession session) {
		return privilegeService.buildRole(pId,session); 
//		Integer roleId = (Integer) session.getAttribute("roleId");
//		if (roleId == null) {
//			return new BaseDto(200, "请选择角色");
//		} else {
//			RolePrivilege record = new RolePrivilege();
//			record.setPrivilegeId(pId);
//			record.setRoleId(roleId);
//			rolePrivilegeMapper.deleteByRoleId(roleId);
//			rolePrivilegeMapper.insert(record);
//		}
//		return null;
	}

	/**
	 * 回显已勾选
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 下午9:07:37
	 * @return
	 */
	@RequestMapping(Url.Privilege.getHashPrivileges)
	@ResponseBody
	public JSONArray getHashPrivileges(HttpSession session) {
		return privilegeService.getHashPrivileges(session); 
//		List<Integer> ls = new ArrayList<Integer>();
//		Integer roleId = (Integer) session.getAttribute("roleId");
//		if (roleId == null) {
//			return null;
//		} else {
//			ls = rolePrivilegeMapper.selectPidByRoleId(roleId);
//		}
//		for (int i = 0; i < ls.size(); i++) {
//		}
//		return JSONArray.fromObject(ls);
	}

	/**
	 * 人员授权
	 * 
	 * @author 高国藩
	 * @date 2015年6月26日 下午8:35:02
	 * @param userRole
	 * @return
	 */
	@RequestMapping(Url.Privilege.setRoleToUser)
	@ResponseBody
	public BaseDto setRoleToUser(UserRole userRole) {
		return privilegeService.setRoleToUser(userRole); 
//		int i = userRoleMapper.insert(userRole);
//		if (i > 0) {
//			return new BaseDto(200, "授权成功");
//		} else {
//			return new BaseDto(200, "授权失败");
//		}
	}

	@RequestMapping(Url.Privilege.getRoleUserLs)
	@ResponseBody
	public JSONObject getAllUserRole() {
		return privilegeService.getAllUserRole(); 
//		List<UserRoleUtils> ls = userRoleMapper.getAllUserRole();
//		return null;
	}

	/**
	 * 跳转至角色新增页面
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 上午10:36:16
	 */
	@RequestMapping(value = Url.Privilege.sendRole)
	public String sendRole(Integer roleId, HttpSession session) {
		return privilegeService.sendRole(roleId,session); 
//		session.setAttribute("roleId", roleId);
//		//return "role/roleInfo";
//		return "roleInfo/roleInfo";
	}

	/**
	 * 跳转至权限新增页面
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 上午10:36:16
	 */
	@RequestMapping(value = Url.Privilege.sendPrivilege)
	public String sendPrivilege() {
		return privilegeService.sendPrivilege(); 
//		System.out.println("跳转至角色新增页面");
//		return "role/privilegeInfo";
	}

	/**
	 * 跳转至人员权限页面
	 * 
	 * @author 高国藩
	 * @date 2015年6月29日 上午10:36:16
	 */
	@RequestMapping(value = Url.Privilege.sendPeoplePrivilege)
	public String sendPeoplePrivilege() {
		return privilegeService.sendPeoplePrivilege(); 
//		System.out.println("跳转至角色新增页面");
//		return "role/tree";
	}

	@RequestMapping(Url.Privilege.getTree)
	@ResponseBody
	public JSONArray getTree(HttpSession session, String status) {
		return privilegeService.getTree(session,status); 
//		/*获取user,如果是登录的话将查询该用户所有的权限*/
//		User user = (User) session.getAttribute("user");
//		List<Privilege> ls; // 一级菜单
//		List<Privilege> al; // 二级菜单
//		System.out.println("用户的信息是:"+user.getUserName());
//		/**status是否进入了人员授权页面*/
//		if (status != null && !user.getUserName().equals("admin")) {
//			Integer roleId = (Integer) session.getAttribute("roleId");
//			if (roleId == null) return null;
//			
//			if (user == null) return null;
//			/** 如果status!=null那么说明用户是在授权的页面,那么要根据user所绑定的权限来展示,如果user为空,不显示了*/
//			/** 点击的角色的权限树形 */
//			RoleUtils roleUtils=new RoleUtils();
//			roleUtils.setRoleId(roleId);
//			roleUtils.setUserId(user.getUserId());
//			ls = rolePrivilegeMapper.selectHasBuildForMenu(roleUtils);
//			System.out.println("一级菜单："+ls.toString()+"roleUtils"+roleUtils.toString());
//			//ls = rolePrivilegeMapper.selectHasBuildForMenu(roleId);
//		} else {
//			ls = privilegeMapper.selectFirstMenu();
//			System.out.println("超级管理员正在获取全部一级菜单。。。。。。。");
//		}
//		StringBuffer sql = new StringBuffer(
//				"[{\"id\":0,\"text\":\"菜单权限\",\"children\":");
//		sql.append("[");
//		// 菜单权限开始
//		for (int i = 0; i < ls.size(); i++) {
//			if (i == ls.size() - 1) {
//				Integer id = ls.get(i).getPrivilegeId();
//				String text = ls.get(i).getPrivilegeName();
//				sql.append("{");
//				sql.append("\"id\" :");
//				sql.append(id);
//				sql.append(",");
//				sql.append("\"text\" :");
//				sql.append("\"" + text + "\"");
//				// 这里要用roleId的条件,否则的话,二级页面就圈出来了,加一个判断
//				if (status != null && !user.getUserName().equals("admin")) {
//					Integer roleId = (Integer) session.getAttribute("roleId");
//					/**如果在授权页面用户没有登录,直接返回null*/
//					if(user == null) return null;
//					al = rolePrivilegeMapper.selectHasBuildForSecondMenu(new TreeUtils(id,roleId,user.getUserId()));
//					System.out.println("二级菜单"+al.toString());
//				} else {
//					al = privilegeMapper.selectSecondPrivileges(id);
//					System.out.println("超级管理员正在获取全部2级菜单。。。。。。。");
//				}
//				if (al.size() > 0 && al != null) {
//					sql.append(",");
//					sql.append("\"children\" : [");
//					for (int j = 0; j < al.size(); j++) {
//						if (j == al.size() - 1) {
//							Integer cid = al.get(j).getPrivilegeId();
//							String ctext = al.get(j).getPrivilegeName();
//							sql.append("{");
//							sql.append("\"id\" :");
//							sql.append(cid);
//							sql.append(",");
//							sql.append("\"text\" :");
//							sql.append("\"" + ctext + "\"");
//							sql.append("}");
//						} else {
//							Integer cid = al.get(j).getPrivilegeId();
//							String ctext = al.get(j).getPrivilegeName();
//							sql.append("{");
//							sql.append("\"id\" :");
//							sql.append(cid);
//							sql.append(",");
//							sql.append("\"text\" :");
//							sql.append("\"" + ctext + "\"");
//							sql.append("}");
//							sql.append(",");
//						}
//					}
//					sql.append("]");
//				}
//				sql.append("}");
//			} else {
//				Integer id = ls.get(i).getPrivilegeId();
//				String text = ls.get(i).getPrivilegeName();
//				sql.append("{");
//				sql.append("\"id\" :");
//				sql.append(id);
//				sql.append(",");
//				sql.append("\"text\" :");
//				sql.append("\"" + text + "\"");
//				if (status != null && !user.getUserName().equals("admin")) {
//					Integer roleId = (Integer) session.getAttribute("roleId");
//					/**如果在授权页面用户没有登录,直接返回null*/
//					if(user == null) return null;
//					al = rolePrivilegeMapper.selectHasBuildForSecondMenu(new TreeUtils(id,roleId,user.getUserId()));
//				} else {
//					al = privilegeMapper.selectSecondPrivileges(id);
//					System.out.println("超级管理员正在获取全部2级菜单。。。。。。。");
//				}
//				if (al.size() > 0 && al != null) {
//					sql.append(",");
//					sql.append("\"children\" : [");
//					for (int j = 0; j < al.size(); j++) {
//						if (j == al.size() - 1) {
//							Integer cid = al.get(j).getPrivilegeId();
//							String ctext = al.get(j).getPrivilegeName();
//							sql.append("{");
//							sql.append("\"id\" :");
//							sql.append(cid);
//							sql.append(",");
//							sql.append("\"text\" :");
//							sql.append("\"" + ctext + "\"");
//							sql.append("}");
//						} else {
//							Integer cid = al.get(j).getPrivilegeId();
//							String ctext = al.get(j).getPrivilegeName();
//							sql.append("{");
//							sql.append("\"id\" :");
//							sql.append(cid);
//							sql.append(",");
//							sql.append("\"text\" :");
//							sql.append("\"" + ctext + "\"");
//							sql.append("}");
//							sql.append(",");
//						}
//					}
//					sql.append("]");
//				}
//				sql.append("}");
//				sql.append(",");
//			}
//		}
//		sql.append("]");
//		sql.append("}");
//		sql.append("]");
//		return JSONArray.fromObject(sql.toString());
	}

	/**
	 * 页面元素权限生成树
	 * @author 高国藩
	 * @date 2015年6月30日 下午8:41:26
	 * @return
	 */
	@RequestMapping(Url.Privilege.getTree2)
	@ResponseBody
	public JSONArray getTree2(HttpSession session, String status) {
		return privilegeService.getTree2(session, status);
		/*获取user,如果是登录的话将查询该用户所有的权限*/
//		User user = (User) session.getAttribute("user");
//		List<Privilege> al;
//		/**status判断是否进入了授权模块,如果是超级管理员,在下面的判断中加入超级的判断,跳过即可获取所有的权限*/
//		if (status != null && !user.getUserName().equals("admin")) {
//			Integer roleId = (Integer) session.getAttribute("roleId");
//			if (roleId == null) return null;
//			if(user == null) return null;
//			RoleUtils roleUtils=new RoleUtils();
//			roleUtils.setRoleId(roleId);
//			roleUtils.setUserId(user.getUserId());
//			/** 点击的角色的权限树形 */
//			al = rolePrivilegeMapper.selectHasBuildForYs(roleUtils);
//		} else {
//			al = privilegeMapper.getAllPrivilegesYs();
//		}
//		StringBuffer sql = new StringBuffer(
//				"[{\"id\":0,\"text\":\"页面元素权限\",\"children\":");
//		sql.append("[");
//		if (al.size() > 0 && al != null) {
//			for (int j = 0; j < al.size(); j++) {
//				Integer id = al.get(j).getPrivilegeId();
//				String text = al.get(j).getPrivilegeName();
//				if (j == al.size() - 1) {
//					sql.append("{");
//					sql.append("\"id\" :");
//					sql.append(id);
//					sql.append(",");
//					sql.append("\"text\" :");
//					sql.append("\"" + text + "\"");
//					sql.append("}");
//				} else {
//					sql.append("{");
//					sql.append("\"id\" :");
//					sql.append(id);
//					sql.append(",");
//					sql.append("\"text\" :");
//					sql.append("\"" + text + "\"");
//					sql.append("}");
//					sql.append(",");
//				}
//			}
//			sql.append("]");
//			sql.append("}");
//		} else {
//			sql.append("]");
//			sql.append("}");
//		}
//		sql.append("]");
//		return JSONArray.fromObject(sql.toString());
	}

	/**
	 * 接口权限
	 * @author 高国藩
	 * @date 2015年6月30日 下午8:41:26
	 * @return
	 */
	@RequestMapping(Url.Privilege.getTree3)
	@ResponseBody
	public JSONArray getTree3(HttpSession session, String status) {
		return privilegeService.getTree3(session, status);
//		User user = (User) session.getAttribute("user");
//		List<Privilege> al;
//		if (status != null && !user.getUserName().equals("admin")) {
//			Integer roleId = (Integer) session.getAttribute("roleId");
//			if (roleId == null) return null;
//			if(user == null) return null;
//			RoleUtils roleUtils=new RoleUtils();
//			roleUtils.setRoleId(roleId);
//			roleUtils.setUserId(user.getUserId());
//			/** 点击的角色的权限树形 */
//			al = rolePrivilegeMapper.selectHasBuildForGn(roleUtils);
//		} else {
//			al = privilegeMapper.getAllPrivilegesGn();
//		}
//		StringBuffer sql = new StringBuffer(
//				"[{\"id\":0,\"text\":\"接口权限\",\"children\":");
//		sql.append("[");
//		if (al.size() > 0 && al != null) {
//			for (int j = 0; j < al.size(); j++) {
//				Integer id = al.get(j).getPrivilegeId();
//				String text = al.get(j).getPrivilegeName();
//				if (j == al.size() - 1) {
//					sql.append("{");
//					sql.append("\"id\" :");
//					sql.append(id);
//					sql.append(",");
//					sql.append("\"text\" :");
//					sql.append("\"" + text + "\"");
//					sql.append("}");
//				} else {
//					sql.append("{");
//					sql.append("\"id\" :");
//					sql.append(id);
//					sql.append(",");
//					sql.append("\"text\" :");
//					sql.append("\"" + text + "\"");
//					sql.append("}");
//					sql.append(",");
//				}
//			}
//			sql.append("]");
//			sql.append("}");
//		} else {
//			sql.append("]");
//			sql.append("}");
//		}
//		sql.append("]");
//		return JSONArray.fromObject(sql.toString());
	}

	/**
	 * 跳转至角色权限绑定页面，在session中保存角色的id,页面上显示该角色已经勾选的权限
	 * @author 高国藩
	 * @date 2015年7月1日 上午10:36:22
	 * @param session
	 */
	@RequestMapping(Url.Privilege.roleBuildPrivlege)
	public ModelAndView sendRoleBuildPrivlege(HttpSession session,
			Integer roleId) {
		return privilegeService.sendRoleBuildPrivlege(session, roleId);
//		session.removeAttribute("pId");
//		session.removeAttribute("roleId");
//		if (roleId != null) {
//			session.setAttribute("roleId", roleId);
//		}
//		List<Integer> ls = rolePrivilegeMapper.selectPidByRoleId(roleId);
//		if (ls.size() > 0) {
//			StringBuffer sql = new StringBuffer();
//			for (int i = 0; i < ls.size(); i++) {
//				if (i == ls.size() - 1) {
//					sql.append(ls.get(i).toString());
//				} else {
//					sql.append(ls.get(i).toString());
//					sql.append("_");
//				}
//				session.setAttribute("pId", sql.toString());
//			}
//		}
//		return new ModelAndView("roleInfo/roleBuildPrivilege");
////		return new ModelAndView("role/roleInfo");
	}

	/**
	 * 角色权限绑定
	 * 
	 * @author 高国藩
	 * @date 2015年7月1日 上午10:36:22
	 * @param session
	 */
	@RequestMapping(Url.Privilege.buildRolePrivlege)
	@ResponseBody
	public BaseDto buildRolePrivlege(HttpSession session, String pId) {
		return privilegeService.buildRolePrivlege(session, pId);
//		Integer roleId = (Integer) session.getAttribute("roleId");
//		if (roleId == null) {
//			return new BaseDto(200, "请选择角色");
//		} else {
//			if (pId.equals("") || pId == null) {
//				rolePrivilegeMapper.deleteByRoleId(roleId);
//				return new BaseDto(200, "绑定成功");
//			} else {
//				RolePrivilege record = new RolePrivilege();
//				String pid[] = pId.split("_");
//				rolePrivilegeMapper.deleteByRoleId(roleId);
//				for (int i = 0; i < pid.length; i++) {
//					record.setPrivilegeId(Integer.valueOf(pid[i]));
//					record.setRoleId(roleId);
//					rolePrivilegeMapper.insert(record);
//				}
//				session.setAttribute("pId", pId);
//				return new BaseDto(200, "绑定成功");
//			}
//		}
	}

	/**
	 * @author 高国藩
	 * @date 2015年7月1日 上午10:36:22
	 * @param session
	 */
	@RequestMapping(Url.Privilege.setPrivilegeToUser)
	public ModelAndView setPrivilegeToUser(Integer userId, HttpSession session,
			Integer roleId, Map<String, String> map) {
		return privilegeService.setPrivilegeToUser(userId, session, roleId, map);
//		//新家的
//		session.removeAttribute("roleId");
//		/*取得已经登录的用户,查询其相关的角色和权限*/
//		User user = (User) session.getAttribute("user");
//		/*取得要授权的用户*/
//		if (userId != null) {
//			session.removeAttribute("userId");
//			session.setAttribute("userId", userId);
//		}
//		if (roleId != null) {
//			session.removeAttribute("roleId");
//			session.setAttribute("roleId", roleId);
//		}
//		roleId = (Integer) session.getAttribute("roleId");
//		userId = (Integer) session.getAttribute("userId");
//		RoleUtils roleUtils = new RoleUtils();
//		roleUtils.setRoleId(roleId);
//		roleUtils.setUserId(userId);
//		Integer uId = (Integer) session.getAttribute("userId");
//		List<Integer> ls = userPrivilegeMapper
//				.selectPidByUserIdAndRoleId(roleUtils);
//		session.removeAttribute("pId");
//		if (ls.size() > 0) {
//			StringBuffer sql = new StringBuffer();
//			for (int i = 0; i < ls.size(); i++) {
//				if (i == ls.size() - 1) {
//					sql.append(ls.get(i).toString());
//				} else {
//					sql.append(ls.get(i).toString());
//					sql.append("_");
//				}
//				session.setAttribute("pId", sql.toString());
//				map.put("pId", sql.toString());
//			}
//		}
//		//ModelAndView model = new ModelAndView("role/roleInfo2");
//		ModelAndView model = new ModelAndView("roleInfo/setPrivilegeToUser");
//		return model;
	}

	/**
	 * 人员权限绑定
	 * @author 高国藩
	 * @date 2015年7月1日 上午10:36:22
	 * @param session
	 */
	@RequestMapping(Url.Privilege.buildUserRolePrivlege)
	@ResponseBody
	public BaseDto buildUserRolePrivlege(HttpSession session, String pId) {
		return privilegeService.buildUserRolePrivlege(session, pId);
		/* 1 */
//		Integer roleId = (Integer) session.getAttribute("roleId");
//		Integer userId = (Integer) session.getAttribute("userId");
//		RoleUtils roleUtils = new RoleUtils();
//		roleUtils.setRoleId(roleId);
//		roleUtils.setUserId(userId);
//		if (userId == null || roleId == null) {
//			return new BaseDto(200, "请选择人员和角色");
//		} else {
//			/* 如果是空,说明是全部勾选掉了,全部删除,根据userId和roleId进行删除 */
//			if (pId.equals("") || pId == null) {
//				userPrivilegeMapper.deleteByRoleIdAndUserId(roleUtils);
//				return new BaseDto(200, "绑定成功");
//			} else {
//				/* 如果传过来了,,全部删除,根据userId和roleId进行删除,再次将其加入数据中 */
//				userPrivilegeMapper.deleteByRoleIdAndUserId(roleUtils);
//				/* 全部删除后,开始批量新增操作 */
//				System.out.println("pId=====>" + pId);
//				String pid[] = pId.split("_");
//				List<UserPrivilege> userPrivileges = new ArrayList<UserPrivilege>();
//				for (int i = 0; i < pid.length; i++) {
//					UserPrivilege userPrivilege = new UserPrivilege();
//					userPrivilege.setPrivilegeId(Integer.valueOf(pid[i]));
//					userPrivilege.setRoleId(roleId);
//					userPrivilege.setUserId(userId);
//					userPrivileges.add(userPrivilege);
//				}
//				System.out.println(userPrivileges.toString());
//				int k = userPrivilegeMapper.insertList(userPrivileges);
//				// 重新将所有的pId查询,用户返回用户已选择的权限
//				List<Integer> ls = userPrivilegeMapper
//						.selectPidByUserIdAndRoleId(roleUtils);
//				StringBuffer sql = new StringBuffer();
//				for (int i = 0; i < ls.size(); i++) {
//					if (i == ls.size() - 1) {
//						sql.append(ls.get(i).toString());
//						sql.append("_");
//					} else {
//						sql.append(ls.get(i).toString());
//					}
//				}
//				session.setAttribute("pId", sql.toString());
//				return new BaseDto(200, "绑定成功");
//			}
//		}
	}
/***********************************bootstrap的树形  授权开始*****************************************/
	/**
	* 人员授权页面 跳转页面以及封装数据
	* @author 高国藩
	* @date 2015年7月8日 下午11:10:39
	* @param session
	* @return
	 */
	@RequestMapping(value=Url.Privilege.sendBprivilege)
	public ModelAndView sendBprivilege(HttpSession session){
		return privilegeService.sendBprivilege(session);
	}
	
	/**
	* 给人员授权 Ajax 接口
	* @author 高国藩
	* @date 2015年7月8日 下午8:51:30
	* @param session
	* @param code 数据格式为 1_1_2_3_4,2_1_3  分别为两个角色下面的不同权限,是以','来隔开的;
	* @return
	 */
	@RequestMapping(value=Url.Privilege.bootstrapUserBuildPrivilege)
	@ResponseBody
	public BaseDto bootstrapUserBuildPrivilege(HttpSession session,String codes,String userId){
		 return privilegeService.bootstrapUserBuildPrivilege(session,codes,userId);
	}
	
	/**
	 * 回线人员已经绑定的角色权限信息
	* @author 高国藩
	* @date 2015年7月8日 下午11:36:23
	* @param userId
	* @return
	 */
	@RequestMapping(value=Url.Privilege.bootstrapUserHasBuildPrivilege)
	@ResponseBody
	public JSONArray bootstrapUserHasBuildPrivilege(Integer userId){
		 return privilegeService.bootstrapUserHasBuildPrivilege(userId);
	}
	
/*****************************角色绑定权限开始  包括了角色的操作*******************************************/
	
	/**
	* 角色权限绑定页面
	* @author 高国藩
	* @date 2015年7月8日 下午11:35:21
	* @param session
	* @param roleId
	* @return
	 */
	@RequestMapping(value=Url.Privilege.bootstraprolebuildprivilege)
	public ModelAndView bootstraprolebuildprivilege(HttpSession session){
		 return privilegeService.bootstraprolebuildprivilege(session);
	}
	/**
	* 显示角色已经绑定的权限信息
	* @author 高国藩
	* @date 2015年7月8日 下午11:36:23
	* @param userId
	* @return
	 */
	@RequestMapping(value=Url.Privilege.bootstrapRoleHasBuildPrivilege)
	@ResponseBody
	public JSONArray bootstrapRoleHasBuildPrivilege(Integer roleId){
		 return privilegeService.bootstrapRoleHasBuildPrivilege(roleId);
	}
	/**
	* 给角色绑定授权 Ajax 接口
	* @author 高国藩
	* @date 2015年7月8日 下午8:51:30
	* @param session
	* @param codes 传入的数据格式为 1_2_3_4 表示为权限的id
	* @return
	 */
	@RequestMapping(value=Url.Privilege.bootstrapRoleBuildPrivilege)
	@ResponseBody
	public BaseDto bootstrapRoleBuildPrivilege(HttpSession session,String codes,String roleId){
		 return privilegeService.bootstrapRoleBuildPrivilege(session,codes,roleId);
	}
	
/*************************************权限开始*******************************************/
	
	/**
	 * 权限新增修改页面
	* @author 高国藩
	* @date 2015年7月10日 上午9:30:32
	* @return
	 */
	@RequestMapping(value=Url.Privilege.bootstrapSendToPrivilege)
	public ModelAndView bootstrapSendToPrivilege(){
		return privilegeService.bootstrapSendToPrivilege();
	}
	/**
	 * 展示权限信息 下三个↓↓↓
	* @author 高国藩
	* @date 2015年7月10日 上午9:30:32
	* @return
	 */
	@RequestMapping(value=Url.Privilege.showPrivilegeInfo)
	@ResponseBody
	public JSONObject showPrivilegeInfo(@RequestBody Map<String, Object> map){
		return privilegeService.showPrivilegeInfo(map);
	}
	@RequestMapping(value=Url.Privilege.showPrivilegeGnInfo)
	@ResponseBody
	public JSONObject showPrivilegeGnInfo(@RequestBody Map<String, Object> map){
		
		return privilegeService.showPrivilegeGnInfo(map);
	}
	@RequestMapping(value=Url.Privilege.showPrivilegeYsInfo)
	@ResponseBody
	public JSONObject showPrivilegeYsInfo(@RequestBody Map<String, Object> map){
		return privilegeService.showPrivilegeYsInfo(map);
	}
	
	
}
