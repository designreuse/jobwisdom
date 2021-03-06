package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.entity.GoodsDiscount;

/**
 * 商品会员折扣
* @author 洪秋霞
* @date 2015年8月11日 上午10:57:47
 */
public interface GoodsDiscountMapper {
    /**
     * 删除
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:58:12
    * @param discountId 折扣id
    * @return int
     */
    int deleteByPrimaryKey(Integer discountId);

    /**
     * 插入
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:58:29
    * @param goodsDiscount 商品会员折扣
    * @return int
     */
    int insertSelective(GoodsDiscount goodsDiscount);

    /**
     * 查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:58:43
    * @param discountId 折扣id
    * @return GoodsDiscount
     */
    GoodsDiscount selectByPrimaryKey(Integer discountId);

    /**
     * 更新
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:59:01
    * @param goodsDiscount 商品会员折扣
    * @return int
     */
    int updateByPrimaryKeySelective(GoodsDiscount goodsDiscount);
    
    /**
     * 动态查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:59:17
    * @param goodsDiscount 商品会员折扣
    * @return List<GoodsDiscount>
     */
    List<GoodsDiscount> selectByProperty(GoodsDiscount goodsDiscount);
    
    /**
     * 批量插入
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:59:34
    * @param goodsDiscountList 商品会员折扣List
    * @return int
     */
    int insertGoodsDiscountList(List<GoodsDiscount> goodsDiscountList);
    
    /**
     * 根据商品标识、会员等级标识查询会员特定价格
     * @param map	商品标识、会员等级标识
     * @return	会员特定价格
     */	
    GoodsDiscount selectDiscountGoodsIdAndLevelId(Map<String, Integer> map);

}