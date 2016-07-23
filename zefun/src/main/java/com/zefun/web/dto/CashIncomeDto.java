package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zefun.web.entity.StoreInfo;

/**
 * 营业汇总下面的现金收入封装类
* @author 乐建建
* @date 2016年2月21日 下午1:15:06 
*/
public class CashIncomeDto extends SummaryResultDto{
    /**项目现金收入*/
    private BigDecimal totalProjectAmt;
    /**商品现金收入*/
    private BigDecimal totalGoodsAmt;
    /**疗程现金收入*/
    private BigDecimal totalComboAmt;
    /**开卡现金收入*/
    private BigDecimal openCardAmt;
    /**充值现金收入*/
    private BigDecimal chargeCardAmt;
    /**升级现金收入*/
    private BigDecimal upgradeAmt;
    /**现金总收入*/
    private BigDecimal totalCashAmt;
    /**现金收入增长率*/
    private BigDecimal cashIncomeRate;
    /**分部门明细数据*/
    private List<DeptCashIncome> deptSummary;
    /**趋势数据*/
    private List<TrendDeptDataDto> trends;
    /**订单类型与名称对应表*/
    private Map<Integer, String> typeForName;
    
    /**
    * @author 乐建建
    * @date 2016年2月21日 下午2:46:58
    * @param stores 门店列表
    * @param deptSummary 当期的部门明细数据
    * @param trends 趋势数据 有可能为空
    * @param lastDeptSummary 上期的部门明细数据
    * @param dto 基本封装对象
    */
    public CashIncomeDto(
            List<StoreInfo> stores,
            List<DeptCashIncome> deptSummary,
            List<TrendDeptDataDto> trends,
            List<DeptCashIncome> lastDeptSummary, SummaryResultDto dto) {
        this.totalProjectAmt=computeAmt(1, deptSummary);
        this.totalGoodsAmt=computeAmt(2, deptSummary);
        this.totalComboAmt=computeAmt(3, deptSummary);
        this.openCardAmt=computeAmt(4, deptSummary);
        this.chargeCardAmt=computeAmt(5, deptSummary);
        this.upgradeAmt=computeAmt(6, deptSummary);
        this.totalCashAmt=this.chargeCardAmt.add(this.openCardAmt).add(this.totalComboAmt).add(this.totalGoodsAmt).
                add(this.totalProjectAmt).add(this.upgradeAmt);
        BigDecimal lastTotalCashAmt=computeSum(lastDeptSummary);
        this.cashIncomeRate=computeRate(this.totalCashAmt, lastTotalCashAmt);
        this.dateType=dto.getDateType();
        this.begin=dto.getBegin();
        this.end=dto.getEnd();
        this.deptSummary=deptSummary;
        
        if (dto.getDateType()!=null && (dto.getDateType()==2 || dto.getDateType()==3 || dto.getDateType()==4)){
            this.trends=computeMonthTrend(trends, dto.getDateType(), dto.getBegin());
        }
        this.storeId=dto.getStoreId();
        this.branchStores= stores;
        this.typeForName=fillName();
    }
    
    
    /**
    * @author 乐建建
    * @date 2016年2月21日 下午3:27:56
    * @return 创建订单类型与名称的对应关系
    */
    private Map<Integer, String> fillName() {
        Map<Integer, String> typeForName=new HashMap<Integer, String>();
        typeForName.put(1, "项目");
        typeForName.put(2, "商品");
        typeForName.put(3, "疗程");
        typeForName.put(4, "开卡");
        typeForName.put(5, "充值");
        typeForName.put(6, "升级");
        return typeForName;
    }


    public Map<Integer, String> getTypeForName() {
        return typeForName;
    }


    public void setTypeForName(Map<Integer, String> typeForName) {
        this.typeForName = typeForName;
    }


