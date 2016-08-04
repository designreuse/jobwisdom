package com.zefun.web.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zefun.web.dto.EmployeeCommissionDto;
import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.dto.EmployeeInfoDto;
import com.zefun.web.entity.EmployeeCommission;
import com.zefun.wechat.dto.CommissionValueAndTypeDto;
import com.zefun.wechat.dto.EmployeeCommissionOfBossDto;

/**
 * 员工提成操作对象
* @author 张进军
* @date Oct 28, 2015 4:32:57 PM
 */
public interface EmployeeCommissionMapper {
    /**
     * 根据提成标识删除提成信息
    * @author 张进军
    * @date Oct 28, 2015 4:33:36 PM
    * @param commissionId   提成标识
    * @return   0:失败，1:成功
     */
    int deleteByPrimaryKey(Integer commissionId);

    /**
     * 插入新的提成信息
    * @author 张进军
    * @date Oct 28, 2015 4:34:03 PM
    * @param record 提成记录
    * @return   0:失败，1:成功
     */
    int insert(EmployeeCommission record);

    /**
     * 根据提成标识查询提成信息
    * @author 张进军
    * @date Oct 28, 2015 4:34:11 PM
    * @param commissionId   提成标识
    * @return   提成记录
     */
    EmployeeCommission selectByPrimaryKey(Integer commissionId);
    
    
    

    /**
     * 根据提成标识修改提成记录
    * @author 张进军
    * @date Oct 28, 2015 4:36:27 PM
    * @param record 提成记录
    * @return    0:失败，1:成功
     */
    int updateByPrimaryKey(EmployeeCommission record);
    
    /**
     * 根据员工标识查询当日业绩金额
    * @author 张进军
    * @date Oct 28, 2015 5:33:00 PM
    * @param employeeId 员工标识
    * @return   当日业绩金额
     */
    BigDecimal selectServiceDayMoneyByEmployeeId(int employeeId);
    
    /**
     * 根据员工标识,日期区间,查询提成金额
    * @author 王大爷
    * @date 2015年11月17日 下午2:32:12
    * @param map 参数
    * @return 当日提成金额
     */
    BigDecimal selectBySectionDayCommission(Map<String, Object> map);
    
    
    /**
     * 业绩报表查询
    * @author 骆峰
    * @date 2016年8月3日 下午3:05:00
    * @param map map
    * @return List
     */
    List<EmployeeInfoDto> selectEmployeeInfoByCommission(Map<String, Object> map);
    
    /**
     * 计算时间区间，类型的业绩 
    * @author 王大爷
    * @date 2015年11月17日 下午3:53:02
    * @param map 参数
    * @return Map<String, BigDecimal>
     */
    BigDecimal selectBySectionDayCalculate(Map<String, Object> map);
    
    /**
     * 查询汇总数据
     * @param map 参数
     * @return Map<String, BigDecimal>
     */
    Map<String, Object> selectByTotalCalculate(Map<String, Object> map);
    
    /**
     * 查询汇总均值
     * @param map 参数
     * @return Map<String, BigDecimal>
     */
    Map<String, BigDecimal> selectByTotalAverageList(Map<String, Object> map);
    
    /**
     * 计算时间区间，指定项目的业绩
    * @author 王大爷
    * @date 2015年11月17日 下午4:40:42
    * @param map 参数
    * @return BigDecimal
     */
    BigDecimal selectBySectionAssignProjectCalculate(Map<String, Object> map);
    
    /**
     * 制定均值
     * @param map 参数
     * @return BigDecimal
     */
    BigDecimal selectAverageScaleByEmployeeId(Map<String, Object> map);
    
    /**
     * 根据员工集合排序 
    * @author 王大爷
    * @date 2015年11月19日 下午3:02:58
    * @param map 参数
    * @return BigDecimal
     */
    List<Map<String, Object>> selectByEmployeeIdList(Map<String, Object> map);
    
    /**
     * 查询区域时间内，岗位对应的总业绩
    * @author 王大爷
    * @date 2015年11月19日 下午6:14:16
    * @param map 参数
    * @return BigDecimal
     */
    BigDecimal selectByTotalEmployeeIdList(Map<String, Object> map);
    
    /**
     * 查询员工对应的指定比
     * @param map 参数
     * @return BigDecimal
     */
    BigDecimal selectScaleByEmployeeId(Map<String, Object> map);
    
