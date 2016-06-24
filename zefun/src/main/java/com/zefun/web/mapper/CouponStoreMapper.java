package com.zefun.web.mapper;

import com.zefun.web.entity.CouponStoreKey;
/**
 * 
* @author 骆峰
* @date 2016年6月23日 下午2:25:10
 */
public interface CouponStoreMapper {
    /**
     * 删除
    * @author 骆峰
    * @date 2016年6月23日 下午2:25:18
    * @param key key
    * @return int
     */
    int deleteByPrimaryKey(CouponStoreKey key);

    /**
     * 新增
    * @author 骆峰
    * @date 2016年6月23日 下午2:25:31
    * @param record record
    * @return int
     */
    int insert(CouponStoreKey record);

    /**
     * 
    * @author 骆峰
    * @date 2016年6月23日 下午2:25:47
    * @param record record
    * @return int
     */
    int insertSelective(CouponStoreKey record);
}