    /**
    * @author 乐建建
    * @date 2016年1月20日 下午5:21:21
    * @param trendData2 查询回来的某个特定部门的数据
    * @param type 日期类型
    * @param begin 开始日期
    * @return 某个部门的一年的数据
    */
    private List<TrendDeptDataDto> computeMonthTrend(
            List<TrendDeptDataDto> trendData2, Integer type, String begin) {
        List<TrendDeptDataDto> resultData=new ArrayList<TrendDeptDataDto>();
        Integer size=12;
        if (type==3) {
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
                TrendDeptDataDto trdto=new TrendDeptDataDto(dto.getDeptId(), dateData);
                trdto.setOrderType(dto.getOrderType());
                resultData.add(trdto);
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
    * @date 2016年2月21日 下午3:14:10
    * @param totalCashAmt2 当期现金总收入
    * @param lastTotalCashAmt 上期现金总收入
    * @return 现金收入增长率
    */
    private BigDecimal computeRate(BigDecimal totalCashAmt2,
            BigDecimal lastTotalCashAmt) {
        if (lastTotalCashAmt.intValue()==0){
            return new BigDecimal(0.00);
        }
        BigDecimal diff=totalCashAmt2.subtract(lastTotalCashAmt);
        return diff.divide(lastTotalCashAmt, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
    * @author 乐建建
    * @date 2016年2月21日 下午3:09:12
    * @param deptSummary 当期的部门明细数据
    * @return 当期的现金收入和
    */
    private BigDecimal computeSum(List<DeptCashIncome> deptSummary) {
        BigDecimal value=new BigDecimal(0);
        if (deptSummary!=null && deptSummary.size()>0){
            for (int i=0; i<deptSummary.size(); i++){
                DeptCashIncome dept=deptSummary.get(i);
                DeptCashIncomeDto dto= dept.getDeptDto();
                
                value=value.add(dto.getTotalAmt()!=null?dto.getTotalAmt():new BigDecimal(0));
            }
        }
        return value;
    }
    /**
    * @author 乐建建
    * @date 2016年2月21日 下午2:49:48
    * @param type 订单类型
    * @param deptSummary 部门明细数据
    * @return 汇总之后的数值
    */
    private BigDecimal computeAmt(int type, List<DeptCashIncome> deptSummary) {
        BigDecimal value=new BigDecimal(0);
        if (deptSummary!=null && deptSummary.size()>0){
            for (int i=0; i< deptSummary.size(); i++){
                DeptCashIncome dept=deptSummary.get(i);
                DeptCashIncomeDto deptIncome = dept.getDeptDto();
                if (deptIncome==null){
                    BigDecimal args=new BigDecimal(0);
                    deptIncome=new DeptCashIncomeDto(null, args, args, args, args, args, args, args);
                }
                if (type==1){
                    value=value.add(deptIncome.getProjectAmt()==null?new BigDecimal(0):deptIncome.getProjectAmt());
                } 
                else if (type==2){
                    value=value.add(deptIncome.getGoodsAmt()==null?new BigDecimal(0):deptIncome.getGoodsAmt());
                }
                else if (type==3){
                    value=value.add(deptIncome.getComboAmt()==null?new BigDecimal(0):deptIncome.getComboAmt());
                }
                else if (type==4){
                    value=value.add(deptIncome.getOpenCardAmt()==null?new BigDecimal(0):deptIncome.getOpenCardAmt());
                }
                else if (type==5){
                    value=value.add(deptIncome.getChargedAmt()==null?new BigDecimal(0):deptIncome.getChargedAmt());
                }
                else if (type==6){
                    value=value.add(deptIncome.getUpgradeAmt()==null?new BigDecimal(0):deptIncome.getUpgradeAmt());
                }
            }
        }
        return value;
    }
    public List<TrendDeptDataDto> getTrends() {
        return trends;
    }
    public void setTrends(List<TrendDeptDataDto> trends) {
        this.trends = trends;
    }
    public List<DeptCashIncome> getDeptSummary() {
        return deptSummary;
    }
    public void setDeptSummary(List<DeptCashIncome> deptSummary) {
        this.deptSummary = deptSummary;
    }
    public BigDecimal getTotalProjectAmt() {
        return totalProjectAmt;
    }
    public void setTotalProjectAmt(BigDecimal totalProjectAmt) {
        this.totalProjectAmt = totalProjectAmt;
    }
    public BigDecimal getTotalGoodsAmt() {
        return totalGoodsAmt;
    }
    public void setTotalGoodsAmt(BigDecimal totalGoodsAmt) {
        this.totalGoodsAmt = totalGoodsAmt;
    }
    public BigDecimal getTotalComboAmt() {
        return totalComboAmt;
    }
    public void setTotalComboAmt(BigDecimal totalComboAmt) {
        this.totalComboAmt = totalComboAmt;
    }
    public BigDecimal getOpenCardAmt() {
        return openCardAmt;
    }
    public void setOpenCardAmt(BigDecimal openCardAmt) {
        this.openCardAmt = openCardAmt;
    }
    public BigDecimal getChargeCardAmt() {
        return chargeCardAmt;
    }
    public void setChargeCardAmt(BigDecimal chargeCardAmt) {
        this.chargeCardAmt = chargeCardAmt;
    }
    public BigDecimal getUpgradeAmt() {
        return upgradeAmt;
    }
    public void setUpgradeAmt(BigDecimal upgradeAmt) {
        this.upgradeAmt = upgradeAmt;
    }
    public BigDecimal getTotalCashAmt() {
        return totalCashAmt;
    }
    public void setTotalCashAmt(BigDecimal totalCashAmt) {
        this.totalCashAmt = totalCashAmt;
    }
    public BigDecimal getCashIncomeRate() {
        return cashIncomeRate;
    }
    public void setCashIncomeRate(BigDecimal cashIncomeRate) {
        this.cashIncomeRate = cashIncomeRate;
    }
    
}
