package com.zefun.web.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.CardConsumedTrendData;
import com.zefun.web.dto.CardSaleSummaryDto;
import com.zefun.web.dto.DeptSummaryByDayDto;
import com.zefun.web.dto.DifferentStoreCardConsumeDto;
import com.zefun.web.dto.DifferentStoreMemberConsumeDto;
import com.zefun.web.dto.Member2Info;
import com.zefun.web.dto.MemberAccountInfo;
import com.zefun.web.dto.MemberCardConsumedInfo;
import com.zefun.web.dto.MemberInfoForLevel;
import com.zefun.web.dto.MemberLevelInfo;
import com.zefun.web.dto.ReconciliationDataDto;
import com.zefun.web.dto.ReconciliationOrderDetailDto;
import com.zefun.web.dto.StoreReconciliation;
import com.zefun.web.dto.SummaryResultDto;
import com.zefun.web.entity.CrossShopAccount;
import com.zefun.web.entity.MemberInfo;
import com.zefun.web.entity.ShopAccountState;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.mapper.CrossShopAccountMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.ShopAccountStateMapper;
import com.zefun.web.mapper.StoreInfoMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
* 营业汇总服务类
* @author 乐建建
* @date 2016年2月23日 下午4:32:21 
*/
@Service
public class BusinessReporterService {
    
    /**订单明细服务借口**/
    @Autowired
    private OrderDetailMapper detailMapper;
    
    /**门店信息服务借口*/
    @Autowired
    private StoreInfoMapper storeInfoMapper;
    
    /**跨店对账信息服务*/
    @Autowired
    private CrossShopAccountMapper accountMapper;
    
    /**会员资料相关查询接口*/
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    
    /**门店对账服务借口*/
    @Autowired
    private ShopAccountStateMapper stateMapper;
    
    /**
    * @author 乐建建
    * @date 2016年2月23日 下午4:36:09
    * @param dto 封装参数类
    * @return MemberInfoForLevel
    */
    public List<MemberInfoForLevel> getMemberInfo(SummaryResultDto dto){
        
        StoreInfo store=storeInfoMapper.selectByPrimaryKey(dto.getStoreId());
        /**如果是连锁店*/
        if (store.getHqStoreId() != null){
            dto.setHpStoreId(store.getHqStoreId());
        }
        else {
            dto.setHpStoreId(store.getStoreId());
        }        
        BigDecimal value=new BigDecimal(0);
        List<MemberLevelInfo> members=detailMapper.getMemberInfo(dto);
        List<MemberInfoForLevel> list=new ArrayList<MemberInfoForLevel>();
        if (members!=null && members.size()>0){
            for (int i=0; i<members.size(); i++){
                MemberInfoForLevel member=new MemberInfoForLevel();
                Member2Info type=null;
                MemberAccountInfo account=null;
                MemberCardConsumedInfo card=null;
                if (members.get(i).getMemberIds()!=null && members.get(i).getMemberIds().size()>0){
                    type=detailMapper.getMemberType2Info(members.get(i));
                    account=detailMapper.getMemberAccountInfo(members.get(i));
                    card=detailMapper.getMemberCardConsumedValue(members.get(i));
                }                        
                if (type==null){
                    type=new Member2Info(value, value, value, value, value, value);
                }
                member.setLevel(type);                                
                if (account==null){
                    account=new MemberAccountInfo(value, value, value); 
                }
                member.setMemberAccounts(account);                
                if (card==null){
                    card=new MemberCardConsumedInfo(value, value);
                }
                member.setMemberCards(card);
                member.setLevelInfo(members.get(i));
                list.add(member);
            }
        }
        return list;
    }

