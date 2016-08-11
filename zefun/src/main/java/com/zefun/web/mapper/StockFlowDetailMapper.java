package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.StockFlowDetail;

/**
 * 库存流水明细 
* @author 高国藩
* @date 2016年8月10日 上午10:42:02
 */
public interface StockFlowDetailMapper {
    
    /**
     * 批量新增库存流水明细
    * @author 高国藩
    * @date 2016年8月10日 上午10:36:10
    * @param stockFlowDetails stockFlowDetails
    * @return                 状态
     */
    int insertStockFlowDetails(List<StockFlowDetail> stockFlowDetails);

    /**
     * 查询明细
    * @author 高国藩
    * @date 2016年8月10日 下午2:15:51
    * @param stockFlowDetail stockFlowDetail
    * @return                List<StockFlowDetail>
     */
    List<StockFlowDetail> selectByProperites(StockFlowDetail stockFlowDetail);
}