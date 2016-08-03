package com.zefun.wechat.service;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zefun.wechat.utils.App;
import com.zefun.wechat.utils.HttpClientUtil;


/**
* @author 张进军
* @date Aug 25, 2015 11:51:52 AM 
*/
@Service
public class TemplateNoticeService {
    @Autowired
    private RedisService redisService;
    
    private Logger logger = Logger.getLogger(TemplateNoticeService.class);
    
    public boolean sendServiceTurn(String title, String remark, String storeId, String url, String openId, String serviceNmae, String turnType){
        String templateId = redisService.hget(App.Redis.WECHAT_TEMPLATE_SERVICE_TURN_HASH, storeId);
        if (StringUtils.isBlank(templateId)) {
            return true;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("touser", openId);
        params.put("template_id", templateId);
        params.put("url", url);
        params.put("topcolor", "#FF0000");
        Map<String, Map<String, String>> data = new HashMap<String, Map<String,String>>();

        //标题
        Map<String, String> keyword0 = new HashMap<String, String>();
        keyword0.put("value", title + "\r\n");
        keyword0.put("color", "#173177");
        data.put("first", keyword0);
        
        Map<String, String> keyword1 = new HashMap<String, String>();
        keyword1.put("value", serviceNmae);
        keyword1.put("color", "#173177");
        data.put("keyword1", keyword1);

        Map<String, String> keyword2 = new HashMap<String, String>();
        keyword2.put("value", turnType);
        keyword2.put("color", "#173177");
        data.put("keyword2", keyword2);

        //备注
        Map<String, String> keyword6 = new HashMap<String, String>();
        keyword6.put("value", remark);
        keyword6.put("color", "#173177");
        data.put("remark", keyword6);

        params.put("data", data);
        
        return sendTmpl(getTemplSendUrl(storeId), JSONObject.fromObject(params).toString());
    }
    
    
    /**
     * 会员预约申请通知,发送给员工
    * @author 张进军
    * @date Nov 4, 2015 2:33:09 PM
    * @param storeId        门店标识
    * @param url            跳转链接
    * @param openId         接受者id
    * @param memberName     会员名称
    * @param memberLevel    会员等级
    * @param projectName    服务项目    
    * @param createTime     下单时间
    * @param appointTime    预约时间
    * @return   成功返回true，失败返回false
     */
    public boolean sendAppointmentApply(String storeId, String url, String openId, String memberName, String memberLevel, String projectName, String createTime, String appointTime){
        String templateId = redisService.hget(App.Redis.WECHAT_TEMPLATE_APPOINTMENT_APPLY_HASH, storeId);
        if (StringUtils.isBlank(templateId)) {
            return true;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("touser", openId);
        params.put("template_id", templateId);
        params.put("url", url);
        params.put("topcolor", "#FF0000");
        Map<String, Map<String, String>> data = new HashMap<String, Map<String,String>>();

        //标题
        Map<String, String> keyword0 = new HashMap<String, String>();
        keyword0.put("value", "您有新的预约\r\n");
        keyword0.put("color", "#173177");
        data.put("first", keyword0);
        
        Map<String, String> keyword1 = new HashMap<String, String>();
        keyword1.put("value", "\r\n预约顾客：" + memberName + "\r\n顾客等级：" + memberLevel + "\r\n预约项目：" + projectName + "\r\n预约时间：" + appointTime);
        keyword1.put("color", "#173177");
        data.put("keyword1", keyword1);

        Map<String, String> keyword2 = new HashMap<String, String>();
        keyword2.put("value", createTime);
        keyword2.put("color", "#173177");
        data.put("keyword2", keyword2);

        //备注
        Map<String, String> keyword6 = new HashMap<String, String>();
        keyword6.put("value", "\r\n您可以在\"我的-我的预约\"中随时查看预约情况");
        keyword6.put("color", "#173177");
        data.put("remark", keyword6);

        params.put("data", data);
        
        return sendTmpl(getTemplSendUrl(storeId), JSONObject.fromObject(params).toString());
    }
    
    
    /**
     * 预约操作结果通知，包括员工同意/拒绝、会员取消
    * @author 张进军
    * @date Nov 4, 2015 2:33:09 PM
    * @param storeId        门店标识
    * @param url            跳转链接
    * @param openId         接受者id
    * @param memberName     会员名称
    * @param memberLevel    会员等级
    * @param projectName    服务项目    
    * @param appointTime    预约时间
    * @param result         预约结果
    * @param title          标题
    * @param remark         备注
    * @return   成功返回true，失败返回false
     */
    public boolean sendAppointmentResult(String storeId, String url, String openId, String memberName, String memberLevel, 
            String projectName, String appointTime, String result, String title, String remark){
        String templateId = redisService.hget(App.Redis.WECHAT_TEMPLATE_APPOINTMENT_RESULT_HASH, storeId);
        if (StringUtils.isBlank(templateId)) {
            return true;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("touser", openId);
        params.put("template_id", templateId);
        params.put("url", url);
        params.put("topcolor", "#FF0000");
        Map<String, Map<String, String>> data = new HashMap<String, Map<String,String>>();

        //标题
        Map<String, String> keyword0 = new HashMap<String, String>();
        keyword0.put("value", title + "\r\n");
        keyword0.put("color", "#173177");
        data.put("first", keyword0);
        
        Map<String, String> keyword1 = new HashMap<String, String>();
        keyword1.put("value", memberName + "\r\n客户等级：" + memberLevel);
        keyword1.put("color", "#173177");
        data.put("keyword1", keyword1);
        
        Map<String, String> keyword2 = new HashMap<String, String>();
        keyword2.put("value", projectName);
        keyword2.put("color", "#173177");
        data.put("keyword2", keyword2);

        Map<String, String> keyword3 = new HashMap<String, String>();
        keyword3.put("value", appointTime);
        keyword3.put("color", "#173177");
        data.put("keyword3", keyword3);
        
        Map<String, String> keyword4 = new HashMap<String, String>();
        keyword4.put("value", result);
        keyword4.put("color", "#173177");
        data.put("keyword4", keyword4);

        //备注
        Map<String, String> keyword6 = new HashMap<String, String>();
        keyword6.put("value", "\r\n" + remark);
        keyword6.put("color", "#173177");
        data.put("remark", keyword6);

        params.put("data", data);
        
        return sendTmpl(getTemplSendUrl(storeId), JSONObject.fromObject(params).toString());
    }
    
    
    /**
     * 会员消费提醒通知
    * @author 张进军
    * @date Nov 4, 2015 3:05:02 PM
    * @param title          标题
    * @param remark         备注
    * @param storeId        门店标识
    * @param url            跳转链接
    * @param openId         接受者id
    * @param storeName      门店名称
    * @param phone          会员手机号码
    * @param receivableAmount   应付金额
    * @param discountAmount     结算金额
    * @param projectName    消费项目
    * @param payTime        消费时间
    * @return   成功返回true，失败返回false
     */
    public boolean sendPaymentNotice(String title, String remark, String storeId, String url, String openId, String storeName, 
            String phone, String projectName, String receivableAmount, String discountAmount, String payTime){
        String templateId = redisService.hget(App.Redis.WECHAT_TEMPLATE_PAYMENT_HASH, storeId);
        if (StringUtils.isBlank(templateId)) {
            return true;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("touser", openId);
        params.put("template_id", templateId);
        params.put("url", url);
        params.put("topcolor", "#FF0000");
        Map<String, Map<String, String>> data = new HashMap<String, Map<String,String>>();

        //标题
        Map<String, String> keyword0 = new HashMap<String, String>();
        keyword0.put("value", title + "\r\n");
        keyword0.put("color", "#173177");
        data.put("first", keyword0);
        
        phone = phone.substring(0, 3) + "****" + phone.substring(7, 11);
        //会员卡号
        Map<String, String> keyword1 = new HashMap<String, String>();
        keyword1.put("value", phone + "\r\n消费项目：" + projectName);
        keyword1.put("color", "#173177");
        data.put("keyword1", keyword1);
        
        //消费金额
        Map<String, String> keyword2 = new HashMap<String, String>();
        keyword2.put("value", receivableAmount);
        keyword2.put("color", "#173177");
        data.put("keyword2", keyword2);
        
        //交易时间
        Map<String, String> keyword3 = new HashMap<String, String>();
        keyword3.put("value", payTime);
        keyword3.put("color", "#173177");
        data.put("keyword3", keyword3);
        
        //结算金额
        Map<String, String> keyword4 = new HashMap<String, String>();
        keyword4.put("value", discountAmount);
        keyword4.put("color", "#173177");
        data.put("keyword4", keyword4);
        
        //备注
        Map<String, String> keyword6 = new HashMap<String, String>();
        keyword6.put("value", "\r\n" + remark);
        keyword6.put("color", "#173177");
        data.put("remark", keyword6);

        params.put("data", data);
        
        return sendTmpl(getTemplSendUrl(storeId), JSONObject.fromObject(params).toString());
    }
    
    
    /**
     * 会员充值通知
    * @author 张进军
    * @date Nov 4, 2015 3:05:02 PM
    * @param storeId        门店标识
    * @param url            跳转链接
    * @param openId         接受者id
    * @param storeName      门店名称
    * @param memberLevel    会员等级
    * @param chargeAmount   充值金额
    * @param balanceAmount  当前余额
    * @param chargeTime     充值时间
    * @return   成功返回true，失败返回false
     */
    public boolean sendMemberCharge(String storeId, String url, String openId, String storeName, 
            String memberLevel, String chargeAmount, String balanceAmount, String chargeTime){
        String templateId = redisService.hget(App.Redis.WECHAT_TEMPLATE_MEMBER_CHARGE_HASH, storeId);
        if (StringUtils.isBlank(templateId)) {
            return true;
        }
        String title = "感谢您选择" + storeName;
        String remark = "您可以在\"我的-储值余额\"中随时查看资金流水记录";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("touser", openId);
        params.put("template_id", templateId);
        params.put("url", url);
        params.put("topcolor", "#FF0000");
        Map<String, Map<String, String>> data = new HashMap<String, Map<String,String>>();

        //标题
        Map<String, String> keyword0 = new HashMap<String, String>();
        keyword0.put("value", title + "\r\n");
        keyword0.put("color", "#173177");
        data.put("first", keyword0);
        
        //店铺名称
        Map<String, String> keyword1 = new HashMap<String, String>();
        keyword1.put("value", storeName);
        keyword1.put("color", "#173177");
        data.put("keyword1", keyword1);
        
        //会员类型
        Map<String, String> keyword2 = new HashMap<String, String>();
        keyword2.put("value", memberLevel);
        keyword2.put("color", "#173177");
        data.put("keyword2", keyword2);
        
        //充值金额
        Map<String, String> keyword3 = new HashMap<String, String>();
        keyword3.put("value", chargeAmount);
        keyword3.put("color", "#173177");
        data.put("keyword3", keyword3);
        
        //当前余额
        Map<String, String> keyword4 = new HashMap<String, String>();
        keyword4.put("value", balanceAmount);
        keyword4.put("color", "#173177");
        data.put("keyword4", keyword4);
        
        //充值时间
        Map<String, String> keyword5 = new HashMap<String, String>();
        keyword5.put("value", chargeTime);
        keyword5.put("color", "#173177");
        data.put("keyword5", keyword5);
        
        //备注
        Map<String, String> keyword6 = new HashMap<String, String>();
        keyword6.put("value", "\r\n" + remark);
        keyword6.put("color", "#173177");
        data.put("remark", keyword6);

        params.put("data", data);
        
        return sendTmpl(getTemplSendUrl(storeId), JSONObject.fromObject(params).toString());
    }
    
    
    public boolean sendTmpl(String url, String params){
        String res = HttpClientUtil.sendPost(url, params, null);
        logger.info("weixin templ msg send result : " + res);
        JSONObject resJson = JSONObject.fromObject(res);
        int code = resJson.getInt("errcode");
        if (code != 0) {
            logger.error("push group join msg error, data : " + params + " result : " + res);
        }
        logger.info("push group join msg success, data : " + params);
        return true;
    }
    
    private String getTemplSendUrl(String storeId) {
        String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeId);
        return "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
    }
}
