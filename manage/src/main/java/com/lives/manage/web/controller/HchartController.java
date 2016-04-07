package com.lives.manage.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lives.manage.common.consts.Url;
import com.lives.manage.common.consts.View;
import com.lives.manage.common.util.DateUtils;
import com.lives.manage.web.dto.BaseDto;
import com.lives.manage.web.dto.HchartDto;
import com.lives.manage.web.dto.SystemStatisticsDto;
import com.lives.manage.web.service.HchartService;

/**
 * @author 陈端斌
 * @date 2015年6月8日 下午1:02:02
 * 统计相关类
 */
@Controller
@RequestMapping("hchart")
public class HchartController {
	private static final Logger logger = Logger.getLogger(MenuController.class);
	@Autowired
	private HchartService hchartService;

	/**
	 * @author 陈端斌
	 * @date 2015年6月10日 下午1:02:02 统计图形管理页面
	 */

	@RequestMapping("view/hchart")
	public ModelAndView addView() {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(View.Hchart.QUERY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 下午1:02:02 获取不同版本的月销售量
	 */
	@RequestMapping(value = Url.Hchart.EACHMONTHSALES)
	@ResponseBody
	public BaseDto eachMonthSales(String years) {
		List<SystemStatisticsDto> info=new ArrayList<SystemStatisticsDto>();
		List<Map<String, Object>> maplist=new ArrayList<Map<String,Object>>();
		try {
			info=hchartService.selectSystemStatistics(years);
			if(info.size()>0){
				for (int i = 0; i < info.size(); i++) {
					Map<String, Object> map1=new HashMap<String, Object>();
					String type=info.get(i).getSystemType();
					List<Map<String,Object>> statisticsList=new ArrayList<Map<String,Object>>();
					statisticsList=info.get(i).getStatisticsList();
					
					Map<String, Integer> map = new HashMap<String, Integer>();
					for (Map<String, Object> m : statisticsList) {
						String k = m.get("createTime").toString();
						Integer v = Integer.parseInt(m.get("count").toString());
						map.put(k, v);
					}
					int result[] = new int[12];
					for (int j = 1; j < 12; j++) {
						int v = 0;
						String k = String.valueOf(j);
						if(j < 10){
							k = "0" + k;
						}
						if(map.containsKey(k)){
							v = map.get(k);
						}
						result[j-1] = v;
					}
					map1.put("name", type);
					map1.put("data",result);
					maplist.add(map1);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("eachMonthSales error : ", e);
			return new BaseDto(-1, "eachMonthSales error");
		}

		return new BaseDto(0, maplist);
	}
	
	
	
	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 下午1:02:02 获取不同版本的月销售量
	 */
	@RequestMapping(value = Url.Hchart.MONTHSALES)
	@ResponseBody
	public BaseDto monthSales(String years ,String monthTime) {
		List<SystemStatisticsDto> list=new ArrayList<SystemStatisticsDto>();
		Map<String,Object>map=new HashMap<String, Object>();
		
		List<Map<String, Object>> maplist=new ArrayList<Map<String,Object>>();
		Map<String, Object> list1=new HashMap<String, Object>();
		try {
			map.put("years", years);
			if(monthTime.length()==1){
				monthTime="0"+monthTime;
			}
			map.put("monthTime", monthTime);
			list=hchartService.monthSales(map);
			if(list.size()>0){
				String result[] = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					Map<String,Object>map1=new HashMap<String, Object>();
					String type=list.get(i).getSystemType();
					Integer count=list.get(i).getCount();
					if(count==null){
						count=0;
					}
					String colors="#7cb5ec,#434348,#90ed7d,#f7a35c,#8085e9,#f15c80,#e4d354,#2b908f,#f45b5b,#91e8e1";
					String[] colors1=colors.split(",");
					map1.put("color", colors1[i]);
					map1.put("y", count);
					result[i]=type;
					maplist.add(map1);
				}
				list1.put("result", result);
				list1.put("maplist", maplist);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("monthSales error : ", e);
			return new BaseDto(-1, "monthSales error");
		}

		return new BaseDto(0, list1);
	}
	
	
	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 下午1:02:02 获取不同版本的月销金额
	 */
	@RequestMapping(value = Url.Hchart.MONTHSALESMONEY)
	@ResponseBody
	public BaseDto monthSalesMoney(String years ,String monthTime) {
		Map<String,Object>map=new HashMap<String, Object>();
		List<SystemStatisticsDto> list=new ArrayList<SystemStatisticsDto>();
		List<Map<String, Object>> maplist=new ArrayList<Map<String,Object>>();
		try {
			map.put("years", years);
			if(monthTime.length()==1){
				monthTime="0"+monthTime;
			}
			map.put("monthTime", monthTime);
			list=hchartService.monthSalesMoney(map);
			if(list.size()>0){
				for (int  i= 0; i< list.size(); i++) {
					Map<String,Object>map1=new HashMap<String, Object>();
					String type=list.get(i).getSystemType();
					Double money=list.get(i).getMoneycount();
					if(money==null){
						money=0.0;
					}
					map1.put("name", type);
					map1.put("y",money);
					maplist.add(map1);
				}
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("monthSalesMoney error : ", e);
			return new BaseDto(-1, "monthSalesMoney error");
		}

		return new BaseDto(0, maplist);
	}
	
	
	/**
	 * 分页数据显示
	* @author 陈端斌
	* @date 2015年6月15日 下午3:41:31
	* @param offset	开始记录数
	* @param limit	查询行数
	* @param order	排序方式
	* @return
	 */
	@RequestMapping(value = Url.Hchart.QUERYCOUNT)
	@ResponseBody
	public JSONObject queryCount(int offset, int limit, @RequestParam(required = false)String order,  @RequestParam(required = false)String sort) {
		List<HchartDto> list=null;
		List<HchartDto> listnum=null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("offset", offset);
			map.put("limit", limit);
			map.put("order", order);
			map.put("sort", sort);
			list=hchartService.queryCount(map);
			listnum=hchartService.queryCountNum(null);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("total", listnum.size());
			jsonObject.put("rows", list);
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("queryCount error : ", e);
		}

		return null;
	}
	
	
	
	@RequestMapping("getInfo")
	public void getinfo(){
		hchartService.selectSystemStatistics(DateUtils.getCurTime());
	}
	
	
	
}