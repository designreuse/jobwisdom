package com.zefun.wechat.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zefun.web.dto.ComboSummaryDto;
import com.zefun.web.dto.DeptComboSummaryDto;
import com.zefun.web.dto.DeptSummaryByDayDto;
import com.zefun.web.dto.SummaryResultDto;
import com.zefun.web.dto.TrendDeptDataDto;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.vo.CardComboSalesVo;
import com.zefun.web.vo.CashComboSalesVo;

/**
* @author 乐建建
* @date 2016年1月20日 下午8:41:25
* 套餐汇总页面对象 
*/
public class ComboSummaryViewDto extends SummaryResultDto{
    /**
    * @author 乐建建
    * @date 2016年2月19日 下午8:48:51
    * @param year 指定年
    * @param month 指定月
    * @return 指定年月的天数
    */
    public static int getLastDay(int year, int month) {
        int day = 1;
        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, day);
        int last = cal.getActualMaximum(Calendar.DATE);
        return last;
    }
    /**套餐销售业绩增长率*/
    private BigDecimal amountIncrementRate;  
    /**卡金套餐销售业绩*/
    private CardComboSalesVo cardComboSales;     
    /**现金套餐销售业绩*/
    private CashComboSalesVo cashComboSales;
    /**套餐销售总额*/
    private BigDecimal comboAmount;    
    /**套餐排行数据*/
    private List<ComboSummaryDto> comboRank;   
    /**套餐总销量*/
    private Integer comboSales;
    /**当前门店下的部门汇总数据*/
    private List<DeptComboSummaryDto> deptComboSummary;
    /**部门id和名称对应表*/
    private Map<Integer, String> idForName;

    /**上期套餐销售总额*/
    private BigDecimal lastComboAmount;
    /**上期套餐排行*/
    private Map<String, Integer> lastComboRank;
    /**部门下月趋势数据*/
    private List<TrendDeptDataDto> trendData;
    /**
     * 默认构造函数
    * @author 乐建建
    * @date 2016年1月20日 下午8:48:08 
    */
    public ComboSummaryViewDto() {
        super();
    }
    /**
    * @author 乐建建
    * @date 2016年1月21日 下午4:39:55
    * @param stores 门店列表
    * @param begin 起始点
    * @param end 终止点
    * @param dateType 日期类型
    * @param deptSummarys 当期部门的套餐汇总数据
    * @param comboRank2 当前部门的套餐排行
    * @param lastdeptSummarys 上期部门的套餐汇总数据
    * @param lastcomboRank  上期部门的套餐排行
    * @param deptsInfo 部门详细信息
    * @param trendData 趋势数据
    * @param storeId 门店id
    * @param cash 现金套餐销售业绩
    * @param card 卡金套餐销售业绩
    */
    public ComboSummaryViewDto(CashComboSalesVo cash, CardComboSalesVo card, List<StoreInfo> stores, 
            List<TrendDeptDataDto> trendData, String begin, String end, Integer dateType, 
            List<DeptComboSummaryDto> deptSummarys,
            List<ComboSummaryDto> comboRank2,
            List<DeptComboSummaryDto> lastdeptSummarys,
            List<ComboSummaryDto> lastcomboRank,
            List<DeptInfo> deptsInfo,
            Integer storeId) {
        this.comboAmount=computeAmount(deptSummarys);
        this.dateType=dateType;
        if (dateType==null){
            this.lastComboAmount=new BigDecimal(0);
        }
        else {
            this.lastComboAmount=computeAmount(lastdeptSummarys);
        }
        this.comboSales=computeCnt(deptSummarys);
        this.amountIncrementRate=computeRate(this.comboAmount, this.lastComboAmount);
        this.lastComboRank=extractRank(lastcomboRank);
        this.deptComboSummary=deptSummarys;
        this.idForName=convertId2Name(deptsInfo);
        this.comboRank=processRank(comboRank2, this.lastComboRank);
        this.comboRank=fillName(this.comboRank, this.idForName);
        if (dateType!=null && (dateType==2 || dateType==3 || dateType==4)){
            this.trendData=computeMonthTrend(trendData, dateType, begin);     
        }           
        this.begin=begin.replaceAll("-", "/");
        this.end=end.replaceAll("-", "/");
        this.branchStores=stores;
        this.storeId=storeId;
        this.cardComboSales=card;
        this.cashComboSales=cash;
    }
    /**
    * @author 乐建建
    * @date 2016年1月21日 下午5:00:42
    * @param deptSummarys 当期部门的套餐汇总数据
    * @return  门店下的套餐总销售额
    */
    private BigDecimal computeAmount(List<DeptComboSummaryDto> deptSummarys) {
        BigDecimal totalAmount=new BigDecimal(0);
        if (deptSummarys!=null){
            for (int i=0; i<deptSummarys.size(); i++){
                totalAmount=totalAmount.add(deptSummarys.get(i).getDeptComboSummary());
            }
        }        
        return totalAmount;
    }

    /**
    * @author 乐建建
    * @date 2016年1月21日 下午5:08:02
    * @param deptSummarys 当期部门的套餐汇总数据
    * @return 门店下的套餐总销售数量
    */
    private Integer computeCnt(List<DeptComboSummaryDto> deptSummarys) {
        Integer num=0;
        if (deptSummarys!=null){
            for (int i=0; i<deptSummarys.size(); i++){
                num=num+deptSummarys.get(i).getDeptComboSales();
            }
        }
        
        return num;
    }

    /**
    * @author 乐建建
    * @date 2016年1月20日 下午5:21:21
    * @param trendData2 查询回来的某个特定部门的数据
    * @param dateType 日期类型
    * @param begin 开始日期
    * @return 某个部门的一年的数据
    */
    private List<TrendDeptDataDto> computeMonthTrend(
            List<TrendDeptDataDto> trendData2, Integer dateType, String begin) {
        List<TrendDeptDataDto> resultData=new ArrayList<TrendDeptDataDto>();        
        Map<Integer, BigDecimal> monthsData=new HashMap<Integer, BigDecimal>();
        Integer size=12;
        if (dateType==2 || dateType==3){
            int year=Integer.parseInt(begin.substring(0, 4));
            int month=Integer.parseInt(begin.substring(5, 7));
            size=getLastDay(year, month);
        }
        if (trendData2!=null){
            for (int i=0; i<trendData2.size(); i++){
                
                for (int k =1 ; k<size+1 ;  k++){
                    monthsData.put(k, new BigDecimal(0));
                }
                
                //美容部
                TrendDeptDataDto dto=trendData2.get(i);
                //美容部下面的数据库存在的月份数据
                List<DeptSummaryByDayDto> dateData= dto.getTrendDeptData();
                if (dto.getTrendDeptData().size()<size){
                    
                    for (int j =0 ; j<dateData.size(); j++){
                        if (dateData.get(j)!=null && dateData.get(j).getCurrDate()!=null){
                            monthsData.put(Integer.parseInt(dateData.get(j).getCurrDate()), dateData.get(j).getDeptSum());
                        }                        
                    }
                    dateData.clear();
                    for (int j=1; j<size+1 ; j++){
                        BigDecimal value=monthsData.get(j);
                        dateData.add(new DeptSummaryByDayDto(value, j+""));
                    }
                }
                resultData.add(new TrendDeptDataDto(dto.getDeptId(), dateData));
            }
        }      
        //美容部 美发部 美甲部
      
        return resultData;
    }

    /**
    * @author 乐建建
    * @date 2016年1月21日 下午5:20:52
    * @param comboAmount2 当期套餐总销售额
    * @param lastComboAmount2 上期套餐销售额
    * @return 套餐总销售额增长率
    */
    private BigDecimal computeRate(BigDecimal comboAmount2,
            BigDecimal lastComboAmount2) {
        BigDecimal diff=comboAmount2.subtract(lastComboAmount2);
        if (lastComboAmount2.intValue()==0){
            return new BigDecimal(0.00);
        }
        return diff.divide(lastComboAmount2, BigDecimal.ROUND_HALF_UP);
    }
    /**
     * @param deptInShop 给定门店下所有的部门信息
     * @return 部门id和name的对应表
     */
    private Map<Integer, String> convertId2Name(List<DeptInfo> deptInShop) {
        Map<Integer, String> idForName=new HashMap<Integer, String>();
        for (int i=0; i<deptInShop.size(); i++){
            if (deptInShop.get(i).getIsResults()==1){
                String name=deptInShop.get(i).getDeptName();
                Integer id=deptInShop.get(i).getDeptId();
                idForName.put(id, name);
            }            
        }
        return idForName;
    }
    
    /**
    * @author 乐建建
    * @date 2016年1月21日 下午5:26:24
    * @param lastcomboRank2 上期部门的套餐排行
    * @return 上期部门套餐的排行 以<套餐名,排名>方式
    */
    private Map<String, Integer> extractRank(
            List<ComboSummaryDto> lastcomboRank2) {
        Map<String, Integer> lastRank=new HashMap<String, Integer>();
        for (int i=0; i<lastcomboRank2.size(); i++){
            lastRank.put(lastcomboRank2.get(i).getComboName(), lastcomboRank2.get(i).getComboRank());
        }
        return lastRank;
    }

    /**
    * @author 乐建建
    * @date 2016年1月27日 下午8:11:47
    * @param comboRank2 封装套餐销售排行数据
    * @param idForName2 部门id和名字对应表
    * @return 填充名字之后的套餐排行数据 
    */
    private List<ComboSummaryDto> fillName(List<ComboSummaryDto> comboRank2,
            Map<Integer, String> idForName2) {
        if (comboRank2!=null && idForName2!=null){
            for (int i=0; i<comboRank2.size(); i++){
                comboRank2.get(i).setDeptName(idForName2.get(comboRank2.get(i).getDeptId()));
            }
        }
        return comboRank2;
    }

    public BigDecimal getAmountIncrementRate() {
        return amountIncrementRate;
    }

    public CardComboSalesVo getCardComboSales() {
        return cardComboSales;
    }

    public CashComboSalesVo getCashComboSales() {
        return cashComboSales;
    }
    
    public BigDecimal getComboAmount() {
        return comboAmount;
    }
    
    
    public List<ComboSummaryDto> getComboRank() {
        return comboRank;
    }

    public Integer getComboSales() {
        return comboSales;
    }

    
    public List<DeptComboSummaryDto> getDeptComboSummary() {
        return deptComboSummary;
    }
    
    public Map<Integer, String> getIdForName() {
        return idForName;
    }
    
    
    public BigDecimal getLastComboAmount() {
        return lastComboAmount;
    }
    
    public Map<String, Integer> getLastComboRank() {
        return lastComboRank;
    }
    public List<TrendDeptDataDto> getTrendData() {
        return trendData;
    }
    /**
    * @author 乐建建
    * @date 2016年1月27日 下午8:01:53
    * @param comboRank2 当期排行数据
    * @param lastComboRank2 上期排行数据的名字与排名的对应表
    * @return List<ComboSummaryDto>
    */
    private List<ComboSummaryDto> processRank(List<ComboSummaryDto> comboRank2,
            Map<String, Integer> lastComboRank2) {
        
        for (int i=0; i<comboRank2.size(); i++){
            Integer rank=lastComboRank2.get(comboRank2.get(i).getComboName());
            if (rank!=null){
                comboRank2.get(i).setLastRank(rank.toString());
            }
            else {
                comboRank2.get(i).setLastRank("---");
            }
        }
        return comboRank2;
    }
    public void setAmountIncrementRate(BigDecimal amountIncrementRate) {
        this.amountIncrementRate = amountIncrementRate;
    }
    
    public void setCardComboSales(CardComboSalesVo cardComboSales) {
        this.cardComboSales = cardComboSales;
    }
    public void setCashComboSales(CashComboSalesVo cashComboSales) {
        this.cashComboSales = cashComboSales;
    }
    public void setComboAmount(BigDecimal comboAmount) {
        this.comboAmount = comboAmount;
    }
    
    public void setComboRank(List<ComboSummaryDto> comboRank) {
        this.comboRank = comboRank;
    }
    public void setComboSales(Integer comboSales) {
        this.comboSales = comboSales;
    }
    public void setDeptComboSummary(List<DeptComboSummaryDto> deptComboSummary) {
        this.deptComboSummary = deptComboSummary;
    }
    public void setIdForName(Map<Integer, String> idForName) {
        this.idForName = idForName;
    }
    public void setLastComboAmount(BigDecimal lastComboAmount) {
        this.lastComboAmount = lastComboAmount;
    }
    public void setLastComboRank(Map<String, Integer> lastComboRank) {
        this.lastComboRank = lastComboRank;
    }
    public void setTrendData(List<TrendDeptDataDto> trendData) {
        this.trendData = trendData;
    }
    
    
}
