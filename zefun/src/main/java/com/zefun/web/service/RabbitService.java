package com.zefun.web.service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.exception.ONSClientException;
import com.zefun.common.consts.App;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.ToolSpring;
import com.zefun.web.dto.ChatDataDto;
import com.zefun.web.dto.ChatNoticeDto;
import com.zefun.web.dto.CouponInfoDto;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.StoreSetting;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.mapper.StoreSettingMapper;
import com.zefun.wechat.service.SmsService;

import net.sf.json.JSONObject;

/**
 * 消息队列服务类
* @author 张进军
* @date Aug 24, 2015 3:51:04 PM 
*/
@Service
public class RabbitService {
    /** 日志对象 */
    private static Logger logger = Logger.getLogger(RabbitService.class);

    /** rabbit队列模版方法 */
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    /**门店信息操作对象*/
    @Autowired
    private StoreInfoMapper storeInfoMapper;
    
    /**门店基础设置操作对象*/
    @Autowired
    private StoreSettingMapper storeSettingMapper;
    
    /**员工信息操作对象*/
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;
    
    /**redis操作对象*/
    @Autowired
    private RedisService redisService;
    
    /** 短信服务对象 */
    @Autowired
    private SmsService smsService;

    /**
     * 发送消息到队列
    * @author 张进军
    * @date Aug 24, 2015 4:06:25 PM
    * @param routingKey     队列名
    * @param object         传输对象
     */
    public void send(String routingKey, Object object) {
    	
    	Producer producer = (Producer) ToolSpring.getBean("producer");
    	Message msg = new Message("jobwisdom_Topic_test", routingKey, toByteArray(object));

        msg.setKey("ORDERID_100");
        // 发送消息，只要不抛异常就是成功
        try {
            SendResult sendResult = producer.send(msg);
            assert sendResult != null;
        }
        catch (ONSClientException e) {
        }
//        logger.info("Publish message --> routingKey : " + routingKey + ", Message : " + JSONObject.fromObject(object));
//        rabbitTemplate.convertAndSend(routingKey, object);
    }
    
