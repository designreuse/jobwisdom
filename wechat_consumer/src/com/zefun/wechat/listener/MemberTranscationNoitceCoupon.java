package com.zefun.wechat.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

import com.rabbitmq.client.Channel;
import com.zefun.web.dto.CouponInfoDto;
import com.zefun.web.entity.StoreWechat;
import com.zefun.web.mapper.StoreWechatMapper;
import com.zefun.wechat.service.RedisService;
import com.zefun.wechat.utils.App;
import com.zefun.wechat.utils.HttpClientUtil;

public class MemberTranscationNoitceCoupon implements
        ChannelAwareMessageListener {

    @Autowired
    private MessageConverter msgConverter;
    @Autowired
    private RedisService redisService;
    @Autowired
    private StoreWechatMapper storeWechatMapper;

    private static final Logger logger = Logger
            .getLogger(EmployeeServiceNoticeListener.class);

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
                message.getMessageProperties().setDeliveryTag(
                        App.DELIVERIED_TAG);
                logger.info("revice and ack msg: "
                        + (obj == null ? message : new String((byte[]) obj)));
            }
        }
        if (obj == null) {
            return;
        }
        Map<?, ?> map = (Map<?, ?>) obj;
        Integer storeId = (Integer) map.get("storeId");
        StoreWechat storeWechat = storeWechatMapper.selectByStoreId(storeId);
        CouponInfoDto couponInfo = (CouponInfoDto) map.get("couponInfo");
        @SuppressWarnings("unchecked")
        List<String> touser = (List<String>) map.get("touser");
        for (int i = 0; i < touser.size(); i++) {
            String openId = touser.get(i);
            String tempId = storeWechat.getCouponsTm();
            logger.info("正在发送优惠券通知,现有模板ID是:"+tempId);
            this.sendCouponTempleMsg("恭喜您获得一张优惠券", couponInfo.getCouponName(),
                    couponInfo.getCouponPrice().toString(),
                    couponInfo.getCouponUse(), couponInfo.getCouponStopTime(),
                    openId, "http://www.maywant.com/memberCenter/view/memberCoupon", storeId.toString(), tempId);
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
    public void sendCouponTempleMsg(String title, String name, String price,
            String use, String stopTime, String openId, String url,
            String storeId, String tempId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // data 数据
        Map<String, Object> data = new HashMap<String, Object>();

        // first 数据
        Map<String, String> first = new HashMap<String, String>();
        first.put("value", title);
        first.put("color", "#173177");

        // couponName 优惠券名称
        Map<String, String> couponName = new HashMap<String, String>();
        couponName.put("value", name);
        couponName.put("color", "#173177");

        // couponName 优惠券名称
        Map<String, String> couponPrice = new HashMap<String, String>();
        couponPrice.put("value", price + "元");
        couponPrice.put("color", "#173177");

        // couponUse 优惠券适用
        Map<String, String> couponUse = new HashMap<String, String>();
        couponUse.put("value", use);
        couponUse.put("color", "#173177");

        // couponStopTime 截止日期
        Map<String, String> couponStopTime = new HashMap<String, String>();
        couponStopTime.put("value", stopTime);
        couponStopTime.put("color", "#173177");
        
        //结束语
        Map<String, String> remark = new HashMap<String, String>();
        remark.put("value", "请您尽快兑换使用,感谢你的关注.");
        remark.put("color", "#173177");

        data.put("first", first);
        data.put("keynote1", couponName);
        data.put("keynote2", couponUse);
        data.put("keynote3", couponStopTime);
        data.put("remark", remark);

        map.put("data", data);
        map.put("touser", openId);
        map.put("template_id", tempId);
        map.put("url", url);
        map.put("topcolor", "#FF0000");

        map.put("storeId", storeId);
        HttpClientUtil.httpRequest(getTemplSendUrl(storeId), "POST", JSONObject
                .fromObject(map).toString());
    }

    private String getTemplSendUrl(String storeId) {
        String accessToken = redisService.hget(
                App.Redis.STORE_WECHAT_ACCESS_TOKEN_KEY_HASH, storeId);
        return "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="
                + accessToken;
    }
}
