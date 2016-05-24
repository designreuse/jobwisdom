package com.zefun.web.mapper;

import java.util.Map;

import com.zefun.web.entity.WechatGroupInfo;

/**
 * 微信分组信息操作类
* @author 张进军
* @date Jan 26, 2016 6:00:29 PM
 */
public interface WechatGroupInfoMapper {
    
    /**
     * 根据门店标识、分组类型查询微信分组ID
    * @author 张进军
    * @date Jan 26, 2016 8:46:36 PM
    * @param map    门店标识、分组类型
    * @return   微信分组ID
     */
    Integer selectGroupIdByStoreIdAndGroupType(Map<String, Object> map);
    
    /**
     * 根据分组标识删除分组信息
    * @author 张进军
    * @date Jan 26, 2016 6:00:38 PM
    * @param id 分组标识
    * @return  0:失败，1:成功
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增分组信息
    * @author 张进军
    * @date Jan 26, 2016 6:01:04 PM
    * @param record 分组信息
    * @return   0:失败，1:成功
     */
    int insert(WechatGroupInfo record);

    /**
     * 根据分组标识查询分组信息
    * @author 张进军
    * @date Jan 26, 2016 6:01:34 PM
    * @param id 分组标识
    * @return   分组信息
     */
    WechatGroupInfo selectByPrimaryKey(Integer id);

    /**
     *  修改分组信息
    * @author 张进军
    * @date Jan 26, 2016 6:01:55 PM
    * @param record 分组信息
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(WechatGroupInfo record);
}