    /**
     * 
    * @author 老王
    * @date 2016年5月17日 下午7:02:25 
    * @param obj 对象
    * @return byte[]
     */
    public byte[] toByteArray (Object obj) {      
        byte[] bytes = null;      
        ByteArrayOutputStream bos = new ByteArrayOutputStream();      
        try {        
            ObjectOutputStream oos = new ObjectOutputStream(bos);         
            oos.writeObject(obj);        
            oos.flush();         
            bytes = bos.toByteArray ();      
            oos.close();         
            bos.close();        
        } 
        catch (IOException ex) {        
            ex.printStackTrace();   
        }      
        return bytes;    
    } 
    
    
    /**
     * 发送短信验证码
    * @author 张进军
    * @date Sep 17, 2015 12:02:11 PM
    * @param storeId    店铺标识
    * @param phone      手机号码
    * @param business   业务描述
     */
    public void sendVerifyCode(int storeId, String phone, String business){
        String code = RandomStringUtils.randomNumeric(6);
//        Map<String, Object> record = new HashMap<String, Object>();
//        record.put("storeId", storeId);
//        record.put("phone", phone);
//        record.put("code", code);
//        record.put("desc", business);
//        send(App.Queue.SMS_TEMPLATE_VERIFYCODE, record);
        
        smsService.sendVerifyCode(storeId, phone, code, business);
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
     */
    public void sendMemberCharge(int storeId, String url, String openId, String storeName, 
            String memberLevel, String chargeAmount, String balanceAmount, String chargeTime){
        int mainId = storeInfoMapper.selectMainIdByStoreId(storeId);
        Map<String, Object> record = new HashMap<String, Object>();
        record.put("storeId", mainId);
        record.put("url", url.replace("{storeId}", String.valueOf(mainId)));
        record.put("openId", openId);
        record.put("storeName", storeName);
        record.put("memberLevel", memberLevel);
        record.put("chargeAmount", chargeAmount);
        record.put("balanceAmount", balanceAmount);
        record.put("chargeTime", chargeTime);
        send(App.Queue.MEMBER_CHARGE_NOTICE, record);
    }
    
    
    /**
     * 会员消费提醒通知
    * @author 张进军
    * @date Nov 4, 2015 3:05:02 PM
    * @param type           提醒类型(1:提醒会员结账,2:结账扣款通知)
    * @param storeId        门店标识
    * @param url            跳转链接
    * @param openId         接受者id
    * @param storeName      门店名称
    * @param phone          会员手机号码
    * @param receivableAmount   应付金额
    * @param discountAmount     结算金额
    * @param balanceAmount      当前余额
    * @param debtAmount         挂账金额
    * @param projectList    消费项目,多个以,号分割
     */
    public void sendPaymentNotice(int type, int storeId, String url, String openId, String storeName, 
            String phone, String projectList, String receivableAmount, String discountAmount, String balanceAmount, String debtAmount){
        String title = "您有一笔消费需要确认结账";
        String remark = "点击进入结账页面，记得评价哦";
        if (type == 2) {
            title = "感谢您选择" + storeName;
            remark = "评价得积分，预约更优惠";
        }
        int mainId = storeInfoMapper.selectMainIdByStoreId(storeId);
        Map<String, Object> record = new HashMap<String, Object>();
        record.put("title", title);
        record.put("remark", remark);
        record.put("storeId", mainId);
        record.put("url", url.replace("{storeId}", mainId + ""));
        record.put("openId", openId);
        record.put("storeName", storeName);
        record.put("phone", phone);
        record.put("projectName", projectList);
        record.put("receivableAmount", receivableAmount);
        record.put("discountAmount", discountAmount);
        record.put("payTime", DateUtil.getCurTime());
        send(App.Queue.MEMBER_TRANSCATION_NOTICE, record);
    }
    
    
    /**
     * 员工服务通知
    * @author 张进军
    * @date Oct 27, 2015 9:28:36 PM
    * @param title          标题
    * @param remark         备注
    * @param storeId        店铺标识
    * @param url            跳转地址
    * @param openId         会员标识对应的微信标识
    * @param serviceNmae    业务名称
    * @param turnType       指定服务/轮牌服务/中途更换
    * @param oldEmployeeInfo    上一步骤人员
    * @param employeeId     当前步骤员工标识
    * @param isSpeech       是否播放语音(0:否，1:是)
     */
    public void sendServiceNotice(String title, String remark, int storeId, String url, String openId, String serviceNmae, 
            String turnType, EmployeeInfo oldEmployeeInfo, int employeeId, int isSpeech) {
        
        int mainId = storeInfoMapper.selectMainIdByStoreId(storeId);
        
        if (oldEmployeeInfo == null) {
            logger.info("sendServiceNotice -- > 上一步骤为空");
        } 
        else {
            logger.info("sendServiceNotice -- > 上一步骤为的员工标识：" + oldEmployeeInfo.getEmployeeId());
        }
        logger.info("sendServiceNotice -- > storeId : " + storeId + ", mainStoreId : " + mainId + ", url : " 
                + url + ", employeeId : " + employeeId + ", openId : " + openId);
        
        Map<String, Object> record = new HashMap<String, Object>();
        record.put("title", title);
        record.put("storeId", mainId);
        record.put("url", url);
        record.put("openId", openId);
        record.put("title", title);
        record.put("remark", remark);
        record.put("serviceNmae", serviceNmae);
        record.put("turnType", turnType);
        send(App.Queue.EMPLOYEE_SERVICE_NOTICE, record);

        if (isSpeech == 1 && (oldEmployeeInfo == null || oldEmployeeInfo.getEmployeeId() != employeeId)) {
            //检查门店是否需要预约语音提示
            StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(storeId);
            if (storeSetting.getSpeechTurnUse() == 1) {
                /**给聊天室门店下在线用户发送通知*/
                Set<String> set = redisService.smembers(App.Redis.STORE_TO_CHAT_USER_SET_PRE + storeId);
                if (set != null && set.size() > 0) {
                    EmployeeInfo employeeInfo = employeeInfoMapper.selectByPrimaryKey(employeeId);
                    ChatNoticeDto chat = new ChatNoticeDto();
                    chat.setFid(2);
                    chat.setFromUser("0");
                    ChatDataDto data = new ChatDataDto();
                    data.setType(4);
                    data.setMsg(employeeInfo.getEmployeeCode() + "号，有顾客需要您的服务");
                    data.setCreateTime(DateUtil.getCurTime());
                    chat.setData(data);
                    for (String userId : set) {
                        chat.setToUser(userId);
                        send(App.Queue.CHAT_NOTIFY, JSONObject.fromObject(chat).toString());
                    }
                }
            }
        }
    }
    
    
    /**
     * 会员预约通知
    * @author 张进军
    * @date Oct 27, 2015 9:28:36 PM
    * @param storeId        店铺标识
    * @param mainStoreId    总店标识
    * @param url            跳转地址
    * @param employeeId     员工标识
    * @param openId         会员标识对应的微信标识
    * @param memberName     会员名称
    * @param memberLevel    会员等级
    * @param projectName    服务项目
    * @param appointTime    预约时间
     */
    public void sendAppointmentApplyNotice(int storeId, String mainStoreId, String url, int employeeId, String openId, String memberName, 
            String memberLevel, String projectName, String appointTime) {
        logger.info("sendAppointmentApplyNotice -- > storeId : " + storeId + ", mainStoreId : " + mainStoreId + ", url : "
                + url + ", employeeId : " + employeeId + ", openId : " + openId);
        Map<String, Object> record = new HashMap<String, Object>();
        record.put("storeId", mainStoreId);
        record.put("url", url);
        record.put("openId", openId);
        record.put("memberName", memberName);
        record.put("memberLevel", memberLevel);
        record.put("projectName", projectName);
        record.put("appointTime", appointTime);
        record.put("createTime", DateUtil.getCurTime());
//        send(App.Queue.APPOINTMENT_APPLY_NOTICE, record);
        rabbitTemplate.convertAndSend(App.Queue.APPOINTMENT_APPLY_NOTICE, record);
        //检查门店是否需要预约语音提示
        StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(storeId);
        if (storeSetting.getSpeechType() == 1) {
            /**给聊天室门店下在线用户发送通知*/
            Set<String> set = redisService.smembers(App.Redis.STORE_TO_CHAT_USER_SET_PRE + storeId);
            if (set != null && set.size() > 0) {
                EmployeeInfo employeeInfo = employeeInfoMapper.selectByPrimaryKey(employeeId);
                ChatNoticeDto chat = new ChatNoticeDto();
                chat.setFid(2);
                chat.setFromUser("0");
                ChatDataDto data = new ChatDataDto();
                data.setType(2);
                data.setMsg(employeeInfo.getEmployeeCode() + "号，您有新预约，请与顾客确认");
                data.setCreateTime(DateUtil.getCurTime());
                chat.setData(data);
                for (String userId : set) {
                    chat.setToUser(userId);
                    send(App.Queue.CHAT_NOTIFY, JSONObject.fromObject(chat).toString());
                    rabbitTemplate.convertAndSend(App.Queue.CHAT_NOTIFY, JSONObject.fromObject(chat).toString());
                }
            }
        }
    }
    
    
    /**
     * 预约结果通知
    * @author 张进军
    * @date Oct 27, 2015 9:28:36 PM
    * @param type           业务类型(1:员工同意,2:员工拒绝,3:会员取消)
    * @param storeId        店铺标识
    * @param url            跳转地址
    * @param openId         会员标识对应的微信标识
    * @param memberName     会员名称
    * @param memberLevel    会员等级
    * @param projectName    服务项目
    * @param appointTime    预约时间
    * @param reason         取消原因
     */
    public void sendAppointmentResultNotice(int type, int storeId, String url, String openId, String memberName, 
            String memberLevel, String projectName, String appointTime, String reason) {
        String title = "您好，您的预约已被确认";
        String remark = "为了完美的服务体验，请提前安排好时间";
        String result = "预约成功";
        if (type == 2) {
            title = "对不起，您的预约不成功";
            remark = "原因：" + reason;
            result = "预约失败";
        }
        else if (type == 3) {
            title = "您好，顾客取消了预约";
            remark = "原因：" + reason;
            result = "预约取消";
        }
        int mainId = storeInfoMapper.selectMainIdByStoreId(storeId);
        Map<String, Object> record = new HashMap<String, Object>();
        record.put("title", title);
        record.put("remark", remark);
        record.put("result", result);
        record.put("storeId", mainId);
        record.put("url", url);
        record.put("openId", openId);
        record.put("memberName", memberName);
        record.put("memberLevel", memberLevel);
        record.put("projectName", projectName);
        record.put("appointTime", appointTime);
        rabbitTemplate.convertAndSend(App.Queue.APPOINTMENT_RESULT_NOTICE, record);
//        send(App.Queue.APPOINTMENT_RESULT_NOTICE, record);
    }
    
    
    /**
     * 发送优惠券队列
    * @author 高国藩
    * @date 2015年12月11日 下午3:40:29
    * @param storeId       门店
    * @param couponInfo    优惠券
    * @param touser        发送者列表
     */
    public void sendCoupons(Integer storeId, CouponInfoDto couponInfo,
            List<String> touser) {
        Map<String, Object> rabbitMap = new HashMap<String, Object>();
        rabbitMap.put("storeId", storeId);
        rabbitMap.put("couponInfo", couponInfo);
        rabbitMap.put("touser", touser);
        send(App.Queue.SEND_COUPONS, rabbitMap);
        logger.info("end send msg");
    }
    
    
    /**i k
     * 自助收银结账时向消息队列推送订单标识，用于处理员工提成的计算
     * @param orderId 订单标识
     */
    public void sendCashierOrderComission(Integer orderId) {
    	JSONObject json = new JSONObject();
		json.put("orderId", orderId);
    	send(App.Queue.CASHIER_ORDER_COMMISSION, json);
    }

    /**
     * 
    * @author 王大爷
    * @date 2016年1月7日 下午5:52:03
    * @param detailId 明细标识
     */
    public void sendCashierUpdateOrderCommission(Integer detailId) {
        JSONObject json = new JSONObject();
        json.put("detailId", detailId);
        send(App.Queue.CASHIER_UPDATE_ORDER_COMMISSION, json);
    }
    
    
    /**
     * 发送会员奖励通知
    * @author 张进军
    * @date Jan 8, 2016 4:00:26 PM
    * @param storeId    店铺标识
    * @param url        跳转地址
    * @param openId     微信标识
    * @param title		标题
    * @param reward		奖励内容
    * @param overdue	过期说明
    * @param remark		备注说明
     */
    public void sendMemberRewardNotice(int storeId, String url, String openId, String title, String reward, String overdue, String remark){
    	int mainId = storeInfoMapper.selectMainIdByStoreId(storeId);
    	
    	Map<String, Object> record = new HashMap<String, Object>();
    	record.put("url", url);
        record.put("title", title);
        record.put("storeId", mainId);
        record.put("openId", openId);
        record.put("reward", reward);
        record.put("overdue", overdue);
        record.put("remark", remark);
        send(App.Queue.MEMBER_REWARD_NOTICE, record);
    }
}
