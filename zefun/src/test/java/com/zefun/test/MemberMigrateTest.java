package com.zefun.test;

import java.io.File;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.web.service.MemberMigrateService;

/**
 * 会员数据移植处理类测试类
* @author 张进军
* @date Mar 21, 2016 10:27:57 PM
 */
public class MemberMigrateTest extends BaseTest {

    /** 会员数据移植处理服务对象 */
    @Autowired
    private MemberMigrateService memberMigrateService;
    
    /** 数据导入测试 */
    @Test
    public void importTest(){
        memberMigrateService.maigrateForExcel(new File("/Users/smallpang/Downloads/huacai_test.xlsx"), 4, 1120);
    }
}
