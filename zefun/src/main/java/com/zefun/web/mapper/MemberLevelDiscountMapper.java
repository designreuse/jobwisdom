package com.zefun.web.mapper;

import com.zefun.web.entity.MemberLevelDiscount;

/**
 * 会员等级操作类
* @author 张进军
* @date Aug 11, 2015 3:41:19 PM
 */
public interface MemberLevelDiscountMapper {
 

    /**
     * 插入会员等级信息
    * @author 老王
    * @date Aug 11, 2015 3:42:20 PM
    * @param record    等级信息
    * @return   0:失败，1:成功
     */
    int insert(MemberLevelDiscount record);

    /***
     * 根据会员等级标识查询等级信息
    * @author 老王
    * @date Aug 11, 2015 3:42:50 PM
    * @param levelId    等级标识
    * @return   会员等级信息
     */
    MemberLevelDiscount selectByPrimaryKey(Integer levelId);

    /**
     * 根据等级标识修改会员等级信息
    * @author 老王
    * @date Aug 11, 2015 3:43:09 PM
    * @param record 会员等级信息
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(MemberLevelDiscount record);    
}