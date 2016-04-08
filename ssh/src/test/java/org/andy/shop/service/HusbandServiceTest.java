package org.andy.shop.service;

import org.andy.shop.entity.Husband;
import org.andy.shop.entity.UserInfo;
import org.andy.shop.entity.Wife;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

/**
 * 创建时间：2015-2-13 下午3:31:07
 * 
 * @author andy
 * @version 2.2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:spring-hibernate.xml" })
public class HusbandServiceTest {

	private static final Logger LOGGER = Logger
			.getLogger(HusbandServiceTest.class);

	@Autowired
	private HusbandService husbandService;

	@Test
	public void save() {
	    Husband husband = new Husband("小高2", 4, null);
		Wife wife= new Wife();
		wife.setName("美女");
		wife.setNum(23);
		wife.setHusband(husband);
		Integer ok = husbandService.save(husband);
		System.out.println(ok);
	}

    @Test
    public void view() {
        husbandService.get(1);
    }
}
