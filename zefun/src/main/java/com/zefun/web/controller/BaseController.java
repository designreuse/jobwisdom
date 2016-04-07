package com.zefun.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.common.consts.App;
import com.zefun.common.consts.App.Session;
import com.zefun.common.consts.Url;
import com.zefun.common.utils.SignUtil;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.UserAccount;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.UserAccountMapper;
import com.zefun.web.service.MemberInfoService;
import com.zefun.web.service.RedisService;

/**
 * 服务基础类
* @author 张进军
* @date Aug 19, 2015 6:15:23 PM
*/
public class BaseController {
    /** redis操作类 */
    @Autowired
    protected RedisService redisService;

    /**会员信息服务操作对象*/
    @Autowired
    private MemberInfoService memberInfoService;

    /**员工资料操作对象*/
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;
    
    /** 用户账户操作对象 */
    @Autowired
    private UserAccountMapper userAccountMapper;
    
    /** 客服账户操作对象 */
/*    @Autowired
    private EmployeeAccountInfoMapper employeeAccountInfoMapper;*/
//    /** 客服账户操作对象 */
//    @Autowired
//    private EmployeeAccountInfoMapper employeeAccountInfoMapper;
    
    /** 日志记录对象 */
    private Logger logger = Logger.getLogger(BaseController.class);

