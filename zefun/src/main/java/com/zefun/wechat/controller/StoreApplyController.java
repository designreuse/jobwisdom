package com.zefun.wechat.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.web.controller.BaseController;
import com.zefun.web.service.StoreInfoService;

/**
 * 门店申请控制器
 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
 * @date 2015年12月4日
 */
@Controller
public class StoreApplyController extends BaseController {

    /** 日志记录对象 */
//    private Logger logger = Logger.getLogger(StoreApplyController.class);

    /**
     * 门店信息查询
     */
    @Autowired
    private StoreInfoService storeInfoService;
    
    /**测试数据*/
//    private String openId = "oVeRPuAYeJBizrexSgVccJxd1tWg";

    /**
     * 入口方法, 根据用户的openid判断有没有门店, 如果有则跳转到他的门店首页, 如果没有, 则跳转到门店申请页, 如果openid下没有门店而code不为空, 则说明该用户是code对应的渠道推荐的用户
     * @author gebing
     * @date 2015年12月4日
     * @param code 推荐人openid
     * @param request 请求
     * @param response 响应
     * @return 返回渠道申请页
     * @throws IOException 跳转异常
     */
    @RequestMapping(value=Url.StoreApply.VIEW_STORE_APPLY, method = RequestMethod.GET)
    public ModelAndView viewStoreApply(String code, HttpServletRequest request, HttpServletResponse response) throws IOException{
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 4, request, response);
        if (openId == null) {
            return null;
        }
        
        if (!StringUtils.isBlank(openId) && storeInfoService.getStoreByOpenId(openId) != null) {
            String contextPath = request.getContextPath();
            if (contextPath.endsWith("/")) {
                contextPath = contextPath.substring(0, contextPath.length() - 1);
            }
            try {
                response.sendRedirect(contextPath + Url.StoreDetail.VIEW_DETAIL_INDEX);
            }
            catch (IOException e) {
                throw e;
            }
            return null;
        }
        ModelAndView mav = new ModelAndView(View.Store.STORE_APPLY);
        mav.addObject("code", code);
        return mav;
    }

}
