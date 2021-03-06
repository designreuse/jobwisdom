package com.zefun.web.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.BusinessSummaryDto;
import com.zefun.web.dto.BusinessSummaryTrend;
import com.zefun.web.dto.CardconsumptionStoreSummaryResultDto;
import com.zefun.web.dto.CardsaleStoreSummaryResultDto;
import com.zefun.web.dto.CashIncomeDto;
import com.zefun.web.dto.ComboSummaryDto;
import com.zefun.web.dto.CommoditySalesDto;
import com.zefun.web.dto.DeptCashIncome;
import com.zefun.web.dto.DeptComboSummaryDto;
import com.zefun.web.dto.DeptGoodSalesSummaryDto;
import com.zefun.web.dto.DeptLaborSummaryDto;
import com.zefun.web.dto.DeptSummaryDto;
import com.zefun.web.dto.EmployeeBaseDto;
import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.dto.EnterpriseInfoDto;
import com.zefun.web.dto.GoodSalesSummaryDto;
import com.zefun.web.dto.ProjectLaborRank;
import com.zefun.web.dto.ServiceReportDto;
import com.zefun.web.dto.StoreIncomeDto;
import com.zefun.web.dto.StoreSummaryDto;
import com.zefun.web.dto.StoreSummaryResultDto;
import com.zefun.web.dto.SummaryResultDto;
import com.zefun.web.dto.TrendDeptDataDto;
import com.zefun.web.dto.UserAccountDto;
import com.zefun.web.entity.AccountGoods;
import com.zefun.web.entity.AccountRoleInfo;
import com.zefun.web.entity.CommissionScheme;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.EnterpriseAccount;
import com.zefun.web.entity.EnterpriseAccountFlow;
import com.zefun.web.entity.EnterpriseInfo;
import com.zefun.web.entity.EnterpriseMsnFlow;
import com.zefun.web.entity.EnterpriseStoreAuthority;
import com.zefun.web.entity.GoodsCategory;
import com.zefun.web.entity.GoodsInfo;
import com.zefun.web.entity.GoodsStock;
import com.zefun.web.entity.MemberAccount;
import com.zefun.web.entity.MemberInfo;
import com.zefun.web.entity.MemberLevel;
import com.zefun.web.entity.MemberLevelDiscount;
import com.zefun.web.entity.PositionInfo;
import com.zefun.web.entity.ProjectInfo;
import com.zefun.web.entity.SpecialService;
import com.zefun.web.entity.StoreAccount;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.entity.StoreSetting;
import com.zefun.web.entity.UserAccount;
import com.zefun.web.entity.WechatStore;
import com.zefun.web.mapper.AccountGoodsMapper;
import com.zefun.web.mapper.AccountRoleInfoMapper;
import com.zefun.web.mapper.BusinessReportMapper;
import com.zefun.web.mapper.CommissionSchemeMapper;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.EnterpriseAccountFlowMapper;
import com.zefun.web.mapper.EnterpriseAccountMapper;
import com.zefun.web.mapper.EnterpriseInfoMapper;
import com.zefun.web.mapper.EnterpriseMsnFlowMapper;
import com.zefun.web.mapper.EnterpriseStoreAuthorityMapper;
import com.zefun.web.mapper.GoodsInfoMapper;
import com.zefun.web.mapper.GoodsStockMapper;
import com.zefun.web.mapper.MemberAccountMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberLevelDiscountMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.OrderInfoMapper;
import com.zefun.web.mapper.PositioninfoMapper;
import com.zefun.web.mapper.ProjectInfoMapper;
import com.zefun.web.mapper.SpecialServiceMapper;
import com.zefun.web.mapper.StoreAccountMapper;
import com.zefun.web.mapper.StoreFlowMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.mapper.StoreSettingMapper;
import com.zefun.web.mapper.UserAccountMapper;
import com.zefun.web.mapper.WechatStoreMapper;
import com.zefun.web.vo.CardComboSalesVo;
import com.zefun.web.vo.CardStoreSalesVo;
import com.zefun.web.vo.CashComboSalesVo;
import com.zefun.web.vo.CashStoreSalesVo;
import com.zefun.wechat.dto.ComboSummaryViewDto;
import com.zefun.wechat.dto.RegionCountDto;
import com.zefun.wechat.dto.RegionCountRankDto;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 门店信息服务类
* @author 张进军
* @date Nov 9, 2015 11:21:30 AM
 */
@Service
@Transactional
public class StoreInfoService {

    /**一号岗位*/
    private static final String P1 = "美发(容/甲)/手艺师";
    /**二号岗位*/
    private static final String P2 = "技师/(美容)经理";
    /**三号岗位*/
    private static final String P3 = "助理";
    /**三号岗位*/
    private static final String P4 = "店长";
    /**三号岗位*/
    private static final String P5 = "收银员";
    /**
     * 剩余使用天数少于该值的正常使用的门店需要续费提醒
     */
    private static final int STORE_RENEW_DAYS = 7;

    /**员工信息操作对象*/
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    /**用户账户信息操作对象*/
    @Autowired
    private UserAccountMapper userAccountMapper;

    /**店铺信息操作对象*/
    @Autowired
    private StoreInfoMapper storeInfoMapper;

    /**店铺基础设置操作对象*/
    @Autowired
    private StoreSettingMapper storeSettingMapper;

    /**企业角色*/
    @Autowired
    private AccountRoleInfoMapper accountRoleInfoMapper;

    /**
     * 门店与微信openid关连信息操作
     */
    @Autowired
    private WechatStoreMapper wechatStoreMapper;

    /**
     * 门店账号操作
     */
    @Autowired
    private StoreAccountMapper storeAccountMapper;
    /**
     * 门店流水操作
     */
    @Autowired
    private StoreFlowMapper storeFlowMapper;

    /**
     * 订单信息操作类
     */
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    /**
     * 订单信息操作服务操作类
     * */
    @Autowired
    private OrderInfoService orderInfoService;

    /**订单明细操作类*/
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    /**劳动业绩报表统计操作类*/
    @Autowired
    private BusinessReportMapper businessReportMapper;

    /**
     * 门店部门操作类
     */
    @Autowired
    private DeptInfoMapper deptInfoMapper;

    /**
     * 会员信息操作类
     */
    @Autowired
    private MemberInfoMapper memberInfoMapper;

    /**
     * 会员账号操作类
     */
    @Autowired
    private MemberAccountMapper memberAccountMapper;

    /**商品*/
    @Autowired
    private GoodsInfoService goodsInfoService;
    /**疗程*/
    @Autowired
    private ComboInfoService comboInfoService;
    /**岗位信息 */
    @Autowired
    private PositioninfoMapper positioninfoMapper;
    /**商品*/
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    /**项目*/
    @Autowired
    private ProjectInfoMapper projectInfoMapper;
    /**特色服务*/
    @Autowired
    private SpecialServiceMapper specialServiceMapper;
    /** 提成分配方案*/
    @Autowired
    private CommissionSchemeMapper commissionSchemeMapper;
    /**
     * 授权码对象
     */
    @Autowired 
    private EnterpriseStoreAuthorityMapper enterpriseStoreAuthorityMapper;
    /**
     * 企业账户
     */
    @Autowired
    private EnterpriseAccountMapper enterpriseAccountMapper;
    /**
     * 企业账户流水
     */
    @Autowired
    private EnterpriseAccountFlowMapper enterpriseAccountFlowMapper;
    /**
     * 企业短信流水
     */
    @Autowired
    private EnterpriseMsnFlowMapper enterpriseMsnFlowMapper;
    /** 企业信息*/
    @Autowired
    private EnterpriseInfoMapper enterpriseInfoMapper;
    
    
    /** 会员折扣操作对象*/
    @Autowired
    private MemberLevelDiscountMapper memberLevelDiscountMapper;
    
    
    /** 会员等级数据操作对象 */
    @Autowired
    private MemberLevelMapper memberLevelMapper;
    /** 企业商品信息*/
    @Autowired
    private AccountGoodsMapper accountGoodsMapper;
    /** 商品库存*/
    @Autowired
    private GoodsStockMapper goodsStockMapper;

    /**
     * 查询门店列表页面
    * @author 老王
    * @date 2016年4月28日 下午12:16:29 
    * @param storeAccount 企业代号
    * @return ModelAndView
     */
    public ModelAndView showStoreList(String storeAccount) {
    	ModelAndView mav = new ModelAndView(View.Setting.STORE_LIST);
    	//查询企业下所有门店
    	List<StoreInfo> storeInfoList = storeInfoMapper.selectByStoreAccount(storeAccount);
    	
    	EnterpriseAccount enterpriseAccount = enterpriseAccountMapper.selectByStoreAccount(storeAccount);
    	
    	mav.addObject("enterpriseAccount", enterpriseAccount);
    	
    	if (storeInfoList == null || storeInfoList.size() == 0) {
    		return mav;
    	}
    	
    	mav.addObject("storeInfoList", storeInfoList);
    	
    	//查询企业下所有门店及门店下的员工
    	List<Integer> storeIds = new ArrayList<>();
    	List<Map<String, Object>> storeEmployeeList = new ArrayList<>();
    	for (StoreInfo storeInfo : storeInfoList) {
			List<EmployeeInfo> employeeInfos = employeeInfoMapper.selectEmployeeByStoreId(storeInfo.getStoreId());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("storeId", storeInfo.getStoreId());
			map.put("employeeInfos", employeeInfos);
			storeEmployeeList.add(map);
			storeIds.add(storeInfo.getStoreId());
		}
    	mav.addObject("storeEmployeeListStr", JSONArray.fromObject(storeEmployeeList).toString());
    	
    	//根据企业代号查询出所有授权码
    	List<EnterpriseStoreAuthority> enterpriseStoreAuthoritys = enterpriseStoreAuthorityMapper.selectAuthorityByStoreAccount(storeAccount);
    	mav.addObject("enterpriseStoreAuthoritys", enterpriseStoreAuthoritys);
    	
    	mav.addObject("priceMoneyOrTimeStr", JSONObject.fromObject(getPriceMoney(enterpriseAccount)).toString());
    	return mav;
    }
    
    /**
     * 抵多少钱
    * @author 老王
    * @date 2016年5月26日 上午1:58:35 
    * @param enterpriseAccount  enterpriseAccount
    * @return  BigDecimal
     */
    public Map<String, BigDecimal> getPriceMoney (EnterpriseAccount enterpriseAccount) {
    	Integer betweNum = 0;
    	try {
    		betweNum = DateUtil.getMonthSpace(DateUtil.getCurDate(), enterpriseAccount.getFinishTime());
		} 
    	catch (ParseException e) {
			e.printStackTrace();
		}
    	//计算当前版本还能抵多少钱
    	BigDecimal priceMoney = new BigDecimal(0);
    	BigDecimal useTiem = div(betweNum);
    	if (enterpriseAccount.getEnterpriseEdition().equals("单店版")) {
    		priceMoney = div(betweNum*2400);
    	}
    	else if (enterpriseAccount.getEnterpriseEdition().equals("5店版")) {
    		priceMoney = div(betweNum*3800);
    	}
        else if (enterpriseAccount.getEnterpriseEdition().equals("10店版")) {
        	priceMoney = div(betweNum*5800);
    	}
    	Map<String, BigDecimal> map = new HashMap<>();
    	map.put("priceMoney", priceMoney);
    	map.put("useTiem", useTiem);
    	return map;
    }
    
