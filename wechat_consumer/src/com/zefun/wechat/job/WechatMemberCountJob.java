package com.zefun.wechat.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.web.mapper.MemberAccountMapper;

/**
* @author 高国藩
* @date Aug 23, 2015 9:03:36 PM 
*/
public class WechatMemberCountJob {
    /** 日志对象 */
    private Logger logger = Logger.getLogger(WechatMemberCountJob.class);
    
    @Autowired
    private MemberAccountMapper memberAccountMapper;
    
    /**
     * 定时器执行内容
    * @author 高国藩
    * @date Aug 23, 2015 9:04:47 PM
     */
	public void execute() {
	    logger.info("WechatMemberCountJob execute start... ");
	    try {
	        memberAccountMapper.updateWechatCount();
        } catch (Exception e) {
            logger.info("WechatMemberCountJob execute Exception! ");
        }
		logger.info("WechatMemberCountJob execute finish! ");
	}
}

