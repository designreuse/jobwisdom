package com.lives.manage.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lives.manage.common.consts.Url;
import com.lives.manage.common.consts.View;
import com.lives.manage.common.util.DateUtils;
import com.lives.manage.web.dto.BaseDto;
import com.lives.manage.web.dto.HomepageDto;
import com.lives.manage.web.dto.SystemStatisticsDto;
import com.lives.manage.web.entity.CustomerInfo;
import com.lives.manage.web.service.HchartService;
import com.lives.manage.web.service.HomepageService;

/**
 * @author 陈端斌
 * @date 2015年6月8日 下午1:02:02
 * 首页相关统计
 */
@Controller
@RequestMapping("homepage")
public class HomepageController {
	private static final Logger logger = Logger.getLogger(MenuController.class);
	@Autowired
	private HomepageService homepageService;
	/**
	 * @author 陈端斌
	 * @date 2015年6月25日 下午1:02:02 首页页面  当月获取每天的销售量
	 */
	@RequestMapping(value = Url.Homepage.EACHDAYSALES)
	@ResponseBody
	public BaseDto eachDaySales(String province,String city,String area){
		
		List<Map<String, Object>> maplist=new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		CustomerInfo customerInfo=new CustomerInfo();
		try {
			String[] customerType = "1,2,3".split(",");
			for (int i = 0; i < customerType.length; i++) {
				Map<String, Object> map1=new HashMap<String, Object>();
				customerInfo.setCreateTime(DateUtils.getCurTime());
				customerInfo.setCustomerType(Integer.parseInt(customerType[i]));
				customerInfo.setProvince(province);
				customerInfo.setCity(city);
				customerInfo.setArea(area);
				list=homepageService.querySale(customerInfo);
				Map<String, Integer> map = new HashMap<String, Integer>();
				for (Map<String, Object> m : list) {
					String k = m.get("createTime").toString();
					Integer v = Integer.parseInt(m.get("count").toString());
					map.put(k, v);
				}
				int days = 31;
				int result[] = new int[days];
				for (int j = 1; j < days; j++) {
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
				map1.put("list",result);
				map1.put("customerType", customerType[i]);
				maplist.add(map1);
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("eachDaySales error : ", e);
			return new BaseDto(-1, "eachDaySales error");
		}
		return new BaseDto(0, maplist);
	}
	/**
	 * @author 陈端斌
	 * 根据时间获取销售量
	 * @return 
     */
	@RequestMapping(value = Url.Homepage.STATISTICS)
	@ResponseBody
	public BaseDto Statistics(){
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		try {
			list=homepageService.queryStatistics();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Statistics error : ", e);
			return new BaseDto(-1, "Statistics error");
		}
		return new BaseDto(0, list);
	}
	/**
	 * 获取城市的销售前十名的
	* @author 陈端斌
	* @date 2015年7月6日 下午8:04:32
	* @return
	 */
	@RequestMapping(value = Url.Homepage.CITYSALERANK)
	@ResponseBody
	public BaseDto citySaleRank(){
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		Map<String, Object> map=new HashMap<String, Object>();
		String cityList[]=new String[10];
		int countList[]=new int[10];
		try {
			list=homepageService.citySaleRank();
			for (int i = 0; i < list.size(); i++) {
				
				String cityname=list.get(i).get("city").toString();
				if(cityname==""||cityname==null){
					cityname="";
				}
				int count=Integer.parseInt(list.get(i).get("count").toString());
				cityList[i]=cityname;
				
				countList[i]=count;
			}
			map.put("cityList", cityList);
			map.put("countList", countList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("citySaleRank : ", e);
			return new BaseDto(-1, "citySaleRank");
		}
		return new BaseDto(0, map);
	}
	
	/**
	 * @author 陈端斌
	 * 获取城市的销售前十名的
	 * @return 
     */
	@RequestMapping(value = Url.Homepage.PROVINCESALERANK)
	@ResponseBody
	public BaseDto provinceSaleRank(){
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		Map<String, Object> map=new HashMap<String, Object>();
		String provinceList[]=new String[10];
		int countList[]=new int[10];
		try {
			list=homepageService.provinceSaleRank();
			for (int i = 0; i < list.size(); i++) {
				
				String provincename=list.get(i).get("province").toString();
				if(provincename==""||provincename==null){
					provincename="";
				}
				int count=Integer.parseInt(list.get(i).get("count").toString());
				provinceList[i]=provincename;
				
				countList[i]=count;
			}
			map.put("provinceList", provinceList);
			map.put("countList", countList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("provinceSaleRank : ", e);
			return new BaseDto(-1, "provinceSaleRank");
		}
		return new BaseDto(0, map);
	}
	
	/**
	 * @author 陈端斌
	 * 本月区域开户汇总
	 * @return 
     */
	@RequestMapping(value = Url.Homepage.MONTHSUMMARY)
	@ResponseBody
	public JSONObject monthSummary(@RequestBody Map<String, Object> map){
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		try {
			map.put("createTime", DateUtils.getCurTime());
			
			list=homepageService.monthSummary(map);
			int count =homepageService.monthSummaryCount(map);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("total", count);
			jsonObject.put("rows", list);
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("provinceSaleRank : ", e);
			return null;
		}
	}
	/**
	 * @author 陈端斌
	 * 本月个人开户汇总
	 * @return 
     */
	@RequestMapping(value = Url.Homepage.PERSSUMMARY)
	@ResponseBody
	public JSONObject persSummary(@RequestBody Map<String, Object> map){
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		try {
			map.put("createTime", DateUtils.getCurTime());
			list=homepageService.persSummary(map);
			int count =homepageService.persSummaryCount(map);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("total", count);
			jsonObject.put("rows", list);
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("persSummary : ", e);
			return null;
		}
	}
	
	/**
	 * @author 陈端斌
	 * 获取城市的销售前十名的
	 * @return 
     */
	@RequestMapping(value = Url.Homepage.PERSSALERANK)
	@ResponseBody
	public BaseDto persSaleRank(){
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		Map<String, Object> map=new HashMap<String, Object>();
		String createnameList[]=new String[10];
		int countList[]=new int[10];
		try {
			list=homepageService.persSaleRank();
			for (int i = 0; i < list.size(); i++) {
				
				String createname=list.get(i).get("real_name").toString();
				if(createname==""||createname==null){
					createname="";
				}
				int count=Integer.parseInt(list.get(i).get("count").toString());
				createnameList[i]=createname;
				
				countList[i]=count;
			}
			map.put("createnameList", createnameList);
			map.put("countList", countList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("persSaleRank : ", e);
			return new BaseDto(-1, "persSaleRank");
		}
		return new BaseDto(0, map);
	}
	
	

	
	
	//---------------------------------------------------------------------页面-------------------------------------------------------------------------
	/**
	 * @author 陈端斌
	 * @date 2015年6月25日 下午1:02:02 
	 * 首页页面
	 */
	@RequestMapping("view/query")
	public ModelAndView addView(ModelAndView model) {

		model.setViewName(View.Homepage.QUERY);

		return model;
	}
	
	/**
	 * @author 陈端斌
	 * @date 2015年6月25日 下午1:02:02
	 * 区域排名页面
	 */
	@RequestMapping("view/aerarank")
	public ModelAndView aerarank(ModelAndView model) {

		model.setViewName(View.Homepage.QUERYAERARANK);

		return model;
	}
	
	/**
	 * @author 陈端斌
	 * @date 2015年6月25日 下午1:02:02
	 * 个人排名页面
	 */
	@RequestMapping("view/persrank")
	public ModelAndView persrank(ModelAndView model) {

		model.setViewName(View.Homepage.QUERYPERSRANK);

		return model;
	}
	
	/**
	 * @author 陈端斌
	 * @date 2015年6月25日 下午1:02:02
	 * 渠道商页面
	 */
	@RequestMapping("view/account")
	public ModelAndView account(ModelAndView model) {

		model.setViewName(View.Homepage.QUERYACCOUNT);

		return model;
	}

}
