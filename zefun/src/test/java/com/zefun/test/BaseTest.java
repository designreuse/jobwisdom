package com.zefun.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * 配置文件载入类，所有测试类须继承此类
 * @author 张进军
 * @date Aug 5, 2015 4:27:31 PM 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml", "classpath*:spring/spring-context.xml" })
@WebAppConfiguration
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
// 不改变数据库数据
public class BaseTest {

    /** mockMvc 测试api*/
    public MockMvc mockMvc;
    /** request 作为参数传入*/
    public MockHttpServletRequest request;
    /** response 作为参数传入*/
    public MockHttpServletResponse response;
    /** 上下文*/
    @Autowired protected WebApplicationContext webApplicationContext;
}
