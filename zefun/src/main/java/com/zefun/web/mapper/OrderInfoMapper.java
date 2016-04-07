package com.zefun.web.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zefun.web.dto.BusinessAnalysisDto;
import com.zefun.web.dto.BusinessSummaryRelativeAmt;
import com.zefun.web.dto.CustomerAnalysisDto;
import com.zefun.web.dto.DayBookDto;
import com.zefun.web.dto.DayBookQueryDto;
import com.zefun.web.dto.DayBookStatDto;
import com.zefun.web.dto.DeptCashIncome;
import com.zefun.web.dto.DeptLaborSummaryDto;
import com.zefun.web.dto.DeptSummaryDto;
import com.zefun.web.dto.MemberOrderDto;
import com.zefun.web.dto.OrderInfoBaseDto;
import com.zefun.web.dto.OrderPaymentDto;
import com.zefun.web.dto.SelfCashierOrderDto;
import com.zefun.web.dto.SelfCashierStatDto;
import com.zefun.web.dto.StoreSummaryDto;
import com.zefun.web.dto.SummaryResultDto;
import com.zefun.web.dto.TrendDeptDataDto;
/*import com.zefun.web.dto.OrderInfoBaseDto;*/
import com.zefun.web.entity.OrderInfo;
import com.zefun.web.entity.Page;

/**
 * 订单明细
 *
 * @author 王大爷
 * @date 2015年8月19日 下午3:03:57
 */
public interface OrderInfoMapper {

    /**
     * 删除订单
     *
     * @author 王大爷
     * @date 2015年8月19日 下午2:59:23
     * @param orderId
     *            订单id
     * @return 是否成功
     */
    int deleteByPrimaryKey(Integer orderId);

    /**
     * 保存订单
     *
     * @author 王大爷
     * @date 2015年8月19日 下午3:00:19
     * @param record
     *            订单
     * @return 是否成功
     */
    int insert(OrderInfo record);

    /**
     * 根据订单id查询订单
     *
     * @author 王大爷
     * @date 2015年8月19日 下午3:01:06
     * @param orderId
     *            订单id
     * @return 订单明细
     */
    OrderInfo selectByPrimaryKey(Integer orderId);

    /**
     * 修改订单信息
     *
     * @author 王大爷
     * @date 2015年8月19日 下午3:01:11
     * @param record
     *            订单
     * @return 是否成功
     */
    int updateByPrimaryKey(OrderInfo record);

    /**
     * 根据条件查询订单
     *
     * @author 王大爷
     * @date 2015年10月8日 下午4:50:16
     * @param map
     *            条件
     * @return List<OrderInfo>
     */
    List<OrderInfo> selectByCondition(Map<String, Object> map);

    /**
     * 根据订单id查询订单信息，包括其下订单明细、服务步骤明细
     *
     * @author 张进军
     * @date Oct 13, 2015 7:08:26 PM
     * @param orderId
     *            订单编号
     * @return 订单基础传输对象
     */
    OrderInfoBaseDto selectOrderBaseByOrderId(int orderId);

    /**
     * 根据门店标识查询订单编号集合
     *
     * @author 张进军
     * @date Oct 13, 2015 7:27:33 PM
     * @param storeId
     *            门店标识
     * @return 订单编号集合
     */
    List<Integer> selectOrderIdByStoreIdNotOver(int storeId);

    /**
     * 根据门店标识查询当天已完成的订单
     *
     * @author 王大爷
     * @date 2015年11月11日 下午4:44:55
     * @param storeId
     *            门店标识
     * @return 订单编号集合
     */
    List<Integer> selectOrderIdByStoreIdIsOver(int storeId);

    /**
     * 根据门店标识查询历史订单
     *
     * @author 王大爷
     * @date 2015年11月11日 下午5:44:15
     * @param storeId
     *            门店标识
     * @return List<Integer>
     */
    List<Integer> selectOrderIdByStoreIdHistory(int storeId);

    /**
     * 根据员工标识查询进行中订单编号集合
     *
     * @author 张进军
     * @date Oct 13, 2015 7:27:33 PM
     * @param employeeId
     *            员工标识
     * @return 订单编号集合
     */
    List<Integer> selectOrderIdByEmployeeIdNotOver(int employeeId);

