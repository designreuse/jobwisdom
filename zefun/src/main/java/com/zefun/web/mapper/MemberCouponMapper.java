package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.MemberCoupon;

/**
 * 优惠券会员信息
* @author 高国藩
* @date 2016年6月28日 上午11:49:42
 */
public interface MemberCouponMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2016年6月28日 上午11:49:50
    * @param mId mId
    * @return    mId
     */
    int deleteByPrimaryKey(Integer mId);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年6月28日 上午11:49:50
    * @param record mId
    * @return    mId
     */
    int insert(MemberCoupon record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年6月28日 上午11:49:50
    * @param record mId
    * @return    mId
     */
    int insertSelective(MemberCoupon record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年6月28日 上午11:49:50
    * @param mId mId
    * @return    mId
     */
    MemberCoupon selectByPrimaryKey(Integer mId);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年6月28日 上午11:49:50
    * @param record mId
    * @return    mId
     */
    int updateByPrimaryKeySelective(MemberCoupon record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年6月28日 上午11:49:50
    * @param record mId
    * @return    mId
     */
    int updateByPrimaryKey(MemberCoupon record);

    /**
     * 批量新增
    * @author 高国藩
    * @date 2016年6月28日 上午11:50:21
    * @param memberCoupons  memberCoupons
     */
    void insertList(List<MemberCoupon> memberCoupons);
}