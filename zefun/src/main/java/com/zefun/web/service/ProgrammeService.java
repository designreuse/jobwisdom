package com.zefun.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import net.sf.json.JSONArray;


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
        else {
        	Map<String, Object> map = new HashMap<>();
        	map.put("storeIdOrAccount", storeAccount);
        	map.put("ruleType", 1);
        	SettingRule settingRule = settingRuleMapper.selectByStoreIdOrAccount(map);
        	
        	List<Map<String, Object>> list = new ArrayList<>();
        	
        	Map<String, Object> map1 = new HashMap<>();
        	//查看企业号是否存在规则
        	if (settingRule == null) {
        		initializationSettingRule(storeAccount, 1);
        		settingRule = settingRuleMapper.selectByStoreIdOrAccount(map);
        	}
        	map1.put("storeIdOrAccount", storeAccount);
    		map1.put("settingRule", settingRule);
    		list.add(map1);
        	
    		String ruleInfo  = null;
        	for (int i =0; i < storeInfoList.size(); i++) {
        		
        		StoreInfo storeInfo = storeInfoList.get(i);
        		map.put("storeIdOrAccount", storeInfo.getStoreId());
            	SettingRule obj = settingRuleMapper.selectByStoreIdOrAccount(map);
            	if (i == 0) {
            		ruleInfo = obj.getRuleInfo();
            	}
            	if (obj == null) {
            		initializationStoreRule(storeInfo.getStoreId(), 1);
            		obj = settingRuleMapper.selectByStoreIdOrAccount(map);
            	}
            	
            	Map<String, Object> map2 = new HashMap<>();
            	map2.put("storeIdOrAccount", storeInfo.getStoreId());
            	map2.put("settingRule", obj);
            	list.add(map2);
			}
        	
        	mav.addObject("ruleListStr", JSONArray.fromObject(list).toString());
        	mav.addObject("storeAccount", storeAccount);
    		mav.addObject("storeInfoList", storeInfoList);
    		return mav;
        }
	}
	
	/**
	 * 初始化该企业的规则数据
	* @author 老王
	* @date 2016年7月27日 下午3:26:05 
	* @param storeAccount 企业号
	* @param ruleType 规则类型
	 */
	public void initializationSettingRule (String storeAccount, Integer ruleType) {
		SettingRule record = new SettingRule();
		record.setStoreIdOrAccount(storeAccount);
		record.setRuleType(1);
		record.setRuleInfo("0:500:2000:5000:10000");
		settingRuleMapper.insertSelective(record);
		
		List<StoreInfo> storeInfoList = storeInfoMapper.selectByStoreAccount(storeAccount);
		
		for (StoreInfo storeInfo : storeInfoList) {
			record.setStoreIdOrAccount(storeInfo.getStoreId().toString());
			settingRuleMapper.insertSelective(record);
		}
	}
	
	/**
	 * 
	* @author 老王
	* @date 2016年7月28日 上午9:54:50 
	* @param storeId 门店标识
	* @param ruleType 规则类型
	 */
	public void initializationStoreRule (Integer storeId, Integer ruleType) {
		SettingRule record = new SettingRule();
		record.setRuleType(1);
		record.setRuleInfo("0:500:2000:5000:10000");
		record.setStoreIdOrAccount(storeId.toString());
		settingRuleMapper.insertSelective(record);
	}
	
	/**
     * 保存方案规则
    * @author 老王
    * @date 2016年7月27日 下午3:19:41 
    * @param settingRuleId 方案规则标识
    * @param ruleType 方案规则类型（1：大客户分析）
    * @param ruleInfo 累计账号数量
    * @return BaseDto
     */
	public BaseDto updateSettingRule (Integer settingRuleId, Integer ruleType, String ruleInfo) {
		SettingRule record = new SettingRule();
		record.setRuleInfo(ruleInfo);
		record.setRuleType(ruleType);
		record.setSettingRuleId(settingRuleId);
		settingRuleMapper.updateByPrimaryKeySelective(record);
		
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
	}
}
