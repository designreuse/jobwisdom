/**
 * 
 */
package com.lives.manage.web.mapper;

import java.util.List;
import java.util.Map;

import com.lives.manage.web.dto.HomepageDto;
import com.lives.manage.web.dto.SystemStatisticsDto;

/** 
 * @author 陈端斌
 * @date 2015年6月26日 上午9:53:37 
 *  
 */
public interface StatisticsMapper {

	/**获取省份的销售前十名的*/
	List<Map<String,Object>> provinceSaleRank();
	/**获取城市的销售前十名的*/
	List<Map<String,Object>> citySaleRank();
	
	List<Map<String,Object>> monthSummary(Map<String ,Object> map);
	int monthSummaryCount(Map<String ,Object> map);
	
	List<Map<String,Object>> persSummary(Map<String ,Object> map);
	int persSummaryCount(Map<String ,Object> map);
	
	/**获取个人的销售前十名的*/
	List<Map<String,Object>> persSaleRank();
	
	List<SystemStatisticsDto> selectSystemStatistics(String createTime);
	
	List<SystemStatisticsDto> monthSales(Map<String ,Object> map);
	
	List<SystemStatisticsDto> monthSalesMoney(Map<String ,Object> map);
	
	
}
