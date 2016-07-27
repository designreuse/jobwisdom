package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.entity.AccountRoleInfo;


/**
 * 
* @author 骆峰
* @date 2016年7月20日 下午8:04:05
 */
public interface AccountRoleInfoMapper {
    
    
    /**
     * 
    * @author 骆峰
    * @date 2016年7月20日 下午8:06:56
    * @param accountRoleId accountRoleId
    * @return AccountRoleInfo
     */
    AccountRoleInfo selectByPrimaryKey(Integer accountRoleId);
    
    /**
     * 
    * @author 骆峰
    * @date 2016年7月26日 下午8:06:19
    * @param mapMenu  mapMenumapMenu
    * @return AccountRoleInfo AccountRoleInfo
     */
    List<AccountRoleInfo> selectByAccountRoleName(Map<String, Object> mapMenu);
    
    /**
     *   查询该权限下的菜单
    * @author 骆峰
    * @date 2016年7月21日 上午11:06:34
    * @param mapMenu mapMenu
    * @return AccountRoleInfo
     */
    List<AccountRoleInfo> selectAccountMenuByRoleId(Map<String, Object> mapMenu);
    /**
     * 
    * @author 骆峰
    * @date 2016年7月20日 下午8:04:08
    * @param accountRoleId accountRoleId
    * @return int
     */
    int deleteByPrimaryKey(Integer accountRoleId);



    /**
     * 
    * @author 骆峰
    * @date 2016年7月20日 下午8:04:15
    * @param accountRoleInfo accountRoleInfo
    * @return int
     */
    int insertSelective(AccountRoleInfo accountRoleInfo);


    /**
     * 
    * @author 骆峰
    * @date 2016年7月20日 下午8:04:27
    * @param record record
    * @return int
     */
    int updateByPrimaryKeySelective(AccountRoleInfo record);


    /**
     * 
    * @author 骆峰
    * @date 2016年7月20日 下午8:04:32
    * @param record record
    * @return int
     */
    int updateByPrimaryKey(AccountRoleInfo record);
    

}