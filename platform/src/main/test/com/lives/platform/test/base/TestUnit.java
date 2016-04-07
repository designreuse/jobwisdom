package com.lives.platform.test.base;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.lives.platform.common.exception.ServiceException;
import com.lives.platform.web.contorller.VipController;

public class TestUnit extends BaseSpringTestCase{
	// 模拟request,response  
    private MockHttpServletRequest request;  
    // 注入userController  
    @Autowired  
    private VipController vipController ; 
    
    // 执行测试方法之前初始化模拟request,response  
    @Before    
    public void setUp(){    
        request = new MockHttpServletRequest();      
        request.setCharacterEncoding("UTF-8");      
        new MockHttpServletResponse();      
    }    
      
    /** 
     *  
     * @Title：testLogin 
     * @Description: 测试用户登录   
     * @author yusj    
     * @date 2014年5月18日 
     */  
<<<<<<< HEAD
    @Test  
    public void testLogin() {  
        try {
        	vipController.vipInfo();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
    }	
=======
//    @Test  
//    public void testLogin() {  
//        try {
////			userController.test();
//		} catch (ServiceException e) {
//			e.printStackTrace();
//		}
//    }	
>>>>>>> 45e227619a2740a0360b0f5db18df51630500c84
}