    /**
     * 根据员工标识查询已完成订单编号集合
     *
     * @author 王大爷
     * @date 2015年11月13日 下午5:07:14
     * @param employeeId
     *            员工标识
     * @return 订单编号集合
     */
    List<Integer> selectOrderIdByEmployeeIdIsOver(int employeeId);

    /**
     * 根据员工标识查询历史订单
     *
     * @author 王大爷
     * @date 2015年11月13日 下午5:07:54
     * @param employeeId
     *            员工标识
     * @return int employ
     */
    List<Integer> selectOrderIdByEmployeeIdHistory(int employeeId);

    /**
     * 根据门店标识查询当日已完成订单统计信息(订单总数量、总金额)
     *
     * @author 张进军
     * @date Oct 13, 2015 9:51:48 PM
     * @param storeId
     *            门店标识
     * @return 订单统计情况
     */
    Map<String, Object> selectOrderStatisticsByStoreId(int storeId);

    /**
     * 根据员工标识查询当日已完成订单统计信息(订单总数量、总金额)
     *
     * @author 张进军
     * @date Oct 13, 2015 9:56:01 PM
     * @param employeeId
     *            员工标识
     * @return 订单统计情况
     */
    Map<String, Object> selectOrderStatisticsByEmployeeId(int employeeId);

    /**
     * 自助收银订单列表查询
     *
     * @author 张进军
     * @date Oct 22, 2015 10:26:48 AM
     * @param storeId
     *            门店标识
     * @return 订单列表
     */
    List<SelfCashierOrderDto> selectUnfinishedOrderInfo(int storeId);

    /**
     * 自助收银订单详情查询
     *
     * @author 张进军
     * @date Oct 22, 2015 10:26:48 AM
     * @param orderId
     *            订单标识
     * @return 订单列表
     */
    SelfCashierOrderDto selectUnfinishedOrderDetail(int orderId);

    /**
     * 根据会员标识查询订单列表
     *
     * @author 张进军
     * @date Oct 24, 2015 2:08:47 PM
     * @param memberId
     *            会员标识
     * @return 订单列表
     */
    List<MemberOrderDto> selectOrderListByMemberId(int memberId);

    /**
     * 根据订单明细标识查询订单信息
     *
     * @author 王大爷
     * @date 2015年10月23日 下午6:38:37
     * @param detailId
     *            明细标识
     * @return OrderInfo
     */
    OrderInfo selectByDetailId(Integer detailId);

    /**
     * 更新订单价格信息
     *
     * @author 王大爷
     * @date 2015年11月6日 上午11:37:21
     * @param orderId
     *            订单标识
     * @return 是否成功
     */
    Integer updateTotalPrice(Integer orderId);

    /**
     * 更新订单，绑定会员信息
     *
     * @param map
     *            map
     * @return 更新结果
     */
    int updateOrderMemberInfo(Map<String, Integer> map);

    /**
     * 查询自助收银统计数据
     *
     * @param map
     *            门店标识、开始时间、结束时间
     * @return 统计数据
     */
    SelfCashierStatDto selectCashierStatInfo(Map<String, Object> map);

    /**
     * 通过查询条件查询订单流水记录数
     *
     * @param queryDto
     *            查询条件
     * @return 记录数
     */
    DayBookDto selectDayBookInfoCount(DayBookQueryDto queryDto);

    /**
     * 按明细类型统计金额
     *
     * @author 张进军
     * @date Nov 30, 2015 2:16:27 AM
     * @param queryDto
     *            查询条件
     * @return 统计结果
     */
    Map<String, Object> selectDetailCountForType(DayBookQueryDto queryDto);

    /**
     * 统计流水的统计信息
     *
     * @param queryDto
     *            查询条件
     * @return 流水统计信息
     */
    DayBookStatDto selectDayBookStat(DayBookQueryDto queryDto);

    /**
     * 查询该会员未结账的订单
     *
     * @author 王大爷
     * @date 2015年11月9日 下午5:38:02
     * @param memberId
     *            会员标识
     * @return 订单集合
     */
    List<OrderInfo> selectIsNotOver(Integer memberId);

