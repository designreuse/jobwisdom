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
 * 会员预约申请通知
* @author 张进军
* @date Aug 25, 2015 3:43:59 PM
 */
public class AppointmentApplyNoticeListener implements ChannelAwareMessageListener {

	private static final Logger logger = Logger.getLogger(AppointmentApplyNoticeListener.class);

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
        	String url = cpaMsg.get("url").toString();
            String storeId = cpaMsg.get("storeId").toString();
            String openId = cpaMsg.get("openId").toString();
            String memberName = cpaMsg.get("memberName").toString();
            String memberLevel = cpaMsg.get("memberLevel").toString();
            String projectName = cpaMsg.get("projectName").toString();
            String appointTime = cpaMsg.get("appointTime").toString();
            String createTime = cpaMsg.get("createTime").toString();
            flag = templateNoticeService.sendAppointmentApply(storeId, url, openId, memberName, memberLevel, projectName, createTime, appointTime);
        } else {
        	logger.warn("not a map msg, ingore it.");
        }
        if (!flag) {
        	logger.error("hanler message " + obj + " failed, throw a exception, and it will be retried.");
            throw new RuntimeException("hanler message " + obj + " failed.");
		}
	}

}
