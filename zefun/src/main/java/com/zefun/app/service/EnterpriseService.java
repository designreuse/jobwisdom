package com.zefun.app.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.dto.EnterpriseInfoDto;
import com.zefun.web.entity.CouponInfo;
import com.zefun.web.entity.EnterpriseAccount;
import com.zefun.web.entity.EnterpriseInfo;
import com.zefun.web.entity.MemberLevel;
import com.zefun.web.entity.MemberLevelDiscount;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.entity.UserAccount;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.EnterpriseAccountMapper;
import com.zefun.web.mapper.EnterpriseInfoMapper;
import com.zefun.web.mapper.MemberLevelDiscountMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.UserAccountMapper;

/**
 * 用户账户基本模块服务类
 * 
 * @author 小高
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
     * 企业账户
     */
    @Autowired
    private EnterpriseAccountMapper enterpriseAccountMapper;
	
    /** 会员等级数据操作对象 */
    @Autowired
    private MemberLevelMapper memberLevelMapper;
    /** 会员折扣操作对象*/
    @Autowired
    private MemberLevelDiscountMapper memberLevelDiscountMapper;
    
    /**
     * 查询所有的门店记录
    * @author 老王
    * @date 2016年6月6日 下午2:03:01 
    * @return ModelAndView
     */
    public ModelAndView showEnterprise () {
    	ModelAndView mav = new ModelAndView(View.Enterprise.ADD_ENTERPRISE);
    	Map<String, Object> params = new HashMap<>();
    	Page<EnterpriseInfoDto> pages = selelctPages(params, 1, 20);
    	mav.addObject("pages", pages);
    	return mav;
    }
    
    /**
     * 分页查询
    * @author 老王
    * @date 2016年6月6日 下午2:17:58 
    * @param params 参数
    * @param pageNo 页数
    * @param pageSize 数量
    * @return Page<EnterpriseInfoDto>
     */
    public Page<EnterpriseInfoDto> selelctPages (Map<String, Object> params, int pageNo, int pageSize) {
    	Page<EnterpriseInfoDto> page = new Page<EnterpriseInfoDto>();
		page.setParams(params);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		List<EnterpriseInfoDto> dtoList = enterpriseInfoMapper.selectByPage(page);
		page.setResults(dtoList);
        return page;
    }
    
	/**
	 * 
	* @author 老王
	* @date 2016年5月21日 下午7:42:13 
	* @param enterpriseName 企业名称
	* @param enterpriseLinkphone  企业联系电话
	* @param enterpriseLinkname 企业联系人
	* @param storeAccount 企业代号
	* @param enterpriseProvince 企业省份
	* @param enterpriseCity 企业城市
	* @param enterpriseAddress 企业详细地址
	* @param enterpriseEdition 企业版本
	* @param useTime 使用时间
	* @param userId userId
	* @param enterpriseInfoId 标识
	* @return  BaseDto
	 */
	@Transactional
	public BaseDto addEnterprise (String enterpriseName, String enterpriseLinkphone, 
			  String enterpriseLinkname, String storeAccount, String enterpriseProvince, String enterpriseCity, String enterpriseAddress,
			  Integer enterpriseEdition, Integer useTime, Integer enterpriseInfoId, Integer  userId) {
	  
		EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
		enterpriseInfo.setStoreAccount(storeAccount);
		enterpriseInfo.setEnterpriseName(enterpriseName);
		enterpriseInfo.setEnterpriseLinkphone(enterpriseLinkphone);
		enterpriseInfo.setEnterpriseLinkname(enterpriseLinkname);
		enterpriseInfo.setEnterpriseProvince(enterpriseProvince);
		enterpriseInfo.setEnterpriseCity(enterpriseCity);
		enterpriseInfo.setEnterpriseAddress(enterpriseAddress);
		
	
		EnterpriseAccount record = new EnterpriseAccount();
		record.setEnterpriseAccountId(enterpriseInfoId);

		if (enterpriseEdition == 1) {
			record.setEnterpriseEdition("单店版");
    		record.setTotalStoreNum(1); 
    		record.setBalanceStoreNum(1);
		}
		else if (enterpriseEdition == 2) {
    		record.setEnterpriseEdition("5店版");
    		record.setTotalStoreNum(5); 
    		record.setBalanceStoreNum(5);
    	}
    	else if (enterpriseEdition == 3){
    		record.setEnterpriseEdition("10店版");
    		record.setTotalStoreNum(10); 
    		record.setBalanceStoreNum(12);
    	}
        else if (enterpriseEdition == 4){
        	record.setEnterpriseEdition("无限版");
        	record.setTotalStoreNum(999); 
    		record.setBalanceStoreNum(999);
    	}
	   
    	
		
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setName(enterpriseLinkname);
		employeeDto.setPhone(enterpriseLinkphone);
	
		
		
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
		
		if (enterpriseInfoId != null) {
            enterpriseInfo.setEnterpriseInfoId(enterpriseInfoId);
            enterpriseInfoMapper.updateByPrimaryKeySelective(enterpriseInfo);
            
            enterpriseAccountMapper.updateByPrimaryKeySelective(record);
            UserAccount user = userAccountMapper.selectTouserByUserAccount(userAccount);
            employeeDto.setEmployeeId(user.getUserId());
            employeeInfoMapper.updateByPrimaryKey(employeeDto);
        }
        else {
            //开始时间 or  结束时间  不可修改
            record.setBeginTime(DateUtil.getCurDate());
            record.setFinishTime(DateUtil.getCurrNumberYear(DateUtil.getCurDate(), useTime));
            
            employeeDto.setIsDeleted(1);
            employeeDto.setCreateTime(DateUtil.getCurTime());
            
            enterpriseInfoMapper.insertSelective(enterpriseInfo);
            record.setEnterpriseAccountId(enterpriseInfo.getEnterpriseInfoId());
            enterpriseAccountMapper.insertSelective(record);
            employeeInfoMapper.insert(employeeDto);
            userAccount.setUserId(employeeDto.getEmployeeId());
            userAccountMapper.insert(userAccount);
            
//            //新增默认会员卡
        
            MemberLevel memberLevel = new MemberLevel();
            memberLevel.setLevelName("默认会员卡");
            memberLevel.setLevelType("等级卡");
            memberLevel.setLevelLogo("65c294");
            memberLevel.setStoreAccount(storeAccount);
            String curTime = DateUtil.getCurTime();
            memberLevel.setLastOperatorId(userId);
            memberLevel.setUpdateTime(curTime);
            memberLevel.setLevelTemplate(1);
            memberLevelMapper.insert(memberLevel);
            
            MemberLevelDiscount memberLevelDiscount = new MemberLevelDiscount();
            memberLevelDiscount.setProjectDiscount(100);
            memberLevelDiscount.setStoreId(0);;
            memberLevelDiscount.setGoodsDiscount(100);
            memberLevelDiscount.setPerformanceDiscountPercent(1);
            memberLevelDiscount.setSellAmount(new BigDecimal(0));
            memberLevelDiscount.setChargeMinMoney(new BigDecimal(0));
            memberLevelDiscount.setCashDiscountType(0);
            memberLevelDiscount.setIntegralUnit(1);
            memberLevelDiscount.setIntegralNumber(1);
            memberLevelDiscount.setUpdateTime(curTime);
            memberLevelDiscount.setLevelId(memberLevel.getLevelId());
            memberLevelDiscount.setLastOperatorId(userId);
            memberLevelDiscount.setCreateTime(curTime);
            memberLevelDiscountMapper.insert(memberLevelDiscount);
            
        }
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
	}

	/**
	 *  会员卡状态修改
	* @author 骆峰
	* @date 2016年6月28日 下午7:51:21
	* @param statu statu
	* @param enterpriseInfoId enterpriseInfoId
	* @param storeAccount storeAccount
	* @return BaseDto
	 */
    public BaseDto disableAndStart(Integer statu, Integer enterpriseInfoId, String storeAccount) {
        UserAccount userInfo = new UserAccount();
        userInfo.setStoreAccount(storeAccount);
        String status = String.valueOf(statu);
        userInfo.setStatus(status);
        userAccountMapper.updateDisableAndStart(userInfo);
        
        EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
        enterpriseInfo.setEnterpriseInfoId(enterpriseInfoId);
        Integer enterpriseStatus = statu==1?3:1;
        enterpriseInfo.setEnterpriseStatus(enterpriseStatus);
        enterpriseInfoMapper.updateByPrimaryKeySelective(enterpriseInfo);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
}
