package com.zefun.web.mapper;

import com.zefun.web.entity.DeptObjective;

/**
 * 部门提成
* @author 骆峰
* @date 2016年7月1日 下午6:24:26
 */
public interface DeptObjectiveMapper {
    /**
     * 
    * @author 骆峰
    * @date 2016年7月1日 下午6:24:38
    * @param objectiveId objectiveId
    * @return int
     */
    int deleteByPrimaryKey(Integer objectiveId);

    /**
     * 
    * @author 骆峰
    * @date 2016年7月1日 下午6:24:43
    * @param record record
    * @return int
     */
    int insert(DeptObjective record);

    /**
     * 
    * @author 骆峰
    * @date 2016年7月1日 下午6:24:46
    * @param record record
    * @return int
     */
    int insertSelective(DeptObjective record);

    /**
     * 
    * @author 骆峰
    * @date 2016年7月1日 下午6:24:50
    * @param objectiveId objectiveId
    * @return DeptObjective
     */
    DeptObjective selectByPrimaryKey(Integer objectiveId);

    /**
     * 
    * @author 骆峰
    * @date 2016年7月1日 下午6:24:53
    * @param record record
    * @return int
     */
    int updateByPrimaryKeySelective(DeptObjective record);

    /**
     *  
    * @author 骆峰
    * @date 2016年7月1日  下午6:24:56
    * @param record record
    * @return int
     */
    int updateByPrimaryKey(DeptObjective record);
}