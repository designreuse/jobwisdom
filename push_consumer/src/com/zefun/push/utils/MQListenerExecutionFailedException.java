package com.zefun.push.utils;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.ListenerExecutionFailedException;

public class MQListenerExecutionFailedException extends
        ListenerExecutionFailedException {

    private static final long serialVersionUID = 1L;

    private Message mqMsg;

    public MQListenerExecutionFailedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public MQListenerExecutionFailedException(String msg, Message mqMsg, Throwable cause) {
        this(msg, cause);
        this.mqMsg = mqMsg;
    }

    public Message getMqMsg() {
        return mqMsg;
    }

}
