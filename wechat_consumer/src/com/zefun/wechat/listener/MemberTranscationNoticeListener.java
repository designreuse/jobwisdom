package com.zefun.wechat.listener;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

import com.rabbitmq.client.Channel;
import com.zefun.wechat.service.TemplateNoticeService;
import com.zefun.wechat.utils.App;

/**
 * 会员交易通知
* @author 张进军
* @date Aug 25, 2015 3:43:59 PM
 */
public class MemberTranscationNoticeListener implements ChannelAwareMessageListener {

	private static final Logger logger = Logger.getLogger(MemberTranscationNoticeListener.class);

	@Autowired
    private MessageConverter msgConverter;
	
	@Autowired
	private TemplateNoticeService templateNoticeService;

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
        boolean flag = false;
        if (obj instanceof Map<?, ?>) {
        	Map<?, ?> cpaMsg = (Map<?, ?>) obj;
        	logger.info("cpaMsg : " + cpaMsg);
        	String title = cpaMsg.get("title").toString();
        	String remark = cpaMsg.get("remark").toString();
            String url = cpaMsg.get("url").toString();
            String storeId = cpaMsg.get("storeId").toString();
            String openId = cpaMsg.get("openId").toString();
            String storeName = cpaMsg.get("storeName").toString();
            String phone = cpaMsg.get("phone").toString();
            String projectName = cpaMsg.get("projectName").toString();
            String receivableAmount = cpaMsg.get("receivableAmount").toString();
            String discountAmount = cpaMsg.get("discountAmount").toString();
            String payTime = cpaMsg.get("payTime").toString();
            flag = templateNoticeService.sendPaymentNotice(title, remark, storeId, url, openId, storeName, 
                    phone, projectName, receivableAmount, discountAmount, payTime);
        } else {
        	logger.warn("not a map msg, ingore it.");
        }
        if (!flag) {
        	logger.error("hanler message " + obj + " failed, throw a exception, and it will be retried.");
            throw new RuntimeException("hanler message " + obj + " failed.");
		}
	}

}
