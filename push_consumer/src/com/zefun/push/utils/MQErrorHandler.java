package com.zefun.push.utils;

import java.lang.reflect.Field;

import org.apache.commons.lang.reflect.FieldUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ErrorHandler;

public class MQErrorHandler implements ErrorHandler {

    private static final Logger logger = Logger.getLogger(MQErrorHandler.class);

    @Autowired
    private MessageConverter msgConverter;

    @Override
    public void handleError(Throwable cause) {
        Field mqMsgField = FieldUtils.getField(MQListenerExecutionFailedException.class, "mqMsg", true);
        if (mqMsgField != null) {
            try {
                Message mqMsg = (Message) mqMsgField.get(cause);
                Object msgObj = msgConverter.fromMessage(mqMsg);
                logger.error("handle MQ msg: " + msgObj + " failed, record it to redis.", cause);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.error("An error occurred.", cause);
        }
    }

}
