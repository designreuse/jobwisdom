package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zefun.web.entity.StoreInfo;

/**
* 营业汇总dto
* @author 乐建建
* @date 2016年1月30日 下午6:12:38 
*/
public class BusinessSummaryDto extends SummaryResultDto{
    
    /**营业扣减汇总*/
    private BigDecimal businessExpense;
    /**营业收入增长率*/
    private BigDecimal businessIncomeIncrementRate;
    /**营业收入汇总*/
    private BigDecimal businessIncomes;
    /**营业实收*/
    private BigDecimal businessRealIncome;
    /**部门汇总数据*/
    private List<DeptSummaryDto> deptSummary;
    /**上期营业实收*/
    private BigDecimal lastBusinessRealIncome;
    /**部门汇总趋势数据*/
    private BusinessSummaryTrend trend;
    /**部门汇总趋势封装数据*/
    private List<BusinessSummaryPage> trends;
    
    /**
    * @author 乐建建
    * @date 2016年2月18日 下午2:12:26 
    * 默认构造函数
    */
    public BusinessSummaryDto() {
    }
    public List<BusinessSummaryPage> getTrends() {
        return trends;
    }
    public void setTrends(List<BusinessSummaryPage> trends) {
        this.trends = trends;
    }
    /**
    * @author 乐建建
    * @date 2016年2月18日 下午2:13:13
    * @param stores 分店列表
    * @param businessIncomes 营业收入
    * @param businessExpense 营业扣减
    * @param businessRealIncome 营业纯收入
    * @param businessIncomeIncrementRate 营业收入增长率
    * @param deptSummary 该门店产生业绩的部门汇总数据
    * @param trend 趋势数据
    * @param lastDeptSummary 门店上期各部门的汇总数据
    * @param begin 开始时间
    * @param end 结束时间
    * @param dateType 日期类型
    * @param storeId 门店id
    */
    public BusinessSummaryDto(List<StoreInfo> stores, BigDecimal businessIncomes,
            BigDecimal businessExpense, BigDecimal businessRealIncome,
            BigDecimal businessIncomeIncrementRate,
            List<DeptSummaryDto> deptSummary,
            BusinessSummaryTrend trend,
            List<DeptSummaryDto> lastDeptSummary, String begin, String end, Integer dateType,
            Integer storeId) {
        this.businessIncomes = processData(deptSummary, 1);
        this.businessExpense = processData(deptSummary, 2);
        this.businessRealIncome = processData(deptSummary, 3);
        this.lastBusinessRealIncome= processData(lastDeptSummary, 3);
        this.businessIncomeIncrementRate = computeRate(this.businessRealIncome, this.lastBusinessRealIncome);
        this.deptSummary = deptSummary;
        this.trend=trend;
        if (dateType!=null && (dateType==2 || dateType==3 || dateType==4)){
            this.trends=computeTrend(this.trend);
        }        
        this.dateType=dateType;
        this.begin=begin;
        this.end=end;
        this.branchStores=stores;
        this.storeId=storeId;
    }
    
