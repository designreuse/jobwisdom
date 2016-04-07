package com.lives.manage.web.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.lives.manage.web.entity.User;
import com.lives.manage.web.mapper.UserMapper;

@Component
public class Test {
	@Autowired
	private UserMapper userMapper;

	public static void main(String[] args) {

		ApplicationContext app = new ClassPathXmlApplicationContext("spring/spring-context.xml");
		Test t = app.getBean(Test.class);
		User user = new User();
		user.setUserName("admin");
		user.setPassWord("123456");
		int i = t.userMapper.insert(user);
		System.out.println(i);
	}
}
