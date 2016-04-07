package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.ubox.UboxGoodsInfoDto;
import com.zefun.web.entity.UboxGoodsInfo;

/**
 * 友宝商品信息操作类
* @author 张进军
* @date Jan 28, 2016 5:51:56 PM
 */
public interface UboxGoodsInfoMapper {
    
    /**
     * 根据商品标识查询商品信息
    * @author 张进军
    * @date Jan 28, 2016 8:43:04 PM
    * @param goodsId    商品标识
    * @return   商品信息
     */
    UboxGoodsInfoDto selectGoodsInfoByGoodsId(String goodsId);
    
    /**
     * 查询所有友宝商品信息
    * @author 张进军
    * @date Jan 28, 2016 8:14:54 PM
    * @return   所有友宝商品信息
     */
    List<UboxGoodsInfoDto> selectAllGoodsList();
    
    /**
     * 根据友宝商品标识删除商品信息
    * @author 张进军
    * @date Jan 28, 2016 5:52:08 PM
    * @param uboxId 友宝商品标识
    * @return   0:失败，1:成功
     */
    int deleteByPrimaryKey(Integer uboxId);

    /**
     * 新增友宝商品信息
    * @author 张进军
    * @date Jan 28, 2016 5:52:29 PM
    * @param record 友宝商品信息
    * @return   0:失败，1:成功
     */
    int insert(UboxGoodsInfo record);

    /**
     * 根据友宝商品标识查询商品信息
    * @author 张进军
    * @date Jan 28, 2016 5:52:57 PM
    * @param uboxId 友宝商品标识
    * @return   商品信息
     */
    UboxGoodsInfo selectByPrimaryKey(Integer uboxId);

    /**
     * 修改商品信息
    * @author 张进军
    * @date Jan 28, 2016 5:53:00 PM
    * @param record 商品信息
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(UboxGoodsInfo record);
}