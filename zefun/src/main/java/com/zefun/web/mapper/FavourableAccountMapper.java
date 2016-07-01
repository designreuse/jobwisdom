package com.zefun.web.mapper;

import com.zefun.web.entity.FavourableAccount;
/**
 * 优惠活动
* @author 骆峰
* @date 2016年6月29日 下午12:13:48
 */
public interface FavourableAccountMapper {
    /**
     * 删除
    * @author 骆峰
    * @date 2016年6月29日 下午12:14:12
    * @param favourableId favourableId
    * @return int
     */
    int deleteByPrimaryKey(Integer favourableId);
    /** 
     *  新增
    * @author 骆峰
    * @date 2016年6月29日 下午12:14:18
    * @param record record
    * @return int
     */
    int insert(FavourableAccount record);

    /** 查询
     * 
    * @author 骆峰
    * @date 2016年6月29日 下午12:14:22
    * @param record record
    * @return int
     */
    int insertSelective(FavourableAccount record);

    /**
     *  查询
    * @author 骆峰
    * @date 2016年6月29日 下午12:14:31
    * @param favourableId favourableId
    * @return FavourableAccount
     */
    FavourableAccount selectByPrimaryKey(Integer favourableId);

    /**
     * 修改
    * @author 骆峰
    * @date 2016年6月29日 下午12:14:37
    * @param record record
    * @return int
     */
    int updateByPrimaryKeySelective(FavourableAccount record);

    /**
     *  修改
    * @author 骆峰
    * @date 2016年6月29日 下午12:14:41
    * @param record record
    * @return int
     */
    int updateByPrimaryKey(FavourableAccount record);
}