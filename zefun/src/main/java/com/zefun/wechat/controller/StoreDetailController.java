package com.zefun.wechat.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.controller.BaseController;
import com.zefun.web.entity.AgentInfo;
import com.zefun.web.entity.AgentRecommendRecord;
import com.zefun.web.entity.RechargeSetting;
import com.zefun.web.entity.StoreAccount;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.service.AgentInfoService;
import com.zefun.web.service.AgentRecommendRecordService;
import com.zefun.web.service.RechargeSettingService;
import com.zefun.web.service.StoreInfoService;
import com.zefun.web.service.UserAccountService;

/**
 * 门店信息控制器
 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
 * @date 2015年11月26日
 */
@Controller
public class StoreDetailController extends BaseController {

    /**
     * 门店信息操作
     */
    @Autowired
    private StoreInfoService storeInfoService;

    /**
     * 用户账户操作
     */
    @Autowired
    private UserAccountService userAccountService;

    /**
     * 推荐记录操作
     */
    @Autowired
    private AgentRecommendRecordService agentRecommendRecordService;

    /**
     * 渠道商操作
     */
    @Autowired
    private AgentInfoService agentInfoService;

    /**
     * 充值配置信息操作
     */
    @Autowired
    private RechargeSettingService rechargeSettingService;

//    /**
//     * 微信回调操作
//     */
//    @Autowired
//    private WechatCallService wechatCallService;

    /**测试数据*/
//    private String openId = "oVeRPuAYeJBizrexSgVccJxd1tWg";

    /**
     * 门店首页
     * @author gebing
     * @date 2015年12月4日
     * @param request 请求
     * @param response 响应
     * @return 门店首页
     * @throws IOException  跳转异常
     */
    @RequestMapping(value = Url.StoreDetail.VIEW_DETAIL_INDEX, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 4, request, response);
        if (openId == null) {
            return null;
        }

        StoreInfo storeInfo = storeInfoService.getStoreByOpenId(openId);
        if (storeInfo == null) {
            String contextPath = request.getContextPath();
            if (contextPath.endsWith("/")) {
                contextPath = contextPath.substring(0, contextPath.length() - 1);
            }
            try {
                response.sendRedirect(contextPath + Url.StoreApply.VIEW_STORE_APPLY);
            }
            catch (IOException e) {
                throw e;
            }
            return null;
        }

        //检查是否有缴费
        StoreAccount storeAccount = storeInfoService.getAccountByStoreId(storeInfo.getStoreId());
        if (storeInfo.getStoreType() != 2 && storeAccount.getBalanceDays() <= 0) {
            //TODO 区分是开通还是续费
            return new ModelAndView("redirect:" + Url.StoreDetail.VIEW_DETAIL_OPEN_SYS);
        }

