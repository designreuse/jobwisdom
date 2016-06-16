package com.zefun.api.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.api.mapper.CouponInfoMapper;

/**
* @author 高国藩
* @date Aug 23, 2015 9:03:36 PM 
*/
public class CouponJob {
    /** 日志对象 */
    private Logger logger = Logger.getLogger(CouponJob.class);
    
    @Autowired
    private CouponInfoMapper couponInfoMapper;
    
    /**
     * 定时器执行内容
    * @author 小高
    * @date Aug 23, 2015 9:04:47 PM
     */
	public void execute() {
	    logger.info("couponInfoMapper execute start... ");
	    try {
	        couponInfoMapper.updateByDate();
        }
        catch (Exception e) {
            logger.error("couponInfoMapper execute error : ", e);
        }
		logger.info("couponInfoMapper execute finish! ");
	}
}

