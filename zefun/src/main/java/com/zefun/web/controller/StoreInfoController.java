package com.zefun.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.SpecialService;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.service.StoreInfoService;

import net.sf.json.JSONObject;

/**
 * 门店信息服务
 * @author 张进军
 * @date Nov 9, 2015 11:20:50 AM
 */
@Controller
public class StoreInfoController extends BaseController {

	/** 门店信息服务对象 */
	@Autowired
	private StoreInfoService storeInfoService;
	/** 轮牌操作 */
	@Autowired
	private ShiftMahjongController shiftMahjongController;
	/** 项目操作 */
	@Autowired
	private ProjectInfoController projectInfoController;

	/**
	 * 查询门店列表页面
	* @author 老王
	* @date 2016年4月28日 上午11:58:04 
	* @param request 返回
	* @return ModelAndView
	 */
	@RequestMapping(value = Url.StoreInfo.SHOW_STORE_LIST, method = RequestMethod.GET)
	public ModelAndView showStoreList(HttpServletRequest request) {
		String storeAccount = getStoreAccount(request);
		return storeInfoService.showStoreList(storeAccount);
	}
    
	/**
	 * 升级续费
	* @author 老王
	* @date 2016年5月26日 上午1:51:43 
	* @param request 返回
	* @param upgradeValue 选择版本
	* @param renewDate 选择续费年数
	* @return BaseDto
	 */
	@RequestMapping(value = Url.StoreInfo.CONFIRM_UPGRADE_RENEW)
	@ResponseBody
	public BaseDto confirmUpgradeRenew (HttpServletRequest request, Integer upgradeValue, Integer renewDate) {
		String storeAccount = getStoreAccount(request);
		return storeInfoService.confirmUpgradeRenew(storeAccount, upgradeValue, renewDate);
	}
	
	/**
	 * 查询门店信息
	* @author 老王
	* @date 2016年5月26日 下午3:17:02 
	* @param request 返回
	* @param storeId 门店标识
	* @return BaseDto
	 */
	@RequestMapping(value = Url.StoreInfo.SELECT_STORE_INFO)
	@ResponseBody
	public BaseDto selectStoreInfo (HttpServletRequest request, Integer storeId) {
		return storeInfoService.selectStoreInfo(storeId);
	}
	
	/**
	 * 创建分店
	* @author 老王
	* @date 2016年4月30日 下午4:52:22 
	* @param storeInfo 门店信息
	* @param userName 操作员
	* @param userPwd 操作原密码
	* @param request 返回
	* @return BaseDto
	 */
	@RequestMapping(value = Url.StoreInfo.SAVE_UPDATE_STORE)
	@ResponseBody
	public BaseDto saveUpdateStore (StoreInfo storeInfo, Integer userName, String userPwd, HttpServletRequest request) {
		if (storeInfo.getStoreId() == null) {
			String storeAccount = getStoreAccount(request);
			storeInfo.setStoreAccount(storeAccount);
			storeInfo.setStoreType(3);
			//新增操作员
			return storeInfoService.saveStore(storeInfo, userName, userPwd);
		}
		else {
			return storeInfoService.storeSettingAction(storeInfo);
		}
	}
	
	/**
	 * 查询企业消费流水
	* @author 老王
	* @date 2016年5月24日 下午4:31:24 
	* @param request 返回
	* @return BaseDto
	 */
	@RequestMapping(value = Url.StoreInfo.SELECT_CONSUMPTION_RECORD)
	@ResponseBody
	public BaseDto selectConsumptionRecord (HttpServletRequest request) {
		String storeAccount = getStoreAccount(request);
		return storeInfoService.selectConsumptionRecord(storeAccount);
	}
	
	/**
	 * 查询企业消费流水
	* @author 老王
	* @date 2016年5月24日 下午4:31:24 
	* @param request 返回
	* @return BaseDto
	 */
	@RequestMapping(value = Url.StoreInfo.SELECT_ENTERPRISE_ACCOUNT)
	@ResponseBody
	public BaseDto selectEnterpriseAccount (HttpServletRequest request) {
		String storeAccount = getStoreAccount(request);
		return storeInfoService.selectEnterpriseAccount(storeAccount);
	}
	
