package com.zefun.web.mapper;

import com.zefun.web.entity.DeptObjective;

/**
 * 关于部门
* @author chendb
* @date 2015年9月8日 上午10:12:59
 */
public interface DeptObjectiveMapper {
    /**
     * 新增功能
    * @author chendb
    * @date 2015年9月8日 上午10:13:41
    * @param record 参数
    * @return int
     */
    int insert(DeptObjective record);

    /**
     * 修改功能
    * @author chendb
    * @date 2015年9月8日 上午10:13:41
    * @param record 参数
    * @return int
     */
    int updateByPrimaryKeySelective(DeptObjective record);
    
}