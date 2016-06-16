package com.zefun.api.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.api.mapper.EmployeeObjectiveMapper;
import com.zefun.api.utils.DateUtil;

/**
* @author 小高
* @date Aug 23, 2015 9:03:36 PM 
*/
public class GiftmoneyDetailJob {
    @Autowired
    private EmployeeObjectiveMapper employeeObjectiveMapper;
    
    /**
     * 定时器执行内容
    * @author 小高
    * @date Aug 23, 2015 9:04:47 PM
     */
	public void execute() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("parameterDate", DateUtil.getCurDate());
		hashMap.put("selectType", 1);
		//查询须赠送会员情况
		List<Map<String, Object>> notPresentDetailList = employeeObjectiveMapper.selectPresentDetail(hashMap);
		
		List<Map<String, Object>> notPresentList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : notPresentDetailList) {
			map.put("flowType", 2);
			map.put("businessType", "分期赠送");
			map.put("businessDesc", "分期赠送");
			map.put("flowTime", DateUtil.getCurTimeStr());
			
			notPresentList.add(map);
		}
		
		if (notPresentList.size() > 0) {
			employeeObjectiveMapper.insertNotPresentDetailList(notPresentList);
		}
		
		hashMap.put("selectType", 2);
		//查询须赠送会员情况
		List<Map<String, Object>> presentDetailList = employeeObjectiveMapper.selectPresentDetail(hashMap);
		
		List<Map<String, Object>> presentList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : presentDetailList) {
			map.put("flowType", 1);
			map.put("businessType", "过期减扣");
			map.put("businessDesc", "过期减扣");
			map.put("flowTime", DateUtil.getCurTimeStr());
			
			presentList.add(map);
		}
		
		if (presentList.size() > 0) {
			employeeObjectiveMapper.insertNotPresentDetailList(presentList);
		}
		
	    //当期时间为今天时
	    Integer isWin = employeeObjectiveMapper.updateGiftmoneyAll(DateUtil.getCurDate());
	    
	    if (isWin != 0) {
	        employeeObjectiveMapper.deleteGiftmoneyDetail(DateUtil.getCurDate());
	    }
	    
	    employeeObjectiveMapper.updatePastdataGiftmoneyDetail(DateUtil.getCurDate());
	    
	    employeeObjectiveMapper.overdueGiftmoneyDetail(DateUtil.getCurDate());
	}
	
}

