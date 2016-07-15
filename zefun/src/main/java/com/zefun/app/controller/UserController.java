/**  
 * Project Name:zefun  
 * File Name:StaffCentreController.java  
 * Package Name:com.zefun.app.controller  
 * Date:2016年3月23日上午11:46:02  
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.  
 *  
*/
/**  
 * Project Name:zefun  
 * File Name:StaffCentreController.java  
 * Package Name:com.zefun.app.controller  
 * Date:2016年3月23日上午11:46:02  
 */

package com.zefun.app.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import com.zefun.app.common.param.BaseParam;
import com.zefun.app.common.param.LoginParam;
import com.zefun.app.service.UserService;
import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.utils.VerifyCodeUtils;
import com.zefun.web.dto.BaseDto;

/**
 * 用户基本模块控制类
 * 
 * @author michael
 * @date Sep 18, 2015 4:48:32 PM
 */
@Controller
@Api(value = "User", description = "用户模块", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	/** 用户基本模块服务对象 */
	@Autowired
	private UserService userService;

	/**
	 * 
	 * login:(这里用一句话描述这个方法的作用).
	 * 
	 * @author michael
	 * @param param
	 *            用户登录需要的参数
	 * @return 成功返回码0，返回值为用户角色、token、userid；失败返回其他错误码，返回值为提示语
	 * @throws Exception
	 *             Exception
	 * @since JDK 1.8
	 */
	@ApiOperation(value = "用户登录管理", notes = "用户登录管理(用于数据同步)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = Url.App.LOGIN, method = RequestMethod.POST)
	@ApiResponses(value = {
			    @ApiResponse(code = App.System.API_RESULT_CODE_FOR_SUCCEES, message = "用户登陆", response = String.class),
			    @ApiResponse(code = App.System.API_RESULT_CODE_FOR_FAIL, message = "系统错误", response = String.class) })
	@ResponseBody
	public BaseDto login(@ApiParam(value = "Json参数", name = "login", required = true) @RequestBody LoginParam param)
			throws Exception {
		return userService.login(param);
	}

	/**
	 * 
	 * userList:(定义获取所有用户的接口).
	 * 
	 * @author maywant
	 * @param param
	 *            获取所有用户信息的参数
	 * @return 返回商店所有用户的信息
	 * @throws Exception
	 *             可能抛出来的异常
	 * @since JDK 1.8
	 */
	@ApiOperation(value = "根据storeId获取所有用户", notes = "获取所用用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = Url.App.GET_USER_LIST, method = RequestMethod.POST)
	@ApiResponses(value = {
			    @ApiResponse(code = App.System.API_RESULT_CODE_FOR_SUCCEES, message = "所有用户", response = String.class),
			    @ApiResponse(code = App.System.API_RESULT_CODE_FOR_FAIL, message = "系统错误", response = String.class) })
	@ResponseBody
	public BaseDto userList(
			    @ApiParam(value = "Json参数", name = "userList", required = true) @RequestBody BaseParam param)
			throws Exception {
		return userService.userList(param);
	}

	/**
	 * 
	 * staffInfo:(通过员工号查询员工详细信息).
	 * 
	 * @author michael
	 * @param param
	 *            BaseParam
	 * @return BaseDto
	 * @throws Exception
	 *             Exception
	 * @since JDK 1.8
	 */
	@ApiOperation(value = "根据employeeId获取员工的详细信息", notes = "根据employeeId获取员工的详细信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = Url.App.STAFF_INFO, method = RequestMethod.POST)
	@ApiResponses(value = {
			    @ApiResponse(code = App.System.API_RESULT_CODE_FOR_SUCCEES, message = "获取用户", response = String.class),
			    @ApiResponse(code = App.System.API_RESULT_CODE_FOR_FAIL, message = "系统错误", response = String.class) })
	@ResponseBody
	public BaseDto staffInfo(
			    @ApiParam(value = "Json参数", name = "staffInfo", required = true) @RequestBody BaseParam param)
			throws Exception {
		return userService.staffInfo(param);
	}

	/**
	 * 获取验证码
	 * 
	 * @author 老王
	 * @date 2016年4月23日 下午4:28:20
	 * @param request 返回
	 * @param response 请求
	 */
	@RequestMapping(value = Url.App.GET_YZM_PAGE)
	public void getYzmPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			VerifyCodeUtils.outputVerifyImage(71, 35, request, response, 4);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 免费门店注册
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            返回
	 * @param StoreName
	 *            门店名称
	 * @param phoneNumber
	 *            电话号码
	 * @param storeAccount
	 *            门店账户名
	 * @param phoneYzm
	 *            短信验证码
	 * @return BaseDto
	 */
	@RequestMapping(value = Url.App.REGISTER_STORE_FREE, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto registerStoreFree(HttpServletRequest request, HttpServletResponse response, String StoreName,
			    String phoneNumber, String storeAccount, Integer phoneYzm) {

		return userService.registerStoreFree(request, StoreName, phoneNumber, storeAccount, phoneYzm);
	}
}
