package com.zefun.wechat.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.AgentInfo;
import com.zefun.web.entity.ConferenceInfo;
import com.zefun.web.entity.EnrollInfo;
import com.zefun.web.service.AgentInfoService;
import com.zefun.wechat.service.ConferenceService;
import com.zefun.wechat.service.WechatCallService;

/**
 * 渠道商会议
* @author 小高
* @date Nov 23, 2015 9:25:48 PM 
*/
@Controller
public class ConferenceController extends BaseController {

    /**
     * 渠道信息操作
     */
    @Autowired
    private AgentInfoService agentInfoService;
    /**
     * 渠道信息操作
     */
    @Autowired
    private ConferenceService conferenceService;
    /**
     * 渠道信息操作
     */
    @Autowired
    private WechatCallService wechatService;
    
    /**
     * 进入我的会议页面
    * @author 高国藩
    * @date 2016年1月7日 下午7:48:44
    * @param request    请求
    * @param response   返回
    * @return           跳转链接
     */
    @RequestMapping(value = Url.Conference.VIEW_CONFERENCE, method = RequestMethod.GET)
    public ModelAndView appointView(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
        if (openId == null) {
            return null;
        }
        return new ModelAndView(View.Conference.CONFERENCE_VIEW);
    }
    
    /**
     * 申请一个会议
    * @author 高国藩
    * @date 2016年1月8日 上午9:58:54
    * @param request           请求
    * @param response          返回
    * @param conferenceInfo    会议数据
    * @return                  申请状态 
     */
    @RequestMapping(value = Url.Conference.SAVE_CONFERENCE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto actionSaveMetting(HttpServletRequest request, HttpServletResponse response, ConferenceInfo conferenceInfo){
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
        if (openId == null) {
            return null;
        }
        AgentInfo agentInfo = agentInfoService.getByOpenId(openId);
        conferenceInfo.setAgentId(agentInfo.getAgentId());
        conferenceInfo.setIsDeleted(0);
        conferenceInfo.setCreateTime(DateUtil.getCurTime());
        return conferenceService.saveMetting(conferenceInfo);
    }
    
    /**
     * 进入会议列表,展示数据
    * @author 高国藩
    * @date 2016年1月8日 上午10:02:33
    * @param request        请求
    * @param response       返回
    * @return               跳转链接
     */
    @RequestMapping(value = Url.Conference.VIEW_CONFERENCE_LIST, method = RequestMethod.GET)
    public ModelAndView conferenceListView(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
        if (openId == null) {
            return null;
        }
        setJsapiSignData(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), request);
        return conferenceService.conferenceListView(openId);
    }
    
    /**
     * 进入会议详情
    * @author 高国藩
    * @date 2016年1月8日 上午10:02:33
    * @param request        请求
    * @param response       返回
    * @param conferenceId   会议信息
    * @param status         会议状态(1:进行中,2:报名中,3:已结束,4:未开始)
    * @return               跳转链接
     * @throws ParseException 
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value = Url.Conference.VIEW_CONFERENCE_INFO, method = RequestMethod.GET)
    public ModelAndView conferenceInfoView(HttpServletRequest request, HttpServletResponse response, 
            Integer conferenceId, Integer status) throws ParseException {
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
        if (openId == null) {
            return null;
        }
        setJsapiSignData(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), request);
        return conferenceService.conferenceInfoView(conferenceId, status);
    }
    
    /**
     * 进入会议修改页面
    * @author 高国藩
    * @date 2016年1月8日 下午3:45:21
    * @param conferenceId   会议标识
    * @param request           请求
    * @param response          返回
    * @return               页面
     */
    @RequestMapping(value = Url.Conference.UPDATE_CONFERENCE_INFO_VIEW, method = RequestMethod.GET)
    public ModelAndView updateConferenceInfoView(Integer conferenceId, 
            HttpServletRequest request, HttpServletResponse response) {
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
        if (openId == null) {
            return null;
        }
        return conferenceService.updateConferenceInfoView(conferenceId);
    }
    
