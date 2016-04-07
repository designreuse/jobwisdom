package com.zefun.web.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.BusinessSummaryDto;
import com.zefun.web.dto.CardSaleSummaryDto;
import com.zefun.web.dto.CardconsumptionStoreSummaryResultDto;
import com.zefun.web.dto.CashIncomeDto;
import com.zefun.web.dto.ComboSummaryDto;
import com.zefun.web.dto.CommoditySalesDto;
import com.zefun.web.dto.DeptLaborSummaryDto;
import com.zefun.web.dto.DifferentStoreCardConsumeDto;
import com.zefun.web.dto.GoodSalesSummaryDto;
import com.zefun.web.dto.ProjectLaborRank;
import com.zefun.web.dto.SummaryResultDto;
import com.zefun.web.service.BusinessReporterService;
import com.zefun.web.service.StoreInfoService;
import com.zefun.wechat.dto.ComboSummaryViewDto;

import net.sf.json.JSONArray;

/**
* 营业报表控制类
* @author 张进军
* @date Jan 16, 2016 8:45:37 PM
*/
@Controller
public class BusinessReportController extends BaseController {
    /**
     * 门店信息操作类
     */
    @Autowired
    private StoreInfoService storeInfoService;

    /**
     * 营业报表服务类
     * */
    @Autowired
    private BusinessReporterService businessReporter;

    /**
     * 营业汇总
    * @author 张进军
    * @date Jan 16, 2016 8:49:33 PM
    * @param storeId 门店id
    * @param begin      开始时间
    * @param end        结束时间
    * @param dateType   日期类型
    * @param request    请求对象
    * @param response   响应对象
    * @return   营业汇总页面
     */
    @RequestMapping(value = Url.Businessreport.SUMMARY)
    public ModelAndView businessSummaryView(@RequestParam(required = false) Integer storeId, String begin, String end, Integer dateType, 
            HttpServletRequest request, HttpServletResponse response){
        boolean flag=true;
        if (storeId == null || storeId==0) {
            storeId = getStoreId(request);
            flag=false;
        }
        SummaryResultDto dto=new SummaryResultDto();
        dto.setBegin(begin);
        dto.setEnd(end);
        dto.setDateType(dateType);
        dto.setStoreId(storeId);
        BusinessSummaryDto summaryResult = storeInfoService.getBusinessSummaryData(dto, flag);
        ModelAndView mav = new ModelAndView(View.BusinessReport.BUSINESSSUMMARY);
        mav.addObject("summaryResult", summaryResult);
        return mav;
    }

    /**
     * 现金收入
    * @author 张进军
    * @date Jan 16, 2016 8:46:33 PM
    * @param storeId 当前门店id
    * @param begin      开始时间
    * @param end        结束时间
    * @param dateType   日期类型
    * @param request    请求对象
    * @param response   响应对象
    * @return   现金收入页面
     */
    @RequestMapping(value = Url.Businessreport.CASHRECEIPTS)
    public ModelAndView cashreceiptsView(@RequestParam(required=false) Integer storeId, String begin, 
            String end, Integer dateType, HttpServletRequest request, HttpServletResponse response){
        boolean flag=true;
        if (storeId == null || storeId==0) {
            storeId = getStoreId(request);
            flag=false;
        }
        SummaryResultDto dto=new SummaryResultDto();
        dto.setBegin(begin);
        dto.setEnd(end);
        dto.setDateType(dateType);
        dto.setStoreId(storeId);
        CashIncomeDto summaryResult=storeInfoService.getCashIncomeData(dto, flag);
        ModelAndView mav = new ModelAndView(View.BusinessReport.CASHRECEIPTS);
        mav.addObject("summaryResult", summaryResult);
        return mav;
    }

    /**
     * 划卡消费
    * @author 张进军
    * @date Jan 16, 2016 8:43:08 PM
    * @param begin  开始时间
    * @param end    结束时间
    * @param dateType   日期类型
    * @param request    请求对象
    * @param response   响应对象
    * @return   划卡消费页
     */
    @RequestMapping(value = Url.Businessreport.CARDCONSUMPTION)
    public ModelAndView cardConsumeView(String begin, String end,
            Integer dateType, HttpServletRequest request,
            HttpServletResponse response) {
        CardconsumptionStoreSummaryResultDto summaryResult = storeInfoService
                .getSummary(begin, end, dateType,
                        Arrays.asList(new Integer[] { 1, 2, 3 }), 2,
                        getStoreId(request));
        ModelAndView mav = new ModelAndView(View.BusinessReport.CARDCONSUPTION);
        mav.addObject("summaryResult", summaryResult);
        return mav;
    }

