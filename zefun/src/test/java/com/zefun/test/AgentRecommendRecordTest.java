package com.zefun.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.web.entity.AgentRecommendRecord;
import com.zefun.web.mapper.AgentRecommendRecordMapper;

/**
 * 
* @author DavidLiang
* @date 2016年1月20日 上午11:41:10
 */
public class AgentRecommendRecordTest extends BaseTest {
	
	/***/
	@Autowired
	private AgentRecommendRecordMapper agentRecommendRecordMapper;

	/***/
	@Test
	public void test() {
		AgentRecommendRecord record = new AgentRecommendRecord();
        record.setAgentId(null);
        record.setRecommendedId(111);
        record.setRecommendId(1111);
        record.setRecommendType(2);
        agentRecommendRecordMapper.insert(record);
	}

}
