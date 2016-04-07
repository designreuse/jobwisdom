package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.CouponBaseDto;
import com.zefun.web.dto.CouponInfoDto;
import com.zefun.web.entity.CouponInfo;

/**
 * 优惠券处理接口
* @author 高国藩
* @date 2015年9月14日 下午2:02:56
 */
public interface CouponInfoMapper {
    /**
     * 根据主键删除
    * @author 高国藩
    * @date 2015年9月14日 下午2:03:13
    * @param couponId 优惠券id
    * @return 影响函数
     */
    int deleteByPrimaryKey(Integer couponId);
    /**
     * 插入
    * @author 高国藩
    * @date 2015年9月14日 下午2:03:13
    * @param record 优惠券
    * @return 影响函数
     */
    int insert(CouponInfo record);
    /**
     * 根據主鍵查詢
    * @author 高国藩
    * @date 2015年9月14日 下午2:03:13
    * @param couponId 优惠券id
    * @return 实体
     */
    CouponInfoDto selectByPrimaryKey(Integer couponId);
    /**
     * 根據实体进行更新
    * @author 高国藩
    * @date 2015年9月14日 下午2:03:13
    * @param record 优惠券
    * @return 影响行数
     */
    int updateByPrimaryKey(CouponInfo record);
    /**
     * 页面展示优惠券数据
    * @author 高国藩
    * @date 2015年9月14日 下午5:56:04
    * @param storeId 门店信息
    * @return 集合
     */
    List<CouponInfoDto> selectByStoreId(Integer storeId);
    
    /**
     * 根据会员标识查询优惠券信息
    * @author 张进军
    * @date Oct 21, 2015 6:18:03 PM
    * @param memberId   会员标识
    * @return   优惠券列表
     */
    List<CouponBaseDto> selectBaseByMemberId(int memberId);
    
    /**
     * 根据优惠券标识查询优惠券信息
    * @author 张进军
    * @date Oct 22, 2015 4:24:47 PM
    * @param couponId   优惠券标识
    * @return   优惠券信息
     */
    CouponInfo selectNormalByCouponId(int couponId);
    
    /**
     * 根据优惠券标识更新优惠券使用次数
     * @param map 优惠券标识、总共使用次数
     * @return 修改的记录数
     */
    int updateCouponHasUsedCount(Map<String, Integer> map);
    
    /**
     * 根据会员标识和优惠券标识查询优惠券信息
     * @param map map
     * @return 优惠券信息
     */
    List<CouponBaseDto> selectCouponByMemberIdAndCouponIds(Map<String, Object> map);
    
    /**
     * 查询门店下所有未过期的优惠券(包括上架/未上架)
    * @author 张进军
    * @date Nov 25, 2015 7:46:50 PM
    * @param storeId    门店标识
    * @return   优惠券列表
     */
    List<CouponInfo> selectCouponListByStoreId(int storeId);
    /**
     * 将过期的优惠券自动下架
    * @author 高国藩
    * @date 2016年1月17日 下午4:03:21
     */
    void updateByDate();
}