	/**
	 * 短信充值
	* @author 老王
	* @date 2016年5月24日 下午8:15:29 
	* @param msnRechargeType 充值类型
	* @param msnNumber 短信条数
	* @param request 返回
	* @return BaseDto
	 */
	@RequestMapping(value = Url.StoreInfo.SAVE_MSN_RECHARGE)
	@ResponseBody
	public BaseDto saveMsnRecharge (Integer msnRechargeType, Integer msnNumber, HttpServletRequest request) {
		String storeAccount = getStoreAccount(request);
		return storeInfoService.saveMsnRecharge(storeAccount, msnRechargeType, msnNumber);
	}
	
	/**
	 * 分配短信
	* @author 老王
	* @date 2016年5月25日 上午12:55:48 
	* @param storeId 门店标识
	* @param distributionNum 分配数量
	* @param request 返回
	* @return BaseDto
	 */
	@RequestMapping(value = Url.StoreInfo.DISTRIBUTION_MSN)
	@ResponseBody
	public BaseDto distributionMsn (Integer storeId, Integer distributionNum, HttpServletRequest request) {
		String storeAccount = getStoreAccount(request);
		return storeInfoService.distributionMsn(storeAccount, storeId, distributionNum);
	}
	
	/**
	 * 短信充值分配记录
	* @author 老王
	* @date 2016年5月25日 下午3:26:00 
	* @param request 返回
	* @return BaseDto
	 */
	@RequestMapping(value = Url.StoreInfo.RECHARGE_FLOW)
	@ResponseBody
	public BaseDto rechargeFlow (HttpServletRequest request) {
		String storeAccount = getStoreAccount(request);
		return storeInfoService.rechargeFlow(storeAccount);
	}
	
	/**
	 * 新增或修改授权码
	* @author 老王
	* @date 2016年5月25日 下午4:58:53 
	* @param request 返回
	* @param storeAuthorityId 授权标识
	* @param storeId 门店标识
	* @param employeeId 员工标识
	* @param authorityValue 授权码
	* @return BaseDto
	 */
	@RequestMapping(value = Url.StoreInfo.ADD_OR_UPDATE_AUTHORITY)
	@ResponseBody
	public BaseDto addOrUpdateAuthority (HttpServletRequest request, Integer storeAuthorityId, Integer storeId, Integer employeeId, 
			  String authorityValue) {
		String storeAccount = getStoreAccount(request);
		return storeInfoService.addOrUpdateAuthority(storeAccount, storeAuthorityId, storeId, employeeId, authorityValue);
	}
	
	/**
	 * 根据授权码查询授权员工
	* @author 老王
	* @date 2016年7月22日 上午11:42:12 
	* @param request 返回
	* @param authorityValue 授权码
	* @return BaseDto
	 */
	@RequestMapping(value = Url.StoreInfo.SELECT_AUTHORITY_BY_AUTHORITY_VALUE)
	@ResponseBody
	public BaseDto selectAuthorityByAuthorityValue (HttpServletRequest request, String authorityValue) {
		Integer storeId = getStoreId(request);
		return storeInfoService.selectAuthorityByAuthorityValue(storeId, authorityValue);
	}
	
	/**
	 * 删除门店授权码
	* @author 老王
	* @date 2016年6月20日 下午4:45:27 
	* @param request request
	* @param storeAuthorityId storeAuthorityId
	* @return storeAuthorityId
	 */
	@RequestMapping(value = Url.StoreInfo.DELETE_AUTHORITY)
	@ResponseBody
	public BaseDto deleteAuthority (HttpServletRequest request, Integer storeAuthorityId) {
		return storeInfoService.deleteAuthority(storeAuthorityId);
	}
	
	/**
	 * 进入店铺设置页面
	 * 
	 * @author 张进军
	 * @date Nov 9, 2015 11:19:03 AM
	 * @param request
	 *            请求对象
	 * @return 店铺设置页面
	 */
	@RequestMapping(value = Url.StoreInfo.VIEW_STORE_SETTING, method = RequestMethod.GET)
	public ModelAndView viewStoreSetting(HttpServletRequest request) {
		return storeInfoService.storeSettingView(getStoreId(request));
	}

