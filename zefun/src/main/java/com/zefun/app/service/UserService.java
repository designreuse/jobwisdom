package com.zefun.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zefun.app.common.dto.UserInfoDTO;
import com.zefun.app.common.param.BaseParam;
import com.zefun.app.common.param.LoginParam;
import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeBaseDto;
import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.entity.MemberMenu;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.entity.UserAccount;
import com.zefun.web.entity.YzmPageQiniu;
import com.zefun.web.mapper.AuthorityRequestMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.MemberMenuMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.mapper.UserAccountMapper;
import com.zefun.web.mapper.YzmPageQiniuMapper;
import com.zefun.web.service.RedisService;

/**
 * 用户账户基本模块服务类
 * 
 * @author 张进军
 * @date Sep 18, 2015 4:54:45 PM
 */
@Service
public class UserService {
	/** 用户账户表操作类 */
	@Autowired
	private UserAccountMapper userAccountMapper;

	/** redis操作服务类 */
	@Autowired
	private RedisService redisService;

	/** 员工 */
	@Autowired
	private EmployeeInfoMapper employeeInfoMapper;

	/** 验证码 */
	@Autowired
	private YzmPageQiniuMapper yzmPageQiniuMapper;

	/** 门店 */
	@Autowired
	private StoreInfoMapper storeInfoMapper;

	/** 接口权限 */
	@Autowired
	private AuthorityRequestMapper authorityRequestMapper;

	/** 菜单权限表 */
	@Autowired
	private MemberMenuMapper memberMenuMapper;

	/**
	 * 
	 * login:(这里用一句话描述这个方法的作用).
	 * 
	 * @author michael
	 * @param param
	 *            用户登录需要的参数
	 * @return BaseDto 返回用户基本信息 成功返回码0，返回值为用户角色、token、userid；失败返回其他错误码，返回值为提示语
	 * @throws Exception
	 *             抛出可能的异常
	 * @since JDK 1.8
	 */
	public BaseDto login(LoginParam param) throws Exception {
		Map<String, String> mapUser = new HashMap<String, String>();
		mapUser.put("userName", param.getUserName());
		mapUser.put("storeAccount", param.getStoreAccount());
		// 较验用户名是否存在
		UserAccount userAccount = userAccountMapper.selectByUserName(mapUser);
		if (userAccount == null) {
			return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "用户名不存在");
		}

