package com.zefun.web.mapper;

import com.zefun.web.entity.ShopAccountState;

/**
* @author 乐建建
* @date 2016年3月7日 上午11:33:25 
*/
public interface ShopAccountStateMapper {
    
    /**
     * 删除门店对账
    * @param stateId 订单明细id
    * @return 是否成功
     */
    int deleteByPrimaryKey(Integer stateId);

    /**
    * 插入一条门店对账明细
    * @param record 门店对账明细记录
    * @return 是否成功
     */
    int insert(ShopAccountState record);
    
    /**
    * 修改对账明细
    * @param record 对账明细
    * @return 是否成功
     */
    int updateByPrimaryKey(ShopAccountState record);
    
    
    /**
    * @author 乐建建
    * @date 2016年3月7日 上午11:41:12
    * @param params 发起对账门店id 和 被对账门店id
    * @return 相关信息
    */
    ShopAccountState selectAccountState(ShopAccountState params);
    
    /**
    * @author 乐建建
    * @date 2016年3月7日 下午1:58:45
    * @param record 门店对账记录
    * @return 成功插入记录条数
    */
    int insertIfNotExist(ShopAccountState record);
}