    /**
    * @author 乐建建
    * @date 2016年2月23日 下午5:36:31
    * @param dto 参数封装条件
    * @param flag 标志
    * @return CardSaleSummaryDto
    */
    public CardSaleSummaryDto getCardSaleSummary(SummaryResultDto dto, boolean flag) {
        StoreInfo store=getStoreInfoById(dto.getStoreId());        
        List<StoreInfo> stores = null;
        //如果当前门店是连锁总店
        if (store.getStoreType()==2){
            stores=storeInfoMapper.selectBaseInfoByMainId(store.getStoreId());
        }
        if (flag){
            stores=storeInfoMapper.selectBaseInfoByMainId(store.getHqStoreId());
        }   
        Map<String, Object> params=computeParams(dto.getBegin(), dto.getEnd(), dto.getDateType(), dto.getStoreId());
        String obegin=params.get("begin").toString();
        String oend=params.get("end").toString();
        dto.setBegin(params.get("begin").toString());
        dto.setDateType((Integer)params.get("dateType"));
        dto.setEnd(params.get("end").toString());
        List<MemberInfoForLevel> memberLevelInfo = getMemberInfo(dto);
        Member2Info currData=detailMapper.getDatePeriodCardSummary(dto);
        List<CardConsumedTrendData> tempTrend = null;
        if (dto.getDateType()!=null && (dto.getDateType()==2 || dto.getDateType()==3 || dto.getDateType()==4)){
            tempTrend=detailMapper.getCardType2Trend(dto);
            List<DeptSummaryByDayDto> cardConsumed=detailMapper.getCardConsumedTrend(dto);
            CardConsumedTrendData card=new CardConsumedTrendData();
            card.setOrderType(0);
            card.setTrends(cardConsumed);
            tempTrend.add(card);
        }        
        params=computeLastParams(dto.getBegin(), dto.getEnd(), dto.getDateType(), params);
        
        dto.setBegin(params.get("begin").toString());
        dto.setDateType((Integer)params.get("dateType"));
        dto.setEnd(params.get("end").toString());
        
        Member2Info lastCurrData=null;
        if (dto.getDateType()!=null) {
            lastCurrData= detailMapper.getDatePeriodCardSummary(dto);
        }
        
        return new CardSaleSummaryDto(stores, memberLevelInfo, obegin, oend, dto.getDateType(), tempTrend, lastCurrData, currData, dto.getStoreId());
    }
    
    /**
     * @param begin 开始日期
     * @param end 截止日期
     * @param dateType 日期类型
     * @param storeId 门店id
     * @return 封装后的参数
     */
    private Map<String, Object> computeParams(String begin, String end,
            Integer dateType, Integer storeId){
        Map<String, Object> params = new HashMap<String, Object>();
        if (begin == null && end == null && dateType == null) {
            dateType = 0;
        }
        if (dateType != null) {
            switch (dateType) {
                case 0: { //本日
                    begin = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
                    end = DateFormatUtils.format(DateUtils.addDays(new Date(), 1), "yyyy-MM-dd");
                    break;
                }
                case 1: { //本周
                    Calendar c = Calendar.getInstance();
                    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
                    if (dayOfWeek == 0) {
                        dayOfWeek = 7;
                    }
                    c.add(Calendar.DATE, - dayOfWeek + 1);
                    begin = DateFormatUtils.format(c, "yyyy-MM-dd");
    
                    dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
                    if (dayOfWeek == 0) {
                        dayOfWeek = 7;
                    }
                    c.add(Calendar.DATE, - dayOfWeek + 7);
                    end = DateFormatUtils.format(DateUtils.addDays(c.getTime(), 1), "yyyy-MM-dd");
                    break;
                }
                case 2:
                case 3: { //本月
                    Calendar c = Calendar.getInstance();
                    c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
                    begin = DateFormatUtils.format(c, "yyyy-MM-dd");
                    c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
                    end = DateFormatUtils.format(DateUtils.addDays(c.getTime(), 1), "yyyy-MM-dd");
                    break;
                }
                case 4: { //本年
                    Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    begin = year + "-01-01";
                    end = (year + 1) + "-01-01";
                    break;
                }
                default:
                    break;
            }
        }
        try {
            if (StringUtils.isBlank(begin) && StringUtils.isBlank(end)) {
                dateType = 0;
            }
            if (StringUtils.isBlank(begin)) {
                begin = DateFormatUtils.format(new Date(), "yyyy/MM/dd");
            }
            begin = DateFormatUtils.format(DateUtils.parseDate(begin, new String[]{"yyyy/MM/dd"}), "yyyy-MM-dd");
            if (StringUtils.isBlank(end)) {
                end = DateFormatUtils.format(new Date(), "yyyy/MM/dd");
            }
            if (dateType!=null){
                end = DateFormatUtils.format(DateUtils.addDays(DateUtils.parseDate(end, new String[]{"yyyy/MM/dd"}), 1), "yyyy-MM-dd");
            }
            else {
                end = DateFormatUtils.format(DateUtils.parseDate(end, new String[]{"yyyy/MM/dd"}), "yyyy-MM-dd");
            }
            
        } 
        catch (Exception e) {
        }
        params.put("begin", begin);
        params.put("end", end);
        params.put("storeId", storeId);
        params.put("dateType", dateType);
        return params;
    }
    
