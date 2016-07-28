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

    /** 创建一个集合存放websocketsession, 该变量服务于扫码支付提醒功能 */
    private static final Map<String, WebSocketSession> SOCKETS;
    /** 创建一个集合存放websocketsession, 该变量服务于扫码支付提醒功能 */
    private static final Map<String, WebSocketSession> LOGIN;
    /** 登陆计时器的前缀 */
    private static final String LOGIN_PREFIX = "login_";

    static {
        SOCKETS = new HashMap<>();
        LOGIN = new HashMap<>();
    }

    /** 客户端连接后,将session存放,以便发送 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String storeAccount = (String) session.getAttributes().get(App.Session.STORE_ACCOUNT);
        if (storeAccount!=null){
            SOCKETS.put(storeAccount, session);
        }
        try {
            Integer userId = Integer.parseInt(session.getAttributes().get(App.Session.USER_ID).toString());
            LOGIN.put(LOGIN_PREFIX + userId, session);
        } 
        catch (Exception e) {
//            log.info("企业用户登陆,无需存放userId");
        }
//        log.info("当前聊天室存放的值为 , " + SOCKETS.toString());
//        log.info("当前聊天室存放的值为 , " + LOGIN.toString());
    }

    /** 接收客户端发送过来的方法 */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        
//        log.info("message"+message.getPayload().toString());
//        String storeAccount = (String) session.getAttributes().get(App.Session.STORE_ACCOUNT);
//        log.info(message.getPayload().toString());
//        TextMessage message1 = new TextMessage("9999");
//        sendMessageToSTOREUSER(message1);
    }

//    /** 传输错误时触发 */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {

    }

    /** 连接关闭时触发 */
    @Override
    public void afterConnectionClosed(WebSocketSession session,
            CloseStatus closeStatus) throws Exception {
        removeMapKeyValue(SOCKETS, session);
        removeMapKeyValue(LOGIN, session);
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
    * @param userName storeAccount
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
    
    /**
     * 提示登陆下线消息推送
    * @author 高国藩
    * @date 2016年6月6日 上午10:10:15
    * @param userId   userId
    * @param message  message
     */
    public void loginOutMessageToUser(Integer userId, TextMessage message){
        Set<String> keys = LOGIN.keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()){
            String loginUser = it.next();
            if (loginUser.equals((LOGIN_PREFIX + userId.toString()))) {
                try {
                    if (LOGIN.get(loginUser).isOpen()) {
                        LOGIN.get(loginUser).sendMessage(message);
//                        log.info(userId+" , 通知强制下线,被人给干掉了.");
                    }
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    
    /**
     * 移除map中的值
    * @author 高国藩
    * @date 2016年6月7日 下午4:31:12
    * @param map      map
    * @param session  session
     */
    public void removeMapKeyValue(Map<String, WebSocketSession> map, WebSocketSession session){
        if (map.containsValue(session)){
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                if (map.get(key) == session){
                    iterator.remove();
                    map.remove(key);
                }
            } 
        }
    }
    
}