	/**
	 * 进行店铺设置操作
	 * @author 张进军
	 * @date Nov 9, 2015 11:19:28 AM
	 * @param storeInfo 店铺信息
	 * @param request   请求对象
	 * @return 成功返回码0；失败返回其他错误码，返回值为提示语
	 */
	@RequestMapping(value = Url.StoreInfo.ACTION_STORE_SETTING, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto storeSettingAction(StoreInfo storeInfo, HttpServletRequest request) {
	    storeInfo.setStoreId(getStoreId(request));
		return storeInfoService.storeSettingAction(storeInfo);
	}
	
	/**
	 * 门店特色服务
	* @author 高国藩
	* @date 2016年5月19日 下午5:02:02
	* @param request  请求
	* @param data     数据
	* @return         状态
	 */
	@RequestMapping(value = Url.StoreInfo.ACTION_STORE_SETTING_SPECIAL, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto storeSettingSpecialAction(HttpServletRequest request, @RequestBody JSONObject data) {
	    SpecialService specialService = (SpecialService) JSONObject.toBean(data, SpecialService.class);
	    specialService.setStoreId(getStoreId(request));
	    specialService.setIsDeleted(0);
	    return storeInfoService.storeSettingSpecialAction(specialService);
    }
	
	/**
	 * 删除特色服务
	* @author 高国藩
	* @date 2016年5月20日 下午3:30:51
	* @param request request
	* @param sId sId
	* @return sId
	 */
	@RequestMapping(value = Url.StoreInfo.ACTION_STORE_SETTING_SPECIAL_DELETED, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto storeSettingSpecialActionDeleted(HttpServletRequest request, Integer sId) {
        SpecialService specialService = new SpecialService();
        specialService.setStoreId(getStoreId(request));
        specialService.setIsDeleted(1);
        specialService.setsId(sId);
        return storeInfoService.storeSettingSpecialActionDeleted(specialService);
    }

	/**
	 * 门店信息编辑操作
	 * 
	 * @author 张进军
	 * @date Jan 29, 2016 12:38:29 PM
	 * @param data
	 *            编辑数据
	 * @param request
	 *            请求对象
	 * @return 成功返回码0；失败返回其他错误码，返回值为提示语
	 * @throws IOException
	 *             解码异常
	 */
	@RequestMapping(value = Url.StoreInfo.ACTION_STORE_EDITOR, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto storeEditorAction(@RequestBody String data, HttpServletRequest request) throws IOException {
		Integer storeId = getStoreId(request);
		return storeInfoService.storeEditorAction(storeId, data);
	}

	/**
	 * 新增门店
	 * 
	 * @author 张进军
	 * @date Oct 29, 2015 11:45:28 AM
	 * @param name
	 *            用户姓名
	 * @param phone
	 *            门店电话
	 * @param storeName
	 *            门店名称
	 * @param storeType
	 *            门店类型(1:单店，2:连锁店总店，3:连锁店分店)
	 * @param parentId
	 *            父级门店标识
	 * @return 成功返回码为0，失败为其它返回码
	 */
	/*@RequestMapping(value = Url.StoreInfo.ACTION_ADD_STORE, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto addStoreInfo(String name, String phone, String storeName, Integer storeType, Integer parentId) {
		return storeInfoService.addStoreInfo(name, phone, storeName, storeType, parentId);
	}*/

	/**
	 * 初始化门店数据(部门-岗位-职位)
	 * 
	 * @author 高国藩
	 * @date 2016年1月17日 下午5:59:31
	 * @param request
	 *            请求
	 * @param response
	 *            返回
	 * @return 状态
	 */
	@RequestMapping(value = Url.StoreInfo.ACTION_STORE_INITIALIZE, method = RequestMethod.POST)
	@ResponseBody
	public BaseDto storeInitialize(HttpServletRequest request, HttpServletResponse response) {
		Integer storeId = getStoreId(request);
		Integer operateId = getUserId(request);
		return storeInfoService.storeInitialize(storeId, operateId, request, response, shiftMahjongController,
				projectInfoController);
	}

	/**
	 * 门店复制
	 * @author 高国藩
	 * @date 2016年3月2日 下午3:48:19
	 * @param request request
	 * @param response response
	 * @param copyStoreId copyStoreId
	 * @return response
	 */
	@RequestMapping(value = Url.StoreInfo.ACTION_STORE_COPY, method = RequestMethod.GET)
	@ResponseBody
	public BaseDto storeInfoCopy(HttpServletRequest request, HttpServletResponse response, Integer copyStoreId) {
		Integer storeId = getStoreId(request);
		Integer operateId = getUserId(request);
		return storeInfoService.storeInfoCopy(storeId, operateId, copyStoreId);
	}
}
