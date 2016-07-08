package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.ActivityInfoDto;
import com.zefun.web.entity.ActivityInfo;
import com.zefun.web.entity.Page;

/**
 *  企业活动
* @author 骆峰
* @date 2016年7月5日 下午8:46:22
 */
public interface ActivityInfoMapper {
    /**
     *  删除
    * @author 骆峰
    * @date 2016年7月5日 下午8:46:16
    * @param activityId activityId
    * @return int
     */
    int deleteByPrimaryKey(Integer activityId);

    /**
     *  新增
    * @author 骆峰
    * @date 2016年7月5日 下午8:46:26
    * @param record record
    * @return int
     */
    int insert(ActivityInfo record);

    /** 
     *  新增
    * @author 骆峰
    * @date 2016年7月5日 下午8:46:31
    * @param record record
    * @return int
     */
    int insertSelective(ActivityInfo record);

    /**
     *  查询
    * @author 骆峰
    * @date 2016年7月5日 下午8:46:36
    * @param activityId activityId
    * @return ActivityInfo
     */
    ActivityInfo selectByPrimaryKey(Integer activityId);

    /**
     * 修改
    * @author 骆峰
    * @date 2016年7月5日 下午8:46:40
    * @param record record
    * @return int
     */
    int updateByPrimaryKeySelective(ActivityInfo record);

    /**
     *  修改
    * @author 骆峰
    * @date 2016年7月5日 下午8:46:45
    * @param record record
    * @return int
     */
    int updateByPrimaryKey(ActivityInfo record);
    
    /**
     *  根据门店查询该门店下的活动
    * @author 骆峰
    * @date 2016年7月5日 下午8:46:48
    * @param page page
    * @return ActivityInfo
     */
    List<ActivityInfoDto> selectByStore(Page<ActivityInfoDto> page);
    
    /**
     * 删除该活动
    * @author 骆峰
    * @date 2016年7月6日 下午4:33:13
    * @param activityId activityId
    * @return int
     */
    int updateIsDeleted(Integer activityId);
}