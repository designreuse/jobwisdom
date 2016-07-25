package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.vo.CardStoreSalesVo;
import com.zefun.web.vo.CashStoreSalesVo;

/**
* @author 乐建建
* @date 2016年1月22日 下午8:02:51 
*/
public class CommoditySalesDto extends SummaryResultDto{
    
    /**平均销售价格*/
    private BigDecimal avgGoodPrice;
    
    /**部门商品数据汇总*/
    private List<DeptGoodSalesSummaryDto> deptGoodSummary;    
    
    /**与劳动业绩页面保持一致而修改*/
    private List<ServiceReportDto> deptUpdateSummary;
    
    /**现金消费业绩*/
    private CashStoreSalesVo cashStoreSales;
    
    /**卡金消费业绩*/
    private CardStoreSalesVo cardStoreSales;

    /**总销售额*/
    private BigDecimal goodAmt;
    
    /**总销售量*/
    private Integer goodCnt;
    
    /**商品销售排行数据*/
    private List<GoodSalesSummaryDto> goodRank;
    
    /**部门id和名称对应表*/
    private Map<Integer, String> idForName;
    
    /**上期销售额*/
    private BigDecimal lastGoodAmt;

    /**上期商品销售排行表*/
    private Map<String, Integer> lastGoodRank;

    /**商品销售额增长率*/
    private BigDecimal salesIncrementRate;
    
    /**门店月趋势数据*/
    private List<TrendDeptDataDto> trendData;
    
    /**
    * @author 乐建建
    * @date 2016年1月23日 下午2:44:00
    * @param cash   现金查询条件
    * @param card   卡金查询条件
    * @param stores 门店列表
    * @param trends 门店月趋势数据
    * @param begin 时间区间起点
    * @param end 时间区间终点
    * @param dateType 时间区间类型
    * @param depts 部门汇总数据
    * @param ranks 商品销售排行
    * @param lastDepts 上期门店汇总数据
    * @param lastRanks 上期商品销售排行
    * @param deptsInShop 部门信息
    * @param deptSummary 部门详细信息
    * @param storeId 门店id
    */
    public CommoditySalesDto(CashStoreSalesVo cash, CardStoreSalesVo card, List<StoreInfo> stores, 
            List<TrendDeptDataDto> trends, String begin,
            String end, Integer dateType, List<DeptGoodSalesSummaryDto> depts,
            List<GoodSalesSummaryDto> ranks,
            List<DeptGoodSalesSummaryDto> lastDepts,
            List<GoodSalesSummaryDto> lastRanks, List<DeptInfo> deptsInShop, List<ServiceReportDto> deptSummary,
            Integer storeId) {
        this.begin=begin.replaceAll("-", "/");
        this.end=end.replaceAll("-", "/");
        this.deptGoodSummary=depts;
        this.trendData=null;
        if (dateType!=null && (dateType==2 || dateType==3 || dateType==4)){
            this.trendData=computeMonthTrend(trends, begin, dateType);
        }       
        this.dateType=dateType;
        this.lastGoodAmt=new BigDecimal(0);
        if (dateType !=null){
            this.lastGoodAmt=computeAmt(lastDepts);
        }        
        this.goodCnt=computeCnt(depts);
        this.goodAmt=computeAmt(depts);       
        this.avgGoodPrice=(this.goodCnt==0?new BigDecimal(0.00):this.goodAmt.divide(new BigDecimal(this.goodCnt), BigDecimal.ROUND_HALF_UP));
        this.salesIncrementRate=computeRate(this.goodAmt, this.lastGoodAmt);
        this.lastGoodRank=extractRank(lastRanks);
        this.idForName=convertId2Name(deptsInShop);
        this.goodRank=processRank(ranks, this.lastGoodRank);
        this.goodRank=fillName(this.goodRank, this.idForName);
        this.deptUpdateSummary=processDeptLabor(deptSummary);
        this.branchStores=stores;
        this.storeId=storeId;
        this.cashStoreSales = cash;
        this.cardStoreSales = card;
    }
    

