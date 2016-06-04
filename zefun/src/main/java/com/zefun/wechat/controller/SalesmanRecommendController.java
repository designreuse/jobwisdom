package com.zefun.wechat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.web.controller.BaseController;
import com.zefun.web.entity.AgentFollow;
import com.zefun.web.entity.SalesmanInfo;
import com.zefun.web.entity.SalesmanRecommendRecord;
import com.zefun.web.entity.StoreAccount;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.mapper.AgentFollowMapper;
import com.zefun.web.mapper.SalesmanInfoMapper;
import com.zefun.web.mapper.SalesmanRecommendRecordMapper;
import com.zefun.web.service.StoreInfoService;

/**
 * 业务员推荐门户关系控制器
 * @author lzc
 *
 */
@Controller
public class SalesmanRecommendController extends BaseController {
	
	/** 业务员门店关联映射 */
	@Autowired
	private SalesmanRecommendRecordMapper salesmanRecommendRecordMapper;
	
	/** 业务员信息映射 */
	@Autowired
	private SalesmanInfoMapper salesmanInfoMapper;
	
	/** 门店信息操作服务 */
    @Autowired
    private StoreInfoService storeInfoService;
    
    /** 渠道跟踪映射 */
    @Autowired
    private AgentFollowMapper agentFollowMapper;
	
    
	/**
	 * 渠道商查看某个业务员下(试运营, 正常运营)客户集 / 业务员查看自己推荐(试运营, 正常运营)客户集
	 * @param request  请求
	 * @param response  响应
	 * @param salesmanId  业务员id
	 * @return  (试运营, 正常运营)客户页面和相关数据
	 */
	@RequestMapping(value = Url.Salesman.VIEW_SALESMAN_STORE_NORMAL, method = RequestMethod.GET)
    public ModelAndView storeNormal(HttpServletRequest request, HttpServletResponse response, int salesmanId) {
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
        if (openId == null) {
            return null;
        }
        return salesmanStoreList(request, response, 1, salesmanId); // 正常使用
    }
	
	/**
	 * 渠道商查看某个业务员下(续费提醒)客户集 / 业务员查看自己推荐(续费提醒)客户集
	 * @param request  请求
	 * @param response  响应
	 * @param salesmanId  业务员id
	 * @return  (续费提醒)客户页面和相关数据
	 */
	@RequestMapping(value = Url.Salesman.VIEW_SALESMAN_STORE_RENEW, method = RequestMethod.GET)
    public ModelAndView salesmanRecommendStoreRenew(HttpServletRequest request, HttpServletResponse response, int salesmanId) {
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
        if (openId == null) {
            return null;
        }
        return salesmanStoreList(request, response, 2, salesmanId); // 续费提醒
    }
	
	/**
	 * 渠道商查看某个业务员下(已过期)客户集 / 业务员查看自己推荐(已过期)客户集
	 * @param request  请求
	 * @param response  响应
	 * @param salesmanId  业务员id
	 * @return  (已过期)客户页面和相关数据
	 */
	@RequestMapping(value = Url.Salesman.VIEW_SALESMAN_STORE_OVER, method = RequestMethod.GET)
    public ModelAndView salesmanRecommendStoreOver(HttpServletRequest request, HttpServletResponse response, int salesmanId) {
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
        if (openId == null) {
            return null;
        }
        return salesmanStoreList(request, response, 3, salesmanId); // 已过期
    }
	
	
	/**
	 * 渠道商查看某个业务员下客户集 / 业务员查看自己推荐客户集
     * (根据type返回对应的归于当前业务员推荐的门店的页面)
     * @param request 请求
     * @param response 响应
     * @param type 1为正常使用, 2为需要学费, 3为过期
     * @param salesmanId 业务员id
     * @return type对应的业务员客户页面
     */
    private ModelAndView salesmanStoreList(HttpServletRequest request, HttpServletResponse response, int type, int salesmanId) {
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
        if (openId == null) {
            return null;
        }
        String view = null;
        switch (type) {
	        case 1:  // 试运营, 正常运营
	        	view = View.Salesman.VIEW_SALESMAN_STORE_NORMAL;
	        	break;
	        case 2:  // 续费提醒
	        	view = View.Salesman.VIEW_SALESMAN_STORE_RENEW;
	        	break;
	        case 3:  // 已过期
	        	view = View.Salesman.VIEW_SALESMAN_STORE_OVER;
	        	break;
        	default:
        		break;
        }
        ModelAndView mav = new ModelAndView(view);
        SalesmanInfo salesmanInfo = salesmanInfoMapper.selectByPrimaryKey(salesmanId);
        //该业务员推荐客户关系集
        List<SalesmanRecommendRecord> salesmanRecommendRecordList 
        		  = salesmanRecommendRecordMapper.selectSalesmanRecommendRecordBySalesmanId(salesmanId);
        List<Integer> storeIds = new ArrayList<Integer>();
        List<StoreInfo> storeInfos = new ArrayList<StoreInfo>();
        List<StoreAccount> storeAccounts = new ArrayList<StoreAccount>();
        //渠道跟踪记录集合预定义
        List<AgentFollow> agentFollowList = new ArrayList<AgentFollow>();
        if (salesmanRecommendRecordList != null && !salesmanRecommendRecordList.isEmpty()) {
            for (SalesmanRecommendRecord srr : salesmanRecommendRecordList) {
                Integer storeId = srr.getRecommendedId();
                storeIds.add(storeId);
            }
            switch (type) {
                case 1:
                    storeAccounts = storeInfoService.getNormalAccountByStoreIds(storeIds);
                    break;
                case 2:
                    storeAccounts = storeInfoService.getRenewAccountByStoreIds(storeIds);
                    break;
                case 3:
                    storeAccounts = storeInfoService.getOverAccountByStoreIds(storeIds);
                    break;
                default:
                    break;
            }
            //storeIds重新赋值查询storeInfos
            if (storeAccounts != null && storeAccounts.size() != 0) {
            	storeIds = new ArrayList<Integer>();
                for (int i=0; i<storeAccounts.size(); i++) {
                	storeIds.add(storeAccounts.get(i).getStoreId());
                }
                storeInfos = storeInfoService.getStoreByStoreIds(storeIds);
                //查询店铺的推荐人(业务员)
                for (int i=0; i<storeInfos.size(); i++) {
                	storeInfos.get(i).setRecommendName(salesmanInfoMapper.selectSalesmanNameByStoreId(storeInfos.get(i).getStoreId()));
                }
                //根据上面店铺id集(storeIds)查询渠道跟踪记录
                agentFollowList = agentFollowMapper.selectAgentFollowByStoreIdList(storeIds);
            }
        }
        mav.addObject("salesmanInfo", salesmanInfo);
        mav.addObject("storeInfos", storeInfos);
        mav.addObject("storeAccounts", storeAccounts);
        mav.addObject("agentFollowList", agentFollowList);
        return mav;
    }

}
