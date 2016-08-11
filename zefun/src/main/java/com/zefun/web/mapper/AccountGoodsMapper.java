package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.AccountGoods;

/**
 * 企业商品信息
* @author 高国藩
* @date 2016年5月30日 上午10:45:08
 */
public interface AccountGoodsMapper {
    
    /**
     * 删除商品
    * @author 高国藩
    * @date 2016年5月30日 上午10:46:13
    * @param goodsId  goodsId
    * @return         i
     */
    int deleteByPrimaryKey(Integer goodsId);
    /**
     * 删除商品
    * @author 高国藩
    * @date 2016年5月30日 上午10:46:13
    * @param record  goodsId
    * @return         i
     */
    int insert(AccountGoods record);
    /**
     * 删除商品
    * @author 高国藩
    * @date 2016年5月30日 上午10:46:13
    * @param record  goodsId
    * @return         i
     */
    int insertSelective(AccountGoods record);
    /**
     * 删除商品
    * @author 高国藩
    * @date 2016年5月30日 上午10:46:13
    * @param goodsId  goodsId
    * @return         i
     */
    AccountGoods selectByPrimaryKey(Integer goodsId);
    /**
     * 删除商品
    * @author 高国藩
    * @date 2016年5月30日 上午10:46:13
    * @param record  goodsId
    * @return         i
     */
    int updateByPrimaryKeySelective(AccountGoods record);
    /**
     * 删除商品
    * @author 高国藩
    * @date 2016年5月30日 上午10:46:13
    * @param record  goodsId
    * @return         i
     */
    int updateByPrimaryKey(AccountGoods record);
    /**
     * 删除商品
    * @author 高国藩
    * @date 2016年5月30日 上午10:46:13
    * @param accountGoods  storeAccount
    * @return         i
     */
    List<AccountGoods> selectByProperties(AccountGoods accountGoods);
    
    /**
     * 查询是否存在满足条件的商品信息
    * @author 高国藩
    * @date 2016年7月27日 下午5:40:03
    * @param goodsInfo goodsInfo
    * @return    List<AccountGoods>
     */
    List<AccountGoods> selectHasGoodsInfo(AccountGoods goodsInfo);
    
    /**
     * 根据供应商查询满足条件的商品
    * @author 高国藩
    * @date 2016年8月9日 下午3:10:27
    * @param supplierId  供应商标示
    * @return            商品集合
     */
    List<AccountGoods> selectAccountGoodsInfoBySupplierId(Integer supplierId);
}