		// 检查用户密码
		if (!StringUtil.md5(param.getPassWord() + userAccount.getPwdSalt()).equals(userAccount.getUserPwd())) {
			// throw new ServiceException(ErrorType.errorUserPwd);
			return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "密码错误");
		}
		int userId = userAccount.getUserId();
		String token = StringUtil.md5(userId + UUID.randomUUID().toString());
		redisService.setMultiToken(String.valueOf(userId), token, param.getDeviceType(), param.getDeviceToken());
		UserInfoDTO uto = new UserInfoDTO();
		uto.setRoleId(userAccount.getRoleId());
		uto.setStoreId(userAccount.getStoreId());
		uto.setUserId(userAccount.getUserId());
		uto.setUserName(userAccount.getUserName());
		BaseDto rto = new BaseDto();
		rto.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
		rto.setMsg(uto);
		return rto;
	}

	/**
	 * 
	 * userList:(基于商店id获取所用用户信息).
	 * 
	 * @author maywant
	 * @param param
	 *            BaseParam
	 * @return BaseDto
	 * @throws Exception
	 *             Exception
	 * @since JDK 1.8
	 */
	public BaseDto userList(BaseParam param) throws Exception {
		List<UserAccount> user = userAccountMapper.getSingleStoreAccount(param.getStoreId());
		if (user == null) {
			return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "商店id错误");
		}
		UserInfoDTO uto = null;
		List<UserInfoDTO> utoList = new ArrayList<UserInfoDTO>();
		;
		setUserDto(uto, utoList, user);
		BaseDto rto = new BaseDto();
		rto.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
		rto.setMsg(utoList);
		return rto;
	}

	/**
	 * 
	 * staffInfo:(根据employeeId获取员工的详细信息).
	 * 
	 * @author michael
	 * @param param
	 *            BaseParam
	 * @return employeeInfo
	 * @throws Exception
	 *             Exception
	 * @since JDK 1.8
	 */

	public BaseDto staffInfo(BaseParam param) throws Exception {
		EmployeeBaseDto employeeInfo = employeeInfoMapper.selectBaseInfoByEmployeeId(param.getEmployeeId());
		if (employeeInfo == null) {
			return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "员工id错误");
		}
		BaseDto rto = new BaseDto();
		rto.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
		rto.setMsg(employeeInfo);
		return rto;
	}

	/**
	 * 
	 * setUserDto:(这里用一句话描述这个方法的作用).
	 * 
	 * @author michael
	 * @param uto
	 *            UserInfoDTO
	 * @param list
	 *            List<UserInfoDTO>
	 * @param user
	 *            List<UserAccount>
	 * @since JDK 1.8
	 */
	void setUserDto(UserInfoDTO uto, List<UserInfoDTO> list, List<UserAccount> user) {
		for (UserAccount users : user) {
			uto = new UserInfoDTO();
			uto.setStoreId(users.getStoreId());
			uto.setUserId(users.getUserId());
			uto.setUserName(users.getUserName());
			uto.setRoleId(users.getRoleId());
			list.add(uto);
		}
	}

	/**
	 * 
	 * @author 高国藩
	 * @date 2016年4月23日 下午4:28:48
	 * @return
	 */
	public Map<String, String> getYzmPage() {
		String pageValue = redisService.srandmember(App.Redis.WEB_PC_YZM_PAGE_SET);
		String pageUrl = "";
		if (pageValue == null || pageValue.isEmpty()) {

			// 如果取出的值为空，更新redis
			List<YzmPageQiniu> yzmPageQiniuList = yzmPageQiniuMapper.selectByAll();

			for (YzmPageQiniu yzmPageQiniu : yzmPageQiniuList) {
				redisService.sadd(App.Redis.WEB_PC_YZM_PAGE_SET, yzmPageQiniu.getPageValue());
				redisService.hset(App.Redis.WEB_PC_YZM_PAGE_HASH, yzmPageQiniu.getPageValue(),
						yzmPageQiniu.getPageUrl());
			}
			pageValue = redisService.srandmember(App.Redis.WEB_PC_YZM_PAGE_SET);
			pageUrl = redisService.hget(App.Redis.WEB_PC_YZM_PAGE_HASH, pageValue);
		} else {
			pageUrl = redisService.hget(App.Redis.WEB_PC_YZM_PAGE_HASH, pageValue);
		}
		Map<String, String> map = new HashMap<>();
		map.put("pageUrl", pageUrl);
		map.put("pageValue", pageValue);
		return map;
	}

	/**
	 * 免费门店注册
	 * 
	 * @param StoreName
	 *            门店名称
	 * @param phoneNumber
	 *            电话号码
	 * @param storeAccount
	 *            门店账户名
	 * @return BaseDto
	 */
	@Transactional
	public BaseDto registerStoreFree(HttpServletRequest request, String storeName, String phoneNumber,
			String storeAccount, Integer phoneYzm) {
		// 判断门店名称是否存在
		int num = storeInfoMapper.isExitsStoreAccount(storeAccount);
		if (num > 0) {
			return new BaseDto(9901, "门店代号已存在!");
		}

		// 保存门店信息
		StoreInfo storeInfo = new StoreInfo();
		storeInfo.setStoreType(2);
		storeInfo.setStoreName(storeName);
		storeInfo.setStoreAccount(storeAccount);
		storeInfo.setStoreLinkname(phoneNumber);
		storeInfo.setStoreLinkphone(phoneNumber);
		storeInfo.setCreateTime(DateUtil.getCurTime());
		storeInfo.setStoreStatus(3);
		storeInfoMapper.insert(storeInfo);

		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setStoreId(storeInfo.getStoreId());
		employeeDto.setName(storeName);
		employeeDto.setPhone(phoneNumber);
		employeeDto.setCreateTime(DateUtil.getCurTime());
		employeeInfoMapper.insert(employeeDto);

		// 保存用户信息初始密码123456
		UserAccount userAccount = new UserAccount();
		userAccount.setStoreId(storeInfo.getStoreId());
		userAccount.setStoreAccount(storeAccount);
		userAccount.setRoleId(1);
		userAccount.setUserName("10000");
		userAccount.setUserId(employeeDto.getEmployeeId());
		String password = StringUtil.md5(StringUtil.md5("123456"));
		String hash = StringUtil.encryptPwd(password);
		password = hash.split(":")[0];
		String salt = hash.split(":")[1];

		userAccount.setUserPwd(password);
		userAccount.setPwdSalt(salt);
		userAccount.setCreateTime(DateUtil.getCurTime());

		userAccountMapper.insert(userAccount);

		HttpSession sessiion = request.getSession();

		// 登陆成功
		Integer userId = userAccount.getUserId();
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
		sessiion.setAttribute(App.Session.SYSTEM_HEAD_MENU, memberMenu.getFirstMenu().replace("{hostname}", basePath));
		sessiion.setAttribute(App.Session.SYSTEM_LEFT_SUB_MENU,
				memberMenu.getSecontMenu().replace("{hostname}", basePath));

		EmployeeBaseDto employeeInfo = employeeInfoMapper.selectBaseInfoByEmployeeId(userId);
		sessiion.setAttribute(App.Session.STORE_ID, employeeInfo.getStoreId());
		sessiion.setAttribute(App.Session.STORE_NAME, employeeInfo.getStoreName());
		sessiion.setAttribute(App.Session.USER_INFO, employeeInfo);
		sessiion.setAttribute(App.Session.ROLE_ID, roleId);
		sessiion.setAttribute(App.Session.ONE_LOGIN_TIME, 2);

		// 如果登陆成功将挑战的地址首页放入basedto中,用于跳转
		BaseDto dto = new BaseDto();
		dto.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);

		dto.setMsg(Url.SelfCashier.VIEW_HOME);

		return dto;

	}
}
