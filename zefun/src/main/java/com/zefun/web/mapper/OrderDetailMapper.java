package com.zefun.web.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zefun.web.dto.CardConsumedTrendData;
import com.zefun.web.dto.CashierCommissionDto;
import com.zefun.web.dto.DeptSummaryByDayDto;
import com.zefun.web.dto.Member2Info;
import com.zefun.web.dto.MemberAccountInfo;
import com.zefun.web.dto.MemberCardConsumedInfo;
import com.zefun.web.dto.MemberLevelInfo;
import com.zefun.web.dto.OrderDetailDto;
import com.zefun.web.dto.OrderProjectPageDto;
import com.zefun.web.dto.SumPriceDto;
import com.zefun.web.dto.SummaryResultDto;
import com.zefun.web.dto.TrendDeptDataDto;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.Page;

/**
 * 订单明细
* @author 王大爷
* @date 2015年8月19日 下午3:03:57
 */
public interface OrderDetailMapper {
    
    /**
     * 删除订单明细
    * @author 王大爷
    * @date 2015年8月19日 下午2:59:23
    * @param detailId 订单明细id
    * @return 是否成功
     */
    int deleteByPrimaryKey(Integer detailId);

    /**
     * 保存订单明细
    * @author 王大爷
    * @date 2015年8月19日 下午3:00:19
    * @param record 订单明细
    * @return 是否成功
     */
    int insert(OrderDetail record);

    /**
     * 保存订单明细
    * @author 王大爷
    * @date 2015年8月19日 下午3:01:02
    * @param record 订单明细
    * @return 是否成功
     */
    int insertSelective(OrderDetail record);

    /**
     * 根据订单明细id查询订单明细
    * @author 王大爷
    * @date 2015年8月19日 下午3:01:06
    * @param detailId 订单明细id
    * @return 订单明细
     */
    OrderDetail selectByPrimaryKey(Integer detailId);

    /**
     * 修改订单明细信息
    * @author 王大爷
    * @date 2015年8月19日 下午3:01:14
    * @param record 订单明细
    * @return 是否成功
     */
    int updateByPrimaryKey(OrderDetail record);
    
    /**
     * 业绩总排行
    * @author 王大爷
    * @date 2015年8月19日 下午4:28:32
    * @param map 员工list
    * @return 业绩总排行
     */
    List<SumPriceDto> selectBySumPriceEmployees(Map<String, Object> map);
    
    /**
     * 算平均（除商品）
    * @author 王大爷
    * @date 2015年8月19日 下午8:46:55
    * @param map 开始时间--结束时间
    * @return 算平均（除商品）
     */
    SumPriceDto selectByMEmployees(Map<String, Object> map);
    
    /**
     * 算平均商品
    * @author 王大爷
    * @date 2015年8月19日 下午8:46:55
    * @param map 开始时间--结束时间
    * @return 算平均商品
     */
    SumPriceDto selectByCEmployees(Map<String, Object> map);
    /**
     * 单个员工的劳动业绩、客单价、指定客比率
    * @author 王大爷
    * @date 2015年8月19日 下午8:24:15
    * @param map 员工id
    * @return 单个员工的劳动业绩、客单价、指定客比率
     */
    SumPriceDto selectByPPriceEmployees(Map<String, Object> map);
    
    /**
     * 单个员工的商品
    * @author 王大爷
    * @date 2015年8月19日 下午8:24:15
    * @param map 员工id
    * @return 单个员工的商品
     */
    SumPriceDto selectByCPriceEmployees(Map<String, Object> map);
    
    /**
     * 
    * @author 王大爷
    * @date 2015年9月22日 下午7:50:46
    * @param orderId 订单标识
    * @return List<OrderDetail>
     */
    List<OrderDetail> selectOrderDetail(Integer orderId);
    
    /**
     * 查询商品30天销售量
    * @author 洪秋霞
    * @date 2015年9月18日 上午10:50:46
    * @param orderDetail 订单详情
    * @return List<OrderDetailDto>
     */
    List<OrderDetailDto> selectByGoodsSale(OrderDetail orderDetail);
    
    /**
     * 修改订单明细服务时长
    * @author 王大爷
    * @date 2015年10月20日 下午4:56:16
    * @param shiftMahjongStepId 轮牌步骤标识
    * @return 是否成功
     */
    Integer updateServiceLength(Integer shiftMahjongStepId);
    
    /**
     * 根据明细标识查询明细及步骤
    * @author 王大爷
    * @date 2015年10月24日 上午11:28:48
    * @param detailId 明细标识
    * @return OrderDetailDto
     */
    OrderDetailDto selectByDetailBaseDto(Integer detailId);
    
    /**
     * 查询该明细对应的订单中存在的未完成的项目明细
    * @author 王大爷
    * @date 2015年10月26日 下午3:56:27
    * @param detailId 明细标识
    * @return List<OrderDetail>
     */
    List<OrderDetail> selectByNotOverOrderDetail(Integer detailId);
    
