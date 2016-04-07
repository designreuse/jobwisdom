package com.zefun.api.job;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.api.mapper.OrderDetailMapper;
import com.zefun.api.service.CashierOrderService;
import com.zefun.api.service.RedisService;
import com.zefun.api.utils.App;


public class AgainCalculateorderJob {

    /** 日志对象 */
    private Logger logger = Logger.getLogger(AgainCalculateorderJob.class);
    
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    
    @Autowired
    private CashierOrderService cashierOrderService;
    
    @Autowired
    private RedisService redisService;
    
    public void execute() {
        logger.info("ObjectiveJob execute start... ");
        List<Integer> orderList = orderDetailMapper.selectOrderId();
        for (Integer orderId : orderList) {
            try {
                cashierOrderService.insertCashierCommission(orderId);
            } catch (Exception e) {
                redisService.sadd(App.Redis.ERROR_ORDER_ID_MESSAGE_STRING, orderId);
            }
        }
    }
}
