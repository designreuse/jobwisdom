package com.zefun.web.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;

import net.sf.json.JSONArray;

/**
 * 权限拦截器
* @author 高国藩
* @date 2015年9月19日 上午11:36:48
 */
public class AuthorityInterceptor implements HandlerInterceptor {

    /**
     * 过滤的url表达式
     */
    public static List<String> allowUrlPatterns;
    
    /**日志*/
    private Logger logger = Logger.getLogger(AuthorityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        // String requestPath = request.getServletPath().toString();
        String requestUrl = request.getRequestURI()
                .replace(request.getContextPath(), "");

        // //过滤非拦截接口
        for (String url : allowUrlPatterns) {
            if (Pattern.matches("^" + url + "$", requestUrl)) {
                return true;
            }
        }

        Object storeAccount = request.getSession()
                .getAttribute(App.Session.STORE_ACCOUNT);
        // 未登陆或者登陆超时
        if (storeAccount == null) {
            writeNoLoginResult(request, response,
                    App.System.ERROR_CODE_SESSION_INVALID);
            return false;
        }

        // //检查权限
        // EmployeeBaseDto employeeInfo = (EmployeeBaseDto) userInfo;
        // String roleId = redisService.hget(App.Redis.PC_USER_ID_ROLE_HASH,
        // employeeInfo.getEmployeeId());
        // if ("100".equals(roleId)){
        // return true;
        // }
        //
        // Set<String> set =
        // redisService.smembers(App.Redis.AUTHORITY_ACCESS_SET_ROLE_PREFIX +
        // roleId);
        // List<String> authorUrl = new ArrayList<String>(set);
        // //如果不拥有该权限
        // if (authorUrl.isEmpty() || !authorUrl.contains(requestPath)){
        // writeNoLoginResult(request, response,
        // App.System.ERROR_CODE_FORBIDDEN);
        // return false;
        // }
        return true;
    }

    /***
     * 未登录时的处理
    * @author 张进军
    * @date Nov 1, 2015 2:22:44 AM
    * @param request    请求对象
    * @param response   响应对象
    * @param code       错误码
    * @throws IOException   异常
     */
    private void writeNoLoginResult(HttpServletRequest request,
            HttpServletResponse response, int code) throws IOException {
        String ajaxHeader = request.getHeader("isAjax");
        if (ajaxHeader != null && "1".equals(ajaxHeader)) {
            response.setStatus(code);
            response.flushBuffer();
            return;
        }
        response.sendRedirect(request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/");
    }

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * 实例化bean,触发该方法,生成默认不拦截url
    * @author 高国藩
    * @date 2016年6月29日 下午5:44:42
     */
    public void initAllowPatterns(){
        allowUrlPatterns = new ArrayList<>();
        allowUrlPatterns.add("/");
        allowUrlPatterns.add("/coreServlet");                  //微信认证
        allowUrlPatterns.add("/user/login/*.*");               //登陆
        allowUrlPatterns.add("/user/logout/*.*");              
        allowUrlPatterns.add("/wechat/*.*");                    //微信api
        allowUrlPatterns.add("/memberCenter/*.*");              //微信会员端
        allowUrlPatterns.add("/app/*.*");                       //移动端
        allowUrlPatterns.add("/uboxMall/*.*");                  //微信商城
        allowUrlPatterns.add("/conference/*.*");                //渠道连接
        allowUrlPatterns.add("/staff/*.*");                     //移动员工
        allowUrlPatterns.add("/boss/*.*");                      //老板端
        allowUrlPatterns.add("/storeinfo/action/addstore");              
        allowUrlPatterns.add("/mobile/*.*");
        allowUrlPatterns.add("/sms/auth/callback");
        allowUrlPatterns.add("/storeapply/*.*");
        allowUrlPatterns.add("/storedetail/*.*");
        allowUrlPatterns.add("/agentapply/*.*");
        allowUrlPatterns.add("/agentdetail/*.*");
        allowUrlPatterns.add("/wechat/common/*.*");
        allowUrlPatterns.add("/salesman/*.*");                  //业务员模块
        allowUrlPatterns.add("/agentFollow/*.*");               //渠道跟踪记录
        logger.info("拦截器生成匹配规则, " + JSONArray.fromObject(allowUrlPatterns).toString());
    }
}
