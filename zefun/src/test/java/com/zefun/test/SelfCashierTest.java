package com.zefun.test;


import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.zefun.web.controller.DayBookController;
import com.zefun.web.service.RabbitService;
import com.zefun.web.service.SelfCashierService;


/**
 * 自助收银签退
* @author luhw
* @date 2015年10月21日 下午16:19:19
 */
public class SelfCashierTest extends BaseTest{
    
	/**  */
    @Autowired
    private SelfCashierService selfCashierService;
    
    /** */
    @Autowired 
    private DayBookController dayBookController;
    
    /**  */
    @Autowired
    private RabbitService rabbitService;
    
    /**
     * request
     */
    @Mock MockHttpServletRequest request = new MockHttpServletRequest();
    
    /**
     * response
     */
    @Mock MockHttpServletResponse response = new MockHttpServletResponse();

    /**
     * 日志
     */
    @SuppressWarnings("unused")
    private Logger logger = Logger.getLogger(SelfCashierTest.class);
    
    /**
     * 
     */
    @Test
    public void testRabbitService(){
    	rabbitService.sendCashierOrderComission(9660);
    }
    
    /**
     * 
    * @author 王大爷
    * @date 2016年1月8日 下午5:36:39
     */
    @Test
    public void testUpdatRabbitService(){
        rabbitService.sendCashierUpdateOrderCommission(2050);
    }
    
    /**
     * 
    * @author 王大爷
    * @date 2015年12月24日 下午3:10:47
     */
    @Test
    public void dayBookController() {
        dayBookController.selectOrderByUpdate(request, response, 3205);
    }
}