    /**
     * 查询区域时间内，岗位对应的指定总业绩
    * @author 王大爷
    * @date 2015年11月19日 下午6:27:54
    * @param map 参数
    * @return BigDecimal
     */
    BigDecimal selectByTotalSectionAssignProjectCalculate(Map<String, Object> map);
    
    /**
     * 根据明细标识查询员工服务提成
    * @author 王大爷
    * @date 2015年12月2日 下午2:07:46
    * @param detailId 明细标识
    * @return List<EmployeeCommission>
     */
    List<EmployeeCommission> selectByDetailId(Integer detailId);
    
    /**
     * 根据轮牌步骤标识
    * @author 王大爷
    * @date 2015年12月2日 下午8:36:15
    * @param shiftMahjongStepId 参数
    * @return EmployeeCommission
     */
    EmployeeCommission selectByEmployeeIdShift(Integer shiftMahjongStepId);
    
    
    /**
     * 根据门店标识删除业绩提成记录(清除测试数据)
    * @author 张进军
    * @date Dec 25, 2015 8:33:18 PM
    * @param storeId	门店标识
    * @return	删除数量
     */
    int deleteByStoreId(int storeId);
    
    /**
     * 根据员工， 月，查询当月按天计算的业绩提成
    * @author 王大爷
    * @date 2016年1月3日 下午6:04:45
    * @param map 参数
    * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> selectCommissionByMonth(Map<String, Object> map);
    
    /**
     * 根据员工， 月,汇总单月提成
    * @author 王大爷
    * @date 2016年1月4日 下午6:13:19
    * @param map 参数
    * @return BigDecimal
     */
    BigDecimal selectSumCommissionByMonth(Map<String, Object> map);
    
    /**
     * 
    * @author 王大爷
    * @date 2016年1月4日 下午9:09:21
    * @param map 参数
    * @return EmployeeCommissionDto
     */
    List<EmployeeCommissionDto> selectCommissionByDay(Map<String, Object> map);
    
    /**
     * 根据订单类型月汇总
    * @author 王大爷
    * @date 2016年1月5日 上午10:12:40
    * @param map 参数
    * @return Map<String, Object>
     */
    Map<String, Object> selectSumByMonthOrderType(Map<String, Object> map);
    
    /**
     * boss端查询员工业绩汇总
    * @author DavidLiang
    * @date 2016年1月22日 下午8:14:16
    * @param storeId  店铺id
    * @param deptId  部门id
    * @param time  查询时间
    * @param sortName  排序名称
    * @return  员工绩效dto集
     */
    List<EmployeeCommissionOfBossDto> selectEmployeeCommissionByBoss(@Param(value = "storeId")int storeId, @Param(value = "deptId")Integer deptId, 
    		  @Param(value = "time")String time, @Param(value = "sortName")String sortName);
    
    /**
     * boss端查询员工现金业绩汇总
    * @author DavidLiang
    * @date 2016年2月20日 下午3:00:43
    * @param storeId  店铺id
    * @param positionId  岗位id
    * @param time  查询时间
    * @return  员工现金绩效dto集
     */
    List<EmployeeCommissionOfBossDto> selectEmployeeCashCommissionByBoss(@Param(value = "storeId")int storeId, 
    		  @Param(value = "positionId")Integer positionId, @Param(value = "time")String time);
    /**
     * boss端查询员工卡金业绩汇总
    * @author DavidLiang
    * @date 2016年2月20日 下午3:00:47
    * @param storeId  店铺id
    * @param positionId  岗位id
    * @param time  查询时间
    * @return  员工卡金绩效dto集
     */
    List<EmployeeCommissionOfBossDto> selectEmployeeCardCommissionByBoss(@Param(value = "storeId")int storeId, 
    		  @Param(value = "positionId")Integer positionId, @Param(value = "time")String time);
    /**
     * boss端查询用疗程抵扣项目的卡金业绩总汇
    * @author DavidLiang
    * @date 2016年2月27日 下午5:16:21
    * @param storeId  店铺id
    * @param positionId  岗位id
    * @param time  查询时间
    * @return  员工(疗程抵扣项目)卡金绩效dto集
     */
    List<EmployeeCommissionOfBossDto> selectEmployeeCardCommissionOfComboDeductionProjectByBoss(
    		  @Param(value = "storeId")int storeId, @Param(value = "positionId")Integer positionId, @Param(value = "time")String time);
    
