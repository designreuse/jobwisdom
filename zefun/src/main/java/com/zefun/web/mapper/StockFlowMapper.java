package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.Page;
import com.zefun.web.entity.StockFlow;

/**
 * 进销存流水搓澡
* @author 高国藩
* @date 2016年6月3日 上午9:56:13
 */
public interface StockFlowMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2016年6月3日 上午9:56:23
    * @param stockFlowId stockFlowId
    * @return            stockFlowId
     */
    int deleteByPrimaryKey(Integer stockFlowId);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年6月3日 上午9:56:23
    * @param record stockFlowId
    * @return            stockFlowId
     */
    int insert(StockFlow record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年6月3日 上午9:56:23
    * @param record stockFlowId
    * @return            stockFlowId
     */
    int insertSelective(StockFlow record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年6月3日 上午9:56:23
    * @param stockFlowId stockFlowId
    * @return            stockFlowId
     */
    StockFlow selectByPrimaryKey(Integer stockFlowId);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年6月3日 上午9:56:23
    * @param record stockFlowId
    * @return            stockFlowId
     */
    int updateByPrimaryKeySelective(StockFlow record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年6月3日 上午9:56:23
    * @param record stockFlowId
    * @return            stockFlowId
     */
    int updateByPrimaryKey(StockFlow record);
    /**
     * 删除
    * @author 高国藩
    * @date 2016年6月3日 上午9:56:23
    * @param query stockFlowId
    * @return            stockFlowId
     */
    List<StockFlow> selectByProperties(StockFlow query);
}