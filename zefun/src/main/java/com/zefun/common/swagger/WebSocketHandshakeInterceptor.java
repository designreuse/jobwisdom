package com.zefun.common.swagger;

import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.zefun.common.consts.App;

/**
 * ws 拦截器
* @author 高国藩
* @date 2016年5月23日 下午4:34:38
 */
public class WebSocketHandshakeInterceptor
        extends HttpSessionHandshakeInterceptor {

    /**log*/
    private Logger log = Logger.getLogger(WebSocketHandshakeInterceptor.class);

    
    @Override
    public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1,
                WebSocketHandler arg2, Exception arg3) {
        log.info("enter the afterHandshake");
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse arg1, WebSocketHandler handler,
            Map<String, Object> attribute) throws Exception {
        if (request instanceof ServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            String storeAccount = servletRequest.getURI()
                    .toString().substring(servletRequest.getURI()
                            .toString().indexOf("?")+4, servletRequest.getURI().toString().length());
            attribute.put(App.Session.STORE_ACCOUNT, storeAccount);
//            HttpSession session = servletRequest.getServletRequest().getSession(false);
//            if (session != null) {
//                String userName = (String) session.getAttribute(App.Session.STORE_ACCOUNT);
//                attribute.put(App.Session.STORE_ACCOUNT, userName);
//            }
        }
        return true;
    }
}