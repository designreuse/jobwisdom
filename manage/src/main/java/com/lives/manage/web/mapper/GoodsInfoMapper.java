package com.lives.manage.web.mapper;

import java.util.List;
import java.util.Map;

import com.lives.manage.web.entity.GoodsInfo;

public interface GoodsInfoMapper {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);
    
    List<GoodsInfo> queryGoodsInfo(Map<String, Object> map);
    
    int goodsInfoCount(Map<String, Object> map);
    
    /**获取客户销售的项目的相关统计*/
    List<Map<String,Object>> getCustSale(Map<String,Object> map);
    int getCustSaleCount(Map<String,Object> map);
    
    /** 订单录入（主订单）*/
    int addCustOrder(Map<String,Object> map);
    /** 订单详细录入订单*/
    int addCustSaledetail(Map<String,Object> map);
    
    /** 获取项目名称*/
    List<Map<String,Object>> getGoodsName(Map<String,Object> map);
    
    /** 获取项目单价*/
    Map<String,Object> getGoodsPrice(Map<String,Object> map);
    
    
    /** 客户版本类型获取项目名称*/
    List<Map<String,Object>> queryGoodsName(Map<String,Object> map);
    
    int updateGoodsStock(Map<String,Object> map);
    
    
    
 
}