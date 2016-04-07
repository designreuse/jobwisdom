package com.lives.manage.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lives.manage.common.consts.Url;
import com.lives.manage.common.consts.View;
import com.lives.manage.common.util.DateUtils;
import com.lives.manage.web.dto.BaseDto;
import com.lives.manage.web.dto.CustClueDto;
import com.lives.manage.web.dto.CustInfoDto;
import com.lives.manage.web.dto.UserInfoDto;
import com.lives.manage.web.entity.CustomerClue;
import com.lives.manage.web.service.CustClueService;

/**
 * @author 陈端斌
 * @date 2015年6月10日 下午1:02:02
 * 
 */
@Controller
@RequestMapping("/custclue")
public class CustClueController {

	private static final Logger logger = Logger.getLogger(MenuController.class);
	@Autowired
	private CustClueService custClueService;

	/**
	 * @author 陈端斌
	 * @date 2015年6月10日 下午1:02:02 新增用户
	 */
	@RequestMapping(value = Url.CustClue.ADD)
	@ResponseBody
	public BaseDto addCustClue(HttpSession session,String customerName, String companyName,
			String province, String city, String area, String addr,
			String phone, String principalName,String principalDuty ,String customerType,
			Integer storeNumber, String brandLevel, String clueSource,
			String clueStatus) {
		CustomerClue customerClue = new CustomerClue();
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		try {
			customerClue.setCustomerName(customerName);
			customerClue.setCompanyName(companyName);
			customerClue.setProvince(province);
			customerClue.setCity(city);
			customerClue.setArea(area);
			customerClue.setAddr(addr);
			customerClue.setCreateTime(DateUtils.getCurTime());
			customerClue.setPrincipalName(principalName);
			customerClue.setPrincipalDuty(principalDuty);
			customerClue.setCustomerType(customerType);
			customerClue.setStoreNumber(storeNumber);
			customerClue.setBrandLevel(brandLevel);
			customerClue.setClueSource(clueSource);
			customerClue.setClueStatus(clueStatus);
			customerClue.setPhone(phone);
			customerClue.setCreatorId(userInfoDto.getUserId());
			customerClue.setOwnerId(userInfoDto.getUserId());
			customerClue.setIsPublic(0);
			custClueService.addCustClue(customerClue);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addCustClue error : ", e);
			return new BaseDto(-1, "addCustClue error");
		}

		return new BaseDto(0, "ok");
	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月10日 下午1:02:02 修改线索信息
	 */
	@RequestMapping(value = Url.CustClue.UPDATE)
	@ResponseBody
	public BaseDto updateCustClueInfo(HttpSession session,Integer clueId, String customerName, String companyName,
			String province, String city, String area, String addr,
			String phone, String principalName,String principalDuty ,String customerType,
			Integer storeNumber, String brandLevel, String clueSource,
			String clueStatus) {
		CustomerClue customerClue = new CustomerClue();
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		try {
			customerClue.setClueId(clueId);
			customerClue.setCustomerName(customerName);
			customerClue.setCompanyName(companyName);
			customerClue.setProvince(province);
			customerClue.setCity(city);
			customerClue.setAddr(addr);
			customerClue.setPrincipalName(principalName);
			customerClue.setPrincipalDuty(principalDuty);
			customerClue.setCustomerType(customerType);
			customerClue.setStoreNumber(storeNumber);
			customerClue.setBrandLevel(brandLevel);
			customerClue.setClueSource(clueSource);
			customerClue.setClueStatus(clueStatus);
			customerClue.setPhone(phone);
			custClueService.updateCustClue(customerClue);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateCustClueInfo error : ", e);
			return new BaseDto(-1, "updateCustClueInfo error");
		}
		return new BaseDto(0, "ok");
	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月10日 下午1:02:02 修改线索信息所属人（入公海  或者取回放到自己的线索里面 ）
	 */
	@RequestMapping(value = Url.CustClue.UPDATESS)
	@ResponseBody
	public BaseDto updateClueSs(HttpSession session,String idArray ,Integer isPublic) {
		CustomerClue customerClue = new CustomerClue();
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		try {
			if(idArray.length()>0){
			String[] ids= idArray.split(",");
			for (int i = 0; i < ids.length; i++) {
				String id=ids[i];
				int clueId=Integer.parseInt(id);
				customerClue.setClueId(clueId);
				//判断当要取道放在自己的线索里面时候  把归属人弄成自己
				if(isPublic==0){
					customerClue.setOwnerId(userInfoDto.getUserId());
				}else{
					customerClue.setOwnerId(null);
					}
				customerClue.setIsPublic(isPublic);
				if(isPublic==0){
					customerClue.setOwnerId(userInfoDto.getUserId());
				}
				custClueService.updateClueSs(customerClue);
			}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateClueSs error : ", e);
			return new BaseDto(-1, "updateClueSs error");
		}
		return new BaseDto(0, "ok");
	}
	
	@RequestMapping(value = Url.CustClue.DELETECLUE)
	@ResponseBody
	public BaseDto deleteClue(HttpSession session,Integer clueId) {
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");
		try {
			
			custClueService.deleteClue(clueId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("deleteClue error : ", e);
			return new BaseDto(-1, "deleteClue error");
		}
		return new BaseDto(0, "ok");
	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月10日 下午1:02:02 
	 */
	@RequestMapping(value = Url.CustClue.QUERY)
	@ResponseBody
	public JSONObject queryCustClueInfo(@RequestBody Map<String, Object> map, HttpSession session) {
		List<CustClueDto> CustClueList = null;
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfoDto");

		try {
			map.put("ownerId", userInfoDto.getUserId());
			map.put("isPublic", 0);
			
			CustClueList = custClueService.queryCustClue(map);
			int count  = custClueService.queryCount(map);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("total", count);
			jsonObject.put("rows", CustClueList);
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("queryCustClueInfo error : ", e);
		}
		return null;
	}
	
	@RequestMapping(value = Url.CustClue.PUBLICQUERY)
	@ResponseBody
	public JSONObject publicQuery(@RequestBody Map<String, Object> map) {
		List<CustClueDto> CustClueList = null;
		try {
			
			map.put("isPublic", 1);
			CustClueList = custClueService.queryCustClue(map);
			int count  = custClueService.queryCount(map);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("total", count);
			jsonObject.put("rows", CustClueList);
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("queryCustClueInfo error : ", e);
		}
		return null;
	}
	
	

	/**
	 * @author 陈端斌
	 * @date 2015年6月10日 下午1:02:02 用用户理页线索管理页面
	 */

	@RequestMapping("view/query")
	public ModelAndView queryView() {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(View.CustClue.QUERY);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("queryView error : ", e);
		}
		return model;
	}
	
	@RequestMapping("view/publicquery")
	public ModelAndView publicquery() {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(View.CustClue.PUBLICQUERY);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("publicquery error : ", e);
		}
		return model;
	}
	
	
}
