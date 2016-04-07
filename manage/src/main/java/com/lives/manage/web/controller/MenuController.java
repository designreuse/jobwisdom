package com.lives.manage.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lives.manage.common.consts.Url;
import com.lives.manage.common.consts.View;
import com.lives.manage.web.dto.BaseDto;
import com.lives.manage.web.dto.MenuInfoDto;
import com.lives.manage.web.entity.MenuInfo;
import com.lives.manage.web.service.MenuService;

/**
 * @author 陈端斌
 * @date 2015年6月8日 上午11:31:25
 * 
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private MenuService menuService;
	/**
	 * @param menuId
	 *            菜单id
	 * @param parentId
	 *            父级id
	 * @param menuName
	 *            菜单名称
	 * @param linkUrl
	 *            连接地址
	 * @param ordreNo
	 *            排序
	 * @return
	 */
	private static final Logger logger = Logger.getLogger(MenuController.class);

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 上午11:31:25
	 * 
	 */
	@RequestMapping(value = Url.Menu.ADD)
	@ResponseBody
	public BaseDto addMenu(Integer parentId, String menuName, String linkUrl,
			Integer ordreNo) {
		MenuInfo menuInfo = new MenuInfo();

		try {
			menuInfo.setLinkUrl(linkUrl);
			menuInfo.setMenuName(menuName);
			menuInfo.setParentId(parentId);
			menuInfo.setOrdreNo(ordreNo);
			menuService.addMenu(menuInfo);
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
	@RequestMapping(value = Url.Menu.UPDATE)
	@ResponseBody
	public BaseDto updateMenu(Integer menuId, Integer parentId,
			String menuName, String linkUrl, Integer ordreNo) {
		MenuInfo menuInfo = new MenuInfo();
		try {
			menuInfo.setLinkUrl(linkUrl);
			menuInfo.setMenuId(menuId);
			menuInfo.setMenuName(menuName);
			menuInfo.setParentId(parentId);
			menuInfo.setOrdreNo(ordreNo);
			menuService.updateMenu(menuInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateMenu error : ", e);
			return new BaseDto(-1, "updateMenu error");
		}
		return new BaseDto(0, "ok");

	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 上午11:31:25
	 * 
	 */
	@RequestMapping(value = Url.Menu.QUERY)
	@ResponseBody
	public BaseDto queryMenu(String menuName, String linkUrl) {
		MenuInfoDto menuInfoDto = new MenuInfoDto();
		List<MenuInfoDto> queryMenu = null;
		try {
			menuInfoDto.setLinkUrl(linkUrl);
			menuInfoDto.setMenuName(menuName);
			queryMenu = menuService.queryMenu(menuInfoDto);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("queryMenu error : ", e);
			return new BaseDto(-1, "queryMenu error");
		}
		return new BaseDto(0, queryMenu);

	}

	// -------------------------------------------------------------测试方法-------------------------------------------------------------------------------------
	// 测试新增页面
	@RequestMapping("view/add")
	public String addView() {
		return View.Menu.ADD;
	}

}
