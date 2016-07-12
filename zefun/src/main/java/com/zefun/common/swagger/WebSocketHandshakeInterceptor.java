package com.zefun.common.swagger;

import java.util.HashMap;
import java.util.Map;

//import javax.servlet.http.HttpSession;

//import org.apache.log4j.Logger;
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
//    private Logger log = Logger.getLogger(WebSocketHandshakeInterceptor.class);

    @Override
    public void afterHandshake(ServerHttpRequest request,
            ServerHttpResponse arg1, WebSocketHandler arg2, Exception arg3) {
//        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
//        HttpSession session = servletRequest.getServletRequest().getSession(false);
//        if (session != null) {
//            String userName = (String) session.getAttribute(App.Session.STORE_ACCOUNT);
//            log.info(userName);
//        }
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
            ServerHttpResponse arg1, WebSocketHandler handler,
            Map<String, Object> attribute) throws Exception {
//        ServletServerHttpRequest servletRequest2 = (ServletServerHttpRequest) request;
//        HttpSession session = servletRequest2.getServletRequest().getSession(false);
//        if (session != null) {
//            String userName = (String) session.getAttribute(App.Session.STORE_ACCOUNT);
//            log.info(userName);
//        }
        /** 在拦截器内强行修改websocket协议，将部分浏览器不支持的 x-webkit-deflate-frame 扩展修改成 permessage-deflate */
        if (request.getHeaders().containsKey("Sec-WebSocket-Extensions")){
            request.getHeaders().set("Sec-WebSocket-Extensions", "permessage-deflate");
        }
        if (request instanceof ServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            Map<String, String> mapRequest = urlRequest(servletRequest.getURI().toString());
            for (String strRequestKey: mapRequest.keySet()) {
                String strRequestValue = mapRequest.get(strRequestKey);
                switch (strRequestKey) {
                    case "storeaccount":
                        attribute.put(App.Session.STORE_ACCOUNT, strRequestValue);
                        break;
                    case "userid":
                        attribute.put(App.Session.USER_ID, strRequestValue);
                        break;
                    default:
                        break;
                }
            }
        }
        return true;
    }

    /**
    * 解析出url请求的路径，包括页面
    * @author 高国藩
    * @date 2016年6月6日 上午10:20:09
    * @param strURL  strURL
    * @return        strURL
    */
    public static String urlPage(String strURL) {
        String strPage = null;
        String[] arrSplit = null;

        strURL = strURL.trim().toLowerCase();

        arrSplit = strURL.split("[?]");
        if (strURL.length() > 0) {
            if (arrSplit.length > 1) {
                if (arrSplit[0] != null) {
                    strPage = arrSplit[0];
                }
            }
        }
        return strPage;
    }

    /**
    * 去掉url中的路径，留下请求参数部分
    * @param strURL url地址
    * @return url请求参数部分
    */
    private static String truncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;
        strURL = strURL.trim().toLowerCase();

        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }
        return strAllParam;
    }

    /**
    * 解析出url参数中的键值对
    * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
    * @param URL  url地址
    * @return  url请求参数部分
    */
    public static Map<String, String> urlRequest(String URL) {
        Map<String, String> mapRequest = new HashMap<String, String>();

        String[] arrSplit = null;

        String strUrlParam = truncateUrlPage(URL);
        if (strUrlParam == null) {
            return mapRequest;
        }
        // 每个键值为一组 www.2cto.com
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");
            // 解析出键值
            if (arrSplitEqual.length > 1) {
                // 正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            } 
            else {
                if (arrSplitEqual[0] != "") {
                    // 只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

}