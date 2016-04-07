package com.lives.platform.test.base;


import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.lives.platform.web.contorller.ProjectController;

public class ProjectTest extends BaseSpringTestCase {

	// 模拟request,response  
    private MockHttpServletRequest request;  
    private MockHttpServletResponse response;   
      
    @Autowired private ProjectController projectController ; 
    
    // 执行测试方法之前初始化模拟request,response  
    @Before    
    public void setUp(){    
        request = new MockHttpServletRequest();      
        request.setCharacterEncoding("UTF-8");      
        response = new MockHttpServletResponse();      
    }    
    
    @Test
    public void testProject(){
//    	projectController.addProjectPlateSave("美容板块", 1, 1);
    }
}
