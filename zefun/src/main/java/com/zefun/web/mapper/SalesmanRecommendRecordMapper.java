package com.zefun.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zefun.web.entity.SalesmanRecommendRecord;

/**
 * 业务员门店关联映射
 * @author lzc
 *
 */
public interface SalesmanRecommendRecordMapper {
	/**
	 * 新增业务员门店关联
	 * @param record 业务员门店关联实体
	 * @return  成功数目
	 */
    int insert(SalesmanRecommendRecord record);

    /**
     * 新增业务员门店关联
     * @param record  业务员门店关联实体
     * @return  成功数目
     */
    int insertSelective(SalesmanRecommendRecord record);
    
    /**
     * 查询该业务员下客户总数
     * @param salesmanId  业务员id
     * @return  该业务员下客户总数
     */
    int selectCountBySalesmanId(int salesmanId);
    
    /**
     * 业务员已完成客户总数
     * @param salesmanId  业务员id
     * @return  已完成客户总数
     */
    int selectCountStoreCompleted(@Param(value="salesmanId")Integer salesmanId);
    
    /**
     * 业务员正在洽谈中(进行中)客户总数
     * @param salesmanId  业务员id
     * @return  正在洽谈中(进行中)客户总数
     */
    int selectCountStoreProcessing(@Param(value="salesmanId")Integer salesmanId);
    
    /**
     * 查询业务员推荐客户关系集
     * @param salesmanId  业务员id
     * @return  业务员推荐客户关系集
     */
    List<SalesmanRecommendRecord> selectSalesmanRecommendRecordBySalesmanId(@Param(value="salesmanId")int salesmanId);
    
}