    /**
    * @author 乐建建
    * @date 2016年2月20日 下午4:22:29
    * @param deptsSummary 部门下系列明细列表
    * @return 按照部门、系列汇总之后的数据
    */
    private List<ServiceReportDto> processDeptLabor(List<ServiceReportDto> deptsSummary) {                
        if (deptsSummary!=null){
            for (int i=0; i<deptsSummary.size(); i++){
                //部门相关数据
                ServiceReportDto deptDto=deptsSummary.get(i);
                BigDecimal deptSummary=new BigDecimal(0);                   
                //部门下项目数量
                Integer categoryNumInDept=deptDto.getCategoryList().size();
                
                Integer deptSales=0;
                
                //遍历部门下的系列
                for (int j=0; j<deptDto.getCategoryList().size(); j++){
                    //系列下项目数量
                    Integer projectNumInCategory=0;
                    BigDecimal seriesSummary=new BigDecimal(0);
                    BigDecimal cashCount = new BigDecimal(0);
                    BigDecimal cardCount = new BigDecimal(0);
                    //系列相关数据
                    CategoryReportDto categoryDto=deptDto.getCategoryList().get(j);
                    String name=categoryDto.getCategoryName()+"汇总";
                    Integer projectSales=0;
                    
                    projectNumInCategory=projectNumInCategory+categoryDto.getProjectList().size();
                    for (int k=0; k<categoryDto.getProjectList().size(); k++){
                            //系列下的项目相关数据
                        ItemReportDto projectDto=categoryDto.getProjectList().get(k);
                        GoodSalesDto project=projectDto.getGoodSales();
                        CashStoreSalesVo cash = projectDto.getCashStoreSales();
                        CardStoreSalesVo card = projectDto.getCardStoreSales();
                            //将projectSales类中的salesAmount设置为ProjectReportDto的收入
                        projectDto.setGoodIncome(project==null?new BigDecimal(0):project.getSalesAmt());
                        seriesSummary=seriesSummary.add(projectDto.getGoodIncome());
                        cashCount = cashCount.add(cash==null?new BigDecimal(0):cash.getCashStoreAmt());
                        cardCount = cardCount.add(card==null?new BigDecimal(0):card.getCardStoreAmt());
                        
                        projectSales=projectSales+(project==null?0:project.getSalesCount());
                        
                        //统计部门下所有项目的销量
                        deptSales=deptSales+(project==null?0:project.getSalesCount());
                    }
                    categoryDto.setCategoryIncomeSummary(seriesSummary);
                    categoryDto.setCategoryCard(cardCount);
                    categoryDto.setCategoryCash(cashCount);
                    categoryDto.setProjectNumInCategory(projectNumInCategory);
                    deptSummary=deptSummary.add(categoryDto.getCategoryIncomeSummary());
                    
                    categoryDto.getProjectList().add(new ItemReportDto(null, seriesSummary, name, projectSales));
                    //categoryDto.getProjectList().add(0, new ProjectReportDto(null, seriesSummary, name, projectSales));
                    Collections.reverse(categoryDto.getProjectList());
                }
                deptDto.setDeptIncome(deptSummary);
                deptDto.setDeptSales(deptSales);
                deptDto.setCategoryNumInDept(categoryNumInDept);
            }
        }  
        return deptsSummary;
    }

    /**
    * @author 乐建建
    * @date 2016年1月23日 下午2:58:54
    * @param depts 部门汇总数据
    * @return 当前门店总的商品销售额
    */
    private BigDecimal computeAmt(List<DeptGoodSalesSummaryDto> depts) {
        
        BigDecimal totalAmt=new BigDecimal(0);        
        if (depts!=null){
            for (int i=0; i<depts.size(); i++){
                totalAmt=totalAmt.add(depts.get(i).getDeptTotalAmt());
            }
        }        
        return totalAmt;
    }

    /**
    * @author 乐建建
    * @date 2016年1月23日 下午2:51:58
    * @param depts 部门汇总数据
    * @return 当前门店的总的商品销售数量
    */
    private Integer computeCnt(List<DeptGoodSalesSummaryDto> depts) {
        Integer num=0;
        if (depts!=null){
            for (int i=0; i<depts.size(); i++){
                num+=depts.get(i).getDeptTotalCnt();
            }
        }       
        return num;
    }
    
