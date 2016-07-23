package com.zefun.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeBaseDto;
import com.zefun.web.entity.CouponInfo;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.StoreAccount;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.entity.StoreSetting;
import com.zefun.web.entity.StoreWechat;
import com.zefun.web.entity.UserAccount;
import com.zefun.web.entity.WechatGroupInfo;
import com.zefun.web.mapper.ComboInfoMapper;
import com.zefun.web.mapper.CouponInfoMapper;
import com.zefun.web.mapper.EmployeeAttendanceMapper;
import com.zefun.web.mapper.EmployeeCommissionMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.EmployeeRewardMapper;
import com.zefun.web.mapper.GiftmoneyDetailMapper;
import com.zefun.web.mapper.GiftmoneyFlowMapper;
import com.zefun.web.mapper.IntegralFlowMapper;
import com.zefun.web.mapper.MemberAccountMapper;
import com.zefun.web.mapper.MemberComboProjectMapper;
import com.zefun.web.mapper.MemberComboRecordMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.MoneyFlowMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.OrderInfoMapper;
import com.zefun.web.mapper.ShiftMahjongEmployeeMapper;
import com.zefun.web.mapper.ShiftMahjongProjectStepMapper;
import com.zefun.web.mapper.StoreAccountMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.mapper.StoreSettingMapper;
import com.zefun.web.mapper.StoreWechatMapper;
import com.zefun.web.mapper.UserAccountMapper;
import com.zefun.web.mapper.WechatGroupInfoMapper;
import com.zefun.web.mapper.WechatMemberMapper;
import com.zefun.wechat.service.WeixinConfigService;
import com.zefun.wechat.service.WeixinMessageService;

import net.sf.json.JSONObject;

/**
 * 系统设置服务类
* @author 张进军
* @date Nov 20, 2015 7:12:13 PM 
*/
@Service
public class SystemSettingService {
    /**员工操作对象*/
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;
    
    /**用户账户操作对象*/
    @Autowired
    private UserAccountMapper userAccountMapper;
    
    /**门店设置操作对象*/
    @Autowired
    private StoreSettingMapper storeSettingMapper;
    
    /**优惠券信息操作对象*/
    @Autowired
    private CouponInfoMapper couponInfoMapper;
    
    /**门店信息操作对象*/
    @Autowired
    private StoreInfoMapper storeInfoMapper;
    
    /**门店微信设置操作对象*/
    @Autowired
    private StoreWechatMapper storeWechatMapper;
    
    /** 门店账户信息操作对象  */
    @Autowired
    private StoreAccountMapper storeAccountMapper;
    
    /** 订单信息操作对象 */
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    
    /** 订单明细操作对象 */
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    
    /** 员工服务步骤记录操作对象 */
    @Autowired
    private ShiftMahjongProjectStepMapper shiftMahjongProjectStepMapper;
    
    /** 员工服务业绩提成操作对象 */
    @Autowired
    private EmployeeCommissionMapper employeeCommissionMapper;
    
    /** 轮牌员工关联操作对象 */
    @Autowired
    private ShiftMahjongEmployeeMapper shiftMahjongEmployeeMapper;
    
    /** 会员礼金明细操作对象 */
    @Autowired
    private GiftmoneyDetailMapper giftmoneyDetailMapper;
    
    /** 会员礼金流水记录操作对象 */
    @Autowired
    private GiftmoneyFlowMapper giftmoneyFlowMapper;
    
    /** 会员积分流水操作对象 */
    @Autowired
    private IntegralFlowMapper integralFlowMapper;
    
    /** 会员账户流水操作对象 */
    @Autowired
    private MoneyFlowMapper moneyFlowMapper;
    
    /** 会员账户操作对象 */
    @Autowired
    private MemberAccountMapper memberAccountMapper;
    
    /** 会员基础信息操作对象 */
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    
    /** 会员等级信息操作对象 */
    @Autowired
    private MemberLevelMapper memberLevelMapper;
    
    /** 会员微信关联操作对象 */
    @Autowired
    private WechatMemberMapper wechatMemberMapper;
    
    /** 疗程基础信息操作对象 */
    @Autowired
    private ComboInfoMapper comboInfoMapper;
    
    /** 会员疗程记录操作对象 */
    @Autowired
    private MemberComboRecordMapper memberComboRecordMapper;
    
