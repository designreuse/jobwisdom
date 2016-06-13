package com.zefun.web.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import net.sf.json.JSONObject;

/**
 * 流水查询查询条件对象
* @author luhw
*/
public class DayBookQueryDto implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -2072513887328303917L;
	
	/**  */
	public DayBookQueryDto() {
		
	}
	
	/**
	 * 
	 * @param storeId 门店标识
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param isDeleted 删除标识
	 */
	public DayBookQueryDto(Integer storeId, String beginTime, String endTime, Integer isDeleted) {
		this.storeId = storeId;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.isDeleted = isDeleted;
	}
	
	/** 门店标识 */
	private Integer storeId;
	
	/** 流水号或会员手机号 */
	private String queryCode;
	
	/** 开始时间 */
	private String beginTime;
	
	/** 结束时间 */
	private String endTime;
	
	/** 删除标识*/
	private Integer isDeleted;
	
	/** 项目类型*/
	private Integer orderType;
	
	/** 付款方式*/
	private Integer moneyWay;
	
	/** 订单状态*/
	private Integer orderState;
	
	/** 选择部门*/
	private Integer deptId;
	
	/** pageNo */
	private Integer pageNo = 1;
	
	/** pageSize */
	private Integer pageSize = 10;
	
	/** 时间排序，1:升序，2:降序 */
	private Integer timeOrder;
	/** 应收金额排序，1:升序，2:降序 */
	private Integer receivableOrder;
	/** 现金支付排序，1:升序，2:降序 */
	private Integer cashOrder;
	/** 银联支付排序，1:升序，2:降序 */
	private Integer unionpayOrder;
	/** 网络支付排序，1:升序，2:降序 */
	private Integer netpayOrder;
	/** 卡金支付排序，1:升序，2:降序 */
    private Integer cardOrder;
    /** 套餐抵扣排序，1:升序，2:降序 */
    private Integer comboOrder;
    /** 礼金抵扣排序，1:升序，2:降序 */
    private Integer giftOrder;
    /** 优惠券抵扣排序，1:升序，2:降序 */
    private Integer couponOrder;
    /** 团购支付排序，1:升序，2:降序 */
    private Integer grouppayOrder;
    /** 挂账金额排序，1:升序，2:降序 */
    private Integer debtOrder;
    /** 实收金额排序，1:升序，2:降序 */
    private Integer realOrder;
    
    
    

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getMoneyWay() {
		return moneyWay;
	}

	public void setMoneyWay(Integer moneyWay) {
		this.moneyWay = moneyWay;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}


	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getPageNo() {
		return pageNo;
	}
	
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getQueryCode() {
		return queryCode;
	}

	public void setQueryCode(String queryCode) {
		this.queryCode = queryCode;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(Integer timeOrder) {
        this.timeOrder = timeOrder;
    }

    public Integer getReceivableOrder() {
        return receivableOrder;
    }

    public void setReceivableOrder(Integer receivableOrder) {
        this.receivableOrder = receivableOrder;
    }

    public Integer getCashOrder() {
        return cashOrder;
    }

    public void setCashOrder(Integer cashOrder) {
        this.cashOrder = cashOrder;
    }

    public Integer getUnionpayOrder() {
        return unionpayOrder;
    }

    public void setUnionpayOrder(Integer unionpayOrder) {
        this.unionpayOrder = unionpayOrder;
    }

    public Integer getNetpayOrder() {
        return netpayOrder;
    }

    public void setNetpayOrder(Integer netpayOrder) {
        this.netpayOrder = netpayOrder;
    }

    public Integer getCardOrder() {
        return cardOrder;
    }

    public void setCardOrder(Integer cardOrder) {
        this.cardOrder = cardOrder;
    }

    public Integer getComboOrder() {
        return comboOrder;
    }

    public void setComboOrder(Integer comboOrder) {
        this.comboOrder = comboOrder;
    }

    public Integer getGiftOrder() {
        return giftOrder;
    }

    public void setGiftOrder(Integer giftOrder) {
        this.giftOrder = giftOrder;
    }

    public Integer getCouponOrder() {
        return couponOrder;
    }

    public void setCouponOrder(Integer couponOrder) {
        this.couponOrder = couponOrder;
    }

    public Integer getGrouppayOrder() {
        return grouppayOrder;
    }

    public void setGrouppayOrder(Integer grouppayOrder) {
        this.grouppayOrder = grouppayOrder;
    }

    public Integer getDebtOrder() {
        return debtOrder;
    }

    public void setDebtOrder(Integer debtOrder) {
        this.debtOrder = debtOrder;
    }

    public Integer getRealOrder() {
        return realOrder;
    }

    public void setRealOrder(Integer realOrder) {
        this.realOrder = realOrder;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
    	return JSONObject.fromObject(this).toString();
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
    
}