    /**
     * 卡项销售
     * @param begin 开始日期
     * @param end 截止日期
     * @param storeId 门店id
     * @param dateType 日期类型
     * @param request http请求
     * @param response 查询结果填充到response
     * @return 卡项销售页面
     */
    @RequestMapping(value = Url.Businessreport.CARDSALES)
    public ModelAndView cardSalesView(@RequestParam(required=false) Integer storeId, String begin, String end, Integer dateType, 
            HttpServletRequest request, HttpServletResponse response){
        boolean flag=true;
        if (storeId == null || storeId==0) {
            storeId = getStoreId(request);
            flag=false;
        }
        SummaryResultDto dto=new SummaryResultDto();
        dto.setBegin(begin);
        dto.setDateType(dateType);
        dto.setEnd(end);
        dto.setStoreId(storeId);
        CardSaleSummaryDto summaryResult = businessReporter.getCardSaleSummary(dto, flag);
        ModelAndView mav = new ModelAndView(View.BusinessReport.CARDSALES);
        mav.addObject("summaryResult", summaryResult);
        return mav;
    }

    /**
     * 劳动业绩
     * @param begin 起始日期
     * @param storeId 门店id
     * @param end 终止日期
     * @param dateType 日期类型
     * @param request 用户请求
     * @param response 响应
     * @return ModelAndView
     */
    @RequestMapping(value = Url.Businessreport.LABORPERFORMANCE)
    public ModelAndView laborPerformanceView(@RequestParam(required=false) Integer storeId, String begin, String end, Integer dateType, 
            HttpServletRequest request, HttpServletResponse response){
        boolean flag=true;
        if (storeId == null || storeId==0) {
            storeId = getStoreId(request);
            flag=false;
        }
        DeptLaborSummaryDto summaryLaborDto=storeInfoService.getLaborPerformanceSummary(begin, end, dateType, storeId, flag);
        ModelAndView mav = new ModelAndView(View.BusinessReport.LABORPERFORMANCE);
        mav.addObject("summaryLaborDto", summaryLaborDto);
        return mav;
    }

    /**
    * 套餐销售
    * @author 乐建建
    * @date 2016年1月21日 下午2:22:14
    * @param begin 日期区间起始点
    * @param storeId 门店id
    * @param end 日期区间终止点
    * @param dateType 日期区间类型
    * @param request 页面请求
    * @param response 返回给页面的响应
    * @return  ModelAndView
    */
    @RequestMapping(value = Url.Businessreport.PACKAGESALES)
    public ModelAndView comboSalesView(@RequestParam(required=false) Integer storeId, String begin, String end, Integer dateType, 
            HttpServletRequest request, HttpServletResponse response){
        boolean flag=true;
        if (storeId == null || storeId==0) {
            storeId = getStoreId(request);
            flag=false;
        }
        SummaryResultDto dto=new SummaryResultDto();
        dto.setBegin(begin);
        dto.setEnd(end);
        dto.setStoreId(storeId);
        dto.setDateType(dateType);
        ComboSummaryViewDto combo=storeInfoService.getComboSummaryResult(dto, flag);
        ModelAndView mav = new ModelAndView(View.BusinessReport.COMBOSALES);
        mav.addObject("combo", combo);
        return mav;
    }

    /**
     * 商品销售
    * @author 乐建建
    * @date 2016年1月22日 下午8:26:59
    * @param begin 时间区间起点
    * @param end 时间区间终点
    * @param storeId 门店id
    * @param dateType 日期类型 日 周 月 年
    * @param request 页面请求
    * @param response 返回的相应结果
    * @return ModelAndView
    */
    @RequestMapping(value = Url.Businessreport.COMMODITYSALES)
    public ModelAndView goodsSalesView(@RequestParam(required=false) Integer storeId, String begin, String end, Integer dateType, 
            HttpServletRequest request, HttpServletResponse response){
        
        boolean flag=true;
        if (storeId == null || storeId==0) {
            storeId = getStoreId(request);
            flag=false;
        }
        
        SummaryResultDto dto=new SummaryResultDto();
        dto.setBegin(begin);
        dto.setEnd(end);
        dto.setDateType(dateType);
        dto.setStoreId(storeId);
        dto.setOrderType(2);
        CommoditySalesDto resultDto=storeInfoService.getCommodityResult(dto, flag);         
        ModelAndView mav = new ModelAndView(View.BusinessReport.GOODSSALES);
        mav.addObject("commodityResult", resultDto);
        return mav;
    }