    /**
    * @author 乐建建
    * @date 2016年2月19日 下午7:56:41
    * @param trend 趋势数据
    * @return  封装后的数据
    */
    private List<BusinessSummaryPage> computeTrend(
            BusinessSummaryTrend trend) {
        Map<String, BusinessSummaryPage> temp=new HashMap<String, BusinessSummaryPage>();
        List<BusinessSummaryRelativeAmt> expense= trend.getBusinessExpense();
        List<BusinessSummaryRelativeAmt> incomes=trend.getIncomes();
        List<BusinessSummaryRelativeAmt> realIncome=trend.getRealIncomes();
        for (int i=0; i< expense.size(); i++){
            BusinessSummaryPage bsp=new BusinessSummaryPage();
            bsp.setDate(expense.get(i).getDate());
            bsp.setExpenses(expense.get(i).getTotalAmt());
            temp.put(expense.get(i).getDate(), bsp);
        }
        for (int i=0; i< incomes.size(); i++){
            BusinessSummaryPage bsp=temp.get(incomes.get(i).getDate());
            bsp.setIncomes(incomes.get(i).getTotalAmt());
            temp.put(incomes.get(i).getDate(), bsp);
        }
        for (int i=0; i< realIncome.size(); i++){
            BusinessSummaryPage bsp=temp.get(realIncome.get(i).getDate());
            bsp.setRealIncomes(realIncome.get(i).getTotalAmt());
            temp.put(realIncome.get(i).getDate(), bsp);
        }
        List<BusinessSummaryPage> result=new ArrayList<>(temp.values());
        Collections.sort(result, new Comparator<BusinessSummaryPage>() {

            @Override
            public int compare(BusinessSummaryPage o1, BusinessSummaryPage o2) {                
                return o1.getDate().compareTo(o2.getDate());
            }            
        });
        return result;
    }
    /**
    * @author 乐建建
    * @date 2016年2月19日 下午3:01:20
    * @param businessRealIncome 本期营业纯收入
    * @param lastRealIncome 上期营业纯收入
    * @return 营业收入增长率
    */
    private BigDecimal computeRate(BigDecimal businessRealIncome,
            BigDecimal lastRealIncome) {
        BigDecimal value=businessRealIncome.subtract(lastRealIncome);
        if (lastRealIncome.intValue()==0){
            return new BigDecimal(0.00);
        }
        return value.divide(lastRealIncome, BigDecimal.ROUND_HALF_EVEN);
    }
    public BigDecimal getBusinessExpense() {
        return businessExpense;
    }
    public BigDecimal getBusinessIncomeIncrementRate() {
        return businessIncomeIncrementRate;
    }
    public BigDecimal getBusinessIncomes() {
        return businessIncomes;
    }
    public BigDecimal getBusinessRealIncome() {
        return businessRealIncome;
    }
    public List<DeptSummaryDto> getDeptSummary() {
        return deptSummary;
    }
    public BigDecimal getLastBusinessRealIncome() {
        return lastBusinessRealIncome;
    }
    public BusinessSummaryTrend getTrend() {
        return trend;
    }
    /**
    * @author 乐建建
    * @date 2016年2月19日 下午2:48:41
    * @param deptSummary 部门汇总数据
    * @param type 类型
    * @return 处理之后的数据结果
    */
    private BigDecimal processData(List<DeptSummaryDto> deptSummary, int type) {
        BigDecimal value=new BigDecimal(0);
        if (deptSummary!=null && deptSummary.size()>0){
            if (type==1){
                for (int i=0; i< deptSummary.size(); i++){
                    value=value.add(deptSummary.get(i).getIncomes());
                }
            }
            if (type==2) {
                for (int i=0; i<deptSummary.size(); i++){
                    value=value.add(deptSummary.get(i).getExpenses());
                }
            }
            if (type==3){
                for (int i=0; i<deptSummary.size(); i++){
                    value=value.add(deptSummary.get(i).getRealIncomes());
                }
            }
        }

        return value;
    }
    public void setBusinessExpense(BigDecimal businessExpense) {
        this.businessExpense = businessExpense;
    }
    public void setBusinessIncomeIncrementRate(
            BigDecimal businessIncomeIncrementRate) {
        this.businessIncomeIncrementRate = businessIncomeIncrementRate;
    }
    public void setBusinessIncomes(BigDecimal businessIncomes) {
        this.businessIncomes = businessIncomes;
    }
    public void setBusinessRealIncome(BigDecimal businessRealIncome) {
        this.businessRealIncome = businessRealIncome;
    }
    public void setDeptSummary(List<DeptSummaryDto> deptSummary) {
        this.deptSummary = deptSummary;
    }
    
    public void setLastBusinessRealIncome(BigDecimal lastBusinessRealIncome) {
        this.lastBusinessRealIncome = lastBusinessRealIncome;
    }
    public void setTrend(BusinessSummaryTrend trend) {
        this.trend = trend;
    }
}
