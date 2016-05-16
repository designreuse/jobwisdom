package com.zefun.wechat;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;


public class AppMain {

    private static final Logger logger = Logger.getLogger(AppMain.class);

    public void start() {
        ClassPathXmlApplicationContext context = null;
        try {
        	context = new ClassPathXmlApplicationContext("classpath:app.xml");
        } catch (Exception e) {
        	logger.error("An error occurred, applicationContext will close.", e);
        	if (context != null) {
        		context.close();
        	}
        	context = null;
        	logger.error("#################closed####################");
        }
        logger.info("wechat_consumer is started...");
    }

    public static void main(String[] args) {
    	new AppMain().start();
    }
    

}
