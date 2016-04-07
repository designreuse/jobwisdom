package com.lives.platform.web.mapper;

import java.util.List;
import java.util.Map;

import com.lives.platform.web.entity.MemberDiscount;

public interface MemberDiscountMapper {
    int deleteByPrimaryKey(Integer memberDiscountId);

    int insert(MemberDiscount memberDiscount);

    int insertSelective(MemberDiscount memberDiscount);

    MemberDiscount selectByPrimaryKey(Integer memberDiscountId);

    int updateByPrimaryKeySelective(MemberDiscount memberDiscount);

    int updateByPrimaryKey(MemberDiscount memberDiscount);
    
    /**
     * 根据门店id和会员等级id查找列表
    * @author 洪秋霞
    * @date 2015年7月8日 上午10:10:16
    * @param paramMap
    * @return
     */
    List<MemberDiscount> selectByCardNoList(Map<String, Object> paramMap);
    
    /**
     * 批量删除
    * @author 洪秋霞
    * @date 2015年7月8日 上午10:10:48
    * @return
     */
    int deleteByDiscountIdList(List<Integer> memberDiscountIds);
    
    List<MemberDiscount> selectByProperty(MemberDiscount memberDiscount);
    
    int insertmemberDiscountBatch(List<MemberDiscount> memberDiscountList);
}