package com.zefun.wechat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.web.controller.BaseController;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.AgentFollow;
import com.zefun.wechat.service.AgentFollowService;

/**
 * 渠道跟踪控制器
* @author DavidLiang
* @date 2016年1月18日 下午5:59:18
 */
@Controller
public class AgentFollowController extends BaseController {
	
	/** 渠道跟踪服务 */
	@Autowired
	private AgentFollowService agentFollowService;
	
	/**
	 * 新增渠道跟踪记录
	* @author DavidLiang
	* @date 2016年1月18日 下午6:24:50
	* @param request  请求
	* @param response  响应
	* @param agentFollow  参数:渠道跟踪记录实体
	* @return  新增相关信息BaseDto
	 */
	@RequestMapping(value = Url.AgentFollow.ACTION_ADD_AGENTFOLLOW, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto addAgentFollow(HttpServletRequest request, HttpServletResponse response, AgentFollow agentFollow) {
		//获得微信标识
		String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
		if (openId == null) {
			return null;
		}
		agentFollow.setOpenId(openId);
		if (agentFollowService.addAgentFollow(agentFollow)) {
			return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "新增成功");
		}
		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "新增失败");
	}
	
	/**
	 * 删除渠道跟踪记录
	* @author DavidLiang
	* @date 2016年1月18日 下午8:00:26
	* @param request  请求
	* @param response  响应
	* @param agentFollowId  渠道跟踪记录id
	* @return  删除相关信息BaseDto
	 */
	@RequestMapping(value = Url.AgentFollow.ACTION_DELETE_AGENTFOLLOW, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto deleteAgentFollow(HttpServletRequest request, HttpServletResponse response, Integer agentFollowId) {
		//获得微信标识
		String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
		if (openId == null) {
			return null;
		}
		if (agentFollowService.deleteAgentFollow(agentFollowId, openId)) {
			new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "删除成功");
		}
		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "删除失败");
	}

}
