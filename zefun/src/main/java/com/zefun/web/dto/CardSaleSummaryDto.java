package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zefun.web.entity.StoreInfo;

/**
* 营业汇总下面卡项销售前端页面汇总封装类
* @author 乐建建
* @date 2016年2月23日 上午10:59:59 
*/
public class CardSaleSummaryDto extends SummaryResultDto{
    
    /**当期开卡金额汇总*/
    private BigDecimal totalOpenCardAmt;
    /**当期充值金额汇总*/
    private BigDecimal totalChargedAmt;
    /**当期升级金额汇总*/
    private BigDecimal totalUpgradeAmt;
    /**上期开卡金额汇总*/
    private BigDecimal lastTotalOpenCardAmt;
    /**上期充值金额汇总*/
    private BigDecimal lastTotalChargedAmt;
    /**上期升级金额汇总*/
    private BigDecimal lastTotalUpgradeAmt;
    
    /**会员总人数*/
    private BigDecimal totalMemberCnt;
    
    /**累计储值总额*/
    private BigDecimal totalMemberValue;
    
    /**累计余额和*/
    private BigDecimal totalBalanceValue;
    
    public BigDecimal getTotalMemberCnt() {
        return totalMemberCnt;
    }
    public void setTotalMemberCnt(BigDecimal totalMemberCnt) {
        this.totalMemberCnt = totalMemberCnt;
    }
    public BigDecimal getTotalMemberValue() {
        return totalMemberValue;
    }
    public void setTotalMemberValue(BigDecimal totalMemberValue) {
        this.totalMemberValue = totalMemberValue;
    }
    public BigDecimal getTotalBalanceValue() {
        return totalBalanceValue;
    }
    public void setTotalBalanceValue(BigDecimal totalBalanceValue) {
        this.totalBalanceValue = totalBalanceValue;
    }

    /**会员等级对应的会员详细数据*/
    private List<MemberInfoForLevel> memberLevelInfo;
    /**趋势数据*/
    private List<CardConsumedTrendData> trends;
    /**订单类型与名称对应表*/
    private Map<Integer, String> idForName;
    
