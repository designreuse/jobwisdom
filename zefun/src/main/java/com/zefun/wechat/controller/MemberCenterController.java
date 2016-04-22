package com.zefun.wechat.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.exception.ServiceException;
import com.zefun.web.controller.BaseController;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.OrderEvaluateDto;
import com.zefun.web.dto.OrderInfoSubmitDto;
import com.zefun.web.entity.ProjectShare;
import com.zefun.wechat.service.MemberCenterService;

/**
 * 会员中心操作控制类
* @author 张进军
* @date Aug 19, 2015 11:01:42 AM 
*/
@Controller
public class MemberCenterController extends BaseController {
    /** 会员中心业务逻辑对象 */
    @Autowired
    private MemberCenterService memberCenterService;
    
    /** 日志记录对象 */
//    private final Logger logger = Logger.getLogger(MemberCenterController.class);
    
//    /**测试会员*/
//    private int memberId = 43062;
//    /**测试门店*/
//    private int storeId = 1005;
    
    
    /**
     * wifi主页
    * @author 张进军
    * @date Jan 27, 2016 4:35:29 PM
    * @param storeId    门店标识
    * @param request    请求对象
    * @param response   响应对象
    * @return   会员/员工/老板/门店信息页
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_WECHAT_WIFI_HOME)
    public ModelAndView wifiHome(@PathVariable int storeId,
            HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(storeId, 3, request, response);
        if (openId == null) {
            return null;
        }
        String businessType = redisService.hget(App.Redis.WECHAT_OPENID_TO_BUSINESS_TYPE_KEY_HASH, openId);
        Integer userId = getUserIdByOpenId(openId);
        Integer ownerStoreId = getStoreIdByOpenId(openId);
        return memberCenterService.wifiHome(storeId, ownerStoreId, businessType, userId);
    }
    
    /**
     * 查看会员主页
    * @author 张进军
    * @date Aug 19, 2015 4:21:25 PM
    * @param storeId    门店标识
    * @param businessType   业务类型(1:会员,2:员工)
    * @param request        请求对象
    * @param response       返回对象
    * @return           会员主页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_HOME, method = RequestMethod.GET)
    public ModelAndView homeView(@PathVariable int storeId, @PathVariable int businessType, 
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(storeId, businessType, request, response);
        if (openId == null) {
            return null;
        }
        int memberId = getUserIdByOpenId(openId);
        int ownerStoreId = getStoreIdByOpenId(openId);
        return memberCenterService.homeView(memberId, ownerStoreId);
    }
    
    
    /**
     * 会员等级信息页面
    * @author 张进军
    * @date Mar 8, 2016 4:04:03 PM
    * @param request        请求对象
    * @param response       返回对象
    * @return   会员等级信息页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_LEVEL_INFO)
    public ModelAndView levelInfoView(HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        int memberId = getUserIdByOpenId(openId);
        return memberCenterService.levelInfoView(memberId);
    }
    
    
    /**
     * 个人资料页面
    * @author 张进军
    * @date Oct 22, 2015 8:31:30 PM
    * @param request        请求对象
    * @param response       返回对象
    * @return   个人资料页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_INFO)
    public ModelAndView infoView(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        int memberId = getUserIdByOpenId(openId);
        int storeId = getStoreId(request);
        setJsapiSignData(storeId, request);
        return memberCenterService.infoView(memberId);
    }
    
    
    /**
     * 修改个人资料
    * @author 张进军
    * @date Oct 23, 2015 10:54:08 AM
    * @param request        请求对象
    * @param response       返回对象
    * @param name       昵称
    * @param headUrl    头像
    * @param sex        性别
    * @param birthday   生日
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    @RequestMapping(value = Url.MemberCenter.ACTION_UPDATE_INFO, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto updateInfoAction(HttpServletRequest request, HttpServletResponse response, 
            String name, String headUrl, String sex,
            @RequestParam(value = "birthday", required = false) String birthday){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        int memberId = getUserIdByOpenId(openId);
        return memberCenterService.updateInfoAction(memberId, name, headUrl, sex, birthday);
    }
    
    
    /**
     * 访问密码设置页面
    * @author 张进军
    * @date Nov 28, 2015 4:06:56 PM
    * @param request        请求对象
    * @param response       返回对象
    * @return   密码设置页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_SET_PASSWORD)
    public ModelAndView setPwdView(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        return new ModelAndView(View.MemberCenter.SET_PWD);
    }
    
    
    /**
     * 设置支付密码
    * @author 张进军
    * @date Nov 28, 2015 4:07:40 PM
    * @param pwd        密码
    * @param request    请求对象
    * @param response   响应对象
    * @return   成功返回码为0
     */
    @RequestMapping(value = Url.MemberCenter.ACTION_SET_PASSWORD, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto setPwdAction(String pwd, HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        int memberId = getUserIdByOpenId(openId);
        return memberCenterService.setPwdAction(memberId, pwd);
    }
    
    
    /**
     * 修改支付密码
    * @author 张进军
    * @date Oct 23, 2015 10:54:08 AM
    * @param request        请求对象
    * @param response       返回对象
    * @param oldPwd     旧密码
    * @param newPwd     新密码
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    @RequestMapping(value = Url.MemberCenter.ACTION_UPDATE_PAYCODE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto updatePaycodeAction(HttpServletRequest request, HttpServletResponse response, 
            String oldPwd, String newPwd){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        int memberId = getUserIdByOpenId(openId);
        return memberCenterService.updatePaycodeAction(memberId, oldPwd, newPwd);
    }
    
    
    /**
     * 查看会员订单列表
    * @author 张进军
    * @date Aug 19, 2015 4:21:25 PM
    * @param request        请求对象
    * @param response       返回对象
    * @return           会员预约列表页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_ORDER_LIST)
    public ModelAndView orderListView(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        int memberId = getUserIdByOpenId(openId);
        return memberCenterService.orderListView(memberId);
    }
    
    
    /**
     * 查看会员订单确认页面
    * @author 张进军
    * @date Aug 19, 2015 4:21:25 PM
    * @param storeId    门店标识
    * @param businessType   业务类型(1:会员,2:员工)
    * @param orderId        订单编号
    * @param request        请求对象
    * @param response       响应对象
    * @return           会员订单确认页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_ORDER_PAY, method = RequestMethod.GET)
    public ModelAndView orderPayView(@PathVariable int storeId, @PathVariable int businessType, 
            Integer orderId, HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(storeId, businessType, request, response);
        if (openId == null) {
            return null;
        }
        return memberCenterService.orderPayView(storeId, orderId);
    }
    
    
    /**
     * 查看订单支付明细信息
    * @author 张进军
    * @date Nov 9, 2015 11:23:08 PM
    * @param orderId        订单编号
    * @param storeId        门店标识
    * @param businessType   业务类型(1:会员,2:员工)
    * @param request        请求对象
    * @param response       响应对象
    * @return   订单支付明细页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_PAYMENT_DETAIL, method = RequestMethod.GET)
    public ModelAndView paymentDetailView(int orderId, @PathVariable int storeId, @PathVariable int businessType, 
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(storeId, businessType, request, response);
        if (openId == null) {
            return null;
        }
        return memberCenterService.paymentDetailView(orderId);
    }
    
    
    /**
     * 支付订单
    * @author 张进军
    * @date Nov 11, 2015 8:23:17 PM
    * @param orderSubmit    订单支付信息
    * @param request    请求对象
    * @param response   响应对象
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     * @throws ServiceException 业务异常
     */
	@RequestMapping(value = Url.MemberCenter.ACTION_ORDER_PAY, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto orderPayAction(@RequestBody OrderInfoSubmitDto orderSubmit, 
            HttpServletRequest request, HttpServletResponse response) throws ServiceException {
	    String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        Integer memberId = getUserIdByOpenId(openId);
        return memberCenterService.orderPayAction(orderSubmit, memberId);
    }
	
	
	/**
     * 根据订单标识查询订单详情
    * @author 张进军
    * @date Nov 7, 2015 4:01:57 PM
    * @param request        请求对象
    * @param response       返回对象
    * @param orderId    订单标识
    * @return   订单评价页面
     */
	@RequestMapping(value = Url.MemberCenter.VIEW_ORDER_EVALUATE)
    public ModelAndView orderEvaluateView(int orderId, HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        return memberCenterService.orderEvaluateView(orderId);
    }
    
	
	/**
     * 根据订单标识查询订单详情
    * @author 张进军
    * @date Nov 7, 2015 4:01:57 PM
    * @param orderEvaluate  评价对象
    * @param request        请求对象
    * @param response       返回对象
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
	@RequestMapping(value = Url.MemberCenter.ACTION_ORDER_EVALUATE, method = RequestMethod.POST)
	@ResponseBody
    public BaseDto orderEvaluateAction(@RequestBody OrderEvaluateDto orderEvaluate,
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
	    int memberId = getUserIdByOpenId(openId);
        return memberCenterService.orderEvaluateAction(orderEvaluate, memberId);
    }
    
    
    /**
     * 查看会员套餐列表
    * @author 张进军
    * @date Aug 19, 2015 4:21:25 PM
    * @param request        请求对象
    * @param response       返回对象
    * @return           会员套餐列表页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_COMBO_LIST)
    public ModelAndView comboListView(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        int memberId = getUserIdByOpenId(openId);
        return memberCenterService.comboListView(memberId);
    }
    
    
    /**
     * 查看会员预约列表
    * @author 张进军
    * @date Aug 19, 2015 4:21:25 PM
    * @param storeId    门店标识
    * @param businessType   业务类型(1:会员,2:员工)
    * @param request        请求对象
    * @param response       返回对象
    * @return           会员预约列表页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_APPOINTMENT_LIST)
    public ModelAndView appointmentListView(@PathVariable int storeId, @PathVariable int businessType, 
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(storeId, businessType, request, response);
        if (openId == null) {
            return null;
        }
        int memberId = getUserIdByOpenId(openId);
        return memberCenterService.appointmentListView(memberId);
    }
    
    
    /**
     * 访问会员注册页面
    * @author 张进军
    * @date Aug 19, 2015 7:08:55 PM
    * @param storeId        注册门店标识
    * @param request        请求对象
    * @param response       返回对象
    * @return   会员注册页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_REGISTER, method = RequestMethod.GET)
    public ModelAndView registerView(@PathVariable int storeId, HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        return memberCenterService.registerView(storeId);
    }
    
    
    /**
     * 获取短信验证码
    * @author 张进军
    * @date Aug 19, 2015 7:08:55 PM
    * @param storeId    注册门店标识
    * @param phone  手机号
    * @param request        请求对象
    * @param response       返回对象
    * @return       验证码
     */
    @RequestMapping(value = Url.MemberCenter.ACTION_GET_VERIFYCODE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto getVerifyCodeAction(int storeId, String phone, HttpServletRequest request, HttpServletResponse response){
    	String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        return memberCenterService.getVerifyCodeAction(storeId, phone);
    }
    
    
    /**
     * 访问会员充值页面
    * @author 张进军
    * @date Aug 19, 2015 7:08:55 PM
    * @param request        请求对象
    * @param response       返回对象
    * @return   会员充值页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_ACCOUNT, method = RequestMethod.GET)
    public String accountView(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(App.System.WECHAT_ZEFUN_STORE_ID, 3, request, response);
        if (openId == null) {
            return null;
        }
        return View.MemberCenter.ACCOUNT;
    }
    
    
    /**
     * 访问完善会员资料页面
    * @author 张进军
    * @date Aug 19, 2015 7:08:55 PM
    * @param request        请求对象
    * @param response       返回对象
    * @return   完善会员信息页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_REGISTER_INFO, method = RequestMethod.GET)
    public ModelAndView registerInfoView(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        setJsapiSignData(getStoreId(request), request);
        String accessToken = getAccessTokenByStore(request);
        return memberCenterService.registerInfoView(accessToken, openId);
    }
    
    
    /**
     * 访问完善会员资料页面
     * @author 张进军
     * @date Aug 19, 2015 7:08:55 PM
     * @param nickname		昵称
     * @param headUrl		头像
     * @param sex			性别
     * @param paycode		支付密码
     * @param birthday      生日（月－日）
     * @param request		请求对象
     * @param response		返回对象
     * @return				成功返回码0；失败返回其他错误码，返回值为提示语	
     */
    @RequestMapping(value = Url.MemberCenter.ACTION_REGISTER_INFO, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto registerInfoAction(String nickname, String headUrl, String sex, 
            @RequestParam(value = "paycode", required = false) String paycode, 
            @RequestParam(value = "birthday", required = false) String birthday,
    		    HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        int userId = getUserIdByOpenId(openId);
        return memberCenterService.registerInfoAction(userId, nickname, headUrl, sex, paycode, birthday);
    }
    
    
    /**
     * 访问分享发型的页面
    * @author 张进军
    * @date Aug 19, 2015 7:08:55 PM
    * @param orderId    订单标识
    * @param request        请求对象
    * @param response       返回对象
    * @return   分享发型页面
     * @throws Exception 加密时抛出的异常
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_SHARE, method = RequestMethod.GET)
    public ModelAndView shareView(int orderId, 
            HttpServletRequest request, HttpServletResponse response) throws Exception{
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        Integer storeId = getStoreId(request);
        Integer memberId = getUserIdByOpenId(openId);
        setJsapiSignData(storeId, request);
        return memberCenterService.shareView(orderId, storeId, memberId);
    }
    
    
    /**
     * 进行分享发型操作
    * @author 张进军
    * @date Aug 19, 2015 7:08:55 PM
    * @param projectShare   分享信息
    * @param request        请求对象
    * @param response       返回对象
    * @return   成功返回获赠积分数量
     */
    @RequestMapping(value = Url.MemberCenter.ACTION_SHARE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto shareAction(ProjectShare projectShare, 
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        return memberCenterService.shareAction(projectShare);
    }

    
    /**
     * 访问分享信息页面
    * @author 张进军
    * @date Aug 19, 2015 7:08:55 PM
    * @param mainStoreId	分享着所属公众号门店标识
    * @param code   		分享者标识
    * @param orderId  		分享订单标识
    * @param request        请求对象
    * @param response       返回对象
    * @return   分享信息页面
     * @throws Exception 	加密时抛出的异常
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_SHARE_INFO, method = RequestMethod.GET)
    public ModelAndView shareInfoView(Integer mainStoreId, String code, Integer orderId,
            HttpServletRequest request, HttpServletResponse response) throws Exception{
    	String openId = getOpenId(mainStoreId, 1, request, response);
        if (openId == null) {
            return null;
        }
        setJsapiSignData(mainStoreId, request);
        Integer memberId = getUserIdByOpenId(openId);
        Integer ownerMemberId = Integer.parseInt(code);
        return memberCenterService.shareInfoView(code, ownerMemberId, memberId, orderId, ownerMemberId.equals(memberId), mainStoreId);
    }
    
    
    /**
     * 会员注册
    * @author 张进军
    * @date Aug 19, 2015 7:13:47 PM
    * @param storeId        注册门店
    * @param phone          手机号
    * @param verifyCode     验证码
    * @param request        请求对象
    * @param response       返回对象
    * @return               成功返回码0，返回值为跳转地址；失败返回其他错误码，返回值为提示语
     */
    @RequestMapping(value = Url.MemberCenter.ACTION_REGISTER, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto registerAction(int storeId, String phone, String verifyCode, HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        int mainStoreId = getStoreId(request);
        String accessToken = getAccessTokenByStore(request);
        return memberCenterService.registerAction(mainStoreId, storeId, phone, verifyCode, openId, accessToken, request, false);
    }
    
    
    /**
     * 会员注销操作
    * @author 张进军
    * @date Dec 12, 2015 12:17:26 AM
    * @param request    请求对象
    * @param response   响应对象
    * @return   成功返回码为0，失败为其他错误码
     */
    @RequestMapping(value = Url.MemberCenter.ACTION_LOGOUT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto logout(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        int storeId = getStoreId(request);
        int memberId = getUserIdByOpenId(openId);
        return memberCenterService.logout(storeId, memberId, openId, request);
    }
    
    
    /**
     * 访问预约页面
    * @author 张进军
    * @date Sep 2, 2015 11:00:28 AM
    * @param storeId        店铺标识
    * @param selectStoreId  所选门店
    * @param businessType   业务类型(1:会员,2:员工)
    * @param request        请求对象
    * @param response       响应对象
    * @return               预约页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_ORDER_APPOINTMENT, method = RequestMethod.GET)
    public ModelAndView orderAppointmentView(@PathVariable int storeId, @PathVariable int businessType, 
            @RequestParam(value = "selectStoreId", required = false) Integer selectStoreId,
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(storeId, businessType, request, response);
        if (openId == null) {
            return null;
        }
        return memberCenterService.orderAppointmentView(storeId, selectStoreId);
    }
    
    
    /**
     * 访问项目详情页面
    * @author 张进军
    * @date Oct 18, 2015 7:21:19 PM
    * @param storeId    微信对应门店标识
    * @param projectId  项目编号
    * @param request        请求对象
    * @param response       响应对象
    * @return   项目详情页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_PROJECT_DETAIL, method = RequestMethod.GET)
    public ModelAndView projectDetailView(int storeId, int projectId, 
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(storeId, 1, request, response);
        if (openId == null) {
            return null;
        }
        Integer memberId = getUserId(request);
        setJsapiSignData(storeId, request);
        return memberCenterService.projectDetailView(projectId, memberId, 1);
    }
    
    
    /**
     * 访问时间预约页面
    * @author 张进军
    * @date Oct 19, 2015 3:43:41 PM
    * @param projectId      项目标识
    * @param projectName    项目名称
    * @param projectStepOrder 服务步骤序号
    * @param shiftMahjongId 服务步骤轮牌标识
    * @param employeeId     员工标识
    * @param employeeName   员工名称
    * @param levelName      员工级别
    * @param request        请求对象
    * @param response       响应对象
    * @return   时间预约页面
     * @throws IOException 页面跳转异常
     * @throws ServletException 页面跳转异常
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_DATE_APPOINTMENT)
    public ModelAndView dateAppointmentView(int projectId, String projectName, int projectStepOrder, int shiftMahjongId,
            int employeeId, String employeeName, String levelName, 
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        return memberCenterService.dateAppointmentView(projectId, projectName, projectStepOrder, shiftMahjongId, 
                employeeId, employeeName, levelName, request, response);
    }
    
    
    /**
     * 预约确认操作
    * @author 张进军
    * @date Oct 19, 2015 3:43:41 PM
    * @param appointDate    预约日期
    * @param appointTime    预约时段
    * @param projectId      项目标识
    * @param projectName    项目名称
    * @param projectStepOrder 服务步骤序号
    * @param shiftMahjongId 服务步骤轮牌标识
    * @param employeeId     员工标识
    * @param request        请求对象
    * @param response       响应对象
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    @RequestMapping(value = Url.MemberCenter.ACTION_ORDER_APPOINTMENT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto orderAppointmentAction(String appointDate, String appointTime, int projectId, String projectName, int projectStepOrder, 
            int shiftMahjongId, int employeeId, HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        int memberId = getUserIdByOpenId(openId);
        int mainStoreId = getStoreId(request);
        return memberCenterService.orderAppointmentAction(memberId, mainStoreId, appointDate, appointTime, projectId, projectName, 
                projectStepOrder, shiftMahjongId, employeeId);
    }
    
    
    /**
     * 取消预约
    * @author 张进军
    * @date Nov 4, 2015 10:49:11 AM
    * @param appointmentId  预约标识
    * @param employeeId     员工标识
    * @param projectName    预约项目    
    * @param appointmentTime    预约时间
    * @param reason         取消原因
    * @param request        请求对象
    * @param response       响应对象
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    @RequestMapping(value = Url.MemberCenter.ACTION_CANCEL_APPOINTMENT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto cancelAppoinmentAction(int appointmentId, int employeeId, 
            String projectName, String appointmentTime, String reason, 
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        int memberId = getUserIdByOpenId(openId);
        int storeId = getStoreId(request);
        return memberCenterService.cancelAppoinmentAction(memberId, storeId, appointmentId, 
                employeeId, projectName, appointmentTime, reason);
    }
    
    
    /**
     * 查看会员卡金流水
    * @author 张进军
    * @date Oct 20, 2015 8:48:15 PM
    * @param request        请求对象
    * @param response       相应对象
    * @return   卡金流水页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_CARD_MONEY_FLOW_LIST)
    public ModelAndView cardmoneyFlowView(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        int memberId = getUserIdByOpenId(openId);
        return memberCenterService.cardmoneyFlowView(memberId);
    }
    
    
    /**
     * 查看会员礼金流水
    * @author 张进军
    * @date Oct 20, 2015 8:48:15 PM
    * @param memberId		会员标识
    * @param request        请求对象
    * @param response       相应对象
    * @return   礼金流水页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_GIFT_MONEY_FLOW_LIST)
    public ModelAndView giftmoneyFlowView(@RequestParam(value = "memberId", required = false) Integer memberId, 
    		    HttpServletRequest request, HttpServletResponse response){
    	if (memberId == null) {
    		String openId = getOpenId(1, request, response);
            if (openId == null) {
                return null;
            }
            memberId = getUserIdByOpenId(openId);
    	}
        return memberCenterService.giftmoneyFlowView(memberId);
    }
    
    
    /**
     * 查看会员积分流水
    * @author 张进军
    * @date Oct 20, 2015 8:48:15 PM
    * @param memberId		会员标识
    * @param request        请求对象
    * @param response       相应对象
    * @return   积分流水页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_INTEGRAL_FLOW_LIST)
    public ModelAndView integralFlowView(@RequestParam(value = "memberId", required = false) Integer memberId, 
    		    HttpServletRequest request, HttpServletResponse response){
    	if (memberId == null) {
    		String openId = getOpenId(1, request, response);
            if (openId == null) {
                return null;
            }
            memberId = getUserIdByOpenId(openId);
    	}
        return memberCenterService.integralFlowView(memberId);
    }
    
    
    /**
     * 查看会员优惠券
    * @author 张进军
    * @date Oct 20, 2015 8:48:15 PM
    * @param memberId		会员标识
    * @param request        请求对象
    * @param response       相应对象
    * @return   会员优惠券页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_MEMBER_COUPON)
    public ModelAndView memberCouponView(@RequestParam(value = "memberId", required = false) Integer memberId, 
    		    HttpServletRequest request, HttpServletResponse response){
    	if (memberId == null) {
    		String openId = getOpenId(1, request, response);
            if (openId == null) {
                return null;
            }
            memberId = getUserIdByOpenId(openId);
    	}
        return memberCenterService.memberCouponView(memberId);
    }
    
    
    /**
     * 查询门店下的优惠券
    * @author 张进军
    * @date Oct 21, 2015 10:00:34 AM
    * @param storeId    门店标识
    * @param businessType   业务类型(1:会员,2:员工)
    * @param request        请求对象
    * @param response       相应对象
    * @return   积分商城页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_SHOP_CENTER)
    public ModelAndView shopCenterView(@PathVariable int storeId, @PathVariable int businessType,
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(storeId, businessType, request, response);
        if (openId == null) {
            return null;
        }
        Integer ownerStoreId = getStoreIdByOpenId(openId);
        return memberCenterService.shopCenterView(storeId, ownerStoreId);
    }

    
    /**
     * 兑换优惠券
    * @author 张进军
    * @date Oct 22, 2015 4:37:34 PM
    * @param request        请求对象
    * @param response       相应对象
    * @param couponId   优惠券标识
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
     */
    @RequestMapping(value = Url.MemberCenter.ACTION_EXCHANGE_COUPON, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto exchangeCouponAction(HttpServletRequest request, HttpServletResponse response, Integer couponId){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        int memberId = getUserIdByOpenId(openId);
        return memberCenterService.exchangeCouponAction(memberId, couponId);
    }
    
    
    /**
     * 查询门店信息
    * @author 张进军
    * @date Oct 21, 2015 10:00:34 AM
    * @param storeId    门店标识
    * @param businessType   业务类型(1:会员,2:员工)
    * @param selectStoreId  所选门店标识
    * @param request        请求对象
    * @param response       相应对象
    * @return   店铺信息页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_STORE_INFO)
    public ModelAndView storeInfoView(@PathVariable int storeId, @PathVariable int businessType, 
            @RequestParam(value = "selectStoreId", required = false) Integer selectStoreId, 
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(storeId, businessType, request, response);
        if (openId == null) {
            return null;
        }
        if (selectStoreId == null) {
            selectStoreId = getStoreIdByOpenId(openId);
        }
//    	storeId = 1005;
//    	selectStoreId = 1005;
        return memberCenterService.storeInfoView(storeId, selectStoreId);
    }
    
    
    /**
     * 店铺展示页面
    * @author 张进军
    * @date Oct 21, 2015 10:00:34 AM
    * @param storeId        门店标识
    * @param type           展示类型(1：门店介绍，2：技术展示，3：特色服务)
    * @param request        请求对象
    * @param response       相应对象
    * @return   店铺展示页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_STORE_SHOW)
    public ModelAndView storeShowView(int storeId, int type, HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        return memberCenterService.storeShowView(storeId, type);
    }
    
    
    /**
     * 查看门店列表
    * @author 张进军
    * @date Nov 19, 2015 2:19:18 PM
    * @param url            跳转地址
    * @param request        请求对象
    * @param response       相应对象
    * @return   门店列表页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_STORE_LIST)
    public ModelAndView storeListView(String url, HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        int storeId = getStoreId(request);
        return memberCenterService.storeListView(storeId, url);
    }
    
    
    /**
     * 新用户领取分享奖励
    * @author 张进军
    * @date Aug 19, 2015 7:13:47 PM
    * @param code		分享者标识
    * @param storeId        注册门店
    * @param phone          手机号
    * @param verifyCode     验证码
    * @param request        请求对象
    * @param response       返回对象
    * @return               成功返回码0，返回值为关注值；失败返回其他错误码，返回值为提示语
     * @throws Exception 	加密异常
     */
    @RequestMapping(value = Url.MemberCenter.ACTION_GET_REWARD, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto getRewardAction(String code, int storeId, String phone, String verifyCode, 
            HttpServletRequest request, HttpServletResponse response) throws Exception{
    	String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        int mainStoreId = getStoreId(request);
        Integer ownerMemberId = Integer.parseInt(code);
        String accessToken = getAccessTokenByStore(request);
    	return memberCenterService.getRewardAction(ownerMemberId, mainStoreId, storeId, phone, verifyCode, openId, accessToken, request);
    }
    
    
    /**
     * 员工信息页面
    * @author 张进军
    * @date Jan 29, 2016 3:09:31 PM
    * @param employeeId 员工标识
    * @param request    请求对象
    * @param response   响应对象
    * @return   员工信息页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_EMPLOYEE_INFO)
    public ModelAndView employeeInfoView(int employeeId, HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(1, request, response);
        if (openId == null) {
            return null;
        }
        return memberCenterService.employeeInfoView(employeeId);
    }
    
    
    /**
     * 员工项目详情页面
    * @author 张进军
    * @date Jan 29, 2016 4:14:24 PM
    * @param storeId        微信对应门店标识
    * @param employeeId     员工标识
    * @param projectId      项目标识
    * @param request        请求对象    
    * @param response       响应对象
    * @return   员工项目详情页面
     */
    @RequestMapping(value = Url.MemberCenter.VIEW_EMPLOYEE_PROJECT)
    public ModelAndView employeeProjectView(int storeId, int employeeId, int projectId,
            HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(storeId, 1, request, response);
        if (openId == null) {
            return null;
        }
        Integer memberId = getUserId(request);
        setJsapiSignData(storeId, request);
        return memberCenterService.employeeProjectView(employeeId, projectId, memberId);
    }
    
    
    /**
     * 页面测试接口
    * @author 张进军
    * @date Aug 19, 2015 11:22:02 AM
    * @param view   页面路径
    * @return       对应页面
     */
    @RequestMapping(value = "/mobile/test")
    public String test(String view) {
        return view;
    }
}