    /**
     * 员工业绩详情查看员工基本信息
    * @author DavidLiang
    * @date 2016年1月26日 下午3:13:39
    * @param employeeId  员工id
    * @return  员工基本信息
     */
    EmployeeDto selectEmployeeInfoOfCommissionDetail(@Param(value = "employeeId")int employeeId);
    
    /**
     * 根据员工id和日期查询订单分组员工现金业绩详情
    * @author DavidLiang
    * @date 2016年1月27日 下午3:13:40
    * @param employeeId  员工id
    * @param time  时间
    * @return  订单分组员工现金业绩详情
     */
    List<CommissionValueAndTypeDto> selectCashCommissionDetailByEmployeeIdAndTime(
    		  @Param(value = "employeeId")int employeeId, @Param(value = "time")String time);
    
    /**
     * 根据员工id和日期查询订单分组员工现金业绩详情
    * @author DavidLiang
    * @date 2016年1月27日 下午3:15:13
    * @param employeeId  员工id
    * @param time  时间
    * @return  订单分组员工现金业绩详情
     */
    List<CommissionValueAndTypeDto> selectCardCommissionDetailByEmployeeIdAndTime(
    		  @Param(value = "employeeId")int employeeId, @Param(value = "time")String time);
    
    /**
     * 计算  业绩分布  中的疗程抵扣项目的员工业绩值
    * @author DavidLiang
    * @date 2016年2月27日 下午8:32:37
    * @param employeeId  员工id
    * @param time  时间
    * @return  员工业绩值
     */
    Double selectComboDeductionProjectEmployeeCommissionDetail(@Param(value = "employeeId")int employeeId, @Param(value = "time")String time);
    
    /**
     * 查询客单量根据大小项分组(根据员工id和日期)
    * @author DavidLiang
    * @date 2016年1月27日 下午8:20:41
    * @param employeeId  员工id
    * @param time  时间
    * @return  大小项分组客单量
     */
    List<Map<String, Object>> selectCustomerCountGroupByProjectType(
    		  @Param(value = "employeeId")int employeeId, @Param(value = "time")String time);
    
    /**
     * 查询客单量根据预约分组(根据员工id和日期)
    * @author DavidLiang
    * @date 2016年1月28日 上午11:59:39
    * @param employeeId  员工id
    * @param time  时间
    * @return  预约分组客单量
     */
    List<Map<String, Object>> selectCustomerCountGroupByAppoint(
    		  @Param(value = "employeeId")int employeeId, @Param(value = "time")String time);
    
    /**
     * 查询客单量根据指定分组(根据员工id和日期)
    * @author DavidLiang
    * @date 2016年1月28日 下午12:00:09
    * @param employeeId  员工id
    * @param time  时间
    * @return  指定分组客单量
     */
    List<Map<String, Object>> selectCustomerCountGroupByAssign(
  		      @Param(value = "employeeId")int employeeId, @Param(value = "time")String time);
    
    /**
     * 根据员工和时间查询员工提成和项目步骤
    * @author DavidLiang
    * @date 2016年2月26日 下午4:04:03
    * @param employeeId  员工id
    * @param time  时间
    * @return  员工提成和项目步骤集
     */
    List<EmployeeCommission> selectCommissionAndStepByEmployeeIdAndTime(
    		  @Param(value = "employeeId")int employeeId, @Param(value = "time")String time);
    
    /**
     * 根据订单类型查询提成(根据员工id和日期)
    * @author DavidLiang
    * @date 2016年1月28日 下午2:57:33
    * @param employeeId  员工id
    * @param time  时间
    * @return  提成分组
     */
    List<Map<String, Object>> selectCommissionAmountGroupByOrderType(
    		  @Param(value = "employeeId")int employeeId, @Param(value = "time")String time);
    
    /**
     * 员工评分
    * @author DavidLiang
    * @date 2016年1月28日 下午5:49:27
    * @param employeeId  员工id
    * @param time  时间
    * @return  员工评分组
     */
    List<Map<String, Object>> selectEvaluateGroupByRate(
    		  @Param(value = "employeeId")int employeeId, @Param(value = "time")String time);
    
    /**
     * 查询某员工某时间段(某年/某月/某日)提成汇总
    * @author DavidLiang
    * @date 2016年3月8日 上午11:18:32
    * @param employeeId  员工id
    * @param time  时间段(某年/某月/某日)
    * @return  提成汇总
     */
    Double selectEmployeeCommissionByTimePeriod(@Param(value = "employeeId")int employeeId, @Param(value = "time")String time);
    
}