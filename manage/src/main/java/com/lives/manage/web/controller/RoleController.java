package com.lives.manage.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lives.manage.common.consts.Url;
import com.lives.manage.common.consts.View;
import com.lives.manage.common.util.DateUtils;
import com.lives.manage.web.dto.BaseDto;
import com.lives.manage.web.entity.RoleInfo;
import com.lives.manage.web.service.RoleService;

/**
 * @author 陈端斌
 * @date 2015年6月8日 上午11:31:25
 * 
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	public RoleService roleService;
	/**
	 * 新增用户账户，同时增加用户信息
	 * 
	 * @param roleId
	 *            角色标识
	 * @param customerId
	 *            客户标识
	 * @param roleName
	 *            角色名称
	 * @param orderNo
	 *            排序
	 * @param createTime
	 *            创建时间
	 * @param createrId
	 *            创建人
	 * @return
	 */
	private static final Logger logger = Logger.getLogger(MenuController.class);

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 上午11:31:25
	 * 
	 */
	@RequestMapping(value = Url.Role.ADD)
	@ResponseBody
	public BaseDto addMenu(Integer customerId, String roleName, Integer orderNo) {
		RoleInfo roleInfo = new RoleInfo();

		try {
			roleInfo.setCustomerId(customerId);
			roleInfo.setRoleName(roleName);
			roleInfo.setOrderNo(orderNo);
			roleInfo.setCreateTime(DateUtils.getCurTime());

			roleService.addRole(roleInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addMenu error : ", e);
			return new BaseDto(-1, "addMenu error");
		}
		return new BaseDto(0, "ok");

	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 上午11:31:25
	 * 
	 */
	@RequestMapping(value = Url.Role.UPDATE)
	@ResponseBody
	public BaseDto updateMenu(Integer roleId, Integer customerId,
			String roleName, Integer orderNo) {
		RoleInfo roleInfo = new RoleInfo();

		try {
			roleInfo.setRoleId(roleId);
			roleInfo.setCustomerId(customerId);
			roleInfo.setRoleName(roleName);
			roleInfo.setOrderNo(orderNo);
			roleInfo.setCreateTime(DateUtils.getCurTime());
			roleService.updateRole(roleInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" updateMenu error : ", e);
			return new BaseDto(-1, "updateMenu error");
		}
		return new BaseDto(0, "ok");
	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 上午11:31:25
	 * 
	 */
	@RequestMapping(value = Url.Role.QUERY)
	@ResponseBody
	public BaseDto queryMenu(String roleName) {
		RoleInfo roleInfo = new RoleInfo();
		List<RoleInfo> list = null;
		try {
			roleService.queryRole(roleInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" queryMenu error : ", e);
			return new BaseDto(-1, "queryMenu error");
		}
		return new BaseDto(0, list);
	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 上午11:31:25
	 * 
	 */
	@RequestMapping(value = Url.Role.ROLEMENU)
	@ResponseBody
	public BaseDto roleMenu(Integer roleId, String menuIdList) {
		try {
			roleService.addRoleMenu(roleId, menuIdList);
		} catch (Exception e) {
			logger.error(" roleMenu error : ", e);
			return new BaseDto(-1, "roleMenu error");
		}
		return new BaseDto(0, "ok");

	}

	// -------------------------------------------------------------测试方法-------------------------------------------------------------------------------------
	// 测试新增页面
	@RequestMapping("view/add")
	public String addView() {
		return View.Role.ADD;
	}

}
