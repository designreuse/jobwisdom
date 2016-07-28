package com.zefun.web.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import com.zefun.common.consts.App;
import com.zefun.common.swagger.SystemWebSocketHandler;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeBaseDto;
import com.zefun.web.entity.AccountRoleInfo;
import com.zefun.web.entity.MenuIdQuote;
import com.zefun.web.entity.UserAccount;
import com.zefun.web.mapper.AccountRoleInfoMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.MenuIdQuoteMapper;
import com.zefun.web.mapper.UserAccountMapper;

/**
 * 登陆操作类
 * 
 * @author 高国藩
 * @date 2015年9月20日 上午10:10:50
 */
@Service
public class LoginService {

	/** 用户账户表操作类 */
	@Autowired
	private UserAccountMapper userAccountMapper;
//	/** 接口权限 */
//	@Autowired
//	private AuthorityRequestMapper authorityRequestMapper;
	/** 员工处理 */
	@Autowired
	private EmployeeInfoMapper employeeInfoMapper;
//	/** redis操作 */
//	@Autowired
//	private RedisService redisService;
//	/** 菜单权限表 */
//	@Autowired
//	private MemberMenuMapper memberMenuMapper;
	
	/** 角色菜单权限表 */
	@Autowired
	private AccountRoleInfoMapper accountRoleInfoMapper;

	/** 菜单权限表 */
    @Autowired
    private MenuIdQuoteMapper menuIdQuoteMapper;
	
