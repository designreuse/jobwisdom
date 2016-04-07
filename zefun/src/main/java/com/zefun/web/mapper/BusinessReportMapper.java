package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.ProjectLaborRank;
import com.zefun.web.dto.ServiceReportDto;
import com.zefun.web.dto.SummaryResultDto;

/**
* @author 乐建建
* @date 2016年1月18日 上午11:23:56 
*/
public interface BusinessReportMapper {
	
	/**
	* @author 乐建建
	* @date 2016年1月18日 上午11:20:42
	* @param dto 对象 比如 起始时间 终止时间 页面点击的日期类型, 日周月年
	* @return 返回门店下各部门的汇总数据
	*/
	List<ServiceReportDto> selectProjectReportByStoreId(SummaryResultDto dto);
	
	/**
	 * @param dto 时间区间(起始日期 终止日期) 门店id
	 * @return 获取项目劳动业绩排行榜
	 */
	List<ProjectLaborRank> getProjectInDeptRank(SummaryResultDto dto);
	
	
	/**
	* @author 乐建建
	* @date 2016年1月26日 下午5:46:23
	* @param dto 封装参数条件 起始条件 终止条件 部门id
	* @return 获取项目劳动业绩
	*/
	List<ProjectLaborRank> getProjectRankByDept(SummaryResultDto dto);
	
    /**
    * @author 乐建建
    * @date 2016年1月18日 上午11:20:42
    * @param dto 对象 比如 起始时间 终止时间 页面点击的日期类型, 日周月年
    * @return 返回门店下各部门的汇总数据
    */
	List<ServiceReportDto> selectGoodReportByStoreId(SummaryResultDto dto);
	
}
