package com.zefun.common.swagger;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextListener;
import com.zefun.common.consts.App;

/**
 * session容器实例化
* @author 高国藩
* @date 2016年5月27日 上午9:54:06
 */
public class SessionContextListener extends RequestContextListener implements HttpSessionListener {
    
    /** 日志 */
    private Logger logger = Logger.getLogger(SessionContextListener.class);

    
    @Override
    public void requestInitialized(ServletRequestEvent requestEvent) {
        // TODO Auto-generated method stub
        super.requestInitialized(requestEvent);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent requestEvent) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("localAddr", requestEvent.getServletRequest().getLocalAddr());
        params.put("remoteAddr", requestEvent.getServletRequest().getRemoteAddr());
        super.requestDestroyed(requestEvent);
    }

    @Override
    public void sessionCreated(HttpSessionEvent e) {
        
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent e) {
        try {
            ServletContext application  = e.getSession().getServletContext();
            application.removeAttribute(e.getSession().getAttribute(App.Session.USER_ID).toString());
            logger.info("{" + e.getSession().getId() + " : invalidate()}");
            e.getSession().invalidate();
        } 
        catch (Exception e2) {
            logger.info("sessionDestroyed : session has bean destroyed");
        }
    }
    
}
