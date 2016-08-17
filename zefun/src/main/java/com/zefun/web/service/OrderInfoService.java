package com.zefun.web.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.zefun.web.dto.BusinessDiscountPart;
import com.zefun.web.dto.BusinessIncomePart;
import com.zefun.web.dto.BusinessSummaryRelativeAmt;
import com.zefun.web.dto.BusinessSummaryTrend;
import com.zefun.web.dto.ChargedIncomePart;
import com.zefun.web.dto.DeptCashIncome;
import com.zefun.web.dto.DeptCashIncomeDto;
import com.zefun.web.dto.DeptSummaryDto;
import com.zefun.web.dto.GoodsInfoDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.OrderDetailDto;
import com.zefun.web.dto.OrderDetailStepDto;
import com.zefun.web.dto.OrderInfoBaseDto;
import com.zefun.web.dto.SummaryResultDto;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.GoodsCategory;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.GoodsCategoryMapper;
import com.zefun.web.mapper.GoodsInfoMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.OrderInfoMapper;
import com.zefun.web.mapper.StoreInfoMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
    
    /** 门店*/
    @Autowired
    private StoreInfoMapper storeInfoMapper;
    /** d订单详细 */
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    /** 项目 */
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;
    /** 商品 */
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    /** 部门*/
    @Autowired
    private DeptInfoMapper deptInfoMapper;
    
    
    
    /**
     * 查询门店下所有的订单信息
    * @author 
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
    * @author 
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
    * @author 
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
    * @author 
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
    * @author 
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
    * @author 
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
    * @author 
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
    * @author 
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
    * @author 
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
    * @author 
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
    * @author 
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


    /**
     * 商品销售
    * @author 骆峰
    * @date 2016年8月9日 下午2:44:20
    * @param storeAccount storeAccount
    * @param storeId storeId
    * @return ModelAndView
     */
    public ModelAndView showOrderDetail(String storeAccount, Object storeId){
        ModelAndView view = new ModelAndView(View.Statistical.STORE_ORDER);
        Map<String, Object> map = new HashMap<String, Object>();
        
        List<StoreInfo> selectByStoreAccount = storeInfoMapper.selectByStoreAccount(storeAccount);
        //没有门店的时候
        if (selectByStoreAccount.size() ==0){
            return new ModelAndView("redirect:500");
        }
        
        //门店进入
        if (storeId != null){
            map.put("storeId", storeId);
            view.addObject("storeId", storeId);
            view.addObject("view", 1);
        }
        else {
            map.put("storeId", selectByStoreAccount.get(0).getStoreId());
            view.addObject("storeId", selectByStoreAccount.get(0).getStoreId());
            view.addObject("view", 0);
        }
        List<GoodsCategory> goodsCategory = goodsCategoryMapper.selectBygoodsInfo(Integer.parseInt(map.get("storeId").toString()));
        List<GoodsInfoDto> goodsInfoDto = goodsInfoMapper.selectAllGoodsInfoByStoreIdAndNotPay(Integer.parseInt(map.get("storeId").toString()));
        
        Calendar a=Calendar.getInstance();
        String year = String.valueOf(a.get(Calendar.YEAR));
        String month = String.valueOf(a.get(Calendar.MONTH)+1);
        
        String yearMonth ="";
        if (Integer.parseInt(month)<10) {
            yearMonth = year +"-0"+month;
        }
        else {
            yearMonth = year +"-"+month;
        }
        
        map.put("time", year);
        map.put("timeType", yearMonth);
        view.addObject("selectByStoreAccount", selectByStoreAccount);
        view.addObject("year", year);
        view.addObject("month", month);
        view.addObject("goodsCategory", goodsCategory);
        view.addObject("goodsInfoDto", goodsInfoDto);
        List<OrderDetail> detail = orderDetailMapper.selectDetailLByOrderId(map);
        JSONObject joinData = joinData(map, detail);
        view.addObject("joinData", joinData);
        return view;
    }
    

    /**
     *   商品出售查询
    * @author 骆峰
    * @date 2016年8月10日 下午2:36:50
    * @param time  时间 年
    * @param timeType  时间 年月
    * @param storeId 门店
    * @return BaseDto
     */
    public BaseDto selectCheck(String time, Integer storeId, String timeType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("time", time);
        map.put("storeId", storeId);
        map.put("timeType", timeType);
        List<OrderDetail> detail = orderDetailMapper.selectDetailLByOrderId(map);
        JSONObject joinData = joinData(map, detail);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, joinData);
    }
    
    
    /**
     *    拼接数据
    * @author 骆峰
    * @date 2016年8月9日 下午5:02:53
    * @param map 条件
    * @param detail detail
    * @return JSONArray
     */
    public JSONObject joinData(Map<String, Object> map, List<OrderDetail> detail){
        
        JSONObject jsono     = new JSONObject(); //总数据
        
        JSONArray jsonaYear  = new JSONArray(); //当条件是年的时候数据
        JSONArray jsonoYear  = new  JSONArray(); 
        
        JSONArray jsonaMonth = new JSONArray(); //当条件是月的时候数据
        JSONArray jsonoMonth = new  JSONArray();
        
        JSONArray day = new  JSONArray(); 
        String [] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String [] months = {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
      
        if (detail.size() != 0){
            String time = map.get("timeType").toString(); //年月
            String year = time.substring(0, 4);
            List<OrderDetail> monthCollect = detail.stream()
                    .filter(s -> s.getCreateTime().substring(0, 7).equals(map.get("timeType").toString())).collect(Collectors.toList());
            Integer monthDay = DateUtil.monthDay(map.get("timeType").toString());
            for (int j = 1; j <= monthDay; j++) {
                int g = j;
                Double count = 0.0;
                Double price = 0.0;
                //天数
                day.add(String.valueOf(j)+ "日");
                if (j<11) {
                    count = monthCollect.parallelStream().filter(f ->f.getCreateTime().substring(0, 10).equals(time+"-"+month[g-1]))
                            .mapToDouble(OrderDetail::getProjectCount).sum();
                    price = monthCollect.parallelStream().filter(f ->f.getCreateTime().substring(0, 10).equals(time+"-"+month[g-1]))
                            .mapToDouble(OrderDetail::getDetailCalculate).sum();
                }
                else {
                    
                    
                    count = monthCollect.parallelStream().filter(f ->f.getCreateTime().substring(0, 10).equals(time+"-"+String.valueOf(g)))
                            .mapToDouble(OrderDetail::getProjectCount).sum();
                    price = monthCollect.parallelStream().filter(f ->f.getCreateTime().substring(0, 10).equals(time+"-"+String.valueOf(g)))
                            .mapToDouble(OrderDetail::getDetailCalculate).sum();
                }
           
                jsonaMonth.add(new BigDecimal(count).setScale(2, BigDecimal.ROUND_HALF_UP));
                jsonoMonth.add(new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            
            //年的数据
            for (int i = 0; i < month.length; i++) {
                int g = i;
                double count = detail.parallelStream().filter(f ->f.getCreateTime().substring(0, 7).equals(year+"-"+month[g]))
                        .mapToDouble(OrderDetail::getProjectCount).sum();
                double price = detail.parallelStream().filter(f ->f.getCreateTime().substring(0, 7).equals(year+"-"+month[g]))
                        .mapToDouble(OrderDetail::getDetailCalculate).sum();
                jsonaYear.add(new BigDecimal(count).setScale(2, BigDecimal.ROUND_HALF_UP));
                jsonoYear.add(new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP));
            }
        } 
        else {
            
        }
 
        jsono.accumulate("day", day);
        jsono.accumulate("jsonaMonth", jsonaMonth); //  月数量
        jsono.accumulate("jsonoMonth", jsonoMonth); //  月业绩
        jsono.accumulate("month", months);
        jsono.accumulate("jsonaYear", jsonaYear); //年 数量
        jsono.accumulate("jsonoYear", jsonoYear); //年 业绩
        return jsono;
    }


    /**
     *  销售PK
    * @author 骆峰
    * @date 2016年8月10日 下午5:26:54
    * @param storeId storeId
    * @param goodsId1  商品
    * @param goodsId2  商品
    * @param categoryId1  大项
    * @param categoryId2  大项
    * @param timeType  年月
    * @param time  年
    * @return BaseDto
     */
    public BaseDto selectCategory(Integer storeId, Integer goodsId1, Integer goodsId2, Integer categoryId1,
            Integer categoryId2, String timeType, String time) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("time", time);
        map.put("storeId", storeId);
        map.put("timeType", timeType);
        map.put("goodsId", goodsId1);
        map.put("categoryId", categoryId1);
        
        List<OrderDetail> category = orderDetailMapper.selectDetailLByCategory(map);
        List<OrderDetail> detail = orderDetailMapper.selectDetailLByGoods(map);
        map.put("categoryId", categoryId2);
        map.put("goodsId", goodsId2);
        List<OrderDetail> orderCategory = orderDetailMapper.selectDetailLByCategory(map);
        List<OrderDetail> orderDetail = orderDetailMapper.selectDetailLByGoods(map);
        
        JSONObject joinDetail = joinData(map, detail);
        JSONObject joinOrderDetail = joinData(map, orderDetail); 
        
        JSONObject joinCategory = joinData(map, category);
        JSONObject joinOrderCategory = joinData(map, orderCategory);
     
        JSONObject json = new JSONObject();
        json.accumulate("joinDetail", joinDetail);
        json.accumulate("joinOrderDetail", joinOrderDetail);
        json.accumulate("joinCategory", joinCategory);
        json.accumulate("joinOrderCategory", joinOrderCategory);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, json);
    }


    
    /**
     *  销量汇总
    * @author 骆峰
    * @date 2016年8月11日 下午3:20:36
    * @param storeId storeId
    * @param time1 time1
    * @param time2 time2
    * @return BaseDto
     */
    public BaseDto selectByGoods(Integer storeId, String time1, String time2) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeId", storeId);
        map.put("time1", time1);
        map.put("time2", time2);
        // 商品
        List<OrderDetailDto> selectByGoods = orderDetailMapper.selectDetailListByGoods(map);
        // 套餐
        List<OrderDetailDto> selectByGoodsd = orderDetailMapper.selectDetailListByGoodsd(map);
        List<DeptInfo> dept = deptInfoMapper.selectAllDetpByStoreId(storeId);
        
        List<GoodsInfoDto> goods = goodsInfoMapper.selectAllGoodsdInfoByStoreId(map);
        map.put("group", "group");
        List<GoodsInfoDto> goodsgroup = goodsInfoMapper.selectAllGoodsdInfoByStoreId(map);
        
        JSONObject json = goodsTotal(selectByGoods, selectByGoodsd); //总数
        
        JSONArray goodsJoin = goodsJoin(selectByGoods, selectByGoodsd, dept, goods, goodsgroup);
        json.accumulate("goodsJoin", goodsJoin);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, json);
    } 
    
   
    /**
     *   销量汇总 数据汇总
    * @author 骆峰
    * @date 2016年8月11日 下午5:34:54
    * @param selectByGoods 门店或商品
    * @param selectByGoodsd 套餐
    * @param dept 部门
    * @param goods 商品
    * @param groups group
    * @return JSONArray
     */
    public JSONArray goodsJoin(List<OrderDetailDto> selectByGoods, List<OrderDetailDto> selectByGoodsd,
            List<DeptInfo> dept, List<GoodsInfoDto> goods , List<GoodsInfoDto> groups){
        
       
        JSONArray  jsonar = new JSONArray();
        JSONArray  tablear = new JSONArray();
        JSONArray jsona   = new JSONArray();
        JSONObject jsono  = new JSONObject();
        JSONObject jsonoj  = new JSONObject();
        JSONObject  table = new JSONObject();
        
        //部门
        dept.stream().forEach(d ->{
                List<GoodsInfoDto> group   = groups.stream().filter(s ->s.getDeptId().equals(d.getDeptId())).collect(Collectors.toList());  //部门下的商品 
                List<OrderDetailDto> collect  = selectByGoods.stream().filter(s ->s.getDeptId().equals(d.getDeptId())).collect(Collectors.toList());  
                List<OrderDetailDto> collect2 = selectByGoodsd.stream().filter(s ->s.getDeptId().equals(d.getDeptId())).collect(Collectors.toList());
              
                // 大项
                group.stream().forEach(g ->{  
                        List<GoodsInfoDto> collect3   = goods.stream().filter(s ->g.getCategoryId().equals(s.getCategoryId()))
                                .collect(Collectors.toList());  //部门下商品所属大项
                        List<OrderDetailDto> store = collect.stream()
                                .filter(s ->s.getOrderType().equals(1) &&  g.getCategoryId().equals(s.getCategoryId()))
                                .collect(Collectors.toList());          //门店
                        
                        List<OrderDetailDto> shop = collect2.stream()
                                .filter(s ->s.getOrderType().equals(3) &&  g.getCategoryId().equals(s.getCategoryId()))
                                .collect(Collectors.toList()); //商城
                        
                        List<OrderDetailDto> price = collect.stream()
                                .filter(s ->s.getCashCardType().equals(1) && g.getCategoryId().equals(s.getCategoryId())) 
                                .collect(Collectors.toList());      //现金 
                        List<OrderDetailDto> caPrice = collect.stream()
                                .filter(s ->s.getCashCardType().equals(2) && g.getCategoryId().equals(s.getCategoryId()))
                                .collect(Collectors.toList()); //卡金
                        //商品
                        collect3.stream().forEach(c ->{
                                Double storeDcl = store.parallelStream()
                                        .filter(s ->c.getGoodsId().equals(s.getGoodsId()))
                                        .mapToDouble(OrderDetailDto::getDetailCalculate).sum(); //门店
                                Double storePcl =  store.parallelStream()
                                        .filter(s ->c.getGoodsId().equals(s.getGoodsId()))
                                        .mapToDouble(OrderDetailDto::getProjectCount).sum();
                                
                                Double shopDcl =  shop.parallelStream()
                                        .filter(s ->c.getGoodsId().equals(s.getGoodsId()))
                                        .mapToDouble(OrderDetailDto::getDetailCalculate).sum(); //商城
                                Double shopPcl = shop.parallelStream()
                                        .filter(s ->c.getGoodsId().equals(s.getGoodsId()))
                                        .mapToDouble(OrderDetailDto::getProjectCount).sum(); 
                               
                                Double priceDcl =  price.parallelStream().filter(s ->c.getGoodsId().equals(s.getGoodsId()))
                                        .mapToDouble(OrderDetailDto::getDetailCalculate).sum();     //现金    业绩
                                Double pricePcl =  price.parallelStream().filter(s ->c.getGoodsId().equals(s.getGoodsId()))
                                        .mapToDouble(OrderDetailDto::getProjectCount).sum();  //数量
                                
                                Double caPriceDcl =  caPrice.parallelStream().filter(s ->c.getGoodsId().equals(s.getGoodsId()))
                                        .mapToDouble(OrderDetailDto::getDetailCalculate).sum();  //卡金
                                Double caPricePcl =  caPrice.parallelStream().filter(s ->c.getGoodsId().equals(s.getGoodsId()))
                                        .mapToDouble(OrderDetailDto::getProjectCount).sum();
                                
                                
                                Double tcDcl =  collect2.parallelStream().filter(s ->c.getGoodsId().equals(s.getGoodsId())  
                                        && g.getCategoryId().equals(s.getCategoryId())).mapToDouble(OrderDetailDto::getDetailCalculate).sum();  //套餐
                                Double tcPcl =  collect2.parallelStream().filter(s ->c.getGoodsId().equals(s.getGoodsId())  
                                        && g.getCategoryId().equals(s.getCategoryId())).mapToDouble(OrderDetailDto::getProjectCount).sum();
                                storeDcl = tcDcl + storeDcl ;
                                storePcl = tcPcl + storePcl;
                                Double totalDcl = shopDcl + storeDcl;
                                Double totalPcl = shopPcl + storePcl;
                                jsono.accumulate("priceDcl",   new BigDecimal(priceDcl).setScale(2, BigDecimal.ROUND_HALF_UP));
                                jsono.accumulate("caPriceDcl", new BigDecimal(caPriceDcl).setScale(2, BigDecimal.ROUND_HALF_UP));
                                jsono.accumulate("tcDcl",      new BigDecimal(tcDcl).setScale(2, BigDecimal.ROUND_HALF_UP));
                                jsono.accumulate("shopDcl",    new BigDecimal(shopDcl).setScale(2, BigDecimal.ROUND_HALF_UP));
                                jsono.accumulate("storeDcl",   new BigDecimal(storeDcl).setScale(2, BigDecimal.ROUND_HALF_UP));
                                jsono.accumulate("pricePcl",   new BigDecimal(pricePcl).setScale(2, BigDecimal.ROUND_HALF_UP));
                                jsono.accumulate("caPricePcl", new BigDecimal(caPricePcl).setScale(2, BigDecimal.ROUND_HALF_UP));
                                jsono.accumulate("tcPcl",      new BigDecimal(tcPcl).setScale(2, BigDecimal.ROUND_HALF_UP));
                                jsono.accumulate("shopPcl",    new BigDecimal(shopPcl).setScale(2, BigDecimal.ROUND_HALF_UP));
                                jsono.accumulate("storePcl",   new BigDecimal(storePcl).setScale(2, BigDecimal.ROUND_HALF_UP));
                                jsono.accumulate("totalDcl",   new BigDecimal(totalDcl).setScale(2, BigDecimal.ROUND_HALF_UP));
                                jsono.accumulate("totalPcl",   new BigDecimal(totalPcl).setScale(2, BigDecimal.ROUND_HALF_UP));
                                jsono.accumulate("name",   c.getGoodsName());
                                jsona.add(jsono);
                                jsono.clear();
                            });
                        Double storeDc = 0.0;
                        Double storePc = 0.0;
                        storeDc =  store.parallelStream().mapToDouble(OrderDetailDto::getDetailCalculate).sum(); //门店
                        storePc = store.parallelStream().mapToDouble(OrderDetailDto::getProjectCount).sum();
                        
                        Double shopDc =  shop.parallelStream().mapToDouble(OrderDetailDto::getDetailCalculate).sum(); //商城
                        Double shopPc = shop.parallelStream().mapToDouble(OrderDetailDto::getProjectCount).sum(); 
                       
                        Double priceDc =  price.parallelStream().mapToDouble(OrderDetailDto::getDetailCalculate).sum();     //现金    业绩
                        Double pricePc =  price.parallelStream().mapToDouble(OrderDetailDto::getProjectCount).sum();  //数量
                        
                        Double caPriceDc =  caPrice.parallelStream().mapToDouble(OrderDetailDto::getDetailCalculate).sum();  //卡金
                        Double caPricePc =  caPrice.parallelStream().mapToDouble(OrderDetailDto::getProjectCount).sum();
                        Double tcDc = 0.0;
                        Double tcPc = 0.0;
                        tcDc =  collect2.parallelStream().filter(s -> g.getCategoryId().equals(s.getCategoryId()))
                                .mapToDouble(OrderDetailDto::getDetailCalculate).sum();  //套餐
                        tcPc =  collect2.parallelStream().filter(s -> g.getCategoryId().equals(s.getCategoryId()))
                                .mapToDouble(OrderDetailDto::getProjectCount).sum();
                        
                        storeDc = tcDc + storeDc;
                        storePc = tcPc + storePc;
                        
                        Double totalDc = priceDc + caPriceDc + tcDc;
                        Double totalPc = pricePc + caPricePc + tcPc;
                       
                        jsonoj.accumulate("storeDc",    new BigDecimal(storeDc).setScale(2, BigDecimal.ROUND_HALF_UP));
                        jsonoj.accumulate("storePc",    new BigDecimal(storePc).setScale(2, BigDecimal.ROUND_HALF_UP));
                        jsonoj.accumulate("shopDc",     new BigDecimal(shopDc).setScale(2, BigDecimal.ROUND_HALF_UP));
                        jsonoj.accumulate("shopPc",     new BigDecimal(shopPc).setScale(2, BigDecimal.ROUND_HALF_UP));
                        jsonoj.accumulate("priceDc",    new BigDecimal(priceDc).setScale(2, BigDecimal.ROUND_HALF_UP));
                        jsonoj.accumulate("pricePc",    new BigDecimal(pricePc).setScale(2, BigDecimal.ROUND_HALF_UP));
                        jsonoj.accumulate("caPriceDc",  new BigDecimal(caPriceDc).setScale(2, BigDecimal.ROUND_HALF_UP));
                        jsonoj.accumulate("caPricePc",  new BigDecimal(caPricePc).setScale(2, BigDecimal.ROUND_HALF_UP));
                        jsonoj.accumulate("tcDc",       new BigDecimal(tcDc).setScale(2, BigDecimal.ROUND_HALF_UP));
                        jsonoj.accumulate("tcPc",       new BigDecimal(tcPc).setScale(2, BigDecimal.ROUND_HALF_UP));
                        jsonoj.accumulate("totalDc",    new BigDecimal(totalDc).setScale(2, BigDecimal.ROUND_HALF_UP));
                        jsonoj.accumulate("totalPc",    new BigDecimal(totalPc).setScale(2, BigDecimal.ROUND_HALF_UP));
                        jsonoj.accumulate("jsonatable", jsona);
                        jsonoj.accumulate("name", g.getCategoryName());
                        tablear.add(jsonoj);
                      
                        jsonoj.clear();
                        jsona.clear();
                    });
                Double deptTotal = 0.0;
                for (int i = 0; i < tablear.size(); i++) {
                    deptTotal += tablear.getJSONObject(i).getDouble("totalDc");
                }
                table.accumulate("name", d.getDeptName());
                table.accumulate("table", tablear);
                table.accumulate("deptTotal", deptTotal);
                tablear.clear();
                jsonar.add(table);
                table.clear();
            });
       
        
        return jsonar;
    }
    
    /**
     *  销售汇总
    * @author 骆峰
    * @date 2016年8月11日 下午3:54:14
    * @param selectByGoods selectByGoods
    * @param selectByGoodsd selectByGoodsd
    * @return JSONObject
     */
    public JSONObject goodsTotal(List<OrderDetailDto> selectByGoods, List<OrderDetailDto> selectByGoodsd){
        JSONObject json = new JSONObject();
   
        List<OrderDetailDto> price = selectByGoods.stream().filter(s ->s.getCashCardType().equals(1)).collect(Collectors.toList());      //现金 
        List<OrderDetailDto> caPrice = selectByGoods.stream().filter(s ->s.getCashCardType().equals(2)).collect(Collectors.toList());  //卡金
       
        Double priceDc =  price.parallelStream().mapToDouble(OrderDetailDto::getDetailCalculate).sum();     //业绩
        Double pricePc =  price.parallelStream().mapToDouble(OrderDetailDto::getProjectCount).sum();  //数量
        
        Double caPriceDc =  caPrice.parallelStream().mapToDouble(OrderDetailDto::getDetailCalculate).sum();
        Double caPricePc =  caPrice.parallelStream().mapToDouble(OrderDetailDto::getProjectCount).sum();
       
        Double tcDc =  selectByGoodsd.parallelStream().mapToDouble(OrderDetailDto::getDetailCalculate).sum();
        Double tcPc =  selectByGoodsd.parallelStream().mapToDouble(OrderDetailDto::getProjectCount).sum();
        
        Double totalDc = priceDc + caPriceDc + tcDc;
        Double totalPc = pricePc + caPricePc + tcPc;
        
        json.accumulate("priceDc",  new BigDecimal(priceDc).setScale(2, BigDecimal.ROUND_HALF_UP));
        json.accumulate("pricePc",  new BigDecimal(pricePc).setScale(2, BigDecimal.ROUND_HALF_UP));
        json.accumulate("caPriceDc", new BigDecimal(caPriceDc).setScale(2, BigDecimal.ROUND_HALF_UP));
        json.accumulate("caPricePc",  new BigDecimal(caPricePc).setScale(2, BigDecimal.ROUND_HALF_UP));
        json.accumulate("tcDc",  new BigDecimal(tcDc).setScale(2, BigDecimal.ROUND_HALF_UP));
        json.accumulate("tcPc",  new BigDecimal(tcPc).setScale(2, BigDecimal.ROUND_HALF_UP));
        json.accumulate("totalDc",  new BigDecimal(totalDc).setScale(2, BigDecimal.ROUND_HALF_UP));
        json.accumulate("totalPc",  new BigDecimal(totalPc).setScale(2, BigDecimal.ROUND_HALF_UP));
        return json;
    }
}
