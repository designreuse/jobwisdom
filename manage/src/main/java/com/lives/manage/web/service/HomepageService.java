/**
 * 
 */
package com.lives.manage.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.manage.web.entity.CustomerInfo;
import com.lives.manage.web.mapper.CustomerInfoMapper;
import com.lives.manage.web.mapper.StatisticsMapper;

/** 
 * @author 陈端斌
 * @date 2015年6月25日 上午10:11:52 
 *  
 */
@Service
public class HomepageService {
	@Autowired
	private CustomerInfoMapper customerInfoMapper;
	@Autowired
	private StatisticsMapper statisticsMapper;
	/**
	 * @author 陈端斌
	 * @date 2015年6月25日 上午10:11:52 
	 * 根据时间获取销售量
	 */
	public List<Map<String,Object>> querySale(CustomerInfo customerInfo){
		 List<Map<String,Object>> list =customerInfoMapper.querySale(customerInfo);
		return list;
	}
	
	/**
	 * @author 陈端斌
	 * @date 2015年6月25日 上午10:11:52 
	 * 页面相关统计
	 */
	public List<Map<String,Object>> queryStatistics(){
		 List<Map<String,Object>> list =customerInfoMapper.queryStatistics();
		return list;
	}
	
	/**
	 * @author 陈端斌
	 * @date 2015年6月25日 上午10:11:52 
	 * 获取省份的销售前十名的
	 */
	public List<Map<String,Object>> provinceSaleRank(){
		 List<Map<String,Object>> list =statisticsMapper.provinceSaleRank();
		return list;
	}
	
	/**
	 * @author 陈端斌
	 * @date 2015年6月25日 上午10:11:52 
	 * 获取城市的销售前十名的
	 */
	public List<Map<String,Object>> citySaleRank(){
		 List<Map<String,Object>> list =statisticsMapper.citySaleRank();
		return list;
	}
	
	/**
	 * @author 陈端斌
	 * @date 2015年6月25日 上午10:11:52 
	 * 本月区域开户汇总
	 */
	public List<Map<String,Object>> monthSummary(Map<String ,Object> map){
		 List<Map<String,Object>> list =statisticsMapper.monthSummary(map);
		return list;
	}
	public int monthSummaryCount(Map<String ,Object> map){
		 int count =statisticsMapper.monthSummaryCount(map);
		return count;
	}
	
	public List<Map<String,Object>> persSummary(Map<String ,Object> map){
		List<Map<String,Object>> list =statisticsMapper.persSummary(map);
		return list;
	}
	
	public int persSummaryCount(Map<String ,Object> map){
		 int count =statisticsMapper.persSummaryCount(map);
		return count;
	}
	
	/**
	 * @author 陈端斌
	 * @date 2015年6月25日 上午10:11:52 
	 * 获取城市的销售前十名的
	 */
	public List<Map<String,Object>> persSaleRank(){
		 List<Map<String,Object>> list =statisticsMapper.persSaleRank();
		return list;
	}
	
	
}
