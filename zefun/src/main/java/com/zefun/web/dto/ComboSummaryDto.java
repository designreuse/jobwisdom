package com.zefun.web.dto;

import java.math.BigDecimal;

import com.zefun.web.vo.CardComboSalesVo;
import com.zefun.web.vo.CashComboSalesVo;
import com.zefun.web.vo.DiscountComboSalesVo;

/**
  *@author Administrator
  *@date 2016年1月20日
  *@description 疗程汇总dto
  */
public class ComboSummaryDto {
    
    /**疗程销售额*/
    private BigDecimal comboAmount;
    
    /**疗程销量*/
    private Integer comboCnt;
    
    /**疗程id*/
    private Integer comboId;
    
    /**疗程名字*/
    private String comboName;
    
    /**疗程排行*/
    private Integer comboRank;
    
    /**平均价格*/
    private BigDecimal avgPrice;
    
    /**卡金疗程销售业绩*/
    private CardComboSalesVo cardComboSales;     
    /**现金疗程销售业绩*/
    private CashComboSalesVo cashComboSales;
    /**抵扣疗程销售业绩*/
    private DiscountComboSalesVo discountComboSales;
    
    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    /**上期疗程排行*/
    private String lastRank;
    
    public String getLastRank() {
        return lastRank;
    }

    public void setLastRank(String lastRank) {
        this.lastRank = lastRank;
    }

    /**所属部门id*/
    private Integer deptId;
    
    /**部门名称*/
    private String deptName;

    /**
     * 默认构造函数
    * @author 乐建建
    * @date 2016年1月21日 下午9:09:41 
    */
    public ComboSummaryDto() {
     
    }

    /**
    * @author 乐建建
    * @date 2016年1月21日 下午9:08:31
    * @param comboId 疗程id
    * @param comboName 疗程名字
    * @param comboCnt 疗程销量
    * @param comboAmount 疗程销售额
    * @param deptId 所属部门id
    * @param comboRank  疗程排行
    */
    public ComboSummaryDto(Integer comboId, String comboName, Integer comboCnt,
            BigDecimal comboAmount, Integer deptId, Integer comboRank) {
        this.comboId = comboId;
        this.comboName = comboName;
        this.comboCnt = comboCnt;
        this.comboAmount = comboAmount;
        this.deptId = deptId;
        this.comboRank = comboRank;
    }

    public BigDecimal getComboAmount() {
        return comboAmount;
    }

    public Integer getComboCnt() {
        return comboCnt;
    }

    public Integer getComboId() {
        return comboId;
    }

    public String getComboName() {
        return comboName;
    }

    public Integer getComboRank() {
        return comboRank;
    }

    public Integer getDeptId() {
        return deptId;
    }



    public void setComboAmount(BigDecimal comboAmount) {
        this.comboAmount = comboAmount;
    }

    public void setComboCnt(Integer comboCnt) {
        this.comboCnt = comboCnt;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public void setComboRank(Integer comboRank) {
        this.comboRank = comboRank;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

	public CardComboSalesVo getCardComboSales() {
		return cardComboSales;
	}

	public void setCardComboSales(CardComboSalesVo cardComboSales) {
		this.cardComboSales = cardComboSales;
	}

	public CashComboSalesVo getCashComboSales() {
		return cashComboSales;
	}

	public void setCashComboSales(CashComboSalesVo cashComboSales) {
		this.cashComboSales = cashComboSales;
	}

	public DiscountComboSalesVo getDiscountComboSales() {
		return discountComboSales;
	}

	public void setDiscountComboSales(DiscountComboSalesVo discountComboSales) {
		this.discountComboSales = discountComboSales;
	}

	@Override
    public String toString() {
        return "ComboSummaryDto [comboId=" + comboId + ", comboName="
                + comboName + ", comboCnt=" + comboCnt + ", comboAmount="
                + comboAmount + ", deptId=" + deptId + ", comboRank="
                + comboRank + "]";
    }
}
