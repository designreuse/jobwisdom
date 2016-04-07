package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zefun.web.dto.ExcelOfEmployeeShiftInfoDto;
import com.zefun.web.dto.ShiftDto;
import com.zefun.web.entity.EmployeeShift;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.ShiftInfo;

/**
 * 关于班次信息
 * 
 * @author chendb
 * @date 2015年8月28日 上午10:02:43
 */
public interface ShiftMapper {

	/**
	 * 修改班次
	 * 
	 * @author chendb
	 * @date 2015年8月28日 上午10:03:59
	 * @param record
	 *            参数
	 * @return int
	 */
	int updateByPrimaryKey(ShiftDto record);

	/**
	 * 获取人员班次信息
	 * 
	 * @author chendb
	 * @date 2015年8月28日 上午10:24:04
	 * @param page
	 *            参数
	 * @return List<ShiftDto>
	 */
	List<ShiftDto> getEmployeeShift(Page<ShiftDto> page);

	/**
	 * 获取班次信息
	 * 
	 * @author chendb
	 * @date 2015年8月28日 上午10:45:15
	 * @param deptId
	 *            部门 标识
	 * @return List<ShiftDto>
	 */
	List<ShiftInfo> getShiftInfo(Integer deptId);

	/**
	 * 
	 * @author chendb
	 * @date 2015年8月28日 下午4:38:03
	 * @param map
	 *            参数
	 * @return int
	 */
	int employeeShift(Map<String, Object> map);

	/**
	 * 删除员工班次信息
	 * 
	 * @author chendb
	 * @date 2015年8月28日 下午4:45:57
	 * @param map
	 *            参数
	 * @return int
	 */
	int deleteShift(Map<String, Object> map);

	/**
	 * 获取员工班次信息
	 * 
	 * @author chendb
	 * @date 2015年8月31日 下午3:58:15
	 * @param map
	 *            参数
	 * @return ShiftDto
	 */
	ShiftDto queryEmployeeShift(Map<String, Object> map);

	/**
	 * 判断是否已经新增过人员班次了
	 * 
	 * @author chendb
	 * @date 2015年8月31日 下午5:04:12
	 * @param map
	 *            参数
	 * @return int
	 */
	int countIsshift(Map<String, Object> map);
	
	/**
	 * 根据员工数组判断其中是否有员工已经排班
	* @author DavidLiang
	* @date 2016年3月15日 上午10:04:48
	* @param employeeIdArr  员工数组
	* @return  是否有员工已经排班(0表示无，>0表示有)
	 */
	int countIsshiftByArray(int[] employeeIdArr);
	
	/**
	 * 批量新增员工排班(e)
	* @author DavidLiang
	* @date 2016年3月15日 上午10:25:49
	* @param list 员工排班list
	* @return 新增状况
	 */
	int insertShiftBatch(@Param(value="list")List<EmployeeShift> list);

	/**
	 * 修改班次信息
	 * 
	 * @author chendb
	 * @date 2015年9月1日 上午11:05:10
	 * @param map
	 *            参数
	 * @return int
	 */
	int updateShift(ShiftInfo map);

	/**
	 * 新增班次信息
	 * 
	 * @author chendb
	 * @date 2015年9月1日 上午11:05:10
	 * @param map
	 *            参数
	 * @return int
	 */
	int addShift(Map<String, Object> map);

	/**
	 * 根据员工查询班次信息
	 * 
	 * @author 张进军
	 * @date Oct 19, 2015 3:21:12 PM
	 * @param employeeId
	 *            员工标识
	 * @return 员工班次信息
	 */
	Map<Integer, String> selectShiftByEmployeeId(int employeeId);

	/**
	 * 
	 * @author chendb
	 * @date 2015年10月21日 下午2:35:36
	 * @param map
	 *            参数
	 * @return ShiftInfo
	 */
	ShiftInfo queryshiftinfo(Map<String, Object> map);

	/**
	 * 
	 * @author chendb
	 * @date 2015年10月21日 下午5:02:07
	 * @param info
	 *            参数
	 * @return int
	 */
	int insertInfo(ShiftDto info);

	/**
	 * 
	 * @author chendb
	 * @date 2015年10月22日 上午10:38:54
	 * @param page
	 *            参数
	 * @return List<ShiftDto>
	 */
	List<ShiftDto> queryEmployee(Page<ShiftDto> page);

	/**
	 * 新增班次
	 * 
	 * @author chendb
	 * @date 2015年11月9日 下午5:45:39
	 * @param shiftInfo
	 *            参数
	 * @return int
	 */
	int insertShiftinfo(ShiftInfo shiftInfo);

	/**
	 * 根据店铺id得到员工班次excel模板信息
	 * 
	 * @param storeId  店铺id
	 * @return  员工班次excel模板信息集
	 */
	List<ExcelOfEmployeeShiftInfoDto> selectExcelOfEmployeeShiftInfo(int storeId);
	
	/**
	 * 根据店铺id和部门id查询班次总数
	 * @param storeId  店铺id
	 * @param detpId  部门id
	 * @return  该店铺该部门班次总数
	 */
	int selectCountShiftByStoreAndDept(@Param(value="storeId")int storeId, @Param(value="detpId")int detpId);
	
	/**
	 * 根据主键查询班次
	* @author DavidLiang
	* @date 2016年1月14日 下午2:16:00
	* @param shiftId  班次id
	* @return  班次
	 */
	ShiftInfo selectByPrimaryKey(@Param(value="shiftId")Integer shiftId);
	
	/**
	 * 根据星期几查询员工班次
	* @author DavidLiang
	* @date 2016年2月18日 下午3:19:11
	* @param employeeId  员工id
	* @param shiftId  星期几班次自动标识(eg:周一是shif_ida)
	* @return  该员工班次
	 */
	ShiftInfo selectShiftByWeekDay(@Param(value="employeeId")Integer employeeId, @Param(value="shiftId")String shiftId);
}