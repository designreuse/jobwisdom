package com.web.JunitSpringTest.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.zefun.web.controller.WechatController;
import com.zefun.web.service.RedisService;

/**
 * 单元测试类
* @author 高国藩
* @date 2015年8月12日 下午5:38:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml",
        "classpath*:spring/spring-context.xml" })
@WebAppConfiguration
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class BaseTest {
    @Autowired
    private WechatController wechatController;
    @Autowired
    private RedisService redisService;
    @Autowired
    private WebApplicationContext wac;
    public MockMvc mockMvc;
    @Before  
    public void setUp() {  
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();  
    }  
    @Test
    public void TestContoller() throws IOException {
        wechatController.test(null, null);
    }
    
    @Test  
    public void testView() throws Exception {  
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/wechat/test"))
                .andExpect(MockMvcResultMatchers.view().name("wechat/test"))  
                .andExpect(MockMvcResultMatchers.model().attributeExists("userName"))  
                .andDo(MockMvcResultHandlers.print())  
                .andReturn();  
        Assert.assertNotNull(result.getModelAndView().getModel().get("userName"));  
    }  
    
    @Test
    public void testRedis(){
        Set<String> set = redisService.smembers("1");
        List<String> ls = new ArrayList<String>(set);
        System.out.println("sd");
        System.out.println(ls.toString());
    }
    
}
