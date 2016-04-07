package com.lives.manage.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lives.manage.common.consts.Url;
import com.lives.manage.common.consts.View;
import com.lives.manage.common.util.DateUtils;
import com.lives.manage.common.util.StringUtils;
import com.lives.manage.web.dto.BaseDto;
import com.lives.manage.web.dto.UserInfoDto;
import com.lives.manage.web.entity.Role;
import com.lives.manage.web.entity.User;
import com.lives.manage.web.entity.UserAccount;
import com.lives.manage.web.service.RoleService;
import com.lives.manage.web.service.SystemAfficheService;
import com.lives.manage.web.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private SystemAfficheService systemAfficheService;
	@Autowired
	private RoleService roleService;

	private static final Logger logger = Logger.getLogger(UserController.class);


	@RequestMapping(value = Url.User.ADD)
	@ResponseBody
	public BaseDto adduser( HttpSession session,Integer roleId, String userName,String province,String city,String area,String realName
			,String companyName,String addr,String phone,String fixedNum,Integer money) {
		User user = new User();
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		try {
			// 账户表信息录入
			user.setUserName(userName);
			// 密码加密获取盐值
			// 默认生成密码密码123456
			String password = "123456";
			String hash = StringUtils.encryptPwd(password);
			password = hash.split(":")[0];
			String salt = hash.split(":")[1];
			user.setSalt(salt);
			user.setPassWord(password);
			user.setProvince(province);
			user.setCity(city);
			user.setArea(area);
			user.setAddr(addr);
			user.setCompanyName(companyName);
			user.setMoney(money);
			user.setPhone(phone);
			user.setFixedNum(fixedNum);
			user.setRoleId(roleId);
			user.setRealName(realName);
			user.setCreatorId(userInfoDto.getUserId());
			user.setCreateTime(DateUtils.getCurTime());
			int result=userService.addUser(user);
			if(result==1){
				return new BaseDto(-1, "后台帐号已经存在了 请重新输入！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("adduser error : ", e);
			return new BaseDto(-1, "数据插入失败");
		}
		return new BaseDto(0, "ok");
	}

	/** 用户修改 */
	@RequestMapping(value = Url.User.UPDATE)
	@ResponseBody
	public BaseDto updateUser(HttpSession session,Integer userId, Integer roleId, String userName,String province,String city,String area,String realName
			,String companyName,String addr,String phone,String fixedNum,Integer money) {
		User user = new User();
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		try {
			// 账户表信息录入
			user.setUserId(userId);
			user.setUserName(userName);
			user.setRoleId(roleId);
			user.setRealName(realName);
			user.setProvince(province);
			user.setCity(city);
			user.setArea(area);
			user.setPhone(phone);
			user.setAddr(addr);
			user.setMoney(money);
			user.setCompanyName(companyName);
			user.setUpdatorId(userInfoDto.getUserId());
			user.setUpdateTime(DateUtils.getCurTime());
			// 用户表信息录入
			int result=userService.updateUser(user);
			if(result==1){
				return new BaseDto(-1, "后台帐号已经存在了 请重新输入！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateUser error : ", e);
			return new BaseDto(-1, "updateUser error");
		}
		return new BaseDto(0, "ok");
	}

	/** 用户删除 */
	@ResponseBody
	@RequestMapping(value = Url.User.DELETE)
	public BaseDto deleteUser(HttpSession session,Integer userId) {
		User user = new User();
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		try {
			user.setUserId(userId);
			user.setIsDelete(1);
			user.setUpdatorId(userInfoDto.getUserId());
			user.setUpdateTime(DateUtils.getCurDate());
			userService.deleteUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("deleteUser error : ", e);
			return new BaseDto(-1, "deleteUser error");
		}
		return new BaseDto(0, "ok");
	}

	/** 用户查询*/
	@RequestMapping(value = Url.User.QUERY)
	@ResponseBody
	public JSONObject queryUser(@RequestBody Map<String ,Object> map,String type) {
		List<User> list =new ArrayList<User>();
		try {
			if(type!=""&&type!=null){
				String roleName="渠道商"; 
				//获取渠道商id
				int id=roleService.selectRoleId(roleName);
			   map.put("roleId", id);
			}
			
		  JSONObject jsonobject=new JSONObject();
		  list = userService.queryUser(map);
		  int count=userService.queryUserCount(map);
		  jsonobject.put("total", count);
		  jsonobject.put("rows", list);
		  return jsonobject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("queryUser error : ", e);
		}
		return null;
	}
	
	/** 用户修改密码 */
	@ResponseBody
	@RequestMapping(value = Url.User.UPDATEPASSWORD)
	public BaseDto updatepassword(HttpSession session,String oldpassword,String newpassword) {
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		try {
			String userName=userInfoDto.getUserName();
			int result=userService.updatepassword(userName, oldpassword, newpassword);
			if(result==1){
				return new BaseDto(1, "旧密码不正确！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updatepassword error : ", e);
			return new BaseDto(-1, "updatepassword error");
		}
		return new BaseDto(0, "ok");
	}
	/** 用户修改密码 */
	@ResponseBody
	@RequestMapping(value = Url.User.INITPASSWORD)
	public BaseDto initpassword(HttpSession session,Integer userId) {
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		try {
			userService.initpassword(userId);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("initpassword error : ", e);
			return new BaseDto(-1, "initpassword error");
		}
		return new BaseDto(0, "ok");
	}
	
	/** 帐号充值 */
	@ResponseBody
	@RequestMapping(value = Url.User.RECHARGE)
	public BaseDto recharge(HttpSession session,Integer userId,BigDecimal money) {
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		Map<String,Object>map=new HashMap<String, Object>();
		try {
			map.put("userId", userId);
			map.put("money", money);
			map.put("creatorId", userInfoDto.getUserId());
			userService.recharge(map);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("recharge error : ", e);
			return new BaseDto(-1, "recharge error");
		}
		return new BaseDto(0, "ok");
	}
	
	//-------------------------------------------------页面---------------------------------------------------------------------
	/**用户新增页面*/ 
//	@RequestMapping("view/add")
//	public String addView() {
//		return View.User.ADD;
//	}
	@RequestMapping ("view/add")
	public  ModelAndView systemAfficheAdd(HttpSession session){
		ModelAndView model=new ModelAndView();
		List<Role> list=new ArrayList<Role>();
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		try {
			list=userService.getAllRoleInfos();
			model.addObject("role", list);
			model.setViewName(View.User.ADD);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("queryUser error : ", e);
		}
		
		return model;	
		
	}
	//只查询渠道商页面
	@RequestMapping ("view/query")
	public  ModelAndView queryView(HttpSession session){
		ModelAndView model=new ModelAndView();
		List<Role> list=new ArrayList<Role>();
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		try {
			list=userService.getRoleInfosByUserId(userInfoDto.getUserId());
			model.addObject("role", list);
			model.setViewName(View.User.QUERY);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("queryUser error : ", e);
		}
		
		return model;	
		
	}
	//查询所有角色的页面
	@RequestMapping ("view/queryinfo")
	public  ModelAndView queryinfo(HttpSession session){
		ModelAndView model=new ModelAndView();
		List<Role> list=new ArrayList<Role>();
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		try {
		    list=userService.getAllRoleInfos();
			model.addObject("role", list);
			model.setViewName(View.User.QUERYINFO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("queryinfo error : ", e);
		}
		return model;	
	}
	//查询用户详情
	@RequestMapping ("view/userdetail")
	public  ModelAndView userdetail(HttpSession session){
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		ModelAndView model=new ModelAndView();
		try {
			Map<String ,Object> map=userService.getUserInfo(userInfoDto.getUserId());
			model.addObject("map",map);
			model.setViewName(View.User.USERDETAIL);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("userdetail error : ", e);
		}
		return model;	
	}
	//充值页面
		@RequestMapping ("view/recharge")
		public  ModelAndView recharge(HttpSession session){
			UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
			ModelAndView model=new ModelAndView();
			try {
				model.setViewName(View.User.RECHARGE);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("userdetail error : ", e);
			}
			return model;	
		}
	// 测试方法-----------------------------------------------------------------------------
	/**
	 * 根据用户输入的帐号密码，获取加盐后的密码
	 * 
	 * @param userName
	 *            用户账户
	 * @param pwd
	 *            用户密码
	 * @return
	 */
	public UserAccount saveUser(String userName, String pwd) {
		String hash = StringUtils.encryptPwd(pwd);
		String password = hash.split(":")[0];
		String salt = hash.split(":")[1];
		UserAccount userAccount = new UserAccount();
		userAccount.setUserName(userName);
		userAccount.setPassword(password);
		userAccount.setSalt(salt);
		return userAccount;
	}

	/**
	 * 检查用户账户密码
	 * 
	 * @param inputPwd
	 *            用户输入的密码
	 * @param userId
	 *            账户ID，用来查询用户密码
	 * @return
	 */
	public boolean checkLogin(String inputPwd, Integer userId) {
		// 检查用户密码
		UserAccount userAccount = new UserAccount();// 此处获取用户当前的账户信息
		if (!StringUtils.MD5(inputPwd + userAccount.getSalt()).equals(
				userAccount.getPassword())) {
			return false;
		}
		return true;
	}


}
