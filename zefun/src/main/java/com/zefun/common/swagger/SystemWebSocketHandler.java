package com.zefun.common.swagger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.zefun.common.consts.App;

/**
 * ws 连接
* @author 高国藩
* @date 2016年5月23日 下午4:32:57
 */
public class SystemWebSocketHandler implements WebSocketHandler {
    
    /** log */
    private Logger log = Logger.getLogger(SystemWebSocketHandler.class);

    /** 创建一个集合存放websocketsession */
    private static final Map<String, WebSocketSession> SOCKETS;

    static {
        SOCKETS = new HashMap<>();
    }

    /** 客户端连接后,将session存放,以便发送 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session)throws Exception {
        String storeAccount = (String) session.getAttributes().get(App.Session.STORE_ACCOUNT);
        SOCKETS.put(storeAccount, session);
        log.info("websocke connenct success, and the online storeAccout is " + storeAccount);
        if (storeAccount != null) {
            //历史消息
        }
    }

    /** 接收客户端发送过来的方法 */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        
        log.info("message"+message.getPayload().toString());
//        String storeAccount = (String) session.getAttributes().get(App.Session.STORE_ACCOUNT);
//        log.info(message.getPayload().toString());
//        TextMessage message1 = new TextMessage("9999");
//        sendMessageToSTOREUSER(message1);
    }

    /** 传输错误时触发 */
    @Override
    public void handleTransportError(WebSocketSession session,
            Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        SOCKETS.remove(session);
    }

    /** 连接关闭时触发 */
    @Override
    public void afterConnectionClosed(WebSocketSession session,
            CloseStatus closeStatus) throws Exception {
        SOCKETS.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 消息推送-所有user
    * @author 高国藩
    * @date 2016年5月23日 下午4:33:51
    * @param message 消息
     * @throws IOException 
     */
    public void sendMessageToSTOREUSER(TextMessage message) throws IOException {
        Set<String> keys = SOCKETS.keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()){
            String storeAccount = it.next();
            if (SOCKETS.get(storeAccount).isOpen()) {
                SOCKETS.get(storeAccount).sendMessage(message);
            }
        }
    }

    /**
     * message定向传输
    * @author 高国藩
    * @date 2016年5月23日 下午4:34:12
    * @param userName userName
    * @param message  message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        Set<String> keys = SOCKETS.keySet();
        
        Iterator<String> it = keys.iterator();
        while (it.hasNext()){
            String storeAccount = it.next();
            if (storeAccount.equals(userName)) {
                try {
                    if (SOCKETS.get(storeAccount).isOpen()) {
                        SOCKETS.get(storeAccount).sendMessage(message);
                        log.info("支付成功消息推送.");
                    }
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}