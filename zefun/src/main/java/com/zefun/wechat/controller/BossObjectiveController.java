package com.zefun.wechat.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.web.controller.BaseController;
import com.zefun.web.dto.BaseDto;
import com.zefun.wechat.service.BossObjectiveService;



/**
 * 移动端老板端业绩报表
* @author 王大爷
* @date Oct 13, 2015 9:26:33 PM 
*/
@Controller
public class BossObjectiveController extends BaseController{
    
    /** 移动端员工的订单服务操作对象 */
    @Autowired
    private BossObjectiveService bossObjectiveService;
    
    /** 测试门店标识 */
//    private Integer ownerStoreId = 1013;
    
    /**
     * 业绩报表
    * @author 张进军
    * @date Oct 14, 2015 9:19:41 AM
    * @param storeId    门店标识
    * @param businessType   业务类型(1:会员,2:员工)
    * @param request    请求对象
    * @param response   响应对象
    * @return   全部订单页面
     */
    @RequestMapping(value = Url.Boss.VIEW_BOSS_OBJECTIVE)
    public ModelAndView allOrderView(@PathVariable(value = "storeId") String storeId, 
            @PathVariable(value = "businessType") Integer businessType,
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(storeId, businessType, request, response);
        if (openId == null) {
            return null;
        }
        Integer ownerStoreId = getStoreIdByOpenId(openId);
        return bossObjectiveService.initializePage(ownerStoreId);
    }
    
    /**
     * 现金业绩
    * @author 王大爷
    * @date 2016年1月16日 下午8:19:44
    * @param request    请求对象
    * @param response   响应对象
    * @param chooseType 选择类型（1、日，2、月，3、年
    * @param deptId 部门标识
    * @param pageType 选择页面类型
    * @return BaseDto
     */
    @RequestMapping(value = Url.Boss.ACTION_CASH_AMOUNT_SERVICE)
    @ResponseBody
    public BaseDto cashAmountService(HttpServletRequest request, HttpServletResponse response, 
            Integer chooseType, Integer deptId, Integer pageType) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        int ownerStoreId = getStoreIdByOpenId(openId);
        if (chooseType == null) {
            chooseType = 1;
        }
        if (pageType == 0) {
            return bossObjectiveService.cashAmountService(ownerStoreId, chooseType, deptId);
        }
        else if (pageType == 1) {
            return bossObjectiveService.cardAmountService(ownerStoreId, chooseType, deptId);
        }
        else if (pageType == 2){
            return bossObjectiveService.projectAmountService(ownerStoreId, chooseType, deptId);
        }
        else if (pageType == 3) {
            return bossObjectiveService.comboAmountService(ownerStoreId, chooseType, deptId);
        }
        else if (pageType == 4) {
            return bossObjectiveService.goodsAmountService(ownerStoreId, chooseType, deptId);
        }
        return null;
    }
    
    /**
     * 客情分析数据
    * @author 高国藩
    * @date 2016年1月21日 下午2:24:07
    * @param storeId    门店标识
    * @param businessType   业务类型(1:会员,2:员工)
    * @param request      请求
    * @param response     返回
    * @return             跳转页面
     */
    @RequestMapping(value = Url.Boss.VIEW_CUSTOMER_ANALYSIS, method = RequestMethod.GET)
    public ModelAndView viewCustomerAnalysis(@PathVariable(value = "storeId") String storeId, 
            @PathVariable(value = "businessType") Integer businessType,
            HttpServletRequest request, HttpServletResponse response){
        String openId = getOpenId(storeId, businessType, request, response);
        if (openId == null) {
            return null;
        }
        Integer ownerStoreId = getStoreIdByOpenId(openId);
        return bossObjectiveService.viewCustomerAnalysis(ownerStoreId);
    }
    
    /**
     * 根据年月日分别查询客情分析数据
    * @author 高国藩
    * @date 2016年1月21日 下午3:46:22
    * @param request                 请求信息
    * @param response                返回信息
    * @param serchType               查询方式
    * @return                        查询数据
     */
    @RequestMapping(value = Url.Boss.SERCH_CUSTOMER_ANALYSIS, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto changeCustomerAnalysis(HttpServletRequest request, HttpServletResponse response, String serchType) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer ownerStoreId = getStoreIdByOpenId(openId);
        return bossObjectiveService.changeCustomerAnalysis(ownerStoreId, serchType);
    }
    
    /**
     * 营业分析数据
    * @author 高国藩
    * @date 2016年1月21日 下午2:24:07
    * @param storeId    门店标识
    * @param businessType   业务类型(1:会员,2:员工)
    * @param request      请求
    * @param response     返回
    * @return             跳转页面
     * @throws ParseException  ParseException
     */
    @RequestMapping(value = Url.Boss.VIEW_BUSINESS_ANALYSIS, method = RequestMethod.GET)
    public ModelAndView viewBusinessAnalysis(@PathVariable String storeId, @PathVariable int businessType, 
            HttpServletRequest request, HttpServletResponse response) throws ParseException{
        String openId = getOpenId(storeId, businessType, request, response);
        if (openId == null) {
            return null;
        }
        Integer ownerStoreId = getStoreIdByOpenId(openId);
        return bossObjectiveService.viewBusinessAnalysis(ownerStoreId);
    }
    
    /**
     * 根据传入的时间进行动态查询
    * @author 高国藩
    * @date 2016年1月26日 下午8:20:46
    * @param request      请求数据
    * @param response     结果数据
    * @param serchDate    查询日期
    * @return             数据结果
     */
    @RequestMapping(value = Url.Boss.SERCH_BUSINESS_ANALYSIS, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto changeBusinessAnalysis(HttpServletRequest request, HttpServletResponse response, String serchDate) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer ownerStoreId = getStoreIdByOpenId(openId);
        return bossObjectiveService.changeBusinessAnalysis(ownerStoreId, serchDate);
    }
    
    /**
     * 根据传入的时间和codeName进行查询项目下的支出明细
    * @author 高国藩
    * @date 2016年1月26日 下午8:20:46
    * @param request      请求数据
    * @param response     结果数据
    * @param date         查询日期
    * @param codeName     支出类型
    * @return             数据结果
     */
    @RequestMapping(value = Url.Boss.SERCH_BUSINESS_PROJECT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto queryBusinessByCodeName(HttpServletRequest request, HttpServletResponse response, String date, String codeName) {
        String openId = getOpenId(2, request, response);
        if (openId == null) {
            return null;
        }
        Integer ownerStoreId = getStoreIdByOpenId(openId);
        return bossObjectiveService.queryBusinessByCodeName(ownerStoreId, date, codeName);
    }
}