    /**
     * 修改会议数据
    * @author 高国藩
    * @date 2016年1月8日 下午3:45:51
    * @param conferenceInfo      会议数据
    * @param request           请求
    * @param response          返回
    * @return                    修改状态
     */
    @RequestMapping(value = Url.Conference.UPDATE_CONFERENCE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto actionUpdateMetting(ConferenceInfo conferenceInfo, 
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
        if (openId == null) {
            return null;
        }
        return conferenceService.updateMetting(conferenceInfo);
    }
    
    /**
     * 进入分享会议的展示页面,使用占位符传递上级推荐人以及会议id
    * @author 高国藩
    * @date 2016年1月8日 下午3:57:53
    * @param request                        请求,用于微信授权
    * @param response                       跳转,用于微信授权
    * @param fromUser                       上级推荐人
    * @param conferenceId                   会议ID
    * @return                               跳转至分享页面
     * @throws ParseException 
     */
    @RequestMapping(value = Url.Conference.VIEW_SHARE_CONFERENCE, method = RequestMethod.GET)
    public ModelAndView shareConferenceView(HttpServletRequest request, HttpServletResponse response, 
            Integer fromUser, Integer conferenceId) throws ParseException{
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
        if (openId == null) {
            return null;
        }
        setJsapiSignData(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), request);
        conferenceId = 1;
        fromUser = 1;
        return conferenceService.shareConferenceView(conferenceId, fromUser, openId);
    }
    
