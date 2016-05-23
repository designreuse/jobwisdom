package com.zefun.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zefun.common.consts.App;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.entity.EnterpriseInfo;
import com.zefun.web.entity.UserAccount;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.EnterpriseInfoMapper;
import com.zefun.web.mapper.UserAccountMapper;

/**
 * 用户账户基本模块服务类
 * 
 * @author 张进军
 * @date Sep 18, 2015 4:54:45 PM
 */
@Service
public class EnterpriseService {
	
	/**
	 * 企业信息
	 */
	@Autowired
	private EnterpriseInfoMapper enterpriseInfoMapper;
	/**
	 * 账户表
	 */
	@Autowired
	private UserAccountMapper userAccountMapper;
	
	/**
	 * 员工
	 */
	@Autowired
	private EmployeeInfoMapper employeeInfoMapper;
	
	/**
	 * 
	* @author 老王
	* @date 2016年5月21日 下午7:42:13 
	* @param enterpriseName 企业名称
	* @param enterpriseLinkphone  企业联系电话
	* @param enterpriseLinkname 企业联系人
	* @param storeAccount 企业代号
	* @return  BaseDto
	 */
	@Transactional
	public BaseDto addEnterprise (String enterpriseName, String enterpriseLinkphone, 
			  String enterpriseLinkname, String storeAccount) {
		EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
		enterpriseInfo.setStoreAccount(storeAccount);
		enterpriseInfo.setEnterpriseName(enterpriseName);
		enterpriseInfo.setEnterpriseLinkphone(enterpriseLinkphone);
		enterpriseInfo.setEnterpriseLinkname(enterpriseLinkname);
		enterpriseInfoMapper.insertSelective(enterpriseInfo);
		
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setName(enterpriseLinkname);
		employeeDto.setPhone(enterpriseLinkphone);
		employeeDto.setIsDeleted(1);
		employeeDto.setCreateTime(DateUtil.getCurTime());
		employeeInfoMapper.insert(employeeDto);
		
		// 保存用户信息初始密码123456
		UserAccount userAccount = new UserAccount();
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
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
	}
}
