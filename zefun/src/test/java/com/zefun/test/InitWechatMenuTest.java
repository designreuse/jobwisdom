package com.zefun.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.web.entity.StoreWechat;
import com.zefun.web.mapper.StoreWechatMapper;
import com.zefun.web.service.SystemSettingService;

/**
 * 微信菜单测试类
* @author 张进军
* @date Mar 10, 2016 2:31:25 PM
 */
public class InitWechatMenuTest extends BaseTest {

    /** 系统设置类 */
    @Autowired
    private SystemSettingService settingService;
    
    /** 门店微信设置操作对象 */
    @Autowired
    private StoreWechatMapper storeWechatMapper;
    
    /**
     * 初始化菜单
    * @author 张进军
    * @date Mar 10, 2016 2:33:14 PM
     */
    @Test
    public void initMenu() {
        List<StoreWechat> list = storeWechatMapper.selectAll();
        for (StoreWechat storeWechat : list) {
            if (storeWechat.getStoreId() != 161) {
                continue;
            }
            settingService.storeWechatAction(storeWechat, storeWechat.getStoreId());
        }
    }
    
    
}
