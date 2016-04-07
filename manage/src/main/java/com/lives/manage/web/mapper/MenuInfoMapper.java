package com.lives.manage.web.mapper;

import java.util.List;

import com.lives.manage.web.dto.MenuInfoDto;
import com.lives.manage.web.entity.MenuInfo;

public interface MenuInfoMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(MenuInfo record);

    int insertSelective(MenuInfo record);

    MenuInfo selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(MenuInfo record);

    int updateByPrimaryKey(MenuInfo record);
    
    List<MenuInfoDto> getMenuinfo(MenuInfoDto menuInfoDto);
}