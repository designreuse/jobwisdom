package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.DebtFlow;
import com.zefun.web.entity.Page;

/**
 * 挂账流水表
* @author 高国藩
* @date 2015年12月28日 下午8:04:17
 */
public interface DebtFlowMapper {
    
    /**
     * 删除
    * @author 高国藩
    * @date 2015年12月28日 下午8:04:27
    * @param debtId  主键
    * @return        影响行数
     */
    int deleteByPrimaryKey(Integer debtId);
    /**
     * 新增
    * @author 高国藩
    * @date 2015年12月28日 下午8:04:27
    * @param record  主键
    * @return        影响行数
     */
    int insert(DebtFlow record);
    /**
     * 新增
    * @author 高国藩
    * @date 2015年12月28日 下午8:04:27
    * @param record  主键
    * @return        影响行数
     */
    int insertSelective(DebtFlow record);
    /**
     * 查询
    * @author 高国藩
    * @date 2015年12月28日 下午8:04:27
    * @param debtId  主键
    * @return        影响行数
     */
    DebtFlow selectByPrimaryKey(Integer debtId);
    /**
     * 更新
    * @author 高国藩
    * @date 2015年12月28日 下午8:04:27
    * @param record  主键
    * @return        影响行数
     */
    int updateByPrimaryKeySelective(DebtFlow record);
    /**
     * 更新
    * @author 高国藩
    * @date 2015年12月28日 下午8:04:27
    * @param record  主键
    * @return        影响行数
     */
    int updateByPrimaryKey(DebtFlow record);
    
    /**
     * 分页查询
    * @author 高国藩
    * @date 2015年12月28日 下午9:29:02
    * @param page  page
    * @return      结合
     */
    List<DebtFlow> selectFlowListByAccountIdPage(Page<DebtFlow> page);
    
    /**
     * 根据订单标识查询挂账记录
    * @author 王大爷
    * @date 2016年1月13日 下午8:07:54
    * @param orderId 订单标识
    * @return DebtFlow
     */
    DebtFlow selectByOrderId(Integer orderId);
}