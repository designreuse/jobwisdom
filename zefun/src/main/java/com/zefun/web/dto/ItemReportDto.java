package com.zefun.web.dto;

import java.math.BigDecimal;

import com.zefun.web.vo.CardStoreSalesVo;
import com.zefun.web.vo.CashStoreSalesVo;


/**
* @author 乐建建
* @date 2016年1月18日 上午11:17:41 
*/
public class ItemReportDto {
    /**商品销量*/
	private Integer goodCount;
	/**商品id*/
	private Integer goodId;
    /**商品销售收入*/
	private BigDecimal goodIncome;
	/**商品名称*/
	private String goodName;
	/**商品销量dto*/
	private GoodSalesDto goodSales;
	/**项目销量*/
	private Integer projectCount;
	/**项目id*/
	private Integer projectId;
	/**项目收入*/
	private BigDecimal projectIncome;
	/**项目名称*/
	private String projectName;
	/**项目销量dto*/
	private ProjectSalesDto projectSales;
	/**现金消费业绩*/
    private CashStoreSalesVo cashStoreSales;
    
    /**卡金消费业绩*/
    private CardStoreSalesVo cardStoreSales;
    /**
    * @author 乐建建
    * @date 2016年1月18日 下午8:21:44 
    */
    public ItemReportDto() {
        super();
    }
    /**
	* @author 乐建建
	* @date 2016年1月18日 下午8:16:13
	* @param projectId 项目id
	* @param projectIncome 项目收入
	* @param projectName 项目名称
	* @param projectCount 项目销量
	*/
	public ItemReportDto(Integer projectId, BigDecimal projectIncome,
            String projectName, Integer projectCount) {
        super();
        this.projectId = projectId;
        this.goodId=projectId;
        this.projectIncome = projectIncome;
        this.goodIncome=projectIncome;
        this.goodCount=projectCount;
        this.goodName=projectName;
        this.projectName = projectName;
        this.projectCount = projectCount;
    }
    public Integer getGoodCount() {
        return goodCount;
    }
    public Integer getGoodId() {
        return goodId;
    }
    public BigDecimal getGoodIncome() {
        return goodIncome;
    }
    public String getGoodName() {
        return goodName;
    }
    public GoodSalesDto getGoodSales() {
        return goodSales;
    }
    public Integer getProjectCount() {
        return projectCount;
    }
    public Integer getProjectId() {
		return projectId;
	}
    public BigDecimal getProjectIncome() {
		return projectIncome;
	}
	
	public String getProjectName() {
		return projectName;
	}
    public ProjectSalesDto getProjectSales() {
		return projectSales;
	}
	public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }
    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }
    public void setGoodIncome(BigDecimal goodIncome) {
        this.goodIncome = goodIncome;
    }
	public void setGoodName(String goodName) {
        this.goodName = goodName;
    }
	public void setGoodSales(GoodSalesDto goodSales) {
        this.goodSales = goodSales;
    }
	public void setProjectCount(Integer projectCount) {
        this.projectCount = projectCount;
    }
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public void setProjectIncome(BigDecimal projectIncome) {
		this.projectIncome = projectIncome;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public void setProjectSales(ProjectSalesDto projectSales) {
		this.projectSales = projectSales;
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
