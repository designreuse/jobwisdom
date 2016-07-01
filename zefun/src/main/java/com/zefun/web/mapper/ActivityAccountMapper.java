package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.ActivityAccount;
import com.zefun.web.entity.CouponInfo;
import com.zefun.web.entity.Page;

/**
 * 门店活动
* @author 骆峰
* @date 2016年6月29日 下午2:02:11
 */
public interface ActivityAccountMapper {
    /**
     *  删除
    * @author 骆峰
    * @date 2016年6月29日 下午2:02:33
    * @param activityId activityId
    * @return int
     */
    int deleteByPrimaryKey(Integer activityId);

    /**
     * 新增
    * @author 骆峰
    * @date 2016年6月29日 下午2:02:37
    * @param record record
    * @return int
     */
    int insert(ActivityAccount record);

    /** 新增
     * 
    * @author 骆峰
    * @date 2016年6月29日 下午2:02:42
    * @param record record
    * @return int
     */
    int insertSelective(ActivityAccount record);

    /**
     *   查询
    * @author 骆峰
    * @date 2016年6月29日 下午2:02:46
    * @param activityId activityId
    * @return ActivityAccount
     */
    ActivityAccount selectByPrimaryKey(Integer activityId);

    /** 
     * 修改
     * 
    * @author 骆峰
    * @date 2016年6月29日 下午2:02:50
    * @param record record
    * @return int
     */
    int updateByPrimaryKeySelective(ActivityAccount record);

    /**
     *  修改
    * @author 骆峰
    * @date 2016年6月29日 下午2:02:53
    * @param record record
    * @return int
     */
    int updateByPrimaryKey(ActivityAccount record);
    
    /**
     * 根据企业代号查询活动
    * @author 骆峰
    * @date 2016年6月29日 下午5:11:38
    * @param page page
    * @return ActivityAccount
     */
    List<ActivityAccount> selectByActivity(Page<ActivityAccount> page);
}