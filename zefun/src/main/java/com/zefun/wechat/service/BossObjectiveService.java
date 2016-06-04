package com.zefun.wechat.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.BusinessAnalysisDto;
import com.zefun.web.dto.CodeLibraryDto;
import com.zefun.web.dto.ComboStatementDto;
import com.zefun.web.dto.ComboStatementProjectDto;
import com.zefun.web.dto.CustomerAnalysisDto;
import com.zefun.web.dto.MemberLevelDto;
import com.zefun.web.entity.CodeLibrary;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.GoodsCategory;
import com.zefun.web.entity.ProjectCategory;
import com.zefun.web.mapper.CodeLibraryMapper;
import com.zefun.web.mapper.ComboInfoMapper;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.GoodsCategoryMapper;
import com.zefun.web.mapper.MemberAccountMapper;
import com.zefun.web.mapper.MemberComboRecordMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.OrderInfoMapper;
import com.zefun.web.mapper.ProjectCategoryMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 员工端service
* @author 王大爷
* @date 2015年8月20日 下午5:37:24
 */
@Service
public class BossObjectiveService {

    /** 订单信息*/
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    
    /** 订单明细*/
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    
    /** 部门*/
    @Autowired
    private DeptInfoMapper deptInfoMapper;
    
    /** 会员账户mapper*/
    @Autowired 
    private MemberAccountMapper memberAccountMapper;
    
    /** 项目类别*/
    @Autowired
    private ProjectCategoryMapper projectCategoryMapper;
    
    /** 会员级别*/
    @Autowired
    private MemberLevelMapper memberLevelMapper;
    
    /** 套餐Mapper*/
    @Autowired 
    private ComboInfoMapper comboInfoMapper;
    /** 数据字典Mapper*/
    @Autowired 
    private CodeLibraryMapper codeLibraryMapper;
    
    /** 会员套餐关联*/
    @Autowired
    private MemberComboRecordMapper memberComboRecordMapper;
    
    /** 商品类别*/
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;
    
    /**
     * 查询业绩报表
    * @author 王潇
    * @date Aug 19, 2015 4:21:25 PM
    * @param storeId 门店标识
    * @return ModelAndView
     */
    public ModelAndView initializePage (Integer storeId){

        ModelAndView mav = new ModelAndView();
        
        mav.setViewName(View.BossPage.BOSS_OBJECTIVE);
        
        List<DeptInfo> deptInfoList = deptInfoMapper.getResultsDeptInfo(storeId);
        
        mav.addObject("deptInfoList", deptInfoList);
        
        BigDecimal residueDebtAmount  = memberAccountMapper.selectDebtAmountByStoreId(storeId);
        
        mav.addObject("residueDebtAmount", residueDebtAmount);
        
        Map<String, Object> balanceMap = memberAccountMapper.selectMemberUserByStoreId(storeId);
        
        mav.addObject("balanceMap", balanceMap);
        
        return mav;
    }
    
