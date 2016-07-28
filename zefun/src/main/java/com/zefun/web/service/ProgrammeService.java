package com.zefun.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.SettingRule;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.mapper.SettingRuleMapper;
import com.zefun.web.mapper.StoreInfoMapper;


/**
 * 方案管理
* @author 老王
* @date Jan 13, 2016 8:20:21 PM
 */
@Service
public class ProgrammeService {
	
	/** 门店*/
	@Autowired
	private StoreInfoMapper storeInfoMapper;
	
	/** 方案规则*/
	@Autowired
	private SettingRuleMapper settingRuleMapper;
	
	/**
     * 大客户分析
    * @author 老王
    * @date Jan 13, 2016 8:19:34 PM
    * @param storeAccount 企业号
    * @return	ModelAndView
     */
	public ModelAndView bigCustomerAnalysis(String storeAccount) {
		ModelAndView mav = new ModelAndView(View.Programme.VIEW_BIG_CUSTOMER_ANALYSIS);
		List<StoreInfo> storeInfoList = storeInfoMapper.selectByStoreAccount(storeAccount);
        if (storeInfoList.isEmpty() || storeInfoList.size() == 0) {
        	mav.addObject("analysisType", 1);
        	return mav;
		}
		mav.addObject("storeAccount", storeAccount);
		mav.addObject("storeInfoList", storeInfoList);
		return mav;
	}
	
	/**
	 * 初始化该企业的规则数据
	* @author 老王
	* @date 2016年7月27日 下午3:26:05 
	* @param storeAccount 企业号
	* @return BaseDto
	 */
	public BaseDto initializationSettingRule (String storeAccount) {
		SettingRule record = new SettingRule();
		record.setStoreIdOrAccount(storeAccount);
		record.setRuleType(1);
		record.setRuleInfo("0:500:2000:5000:10000:30000");
		settingRuleMapper.insertSelective(record);
		
		List<StoreInfo> storeInfoList = storeInfoMapper.selectByStoreAccount(storeAccount);
		
		for (StoreInfo storeInfo : storeInfoList) {
			record.setStoreIdOrAccount(storeInfo.getStoreId().toString());
			settingRuleMapper.insertSelective(record);
		}
		
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
	}
	
	/**
     * 保存方案规则
    * @author 老王
    * @date 2016年7月27日 下午3:19:41 
    * @param storeIdOrAccount 方案规则归宿（门店或者企业）
    * @param ruleType 方案规则类型（1：大客户分析）
    * @param ruleInfo 累计账号数量
    * @return BaseDto
     */
	public BaseDto updateSettingRule (String storeIdOrAccount, Integer ruleType, String ruleInfo) {
		
		
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
	}
}
