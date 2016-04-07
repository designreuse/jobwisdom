package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.EmployeePayrollDto;

/**
 * 营业分析数据操作类
* @author 张进军
* @date Jan 13, 2016 10:57:04 PM
 */
public interface BusinessAnalysissMapper {
	
	/**
	 * 根据门店标识查询员工某月工资信息
	* @author 张进军
	* @date Jan 13, 2016 10:57:47 PM
	* @param params	门店标识、月份
	* @return	员工工资信息
	 */
	List<EmployeePayrollDto> selectPayrollByStoreId(Map<String, Object> params);
}
