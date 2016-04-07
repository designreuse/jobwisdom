package com.zefun.web.dto.ubox;

import com.zefun.common.utils.DateUtil;
import com.zefun.web.entity.UboxMachineInfo;
import com.zefun.web.entity.UboxTransaction;

/**
 * 友宝交易信息传输对象
* @author 张进军
* @date Mar 5, 2016 9:33:44 AM
 */
public class UboxTransactionDto extends UboxTransaction {
    /** 商品信息 */
    private UboxStoreGoodsDto goodsInfo;
    
    /** 机器信息 */
    private UboxMachineInfo machineInfo;

    public String getPickupExpireTime() {
        return DateUtil.getDateStrByTimeMillis(new Long(getExpireTime()) * 1000);
    }

    public UboxStoreGoodsDto getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(UboxStoreGoodsDto goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public UboxMachineInfo getMachineInfo() {
        return machineInfo;
    }

    public void setMachineInfo(UboxMachineInfo machineInfo) {
        this.machineInfo = machineInfo;
    }
    
}
