package com.zefun.web.app;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * java jvm spring 启动
 * 注册:WebApplicationContext 和 DispatcherServlet 两个上下文
* @author 高国藩
* @date 2016年4月19日 下午4:58:44
 */
public class WebAppInitializer implements WebApplicationInitializer  {

    /***
     * log
     */
    private Logger log = Logger.getLogger(WebAppInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {
        
        @SuppressWarnings("resource")
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(ApplicationContextConfig.class);
        ctx.setServletContext(servletContext);
        
        log.info("########## Java spring jvm init has bean covers {" + WebApplicationContext.class.toString() + "}" + " ##########");
        log.info("########## Java spring jvm init has bean covers {" + DispatcherServlet.class.toString() + "}" + " ##########");
        
    }

}