    /**
     * 修改订单评价信息
     *
     * @author 张进军
     * @date Nov 9, 2015 4:49:31 PM
     * @param orderInfo
     *            订单信息
     * @return 0:失败，1:成功
     */
    int updateEvaluateByOrderId(OrderInfo orderInfo);

    /**
     * 根据订单标识查询订单支付明细信息
     *
     * @author 张进军
     * @date Nov 9, 2015 11:17:47 PM
     * @param orderId
     *            订单标识
     * @return 订单支付明细
     */
    OrderPaymentDto selectOrderPaymentByOrderId(int orderId);

    /**
     * 修改订单分享状态
     *
     * @author 张进军
     * @date Nov 14, 2015 1:58:51 PM
     * @param orderId
     *            订单标识
     * @return 0:失败，1:成功
     */
    int updateOrderShare(int orderId);

    /**
     * 汇总签单总金额
     *
     * @author 王大爷
     * @date 2015年12月3日 下午3:25:52
     * @param detailId
     *            明细标识
     * @return 是否成功
     */
    Integer updateFreeAmount(Integer detailId);

    /**
     * 删除门店所有订单数据(清除测试数据)
     *
     * @author 张进军
     * @date Dec 25, 2015 8:15:13 PM
     * @param storeId
     *            门店标识
     * @return 删除数量
     */
    int deleteByStoreId(int storeId);

    /**
     * 查询该会员的账单,如果存在,会员不可删除
     *
     * @author 高国藩
     * @date 2015年12月25日 下午8:12:32
     * @param memberId
     *            会员标示
     * @return 账单集合
     */
    List<OrderInfo> selectIsCanDeletedMember(Integer memberId);

    /**
     * 根据时间区间查询营业汇总数据
     *
     * @author gebing
     * @date 2016年1月4日
     * @param params 开始时间
     * @return 汇总数据
     */
    List<StoreSummaryDto> selectSummary(Map<String, Object> params);
       
    /**
     * @param dto 相关查询条件 比如起始时间 终止时间 查询日期类型
     * @return 当前门店的汇总数据(当前日期的服务人数 劳动业绩总收入)
     */
    DeptLaborSummaryDto getCurrSummaryData(SummaryResultDto dto);
    
    /**
     * 根据时间查询该门店现金支付方式
    * @author 王大爷
    * @date 2016年1月15日 下午2:17:45
    * @param map 参数
    * @return Map<String, Object>
     */
    Map<String, Object> selectTatalCashAmount(Map<String, Object> map);

    /**
     * 查找客流量
    * @author 高国藩
    * @date 2016年1月20日 下午4:48:01
    * @param paramFlow    参数
    * @return             数据
     */
    CustomerAnalysisDto selectCustomerFlow(Map<String, Object> paramFlow);

    /**
     * 查询天中时间段中客情数据
    * @author 高国藩
    * @date 2016年1月20日 下午9:26:12
    * @param paramFlow     参数
    * @return              客情数据
     */
    CustomerAnalysisDto selectCustomerFlux(Map<String, Object> paramFlow);

    /**
     * 查询指定时间段内的客流量
    * @author 高国藩
    * @date 2016年1月21日 下午8:26:57
    * @param paramFlow      参数
    * @return               流量数据
     */
    Integer selectCustomerFlol(Map<String, Object> paramFlow);

    /**
     * 查询莫一时间段内莫以会员卡的消费(客单价)
    * @author 高国藩
    * @date 2016年1月22日 下午5:43:30
    * @param paramFlow     参数
    * @return              结果集合
     */
    List<BigDecimal> selectCustomerAnalysisLevel(Map<String, Object> paramFlow);

    /**
     * 查询莫一段时间内散客对项目的消费(客单价)
    * @author 高国藩
    * @date 2016年1月22日 下午8:08:31
    * @param paramFlow 查询条件
    * @return  返回数据
     */
    List<BigDecimal> selectCustomerAnalysisSank(Map<String, Object> paramFlow);
    
    /**
     * 查询莫一段时间内项目消费中数据
    * @author 高国藩
    * @date 2016年1月22日 下午8:08:31
    * @param paramFlow 查询条件
    * @return  返回数据
     */
    BigDecimal selectCustomerAnalysisProject(Map<String, Object> paramFlow);

