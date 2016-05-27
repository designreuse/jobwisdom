package com.zefun.common.swagger;

import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextListener;

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
        // TODO Auto-generated method stub
        super.requestDestroyed(requestEvent);
    }

    @Override
    public void sessionCreated(HttpSessionEvent e) {
        
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent e) {
        e.getSession().getLastAccessedTime();
        e.getSession().invalidate();
        logger.info("session Destroy and id is :" + e.getSession().getId());
    }
    
}
