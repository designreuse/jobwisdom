package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.DeptInfoDto;
import com.zefun.web.dto.GoodsCategoryDto;
import com.zefun.web.entity.GoodsCategory;

/**
 * 商品类别
* @author 洪秋霞
* @date 2015年8月11日 上午10:55:31
 */
public interface GoodsCategoryMapper {
    /**
     * 删除
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:55:42
    * @param categoryId 类别id
    * @return int
     */
    int deleteByPrimaryKey(Integer categoryId);

    /**
     * 插入
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:55:53
    * @param goodsCategory 商品类别
    * @return int
     */
    int insertSelective(GoodsCategory goodsCategory);

    /**
     * 查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:56:13
    * @param categoryId 类别id
    * @return GoodsCategory
     */
    GoodsCategory selectByPrimaryKey(Integer categoryId);

    /**
     * 更新
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:56:55
    * @param goodsCategory 商品类别
    * @return int
     */
    int updateByPrimaryKeySelective(GoodsCategory goodsCategory);
    
    /**
     * 根据门店id查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:57:07
    * @param storeId 门店id
    * @return List<GoodsCategory>
     */
    List<GoodsCategory> selectByStoreId(Integer storeId);
    
    
    /**
     *  门店下已完成的商品大项
    * @author 骆峰
    * @date 2016年8月10日 下午8:58:36
    * @param storeId storeId
    * @return List<GoodsCategory>
     */
    List<GoodsCategory> selectBygoodsInfo(Integer storeId);
    
    
    /**
     *   门店下有无该名称
    * @author 骆峰
    * @date 2016年8月15日 上午10:13:17
    * @param map map
    * @return List<GoodsCategory>
     */
    List<GoodsCategory> selectByName(Map<String, Object> map);
    
    /**
     * 获取部门，商品类别和商品列表
    * @author 洪秋霞
    * @date 2015年9月16日 下午2:57:17
    * @param storeId 门店id
    * @return List<DeptInfoDto>
     */
    List<DeptInfoDto> getDetpInfoByGoods(Integer storeId);
    
    /**
     * 根据门店标识、部门标识查询类别下存在商品的类别
    * @author 王大爷
    * @date 2015年9月18日 上午11:09:04
    * @param goodsCategory 商品类别
    * @return List<GoodsCategory>
     */
    List<GoodsCategoryDto> selectByGoodsCategory(GoodsCategory goodsCategory);
    
    /**
     * 根据门店标识，部门标识查询商品
     * @param map 参数
     * @return List<GoodsCategory>
     */
    List<GoodsCategory> selectAllCategoryByStoreIdOrDeptId(Map<String, Integer> map);
}