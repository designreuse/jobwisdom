package com.zefun.web.dto;

import com.zefun.web.entity.CrossShopAccount;

/**
 * 他店到本店划卡消费封装数据
 * 使用组合类进行数据拼装
* @author 乐建建
* @date 2016年3月1日 下午3:20:19 
*/
public class DifferentStoreCardConsumeDto {
    
    /**订单明细数据*/
    private ReconciliationOrderDetailDto orderDetail;
    
    /**跨店对账明细类*/
    private CrossShopAccount shopAccount;

    public ReconciliationOrderDetailDto getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(ReconciliationOrderDetailDto orderDetail) {
        this.orderDetail = orderDetail;
    }

    public CrossShopAccount getShopAccount() {
        return shopAccount;
    }

    public void setShopAccount(CrossShopAccount shopAccount) {
        this.shopAccount = shopAccount;
    }
    
    
    
}