    /** 会员疗程明细操作对象 */
    @Autowired
    private MemberComboProjectMapper memberComboProjectMapper;
    
    /** 员工考勤记录操作对象 */
    @Autowired
    private EmployeeAttendanceMapper employeeAttendanceMapper;
    
    /** 员工惩罚记录操作对象 */
    @Autowired
    private EmployeeRewardMapper employeeRewardMapper;
    
    /** 微信分组信息操作对象 */
    @Autowired
    private WechatGroupInfoMapper wechatGroupInfoMapper;
    
    /** redis缓存服务对象 */
    @Autowired
    private RedisService redisService;
    
    /** 微信基础配置服务对象 */
    @Autowired
    private WeixinConfigService weixinConfigService;
    
    /** 微信信息处理服务对象 */
    @Autowired
    private WeixinMessageService weixinMessageService;
    
    /**
     * 个人设置操作
    * @author 张进军
    * @date Nov 20, 2015 7:14:55 PM
    * @param employeeInfo   员工资料
    * @param request        请求对象
    * @return   成功返回码为0，失败为其他返回码
     */
    public BaseDto personSettingAction(EmployeeInfo employeeInfo, HttpServletRequest request){
        employeeInfoMapper.updateByPrimaryKeySelective(employeeInfo);
        EmployeeBaseDto employeeBaseDto = employeeInfoMapper.selectBaseInfoByEmployeeId(employeeInfo.getEmployeeId());
        request.getSession().setAttribute(App.Session.USER_INFO, employeeBaseDto);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 修改账户密码
    * @author 张进军
    * @date Nov 20, 2015 10:05:26 PM
    * @param userId     用户标识
    * @param oldPwd     原密码
    * @param newPwd     新密码
    * @return   成功返回码为0，失败为其他返回码
     */
    public BaseDto updatePwdAction(int userId, String oldPwd, String newPwd){
        UserAccount userAccount = userAccountMapper.selectByPrimaryKey(userId);
        
        //检查用户密码
        /*if (!StringUtil.md5(oldPwd + userAccount.getPwdSalt()).equals(userAccount.getUserPwd())) {
            return new BaseDto(9002, "密码不对，努力回忆下");
        }*/
        
        String hash = StringUtil.encryptPwd(newPwd);
        newPwd = hash.split(":")[0];
        String passwordSalt = hash.split(":")[1];
        userAccount.setUserPwd(newPwd);
        userAccount.setPwdSalt(passwordSalt);
        userAccount.setUpdateTime(DateUtil.getCurTime());
        userAccountMapper.updateByPrimaryKey(userAccount);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 访问门店基础设置页面
    * @author 张进军
    * @date Nov 25, 2015 7:40:34 PM
    * @param storeId    门店标识
    * @return   门店基础设置页面
     */
    public ModelAndView baseSettingView(int storeId){
        StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(storeId);
        List<CouponInfo> couponList = couponInfoMapper.selectCouponListByStoreId(storeId);
        List<StoreInfo> storeInfos = storeInfoMapper.selectByStoreIds(storeInfoMapper.selectStoreIdAll());
        ModelAndView mav = new ModelAndView(View.Setting.BASE_SETTING);
        mav.addObject("storeSetting", storeSetting);
        mav.addObject("couponList", couponList);
        mav.addObject("storeInfos", storeInfos);
        return mav;
    }
    
    
    /**
     * 门店基础数据操作
    * @author 张进军
    * @date Nov 25, 2015 8:49:52 PM
    * @param storeSetting   门店基础数据
    * @return   成功返回码为0，失败返回其他返回码
     */
    public BaseDto baseSettingAction(StoreSetting storeSetting){
    	if (storeSetting.getFirstFollowCoupon() == null){
    		storeSetting.setFirstFollowCoupon("");
    	}
        storeSettingMapper.updateByPrimaryKey(storeSetting);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 我的分店列表页面
    * @author 张进军
    * @date Dec 14, 2015 10:56:29 PM
    * @param storeId    总店标识
    * @return   分店列表页面
     */
    public ModelAndView branchListView(int storeId){
        ModelAndView mav = new ModelAndView(View.Setting.BRANCH_LIST);
        StoreInfo storeInfo = storeInfoMapper.selectBaseInfoByStoreId(storeId);
        mav.addObject("storeInfo", storeInfo);
        List<StoreInfo> storeList = storeInfoMapper.selectBaseInfoByMainId(storeId);
        mav.addObject("storeList", storeList);
        return mav;
    }
    
    
    /**
     * 查看门店微信设置页面
    * @author 张进军
    * @date Dec 25, 2015 1:56:02 PM
    * @param storeAccount	门店标识
    * @return	门店微信设置页面
     */
    public ModelAndView storeWechatView(String storeAccount){
    	ModelAndView mav = new ModelAndView(View.Setting.STORE_WECHAT);
    	StoreWechat storeWechat = storeWechatMapper.selectByPrimaryKey(storeAccount);
    	mav.addObject("storeWechat", storeWechat);
    	return mav;
    }
    
    
    /**
     * 门店微信设置操作
    * @author 张进军
    * @date Dec 25, 2015 2:45:48 PM
    * @param storeWechat	门店微信关联信息
    * @param storeAccount		门店标识
    * @return	成功返回码为0，失败为其他返回码
     */
    @Transactional
    public BaseDto storeWechatAction(StoreWechat storeWechat, String storeAccount){
        Integer memberGroupId = null;
        Integer staffGroupId = null;
        Integer bossGroupId = null;
        Integer noneGroupId = null;
        
    	if (storeWechat.getStoreAccount() != null&&!storeWechat.getStoreAccount().equals("")) {
    		storeWechatMapper.updateByPrimaryKey(storeWechat);
    	}
    	else {
    		storeWechat.setStoreAccount(storeAccount);
    		storeWechatMapper.insert(storeWechat);
    	}
    	
    	redisService.hset(App.Redis.STORE_WECHAT_APP_ID_KEY_HASH, storeAccount, storeWechat.getWechatAppid());
    	redisService.hset(App.Redis.STORE_WECHAT_APP_SECRET_KEY_HASH, storeAccount, storeWechat.getWechatAppsecret());
    	String accessToken = weixinConfigService.getAccessToken(storeWechat.getWechatAppid(), storeWechat.getWechatAppsecret());
        redisService.hset(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeAccount, accessToken);
    	
    	Map<String, Object> map = new HashMap<>();
        map.put("storeAccount", storeAccount);
        map.put("groupType", 1);
        memberGroupId = wechatGroupInfoMapper.selectGroupIdByStoreIdAndGroupType(map);
        
    	if (memberGroupId != null) {
            map.put("groupType", 2);
            staffGroupId = wechatGroupInfoMapper.selectGroupIdByStoreIdAndGroupType(map);
            
            map.put("groupType", 3);
            bossGroupId = wechatGroupInfoMapper.selectGroupIdByStoreIdAndGroupType(map);
            
            map.put("groupType", 4);
            noneGroupId = wechatGroupInfoMapper.selectGroupIdByStoreIdAndGroupType(map);
    	}
    	else {
    	    //首次配置微信功能，进行分组初始化
            memberGroupId = weixinMessageService.createGroup(storeAccount, "会员_我道系统创建");
            WechatGroupInfo membereGroup = new WechatGroupInfo(storeAccount, 1, memberGroupId, "会员_我道系统创建");
            wechatGroupInfoMapper.insert(membereGroup);
            
            staffGroupId = weixinMessageService.createGroup(storeAccount, "员工_我道系统创建");
            WechatGroupInfo staffGroup = new WechatGroupInfo(storeAccount, 2, staffGroupId, "员工_我道系统创建");
            wechatGroupInfoMapper.insert(staffGroup);
            
            bossGroupId = weixinMessageService.createGroup(storeAccount, "老板_我道系统创建");
            WechatGroupInfo bossGroup = new WechatGroupInfo(storeAccount, 3, bossGroupId, "老板_我道系统创建");
            wechatGroupInfoMapper.insert(bossGroup);
            
            noneGroupId = weixinMessageService.createGroup(storeAccount, "未绑定_我道系统创建");
            WechatGroupInfo noneGroup = new WechatGroupInfo(storeAccount, 4, noneGroupId, "未绑定_我道系统创建");
            wechatGroupInfoMapper.insert(noneGroup);
    	}
    	
        //先删除原有菜单
        weixinMessageService.deleteMenu(storeAccount);
        
        //初始化标准菜单
        weixinMessageService.initNormalMenu(storeWechat.getStoreAccount());
        
        //初始化个性菜单
        weixinMessageService.initConditionalMenu(storeAccount, 1, memberGroupId);
        weixinMessageService.initConditionalMenu(storeAccount, 2, staffGroupId);
        weixinMessageService.initConditionalMenu(storeAccount, 3, bossGroupId);
        weixinMessageService.initConditionalMenu(storeAccount, 4, noneGroupId);
    	
    	redisService.hset(App.Redis.WECHAT_TEMPLATE_APPOINTMENT_APPLY_HASH, storeAccount, storeWechat.getTmAppointApply().toString());
    	redisService.hset(App.Redis.WECHAT_TEMPLATE_APPOINTMENT_RESULT_HASH, storeAccount, storeWechat.getTmAppointResult().toString());
    	redisService.hset(App.Redis.WECHAT_TEMPLATE_MEMBER_CHARGE_HASH, storeAccount, storeWechat.getTmChargeResult().toString());
    	redisService.hset(App.Redis.WECHAT_TEMPLATE_PAYMENT_HASH, storeAccount, storeWechat.getTmPaymentInfo().toString());
    	redisService.hset(App.Redis.WECHAT_TEMPLATE_SERVICE_TURN_HASH, storeAccount, storeWechat.getTmServiceTurn().toString());
    	redisService.hset(App.Redis.WECHAT_TEMPLATE_SERVICE_TOPIC_HASH, storeAccount, storeWechat.getTmServiceTopic().toString());
    	redisService.hset(App.Redis.WECHAT_TEMPLATE_APPOINTMENT_REMIND_HASH, storeAccount, storeWechat.getTmAppointRemind().toString());
    	redisService.hset(App.Redis.WECHAT_TEMPLATE_COUPON_OVERDUE_HASH, storeAccount, storeWechat.getTmCouponOverdue().toString());
    	
    	return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 门店系统使用状态查询
    * @author 张进军
    * @date Dec 25, 2015 5:29:48 PM
    * @param storeId	门店标识
    * @return	门店系统使用状态页面
     */
    public ModelAndView storeUsageView(int storeId){
    	ModelAndView mav = new ModelAndView(View.Setting.STORE_USAGE);
    	StoreAccount storeAccount = storeAccountMapper.selectByPrimaryKey(storeId);
    	mav.addObject("storeAccount", storeAccount);
    	return mav;
    }
    
    
    /**
     * 清除测试数据，切换到正式营业状态
    * @author 张进军
    * @date Dec 25, 2015 8:00:51 PM
    * @param storeId	门店标识
    * @return	成功返回码为0，失败为其他返回码
     */
    @Transactional
    public BaseDto cleanDataAction(int storeId){
    	//查询门店所有员工标识
    	boolean hasEmployee = false;
    	List<Integer> employeeIdList = employeeInfoMapper.selectEmployeeIdByStoreId(storeId);
    	if (employeeIdList != null && employeeIdList.size() > 0) {
    		//1.清除服务步骤(根据订单明细标识删除)
        	shiftMahjongProjectStepMapper.deleteByStoreId(storeId);
        	//2.清除业绩提成(根据订单明细标识删除)
        	employeeCommissionMapper.deleteByStoreId(storeId);
        	//3.清除订单
        	orderInfoMapper.deleteByStoreId(storeId);
        	//4.清除订单明细
        	orderDetailMapper.deleteByStoreId(storeId);
        	//5.恢复员工轮牌状态(根据门店轮牌标识删除)
        	shiftMahjongEmployeeMapper.recoverShiftMahjongStatusByStoreId(storeId);
    		//6.清除员工打卡记录
    		employeeAttendanceMapper.deleteByEmployeeIdList(employeeIdList);
        	//7.清除员工奖惩记录
    		employeeRewardMapper.deleteByEmployeeIdList(employeeIdList);
    		hasEmployee = true;
    	}
    	
    	//删除会员数据，先查出门店所有的会员标识
    	boolean hasMember = false;
    	String[] openidArray = null;
    	List<Integer> memberIdList = memberInfoMapper.selectMemberIdByStoreId(storeId);
    	if (memberIdList != null && memberIdList.size() > 0) {
    		//会员礼金明细、礼金流水、账户信息、账户流水，积分流水、疗程记录、疗程记录明细、会员微信关联信息、会员信息
        	//8.清除礼金流水
        	giftmoneyFlowMapper.deleteByMemberIdList(memberIdList);
        	//9.清除礼金账户
        	giftmoneyDetailMapper.deleteByMemberIdList(memberIdList);
        	//10.清除积分流水
        	integralFlowMapper.deleteByMemberIdList(memberIdList);
        	//11.清除账户流水
        	moneyFlowMapper.deleteByMemberIdList(memberIdList);
        	//12.清除储值账户
        	memberAccountMapper.deleteByMemberIdList(memberIdList);
        	//13.清除会员信息
        	memberInfoMapper.deleteByMemberIdList(memberIdList);
        	//14.清除会员疗程记录
        	List<Integer> comboIdList = comboInfoMapper.selectComboIdByStoreId(storeId);
        	if (comboIdList != null && comboIdList.size() > 0) {
        		memberComboRecordMapper.deleteByComboIdList(comboIdList);
        		memberComboProjectMapper.deleteByComboIdList(comboIdList);
        	}
        	
        	//先查出所有关联微信的会员对应的openid
        	List<String> openIdList = wechatMemberMapper.selectOpenIdByMemberIdList(memberIdList);
        	if (openIdList != null && openIdList.size() > 0) {
        		//15.删除会员微信关联信息
            	wechatMemberMapper.deleteByOpenIdList(openIdList);
            	openidArray = openIdList.toArray(new String[openIdList.size()]);
        	}
        	hasMember = true;
    	}
    	//16.清除会员卡(保留默认)
    	memberLevelMapper.deleteByStoreId(storeId);
    	
    	//将门店状态切换至正式营业
        StoreAccount storeAccount = new StoreAccount();
        storeAccount.setStoreId(storeId);
        storeAccount.setStoreStatus(3);
        storeAccountMapper.updateByPrimaryKey(storeAccount);
        
        if (hasMember) {
        	String[] memberArray = new String[memberIdList.size()];
        	for (int i = 0; i < memberIdList.size(); i++) {
        		memberArray[i] = memberIdList.get(i).toString();
			}
        	
        	//清除redis中会员相关信息
        	redisService.hdel(App.Redis.MEMBER_BASE_INFO_KEY_HASH, memberArray);
        	redisService.hdel(App.Redis.WECHAT_MEMBERID_TO_OPENID_KEY_HASH, memberArray);
        	if (openidArray != null) {
        		redisService.hdel(App.Redis.WECHAT_OPENID_TO_USERID_KEY_HASH, openidArray);
                redisService.hdel(App.Redis.WECHAT_OPENID_TO_BUSINESS_TYPE_KEY_HASH, openidArray);
                redisService.hdel(App.Redis.WECHAT_OPENID_TO_STORE_KEY_HASH, openidArray);
        	}
        }
    	
        if (hasEmployee) {
        	//2.清除redis中订单信息
            redisService.del(App.Redis.GET_ORDER_CODE + storeId);
            redisService.del(App.Queue.WAIT_ORDER_EMPLOYEE + storeId);
            //3.清除redis考勤信息
        	String[] employeeArray = new String[employeeIdList.size()];
        	for (int i = 0; i < employeeIdList.size(); i++) {
        		employeeArray[i] = employeeIdList.get(i).toString();
			}
        	redisService.hdel(App.Redis.EMPLOYEE_ATTENDANCE_STATUS_HASH, employeeArray);
        }
        
    	return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }


    /**
     * 访问门店分享设置页面
    * @author 高国藩
    * @date 2016年1月7日 下午3:26:22
    * @param storeId   门店
    * @return          页面
     */
    public ModelAndView viewShare(Integer storeId) {
        ModelAndView view = new ModelAndView(View.Setting.SHARE);
        List<CouponInfo> couponList = couponInfoMapper.selectCouponListByStoreId(storeId);
        StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(storeId);
        if (!StringUtils.isEmpty(storeSetting.getMemberShareReward())){
            view.addObject("shareReward", JSONObject.fromObject(storeSetting.getMemberShareReward()));
        }
        view.addObject("couponInfoDtos", couponList);
        return view;
    }

    /**
     * 保存门店分享设置
    * @author 高国藩
    * @date 2016年1月7日 下午3:26:47
    * @param storeId          门店
    * @param shareReward      设置内容
    * @return                 请求状态
     */
    @Transactional
    public BaseDto actionUpdateShare(Integer storeId, JSONObject shareReward) {
        StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(storeId);
        storeSetting.setMemberShareReward(shareReward.toString());
        storeSettingMapper.updateByPrimaryKey(storeSetting);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "保存成功");
    }
}