    /**
     * 获取当前用户所在的门店
    * @author 张进军
    * @date Aug 17, 2015 6:49:50 PM
    * @param request    请求对象
    * @return           当前用户所在的门店id
     */
    public Integer getStoreId(HttpServletRequest request){
        try {
            Integer storeId = Integer.parseInt(request.getSession().getAttribute(Session.STORE_ID).toString());
            return storeId;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 根据微信唯一标识查询所属门店标识
    * @author 张进军
    * @date Nov 19, 2015 4:36:24 PM
    * @param openId 微信openid
    * @return   门店标识
     */
    public Integer getStoreIdByOpenId(String openId){
        try {
            return Integer.parseInt(redisService.hget(App.Redis.WECHAT_OPENID_TO_STORE_KEY_HASH, openId));
        }
        catch (Exception e) {
            Integer storeId = 0;
            String uid = redisService.hget(App.Redis.WECHAT_OPENID_TO_USERID_KEY_HASH, openId);
            if (StringUtils.isBlank(uid)) {
                return null;
            }
            
            String businessType = redisService.hget(App.Redis.WECHAT_OPENID_TO_BUSINESS_TYPE_KEY_HASH, openId);
            Integer userId = Integer.parseInt(uid);
            if ("1".equals(businessType)) {
            	MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(userId, false);
            	if (memberInfo != null) {
            		storeId = memberInfo.getStoreId();
            	}
            }
            else if ("2".equals(businessType)) {
            	EmployeeInfo employeeInfo = employeeInfoMapper.selectByPrimaryKey(userId);
            	if (employeeInfo != null) {
            		storeId = employeeInfo.getStoreId();
            	}
            }
            
            if (storeId != null) {
                redisService.hset(App.Redis.WECHAT_OPENID_TO_STORE_KEY_HASH, openId, storeId);
            }
            return storeId;
        }
    }


    /**
     * 获取当前登录用户id
    * @author 张进军
    * @date Oct 14, 2015 10:30:18 AM
    * @param request    请求对象
    * @return   当前用户id
     */
    public Integer getUserId(HttpServletRequest request){
        try {
            return Integer.parseInt(request.getSession().getAttribute(App.Session.USER_ID).toString());
        }
        catch (Exception e) {
            return null;
        }
    }


    /**
     * 根据微信openID从redis中获取对应的userID
    * @author 张进军
    * @date Nov 3, 2015 2:54:39 PM
    * @param openId 微信openID
    * @return   会员/员工ID
     */
    public Integer getUserIdByOpenId(String openId){
        String uid = redisService.hget(App.Redis.WECHAT_OPENID_TO_USERID_KEY_HASH, openId);
        if (StringUtils.isBlank(uid)) {
            return null;
        }
        return Integer.parseInt(uid);
    }


    /**
     * 获取session中的openId
     * @param storeId        门店标识
     * @param businessType   业务类型(1:会员,2:员工)
     * @param request        请求对象
     * @param response       返回对象
     * @return               当前用户的openId
     * @throws IOException   重定向失败时抛出的异常
     */
    public String getOpenId(int storeId, int businessType, HttpServletRequest request, HttpServletResponse response) {
        try {
            //检查是否跨门店访问
            int sid = Integer.parseInt(request.getSession().getAttribute(App.Session.STORE_ID).toString());
            if (sid != storeId) {
                sendRedirect(storeId, businessType, App.Session.WECHAT_OPEN_ID, request, response);
                return null;
            }
            
            String openId = request.getSession().getAttribute(App.Session.WECHAT_OPEN_ID).toString();
            
            //检查业务类型，3为渠道方面功能，直接返回
            if (businessType == 3 || businessType == 4) {
                return openId;
            }
            
            //如果为注册页面，直接跳过
            String url = getRequstUri(request);
            if (url.contains("/action/") 
                    || url.contains(Url.MemberCenter.VIEW_REGISTER.replace("{storeId}", ""))
                    || url.contains(Url.MemberCenter.VIEW_STORE_LIST)
                    || url.contains(Url.Staff.VIEW_REGISTER)
                    || url.contains(Url.MemberCenter.VIEW_REGISTER_INFO)){
                return openId;
            }
            
            //检查是否跨权限访问
            String bt = redisService.hget(App.Redis.WECHAT_OPENID_TO_BUSINESS_TYPE_KEY_HASH, openId);
            
            //会员或者无身份的用户，才能查看分享页面
            if ((StringUtils.isEmpty(bt) || "1".equals(bt)) && url.contains(Url.MemberCenter.VIEW_SHARE_INFO)) {
            	return openId;
            }
            
            if (!(url.contains(Url.MemberCenter.VIEW_STORE_INFO.replace("/{storeId}", "").replace("/{businessType}", ""))
                    || url.contains(Url.MemberCenter.VIEW_ORDER_APPOINTMENT.replace("/{storeId}", "").replace("/{businessType}", ""))
                    || url.contains(Url.MemberCenter.VIEW_PROJECT_DETAIL)
                    || url.contains(Url.MemberCenter.VIEW_EMPLOYEE_INFO)
                    || url.contains(Url.MemberCenter.VIEW_EMPLOYEE_PROJECT)
                    || url.contains(Url.MemberCenter.VIEW_SHOP_CENTER.replace("/{storeId}", "").replace("/{businessType}", ""))
                    || url.contains(Url.UboxMall.VIEW_GOODS_INFO)) 
            		  && (StringUtils.isNotBlank(bt) && !String.valueOf(businessType).equals(bt))) {
                if ("1".equals(bt) && 2 == businessType) {
                	request.setAttribute("tip", "该模块只有内部员工才能访问");
            	}
                else if ("2".equals(bt) && 1 == businessType) {
                	request.setAttribute("tip", "员工不能查看会员的模块");
                }
                request.getRequestDispatcher("/404.jsp").forward(request, response);
                return null;
            }

            String uid = redisService.hget(App.Redis.WECHAT_OPENID_TO_USERID_KEY_HASH, openId);
            if (StringUtils.isBlank(uid)) {
                String redirect = "";
                //如果是操作会员相关api
                if (businessType == 1 && !(url.contains(Url.MemberCenter.VIEW_STORE_INFO.replace("/{storeId}", "").replace("/{businessType}", ""))
                        || url.contains(Url.MemberCenter.VIEW_ORDER_APPOINTMENT.replace("/{storeId}", "").replace("/{businessType}", ""))
                        || url.contains(Url.MemberCenter.VIEW_PROJECT_DETAIL)
                        || url.contains(Url.MemberCenter.VIEW_EMPLOYEE_INFO)
                        || url.contains(Url.MemberCenter.VIEW_EMPLOYEE_PROJECT)
                        || url.contains(Url.MemberCenter.VIEW_SHOP_CENTER.replace("/{storeId}", "").replace("/{businessType}", ""))
                        || url.contains(Url.UboxMall.VIEW_GOODS_INFO))) {
                    redirect = App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_STORE_LIST 
                            + "?url=" + Url.MemberCenter.VIEW_REGISTER.replace("{storeId}", "_storeId_");
                }
                //如果是操作员工相关api
                else if (businessType == 2) {
                    redirect = App.System.SERVER_BASE_URL + Url.Staff.VIEW_REGISTER;
                }
                if (StringUtils.isNotBlank(redirect)) {
                    response.sendRedirect(redirect);
                    return null;
                }
            }
            //存储员工角色信息
            else {
                if (businessType == 2) {
                    int employeeId = Integer.parseInt(uid);
                    UserAccount userAccount = userAccountMapper.selectByPrimaryKey(employeeId);
                    request.getSession().setAttribute(App.Session.ROLE_ID, userAccount.getRoleId());
                } 
                //检查会员是否完善资料
                else if (businessType == 1) {
                	MemberBaseDto memberInfo = memberInfoService.getMemberBaseInfo(Integer.parseInt(uid), false);
                	if (memberInfo != null && StringUtils.isBlank(memberInfo.getName())) {
                		String redirect = App.System.SERVER_BASE_URL + Url.MemberCenter.VIEW_REGISTER_INFO; 
                		response.sendRedirect(redirect);
                        return null;
                	}
                }
                request.getSession().setAttribute(App.Session.USER_ID, uid);
            }
            
            return openId;
        }
        catch (Exception e) {
            sendRedirect(storeId, businessType, App.Session.WECHAT_OPEN_ID, request, response);
        }
        return null;
    }
    
    
    /**
     * 
    * @author 张进军
    * @date Feb 26, 2016 7:36:58 PM
    * @param businessType   业务类型(1:会员,2:员工)
    * @param request    请求对象
    * @param response   响应对象
    * @return   openid
     */
    public String getOpenIdForZefun(Integer businessType,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            return request.getSession().getAttribute(App.Session.WECHAT_PAY_FOR_ZEFUN_OPEN_ID).toString();
        }
        catch (Exception e) {
            sendRedirect(App.System.WECHAT_ZEFUN_STORE_ID, businessType, App.Session.WECHAT_PAY_FOR_ZEFUN_OPEN_ID, request, response);
        }
        return null;
    }
    
    
    /**
     * 
    * @author 张进军
    * @date Feb 26, 2016 7:36:58 PM
    * @param businessType   业务类型(1:会员,2:员工)
    * @param request    请求对象
    * @param response   响应对象
    * @return   openid
     */
    public String getOpenIdForYoumei(Integer businessType,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            return request.getSession().getAttribute(App.Session.WECHAT_PAY_FOR_YOUMEI_OPEN_ID).toString();
        }
        catch (Exception e) {
            sendRedirect(App.System.WECHAT_YOUMEI_STORE_ID, businessType, App.Session.WECHAT_PAY_FOR_YOUMEI_OPEN_ID, request, response);
        }
        return null;
    }


