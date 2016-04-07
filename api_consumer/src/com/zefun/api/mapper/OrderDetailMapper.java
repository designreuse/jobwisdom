package com.zefun.api.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zefun.api.dto.CashierCommissionDto;
import com.zefun.api.dto.CashierOrderDetailDto;
import com.zefun.api.entity.EmployeeCommission;
import com.zefun.api.entity.OrderDetail;

/**
 * 订单详情操作对象
 * @author luhw
 *
 */
public interface OrderDetailMapper {
	
	/**
	 * 根据订单标识查询订单详情
	 * @param orderId 订单标识
	 * @return 订单详情
	 */
	List<CashierOrderDetailDto> selectOrderDetailByOrderId(Integer orderId);
	
	/**
	 * 
	* @author 王大爷
	* @date 2016年1月6日 下午6:08:50
	* @param detailId 明细标识
	* @return CashierOrderDetailDto
	 */
	List<CashierOrderDetailDto> selectUpdateOrderDetailByDetailId(Integer detailId);
	
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
	 * 新增员工提成信息
	 * @param employeeCommission 员工提成对象
	 * @return 新增记录数
	 */
	int insertEmployeeCommission(EmployeeCommission employeeCommission);
	
	/**
	 * 更新项目销售信息
	 * @param map map
	 * @return 更新记录数
	 */
	int updateProjectSalesInfo(Map<String, Integer> map);
	
	/**
	 * 更新商品销售信息
	 * @param map map
	 * @return 更新记录数
	 */
	int updateGoodsSalesInfo(Map<String, Integer> map);
	
	/**
	 * 更新套餐销售信息
	 * @param map map
	 * @return 更新记录数
	 */
	int updateComboSalesInfo(Map<String, Integer> map);
	
	/**
	 * 更新员工服务次数
	 * @param map map
	 * @return 更新记录数
	 */
	int updateEmployeeSalesInfo(Map<String, Integer> map);
	
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
	
	/**
	 * 是否指定及步骤业绩计算值
	* @author 王大爷
	* @date 2015年11月17日 下午9:28:26
	* @param shiftMahjongStepId 轮牌标识
	* @return 是否指定
	 */
	Map<String, Object> selectIsAssignStepPerformance(Integer shiftMahjongStepId);

	/**
	 * 根据轮牌步骤查询轮牌标识
	* @author 王大爷
	* @date 2015年11月25日 下午8:47:09
	* @param shiftMahjongStepId 步骤标识
	* @return Integer
	 */
	Integer selectByShiftMahjongStepId(Integer shiftMahjongStepId);
	
	/**
	 * 根据明细标识查询明细
	* @author 王大爷
	* @date 2015年11月25日 下午9:03:44
	* @param detailId 明细标识
	* @return OrderDetail
	 */
	OrderDetail selectByDetailId(Integer detailId);
	
	/**
	 * 查询orderId
	* @author 王大爷
	* @date 2015年12月28日 下午9:21:40
	* @return List<Integer>
	 */
	List<Integer> selectOrderId();
	
	/**
	 * 根据订单标识查询会员标识
	* @author 王大爷
	* @date 2015年11月26日 下午3:52:24
	* @param orderId 订单标识
	* @return 会员标识
	 */
	Map<String, Object> selectMemberIdByOrderId(Integer orderId);
	
	/**
	 *  计算会员单次消费 
	* @author 王大爷
	* @date 2015年11月26日 下午4:29:51
	* @param memberId 会员标识
	* @return 是否成功
	 */
	Integer updateAvgConsume(Integer memberId);
	
	/**
	 * 计算会员平均消费时间
	* @author 王大爷
	* @date 2015年11月26日 下午4:44:28
	* @param memberId 会员标识
	* @return List<String>
	 */
	List<String> selectCreateTime(Integer memberId);
	
	/**
	 * 修改会员平均消费天数
	* @author 王大爷
	* @date 2015年11月26日 下午5:20:54
	* @param map 参数
	* @return 是否成功
	 */
	Integer updateAvgConsumeDays(Map<String, Integer> map);

	/**
	 * 查询项目会员折扣
	* @author 王大爷
	* @date 2016年1月7日 上午10:11:41
	* @param map 参数
	* @return Map<String, Object>
	 */
	Map<String, Object> selectByDiscount(Map<String, Integer> map);
	
	/**
	 * 根据会员标识查询会员等级
	* @author 王大爷
	* @date 2016年1月7日 下午4:21:24
	* @param memberId 会员标识
	* @return Integer
	 */
	Integer selectLevelIdByMemberId(Integer memberId);
	
	/**
	 * 根据会员等级标识查询会员项目折扣
	* @author 王大爷
	* @date 2016年1月7日 下午4:27:23
	* @param levelId 会员等级标识
	* @return BigDecimal
	 */
	BigDecimal selectMemberLevel(Integer levelId);
	
	/**
	 * 根据项目、商品、套餐标识查询部门
	* @author 王大爷
	* @date 2016年1月13日 下午8:51:27
	* @param map 参数
	* @return Integer
	 */
	Integer selectDeptIdByProjectId(Map<String, Integer> map);
	
	/**
	 * 修改明细部门
	* @author 王大爷
	* @date 2016年1月13日 下午8:57:47
	* @param map canshu
	* @return Integer
	 */
	Integer updateOrderDetailDeptId(Map<String, Integer> map);
	
	/**
	 * 根据订单标识查询明细集合
	* @author 王大爷
	* @date 2016年1月14日 下午3:43:40
	* @param orderId 订单标识
	* @return List<Integer>
	 */
	List<Integer> selectDetailIdByOrderId(Integer orderId);
	
	/**
	 * 根据套餐标识查询对应商品数量
	 * @param comboId 套餐标识
	 * @return List<Map<String, Integer>>
	 */
	List<Map<String, Object>> selectGoodsByComboId(Integer comboId);
	
	/**
	 * 修改商品库存数量
	 * @param map 参数
	 * @return 是否成功
	 */
	Integer updateGoodsStock(Map<String, Object> map);
}