    /**
     * 查询订单中存在的未完成的项目明细
    * @author 王大爷
    * @date 2015年11月11日 下午9:19:59
    * @param orderId 订单明细标识
    * @return List<OrderDetail>
     */
    List<OrderDetail> selectByNotOverByOrderId(Integer orderId);
    
    /**
     * 
    * @author 王大爷
    * @date 2015年11月9日 下午6:40:46
    * @param orderId 订单标识
    * @return List<OrderDetail
     */
    List<OrderDetail> selectOrderId(Integer orderId);
    
    /**
     * 会员对应的项目消费
    * @author 王大爷
    * @date 2015年11月30日 下午3:43:15
    * @param page 参数
    * @return List<OrderProjectPageDto>
     */
    List<OrderProjectPageDto> selectByOrderProjectPage(Page<OrderProjectPageDto> page);
    
    /**
     * 会员对应的商品消费
    * @author 王大爷
    * @date 2015年11月30日 下午6:14:53
    * @param page 参数
    * @return List<OrderProjectPageDto>
     */
    List<OrderProjectPageDto> selectByOrderGoodsPage(Page<OrderProjectPageDto> page);
    
    /**
     * 根据明细标识查询套餐名称
    * @author 王大爷
    * @date 2015年11月30日 下午4:26:08
    * @param detailId 明细标识
    * @return 套餐名称
     */
    String selectComboNameByDetailId(Integer detailId);
    
    /**
     * 根据明细标识查询优惠卷名称
    * @author 王大爷
    * @date 2015年11月30日 下午4:42:30
    * @param detailId 明细标识
    * @return 优惠卷名称
     */
    Map<String, Object> selectCouponNameByDetailId(Integer detailId);
    
    /**
     * 根据项目查询出状态值为1,2的订单信息
    * @author 高国藩
    * @date 2015年12月9日 下午4:54:53
    * @param projectId     项目Id
    * @return              订单列表
     */
    List<OrderDetail> selectHasProjectAndStatus(Integer projectId);
    
    /**
     * 删除门店所有订单明细数据(清除测试数据)
    * @author 张进军
    * @date Dec 25, 2015 8:15:13 PM
    * @param storeId	门店标识
    * @return	删除数量
     */
    int deleteByStoreId(int storeId);
    
    /**
     * 根据订单标识查询修改明细标识
    * @author 王大爷
    * @date 2016年1月8日 下午5:22:07
    * @param orderId 订单标识
    * @return List<Integer>
     */
    List<Integer> selectUpdateByOrderId(Integer orderId);
    

    
    /**
     * 根据类型查询现金业绩
    * @author 王大爷
    * @date 2016年1月15日 下午6:11:03
    * @param map 参数
    * @return BigDecimal
     */
    BigDecimal selectTataiRealPriceByType(Map<String, Object> map);
    
    /**
     * 根据项目类别查询卡金或现金支付金额
    * @author 王大爷
    * @date 2016年1月19日 下午3:36:58
    * @param map 参数
    * @return BigDecimal
     */
    BigDecimal selectTataiAmountProject(Map<String, Object> map);
    
    /**
     * 查询大小项
    * @author 王大爷
    * @date 2016年1月19日 下午6:54:35
    * @param map 参数
    * @return BigDecimal
     */
    BigDecimal selectTataiProjectSizeType(Map<String, Object> map);
    
    /**
     * 查询卡金现金
    * @author 王大爷
    * @date 2016年1月19日 下午8:20:27
    * @param map 参数
    * @return BigDecimal
     */
    BigDecimal selectTataiProjectAmountType(Map<String, Object> map);
    
    /**
     * 查询项目总数
    * @author 王大爷
    * @date 2016年1月19日 下午9:10:36
    * @param map 参数
    * @return BigDecimal
     */
    BigDecimal selectTataiProject(Map<String, Object> map);
    
    /**
     * 查询使用套餐抵扣的项目业绩总值
     * @param map 参数
     * @return BigDecimal
     */
    BigDecimal selectTataiProjectUseCombo(Map<String, Object> map);
    
    /**
     * 根据项目类型查询类型对应项目使用套餐抵扣金额
     * @param map 参数
     * @return BigDecimal
     */
    BigDecimal selectTataiAmountUseComboProject(Map<String, Object> map);
    
    /**
     * 查询使用套餐抵扣的总额
     * @param map   查询参数
     * @return  抵扣总额
     */
    BigDecimal selectTataiUseComboProjectAmountType(Map<String, Object> map);
    
    /**
     * 大小项套餐抵扣
     * @param map 参数
     * @return BigDecimal
     */
    BigDecimal selectTataiUseComboProjectSizeType(Map<String, Object> map);
    
    /**
     * 查询购买套餐中商品价格
     * @param map 参数
     * @return BigDecimal
     */
    BigDecimal selectTataiProjectUseComboGoods(Map<String, Object> map);
    
    /**
     * 查询根据类别查询商品销量
     * @param map   查询参数
     * @return  商品销量
     */
    BigDecimal selectTataiAmountUseComboGoods(Map<String, Object> map);
    
    /**
     * 商品数量
     * @param map 参数
     * @return Integer
     */
    Integer selectBuyGoodsComboTime(Map<String, Object> map);
    
