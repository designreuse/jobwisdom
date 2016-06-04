package com.zefun.wechat.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.web.controller.BaseController;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.AgentInfo;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.SalesmanInfo;
import com.zefun.web.mapper.SalesmanInfoMapper;
import com.zefun.web.mapper.SalesmanRecommendRecordMapper;
import com.zefun.web.service.AgentInfoService;
import com.zefun.web.vo.SalesmanInfoVo;
import com.zefun.wechat.service.SalesmanInfoService;

/**
 * 业务员信息控制器
 * @author lzc
 *
 */
@Controller
public class SalesmanInfoController extends BaseController {
	
	/** 业务员门店关联映射 */
	@Autowired
	private SalesmanRecommendRecordMapper salesmanRecommendRecordMapper;
	
	/** 业务员信息映射 */
	@Autowired
	private SalesmanInfoMapper salesmanInfoMapper;
	
	/** 渠道(代理)信息服务 */
    @Autowired
    private AgentInfoService agentInfoService;
	
	/** 业务员信息服务 */
	@Autowired
	private SalesmanInfoService salesmanInfoService;
	
	
	/**
	 * 业务员入口方法(业务员登录)
	 * 1.根据phone查看是否有该员工账号
	 * 2.如果还没该业务员，重定向到业务员注册页面
	 * 3.如果有该业务员，检查是否绑定openId，没绑定就绑定后给他登录，已绑定就直接登录
	 * @param request  请求
	 * @param response  响应
	 * @return  登录结果
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value = Url.Salesman.ACTION_LOGIN_SALESMAN, method = RequestMethod.GET)
	public ModelAndView salesmanLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
		if (openId == null) {
			return null;
		}
		/**
		 * 根据openId查询该操作人是渠道商还是业务员
		 * if 渠道商  跳到该渠道商业务员列表页面
		 * if 业务员  去往业务员首页
		 */
		AgentInfo agentInfo = agentInfoService.getByOpenId(openId);
		SalesmanInfo salesman = salesmanInfoMapper.selectSalesmanByOpenId(openId);
		SalesmanInfo salesmanByOpenIdIgnoreStatus = salesmanInfoMapper.selectSalesmanByOpenIdIgnoreStatus(openId);
		// if 是渠道商
		if (agentInfo != null && salesman == null) {
			redirectToOther(request, response, Url.Salesman.VIEW_SALESMAN_LISTALL);
			return null;
		}
		//if 是业务员首次登录(删除业务员逻辑移动到此处)
		if (agentInfo == null && salesman == null) {
			//查看该业务员是否状态是删除的
			if (salesmanByOpenIdIgnoreStatus != null && salesmanByOpenIdIgnoreStatus.getIsDelete() == 1) {
				request.setAttribute("tip", "很抱歉,您的账号已删除");
				request.getRequestDispatcher("/404.jsp").forward(request, response);
				return null;
			}
			redirectToOther(request, response, Url.Salesman.VIEW_TOADD_SALESMAN);
			return null;
		}
		/**
		 * 检查业务员停用，删除状态
		 * 停用业务员：停用之后，该业务员无法登陆，列表中显示已停用，可恢复
		 * 删除业务员：删除之后，业务员无法登陆，列表中不显示，数据不能删除(此逻辑已移动到上面)。
		 */
		else if (salesmanByOpenIdIgnoreStatus != null && salesmanByOpenIdIgnoreStatus.getStatus() == 1) {
			request.setAttribute("tip", "您的账号已停用(如需重新启用可联系相应渠道商)");
			request.getRequestDispatcher("/404.jsp").forward(request, response);
			return null;
		}
		//微信分享注册
		setJsapiSignData(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), request);
		ModelAndView mav = new ModelAndView(View.Salesman.VIEW_SALESMAN_ACCOUNT);
		mav.addObject("salesman", salesman);
		//微信分享传递的微信标识参数
		mav.addObject("code", salesman.getOpenId());
		return mav;
	}
	
	/**
	 * 业务员登录成功返回业务员账户页面
	 * @param request  请求
	 * @param response  响应
	 * @return  业务员账户页面
	 */
	@RequestMapping(value = Url.Salesman.VIEW_ACCOUNT_SALESMAN, method = RequestMethod.GET)
	public ModelAndView toSalesmanAccount(HttpServletRequest request, HttpServletResponse response) {
		String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
		if (openId == null) {
			return null;
		}
		//微信分享注册
		setJsapiSignData(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), request);
		ModelAndView mav = new ModelAndView(View.Salesman.VIEW_SALESMAN_ACCOUNT);
		SalesmanInfo salesman = salesmanInfoMapper.selectSalesmanByOpenId(openId);
		mav.addObject("salesman", salesman);
		//微信分享传递的微信标识参数
		mav.addObject("code", salesman.getOpenId());
		return mav;
	}
	
	
	/**
	 * 渠道(代理)商查询门下所有业务员
	 * @param request  请求
	 * @param response  响应
	 * @return  业务员列表页面
	 * @throws IOException
	 */
	@RequestMapping(value = Url.Salesman.VIEW_SALESMAN_LISTALL)
	public ModelAndView salesmanListAll(HttpServletRequest request, HttpServletResponse response) {
		//获得微信标识
		String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
		if (openId == null) {
			return null;
		}
		AgentInfo agentInfo = agentInfoService.getByOpenId(openId);
		if (agentInfo == null) {
            try {
				redirectChannelRegister(request, response);
			} 
            catch (IOException e) {
				e.printStackTrace();
			}
            return null;
        }
		
		return salesmanInfoService.findSalesmanInfoByAgentId(agentInfo);
	}
	
	/**
	 * 首页渠道(代理)商分页查询门下业务员
	 * @param request  请求
	 * @param response  响应
	 * @param salesmanInfoVo  业务员查询参数
	 * @return  业务员列表页面
	 * @throws IOException 
	 */
	@RequestMapping(value = Url.Salesman.VIEW_SALESMAN_HOME)
	public ModelAndView salesmanList(HttpServletRequest request, HttpServletResponse response, SalesmanInfoVo salesmanInfoVo) throws IOException {
		//获得微信标识
		String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
		if (openId == null) {
			return null;
		}
		AgentInfo agentInfo = agentInfoService.getByOpenId(openId);
		//如果查询他还不是代理商重定向到渠道申请界面
		if (agentInfo == null) {
            redirectChannelRegister(request, response);
            return null;
        }
		ModelAndView mav = new ModelAndView(View.Salesman.VIEW_SALESMAN_LIST);
		Page<SalesmanInfo> page = new Page<SalesmanInfo>();
		page.setPageNo(1);
		page.setPageSize(App.System.API_DEFAULT_PAGE_SIZE);
		salesmanInfoVo.setAgentId(agentInfo.getAgentId());
		mav.addObject("salesmanInfoPage", salesmanInfoService.findSalesmanInfoByPage(page, salesmanInfoVo));
		return mav;
	}
	
	/**
	 * ajax分页查询业务员
	 * @param request  请求
	 * @param response  响应
	 * @param salesmanInfoVo  业务员查询参数
	 * @param page  业务员分页查询参数
	 * @return  业务员列表
	 * @throws IOException 
	 */
	@RequestMapping(value = Url.Salesman.ACTION_LIST_SALESMAN, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto ajaxFindSalemanInfoByPage(HttpServletRequest request, HttpServletResponse response, 
			  SalesmanInfoVo salesmanInfoVo, Page<SalesmanInfo> page) throws IOException {
		//获得微信标识
		String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
		if (openId == null) {
			return null;
		}
		AgentInfo agentInfo = agentInfoService.getByOpenId(openId);
		//如果查询他还不是代理商重定向到渠道申请界面
		if (agentInfo == null) {
            redirectChannelRegister(request, response);
            return null;
        }
		salesmanInfoVo.setAgentId(agentInfo.getAgentId());
		page = salesmanInfoService.findSalesmanInfoByPage(page, salesmanInfoVo);
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
	}
	
	/**
	 * 去往业务员新增页面
	 * @param request  请求
	 * @param response  响应
	 * @param agentId  渠道商id(如果是渠道商来该页面会带自身id参数)
	 * @return  业务员新增页面
	 */
	@RequestMapping(value = Url.Salesman.VIEW_TOADD_SALESMAN, method = RequestMethod.GET)
	public ModelAndView toAddSalesman(HttpServletRequest request, HttpServletResponse response, Integer agentId) {
		String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
		if (openId == null) {
			return null;
		}
		ModelAndView mav = new ModelAndView(View.Salesman.VIEW_SALESMAN_TOADD);
		mav.addObject("agentId", agentId);
		return mav;
	}
	
	/**
	 * 渠道商新增业务员/业务员绑定openId
	 * @param request  请求
	 * @param response  响应
	 * @param salesmanInfo  业务员实体
	 * @param agentId  渠道商id(判断是渠道商还是业务员操作的，新加参数)
	 * @return  新增结果
	 */
	@RequestMapping(value = Url.Salesman.ACTION_ADD_SALESMAN, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto addSalesman(HttpServletRequest request, HttpServletResponse response, SalesmanInfo salesmanInfo, Integer agentId) {
		String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
		if (openId == null) {
			return null;
		}
		/**
		 * 需要根据agentId判断该操作人是渠道商还是业务员(有值代表是渠道商)
		 * if 渠道商，新增业务员信息不要保存openId，需要保存agentId，链接至渠道商首页
		 * if 业务员，新增业务员信息需要保存openId，不要保存agentId，链接至业务员首页
		 */
		String url;
		//如果是渠道商(agentId参数有值就证明该操作人是渠道商)
		if (agentId != null) {
			if (salesmanInfoService.decidePhone(salesmanInfo.getPhone())) {
				return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "该账号/手机号已被注册");
			}
			salesmanInfo.setAgentId(agentId);
			salesmanInfo.setCreateTime(new SimpleDateFormat("yyyy-MM-DD HH:mm:ss").format(new Date()));
			url = Url.AgentApply.VIEW_AGENT_APPLY.replaceFirst("/", "");
			salesmanInfoMapper.insertSelective(salesmanInfo);
		}
		//否则就是业务员
		else {
			SalesmanInfo salesman = salesmanInfoMapper.selectSalesmanInfoByPhone(salesmanInfo.getPhone(), salesmanInfo.getPassword());
			//if 根据参数电话查询业务员为空(不能登录)
			if (salesman == null) {
				return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "用户名或密码错误");
			}
			else if (salesman != null && salesman.getOpenId() == null) {
				salesman.setOpenId(openId);
				salesmanInfoMapper.updateByPrimaryKeySelective(salesman);
			}
			url = Url.Salesman.VIEW_ACCOUNT_SALESMAN.replaceFirst("/", "");
		}
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, url);
	}
	
	/**
	 * 查看业务员详细信息
	 * @param request  请求
	 * @param response  响应
	 * @param salesmanId  业务员id
	 * @return  业务员详细信息
	 */
	@RequestMapping(value = Url.Salesman.VIEW_SALESMAN_INFO, method = RequestMethod.GET)
	public ModelAndView getSalesmanInfo(HttpServletRequest request, HttpServletResponse response, Integer salesmanId) {
		String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
		if (openId == null) {
			return null;
		}
		ModelAndView mav = new ModelAndView(View.Salesman.VIEW_SALESMAN_INFO);
		SalesmanInfo salesman = salesmanInfoMapper.selectByPrimaryKey(salesmanId);
		if (salesman != null) {
			salesman.setCountStoreCompleted(salesmanRecommendRecordMapper.selectCountStoreCompleted(salesman.getSalesmanId()));
			salesman.setCountStoreProcessing(salesmanRecommendRecordMapper.selectCountStoreProcessing(salesman.getSalesmanId()));
		}
		mav.addObject("salesman", salesman);
		return mav;
	}
	
	
	/**
	 * 停用业务员
	 * @param salesmanId  业务员id
	 * @return  停用结果
	 */
	@RequestMapping(value = Url.Salesman.ACTION_DEACTIVATE_SALESMAN, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto deactivateSalesman(Integer salesmanId) {
		if (salesmanInfoService.deactivateSalesman(salesmanId)) {
			return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "停用成功");
		}
		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "停用失败,请稍后重试!");
	}
	
	/**
	 * 删除业务员
	 * @param salesmanId  业务员id
	 * @return  删除结果
	 */
	@RequestMapping(value = Url.Salesman.ACTION_DELETE_SALESMAN, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto deleteSalesman(Integer salesmanId) {
		if (salesmanInfoService.deleteSalesman(salesmanId)) {
			return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "删除成功");
		}
		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "删除失败,请稍后重试!");
	}
	
	/**
	 * 重新启用业务员
	 * @param salesmanId  业务员id
	 * @return  停用结果
	 */
	@RequestMapping(value = Url.Salesman.ACTION_REENABLE_SALESMAN, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto reEnableSalesman(Integer salesmanId) {
		if (salesmanInfoService.reEnableSalesman(salesmanId)) {
			return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "停用成功");
		}
		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "停用失败,请稍后重试!");
	}
	
	/**
	 * 重定向到渠道商注册页面
	 * @param request  请求
	 * @param response  响应
	 * @throws IOException 
	 */
	private void redirectChannelRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String contextPath = request.getContextPath();
	    if (contextPath.endsWith("/")) {
	        contextPath = contextPath.substring(0, contextPath.length() - 1);
	    }
	    try {
	        response.sendRedirect(contextPath + Url.AgentApply.VIEW_AGENT_APPLY);
	    }
	    catch (IOException e) {
	        throw e;
	    }
	}
	
	/**
	 * 去往定制重定向路径
	 * @param request  请求
	 * @param response  响应
	 * @param url  定制路径
	 */
	public void redirectToOther(HttpServletRequest request, HttpServletResponse response, String url){
		String contextPath = request.getContextPath();
        if (contextPath.endsWith("/")) {
            contextPath = contextPath.substring(0, contextPath.length() - 1);
        }
        try {
            response.sendRedirect(contextPath + url);
        }
        catch (IOException e) {
            try {
				throw e;
			} 
            catch (IOException e1) {
				e1.printStackTrace();
			}
        }
	}

}
