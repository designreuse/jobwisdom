package com.zefun.web.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.wordnik.swagger.annotations.Api;
import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.service.LoginService;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * 登陆类
* @author 高国藩
* @date 2015年9月20日 上午9:39:31
 */
@Api(value = "用户管理", description = "登录管理")
@Controller
public class LoginController extends BaseController {

    /**用户登陆操作*/
    @Autowired
    private LoginService loginService;

    /**
     * 实现登陆¸
    * @author 高国藩
    * @date 2015年9月20日 上午10:04:24
    * @param request 请求
    * @param response 对登陆的状态进行封装
    * @param username 用户
    * @param password 密码
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    @ApiOperation(value = "用户登录管理", notes = "用户登录管理", httpMethod = "POST", produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value={@ApiResponse(code=200, message="用户登陆信息"),
    			 @ApiResponse(code=0, message=""+"(token验证失败)", response=String.class),
    			 @ApiResponse(code=1, message=""+"(系统错误)", response=String.class)})
    @RequestMapping(value = Url.UserLogin.LOGIN, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto userLogin(HttpServletRequest request,
            HttpServletResponse response, String username, String password) {
        return loginService.login(request, response, username, password);
    }

    /**
     * 如果已经登陆,跳转主页
    * @author 高国藩
    * @date 2015年10月26日 下午4:46:57
    * @param request 请求
    * @param response 结果
    * @return 跳转页面
     */
    @ApiOperation(value = "用户登录管理", notes = "用户登录管理", httpMethod = "POST", produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value={@ApiResponse(code=200, message="用户登陆信息"),
    			 @ApiResponse(code=0, message=""+"(token验证失败)", response=String.class),
    			 @ApiResponse(code=1, message=""+"(系统错误)", response=String.class)})
    @RequestMapping(value = Url.UserLogin.INDEX)
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response) {
        // 未登陆或者登陆超时
        if (request.getSession().getAttribute(App.Session.USER_INFO) == null) {
            return new ModelAndView(View.Index.LOGIN);
        } 
        else {
            Integer roleId = (Integer) request.getSession().getAttribute(App.Session.ROLE_ID);
            if (roleId == App.System.SYSTEM_ROLE_STORE_EMPLOYEE){
                return new ModelAndView("redirect:/" + Url.SystemSetting.VIEW_PERSON_SETTING);
            }
            else if (roleId == App.System.SYSTEM_ROLE_STORE_MAIN_OWNER) {
                return new ModelAndView("redirect:/" + Url.Member.VIEW_BASE_MEMBER);
            }
            else {
                return new ModelAndView("redirect:/" + Url.SelfCashier.VIEW_HOME);
            }
        }
    }
    

    /**
     * 用户退出
    * @author 张进军
    * @date Oct 26, 2015 2:28:38 PM
    * @param request    请求对象
    * @param response   响应对象
    * @return   登录页面
     */
    @ApiOperation(value = "用户登录管理", notes = "用户登录管理", httpMethod = "POST", produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value={@ApiResponse(code=200, message="用户登陆信息"),
    			 @ApiResponse(code=0, message=""+"(token验证失败)", response=String.class),
    			 @ApiResponse(code=1, message=""+"(系统错误)", response=String.class)})	
    @RequestMapping(value = Url.UserLogin.LOGOUT)
    public ModelAndView logout(HttpServletRequest request,
            HttpServletResponse response) {
        request.getSession().invalidate();
        return new ModelAndView("redirect:/" + Url.UserLogin.INDEX);
    }
    
}