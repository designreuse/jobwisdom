package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.List;

/**
* 门店对账封装类
* @author 乐建建
* @date 2016年3月1日 下午4:01:46 
*/
public class StoreReconciliation {
    
    /**出账金额*/
    private BigDecimal chargeOffAmt;
    
    /**出账状态*/
    private Integer chargeOffState;
    
    /**本店会员到他店消费数据*/
    private DifferentStoreMemberConsumeDto meToOther;
    
    @Override
    public String toString() {
        return "StoreReconciliation [chargeOffAmt=" + chargeOffAmt
                + ", chargeOffState=" + chargeOffState + ", meToOther="
                + meToOther + ", meToOtherMembers=" + meToOtherMembers
                + ", otherToMe=" + otherToMe + ", otherToMeMebers="
                + otherToMeMebers + ", storeId=" + storeId + ", storeName="
                + storeName + "]";
    }

    /**本店会员到他店消费的会员id*/
    private List<Integer> meToOtherMembers;
    
    /**他店会员到本店消费数据*/
    private DifferentStoreMemberConsumeDto otherToMe;
    
    /**他店会员到本店消费的会员id*/
    private List<Integer> otherToMeMebers;
    /**门店id*/
    private Integer storeId;

    /**门店名称*/
    private String storeName;

    public BigDecimal getChargeOffAmt() {
        return chargeOffAmt;
    }

    public Integer getChargeOffState() {
        return chargeOffState;
    }

    public DifferentStoreMemberConsumeDto getMeToOther() {
        return meToOther;
    }
    public List<Integer> getMeToOtherMembers() {
        return meToOtherMembers;
    }
    public DifferentStoreMemberConsumeDto getOtherToMe() {
        return otherToMe;
    }
    public List<Integer> getOtherToMeMebers() {
        return otherToMeMebers;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setChargeOffAmt(BigDecimal chargeOffAmt) {
        this.chargeOffAmt = chargeOffAmt;
    }

    public void setChargeOffState(Integer chargeOffState) {
        this.chargeOffState = chargeOffState;
    }

    public void setMeToOther(DifferentStoreMemberConsumeDto meToOther) {
        this.meToOther = meToOther;
    }

    public void setMeToOtherMembers(List<Integer> meToOtherMembers) {
        this.meToOtherMembers = meToOtherMembers;
    }

    public void setOtherToMe(DifferentStoreMemberConsumeDto otherToMe) {
        this.otherToMe = otherToMe;
    }

    public void setOtherToMeMebers(List<Integer> otherToMeMebers) {
        this.otherToMeMebers = otherToMeMebers;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
