package com.lives.manage.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.manage.common.util.DateUtils;
import com.lives.manage.web.dto.HchartDto;
import com.lives.manage.web.dto.SystemStatisticsDto;
import com.lives.manage.web.entity.CustomerInfo;
import com.lives.manage.web.mapper.CustomerInfoMapper;
import com.lives.manage.web.mapper.StatisticsMapper;

@Service
public class HchartService {

	@Autowired
	private CustomerInfoMapper customerInfoMapper;
	
	@Autowired
	private StatisticsMapper statisticsMapper;
	
	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 下午2:06:09 获取不同版本的每个月销售量
	 */
	public List<HchartDto> eachMonthSales(HchartDto hchartDto) {
		
		List<HchartDto> eachMonthSales=customerInfoMapper.eachMonthSales(hchartDto);
		
		return eachMonthSales;
	}
	
	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 下午2:06:09 获取不同版本的月销售量
	 */
	public List<SystemStatisticsDto> monthSales(Map<String,Object> map) {
		
		List<SystemStatisticsDto> list = statisticsMapper.monthSales(map);
		
		return list;
	}
	
	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 下午2:06:09 获取不同版本的月销金额
	 */
	public List<SystemStatisticsDto> monthSalesMoney(Map<String ,Object> map) {
		
		//HchartDto monthSales=customerInfoMapper.monthSalesMoney(hchartDto);
		List<SystemStatisticsDto> list = statisticsMapper.monthSalesMoney(map);
		
		return list;
	}
	
	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 下午2:06:09 统计平台销售列表
	 */
	public List<HchartDto> queryCount(Map<String, Object> map) {
		
		List<HchartDto> queryCount=customerInfoMapper.queryCount(map);
		
		return queryCount;
	}
	
    public List<HchartDto> queryCountNum(HchartDto hchartDto) {
		
		List<HchartDto> queryCountNum=customerInfoMapper.queryCountNum(hchartDto);
		
		return queryCountNum;
	}
	
    
    public List<SystemStatisticsDto> selectSystemStatistics(String createTime) {
    	List<SystemStatisticsDto> list = statisticsMapper.selectSystemStatistics(createTime);
    	//System.out.println(JSONArray.fromObject(list).toString());
    	return list;
	}
}
