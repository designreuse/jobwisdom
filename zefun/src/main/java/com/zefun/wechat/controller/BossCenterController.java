package com.zefun.wechat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.web.controller.BaseController;
import com.zefun.wechat.service.BossCenterService;

/**
 * 老板模块中心控制类
* @author 张进军
* @date Mar 1, 2016 2:34:34 PM
 */
@Controller
public class BossCenterController extends BaseController {
    
    /** 老板模块中心服务对象 */
    @Autowired
    private BossCenterService bossCenterService;
    
    
    /**
     * 访问老板模块首页
    * @author 张进军
    * @date Mar 1, 2016 3:15:19 PM
    * @param storeId        门店标识
    * @param businessType   业务类型(1:会员,2:员工)
    * @param request        请求对象
    * @param response       响应对象
    * @return   老板模块首页
     */
    @RequestMapping(value = Url.Boss.VIEW_BOSS_HOME)
    public ModelAndView homeView(@PathVariable String storeId, @PathVariable int businessType,
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(storeId, businessType, request, response);
        if (openId == null) {
            return null;
        }
        int employeeId = getUserIdByOpenId(openId);
        int ownerStoreId = getStoreIdByOpenId(openId);
        return bossCenterService.homeView(ownerStoreId, employeeId);
    }
}