    /**
     * 
    * @author 老王
    * @date 2016年5月26日 下午12:10:30 
    * @param v1 值
    * @return BigDecimal
     */
    public BigDecimal div(Integer v1){     
    	BigDecimal b1 = new BigDecimal(v1);   
    	BigDecimal b2 = new BigDecimal(12);   
    	return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);   
    }
    
    /**
     * 
    * @author 老王
    * @date 2016年5月26日 上午1:54:34 
    * @param storeAccount 企业代号
    * @param upgradeValue 升级版本
    * @param renewDate 续费时间数
    * @return BaseDto
     */
    @Transactional
    public BaseDto confirmUpgradeRenew (String storeAccount, Integer upgradeValue, Integer  renewDate) {
    	EnterpriseAccount enterpriseAccount = enterpriseAccountMapper.selectByStoreAccount(storeAccount);
    	Map<String, BigDecimal> map = getPriceMoney(enterpriseAccount);
    	BigDecimal priceMoney = map.get("priceMoney");
    	BigDecimal useTiem = map.get("useTiem");
    	BigDecimal payable = new BigDecimal(0);
    	if (upgradeValue == 2) {
    		BigDecimal tatailMoney = (new BigDecimal(3800).multiply(useTiem)).subtract(priceMoney);
    		payable = tatailMoney.add(new BigDecimal(3800*renewDate));
    	}
    	else if (upgradeValue == 3){
    		BigDecimal tatailMoney = (new BigDecimal(5800).multiply(useTiem)).subtract(priceMoney);
    		payable = tatailMoney.add(new BigDecimal(5800*renewDate));
    	}
        else if (upgradeValue == 4){
        	BigDecimal tatailMoney = (new BigDecimal(2400).multiply(useTiem)).subtract(priceMoney);
    		payable = tatailMoney.add(new BigDecimal(8800*renewDate));
    	}
        else {
        	if (enterpriseAccount.getEnterpriseEdition().equals("单店版")) {
        		payable = new BigDecimal(2400*renewDate);
        	}
        	else if (enterpriseAccount.getEnterpriseEdition().equals("5店版")) {
        		payable = new BigDecimal(3800*renewDate);
        	}
        	else if (enterpriseAccount.getEnterpriseEdition().equals("10店版")) {
        		payable = new BigDecimal(5800*renewDate);
        	}
        	else if (enterpriseAccount.getEnterpriseEdition().equals("无限版")) {
        		payable = new BigDecimal(8800*renewDate); 
        	}
        }
    	
    	
    	if (enterpriseAccount.getBalanceAmount().compareTo(payable) < 0) {
    		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "账户余额不足，操作失败");
    	}
    	EnterpriseAccount record = new EnterpriseAccount();
    	record.setEnterpriseAccountId(enterpriseAccount.getEnterpriseAccountId());
    	record.setBalanceAmount(enterpriseAccount.getBalanceAmount().subtract(payable));
    	
    	record.setFinishTime(DateUtil.getCurrNumberYear(enterpriseAccount.getFinishTime(), renewDate));
    	if (upgradeValue == 2) {
    		record.setEnterpriseEdition("普通版");
    		record.setTotalStoreNum(5); 
    		record.setBalanceStoreNum(5 - enterpriseAccount.getAlreadyStoreNum());
    	}
    	else if (upgradeValue == 3){
    		record.setEnterpriseEdition("5店版");
    		record.setTotalStoreNum(12); 
    		record.setBalanceStoreNum(12 - enterpriseAccount.getAlreadyStoreNum());
    	}
        else if (upgradeValue == 4){
        	record.setEnterpriseEdition("10店版");
        	record.setTotalStoreNum(999); 
    		record.setBalanceStoreNum(999);
    	}
    	
    	enterpriseAccountMapper.updateByPrimaryKeySelective(record);
    	
    	//增加账户流水
    	EnterpriseAccountFlow enterpriseAccountFlow = new EnterpriseAccountFlow();
    	enterpriseAccountFlow.setEnterpriseAccountId(enterpriseAccount.getEnterpriseAccountId());
    	enterpriseAccountFlow.setFlowType(1);
    	enterpriseAccountFlow.setFlowAmount(priceMoney);
    	enterpriseAccountFlow.setBalanceAmount(enterpriseAccount.getBalanceAmount().subtract(payable));
    	enterpriseAccountFlow.setBusinessType("升级续费");
    	enterpriseAccountFlow.setCreateTime(DateUtil.getCurTime());
    	
    	enterpriseAccountFlowMapper.insertSelective(enterpriseAccountFlow);
    	
    	return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 查看企业消费记录
    * @author 老王
    * @date 2016年5月24日 下午3:08:11 
    * @param storeAccount 企业代号
    * @return BaseDto
     */
    public BaseDto selectConsumptionRecord (String storeAccount) {
    	List<EnterpriseAccountFlow> enterpriseAccountFlows = enterpriseAccountFlowMapper.selectByStoreAccount(storeAccount);
    	return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, enterpriseAccountFlows);
    }
    
    /**
     *查询账户信息
    * @author 老王
    * @date 2016年5月24日 下午5:31:57 
    * @param storeAccount 企业代号
    * @return BaseDto
     */
    public BaseDto selectEnterpriseAccount (String storeAccount) {
    	EnterpriseAccount enterpriseAccount = enterpriseAccountMapper.selectByStoreAccount(storeAccount);
    	return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, enterpriseAccount);
    }
    
    /**
     * 查询门店信息
    * @author 老王
    * @date 2016年5月26日 下午3:16:45 
    * @param storeId 门店标识
    * @return BaseDto
     */
    public BaseDto selectStoreInfo (Integer storeId) {
    	StoreInfo storeInfo = storeInfoMapper.selectByPrimaryKey(storeId);
    	storeInfo.setCarouselPicture("");
    	storeInfo.setStoreDesc("");
    	Map<String, Object> map = new HashMap<>();
    	map.put("storeInfo", storeInfo);
    	return  new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
    }
    
    /**
     * 短信充值
    * @author 老王
    * @date 2016年5月24日 下午8:17:18 
    * @param storeAccount 企业代号
    * @param msnRechargeType 充值类型
	* @param msnNumber 短信条数
    * @return BaseDto
     */
    @Transactional
    public BaseDto saveMsnRecharge (String storeAccount, Integer msnRechargeType, Integer msnNumber) {
    	EnterpriseAccount enterpriseAccount = enterpriseAccountMapper.selectByStoreAccount(storeAccount);

    	EnterpriseAccount objAccount = new EnterpriseAccount();
    	objAccount.setEnterpriseAccountId(enterpriseAccount.getEnterpriseAccountId());
    	
    	BigDecimal balanceAmount = new BigDecimal(0);
    	Integer msnNum = 0;
    	if (msnRechargeType == 1) {
    		balanceAmount = new BigDecimal(10);
    		msnNum = 100;
    	}
    	else if (msnRechargeType == 2){
    		balanceAmount = new BigDecimal(50);
    		msnNum = 500;
    	}
        else if (msnRechargeType == 3){
    		balanceAmount = new BigDecimal(100);
    		msnNum = 1000;
    	}
		else if (msnRechargeType == 4){
    		balanceAmount = new BigDecimal(200);
    		msnNum = 2000;
		}
		else if (msnRechargeType == 5){
    		balanceAmount = new BigDecimal(1000);
    		msnNum = 10000;
		}
		else if (msnRechargeType == 6){
			balanceAmount = new BigDecimal(msnNumber * 0.1);
			msnNum = msnNumber;
		}
		if (enterpriseAccount.getBalanceAmount().compareTo(balanceAmount) < 1) {
			return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "您的余额不足！");
		}
		objAccount.setBalanceAmount(balanceAmount);
		objAccount.setTotalMsnNum(msnNum);
		objAccount.setBalanceMsnNum(msnNum);
    	enterpriseAccountMapper.updateSaveMsn(objAccount);
    	
    	EnterpriseAccountFlow enterpriseAccountFlow = new EnterpriseAccountFlow();
    	enterpriseAccountFlow.setEnterpriseAccountId(enterpriseAccount.getEnterpriseAccountId());
    	enterpriseAccountFlow.setFlowType(1);
    	enterpriseAccountFlow.setFlowAmount(balanceAmount);
    	enterpriseAccountFlow.setBalanceAmount(enterpriseAccount.getBalanceAmount().subtract(balanceAmount));
    	enterpriseAccountFlow.setBusinessType("短信充值");
    	enterpriseAccountFlow.setCreateTime(DateUtil.getCurTime());
    	
    	enterpriseAccountFlowMapper.insertSelective(enterpriseAccountFlow);
    	
    	EnterpriseMsnFlow enterpriseMsnFlow = new EnterpriseMsnFlow();
    	enterpriseMsnFlow.setEnterpriseAccountId(enterpriseAccount.getEnterpriseAccountId());
    	enterpriseMsnFlow.setFlowType(2);
    	enterpriseMsnFlow.setFlowAmount(msnNum);
    	enterpriseMsnFlow.setBalanceAmount(enterpriseAccount.getBalanceMsnNum() + msnNum);
    	enterpriseMsnFlow.setStoreName("短信充值");
    	enterpriseMsnFlow.setCreateTime(DateUtil.getCurTime());
    	enterpriseMsnFlowMapper.insertSelective(enterpriseMsnFlow);
    	return  new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 短信充值
    * @author 老王
    * @date 2016年5月25日 上午12:57:50 
    * @param storeAccount 企业标识
    * @param storeId 门店标识
    * @param distributionNum 充值数量
    * @return BaseDto
     */
    @Transactional
    public BaseDto distributionMsn (String storeAccount, Integer storeId, Integer distributionNum) {
    	//修改企业短信数量
    	EnterpriseAccount enterpriseAccount = enterpriseAccountMapper.selectByStoreAccount(storeAccount);
    	
    	if (enterpriseAccount.getBalanceMsnNum() < distributionNum) {
    		return  new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "短信数量不能小于分配数量");
    	}
    	
    	EnterpriseAccount obj = new EnterpriseAccount();
    	obj.setEnterpriseAccountId(enterpriseAccount.getEnterpriseAccountId());
    	obj.setBalanceMsnNum(enterpriseAccount.getBalanceMsnNum() - distributionNum);
    	obj.setUpdateTime(DateUtil.getCurTime());
    	enterpriseAccountMapper.updateByPrimaryKeySelective(obj);
    	
    	StoreInfo storeInfo = storeInfoMapper.selectByPrimaryKey(storeId);
    	//修改门店短信数量
    	StoreInfo storeObj = new StoreInfo();
    	storeObj.setStoreId(storeInfo.getStoreId());
    	storeObj.setTotalSms(storeInfo.getTotalSms() + distributionNum);
    	storeObj.setBalanceSms(storeInfo.getBalanceSms() + distributionNum);
    	storeObj.setUpdateTime(DateUtil.getCurTime());
    	
    	storeInfoMapper.updateByPrimaryKey(storeObj);
    	
    	EnterpriseMsnFlow enterpriseMsnFlow = new EnterpriseMsnFlow();
    	enterpriseMsnFlow.setEnterpriseAccountId(enterpriseAccount.getEnterpriseAccountId());
    	enterpriseMsnFlow.setFlowType(1);
    	enterpriseMsnFlow.setFlowAmount(distributionNum);
    	enterpriseMsnFlow.setBalanceAmount(enterpriseAccount.getBalanceMsnNum() - distributionNum);
    	enterpriseMsnFlow.setStoreId(storeInfo.getStoreId());
    	enterpriseMsnFlow.setStoreName(storeInfo.getStoreName());
    	enterpriseMsnFlow.setCreateTime(DateUtil.getCurTime());
    	enterpriseMsnFlowMapper.insertSelective(enterpriseMsnFlow);
    	
    	List<StoreInfo> storeInfoList = storeInfoMapper.selectByStoreAccount(storeAccount);
    	for (StoreInfo storeInfo2 : storeInfoList) {
    		storeInfo2.setStoreDesc("");
		}
    	Integer msnNum = enterpriseAccount.getBalanceMsnNum() - distributionNum;
    	Map<String, Object> map = new HashMap<>();
    	map.put("storeInfoList", storeInfoList);
    	map.put("msnNum", msnNum);
    	return  new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
    }
    
    /**
     * 短信分配记录
    * @author 老王
    * @date 2016年5月25日 下午3:28:03 
    * @param storeAccount 企业代号
    * @return BaseDto
     */
    public BaseDto rechargeFlow (String storeAccount) {
    	List<EnterpriseMsnFlow> enterpriseMsnFlowList = enterpriseMsnFlowMapper.selectByStoreAccount(storeAccount);
    	return  new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, enterpriseMsnFlowList);
    }
    
    /**
     * 新增或修改授权码
    * @author 老王
    * @date 2016年5月25日 下午5:04:34 
    * @param storeAccount 企业代号
    * @param storeAuthorityId 授权标识
	* @param storeId 门店标识
	* @param employeeId 员工标识
	* @param authorityValue 授权码
    * @return BaseDto
     */
    @Transactional
    public BaseDto addOrUpdateAuthority (String storeAccount, Integer storeAuthorityId, Integer storeId, Integer employeeId, String authorityValue) {
    	
    	EnterpriseStoreAuthority objRecord = new EnterpriseStoreAuthority();
    	objRecord.setStoreId(storeId);
    	objRecord.setAuthorityValue(authorityValue);
    	Integer a = enterpriseStoreAuthorityMapper.selectIsExistsAuthorityValue(objRecord);
    	
    	if (a != null && a > 0) {
    		return  new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "该授权码，不能重复设置！");
    	}
    	
    	StoreInfo storeInfo = storeInfoMapper.selectByPrimaryKey(storeId);
    	EmployeeInfo employeeInfo = employeeInfoMapper.selectByPrimaryKey(employeeId);
    	
    	Map<String, Object> map = new HashMap<>();
    	
    	if (storeAuthorityId == null) {
    		
    		EnterpriseStoreAuthority record = new EnterpriseStoreAuthority();
        	record.setEmployeeId(employeeId);
        	List<EnterpriseStoreAuthority> objList = enterpriseStoreAuthorityMapper.selectByProperties(record);
        	if (objList != null && objList.size() > 0) {
        		return  new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "该员工已存在授权码，不能重复授权");
        	}
    		
    		EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
    		enterpriseInfo.setStoreAccount(storeAccount);
    		EnterpriseInfoDto obj = enterpriseInfoMapper.selectByProperties(enterpriseInfo);
    		EnterpriseStoreAuthority enterpriseStoreAuthority = new EnterpriseStoreAuthority();
    		enterpriseStoreAuthority.setEnterpriseInfoId(obj.getEnterpriseInfoId());
    		enterpriseStoreAuthority.setStoreId(storeId);
    		enterpriseStoreAuthority.setStoreName(storeInfo.getStoreName());
    		enterpriseStoreAuthority.setAuthorityValue(authorityValue);
    		enterpriseStoreAuthority.setEmployeeId(employeeId);
    		enterpriseStoreAuthority.setEmployeeCode(employeeInfo.getEmployeeCode());
    		enterpriseStoreAuthority.setName(employeeInfo.getName());
    		enterpriseStoreAuthority.setCreateTime(DateUtil.getCurTime());
    		enterpriseStoreAuthorityMapper.insertSelective(enterpriseStoreAuthority);
    		
    		map.put("storeAuthorityId", enterpriseStoreAuthority.getStoreAuthorityId());
    		map.put("storeId", storeId);
    		map.put("storeName", storeInfo.getStoreName());
    		map.put("authorityValue", authorityValue);
    		map.put("employeeId", employeeId);
    		map.put("employeeCode", employeeInfo.getEmployeeCode());
    		map.put("name", employeeInfo.getName());
    		map.put("createTime", DateUtil.getCurTime());
    	}
    	else {
    		EnterpriseStoreAuthority enterpriseStoreAuthority = new EnterpriseStoreAuthority();
    		enterpriseStoreAuthority.setStoreAuthorityId(storeAuthorityId);
    		enterpriseStoreAuthority.setStoreId(storeId);
    		enterpriseStoreAuthority.setStoreName(storeInfo.getStoreName());
    		enterpriseStoreAuthority.setAuthorityValue(authorityValue);
    		enterpriseStoreAuthority.setEmployeeId(employeeId);
    		enterpriseStoreAuthority.setEmployeeCode(employeeInfo.getEmployeeCode());
    		enterpriseStoreAuthority.setName(employeeInfo.getName());
    		enterpriseStoreAuthority.setCreateTime(DateUtil.getCurTime());
    		enterpriseStoreAuthorityMapper.updateByPrimaryKeySelective(enterpriseStoreAuthority);
    		
    		map.put("storeAuthorityId", enterpriseStoreAuthority.getStoreAuthorityId());
    		map.put("storeId", storeId);
    		map.put("storeName", storeInfo.getStoreName());
    		map.put("authorityValue", authorityValue);
    		map.put("employeeId", employeeId);
    		map.put("employeeCode", employeeInfo.getEmployeeCode());
    		map.put("name", employeeInfo.getName());
    		map.put("createTime", DateUtil.getCurTime());
    	}
    	return  new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
    }
    
    /**
     * 根据授权码查询授权员工
    * @author 老王
    * @date 2016年7月22日 上午11:39:54 
    * @param storeId 门店标识
    * @param authorityValue 授权码
    * @return BaseDto
     */
    public BaseDto selectAuthorityByAuthorityValue (Integer storeId, String authorityValue) {
    	EnterpriseStoreAuthority record = new EnterpriseStoreAuthority();
    	record.setAuthorityValue(authorityValue);
    	record.setStoreId(storeId);
    	List<EnterpriseStoreAuthority> authorityList = enterpriseStoreAuthorityMapper.selectByProperties(record);
    	if (authorityList.isEmpty() || authorityList.size() == 0) {
    		return  new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "授权码不存在，请重新输入！");
    	}
    	else {
    		return  new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, authorityList.get(0).getEmployeeId());
    	}
    }
    
    /**
     * 删除授权码
    * @author 老王
    * @date 2016年6月20日 下午4:43:50 
    * @param storeAuthorityId 授权码标识
    * @return BaseDto
     */
    public BaseDto deleteAuthority (Integer storeAuthorityId) {
    	EnterpriseStoreAuthority enterpriseStoreAuthority = new EnterpriseStoreAuthority();
    	enterpriseStoreAuthority.setStoreAuthorityId(storeAuthorityId);
    	enterpriseStoreAuthority.setIsDeleted(1);
    	
    	enterpriseStoreAuthorityMapper.updateByPrimaryKeySelective(enterpriseStoreAuthority);
    	return  new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 进行店铺设置操作
    * @author 张进军
    * @date Nov 9, 2015 11:19:28 AM
    * @param storeInfo  店铺信息
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    public BaseDto storeSettingAction(StoreInfo storeInfo) {
        storeInfoMapper.updateByPrimaryKey(storeInfo);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     * 新增门店
    * @author 老王
    * @date 2016年4月30日 下午4:55:19 
    * @param storeInfo 门店信息
    * @return BaseDto
     */
    @Transactional
    public BaseDto saveStore (StoreInfo storeInfo) {
    	return addStoreInfo(storeInfo);
    }
    

    /**
     * 门店信息编辑操作
    * @author 张进军
    * @date Jan 29, 2016 12:38:29 PM
    * @param storeId    门店标识
    * @param data       编辑数据
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     * @throws IOException  解码异常
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = Url.StoreInfo.ACTION_STORE_EDITOR, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto storeEditorAction(int storeId, String data) throws IOException{
        data = new String(Base64.decodeBase64(data), "utf-8");
        JSONObject dataJson = JSONObject.fromObject(data);

        String sep = "\001";
        Collection<String> contents = JSONArray.toCollection(dataJson.getJSONArray("contents"), String.class);
        List<Map<String, Object>> contentList = new ArrayList<Map<String, Object>>();
        for (String c : contents) {
            String[] cs = c.split(sep);
            Map<String, Object> cm = new HashMap<String, Object>();
            cm.put("type", cs[1]);
            cm.put("text", cs[0]);
            contentList.add(cm);
        }

        int type = dataJson.getInt("type");
        String content = JSONArray.fromObject(contentList).toString();

        StoreInfo storeInfo = new StoreInfo();
        storeInfo.setStoreId(storeId);
        if (type == 1) {
            storeInfo.setStoreDesc(content);
        }
        else if (type == 2) {
            storeInfo.setCharacteristic(content);
        }
        storeInfoMapper.updateByPrimaryKey(storeInfo);

        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }


    /**
     * 进入店铺设置页面
    * @author 张进军
    * @date Nov 9, 2015 11:19:03 AM
    * @param storeId    店铺标识
    * @return   店铺设置页面
     */
    public ModelAndView storeSettingView(Integer storeId) {
        ModelAndView view = new ModelAndView(View.Setting.STORE_SETTING);
        StoreInfo storeInfo = storeInfoMapper.selectByPrimaryKey(storeId);
        view.addObject("storeInfo", storeInfo);
        
        List<ProjectInfo> projectInfos = projectInfoMapper.selectByStoreId(storeId);
        view.addObject("projectInfos", projectInfos);
        
        List<EmployeeBaseDto> employeeList = employeeInfoMapper.selectEmployeeListByStoreId(storeId);
        view.addObject("storeEmployeeList", employeeList);
        
        List<SpecialService> specialServices = specialServiceMapper.selectByStoreId(storeId);
        view.addObject("specialServices", specialServices);
        view.addObject("specialServicesJs", JSONArray.fromObject(specialServices));
        
        if (StringUtils.isNotBlank(storeInfo.getTeacherIntroduction())) {
            List<String> list = Arrays.asList(storeInfo.getTeacherIntroduction().split(","));
            List<EmployeeBaseDto> showEmployeeList = employeeInfoMapper.selectEmployeeListByList(list);
            view.addObject("showEmployeeList", showEmployeeList);
        }

        return view;
    }


    /**
     * 新增门店
    * @author 张进军
    * @date Oct 29, 2015 11:45:28 AM
    * @param storeInfo       用户姓名
    * @return   成功返回码为0，失败为其它返回码
     */
    @Transactional
    public BaseDto addStoreInfo(StoreInfo storeInfo) {
        EnterpriseAccount enterpriseAccount = enterpriseAccountMapper.selectByStoreAccount(storeInfo.getStoreAccount());

    	if (enterpriseAccount.getBalanceStoreNum() <= 0) {
        	return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "创建门店数已满，无法新增门店！请升级版本");
        }
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("storeAccount", storeInfo.getStoreAccount());
    	map.put("storeName", storeInfo.getStoreName());
    	List<StoreInfo> selectByStoreName = storeInfoMapper.selectByStoreName(map);
    	if (selectByStoreName.size() !=0){
    	    return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "已有门店存在改名称，请修改后保存");
    	}
        storeInfo.setCreateTime(DateUtil.getCurTime());
        storeInfoMapper.insert(storeInfo);
        
        // 在此门店中新增三个岗位
        initStorePosition(storeInfo);
        
        // 初始化商品信息
        initStoreGoodsInfos(storeInfo, storeInfo.getStoreAccount());
        
        EnterpriseAccount record = new EnterpriseAccount();
        record.setEnterpriseAccountId(enterpriseAccount.getEnterpriseAccountId());
        record.setAlreadyStoreNum(enterpriseAccount.getAlreadyStoreNum() + 1);
        record.setBalanceStoreNum(enterpriseAccount.getBalanceStoreNum() - 1);
        enterpriseAccountMapper.updateByPrimaryKeySelective(record);
        
        initStoreData(storeInfo.getStoreId(), storeInfo.getStoreAccount());
        
        // 会员卡复制
        List<MemberLevel> memberLevels = memberLevelMapper.selectMemberLevelByStoreAccount(storeInfo.getStoreAccount());
        for (int i = 0; i < memberLevels.size(); i++) {
            MemberLevel member = memberLevels.get(i);
            Map<String, Integer> params = new HashMap<String, Integer>();
            params.put("levelId", member.getLevelId());
            params.put("storeId", 0);
            MemberLevelDiscount selectByStoreLevel = memberLevelDiscountMapper.selectByStoreLevel(params);
            selectByStoreLevel.setStoreId(storeInfo.getStoreId());
            selectByStoreLevel.setCreateTime(DateUtil.getCurTime());
            selectByStoreLevel.setDiscountId(null);
            memberLevelDiscountMapper.insert(selectByStoreLevel);
        }
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 创建门店的时候将商品信息复制一份数据
    * @author 高国藩
    * @date 2016年7月18日 上午11:41:53
    * @param storeInfo        storeInfo
    * @param storeAccount     storeAccount
     */
    @Transactional
    public void initStoreGoodsInfos(StoreInfo storeInfo, String storeAccount){
        AccountGoods query = new AccountGoods();
        query.setStoreAccount(storeAccount);
        List<AccountGoods> accountGoods = accountGoodsMapper.selectByProperties(query);
        for (int i = 0; i < accountGoods.size(); i++) {
            GoodsInfo info = new GoodsInfo();
            info.setStoreId(storeInfo.getStoreId());
            info.setaId(accountGoods.get(i).getGoodsId());
            info.setIsDeleted(0);
            goodsInfoMapper.insertSelective(info);
            
            // 初始化商品的库存信息0
            GoodsStock goodsStock = new GoodsStock();
            goodsStock.setaId(accountGoods.get(i).getGoodsId());
            goodsStock.setCount(0);
            goodsStock.setStoreId(storeInfo.getStoreId());
            goodsStock.setUpdateTime(DateUtil.getCurDate());
            goodsStockMapper.insertSelective(goodsStock);
        }
    }
    
    /**
     * 初始化门店岗位信息
    * @author 高国藩
    * @date 2016年7月18日 上午11:35:17
    * @param storeInfo storeInfo
     */
    @Transactional
    public void initStorePosition(StoreInfo storeInfo){
        PositionInfo p1 = new PositionInfo(null, storeInfo.getStoreId(), P1, DateUtil.getCurDate(), null, null);
        PositionInfo p2 = new PositionInfo(null, storeInfo.getStoreId(), P2, DateUtil.getCurDate(), null, null);
        PositionInfo p3 = new PositionInfo(null, storeInfo.getStoreId(), P3, DateUtil.getCurDate(), null, null);
        PositionInfo p4 = new PositionInfo(null, storeInfo.getStoreId(), P4, DateUtil.getCurDate(), null, null, 1);
        PositionInfo p5 = new PositionInfo(null, storeInfo.getStoreId(), P5, DateUtil.getCurDate(), null, null, 1);
        positioninfoMapper.insert(p1);
        positioninfoMapper.insert(p2);
        positioninfoMapper.insert(p3);
        positioninfoMapper.insert(p4);
        positioninfoMapper.insert(p5);
    }


    /**
     * 初始化门店数据
    * @author 张进军
    * @date Feb 22, 2016 2:18:30 PM
    * @param storeId    门店标识
    * @param storeAccount 企业代号
     */
    @Transactional
    public void initStoreData(int storeId, String storeAccount){
        /*EmployeeDto employeeDto=new EmployeeDto();
        employeeDto.setStoreId(storeId);
        employeeDto.setDeptId(0);
        employeeDto.setName("操作员");
        employeeDto.setHeadImage("pc/defaulf_male.png");
        employeeDto.setIsDeleted(1);
        employeeInfoMapper.insert(employeeDto);

        int roleId = 2;

        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(employeeDto.getEmployeeId());
        userAccount.setUserName(userName.toString());
        String hash = StringUtil.encryptPwd(userPwd);
        userPwd = hash.split(":")[0];
        String passwordSalt = hash.split(":")[1];
        userAccount.setUserPwd(userPwd);
        userAccount.setPwdSalt(passwordSalt);
        userAccount.setRoleId(roleId);
        userAccount.setStoreId(storeId);
        userAccount.setCreateTime(DateUtil.getCurTime());
        userAccount.setStoreAccount(storeAccount);
        userAccountMapper.insert(userAccount);*/

        //如果是总店，添加默认等级
       /* if (storeType != 3) {
            //添加一个默认会员等级
            MemberLevel memberLevel = new MemberLevel();
            memberLevel.setStoreId(storeId);
            memberLevel.setCreateTime(DateUtil.getCurTime());
            memberLevel.setGoodsDiscount(100);
            memberLevel.setIntegralNumber(1);
            memberLevel.setIntegralUnit(0);
            memberLevel.setIsDefault(1);
            memberLevel.setIsDeleted(0);
            memberLevel.setLevelName("默认会员");
            memberLevel.setProjectDiscount(100);
            memberLevel.setSellAmount(0);
            memberLevelMapper.insert(memberLevel);

            //初始化门店管理制度
            storeManageRuleMapper.initStoreRuleByStoreId(storeId);
        }*/

        /*storeManageRuleMapper.initStoreRuleByStoreId(storeId);*/
        
        CommissionScheme record = new CommissionScheme();
        record.setStoreId(storeId);
        record.setCommissionType(1);
        record.setCreateTime(DateUtil.getCurTime());
        //设置默认提成分配方案
        commissionSchemeMapper.insertSelective(record);
        record.setCommissionType(2);
        commissionSchemeMapper.insertSelective(record);
        
        //创建默认设置
        StoreSetting setting = new StoreSetting();
        setting.setStoreId(storeId);
        storeSettingMapper.insert(setting);
        
        
   
    }

    /**
     * 根据openid查询门店信息
     * @author gebing
     * @date 2015年12月4日
     * @param openId 微信openid
     * @return 门店信息
     */
    public StoreInfo getStoreByOpenId(String openId) {

        WechatStore wechatStore = wechatStoreMapper.selectByPrimaryKey(openId);
        if (wechatStore == null || wechatStore.getStoreId() == null) {
            return null;
        }
        return storeInfoMapper.selectByPrimaryKey(wechatStore.getStoreId());
    }

    /**
     * 根据门店id查询门店账号
     * @author gebing
     * @date 2015年12月4日
     * @param storeId 门店id
     * @return 门店账号
     */
    public StoreAccount getAccountByStoreId(Integer storeId) {
        return storeAccountMapper.selectByPrimaryKey(storeId);
    }

    /**
     * 根据门店id 查询门店信息
     * @author gebing
     * @date 2015年12月4日
     * @param storeId 门店id
     * @return 门店信息
     */
    public StoreInfo getByStoreId(Integer storeId) {
        return storeInfoMapper.selectByPrimaryKey(storeId);
    }

    /**
     * 根据总店id查询旗下的分店数量
     * @param storeId 总店id
     * @return 分店数量
     */
    public int countChainStores(Integer storeId) {
        return storeInfoMapper.countByHQStoreId(storeId);
    }

    /**
     * 根据总店id查询旗下所有分店
     * @param hqStoreId 总店id
     * @return 总店id查询旗下所有分店
     */
    public List<StoreInfo> getChainsByHQStoreId(Integer hqStoreId) {
        return storeInfoMapper.selectChainsByHQStoreId(hqStoreId);
    }

    /**
     * 根据多个门店id查询门店信息
     * @author gebing
     * @date 2015年12月4日
     * @param storeIds 多个门店id
     * @return 多个门店信息
     */
    public List<StoreInfo> getStoreByStoreIds(List<Integer> storeIds) {
        return storeInfoMapper.selectByStoreIds(storeIds);
    }

    /**
     * 根据多个门店id查询门店账号
     * @author gebing
     * @date 2015年12月4日
     * @param storeIds 多个门店id
     * @return 多个门店账号
     */
    public List<StoreAccount> getAccountByStoreIds(List<Integer> storeIds) {
        return storeAccountMapper.selectByStoreIds(storeIds);
    }

    /**
     * 根据多个门店id和门店账号状态查询门店账号
     * @author gebing
     * @date 2015年12月4日
     * @param storeIds 多个门店id
     * @param status 门店状态
     * @return 多个门店账号
     */
    public List<StoreAccount> getAccountByStoreIds(List<Integer> storeIds, int status) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("status", status);
//        params.put("storeIds", StringUtils.join(storeIds, ','));
        params.put("storeIds", storeIds);
        return storeAccountMapper.selectByStoreIdsAndStatus(params);
    }

    /**
     * 根据多个门店id查询正常使用的门店账号
     * @author gebing
     * @date 2015年12月4日
     * @param storeIds 多个门店id
     * @return storeIds中正常使用门店账号
     */
    public List<StoreAccount> getNormalAccountByStoreIds(List<Integer> storeIds) {
        return storeAccountMapper.selectNormalAccountByStoreIds(storeIds);
    }

    /**
     * 根据多个门店id查询需要续费的门店账号
     * @author gebing
     * @date 2015年12月4日
     * @param storeIds 多个门店id
     * @return storeIds中需要续费的门店账号
     */
    public List<StoreAccount> getRenewAccountByStoreIds(List<Integer> storeIds) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("balanceDays", STORE_RENEW_DAYS);
        params.put("storeIds", storeIds);
        return storeAccountMapper.selectRenewAccountByStoreIds(params);
    }

    /**
     * 根据多个门店id查询已停用(剩余使用时间为0)的门店账号
     * @author gebing
     * @date 2015年12月4日
     * @param storeIds 多个门店id
     * @return storeIds中已停用的门店账号
     */
    public List<StoreAccount> getOverAccountByStoreIds(List<Integer> storeIds) {
        return storeAccountMapper.selectOverAccountByStoreIds(storeIds);
    }

    /**
     * 根据门店标识修改门店的使用期限/短信数量
    * @author 张进军
    * @date Feb 23, 2016 9:51:35 AM
    * @param storeAccount   门店账户标识、系统使用天数、短信数量
    * @return   0:失败，1:成功
     */
    public int storeAccountCharge(StoreAccount storeAccount){
        return storeAccountMapper.updateSysAndSmsByStoreId(storeAccount);
    }

    /**
     * 根据门店标识、角色标识查询账户用户名
    * @author 张进军
    * @date Feb 23, 2016 11:34:19 AM
    * @param map    门店标识、角色标识
    * @return   账户用户名
     */
    public String selectUserNameByStoreIdAndRoleId(Map<String, Integer> map){
        return userAccountMapper.selectUserNameByStoreIdAndRoleId(map);
    }


    /**
     * 获取门店过期日期
    * @author 张进军
    * @date Feb 23, 2016 3:12:12 PM
    * @param storeId    门店标识
    * @return   过期日期
     */
    public String getStoreOverdueDateByStoreId(int storeId) {
        StoreAccount storeAccount = storeAccountMapper.selectByPrimaryKey(storeId);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, storeAccount.getBalanceDays());
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
        return smf.format(calendar.getTime());
    }


    /**
     * 根据多个门店id和时间查询各个门店收益
     * @author gebing
     * @date 2015年12月4日
     * @param storeIds 多个门店id
     * @param begin 开始
     * @param end 结束
     * @return 各个门店收益
     */
    public List<StoreIncomeDto> getMotnIncomesByTime(List<Integer> storeIds,
            String begin, String end) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("begin", begin);
        params.put("end", end);
        params.put("storeIds", storeIds);
        return storeFlowMapper.selectMonthIncomesByTime(params);
    }

    /**
     * 根据时间区间查询营业汇总数据
     * @author gebing
     * @param <E> 泛型
     * @date 2016年1月4日
     * @param begin 开始时间
     * @param end 结束时间
     * @param dateType 时间区间类型, 0 日, 1 周, 2 月, 3 年
     * @param storeId 门店id
     * @param orderTypes order_details的order_type字段的范围
     * @param summaryType 统计类型, 0 营业汇总或现金收入, 1 卡项销售
     * @return SummaryResultDto
     */
    @SuppressWarnings("unchecked")
    public <E extends SummaryResultDto> E  getSummary(String begin, String end,
            Integer dateType, List<Integer> orderTypes, int summaryType, Integer storeId) {
        Map<String, Object> params=computeParams(begin, end, dateType, storeId);
        begin=(String)(params.get("begin"));
        end=(String)(params.get("end"));
        dateType=(Integer)(params.get("dateType"));
        params.put("orderTypes", (orderTypes == null || orderTypes.isEmpty()) ? null : orderTypes);
        List<StoreSummaryDto> summaryDtos = orderInfoMapper.selectSummary(params);
        List<StoreSummaryDto> lastSummaryDtos = null;
        String oBegin = begin;
        String oEnd = end;
        if (dateType != null && end.compareTo(begin) > 0) {
            params=computeLastParams(begin, end, dateType, params);
            if (begin != null && end != null) {
                lastSummaryDtos = orderInfoMapper.selectSummary(params);
            }
        }
        try {
            switch (summaryType) {
                case 0: {
                    return (E) new StoreSummaryResultDto(deptInfoMapper.getDeptInfo(storeId), dateType, oBegin, oEnd, summaryDtos, lastSummaryDtos);
                }
                case 1: {
                    Map<Integer, Integer> memberLevelIds = new HashMap<Integer, Integer>();
                    Map<Integer, MemberAccount> mMemberAccounts = new HashMap<Integer, MemberAccount>();
                    if (summaryDtos != null && !summaryDtos.isEmpty()) {
                        List<Integer> memberIds = new ArrayList<Integer>();
                        for (StoreSummaryDto storeSummaryDto : summaryDtos) {
                            Integer memberId = storeSummaryDto.getMemberId();
                            if (memberId == null || memberIds.contains(memberId)) {
                                continue;
                            }
                            memberIds.add(memberId);
                        }
                        if (!memberIds.isEmpty()) {
                            List<MemberInfo> memInfos = memberInfoMapper.selectByMemberIds(memberIds);
                            if (memInfos != null && !memInfos.isEmpty()) {
                                for (MemberInfo memberInfo : memInfos) {
                                    memberLevelIds.put(memberInfo.getMemberId(), memberInfo.getLevelId());
                                }
                            }
                            List<MemberAccount> memberAccounts = memberAccountMapper.selectByAccountIds(memberIds);
                            if (memberAccounts != null && !memberAccounts.isEmpty()) {
                                for (MemberAccount memberAccount : memberAccounts) {
                                    mMemberAccounts.put(memberAccount.getAccountId(), memberAccount);
                                }
                            }
                        }
                    }

                    return (E) new CardsaleStoreSummaryResultDto(mMemberAccounts, memberLevelIds, memberLevelMapper.selectByStoreId(storeId),
                            dateType, oBegin, oEnd, summaryDtos, lastSummaryDtos);
                }
                case 2: {
                    Map<Integer, Integer> memberLevelIds = new HashMap<Integer, Integer>();
                    if (summaryDtos != null && !summaryDtos.isEmpty()) {
                        List<Integer> memberIds = new ArrayList<Integer>();
                        for (StoreSummaryDto storeSummaryDto : summaryDtos) {
                            Integer memberId = storeSummaryDto.getMemberId();
                            if (memberId == null || memberIds.contains(memberId)) {
                                continue;
                            }
                            memberIds.add(memberId);
                        }
                        if (!memberIds.isEmpty()) {
                            List<MemberInfo> memInfos = memberInfoMapper.selectByMemberIds(memberIds);
                            if (memInfos != null && !memInfos.isEmpty()) {
                                for (MemberInfo memberInfo : memInfos) {
                                    memberLevelIds.put(memberInfo.getMemberId(), memberInfo.getLevelId());
                                }
                            }
                        }
                    }
                    return (E) new CardconsumptionStoreSummaryResultDto(memberLevelIds, memberLevelMapper.selectByStoreId(storeId),
                            deptInfoMapper.getDeptInfo(storeId), dateType, oBegin, oEnd, summaryDtos, lastSummaryDtos);
                }

                default:
                    throw new RuntimeException("unsupported summaryType: " + summaryType);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
    * @author 乐建建
    * @date 2016年2月21日 下午1:34:03
    * @param dto 封装参数条件
    * @param flag 标志
    * @return  现金收入封装类
    */
    public CashIncomeDto getCashIncomeData(SummaryResultDto dto, boolean flag){

        StoreInfo store=getStoreInfoById(dto.getStoreId());

        List<StoreInfo> stores = null;
        //如果当前门店是连锁总店
        if (store.getStoreType()==2){
            stores=storeInfoMapper.selectBaseInfoByMainId(store.getStoreId());
        }
        if (flag){
            stores=storeInfoMapper.selectBaseInfoByMainId(store.getHqStoreId());
        }
        Map<String, Object> params=new HashMap<String, Object>();
        params=computeParams(dto.getBegin(), dto.getEnd(), dto.getDateType(), dto.getStoreId());
        String oldBegin=params.get("begin").toString();
        String oldEnd=params.get("end").toString();
        dto.setBegin(oldBegin);
        dto.setEnd(oldEnd);
        dto.setDateType((Integer)params.get("dateType"));
        List<DeptCashIncome> deptSummary = orderInfoService.processData(dto);
        List<TrendDeptDataDto> trends=null;
        if (dto.getDateType()!=null && (dto.getDateType()==2 || dto.getDateType()==3 || dto.getDateType()==4)){
            trends=orderInfoMapper.getDeptCashTrendData(dto);
        }
        params=computeLastParams(dto.getBegin(), dto.getEnd(), dto.getDateType(), params);
        dto.setBegin((String)params.get("begin"));
        dto.setEnd((String)params.get("end"));
        dto.setDateType((Integer)params.get("dateType"));
        List<DeptCashIncome> lastDeptSummary = orderInfoService.processData(dto);
        dto.setBegin(oldBegin);
        dto.setEnd(oldEnd);
        return new CashIncomeDto(stores, deptSummary, trends, lastDeptSummary, dto);
    }

    /**
    * @author 乐建建
    * @date 2016年2月18日 下午2:15:54
    * @param flag 判断是否从页面传storeId
    * @param dto 封装参数条件
    * @return BusinessSummaryDto 营业汇总封装类
    */
    public BusinessSummaryDto getBusinessSummaryData(SummaryResultDto dto, boolean flag){
        Map<String, Object> params=new HashMap<String, Object>();


        StoreInfo store=getStoreInfoById(dto.getStoreId());

        List<StoreInfo> stores = null;
        //如果当前门店是连锁总店
        if (store.getStoreType()==2){
            stores=storeInfoMapper.selectBaseInfoByMainId(store.getStoreId());
        }
        if (flag){
            stores=storeInfoMapper.selectBaseInfoByMainId(store.getHqStoreId());
        }
        params=computeParams(dto.getBegin(), dto.getEnd(), dto.getDateType(), dto.getStoreId());
        String oldBegin=params.get("begin").toString();
        String oldEnd=params.get("end").toString();
        dto.setBegin(oldBegin);
        dto.setEnd(oldEnd);
        dto.setDateType((Integer)params.get("dateType"));
        List<DeptSummaryDto> depts= orderInfoService.getProcessedDeptData(dto);
        BusinessSummaryTrend trend=null;
        if ((dto.getDateType()!=null)&&(dto.getDateType()==2 || dto.getDateType()==3 || dto.getDateType()==4)){
            trend=orderInfoService.getBusinessSummaryTrend(dto);
        }
        params=computeLastParams(dto.getBegin(), dto.getEnd(), dto.getDateType(), params);

        dto.setBegin(params.get("begin").toString());
        dto.setEnd(params.get("end").toString());

        List<DeptSummaryDto> lastDepts= null;
        if (dto.getDateType()!=null){
            lastDepts=orderInfoService.getProcessedDeptData(dto);
        }
        return new BusinessSummaryDto(stores, null, null, null, null, depts, trend, lastDepts, oldBegin, oldEnd, dto.getDateType(), dto.getStoreId());
    }



    /**
     * @param begin 开始日期
     * @param end 截止日期
     * @param dateType 日期类型
     * @param storeId 门店id
     * @return 封装后的参数
     */
    private Map<String, Object> computeParams(String begin, String end,
            Integer dateType, Integer storeId){
        Map<String, Object> params = new HashMap<String, Object>();
        if (begin == null && end == null && dateType == null) {
            dateType = 0;
        }
        if (dateType != null) {
            switch (dateType) {
                case 0: { //本日
                    begin = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
                    end = DateFormatUtils.format(DateUtils.addDays(new Date(), 1), "yyyy-MM-dd");
                    break;
                }
                case 1: { //本周
                    Calendar c = Calendar.getInstance();
                    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
                    if (dayOfWeek == 0) {
                        dayOfWeek = 7;
                    }
                    c.add(Calendar.DATE, - dayOfWeek + 1);
                    begin = DateFormatUtils.format(c, "yyyy-MM-dd");

                    dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
                    if (dayOfWeek == 0) {
                        dayOfWeek = 7;
                    }
                    c.add(Calendar.DATE, - dayOfWeek + 7);
                    end = DateFormatUtils.format(DateUtils.addDays(c.getTime(), 1), "yyyy-MM-dd");
                    break;
                }
                case 2:
                case 3: { //本月
                    Calendar c = Calendar.getInstance();
                    c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
                    begin = DateFormatUtils.format(c, "yyyy-MM-dd");
                    c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
                    end = DateFormatUtils.format(DateUtils.addDays(c.getTime(), 1), "yyyy-MM-dd");
                    break;
                }
                case 4: { //本年
                    Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    begin = year + "-01-01";
                    end = (year + 1) + "-01-01";
                    break;
                }
                default:
                    break;
            }
        }
        try {
            if (StringUtils.isBlank(begin) && StringUtils.isBlank(end)) {
                dateType = 0;
            }
            if (StringUtils.isBlank(begin)) {
                begin = DateFormatUtils.format(new Date(), "yyyy/MM/dd");
            }
            begin = DateFormatUtils.format(DateUtils.parseDate(begin, new String[]{"yyyy/MM/dd"}), "yyyy-MM-dd");
            if (StringUtils.isBlank(end)) {
                end = DateFormatUtils.format(new Date(), "yyyy/MM/dd");
            }
            if (dateType!=null){
                end = DateFormatUtils.format(DateUtils.addDays(DateUtils.parseDate(end, new String[]{"yyyy/MM/dd"}), 1), "yyyy-MM-dd");
            }
            else {
                end=end.replaceAll("/", "-");
            }
        }
        catch (Exception e) {
        }
        params.put("begin", begin);
        params.put("end", end);
        params.put("storeId", storeId);
        params.put("dateType", dateType);
        return params;
    }

    /**
     * @param begin 起始日期
     * @param end 终止日期
     * @param dateType 日期类型
     * @param params 参数条件 包括起始日期 终止日期
     * @return 填充数据
     */
    public Map<String, Object> computeLastParams(String begin, String end, Integer dateType, Map<String, Object> params){
        if (dateType != null && end.compareTo(begin) > 0) {
            switch (dateType) {
                case 0: { // 日, 获取前一天的数据
                    begin = DateFormatUtils.format(DateUtils.addDays(new Date(), -1), "yyyy-MM-dd");
                    end = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
                    break;
                }
                case 1: {// 周, 获取前一周的数据
                    Calendar c = Calendar.getInstance();
                    c.setTime(DateUtils.addWeeks(new Date(), -1));
                    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
                    if (dayOfWeek == 0) {
                        dayOfWeek = 7;
                    }
                    c.add(Calendar.DATE, - dayOfWeek + 1);
                    begin = DateFormatUtils.format(c, "yyyy-MM-dd");

                    dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
                    if (dayOfWeek == 0) {
                        dayOfWeek = 7;
                    }
                    c.add(Calendar.DATE, - dayOfWeek + 7);
                    end = DateFormatUtils.format(DateUtils.addDays(c.getTime(), 1), "yyyy-MM-dd");
                    break;
                }
                case 2:
                case 3: { // 月, 获取前一月的数据
                    Calendar c = Calendar.getInstance();
                    c.setTime(DateUtils.addMonths(new Date(), -1));
                    c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
                    begin = DateFormatUtils.format(c, "yyyy-MM-dd");
                    c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
                    end = DateFormatUtils.format(DateUtils.addDays(c.getTime(), 1), "yyyy-MM-dd");
                    break;
                }
                case 4: {// 年, 获取前一年的数据
                    Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    begin = (year - 1) + "-01-01";
                    end = year + "-01-01";
                    break;
                }
                default: {
                    begin = null;
                    end = null;
                    break;
                }
            }
            if (begin != null && end != null) {
                params.put("begin", begin);
                params.put("end", end);
            }
        }
        return params;
    }

    /**
     * @param begin 起始日期
     * @param end 终止日期
     * @param flag 标志
     * @param dateType 日期类型
     * @param storeId 当前门店id
     * @return LaborPerformanceSummaryDto 传回前端
     */
    public DeptLaborSummaryDto getLaborPerformanceSummary(String begin, String end, Integer dateType, Integer storeId, boolean flag){

        StoreInfo store=getStoreInfoById(storeId);
        List<StoreInfo> stores = null;
        //如果当前门店是连锁总店
        if (store.getStoreType()==2){
            stores=storeInfoMapper.selectBaseInfoByMainId(store.getStoreId());
        }
        if (flag){
            stores=storeInfoMapper.selectBaseInfoByMainId(store.getHqStoreId());
        }

        SummaryResultDto dto=new SummaryResultDto();
        //当begin end为空时 需要进行预处理
        Map<String, Object> params=computeParams(begin, end, dateType, storeId);
        begin=(String)(params.get("begin"));
        end=(String)(params.get("end"));
        dateType=(Integer)(params.get("dateType"));
        dto.setBegin(begin);
        dto.setEnd(end);
        dto.setOrderType(1);
        dto.setStoreId(storeId);
        dto.setDateType(dateType);
        List<ServiceReportDto> deptsSummary =businessReportMapper.selectProjectReportByStoreId(dto);
        List<TrendDeptDataDto> trendData=null;
        if (dateType!=null && (dateType==2 || dateType==3 || dateType==4)){
            trendData=orderDetailMapper.getTrendData(dto);
        }
        List<ProjectLaborRank> ranks= businessReportMapper.getProjectInDeptRank(dto);
        List<DeptInfo> deptsInShop=deptInfoMapper.getDeptInfo(storeId);
        ranks=fillRank(ranks);
        DeptLaborSummaryDto summaryDto=orderInfoMapper.getCurrSummaryData(dto);
        params=computeLastParams(begin, end, dateType, params);
        dto.setBegin((String)(params.get("begin")));
        dto.setEnd((String)(params.get("end")));
        List<ProjectLaborRank> lastranks= businessReportMapper.getProjectInDeptRank(dto);
        lastranks=fillRank(lastranks);
        DeptLaborSummaryDto lastsummaryDto=orderInfoMapper.getCurrSummaryData(dto);
        return new DeptLaborSummaryDto(stores, begin, end, trendData, dateType, deptsInShop,
                lastsummaryDto, summaryDto, deptsSummary, ranks, lastranks, dto.getStoreId());
    }


    /**
     * @param ranks 没有排名的项目劳动业绩排行
     * @return 有排名的项目劳动业绩
     */
    private List<ProjectLaborRank> fillRank(List<ProjectLaborRank> ranks) {
        for (int i=0; i<ranks.size(); i++){
            ranks.get(i).setProjectRank(i+1);
        }
        return ranks;
    }





    /**
    * @author 乐建建
    * @date 2016年1月21日 下午2:33:52
    * @param dto 封装参数条件
    * @param flag 标志
    * @return  ComboSummaryViewDto 封装对象 在页面直接取相应值
    */
    public ComboSummaryViewDto getComboSummaryResult(SummaryResultDto dto, boolean flag){

        StoreInfo store=getStoreInfoById(dto.getStoreId());
        List<StoreInfo> stores = null;
        //如果当前门店是连锁总店
        if (store.getStoreType()==2){
            stores=storeInfoMapper.selectBaseInfoByMainId(store.getStoreId());
        }
        if (flag){
            stores=storeInfoMapper.selectBaseInfoByMainId(store.getHqStoreId());
        }

        Map<String, Object> params=computeParams(dto.getBegin(), dto.getEnd(), dto.getDateType(), dto.getStoreId());
        String begin=params.get("begin").toString();
        String end=params.get("end").toString();
        Integer dateType=(Integer)(params.get("dateType"));
        dto.setBegin(begin);
        dto.setEnd(end);
        dto.setDateType(dateType);
        List<DeptComboSummaryDto>  deptSummarys=comboInfoService.getComboInfo(dto);
        List<ComboSummaryDto> comboRank=comboInfoService.getComboRank(dto);
        CashComboSalesVo cash=comboInfoService.cashComboSalesProcessed(dto);
        CardComboSalesVo card=comboInfoService.cardComboSalesProcessed(dto);
        List<TrendDeptDataDto> trendData=null;

        if (dto.getDateType()!=null && (dto.getDateType()==2 || dto.getDateType()==3 || dto.getDateType()==4)){
            trendData=comboInfoService.getComboTrendData(dto);
        }
        List<DeptInfo> deptsInShop=deptInfoMapper.getDeptInfo(dto.getStoreId());
        params=computeLastParams(begin, end, dateType, params);
        dto.setBegin(params.get("begin").toString());
        dto.setEnd(params.get("end").toString());

        List<DeptComboSummaryDto>  lastdeptSummarys=comboInfoService.getComboInfo(dto);
        List<ComboSummaryDto> lastcomboRank=comboInfoService.getComboRank(dto);
        return new ComboSummaryViewDto(cash, card, stores, trendData, begin, end, dateType , deptSummarys,
                comboRank, lastdeptSummarys, lastcomboRank, deptsInShop, dto.getStoreId());
    }


    /**
    * @author 乐建建
    * @date 2016年1月22日 下午8:30:37
    * @param dto  封装参数条件
    * @param flag 标志
    * @return CommoditySalesDto 商品销售结果
    */
    public CommoditySalesDto getCommodityResult(SummaryResultDto dto, boolean flag){

        StoreInfo store=getStoreInfoById(dto.getStoreId());
        List<StoreInfo> stores = null;
        //如果当前门店是连锁总店
        if (store.getStoreType()==2){
            stores=storeInfoMapper.selectBaseInfoByMainId(store.getStoreId());
        }
        if (flag){
            stores=storeInfoMapper.selectBaseInfoByMainId(store.getHqStoreId());
        }

        Map<String, Object> params=computeParams(dto.getBegin(), dto.getEnd(), dto.getDateType(), dto.getStoreId());
        String begin=params.get("begin").toString();
        String end=params.get("end").toString();
        Integer dateType=(Integer)(params.get("dateType"));
        dto.setBegin(begin);
        dto.setEnd(end);
        dto.setDateType(dateType);
        List<DeptGoodSalesSummaryDto> depts= goodsInfoService.getDeptGoodSummary(dto);
        List<GoodSalesSummaryDto>  ranks=goodsInfoService.getGoodRank(dto);
        List<TrendDeptDataDto>  trends=goodsInfoService.getGoodTrendData(dto);
        List<ServiceReportDto> deptsSummary =businessReportMapper.selectGoodReportByStoreId(dto);
        List<DeptInfo> deptsInShop=deptInfoMapper.getDeptInfo(dto.getStoreId());
        CardStoreSalesVo card = goodsInfoService.cardStoreSalesProcessed(dto);
        CashStoreSalesVo cash = goodsInfoService.cashStoreSalesProcessed(dto);

        params=computeLastParams(begin, end, dateType, params);
        dto.setBegin(params.get("begin").toString());
        dto.setEnd(params.get("end").toString());
        List<DeptGoodSalesSummaryDto> lastDepts=goodsInfoService.getDeptGoodSummary(dto);
        List<GoodSalesSummaryDto>  lastRanks=goodsInfoService.getGoodRank(dto);

        return new CommoditySalesDto(cash, card, stores, trends, begin, end, dateType, depts, ranks, lastDepts,
                lastRanks, deptsInShop, deptsSummary, dto.getStoreId());
    }


    /**
     * 初始化商品
    * @author 高国藩
    * @date 2016年1月19日 下午9:10:39
    * @param storeId    门店
    * @param deptId     部门
    * @return           商品信息
     */
    @SuppressWarnings("unused")
    private GoodsInfo initGoodsInfo(Integer storeId, Integer deptId) {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setStoreId(storeId);
        goodsCategory.setCategoryName("标准系列");
        goodsCategory.setCreateTime(DateUtil.getCurTime());
        goodsCategory.setDeptId(deptId);
        goodsCategory.setIsDeleted(0);
        Integer categoryId = goodsInfoService.saveGoodsCategory(goodsCategory);
        goodsCategory.setCategoryId(categoryId);
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setAffiliatedImage("zefun/images/pic_none.gif,zefun/images/pic_none.gif,zefun/images/pic_none.gif,zefun/images/pic_none.gif");
//        goodsInfo.setBrandId("海飞丝");
        goodsInfo.setCalculationType(1);
        goodsInfo.setCardAmount(5);
        goodsInfo.setCategoryId(categoryId);
        goodsInfo.setCommissionAmount(5);
        goodsInfo.setCommissionType(1);
//        goodsInfo.setCostPrice(new BigDecimal(50));
        goodsInfo.setDeptId(deptId);
        goodsInfo.setGoodsDesc("门店初始化商品模板");
        goodsInfo.setGoodsImage("zefun/images/pic_none.gif");
//        goodsInfo.setGoodsName("标准商品");
        goodsInfo.setGoodsPrice(new BigDecimal(100));
//        goodsInfo.setGoodsStock(10);
        goodsInfo.setHighestDiscount(new BigDecimal(5));
        goodsInfo.setIsCashDeduction(1);
//        goodsInfo.setIsSellProduct(1);
        goodsInfo.setOnlineShoppingPrice(new BigDecimal(10));
        goodsInfo.setWarnStock(10);
        goodsInfo.setIsDeleted(0);
        goodsInfo.setStoreId(storeId);
        Integer goodsId = goodsInfoService.saveGoodsInfo(goodsInfo, null, null);
        goodsInfo.setGoodsId(goodsId);
        return goodsInfo;
    }

    /**
    * @author 乐建建
    * @date 2016年1月26日 下午5:13:43
    * @param dateType 日期类型
    * @param deptId 部门id
    * @param storeId 门店id
    * @param deptName 部门名称
    * @return 项目劳动业绩排行列表
    */
    public List<ProjectLaborRank> getLaborPerformanceRankByDeptAndDate(Integer dateType, Integer deptId, String deptName, Integer storeId){

        SummaryResultDto dto=new SummaryResultDto();
        Map<String, Object> params=computeParams(null, null, dateType, null);
        String begin=params.get("begin").toString();
        String end=params.get("end").toString();
        dto.setBegin(begin);
        dto.setEnd(end);
        dto.setDeptId(deptId);
        List<ProjectLaborRank> resultList=businessReportMapper.getProjectRankByDept(dto);
        resultList=fillRank(resultList);
        params=computeLastParams(begin, end, dateType, params);
        dto.setBegin(params.get("begin").toString());
        dto.setEnd(params.get("end").toString());
        List<ProjectLaborRank> lastResultList=businessReportMapper.getProjectRankByDept(dto);

        lastResultList=fillRank(lastResultList);
        Map<String, Integer> nameForRank=processRank(lastResultList);
        resultList=fillLastRank(nameForRank, deptName, resultList);
        return resultList;
    }

    /**
    * @author 乐建建
    * @date 2016年1月26日 下午8:07:04
    * @param nameForRank 名称与排名的对应表
    * @param resultList 劳动业绩排行表
    * @param deptName 部门名称
    * @return 填充了上期排名的劳动业绩排行表
    */
    private List<ProjectLaborRank> fillLastRank(
            Map<String, Integer> nameForRank, String deptName,
            List<ProjectLaborRank> resultList) {
        for (int i=0; i<resultList.size(); i++){
            Object obj=nameForRank.get(resultList.get(i).getProjectName());
            resultList.get(i).setDeptName(deptName);
            if (obj!=null){
                resultList.get(i).setLastProjectRank(obj.toString());
            }
            else {
                resultList.get(i).setLastProjectRank("---");
            }
        }
        return resultList;
    }


    /**
     * @param lastranks2 上期店内各项目根据销量的排名
     * @return 上期店内项目名与排名对应表
     */
    public Map<String, Integer> processRank(List<ProjectLaborRank> lastranks2) {
        Map<String, Integer> projectRank=new HashMap<String, Integer>();
        for (int i=0; i<lastranks2.size(); i++){
            String name=lastranks2.get(i).getProjectName();
            Integer rank=lastranks2.get(i).getProjectRank();
            projectRank.put(name, rank);
        }
        return projectRank;
    }

    /**
    * @author 乐建建
    * @date 2016年1月27日 下午3:38:27
    * @param type 排序类型  即从多到少 或者从少到多
    * @param begin 开始日期
    * @param end 终止日期
    * @param dateType 日期区间类型
    * @param storeId 门店id
    * @return  List<ProjectLaborRank>
    */
    public DeptLaborSummaryDto getProjectRankByNum(Integer type, String begin, String end, Integer storeId, Integer dateType){

        SummaryResultDto dto=new SummaryResultDto();
        dto.setBegin(begin.replaceAll("/", "-"));
        dto.setEnd(end.replaceAll("/", "-"));
        dto.setType(type);
        dto.setStoreId(storeId);
        List<ProjectLaborRank> rankList=businessReportMapper.getProjectInDeptRank(dto);
        rankList=fillRank(rankList);
        Map<String, Object> params=new HashMap<String, Object>();

        params=computeLastParams(begin, end, dateType, params);

        dto.setBegin(params.get("begin").toString());
        dto.setEnd(params.get("end").toString());

        List<ProjectLaborRank> lastRankList=businessReportMapper.getProjectInDeptRank(dto);
        lastRankList=fillRank(lastRankList);
        List<DeptInfo> deptsInShop=deptInfoMapper.getDeptInfo(storeId);
        return new DeptLaborSummaryDto(null, begin, end, null, dateType, deptsInShop, null, null, null, rankList, lastRankList, dto.getStoreId());
    }

    /**
    * @author 乐建建
    * @date 2016年1月27日 下午5:49:44
    * @param dateType 日期类型
    * @param deptId 部门id
    * @param deptName 部门名称
    * @return  List<ComboSummaryDto>
    */
    public List<ComboSummaryDto> getComboRankByDept(Integer dateType, Integer deptId, String deptName){
        SummaryResultDto dto=new SummaryResultDto();
        Map<String, Object> params=computeParams(null, null, dateType, null);
        String begin=params.get("begin").toString();
        String end=params.get("end").toString();
        dto.setBegin(begin);
        dto.setEnd(end);
        dto.setDeptId(deptId);
        List<ComboSummaryDto> comboRank=comboInfoService.getComboRankByDept(dto);

        params=computeLastParams(begin, end, dateType, params);
        dto.setBegin(params.get("begin").toString());
        dto.setEnd(params.get("end").toString());
        List<ComboSummaryDto> lastcomboRank=comboInfoService.getComboRankByDept(dto);

        Map<String, Integer> nameForRank=comboInfoService.getNameForRank(lastcomboRank);

        return comboInfoService.fillLastRank(nameForRank, deptName, comboRank);
    }

    /**
    * @author 乐建建
    * @date 2016年1月27日 下午7:45:52
    * @param type 排序类型
    * @param begin 起始时间
    * @param end 终止时间
    * @param storeId 门店id
    * @param dateType 日期区间类型
    * @return ComboSummaryViewDto 对象
    */
    public ComboSummaryViewDto getComboRankBySalesOrAmt(Integer type, String begin, String end, Integer storeId, Integer dateType){
        SummaryResultDto dto=new SummaryResultDto();
        dto.setBegin(begin.replaceAll("/", "-"));
        dto.setEnd(end.replaceAll("/", "-"));
        dto.setType(type);
        dto.setStoreId(storeId);
        List<ComboSummaryDto> comboRank=comboInfoService.getComboRank(dto);
        List<DeptInfo> deptsInShop=deptInfoMapper.getDeptInfo(dto.getStoreId());

        Map<String, Object> params=new HashMap<String, Object>();

        params=computeLastParams(begin, end, dateType, params);

        dto.setBegin(params.get("begin").toString());
        dto.setEnd(params.get("end").toString());

        List<ComboSummaryDto> lastComboRank=comboInfoService.getComboRank(dto);

        return new ComboSummaryViewDto(null, null, null, null, begin, end, dateType, null, comboRank,
                null, lastComboRank, deptsInShop, dto.getStoreId());
    }

    /**
     * 门店商品销售按照销量或者销售额排行
    * @author 乐建建
    * @date 2016年1月28日 下午12:13:58
    * @param type 排序类型
    * @param begin 起始时间
    * @param end 截止时间
    * @param storeId 门店id
    * @param dateType 日期区间类型
    * @return  CommoditySalesDto
    */
    public CommoditySalesDto getGoodsRankBySalesOrAmt(Integer type, String begin, String end, Integer storeId, Integer dateType){
        SummaryResultDto dto=new SummaryResultDto();
        dto.setBegin(begin.replaceAll("/", "-"));
        dto.setEnd(end.replaceAll("/", "-"));
        dto.setType(type);
        dto.setStoreId(storeId);
        List<GoodSalesSummaryDto>  ranks=goodsInfoService.getGoodRank(dto);
        List<DeptInfo> deptsInShop=deptInfoMapper.getDeptInfo(dto.getStoreId());
        Map<String, Object> params=new HashMap<String, Object>();
        params=computeLastParams(begin, end, dateType, params);

        dto.setBegin(params.get("begin").toString());
        dto.setEnd(params.get("end").toString());
        List<GoodSalesSummaryDto>  lastRanks=goodsInfoService.getGoodRank(dto);
        return new CommoditySalesDto(null, null, null, null, begin, end, dateType, null, ranks, null, lastRanks, deptsInShop, null, dto.getStoreId());
    }


    /**
    * @author 乐建建
    * @date 2016年1月28日 上午11:29:10
    * @param dateType 日期区间类型
    * @param deptId 部门id
    * @param deptName 部门名称
    * @return List<GoodSalesSummaryDto>
    */
    public List<GoodSalesSummaryDto> getGoodRankByDept(Integer dateType, Integer deptId, String deptName){
        SummaryResultDto dto=new SummaryResultDto();
        Map<String, Object> params=computeParams(null, null, dateType, null);
        String begin=params.get("begin").toString();
        String end=params.get("end").toString();
        dto.setBegin(begin);
        dto.setEnd(end);
        dto.setDeptId(deptId);
        List<GoodSalesSummaryDto>  ranks=goodsInfoService.getGoodRankByDept(dto);
        params=computeLastParams(begin, end, dateType, params);
        dto.setBegin(params.get("begin").toString());
        dto.setEnd(params.get("end").toString());
        List<GoodSalesSummaryDto>  lastRanks=goodsInfoService.getGoodRankByDept(dto);

        Map<String, Integer> nameForRank=goodsInfoService.getNameForRank(lastRanks);

        return goodsInfoService.fillLastRank(nameForRank, deptName, ranks);
    }

    /**
    * @author 乐建建
    * @date 2016年2月26日 上午10:42:56
    * @param storeId 当前门店id
    * @return 当前门店相关信息
    */
    public StoreInfo getStoreInfoById(Integer storeId){
        StoreInfo store=storeInfoMapper.selectByPrimaryKey(storeId);
        return store;
    }


 
    /**
     * 按地区统计门店信息
     * @author gebing
     * @date 2016年3月18日
     * @param cityName 城市名
     * @param orderType 排序类型, 1 总装店数倒序, 2 当月装店数倒序, 3 微信会员数
     * @return 统计结果
     */
    public List<RegionCountRankDto> statStoreRank(String cityName, Integer orderType) {
        if (orderType == null || !",1,2,3,".contains("," + orderType + ",")) {
            orderType = 1;
        }
        final Integer rOrderType = orderType;
        Map<String, RegionCountRankDto> rankMapper = new HashMap<String, RegionCountRankDto>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("cityName", cityName);
        List<RegionCountDto> totalCountDtos = storeInfoMapper.countByRegion(params);
        if (totalCountDtos != null && !totalCountDtos.isEmpty()) {
            for (RegionCountDto regionCountDto : totalCountDtos) {
                String region = "未知";
                String city = regionCountDto.getCity();
                String province = regionCountDto.getProvince();
                if (StringUtils.isBlank(province)) {
                    province = region;
                }
                if (StringUtils.isBlank(city)) {
                    city = region;
                }
                region = province + city;
                RegionCountRankDto regionCountRankDto = rankMapper.get(region);
                if (regionCountRankDto == null) {
                    regionCountRankDto = new RegionCountRankDto();
                }
                regionCountRankDto.setCity(city);
                regionCountRankDto.setProvince(province);
                regionCountRankDto.setCurrMonth(0);
                regionCountRankDto.setTotal(regionCountDto.getCount());
                regionCountRankDto.setWechat(0);
                rankMapper.put(region, regionCountRankDto);
            }
        }
        Date start = DateUtils.truncate(new Date(), Calendar.MONTH);
        Date end = DateUtils.addMonths(start, 1);
        params.put("start", DateFormatUtils.format(start, "yyyy-MM-dd"));
        params.put("end", DateFormatUtils.format(end, "yyyy-MM-dd"));
        List<RegionCountDto> currMonthCountDtos = storeInfoMapper.countByRegion(params);
        if (currMonthCountDtos != null && !currMonthCountDtos.isEmpty()) {
            for (RegionCountDto regionCountDto : currMonthCountDtos) {
                String region = "未知";
                String city = regionCountDto.getCity();
                String province = regionCountDto.getProvince();
                if (StringUtils.isBlank(province)) {
                    province = region;
                }
                if (StringUtils.isBlank(city)) {
                    city = region;
                }
                region = province + city;
                RegionCountRankDto regionCountRankDto = rankMapper.get(region);
                if (regionCountRankDto == null) {
                    regionCountRankDto = new RegionCountRankDto();
                }
                regionCountRankDto.setCity(city);
                regionCountRankDto.setProvince(province);
                regionCountRankDto.setCurrMonth(regionCountDto.getCount());
                if (regionCountRankDto.getTotal() == null) {
                    regionCountRankDto.setTotal(0);
                }
                regionCountRankDto.setWechat(0);
                rankMapper.put(region, regionCountRankDto);
            }
        }
        List<RegionCountDto> wechatCountDtos = memberInfoMapper.statWechatByRegion(params);
        if (wechatCountDtos != null && !wechatCountDtos.isEmpty()) {
            for (RegionCountDto regionCountDto : wechatCountDtos) {
                String region = "未知";
                String city = regionCountDto.getCity();
                String province = regionCountDto.getProvince();
                if (StringUtils.isBlank(province)) {
                    province = region;
                }
                if (StringUtils.isBlank(city)) {
                    city = region;
                }
                region = province + city;
                RegionCountRankDto regionCountRankDto = rankMapper.get(region);
                if (regionCountRankDto == null) {
                    regionCountRankDto = new RegionCountRankDto();
                }
                regionCountRankDto.setCity(city);
                regionCountRankDto.setProvince(province);
                if (regionCountRankDto.getCurrMonth() == null) {
                    regionCountRankDto.setCurrMonth(0);
                }
                if (regionCountRankDto.getTotal() == null) {
                    regionCountRankDto.setTotal(0);
                }
                regionCountRankDto.setWechat(regionCountDto.getCount());
                rankMapper.put(region, regionCountRankDto);
            }
        }
        Collection<RegionCountRankDto> crankDtos = rankMapper.values();
        List<RegionCountRankDto> rankDtos = new ArrayList<RegionCountRankDto>();
        rankDtos.addAll(crankDtos);
        Collections.sort(rankDtos, new Comparator<RegionCountRankDto>() {

            @Override
            public int compare(RegionCountRankDto o1, RegionCountRankDto o2) {
                if (rOrderType == 1) {
                    return o2.getTotal() - o1.getTotal();
                } 
                else if (rOrderType == 2) {
                    return o2.getCurrMonth() - o1.getCurrMonth();
                } 
                else if (rOrderType == 3) {
                    return o2.getWechat() - o1.getWechat();
                } 
                else {
                    return 0;
                }
            }
        });
        return rankDtos;
    }


    /**
     * 根据门店id按剩余使用天数排序(排除申请中的)
     * @author gebing
     * @date 2016年3月18日
     * @param storeIds 门店id
     * @return 排序后的门店账户
     */
    public List<StoreAccount> getOrderedAccountByStoreIds(List<Integer> storeIds) {
        return storeAccountMapper.selectOrderedAccountByStoreIds(storeIds);
    }

    /**
     * 根据门店id和门店名称查询门店
     * @author gebing
     * @date 2016年3月18日
     * @param storeIds 门店id, 结果要按照这个参数的顺序排序
     * @param keyword 门店名称关键字
     * @param page 页码
     * @param pageSize 每页个数
     * @return 门店集合
     */
    public List<StoreInfo> getOrderedStore(List<Integer> storeIds,
            String keyword, Integer page, int pageSize) {
        Map<String, Object> prams = new HashMap<String, Object>();
        prams.put("storeIds", storeIds);
        prams.put("keyword", keyword);
        if (page <= 0) {
            page = 1;
        }
        prams.put("page", page);
        prams.put("pageSize", pageSize);
        List<StoreInfo> list = null;
        if (storeIds != null && storeIds.size() > 0) {
            list = new ArrayList<>(storeIds.size());
            for (int storeId : storeIds) {
                StoreInfo storeInfo = storeInfoMapper.selectBaseInfoByStoreId(storeId);
                list.add(storeInfo);
            }
        }
//        return storeInfoMapper.selectOrderedStore(prams);
        return list;
    }

    /**
     * 根据门店id查询门店的微信会员数
     * @author gebing
     * @date 2016年3月18日
     * @param storeIds 门店id
     * @return 会员数
     */
    public int countWechatByIds(List<Integer> storeIds) {
        return storeInfoMapper.countWechatByIds(storeIds);
    }

    /**
     * 设置特色服务
    * @author 高国藩
    * @date 2016年5月19日 下午5:00:11
    * @param specialService specialService
    * @return               specialService
     */
    public BaseDto storeSettingSpecialAction(SpecialService specialService) {
        if (specialService.getsId()==null){
            specialServiceMapper.insert(specialService);
        }
        else {
            specialServiceMapper.updateByPrimaryKeyWithBLOBs(specialService);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, specialService);
    }

    /**
     * 删除特色服务
    * @author 高国藩
    * @date 2016年5月20日 下午3:24:52
    * @param specialService specialService
    * @return               状态吗
     */
    public BaseDto storeSettingSpecialActionDeleted(SpecialService specialService) {
        specialServiceMapper.updateByPrimaryKeySelective(specialService);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "删除成功");
    }

    /**
     *  管理员设置
     *  
    * @author 骆峰
    * @date 2016年7月25日 上午11:36:49
    * @param storeAccount storeAccount
    * @return ModelAndView
     */
    public ModelAndView userAccount(String storeAccount) {
        ModelAndView view = new ModelAndView(View.Setting.VIEW_ADMINISTRATOR);
        Map<String, Object> mapUser = new HashMap<String, Object>();
        mapUser.put("storeAccount", storeAccount);
        List<UserAccountDto> userAccount = userAccountMapper.selectByAccout(mapUser);
        view.addObject("userAccount", userAccount);
        List<AccountRoleInfo> accountRoleInfo = accountRoleInfoMapper.selectAccountMenuByRoleId(mapUser);
        view.addObject("accountRoleInfo", accountRoleInfo);
        List<StoreInfo> storeInfo = storeInfoMapper.selectByStoreAccount(storeAccount);
        view.addObject("storeInfo", storeInfo);
        return view;
    }

    /**
     *   管理员设置保存
    * @author 骆峰
    * @date 2016年7月25日 下午3:09:37
    * @param storeAccount storeAccount
    * @param userAccount userAccount
    * @param employeeName employeeName
    * @return BaseDto
     */
    
    public BaseDto userAccountSave(String storeAccount, UserAccount userAccount,
            String employeeName) {
        Map<String, Object> mapUser = new HashMap<String, Object>();
        mapUser.put("storeAccount", storeAccount);
        mapUser.put("userName", userAccount.getUserName());
        List<UserAccountDto> userAccounts = userAccountMapper.selectByAccout(mapUser);
        
        if (userAccounts.size() != 0 && userAccount.getUserId() ==null){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "已有该工号，请修改再保存");
        }
        userAccount.setStoreId(userAccount.getStoreId()==0?null:userAccount.getStoreId());
        
        if (userAccount.getUserId() != null){
            EmployeeInfo employeeDto=new EmployeeInfo();
            employeeDto.setStoreId(userAccount.getStoreId());
            employeeDto.setDeptId(0);
            employeeDto.setName(employeeName);
            employeeDto.setHeadImage("pc/defaulf_male.png");
            employeeDto.setIsDeleted(1);
            employeeDto.setEmployeeId(userAccount.getUserId());
            employeeInfoMapper.updateByPrimaryKeySelective(employeeDto);
            
            
            String hash = StringUtil.encryptPwd(userAccount.getUserPwd());
            String userPwd = hash.split(":")[0];
            String passwordSalt = hash.split(":")[1];
            userAccount.setUserPwd(userPwd);
            userAccount.setPwdSalt(passwordSalt);
            userAccountMapper.updateByPrimaryKey(userAccount);
        }
        else {
            EmployeeDto employeeDto=new EmployeeDto();
            employeeDto.setStoreId(userAccount.getStoreId());
            employeeDto.setDeptId(0);
            employeeDto.setName(employeeName);
            employeeDto.setHeadImage("pc/defaulf_male.png");
            employeeDto.setIsDeleted(1);
            employeeInfoMapper.insert(employeeDto);


            userAccount.setUserId(employeeDto.getEmployeeId());
            String hash = StringUtil.encryptPwd(userAccount.getUserPwd());
            String userPwd = hash.split(":")[0];
            String passwordSalt = hash.split(":")[1];
            userAccount.setUserPwd(userPwd);
            userAccount.setPwdSalt(passwordSalt);
            userAccount.setCreateTime(DateUtil.getCurTime());
            userAccount.setStoreAccount(storeAccount);
            userAccountMapper.insert(userAccount);
        }
       
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, userAccount);
    }

    /**
     *  管理员 条件查询
    * @author 骆峰
    * @date 2016年7月25日 下午4:04:29
    * @param codeName codeName
    * @param role role
    * @param storeAccount storeAccount
    * @return BaseDto
     */
    public BaseDto selectUser(String codeName, Integer role, String storeAccount) {
        Map<String, Object> mapUser = new HashMap<String, Object>();
        mapUser.put("storeAccount", storeAccount);
        mapUser.put("role", role);
        mapUser.put("codeName", codeName);
        List<UserAccountDto> userAccount = userAccountMapper.selectByAccout(mapUser);
        if (userAccount.size() == 0) {
            return new BaseDto(-1, "没有查询出该管理员");
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, userAccount);
    }

    
    /**
     * 管理员删除
    * @author 骆峰
    * @date 2016年7月25日 下午4:24:45
    * @param userId userId
    * @param storeAccount storeAccount
    * @return BaseDto
     */
    public BaseDto deleteUser(Integer userId, String storeAccount) {
        UserAccount userInfo = new  UserAccount();
        userInfo.setStatus("1");
        userInfo.setUserId(userId);
        userAccountMapper.updateByPrimaryKey(userInfo);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, userInfo);
    }
    

}
