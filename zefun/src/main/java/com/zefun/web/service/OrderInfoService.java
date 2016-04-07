package com.zefun.web.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zefun.web.dto.BusinessDiscountPart;
import com.zefun.web.dto.BusinessIncomePart;
import com.zefun.web.dto.BusinessSummaryRelativeAmt;
import com.zefun.web.dto.BusinessSummaryTrend;
import com.zefun.web.dto.ChargedIncomePart;
import com.zefun.web.dto.DeptCashIncome;
import com.zefun.web.dto.DeptCashIncomeDto;
import com.zefun.web.dto.DeptSummaryDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.OrderDetailDto;
import com.zefun.web.dto.OrderDetailStepDto;
import com.zefun.web.dto.OrderInfoBaseDto;
import com.zefun.web.dto.SummaryResultDto;
import com.zefun.web.mapper.OrderInfoMapper;

/**
 * 订单相关操作服务类
* @author 张进军
* @date Oct 13, 2015 7:43:34 PM 
*/
@Service
public class OrderInfoService {
    /** 会员接口操作服务类 */
    @Autowired
    private MemberInfoService memberInfoService;

    /** 订单数据操作对象 */
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    
    
    /**
     * 查询门店下所有的订单信息
    * @author 张进军
    * @date Oct 13, 2015 8:04:21 PM
    * @param storeId    门店标识
    * @param type 订单状态类型（1、进行中，2、已完成，3、全部）
    * @return   门店下订单信息集合
     */
    public List<OrderInfoBaseDto> getOrderInfoBaseDtoByStoreId(int storeId, Integer type){
        List<Integer> orderIdList = new ArrayList<Integer>();
        if (type == 1) {
            orderIdList = orderInfoMapper.selectOrderIdByStoreIdNotOver(storeId);
        }
        else if (type == 2) {
            orderIdList = orderInfoMapper.selectOrderIdByStoreIdIsOver(storeId);
        }
        else {
            orderIdList = orderInfoMapper.selectOrderIdByStoreIdHistory(storeId);
        }
        
        return getOrderInfoBaseDtoByOrderIdList(orderIdList);
    }
    
    
    /**
     * 查询某员工的订单信息
    * @author 张进军
    * @date Oct 13, 2015 8:04:21 PM
    * @param employeeId    员工标识
    * @param type 订单状态类型（1、进行中，2、已完成，3、全部）
    * @return   门店下订单信息集合
     */
    public List<OrderInfoBaseDto> getOrderInfoBaseDtoByEmployeeId(int employeeId, Integer type){
        List<Integer> orderIdList = new ArrayList<Integer>();
        if (type == 1) {
            orderIdList = orderInfoMapper.selectOrderIdByEmployeeIdNotOver(employeeId);
        }
        else if (type == 2) {
            orderIdList = orderInfoMapper.selectOrderIdByEmployeeIdIsOver(employeeId);
        }
        else {
            orderIdList = orderInfoMapper.selectOrderIdByEmployeeIdHistory(employeeId);
        }
        return getOrderInfoBaseDtoByOrderIdList(orderIdList);
    }
    
    
    /**
     * 根据订单编号集合查询订单信息
    * @author 张进军
    * @date Oct 13, 2015 8:07:29 PM
    * @param orderIdList    订单编号集合
    * @return   订单信息集合
     */
    private List<OrderInfoBaseDto> getOrderInfoBaseDtoByOrderIdList(List<Integer> orderIdList) {
        if (orderIdList.isEmpty()) {
            return null;
        }
        
        List<OrderInfoBaseDto> orderList = new ArrayList<OrderInfoBaseDto>(orderIdList.size());
        for (Integer orderId : orderIdList) {
            OrderInfoBaseDto orderInfo = getOrderInfoBaseDto(orderId);
            orderList.add(orderInfo);
        }
        return orderList;
    }
    
