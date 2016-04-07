package com.lives.platform.test.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/** 
* 配置文件载入类  
* @ClassName: BaseSpringTestCase  
* @Description: 要想实现Spring自动注入，必须继承此类 
* @author yusj   
* @date 2014年6月9日 下午3:16:44  
* 
 */  
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({  
    "file:src/main/resources/spring/spring-context.xml",  
    "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"
})  
  
// 添加注释@Transactional 回滚对数据库操作    
@Transactional  
public class BaseSpringTestCase {  
      
} 