    /**
    * 劳动业绩分部门排行
    * @author 乐建建
    * @date 2016年1月26日 下午8:19:43
    * @param dateType 日期区间类型
    * @param deptId 部门id
    * @param deptName 部门名称
    * @param request 页面请求
    * @param response 响应
    * @return 部门项目排行列表
    */
    @RequestMapping(value = Url.Businessreport.LABORPERFORMANCERANKBYDEPT, method = RequestMethod.POST)
    @ResponseBody
    public List<ProjectLaborRank> getDeptProjectRank(Integer dateType,
            Integer deptId, String deptName, HttpServletRequest request,
            HttpServletResponse response) {
        return storeInfoService.getLaborPerformanceRankByDeptAndDate(dateType,
                deptId, deptName, getStoreId(request));
    }

    /**
     * 劳动业绩分销量(销售额)排行
    * @author 乐建建
    * @date 2016年1月27日 下午2:19:40
    * @param begin 开始日期
    * @param end 结束日期
    * @param storeId 当前传入的门店id
    * @param dateType 日期区间类型
    * @param request 页面请求
    * @param response 响应
    * @return 排序后的排行数据
    */
    @RequestMapping(value = Url.Businessreport.LABORPERFORMANCERANKBYNUM, method = RequestMethod.POST)
    @ResponseBody
    public List<ProjectLaborRank> getDeptProjectRankByNum(Integer storeId,
            String begin, String end, Integer dateType,
            HttpServletRequest request, HttpServletResponse response) {
        if (storeId==null){
            storeId=getStoreId(request);
        }
        DeptLaborSummaryDto summaryDto = storeInfoService.getProjectRankByNum(
                1, begin, end, storeId, dateType);

        List<ProjectLaborRank> list = summaryDto.getProjectLaborAchievement();
        return list;
    }

    /**
     * 套餐分部门排行
    * @author 乐建建
    * @date 2016年1月27日 下午5:43:34
    * @param dateType 日期区间类型
    * @param deptId 部门id
    * @param deptName 部门名称
    * @param request 页面请求
    * @param response 响应
    * @return List<ComboSummaryDto>
    */
    @RequestMapping(value = Url.Businessreport.COMBORANKBYDEPT, method = RequestMethod.POST)
    @ResponseBody
    public List<ComboSummaryDto> getDeptComboRank(Integer dateType,
            Integer deptId, String deptName, HttpServletRequest request,
            HttpServletResponse response) {
        return storeInfoService.getComboRankByDept(dateType, deptId, deptName);
    }

    /**
     * 商品销售分部门排行
    * @author 乐建建
    * @date 2016年1月28日 上午11:24:14
    * @param dateType 日期区间类型
    * @param deptId 部门id
    * @param deptName 部门名称
    * @param request 页面请求
    * @param response 响应
    * @return  List<GoodSalesSummaryDto>
    */
    @RequestMapping(value = Url.Businessreport.GOODSRANKBYDEPT, method = RequestMethod.POST)
    @ResponseBody
    public List<GoodSalesSummaryDto> getDeptGoodsRank(Integer dateType,
            Integer deptId, String deptName, HttpServletRequest request,
            HttpServletResponse response) {

        return storeInfoService.getGoodRankByDept(dateType, deptId, deptName);
    }

    /**
     * 套餐销售按照销量或者销售额排行
    * @author 乐建建
    * @date 2016年1月27日 下午7:41:36
    * @param storeId 当前传入的门店id
    * @param begin 开始日期
    * @param end 结束日期
    * @param dateType 日期区间类型
    * @param request 页面请求
    * @param response 响应
    * @return List<ComboSummaryDto>
    */
    @RequestMapping(value = Url.Businessreport.COMBORANKBYNUMORSALES, method = RequestMethod.POST)
    @ResponseBody
    public List<ComboSummaryDto> getDeptComboRankBySales(Integer storeId,
            String begin, String end, Integer dateType,
            HttpServletRequest request, HttpServletResponse response) {
        if (storeId == null){
            storeId=getStoreId(request);
        }
        ComboSummaryViewDto dto = storeInfoService.getComboRankBySalesOrAmt(
                1, begin, end, storeId , dateType);
        return dto.getComboRank();

    }

