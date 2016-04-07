package com.ucs.security.server;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ucs.security.face.SecurityTestInterface;

@Controller
public class SecurityTest {
	@Resource
	private SecurityTestInterface dao;
	
	@RequestMapping(value="/jsp/getinput")//查看最近收入
	@ResponseBody
	public boolean getinput(HttpServletRequest req,HttpServletRequest res){
		boolean b=dao.getinput();
		return b;
	}
	
	
	@RequestMapping(value="/jsp/geoutput")//查看最近支出
	@ResponseBody
	public boolean geoutput(HttpServletRequest req,HttpServletRequest res){
		boolean b=dao.geoutput();
		return b;
	}
	
	@RequestMapping(value="/jsp/addreport_admin")//添加报表管理员
	@ResponseBody
	public boolean addreport_admin(HttpServletRequest req,HttpServletRequest res){
		boolean b=dao.addreport_admin();
		return b;
	}
	
	@RequestMapping(value="/jsp/deletereport_admin")//删除报表管理员
	@ResponseBody
	public boolean deletereport_admin(HttpServletRequest req,HttpServletRequest res){
		boolean b=dao.deletereport_admin();
		return b;
	}
	
	@RequestMapping(value="/jsp/user")//普通用户登录
	public ModelAndView user_login(HttpServletRequest req,HttpServletRequest res){
		dao.user_login();
		return new ModelAndView("user");
	}
	
}