    /**
     * @param begin 起始日期
     * @param end 终止日期
     * @param dateType 日期类型
     * @param params 参数条件 包括起始日期 终止日期
     * @return 填充数据
     */
    public Map<String, Object> computeLastParams(String begin, String end, Integer dateType, Map<String, Object> params){
        if (dateType != null && end.compareTo(begin) > 0) {
            switch (dateType) {
                case 0: { // 日, 获取前一天的数据
                    begin = DateFormatUtils.format(DateUtils.addDays(new Date(), -1), "yyyy-MM-dd");
                    end = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
                    break;
                }
                case 1: {// 周, 获取前一周的数据
                    Calendar c = Calendar.getInstance();
                    c.setTime(DateUtils.addWeeks(new Date(), -1));
                    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
                    if (dayOfWeek == 0) {
                        dayOfWeek = 7;
                    }
                    c.add(Calendar.DATE, - dayOfWeek + 1);
                    begin = DateFormatUtils.format(c, "yyyy-MM-dd");
    
                    dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
                    if (dayOfWeek == 0) {
                        dayOfWeek = 7;
                    }
                    c.add(Calendar.DATE, - dayOfWeek + 7);
                    end = DateFormatUtils.format(DateUtils.addDays(c.getTime(), 1), "yyyy-MM-dd");
                    break;
                }
                case 2:
                case 3: { // 月, 获取前一月的数据
                    Calendar c = Calendar.getInstance();
                    c.setTime(DateUtils.addMonths(new Date(), -1));
                    c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
                    begin = DateFormatUtils.format(c, "yyyy-MM-dd");
                    c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
                    end = DateFormatUtils.format(DateUtils.addDays(c.getTime(), 1), "yyyy-MM-dd");
                    break;
                }
                case 4: {// 年, 获取前一年的数据
                    Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    begin = (year - 1) + "-01-01";
                    end = year + "-01-01";
                    break;
                }
                default: {
                    begin = null;
                    end = null;
                    break;
                }
            }
            if (begin != null && end != null) {
                params.put("begin", begin);
                params.put("end", end);
            }
        }
        return params;
    }
    
    /**
    * @author 乐建建
    * @date 2016年2月26日 上午10:42:56
    * @param storeId 当前门店id
    * @return 当前门店相关信息
    */
    public StoreInfo getStoreInfoById(Integer storeId){
        StoreInfo store=storeInfoMapper.selectByPrimaryKey(storeId);
        return store;
    }
    
    /**
    * @author 乐建建
    * @date 2016年3月1日 下午5:04:53
    * @param storeId 门店id
    * @param month 月份 
    * @return ModelAndView
    */
    public ModelAndView getStoreReconciliation(Integer storeId, String month){
        ModelAndView mav=new ModelAndView(View.BusinessReport.RECONCILIATION);
        mav.addAllObjects(getResult(storeId, month==null?DateUtil.getCurMonth():month));
        return mav;
    }

