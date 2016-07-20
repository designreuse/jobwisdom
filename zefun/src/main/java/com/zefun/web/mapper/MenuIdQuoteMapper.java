package com.zefun.web.mapper;

import com.zefun.web.entity.MenuIdQuote;

/**
 * 菜单
* @author 骆峰
* @date 2016年7月20日 下午4:02:20
 */
public interface MenuIdQuoteMapper {
    /**
     * 新增
    * @author 骆峰
    * @date 2016年7月20日 下午4:02:33
    * @param record record
    * @return int
     */
    int insert(MenuIdQuote record);

    /**
     * 新增
    * @author 骆峰
    * @date 2016年7月20日 下午4:02:44
    * @param record record
    * @return int
     */
    int insertSelective(MenuIdQuote record);
}