    /**
     * 根据订单编号获取订单基础传输对象
    * @author 张进军
    * @date Oct 13, 2015 8:01:38 PM
    * @param orderId    订单编号
    * @return   订单基础传输对象
     */
    public OrderInfoBaseDto getOrderInfoBaseDto(Integer orderId){
        if (orderId == null) {
            return null;
        }
        
        OrderInfoBaseDto orderInfo = orderInfoMapper.selectOrderBaseByOrderId(orderId);
        
        List<OrderDetailDto> orderDetailList = orderInfo.getOrderDetailList();
        //由于员工备注引起报错处理
        for (OrderDetailDto orderDetailDto : orderDetailList) {
        	List<OrderDetailStepDto> stepList = orderDetailDto.getStepList();
        	
        	for (OrderDetailStepDto orderDetailStepDto : stepList) {
        	    if (orderDetailStepDto.getEmployeeInfo() != null) {
        	        orderDetailStepDto.getEmployeeInfo().setEmployeeDesc("");
        	    }
			}
		}
        
        MemberBaseDto memberInfo = new MemberBaseDto();
        //但会员为空时，为散客
        if (orderInfo.getMemberId() == null) {
            memberInfo.setName("散客");
        }
        else {
            memberInfo = memberInfoService.getMemberBaseInfo(orderInfo.getMemberId(), false);
        }
        
        orderInfo.setMemberInfo(memberInfo);
        return orderInfo;
    }
    
    /**
    * @author 乐建建
    * @date 2016年2月18日 下午2:32:15
    * @param dto 传递给定的参数条件 
    * @return 处理之后的结果
    */
    public List<DeptSummaryDto> getProcessedDeptData(SummaryResultDto dto){
        
        List<DeptSummaryDto> depts=orderInfoMapper.getDeptSummary(dto);
        if (depts!=null && depts.size()>0){
            for (int i=0; i<depts.size(); i++){
                BigDecimal value=new BigDecimal(0);
                BigDecimal expense=new BigDecimal(0);
                DeptSummaryDto dept=depts.get(i);
                BusinessDiscountPart discount=dept.getBusinessDiscount();
                BusinessIncomePart income=dept.getBusinessIncome();
                ChargedIncomePart charged=dept.getChargedIncome();
                if (income!=null){
                    value=value.add(income.getTotalAmt());
                }
                if (charged!=null){
                    value=value.add(charged.getTotalAmt());
                }
                dept.setIncomes(value);
                if (discount!=null){
                    expense=expense.add(discount.getTotalAmt());
                    discount.setComboAmt(dept.getComboDiscount());
                }               
                expense=expense.add(dept.getCardConsumedAmt()).add(dept.getComboDiscount());
                dept.setExpenses(expense);
                dept.setRealIncomes(value.subtract(expense));
            }
        }
        return depts;
    }
    
    /**
    * @author 乐建建
    * @date 2016年2月19日 上午11:57:01
    * @param dto 封装参数条件
    * @return 营业汇总封装类
    */
    public BusinessSummaryTrend getBusinessSummaryTrend(SummaryResultDto dto){
        BusinessSummaryTrend trend=new BusinessSummaryTrend();
        List<BusinessSummaryRelativeAmt> cards=orderInfoMapper.getBusinessCardConsumed(dto);
        List<BusinessSummaryRelativeAmt> expenses= orderInfoMapper.getBusinessExpense(dto);
        List<BusinessSummaryRelativeAmt> incomes=orderInfoMapper.getBusinessIncomes(dto);
        List<BusinessSummaryRelativeAmt>  comboDiscounts=orderInfoMapper.getBusinessComboDiscount(dto);
        cards=processData(dto, cards);
        expenses=processData(dto, expenses);
        incomes=processData(dto, incomes);
        comboDiscounts=processData(dto, comboDiscounts);
        expenses=plusCardsAndExpense(cards, expenses);
        expenses=plusCardsAndExpense(comboDiscounts, expenses);
        List<BusinessSummaryRelativeAmt> realIncomes=substractIncomeAndExpense(incomes, expenses);
        trend.setBusinessExpense(expenses);
        trend.setIncomes(incomes);
        trend.setRealIncomes(realIncomes);
        return trend;
    }
    