    /**
    * @author 乐建建
    * @date 2016年3月1日 下午5:11:18
    * @param storeId 门店id
    * @param month 月份
    * @return 根据给定条件查询回来的数据
    */
    public Map<String, Object> getResult(Integer storeId, String month) {
        
        Map<String, Object> result=new HashMap<String, Object>();
        result.put("month", month);
        BigDecimal value=new BigDecimal(0);
        
        Map<String, Object> conditions=new HashMap<String, Object>();
        String next=Integer.parseInt(month.substring(5, 7))+1>10
                ?(Integer.parseInt(month.substring(5, 7))+1)+"":"0"+(Integer.parseInt(month.substring(5, 7))+1);
        String begin =month+"-01";
        String end = month.substring(0, 4)+"-"+next+"-01";
        StoreInfo store=storeInfoMapper.selectByPrimaryKey(storeId);
        if (store.getHqStoreId()!=null){
            conditions.put("hqStoreId", store.getHqStoreId());
        }
        conditions.put("begin", begin);
        conditions.put("end", end);
        conditions.put("lstoreId", storeId);
        List<StoreReconciliation> storesInformation=accountMapper.selectDifferentStoreMemberConsumed(conditions);
        if (storesInformation!=null && storesInformation.size()>0){
            for (int i=0; i<storesInformation.size(); i++){
                Map<String, Object> params=new HashMap<String, Object>();
                params.put("begin", begin);
                params.put("end", end);
                params.put("storeId", storeId);
                StoreReconciliation storeRecon= storesInformation.get(i);
                DifferentStoreMemberConsumeDto otherToMe=null;
                DifferentStoreMemberConsumeDto meToOther=null;
                if (storeRecon.getOtherToMeMebers()!=null && storeRecon.getOtherToMeMebers().size()>0){
                    params.put("storeId", storeRecon.getStoreId());
                    params.put("memberIds", storeRecon.getOtherToMeMebers());
                    otherToMe=accountMapper.getOtherToMeResult(params);
                    int charged=accountMapper.getMemberChargeCnt(params);
                    int card=accountMapper.getMemberCardCnt(params);
                    otherToMe.setCardConsumedCnt(card);
                    otherToMe.setChargeCardCnt(charged);
                }
                if (otherToMe == null){
                    otherToMe=new DifferentStoreMemberConsumeDto(0, value, 0, value, null);
                }
                if (storeRecon.getMeToOtherMembers()!=null && storeRecon.getMeToOtherMembers().size()>0){
                    params.put("storeId", storeId);
                    params.put("memberIds", storeRecon.getMeToOtherMembers());
                    meToOther=accountMapper.getOtherToMeResult(params);
                    int charged=accountMapper.getMemberChargeCnt(params);
                    int card=accountMapper.getMemberCardCnt(params);
                    meToOther.setCardConsumedCnt(card);
                    meToOther.setChargeCardCnt(charged);
                }
                if (meToOther==null){
                    meToOther=new DifferentStoreMemberConsumeDto(0, value, 0, value, null);
                }
                storeRecon.setMeToOther(meToOther);
                storeRecon.setOtherToMe(otherToMe);
                BaseDto dto=getCrossShopReconciliation(storeId, storeRecon.getStoreId(), month, false);
                if (dto.getCode()!=0){
                    if (dto.getMsg().equals("2")){
                        storeRecon.setChargeOffState(3); //(未出账)
                    }
                    else {
                        storeRecon.setChargeOffState(1); //(待审核)
                    }                   
                }
                else {
                    storeRecon.setChargeOffState(2); //已出账
                }
            }
        }
        result.put("storeInformations", storesInformation);
        //根据当前时间获取近半年内的日期
        List<String> monthList = new ArrayList<>();
        String curMonth = DateUtil.getCurMonth();
        monthList.add(curMonth);
        for (int i = 1; i < 6; i++) {
            monthList.add(DateUtil.addMonth(curMonth, -i));
        }
        result.put("monthList", monthList);
        //result.put("detailList", getDeptDetailList(storeId, storesInformation.get(0).getStoreId(), curMonth, 1));
        return result;
    }

/*    *//**
    * @author 乐建建
    * @date 2016年3月2日 上午11:15:29
    * @param storeRecon 门店对账封装类
    * @param consumedData 异店消费数据
    * @param details 他店到本店消费明细数据
    * @param thisStoreId 本店id
    * @param thatStoreId 他店id
    * @param type 类型(1 代表他店到本店 2 代表本店到他店)
    * @return 组装成员变量的storeRecon
    *//*
    private StoreReconciliation ressembleElements(
            StoreReconciliation storeRecon,
            DifferentStoreMemberConsumeDto consumedData,
            List<OrderDetailDto> details , Integer thisStoreId, Integer thatStoreId , Integer type) {
        List<DifferentStoreCardConsumeDto> list=new ArrayList<DifferentStoreCardConsumeDto>();
        if (details!=null && details.size()>0){
            for (OrderDetailDto orderDetailDto : details) {
                DifferentStoreCardConsumeDto dscc=new DifferentStoreCardConsumeDto();
                dscc.setOrderDetail(orderDetailDto);
                
                CrossShopAccount csAccount=new CrossShopAccount();
                csAccount.setConsumedStoreId(thatStoreId);
                csAccount.setReferenceDetailId(orderDetailDto.getDetailId());
                csAccount.setBelongStoreId(thisStoreId);                           
                dscc.setShopAccount(csAccount);
                list.add(dscc);
            }
        }
        else {
            list=null;
        }
        consumedData.setDetailData(list);
        if (type==1){
            storeRecon.setOtherToMe(consumedData);
        }
        if (type==2){
            storeRecon.setMeToOther(consumedData); 
        }
        return storeRecon;
    }*/
    