    /**
     * 查询一定时间段内的营业分析数据
    * @author 高国藩
    * @date 2016年1月26日 下午3:45:07
    * @param params    查新条件
    * @return          查询结果
     */
    BusinessAnalysisDto selectBusinessInclude(Map<String, Object> params);

    /**
     * 查询一定时间段内的营业分析数据
    * @author 高国藩
    * @date 2016年1月26日 下午3:45:07
    * @param params    查新条件
    * @return          查询结果
     */
    BusinessAnalysisDto selectBusinessConsumption(Map<String, Object> params);

    /**
     * 查询一定时间段内的门店支出明细
    * @author 高国藩
    * @date 2016年1月26日 下午4:55:29
    * @param params    查新条件
    * @return          查询结果
     */
    BusinessAnalysisDto selectStoreExpenses(Map<String, Object> params);

    /**
     * 查询一定时间段内的门店支出项目明细
    * @author 高国藩
    * @date 2016年1月26日 下午5:27:57
    * @param params    查新条件
    * @return          查询结果
     */
    List<BusinessAnalysisDto> selectStoreExpensesDetails(Map<String, Object> params);

    /**
     * 查询一定时间段内的门店指定比例
    * @author 高国藩
    * @date 2016年1月27日 下午3:24:49
    * @param params    查新条件 
    * @return          查询结果
     */
    BusinessAnalysisDto selectStoreAssign(Map<String, Object> params);

    /**
     * 查询一定时间段内的门店单量
    * @author 高国藩
    * @date 2016年1月27日 下午3:29:03
    * @param params    查新条件
    * @return          查询结果
     */
    Integer selectOrderCount(Map<String, Object> params);
    
    /**
    * @author 乐建建
    * @date 2016年1月30日 下午7:20:53
    * @param dto 参数条件 包括门店id 起始时间 终止时间
    * @return 当前门店的部门汇总数据 
    */
    List<DeptSummaryDto> getDeptSummary(SummaryResultDto dto);
    
    /**
     * 获取特定时间最大订单编号+1
     * @param map map
     * @return String
     */
    String getOpenOrderDateCode(Map<String, Object> map);
    
    /**
     * 分页查询订单
     * @param page 页面参数
     * @return List<DayBookDto>
     */
    List<DayBookDto> selectByPage(Page<DayBookDto> page);    
    
    /**
    * @author 乐建建
    * @date 2016年2月19日 上午11:30:10
    * @param dto 封装查询参数条件
    * @return 营业汇总的营业扣减数据
    */
    List<BusinessSummaryRelativeAmt> getBusinessExpense(SummaryResultDto dto);
    
    /**
    * @author 乐建建
    * @date 2016年2月19日 上午11:36:36
    * @param dto 封装查询参数条件
    * @return 营业汇总的营业扣减数据
    */
    List<BusinessSummaryRelativeAmt> getBusinessIncomes(SummaryResultDto dto);
    
    /**
    * @author 乐建建
    * @date 2016年2月19日 上午11:40:28
    * @param dto 封装查询参数条件
    * @return 营业汇总的刷卡数据
    */
    List<BusinessSummaryRelativeAmt> getBusinessCardConsumed(SummaryResultDto dto);
    
    /**
    * @author 乐建建
    * @date 2016年2月21日 下午1:36:57
    * @param dto 封装查询参数条件
    * @return 现金收入汇总数据
    */
    List<DeptCashIncome> getDeptCashSummary(SummaryResultDto dto);
    
    /**
    * @author 乐建建
    * @date 2016年2月21日 下午2:17:32
    * @param dto 封装查询参数条件
    * @return 现金收入趋势汇总数据
    */
    List<TrendDeptDataDto> getDeptCashTrendData(SummaryResultDto dto);

    /**
    * 查询项目商品的成本价
    * @author 高国藩
    * @date 2016年2月18日 下午5:15:31
    * @param params   参数
    * @return         成本价
     */
    BigDecimal selectProjectAndGoodsCost(Map<String, Object> params);
    
    /**
    * @author 乐建建
    * @date 2016年2月19日 上午11:40:28
    * @param dto 封装查询参数条件
    * @return 营业汇总的刷卡数据
    */
    List<BusinessSummaryRelativeAmt> getBusinessComboDiscount(SummaryResultDto dto);
    
}
