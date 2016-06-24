package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.CouponBaseDto;
import com.zefun.web.entity.CouponInfo;
import com.zefun.web.entity.Page;
/**
 * 优惠卷查询
* @author 骆峰
* @date 2016年6月21日 下午6:13:22
 */
public interface CouponInfoMapper {
    /**
     * 删除
    * @author 骆峰
    * @date 2016年6月21日 下午6:13:35
    * @param couponId couponId
    * @return int
     */
    int deleteByPrimaryKey(Integer couponId);
    
    /**
     * 新增
    * @author 骆峰
    * @date 2016年6月21日 下午6:14:00
    * @param record record
    * @return int
     */
    int insert(CouponInfo record);

    /**
     * 新增
    * @author 骆峰
    * @date 2016年6月21日 下午6:14:23
    * @param record record
    * @return int
     */
    int insertSelective(CouponInfo record);
    /**
     * 查询
    * @author 骆峰
    * @date 2016年6月21日 下午6:14:28
    * @param couponId couponId
    * @return CouponInFo
     */
    CouponInfo selectByPrimaryKey(Integer couponId);
    /**
     * 修改
    * @author 骆峰
    * @date 2016年6月21日 下午6:14:33
    * @param record record
    * @return int
     */
    int updateByPrimaryKeySelective(CouponInfo record);
    /**
     * 修改
    * @author 骆峰
    * @date 2016年6月21日 下午6:14:37
    * @param record record
    * @return int
     */
    int updateByPrimaryKey(CouponInfo record);
    
    /**
     * 查询该企业的所以优惠卷
    * @author 骆峰
    * @date 2016年6月21日 下午6:14:37
    * @param page page
    * @return CouponInfo
     */
    List<CouponInfo> selectByCoupon(Page<CouponInfo> page);
    
    
    
    /**
     * 查询该企业的所以优惠卷 分页
    * @author 骆峰
    * @date 2016年6月21日 下午6:14:37
    * @param page page
    * @return CouponInfo
     */
    List<CouponInfo> selectByStore(Page<CouponInfo> page);
    
    /**
     * 查询门店下所有未过期的优惠券(包括上架/未上架)
    * @author 张进军
    * @date Nov 25, 2015 7:46:50 PM
    * @param storeId    门店标识
    * @return   优惠券列表
     */
    List<CouponInfo> selectCouponListByStoreId(int storeId);
    
    
    /**
     * 根据优惠券标识查询优惠券信息
    * @author 张进军
    * @date Oct 22, 2015 4:24:47 PM
    * @param couponId   优惠券标识
    * @return   优惠券信息
     */
    CouponInfo selectNormalByCouponId(int couponId);
    
    /**
     * 根据会员标识查询优惠券信息
    * @author 张进军
    * @date Oct 21, 2015 6:18:03 PM
    * @param memberId   会员标识
    * @return   优惠券列表
     */
    List<CouponBaseDto> selectBaseByMemberId(int memberId);

    /**
     *  优惠卷上架
    * @author 骆峰
    * @date 2016年6月23日 下午3:09:34
    * @param couponId couponId
    * @return int
     */
    int updateBytype(CouponInfo couponId);
    
    /**
     * 优惠卷删除
    * @author 骆峰
    * @date 2016年6月23日 下午5:36:38
    * @param couponId couponId
    * @return int
     */
    int updateByDelete(CouponInfo couponId);
    
    
}