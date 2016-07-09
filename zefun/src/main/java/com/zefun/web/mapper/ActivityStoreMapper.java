package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.ActivityStore;
import com.zefun.web.entity.Page;

/**
 * 门店活动
* @author 骆峰
* @date 2016年7月7日 下午6:16:55
 */
public interface ActivityStoreMapper {
    /**
     * 
    * @author 骆峰
    * @date 2016年7月7日 下午6:17:11
    * @param activityStoreId activityStoreId
    * @return int
     */
    int deleteByPrimaryKey(Integer activityStoreId);

    /**
     * 
    * @author 骆峰
    * @date 2016年7月7日 下午6:17:16
    * @param record record
    * @return int
     */
    int insert(ActivityStore record);

    /**
     * 
    * @author 骆峰
    * @date 2016年7月7日 下午6:17:20
    * @param record record
    * @return int
     */
    int insertSelective(ActivityStore record);

    /**
     * 
    * @author 骆峰
    * @date 2016年7月7日 下午6:17:26
    * @param activityStoreId activityStoreId
    * @return ActivityStore
     */
    ActivityStore selectByPrimaryKey(Integer activityStoreId);

    /**
     * 
    * @author 骆峰
    * @date 2016年7月7日 下午6:17:31
    * @param record record
    * @return int
     */
    int updateByPrimaryKeySelective(ActivityStore record);

    /**
     * 
    * @author 骆峰
    * @date 2016年7月7日 下午6:17:35
    * @param record record
    * @return int
     */
    int updateByPrimaryKey(ActivityStore record);
    
    /**
     *  查询该企业下的所有活动
    * @author 骆峰
    * @date 2016年7月7日 下午6:17:39
    * @param page page
    * @return List
     */
    List<ActivityStore> selectByStoreAcount(Page<ActivityStore> page);
    
    
}