    /**
    * 商品销售按照销量或者销售额排行
    * @author 乐建建
    * @date 2016年1月28日 下午12:09:04
    * @param storeId 当前传入的门店id
    * @param begin 开始日期
    * @param end 结束日期
    * @param dateType 日期区间类型
    * @param request 页面请求
    * @param response 响应
    * @return  List<GoodSalesSummaryDto>
    */
    @RequestMapping(value = Url.Businessreport.GOODSRANKBYCNTORAMT, method = RequestMethod.POST)
    @ResponseBody
    public List<GoodSalesSummaryDto> getGoodsRankByAmtOrCnt(Integer storeId,
            String begin, String end, Integer dateType,
            HttpServletRequest request, HttpServletResponse response) {
        if (storeId==null){
            storeId=getStoreId(request);
        }
        CommoditySalesDto result = storeInfoService.getGoodsRankBySalesOrAmt(
                1, begin, end, storeId, dateType);
        return result.getGoodRank();
    }
    
    /**
    * @author 乐建建
    * @date 2016年3月1日 下午4:46:07
    * @param month 跨店对账的月份数据
    * @param request 前端页面请求
    * @param response 响应数据
    * @return ModelAndView
    */
    @RequestMapping(value= Url.Businessreport.CROSSSHOPRECONCILIATIONBYMONTH)
    public ModelAndView getCrossShopResult(@RequestParam(value="month", required=false)String month,
            HttpServletRequest request, HttpServletResponse response){
        int storeId=getStoreId(request);
        return businessReporter.getStoreReconciliation(storeId, month);
    }
    
    /**
    * @author 乐建建
    * @date 2016年3月2日 下午3:15:59
    * @param request 页面请求
    * @param response 返回响应
    * @param thatStoreId 他店id
    * @param month 传入的月份数据
    * @param type (1 他店会员本店刷卡消费 2 他店会员本店充值 3 本店会员他店刷卡消费 4 本店会员他店充值)
    * @return List<DifferentStoreCardConsumeDto>
    */
    @RequestMapping(value=Url.Businessreport.CROSSSHOPCONSUMEDETAIL, method=RequestMethod.POST)
    @ResponseBody
    public BaseDto getShopReconciliation(HttpServletRequest request, HttpServletResponse response, 
            Integer thatStoreId, String month, Integer type){
        Integer thisStoreId=getStoreId(request);
        return businessReporter.getCrossShopConsumedDetail(thisStoreId, thatStoreId, month, type);
    }
    
    /**
    * @author 乐建建
    * @date 2016年3月4日 下午5:45:03
    * @param request 页面请求
    * @param response 响应
    * @param arrayObjStr 页面封装的json串
    * @return BaseDto
    */
    @RequestMapping(value=Url.Businessreport.CROSSSHOPDETAILUPDATE, method=RequestMethod.POST)
    @ResponseBody
    public BaseDto updateCrossShopConsumedDetail(HttpServletRequest request, HttpServletResponse response, String arrayObjStr){
        JSONArray arrayObj =JSONArray.fromObject(arrayObjStr);
        List<DifferentStoreCardConsumeDto> list=businessReporter.getDetailData(arrayObj);
        int num=businessReporter.updateUserSubmitData(list);
        BaseDto dto=new BaseDto();
        if (num>0){
            dto.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
            dto.setMsg("success");
        }
        else {
            dto.setCode(App.System.API_RESULT_CODE_FOR_FAIL);
            dto.setMsg("fail");
        }
        return dto;
    }
    
    /**
    * @author 乐建建
    * @date 2016年3月2日 下午7:33:52
    * @param request 页面请求
    * @param response 返回响应
    * @param thatStoreId 他店id
    * @param month 当前传入的月份数据
    * @return BaseDto
    */
    @RequestMapping(value=Url.Businessreport.VIEW_CHECK_RECONCILIATION, method=RequestMethod.POST)
    @ResponseBody
    public BaseDto getReconciliationResult(HttpServletRequest request, HttpServletResponse response, 
            Integer thatStoreId, String month){
        Integer thisStoreId=getStoreId(request);        
        return businessReporter.getCrossShopReconciliation(thisStoreId, thatStoreId, month, true);
    }
}
