package com.zefun.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  
import com.rabbitmq.client.MessageProperties;

import net.sf.json.JSONObject; 


/**
 * 开卡充值Test
* @author 王大爷
* @date 2015年8月11日 下午3:52:35
 */
@Component
public class RabbitServerTest extends BaseTest{
    /**日志*/
    public static Logger log = Logger.getLogger(RabbitServerTest.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Test
    public void send(){
        log.info("Publish message --> routingKey : " + "routingKey" + ", Message : " + JSONObject.fromObject("{'name':1}"));
        rabbitTemplate.convertAndSend("routingKey", JSONObject.fromObject("{'name':1}"));
    }
    
    private static final String TASK_QUEUE_NAME = "task_queue";  
    
    public static void main(String[] argv) throws Exception {  
  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("120.24.165.15");
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        //声明此队列并且持久化  
        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);  
  
        String message = getMessage(argv);  
  
        channel.basicPublish("", TASK_QUEUE_NAME,  
                MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());//持久化消息  
        System.out.println(" [x] Sent '" + message + "'");  
  
        channel.close();  
        connection.close();  
    }  
  
    private static String getMessage(String[] strings) {  
        if (strings.length < 1)  
            return "Hello World!";  
        return joinStrings(strings, " ");  
    }  
    
    private static String joinStrings(String[] strings, String delimiter) {  
        int length = strings.length;  
        if (length == 0)  
            return "";  
        StringBuilder words = new StringBuilder(strings[0]);  
        for (int i = 1; i < length; i++) {  
            words.append(delimiter).append(strings[i]);  
        }  
        return words.toString();  
    }  
    
}