        Integer storeType = storeInfo.getStoreType();
        switch (storeType) {
            case 1: { // 单店
                return singleStore(storeInfo, request, response);
            }
            case 2: { // 连锁总店
                return chainHQStore(storeInfo, request, response);
            }
            case 3: { // 连锁分店
                return chainStore(storeInfo, request, response);
            }
            default: {//
                return null;
            }
        }

    }

    /**
     * 连锁分店
     * @author gebing
     * @date 2015年12月4日
     * @param storeInfo 门店信息
     * @param request   请求对象
     * @param response  响应对象
     * @return 连锁分店首页
     */
    private ModelAndView chainStore(StoreInfo storeInfo, HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 4, request, response);
        if (openId == null) {
            return null;
        }

        StoreAccount storeAccount = storeInfoService
                .getAccountByStoreId(storeInfo.getStoreId());

        StoreInfo hqStoreInfo = storeInfoService.getByStoreId(storeInfo
                .getHqStoreId());
        ModelAndView mav = new ModelAndView(View.StoreDetail.CHAIN_STORE);
        mav.addObject("storeAccount", storeAccount);
        mav.addObject("storeInfo", storeInfo);
        mav.addObject("hqStoreInfo", hqStoreInfo);
        return mav;
    }

    /**
     * 连锁总店
     * @author gebing
     * @date 2015年12月4日
     * @param storeInfo 门店信息
     * @param request   请求对象
     * @param response  响应对象
     * @return 连锁总店首页
     */
    private ModelAndView chainHQStore(StoreInfo storeInfo, HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 4, request, response);
        if (openId == null) {
            return null;
        }

        StoreAccount storeAccount = storeInfoService
                .getAccountByStoreId(storeInfo.getStoreId());
        ModelAndView mav = new ModelAndView(View.StoreDetail.CHAIN_HQ_STORE);
        mav.addObject("storeAccount", storeAccount);
        mav.addObject("storeInfo", storeInfo);
        mav.addObject("chainsCnt",
                storeInfoService.countChainStores(storeInfo.getStoreId()));
        return mav;
    }

    /**
     * 单店
     * @author gebing
     * @date 2015年12月4日
     * @param storeInfo 门店信息
     * @param request   请求对象
     * @param response  响应对象
     * @return 单店首页
     */
    private ModelAndView singleStore(StoreInfo storeInfo, HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 4, request, response);
        if (openId == null) {
            return null;
        }

        StoreAccount storeAccount = storeInfoService
                .getAccountByStoreId(storeInfo.getStoreId());
        ModelAndView mav = new ModelAndView(View.StoreDetail.SINGLE_STORE);
        mav.addObject("storeAccount", storeAccount);
        mav.addObject("storeInfo", storeInfo);
        return mav;
    }

    /**
     * 门店资料
     * @author gebing
     * @date 2015年12月4日
     * @param request 请求
     * @param response 响应
     * @return 门店资料页
     */
    @RequestMapping(value = Url.StoreDetail.VIEW_DETAIL_INFO, method = RequestMethod.GET)
    public ModelAndView info(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 4, request, response);
        if (openId == null) {
            return null;
        }

        StoreInfo storeInfo = storeInfoService.getStoreByOpenId(openId);
        Integer storeId = storeInfo.getStoreId();
        StoreAccount storeAccount = storeInfoService
                .getAccountByStoreId(storeId);

        ModelAndView mav = new ModelAndView();
        mav.addObject("storeInfo", storeInfo);
        mav.addObject("storeAccount", storeAccount);
        Integer storeType = storeInfo.getStoreType();
        String view = "";
        switch (storeType) {
            case 1: { // 单店
                view = View.StoreDetail.SINGLE_STORE_INFO;
                mav.addObject("userAccount",
                        userAccountService.getSingleStoreAccount(storeId));
                break;
            }
            case 2: { // 连锁总店
                mav.addObject("chainsCnt",
                        storeInfoService.countChainStores(storeId));
                mav.addObject("userAccount",
                        userAccountService.getChainHQStoreAccount(storeId));
                view = View.StoreDetail.CHAIN_HQ_STORE_INFO;
                break;
            }
            case 3: { // 连锁分店
                view = View.StoreDetail.CHAIN_STORE_INFO;
                mav.addObject("userAccount",
                        userAccountService.getChainStoreAccount(storeId));
                break;
            }
            default: {
                return null;
            }
        }
        mav.setViewName(view);
        return mav;

    }

    /**
     * 总店下连锁分店列表
     * @author gebing
     * @date 2015年12月4日
     * @param request 请求
     * @param response 响应
     * @return  总店下连锁分店列表页
     */
    @RequestMapping(value = Url.StoreDetail.VIEW_DETAIL_HQ_CHAINS, method = RequestMethod.GET)
    public ModelAndView chains(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 4, request, response);
        if (openId == null) {
            return null;
        }

        StoreInfo storeInfo = storeInfoService.getStoreByOpenId(openId);
        Integer storeId = storeInfo.getStoreId();
        StoreAccount storeAccount = storeInfoService
                .getAccountByStoreId(storeId);

        ModelAndView mav = new ModelAndView();
        mav.addObject("storeInfo", storeInfo);
        mav.addObject("storeAccount", storeAccount);
        mav.addObject("userAccount",
                userAccountService.getChainHQStoreAccount(storeId));
        mav.addObject("chainsCnt", storeInfoService.countChainStores(storeId));
        List<StoreInfo> chains = storeInfoService.getChainsByHQStoreId(storeId);
        if (chains != null && !chains.isEmpty()) {
            List<Integer> storeIds = new ArrayList<Integer>();
            for (StoreInfo chain : chains) {
                storeIds.add(chain.getStoreId());
            }
            List<StoreAccount> chainStoreAccounts = storeInfoService
                    .getAccountByStoreIds(storeIds);
            Map<Integer, StoreAccount> mChainStoreAccounts = new HashMap<Integer, StoreAccount>();
            if (chainStoreAccounts != null && !chainStoreAccounts.isEmpty()) {
                for (StoreAccount account : chainStoreAccounts) {
                    mChainStoreAccounts.put(account.getStoreId(), account);
                }
            }
            mav.addObject("chainStoreAccounts", mChainStoreAccounts);

            Map<Integer, AgentInfo> mRecommendAgents = new HashMap<Integer, AgentInfo>();
            List<AgentRecommendRecord> recommendRecords = agentRecommendRecordService.getByRecommendedIds(storeIds);
            if (recommendRecords != null && !recommendRecords.isEmpty()) {
                for (AgentRecommendRecord recommendRecord : recommendRecords) {
                    AgentInfo agentInfo = agentInfoService
                            .getByAgentId(recommendRecord.getAgentId());
                    if (agentInfo != null) {
                        mRecommendAgents.put(recommendRecord.getRecommendedId(),
                                agentInfo);
                    }
                }
            }
            mav.addObject("recommendAgents", mRecommendAgents);
        }
        else {
            chains = new ArrayList<StoreInfo>();
        }
        mav.addObject("chains", chains);
        mav.setViewName(View.StoreDetail.CHAIN_STORE_CHAINS);
        return mav;

    }


    /**
     * 系统开通页面
    * @author 张进军
    * @date Feb 21, 2016 4:34:12 PM
    * @param request    请求对象
    * @param response   响应对象
    * @return   系统开通页面
     */
    @RequestMapping(value = Url.StoreDetail.VIEW_DETAIL_OPEN_SYS, method = RequestMethod.GET)
    public ModelAndView openSysView(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 4, request, response);
        if (openId == null) {
            return null;
        }

        int sysType = 1;
        StoreInfo storeInfo = storeInfoService.getStoreByOpenId(openId);
        if (storeInfo.getStoreType() == 1) {
            sysType = 1;
        }
        else if (storeInfo.getStoreType() == 3) {
            sysType = 2;
        }
        //总店不需要缴费
        else {
            return chargeInfo(storeInfo.getStoreId(), 1);
        }

        List<RechargeSetting> sysList = rechargeSettingService.getByType(sysType);
        List<RechargeSetting> smsList = rechargeSettingService.getByType(9);
        ModelAndView mav = new ModelAndView();
        mav.setViewName(View.StoreDetail.STORE_OPEN_SYS);
        mav.addObject("sysList", sysList);
        mav.addObject("smsList", smsList);
        mav.addObject("storeId", storeInfo.getStoreId());
        return mav;
    }


    /**
     * 开通系统的支付回调处理
    * @author 张进军
    * @date Feb 22, 2016 1:54:28 PM
    * @param data       微信数据
    * @param storeId    门店标识
    * @param sysType    系统类型
    * @param smsType    短信类型
    * @return   成功返回SUCCESS，失败返回FAIL
     */
    @RequestMapping(value = Url.StoreDetail.ACTION_CALLBACK_SYS_OPEN)
    @ResponseBody
    public String sysOpenCallback(String data, @PathVariable Integer storeId,
            @PathVariable Integer sysType, @PathVariable Integer smsType) {
        StoreInfo storeInfo = storeInfoService.getByStoreId(storeId);
        RechargeSetting sysSetting = rechargeSettingService.getById(sysType);
        RechargeSetting smsSetting = rechargeSettingService.getById(smsType);

        //初始化门店数据
        storeInfoService.initStoreData(storeId, storeInfo.getStoreType(), storeInfo.getStoreLinkname(),
                storeInfo.getStoreLinkphone());

        //增加系统使用时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, sysSetting.getQuantity());
        int days = 0;
        try {
            days = DateUtil.daysBetween(new Date(), calendar.getTime());
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        StoreAccount storeAccount = new StoreAccount();
        storeAccount.setStoreId(storeId);
        storeAccount.setBalanceDays(days);
        storeAccount.setBalanceSms(smsSetting.getQuantity());
        storeAccount.setStoreStatus(2);
        storeAccount.setOpenTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        storeInfoService.storeAccountCharge(storeAccount);

        return "SUCCESS";
    }


    /**
     *  访问系统充值/短信购买页面
    * @author 张进军
    * @date Feb 23, 2016 4:41:28 PM
    * @param businessType   业务类型（1：系统续费，2:短信购买）
    * @param request        请求对象
    * @param response       响应对象
    * @return   系统充值/短信购买页面
     */
    @RequestMapping(value = Url.StoreDetail.VIEW_DETAIL_CHARGE_SYS, method = RequestMethod.GET)
    public ModelAndView chargeSysView(Integer businessType,
            HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 4, request, response);
        if (openId == null) {
            return null;
        }

        ModelAndView mav = new ModelAndView(View.StoreDetail.STORE_CHARGE_SYS);
        List<RechargeSetting> rechargeList = null;
        //短信购买
        int sysType = 9;
        StoreInfo storeInfo = storeInfoService.getStoreByOpenId(openId);
        if (businessType == 1) {
            if (storeInfo.getStoreType() == 1) {
                sysType = 3;
            }
            else if (storeInfo.getStoreType() == 3) {
                sysType = 4;
            }
        }
        rechargeList = rechargeSettingService.getByType(sysType);
        mav.addObject("rechargeList", rechargeList);
        mav.addObject("businessType", businessType);
        mav.addObject("storeId", storeInfo.getStoreId());

        return mav;
    }


    /**
     * 系统续费/短信购买的微信支付回调
    * @author 张进军
    * @date Feb 22, 2016 1:54:28 PM
    * @param data       微信数据
    * @param storeId    门店标识
    * @param businessType    业务类型（1：系统续费，2:短信购买）
    * @param productType    产品类型（同数据库）
    * @return   成功返回SUCCESS，失败返回FAIL
     */
    @RequestMapping(value = Url.StoreDetail.ACTION_CALLBACK_SYS_CHARGE)
    @ResponseBody
    public String sysChargeCallback(String data, @PathVariable Integer storeId,
            @PathVariable Integer businessType, @PathVariable Integer productType) {
        StoreAccount storeAccount = new StoreAccount();
        storeAccount.setStoreId(storeId);

        if (businessType == 1) {
            RechargeSetting sysSetting = rechargeSettingService.getById(productType);

            //增加系统使用时间
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, sysSetting.getQuantity());
            int days = 0;
            try {
                days = DateUtil.daysBetween(new Date(), calendar.getTime());
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            storeAccount.setBalanceDays(days);
        }
        else {
            RechargeSetting smsSetting = rechargeSettingService.getById(productType);
            storeAccount.setBalanceSms(smsSetting.getQuantity());
        }

        storeInfoService.storeAccountCharge(storeAccount);

        return "SUCCESS";
    }


    /**
     * 系统充值信息
    * @author 张进军
    * @date Feb 23, 2016 11:27:56 AM
    * @param storeId    门店标识
    * @param type       操作类型（1:门店开通，2:门店充值，3:短信充值）
    * @return   充值信息
     */
    @RequestMapping(value = Url.StoreDetail.VIEW_DETAIL_CHARGE_INFO)
    public ModelAndView chargeInfo(int storeId, int type) {
        ModelAndView mav = new ModelAndView(View.StoreDetail.STORE_CHARGE_INFO);
        StoreInfo storeInfo = storeInfoService.getByStoreId(storeId);
        mav.addObject("storeInfo", storeInfo);
        mav.addObject("type", type);

        //查找门店负责人账户用户名
        if (type == 1 ||  type == 2) {
            //如果为连锁总店
            int roleId = App.System.SYSTEM_ROLE_STORE_MAIN_OWNER;
            if (storeInfo.getStoreType() == 1) {
                roleId = App.System.SYSTEM_ROLE_STORE_SINGLE_OWNER;
            }
            else if (storeInfo.getStoreType() == 3) {
                roleId = App.System.SYSTEM_ROLE_STORE_BRANCH_OWNER;
            }
            Map<String, Integer> map = new HashMap<>();
            map.put("storeId", storeId);
            map.put("roleId", roleId);
            String userName = storeInfoService.selectUserNameByStoreIdAndRoleId(map);
            mav.addObject("userName", userName);

            String overdueDate = storeInfoService.getStoreOverdueDateByStoreId(storeId);
            mav.addObject("overdueDate", overdueDate);
        }
        StoreAccount storeAccount = storeInfoService.getAccountByStoreId(storeId);
        mav.addObject("storeAccount", storeAccount);

        return mav;
    }

}
