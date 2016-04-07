package com.zefun.web.dto;

import java.util.List;

/**
 * 营业汇总趋势数据封装类
* @author 乐建建
* @date 2016年2月18日 下午9:08:54 
*/
public class BusinessSummaryTrend {
    
    /**营业扣减*/
    private List<BusinessSummaryRelativeAmt> businessExpense;    
    /**刷卡消费*/
    private List<BusinessSummaryRelativeAmt> cardConsumedExpense;
    /**营业收入*/
    private List<BusinessSummaryRelativeAmt> incomes;
    /**营业实际收入*/
    private List<BusinessSummaryRelativeAmt> realIncomes;
    public List<BusinessSummaryRelativeAmt> getBusinessExpense() {
        return businessExpense;
    }
    public List<BusinessSummaryRelativeAmt> getCardConsumedExpense() {
        return cardConsumedExpense;
    }
    
    public List<BusinessSummaryRelativeAmt> getIncomes() {
        return incomes;
    }
    public List<BusinessSummaryRelativeAmt> getRealIncomes() {
        return realIncomes;
    }
    public void setBusinessExpense(List<BusinessSummaryRelativeAmt> businessExpense) {
        this.businessExpense = businessExpense;
    }
    
    public void setCardConsumedExpense(
            List<BusinessSummaryRelativeAmt> cardConsumedExpense) {
        this.cardConsumedExpense = cardConsumedExpense;
    }
    public void setIncomes(List<BusinessSummaryRelativeAmt> incomes) {
        this.incomes = incomes;
    }
    public void setRealIncomes(List<BusinessSummaryRelativeAmt> realIncomes) {
        this.realIncomes = realIncomes;
    }
}