    /**
     * 现金业绩
    * @author 王大爷
    * @date 2016年1月16日 下午7:33:41
    * @param storeId 门店标识
    * @param chooseType 选择类型（1、日，2、月，3、年）
    * @param deptId 部门标识
    * @return BaseDto
     */
    public BaseDto cashAmountService(Integer storeId, Integer chooseType, Integer deptId) {
        //现金总业绩
        Map<String, Object> cashTatailAmountMap = new HashMap<String, Object>();
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put("storeId", storeId);
                
        List<BigDecimal> tendencyList = new ArrayList<BigDecimal>();
        
        List<String> dateList = new ArrayList<String>();
        
        String nowDay = DateUtil.getCurDate();
        
        if (chooseType == 1) {
            String beginDay = DateUtil.getCurDate();
            map.put("beginDay", beginDay);
            map.put("endDay", beginDay);
            
            for (int i =6; i >= 0; i--) {
                
                String weekDay = DateUtil.getDateDaysBeforeStr(nowDay, i);
                
                Map<String, Object> weekMap = new HashMap<String, Object>();
                weekMap.put("storeId", storeId);
                weekMap.put("beginDay", weekDay);
                weekMap.put("endDay", weekDay);
                
                BigDecimal totalAmount = new BigDecimal(0);
                if (deptId == -1) {
                    Map<String, Object> weekTatalMap = orderInfoMapper.selectTatalCashAmount(weekMap);
                    totalAmount = new BigDecimal(weekTatalMap.get("totalAmount").toString());
                }
                else {
                    weekMap.put("orType", 5);
                    weekMap.put("deptId", deptId);
                    totalAmount = orderDetailMapper.selectTataiRealPriceByType(weekMap);
                }
                tendencyList.add(totalAmount);
                
                dateList.add(weekDay.substring(6, 10));
            }
        }
        else if (chooseType == 2) {
            map.put("beginDay", DateUtil.getMinMonthDateStr());
            map.put("endDay", DateUtil.getCurDate());
                        
            for (int i =29; i >= 0; i--) {
                
                String monthDay = DateUtil.getDateDaysBeforeStr(nowDay, i);
                
                Map<String, Object> monthMap = new HashMap<String, Object>();
                monthMap.put("storeId", storeId);
                monthMap.put("beginDay", monthDay);
                monthMap.put("endDay", monthDay);
                
                BigDecimal totalAmount = new BigDecimal(0);
                if (deptId == -1) {
                    Map<String, Object> weekTatalMap = orderInfoMapper.selectTatalCashAmount(monthMap);
                    totalAmount = new BigDecimal(weekTatalMap.get("totalAmount").toString());
                }
                else {
                    monthMap.put("orType", 5);
                    monthMap.put("deptId", deptId);
                    totalAmount = orderDetailMapper.selectTataiRealPriceByType(monthMap);
                }
                
                tendencyList.add(totalAmount);
                
                dateList.add(monthDay.substring(6, 10));
            }
        }
        else {
            map.put("beginDay", DateUtil.getMinMonthDateStr().substring(0, 4) + "-01-01");
            map.put("endDay", DateUtil.getCurDate());
            
            DateFormat df = new SimpleDateFormat("yyyy-MM");
            
            for (int i = 12; i > 0; i--) {
                
                Date yearDay = DateUtil.getDateMonthsBefore(new Date(), i);
                
                Map<String, Object> yearMap = new HashMap<String, Object>();
                yearMap.put("storeId", storeId);
                yearMap.put("beginDay", DateUtil.getMinMonthDateValue(yearDay));
                yearMap.put("endDay", DateUtil.getMaxMonthDateValue(yearDay));
                
                BigDecimal totalAmount = new BigDecimal(0);
                if (deptId == -1) {
                    Map<String, Object> yearTatalMap = orderInfoMapper.selectTatalCashAmount(yearMap);
                    totalAmount = new BigDecimal(yearTatalMap.get("totalAmount").toString());
                }
                else {
                    yearMap.put("orType", 5);
                    yearMap.put("deptId", deptId);
                    totalAmount = orderDetailMapper.selectTataiRealPriceByType(yearMap);
                }
                tendencyList.add(totalAmount);
                
                dateList.add(df.format(yearDay));
            }
        }
        
        
        if (deptId == -1) {
            Map<String, Object> tatalMap = orderInfoMapper.selectTatalCashAmount(map);
            
            cashTatailAmountMap.put("tatalMap", tatalMap);
        }
        
        Map<String, Object> tendencyListMap = new HashMap<String, Object>();
        tendencyListMap.put("tendencyList", tendencyList);
        tendencyListMap.put("dateList", dateList);
        
        cashTatailAmountMap.put("tendencyListMap", tendencyListMap);
        
        Map<String, Object> cashAmountMap = new HashMap<String, Object>();
        
        BigDecimal tatailAmount = new BigDecimal(0);
        
        for (int i = 1; i < 5; i++) {
            map.put("orType", i);
            map.put("deptId", deptId);
            BigDecimal cashAmount = orderDetailMapper.selectTataiRealPriceByType(map);
            if (i == 1) {
                cashAmountMap.put("projectCashAmount", cashAmount);
                tatailAmount = tatailAmount.add(cashAmount);
            }
            else if (i == 2){
                cashAmountMap.put("goodsCashAmount", cashAmount);
                tatailAmount = tatailAmount.add(cashAmount);
            }
            else if (i == 3){
                cashAmountMap.put("comboCashAmount", cashAmount);
                tatailAmount = tatailAmount.add(cashAmount);
            }
            else {
                cashAmountMap.put("chargeCashAmount", cashAmount);
                tatailAmount = tatailAmount.add(cashAmount);
            }
        }
        
        cashAmountMap.put("tatailAmount", tatailAmount);
        
        cashTatailAmountMap.put("cashAmountMap", cashAmountMap);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, cashTatailAmountMap);
    }
    
    /**
     * 卡金业绩
    * @author 王大爷
    * @date 2016年1月18日 下午6:00:10
    * @param storeId 门店标识
    * @param chooseType 选择类型（1、日，2、月，3、年）
    * @param deptId 门店标识
    * @return BaseDto
     */
    public BaseDto cardAmountService(Integer storeId, Integer chooseType, Integer deptId) {
        Map<String, Object> cashTatailAmountMap = new HashMap<String, Object>();
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put("storeId", storeId);
                
        List<BigDecimal> tendencyList = new ArrayList<BigDecimal>();
        
        List<String> dateList = new ArrayList<String>();
        
        String nowDay = DateUtil.getCurDate();
        
        if (chooseType == 1) {
            String beginDay = DateUtil.getCurDate();
            map.put("beginDay", beginDay);
            map.put("endDay", beginDay);
            
            for (int i =6; i >=0; i--) {
                
                String weekDay = DateUtil.getDateDaysBeforeStr(nowDay, i);
                
                Map<String, Object> weekMap = new HashMap<String, Object>();
                weekMap.put("storeId", storeId);
                weekMap.put("beginDay", weekDay);
                weekMap.put("endDay", weekDay);
                weekMap.put("orType", 5);
                weekMap.put("deptId", deptId);
                BigDecimal totalAmount = orderDetailMapper.selectTataiCardByType(weekMap);
                
                dateList.add(weekDay.substring(6, 10));
                
                tendencyList.add(totalAmount);
            }
        }
        else if (chooseType == 2) {
            map.put("beginDay", DateUtil.getMinMonthDateStr());
            map.put("endDay", DateUtil.getCurDate());
                        
            for (int i =29; i >= 0; i--) {
                
                String monthDay = DateUtil.getDateDaysBeforeStr(nowDay, i);
                
                Map<String, Object> monthMap = new HashMap<String, Object>();
                monthMap.put("storeId", storeId);
                monthMap.put("beginDay", monthDay);
                monthMap.put("endDay", monthDay);
                monthMap.put("orType", 5);
                monthMap.put("deptId", deptId);
                BigDecimal totalAmount = orderDetailMapper.selectTataiCardByType(monthMap);
                dateList.add(monthDay.substring(6, 10));
                tendencyList.add(totalAmount);
            }
        }
        else {
            map.put("beginDay", DateUtil.getMinMonthDateStr().substring(0, 4) + "-01-01");
            map.put("endDay", DateUtil.getCurDate());
            
            DateFormat df = new SimpleDateFormat("yyyy-MM");
            
            for (int i = 12; i > 0; i--) {
                
                Date yearDay = DateUtil.getDateMonthsBefore(new Date(), i);
                
                Map<String, Object> yearMap = new HashMap<String, Object>();
                yearMap.put("storeId", storeId);
                yearMap.put("beginDay", DateUtil.getMinMonthDateValue(yearDay));
                yearMap.put("endDay", DateUtil.getMaxMonthDateValue(yearDay));
                yearMap.put("orType", 5);
                yearMap.put("deptId", deptId);
                BigDecimal totalAmount = orderDetailMapper.selectTataiCardByType(yearMap);
                dateList.add(df.format(yearDay));
                tendencyList.add(totalAmount);
            }
        }
        
        Map<String, Object> tendencyListMap = new HashMap<String, Object>();
        tendencyListMap.put("tendencyList", tendencyList);
        tendencyListMap.put("dateList", dateList);
        
        cashTatailAmountMap.put("tendencyListMap", tendencyListMap);
        
        Map<String, Object> cashAmountMap = new HashMap<String, Object>();
        
        BigDecimal tatailAmount = new BigDecimal(0);
        
        for (int i = 1; i < 4; i++) {
            map.put("orType", i);
            map.put("deptId", deptId);
            BigDecimal cashAmount = orderDetailMapper.selectTataiCardByType(map);
            if (i == 1) {
                cashAmountMap.put("projectCashAmount", cashAmount);
                tatailAmount = tatailAmount.add(cashAmount);
            }
            else if (i == 2){
                cashAmountMap.put("goodsCashAmount", cashAmount);
                tatailAmount = tatailAmount.add(cashAmount);
            }
            else {
                cashAmountMap.put("comboCashAmount", cashAmount);
                tatailAmount = tatailAmount.add(cashAmount);
            }
        }
        
        cashAmountMap.put("tatailAmount", tatailAmount);
        
        cashTatailAmountMap.put("cashAmountMap", cashAmountMap);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, cashTatailAmountMap);
    }
    
    /**
     * 项目业绩
    * @author 王大爷
    * @date 2016年1月18日 下午6:00:10
    * @param storeId 门店标识
    * @param chooseType 选择类型（1、日，2、月，3、年）
    * @param deptId 门店标识
    * @return BaseDto
     */
    public BaseDto projectAmountService(Integer storeId, Integer chooseType, Integer deptId) {
        Map<String, Object> cashTatailAmountMap = new HashMap<String, Object>();
        Map<String, Integer> storeMap = new HashMap<String, Integer>();
        storeMap.put("storeId", storeId);
        storeMap.put("deptId", deptId);
        List<ProjectCategory> categoryList = projectCategoryMapper.selectAllCategoryByStoreIdOrDeptId(storeMap);
        
        List<String> categoryNameList = new ArrayList<String>();
        
        List<BigDecimal> cashTatailAmountList = new ArrayList<BigDecimal>();
        
        List<BigDecimal> cardTatailAmountList = new ArrayList<BigDecimal>();
                
        Map<String, Object> categoryMap = new HashMap<String, Object>();
        
        Map<String, Object> amountMap = new HashMap<String, Object>();
        
        Map<String, Object> sizeMap = new HashMap<String, Object>();
        
        Map<String, Object> amountTypeMap = new HashMap<String, Object>();
        
        List<BigDecimal> tendencyList = new ArrayList<BigDecimal>();
        
        List<String> dateList = new ArrayList<String>();
        
        amountMap.put("storeId", storeId);
        amountMap.put("deptId", deptId);
        
        String nowDay = DateUtil.getCurDate();
        
        if (chooseType == 1) {
            
            amountMap.put("beginDay", nowDay);
            amountMap.put("endDay", nowDay);
            
            for (int i =6; i >= 0; i--) {
                
                String weekDay = DateUtil.getDateDaysBeforeStr(nowDay, i);
                
                Map<String, Object> weekMap = new HashMap<String, Object>();
                weekMap.put("storeId", storeId);
                weekMap.put("beginDay", weekDay);
                weekMap.put("endDay", weekDay);
                weekMap.put("orderType", 1);
                weekMap.put("deptId", deptId);
                
                //不同结账方式的项目金额
                BigDecimal totalAmount = orderDetailMapper.selectTataiProject(weekMap);
                
                //用套餐抵扣的项目
                BigDecimal totalComboAmount = orderDetailMapper.selectTataiProjectUseCombo(weekMap);
                dateList.add(weekDay.substring(6, 10));
                tendencyList.add(totalAmount.add(totalComboAmount));
            }
            
            packageDataProject(categoryList, categoryNameList, amountMap, cashTatailAmountList, cardTatailAmountList, sizeMap, amountTypeMap);

        }
        else if (chooseType == 2) {

            amountMap.put("beginDay", DateUtil.getMinMonthDateStr());
            amountMap.put("endDay", nowDay);
            
            for (int i =29; i >= 0; i--) {
                
                String monthDay = DateUtil.getDateDaysBeforeStr(nowDay, i);
                
                Map<String, Object> monthMap = new HashMap<String, Object>();
                monthMap.put("storeId", storeId);
                monthMap.put("beginDay", monthDay);
                monthMap.put("endDay", monthDay);
                monthMap.put("orderType", 1);
                monthMap.put("deptId", deptId);
                BigDecimal totalAmount = orderDetailMapper.selectTataiProject(monthMap);
                //用套餐抵扣的项目
                BigDecimal totalComboAmount = orderDetailMapper.selectTataiProjectUseCombo(monthMap);
                dateList.add(monthDay.substring(6, 10));
                
                tendencyList.add(totalAmount.add(totalComboAmount));
            }
            
            packageDataProject(categoryList, categoryNameList, amountMap, cashTatailAmountList, cardTatailAmountList, sizeMap, amountTypeMap);
        }
        else {

            amountMap.put("beginDay", DateUtil.getMinMonthDateStr().substring(0, 4) + "-01-01");
            amountMap.put("endDay", DateUtil.getCurDate());
            
            DateFormat df = new SimpleDateFormat("yyyy-MM");
            
            for (int i = 12; i > 0; i--) {
                
                Date yearDay = DateUtil.getDateMonthsBefore(new Date(), i);
                
                Map<String, Object> yearMap = new HashMap<String, Object>();
                yearMap.put("storeId", storeId);
                yearMap.put("beginDay", DateUtil.getMinMonthDateValue(yearDay));
                yearMap.put("endDay", DateUtil.getMaxMonthDateValue(yearDay));
                yearMap.put("orderType", 1);
                yearMap.put("deptId", deptId);
                BigDecimal totalAmount = orderDetailMapper.selectTataiProject(yearMap);
                //用套餐抵扣的项目
                BigDecimal totalComboAmount = orderDetailMapper.selectTataiProjectUseCombo(yearMap);
                dateList.add(df.format(yearDay));
                
                tendencyList.add(totalAmount.add(totalComboAmount));
            }
            
            packageDataProject(categoryList, categoryNameList, amountMap, cashTatailAmountList, cardTatailAmountList, sizeMap, amountTypeMap);

        }
        
        categoryMap.put("categoryNameList", categoryNameList);
        
        categoryMap.put("cashTatailAmountList", cashTatailAmountList);
        
        categoryMap.put("cardTatailAmountList", cardTatailAmountList);
                
        categoryMap.put("projectType", 1);
        
        cashTatailAmountMap.put("categoryMap", categoryMap);
        
        cashTatailAmountMap.put("sizeMap", sizeMap);
        
        cashTatailAmountMap.put("amountTypeMap", amountTypeMap);
        
        Map<String, Object> tendencyListMap = new HashMap<String, Object>();
        tendencyListMap.put("tendencyList", tendencyList);
        tendencyListMap.put("dateList", dateList);
        
        cashTatailAmountMap.put("tendencyListMap", tendencyListMap);
                
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, cashTatailAmountMap);
    }
    
    /**
     * 套餐业绩
    * @author 王大爷
    * @date 2016年1月18日 下午6:00:10
    * @param storeId 门店标识
    * @param chooseType 选择类型（1、日，2、月，3、年）
    * @param deptId 门店标识
    * @return BaseDto
     */
    public BaseDto comboAmountService(Integer storeId, Integer chooseType, Integer deptId) {
        Map<String, Object> cashTatailAmountMap = new HashMap<String, Object>();
        
        Map<String, Object> amountMap = new HashMap<String, Object>();
        
        amountMap.put("storeId", storeId);
        amountMap.put("deptId", deptId);
        
        String nowDay = DateUtil.getCurDate();
        
        //卡金、现金 次数卡月季年卡对比
        List<BigDecimal> cardMoneyList =new ArrayList<BigDecimal>();
        List<BigDecimal> cashMoneyList =new ArrayList<BigDecimal>();
                
        if (chooseType == 1) {
            
            amountMap.put("beginDay", nowDay);
            amountMap.put("endDay", nowDay);
        }
        else if (chooseType == 2) {

            amountMap.put("beginDay", DateUtil.getMinMonthDateStr());
            amountMap.put("endDay", nowDay);
        }
        else {

            amountMap.put("beginDay", DateUtil.getMinMonthDateStr().substring(0, 4) + "-01-01");
            amountMap.put("endDay", DateUtil.getCurDate());
        }
        
        //查询次数卡、月季年卡总次数
        Map<String, Integer> tatailTimeMap = orderDetailMapper.selectComboTatailTime(amountMap);
        
        cashTatailAmountMap.put("seriesComboTatailTime", tatailTimeMap.get("seriesComboTatailTime"));
        cashTatailAmountMap.put("timeComboTatailTime", tatailTimeMap.get("timeComboTatailTime"));
        
        for (int i = 1; i < 3; i++) {
            amountMap.put("orderType", 3);
            
            amountMap.put("amountType", i);
            
            int a = 1;
            for (int j = 0; j < 2; j++) {
                amountMap.put("isCountLimit", a);
                
                BigDecimal amount = orderDetailMapper.selectTataiAmountProject(amountMap);
                
                if (i == 1) {
                    cardMoneyList.add(amount);
                }
                else {
                    cashMoneyList.add(amount);
                }
                a--;
            }
        }
        
        Map<String, Integer> comboMap = new HashMap<String, Integer>();
        comboMap.put("storeId", storeId);
        comboMap.put("deptId", deptId);
        comboMap.put("isCountLimit", 1);
        List<ComboStatementDto> timeStatementDtoList = comboInfoMapper.selectComboByTimeSize(comboMap);
        
        //组装套餐数据
        for (ComboStatementDto comboStatementDto : timeStatementDtoList) {
            amountMap.put("comboId", comboStatementDto.getComboId());
            Integer comboTime = orderDetailMapper.selectBuyComboTime(amountMap);
            
            comboStatementDto.setNewAddTime(comboTime);
            
            //疗程套餐项目
            List<ComboStatementProjectDto> statementProjectDtoList = comboStatementDto.getStatementProjectDtoList();
            Map<String, Object> tataiMap = new HashMap<String, Object>();
            
            tataiMap.put("comboId", comboStatementDto.getComboId());
            
            for (ComboStatementProjectDto comboStatementProjectDto : statementProjectDtoList) {
                tataiMap.put("projectId", comboStatementProjectDto.getProjectId());
                
                Map<String, Object> tataiTimeMap = comboInfoMapper.getComboTatailTime(tataiMap);
                //新增购买次数
                comboStatementProjectDto.setNewAddTime(comboTime * comboStatementProjectDto.getProjectCount());
                
                amountMap.put("projectId", comboStatementProjectDto.getProjectId());
                
                Integer consumeTime = orderDetailMapper.selectConsumeTime(amountMap);
                
                //新增消费次数
                comboStatementProjectDto.setConsumeTime(consumeTime);
                
                if (tataiTimeMap == null) {
                	comboStatementProjectDto.setResidueTime(0);
                    comboStatementProjectDto.setConsumeTatailTime(0);
                }
                else {
                	Integer residueTime = Integer.valueOf(tataiTimeMap.get("residueTime").toString());
                    
                	comboStatementProjectDto.setResidueTime(residueTime);
                    comboStatementProjectDto.setConsumeTatailTime(Integer.valueOf(tataiTimeMap.get("consumeTatailTime").toString()));
                }
                
            }
        }
        
        comboMap.put("isCountLimit", 0);
        List<ComboStatementDto> packageStatementDtoList = comboInfoMapper.selectComboByTimeSize(comboMap);
        
        for (ComboStatementDto comboStatementDto : packageStatementDtoList) {
        	//查询套餐有效个数
            Integer validNumber = memberComboRecordMapper.selectValidByComboId(comboStatementDto.getComboId());
            comboStatementDto.setValidNumber(validNumber);
            
            amountMap.put("comboId", comboStatementDto.getComboId());
            Integer comboTime = orderDetailMapper.selectBuyComboTime(amountMap);
            
             //新增购买次数
            comboStatementDto.setNewAddTime(comboTime);
            
            Integer newUseTime = orderDetailMapper.selectComboStatementTime(amountMap);
            
            comboStatementDto.setNewUseTime(newUseTime);
        }
        
        cashTatailAmountMap.put("timeStatementDtoList", timeStatementDtoList);
        cashTatailAmountMap.put("packageStatementDtoList", packageStatementDtoList);
        
        cashTatailAmountMap.put("cardMoneyList", cardMoneyList);
        cashTatailAmountMap.put("cashMoneyList", cashMoneyList);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, cashTatailAmountMap);
    }
    
    /**
     * 商品业绩
    * @author 王大爷
    * @date 2016年1月18日 下午6:00:10
    * @param storeId 门店标识
    * @param chooseType 选择类型（1、日，2、月，3、年）
    * @param deptId 门店标识
    * @return BaseDto
     */
    public BaseDto goodsAmountService(Integer storeId, Integer chooseType, Integer deptId) {
    	Map<String, Object> cashTatailAmountMap = new HashMap<String, Object>();
    	
        Map<String, Integer> storeMap = new HashMap<String, Integer>();
        storeMap.put("storeId", storeId);
        storeMap.put("deptId", deptId);
        List<GoodsCategory> categoryList = goodsCategoryMapper.selectAllCategoryByStoreIdOrDeptId(storeMap);
        //类别名称 
        List<String> categoryNameList = new ArrayList<String>();
        //现金汇总
        List<BigDecimal> cashTatailAmountList = new ArrayList<BigDecimal>();
        //卡金汇总
        List<BigDecimal> cardTatailAmountList = new ArrayList<BigDecimal>();
        
        List<Map<String, Object>> goodsTimeList = new ArrayList<Map<String, Object>>();
         
        Map<String, Object> categoryMap = new HashMap<String, Object>();
         
        Map<String, Object> amountMap = new HashMap<String, Object>();
                  
        Map<String, Object> amountTypeMap = new HashMap<String, Object>();
         
        List<BigDecimal> tendencyList = new ArrayList<BigDecimal>();
        
        List<String> dateList = new ArrayList<String>();
        
        //总购买次数
        Integer tailTime = 0;
        
        amountMap.put("storeId", storeId);
        amountMap.put("deptId", deptId);
         
        String nowDay = DateUtil.getCurDate();
         
        if (chooseType == 1) {
             
            amountMap.put("beginDay", nowDay);
            amountMap.put("endDay", nowDay);
             
            for (int i =6; i >= 0; i--) {
                 
                String weekDay = DateUtil.getDateDaysBeforeStr(nowDay, i);
                 
                Map<String, Object> weekMap = new HashMap<String, Object>();
                weekMap.put("storeId", storeId);
                weekMap.put("beginDay", weekDay);
                weekMap.put("endDay", weekDay);
                weekMap.put("orderType", 2);
                weekMap.put("deptId", deptId);
                BigDecimal totalAmount = orderDetailMapper.selectTataiProject(weekMap);
                
                totalAmount = totalAmount.add(orderDetailMapper.selectTataiProjectUseComboGoods(weekMap));
                dateList.add(weekDay.substring(6, 10));
                tendencyList.add(totalAmount);
            }
             
            tailTime = packageDataGoods(categoryList, categoryNameList, amountMap, cashTatailAmountList, cardTatailAmountList, goodsTimeList);

        }
        else if (chooseType == 2) {

            amountMap.put("beginDay", DateUtil.getMinMonthDateStr());
            amountMap.put("endDay", nowDay);
             
            for (int i =29; i >= 0; i--) {
                 
                String monthDay = DateUtil.getDateDaysBeforeStr(nowDay, i);
                 
                Map<String, Object> monthMap = new HashMap<String, Object>();
                monthMap.put("storeId", storeId);
                monthMap.put("beginDay", monthDay);
                monthMap.put("endDay", monthDay);
                monthMap.put("orderType", 2);
                monthMap.put("deptId", deptId);
                BigDecimal totalAmount = orderDetailMapper.selectTataiProject(monthMap);
                
                totalAmount = totalAmount.add(orderDetailMapper.selectTataiProjectUseComboGoods(monthMap));
                dateList.add(monthDay.substring(6, 10));
                tendencyList.add(totalAmount);
            }
             
            tailTime = packageDataGoods(categoryList, categoryNameList, amountMap, cashTatailAmountList, cardTatailAmountList, goodsTimeList);

        }
        else {

            amountMap.put("beginDay", DateUtil.getMinMonthDateStr().substring(0, 4) + "-01-01");
            amountMap.put("endDay", DateUtil.getCurDate());
             
            DateFormat df = new SimpleDateFormat("yyyy-MM");
            
            for (int i = 12; i > 0; i--) {
                 
                Date yearDay = DateUtil.getDateMonthsBefore(new Date(), i);
                 
                Map<String, Object> yearMap = new HashMap<String, Object>();
                yearMap.put("storeId", storeId);
                yearMap.put("beginDay", DateUtil.getMinMonthDateValue(yearDay));
                yearMap.put("endDay", DateUtil.getMaxMonthDateValue(yearDay));
                yearMap.put("orderType", 2);
                yearMap.put("deptId", deptId);
                BigDecimal totalAmount = orderDetailMapper.selectTataiProject(yearMap);
                
                totalAmount = totalAmount.add(orderDetailMapper.selectTataiProjectUseComboGoods(yearMap));
                dateList.add(df.format(yearDay));
                tendencyList.add(totalAmount);
            }
             
            tailTime = packageDataGoods(categoryList, categoryNameList, amountMap, cashTatailAmountList, cardTatailAmountList, goodsTimeList);

        }
        amountMap.put("sortType", 1);
        //商品排行查询(金额排序)
        List<Map<String, Object>> rankingAmountList = orderDetailMapper.selectRankingGoodsCount(amountMap);
        amountMap.put("sortType", 2);
        //商品排行查询（销量排行）
        List<Map<String, Object>> rankingNumberList = orderDetailMapper.selectRankingGoodsCount(amountMap);
        
        cashTatailAmountMap.put("rankingAmountList", rankingAmountList);
        
        cashTatailAmountMap.put("rankingNumberList", rankingNumberList);
        
        categoryMap.put("categoryNameList", categoryNameList);
         
        categoryMap.put("cashTatailAmountList", cashTatailAmountList);
         
        categoryMap.put("cardTatailAmountList", cardTatailAmountList);
                
        categoryMap.put("projectType", 2);
         
        cashTatailAmountMap.put("categoryMap", categoryMap);
                  
        amountTypeMap.put("goodsTimeList", goodsTimeList);
        
        amountTypeMap.put("tailTime", tailTime);
        
        cashTatailAmountMap.put("amountTypeMap", amountTypeMap);
        
        //趋势
        Map<String, Object> tendencyListMap = new HashMap<String, Object>();
        tendencyListMap.put("tendencyList", tendencyList);
        tendencyListMap.put("dateList", dateList);
        
        cashTatailAmountMap.put("tendencyListMap", tendencyListMap);
        
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, cashTatailAmountMap);
    }
    
    /**
     * 组装数据(项目)
    * @author 王大爷
    * @date 2016年1月19日 下午9:05:44
    * @param categoryList categoryList
    * @param categoryNameList categoryNameList
    * @param amountMap amountMap
    * @param cashTatailAmountList cashTatailAmountList
    * @param cardTatailAmountList cardTatailAmountList
    * @param sizeMap sizeMap
    * @param amountTypeMap amountTypeMap
     */
    public void packageDataProject(List<ProjectCategory> categoryList, List<String> categoryNameList, Map<String, Object> amountMap, 
            List<BigDecimal> cashTatailAmountList, List<BigDecimal> cardTatailAmountList, Map<String, Object> sizeMap, 
            Map<String, Object> amountTypeMap) {
    	
        for (ProjectCategory projectCategory : categoryList) {
        	String categoryName = projectCategory.getCategoryName();
        	/*.substring(0, 4)+"<br>"+categoryName.substring(4)*/
        	/*if (categoryName.length() > 4) {
        		categoryName = "<span>"+categoryName+"</span>";
        	}*/
            categoryNameList.add(categoryName);
            
            amountMap.put("orderType", 1);
            amountMap.put("categoryId", projectCategory.getCategoryId());
            
            amountMap.put("amountType", 1);
            
            BigDecimal cardTatailAmount = orderDetailMapper.selectTataiAmountProject(amountMap);
            
            //套餐抵扣
            cardTatailAmount = cardTatailAmount.add(orderDetailMapper.selectTataiAmountUseComboProject(amountMap));
            
            cardTatailAmountList.add(cardTatailAmount);
            
            amountMap.put("amountType", 2);
            
            BigDecimal cashTatailAmount = orderDetailMapper.selectTataiAmountProject(amountMap);
            
            cashTatailAmountList.add(cashTatailAmount);
        }
        
        amountMap.put("projectType", 1);
        BigDecimal bigAmount = orderDetailMapper.selectTataiProjectSizeType(amountMap);
        
        bigAmount = bigAmount.add(orderDetailMapper.selectTataiUseComboProjectSizeType(amountMap));
        
        amountMap.put("projectType", 2);
        BigDecimal smallAmount = orderDetailMapper.selectTataiProjectSizeType(amountMap);
        
        smallAmount = smallAmount.add(orderDetailMapper.selectTataiUseComboProjectSizeType(amountMap));
        
        sizeMap.put("tatailAmount", bigAmount.add(smallAmount));
        sizeMap.put("smallAmount", smallAmount);
        sizeMap.put("bigAmount", bigAmount);
        
        amountMap.put("caType", 1);
        BigDecimal cardAmount = orderDetailMapper.selectTataiProjectAmountType(amountMap);
        //套餐抵扣
        cardAmount = cardAmount.add(orderDetailMapper.selectTataiUseComboProjectAmountType(amountMap));
        
        amountMap.put("caType", 2);
        BigDecimal cashAmount = orderDetailMapper.selectTataiProjectAmountType(amountMap);
        
        amountTypeMap.put("tatailAmount", cardAmount.add(cashAmount));
        amountTypeMap.put("cardAmount", cardAmount);
        amountTypeMap.put("cashAmount", cashAmount);
    }
    
    /**
     * 组装数据（商品）
    * @author 王大爷
    * @date 2016年1月19日 下午9:05:44
    * @param categoryList categoryList
    * @param categoryNameList categoryNameList
    * @param amountMap amountMap
    * @param cashTatailAmountList cashTatailAmountList
    * @param cardTatailAmountList cardTatailAmountList
    * @param goodsTimeList 商品销售次数
    * @return Integer
     */
    public Integer packageDataGoods(List<GoodsCategory> categoryList, List<String> categoryNameList, Map<String, Object> amountMap, 
            List<BigDecimal> cashTatailAmountList, List<BigDecimal> cardTatailAmountList,
            List<Map<String, Object>> goodsTimeList) {
        
    	Integer tailTime = 0;
    	
    	for (GoodsCategory goodsCategory : categoryList) {
            
            String categoryName = goodsCategory.getCategoryName();
        	
        	/*if (categoryName.length() > 4) {
        		categoryName = "<span>"+categoryName+"</span>";
        	}*/
            categoryNameList.add(categoryName);
            
            amountMap.put("orderType", 2);
            amountMap.put("categoryId", goodsCategory.getCategoryId());
            
            amountMap.put("amountType", 1);
            
            BigDecimal cardTatailAmount = orderDetailMapper.selectTataiAmountProject(amountMap);
            cardTatailAmount = cardTatailAmount.add(orderDetailMapper.selectTataiAmountUseComboGoods(amountMap));
            cardTatailAmountList.add(cardTatailAmount);
            
            amountMap.put("amountType", 2);
            
            BigDecimal cashTatailAmount = orderDetailMapper.selectTataiAmountProject(amountMap);
            cashTatailAmount = cashTatailAmount.add(orderDetailMapper.selectTataiAmountUseComboGoods(amountMap));
            cashTatailAmountList.add(cashTatailAmount);
            
            Integer goodsTime = orderDetailMapper.selectBuyGoodsTime(amountMap);
            
            goodsTime = goodsTime + orderDetailMapper.selectBuyGoodsComboTime(amountMap);
            
            tailTime = tailTime + goodsTime;
            
            Map<String, Object> timeMap = new HashMap<String, Object>();
            timeMap.put("name", goodsCategory.getCategoryName());
            timeMap.put("y", goodsTime);
            
            goodsTimeList.add(timeMap);
        }
    	return tailTime;
    }

    /**
     * 展示客情分析数据
    * @author 高国藩
    * @date 2016年1月21日 下午2:30:21
    * @param storeId    门店
    * @return           跳转页面
     */
    public ModelAndView viewCustomerAnalysis(Integer storeId) {
        Map<String, Object> paramFlow = new HashMap<>();
        paramFlow.put("searchType", "day");
        paramFlow.put("storeId", storeId);
        paramFlow.put("flowStartDate", DateUtil.getCurDate());
        paramFlow.put("flowStopDate", DateUtil.getCurDate());
        CustomerAnalysisDto customerAnalysisFlow = orderInfoMapper.selectCustomerFlow(paramFlow);
        paramFlow.put("fluxStartDate", DateUtil.getCurrLastDay()+" 22:00:00");
        paramFlow.put("fluxStopDate", DateUtil.getCurTime());
        CustomerAnalysisDto customerAnalysisFlux = orderInfoMapper.selectCustomerFlux(paramFlow);
        List<Integer> customerAnalysisFlol = new ArrayList<>();
        List<BigDecimal> customerAnalysisProject = new ArrayList<>();
        Integer num = 7;
        for (int i = 1; i <= num; i++) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
            calendar.add(Calendar.DAY_OF_MONTH, -(i-1));
            paramFlow.put("flolStopDate", sf1.format(calendar.getTime()));
            paramFlow.put("flolStartDate", sf1.format(calendar.getTime()));
            Integer count = orderInfoMapper.selectCustomerFlol(paramFlow);
            customerAnalysisFlol.add(count);
            BigDecimal count2 = orderInfoMapper.selectCustomerAnalysisProject(paramFlow);
            customerAnalysisProject.add(count2);
        }
        ModelAndView view = new ModelAndView(View.BossPage.CUSTOMER_ANALYSIS);
        addCustomerAnalysisKdj(storeId, paramFlow, view, null);
        view.addObject("customerAnalysisFlow", JSONObject.fromObject(customerAnalysisFlow).toString());
        view.addObject("customerAnalysisFlux", JSONObject.fromObject(customerAnalysisFlux).toString());
        Collections.reverse(customerAnalysisFlol);
        view.addObject("customerAnalysisFlol", JSONArray.fromObject(customerAnalysisFlol).toString());
        view.addObject("customerAnalysisProject", JSONArray.fromObject(customerAnalysisProject).toString());
        view.addObject("storeId", storeId);
        return view;
    }


    /**
     * 客单价数据查询
    * @author 高国藩
    * @date 2016年1月22日 下午8:20:01
    * @param storeId    门店ID
    * @param paramFlow  查询条件
    * @param view       数据插入
    * @param dto        数据插入
     */
    private void addCustomerAnalysisKdj(Integer storeId, Map<String, Object> paramFlow, ModelAndView view, JSONObject dto) {
        List<Integer> customerAnalysisKdj = new ArrayList<>();
        List<Integer> customerAnalysisKbt = new ArrayList<>();
        List<MemberLevelDto> levels = memberLevelMapper.selectByStoreId(storeId);
        List<String> levelName = new ArrayList<>();
        Integer amount = 0;
        for (MemberLevelDto memberLevel : levels) {
            paramFlow.put("levelId", memberLevel.getLevelId());
            List<BigDecimal> bigDecimals = orderInfoMapper.selectCustomerAnalysisLevel(paramFlow);
            Integer temp = bigDecimals.stream().filter(num -> num != null).mapToInt(num -> num.intValue()).sum();
            amount += temp;
            if (bigDecimals.size()==0){
                customerAnalysisKdj.add(0);
            }
            else {
                customerAnalysisKdj.add(temp.intValue()/bigDecimals.size());
            }
            customerAnalysisKbt.add(temp.intValue());
            levelName.add(memberLevel.getLevelName());
        }
        levelName.add("散客");
        List<BigDecimal> bigDecimals = orderInfoMapper.selectCustomerAnalysisSank(paramFlow);
        Integer temp = bigDecimals.stream().filter(num -> num != null).mapToInt(num -> num.intValue()).sum();
        amount += temp;
//        for (int i = 0; i < bigDecimals.size(); i++) {
//            temp += bigDecimals.get(i).intValue();
//            amount += bigDecimals.get(i).intValue();
//        }
        if (bigDecimals.size()==0){
            customerAnalysisKdj.add(0);
        }
        else {
            customerAnalysisKdj.add(temp.intValue()/bigDecimals.size());
        }
        customerAnalysisKbt.add(temp.intValue());
        
        List<List<Object>> kbk = new ArrayList<>();
        //拼出饼状图(项目分析饼图)
        for (int i = 0; i < levelName.size(); i++) {
            List<Object> objects = new ArrayList<>();
            objects.add(levelName.get(i));
            objects.add(customerAnalysisKbt.get(i));
            kbk.add(objects);
        }
        if (view!=null){
            view.addObject("customerAnalysisKdj", JSONArray.fromObject(customerAnalysisKdj).toString());
            view.addObject("customerAnalysisKbt", JSONArray.fromObject(customerAnalysisKbt).toString());
            view.addObject("levels", JSONArray.fromObject(levels).toString());
            view.addObject("levelName", JSONArray.fromObject(levelName).toString());
            view.addObject("amount", amount);
            view.addObject("kbk", JSONArray.fromObject(kbk).toString());
        }
        if (dto!=null){
            dto.put("customerAnalysisKdj", JSONArray.fromObject(customerAnalysisKdj).toString());
            dto.put("customerAnalysisKbt", JSONArray.fromObject(customerAnalysisKbt).toString());
            dto.put("levels", JSONArray.fromObject(levels).toString());
            dto.put("levelName", JSONArray.fromObject(levelName).toString());
            dto.put("amount", amount);
            dto.put("kbk", kbk);
        }
        
    }

    /**
     * 更改条件查询客情分析数据
    * @author 高国藩
    * @date 2016年1月21日 下午3:15:32
    * @param storeId        门店信息
    * @param serchType      查询条件(年月日)
    * @return               查询数据
     */
    public BaseDto changeCustomerAnalysis(Integer storeId, String serchType) {
        Map<String, Object> paramFlow = new HashMap<>();
        paramFlow.put("searchType", serchType);
        paramFlow.put("storeId", storeId);
        List<Integer> customerAnalysisFlol = new ArrayList<>();
        List<BigDecimal> customerAnalysisProject = new ArrayList<>();
        if ("day".equals(serchType)){
            paramFlow.put("flowStartDate", DateUtil.getCurDate());
            paramFlow.put("flowStopDate", DateUtil.getCurDate());
            paramFlow.put("fluxStartDate", DateUtil.getCurrLastDay()+" 22:00:00");
            paramFlow.put("fluxStopDate", DateUtil.getCurTime());
            initFlol(7, customerAnalysisFlol, customerAnalysisProject, storeId, "day");
        }
        if ("month".equals(serchType)){
            paramFlow.put("flowStartDate", DateUtil.getMinMonthDateStr());
            paramFlow.put("flowStopDate", DateUtil.getMaxMonthDateStr());
            paramFlow.put("fluxStartDate", DateUtil.getLastLastMonthLastDay()+" 22:00:00");
            paramFlow.put("fluxStopDate", DateUtil.getCurTime());
            initFlol(30, customerAnalysisFlol, customerAnalysisProject, storeId, "month");
        }
        if ("year".equals(serchType)){
            paramFlow.put("flowStartDate", DateUtil.getCurrYearFirst());
            paramFlow.put("flowStopDate", DateUtil.getCurrYearLast());
            paramFlow.put("fluxStartDate", DateUtil.getCurrYearFirst()+" 22:00:00");
            paramFlow.put("fluxStopDate", DateUtil.getCurTime());
            initFlol(12, customerAnalysisFlol, customerAnalysisProject, storeId, "year");
            
        }
        
        CustomerAnalysisDto customerAnalysisFlow = orderInfoMapper.selectCustomerFlow(paramFlow);
        CustomerAnalysisDto customerAnalysisFlux = orderInfoMapper.selectCustomerFlux(paramFlow);
        JSONObject dto = new JSONObject();
        Collections.reverse(customerAnalysisFlol);
        dto.put("customerAnalysisFlow", customerAnalysisFlow);
        dto.put("customerAnalysisFlux", customerAnalysisFlux);
        dto.put("customerAnalysisFlol", customerAnalysisFlol);
        dto.put("customerAnalysisProject", customerAnalysisProject);
        addCustomerAnalysisKdj(storeId, paramFlow, null, dto);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, dto);
    }

    /**
     * 查询推算天数之间的数据
    * @author 高国藩
    * @date 2016年1月21日 下午9:18:25
    * @param num                     推算天数
    * @param customerAnalysisFlol    集合数据
    * @param customerAnalysisProject 项目消费流量
    * @param storeId                 门店信息
    * @param serchType               查询类型
     */
    private void initFlol(Integer num, List<Integer> customerAnalysisFlol, List<BigDecimal> customerAnalysisProject, 
            Integer storeId, String serchType) {
        if (!serchType.equals("year")){
            Map<String, Object> paramFlow = new HashMap<>();
            paramFlow.put("storeId", storeId);
            for (int i = 1; i <= num; i++) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
                calendar.add(Calendar.DAY_OF_MONTH, -(i-1));
                paramFlow.put("flolStopDate", sf1.format(calendar.getTime()));
                paramFlow.put("flolStartDate", sf1.format(calendar.getTime()));
                Integer count = orderInfoMapper.selectCustomerFlol(paramFlow);
                customerAnalysisFlol.add(count);
                BigDecimal count2 = orderInfoMapper.selectCustomerAnalysisProject(paramFlow);
                if (count2 == null){
                    customerAnalysisProject.add(new BigDecimal(0));
                }
                else {
                    customerAnalysisProject.add(count2);
                }
//                Calendar calendar = Calendar.getInstance();
//                SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
//                calendar.add(Calendar.DAY_OF_MONTH, -(i-1));
//                paramFlow.put("flolStopDate", sf1.format(calendar.getTime()));
//                Calendar calendar2 = Calendar.getInstance();
//                calendar2.add(Calendar.DAY_OF_MONTH, -i);
//                paramFlow.put("flolStartDate", sf1.format(calendar2.getTime()));
//                Integer count = orderInfoMapper.selectCustomerFlol(paramFlow);
//                customerAnalysisFlol.add(count);
//                BigDecimal count2 = orderInfoMapper.selectCustomerAnalysisProject(paramFlow);
//                if (count2 == null){
//                    customerAnalysisProject.add(new BigDecimal(0));
//                }
//                else {
//                    customerAnalysisProject.add(count2);
//                }
            }
        }
        else {
            Map<String, Object> paramFlow = new HashMap<>();
            paramFlow.put("storeId", storeId);
            for (int i = 1; i <= num; i++) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
                calendar.add(Calendar.MONTH, -(i-1));
                paramFlow.put("flolStopDate", sf1.format(calendar.getTime()));
                Calendar calendar2 = Calendar.getInstance();
                calendar2.add(Calendar.MONTH, -i);
                paramFlow.put("flolStartDate", sf1.format(calendar2.getTime()));
                Integer count = orderInfoMapper.selectCustomerFlol(paramFlow);
                customerAnalysisFlol.add(count);
                BigDecimal count2 = orderInfoMapper.selectCustomerAnalysisProject(paramFlow);
                if (count2 == null){
                    customerAnalysisProject.add(new BigDecimal(0));
                }
                else {
                    customerAnalysisProject.add(count2);
                }
                
            }
        }
    }

    /**
     * 营业分析页面
    * @author 高国藩
    * @date 2016年1月26日 下午4:33:27
    * @param storeId  门店
    * @return         跳转页面
     * @throws ParseException 
     */
    public ModelAndView viewBusinessAnalysis(Integer storeId) throws ParseException {
        Map<String, Object> params = new HashMap<>();
        params.put("startDate", DateUtil.getMinMonthDateStr());
        params.put("stopDate", DateUtil.getMaxMonthDateStr());
        params.put("storeId", storeId);
        BusinessAnalysisDto analysisDto =  orderInfoMapper.selectBusinessInclude(params);
        BusinessAnalysisDto analysisDto2 = orderInfoMapper.selectBusinessConsumption(params);
        //总支出
        BusinessAnalysisDto expenditure = orderInfoMapper.selectStoreExpenses(params);
        if (expenditure == null){
            expenditure = new BusinessAnalysisDto(new BigDecimal(0), new BigDecimal(0), 
                    new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 
                    new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 
                    new BigDecimal(0), new BigDecimal(0), null, new BigDecimal(0), 
                    new BigDecimal(0));
        }
        //在直接运营中加入了项目商品成本价钱
        BigDecimal projectGoodsCost = orderInfoMapper.selectProjectAndGoodsCost(params);
        if (projectGoodsCost == null){
            projectGoodsCost = new BigDecimal(0);
        }
        expenditure.setDirectCost(expenditure.getDirectCost().add(projectGoodsCost));
        
        //指定比例要去掉了
        //BusinessAnalysisDto analysisDto4 = orderInfoMapper.selectStoreAssign(params);
        
        Integer orderCount = orderInfoMapper.selectOrderCount(params);
        //项目列表
        List<CodeLibrary> businessProjectList =  codeName("固定支出");
        params.put("codeName", "固定支出");
        List<BusinessAnalysisDto> nowPays = orderInfoMapper.selectStoreExpensesDetails(params);
        params.put("startDate", DateUtil.getMonthLastStart(DateUtil.getMinMonthDateStr()));
        params.put("stopDate", DateUtil.getMonthLastEnd(DateUtil.getMinMonthDateStr()));
        List<BusinessAnalysisDto> lastPays = orderInfoMapper.selectStoreExpensesDetails(params);
        
        emptyList(businessProjectList, nowPays, lastPays);
        
        ModelAndView view = new ModelAndView(View.BossPage.BUSINESS_ANALYSIS);
        
        //客单价,客成本,客盈利趋势图
        initTendencyChart(DateUtil.getMinMonthDateStr(), params, view, null);
        //营利趋势图
        initTendencyChartInclude(DateUtil.getMinMonthDateStr(), params, view, null);
        
        view.addObject("date", DateUtil.getMinMonthDateStr());
        view.addObject("storeId", storeId);
        view.addObject("include", JSONArray.fromObject(BusinessAnalysisDto.getInclude(analysisDto)).toString());
        view.addObject("consumption", JSONArray.fromObject(BusinessAnalysisDto.getConsumption(analysisDto2)).toString());
        view.addObject("expenditure", expenditure);
        if (expenditure.getFixedCost().add(expenditure.getWriterCost()).add(expenditure.getBriefOperation())
                .add(expenditure.getStaffCosts()).add(expenditure.getAdministration()).add(expenditure.getDirectCost()).intValue() == 0){
            view.addObject("expenditureStatus", 0);
        }
        view.addObject("nowPays", nowPays);
        view.addObject("lastPays", lastPays);
        view.addObject("nowPaysAmount", nowPays.stream().mapToInt(list -> list.getFlowAmount().intValue()).sum());
        view.addObject("lastPaysAmount", lastPays.stream().mapToInt(list -> list.getFlowAmount().intValue()).sum());
        /*if (analysisDto4 == null){
            view.addObject("assignRatio", 0);
        }
        else {
            view.addObject("assignRatio", analysisDto4.getAssignRatio().multiply(new BigDecimal(100)));
        }*/
        view.addObject("orderCount", orderCount);
        
        //客单价
        if (analysisDto == null){
            view.addObject("guestUnitPrice", 0);
        }
        else {
            view.addObject("guestUnitPrice", analysisDto.getCashConsumption().add(analysisDto.getCardConsumption()).
                    add(analysisDto.getComboDeductible()).intValue()/orderCount);
        }
        //客成本
        if (expenditure == null || orderCount == 0){
            view.addObject("customerCost", 0);
        }
        else {
            view.addObject("customerCost", BusinessAnalysisDto.getExpenditureAmount(expenditure).intValue()/orderCount);
        }
        //客盈利
        if (analysisDto == null || orderCount == 0){
            view.addObject("customerProfitability", 0);
        }
        else {
            view.addObject("customerProfitability", (analysisDto.getCashConsumption()
                    .add(analysisDto.getCardConsumption())
                    .add(analysisDto.getComboDeductible()).intValue()
                    -(BusinessAnalysisDto.getExpenditureAmount(expenditure).intValue()))/orderCount);
        }
        return view;
    }
    
    /**
     * 生成月盈利趋势图
    * @author 高国藩
    * @date 2016年1月27日 下午4:22:43
    * @param date        日期  
    * @param params      参数
    * @param view        view 
    * @param result      result
     * @throws ParseException ParseException
     */
    private void initTendencyChartInclude(String date, Map<String, Object> params, ModelAndView view, Map<String, Object> result) {
        List<Integer> yyls = new ArrayList<>();
        List<Integer> ycbs = new ArrayList<>();
        List<Integer> ylrs = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            try {
                params.put("startDate", DateUtil.getMonthLastStart(date));
                params.put("stopDate", DateUtil.getMonthLastEnd(date));
                date = DateUtil.getMonthLastStart(date);
                BusinessAnalysisDto analysisDto =  orderInfoMapper.selectBusinessInclude(params);
                BigDecimal incomne = analysisDto.getCashConsumption().add(analysisDto.getCardConsumption()).add(analysisDto.getComboDeductible());
                BusinessAnalysisDto expenditure = orderInfoMapper.selectStoreExpenses(params);
                int expend = BusinessAnalysisDto.getExpenditureAmount(expenditure).intValue();
                yyls.add(incomne.intValue());
                ycbs.add(expend);
                ylrs.add(incomne.intValue()-expend);
            } 
            catch (Exception e) {
                yyls.add(0);
                ycbs.add(0);
                ylrs.add(0);
            }
        }
        if (view!=null){
            view.addObject("yyls", JSONArray.fromObject(yyls).toString());
            view.addObject("ycbs", JSONArray.fromObject(ycbs).toString());
            view.addObject("ylrs", JSONArray.fromObject(ylrs).toString());
        }
        else {
            result.put("yyls", yyls);
            result.put("ycbs", ycbs);
            result.put("ylrs", ylrs);
        }
    }

    /**
     * 生成客单价,客成本,客盈利趋势图
    * @author 高国藩
    * @date 2016年1月27日 下午4:22:43
    * @param date        日期  
    * @param params      参数
    * @param view        view 
    * @param result      result
     * @throws ParseException ParseException
     */
    private void initTendencyChart(String date, Map<String, Object> params, ModelAndView view, Map<String, Object> result) throws ParseException {
        List<Integer> kdjs = new ArrayList<>();
        List<Integer> kcbs = new ArrayList<>();
        List<Integer> kyls = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            params.put("startDate", DateUtil.getMonthLastStart(date));
            params.put("stopDate", DateUtil.getMonthLastEnd(date));
            date = DateUtil.getMonthLastStart(date);
            BusinessAnalysisDto analysisDto =  orderInfoMapper.selectBusinessInclude(params);
            Integer orderCount = orderInfoMapper.selectOrderCount(params);
            BusinessAnalysisDto expenditure = orderInfoMapper.selectStoreExpenses(params);
            
            try {
                Integer kdj = analysisDto.getCashConsumption()
                        .add(analysisDto.getCardConsumption()).add(analysisDto.getComboDeductible()).intValue()/orderCount;
                Integer kcb = BusinessAnalysisDto.getExpenditureAmount(expenditure).intValue()/orderCount;
                Integer kyl = analysisDto.getCashConsumption()
                        .add(analysisDto.getCardConsumption())
                        .add(analysisDto.getComboDeductible()).intValue()
                        -(BusinessAnalysisDto.getExpenditureAmount(expenditure).intValue())/orderCount;
                kdjs.add(kdj);
                kcbs.add(kcb);
                kyls.add(kyl);
            } 
            catch (Exception e) {
                // TODO: handle exception
                kdjs.add(0);
                kcbs.add(0);
                kyls.add(0);
            }
        }
        if (view!=null){
            view.addObject("kdjs", JSONArray.fromObject(kdjs).toString());
            view.addObject("kcbs", JSONArray.fromObject(kcbs).toString());
            view.addObject("kyls", JSONArray.fromObject(kyls).toString());
        }
        else {
            result.put("kdjs", kdjs);
            result.put("kcbs", kcbs);
            result.put("kyls", kyls);
        }
    }
    
    /**
     * 将一个支出的list中,对应数据字典,全部填满
    * @author 高国藩
    * @date 2016年1月26日 下午7:41:05
    * @param businessProjectList  数据字段
    * @param nowPays              当月数据
    * @param lastPays             上月数据
     */
    private void emptyList(List<CodeLibrary> businessProjectList,
            List<BusinessAnalysisDto> nowPays,
            List<BusinessAnalysisDto> lastPays) {
        List<String> nowPaysNames = nowPays.stream().map(list -> list.getBusinessProject()).collect(Collectors.toList());
        List<String> lastPaysNames = lastPays.stream().map(list -> list.getBusinessProject()).collect(Collectors.toList());
        nowPays.addAll(businessProjectList.stream()
                .filter(list -> !nowPaysNames.contains(list.getCodeName()))
                .map(list -> new BusinessAnalysisDto(list.getCodeName(), new BigDecimal(0))).collect(Collectors.toList()));
        lastPays.addAll(businessProjectList.stream()
                .filter(list -> !lastPaysNames.contains(list.getCodeName()))
                .map(list -> new BusinessAnalysisDto(list.getCodeName(), new BigDecimal(0))).collect(Collectors.toList()));
    }

    /**
     * 查询一个支出类型中的支出项目
    * @author 高国藩
    * @date 2016年1月26日 下午7:43:44
    * @param codeName   固定支出   水电燃料费 间接运营成本  人员费用   行政管理费用  直接运营支出     
    * @return           该支出类型下的项目详细
     */
    public List<CodeLibrary> codeName(String codeName){
        CodeLibraryDto codeLibraryDto =  new CodeLibraryDto();
        codeLibraryDto.setCodeName(codeName);
        codeLibraryDto.setTypeNo(101);
        CodeLibrary codeLibrary = codeLibraryMapper.selectByCodeName(codeLibraryDto);
        CodeLibraryDto codeLibraryDtos =  new CodeLibraryDto();
        codeLibraryDtos.setFatherCodeNo(codeLibrary.getCodeNo());
        codeLibraryDtos.setTypeNo(102);
        List<CodeLibrary> businessProjectList = codeLibraryMapper.selectBySunCodeName(codeLibraryDtos);
        return businessProjectList;
    }

    /**
     * 营业分析,动态查询
    * @author 高国藩
    * @date 2016年1月26日 下午8:00:57
    * @param storeId        门店信息
    * @param serchDate      时间信息
    * @return               数据封装 
     */
    public BaseDto changeBusinessAnalysis(Integer storeId, String serchDate) {
        BaseDto baseDto = new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, null);
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("startDate", DateUtil.getMonthStart(serchDate));
            params.put("stopDate", DateUtil.getMonthEnd(serchDate));
            params.put("storeId", storeId);
            BusinessAnalysisDto analysisDto =  orderInfoMapper.selectBusinessInclude(params);
            BusinessAnalysisDto analysisDto2 = orderInfoMapper.selectBusinessConsumption(params);
            //支出明细
            BusinessAnalysisDto expenditure = orderInfoMapper.selectStoreExpenses(params);
            //在直接运营中加入了项目商品成本价钱
            BigDecimal projectGoodsCost = orderInfoMapper.selectProjectAndGoodsCost(params);
            expenditure.setDirectCost(expenditure.getDirectCost().add(projectGoodsCost));
            //指定比例
            //BusinessAnalysisDto analysisDto4 = orderInfoMapper.selectStoreAssign(params);
            //单量
            Integer orderCount = orderInfoMapper.selectOrderCount(params);
            //项目列表
            List<CodeLibrary> businessProjectList =  codeName("固定支出");
            params.put("codeName", "固定支出");
            List<BusinessAnalysisDto> nowPays = orderInfoMapper.selectStoreExpensesDetails(params);
            params.put("startDate", DateUtil.getMonthLastStart(serchDate));
            params.put("stopDate", DateUtil.getMonthLastEnd(serchDate));
            List<BusinessAnalysisDto> lastPays = orderInfoMapper.selectStoreExpensesDetails(params);
            
            emptyList(businessProjectList, nowPays, lastPays);
           
            Map<String, Object> result = new HashMap<>();
            
            initTendencyChart(DateUtil.getMinMonthDateStr(), params, null, result);
            initTendencyChartInclude(DateUtil.getMinMonthDateStr(), params, null, result);
            
            result.put("storeId", storeId);
            result.put("include", BusinessAnalysisDto.getInclude(analysisDto));
            result.put("consumption", BusinessAnalysisDto.getConsumption(analysisDto2));
            result.put("expenditure", expenditure);
            result.put("nowPays", nowPays);
            result.put("lastPays", lastPays);
            result.put("nowPaysAmount", nowPays.stream().mapToInt(list -> list.getFlowAmount().intValue()).sum());
            result.put("lastPaysAmount", lastPays.stream().mapToInt(list -> list.getFlowAmount().intValue()).sum());
            //指定比例
            //result.put("assignRatio", analysisDto4.getAssignRatio().multiply(new BigDecimal(100)));
            //客量
            result.put("orderCount", orderCount);
            //客单价
            result.put("guestUnitPrice", analysisDto.getCashConsumption().add(analysisDto.getCardConsumption()).
                    add(analysisDto.getComboDeductible()).intValue()/orderCount);
            //客成本
            result.put("customerCost", BusinessAnalysisDto.getExpenditureAmount(expenditure).intValue()/orderCount);
            //客盈利
            result.put("customerProfitability", (analysisDto.getCashConsumption()
                    .add(analysisDto.getCardConsumption())
                    .add(analysisDto.getComboDeductible()).intValue()
                    -(BusinessAnalysisDto.getExpenditureAmount(expenditure).intValue()))/orderCount);
            baseDto.setMsg(result);
            return baseDto;
        } 
        catch (Exception e) {
            // TODO: handle exception
            baseDto.setCode(App.System.API_RESULT_CODE_FOR_FAIL);
            baseDto.setMsg("当前查询出错,请选择合理的时间");
            return baseDto;
        }
        
    }

    /**
     * 根据传入的时间和codeName进行查询项目下的支出明细
    * @author 高国藩
    * @date 2016年1月26日 下午8:20:46
    * @param storeId      门店信息
    * @param date    查询日期
    * @param codeName     支出类型
    * @return             数据结果
     */
    public BaseDto queryBusinessByCodeName(Integer storeId, String date, String codeName) {
        BaseDto baseDto = new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, null);
        Map<String, Object> params = new HashMap<>();
        params.put("startDate", DateUtil.getMonthStart(date));
        params.put("stopDate", DateUtil.getMonthEnd(date));
        params.put("storeId", storeId);
        BusinessAnalysisDto analysisDto3 = orderInfoMapper.selectStoreExpenses(params);
        List<CodeLibrary> businessProjectList =  codeName(codeName);
        params.put("codeName", codeName);
        
        List<BusinessAnalysisDto> nowPays = orderInfoMapper.selectStoreExpensesDetails(params);
        BigDecimal nowPaysProjectGoodsCost = orderInfoMapper.selectProjectAndGoodsCost(params);
        BusinessAnalysisDto analysisDto1 = new BusinessAnalysisDto();
        analysisDto1.setBusinessProject("项目商品成本");
        analysisDto1.setFlowAmount(nowPaysProjectGoodsCost);
        
        params.put("startDate", DateUtil.getMonthLastStart(date));
        params.put("stopDate", DateUtil.getMonthLastEnd(date));
        
        List<BusinessAnalysisDto> lastPays = orderInfoMapper.selectStoreExpensesDetails(params);
        BigDecimal lastPaysProjectGoodsCost = orderInfoMapper.selectProjectAndGoodsCost(params);
        BusinessAnalysisDto analysisDto2 = new BusinessAnalysisDto();
        analysisDto2.setBusinessProject("项目商品成本");
        analysisDto2.setFlowAmount(lastPaysProjectGoodsCost);
        
        if (codeName.equals("直接运营支出")){
            nowPays.add(analysisDto1);
            lastPays.add(analysisDto2);
            CodeLibrary codeLibrary = new CodeLibrary();
            codeLibrary.setCodeName("项目商品成本");
            businessProjectList.add(codeLibrary);
        }
        
        emptyList(businessProjectList, nowPays, lastPays);
        
        Map<String, Object> result = new HashMap<>();
        result.put("storeId", storeId);
        result.put("nowPays", nowPays);
        result.put("lastPays", lastPays);
        result.put("expenditure", analysisDto3);
        result.put("nowPaysAmount", nowPays.stream().mapToInt(list -> list.getFlowAmount().intValue()).sum());
        result.put("lastPaysAmount", lastPays.stream().mapToInt(list -> list.getFlowAmount().intValue()).sum());
        baseDto.setMsg(result);
        return baseDto;
    }
    
}
