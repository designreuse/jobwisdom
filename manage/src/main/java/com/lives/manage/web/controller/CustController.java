package com.lives.manage.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.lives.manage.common.exception.ServiceException;
import com.lives.manage.common.util.DateUtils;
import com.lives.manage.web.dto.BaseDto;
import com.lives.manage.web.dto.UserInfoDto;
import com.lives.manage.web.entity.CustomerInfo;
import com.lives.manage.web.service.CustService;
import com.lives.manage.web.service.ProjectService;
import com.lives.manage.web.service.UserService;

/**
 * @author 陈端斌
 * @date 2015年6月8日 下午1:02:02
 * 
 */
@Controller
@RequestMapping("/cust")
public class CustController {
	private static final Logger logger = Logger.getLogger(MenuController.class);
	@Autowired
	private CustService custService;
	@Autowired
	private ProjectService projectService;
	@Autowired
    private UserService userService;

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 下午1:02:02 新增用户
	 */
	@RequestMapping(value = Url.Cust.ADD)
	@ResponseBody
	
	public BaseDto addCust(HttpSession session,Integer customerType, String sysName,String customerName,String cardNo,
			Integer parentId, String companyName, String province, String city,String principalName,String principalDuty,
			String area, String addr, String brandName, String phone,String userName) {
		CustomerInfo customerInfo = new CustomerInfo();
		Map<String,Object>map=new HashMap<String, Object>();
		List<Map<String,Object>> listmap=new ArrayList<Map<String,Object>>();
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		try {
			// 参数进行封装
			customerInfo.setCustomerType(customerType);
			customerInfo.setPrincipalName(principalName);
			customerInfo.setPrincipalDuty(principalDuty);
			customerInfo.setCustomerName(customerName);
			customerInfo.setSysName(sysName);
			customerInfo.setCardNo(cardNo);
			customerInfo.setCompanyName(companyName);
			customerInfo.setProvince(province);
			customerInfo.setCity(city);
			customerInfo.setArea(area);
			customerInfo.setAddr(addr);
			customerInfo.setBrandName(brandName);
			customerInfo.setPhone(phone);
			customerInfo.setCreatorId(userInfoDto.getUserId());
			customerInfo.setUserName(userName);
			customerInfo.setCreateTime(DateUtils.getCurTime());
			map.put("goodsName", sysName);
			listmap=projectService.queryGoodsName(map);
			if(listmap.size()>0){
				String time=listmap.get(0).get("goodsDesc").toString();
				Calendar c = Calendar.getInstance(); 
				c.setTime(new Date());
				if(time.equals("1")){
					c.add(Calendar.MONTH, 1);
				}else if(time.equals("2")){
					c.add(Calendar.YEAR, 1);
				}else if(time.equals("3")){
					c.add(Calendar.YEAR, 2);
				}else if(time.equals("4")){
					c.add(Calendar.YEAR, 100);
				}
				Date date = c.getTime();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String mtime=format.format(date);
				customerInfo.setExpireTime(mtime);
			}

			int result=custService.addCust(customerInfo);
			if(result==1){
				return new BaseDto(-1, "公司名称已经存在请重新输入！");
			}else if(result==2){
				return new BaseDto(-2, "客户名称已经存在请重新输入！");
			}else if(result==3){
				return new BaseDto(-3, "公司名称不存在请确认后再输入！");
			}else if(result==9){
				return new BaseDto(-9, "您新增客户的次数已经用完，请先联系管理人员！");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			logger.warn("addMenu error : "+ e.getMsg());
			return new BaseDto(-1, e.getMsg());
		}

		return new BaseDto(0, "ok");

	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 下午1:02:02 修改用户
	 */
	@RequestMapping(value = Url.Cust.UPDATE)
	@ResponseBody
	public BaseDto updateCust(HttpSession session,Integer customerId,Integer customerType, String sysName,String customerName,String cardNo,
			Integer parentId, String companyName, String province, String city,String principalName,String principalDuty,
			String area, String addr, String brandName, String phone, String expireTime, String userName) {
		CustomerInfo customerInfo = new CustomerInfo();
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		try {
			// 参数进行封装
			customerInfo.setCustomerId(customerId);
			customerInfo.setCustomerType(customerType);
			customerInfo.setCustomerName(customerName);
			customerInfo.setSysName(sysName);
			customerInfo.setCompanyName(companyName);
			customerInfo.setCardNo(cardNo);
			customerInfo.setParentId(parentId);
			customerInfo.setProvince(province);
			customerInfo.setPrincipalName(principalName);
			customerInfo.setPrincipalDuty(principalDuty);
			customerInfo.setCity(city);
			customerInfo.setArea(area);
			customerInfo.setAddr(addr);
			customerInfo.setBrandName(brandName);
			customerInfo.setPhone(phone);
			customerInfo.setExpireTime(expireTime);
			//customerInfo.setIsPay(isPay);
			customerInfo.setUserName(userName);
			customerInfo.setUpdateTime(DateUtils.getCurTime());
			customerInfo.setUpdatorId(userInfoDto.getUserId());

			int result=custService.updateCust(customerInfo);
			if(result==1){
				return new BaseDto(-1, "公司名称已经存在请重新输入！");
			}else if(result==2){
				return new BaseDto(-2, "客户名称已经存在请重新输入！");
			}else if(result==3){
				return new BaseDto(-2, "公司名称不存在请确认后再输入！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateCust error : ", e);
			return new BaseDto(-1, "updateCust error");
		}

		return new BaseDto(0, "ok");

	}
	
	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 下午1:02:02 修改用户
	 */
	@RequestMapping(value = Url.Cust.DELETE)
	@ResponseBody
	public BaseDto deleteCust(HttpSession session,Integer customerId) {
		CustomerInfo customerInfo = new CustomerInfo();
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		try {
			// 参数进行封装
			customerInfo.setUpdatorId(userInfoDto.getUserId());
			customerInfo.setCustomerId(customerId);
			customerInfo.setIsDelete(1);
			custService.updateCust(customerInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateCust error : ", e);
			return new BaseDto(-1, "updateCust error");
		}

		return new BaseDto(0, "ok");

	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 下午1:02:02 用户查询
	 */
	@RequestMapping(value = Url.Cust.QUERY)
	@ResponseBody
	public JSONObject queryCustInfo(@RequestBody Map<String ,Object> map,HttpSession session) {
     List<Map<String ,Object>>map1=new ArrayList<Map<String,Object>>();
     UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		try {
		    //如果不是超级管理员只查询属于自己的新增客户
		    if(userInfoDto.getRoleId()!=1){
		        map.put("userId", userInfoDto.getUserId());
		    }
			
			map1 = custService.queryCustInfo(map);
			int count =custService.queryCustCount(map);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("total", count);
			jsonObject.put("rows", map1);
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("queryCustInfo error : ", e);
		}
		return null;
	}

	
	/**
	 * @author 陈端斌
	 * @date 2015年7月1日 下午1:02:02 获取公司名称
	 */
	@RequestMapping(value = Url.Cust.GETCOMPANYNAME)
	@ResponseBody
	public BaseDto getCompanyName() {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		String [] companyNameList=null;
		try {
			 list=custService.getCompanyName();
			 companyNameList=new String[list.size()];
			 for (int i = 0; i < list.size(); i++) {
				 String companyName=list.get(i).get("companyName").toString();
				 companyNameList[i]=companyName;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getCompanyName error : ", e);
			return new BaseDto(-1, "getCompanyName error");
		}

		return new BaseDto(0, companyNameList);

	}
	
	/**
	 * @author 陈端斌
	 * @date 2015年7月1日 下午1:02:02 获取客户名称
	 */
	@RequestMapping(value = Url.Cust.GETCUSTNAME)
	@ResponseBody
	public BaseDto getcustomerName() {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		String [] custNameList=null;
		try {
			 list=custService.getcustomerName();
			 custNameList=new String[list.size()];
			 for (int i = 0; i < list.size(); i++) {
				 String customerName=list.get(i).get("customerName").toString();
				 custNameList[i]=customerName;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getcustomerName error : ", e);
			return new BaseDto(-1, "getcustomerName error");
		}
		return new BaseDto(0, custNameList);
	}
	/**
	 * @author 陈端斌
	 * @date 2015年7月9日 下午1:02:02 获取项目价格
	 */
	@RequestMapping(value = Url.Cust.GETGOODSPRICE)
	@ResponseBody
	public BaseDto getGoodsPrice(String sysName) {
		List<Map<String,Object>> listmap=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String, Object>();
		Double goodsPrice=0.00;
		try {
			map.put("goodsName", sysName);
			listmap=projectService.queryGoodsName(map);
			if(listmap.size()>0){
			 goodsPrice=Double.parseDouble(listmap.get(0).get("goodsPrice").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getGoodsPrice error : ", e);
			return new BaseDto(-1, "getGoodsPrice error");
		}
		return new BaseDto(0, goodsPrice);
	}
	//-------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 
	* @author chendb
	* @date 2015年10月31日 下午5:26:57
	* @param session
	* @param type
	* @return
	 */
	@RequestMapping("view/query")
	public ModelAndView queryView(HttpSession session,String type) {
		ModelAndView model=new ModelAndView();
		List<Map<String,Object>> listmap=new ArrayList<Map<String,Object>>();
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			listmap=projectService.queryGoodsName(map);
			model.addObject("type", type);
			model.addObject("userId", userInfoDto.getUserId());//返回用户登录id只能查询自己新增的客户
			model.addObject("listmap", listmap);
			model.setViewName(View.Cust.QUERY);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return model;
	}
	
	/**
	 * 客户新增页面
	 * @author 陈端斌
	 * @date 2015年6月8日 下午1:02:02 
	 */
	@RequestMapping("view/addcust")
	public ModelAndView addView(HttpSession session) {
		ModelAndView model=new ModelAndView();
		List<Map<String,Object>> listmap=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String, Object>();
		try {
		    UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		    listmap=projectService.queryGoodsName(map);
            model.addObject("listmap", listmap);
		    Map<String,Object> mapinfo=userService.getUserInfo(userInfoDto.getUserId());
			model.addObject("money", mapinfo.get("money"));
			model.setViewName(View.Cust.ADD);
		} catch (Exception e) {
		}
		return model;
	}
	/**
	 * 客户修改页面
	 * @author 陈端斌
	 * @date 2015年7月8日 下午1:02:02 
	 */
	@RequestMapping("view/updatecust")
	public ModelAndView updateView(Integer customerId) {
		ModelAndView model=new ModelAndView();
		List<Map<String,Object>> listmap=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			map.put("customerId", customerId);
			model.addObject("infomap", custService.getCustomerInfo(map));
			listmap=projectService.queryGoodsName(null);
			model.addObject("listmap", listmap);
			model.setViewName(View.Cust.UPDATE);
		} catch (Exception e) {
		}
		return model;
	}
	// -------------------------------------------------------------测试方法-------------------------------------------------------------------------------------

}
