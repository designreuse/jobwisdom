package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.entity.MenuIdQuote;

/**
 * 菜单
* @author 骆峰
* @date 2016年7月20日 下午4:02:20
 */
public interface MenuIdQuoteMapper {
    
    /**
     * 嵌套查询
    * @author 骆峰
    * @date 2016年7月25日 下午6:47:10
    * @param params params
    * @return       List<MenuIdQuote>
     */
    List<MenuIdQuote> selectMemberFirts(Map<String, Object> params);
    
    
    /**
     * 二级菜单
    * @author 骆峰
    * @date 2016年8月12日 下午6:28:19
    * @param params params
    * @return List
     */
    List<MenuIdQuote> selectMemberSecounds(Map<String, Object> params);
    
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
    
    
    /**
     * 查询该角色下的菜单
    * @author 骆峰
    * @date 2016年7月21日 上午11:25:51
    * @param mapUser mapUser
    * @return List
     */
    List<MenuIdQuote> selectByMemuRoles (Map<String, Object> mapUser);
    
    /**
     * 查询该角色下有几个菜单
    * @author 骆峰
    * @date 2016年7月21日 上午11:25:51
    * @param mapUser mapUser
    * @return List
     */
    List<MenuIdQuote> selectGroupByMemuRoles (Map<String, Object> mapUser);
    
}