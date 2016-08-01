package com.zefun.web.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.BigMemberDto;
import com.zefun.web.dto.MemberInfoDto;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.SettingRule;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.mapper.MemberAccountMapper;
import com.zefun.web.mapper.MemberInfoMapper;
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
	/** 会员账户*/
	@Autowired
	private MemberAccountMapper memberAccountMapper;
	/** 会员基本*/
	@Autowired
	private MemberInfoMapper memberInfoMapper;
	
	/**
     * 大客户分析
    * @author 老王
    * @date Jan 13, 2016 8:19:34 PM
    * @param storeAccount 企业号
    * @param pageType 页面类型
    * @param roleId 角色权限
    * @return	ModelAndView
     */
	public ModelAndView bigCustomerAnalysis(String storeAccount, Integer pageType, Integer roleId) {
		ModelAndView mav = new ModelAndView(View.Programme.VIEW_BIG_CUSTOMER_ANALYSIS);
		List<StoreInfo> storeInfoList = storeInfoMapper.selectByStoreAccount(storeAccount);
        if (storeInfoList.isEmpty() || storeInfoList.size() == 0) {
        	mav.addObject("analysisType", 1);
        	return mav;
		}
        else {
        	Map<String, Object> map = new HashMap<>();
        	map.put("storeIdOrAccount", storeAccount);
        	map.put("ruleType", pageType);
        	SettingRule settingRule = settingRuleMapper.selectByStoreIdOrAccount(map);
        	
        	List<Map<String, Object>> list = new ArrayList<>();
        	
        	Map<String, Object> map1 = new HashMap<>();
        	//查看企业号是否存在规则
        	if (settingRule == null) {
        		initializationSettingRule(storeAccount, pageType);
        		settingRule = settingRuleMapper.selectByStoreIdOrAccount(map);
        	}
        	map1.put("storeIdOrAccount", storeAccount);
    		map1.put("settingRule", settingRule);
    		list.add(map1);
        	
        	for (int i =0; i < storeInfoList.size(); i++) {
        		
        		StoreInfo storeInfo = storeInfoList.get(i);
        		map.put("storeIdOrAccount", storeInfo.getStoreId());
            	SettingRule obj = settingRuleMapper.selectByStoreIdOrAccount(map);
            	if (obj == null) {
            		initializationStoreRule(storeInfo.getStoreId(), pageType);
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
    		mav.addObject("pageType", pageType);
    		mav.addObject("roleId", roleId);
    		
    		return mav;
        }
	}
	
	/**
	 * 大客户分析数据
	* @author 老王
	* @date 2016年7月29日 下午7:45:33 
	* @param storeIdOrAccount 方案规则归宿（门店或者企业）
    * @param ruleType 1、门店 2企业号
    * @param pageType 拉取数据类型
    * @return BaseDto
	 */
	public BaseDto bigMemberData (String storeIdOrAccount, Integer ruleType, Integer pageType) {
		Map<String, Object> map = new HashMap<>();
    	map.put("storeIdOrAccount", storeIdOrAccount);
    	map.put("ruleType", pageType);
    	SettingRule settingRule = settingRuleMapper.selectByStoreIdOrAccount(map);
    	
    	Map<String, Object> dataMap = selectRuleData(settingRule, ruleType, pageType);
    	return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, dataMap);
	}
	
	/**
	 * 根据规则组装数据
	* @author 老王
	* @date 2016年7月29日 下午3:50:40 
	* @param selectObj 规则信息
	* @param ruleType 1、门店 2企业号
	* @param pageType 拉取数据类型
	* @return Map<String, Object>
	 */
	public Map<String, Object> selectRuleData (SettingRule selectObj, Integer ruleType, Integer pageType) {
        String storeIdOrAccount = selectObj.getStoreIdOrAccount();
		
		String ruleInfo = selectObj.getRuleInfo();
		
		String[] rule = ruleInfo.split(":");
		
		Map<String, Object> map = new HashMap<>();
		map.put("storeIdOrAccount", storeIdOrAccount);
		map.put("ruleType", ruleType);
		
		List<BigMemberDto> averageVlaueList = memberAccountMapper.selectBigMember(map);
		
		List<BigMemberDto> girlAverageVlaueList = averageVlaueList.parallelStream().filter(a -> "女".equals(a.getSex()))
				  .collect(Collectors.toList());
		
		List<BigMemberDto> boyAverageVlaueList = averageVlaueList.parallelStream().filter(a -> "男".equals(a.getSex()))
				  .collect(Collectors.toList());
		
		List<Map<String, Object>> girlObjList = packgObj(girlAverageVlaueList, rule, pageType);
		
		List<Map<String, Object>> boyObjList = packgObj(boyAverageVlaueList, rule, pageType);
		
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("girlObjList", girlObjList);
		dataMap.put("boyObjList", boyObjList);
		return dataMap;
	}
	
	/**
	 * 
	* @author 老王
	* @date 2016年7月29日 下午6:30:25 
	* @param averageVlaueList averageVlaueList
	* @param rule rule
	* @param pageType  pageType
	* @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> packgObj (List<BigMemberDto> averageVlaueList, String[] rule, Integer pageType) {
        double totalNoSexVlaue = averageVlaueList.parallelStream().mapToDouble(BigMemberDto::getYearAverageVlaue).sum();
		List<Map<String, Object>> boyObjList = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			List<BigMemberDto> boyList = null;
			Map<String, Object> boyObj = new HashMap<>();
			String ruleStr = rule[i];
			if (i == 4) {
				boyList = averageVlaueList.parallelStream().filter(a -> new BigDecimal(a.getYearAverageVlaue())
						  .compareTo(new BigDecimal(ruleStr)) >= 0).collect(Collectors.toList());
				if (pageType == 1) {
					boyObj.put("rule", rule[4] + "元以上");
				}
				else if (pageType == 2) {
					boyObj.put("rule", rule[4] + "次以上");
				}
				else {
					boyObj.put("rule", rule[4] + "天以上");
				}
			}
			else {
				String ruleStrToo = rule[i + 1];
				boyList = averageVlaueList.parallelStream().filter(a -> new BigDecimal(a.getYearAverageVlaue())
						  .compareTo(new BigDecimal(ruleStr)) >= 0 
						  && new BigDecimal(a.getYearAverageVlaue()).compareTo(new BigDecimal(ruleStrToo)) < 0)
						  .collect(Collectors.toList());
				if (pageType == 1) {
					boyObj.put("rule", rule[0] + "-" + rule[1] + "元");
				}
				else if (pageType == 2) {
					boyObj.put("rule", rule[0] + "-" + rule[1] + "次");
				}
				else {
					boyObj.put("rule", rule[0] + "-" + rule[1] + "天");
				}
			}
			boyObj.put("memberNumber", boyList.size());
			List<Integer> memberIdList = new ArrayList<>();
			boyList.forEach(a -> memberIdList.add(a.getMemberId()));
			boyObj.put("memberIdList", memberIdList);
			if (averageVlaueList.size() == 0) {
				boyObj.put("numProportion", "0.00");
			}
			else {
				boyObj.put("numProportion", new BigDecimal(boyList.size()*100)
						  .divide(new BigDecimal(averageVlaueList.size()), 2, BigDecimal.ROUND_HALF_EVEN));
			}
			
			double totalVlaue = boyList.parallelStream().mapToDouble(BigMemberDto::getYearAverageVlaue).sum();
			boyObj.put("totalVlaue", new BigDecimal(totalVlaue).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			
			if (totalNoSexVlaue == 0) {
				boyObj.put("totalProportion", "0.00");
			}
			else {
				boyObj.put("totalProportion", new BigDecimal(totalVlaue/totalNoSexVlaue)
						  .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
			
			double avgProportion = 0.00;
			if (boyList.size() > 0) {
				avgProportion = boyList.parallelStream().mapToDouble(BigMemberDto::getAvgConsumeAmount).average().getAsDouble();
			}
			
			boyObj.put("avgProportion", new BigDecimal(avgProportion).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			
			boyObjList.add(boyObj);
		}
		return boyObjList;
	}
	
	/**
	 * 规则内所有会员信息
	* @author 老王
	* @date 2016年7月30日 下午2:18:40 
	* @param memberIdList 会员标识集合
	* @param pageNo pageNo
    * @param pageSize pageSize
	* @return BaseDto
	 */
	public BaseDto ruleMemberIdList (Integer pageNo, Integer pageSize, List<Integer> memberIdList) {
		Page<MemberInfoDto> page = new Page<MemberInfoDto>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("memberIdList", memberIdList);
        page.setParams(map);
        List<MemberInfoDto> memberInfoDtos = memberInfoMapper.selectMemberInfosByRulePage(page);
        page.setResults(memberInfoDtos);
        
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
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
		record.setRuleType(ruleType);
		if (ruleType == 1) {
			record.setRuleInfo("0:500:2000:5000:10000");
		}
		else if (ruleType == 2) {
			record.setRuleInfo("0:5:10:30:60");
		}
		else {
			record.setRuleInfo("0:15:30:60:90");
		}
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
		record.setRuleType(ruleType);
		if (ruleType == 1) {
			record.setRuleInfo("0:500:2000:5000:10000");
		}
		else if (ruleType == 2) {
			record.setRuleInfo("0:5:10:30:60");
		}
		else {
			record.setRuleInfo("0:15:30:60:90");
		}
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