    /**
     * 获取session中的openId
    * @author 张进军
    * @date Aug 27, 2015 11:11:30 AM
    * @param businessType   业务类型(1:会员,2:员工,3:固定门店ID)
    * @param request    请求对象
    * @param response   响应对象
    * @return           当前用户的openId
     */
    public String getOpenId(int businessType, HttpServletRequest request, HttpServletResponse response) {
        try {
            int storeId = Integer.parseInt(request.getSession().getAttribute(App.Session.STORE_ID).toString());
            return getOpenId(storeId, businessType, request, response);
        }
        catch (Exception e) {
            request.setAttribute("tip", "你在此页面停留时间太长啦，关闭重试一下吧！");
            try {
                request.getRequestDispatcher("/404.jsp").forward(request, response);
            }
            catch (ServletException | IOException e1) {
            }
        }
        return null;
    }
    
    
    /**
     * 判断是否是ajax请求
    * @author 张进军
    * @date Aug 17, 2015 4:35:42 PM
    * @param request       请求对象
    * @return              返回判断结果
     */
    public boolean isAjax(HttpServletRequest request) {
        String ajaxHeader = request.getHeader("isAjax");
        return ajaxHeader != null && "1".equals(ajaxHeader);
    }


    /**
     * 发起微信授权请求
    * @author 张进军
    * @date Aug 17, 2015 4:35:42 PM
    * @param storeId        门店标识
    * @param businessType   业务类型(1:会员,2:员工)
    * @param openidKey      存储openid的session key
    * @param request        请求对象
    * @param response       返回对象
    * @throws IOException   重定向失败时抛出的异常
     */
    public void sendRedirect(int storeId, int businessType, String openidKey, 
            HttpServletRequest request, HttpServletResponse response) {
        String redirectUrl = getRequstUri(request);
        redirectUrl = redirectUrl.replace("&", "__");
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
            String appId = getAppIdByStore(storeId);
            HttpSession session = request.getSession();
            session.setAttribute(App.Session.STORE_ID, storeId);
            session.setAttribute(App.Session.WECHAT_BUSINESS_TYPE, businessType);
            String authUrl = App.Wechat.AUTH_REDIRECT_BASE_URL.replace("{app_id}", appId)
                    .replace("{redirect_uri}", redirectUrl)
                    .replace("{openid_key}", openidKey);
            if (isAjax(request)) {
                response.setStatus(App.System.ERROR_CODE_FORBIDDEN);
                PrintWriter pw = response.getWriter();
                pw.write(authUrl);
                pw.flush();
                pw.close();
            } 
            else {
                response.sendRedirect(authUrl);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 为当前地址进行微信js api授权
    * @author 张进军
    * @date Aug 17, 2015 4:44:46 PM
    * @param storeId    门店标识
    * @param request    请求对象
     */
    public void setJsapiSignData(Integer storeId, HttpServletRequest request) {
    	if (storeId == null) {
    		return;
    	}
        String url = getRequstUri(request);
        String appId = getAppIdByStore(storeId);
        String jsapiTicket = getJsapiTicketByStore(storeId);
        Map<String, String> sign = SignUtil.jsSign(jsapiTicket, url, appId);
        logger.info("setJsapiSignData --> url : " + url + ", sign : " + sign);
        for (String key : sign.keySet()) {
            request.setAttribute(key, sign.get(key));
        }
    }


    /**
     * 为当前地址进行微信地址api授权
    * @author 张进军
    * @date Aug 17, 2015 4:44:46 PM
    * @param storeId    门店标识
    * @param request    请求对象
     */
    public void setAddriSignData(int storeId, HttpServletRequest request) {
        String url = getRequstUri(request);
        String appId = getAppIdByStore(storeId);
        String accessToken = getAccessTokenByStore(request);
        Map<String, String> sign = SignUtil.addrSign(accessToken, url, appId);
        logger.info("setAddriSignData --> url : " + url + ", sign : " + sign);
        for (String key : sign.keySet()) {
            request.setAttribute(key, sign.get(key));
        }
    }


    /**
     * 获取门店标识的微信应用id
    * @author 张进军
    * @date Aug 17, 2015 4:39:06 PM
    * @param storeId    门店标识
    * @return           对应门店的微信应用id
     */
    public String getAppIdByStore(int storeId){
        return redisService.hget(App.Redis.STORE_WECHAT_APP_ID_KEY_HASH, storeId);
    }


    /**
     * 获取门店标识的微信密钥
    * @author 张进军
    * @date Aug 17, 2015 4:39:06 PM
    * @param storeId    门店标识
    * @return           对应门店的微信密钥
     */
    public String getAppSecretByStore(int storeId){
        return redisService.hget(App.Redis.STORE_WECHAT_APP_SECRET_KEY_HASH, storeId);
    }


    /**
     * 获取当前门店的微信访问口令
    * @author 张进军
    * @date Aug 17, 2015 4:39:06 PM
    * @param request    请求对象
    * @return           当前门店的微信访问口令
     */
    public String getAccessTokenByStore(HttpServletRequest request){
        return redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, getStoreId(request).toString());
    }


    /**
     * 获取当前门店的微信JS接口的临时票据
    * @author 张进军
    * @date Aug 17, 2015 4:39:06 PM
    * @param storeId    门店标识
    * @return           当前门店的微信JS接口的临时票据
     */
    public String getJsapiTicketByStore(Integer storeId){
        return redisService.hget(App.Redis.STORE_WECHAT_JSAPI_TICKET_KEY_HASH, storeId);
    }


    /**
     * 获取请求地址的绝对路径
    * @author 张进军
    * @date Aug 17, 2015 3:46:05 PM
    * @param request    请求对象
    * @return           当前请求的绝对路径
     */
    private String getRequstUri(HttpServletRequest request) {
        String url = App.System.SERVER_BASE_URL + request.getRequestURI().replace(request.getContextPath(), "");
        String params = request.getQueryString();
        if (!StringUtils.isEmpty(params)) {
            url += "?" + params;
        }
        return url;
    }
    
/**
     * 在运行前都加入客服信息
    * @author 高国藩
    * @date 2016年3月11日 下午4:51:44
    * @param model    model
    * @param request  request
     */
    /*
    @ModelAttribute  
    public void populateModel(Model model, HttpServletRequest request) {
//        List<EmployeeAccountInfo> infos = employeeAccountInfoMapper.selectAll();
        List<EmployeeAccountInfo> infos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            EmployeeAccountInfo accountInfo = new EmployeeAccountInfo();
            accountInfo.setEmployId(i);
            accountInfo.setEmployAccount("客服 "+i+"号");
            infos.add(accountInfo);
        }
        model.addAttribute("kfInfos", infos);
    }  */
}