    /**
    * @author 乐建建
    * @date 2016年1月19日 下午6:57:43
    * @param dto 根据条件查询趋势数据
    * @return  List<DeptSummaryByDayDto>
    */
    List<TrendDeptDataDto> getTrendData(SummaryResultDto dto);
    
    /**
     * 查询卡金
    * @author 王大爷
    * @date 2016年1月20日 下午3:25:10
    * @param map 参数
    * @return BigDecimal
     */
    BigDecimal selectTataiCardByType(Map<String, Object> map);
    
    /**
     * 查询区域时间内购买套餐次数
    * @author 王大爷
    * @date 2016年1月21日 下午5:36:50
    * @param map 参数
    * @return 次数
     */
    Integer selectBuyComboTime(Map<String, Object> map);
    
    /**
     * 区域时间内，年季月卡消费次数
     * @param map 参数
     * @return 消费次数
     */
    Integer selectComboStatementTime(Map<String, Object> map);
    
    /**
     * 查询区域时间内购买商品次数
     * @param map 参数
     * @return 次数
     */
    Integer selectBuyGoodsTime(Map<String, Object> map);
    
    /**
     * 区域时间内消费抵扣套餐项目次数
    * @author 王大爷
    * @date 2016年1月25日 上午10:25:05
    * @param map 参数
    * @return 次数
     */
    Integer selectConsumeTime(Map<String, Object> map);
    
    /**
     * 查询商品排行（最多5个）
     * @param map 参数
     * @return Map<String, Object>
     */
    List<Map<String, Object>> selectRankingGoodsCount(Map<String, Object> map);
    
    /**
     * 查询区域时间内疗程及时间卡总次数
     * @param map 参数
     * @return Map<String, Object>
     */
    Map<String, Integer> selectComboTatailTime(Map<String, Object> map);
    
    /**
    * @author 乐建建
    * @date 2016年2月23日 上午11:57:06
    * @param dto  参数封装类
    * @return 根据给定条件查询的会员相关数据
    */
    List<MemberLevelInfo> getMemberInfo(SummaryResultDto dto);
    
    
    /**
    * @author 乐建建
    * @date 2016年2月23日 下午3:24:28
    * @param member 会员等级相关数据
    * @return Member2Info
    */
    Member2Info getMemberType2Info(MemberLevelInfo member);
    
    /**
    * @author 乐建建
    * @date 2016年2月23日 下午3:50:00
    * @param member 会员等级相关数据
    * @return MemberAccountInfo 
    */
    MemberAccountInfo getMemberAccountInfo(MemberLevelInfo member);
    
    /**
    * @author 乐建建
    * @date 2016年2月23日 下午4:20:26
    * @param member 会员等级相关数据
    * @return  MemberCardConsumedInfo
    */
    MemberCardConsumedInfo getMemberCardConsumedValue(MemberLevelInfo member);
    
    /**
    * @author 乐建建
    * @date 2016年2月23日 下午5:25:20
    * @param dto 封装参数条件
    * @return Member2Info
    */
    Member2Info getDatePeriodCardSummary(SummaryResultDto dto);
    
    /**
    * @author 乐建建
    * @date 2016年2月23日 下午6:00:28
    * @param dto 封装参数条件
    * @return List<CardConsumedTrendData>
    */
    List<CardConsumedTrendData> getCardType2Trend(SummaryResultDto dto);
    
    /**
    * @author 乐建建
    * @date 2016年2月23日 下午6:19:10
    * @param dto 封装参数条件 
    * @return List<DeptSummaryByDayDto>
    */
    List<DeptSummaryByDayDto> getCardConsumedTrend(SummaryResultDto dto);
    
    /**
	 * 查询项目提成信息
	 * @param map 项目标识、服务人员等级等信息
	 * @return CashierCommissionDto
	 */
	CashierCommissionDto selectProjectCommissionInfo(Map<String, Integer> map);
	
	/**
	 * 查询商品提成信息
	 * @param map 项目标识等信息
	 * @return CashierCommissionDto
	 */
	CashierCommissionDto selectGoodsCommissionInfo(Map<String, Integer> map);
	
	/**
	 * 查询套餐提成信息
	 * @param map 套餐标识、项目标识等信息
	 * @return CashierCommissionDto
	 */
	CashierCommissionDto selectComboCommissionInfo(Map<String, Integer> map);
	
	/**
	 * 根据会员标识查询所有的订单
	* @author 老王
	* @date 2016年6月30日 下午1:59:10 
	* @param memberId 会员标识
	* @return List<Map<String, Integer>>
	 */
	List<Map<String, Object>> selectIsNotOverOrderDetail(Integer memberId);
	
	/**
	 * 新增会员套餐
	 * @param map map
	 * @return 新增记录数
	 */
	int insertMemberComboRecord(Map<String, Integer> map);
	
	/**
	 * 新增会员套餐项目
	 * @param map map
	 * @return 新增记录数
	 */
	int insertMemberComboProject(Map<String, Integer> map);
}