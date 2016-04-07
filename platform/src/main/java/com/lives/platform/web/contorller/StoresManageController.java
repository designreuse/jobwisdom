package com.lives.platform.web.contorller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lives.platform.common.consts.Url;
import com.lives.platform.common.exception.ServiceException;
import com.lives.platform.common.util.DateUtils;
import com.lives.platform.web.entity.StoresManage;
import com.lives.platform.web.entity.StoresManageRelation;
import com.lives.platform.web.param.StoresManageRegister;
import com.lives.platform.web.service.StoresManageMapperService;
import com.lives.platform.web.service.StoresManageRelationMapperService;


/**
 * 提成基础信息
 * @author 王潇
 * @date 2015年6月8日 下午2:06:08
 *
 */

@Controller
public class StoresManageController {

	@Autowired
	private StoresManageMapperService storesManageMapperService;
	@Autowired
	private StoresManageRelationMapperService storesManageRelationMapperService;
	
	@RequestMapping(value = Url.StoresManage.STORESMANAGELOG)
	public String storesManageLog() throws ServiceException{
       return "storesManageRelation/storesManageRelation";
	}
	
	/**
	 * 新增区域信息
	* @author 王潇
	* @date 2015年6月8日 下午2:11:32
	* @param storesManageRegister
	* @return
	* @throws ServiceException
	 */
	@RequestMapping(value = Url.StoresManage.STORESMANAGESUBMIT)
	public String storesManageAdd(StoresManageRegister storesManageRegister) throws ServiceException{
		
		StoresManage storesManage = new StoresManage();
		storesManage.setHqStoresId(storesManageRegister.getHqStoresId());
		storesManage.setStoresManageName(storesManageRegister.getStoresManageName());
		storesManage.setCreateTime(DateUtils.getCurTime());
		
		storesManageMapperService.insertStoresManage(storesManage);
		List<StoresManageRelation> voList = new ArrayList<StoresManageRelation>();
		String[] valueStrings =  (storesManageRegister.getCheckboxname()).split(",");
		for(int i =0;i < valueStrings.length;i++){
			StoresManageRelation storesManageRelation = new StoresManageRelation();
			storesManageRelation.setItemid1(storesManage.getStoresManageId());
			storesManageRelation.setItemid2(Integer.valueOf(valueStrings[i]));
			storesManageRelation.setCreateTime(DateUtils.getCurTime());
			voList.add(storesManageRelation);
		}
		Integer type  = storesManageRelationMapperService.insertStoresManageRelationList(voList);
		
		if(type == 1){
			return "vipLevel/vipLevelView";
		}else{
			return "vipLevel/vipLevelView";
		}

	}
	
	/**
	 * 新增区域关系
	* @author 王潇
	* @date 2015年6月8日 下午2:11:32
	* @param storesManageId 区域id
	* @param StoresAccountsId 门店帐号id
	* @return
	* @throws ServiceException
	 */
	@RequestMapping(value = Url.StoresManage.STORESMANAGERELATIONCOPY)
	public String storesManageRelationCopyAdd(Integer storesManageId,Integer storesAccountsId) throws ServiceException{
		
		StoresManageRelation storesManageRelation = new StoresManageRelation();
		storesManageRelation.setItemid1(storesAccountsId);
		storesManageRelation.setItemid2(storesManageId);
		storesManageRelation.setCreateTime(DateUtils.getCurTime());
		Integer type  = storesManageRelationMapperService.insertStoresManageRelationCopy(storesManageRelation);
		
		if(type == 1){
			return "vipLevel/vipLevelView";
		}else{
			return "vipLevel/vipLevelView";
		}

	}
	
}
