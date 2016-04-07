package com.zefun.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.web.dto.ubox.UboxStoreGoodsDto;
import com.zefun.web.service.UboxService;

import net.sf.json.JSONArray;

/**
 * 友宝测试类
* @author 张进军
* @date Jan 28, 2016 7:56:52 PM
 */
public class UboxTest extends BaseTest{
    
    /** 友宝商品信息操作对象 */
    @Autowired
    private UboxService uboxService;
    
    /** 日志记录对象 */
    private static Logger logger = Logger.getLogger(UboxTest.class);
    
    /** 测试门店 */
    private int storeId = 1005;
    
    /**
     * 根据便利柜编码初始化商品信息
    * @author 张进军
    * @date Jan 28, 2016 8:00:21 PM
     */
    @Test
    public void selectGoodsListByStoreId(){
        List<UboxStoreGoodsDto> goodsList = uboxService.getGoodsListByStoreId(storeId);
        logger.info("selectGoodsListByStoreId : " + JSONArray.fromObject(goodsList).toString());
    }
}