    /**
    * @author 乐建建
    * @date 2016年3月2日 下午3:42:43
    * @param thisStoreId 本店id
    * @param thatStoreId 他店id
    * @param month 传入的月份数据
    * @param type 类型
    * @return List<DifferentStoreCardConsumeDto>
    */
    public BaseDto getCrossShopConsumedDetail(Integer thisStoreId, Integer thatStoreId, 
            String month, Integer type){
        List<DifferentStoreCardConsumeDto> list = getDeptDetailList(thisStoreId,
                thatStoreId, month, type);
        ShopAccountState shop=new ShopAccountState(null, thisStoreId, thatStoreId, null, null);
        ShopAccountState result=stateMapper.selectAccountState(shop);
        BaseDto dto=new BaseDto();
        if (result!=null){            
            dto.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
            dto.setMsg(list);
            return dto;
        }
        else {
            dto.setCode(App.System.API_RESULT_CODE_FOR_FAIL);
            dto.setMsg(list);
            return dto;
        }
    }

    /**
    * @author 乐建建
    * @date 2016年3月9日 下午2:25:50
    * @param thisStoreId 本店id
    * @param thatStoreId 他店id
    * @param month 当前月份
    * @param type 消费类型
    * @return 消费明细列表
    */
    public List<DifferentStoreCardConsumeDto> getDeptDetailList(
            Integer thisStoreId, Integer thatStoreId, String month,
            Integer type) {
        if (month==null){
            month=DateUtil.getCurMonth();
        }
        String next=Integer.parseInt(month.substring(5, 7))+1>10
                ?(Integer.parseInt(month.substring(5, 7))+1)+"":"0"+(Integer.parseInt(month.substring(5, 7))+1);
        String begin =month+"-01";
        String end = month.substring(0, 4)+"-"+next+"-01";
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("begin", begin);
        params.put("end", end);
        if (type==1 || type==2){
            params.put("localStoreId", thatStoreId);
            params.put("storeId", thisStoreId);
        }
        else {
            params.put("storeId", thatStoreId);
            params.put("localStoreId", thisStoreId);
        }
        List<Integer> memberIds=accountMapper.selectMembersByStoreId(params);
        List<DifferentStoreCardConsumeDto> list=new ArrayList<DifferentStoreCardConsumeDto>();
        List<ReconciliationOrderDetailDto> details=null;
        params.remove("localStoreId");
        params.remove("storeId");
        if (type==1 || type==2){
            params.put("storeId", thisStoreId);
        }
        else {
            params.put("storeId", thatStoreId);       
        }
        if (type==2 || type==4){
            params.put("type", 2);
        }
        else {
            params.put("type", 1);
        }
        if (memberIds!=null && memberIds.size()>0){            
            params.put("memberIds", memberIds);
            details=accountMapper.getCrossShopConsumedDetail(params);
        }
        if (details!=null){
            for (ReconciliationOrderDetailDto orderDetailDto : details) {
                DifferentStoreCardConsumeDto dscc=new DifferentStoreCardConsumeDto();
                String detailId=orderDetailDto.getDetailId();
                MemberInfo member=memberInfoMapper.selectByPrimaryKey(orderDetailDto.getMemberId());
                orderDetailDto.setMemberName(member.getName());
                CrossShopAccount account=new CrossShopAccount();
                account.setReferenceDetailId(detailId);
                account.setSeriesId(orderDetailDto.getSeriesId());
                if (type==1 || type==2){
                    account.setBelongStoreId(thatStoreId);
                    account.setConsumedStoreId(thisStoreId);
                }
                else {
                    account.setBelongStoreId(thisStoreId);
                    account.setConsumedStoreId(thatStoreId);
                }  
                accountMapper.insertIfNotExist(account);
                dscc.setOrderDetail(orderDetailDto);
                account=accountMapper.selectByPrimaryKey(orderDetailDto.getDetailId());
                dscc.setShopAccount(account);
                list.add(dscc);
            }
            /**
             * 对list进行排序，将审核未通过的放在前面
             * */
            Collections.sort(list, new Comparator<DifferentStoreCardConsumeDto>() {

                @Override
                public int compare(DifferentStoreCardConsumeDto o1,
                        DifferentStoreCardConsumeDto o2) {
                    return o1.getShopAccount().getCheckState()-o2.getShopAccount().getCheckState();
                }
                
            });
        }
        return list;
    }
    