    /**
     * 参加报名
    * @author 高国藩
    * @date 2016年1月8日 下午7:27:20
    * @param enrollInfo  报名数据
    * @param request           请求
    * @param response          返回
    * @return            状态
     */
    @RequestMapping(value = Url.Conference.JOIN_CONFERENCE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto actionJoinMetting(EnrollInfo enrollInfo, 
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
        if (openId == null) {
            return null;
        }
        return conferenceService.actionJoinMetting(enrollInfo);
    }
    
    /**
     * 报名人数查看
    * @author 高国藩
    * @date 2016年1月9日 下午8:15:46
    * @param conferenceId      会议ID
    * @param request           请求
    * @param response          返回
    * @return                  跳转页面
     */
    @RequestMapping(value = Url.Conference.VIEW_CONFERENCE_REGIST, method = RequestMethod.GET)
    public ModelAndView viewConferenceRegist(Integer conferenceId, 
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
        if (openId == null) {
            return null;
        }
        return conferenceService.viewConferenceRegist(conferenceId);
    }
    
    /**
     * 查看本次会议的收支明细
    * @author 高国藩
    * @date 2016年1月9日 下午8:16:39
    * @param conferenceId      会议ID
    * @param request           请求
    * @param response          返回
    * @return                  跳转页面
     * @throws ParseException 
     */
    @RequestMapping(value = Url.Conference.VIEW_CONFERENCE_DETAILS, method = RequestMethod.GET)
    public ModelAndView viewConferenceDetails(Integer conferenceId, 
            HttpServletRequest request, HttpServletResponse response) throws ParseException{
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
        if (openId == null) {
            return null;
        }
        return conferenceService.viewConferenceDetails(conferenceId);
    }
    
    /**
     * 查看本次会议的到场情况
    * @author 高国藩
    * @date 2016年1月15日 上午11:49:25
    * @param conferenceId     会议ID
    * @param request           请求
    * @param response          返回
    * @return                 跳转页面
     */
    @RequestMapping(value = Url.Conference.VIEW_CONFERENCE_ADDMISSION, method = RequestMethod.GET)
    public ModelAndView viewConferenceAdmission(Integer conferenceId, 
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
        if (openId == null) {
            return null;
        }
        return conferenceService.viewConferenceAdmission(conferenceId);
    }
    
    /**
     * 对人员进行入场签到或者取消签到
    * @author 高国藩
    * @date 2016年1月15日 下午3:36:28
    * @param enrollInfo     人员数据
    * @param request           请求
    * @param response          返回
    * @return               修改状态
     */
    @RequestMapping(value = Url.Conference.ACTION_CONFERENCE_ADDMISSION, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto actionAdmission(EnrollInfo enrollInfo, 
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
        if (openId == null) {
            return null;
        }
        return conferenceService.actionAdmission(enrollInfo);
    }
    
    
    /**
     * 发起微信支付
    * @author 高国藩
    * @date Sep 23, 2015 5:26:18 PM
    * @param goodsName      商品名称
    * @param request        请求对象
    * @param response       响应对象
    * @param personnelId    报名ID标识
    * @param conferenceId   会议ID标识
    * @return               支付请求结果
     */
    @RequestMapping(value = Url.Conference.CONFERENCE_CREATE_PAY, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto wepay(HttpServletRequest request, HttpServletResponse response, String goodsName, Integer personnelId, Integer conferenceId){
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
        if (openId == null) {
            return null;
        }
        
        ConferenceInfo conferenceInfo = conferenceService.getConferenceInfoById(conferenceId);
        Integer totalFee = conferenceInfo.getRegistrationAmount();
        totalFee = 1;
        goodsName = "参会费用";
        String callback = "/" + Url.Conference.WECHAT_CALLBACK_CONFERENCE_PAY.replace("{personnelId}", String.valueOf(personnelId))
        		  .replace("{conferenceId}", String.valueOf(conferenceId));
        return wechatService.wepayForZefun(App.System.WECHAT_ZEFUN_STORE_ID, 5, conferenceId, goodsName, 
                totalFee, openId, callback, request);
    }
    
    /**
     * 报名支付成功微信回调接口
    * @author 高国藩
    * @date 2016年1月11日 下午2:25:19
    * @param data             data
    * @param personnelId      报名生成的主键ID
    * @param conferenceId     会议ID
    * @return                 返回
     */
    @RequestMapping(value = Url.Conference.WECHAT_CALLBACK_CONFERENCE_PAY, method = RequestMethod.POST)
    @ResponseBody
    public String wechatCallBackConferencePay(String data, 
            @PathVariable("personnelId") Integer personnelId, @PathVariable("conferenceId") Integer conferenceId){
        conferenceService.wechatCallBackConferencePay(personnelId, conferenceId);
		return "SUCCESS";
    }
    
    
    /**
     * 微信Js-SDK开发demo
    * @author 高国藩
    * @date 2016年5月6日 下午6:19:24
    * @param request   request
    * @param response  response
    * @return          view
     */
    @RequestMapping(value = "mobile/pay/h5Pay")
    public ModelAndView h5Pay(HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 3, request, response);
        if (openId == null) {
            return null;
        }
        setJsapiSignData(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), request);
        /*Integer conferenceId = 1;
        String goodsName = "iphone 7s";
        Integer totalFee = 1;
        String callback = "/" + Url.Conference.WECHAT_CALLBACK_CONFERENCE_PAY.replace("{personnelId}", String.valueOf(1))
                .replace("{conferenceId}", String.valueOf(2));
        BaseDto baseDto = wechatService.wepayForZefun(new Integer(App.System.WECHAT_ZEFUN_STORE_ID).toString(), 5, conferenceId, goodsName, 
                totalFee, openId, callback, request);
        Map<String, String> payMap =  (Map<String, String>) baseDto.getMsg();
        */
       /* String appId = payMap.get("appId");
        String timeStamp = payMap.get("timeStamp");
        String nonceStr = payMap.get("nonceStr");
        String signType = payMap.get("signType");
        String paySign = payMap.get("paySign");
        String packages = payMap.get("package");
        String outTradeNo = payMap.get("transactionId");*/
        
        ModelAndView view = new ModelAndView("wechat/H5Pay");
       /* view.addObject("appId", appId);
        view.addObject("timeStamp", timeStamp);
        view.addObject("nonceStr", nonceStr);
        view.addObject("signType", signType);
        view.addObject("paySign", paySign);
        view.addObject("packages", packages);
        view.addObject("outTradeNo", outTradeNo);*/
        
        return view;
    }
    
}
