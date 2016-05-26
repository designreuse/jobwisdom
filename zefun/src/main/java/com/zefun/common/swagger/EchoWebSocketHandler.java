package com.zefun.common.swagger;

import org.apache.log4j.Logger;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * socke.js推送消息
* @author 高国藩
* @date 2016年5月25日 下午7:43:00
 */
public class EchoWebSocketHandler extends TextWebSocketHandler {


    /** log */
    private Logger logger = Logger.getLogger(EchoWebSocketHandler.class);
    
    /**
     * 收消息
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info(message.toString());
        session.sendMessage(new TextMessage(message.getPayload()));
    }
    
    /**
     * 无惨
    * @author 高国藩
    * @date 2016年5月25日 下午7:42:50
     */
    public EchoWebSocketHandler() {
        // TODO Auto-generated constructor stub
    }

}
