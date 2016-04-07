package com.lives.manage.web.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lives.manage.common.consts.View;
import com.lives.manage.common.util.DateUtils;
import com.lives.manage.web.dto.BaseDto;
import com.lives.manage.web.dto.UserInfoDto;
import com.lives.manage.web.entity.Role;
import com.lives.manage.web.entity.SystemAffiche;
import com.lives.manage.web.service.PrivilegeService;
import com.lives.manage.web.service.SystemAfficheService;

@Controller
@RequestMapping (value = "/SystemAffiche")
public class SystemAfficheController {


	private static final Logger logger = Logger.getLogger(MenuController.class);
	@Autowired
	private SystemAfficheService systemAfficheService;
	@Autowired
	private PrivilegeService privilegeService;
	
	
	/**
	 * 添加公告
	 * @param afficheTitle
	 * @param afficheContent
	 * @param afficheRole
	 * @param creatorId
	 * @param createTime
	 * @param afficheClasses
	 * @param afficheStatus
	 * @param updateTime
	 * @param afficheTime
	 * @return
	 */
	@RequestMapping (value = "addaffiche")
	@ResponseBody

	public BaseDto addSystemAffiche(HttpSession session,String afficheTitle,String afficheContent,String afficheTime,String afficheClasses,String role){
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		try {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("afficheTitle", afficheTitle);
			map.put("afficheContent", afficheContent);
			map.put("creatorId", userInfoDto.getUserId());
			map.put("createTime", DateUtils.getCurTime());
			map.put("afficheClasses", afficheClasses);
			map.put("afficheTime", afficheTime);
			map.put("afficheRole", role);
			
			systemAfficheService.addSystemAfficheService(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" addSystemAffiche error : ", e);
			return new BaseDto(-1, "addSystemAffiche error");
		}
		return new BaseDto(0, "ok");
	}
	
	/**
	 * 删除公告
	 * @param afficheId
	 * @return
	 */
	@RequestMapping (value = "deleteaffiche")
	@ResponseBody
	public int deleteSystemAfficheController(Integer afficheId){
		  return systemAfficheService.deleteSystemAfficheService(afficheId);
		
	}
	
	@RequestMapping (value = "updateaffiche")
	@ResponseBody
	public BaseDto updateSystemAffiche(HttpSession session,Integer afficheId,String afficheTitle,
			String afficheContent,String afficheTime,String afficheClasses,String role){
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		try {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("afficheTitle", afficheTitle);
			map.put("afficheContent", afficheContent);
			map.put("creatorId", userInfoDto.getUserId());
			map.put("updateTime", DateUtils.getCurTime());
			map.put("afficheClasses", afficheClasses);
			map.put("afficheTime", afficheTime);
			map.put("afficheRole", role);
			map.put("afficheId", afficheId);
			systemAfficheService.updateSystemAfficheService(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateSystemAffiche error : ", e);
			return new BaseDto(-1, "updateSystemAffiche error");
		}

		return new BaseDto(0, "ok");
		
	}
	
	@RequestMapping (value = "updateState")
	@ResponseBody
	public BaseDto updateState(Integer afficheId,Integer afficheStatus){
		
		try {
			Map<String ,Object> map=new HashMap<String, Object>();
			map.put("afficheId", afficheId);
			map.put("afficheStatus", afficheStatus);

			systemAfficheService.updatestate(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateState error : ", e);
			return new BaseDto(-1, "updateState error");
		}

		return new BaseDto(0, "ok");	
	}
	
	@RequestMapping (value = "selectAfficheInfo")
	@ResponseBody
	public BaseDto selectAfficheInfo(Integer afficheId){
		Map<String ,Object> map=new HashMap<String, Object>();
		Map<String ,Object> map1=new HashMap<String, Object>();
		try {
			map.put("afficheId", afficheId);
			map1=systemAfficheService.selectAfficheInfo(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateState error : ", e);
			return new BaseDto(-1, "updateState error");
		}

		return new BaseDto(0, map1);	
	}
	
	/**
	 * 查询列表
	 * @param map1
	 * @param offset
	 * @param limit
	 * @param order
	 * @param sort
	 * @return
	 */
	@RequestMapping (value = "selectTable")
	@ResponseBody
	public JSONObject selectTable(@RequestBody Map<String ,Object> map1,Integer offset, Integer limit, @RequestParam(required = false)String order,  @RequestParam(required = false)String sort) {
		List<SystemAffiche> List = null;
		try {
			List = systemAfficheService.selectTable(map1);
			int count  = systemAfficheService.selectdetalis(map1);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("total", count);
			jsonObject.put("rows", List);
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("queryCustClueInfo error : ", e);
		}
		return null;
	}
	
	/**
	 * 查询公告详情
	 * @param afficheId
	 * @return
	 */
	@RequestMapping (value = "selectDetails")
	@ResponseBody
	public SystemAffiche selectDetails(Integer afficheId){
			SystemAffiche affiche = new SystemAffiche();
			affiche = systemAfficheService.selectSystemAfficheService(afficheId);
			return affiche;
	}
	
	/**
	 * 添加公告页面
	 * @return
	 */
	@RequestMapping (value = "view/affiche/addAffiche")
	public  ModelAndView systemAfficheAdd(HttpSession session){
		ModelAndView model=new ModelAndView();
		List<Role> list=new ArrayList<Role>();
		try {
			
			list=systemAfficheService.selectAllRole();
			model.addObject("role", list);
			model.setViewName("affiche/addAffiche");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return model;	
		
	}
	/**
	 * 修改公告页面
	 * @return
	 */
	@RequestMapping (value = "view/affiche/updateAffiche")
	public  ModelAndView updateAffiche(Integer afficheId){
		ModelAndView model=new ModelAndView();
		Map<String,Object>map=new HashMap<String, Object>();
		Map<String,Object>map1=new HashMap<String, Object>();
		List<Role> list=new ArrayList<Role>();
		try {
			map.put("afficheId", afficheId);
			map1=systemAfficheService.selectAfficheInfo(map);
			list=systemAfficheService.selectAllRole();
			model.addObject("role", list);
			model.addObject("info", map1);
			model.setViewName("affiche/updateAffiche");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return model;
		
	}

	/**
	 * 查询公告列表页面
	 * @return
	 */
	@RequestMapping("view/selectTable")
	public ModelAndView systemAfficheView() {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(View.SystemAffiche.QUERY);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("systemAfficheView error : ", e);
		}
		return model;
	}
	
	/**
	 * 公告详情页面
	 * @return
	 */
	@RequestMapping (value ="view/affiche/selectDetails")
	public ModelAndView systemAfficheDetails(Integer afficheId){
		ModelAndView model=new ModelAndView();
		Map<String,Object>map=new HashMap<String, Object>();
		Map<String,Object>map1=new HashMap<String, Object>();
		try {
			map.put("afficheId", afficheId);
			map1=systemAfficheService.selectAfficheInfo(map);
			String afficheTitle=map1.get("afficheTitle").toString();
			String afficheClasses=map1.get("afficheClasses").toString();
			String afficheTime=map1.get("afficheTime").toString();
			String afficheContent=map1.get("afficheContent").toString();
			model.addObject("afficheTitle", afficheTitle);
			model.addObject("afficheClasses", afficheClasses);
			model.addObject("afficheTime", afficheTime);
			model.addObject("afficheContent", afficheContent);
			model.setViewName("affiche/selectDetails");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return model;
	}
}
