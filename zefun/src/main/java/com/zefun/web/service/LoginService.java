package com.zefun.web.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.swagger.SystemWebSocketHandler;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeBaseDto;
import com.zefun.web.entity.MemberMenu;
import com.zefun.web.entity.UserAccount;
import com.zefun.web.mapper.AuthorityRequestMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.MemberMenuMapper;
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
	/** 接口权限 */
	@Autowired
	private AuthorityRequestMapper authorityRequestMapper;
	/** 员工处理 */
	@Autowired
	private EmployeeInfoMapper employeeInfoMapper;
	/** redis操作 */
	@Autowired
	private RedisService redisService;
	/** 菜单权限表 */
	@Autowired
	private MemberMenuMapper memberMenuMapper;

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
	 * @param systemWebSocketHandler systemWebSocketHandler
	 * @return 成功返回码0；失败返回其他错误码，返回值为提示语
	 * @throws UnsupportedEncodingException  UnsupportedEncodingException
	 */
	public BaseDto login(HttpServletRequest request, HttpServletResponse response, String username, String storeAccount,
			  String password, SystemWebSocketHandler systemWebSocketHandler) throws UnsupportedEncodingException {
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

		HttpSession sessiion = request.getSession();

		// 登陆成功
		Integer userId = userAccount.getUserId();
		Integer isLogin = (Integer) request.getSession().getServletContext().getAttribute(userId.toString());
		if (isLogin!=null){
		    systemWebSocketHandler.loginOutMessageToUser(userId, new TextMessage("账号异地登陆".getBytes("UTF-8")));
		}
		
		// 确保另外登陆者安全退出
		try {
            Thread.sleep(1000);
        } 
		catch (Exception e) {
            // TODO: handle exception
        }
		request.getSession().getServletContext().setAttribute(userId.toString(), 1);
		sessiion.setAttribute(App.Session.USER_ID, userId);

		int roleId = userAccount.getRoleId();
		// 要查询出该用户所拥有的接口权限,将其放入session中
		List<String> authorUrl = authorityRequestMapper.selectByUserRoleId(userAccount.getRoleId());
		// 将接口权限放入redis中一份
		redisService.del(App.Redis.AUTHORITY_ACCESS_SET_ROLE_PREFIX + roleId);
		redisService.sadd(App.Redis.AUTHORITY_ACCESS_SET_ROLE_PREFIX + roleId,
				  authorUrl.toArray(new String[authorUrl.size()]));
		// 将roleName放入redis中 下面并没从redis中直接取出,是因为可能放入错误的数据 比如新增了权限,人员角色调整.
		redisService.hset(App.Redis.PC_USER_ID_ROLE_HASH, userId, roleId);

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				  + (request.getServerPort() == 80 ? "" : ":" + request.getServerPort()) + path + "/";
		// 查询出该用户所拥有的菜单权限,将其放入session中
		MemberMenu memberMenu = memberMenuMapper.selectMenuByRoleId(userAccount.getRoleId());
		sessiion.setAttribute(App.Session.SYSTEM_HEAD_MENU, memberMenu.getFirstMenu().replace("<%=menuBasePath%>", basePath));
		sessiion.setAttribute(App.Session.SYSTEM_LEFT_SUB_MENU, memberMenu.getSecontMenu().replace("<%=menuBasePath%>", basePath));

		EmployeeBaseDto employeeInfo = employeeInfoMapper.selectBaseInfoByEmployeeId(userId);
		
		if (employeeInfo != null) {
			sessiion.setAttribute(App.Session.STORE_ID, employeeInfo.getStoreId());
			sessiion.setAttribute(App.Session.STORE_NAME, employeeInfo.getStoreName());
			sessiion.setAttribute(App.Session.USER_INFO, employeeInfo);
		}
		sessiion.setAttribute(App.Session.STORE_ACCOUNT, storeAccount);
		sessiion.setAttribute(App.Session.ROLE_ID, roleId);
		sessiion.setAttribute(App.Session.ONE_LOGIN_TIME, 1);
		// 如果登陆成功将挑战的地址首页放入basedto中,用于跳转
		BaseDto dto = new BaseDto();
		dto.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);

		if (roleId == App.System.SYSTEM_ROLE_STORE_BOSS) {
            dto.setMsg(Url.StoreInfo.SHOW_STORE_LIST);
        } 
		else if (roleId == App.System.SYSTEM_ROLE_STORE_EMPLOYEE) {
			dto.setMsg(Url.SystemSetting.VIEW_PERSON_SETTING);
		} 
		else if (roleId == App.System.SYSTEM_ROLE_STORE_MAIN_OWNER) {
			dto.setMsg(Url.Member.VIEW_BASE_MEMBER);
		} 
		else {
			dto.setMsg(Url.SelfCashier.VIEW_HOME);
		}

		return dto;
	}

}
