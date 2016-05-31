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
}