    /**
    * @author 乐建建
    * @date 2016年1月20日 下午5:21:21
    * @param trendData2 查询回来的某个特定部门的数据
    * @param begin 开始日期
    * @param dateType 日期类型
    * @return 某个部门的一年的数据
    */
    private List<TrendDeptDataDto> computeMonthTrend(
            List<TrendDeptDataDto> trendData2, String begin, Integer dateType) {
        List<TrendDeptDataDto> resultData=new ArrayList<TrendDeptDataDto>();
        Integer size=12;
        if (dateType==2 || dateType==3){
            int year=Integer.parseInt(begin.substring(0, 4));
            int month=Integer.parseInt(begin.substring(5, 7));
            size=getLastDay(year, month);
        }
        Map<Integer, BigDecimal> monthsData=new HashMap<Integer, BigDecimal>();
        if (trendData2!=null){
            //美容部 美发部 美甲部
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
                        if (dateData.get(j)!=null && dateData.get(j).getCurrDate()!=null) {
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
        return resultData;
    }
    
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
    
    /**
    * @author 乐建建
    * @date 2016年1月23日 下午3:13:16
    * @param goodAmt2 当期销售额
    * @param lastAmt 上期销售额
    * @return 销售额增长率
    */
    private BigDecimal computeRate(BigDecimal goodAmt2, BigDecimal lastAmt) {
        BigDecimal diff=goodAmt2.subtract(lastAmt);
        if (lastAmt.intValue()==0){
            return new BigDecimal(0.00);
        }
        return diff.divide(lastAmt, BigDecimal.ROUND_HALF_UP);
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
    * @param lastDept 上期部门的疗程排行
    * @return 上期部门疗程的排行 以<疗程名,排名>方式
    */
    private Map<String, Integer> extractRank(
            List<GoodSalesSummaryDto> lastDept) {
        Map<String, Integer> lastRank=new HashMap<String, Integer>();
        if (lastDept!=null){
            for (int i=0; i<lastDept.size(); i++){
                lastRank.put(lastDept.get(i).getGoodName(), lastDept.get(i).getGoodRank());
            }
        }        
        return lastRank;
    }
    
    /**
    * @author 乐建建
    * @date 2016年1月27日 下午8:11:47
    * @param comboRank2 封装疗程销售排行数据
    * @param idForName2 部门id和名字对应表
    * @return 填充名字之后的疗程排行数据 
    */
    private List<GoodSalesSummaryDto> fillName(List<GoodSalesSummaryDto> comboRank2,
            Map<Integer, String> idForName2) {
        if (comboRank2!=null && idForName2!=null){
            for (int i=0; i<comboRank2.size(); i++){
                comboRank2.get(i).setDeptName(idForName2.get(comboRank2.get(i).getGoodBelongToDeptId()));
            }
        }
        return comboRank2;
    }
    
    public BigDecimal getAvgGoodPrice() {
        return avgGoodPrice;
    }
    
    
    public List<DeptGoodSalesSummaryDto> getDeptGoodSummary() {
        return deptGoodSummary;
    }

    public List<ServiceReportDto> getDeptUpdateSummary() {
        return deptUpdateSummary;
    }

    public BigDecimal getGoodAmt() {
        return goodAmt;
    }

    public Integer getGoodCnt() {
        return goodCnt;
    }

    public List<GoodSalesSummaryDto> getGoodRank() {
        return goodRank;
    }

    public Map<Integer, String> getIdForName() {
        return idForName;
    }

 

    public BigDecimal getLastGoodAmt() {
        return lastGoodAmt;
    }

    public Map<String, Integer> getLastGoodRank() {
        return lastGoodRank;
    }

    public BigDecimal getSalesIncrementRate() {
        return salesIncrementRate;
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
    private List<GoodSalesSummaryDto> processRank(List<GoodSalesSummaryDto> comboRank2,
            Map<String, Integer> lastComboRank2) {
        
        for (int i=0; i<comboRank2.size(); i++){
            Integer rank=lastComboRank2.get(comboRank2.get(i).getGoodName());
            if (rank!=null){
                comboRank2.get(i).setLastRank(rank.toString());
            }
            else {
                comboRank2.get(i).setLastRank("---");
            }
        }
        return comboRank2;
    }

    public void setAvgGoodPrice(BigDecimal avgGoodPrice) {
        this.avgGoodPrice = avgGoodPrice;
    }

    public void setDeptGoodSummary(List<DeptGoodSalesSummaryDto> deptGoodSummary) {
        this.deptGoodSummary = deptGoodSummary;
    }

    public void setDeptUpdateSummary(List<ServiceReportDto> deptUpdateSummary) {
        this.deptUpdateSummary = deptUpdateSummary;
    }


    public void setGoodAmt(BigDecimal goodAmt) {
        this.goodAmt = goodAmt;
    }

    public void setGoodCnt(Integer goodCnt) {
        this.goodCnt = goodCnt;
    }

    public void setGoodRank(List<GoodSalesSummaryDto> goodRank) {
        this.goodRank = goodRank;
    }

    public void setIdForName(Map<Integer, String> idForName) {
        this.idForName = idForName;
    }

    public void setLastGoodAmt(BigDecimal lastGoodAmt) {
        this.lastGoodAmt = lastGoodAmt;
    }

    public void setLastGoodRank(Map<String, Integer> lastGoodRank) {
        this.lastGoodRank = lastGoodRank;
    }

    public void setSalesIncrementRate(BigDecimal salesIncrementRate) {
        this.salesIncrementRate = salesIncrementRate;
    }

    public void setTrendData(List<TrendDeptDataDto> trendData) {
        this.trendData = trendData;
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