    /**
    * @author 乐建建
    * @date 2016年2月19日 下午2:25:09
    * @param incomes 营业收入
    * @param expenses 营业扣减
    * @return 营业纯收入
    */
    private List<BusinessSummaryRelativeAmt> substractIncomeAndExpense(
            List<BusinessSummaryRelativeAmt> incomes,
            List<BusinessSummaryRelativeAmt> expenses) {
        int size=incomes.size();
        List<BusinessSummaryRelativeAmt> result=new ArrayList<BusinessSummaryRelativeAmt>();
        for (int i=0; i< size; i++){
            BusinessSummaryRelativeAmt bsra=new BusinessSummaryRelativeAmt();
            bsra.setDate(incomes.get(i).getDate());
            bsra.setTotalAmt(incomes.get(i).getTotalAmt().subtract(expenses.get(i).getTotalAmt()));
            result.add(bsra);
        }
        return result;
    }


    /**
    * @author 乐建建
    * @date 2016年2月19日 下午2:21:27
    * @param cards 刷卡消费
    * @param expenses 营业扣减消费
    * @return 汇总之后的营业扣减
    */
    private List<BusinessSummaryRelativeAmt> plusCardsAndExpense(
            List<BusinessSummaryRelativeAmt> cards,
            List<BusinessSummaryRelativeAmt> expenses) {
        int size=cards.size();
        for (int i=0; i<size; i++){
            BusinessSummaryRelativeAmt bsa=expenses.get(i);
            bsa.setTotalAmt(bsa.getTotalAmt().add(cards.get(i).getTotalAmt()));
        }
        return expenses;
    }


    /**
    * @author 乐建建
    * @date 2016年2月19日 下午1:56:00
    * @param dto 封装参数条件
    * @param list 待处理的数据
    * @return 处理之后的list数据
    */
    public List<BusinessSummaryRelativeAmt> processData(SummaryResultDto dto, List<BusinessSummaryRelativeAmt> list){
        List<BusinessSummaryRelativeAmt> result=new ArrayList<BusinessSummaryRelativeAmt>();
        Map<Integer, BigDecimal> base=new HashMap<Integer, BigDecimal>();  
        Integer days=12;
        if (dto.getDateType()==3 || dto.getDateType()==2){
            int year=Integer.parseInt(dto.getEnd().substring(0, 4));
            int month=Integer.parseInt(dto.getBegin().substring(5, 7));
            days=getLastDay(year, month);
        }
        for (int i=0; i<list.size(); i++){
            if (list.get(i)!=null){
                base.put(Integer.parseInt(list.get(i).getDate()), list.get(i).getTotalAmt());
            }
        }
        for (int i=1; i<=days; i++){
            if (base.get(i)==null){
                result.add(new BusinessSummaryRelativeAmt(i>=10?""+i:"0"+i, new BigDecimal(0)));
            }
            else {
                result.add(new BusinessSummaryRelativeAmt(i>=10?""+i:"0"+i, base.get(i)));
            }
        }
        return result;
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
    * @date 2016年2月21日 下午4:41:37
    * @param dto 封装参数
    * @return 将某些null对象转换成有默认值的对象 不要返回null
    */
    public List<DeptCashIncome> processData(SummaryResultDto dto){
        List<DeptCashIncome> list= orderInfoMapper.getDeptCashSummary(dto);
        BigDecimal value=new BigDecimal(0);
        DeptCashIncomeDto cash=new DeptCashIncomeDto(null, value, value, value, value, value, value, value);
        if (list!=null && list.size()>0){
            for (int i=0; i< list.size(); i++){
                DeptCashIncome dept=list.get(i);
                if (dept.getDeptDto()==null){
                    dept.setDeptDto(cash);
                }
            }
        }
        return list;
    }
    
}