    public Map<Integer, String> getIdForName() {
        return idForName;
    }
    public void setIdForName(Map<Integer, String> idForName) {
        this.idForName = idForName;
    }
    /**
    * @author 乐建建
    * @date 2016年2月23日 下午7:07:40
    * @param memberLevelInfo 会员等级对应的会员详细数据
    * @param stores 门店列表
    * @param obegin 开始日期
    * @param oend 结束日期
    * @param dateType 日期类型
    * @param tempTrend 趋势数据 
    * @param lastCurrData 上期数据
    * @param currData 当期数据
    * @param storeId 门店id
    */
    public CardSaleSummaryDto(List<StoreInfo> stores, List<MemberInfoForLevel> memberLevelInfo,
            String obegin, String oend, Integer dateType,
            List<CardConsumedTrendData> tempTrend, Member2Info lastCurrData, Member2Info currData,
            Integer storeId) {
        BigDecimal value=new BigDecimal(0);
        this.totalChargedAmt=value;
        this.totalOpenCardAmt=value;
        this.totalUpgradeAmt=value;
        if (currData!=null){
            this.totalChargedAmt=currData.getChargeCardAmt()==null?value:currData.getChargeCardAmt();
            this.totalOpenCardAmt=currData.getNewOpenAmt()==null?value:currData.getNewOpenAmt();
            this.totalUpgradeAmt=currData.getUpgradeAmt()==null?value:currData.getUpgradeAmt();
        }    
        this.lastTotalChargedAmt=value;
        this.lastTotalOpenCardAmt=value;
        this.lastTotalUpgradeAmt=value;
        if (lastCurrData!=null){
            this.lastTotalChargedAmt=lastCurrData.getChargeCardAmt()==null?value:lastCurrData.getChargeCardAmt();
            this.lastTotalOpenCardAmt=lastCurrData.getNewOpenAmt()==null?value:lastCurrData.getNewOpenAmt();
            this.lastTotalUpgradeAmt=lastCurrData.getUpgradeAmt()==null?value:lastCurrData.getUpgradeAmt();   
        }       
        this.memberLevelInfo=process(memberLevelInfo);
        this.begin=obegin;
        this.dateType=dateType;
        this.end=oend;
        this.trends=null;
        if (dateType!=null && (dateType==2 || dateType==3 || dateType==4)){
            this.trends=computeMonthTrend(tempTrend, dateType, obegin);
            this.idForName=fillName();
        }
        this.totalBalanceValue=computeAmt(this.memberLevelInfo, 1);
        this.totalMemberCnt=computeAmt(this.memberLevelInfo, 2);
        this.totalMemberValue=computeAmt(this.memberLevelInfo, 3);
        this.branchStores=stores;
        this.storeId=storeId;
    }
    /**
    * @author 乐建建
    * @date 2016年2月24日 上午11:02:45
    * @param memberLevelInfo2 会员等级对应的会员详细数据
    * @return 将为空的数据进行处理
    */
    private List<MemberInfoForLevel> process(
            List<MemberInfoForLevel> memberLevelInfo2) {
        BigDecimal value=new BigDecimal(0);
        if (memberLevelInfo2!=null && memberLevelInfo2.size()>0){
            for (int i=0; i<memberLevelInfo2.size(); i++){
                MemberInfoForLevel member=memberLevelInfo2.get(i);
                Member2Info level=member.getLevel();
                if (level.getChargeCardAmt()==null){
                    level.setChargeCardAmt(value);
                }
                if (level.getNewOpenAmt()==null){
                    level.setNewOpenAmt(value);
                }
                if (level.getUpgradeAmt()==null){
                    level.setUpgradeAmt(value);
                }
                MemberAccountInfo account=member.getMemberAccounts();
                if (account.getAccumulativeAmt()==null){
                    account.setAccumulativeAmt(value);
                }
                if (account.getBalanceAmt()==null){
                    account.setBalanceAmt(value);
                }
                if (account.getMemberCnt()==null){
                    account.setMemberCnt(value);
                }
                MemberCardConsumedInfo card=member.getMemberCards();
                if (card.getCardAmt()==null){
                    card.setCardAmt(value);
                }
                if (card.getMemberCnt()==null){
                    card.setMemberCnt(value);
                }
            }
        }
        return memberLevelInfo2;
    }
    /**
    * @author 乐建建
    * @date 2016年2月24日 上午10:42:53
    * @return 订单类型与名称对应表
    */
    private Map<Integer, String> fillName() {
        Map<Integer, String> maps=new HashMap<Integer, String>();
        maps.put(4, "开卡");
        maps.put(5, "充值");
        maps.put(6, "升级");
        maps.put(0, "刷卡消费");
        return maps;
    }
    /**
    * @author 乐建建
    * @date 2016年2月23日 下午7:45:03
    * @param memberLevelInfo2 会员等级对应的会员详细数据
    * @param type 汇总类型
    * @return 汇总值
    */
    private BigDecimal computeAmt(List<MemberInfoForLevel> memberLevelInfo2, int type) {
        
        BigDecimal value=new BigDecimal(0);
        for (int i=0; i<memberLevelInfo2.size(); i++){
            MemberInfoForLevel member=memberLevelInfo2.get(i);
            if (type==1){
                value=value.add(member.getMemberAccounts().getBalanceAmt()) ;
            }
            if (type==2){
                value=value.add(member.getMemberAccounts().getMemberCnt());
            }
            if (type==3){
                value=value.add(member.getMemberAccounts().getAccumulativeAmt());
            }
        }
        return value;
    }
    public BigDecimal getTotalOpenCardAmt() {
        return totalOpenCardAmt;
    }
    public void setTotalOpenCardAmt(BigDecimal totalOpenCardAmt) {
        this.totalOpenCardAmt = totalOpenCardAmt;
    }
    public BigDecimal getTotalChargedAmt() {
        return totalChargedAmt;
    }
    public void setTotalChargedAmt(BigDecimal totalChargedAmt) {
        this.totalChargedAmt = totalChargedAmt;
    }
    public BigDecimal getTotalUpgradeAmt() {
        return totalUpgradeAmt;
    }
    public void setTotalUpgradeAmt(BigDecimal totalUpgradeAmt) {
        this.totalUpgradeAmt = totalUpgradeAmt;
    }
    public BigDecimal getLastTotalOpenCardAmt() {
        return lastTotalOpenCardAmt;
    }
    public void setLastTotalOpenCardAmt(BigDecimal lastTotalOpenCardAmt) {
        this.lastTotalOpenCardAmt = lastTotalOpenCardAmt;
    }
    public BigDecimal getLastTotalChargedAmt() {
        return lastTotalChargedAmt;
    }
    public void setLastTotalChargedAmt(BigDecimal lastTotalChargedAmt) {
        this.lastTotalChargedAmt = lastTotalChargedAmt;
    }
    public BigDecimal getLastTotalUpgradeAmt() {
        return lastTotalUpgradeAmt;
    }
    public void setLastTotalUpgradeAmt(BigDecimal lastTotalUpgradeAmt) {
        this.lastTotalUpgradeAmt = lastTotalUpgradeAmt;
    }
    public List<MemberInfoForLevel> getMemberLevelInfo() {
        return memberLevelInfo;
    }
    public void setMemberLevelInfo(List<MemberInfoForLevel> memberLevelInfo) {
        this.memberLevelInfo = memberLevelInfo;
    }
    public List<CardConsumedTrendData> getTrends() {
        return trends;
    }
    public void setTrends(List<CardConsumedTrendData> trends) {
        this.trends = trends;
    }
    
    /**
    * @author 乐建建
    * @date 2016年1月20日 下午5:21:21
    * @param trendData2 查询回来的某个特定部门的数据
    * @param type 日期类型
    * @param begin 开始日期
    * @return 某个部门的一年的数据
    */
    private List<CardConsumedTrendData> computeMonthTrend(
            List<CardConsumedTrendData> trendData2, Integer type, String begin) {
        Integer size=12;
        if (type==3 || type==2) {
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
                
                CardConsumedTrendData cardTrend= trendData2.get(i);
                //美容部下面的数据库存在的月份数据
                List<DeptSummaryByDayDto> dateData= cardTrend.getTrends();
                if (dateData.size()<size){
                    
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
            }
        }

        return trendData2;
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
}
