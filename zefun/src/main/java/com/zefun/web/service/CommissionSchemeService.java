package com.zefun.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.View;
import com.zefun.web.entity.CommissionScheme;
import com.zefun.web.mapper.CommissionSchemeMapper;


/**
 * 提成分配方案管理
* @author 老王
* @date Jan 13, 2016 8:20:21 PM
 */
@Service
public class CommissionSchemeService {

	/** 提成分配方案管理*/
	@Autowired
	private CommissionSchemeMapper commissionSchemeMapper;
	
	
	/**
     * 客情分析页面
    * @author 张进军
    * @date Jan 13, 2016 8:19:34 PM
    * @param storeId    门店标识
    * @return	客情分析页面
     */
	public ModelAndView showCommissionScheme(int storeId) {
		ModelAndView mav = new ModelAndView(View.Setting.VIEW_COMMISSION_SCHEME);
		List<CommissionScheme> commissionSchemeList = commissionSchemeMapper.selectByStoreId(storeId);
		Map<String, Object> goodsMap = new HashMap<>();
		Map<String, Object> comboMap = new HashMap<>();
		//将分配业绩提成重新组装好
		for (CommissionScheme commissionScheme : commissionSchemeList) {
			Map<String, Object> map = null;
			if (commissionScheme.getCommissionType() == 1) {
				map = goodsMap;
			}
			else {
				map = comboMap;
			}
			String cashTwoCommission = commissionScheme.getCashTwoCommission();
			List<Map<String, Object>> cashTwoList = new ArrayList<>();
			
		}
		mav.addObject("commissionSchemeList", commissionSchemeList);
		return mav;
	}
}