	/**
	 * 登陆服务方法
	 * 
	 * @author 高国藩
	 * @date 2015年9月20日 上午10:14:39
	 * @param request  氢气
	 * @param response 结果封装
	 * @param username 用户名
	 * @param storeAccount 门店账号
	 * @param password  密码
	 * @param verification 验证码
	 * @param systemWebSocketHandler systemWebSocketHandler
	 * @return 成功返回码0；失败返回其他错误码，返回值为提示语
	 * @throws UnsupportedEncodingException  UnsupportedEncodingException
	 */
	public BaseDto login(HttpServletRequest request, HttpServletResponse response, String username, String storeAccount,
			  String password, String verification, SystemWebSocketHandler systemWebSocketHandler) throws UnsupportedEncodingException {
		HttpSession sessiion = request.getSession();
		
		String yzmCode = sessiion.getAttribute("yzmCode").toString();
		
		if (!verification.equalsIgnoreCase(yzmCode)) {
			return new BaseDto(9003, "验证码不一致，请重新输入！");
		}
		
		Map<String, String> mapUser = new HashMap<String, String>();
		mapUser.put("userName", username);
		mapUser.put("storeAccount", storeAccount);
		UserAccount userAccount = userAccountMapper.selectByUserName(mapUser);
		
		if (userAccount == null) {
			return new BaseDto(9001, "亲，确定没记错企业号或工号？");
		}

		// 检查用户密码
		if (!StringUtil.md5(password + userAccount.getPwdSalt()).equals(userAccount.getUserPwd())) {
			return new BaseDto(9002, "密码不对，努力回忆下");
		}
		
		// 登陆成功
		Integer userId = userAccount.getUserId();
		Integer isLogin = (Integer) request.getSession().getServletContext().getAttribute(userId.toString());
		if (isLogin!=null){
		    systemWebSocketHandler.loginOutMessageToUser(userId, new TextMessage("账号异地登陆".getBytes("UTF-8")));
		}
		try {
            Thread.sleep(1000);
        } 
		catch (Exception e) {
            // TODO: handle exception
        }
		request.getSession().getServletContext().setAttribute(userId.toString(), 1);
		sessiion.setAttribute(App.Session.USER_ID, userId);

		int roleId = userAccount.getRoleId();


		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				  + (request.getServerPort() == 80 ? "" : ":" + request.getServerPort()) + path + "/";
	
		Map<String, Object> mapMenu = new HashMap<String, Object>();
		mapMenu.put("roleId", roleId);
		mapMenu.put("storeAccount", storeAccount);
		List<AccountRoleInfo> selectAccountMenuByRoleId = accountRoleInfoMapper.selectAccountMenuByRoleId(mapMenu);
		
	    ArrayList<Integer> listFirst = new ArrayList<Integer>();
	    ArrayList<Integer> listSecond = new ArrayList<Integer>();
	    // 存放权限菜单的ID集合
	    Stream.of(selectAccountMenuByRoleId.get(0).getFristMenu().split(",")).forEach(str -> listFirst.add(Integer.parseInt(str.trim())));
	    Stream.of(selectAccountMenuByRoleId.get(0).getSecondMenu().split(",")).forEach(str -> listSecond.add(Integer.parseInt(str.trim())));
	    
		mapMenu.put("menuId", listFirst);
		List<MenuIdQuote> idQuotes = menuIdQuoteMapper.selectMemberFirts(mapMenu);
		
		StringBuffer firstSb =  new StringBuffer();
		firstSb.append("<ul class='left_nav'>");
		
        StringBuffer secontSb =  new StringBuffer();
        secontSb.append("<div class='left_nav_2' style='height: 840px;'>");
        // 封装二级菜单
        idQuotes.stream().forEach(f -> {
                int index = Integer.parseInt(Jsoup.parse(f.getMenuHtml()).select("li").attr("index").trim())-1;
                List<String> selectMenu = new ArrayList<>();
                secontSb.append("<ul index='"+index+"'>");
                f.getMenuIdQuotes().stream().filter(s -> listSecond.contains(s.getMenuId())).collect(Collectors.toList()).stream().forEach(s -> {
                        selectMenu.add(Jsoup.parse(s.getMenuHtml()).select("a").attr("href"));
                        secontSb.append(s.getMenuHtml().replace("<%=menuBasePath%>", basePath));
                    });
                secontSb.append("</ul>");
                firstSb.append(Jsoup.parse(f.getMenuHtml()).select("li").attr("url", "<%=url%>")
                        .toString().replace("<%=menuBasePath%>", basePath)
                        .replace("<%=url%>", selectMenu.size()>0?selectMenu.get(0):"").replace("<%=menuBasePath%>", basePath));
            });
		firstSb.append("</ul>");
		sessiion.setAttribute(App.Session.SYSTEM_HEAD_MENU, firstSb.toString());
        secontSb.append("</div>");
        sessiion.setAttribute(App.Session.SYSTEM_LEFT_SUB_MENU, secontSb.toString());
        Document document = Jsoup.parse(secontSb.toString());
        String url = document.select("div").eq(0).select("ul").eq(0).select("a").eq(0).attr("href");
		EmployeeBaseDto employeeInfo = employeeInfoMapper.selectBaseInfoByEmployeeId(userId);
		AccountRoleInfo selectByPrimaryKey = accountRoleInfoMapper.selectByPrimaryKey(roleId);
		if (employeeInfo != null) {
			sessiion.setAttribute(App.Session.STORE_ID, employeeInfo.getStoreId());
			sessiion.setAttribute(App.Session.STORE_NAME, employeeInfo.getStoreName());
			sessiion.setAttribute(App.Session.USER_INFO, employeeInfo);
		}
		sessiion.setAttribute(App.Session.STORE_ACCOUNT, storeAccount);
		sessiion.setAttribute(App.Session.ROLE_ID, selectByPrimaryKey.getRoleId());
		sessiion.setAttribute(App.Session.ACCOUNT_ROLE_ID, roleId);
		sessiion.setAttribute(App.Session.ONE_LOGIN_TIME, 1);
		// 如果登陆成功将挑战的地址首页放入basedto中,用于跳转
		BaseDto dto = new BaseDto();
		dto.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
		dto.setMsg(url.replaceAll(basePath, ""));

		return dto;
	}

}