    /**
    * @author 乐建建
    * @date 2016年3月2日 下午5:47:37
    * @param list 用户修改的数据
    * @return 成功更新的行数
    */
    public int updateUserSubmitData(List<DifferentStoreCardConsumeDto> list){
        int num=0;
        if (list!=null && list.size()>0){
            for (DifferentStoreCardConsumeDto card : list) {
                CrossShopAccount account=card.getShopAccount();
                num=num+accountMapper.updateByPrimaryKeySelective(account);
            }
        }
        return num;
    }

    /**
    * @author 乐建建
    * @date 2016年3月2日 下午7:39:15
    * @param thisStoreId 本店id
    * @param thatStoreId 他店id
    * @param month 当前传入的月份数据
    * @param flag 是否插入数据库
    * @return BaseDto
    */
    public BaseDto getCrossShopReconciliation(Integer thisStoreId,
            Integer thatStoreId, String month, boolean flag) {
        Map<String, Object> conditions=new HashMap<String, Object>();
        List<String> detailIds=null;
        ReconciliationDataDto otherToMedto=null;
        ReconciliationDataDto meToOtherdto=null;
        BaseDto baseDto=new BaseDto();
        String next=Integer.parseInt(month.substring(5, 7))+1>10
                ?(Integer.parseInt(month.substring(5, 7))+1)+"":"0"+(Integer.parseInt(month.substring(5, 7))+1);
        String begin =month+"-01";
        String end = month.substring(0, 4)+"-"+next+"-01";
        if (flag && DateUtil.getCurDate().replaceAll("-", "").compareTo(end.replaceAll("-", ""))<0){
            baseDto.setCode(App.System.API_RESULT_CODE_FOR_FAIL);
            baseDto.setMsg("时间未到,不能对账哦!");
            return baseDto;
        }
        conditions.put("begin", begin);
        conditions.put("end", end);
        conditions.put("storeId", thisStoreId);
        conditions.put("localStoreId", thatStoreId);
        
        List<Integer> otherToMemembers=accountMapper.selectMembersByStoreId(conditions);
        if (otherToMemembers!=null && otherToMemembers.size()>0){
            conditions.put("memberIds", otherToMemembers);
            detailIds=accountMapper.selectDetailIdByMemberId(conditions);
        }
        if (detailIds!=null){
            otherToMedto=accountMapper.getDetailReconciliationResult(detailIds);
        }
        else {
            //不存在他店会员到本店消费的情况 此时默认都为0
            otherToMedto=new ReconciliationDataDto();
            otherToMedto.setAllDetailsCnt(0);
            otherToMedto.setIsChecked(0);
            otherToMedto.setInitialiseState(0);
            otherToMedto.setReconciliationAmt(new BigDecimal(0));
        }
        conditions.put("storeId", thatStoreId);
        conditions.put("localStoreId", thisStoreId);
        detailIds=null;
        List<Integer> meToOtherMembers=accountMapper.selectMembersByStoreId(conditions);
        if (meToOtherMembers!=null && meToOtherMembers.size()>0){
            conditions.put("memberIds", meToOtherMembers);
            detailIds=accountMapper.selectDetailIdByMemberId(conditions);
        }
        if (detailIds!=null){
            meToOtherdto=accountMapper.getDetailReconciliationResult(detailIds);
        }
        else {
            //不存在他店会员到本店消费的情况 此时默认都为0
            meToOtherdto=new ReconciliationDataDto();
            meToOtherdto.setAllDetailsCnt(0);
            meToOtherdto.setIsChecked(0);
            meToOtherdto.setInitialiseState(0);
            meToOtherdto.setReconciliationAmt(new BigDecimal(0));
        }
        //双方跨店消费人数为0
        if (otherToMedto.getAllDetailsCnt()==0 && meToOtherdto.getAllDetailsCnt()==0){
            baseDto.setCode(App.System.API_RESULT_CODE_FOR_FAIL);
            baseDto.setMsg("2");
            return baseDto;
        }
        //双方都是初始状态(未对账 未审核)
        if (otherToMedto.getInitialiseState()==otherToMedto.getAllDetailsCnt() 
                && meToOtherdto.getInitialiseState()==meToOtherdto.getAllDetailsCnt()){
            baseDto.setCode(App.System.API_RESULT_CODE_FOR_FAIL);
            baseDto.setMsg("2");
            return baseDto;
        }
        //双方都已对账
        if (otherToMedto.getAllDetailsCnt()==otherToMedto.getIsChecked() 
                && meToOtherdto.getAllDetailsCnt()==meToOtherdto.getIsChecked()){
            BigDecimal value=otherToMedto.getReconciliationAmt().subtract(meToOtherdto.getReconciliationAmt());
            baseDto.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
            baseDto.setMsg(value);
            if (flag){
                ShopAccountState record=new ShopAccountState(null, thisStoreId, thatStoreId, value, month);
                stateMapper.insertIfNotExist(record);
            }
            
            return baseDto;
        }
        baseDto.setCode(App.System.API_RESULT_CODE_FOR_FAIL);
        baseDto.setMsg("1");
        return baseDto;
    }

