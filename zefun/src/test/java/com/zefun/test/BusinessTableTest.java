package com.zefun.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.web.mapper.BusinessReportMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.service.BusinessReporterService;

import net.sf.json.JSONArray;

/**
 * 营业报表相关测试类
* @author 乐建建
* @date 2016年3月3日 下午3:55:32 
*/
public class BusinessTableTest  extends BaseTest{ 
    
    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(ComboInfoTest.class);
    
    /**门店服务接口*/
    @Autowired
    private StoreInfoMapper storeInfoMapper;
    
    /**
     * 统计business的reporter
     * */
    @Autowired
    private BusinessReportMapper reporter;
        
    /**
     * 营业报表服务类
     * */
    @Autowired
    private BusinessReporterService reporterService;
    
    
    /**
    * @author 乐建建
    * @date 2016年3月3日 下午5:31:31 
    */
    @Test
    public void testGetResult(){
        Object obj=reporterService.getResult(1005, "2016-03");
        logger.info(JSONArray.fromObject(obj));
    }
    
    
}
