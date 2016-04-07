package com.lives.manage.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.manage.web.dto.MenuInfoDto;
import com.lives.manage.web.entity.MenuInfo;
import com.lives.manage.web.mapper.MenuInfoMapper;

/**
 * @author 陈端斌
 * @date 2015年6月8日 上午11:31:25
 * 
 */
@Service
public class MenuService {
	@Autowired
	private MenuInfoMapper menuInfoMapper;

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 上午11:31:25 新增菜单
	 */
	public String addMenu(MenuInfo menuInfo) {

		menuInfoMapper.insert(menuInfo);
		return null;

	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 上午11:31:25 修改菜单
	 */
	public String updateMenu(MenuInfo menuInfo) {

		menuInfoMapper.updateByPrimaryKeySelective(menuInfo);

		return null;

	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 上午11:31:25 菜单查询
	 */
	public List<MenuInfoDto> queryMenu(MenuInfoDto menuInfoDto) {

		List<MenuInfoDto> list = menuInfoMapper.getMenuinfo(menuInfoDto);

		return list;

	}

}
