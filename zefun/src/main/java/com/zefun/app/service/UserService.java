package com.zefun.app.service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zefun.app.common.dto.UserInfoDTO;
import com.zefun.app.common.param.BaseParam;
import com.zefun.app.common.param.LoginParam;
import com.zefun.common.consts.App;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeBaseDto;
import com.zefun.web.entity.UserAccount;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.UserAccountMapper;
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
	
    /** 员工*/
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;
	/**
	 * 
	 * login:(这里用一句话描述这个方法的作用).  
	 * @author michael 
	 * @param param 用户登录需要的参数
	 * @return BaseDto 返回用户基本信息 成功返回码0，返回值为用户角色、token、userid；失败返回其他错误码，返回值为提示语
	 * @throws Exception  抛出可能的异常
	 * @since JDK 1.8
	 */
	public  BaseDto login(LoginParam param) throws Exception {
        //较验用户名是否存在
		UserAccount userAccount = userAccountMapper.selectByUserName(param.getUserName());
		if (userAccount == null) {
			return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "用户名不存在");
		}
        
		// 检查用户密码
		if (!StringUtil.md5(param.getPassWord() + userAccount.getPwdSalt()).equals(userAccount.getUserPwd())) {
//			throw new ServiceException(ErrorType.errorUserPwd);
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
	 * @author maywant  
	 * @param param BaseParam 
	 * @return BaseDto
	 * @throws Exception  
	 * @since JDK 1.8
	 */
	public BaseDto userList(BaseParam param) throws Exception{
		List<UserAccount> user = userAccountMapper.getSingleStoreAccount(param.getStoreId());
		if (user==null){
			return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "商店id错误");  
		}
		UserInfoDTO uto = null;
		List<UserInfoDTO> utoList = new ArrayList<UserInfoDTO>();;
		setUserDto(uto, utoList, user);
		BaseDto rto = new BaseDto();
		rto.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
		rto.setMsg(utoList);
		return rto;
	}
	
	/**
	 * 
	 * staffInfo:(根据employeeId获取员工的详细信息).   
	 * @author michael 
	 * @param param BaseParam
	 * @return employeeInfo
	 * @throws Exception  
	 * @since JDK 1.8
	 */
	
	public BaseDto staffInfo(BaseParam param) throws Exception{
		EmployeeBaseDto employeeInfo = employeeInfoMapper.selectBaseInfoByEmployeeId(param.getEmployeeId());
		if (employeeInfo==null){
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
	 * @author michael 
	 * @param uto UserInfoDTO
	 * @param list List<UserInfoDTO>
	 * @param user   List<UserAccount>
	 * @since JDK 1.8
	 */
	void setUserDto(UserInfoDTO uto, List<UserInfoDTO> list, List<UserAccount> user){
		for (UserAccount users:user){
		    uto = new UserInfoDTO();
		    uto.setStoreId(users.getStoreId());
		    uto.setUserId(users.getUserId());
		    uto.setUserName(users.getUserName());
		    uto.setRoleId(users.getRoleId());
		    list.add(uto);
		}
	}
}
