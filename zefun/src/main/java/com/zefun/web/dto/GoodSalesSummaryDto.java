package com.zefun.web.dto;

import java.math.BigDecimal;

import com.zefun.web.vo.CardStoreSalesVo;
import com.zefun.web.vo.CashStoreSalesVo;

/**
* @author 乐建建
* @date 2016年1月22日 下午7:39:31
* 商品销售汇总dto 
*/
public class GoodSalesSummaryDto {
    
    @Override
    public String toString() {
        return "GoodSalesSummaryDto [avgGoodPrice=" + avgGoodPrice
                + ", deptName=" + deptName + ", goodAmount=" + goodAmount
                + ", goodBelongToDeptId=" + goodBelongToDeptId + ", goodCnt="
                + goodCnt + ", goodId=" + goodId + ", goodName=" + goodName
                + ", goodRank=" + goodRank + ", lastRank=" + lastRank + "]";
    }

    /**平均购买单价*/
    private BigDecimal avgGoodPrice;

    /**部门名称*/
    private String deptName;
    
    /**商品销售总额*/
    private BigDecimal goodAmount;
    
    /**商品所属部门*/
    private Integer goodBelongToDeptId;
    
    /**商品销售数量*/
    private Integer goodCnt;

    /**商品id*/
    private Integer goodId;

    /**商品名字*/
    private String goodName;
    
    /**商品销售额排名*/
    private Integer goodRank;
    
    /**商品销售额上期排名*/
    private String lastRank;
    
    /**现金消费业绩*/
    private CashStoreSalesVo cashStoreSales;
    
    /**卡金消费业绩*/
    private CardStoreSalesVo cardStoreSales;
    
    /**
    * @author 乐建建
    * @date 2016年1月23日 下午3:54:31 
    * 默认构造函数
    */
    public GoodSalesSummaryDto() {
        
    }
    
    /**
    * @author 乐建建
    * @date 2016年1月23日 下午2:05:19
    * @param goodId 商品id
    * @param name 商品名称
    * @param deptId 部门id
    * @param deptCnt 商品数量
    * @param deptSum 部门总销售额
    * @param avg 平均购买单价
    */
    public GoodSalesSummaryDto(Integer goodId, String name, Integer deptId,
            Integer deptCnt, BigDecimal deptSum, BigDecimal avg) {
        this.goodId=goodId;
        this.goodName=name;
        this.goodBelongToDeptId=deptId;
        this.goodCnt=deptCnt;
        this.goodAmount=deptSum;
        this.avgGoodPrice=avg;
    }
    
    public BigDecimal getAvgGoodPrice() {
        return avgGoodPrice;
    }

    public String getDeptName() {
        return deptName;
    }

    public BigDecimal getGoodAmount() {
        return goodAmount;
    }

    public Integer getGoodBelongToDeptId() {
        return goodBelongToDeptId;
    }

    public Integer getGoodCnt() {
        return goodCnt;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public Integer getGoodRank() {
        return goodRank;
    }

    public String getLastRank() {
        return lastRank;
    }

    public void setAvgGoodPrice(BigDecimal avgGoodPrice) {
        this.avgGoodPrice = avgGoodPrice;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setGoodAmount(BigDecimal goodAmount) {
        this.goodAmount = goodAmount;
    }

    public void setGoodBelongToDeptId(Integer goodBelongToDeptId) {
        this.goodBelongToDeptId = goodBelongToDeptId;
    }

    public void setGoodCnt(Integer goodCnt) {
        this.goodCnt = goodCnt;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public void setGoodRank(Integer goodRank) {
        this.goodRank = goodRank;
    }

    public void setLastRank(String lastRank) {
        this.lastRank = lastRank;
    }

	public CashStoreSalesVo getCashStoreSales() {
		return cashStoreSales;
	}

	public void setCashStoreSales(CashStoreSalesVo cashStoreSales) {
		this.cashStoreSales = cashStoreSales;
	}

	public CardStoreSalesVo getCardStoreSales() {
		return cardStoreSales;
	}

	public void setCardStoreSales(CardStoreSalesVo cardStoreSales) {
		this.cardStoreSales = cardStoreSales;
	}
    
    
}
