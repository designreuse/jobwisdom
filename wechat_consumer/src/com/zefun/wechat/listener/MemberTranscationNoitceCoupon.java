package com.zefun.wechat.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

import com.rabbitmq.client.Channel;
import com.zefun.wechat.service.RedisService;
import com.zefun.wechat.utils.App;
import com.zefun.wechat.utils.HttpClientUtil;

public class MemberTranscationNoitceCoupon implements ChannelAwareMessageListener {

    @Autowired
    private MessageConverter msgConverter;
    @Autowired
    private RedisService redisService;

    private static final Logger logger = Logger.getLogger(EmployeeServiceNoticeListener.class);
    
    private static final String serverPost = "http://job.jobwisdom.cn/jobwisdom/memberCenter/view/memberCoupon";

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        Object obj = null;
        try {
            obj = msgConverter.fromMessage(message);
        } catch (MessageConversionException e) {
            logger.error("convert MQ message error.", e);
        } finally {
            long deliveryTag = message.getMessageProperties().getDeliveryTag();
            if (deliveryTag != App.DELIVERIED_TAG) {
                channel.basicAck(deliveryTag, false);
                message.getMessageProperties().setDeliveryTag(App.DELIVERIED_TAG);
                logger.info("revice and ack msg: " + (obj == null ? message : new String((byte[]) obj)));
            }
        }
        if (obj == null) {
            return;
        }
        JSONObject map = JSONObject.fromObject(obj);
        String storeAccount = map.get("storeAccount").toString();
        String storeName = map.get("storeName").toString();
        String couponName = map.get("couponName").toString();
        String couponStopTime = map.get("couponStopTime").toString();
        String tempId = map.get("tempId").toString();
        for (int i = 0; i < map.getJSONArray("touser").size(); i++) {
            String openId = (String) map.getJSONArray("touser").get(i);
            logger.info("正在发送优惠券通知,现有模板ID是:"+tempId);
            this.sendCouponTempleMsg(storeName, couponName, couponStopTime, openId, serverPost, tempId, storeAccount);
        }
    }

    /**
     * 模板发送优惠券
    * @author 高国藩
    * @date 2015年9月15日 下午4:21:35
    * @param title 标题
    * @param name 优惠券名称
    * @param price 优惠券价格
    * @param use 优惠券适用
    * @param stopTime 截止日期
    * @param openId 发送对象
    * @param url 点击跳转链接
    * @param storeId 门店
    * @param tempId 微信模板ID
     */
    public void sendCouponTempleMsg(String storeName, String name, String stopTime, String openId, String url, String tempId, String storeAccount) {
        Map<String, Object> map = new HashMap<String, Object>();
        // data 数据
        Map<String, Object> data = new HashMap<String, Object>();

        // first 数据
        Map<String, String> first = new HashMap<String, String>();
        first.put("value", "优惠券提醒");
        first.put("color", "#173177");
        
        // couponName 商户名称
        Map<String, String> storeInfo = new HashMap<String, String>();
        storeInfo.put("value", storeName);
        storeInfo.put("color", "#173177");

        // couponName 优惠券名称
        Map<String, String> couponName = new HashMap<String, String>();
        couponName.put("value", name);
        couponName.put("color", "#173177");

        // couponStopTime 截止日期
        Map<String, String> couponStopTime = new HashMap<String, String>();
        couponStopTime.put("value", stopTime);
        couponStopTime.put("color", "#173177");
        
        //结束语
        Map<String, String> remark = new HashMap<String, String>();
        remark.put("value", "请您尽快到点使用,感谢你的关注.");
        remark.put("color", "#173177");
        
      //结束语
        Map<String, String> couponsDescNum = new HashMap<String, String>();
        couponsDescNum.put("value", "1张");
        couponsDescNum.put("color", "#173177");

        data.put("first", first);
        data.put("keyword1", storeInfo);
        data.put("keyword2", couponName);
        data.put("keyword3", couponsDescNum);
        data.put("keyword4", couponStopTime);
        data.put("remark", remark);

        map.put("data", data);
        map.put("touser", openId);
        map.put("template_id", tempId);
        map.put("url", url);
        map.put("topcolor", "#FF0000");

        HttpClientUtil.httpRequest(getTemplSendUrl(storeAccount), "POST", JSONObject.fromObject(map).toString());
    }

    private String getTemplSendUrl(String storeAccount) {
        String accessToken = redisService.hget(App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeAccount);
        return "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
    }
}
