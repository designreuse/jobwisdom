/**
 * 
 */
package com.lives.manage.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import com.lives.manage.web.dto.UserInfoDto;
import com.lives.manage.web.entity.GoodsInfo;
import com.lives.manage.web.service.CustService;
import com.lives.manage.web.service.ProjectService;

/** 
 * @author 陈端斌
 * @date 2015年6月17日 下午5:49:14 
 *  
 */
@Controller
@RequestMapping("/project")
public class ProjectController {
	private static final Logger logger = Logger.getLogger(MenuController.class);
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CustService custService;
	/** 
	 * @author 陈端斌
	 * @date 2015年6月17日 下午5:49:14 新增项目产品
	 *  
	 */
	@RequestMapping(value = Url.Project.ADD)
	@ResponseBody
	public BaseDto addGoodsInfo(HttpSession session,Integer goodsType, String goodsName,
			String goodsDesc, BigDecimal goodsPrice,String goodsDesc1,Integer goodsStock){
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		try {
			GoodsInfo goodsInfo=new GoodsInfo();
			goodsInfo.setGoodsType(goodsType);
			goodsInfo.setGoodsNametype(goodsName);
			goodsName=goodsName+"("+goodsDesc1+")";
			goodsInfo.setGoodsName(goodsName);
			goodsInfo.setGoodsDesc(goodsDesc);
			goodsInfo.setGoodsPrice(goodsPrice);
			goodsInfo.setGoodsStock(goodsStock);
			goodsInfo.setCreatorId(userInfoDto.getUserId());
			goodsInfo.setCreateTime(DateUtils.getCurTime());
			projectService.addGoodsInfo(goodsInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addGoodsInfo error : ", e);
			return new BaseDto(-1, "addGoodsInfo error");
		}

		return new BaseDto(0, "ok");
	}
	/** 
	 * @author 陈端斌
	 * @date 2015年6月17日 下午5:49:14 删除项目产品
	 *  
	 */
	@RequestMapping(value = Url.Project.DELETE)
	@ResponseBody
	public BaseDto deleteGoodsInfo(Integer goodsId){
		try {
			projectService.deleteGoodsInfo(goodsId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("deleteGoodsInfo error : ", e);
			return new BaseDto(-1, "deleteGoodsInfo error");
		}
		return new BaseDto(0, "ok");
	}
	/** 
	 * @author 陈端斌
	 * @date 2015年6月17日 下午5:49:14 修改项目产品
	 *  
	 */
	@RequestMapping(value = Url.Project.UPDATE)
	@ResponseBody
	public BaseDto updateGoodsInfo(Integer goodsId,Integer goodsType, String goodsName,
			String goodsDesc, BigDecimal goodsPrice ,String goodsDesc1,Integer goodsStock){
		try {
			GoodsInfo goodsInfo=new GoodsInfo();
			goodsInfo.setGoodsId(goodsId);
			goodsInfo.setGoodsType(goodsType);
			goodsInfo.setGoodsNametype(goodsName);
			goodsName=goodsName+"("+goodsDesc1+")";
			goodsInfo.setGoodsName(goodsName);
			goodsInfo.setGoodsDesc(goodsDesc);
			goodsInfo.setGoodsPrice(goodsPrice);
			goodsInfo.setGoodsStock(goodsStock);
			projectService.updateGoodsInfo(goodsInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateGoodsInfo error : ", e);
			return new BaseDto(-1, "updateGoodsInfo error");
		}

		return new BaseDto(0, "ok");
	}
	/** 
	 * @author 陈端斌
	 * @date 2015年6月17日 下午5:49:14 
	 * 获取客户消费信息
	 *  
	 */
	@RequestMapping(value = Url.Project.GETCUSTSALE)
	@ResponseBody
	public JSONObject getCustSale(@RequestBody Map<String ,Object> map){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try {
			list=projectService.getCustSale(map);
			int count=projectService.getCustSaleCount(map);
			
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("total", count);
			jsonObject.put("rows", list);
			return jsonObject;
		} catch (Exception e) {
			
		}

		return null;
	}
	/** 
	 * @author 陈端斌
	 * @date 2015年6月17日 下午5:49:14
	 *  客户新增消费订单
	 *  
	 */
	@RequestMapping(value = Url.Project.ADDCUSTSALE)
	@ResponseBody
	public BaseDto addCustSale(HttpSession session,String customerName,String goodsTypeList,String goodsNameList,String goodsPriceList,
			String goodsQuantityList,String goodsAmountList){
		List<Map<String ,Object>> maplist=new ArrayList<Map<String,Object>>();
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		Double subtractAmount=0.0;
		Double goodsAmountCount=0.0;
		int num=0;
		try {
			String[] goodsTypeList1=goodsTypeList.split(",");
			String[] goodsNameList1=goodsNameList.split(",");
			String[] goodsPriceList1=goodsPriceList.split(",");
			String[] goodsQuantityList1=goodsQuantityList.split(",");
			String[] goodsAmountList1=goodsAmountList.split(",");
			Map<String ,Object> map1=new HashMap<String, Object>();
			map1.put("customerName", customerName);
			//循环获取客户名称 然后根据客户名称去获取id
			
			Integer custId=custService.getcustomerId(map1);
			if(custId==0){
				return new BaseDto(-2, "客户名称不存在！");
			}
			//循环遍历出所有参赛 然后封装
			for (int i = 0; i < goodsTypeList1.length; i++) {
				Map<String ,Object> map=new HashMap<String, Object>();
				Map<String ,Object> goodsPricemap=new HashMap<String, Object>();
				String goodsType=goodsTypeList1[i];
				String goodsName=goodsNameList1[i];
				map.put("goodsType", goodsType);
				map.put("goodsName", goodsName);
				//根据 类型+商品名称  获取商品单价
				goodsPricemap=projectService.getGoodsPrice(map);
				Double goodsPrice=Double.parseDouble(goodsPricemap.get("goodsPrice").toString());
				//String goodsPrice=goodsPriceList1[i];
				
				//subtractAmount+=goodsPrice;
				//商品数量
				String goodsQuantity=goodsQuantityList1[i];
				num=Integer.parseInt(goodsQuantity);
				int goodsStock=Integer.parseInt(goodsPricemap.get("goodsStock").toString());
				goodsStock=goodsStock-num;
				if(goodsStock<0){
					return new BaseDto(2, "库存数量不足，请联系系统管理员！");
				}
				//商品总价格
				//String goodsAmount=goodsAmountList1[i];
				Double goodsAmount=num*goodsPrice;
				
				goodsAmountCount+=goodsAmount;
				/***/
				map.put("goodsStock", goodsStock);
				map.put("goodsType", goodsType);
				map.put("goodsName", goodsName);
				map.put("goodsPrice", goodsPrice);
				map.put("goodsQuantity", goodsQuantity);
				map.put("goodsAmount", goodsAmount);
				maplist.add(map);
			}
			//封装要进入主订单
			Map<String ,Object> order=new HashMap<String, Object>();
			order.put("createTime", DateUtils.getCurTime());
			order.put("customerId", custId);
			order.put("realAmount", goodsAmountCount);
			//order.put("subtractAmount", subtractAmount);
			order.put("quantity", num);
			order.put("creatorId", userInfoDto.getUserId());
			order.put("createime", DateUtils.getCurTime());
			int result=projectService.addCustSale(maplist,order);
			if(result==9){
				return new BaseDto(-9, "你的金额不够，请先充值！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addCustSale error : ", e);
			return new BaseDto(-1, "addCustSale error");
		}
		return new BaseDto(0, "ok");
	}
	/** 
	 * @author 陈端斌
	 * @date 2015年6月17日 下午5:49:14 查询项目产品
	 *  
	 */
	@RequestMapping(value = Url.Project.QUERY)
	@ResponseBody
	public JSONObject queryGoodsInfo(@RequestBody Map<String, Object> map){
		List<GoodsInfo> GoodsList=null;
		try {
			
			GoodsList=projectService.queryGoodsInfo(map);
			int count=projectService.goodsInfoCount(map);
			
			JSONObject jsonobject=new JSONObject();
			jsonobject.put("total", count);
			jsonobject.put("rows", GoodsList);
			return jsonobject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("queryGoodsInfo error : ", e);
		}
		return null;
	}
	
	
	/** 
	 * @author 陈端斌
	 * @date 2015年6月17日 下午5:49:14 
	 * 根据类型获取项目产品
	 *  
	 */
	@RequestMapping(value = Url.Project.GETGOODSNAME)
	@ResponseBody
	public BaseDto getGoodsName(Integer goodsType){
		Map<String ,Object> map=new HashMap<String, Object>();
		List<Map<String ,Object>> list=new ArrayList<Map<String,Object>>();
		try {
			map.put("goodsType", goodsType);
			 list=projectService.getGoodsName(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getGoodsName error : ", e);
			return new BaseDto(-1, "getGoodsName error");
		}

		return new BaseDto(0, list);
	}
	/** 
	 * @author 陈端斌
	 * @date 2015年6月17日 下午5:49:14 
	 * 根据类型和项目名称获取项目价格
	 *  
	 */
	@RequestMapping(value = Url.Project.GETGOODSPRICE)
	@ResponseBody
	public BaseDto getGoodsPrice(Integer goodsType,String goodsName){
		Map<String ,Object> map=new HashMap<String, Object>();
		Double goodsPrice=null;
		Map<String,Object> goodsPrice1=new HashMap<String, Object>();
		try {
			map.put("goodsType", goodsType);
			map.put("goodsName", goodsName);
			goodsPrice1=projectService.getGoodsPrice(map);
			
			if(goodsPrice1!=null){
				goodsPrice=Double.parseDouble(goodsPrice1.get("goodsPrice").toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getGoodsPrice error : ", e);
			return new BaseDto(-1, "getGoodsPrice error");
		}

		return new BaseDto(0, goodsPrice);
	}
	
	
	
	
	@RequestMapping("view/query")
	public ModelAndView projectquery() {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(View.Project.QUERY);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("projectquery error : ", e);
		}
		return model;
	}
	@RequestMapping("view/salesproject")
	public ModelAndView salesproject() {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(View.Project.SALESPROJECT);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("salesproject error : ", e);
		}
		return model;
	}

}
