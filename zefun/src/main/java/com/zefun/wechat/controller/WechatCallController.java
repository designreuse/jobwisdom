package com.zefun.wechat.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.web.controller.BaseController;
import com.zefun.web.dto.BaseDto;
import com.zefun.wechat.service.WechatCallService;

/**
 * 微信相关api操作控制类
* @author 张进军
* @date Aug 13, 2015 2:29:50 PM 
*/
@Controller
public class WechatCallController extends BaseController {
    
    /** 微信相关api操作业务逻辑类 */
    @Autowired
    private WechatCallService wechatService;
    /** 日志处理 */
    private Logger logger = Logger.getLogger(WechatCallController.class);
    
    
    /**
     *  微信授权回调处理
    * @author 张进军
    * @date Aug 17, 2015 3:54:23 PM
    * @param redirect       重定向地址
    * @param code           微信返回，用于获取授权的access token
    * @param state          随机字符，用作校验
    * @param scope          应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
    *                       snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。
    *                           并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
    * @param openidKey      存储openid的session key                          
    * @param request        请求对象
    * @param response       返回对象
     * @throws IOException  重定向失败时抛出的异常 
     * @throws ServletException 
     */
    @RequestMapping(value = Url.Wechat.CALL_BACK)
    public void callback(String redirect, String code, String state, String scope, @PathVariable String openidKey,
            HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer storeId = getStoreId(request);
        if (storeId == null) {
            return;
        }
        String s = request.getSession().getAttribute(App.Session.WECHAT_BUSINESS_TYPE).toString();
        if (s == null) {
            return;
        }
        int businessType = Integer.parseInt(s);
        String appId = getAppIdByStore(storeId);
        String appSecret = getAppSecretByStore(storeId);
        wechatService.callback(redirect, code, state, scope, openidKey, storeId, businessType, appId, appSecret, request, response);
    }
    
    
    /**
     * 微信支付回调处理
    * @author 张进军
    * @date Sep 23, 2015 5:23:58 PM
    * @param data   支付结果
    * @return       业务处理成功返回SUCCESS
     */
    @RequestMapping(value = Url.Wechat.CALL_BACK_PAY)
    @ResponseBody
    public String payCallback(String data){
        return wechatService.payCallback(data);
    }
    
    
    /**
     * 上传微信media到七牛
    * @author 张进军
    * @date Aug 22, 2015 11:53:15 AM
    * @param mediaid    微信的资源id
    * @param key        七牛目标地址
    * @param request    请求对象
    * @return           成功返回码0,失败返回其他错误码
     */
    @RequestMapping(value = Url.Wechat.FETCH_MEDIA, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto uploadMediaToQiniu(String mediaid, String key, HttpServletRequest request){
        String accessToken = getAccessTokenByStore(request);
        return wechatService.uploadMediaToQiniu(mediaid, key, accessToken);
    }
}
