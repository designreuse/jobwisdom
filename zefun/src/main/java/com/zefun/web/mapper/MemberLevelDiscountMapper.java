package com.zefun.web.mapper;

import java.util.Map;

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
    
    /**
     * 门店查询对应会员卡信息
    * @author 老王
    * @date 2016年5月31日 下午11:49:47 
    * @param map 参数
    * @return List<MemberLevelDiscount>
     */
    MemberLevelDiscount selectByStoreLevel(Map<String, Integer> map);
    
    /**
     * 删除会员信息
    * @author 骆峰
    * @date 2016年6月28日 下午12:10:35
    * @param record record
    * @return int
     */
    int updateDeleteByLevelId(MemberLevelDiscount record);
}