    /**
    * @author 乐建建
    * @date 2016年3月4日 下午5:47:45
    * @param arrayObj 用户更新的json串
    * @return 将json串改成List<DifferentStoreCardConsumeDto>
    */
    public List<DifferentStoreCardConsumeDto> getDetailData(
            JSONArray arrayObj) {
        if (arrayObj!=null && arrayObj.size()>0){
            List<DifferentStoreCardConsumeDto> list=new ArrayList<DifferentStoreCardConsumeDto>();
            for (int i=0; i<arrayObj.size(); i++){
                DifferentStoreCardConsumeDto dto=new DifferentStoreCardConsumeDto();
                CrossShopAccount account=new CrossShopAccount();
                JSONObject obj=arrayObj.getJSONObject(i);
                if (obj.has("serialId")){
                    String serialId=obj.getString("serialId");
                    account.setReferenceDetailId(serialId);
                }
                if (obj.has("reconciliation")){
                    double amount=obj.getDouble("reconciliation");
                    account.setReconciliationAmount(new BigDecimal(amount));
                }
                if (obj.has("state")){
                    Integer state=obj.getInt("state");                    
                    account.setCheckState(state);
                }  
                if (obj.has("remark")){
                    String remark=obj.getString("remark");
                    account.setRemark(remark);
                }
                dto.setShopAccount(account);
                list.add(dto);
            }
            return list;
        }
        return null;
    }
}
