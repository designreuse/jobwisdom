package com.zefun.test;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/** 
 * 本类测试简单邮件 直接用邮件发送 
 *  
 * @author Administrator 
 *  
 */
public class SingleMailSend {

    /**
     * 发送邮件
    * @author 高国藩
    * @date 2016年5月3日 下午4:13:46
     */
    public void snm() {

        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        // 设定mail server
        senderImpl.setHost("mail.qq.com");
        // senderImpl.setPort(587);

        // 建立邮件消息
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人 用数组发送多个邮件
        // String[] array = new String[] {"sun111@163.com","sun222@sohu.com"};
        // mailMessage.setTo(array);
        mailMessage.setTo("job1@jobwisdom.cn");
        mailMessage.setFrom("772846384@qq.com");
        mailMessage.setSubject("测试简单文本邮件发");
        mailMessage.setText("测试我的简单邮件发送机制");

        senderImpl.setUsername("772846384@qq.com"); // 根据自己的情况,设置username
        senderImpl.setPassword("nbuqenulxrtabdic"); // 根据自己的情况, 设置授权吗

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        prop.put("mail.smtp.timeout", "25000");
        senderImpl.setJavaMailProperties(prop);
        // 发送邮件
        senderImpl.send(mailMessage);

    }
}